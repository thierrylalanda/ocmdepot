/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.IllegalOrphanException;
import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Tservices;
import com.eh2s.ocm.Entity.Tsites;
import com.eh2s.ocm.Entity.Tusers;
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
public class TservicesFacade extends AbstractFacade<Tservices> implements TservicesFacadeLocal {

    @PersistenceContext(unitName = "ccmanagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TservicesFacade() {
        super(Tservices.class);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public Tservices creer(Tservices tservices) throws RollbackFailureException, Exception {
        if (tservices.getTusersList() == null) {
            tservices.setTusersList(new ArrayList<Tusers>());
        }
        try {

            Tsites site = tservices.getSite();
            if (site != null) {
                site = getEntityManager().getReference(site.getClass(), site.getId());
                tservices.setSite(site);
            }
            List<Tusers> attachedTusersList = new ArrayList<Tusers>();
            for (Tusers tusersListTusersToAttach : tservices.getTusersList()) {
                tusersListTusersToAttach = getEntityManager().getReference(tusersListTusersToAttach.getClass(), tusersListTusersToAttach.getId());
                attachedTusersList.add(tusersListTusersToAttach);
            }
            tservices.setTusersList(attachedTusersList);
            Tservices services = getEntityManager().merge(tservices);

            if (site != null) {
                site.getTservicesList().add(services);
                site = getEntityManager().merge(site);
            }
            for (Tusers tusersListTusers : services.getTusersList()) {
                Tservices oldServiceidOfTusersListTusers = tusersListTusers.getServiceid();
                tusersListTusers.setServiceid(services);
                tusersListTusers = getEntityManager().merge(tusersListTusers);
                if (oldServiceidOfTusersListTusers != null) {
                    oldServiceidOfTusersListTusers.getTusersList().remove(tusersListTusers);
                    oldServiceidOfTusersListTusers = getEntityManager().merge(oldServiceidOfTusersListTusers);
                }
            }
            return services;
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void MisAJour(Tservices tservices) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        try {
            Tservices persistentTservices = getEntityManager().find(Tservices.class, tservices.getId());

            Tsites siteOld = persistentTservices.getSite();
            Tsites siteNew = tservices.getSite();
            List<Tusers> tusersListOld = persistentTservices.getTusersList();
            List<Tusers> tusersListNew = tservices.getTusersList();
            List<String> illegalOrphanMessages = null;
            for (Tusers tusersListOldTusers : tusersListOld) {
                if (!tusersListNew.contains(tusersListOldTusers)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tusers " + tusersListOldTusers + " since its serviceid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }

            if (siteNew != null) {
                siteNew = getEntityManager().getReference(siteNew.getClass(), siteNew.getId());
                tservices.setSite(siteNew);
            }
            List<Tusers> attachedTusersListNew = new ArrayList<Tusers>();
            for (Tusers tusersListNewTusersToAttach : tusersListNew) {
                tusersListNewTusersToAttach = getEntityManager().getReference(tusersListNewTusersToAttach.getClass(), tusersListNewTusersToAttach.getId());
                attachedTusersListNew.add(tusersListNewTusersToAttach);
            }
            tusersListNew = attachedTusersListNew;
            tservices.setTusersList(tusersListNew);
            tservices = getEntityManager().merge(tservices);

            if (siteOld != null && !siteOld.equals(siteNew)) {
                siteOld.getTservicesList().remove(tservices);
                siteOld = getEntityManager().merge(siteOld);
            }
            if (siteNew != null && !siteNew.equals(siteOld)) {
                siteNew.getTservicesList().add(tservices);
                siteNew = getEntityManager().merge(siteNew);
            }
            for (Tusers tusersListNewTusers : tusersListNew) {
                if (!tusersListOld.contains(tusersListNewTusers)) {
                    Tservices oldServiceidOfTusersListNewTusers = tusersListNewTusers.getServiceid();
                    tusersListNewTusers.setServiceid(tservices);
                    tusersListNewTusers = getEntityManager().merge(tusersListNewTusers);
                    if (oldServiceidOfTusersListNewTusers != null && !oldServiceidOfTusersListNewTusers.equals(tservices)) {
                        oldServiceidOfTusersListNewTusers.getTusersList().remove(tusersListNewTusers);
                        oldServiceidOfTusersListNewTusers = getEntityManager().merge(oldServiceidOfTusersListNewTusers);
                    }
                }
            }

        } catch (Exception ex) {

            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tservices.getId();
                if (findTservices(id) == null) {
                    throw new NonexistentEntityException("The tservices with id " + id + " no longer exists.");
                }
            }
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void Supprimer(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        try {
            Tservices tservices;
            try {
                tservices = getEntityManager().getReference(Tservices.class, id);
                tservices.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tservices with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Tusers> tusersListOrphanCheck = tservices.getTusersList();
            for (Tusers tusersListOrphanCheckTusers : tusersListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tservices (" + tservices + ") cannot be destroyed since the Tusers " + tusersListOrphanCheckTusers + " in its tusersList field has a non-nullable serviceid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }

            Tsites site = tservices.getSite();
            if (site != null) {
                site.getTservicesList().remove(tservices);
                site = getEntityManager().merge(site);
            }
            getEntityManager().remove(tservices);

        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @Override
    public List<Tservices> findTservicesEntities() {
        return findTservicesEntities(true, -1, -1);
    }

    @Override
    public List<Tservices> findTservicesEntities(int maxResults, int firstResult) {
        return findTservicesEntities(false, maxResults, firstResult);
    }

    private List<Tservices> findTservicesEntities(boolean all, int maxResults, int firstResult) {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Tservices.class));
        Query q = getEntityManager().createQuery(cq);
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    @Override
    public Tservices findTservices(Integer id) {
        return getEntityManager().find(Tservices.class, id);
    }

    @Override
    public int getTservicesCount() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<Tservices> rt = cq.from(Tservices.class);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    @Override
    public List<Tservices> findAll(String societe) {
        Query q = getEntityManager().createNamedQuery("Tservices.findAll");
        q.setParameter("imma", societe);
        return q.getResultList();
    }

    @Override
    public List<Tservices> findAllByRegion(int region) {
        Query q = getEntityManager().createNamedQuery("Tservices.findByRegion");
        q.setParameter("reg", region);
        return q.getResultList();
    }
}
