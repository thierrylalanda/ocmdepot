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
import com.eh2s.ocm.Entity.Tpriority;
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
public class TpriorityFacade extends AbstractFacade<Tpriority> implements TpriorityFacadeLocal {

    @PersistenceContext(unitName = "ccmanagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TpriorityFacade() {
        super(Tpriority.class);
    }

    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    @Override
    public Tpriority creer(Tpriority tpriority) throws RollbackFailureException, Exception {

        if (tpriority.getTincidentsList() == null) {
            tpriority.setTincidentsList(new ArrayList<Tincidents>());
        }
        try {
            Societe societe = tpriority.getSociete();
            if (societe != null) {
                societe = getEntityManager().getReference(societe.getClass(), societe.getId());
                tpriority.setSociete(societe);
            }
            List<Tincidents> attachedTincidentsList = new ArrayList<Tincidents>();
            for (Tincidents tincidentsListTincidentsToAttach : tpriority.getTincidentsList()) {
                tincidentsListTincidentsToAttach = getEntityManager().getReference(tincidentsListTincidentsToAttach.getClass(), tincidentsListTincidentsToAttach.getId());
                attachedTincidentsList.add(tincidentsListTincidentsToAttach);
            }
            tpriority.setTincidentsList(attachedTincidentsList);
            Tpriority priorite = getEntityManager().merge(tpriority);

            if (societe != null) {
                societe.getTpriorityList().add(tpriority);
                societe = getEntityManager().merge(societe);
            }
            for (Tincidents tincidentsListTincidents : priorite.getTincidentsList()) {
                Tpriority oldPrioriteidOfTincidentsListTincidents = tincidentsListTincidents.getPrioriteid();
                tincidentsListTincidents.setPrioriteid(priorite);
                tincidentsListTincidents = getEntityManager().merge(tincidentsListTincidents);
                if (oldPrioriteidOfTincidentsListTincidents != null) {
                    oldPrioriteidOfTincidentsListTincidents.getTincidentsList().remove(tincidentsListTincidents);
                    oldPrioriteidOfTincidentsListTincidents = getEntityManager().merge(oldPrioriteidOfTincidentsListTincidents);
                }
            }
            return priorite;
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);
        }
    }

    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    @Override
    public void MisAJour(Tpriority tpriority) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        try {
            Tpriority persistentTpriority = getEntityManager().find(Tpriority.class, tpriority.getId());
            Societe societeOld = persistentTpriority.getSociete();
            Societe societeNew = tpriority.getSociete();
            List<Tincidents> tincidentsListOld = persistentTpriority.getTincidentsList();
            List<Tincidents> tincidentsListNew = tpriority.getTincidentsList();

            if (societeNew != null) {
                societeNew = em.getReference(societeNew.getClass(), societeNew.getId());
                tpriority.setSociete(societeNew);
            }

            List<Tincidents> attachedTincidentsListNew = new ArrayList<Tincidents>();
            for (Tincidents tincidentsListNewTincidentsToAttach : tincidentsListNew) {
                tincidentsListNewTincidentsToAttach = getEntityManager().getReference(tincidentsListNewTincidentsToAttach.getClass(), tincidentsListNewTincidentsToAttach.getId());
                attachedTincidentsListNew.add(tincidentsListNewTincidentsToAttach);
            }
            tincidentsListNew = attachedTincidentsListNew;
            tpriority.setTincidentsList(tincidentsListNew);
            tpriority = getEntityManager().merge(tpriority);
            if (societeOld != null && !societeOld.equals(societeNew)) {
                societeOld.getTpriorityList().remove(tpriority);
                societeOld = getEntityManager().merge(societeOld);
            }
            if (societeNew != null && !societeNew.equals(societeOld)) {
                societeNew.getTpriorityList().add(tpriority);
                societeNew = getEntityManager().merge(societeNew);
            }
            for (Tincidents tincidentsListOldTincidents : tincidentsListOld) {
                if (!tincidentsListNew.contains(tincidentsListOldTincidents)) {
                    tincidentsListOldTincidents.setPrioriteid(null);
                    tincidentsListOldTincidents = getEntityManager().merge(tincidentsListOldTincidents);
                }
            }
            for (Tincidents tincidentsListNewTincidents : tincidentsListNew) {
                if (!tincidentsListOld.contains(tincidentsListNewTincidents)) {
                    Tpriority oldPrioriteidOfTincidentsListNewTincidents = tincidentsListNewTincidents.getPrioriteid();
                    tincidentsListNewTincidents.setPrioriteid(tpriority);
                    tincidentsListNewTincidents = getEntityManager().merge(tincidentsListNewTincidents);
                    if (oldPrioriteidOfTincidentsListNewTincidents != null && !oldPrioriteidOfTincidentsListNewTincidents.equals(tpriority)) {
                        oldPrioriteidOfTincidentsListNewTincidents.getTincidentsList().remove(tincidentsListNewTincidents);
                        oldPrioriteidOfTincidentsListNewTincidents = getEntityManager().merge(oldPrioriteidOfTincidentsListNewTincidents);
                    }
                }
            }
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tpriority.getId();
                if (findTpriority(id) == null) {
                    throw new NonexistentEntityException("The tpriority with id " + id + " no longer exists.");
                }
            }
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);
        }
    }

    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    @Override
    public void Supprimer(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        try {
            Tpriority tpriority;
            try {
                tpriority = getEntityManager().getReference(Tpriority.class, id);
                tpriority.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tpriority with id " + id + " no longer exists.", enfe);
            }

            List<Tincidents> tincidentsList = tpriority.getTincidentsList();
            for (Tincidents tincidentsListTincidents : tincidentsList) {
                tincidentsListTincidents.setPrioriteid(null);
                tincidentsListTincidents = getEntityManager().merge(tincidentsListTincidents);
            }
            getEntityManager().remove(tpriority);
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @Override
    public List<Tpriority> findTpriorityEntities() {
        return findTpriorityEntities(true, -1, -1);
    }

    @Override
    public List<Tpriority> findTpriorityEntities(int maxResults, int firstResult) {
        return findTpriorityEntities(false, maxResults, firstResult);
    }

    private List<Tpriority> findTpriorityEntities(boolean all, int maxResults, int firstResult) {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Tpriority.class));
        Query q = getEntityManager().createQuery(cq);
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    @Override
    public Tpriority findTpriority(Integer id) {
        return getEntityManager().find(Tpriority.class, id);
    }

    @Override
    public int getTpriorityCount() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<Tpriority> rt = cq.from(Tpriority.class);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    @Override
    public List<Tpriority> findAll(String societe) {
        Query query = getEntityManager().createNamedQuery("Tpriority.findAll");
        query.setParameter("imma", societe);
        return query.getResultList();
    }

}
