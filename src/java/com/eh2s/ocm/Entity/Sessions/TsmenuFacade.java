/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.IllegalOrphanException;
import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.PreexistingEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Tactions;
import com.eh2s.ocm.Entity.Tmenu;
import com.eh2s.ocm.Entity.Tsmenu;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author messi
 */
@Stateless
public class TsmenuFacade extends AbstractFacade<Tsmenu> implements TsmenuFacadeLocal {

    @PersistenceContext(unitName = "ccmanagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TsmenuFacade() {
        super(Tsmenu.class);
    }

    @Override
    public Tsmenu creer(Tsmenu tsmenu) throws RollbackFailureException, Exception {
        if (tsmenu.getTactionsList() == null) {
            tsmenu.setTactionsList(new ArrayList<Tactions>());
        }
        try {
            Tmenu menu = tsmenu.getMenu();
            if (menu != null) {
                menu = getEntityManager().getReference(menu.getClass(), menu.getId());
                tsmenu.setMenu(menu);
            }
            List<Tactions> attachedTactionsList = new ArrayList<Tactions>();
            for (Tactions tactionsListTactionsToAttach : tsmenu.getTactionsList()) {
                tactionsListTactionsToAttach = getEntityManager().getReference(tactionsListTactionsToAttach.getClass(), tactionsListTactionsToAttach.getIdAction());
                attachedTactionsList.add(tactionsListTactionsToAttach);
            }
            tsmenu.setTactionsList(attachedTactionsList);
            Tsmenu m = getEntityManager().merge(tsmenu);
            if (menu != null) {
                menu.getTsmenuList().add(m);
                menu = getEntityManager().merge(menu);
            }
            for (Tactions tactionsListTactions : m.getTactionsList()) {
                Tsmenu oldSmenuOfTactionsListTactions = tactionsListTactions.getSmenu();
                tactionsListTactions.setSmenu(m);
                tactionsListTactions = getEntityManager().merge(tactionsListTactions);
                if (oldSmenuOfTactionsListTactions != null) {
                    oldSmenuOfTactionsListTactions.getTactionsList().remove(tactionsListTactions);
                    oldSmenuOfTactionsListTactions = getEntityManager().merge(oldSmenuOfTactionsListTactions);
                }
            }
            return m;
        } catch (Exception ex) {

            if (findTsmenu(tsmenu.getId()) != null) {
                throw new PreexistingEntityException("Tsmenu " + tsmenu + " already exists.", ex);
            }
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @Override
    public void MisAJour(Tsmenu tsmenu) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager emf = null;
        try {
            emf = getEntityManager();
            Tsmenu persistentTsmenu = emf.find(Tsmenu.class, tsmenu.getId());
            Tmenu menuOld = persistentTsmenu.getMenu();
            Tmenu menuNew = tsmenu.getMenu();
            List<Tactions> tactionsListOld = persistentTsmenu.getTactionsList();
            List<Tactions> tactionsListNew = tsmenu.getTactionsList();
            if (menuNew != null) {
                menuNew = emf.getReference(menuNew.getClass(), menuNew.getId());
                tsmenu.setMenu(menuNew);
            }
            List<Tactions> attachedTactionsListNew = new ArrayList<Tactions>();
            for (Tactions tactionsListNewTactionsToAttach : tactionsListNew) {
                tactionsListNewTactionsToAttach = emf.getReference(tactionsListNewTactionsToAttach.getClass(), tactionsListNewTactionsToAttach.getIdAction());
                attachedTactionsListNew.add(tactionsListNewTactionsToAttach);
            }
            tactionsListNew = attachedTactionsListNew;
            tsmenu.setTactionsList(tactionsListNew);
            tsmenu = emf.merge(tsmenu);
            if (menuOld != null && !menuOld.equals(menuNew)) {
                menuOld.getTsmenuList().remove(tsmenu);
                menuOld = emf.merge(menuOld);
            }
            if (menuNew != null && !menuNew.equals(menuOld)) {
                menuNew.getTsmenuList().add(tsmenu);
                menuNew = emf.merge(menuNew);
            }
            for (Tactions tactionsListOldTactions : tactionsListOld) {
                if (!tactionsListNew.contains(tactionsListOldTactions)) {
                    tactionsListOldTactions.setSmenu(null);
                    tactionsListOldTactions = emf.merge(tactionsListOldTactions);
                }
            }
            for (Tactions tactionsListNewTactions : tactionsListNew) {
                if (!tactionsListOld.contains(tactionsListNewTactions)) {
                    Tsmenu oldSmenuOfTactionsListNewTactions = tactionsListNewTactions.getSmenu();
                    tactionsListNewTactions.setSmenu(tsmenu);
                    tactionsListNewTactions = emf.merge(tactionsListNewTactions);
                    if (oldSmenuOfTactionsListNewTactions != null && !oldSmenuOfTactionsListNewTactions.equals(tsmenu)) {
                        oldSmenuOfTactionsListNewTactions.getTactionsList().remove(tactionsListNewTactions);
                        oldSmenuOfTactionsListNewTactions = emf.merge(oldSmenuOfTactionsListNewTactions);
                    }
                }
            }
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tsmenu.getId();
                if (findTsmenu(id) == null) {
                    throw new NonexistentEntityException("The tsmenu with id " + id + " no longer exists.");
                }
            }
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @Override
    public List<Tsmenu> findTsmenuEntities() {
        return findTsmenuEntities(true, -1, -1);
    }

    private List<Tsmenu> findTsmenuEntities(boolean all, int maxResults, int firstResult) {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Tsmenu.class));
        Query q = getEntityManager().createQuery(cq);
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    @Override
    public Tsmenu findTsmenu(Integer id) {
        return getEntityManager().find(Tsmenu.class, id);
    }
}
