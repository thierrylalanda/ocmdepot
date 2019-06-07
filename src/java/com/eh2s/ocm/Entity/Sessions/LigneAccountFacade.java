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
import com.eh2s.ocm.Entity.LigneInventaire;
import com.eh2s.ocm.Entity.Societe;
import com.eh2s.ocm.Entity.Tclients;
import com.eh2s.ocm.Entity.Tlignecommande;
import com.eh2s.ocm.Entity.Tusers;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Administrateur
 */
@Stateless
public class LigneAccountFacade extends AbstractFacade<LigneAccount> implements LigneAccountFacadeLocal {

    @PersistenceContext(unitName = "ccmanagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LigneAccountFacade() {
        super(LigneAccount.class);
    }

    @Override
    public LigneAccount creer(LigneAccount ligneAccount) throws RollbackFailureException, Exception {
        if (ligneAccount.getAccountList() == null) {
            ligneAccount.setAccountList(new ArrayList<Account>());
        }
        try {
            Tclients client = ligneAccount.getClient();
            if (client != null) {
                client = getEntityManager().getReference(client.getClass(), client.getId());
                ligneAccount.setClient(client);
            }
            Societe societe = ligneAccount.getSociete();
            if (societe != null) {
                societe = getEntityManager().getReference(societe.getClass(), societe.getId());
                ligneAccount.setSociete(societe);
            }
            Tlignecommande ligneCommande = ligneAccount.getLigneCommande();
            if (ligneCommande != null) {
                ligneCommande = getEntityManager().getReference(ligneCommande.getClass(), ligneCommande.getId());
                ligneAccount.setLigneCommande(ligneCommande);
            }
            Tusers operateur = ligneAccount.getOperateur();
            if (operateur != null) {
                operateur = getEntityManager().getReference(operateur.getClass(), operateur.getId());
                ligneAccount.setOperateur(operateur);
            }
            List<Account> attachedAccountList = new ArrayList<Account>();
            for (Account accountListAccountToAttach : ligneAccount.getAccountList()) {
                accountListAccountToAttach = getEntityManager().getReference(accountListAccountToAttach.getClass(), accountListAccountToAttach.getId());
                attachedAccountList.add(accountListAccountToAttach);
            }
            ligneAccount.setAccountList(attachedAccountList);
            getEntityManager().persist(ligneAccount);
            if (client != null) {
                client.getLigneAccountList().add(ligneAccount);
                client = getEntityManager().merge(client);
            }
            if (societe != null) {
                societe.getLigneAccountList().add(ligneAccount);
                societe = getEntityManager().merge(societe);
            }
            if (ligneCommande != null) {
                ligneCommande.getLigneAccountList().add(ligneAccount);
                ligneCommande = getEntityManager().merge(ligneCommande);
            }
            if (operateur != null) {
                operateur.getLigneAccountList().add(ligneAccount);
                operateur = getEntityManager().merge(operateur);
            }
            for (Account accountListAccount : ligneAccount.getAccountList()) {
                LigneAccount oldLigneAccountOfAccountListAccount = accountListAccount.getLigneAccount();
                accountListAccount.setLigneAccount(ligneAccount);
                accountListAccount = getEntityManager().merge(accountListAccount);
                if (oldLigneAccountOfAccountListAccount != null) {
                    oldLigneAccountOfAccountListAccount.getAccountList().remove(accountListAccount);
                    oldLigneAccountOfAccountListAccount = getEntityManager().merge(oldLigneAccountOfAccountListAccount);
                }
            }
            return ligneAccount;
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @Override
    public LigneAccount MisAjour(LigneAccount ligneAccount) throws NonexistentEntityException, RollbackFailureException, Exception {
        try {
            LigneAccount persistentLigneAccount = getEntityManager().find(LigneAccount.class, ligneAccount.getId());
            Tclients clientOld = persistentLigneAccount.getClient();
            Tclients clientNew = ligneAccount.getClient();
            Societe societeOld = persistentLigneAccount.getSociete();
            Societe societeNew = ligneAccount.getSociete();
            Tlignecommande ligneCommandeOld = persistentLigneAccount.getLigneCommande();
            Tlignecommande ligneCommandeNew = ligneAccount.getLigneCommande();
            Tusers operateurOld = persistentLigneAccount.getOperateur();
            Tusers operateurNew = ligneAccount.getOperateur();
            List<Account> accountListOld = persistentLigneAccount.getAccountList();
            List<Account> accountListNew = ligneAccount.getAccountList();
            if (clientNew != null) {
                clientNew = getEntityManager().getReference(clientNew.getClass(), clientNew.getId());
                ligneAccount.setClient(clientNew);
            }
            if (societeNew != null) {
                societeNew = getEntityManager().getReference(societeNew.getClass(), societeNew.getId());
                ligneAccount.setSociete(societeNew);
            }
            if (ligneCommandeNew != null) {
                ligneCommandeNew = getEntityManager().getReference(ligneCommandeNew.getClass(), ligneCommandeNew.getId());
                ligneAccount.setLigneCommande(ligneCommandeNew);
            }
            if (operateurNew != null) {
                operateurNew = getEntityManager().getReference(operateurNew.getClass(), operateurNew.getId());
                ligneAccount.setOperateur(operateurNew);
            }
            List<Account> attachedAccountListNew = new ArrayList<Account>();
            for (Account accountListNewAccountToAttach : accountListNew) {
                accountListNewAccountToAttach = getEntityManager().getReference(accountListNewAccountToAttach.getClass(), accountListNewAccountToAttach.getId());
                attachedAccountListNew.add(accountListNewAccountToAttach);
            }
            accountListNew = attachedAccountListNew;
            ligneAccount.setAccountList(accountListNew);
            ligneAccount = getEntityManager().merge(ligneAccount);
            if (clientOld != null && !clientOld.equals(clientNew)) {
                clientOld.getLigneAccountList().remove(ligneAccount);
                clientOld = getEntityManager().merge(clientOld);
            }
            if (clientNew != null && !clientNew.equals(clientOld)) {
                clientNew.getLigneAccountList().add(ligneAccount);
                clientNew = getEntityManager().merge(clientNew);
            }
            if (societeOld != null && !societeOld.equals(societeNew)) {
                societeOld.getLigneAccountList().remove(ligneAccount);
                societeOld = getEntityManager().merge(societeOld);
            }
            if (societeNew != null && !societeNew.equals(societeOld)) {
                societeNew.getLigneAccountList().add(ligneAccount);
                societeNew = getEntityManager().merge(societeNew);
            }
            if (ligneCommandeOld != null && !ligneCommandeOld.equals(ligneCommandeNew)) {
                ligneCommandeOld.getLigneAccountList().remove(ligneAccount);
                ligneCommandeOld = getEntityManager().merge(ligneCommandeOld);
            }
            if (ligneCommandeNew != null && !ligneCommandeNew.equals(ligneCommandeOld)) {
                ligneCommandeNew.getLigneAccountList().add(ligneAccount);
                ligneCommandeNew = getEntityManager().merge(ligneCommandeNew);
            }
            if (operateurOld != null && !operateurOld.equals(operateurNew)) {
                operateurOld.getLigneAccountList().remove(ligneAccount);
                operateurOld = getEntityManager().merge(operateurOld);
            }
            if (operateurNew != null && !operateurNew.equals(operateurOld)) {
                operateurNew.getLigneAccountList().add(ligneAccount);
                operateurNew = getEntityManager().merge(operateurNew);
            }
            for (Account accountListOldAccount : accountListOld) {
                if (!accountListNew.contains(accountListOldAccount)) {
                    accountListOldAccount.setLigneAccount(null);
                    accountListOldAccount = getEntityManager().merge(accountListOldAccount);
                }
            }
            for (Account accountListNewAccount : accountListNew) {
                if (!accountListOld.contains(accountListNewAccount)) {
                    LigneAccount oldLigneAccountOfAccountListNewAccount = accountListNewAccount.getLigneAccount();
                    accountListNewAccount.setLigneAccount(ligneAccount);
                    accountListNewAccount = getEntityManager().merge(accountListNewAccount);
                    if (oldLigneAccountOfAccountListNewAccount != null && !oldLigneAccountOfAccountListNewAccount.equals(ligneAccount)) {
                        oldLigneAccountOfAccountListNewAccount.getAccountList().remove(accountListNewAccount);
                        oldLigneAccountOfAccountListNewAccount = getEntityManager().merge(oldLigneAccountOfAccountListNewAccount);
                    }
                }
            }
            return ligneAccount;
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @Override
    public void Supprimer(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        try {
            LigneAccount ligneAccount;
            try {
                ligneAccount = getEntityManager().getReference(LigneAccount.class, id);
                ligneAccount.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ligneAccount with id " + id + " no longer exists.", enfe);
            }
            Tclients client = ligneAccount.getClient();
            if (client != null) {
                client.getLigneAccountList().remove(ligneAccount);
                client = getEntityManager().merge(client);
            }
            Societe societe = ligneAccount.getSociete();
            if (societe != null) {
                societe.getLigneAccountList().remove(ligneAccount);
                societe = getEntityManager().merge(societe);
            }
            Tlignecommande ligneCommande = ligneAccount.getLigneCommande();
            if (ligneCommande != null) {
                ligneCommande.getLigneAccountList().remove(ligneAccount);
                ligneCommande = getEntityManager().merge(ligneCommande);
            }
            Tusers operateur = ligneAccount.getOperateur();
            if (operateur != null) {
                operateur.getLigneAccountList().remove(ligneAccount);
                operateur = getEntityManager().merge(operateur);
            }
            List<Account> accountList = ligneAccount.getAccountList();
            for (Account accountListAccount : accountList) {
                accountListAccount.setLigneAccount(null);
                accountListAccount = getEntityManager().merge(accountListAccount);
            }
            getEntityManager().remove(ligneAccount);
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @Override
    public List<LigneAccount> findBySociete(int societe) {
        Query q = getEntityManager().createNamedQuery("LigneAccount.findBySociete");
        q.setParameter("societe", societe);
        return q.getResultList();
    }

    @Override
    public List<LigneAccount> findByPeriodeSociete(Date debut, Date fin, int societe) {
        Query q = getEntityManager().createNamedQuery("LigneAccount.findByPeriodeSociete");
        q.setParameter("d", debut);
        q.setParameter("d1", fin);
        q.setParameter("societe", societe);
        return q.getResultList();
    }

    @Override
    public List<LigneAccount> findByClient(int client) {
        Query q = getEntityManager().createNamedQuery("LigneAccount.findByClient");
        q.setParameter("client", client);
        return q.getResultList();
    }

    @Override
    public List<LigneAccount> historiqueAccount(Date debut, Date fin, Integer type, Integer entite, Integer etat) {

        if (etat != -1) {
            if (type == 1) {
                Query q = getEntityManager().createNamedQuery("LigneAccount.findByPeriodeClientAndEtat");
                q.setParameter("d", debut);
                q.setParameter("d1", fin);
                q.setParameter("etat", etat);
                q.setParameter("client", entite);
                return q.getResultList();
            } else {
                Query q = getEntityManager().createNamedQuery("LigneAccount.findByPeriodeSocieteAndEtat");
                q.setParameter("d", debut);
                q.setParameter("d1", fin);
                q.setParameter("etat", etat);
                q.setParameter("societe", entite);
                return q.getResultList();
            }
        } else {
            if (type == 1) {
                Query q = getEntityManager().createNamedQuery("LigneAccount.findByPeriodeClient");
                q.setParameter("d", debut);
                q.setParameter("d1", fin);
                q.setParameter("client", entite);
                return q.getResultList();
            } else {
                Query q = getEntityManager().createNamedQuery("LigneAccount.findByPeriodeSociete");
                q.setParameter("d", debut);
                q.setParameter("d1", fin);
                q.setParameter("societe", entite);
                return q.getResultList();
            }
        }

    }

}
