/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Taffectzone;
import com.eh2s.ocm.Entity.Tusers;
import java.util.List;
import javax.ejb.Stateless;
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
public class TaffectzoneFacade extends AbstractFacade<Taffectzone> implements TaffectzoneFacadeLocal {

    @PersistenceContext(unitName = "ccmanagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TaffectzoneFacade() {
        super(Taffectzone.class);
    }

    @Override
    public List<Taffectzone> findByUser(int user) {
        Query query = getEntityManager().createNamedQuery("Taffectzone.findByusers");
        query.setParameter("user", user);
        return query.getResultList();
    }

    @Override
    public Taffectzone creer(Taffectzone taffectzone) throws RollbackFailureException, Exception {
        try {
            Tusers users = taffectzone.getUsers();
            if (users != null) {
                users = getEntityManager().getReference(users.getClass(), users.getId());
                taffectzone.setUsers(users);
            }
            taffectzone = getEntityManager().merge(taffectzone);
            if (users != null) {
                users.getTaffectzoneList().add(taffectzone);
                users = getEntityManager().merge(users);
            }
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
        return taffectzone;
    }

    @Override
    public Taffectzone MisAJour(Taffectzone taffectzone) throws NonexistentEntityException, RollbackFailureException, Exception {
        try {
            Taffectzone persistentTaffectzone = getEntityManager().find(Taffectzone.class, taffectzone.getId());
            Tusers usersOld = persistentTaffectzone.getUsers();
            Tusers usersNew = taffectzone.getUsers();
            if (usersNew != null) {
                usersNew = getEntityManager().getReference(usersNew.getClass(), usersNew.getId());
                taffectzone.setUsers(usersNew);
            }
            taffectzone = getEntityManager().merge(taffectzone);
            if (usersOld != null && !usersOld.equals(usersNew)) {
                usersOld.getTaffectzoneList().remove(taffectzone);
                usersOld = getEntityManager().merge(usersOld);
            }
            if (usersNew != null && !usersNew.equals(usersOld)) {
                usersNew.getTaffectzoneList().add(taffectzone);
                usersNew = getEntityManager().merge(usersNew);
            }
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
        return taffectzone;
    }

    @Override
    public void Supprimer(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        try {
            Taffectzone taffectzone;
            try {
                taffectzone = getEntityManager().getReference(Taffectzone.class, id);
                taffectzone.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The taffectzone with id " + id + " no longer exists.", enfe);
            }
            Tusers users = taffectzone.getUsers();
            if (users != null) {
                users.getTaffectzoneList().remove(taffectzone);
                users = getEntityManager().merge(users);
            }
            getEntityManager().remove(taffectzone);
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @Override
    public List<Taffectzone> findTaffectzoneEntities() {
        return findTaffectzoneEntities(true, -1, -1);
    }

    @Override
    public Taffectzone findTaffectzone(Integer id) {
        return getEntityManager().find(Taffectzone.class, id);
    }

    private List<Taffectzone> findTaffectzoneEntities(boolean all, int maxResults, int firstResult) {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Taffectzone.class));
        Query q = getEntityManager().createQuery(cq);
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

}
