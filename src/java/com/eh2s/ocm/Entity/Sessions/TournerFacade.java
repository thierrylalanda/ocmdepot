/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.AffectTournerUser;
import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Magasin;
import com.eh2s.ocm.Entity.Societe;
import com.eh2s.ocm.Entity.Tclients;
import com.eh2s.ocm.Entity.Tlignecommande;
import com.eh2s.ocm.Entity.Tourner;
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

/**
 *
 * @author messi01
 */
@Stateless
public class TournerFacade extends AbstractFacade<Tourner> implements TournerFacadeLocal {

    @PersistenceContext(unitName = "ccmanagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TournerFacade() {
        super(Tourner.class);
    }

    @Override
    public Tourner findByNum(String numtr) {
        Query q = getEntityManager().createNamedQuery("Tourner.findByNumc");
        q.setParameter("numc", numtr);
        return (Tourner) q.getSingleResult();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public Tourner creer(Tourner tourner) throws RollbackFailureException, Exception {
        if (tourner.getTclientsList() == null) {
            tourner.setTclientsList(new ArrayList<Tclients>());
        }
        if (tourner.getTlignecommandeList() == null) {
            tourner.setTlignecommandeList(new ArrayList<Tlignecommande>());
        }
        if (tourner.getAffectTournerUserList() == null) {
            tourner.setAffectTournerUserList(new ArrayList<AffectTournerUser>());
        }
        try {
            Societe societe = tourner.getSociete();
            if (societe != null) {
                societe = getEntityManager().getReference(societe.getClass(), societe.getId());
                tourner.setSociete(societe);
            }
            Magasin magasin = tourner.getMagasin();
            if (magasin != null) {
                magasin = getEntityManager().getReference(magasin.getClass(), magasin.getId());
                tourner.setMagasin(magasin);
            }
            List<Tclients> attachedTclientsList = new ArrayList<Tclients>();
            for (Tclients tclientsListTclientsToAttach : tourner.getTclientsList()) {
                tclientsListTclientsToAttach = getEntityManager().getReference(tclientsListTclientsToAttach.getClass(), tclientsListTclientsToAttach.getId());
                attachedTclientsList.add(tclientsListTclientsToAttach);
            }
            tourner.setTclientsList(attachedTclientsList);
            List<Tlignecommande> attachedTlignecommandeList = new ArrayList<Tlignecommande>();
            for (Tlignecommande tlignecommandeListTlignecommandeToAttach : tourner.getTlignecommandeList()) {
                tlignecommandeListTlignecommandeToAttach = getEntityManager().getReference(tlignecommandeListTlignecommandeToAttach.getClass(), tlignecommandeListTlignecommandeToAttach.getId());
                attachedTlignecommandeList.add(tlignecommandeListTlignecommandeToAttach);
            }
            tourner.setTlignecommandeList(attachedTlignecommandeList);
            List<AffectTournerUser> attachedAffectTournerUserList = new ArrayList<AffectTournerUser>();
            for (AffectTournerUser affectTournerUserListAffectTournerUserToAttach : tourner.getAffectTournerUserList()) {
                affectTournerUserListAffectTournerUserToAttach = getEntityManager().getReference(affectTournerUserListAffectTournerUserToAttach.getClass(), affectTournerUserListAffectTournerUserToAttach.getId());
                attachedAffectTournerUserList.add(affectTournerUserListAffectTournerUserToAttach);
            }
            tourner.setAffectTournerUserList(attachedAffectTournerUserList);
            tourner = getEntityManager().merge(tourner);
            if (societe != null) {
                societe.getTournerList().add(tourner);
                societe = getEntityManager().merge(societe);
            }
            if (magasin != null) {
                magasin.getTournerList().add(tourner);
                magasin = getEntityManager().merge(magasin);
            }
            for (Tclients tclientsListTclients : tourner.getTclientsList()) {
                Tourner oldTournerOfTclientsListTclients = tclientsListTclients.getTourner();
                tclientsListTclients.setTourner(tourner);
                tclientsListTclients = getEntityManager().merge(tclientsListTclients);
                if (oldTournerOfTclientsListTclients != null) {
                    oldTournerOfTclientsListTclients.getTclientsList().remove(tclientsListTclients);
                    oldTournerOfTclientsListTclients = getEntityManager().merge(oldTournerOfTclientsListTclients);
                }
            }
            for (Tlignecommande tlignecommandeListTlignecommande : tourner.getTlignecommandeList()) {
                Tourner oldTournerOfTlignecommandeListTlignecommande = tlignecommandeListTlignecommande.getTourner();
                tlignecommandeListTlignecommande.setTourner(tourner);
                tlignecommandeListTlignecommande = getEntityManager().merge(tlignecommandeListTlignecommande);
                if (oldTournerOfTlignecommandeListTlignecommande != null) {
                    oldTournerOfTlignecommandeListTlignecommande.getTlignecommandeList().remove(tlignecommandeListTlignecommande);
                    oldTournerOfTlignecommandeListTlignecommande = getEntityManager().merge(oldTournerOfTlignecommandeListTlignecommande);
                }
            }
            for (AffectTournerUser affectTournerUserListAffectTournerUser : tourner.getAffectTournerUserList()) {
                Tourner oldTournerOfAffectTournerUserListAffectTournerUser = affectTournerUserListAffectTournerUser.getTourner();
                affectTournerUserListAffectTournerUser.setTourner(tourner);
                affectTournerUserListAffectTournerUser = getEntityManager().merge(affectTournerUserListAffectTournerUser);
                if (oldTournerOfAffectTournerUserListAffectTournerUser != null) {
                    oldTournerOfAffectTournerUserListAffectTournerUser.getAffectTournerUserList().remove(affectTournerUserListAffectTournerUser);
                    oldTournerOfAffectTournerUserListAffectTournerUser = getEntityManager().merge(oldTournerOfAffectTournerUserListAffectTournerUser);
                }
            }
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);
        }
        return tourner;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public Tourner MisAJour(Tourner tourner) throws NonexistentEntityException, RollbackFailureException, Exception {
        try {
            Tourner persistentTourner = getEntityManager().find(Tourner.class, tourner.getId());
            Societe societeOld = persistentTourner.getSociete();
            Societe societeNew = tourner.getSociete();
            Magasin magasinOld = persistentTourner.getMagasin();
            Magasin magasinNew = tourner.getMagasin();
            List<Tclients> tclientsListOld = persistentTourner.getTclientsList();
            List<Tclients> tclientsListNew = tourner.getTclientsList();
            List<Tlignecommande> tlignecommandeListOld = persistentTourner.getTlignecommandeList();
            List<Tlignecommande> tlignecommandeListNew = tourner.getTlignecommandeList();
            List<AffectTournerUser> affectTournerUserListOld = persistentTourner.getAffectTournerUserList();
            List<AffectTournerUser> affectTournerUserListNew = tourner.getAffectTournerUserList();

            if (societeNew != null) {
                societeNew = getEntityManager().getReference(societeNew.getClass(), societeNew.getId());
                tourner.setSociete(societeNew);
            }
            List<Tclients> attachedTclientsListNew = new ArrayList<Tclients>();
            for (Tclients tclientsListNewTclientsToAttach : tclientsListNew) {
                tclientsListNewTclientsToAttach = getEntityManager().getReference(tclientsListNewTclientsToAttach.getClass(), tclientsListNewTclientsToAttach.getId());
                attachedTclientsListNew.add(tclientsListNewTclientsToAttach);
            }
            tclientsListNew = attachedTclientsListNew;
            tourner.setTclientsList(tclientsListNew);
            List<Tlignecommande> attachedTlignecommandeListNew = new ArrayList<Tlignecommande>();
            for (Tlignecommande tlignecommandeListNewTlignecommandeToAttach : tlignecommandeListNew) {
                tlignecommandeListNewTlignecommandeToAttach = getEntityManager().getReference(tlignecommandeListNewTlignecommandeToAttach.getClass(), tlignecommandeListNewTlignecommandeToAttach.getId());
                attachedTlignecommandeListNew.add(tlignecommandeListNewTlignecommandeToAttach);
            }
            tlignecommandeListNew = attachedTlignecommandeListNew;
            tourner.setTlignecommandeList(tlignecommandeListNew);
            List<AffectTournerUser> attachedAffectTournerUserListNew = new ArrayList<AffectTournerUser>();
            for (AffectTournerUser affectTournerUserListNewAffectTournerUserToAttach : affectTournerUserListNew) {
                affectTournerUserListNewAffectTournerUserToAttach = getEntityManager().getReference(affectTournerUserListNewAffectTournerUserToAttach.getClass(), affectTournerUserListNewAffectTournerUserToAttach.getId());
                attachedAffectTournerUserListNew.add(affectTournerUserListNewAffectTournerUserToAttach);
            }
            affectTournerUserListNew = attachedAffectTournerUserListNew;
            tourner.setAffectTournerUserList(affectTournerUserListNew);
            tourner = getEntityManager().merge(tourner);
            if (societeOld != null && !societeOld.equals(societeNew)) {
                societeOld.getTournerList().remove(tourner);
                societeOld = getEntityManager().merge(societeOld);
            }
            if (societeNew != null && !societeNew.equals(societeOld)) {
                societeNew.getTournerList().add(tourner);
                societeNew = getEntityManager().merge(societeNew);
            }
             if (magasinOld != null && !magasinOld.equals(magasinNew)) {
                magasinOld.getTournerList().remove(tourner);
                magasinOld = getEntityManager().merge(magasinOld);
            }
            if (magasinNew != null && !magasinNew.equals(magasinOld)) {
                magasinNew.getTournerList().add(tourner);
                magasinNew = getEntityManager().merge(magasinNew);
            }
            for (Tclients tclientsListOldTclients : tclientsListOld) {
                if (!tclientsListNew.contains(tclientsListOldTclients)) {
                    tclientsListOldTclients.setTourner(null);
                    tclientsListOldTclients = getEntityManager().merge(tclientsListOldTclients);
                }
            }
            for (Tclients tclientsListNewTclients : tclientsListNew) {
                if (!tclientsListOld.contains(tclientsListNewTclients)) {
                    Tourner oldTournerOfTclientsListNewTclients = tclientsListNewTclients.getTourner();
                    tclientsListNewTclients.setTourner(tourner);
                    tclientsListNewTclients = getEntityManager().merge(tclientsListNewTclients);
                    if (oldTournerOfTclientsListNewTclients != null && !oldTournerOfTclientsListNewTclients.equals(tourner)) {
                        oldTournerOfTclientsListNewTclients.getTclientsList().remove(tclientsListNewTclients);
                        oldTournerOfTclientsListNewTclients = getEntityManager().merge(oldTournerOfTclientsListNewTclients);
                    }
                }
            }
            for (Tlignecommande tlignecommandeListOldTlignecommande : tlignecommandeListOld) {
                if (!tlignecommandeListNew.contains(tlignecommandeListOldTlignecommande)) {
                    tlignecommandeListOldTlignecommande.setTourner(null);
                    tlignecommandeListOldTlignecommande = getEntityManager().merge(tlignecommandeListOldTlignecommande);
                }
            }
            for (Tlignecommande tlignecommandeListNewTlignecommande : tlignecommandeListNew) {
                if (!tlignecommandeListOld.contains(tlignecommandeListNewTlignecommande)) {
                    Tourner oldTournerOfTlignecommandeListNewTlignecommande = tlignecommandeListNewTlignecommande.getTourner();
                    tlignecommandeListNewTlignecommande.setTourner(tourner);
                    tlignecommandeListNewTlignecommande = getEntityManager().merge(tlignecommandeListNewTlignecommande);
                    if (oldTournerOfTlignecommandeListNewTlignecommande != null && !oldTournerOfTlignecommandeListNewTlignecommande.equals(tourner)) {
                        oldTournerOfTlignecommandeListNewTlignecommande.getTlignecommandeList().remove(tlignecommandeListNewTlignecommande);
                        oldTournerOfTlignecommandeListNewTlignecommande = getEntityManager().merge(oldTournerOfTlignecommandeListNewTlignecommande);
                    }
                }
            }
            for (AffectTournerUser affectTournerUserListNewAffectTournerUser : affectTournerUserListNew) {
                if (!affectTournerUserListOld.contains(affectTournerUserListNewAffectTournerUser)) {
                    Tourner oldTournerOfAffectTournerUserListNewAffectTournerUser = affectTournerUserListNewAffectTournerUser.getTourner();
                    affectTournerUserListNewAffectTournerUser.setTourner(tourner);
                    affectTournerUserListNewAffectTournerUser = getEntityManager().merge(affectTournerUserListNewAffectTournerUser);
                    if (oldTournerOfAffectTournerUserListNewAffectTournerUser != null && !oldTournerOfAffectTournerUserListNewAffectTournerUser.equals(tourner)) {
                        oldTournerOfAffectTournerUserListNewAffectTournerUser.getAffectTournerUserList().remove(affectTournerUserListNewAffectTournerUser);
                        oldTournerOfAffectTournerUserListNewAffectTournerUser = getEntityManager().merge(oldTournerOfAffectTournerUserListNewAffectTournerUser);
                    }
                }
            }
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
        return tourner;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void Supprimer(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        try {
            Tourner tourner;
            try {
                tourner = getEntityManager().getReference(Tourner.class, id);
                tourner.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tourner with id " + id + " no longer exists.", enfe);
            }

            Societe societe = tourner.getSociete();
            if (societe != null) {
                societe.getTournerList().remove(tourner);
                societe = getEntityManager().merge(societe);
            }
            List<Tclients> tclientsList = tourner.getTclientsList();
            for (Tclients tclientsListTclients : tclientsList) {
                tclientsListTclients.setTourner(null);
                tclientsListTclients = getEntityManager().merge(tclientsListTclients);
            }
            List<Tlignecommande> tlignecommandeList = tourner.getTlignecommandeList();
            for (Tlignecommande tlignecommandeListTlignecommande : tlignecommandeList) {
                tlignecommandeListTlignecommande.setTourner(null);
                tlignecommandeListTlignecommande = getEntityManager().merge(tlignecommandeListTlignecommande);
            }
            getEntityManager().remove(tourner);
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @Override
    public List<Tourner> findTournerEntities() {
        return findTournerEntities(true, -1, -1);
    }

    private List<Tourner> findTournerEntities(boolean all, int maxResults, int firstResult) {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Tourner.class));
        Query q = getEntityManager().createQuery(cq);
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    @Override
    public Tourner findTourner(Integer id) {
        return getEntityManager().find(Tourner.class, id);
    }

    @Override
    public List<Tourner> findAll(int societe) {
        Query q = getEntityManager().createNamedQuery("Tourner.findAllBySociete");
        q.setParameter("id", societe);
        return q.getResultList();
    }

}
