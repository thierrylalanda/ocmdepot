/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.IllegalOrphanException;
import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Societe;
import com.eh2s.ocm.Entity.Tclients;
import com.eh2s.ocm.Entity.Ttypeclients;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
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
public class TtypeclientsFacade extends AbstractFacade<Ttypeclients> implements TtypeclientsFacadeLocal {

    @PersistenceContext(unitName = "ccmanagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TtypeclientsFacade() {
        super(Ttypeclients.class);
    }

    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    @Override
    public Ttypeclients creer(Ttypeclients ttypeclients) throws RollbackFailureException, Exception {
        try {
            Societe societe = ttypeclients.getSociete();
            if (societe != null) {
                societe = getEntityManager().getReference(societe.getClass(), societe.getId());
                ttypeclients.setSociete(societe);
            }
            List<Tclients> attachedTclientsList = new ArrayList<Tclients>();
            ttypeclients.setTclientsList(attachedTclientsList);
            for (Tclients tclientsListTclientsToAttach : ttypeclients.getTclientsList()) {
                tclientsListTclientsToAttach = getEntityManager().getReference(tclientsListTclientsToAttach.getClass(), tclientsListTclientsToAttach.getId());
                attachedTclientsList.add(tclientsListTclientsToAttach);
            }
            ttypeclients.setTclientsList(attachedTclientsList);
            ttypeclients = getEntityManager().merge(ttypeclients);
            if (societe != null) {
                societe.getTtypeclientsList().add(ttypeclients);
                societe = getEntityManager().merge(societe);
            }
            for (Tclients tclientsListTclients : ttypeclients.getTclientsList()) {
                Ttypeclients oldTypeclientidOfTclientsListTclients = tclientsListTclients.getTypeclientid();
                tclientsListTclients.setTypeclientid(ttypeclients);
                tclientsListTclients = getEntityManager().merge(tclientsListTclients);
                if (oldTypeclientidOfTclientsListTclients != null) {
                    oldTypeclientidOfTclientsListTclients.getTclientsList().remove(tclientsListTclients);
                    oldTypeclientidOfTclientsListTclients = getEntityManager().merge(oldTypeclientidOfTclientsListTclients);
                }
            }
            return ttypeclients;
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    @Override
    public void MisAJour(Ttypeclients ttypeclients) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        try {
            Ttypeclients persistentTtypeclients = getEntityManager().find(Ttypeclients.class, ttypeclients.getId());
            Societe societeOld = persistentTtypeclients.getSociete();
            Societe societeNew = ttypeclients.getSociete();
            List<Tclients> tclientsListOld = persistentTtypeclients.getTclientsList();
            List<Tclients> tclientsListNew = ttypeclients.getTclientsList();

            if (societeNew != null) {
                societeNew = getEntityManager().getReference(societeNew.getClass(), societeNew.getId());
                ttypeclients.setSociete(societeNew);
            }
            List<Tclients> attachedTclientsListNew = new ArrayList<Tclients>();
            for (Tclients tclientsListNewTclientsToAttach : tclientsListNew) {
                tclientsListNewTclientsToAttach = getEntityManager().getReference(tclientsListNewTclientsToAttach.getClass(), tclientsListNewTclientsToAttach.getId());
                attachedTclientsListNew.add(tclientsListNewTclientsToAttach);
            }
            tclientsListNew = attachedTclientsListNew;
            ttypeclients.setTclientsList(tclientsListNew);
            ttypeclients = getEntityManager().merge(ttypeclients);
            if (societeOld != null && !societeOld.equals(societeNew)) {
                societeOld.getTtypeclientsList().remove(ttypeclients);
                societeOld = getEntityManager().merge(societeOld);
            }
            if (societeNew != null && !societeNew.equals(societeOld)) {
                societeNew.getTtypeclientsList().add(ttypeclients);
                societeNew = getEntityManager().merge(societeNew);
            }
            for (Tclients tclientsListNewTclients : tclientsListNew) {
                if (!tclientsListOld.contains(tclientsListNewTclients)) {
                    Ttypeclients oldTypeclientidOfTclientsListNewTclients = tclientsListNewTclients.getTypeclientid();
                    tclientsListNewTclients.setTypeclientid(ttypeclients);
                    tclientsListNewTclients = getEntityManager().merge(tclientsListNewTclients);
                    if (oldTypeclientidOfTclientsListNewTclients != null && !oldTypeclientidOfTclientsListNewTclients.equals(ttypeclients)) {
                        oldTypeclientidOfTclientsListNewTclients.getTclientsList().remove(tclientsListNewTclients);
                        oldTypeclientidOfTclientsListNewTclients = getEntityManager().merge(oldTypeclientidOfTclientsListNewTclients);
                    }
                }
            }

        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ttypeclients.getId();
                if (findTtypeclients(id) == null) {
                    throw new NonexistentEntityException("The ttypeclients with id " + id + " no longer exists.");
                }
            }
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    @Override
    public void Supprimer(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        Ttypeclients ttypeclients;
        try {
            ttypeclients = getEntityManager().getReference(Ttypeclients.class, id);
            ttypeclients.getId();
        } catch (EntityNotFoundException enfe) {
            throw new NonexistentEntityException("The ttypeclients with id " + id + " no longer exists.", enfe);
        }
        Societe societe = ttypeclients.getSociete();
        if (societe != null) {
            societe.getTtypeclientsList().remove(ttypeclients);
            societe = getEntityManager().merge(societe);
        }
        getEntityManager().remove(ttypeclients);
    }

    @Override
    public List<Ttypeclients> findTtypeclientsEntities() {
        return findTtypeclientsEntities(true, -1, -1);
    }

    @Override
    public List<Ttypeclients> findTtypeclientsEntities(int maxResults, int firstResult) {
        return findTtypeclientsEntities(false, maxResults, firstResult);
    }

    private List<Ttypeclients> findTtypeclientsEntities(boolean all, int maxResults, int firstResult) {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Ttypeclients.class));
        Query q = getEntityManager().createQuery(cq);
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    @Override
    public Ttypeclients findTtypeclients(Integer id) {
        return getEntityManager().find(Ttypeclients.class, id);
    }

    @Override
    public int getTtypeclientsCount() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<Ttypeclients> rt = cq.from(Ttypeclients.class);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    @Override
    public List<Ttypeclients> findAll(String societe) {
        Query q = getEntityManager().createNamedQuery("Ttypeclients.findAll");
        q.setParameter("imma", societe);
        return q.getResultList();
    }
}
