/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.IllegalOrphanException;
import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Tdistricts;
import com.eh2s.ocm.Entity.Tregions;
import com.eh2s.ocm.Entity.Tsecteurs;
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
public class TdistrictsFacade extends AbstractFacade<Tdistricts> implements TdistrictsFacadeLocal {

    @PersistenceContext(unitName = "ccmanagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TdistrictsFacade() {
        super(Tdistricts.class);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public Tdistricts creer(Tdistricts tdistricts) throws RollbackFailureException, Exception {
        if (tdistricts.getTsecteursList() == null) {
            tdistricts.setTsecteursList(new ArrayList<Tsecteurs>());
        }
        try {
            Tregions regionid = tdistricts.getRegionid();
            if (regionid != null) {
                regionid = getEntityManager().getReference(regionid.getClass(), regionid.getId());
                tdistricts.setRegionid(regionid);
            }

            Tdistricts district = getEntityManager().merge(tdistricts);
            if (regionid != null) {
                regionid.getTdistrictsList().add(district);
                regionid = getEntityManager().merge(regionid);
            }
            for (Tsecteurs tsecteursListTsecteurs : district.getTsecteursList()) {
                Tdistricts oldDistrictidOfTsecteursListTsecteurs = tsecteursListTsecteurs.getDistrictid();
                tsecteursListTsecteurs.setDistrictid(district);
                tsecteursListTsecteurs = getEntityManager().merge(tsecteursListTsecteurs);
                if (oldDistrictidOfTsecteursListTsecteurs != null) {
                    oldDistrictidOfTsecteursListTsecteurs.getTsecteursList().remove(tsecteursListTsecteurs);
                    oldDistrictidOfTsecteursListTsecteurs = getEntityManager().merge(oldDistrictidOfTsecteursListTsecteurs);
                }
            }
            return district;
        } catch (Exception ex) {
            throw new RollbackFailureException("une erreur est survenue le District n'a pas été enregistrer", ex);
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void MisAJour(Tdistricts tdistricts) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        try {

            Tdistricts persistentTdistricts = getEntityManager().find(Tdistricts.class, tdistricts.getId());
            Tregions regionidOld = persistentTdistricts.getRegionid();
            Tregions regionidNew = tdistricts.getRegionid();
            List<Tsecteurs> tsecteursListOld = persistentTdistricts.getTsecteursList();
            List<Tsecteurs> tsecteursListNew = tdistricts.getTsecteursList();
            List<String> illegalOrphanMessages = null;
            for (Tsecteurs tsecteursListOldTsecteurs : tsecteursListOld) {
                if (!tsecteursListNew.contains(tsecteursListOldTsecteurs)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tsecteurs " + tsecteursListOldTsecteurs + " since its districtid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (regionidNew != null) {
                regionidNew = getEntityManager().getReference(regionidNew.getClass(), regionidNew.getId());
                tdistricts.setRegionid(regionidNew);
            }
            List<Tsecteurs> attachedTsecteursListNew = new ArrayList<Tsecteurs>();
            for (Tsecteurs tsecteursListNewTsecteursToAttach : tsecteursListNew) {
                tsecteursListNewTsecteursToAttach = getEntityManager().getReference(tsecteursListNewTsecteursToAttach.getClass(), tsecteursListNewTsecteursToAttach.getId());
                attachedTsecteursListNew.add(tsecteursListNewTsecteursToAttach);
            }
            tsecteursListNew = attachedTsecteursListNew;
            tdistricts.setTsecteursList(tsecteursListNew);
            tdistricts = getEntityManager().merge(tdistricts);
            if (regionidOld != null && !regionidOld.equals(regionidNew)) {
                regionidOld.getTdistrictsList().remove(tdistricts);
                regionidOld = getEntityManager().merge(regionidOld);
            }
            if (regionidNew != null && !regionidNew.equals(regionidOld)) {
                regionidNew.getTdistrictsList().add(tdistricts);
                regionidNew = getEntityManager().merge(regionidNew);
            }
            for (Tsecteurs tsecteursListNewTsecteurs : tsecteursListNew) {
                if (!tsecteursListOld.contains(tsecteursListNewTsecteurs)) {
                    Tdistricts oldDistrictidOfTsecteursListNewTsecteurs = tsecteursListNewTsecteurs.getDistrictid();
                    tsecteursListNewTsecteurs.setDistrictid(tdistricts);
                    tsecteursListNewTsecteurs = getEntityManager().merge(tsecteursListNewTsecteurs);
                    if (oldDistrictidOfTsecteursListNewTsecteurs != null && !oldDistrictidOfTsecteursListNewTsecteurs.equals(tdistricts)) {
                        oldDistrictidOfTsecteursListNewTsecteurs.getTsecteursList().remove(tsecteursListNewTsecteurs);
                        oldDistrictidOfTsecteursListNewTsecteurs = getEntityManager().merge(oldDistrictidOfTsecteursListNewTsecteurs);
                    }
                }
            }
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tdistricts.getId();
                if (findTdistricts(id) == null) {
                    throw new NonexistentEntityException("le district avec l'id " + id + " n'éxiste pas.");
                }
            }
            throw new RollbackFailureException("une erreur est survenue le district n'a pas été enregistrer", ex);
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void Supprimer(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        try {
            Tdistricts tdistricts;
            try {
                tdistricts = getEntityManager().getReference(Tdistricts.class, id);
                tdistricts.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("le district avec l'id " + id + " n'éxiste pas.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Tsecteurs> tsecteursListOrphanCheck = tdistricts.getTsecteursList();
            for (Tsecteurs tsecteursListOrphanCheckTsecteurs : tsecteursListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tdistricts (" + tdistricts + ") cannot be destroyed since the Tsecteurs " + tsecteursListOrphanCheckTsecteurs + " in its tsecteursList field has a non-nullable districtid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Tregions regionid = tdistricts.getRegionid();
            if (regionid != null) {
                regionid.getTdistrictsList().remove(tdistricts);
                regionid = getEntityManager().merge(regionid);
            }
            getEntityManager().remove(tdistricts);
        } catch (Exception ex) {
            throw new RollbackFailureException("une erreur est survenue le district n'a pas été mis à jour", ex);

        }
    }

    @Override
    public List<Tdistricts> findTdistrictsEntities() {
        return findTdistrictsEntities(true, -1, -1);
    }

    @Override
    public List<Tdistricts> findTdistrictsEntities(int maxResults, int firstResult) {
        return findTdistrictsEntities(false, maxResults, firstResult);
    }

    private List<Tdistricts> findTdistrictsEntities(boolean all, int maxResults, int firstResult) {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Tdistricts.class));
        Query q = getEntityManager().createQuery(cq);
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    @Override
    public Tdistricts findTdistricts(Integer id) {
        return getEntityManager().find(Tdistricts.class, id);
    }

    @Override
    public int getTdistrictsCount() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<Tdistricts> rt = cq.from(Tdistricts.class);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    @Override
    public List<Tdistricts> findAll(String societe) {
        Query q = getEntityManager().createNamedQuery("Tdistricts.findAll");
        q.setParameter("imma", societe);
        return q.getResultList();
    }
}
