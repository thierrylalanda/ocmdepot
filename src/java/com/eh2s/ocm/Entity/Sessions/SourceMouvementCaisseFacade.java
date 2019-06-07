/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Account;
import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.SortieCaisse;
import com.eh2s.ocm.Entity.SourceMouvementCaisse;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Administrateur
 */
@Stateless
public class SourceMouvementCaisseFacade extends AbstractFacade<SourceMouvementCaisse> implements SourceMouvementCaisseFacadeLocal {

    @PersistenceContext(unitName = "ccmanagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SourceMouvementCaisseFacade() {
        super(SourceMouvementCaisse.class);
    }

    @Override
    public SourceMouvementCaisse creer(SourceMouvementCaisse sourceMouvementCaisse) throws RollbackFailureException, Exception {
        if (sourceMouvementCaisse.getSortieCaisseList() == null) {
            sourceMouvementCaisse.setSortieCaisseList(new ArrayList<SortieCaisse>());
        }
        if (sourceMouvementCaisse.getAccountList() == null) {
            sourceMouvementCaisse.setAccountList(new ArrayList<Account>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            List<SortieCaisse> attachedSortieCaisseList = new ArrayList<SortieCaisse>();
            for (SortieCaisse sortieCaisseListSortieCaisseToAttach : sourceMouvementCaisse.getSortieCaisseList()) {
                sortieCaisseListSortieCaisseToAttach = em.getReference(sortieCaisseListSortieCaisseToAttach.getClass(), sortieCaisseListSortieCaisseToAttach.getId());
                attachedSortieCaisseList.add(sortieCaisseListSortieCaisseToAttach);
            }
            sourceMouvementCaisse.setSortieCaisseList(attachedSortieCaisseList);
            List<Account> attachedAccountList = new ArrayList<Account>();
            for (Account accountListAccountToAttach : sourceMouvementCaisse.getAccountList()) {
                accountListAccountToAttach = em.getReference(accountListAccountToAttach.getClass(), accountListAccountToAttach.getId());
                attachedAccountList.add(accountListAccountToAttach);
            }
            sourceMouvementCaisse.setAccountList(attachedAccountList);
            em.persist(sourceMouvementCaisse);
            for (SortieCaisse sortieCaisseListSortieCaisse : sourceMouvementCaisse.getSortieCaisseList()) {
                SourceMouvementCaisse oldSourceOfSortieCaisseListSortieCaisse = sortieCaisseListSortieCaisse.getSource();
                sortieCaisseListSortieCaisse.setSource(sourceMouvementCaisse);
                sortieCaisseListSortieCaisse = em.merge(sortieCaisseListSortieCaisse);
                if (oldSourceOfSortieCaisseListSortieCaisse != null) {
                    oldSourceOfSortieCaisseListSortieCaisse.getSortieCaisseList().remove(sortieCaisseListSortieCaisse);
                    oldSourceOfSortieCaisseListSortieCaisse = em.merge(oldSourceOfSortieCaisseListSortieCaisse);
                }
            }
            for (Account accountListAccount : sourceMouvementCaisse.getAccountList()) {
                SourceMouvementCaisse oldSourceOfAccountListAccount = accountListAccount.getSource();
                accountListAccount.setSource(sourceMouvementCaisse);
                accountListAccount = em.merge(accountListAccount);
                if (oldSourceOfAccountListAccount != null) {
                    oldSourceOfAccountListAccount.getAccountList().remove(accountListAccount);
                    oldSourceOfAccountListAccount = em.merge(oldSourceOfAccountListAccount);
                }
            }
            return sourceMouvementCaisse;
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @Override
    public SourceMouvementCaisse MisAJour(SourceMouvementCaisse sourceMouvementCaisse) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            SourceMouvementCaisse persistentSourceMouvementCaisse = em.find(SourceMouvementCaisse.class, sourceMouvementCaisse.getId());
            List<SortieCaisse> sortieCaisseListOld = persistentSourceMouvementCaisse.getSortieCaisseList();
            List<SortieCaisse> sortieCaisseListNew = sourceMouvementCaisse.getSortieCaisseList();
            List<Account> accountListOld = persistentSourceMouvementCaisse.getAccountList();
            List<Account> accountListNew = sourceMouvementCaisse.getAccountList();
            List<SortieCaisse> attachedSortieCaisseListNew = new ArrayList<SortieCaisse>();
            for (SortieCaisse sortieCaisseListNewSortieCaisseToAttach : sortieCaisseListNew) {
                sortieCaisseListNewSortieCaisseToAttach = em.getReference(sortieCaisseListNewSortieCaisseToAttach.getClass(), sortieCaisseListNewSortieCaisseToAttach.getId());
                attachedSortieCaisseListNew.add(sortieCaisseListNewSortieCaisseToAttach);
            }
            sortieCaisseListNew = attachedSortieCaisseListNew;
            sourceMouvementCaisse.setSortieCaisseList(sortieCaisseListNew);
            List<Account> attachedAccountListNew = new ArrayList<Account>();
            for (Account accountListNewAccountToAttach : accountListNew) {
                accountListNewAccountToAttach = em.getReference(accountListNewAccountToAttach.getClass(), accountListNewAccountToAttach.getId());
                attachedAccountListNew.add(accountListNewAccountToAttach);
            }
            accountListNew = attachedAccountListNew;
            sourceMouvementCaisse.setAccountList(accountListNew);
            sourceMouvementCaisse = em.merge(sourceMouvementCaisse);
            for (SortieCaisse sortieCaisseListOldSortieCaisse : sortieCaisseListOld) {
                if (!sortieCaisseListNew.contains(sortieCaisseListOldSortieCaisse)) {
                    sortieCaisseListOldSortieCaisse.setSource(null);
                    sortieCaisseListOldSortieCaisse = em.merge(sortieCaisseListOldSortieCaisse);
                }
            }
            for (SortieCaisse sortieCaisseListNewSortieCaisse : sortieCaisseListNew) {
                if (!sortieCaisseListOld.contains(sortieCaisseListNewSortieCaisse)) {
                    SourceMouvementCaisse oldSourceOfSortieCaisseListNewSortieCaisse = sortieCaisseListNewSortieCaisse.getSource();
                    sortieCaisseListNewSortieCaisse.setSource(sourceMouvementCaisse);
                    sortieCaisseListNewSortieCaisse = em.merge(sortieCaisseListNewSortieCaisse);
                    if (oldSourceOfSortieCaisseListNewSortieCaisse != null && !oldSourceOfSortieCaisseListNewSortieCaisse.equals(sourceMouvementCaisse)) {
                        oldSourceOfSortieCaisseListNewSortieCaisse.getSortieCaisseList().remove(sortieCaisseListNewSortieCaisse);
                        oldSourceOfSortieCaisseListNewSortieCaisse = em.merge(oldSourceOfSortieCaisseListNewSortieCaisse);
                    }
                }
            }
            for (Account accountListOldAccount : accountListOld) {
                if (!accountListNew.contains(accountListOldAccount)) {
                    accountListOldAccount.setSource(null);
                    accountListOldAccount = em.merge(accountListOldAccount);
                }
            }
            for (Account accountListNewAccount : accountListNew) {
                if (!accountListOld.contains(accountListNewAccount)) {
                    SourceMouvementCaisse oldSourceOfAccountListNewAccount = accountListNewAccount.getSource();
                    accountListNewAccount.setSource(sourceMouvementCaisse);
                    accountListNewAccount = em.merge(accountListNewAccount);
                    if (oldSourceOfAccountListNewAccount != null && !oldSourceOfAccountListNewAccount.equals(sourceMouvementCaisse)) {
                        oldSourceOfAccountListNewAccount.getAccountList().remove(accountListNewAccount);
                        oldSourceOfAccountListNewAccount = em.merge(oldSourceOfAccountListNewAccount);
                    }
                }
            }
            return sourceMouvementCaisse;
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @Override
    public void Supprimer(Integer id) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            SourceMouvementCaisse sourceMouvementCaisse;
            try {
                sourceMouvementCaisse = em.getReference(SourceMouvementCaisse.class, id);
                sourceMouvementCaisse.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sourceMouvementCaisse with id " + id + " no longer exists.", enfe);
            }
            List<SortieCaisse> sortieCaisseList = sourceMouvementCaisse.getSortieCaisseList();
            for (SortieCaisse sortieCaisseListSortieCaisse : sortieCaisseList) {
                sortieCaisseListSortieCaisse.setSource(null);
                sortieCaisseListSortieCaisse = em.merge(sortieCaisseListSortieCaisse);
            }
            List<Account> accountList = sourceMouvementCaisse.getAccountList();
            for (Account accountListAccount : accountList) {
                accountListAccount.setSource(null);
                accountListAccount = em.merge(accountListAccount);
            }
            em.remove(sourceMouvementCaisse);
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

}
