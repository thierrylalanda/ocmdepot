/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.IllegalOrphanException;
import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.PieceJointe;
import com.eh2s.ocm.Entity.StatutIncident;
import com.eh2s.ocm.Entity.Tclients;
import com.eh2s.ocm.Entity.Tincidents;
import com.eh2s.ocm.Entity.Tpriority;
import com.eh2s.ocm.Entity.Tsources;
import com.eh2s.ocm.Entity.Tsrubriques;
import com.eh2s.ocm.Entity.TtraitementTicket;
import com.eh2s.ocm.Entity.Tusers;
import com.eh2s.ocm.Entity.Userplainte;
import com.eh2s.ocm.UtilityFonctions.date_du_jour;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author messi
 */
@Stateless
public class TincidentsFacade extends AbstractFacade<Tincidents> implements TincidentsFacadeLocal {

    @PersistenceContext(unitName = "ccmanagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TincidentsFacade() {
        super(Tincidents.class);
    }

    @Override
    public Tincidents creer(Tincidents tincidents) throws RollbackFailureException, Exception {
        if (tincidents.getPieceJointeList() == null) {
            tincidents.setPieceJointeList(new ArrayList<PieceJointe>());
        }
        if (tincidents.getUserplainteList() == null) {
            tincidents.setUserplainteList(new ArrayList<Userplainte>());
        }
        if (tincidents.getTtraitementTicketList() == null) {
            tincidents.setTtraitementTicketList(new ArrayList<TtraitementTicket>());
        }
        try {
            Tpriority prioriteid = tincidents.getPrioriteid();
            if (prioriteid != null) {
                prioriteid = getEntityManager().getReference(prioriteid.getClass(), prioriteid.getId());
                tincidents.setPrioriteid(prioriteid);
            }
            Tsrubriques srubriqueid = tincidents.getSrubriqueid();
            if (srubriqueid != null) {
                srubriqueid = getEntityManager().getReference(srubriqueid.getClass(), srubriqueid.getId());
                tincidents.setSrubriqueid(srubriqueid);
            }
            Tsources sourceid = tincidents.getSourceid();
            if (sourceid != null) {
                sourceid = getEntityManager().getReference(sourceid.getClass(), sourceid.getId());
                tincidents.setSourceid(sourceid);
            }
            Tclients clientid = tincidents.getClientid();
            if (clientid != null) {
                clientid = getEntityManager().getReference(clientid.getClass(), clientid.getId());
                tincidents.setClientid(clientid);
            }
            Tusers creator = tincidents.getCreator();
            if (creator != null) {
                creator = getEntityManager().getReference(creator.getClass(), creator.getId());
                tincidents.setCreator(creator);
            }
            StatutIncident state = tincidents.getState();
            if (state != null) {
                state = getEntityManager().getReference(state.getClass(), state.getCode());
                tincidents.setState(state);
            }
            List<PieceJointe> attachedPieceJointeList = new ArrayList<PieceJointe>();
            for (PieceJointe pieceJointeListPieceJointeToAttach : tincidents.getPieceJointeList()) {
                pieceJointeListPieceJointeToAttach = getEntityManager().getReference(pieceJointeListPieceJointeToAttach.getClass(), pieceJointeListPieceJointeToAttach.getIdPj());
                attachedPieceJointeList.add(pieceJointeListPieceJointeToAttach);
            }
            tincidents.setPieceJointeList(attachedPieceJointeList);
            List<Userplainte> attachedUserplainteList = new ArrayList<Userplainte>();
            for (Userplainte userplainteListUserplainteToAttach : tincidents.getUserplainteList()) {
                userplainteListUserplainteToAttach = getEntityManager().getReference(userplainteListUserplainteToAttach.getClass(), userplainteListUserplainteToAttach.getId());
                attachedUserplainteList.add(userplainteListUserplainteToAttach);
            }
            tincidents.setUserplainteList(attachedUserplainteList);
            List<TtraitementTicket> attachedTtraitementTicketList = new ArrayList<TtraitementTicket>();
            for (TtraitementTicket ttraitementTicketListTtraitementTicketToAttach : tincidents.getTtraitementTicketList()) {
                ttraitementTicketListTtraitementTicketToAttach = getEntityManager().getReference(ttraitementTicketListTtraitementTicketToAttach.getClass(), ttraitementTicketListTtraitementTicketToAttach.getId());
                attachedTtraitementTicketList.add(ttraitementTicketListTtraitementTicketToAttach);
            }
            tincidents.setTtraitementTicketList(attachedTtraitementTicketList);
            Tincidents incidents = getEntityManager().merge(tincidents);
            if (prioriteid != null) {
                prioriteid.getTincidentsList().add(incidents);
                prioriteid = getEntityManager().merge(prioriteid);
            }
            if (srubriqueid != null) {
                srubriqueid.getTincidentsList().add(incidents);
                srubriqueid = getEntityManager().merge(srubriqueid);
            }
            if (sourceid != null) {
                sourceid.getTincidentsList().add(incidents);
                sourceid = getEntityManager().merge(sourceid);
            }
            if (clientid != null) {
                clientid.getTincidentsList().add(incidents);
                clientid = getEntityManager().merge(clientid);
            }
            if (creator != null) {
                creator.getTincidentsList().add(incidents);
                creator = getEntityManager().merge(creator);
            }
            if (state != null) {
                state.getTincidentsList().add(tincidents);
                state = getEntityManager().merge(state);
            }
            for (PieceJointe pieceJointeListPieceJointe : incidents.getPieceJointeList()) {
                Tincidents oldIncidentOfPieceJointeListPieceJointe = pieceJointeListPieceJointe.getIncident();
                pieceJointeListPieceJointe.setIncident(incidents);
                pieceJointeListPieceJointe = getEntityManager().merge(pieceJointeListPieceJointe);
                if (oldIncidentOfPieceJointeListPieceJointe != null) {
                    oldIncidentOfPieceJointeListPieceJointe.getPieceJointeList().remove(pieceJointeListPieceJointe);
                    oldIncidentOfPieceJointeListPieceJointe = getEntityManager().merge(oldIncidentOfPieceJointeListPieceJointe);
                }
            }
            for (Userplainte userplainteListUserplainte : incidents.getUserplainteList()) {
                Tincidents oldIdplainteOfUserplainteListUserplainte = userplainteListUserplainte.getIdplainte();
                userplainteListUserplainte.setIdplainte(incidents);
                userplainteListUserplainte = getEntityManager().merge(userplainteListUserplainte);
                if (oldIdplainteOfUserplainteListUserplainte != null) {
                    oldIdplainteOfUserplainteListUserplainte.getUserplainteList().remove(userplainteListUserplainte);
                    oldIdplainteOfUserplainteListUserplainte = getEntityManager().merge(oldIdplainteOfUserplainteListUserplainte);
                }
            }
            for (TtraitementTicket ttraitementTicketListTtraitementTicket : incidents.getTtraitementTicketList()) {
                Tincidents oldIncidentOfTtraitementTicketListTtraitementTicket = ttraitementTicketListTtraitementTicket.getIncident();
                ttraitementTicketListTtraitementTicket.setIncident(incidents);
                ttraitementTicketListTtraitementTicket = getEntityManager().merge(ttraitementTicketListTtraitementTicket);
                if (oldIncidentOfTtraitementTicketListTtraitementTicket != null) {
                    oldIncidentOfTtraitementTicketListTtraitementTicket.getTtraitementTicketList().remove(ttraitementTicketListTtraitementTicket);
                    oldIncidentOfTtraitementTicketListTtraitementTicket = getEntityManager().merge(oldIncidentOfTtraitementTicketListTtraitementTicket);
                }
            }
            return incidents;
        } catch (Exception ex) {
            throw new RollbackFailureException("une erreur est survenue la plainte du client n'a pas été enregistrer.", ex);

        }
    }

    @Override
    public Tincidents MisAJour(Tincidents tincidents) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        try {
            Tincidents persistentTincidents = getEntityManager().find(Tincidents.class, tincidents.getId());
            Tpriority prioriteidOld = persistentTincidents.getPrioriteid();
            Tpriority prioriteidNew = tincidents.getPrioriteid();
            Tsrubriques srubriqueidOld = persistentTincidents.getSrubriqueid();
            Tsrubriques srubriqueidNew = tincidents.getSrubriqueid();
            Tsources sourceidOld = persistentTincidents.getSourceid();
            Tsources sourceidNew = tincidents.getSourceid();
            Tclients clientidOld = persistentTincidents.getClientid();
            Tclients clientidNew = tincidents.getClientid();
            Tusers creatorOld = persistentTincidents.getCreator();
            Tusers creatorNew = tincidents.getCreator();
            StatutIncident stateOld = persistentTincidents.getState();
            StatutIncident stateNew = tincidents.getState();
            List<PieceJointe> pieceJointeListOld = persistentTincidents.getPieceJointeList();
            List<PieceJointe> pieceJointeListNew = tincidents.getPieceJointeList();
            List<Userplainte> userplainteListOld = persistentTincidents.getUserplainteList();
            List<Userplainte> userplainteListNew = tincidents.getUserplainteList();
            List<TtraitementTicket> ttraitementTicketListOld = persistentTincidents.getTtraitementTicketList();
            List<TtraitementTicket> ttraitementTicketListNew = tincidents.getTtraitementTicketList();
            List<String> illegalOrphanMessages = null;
            for (PieceJointe pieceJointeListOldPieceJointe : pieceJointeListOld) {
                if (!pieceJointeListNew.contains(pieceJointeListOldPieceJointe)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain PieceJointe " + pieceJointeListOldPieceJointe + " since its incident field is not nullable.");
                }
            }
            for (Userplainte userplainteListOldUserplainte : userplainteListOld) {
                if (!userplainteListNew.contains(userplainteListOldUserplainte)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Userplainte " + userplainteListOldUserplainte + " since its idplainte field is not nullable.");
                }
            }
            for (TtraitementTicket ttraitementTicketListOldTtraitementTicket : ttraitementTicketListOld) {
                if (!ttraitementTicketListNew.contains(ttraitementTicketListOldTtraitementTicket)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TtraitementTicket " + ttraitementTicketListOldTtraitementTicket + " since its incident field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (prioriteidNew != null) {
                prioriteidNew = getEntityManager().getReference(prioriteidNew.getClass(), prioriteidNew.getId());
                tincidents.setPrioriteid(prioriteidNew);
            }
            if (srubriqueidNew != null) {
                srubriqueidNew = getEntityManager().getReference(srubriqueidNew.getClass(), srubriqueidNew.getId());
                tincidents.setSrubriqueid(srubriqueidNew);
            }
            if (sourceidNew != null) {
                sourceidNew = getEntityManager().getReference(sourceidNew.getClass(), sourceidNew.getId());
                tincidents.setSourceid(sourceidNew);
            }
            if (clientidNew != null) {
                clientidNew = getEntityManager().getReference(clientidNew.getClass(), clientidNew.getId());
                tincidents.setClientid(clientidNew);
            }
            if (creatorNew != null) {
                creatorNew = getEntityManager().getReference(creatorNew.getClass(), creatorNew.getId());
                tincidents.setCreator(creatorNew);
            }
            if (stateNew != null) {
                stateNew = getEntityManager().getReference(stateNew.getClass(), stateNew.getCode());
                tincidents.setState(stateNew);
            }
            List<PieceJointe> attachedPieceJointeListNew = new ArrayList<PieceJointe>();
            for (PieceJointe pieceJointeListNewPieceJointeToAttach : pieceJointeListNew) {
                pieceJointeListNewPieceJointeToAttach = getEntityManager().getReference(pieceJointeListNewPieceJointeToAttach.getClass(), pieceJointeListNewPieceJointeToAttach.getIdPj());
                attachedPieceJointeListNew.add(pieceJointeListNewPieceJointeToAttach);
            }
            pieceJointeListNew = attachedPieceJointeListNew;
            tincidents.setPieceJointeList(pieceJointeListNew);
            List<Userplainte> attachedUserplainteListNew = new ArrayList<Userplainte>();
            for (Userplainte userplainteListNewUserplainteToAttach : userplainteListNew) {
                userplainteListNewUserplainteToAttach = getEntityManager().getReference(userplainteListNewUserplainteToAttach.getClass(), userplainteListNewUserplainteToAttach.getId());
                attachedUserplainteListNew.add(userplainteListNewUserplainteToAttach);
            }
            userplainteListNew = attachedUserplainteListNew;
            tincidents.setUserplainteList(userplainteListNew);
            List<TtraitementTicket> attachedTtraitementTicketListNew = new ArrayList<TtraitementTicket>();
            for (TtraitementTicket ttraitementTicketListNewTtraitementTicketToAttach : ttraitementTicketListNew) {
                ttraitementTicketListNewTtraitementTicketToAttach = getEntityManager().getReference(ttraitementTicketListNewTtraitementTicketToAttach.getClass(), ttraitementTicketListNewTtraitementTicketToAttach.getId());
                attachedTtraitementTicketListNew.add(ttraitementTicketListNewTtraitementTicketToAttach);
            }
            ttraitementTicketListNew = attachedTtraitementTicketListNew;
            tincidents.setTtraitementTicketList(ttraitementTicketListNew);
            tincidents = getEntityManager().merge(tincidents);
            if (prioriteidOld != null && !prioriteidOld.equals(prioriteidNew)) {
                prioriteidOld.getTincidentsList().remove(tincidents);
                prioriteidOld = getEntityManager().merge(prioriteidOld);
            }
            if (prioriteidNew != null && !prioriteidNew.equals(prioriteidOld)) {
                prioriteidNew.getTincidentsList().add(tincidents);
                prioriteidNew = getEntityManager().merge(prioriteidNew);
            }
            if (srubriqueidOld != null && !srubriqueidOld.equals(srubriqueidNew)) {
                srubriqueidOld.getTincidentsList().remove(tincidents);
                srubriqueidOld = getEntityManager().merge(srubriqueidOld);
            }
            if (srubriqueidNew != null && !srubriqueidNew.equals(srubriqueidOld)) {
                srubriqueidNew.getTincidentsList().add(tincidents);
                srubriqueidNew = getEntityManager().merge(srubriqueidNew);
            }
            if (sourceidOld != null && !sourceidOld.equals(sourceidNew)) {
                sourceidOld.getTincidentsList().remove(tincidents);
                sourceidOld = getEntityManager().merge(sourceidOld);
            }
            if (sourceidNew != null && !sourceidNew.equals(sourceidOld)) {
                sourceidNew.getTincidentsList().add(tincidents);
                sourceidNew = getEntityManager().merge(sourceidNew);
            }
            if (clientidOld != null && !clientidOld.equals(clientidNew)) {
                clientidOld.getTincidentsList().remove(tincidents);
                clientidOld = getEntityManager().merge(clientidOld);
            }
            if (clientidNew != null && !clientidNew.equals(clientidOld)) {
                clientidNew.getTincidentsList().add(tincidents);
                clientidNew = getEntityManager().merge(clientidNew);
            }
            if (creatorOld != null && !creatorOld.equals(creatorNew)) {
                creatorOld.getTincidentsList().remove(tincidents);
                creatorOld = getEntityManager().merge(creatorOld);
            }
            if (creatorNew != null && !creatorNew.equals(creatorOld)) {
                creatorNew.getTincidentsList().add(tincidents);
                creatorNew = getEntityManager().merge(creatorNew);
            }
            if (stateOld != null && !stateOld.equals(stateNew)) {
                stateOld.getTincidentsList().remove(tincidents);
                stateOld = getEntityManager().merge(stateOld);
            }
            if (stateNew != null && !stateNew.equals(stateOld)) {
                stateNew.getTincidentsList().add(tincidents);
                stateNew = getEntityManager().merge(stateNew);
            }
            for (PieceJointe pieceJointeListNewPieceJointe : pieceJointeListNew) {
                if (!pieceJointeListOld.contains(pieceJointeListNewPieceJointe)) {
                    Tincidents oldIncidentOfPieceJointeListNewPieceJointe = pieceJointeListNewPieceJointe.getIncident();
                    pieceJointeListNewPieceJointe.setIncident(tincidents);
                    pieceJointeListNewPieceJointe = getEntityManager().merge(pieceJointeListNewPieceJointe);
                    if (oldIncidentOfPieceJointeListNewPieceJointe != null && !oldIncidentOfPieceJointeListNewPieceJointe.equals(tincidents)) {
                        oldIncidentOfPieceJointeListNewPieceJointe.getPieceJointeList().remove(pieceJointeListNewPieceJointe);
                        oldIncidentOfPieceJointeListNewPieceJointe = getEntityManager().merge(oldIncidentOfPieceJointeListNewPieceJointe);
                    }
                }
            }
            for (Userplainte userplainteListNewUserplainte : userplainteListNew) {
                if (!userplainteListOld.contains(userplainteListNewUserplainte)) {
                    Tincidents oldIdplainteOfUserplainteListNewUserplainte = userplainteListNewUserplainte.getIdplainte();
                    userplainteListNewUserplainte.setIdplainte(tincidents);
                    userplainteListNewUserplainte = getEntityManager().merge(userplainteListNewUserplainte);
                    if (oldIdplainteOfUserplainteListNewUserplainte != null && !oldIdplainteOfUserplainteListNewUserplainte.equals(tincidents)) {
                        oldIdplainteOfUserplainteListNewUserplainte.getUserplainteList().remove(userplainteListNewUserplainte);
                        oldIdplainteOfUserplainteListNewUserplainte = getEntityManager().merge(oldIdplainteOfUserplainteListNewUserplainte);
                    }
                }
            }
            for (TtraitementTicket ttraitementTicketListNewTtraitementTicket : ttraitementTicketListNew) {
                if (!ttraitementTicketListOld.contains(ttraitementTicketListNewTtraitementTicket)) {
                    Tincidents oldIncidentOfTtraitementTicketListNewTtraitementTicket = ttraitementTicketListNewTtraitementTicket.getIncident();
                    ttraitementTicketListNewTtraitementTicket.setIncident(tincidents);
                    ttraitementTicketListNewTtraitementTicket = getEntityManager().merge(ttraitementTicketListNewTtraitementTicket);
                    if (oldIncidentOfTtraitementTicketListNewTtraitementTicket != null && !oldIncidentOfTtraitementTicketListNewTtraitementTicket.equals(tincidents)) {
                        oldIncidentOfTtraitementTicketListNewTtraitementTicket.getTtraitementTicketList().remove(ttraitementTicketListNewTtraitementTicket);
                        oldIncidentOfTtraitementTicketListNewTtraitementTicket = getEntityManager().merge(oldIncidentOfTtraitementTicketListNewTtraitementTicket);
                    }
                }
            }
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tincidents.getId();
                if (findTincidents(id) == null) {
                    throw new NonexistentEntityException("l'incident avec l'id " + id + " n'éxiste pas.");
                }
            }
            throw new RollbackFailureException("une erreur est survenue la plainte du client n'a pas été mis à jour.", ex);
        }
        return tincidents;
    }

    @Override
    public void Supprimer(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        try {
            Tincidents tincidents;
            try {
                tincidents = getEntityManager().getReference(Tincidents.class, id);
                tincidents.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tincidents with id " + id + " no longer exists.", enfe);
            }

            Tpriority prioriteid = tincidents.getPrioriteid();
            if (prioriteid != null) {
                prioriteid.getTincidentsList().remove(tincidents);
                prioriteid = getEntityManager().merge(prioriteid);
            }
            Tsrubriques srubriqueid = tincidents.getSrubriqueid();
            if (srubriqueid != null) {
                srubriqueid.getTincidentsList().remove(tincidents);
                srubriqueid = getEntityManager().merge(srubriqueid);
            }
            Tsources sourceid = tincidents.getSourceid();
            if (sourceid != null) {
                sourceid.getTincidentsList().remove(tincidents);
                sourceid = getEntityManager().merge(sourceid);
            }
            Tclients clientid = tincidents.getClientid();
            if (clientid != null) {
                clientid.getTincidentsList().remove(tincidents);
                clientid = getEntityManager().merge(clientid);
            }
            Tusers creator = tincidents.getCreator();
            if (creator != null) {
                creator.getTincidentsList().remove(tincidents);
                creator = getEntityManager().merge(creator);
            }
            StatutIncident state = tincidents.getState();
            if (state != null) {
                state.getTincidentsList().remove(tincidents);
                state = getEntityManager().merge(state);
            }
            getEntityManager().remove(tincidents);
        } catch (NonexistentEntityException ex) {
            throw new RollbackFailureException("une erreur est survenue la plainte du client n'a pas été supprimer", ex);
        }
    }

    @Override
    public List<Tincidents> findTincidentsEntities() {
        return findTincidentsEntities(true, -1, -1);
    }

    @Override
    public List<Tincidents> findTincidentsEntities(int maxResults, int firstResult) {
        return findTincidentsEntities(false, maxResults, firstResult);
    }

    private List<Tincidents> findTincidentsEntities(boolean all, int maxResults, int firstResult) {
         getEntityManager().setProperty("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Tincidents.class));
        Query q = getEntityManager().createQuery(cq);
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    @Override
    public Tincidents findTincidents(Integer id) {
        getEntityManager().setProperty("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        return getEntityManager().find(Tincidents.class, id);
    }

    @Override
    public int getTincidentsCount() {
         getEntityManager().setProperty("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<Tincidents> rt = cq.from(Tincidents.class);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();

    }

    @Override
    public List<Tincidents> findTincidentsEntitiesByPeriode(Date d1, Date d2, String societe) {
        getEntityManager().setProperty("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        Query query = getEntityManager().createNamedQuery("Tincidents.findByPeriode");
        query.setParameter("d1", d1);
        query.setParameter("d2", d2);
        query.setParameter("imma", societe);
        return query.getResultList();
    }

    @Override
    public List<Tincidents> findTicketHorsDelais(String societe) {
        getEntityManager().setProperty("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        Query query = getEntityManager().createNamedQuery("Tincidents.findByDateHope");
        query.setParameter("d1", date_du_jour.dateJourUniqueDate());
        query.setParameter("aff", 1);
        query.setParameter("status", 501);
        query.setParameter("imma", societe);
        return query.getResultList();
    }

    @Override
    public List<Tincidents> findTicketByAnnee(int annee, String societe) {
        getEntityManager().setProperty("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        Query query = getEntityManager().createNamedQuery("Tincidents.findByAnnee");
        query.setParameter("annee", annee);
        query.setParameter("imma", societe);
        return query.getResultList();
    }

    @Override
    public List<Tincidents> findAll(String societe) {
        getEntityManager().setProperty("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        Query q = getEntityManager().createNamedQuery("Tincidents.findAll");
        q.setParameter("imma", societe);
        return q.getResultList();
    }

    @Override
    public List<Tincidents> findAllByClient(int client) {
        getEntityManager().setProperty("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        Query q = getEntityManager().createNamedQuery("Tincidents.findByClient");
        q.setParameter("cli", client);
        return q.getResultList();
    }

}
