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
import com.eh2s.ocm.Entity.Tincidents;
import com.eh2s.ocm.Entity.Tsources;
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
public class TsourcesFacade extends AbstractFacade<Tsources> implements TsourcesFacadeLocal {

    @PersistenceContext(unitName = "ccmanagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TsourcesFacade() {
        super(Tsources.class);
    }

    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    @Override
    public Tsources creer(Tsources tsources) throws RollbackFailureException, Exception {
        if (tsources.getTincidentsList() == null) {
            tsources.setTincidentsList(new ArrayList<Tincidents>());
        }
        try {
            Societe societe = tsources.getSociete();
            if (societe != null) {
                societe = getEntityManager().getReference(societe.getClass(), societe.getId());
                tsources.setSociete(societe);
            }
            List<Tincidents> attachedTincidentsList = new ArrayList<Tincidents>();
            for (Tincidents tincidentsListTincidentsToAttach : tsources.getTincidentsList()) {
                tincidentsListTincidentsToAttach = getEntityManager().getReference(tincidentsListTincidentsToAttach.getClass(), tincidentsListTincidentsToAttach.getId());
                attachedTincidentsList.add(tincidentsListTincidentsToAttach);
            }
            tsources.setTincidentsList(attachedTincidentsList);
            tsources = getEntityManager().merge(tsources);
            if (societe != null) {
                societe.getTsourcesList().add(tsources);
                societe = getEntityManager().merge(societe);
            }
            for (Tincidents tincidentsListTincidents : tsources.getTincidentsList()) {
                Tsources oldSourceidOfTincidentsListTincidents = tincidentsListTincidents.getSourceid();
                tincidentsListTincidents.setSourceid(tsources);
                tincidentsListTincidents = getEntityManager().merge(tincidentsListTincidents);
                if (oldSourceidOfTincidentsListTincidents != null) {
                    oldSourceidOfTincidentsListTincidents.getTincidentsList().remove(tincidentsListTincidents);
                    oldSourceidOfTincidentsListTincidents = getEntityManager().merge(oldSourceidOfTincidentsListTincidents);
                }
            }
            return tsources;
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);
        }
    }

    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    @Override
    public void MisAJour(Tsources tsources) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        try {
            Tsources persistentTsources = getEntityManager().find(Tsources.class, tsources.getId());
            Societe societeOld = persistentTsources.getSociete();
            Societe societeNew = tsources.getSociete();
            List<Tincidents> tincidentsListOld = persistentTsources.getTincidentsList();
            List<Tincidents> tincidentsListNew = tsources.getTincidentsList();

            if (societeNew != null) {
                societeNew = getEntityManager().getReference(societeNew.getClass(), societeNew.getId());
                tsources.setSociete(societeNew);
            }
            List<Tincidents> attachedTincidentsListNew = new ArrayList<Tincidents>();
            for (Tincidents tincidentsListNewTincidentsToAttach : tincidentsListNew) {
                tincidentsListNewTincidentsToAttach = getEntityManager().getReference(tincidentsListNewTincidentsToAttach.getClass(), tincidentsListNewTincidentsToAttach.getId());
                attachedTincidentsListNew.add(tincidentsListNewTincidentsToAttach);
            }
            tincidentsListNew = attachedTincidentsListNew;
            tsources.setTincidentsList(tincidentsListNew);
            tsources = getEntityManager().merge(tsources);
            if (societeOld != null && !societeOld.equals(societeNew)) {
                societeOld.getTsourcesList().remove(tsources);
                societeOld = getEntityManager().merge(societeOld);
            }
            if (societeNew != null && !societeNew.equals(societeOld)) {
                societeNew.getTsourcesList().add(tsources);
                societeNew = getEntityManager().merge(societeNew);
            }
            for (Tincidents tincidentsListNewTincidents : tincidentsListNew) {
                if (!tincidentsListOld.contains(tincidentsListNewTincidents)) {
                    Tsources oldSourceidOfTincidentsListNewTincidents = tincidentsListNewTincidents.getSourceid();
                    tincidentsListNewTincidents.setSourceid(tsources);
                    tincidentsListNewTincidents = getEntityManager().merge(tincidentsListNewTincidents);
                    if (oldSourceidOfTincidentsListNewTincidents != null && !oldSourceidOfTincidentsListNewTincidents.equals(tsources)) {
                        oldSourceidOfTincidentsListNewTincidents.getTincidentsList().remove(tincidentsListNewTincidents);
                        oldSourceidOfTincidentsListNewTincidents = getEntityManager().merge(oldSourceidOfTincidentsListNewTincidents);
                    }
                }
            }
        } catch (Exception ex) {

            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tsources.getId();
                if (findTsources(id) == null) {
                    throw new NonexistentEntityException("The tsources with id " + id + " no longer exists.");
                }
            }
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);
        }
    }

    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    @Override
    public void Supprimer(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        try {
            Tsources tsources;
            try {
                tsources = getEntityManager().getReference(Tsources.class, id);
                tsources.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tsources with id " + id + " no longer exists.", enfe);
            }

            Societe societe = tsources.getSociete();
            if (societe != null) {
                societe.getTsourcesList().remove(tsources);
                societe = getEntityManager().merge(societe);
            }
            getEntityManager().remove(tsources);
        } catch (NonexistentEntityException ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @Override
    public List<Tsources> findTsourcesEntities() {
        return findTsourcesEntities(true, -1, -1);
    }

    @Override
    public List<Tsources> findTsourcesEntities(int maxResults, int firstResult) {
        return findTsourcesEntities(false, maxResults, firstResult);
    }

    private List<Tsources> findTsourcesEntities(boolean all, int maxResults, int firstResult) {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Tsources.class));
        Query q = getEntityManager().createQuery(cq);
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    @Override
    public Tsources findTsources(Integer id) {
        return getEntityManager().find(Tsources.class, id);
    }

    @Override
    public int getTsourcesCount() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<Tsources> rt = cq.from(Tsources.class);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    @Override
    public List<Tsources> findAll(String societe) {
        Query q = getEntityManager().createNamedQuery("Tsources.findAll");
        q.setParameter("imma", societe);
        return q.getResultList();
    }
}
