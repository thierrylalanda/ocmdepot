/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.StatutIncident;
import com.eh2s.ocm.Entity.Tincidents;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author messi
 */
@Stateless
public class StatutIncidentFacade extends AbstractFacade<StatutIncident> implements StatutIncidentFacadeLocal {

    @PersistenceContext(unitName = "ccmanagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StatutIncidentFacade() {
        super(StatutIncident.class);
    }

    @Override
    public StatutIncident Creer(StatutIncident statutIncident) throws RollbackFailureException, Exception {
        try {

            List<Tincidents> attachedTincidentsList = new ArrayList<Tincidents>();
            for (Tincidents tincidentsListTincidentsToAttach : statutIncident.getTincidentsList()) {
                tincidentsListTincidentsToAttach = getEntityManager().getReference(tincidentsListTincidentsToAttach.getClass(), tincidentsListTincidentsToAttach.getId());
                attachedTincidentsList.add(tincidentsListTincidentsToAttach);
            }
            statutIncident.setTincidentsList(attachedTincidentsList);
            StatutIncident stat = getEntityManager().merge(statutIncident);
            for (Tincidents tincidentsListTincidents : stat.getTincidentsList()) {
                StatutIncident oldStateOfTincidentsListTincidents = tincidentsListTincidents.getState();
                tincidentsListTincidents.setState(stat);
                tincidentsListTincidents = getEntityManager().merge(tincidentsListTincidents);
                if (oldStateOfTincidentsListTincidents != null) {
                    oldStateOfTincidentsListTincidents.getTincidentsList().remove(tincidentsListTincidents);
                    oldStateOfTincidentsListTincidents = getEntityManager().merge(oldStateOfTincidentsListTincidents);
                }
            }
            return stat;
        } catch (Exception ex) {

            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @Override
    public void MisAJour(StatutIncident statutIncident) throws NonexistentEntityException, RollbackFailureException, Exception {
        try {
            StatutIncident persistentStatutIncident = getEntityManager().find(StatutIncident.class, statutIncident.getCode());
            List<Tincidents> tincidentsListOld = persistentStatutIncident.getTincidentsList();
            List<Tincidents> tincidentsListNew = statutIncident.getTincidentsList();
            List<Tincidents> attachedTincidentsListNew = new ArrayList<Tincidents>();
            statutIncident.setTincidentsList(tincidentsListNew);
            for (Tincidents tincidentsListNewTincidentsToAttach : tincidentsListNew) {
                tincidentsListNewTincidentsToAttach = getEntityManager().getReference(tincidentsListNewTincidentsToAttach.getClass(), tincidentsListNewTincidentsToAttach.getId());
                attachedTincidentsListNew.add(tincidentsListNewTincidentsToAttach);
            }
            tincidentsListNew = attachedTincidentsListNew;
            statutIncident.setTincidentsList(tincidentsListNew);
            statutIncident = getEntityManager().merge(statutIncident);
            for (Tincidents tincidentsListOldTincidents : tincidentsListOld) {
                if (!tincidentsListNew.contains(tincidentsListOldTincidents)) {
                    tincidentsListOldTincidents.setState(null);
                    tincidentsListOldTincidents = getEntityManager().merge(tincidentsListOldTincidents);
                }
            }
            for (Tincidents tincidentsListNewTincidents : tincidentsListNew) {
                if (!tincidentsListOld.contains(tincidentsListNewTincidents)) {
                    StatutIncident oldStateOfTincidentsListNewTincidents = tincidentsListNewTincidents.getState();
                    tincidentsListNewTincidents.setState(statutIncident);
                    tincidentsListNewTincidents = getEntityManager().merge(tincidentsListNewTincidents);
                    if (oldStateOfTincidentsListNewTincidents != null && !oldStateOfTincidentsListNewTincidents.equals(statutIncident)) {
                        oldStateOfTincidentsListNewTincidents.getTincidentsList().remove(tincidentsListNewTincidents);
                        oldStateOfTincidentsListNewTincidents = getEntityManager().merge(oldStateOfTincidentsListNewTincidents);
                    }
                }
            }
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = statutIncident.getCode();
                if (findStatutIncident(id) == null) {
                    throw new NonexistentEntityException("The statutIncident with id " + id + " no longer exists.");
                }
            }
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @Override
    public void Supprimer(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        try {

            StatutIncident statutIncident;
            try {
                statutIncident = getEntityManager().getReference(StatutIncident.class, id);
                statutIncident.getCode();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The statutIncident with id " + id + " no longer exists.", enfe);
            }
            List<Tincidents> tincidentsList = statutIncident.getTincidentsList();
            for (Tincidents tincidentsListTincidents : tincidentsList) {
                tincidentsListTincidents.setState(null);
                tincidentsListTincidents = getEntityManager().merge(tincidentsListTincidents);
            }
            getEntityManager().remove(statutIncident);

        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @Override
    public List<StatutIncident> findStatutIncidentEntities() {
        return findStatutIncidentEntities(true, -1, -1);
    }

    @Override
    public StatutIncident findStatutIncident(Integer id) {
        return getEntityManager().find(StatutIncident.class, id);
    }

    @Override
    public List<StatutIncident> findStatutIncidentEntities(int maxResults, int firstResult) {
        return findStatutIncidentEntities(false, maxResults, firstResult);
    }

    private List<StatutIncident> findStatutIncidentEntities(boolean all, int maxResults, int firstResult) {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(StatutIncident.class));
        Query q = getEntityManager().createQuery(cq);
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }
}
