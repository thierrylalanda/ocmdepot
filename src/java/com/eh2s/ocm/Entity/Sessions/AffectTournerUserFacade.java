/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.AffectTournerUser;
import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Tourner;
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

/**
 *
 * @author messi01
 */
@Stateless
public class AffectTournerUserFacade extends AbstractFacade<AffectTournerUser> implements AffectTournerUserFacadeLocal {

    @PersistenceContext(unitName = "ccmanagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AffectTournerUserFacade() {
        super(AffectTournerUser.class);
    }

    @Override
    public AffectTournerUser save(AffectTournerUser affectTournerUser) {
        return getEntityManager().merge(affectTournerUser);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public AffectTournerUser creer(AffectTournerUser affectTournerUser) throws RollbackFailureException, Exception {
        try {
            Tusers user = affectTournerUser.getUser();
            if (user != null) {
                user = getEntityManager().getReference(user.getClass(), user.getId());
                affectTournerUser.setUser(user);
            }
            Tourner tourner = affectTournerUser.getTourner();
            if (tourner != null) {
                tourner = getEntityManager().getReference(tourner.getClass(), tourner.getId());
                affectTournerUser.setTourner(tourner);
            }
            affectTournerUser = getEntityManager().merge(affectTournerUser);
            if (user != null) {
                user.getAffectTournerUserList().add(affectTournerUser);
                user = getEntityManager().merge(user);
            }
            if (tourner != null) {
                tourner.getAffectTournerUserList().add(affectTournerUser);
                tourner = getEntityManager().merge(tourner);
            }

        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
        return affectTournerUser;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public AffectTournerUser MisAJour(AffectTournerUser affectTournerUser) throws NonexistentEntityException, RollbackFailureException, Exception {
        try {
            AffectTournerUser persistentAffectTournerUser = getEntityManager().find(AffectTournerUser.class, affectTournerUser.getId());
            Tusers userOld = persistentAffectTournerUser.getUser();
            Tusers userNew = affectTournerUser.getUser();
            Tourner tournerOld = persistentAffectTournerUser.getTourner();
            Tourner tournerNew = affectTournerUser.getTourner();
            if (userNew != null) {
                userNew = getEntityManager().getReference(userNew.getClass(), userNew.getId());
                affectTournerUser.setUser(userNew);
            }
            if (tournerNew != null) {
                tournerNew = getEntityManager().getReference(tournerNew.getClass(), tournerNew.getId());
                affectTournerUser.setTourner(tournerNew);
            }
            affectTournerUser = getEntityManager().merge(affectTournerUser);
            if (userOld != null && !userOld.equals(userNew)) {
                userOld.getAffectTournerUserList().remove(affectTournerUser);
                userOld = getEntityManager().merge(userOld);
            }
            if (userNew != null && !userNew.equals(userOld)) {
                userNew.getAffectTournerUserList().add(affectTournerUser);
                userNew = getEntityManager().merge(userNew);
            }
            if (tournerOld != null && !tournerOld.equals(tournerNew)) {
                tournerOld.getAffectTournerUserList().remove(affectTournerUser);
                tournerOld = getEntityManager().merge(tournerOld);
            }
            if (tournerNew != null && !tournerNew.equals(tournerOld)) {
                tournerNew.getAffectTournerUserList().add(affectTournerUser);
                tournerNew = getEntityManager().merge(tournerNew);
            }
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);
        }
        return affectTournerUser;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void Supprimer(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        try {
            AffectTournerUser affectTournerUser;
            try {
                affectTournerUser = getEntityManager().getReference(AffectTournerUser.class, id);
                affectTournerUser.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The affectTournerUser with id " + id + " no longer exists.", enfe);
            }
            Tusers user = affectTournerUser.getUser();
            if (user != null) {
                user.getAffectTournerUserList().remove(affectTournerUser);
                user = getEntityManager().merge(user);
            }
            Tourner tourner = affectTournerUser.getTourner();
            if (tourner != null) {
                tourner.getAffectTournerUserList().remove(affectTournerUser);
                tourner = getEntityManager().merge(tourner);
            }
            getEntityManager().remove(affectTournerUser);
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @Override
    public List<AffectTournerUser> findAffectTournerUserEntities() {
        return findAffectTournerUserEntities(true, -1, -1);
    }

    @Override
    public AffectTournerUser findAffectTournerUser(Integer id) {
        return getEntityManager().find(AffectTournerUser.class, id);
    }

    private List<AffectTournerUser> findAffectTournerUserEntities(boolean all, int maxResults, int firstResult) {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(AffectTournerUser.class));
        Query q = getEntityManager().createQuery(cq);
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    @Override
    public List<AffectTournerUser> findAffectTournerUserEntities(int user) {
        Query q = getEntityManager().createNamedQuery("AffectTournerUser.findByUser");
        q.setParameter("id", user);
        return q.getResultList();
    }

    @Override
    public List<Tusers> findUserAffectByTourner(int tourner) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<AffectTournerUser> findAffectTournerTournerEntities(int tourner) {
        Query q = getEntityManager().createNamedQuery("AffectTournerUser.findByTourner");
        q.setParameter("id", tourner);
        return q.getResultList();
    }

}
