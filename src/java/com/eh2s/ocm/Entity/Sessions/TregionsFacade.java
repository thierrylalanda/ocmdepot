/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.IllegalOrphanException;
import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Magasin;
import com.eh2s.ocm.Entity.Societe;
import com.eh2s.ocm.Entity.Tdistricts;
import com.eh2s.ocm.Entity.Tregions;
import com.eh2s.ocm.Entity.Tsites;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class TregionsFacade extends AbstractFacade<Tregions> implements TregionsFacadeLocal {

    @PersistenceContext(unitName = "ccmanagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TregionsFacade() {
        super(Tregions.class);
    }

    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    @Override
    public Tregions creer(Tregions tregions) {
        try {
            Societe societe = tregions.getSociete();
            if (societe != null) {
                societe = getEntityManager().getReference(societe.getClass(), societe.getId());
                tregions.setSociete(societe);
            }
            List<Tsites> attachedTsitesList = new ArrayList<Tsites>();
            tregions.setTsitesList(attachedTsitesList);
            for (Tsites tsitesListTsitesToAttach : tregions.getTsitesList()) {
                tsitesListTsitesToAttach = getEntityManager().getReference(tsitesListTsitesToAttach.getClass(), tsitesListTsitesToAttach.getId());
                attachedTsitesList.add(tsitesListTsitesToAttach);
            }
            tregions.setTsitesList(attachedTsitesList);
            List<Tdistricts> attachedTdistrictsList = new ArrayList<Tdistricts>();
            tregions.setTdistrictsList(attachedTdistrictsList);
            for (Tdistricts tdistrictsListTdistrictsToAttach : tregions.getTdistrictsList()) {
                tdistrictsListTdistrictsToAttach = getEntityManager().getReference(tdistrictsListTdistrictsToAttach.getClass(), tdistrictsListTdistrictsToAttach.getId());
                attachedTdistrictsList.add(tdistrictsListTdistrictsToAttach);
            }
            tregions.setTdistrictsList(attachedTdistrictsList);
//            List<Magasin> attachedMagasinList = new ArrayList<Magasin>();
//            for (Magasin magasinListMagasinToAttach : tregions.getMagasinList()) {
//                magasinListMagasinToAttach = getEntityManager().getReference(magasinListMagasinToAttach.getClass(), magasinListMagasinToAttach.getId());
//                attachedMagasinList.add(magasinListMagasinToAttach);
//            }
//            tregions.setMagasinList(attachedMagasinList);
            tregions = getEntityManager().merge(tregions);
            if (societe != null) {
                societe.getTregionsList().add(tregions);
                societe = getEntityManager().merge(societe);
            }
            for (Tsites tsitesListTsites : tregions.getTsitesList()) {
                Tregions oldRegionidOfTsitesListTsites = tsitesListTsites.getRegionid();
                tsitesListTsites.setRegionid(tregions);
                tsitesListTsites = getEntityManager().merge(tsitesListTsites);
                if (oldRegionidOfTsitesListTsites != null) {
                    oldRegionidOfTsitesListTsites.getTsitesList().remove(tsitesListTsites);
                    oldRegionidOfTsitesListTsites = getEntityManager().merge(oldRegionidOfTsitesListTsites);
                }
            }
            for (Tdistricts tdistrictsListTdistricts : tregions.getTdistrictsList()) {
                Tregions oldRegionidOfTdistrictsListTdistricts = tdistrictsListTdistricts.getRegionid();
                tdistrictsListTdistricts.setRegionid(tregions);
                tdistrictsListTdistricts = getEntityManager().merge(tdistrictsListTdistricts);
                if (oldRegionidOfTdistrictsListTdistricts != null) {
                    oldRegionidOfTdistrictsListTdistricts.getTdistrictsList().remove(tdistrictsListTdistricts);
                    oldRegionidOfTdistrictsListTdistricts = getEntityManager().merge(oldRegionidOfTdistrictsListTdistricts);
                }
            }
//            for (Magasin magasinListMagasin : tregions.getMagasinList()) {
//                Tregions oldRegionOfMagasinListMagasin = magasinListMagasin.getRegion();
//                magasinListMagasin.setRegion(tregions);
//                magasinListMagasin = getEntityManager().merge(magasinListMagasin);
//                if (oldRegionOfMagasinListMagasin != null) {
//                    oldRegionOfMagasinListMagasin.getMagasinList().remove(magasinListMagasin);
//                    oldRegionOfMagasinListMagasin = getEntityManager().merge(oldRegionOfMagasinListMagasin);
//                }
//            }
            return tregions;
        } catch (Exception ex) {

            try {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);
            } catch (RollbackFailureException ex1) {
                Logger.getLogger(TregionsFacade.class.getName()).log(Level.SEVERE, null, ex1);
            }

            throw ex;
        }

    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void MisAJour(Tregions tregions) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        try {
            Tregions persistentTregions = getEntityManager().find(Tregions.class, tregions.getId());
            Societe societeOld = persistentTregions.getSociete();
            Societe societeNew = tregions.getSociete();
            List<Tsites> tsitesListOld = persistentTregions.getTsitesList();
            List<Tsites> tsitesListNew = tregions.getTsitesList();
            List<Tdistricts> tdistrictsListOld = persistentTregions.getTdistrictsList();
            List<Tdistricts> tdistrictsListNew = tregions.getTdistrictsList();
//            List<Magasin> magasinListOld = persistentTregions.getMagasinList();
//            List<Magasin> magasinListNew = tregions.getMagasinList();

            if (societeNew != null) {
                societeNew = getEntityManager().getReference(societeNew.getClass(), societeNew.getId());
                tregions.setSociete(societeNew);
            }
            List<Tsites> attachedTsitesListNew = new ArrayList<Tsites>();
            for (Tsites tsitesListNewTsitesToAttach : tsitesListNew) {
                tsitesListNewTsitesToAttach = getEntityManager().getReference(tsitesListNewTsitesToAttach.getClass(), tsitesListNewTsitesToAttach.getId());
                attachedTsitesListNew.add(tsitesListNewTsitesToAttach);
            }
            tsitesListNew = attachedTsitesListNew;
            tregions.setTsitesList(tsitesListNew);
            List<Tdistricts> attachedTdistrictsListNew = new ArrayList<Tdistricts>();
            for (Tdistricts tdistrictsListNewTdistrictsToAttach : tdistrictsListNew) {
                tdistrictsListNewTdistrictsToAttach = getEntityManager().getReference(tdistrictsListNewTdistrictsToAttach.getClass(), tdistrictsListNewTdistrictsToAttach.getId());
                attachedTdistrictsListNew.add(tdistrictsListNewTdistrictsToAttach);
            }
            tdistrictsListNew = attachedTdistrictsListNew;
            tregions.setTdistrictsList(tdistrictsListNew);
            List<Magasin> attachedMagasinListNew = new ArrayList<Magasin>();
//            for (Magasin magasinListNewMagasinToAttach : magasinListNew) {
//                magasinListNewMagasinToAttach = getEntityManager().getReference(magasinListNewMagasinToAttach.getClass(), magasinListNewMagasinToAttach.getId());
//                attachedMagasinListNew.add(magasinListNewMagasinToAttach);
//            }
//            magasinListNew = attachedMagasinListNew;
//            tregions.setMagasinList(magasinListNew);
            tregions = getEntityManager().merge(tregions);
            if (societeOld != null && !societeOld.equals(societeNew)) {
                societeOld.getTregionsList().remove(tregions);
                societeOld = getEntityManager().merge(societeOld);
            }
            if (societeNew != null && !societeNew.equals(societeOld)) {
                societeNew.getTregionsList().add(tregions);
                societeNew = getEntityManager().merge(societeNew);
            }
            for (Tsites tsitesListNewTsites : tsitesListNew) {
                if (!tsitesListOld.contains(tsitesListNewTsites)) {
                    Tregions oldRegionidOfTsitesListNewTsites = tsitesListNewTsites.getRegionid();
                    tsitesListNewTsites.setRegionid(tregions);
                    tsitesListNewTsites = getEntityManager().merge(tsitesListNewTsites);
                    if (oldRegionidOfTsitesListNewTsites != null && !oldRegionidOfTsitesListNewTsites.equals(tregions)) {
                        oldRegionidOfTsitesListNewTsites.getTsitesList().remove(tsitesListNewTsites);
                        oldRegionidOfTsitesListNewTsites = getEntityManager().merge(oldRegionidOfTsitesListNewTsites);
                    }
                }
            }
//            for (Magasin magasinListNewMagasin : magasinListNew) {
//                if (!magasinListOld.contains(magasinListNewMagasin)) {
//                    Tregions oldRegionOfMagasinListNewMagasin = magasinListNewMagasin.getRegion();
//                    magasinListNewMagasin.setRegion(tregions);
//                    magasinListNewMagasin = getEntityManager().merge(magasinListNewMagasin);
//                    if (oldRegionOfMagasinListNewMagasin != null && !oldRegionOfMagasinListNewMagasin.equals(tregions)) {
//                        oldRegionOfMagasinListNewMagasin.getMagasinList().remove(magasinListNewMagasin);
//                        oldRegionOfMagasinListNewMagasin = getEntityManager().merge(oldRegionOfMagasinListNewMagasin);
//                    }
//                }
//            }
            for (Tdistricts tdistrictsListNewTdistricts : tdistrictsListNew) {
                if (!tdistrictsListOld.contains(tdistrictsListNewTdistricts)) {
                    Tregions oldRegionidOfTdistrictsListNewTdistricts = tdistrictsListNewTdistricts.getRegionid();
                    tdistrictsListNewTdistricts.setRegionid(tregions);
                    tdistrictsListNewTdistricts = getEntityManager().merge(tdistrictsListNewTdistricts);
                    if (oldRegionidOfTdistrictsListNewTdistricts != null && !oldRegionidOfTdistrictsListNewTdistricts.equals(tregions)) {
                        oldRegionidOfTdistrictsListNewTdistricts.getTdistrictsList().remove(tdistrictsListNewTdistricts);
                        oldRegionidOfTdistrictsListNewTdistricts = getEntityManager().merge(oldRegionidOfTdistrictsListNewTdistricts);
                    }
                }
            }
        } catch (Exception ex) {

            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tregions.getId();
                if (findTregions(id) == null) {
                    throw new NonexistentEntityException("The tregions with id " + id + " no longer exists.");
                }
            }
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void Supprimer(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        try {
            Tregions tregions;
            try {
                tregions = getEntityManager().getReference(Tregions.class, id);
                tregions.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tregions with id " + id + " no longer exists.", enfe);
            }
            Societe societe = tregions.getSociete();
            if (societe != null) {
                societe.getTregionsList().remove(tregions);
                societe = getEntityManager().merge(societe);
            }
//            List<Magasin> magasinList = tregions.getMagasinList();
//            for (Magasin magasinListMagasin : magasinList) {
//                magasinListMagasin.setRegion(null);
//                magasinListMagasin = getEntityManager().merge(magasinListMagasin);
//            }
            getEntityManager().remove(tregions);
        } catch (NonexistentEntityException ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @Override
    public List<Tregions> findTregionsEntities() {
        return findTregionsEntities(true, -1, -1);
    }

    @Override
    public List<Tregions> findTregionsEntities(int maxResults, int firstResult) {
        return findTregionsEntities(false, maxResults, firstResult);
    }

    private List<Tregions> findTregionsEntities(boolean all, int maxResults, int firstResult) {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Tregions.class));
        Query q = getEntityManager().createQuery(cq);
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    @Override
    public Tregions findTregions(Integer id) {
        return getEntityManager().find(Tregions.class, id);
    }

    @Override
    public int getTregionsCount() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<Tregions> rt = cq.from(Tregions.class);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    @Override
    public List<Tregions> findAll(String societe) {
        Query q = getEntityManager().createNamedQuery("Tregions.findAll");
        q.setParameter("imma", societe);
        return q.getResultList();
    }

}
