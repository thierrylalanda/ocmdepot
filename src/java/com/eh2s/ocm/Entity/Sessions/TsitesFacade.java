/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.IllegalOrphanException;
import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Tregions;
import com.eh2s.ocm.Entity.Tservices;
import com.eh2s.ocm.Entity.Tsites;
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
public class TsitesFacade extends AbstractFacade<Tsites> implements TsitesFacadeLocal {

    @PersistenceContext(unitName = "ccmanagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TsitesFacade() {
        super(Tsites.class);
    }

    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    @Override
    public Tsites creer(Tsites tsites) throws RollbackFailureException, Exception {
        if (tsites.getTservicesList() == null) {
            tsites.setTservicesList(new ArrayList<Tservices>());
        }
        try {
            Tregions regionid = tsites.getRegionid();
            if (regionid != null) {
                regionid = getEntityManager().getReference(regionid.getClass(), regionid.getId());
                tsites.setRegionid(regionid);
            }
            List<Tservices> attachedTservicesList = new ArrayList<Tservices>();
            for (Tservices tservicesListTservicesToAttach : tsites.getTservicesList()) {
                tservicesListTservicesToAttach = getEntityManager().getReference(tservicesListTservicesToAttach.getClass(), tservicesListTservicesToAttach.getId());
                attachedTservicesList.add(tservicesListTservicesToAttach);
            }
            tsites.setTservicesList(attachedTservicesList);
            Tsites sites = getEntityManager().merge(tsites);
            if (regionid != null) {
                regionid.getTsitesList().add(sites);
                regionid = getEntityManager().merge(regionid);
            }
            for (Tservices tservicesListTservices : sites.getTservicesList()) {
                Tsites oldSiteOfTservicesListTservices = tservicesListTservices.getSite();
                tservicesListTservices.setSite(sites);
                tservicesListTservices = getEntityManager().merge(tservicesListTservices);
                if (oldSiteOfTservicesListTservices != null) {
                    oldSiteOfTservicesListTservices.getTservicesList().remove(tservicesListTservices);
                    oldSiteOfTservicesListTservices = getEntityManager().merge(oldSiteOfTservicesListTservices);
                }
            }
            return sites;
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    @Override
    public void MisAJour(Tsites tsites) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        try {
            Tsites persistentTsites = getEntityManager().find(Tsites.class, tsites.getId());
            Tregions regionidOld = persistentTsites.getRegionid();
            Tregions regionidNew = tsites.getRegionid();
            List<Tservices> tservicesListOld = persistentTsites.getTservicesList();
            List<Tservices> tservicesListNew = tsites.getTservicesList();
            List<String> illegalOrphanMessages = null;
            for (Tservices tservicesListOldTservices : tservicesListOld) {
                if (!tservicesListNew.contains(tservicesListOldTservices)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tservices " + tservicesListOldTservices + " since its site field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (regionidNew != null) {
                regionidNew = getEntityManager().getReference(regionidNew.getClass(), regionidNew.getId());
                tsites.setRegionid(regionidNew);
            }
            List<Tservices> attachedTservicesListNew = new ArrayList<Tservices>();
            for (Tservices tservicesListNewTservicesToAttach : tservicesListNew) {
                tservicesListNewTservicesToAttach = getEntityManager().getReference(tservicesListNewTservicesToAttach.getClass(), tservicesListNewTservicesToAttach.getId());
                attachedTservicesListNew.add(tservicesListNewTservicesToAttach);
            }
            tservicesListNew = attachedTservicesListNew;
            tsites.setTservicesList(tservicesListNew);
            tsites = getEntityManager().merge(tsites);
            if (regionidOld != null && !regionidOld.equals(regionidNew)) {
                regionidOld.getTsitesList().remove(tsites);
                regionidOld = getEntityManager().merge(regionidOld);
            }
            if (regionidNew != null && !regionidNew.equals(regionidOld)) {
                regionidNew.getTsitesList().add(tsites);
                regionidNew = getEntityManager().merge(regionidNew);
            }
            for (Tservices tservicesListNewTservices : tservicesListNew) {
                if (!tservicesListOld.contains(tservicesListNewTservices)) {
                    Tsites oldSiteOfTservicesListNewTservices = tservicesListNewTservices.getSite();
                    tservicesListNewTservices.setSite(tsites);
                    tservicesListNewTservices = getEntityManager().merge(tservicesListNewTservices);
                    if (oldSiteOfTservicesListNewTservices != null && !oldSiteOfTservicesListNewTservices.equals(tsites)) {
                        oldSiteOfTservicesListNewTservices.getTservicesList().remove(tservicesListNewTservices);
                        oldSiteOfTservicesListNewTservices = getEntityManager().merge(oldSiteOfTservicesListNewTservices);
                    }
                }
            }
        } catch (Exception ex) {

            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tsites.getId();
                if (findTsites(id) == null) {
                    throw new NonexistentEntityException("The tsites with id " + id + " no longer exists.");
                }
            }
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);
        }
    }

    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    @Override
    public void Supprimer(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        try {
            Tsites tsites;
            try {
                tsites = getEntityManager().getReference(Tsites.class, id);
                tsites.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tsites with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Tservices> tservicesListOrphanCheck = tsites.getTservicesList();
            for (Tservices tservicesListOrphanCheckTservices : tservicesListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tsites (" + tsites + ") cannot be destroyed since the Tservices " + tservicesListOrphanCheckTservices + " in its tservicesList field has a non-nullable site field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Tregions regionid = tsites.getRegionid();
            if (regionid != null) {
                regionid.getTsitesList().remove(tsites);
                regionid = getEntityManager().merge(regionid);
            }
            getEntityManager().remove(tsites);
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @Override
    public List<Tsites> findTsitesEntities() {
        return findTsitesEntities(true, -1, -1);
    }

    @Override
    public List<Tsites> findTsitesEntities(int maxResults, int firstResult) {
        return findTsitesEntities(false, maxResults, firstResult);
    }

    private List<Tsites> findTsitesEntities(boolean all, int maxResults, int firstResult) {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Tsites.class));
        Query q = getEntityManager().createQuery(cq);
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    @Override
    public Tsites findTsites(Integer id) {
        return getEntityManager().find(Tsites.class, id);
    }

    @Override
    public int getTsitesCount() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<Tsites> rt = cq.from(Tsites.class);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    @Override
    public List<Tsites> findAll(String societe) {
        Query q = getEntityManager().createNamedQuery("Tsites.findAll");
        q.setParameter("imma", societe);
        return q.getResultList();
    }
}
