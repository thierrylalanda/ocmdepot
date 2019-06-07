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
import com.eh2s.ocm.Entity.TgroupsAssoc;
import com.eh2s.ocm.Entity.Tsmenu;
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

@Stateless
public class TactionsFacade extends AbstractFacade<Tactions> implements TactionsFacadeLocal {

    @PersistenceContext(unitName = "ccmanagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TactionsFacade() {
        super(Tactions.class);
    }

    @Override
    public Tactions creer(Tactions tactions) throws RollbackFailureException, Exception {
        if (tactions.getTgroupsAssocList() == null) {
            tactions.setTgroupsAssocList(new ArrayList<TgroupsAssoc>());
        }
        Tsmenu smenu = tactions.getSmenu();
        if (smenu != null) {
            smenu = getEntityManager().getReference(smenu.getClass(), smenu.getId());
            tactions.setSmenu(smenu);
        }
        try {
            List<TgroupsAssoc> attachedTgroupsAssocList = new ArrayList<TgroupsAssoc>();
            for (TgroupsAssoc tgroupsAssocListTgroupsAssocToAttach : tactions.getTgroupsAssocList()) {
                tgroupsAssocListTgroupsAssocToAttach = getEntityManager().getReference(tgroupsAssocListTgroupsAssocToAttach.getClass(), tgroupsAssocListTgroupsAssocToAttach.getId());
                attachedTgroupsAssocList.add(tgroupsAssocListTgroupsAssocToAttach);
            }
            tactions.setTgroupsAssocList(attachedTgroupsAssocList);
            Tactions t = getEntityManager().merge(tactions);
            if (smenu != null) {
                smenu.getTactionsList().add(t);
                smenu = getEntityManager().merge(smenu);
            }
            for (TgroupsAssoc tgroupsAssocListTgroupsAssoc : t.getTgroupsAssocList()) {
                Tactions oldActionOfTgroupsAssocListTgroupsAssoc = tgroupsAssocListTgroupsAssoc.getAction();
                tgroupsAssocListTgroupsAssoc.setAction(t);
                tgroupsAssocListTgroupsAssoc = getEntityManager().merge(tgroupsAssocListTgroupsAssoc);
                if (oldActionOfTgroupsAssocListTgroupsAssoc != null) {
                    oldActionOfTgroupsAssocListTgroupsAssoc.getTgroupsAssocList().remove(tgroupsAssocListTgroupsAssoc);
                    oldActionOfTgroupsAssocListTgroupsAssoc = getEntityManager().merge(oldActionOfTgroupsAssocListTgroupsAssoc);
                }
            }
            return t;
        } catch (Exception ex) {
            throw new RollbackFailureException("une erreur est survenue l'action n'a pas été enregistrer", ex);

        }
    }

    @Override
    public void MisAJour(Tactions tactions) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        try {
            Tactions persistentTactions = getEntityManager().find(Tactions.class, tactions.getIdAction());
            Tsmenu smenuOld = persistentTactions.getSmenu();
            List<TgroupsAssoc> tgroupsAssocListOld = persistentTactions.getTgroupsAssocList();
            List<TgroupsAssoc> tgroupsAssocListNew = tactions.getTgroupsAssocList();
            Tsmenu smenuNew = tactions.getSmenu();
            List<String> illegalOrphanMessages = null;
            for (TgroupsAssoc tgroupsAssocListOldTgroupsAssoc : tgroupsAssocListOld) {
                if (!tgroupsAssocListNew.contains(tgroupsAssocListOldTgroupsAssoc)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TgroupsAssoc " + tgroupsAssocListOldTgroupsAssoc + " since its action field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (smenuNew != null) {
                smenuNew = getEntityManager().getReference(smenuNew.getClass(), smenuNew.getId());
                tactions.setSmenu(smenuNew);
            }
            List<TgroupsAssoc> attachedTgroupsAssocListNew = new ArrayList<TgroupsAssoc>();
            for (TgroupsAssoc tgroupsAssocListNewTgroupsAssocToAttach : tgroupsAssocListNew) {
                tgroupsAssocListNewTgroupsAssocToAttach = getEntityManager().getReference(tgroupsAssocListNewTgroupsAssocToAttach.getClass(), tgroupsAssocListNewTgroupsAssocToAttach.getId());
                attachedTgroupsAssocListNew.add(tgroupsAssocListNewTgroupsAssocToAttach);
            }
            tgroupsAssocListNew = attachedTgroupsAssocListNew;
            tactions.setTgroupsAssocList(tgroupsAssocListNew);
            tactions = getEntityManager().merge(tactions);
            if (smenuOld != null && !smenuOld.equals(smenuNew)) {
                smenuOld.getTactionsList().remove(tactions);
                smenuOld = getEntityManager().merge(smenuOld);
            }
            if (smenuNew != null && !smenuNew.equals(smenuOld)) {
                smenuNew.getTactionsList().add(tactions);
                smenuNew = getEntityManager().merge(smenuNew);
            }
            for (TgroupsAssoc tgroupsAssocListNewTgroupsAssoc : tgroupsAssocListNew) {
                if (!tgroupsAssocListOld.contains(tgroupsAssocListNewTgroupsAssoc)) {
                    Tactions oldActionOfTgroupsAssocListNewTgroupsAssoc = tgroupsAssocListNewTgroupsAssoc.getAction();
                    tgroupsAssocListNewTgroupsAssoc.setAction(tactions);
                    tgroupsAssocListNewTgroupsAssoc = getEntityManager().merge(tgroupsAssocListNewTgroupsAssoc);
                    if (oldActionOfTgroupsAssocListNewTgroupsAssoc != null && !oldActionOfTgroupsAssocListNewTgroupsAssoc.equals(tactions)) {
                        oldActionOfTgroupsAssocListNewTgroupsAssoc.getTgroupsAssocList().remove(tgroupsAssocListNewTgroupsAssoc);
                        oldActionOfTgroupsAssocListNewTgroupsAssoc = getEntityManager().merge(oldActionOfTgroupsAssocListNewTgroupsAssoc);
                    }
                }
            }
        } catch (Exception ex) {

            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tactions.getIdAction();
                if (findTactions(id) == null) {
                    throw new NonexistentEntityException("l'action avec l'id " + id + " n'éxiste pas.");
                }
            }
            throw new RollbackFailureException("une erreur est survenue l'action n'a pas été Mis à jour", ex);
        }
    }

    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    @Override
    public void Supprimer(Integer id) throws  NonexistentEntityException, RollbackFailureException, Exception {
        try {
            Tactions tactions;
            try {
                tactions = getEntityManager().getReference(Tactions.class, id);
                tactions.getIdAction();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("l'action avec l'id " + id + " n'éxiste pas.", enfe);
            }
           
            getEntityManager().remove(tactions);
        } catch (Exception ex) {
            throw new RollbackFailureException("une erreur est survenue l'action n'a pas été supprimer", ex);
        }
    }

    @Override
    public List<Tactions> findTactionsEntities() {
        return findTactionsEntities(true, -1, -1);
    }

    @Override
    public List<Tactions> findTactionsEntities(int maxResults, int firstResult) {
        return findTactionsEntities(false, maxResults, firstResult);
    }

    @Override
    public Tactions findTactions(Integer id) {
        return getEntityManager().find(Tactions.class, id);
    }

    @Override
    public int getTactionsCount() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<Tactions> rt = cq.from(Tactions.class);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    private List<Tactions> findTactionsEntities(boolean all, int maxResults, int firstResult) {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Tactions.class));
        Query q = getEntityManager().createQuery(cq);
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }
}
