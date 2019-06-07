/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Tincidents;
import com.eh2s.ocm.Entity.Tusers;
import com.eh2s.ocm.Entity.Userplainte;
import java.sql.Date;
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
public class UserplainteFacade extends AbstractFacade<Userplainte> implements UserplainteFacadeLocal {

    @PersistenceContext(unitName = "ccmanagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserplainteFacade() {
        super(Userplainte.class);
    }

    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    @Override
    public Userplainte creer(Userplainte userplainte) throws RollbackFailureException, Exception {
        try {
            Tusers iduser = userplainte.getIduser();
            if (iduser != null) {
                iduser = getEntityManager().getReference(iduser.getClass(), iduser.getId());
                userplainte.setIduser(iduser);
            }
            Tincidents idplainte = userplainte.getIdplainte();
            if (idplainte != null) {
                idplainte = getEntityManager().getReference(idplainte.getClass(), idplainte.getId());
                userplainte.setIdplainte(idplainte);
            }
            Userplainte userplainte1 = getEntityManager().merge(userplainte);
            if (iduser != null) {
                iduser.getUserplainteList().add(userplainte1);
                iduser = getEntityManager().merge(iduser);
            }
            if (idplainte != null) {
                idplainte.getUserplainteList().add(userplainte1);
                idplainte = getEntityManager().merge(idplainte);
            }
            return userplainte1;
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);
        }
    }

    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    @Override
    public void MisAJour(Userplainte userplainte) throws NonexistentEntityException, RollbackFailureException, Exception {
        try {
            Userplainte persistentUserplainte = getEntityManager().find(Userplainte.class, userplainte.getId());
            Tusers iduserOld = persistentUserplainte.getIduser();
            Tusers iduserNew = userplainte.getIduser();
            Tincidents idplainteOld = persistentUserplainte.getIdplainte();
            Tincidents idplainteNew = userplainte.getIdplainte();
            if (iduserNew != null) {
                iduserNew = getEntityManager().getReference(iduserNew.getClass(), iduserNew.getId());
                userplainte.setIduser(iduserNew);
            }
            if (idplainteNew != null) {
                idplainteNew = getEntityManager().getReference(idplainteNew.getClass(), idplainteNew.getId());
                userplainte.setIdplainte(idplainteNew);
            }
            userplainte = getEntityManager().merge(userplainte);
            if (iduserOld != null && !iduserOld.equals(iduserNew)) {
                iduserOld.getUserplainteList().remove(userplainte);
                iduserOld = getEntityManager().merge(iduserOld);
            }
            if (iduserNew != null && !iduserNew.equals(iduserOld)) {
                iduserNew.getUserplainteList().add(userplainte);
                iduserNew = getEntityManager().merge(iduserNew);
            }
            if (idplainteOld != null && !idplainteOld.equals(idplainteNew)) {
                idplainteOld.getUserplainteList().remove(userplainte);
                idplainteOld = getEntityManager().merge(idplainteOld);
            }
            if (idplainteNew != null && !idplainteNew.equals(idplainteOld)) {
                idplainteNew.getUserplainteList().add(userplainte);
                idplainteNew = getEntityManager().merge(idplainteNew);
            }

        } catch (Exception ex) {

            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = userplainte.getId();
                if (findUserplainte(id) == null) {
                    throw new NonexistentEntityException("The userplainte with id " + id + " no longer exists.");
                }
            }
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    @Override
    public void Supprimer(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        try {
            Userplainte userplainte;
            try {
                userplainte = getEntityManager().getReference(Userplainte.class, id);
                userplainte.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The userplainte with id " + id + " no longer exists.", enfe);
            }
            Tusers iduser = userplainte.getIduser();
            if (iduser != null) {
                iduser.getUserplainteList().remove(userplainte);
                iduser = getEntityManager().merge(iduser);
            }
            Tincidents idplainte = userplainte.getIdplainte();
            if (idplainte != null) {
                idplainte.getUserplainteList().remove(userplainte);
                idplainte = getEntityManager().merge(idplainte);
            }
            getEntityManager().remove(userplainte);

        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @Override
    public List<Userplainte> findUserplainteEntities() {
        return findUserplainteEntities(true, -1, -1);
    }

    @Override
    public List<Userplainte> findUserplainteEntities(int maxResults, int firstResult) {
        return findUserplainteEntities(false, maxResults, firstResult);
    }

    private List<Userplainte> findUserplainteEntities(boolean all, int maxResults, int firstResult) {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Userplainte.class));
        Query q = getEntityManager().createQuery(cq);
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    @Override
    public Userplainte findUserplainte(Integer id) {
        return getEntityManager().find(Userplainte.class, id);
    }

    @Override
    public int getUserplainteCount() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<Userplainte> rt = cq.from(Userplainte.class);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    @Override
    public Userplainte findUserplainteByIdTicket(Integer id) {
        Query q = getEntityManager().createNamedQuery("Userplainte.findByIdPlainte");
        q.setParameter("id", id);
        return (Userplainte) q.getSingleResult();
    }

    @Override
    public List<Tincidents> findTincidentsEntitiesByPeriode(Date d1, Date d2, int responsable) {
        List<Tincidents> l = new ArrayList<>();
        Query query = getEntityManager().createNamedQuery("Userplainte.findByPeriode");
        query.setParameter("id", responsable);
        query.setParameter("d1", d1);
        query.setParameter("d2", d2);
        for (Object d : query.getResultList()) {
            Userplainte plainte = (Userplainte) d;
            l.add(plainte.getIdplainte());
        }
        return l;
    }
}
