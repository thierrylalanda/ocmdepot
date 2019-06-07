/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Account;
import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.LigneAccount;
import com.eh2s.ocm.Entity.Tusers;
import java.sql.Date;
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
 * @author Administrateur
 */
@Stateless
public class AccountFacade extends AbstractFacade<Account> implements AccountFacadeLocal {

    @PersistenceContext(unitName = "ccmanagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AccountFacade() {
        super(Account.class);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public Account creer(Account account) throws RollbackFailureException, Exception {
        try {
            Tusers operateur = account.getOperateur();
            if (operateur != null) {
                operateur = getEntityManager().getReference(operateur.getClass(), operateur.getId());
                account.setOperateur(operateur);
            }
            LigneAccount ligneInv = account.getLigneAccount();
            if (ligneInv != null) {
                ligneInv = getEntityManager().getReference(ligneInv.getClass(), ligneInv.getId());
                account.setLigneAccount(ligneInv);
            }
            account = getEntityManager().merge(account);
            if (ligneInv != null) {
                ligneInv.getAccountList().add(account);
                ligneInv = getEntityManager().merge(ligneInv);
            }
            return account;
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public Account MisAjour(Account account) throws NonexistentEntityException, RollbackFailureException, Exception {
        try {
            Account persistentAccount = getEntityManager().find(Account.class, account.getId());
            LigneAccount ligneOld = persistentAccount.getLigneAccount();
            LigneAccount ligneNew = account.getLigneAccount();
            Tusers operateurOld = persistentAccount.getOperateur();
            Tusers operateurNew = account.getOperateur();
            if (ligneNew != null) {
                ligneNew = getEntityManager().getReference(ligneNew.getClass(), ligneNew.getId());
                account.setLigneAccount(ligneNew);
            }
            if (operateurNew != null) {
                operateurNew = getEntityManager().getReference(operateurNew.getClass(), operateurNew.getId());
                account.setOperateur(operateurNew);
            }
            account = getEntityManager().merge(account);

            if (ligneNew != null && !ligneNew.equals(ligneOld)) {
                ligneNew.getAccountList().add(account);
                ligneNew = getEntityManager().merge(ligneNew);
            }

            return account;
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void Supprimer(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        try {
            Account account;
            try {
                account = getEntityManager().getReference(Account.class, id);
                account.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The Account with id " + id + " no longer exists.", enfe);
            }
            LigneAccount ligne = account.getLigneAccount();
            if (ligne != null) {
                ligne.getAccountList().remove(account);
                account = getEntityManager().merge(account);
            }
            getEntityManager().remove(account);
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @Override
    public List<Account> findAll(int societe) {
        Query q = getEntityManager().createNamedQuery("Account.findBySociete");
        q.setParameter("societe", societe);
        return q.getResultList();
    }

    @Override
    public List<Account> findAllToday(int societe) {
        Query q = getEntityManager().createNamedQuery("Account.findAccountSocieteToDay");
        q.setParameter("societe", societe);
        return q.getResultList();
    }

    @Override
    public List<Account> findAllByPeriode(Date d, Date d1, int societe) {
        Query q = getEntityManager().createNamedQuery("Account.findAccountSocieteByPeriode");
        q.setParameter("societe", societe);
        q.setParameter("d", d);
        q.setParameter("d1", d1);
        return q.getResultList();
    }

}
