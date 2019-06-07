/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.IllegalOrphanException;
import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Tactions;
import com.eh2s.ocm.Entity.Tgroups;
import com.eh2s.ocm.Entity.TgroupsAssoc;
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
public class TgroupsAssocFacade extends AbstractFacade<TgroupsAssoc> implements TgroupsAssocFacadeLocal {

    @PersistenceContext(unitName = "ccmanagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TgroupsAssocFacade() {
        super(TgroupsAssoc.class);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public TgroupsAssoc creer(TgroupsAssoc tgroupsAssoc) throws RollbackFailureException, Exception {
        try {
            Tgroups group1 = tgroupsAssoc.getGroup1();
            if (group1 != null) {
                group1 = getEntityManager().getReference(group1.getClass(), group1.getId());
                tgroupsAssoc.setGroup1(group1);
            }
            Tactions action = tgroupsAssoc.getAction();
            if (action != null) {
                action = getEntityManager().getReference(action.getClass(), action.getIdAction());
                tgroupsAssoc.setAction(action);
            }
            TgroupsAssoc assoc = getEntityManager().merge(tgroupsAssoc);
            if (group1 != null) {
                group1.getTgroupsAssocList().add(assoc);
                group1 = getEntityManager().merge(group1);
            }
            if (action != null) {
                action.getTgroupsAssocList().add(assoc);
                action = getEntityManager().merge(action);
            }
            return assoc;
        } catch (Exception ex) {
            throw new RollbackFailureException("une erreur est survenue le groupe associer n'a pas été enregistrer", ex);

        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void MisAJour(TgroupsAssoc tgroupsAssoc) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        try {
            TgroupsAssoc persistentTgroupsAssoc = getEntityManager().find(TgroupsAssoc.class, tgroupsAssoc.getId());
            Tgroups group1Old = persistentTgroupsAssoc.getGroup1();
            Tgroups group1New = tgroupsAssoc.getGroup1();
            Tactions actionOld = persistentTgroupsAssoc.getAction();
            Tactions actionNew = tgroupsAssoc.getAction();
            if (group1New != null) {
                group1New = getEntityManager().getReference(group1New.getClass(), group1New.getId());
                tgroupsAssoc.setGroup1(group1New);
            }
            if (actionNew != null) {
                actionNew = getEntityManager().getReference(actionNew.getClass(), actionNew.getIdAction());
                tgroupsAssoc.setAction(actionNew);
            }
            tgroupsAssoc = getEntityManager().merge(tgroupsAssoc);
            if (group1Old != null && !group1Old.equals(group1New)) {
                group1Old.getTgroupsAssocList().remove(tgroupsAssoc);
                group1Old = getEntityManager().merge(group1Old);
            }
            if (group1New != null && !group1New.equals(group1Old)) {
                group1New.getTgroupsAssocList().add(tgroupsAssoc);
                group1New = getEntityManager().merge(group1New);
            }
            if (actionOld != null && !actionOld.equals(actionNew)) {
                actionOld.getTgroupsAssocList().remove(tgroupsAssoc);
                actionOld = getEntityManager().merge(actionOld);
            }
            if (actionNew != null && !actionNew.equals(actionOld)) {
                actionNew.getTgroupsAssocList().add(tgroupsAssoc);
                actionNew = getEntityManager().merge(actionNew);
            }

        } catch (Exception ex) {

            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tgroupsAssoc.getId();
                if (findTgroupsAssoc(id) == null) {
                    throw new NonexistentEntityException("le tgroupsAssoc avec l'id " + id + " n'éxiste pas");
                }
            }
            throw new RollbackFailureException("une erreur est survenue le groupe associer n'a pas été mis à jour", ex);

        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void Supprimer(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        try {
            TgroupsAssoc tgroupsAssoc;
            try {
                tgroupsAssoc = getEntityManager().getReference(TgroupsAssoc.class, id);
                tgroupsAssoc.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("le tgroupsAssoc avec l'id " + id + " n'éxiste pas", enfe);
            }
            Tgroups group1 = tgroupsAssoc.getGroup1();
            if (group1 != null) {
                group1.getTgroupsAssocList().remove(tgroupsAssoc);
                group1 = getEntityManager().merge(group1);
            }
            Tactions action = tgroupsAssoc.getAction();
            if (action != null) {
                action.getTgroupsAssocList().remove(tgroupsAssoc);
                action = getEntityManager().merge(action);
            }
            getEntityManager().remove(tgroupsAssoc);
        } catch (Exception ex) {
            throw new RollbackFailureException("une erreur est survenue le groupe associer n'a pas été supprimer", ex);

        }
    }

    @Override
    public List<TgroupsAssoc> findTgroupsAssocEntities() {
        return findTgroupsAssocEntities(true, -1, -1);
    }

    @Override
    public List<TgroupsAssoc> findTgroupsAssocEntities(int maxResults, int firstResult) {
        return findTgroupsAssocEntities(false, maxResults, firstResult);
    }

    private List<TgroupsAssoc> findTgroupsAssocEntities(boolean all, int maxResults, int firstResult) {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(TgroupsAssoc.class));
        Query q = getEntityManager().createQuery(cq);
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    @Override
    public TgroupsAssoc findTgroupsAssoc(Integer id) {
        return getEntityManager().find(TgroupsAssoc.class, id);
    }

    @Override
    public int getTgroupsAssocCount() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<TgroupsAssoc> rt = cq.from(TgroupsAssoc.class);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    @Override
    public void deletteByGroup(Integer group) {
        Query query = getEntityManager().createNamedQuery("TgroupsAssoc.DeletteBygroup");
        query.setParameter("group", group);
        query.executeUpdate();
    }
    
    @Override
    public  List<TgroupsAssoc>  GroupAssocieteByGroup(Integer group) {
        Query query = getEntityManager().createNamedQuery("TgroupsAssoc.findByGroupe");
        query.setParameter("id", group);
        return query.getResultList();
    }

}
