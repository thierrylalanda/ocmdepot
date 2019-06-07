/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Tincidents;
import com.eh2s.ocm.Entity.TtraitementTicket;
import com.eh2s.ocm.Entity.Tusers;
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
public class TtraitementTicketFacade extends AbstractFacade<TtraitementTicket> implements TtraitementTicketFacadeLocal {

    @PersistenceContext(unitName = "ccmanagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TtraitementTicketFacade() {
        super(TtraitementTicket.class);
    }

    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    @Override
    public TtraitementTicket creer(TtraitementTicket ttraitementTicket) throws RollbackFailureException, Exception {
        try {
            Tincidents incident = ttraitementTicket.getIncident();
            if (incident != null) {
                incident = getEntityManager().getReference(incident.getClass(), incident.getId());
                ttraitementTicket.setIncident(incident);
            }
            Tusers user = ttraitementTicket.getUser();
            if (user != null) {
                user = getEntityManager().getReference(user.getClass(), user.getId());
                ttraitementTicket.setUser(user);
            }
            TtraitementTicket ticket = getEntityManager().merge(ttraitementTicket);
            if (incident != null) {
                incident.getTtraitementTicketList().add(ticket);
                incident = getEntityManager().merge(incident);
            }
            if (user != null) {
                user.getTtraitementTicketList().add(ticket);
                user = getEntityManager().merge(user);
            }
            return ticket;

        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);
        }
    }

    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    @Override
    public void MisAJour(TtraitementTicket ttraitementTicket) throws NonexistentEntityException, RollbackFailureException, Exception {
        try {
            TtraitementTicket persistentTtraitementTicket = getEntityManager().find(TtraitementTicket.class, ttraitementTicket.getId());
            Tincidents incidentOld = persistentTtraitementTicket.getIncident();
            Tincidents incidentNew = ttraitementTicket.getIncident();
            Tusers userOld = persistentTtraitementTicket.getUser();
            Tusers userNew = ttraitementTicket.getUser();
            if (incidentNew != null) {
                incidentNew = getEntityManager().getReference(incidentNew.getClass(), incidentNew.getId());
                ttraitementTicket.setIncident(incidentNew);
            }
            if (userNew != null) {
                userNew = getEntityManager().getReference(userNew.getClass(), userNew.getId());
                ttraitementTicket.setUser(userNew);
            }
            ttraitementTicket = getEntityManager().merge(ttraitementTicket);
            if (incidentOld != null && !incidentOld.equals(incidentNew)) {
                incidentOld.getTtraitementTicketList().remove(ttraitementTicket);
                incidentOld = getEntityManager().merge(incidentOld);
            }
            if (incidentNew != null && !incidentNew.equals(incidentOld)) {
                incidentNew.getTtraitementTicketList().add(ttraitementTicket);
                incidentNew = getEntityManager().merge(incidentNew);
            }
            if (userOld != null && !userOld.equals(userNew)) {
                userOld.getTtraitementTicketList().remove(ttraitementTicket);
                userOld = getEntityManager().merge(userOld);
            }
            if (userNew != null && !userNew.equals(userOld)) {
                userNew.getTtraitementTicketList().add(ttraitementTicket);
                userNew = getEntityManager().merge(userNew);
            }

        } catch (Exception ex) {

            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ttraitementTicket.getId();
                if (findTtraitementTicket(id) == null) {
                    throw new NonexistentEntityException("The ttraitementTicket with id " + id + " no longer exists.");
                }
            }
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    @Override
    public void Supprimer(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        try {
            TtraitementTicket ttraitementTicket;
            try {
                ttraitementTicket = getEntityManager().getReference(TtraitementTicket.class, id);
                ttraitementTicket.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ttraitementTicket with id " + id + " no longer exists.", enfe);
            }
            Tincidents incident = ttraitementTicket.getIncident();
            if (incident != null) {
                incident.getTtraitementTicketList().remove(ttraitementTicket);
                incident = getEntityManager().merge(incident);
            }
            Tusers user = ttraitementTicket.getUser();
            if (user != null) {
                user.getTtraitementTicketList().remove(ttraitementTicket);
                user = getEntityManager().merge(user);
            }
            getEntityManager().remove(ttraitementTicket);

        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @Override
    public List<TtraitementTicket> findTtraitementTicketEntities() {
        return findTtraitementTicketEntities(true, -1, -1);
    }

    @Override
    public List<TtraitementTicket> findTtraitementTicketEntities(int maxResults, int firstResult) {
        return findTtraitementTicketEntities(false, maxResults, firstResult);
    }

    private List<TtraitementTicket> findTtraitementTicketEntities(boolean all, int maxResults, int firstResult) {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(TtraitementTicket.class));
        Query q = getEntityManager().createQuery(cq);
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    @Override
    public TtraitementTicket findTtraitementTicket(Integer id) {
        return getEntityManager().find(TtraitementTicket.class, id);
    }

    @Override
    public int getTtraitementTicketCount() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<TtraitementTicket> rt = cq.from(TtraitementTicket.class);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
    
     @Override
    public List<TtraitementTicket> findAllTickect(int ticket) {
         Query query = getEntityManager().createNamedQuery("TtraitementTicket.findAllByTicket");
         query.setParameter("ticket", ticket);
         return query.getResultList();
    }
}
