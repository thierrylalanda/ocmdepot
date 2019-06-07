/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.PieceJointe;
import com.eh2s.ocm.Entity.Tincidents;
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
public class PieceJointeFacade extends AbstractFacade<PieceJointe> implements PieceJointeFacadeLocal {

    @PersistenceContext(unitName = "ccmanagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PieceJointeFacade() {
        super(PieceJointe.class);
    }

    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    @Override
    public PieceJointe Creer(PieceJointe pieceJointe) throws RollbackFailureException, Exception {
        try {
            Tincidents incident = pieceJointe.getIncident();
            if (incident != null) {
                incident = getEntityManager().getReference(incident.getClass(), incident.getId());
                pieceJointe.setIncident(incident);
            }
            PieceJointe p = getEntityManager().merge(pieceJointe);
            if (incident != null) {
                incident.getPieceJointeList().add(p);
                incident = getEntityManager().merge(incident);
            }
            return p;
        } catch (Exception ex) {
            throw new RollbackFailureException("une erreur est survenue la piece jointe n'a pas été enregistrer", ex);
        }
    }

    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    @Override
    public void MisAJour(PieceJointe pieceJointe) throws NonexistentEntityException, RollbackFailureException, Exception {
        try {
            PieceJointe persistentPieceJointe = getEntityManager().find(PieceJointe.class, pieceJointe.getIdPj());
            Tincidents incidentOld = persistentPieceJointe.getIncident();
            Tincidents incidentNew = pieceJointe.getIncident();
            if (incidentNew != null) {
                incidentNew = getEntityManager().getReference(incidentNew.getClass(), incidentNew.getId());
                pieceJointe.setIncident(incidentNew);
            }
            pieceJointe = getEntityManager().merge(pieceJointe);
            if (incidentOld != null && !incidentOld.equals(incidentNew)) {
                incidentOld.getPieceJointeList().remove(pieceJointe);
                incidentOld = getEntityManager().merge(incidentOld);
            }
            if (incidentNew != null && !incidentNew.equals(incidentOld)) {
                incidentNew.getPieceJointeList().add(pieceJointe);
                incidentNew = getEntityManager().merge(incidentNew);
            }
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = pieceJointe.getIdPj();
                if (findPieceJointe(id) == null) {
                    throw new NonexistentEntityException("la pieceJointe avec l'id " + id + " n'éxiste pas.");
                }
            }
            throw new RollbackFailureException("une erreur est survenue la piece jointe n'a pas été mis à jour", ex);
        }
    }

    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    @Override
    public void Supprimer(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        try {
            PieceJointe pieceJointe;
            try {
                pieceJointe = getEntityManager().getReference(PieceJointe.class, id);
                pieceJointe.getIdPj();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("la pieceJointe avec l'id " + id + " n'éxiste pas.", enfe);
            }
            Tincidents incident = pieceJointe.getIncident();
            if (incident != null) {
                incident.getPieceJointeList().remove(pieceJointe);
                incident = getEntityManager().merge(incident);
            }
            getEntityManager().remove(pieceJointe);
        } catch (Exception ex) {
            throw new RollbackFailureException("une erreur est survenue la piece jointe n'a pas été supprimer", ex);

        }
    }

    @Override
    public List<PieceJointe> findPieceJointeEntities() {
        return findPieceJointeEntities(true, -1, -1);
    }

    @Override
    public List<PieceJointe> findPieceJointeEntities(int maxResults, int firstResult) {
        return findPieceJointeEntities(false, maxResults, firstResult);
    }

    private List<PieceJointe> findPieceJointeEntities(boolean all, int maxResults, int firstResult) {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(PieceJointe.class));
        Query q = getEntityManager().createQuery(cq);
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    @Override
    public PieceJointe findPieceJointe(Integer id) {
        return getEntityManager().find(PieceJointe.class, id);
    }

    @Override
    public int getPieceJointeCount() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<PieceJointe> rt = cq.from(PieceJointe.class);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        Query q = getEntityManager().createQuery(cq);

        return ((Long) q.getSingleResult()).intValue();

    }
    
     @Override
    public List<PieceJointe> findAllTickect(int ticket) {
         Query query = getEntityManager().createNamedQuery("PieceJointe.findAllByTickect");
         query.setParameter("tikect", ticket);
         return query.getResultList();
    }
}
