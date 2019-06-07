/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Account;
import com.eh2s.ocm.Entity.AffectTournerUser;
import com.eh2s.ocm.Entity.Exceptions.IllegalOrphanException;
import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.LigneAccount;
import com.eh2s.ocm.Entity.LigneCommandeFournisseur;
import com.eh2s.ocm.Entity.LigneInventaire;
import com.eh2s.ocm.Entity.LigneSortie;
import com.eh2s.ocm.Entity.Magasin;
import com.eh2s.ocm.Entity.RoleApk;
import com.eh2s.ocm.Entity.Societe;
import com.eh2s.ocm.Entity.SortieCaisse;
import com.eh2s.ocm.Entity.Taffectzone;
import com.eh2s.ocm.Entity.Tgroups;
import com.eh2s.ocm.Entity.Tincidents;
import com.eh2s.ocm.Entity.Tlignecommande;
import com.eh2s.ocm.Entity.Tservices;
import com.eh2s.ocm.Entity.TtraitementTicket;
import com.eh2s.ocm.Entity.Tusers;
import com.eh2s.ocm.Entity.Userplainte;
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
public class TusersFacade extends AbstractFacade<Tusers> implements TusersFacadeLocal {

    @PersistenceContext(unitName = "ccmanagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TusersFacade() {
        super(Tusers.class);
    }

    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    @Override
    public Tusers creer(Tusers tusers) throws RollbackFailureException, Exception {
        if (tusers.getLigneInventaireList() == null) {
            tusers.setLigneInventaireList(new ArrayList<LigneInventaire>());
        }
        if (tusers.getTlignecommandeList() == null) {
            tusers.setTlignecommandeList(new ArrayList<Tlignecommande>());
        }
        if (tusers.getAffectTournerUserList() == null) {
            tusers.setAffectTournerUserList(new ArrayList<AffectTournerUser>());
        }
        if (tusers.getTtraitementTicketList() == null) {
            tusers.setTtraitementTicketList(new ArrayList<TtraitementTicket>());
        }
        if (tusers.getSortieCaisseList() == null) {
            tusers.setSortieCaisseList(new ArrayList<SortieCaisse>());
        }
        if (tusers.getLigneAccountList() == null) {
            tusers.setLigneAccountList(new ArrayList<LigneAccount>());
        }
        if (tusers.getUserplainteList() == null) {
            tusers.setUserplainteList(new ArrayList<Userplainte>());
        }
        if (tusers.getLigneCommandeFournisseurList() == null) {
            tusers.setLigneCommandeFournisseurList(new ArrayList<LigneCommandeFournisseur>());
        }
        if (tusers.getTaffectzoneList() == null) {
            tusers.setTaffectzoneList(new ArrayList<Taffectzone>());
        }
        if (tusers.getTincidentsList() == null) {
            tusers.setTincidentsList(new ArrayList<Tincidents>());
        }
        if (tusers.getRoleApkList() == null) {
            tusers.setRoleApkList(new ArrayList<RoleApk>());
        }
        if (tusers.getAccountList() == null) {
            tusers.setAccountList(new ArrayList<Account>());
        }
        if (tusers.getLigneSortieList() == null) {
            tusers.setLigneSortieList(new ArrayList<LigneSortie>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            Societe societe = tusers.getSociete();
            if (societe != null) {
                societe = em.getReference(societe.getClass(), societe.getId());
                tusers.setSociete(societe);
            }
            Tgroups groupeid = tusers.getGroupeid();
            if (groupeid != null) {
                groupeid = em.getReference(groupeid.getClass(), groupeid.getId());
                tusers.setGroupeid(groupeid);
            }
            Tservices serviceid = tusers.getServiceid();
            if (serviceid != null) {
                serviceid = em.getReference(serviceid.getClass(), serviceid.getId());
                tusers.setServiceid(serviceid);
            }
            Magasin magasin = tusers.getMagasin();
            if (magasin != null) {
                magasin = em.getReference(magasin.getClass(), magasin.getId());
                tusers.setMagasin(magasin);
            }
            List<LigneInventaire> attachedLigneInventaireList = new ArrayList<LigneInventaire>();
            for (LigneInventaire ligneInventaireListLigneInventaireToAttach : tusers.getLigneInventaireList()) {
                ligneInventaireListLigneInventaireToAttach = em.getReference(ligneInventaireListLigneInventaireToAttach.getClass(), ligneInventaireListLigneInventaireToAttach.getId());
                attachedLigneInventaireList.add(ligneInventaireListLigneInventaireToAttach);
            }
            tusers.setLigneInventaireList(attachedLigneInventaireList);
            List<Tlignecommande> attachedTlignecommandeList = new ArrayList<Tlignecommande>();
            for (Tlignecommande tlignecommandeListTlignecommandeToAttach : tusers.getTlignecommandeList()) {
                tlignecommandeListTlignecommandeToAttach = em.getReference(tlignecommandeListTlignecommandeToAttach.getClass(), tlignecommandeListTlignecommandeToAttach.getId());
                attachedTlignecommandeList.add(tlignecommandeListTlignecommandeToAttach);
            }
            tusers.setTlignecommandeList(attachedTlignecommandeList);
            List<AffectTournerUser> attachedAffectTournerUserList = new ArrayList<AffectTournerUser>();
            for (AffectTournerUser affectTournerUserListAffectTournerUserToAttach : tusers.getAffectTournerUserList()) {
                affectTournerUserListAffectTournerUserToAttach = em.getReference(affectTournerUserListAffectTournerUserToAttach.getClass(), affectTournerUserListAffectTournerUserToAttach.getId());
                attachedAffectTournerUserList.add(affectTournerUserListAffectTournerUserToAttach);
            }
            tusers.setAffectTournerUserList(attachedAffectTournerUserList);
            List<TtraitementTicket> attachedTtraitementTicketList = new ArrayList<TtraitementTicket>();
            for (TtraitementTicket ttraitementTicketListTtraitementTicketToAttach : tusers.getTtraitementTicketList()) {
                ttraitementTicketListTtraitementTicketToAttach = em.getReference(ttraitementTicketListTtraitementTicketToAttach.getClass(), ttraitementTicketListTtraitementTicketToAttach.getId());
                attachedTtraitementTicketList.add(ttraitementTicketListTtraitementTicketToAttach);
            }
            tusers.setTtraitementTicketList(attachedTtraitementTicketList);
            List<SortieCaisse> attachedSortieCaisseList = new ArrayList<SortieCaisse>();
            for (SortieCaisse sortieCaisseListSortieCaisseToAttach : tusers.getSortieCaisseList()) {
                sortieCaisseListSortieCaisseToAttach = em.getReference(sortieCaisseListSortieCaisseToAttach.getClass(), sortieCaisseListSortieCaisseToAttach.getId());
                attachedSortieCaisseList.add(sortieCaisseListSortieCaisseToAttach);
            }
            tusers.setSortieCaisseList(attachedSortieCaisseList);
            List<LigneAccount> attachedLigneAccountList = new ArrayList<LigneAccount>();
            for (LigneAccount ligneAccountListLigneAccountToAttach : tusers.getLigneAccountList()) {
                ligneAccountListLigneAccountToAttach = em.getReference(ligneAccountListLigneAccountToAttach.getClass(), ligneAccountListLigneAccountToAttach.getId());
                attachedLigneAccountList.add(ligneAccountListLigneAccountToAttach);
            }
            tusers.setLigneAccountList(attachedLigneAccountList);
            List<Userplainte> attachedUserplainteList = new ArrayList<Userplainte>();
            for (Userplainte userplainteListUserplainteToAttach : tusers.getUserplainteList()) {
                userplainteListUserplainteToAttach = em.getReference(userplainteListUserplainteToAttach.getClass(), userplainteListUserplainteToAttach.getId());
                attachedUserplainteList.add(userplainteListUserplainteToAttach);
            }
            tusers.setUserplainteList(attachedUserplainteList);
            List<LigneCommandeFournisseur> attachedLigneCommandeFournisseurList = new ArrayList<LigneCommandeFournisseur>();
            for (LigneCommandeFournisseur ligneCommandeFournisseurListLigneCommandeFournisseurToAttach : tusers.getLigneCommandeFournisseurList()) {
                ligneCommandeFournisseurListLigneCommandeFournisseurToAttach = em.getReference(ligneCommandeFournisseurListLigneCommandeFournisseurToAttach.getClass(), ligneCommandeFournisseurListLigneCommandeFournisseurToAttach.getId());
                attachedLigneCommandeFournisseurList.add(ligneCommandeFournisseurListLigneCommandeFournisseurToAttach);
            }
            tusers.setLigneCommandeFournisseurList(attachedLigneCommandeFournisseurList);
            List<Taffectzone> attachedTaffectzoneList = new ArrayList<Taffectzone>();
            for (Taffectzone taffectzoneListTaffectzoneToAttach : tusers.getTaffectzoneList()) {
                taffectzoneListTaffectzoneToAttach = em.getReference(taffectzoneListTaffectzoneToAttach.getClass(), taffectzoneListTaffectzoneToAttach.getId());
                attachedTaffectzoneList.add(taffectzoneListTaffectzoneToAttach);
            }
            tusers.setTaffectzoneList(attachedTaffectzoneList);
            List<Tincidents> attachedTincidentsList = new ArrayList<Tincidents>();
            for (Tincidents tincidentsListTincidentsToAttach : tusers.getTincidentsList()) {
                tincidentsListTincidentsToAttach = em.getReference(tincidentsListTincidentsToAttach.getClass(), tincidentsListTincidentsToAttach.getId());
                attachedTincidentsList.add(tincidentsListTincidentsToAttach);
            }
            tusers.setTincidentsList(attachedTincidentsList);
            List<RoleApk> attachedRoleApkList = new ArrayList<RoleApk>();
            for (RoleApk roleApkListRoleApkToAttach : tusers.getRoleApkList()) {
                roleApkListRoleApkToAttach = em.getReference(roleApkListRoleApkToAttach.getClass(), roleApkListRoleApkToAttach.getId());
                attachedRoleApkList.add(roleApkListRoleApkToAttach);
            }
            List<LigneSortie> attachedLigneSortieList = new ArrayList<LigneSortie>();
            for (LigneSortie ligneSortieListLigneSortieToAttach : tusers.getLigneSortieList()) {
                ligneSortieListLigneSortieToAttach = em.getReference(ligneSortieListLigneSortieToAttach.getClass(), ligneSortieListLigneSortieToAttach.getId());
                attachedLigneSortieList.add(ligneSortieListLigneSortieToAttach);
            }
            tusers.setLigneSortieList(attachedLigneSortieList);
            tusers.setRoleApkList(attachedRoleApkList);
            List<Account> attachedAccountList = new ArrayList<Account>();
            for (Account accountListAccountToAttach : tusers.getAccountList()) {
                accountListAccountToAttach = em.getReference(accountListAccountToAttach.getClass(), accountListAccountToAttach.getId());
                attachedAccountList.add(accountListAccountToAttach);
            }
            tusers.setAccountList(attachedAccountList);
            em.persist(tusers);
            if (societe != null) {
                societe.getTusersList().add(tusers);
                societe = em.merge(societe);
            }
            if (groupeid != null) {
                groupeid.getTusersList().add(tusers);
                groupeid = em.merge(groupeid);
            }
            if (serviceid != null) {
                serviceid.getTusersList().add(tusers);
                serviceid = em.merge(serviceid);
            }
            if (magasin != null) {
                Tusers oldMagasignerOfMagasin = magasin.getMagasigner();
                if (oldMagasignerOfMagasin != null) {
                    oldMagasignerOfMagasin.setMagasin(null);
                    oldMagasignerOfMagasin = em.merge(oldMagasignerOfMagasin);
                }
                magasin.setMagasigner(tusers);
                magasin = em.merge(magasin);
            }
            for (LigneInventaire ligneInventaireListLigneInventaire : tusers.getLigneInventaireList()) {
                Tusers oldOperateurOfLigneInventaireListLigneInventaire = ligneInventaireListLigneInventaire.getOperateur();
                ligneInventaireListLigneInventaire.setOperateur(tusers);
                ligneInventaireListLigneInventaire = em.merge(ligneInventaireListLigneInventaire);
                if (oldOperateurOfLigneInventaireListLigneInventaire != null) {
                    oldOperateurOfLigneInventaireListLigneInventaire.getLigneInventaireList().remove(ligneInventaireListLigneInventaire);
                    oldOperateurOfLigneInventaireListLigneInventaire = em.merge(oldOperateurOfLigneInventaireListLigneInventaire);
                }
            }
            for (Tlignecommande tlignecommandeListTlignecommande : tusers.getTlignecommandeList()) {
                Tusers oldPreselleurOfTlignecommandeListTlignecommande = tlignecommandeListTlignecommande.getPreselleur();
                tlignecommandeListTlignecommande.setPreselleur(tusers);
                tlignecommandeListTlignecommande = em.merge(tlignecommandeListTlignecommande);
                if (oldPreselleurOfTlignecommandeListTlignecommande != null) {
                    oldPreselleurOfTlignecommandeListTlignecommande.getTlignecommandeList().remove(tlignecommandeListTlignecommande);
                    oldPreselleurOfTlignecommandeListTlignecommande = em.merge(oldPreselleurOfTlignecommandeListTlignecommande);
                }
            }
            for (AffectTournerUser affectTournerUserListAffectTournerUser : tusers.getAffectTournerUserList()) {
                Tusers oldUserOfAffectTournerUserListAffectTournerUser = affectTournerUserListAffectTournerUser.getUser();
                affectTournerUserListAffectTournerUser.setUser(tusers);
                affectTournerUserListAffectTournerUser = em.merge(affectTournerUserListAffectTournerUser);
                if (oldUserOfAffectTournerUserListAffectTournerUser != null) {
                    oldUserOfAffectTournerUserListAffectTournerUser.getAffectTournerUserList().remove(affectTournerUserListAffectTournerUser);
                    oldUserOfAffectTournerUserListAffectTournerUser = em.merge(oldUserOfAffectTournerUserListAffectTournerUser);
                }
            }
            for (TtraitementTicket ttraitementTicketListTtraitementTicket : tusers.getTtraitementTicketList()) {
                Tusers oldUserOfTtraitementTicketListTtraitementTicket = ttraitementTicketListTtraitementTicket.getUser();
                ttraitementTicketListTtraitementTicket.setUser(tusers);
                ttraitementTicketListTtraitementTicket = em.merge(ttraitementTicketListTtraitementTicket);
                if (oldUserOfTtraitementTicketListTtraitementTicket != null) {
                    oldUserOfTtraitementTicketListTtraitementTicket.getTtraitementTicketList().remove(ttraitementTicketListTtraitementTicket);
                    oldUserOfTtraitementTicketListTtraitementTicket = em.merge(oldUserOfTtraitementTicketListTtraitementTicket);
                }
            }
            for (SortieCaisse sortieCaisseListSortieCaisse : tusers.getSortieCaisseList()) {
                Tusers oldOperateurOfSortieCaisseListSortieCaisse = sortieCaisseListSortieCaisse.getOperateur();
                sortieCaisseListSortieCaisse.setOperateur(tusers);
                sortieCaisseListSortieCaisse = em.merge(sortieCaisseListSortieCaisse);
                if (oldOperateurOfSortieCaisseListSortieCaisse != null) {
                    oldOperateurOfSortieCaisseListSortieCaisse.getSortieCaisseList().remove(sortieCaisseListSortieCaisse);
                    oldOperateurOfSortieCaisseListSortieCaisse = em.merge(oldOperateurOfSortieCaisseListSortieCaisse);
                }
            }
            for (LigneAccount ligneAccountListLigneAccount : tusers.getLigneAccountList()) {
                Tusers oldOperateurOfLigneAccountListLigneAccount = ligneAccountListLigneAccount.getOperateur();
                ligneAccountListLigneAccount.setOperateur(tusers);
                ligneAccountListLigneAccount = em.merge(ligneAccountListLigneAccount);
                if (oldOperateurOfLigneAccountListLigneAccount != null) {
                    oldOperateurOfLigneAccountListLigneAccount.getLigneAccountList().remove(ligneAccountListLigneAccount);
                    oldOperateurOfLigneAccountListLigneAccount = em.merge(oldOperateurOfLigneAccountListLigneAccount);
                }
            }
            for (Userplainte userplainteListUserplainte : tusers.getUserplainteList()) {
                Tusers oldIduserOfUserplainteListUserplainte = userplainteListUserplainte.getIduser();
                userplainteListUserplainte.setIduser(tusers);
                userplainteListUserplainte = em.merge(userplainteListUserplainte);
                if (oldIduserOfUserplainteListUserplainte != null) {
                    oldIduserOfUserplainteListUserplainte.getUserplainteList().remove(userplainteListUserplainte);
                    oldIduserOfUserplainteListUserplainte = em.merge(oldIduserOfUserplainteListUserplainte);
                }
            }
            for (LigneCommandeFournisseur ligneCommandeFournisseurListLigneCommandeFournisseur : tusers.getLigneCommandeFournisseurList()) {
                Tusers oldOperateurOfLigneCommandeFournisseurListLigneCommandeFournisseur = ligneCommandeFournisseurListLigneCommandeFournisseur.getOperateur();
                ligneCommandeFournisseurListLigneCommandeFournisseur.setOperateur(tusers);
                ligneCommandeFournisseurListLigneCommandeFournisseur = em.merge(ligneCommandeFournisseurListLigneCommandeFournisseur);
                if (oldOperateurOfLigneCommandeFournisseurListLigneCommandeFournisseur != null) {
                    oldOperateurOfLigneCommandeFournisseurListLigneCommandeFournisseur.getLigneCommandeFournisseurList().remove(ligneCommandeFournisseurListLigneCommandeFournisseur);
                    oldOperateurOfLigneCommandeFournisseurListLigneCommandeFournisseur = em.merge(oldOperateurOfLigneCommandeFournisseurListLigneCommandeFournisseur);
                }
            }
            for (Taffectzone taffectzoneListTaffectzone : tusers.getTaffectzoneList()) {
                Tusers oldUsersOfTaffectzoneListTaffectzone = taffectzoneListTaffectzone.getUsers();
                taffectzoneListTaffectzone.setUsers(tusers);
                taffectzoneListTaffectzone = em.merge(taffectzoneListTaffectzone);
                if (oldUsersOfTaffectzoneListTaffectzone != null) {
                    oldUsersOfTaffectzoneListTaffectzone.getTaffectzoneList().remove(taffectzoneListTaffectzone);
                    oldUsersOfTaffectzoneListTaffectzone = em.merge(oldUsersOfTaffectzoneListTaffectzone);
                }
            }
            for (Tincidents tincidentsListTincidents : tusers.getTincidentsList()) {
                Tusers oldCreatorOfTincidentsListTincidents = tincidentsListTincidents.getCreator();
                tincidentsListTincidents.setCreator(tusers);
                tincidentsListTincidents = em.merge(tincidentsListTincidents);
                if (oldCreatorOfTincidentsListTincidents != null) {
                    oldCreatorOfTincidentsListTincidents.getTincidentsList().remove(tincidentsListTincidents);
                    oldCreatorOfTincidentsListTincidents = em.merge(oldCreatorOfTincidentsListTincidents);
                }
            }
            for (LigneSortie ligneSortieListLigneSortie : tusers.getLigneSortieList()) {
                Tusers oldOperateurOfLigneSortieListLigneSortie = ligneSortieListLigneSortie.getOperateur();
                ligneSortieListLigneSortie.setOperateur(tusers);
                ligneSortieListLigneSortie = em.merge(ligneSortieListLigneSortie);
                if (oldOperateurOfLigneSortieListLigneSortie != null) {
                    oldOperateurOfLigneSortieListLigneSortie.getLigneSortieList().remove(ligneSortieListLigneSortie);
                    oldOperateurOfLigneSortieListLigneSortie = em.merge(oldOperateurOfLigneSortieListLigneSortie);
                }
            }
            for (RoleApk roleApkListRoleApk : tusers.getRoleApkList()) {
                Tusers oldUtilisateurOfRoleApkListRoleApk = roleApkListRoleApk.getUtilisateur();
                roleApkListRoleApk.setUtilisateur(tusers);
                roleApkListRoleApk = em.merge(roleApkListRoleApk);
                if (oldUtilisateurOfRoleApkListRoleApk != null) {
                    oldUtilisateurOfRoleApkListRoleApk.getRoleApkList().remove(roleApkListRoleApk);
                    oldUtilisateurOfRoleApkListRoleApk = em.merge(oldUtilisateurOfRoleApkListRoleApk);
                }
            }
            for (Account accountListAccount : tusers.getAccountList()) {
                Tusers oldOperateurOfAccountListAccount = accountListAccount.getOperateur();
                accountListAccount.setOperateur(tusers);
                accountListAccount = em.merge(accountListAccount);
                if (oldOperateurOfAccountListAccount != null) {
                    oldOperateurOfAccountListAccount.getAccountList().remove(accountListAccount);
                    oldOperateurOfAccountListAccount = em.merge(oldOperateurOfAccountListAccount);
                }
            }
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
        return tusers;
    }

    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    @Override
    public Tusers MisAJour(Tusers tusers) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            Tusers persistentTusers = em.find(Tusers.class, tusers.getId());
            Societe societeOld = persistentTusers.getSociete();
            Societe societeNew = tusers.getSociete();
            Tgroups groupeidOld = persistentTusers.getGroupeid();
            Tgroups groupeidNew = tusers.getGroupeid();
            Tservices serviceidOld = persistentTusers.getServiceid();
            Tservices serviceidNew = tusers.getServiceid();
            Magasin magasinOld = persistentTusers.getMagasin();
            Magasin magasinNew = tusers.getMagasin();
            List<LigneInventaire> ligneInventaireListOld = persistentTusers.getLigneInventaireList();
            List<LigneInventaire> ligneInventaireListNew = tusers.getLigneInventaireList();
            List<Tlignecommande> tlignecommandeListOld = persistentTusers.getTlignecommandeList();
            List<Tlignecommande> tlignecommandeListNew = tusers.getTlignecommandeList();
            List<AffectTournerUser> affectTournerUserListOld = persistentTusers.getAffectTournerUserList();
            List<AffectTournerUser> affectTournerUserListNew = tusers.getAffectTournerUserList();
            List<TtraitementTicket> ttraitementTicketListOld = persistentTusers.getTtraitementTicketList();
            List<TtraitementTicket> ttraitementTicketListNew = tusers.getTtraitementTicketList();
            List<SortieCaisse> sortieCaisseListOld = persistentTusers.getSortieCaisseList();
            List<SortieCaisse> sortieCaisseListNew = tusers.getSortieCaisseList();
            List<LigneAccount> ligneAccountListOld = persistentTusers.getLigneAccountList();
            List<LigneAccount> ligneAccountListNew = tusers.getLigneAccountList();
            List<Userplainte> userplainteListOld = persistentTusers.getUserplainteList();
            List<Userplainte> userplainteListNew = tusers.getUserplainteList();
            List<LigneCommandeFournisseur> ligneCommandeFournisseurListOld = persistentTusers.getLigneCommandeFournisseurList();
            List<LigneCommandeFournisseur> ligneCommandeFournisseurListNew = tusers.getLigneCommandeFournisseurList();
            List<Taffectzone> taffectzoneListOld = persistentTusers.getTaffectzoneList();
            List<Taffectzone> taffectzoneListNew = tusers.getTaffectzoneList();
            List<Tincidents> tincidentsListOld = persistentTusers.getTincidentsList();
            List<Tincidents> tincidentsListNew = tusers.getTincidentsList();
            List<RoleApk> roleApkListOld = persistentTusers.getRoleApkList();
            List<RoleApk> roleApkListNew = tusers.getRoleApkList();
            List<Account> accountListOld = persistentTusers.getAccountList();
            List<Account> accountListNew = tusers.getAccountList();
            List<LigneSortie> ligneSortieListOld = persistentTusers.getLigneSortieList();
            List<LigneSortie> ligneSortieListNew = tusers.getLigneSortieList();
            if (societeNew != null) {
                societeNew = em.getReference(societeNew.getClass(), societeNew.getId());
                tusers.setSociete(societeNew);
            }
            if (groupeidNew != null) {
                groupeidNew = em.getReference(groupeidNew.getClass(), groupeidNew.getId());
                tusers.setGroupeid(groupeidNew);
            }
            if (serviceidNew != null) {
                serviceidNew = em.getReference(serviceidNew.getClass(), serviceidNew.getId());
                tusers.setServiceid(serviceidNew);
            }
            if (magasinNew != null) {
                magasinNew = em.getReference(magasinNew.getClass(), magasinNew.getId());
                tusers.setMagasin(magasinNew);
            }
            List<LigneInventaire> attachedLigneInventaireListNew = new ArrayList<LigneInventaire>();
            for (LigneInventaire ligneInventaireListNewLigneInventaireToAttach : ligneInventaireListNew) {
                ligneInventaireListNewLigneInventaireToAttach = em.getReference(ligneInventaireListNewLigneInventaireToAttach.getClass(), ligneInventaireListNewLigneInventaireToAttach.getId());
                attachedLigneInventaireListNew.add(ligneInventaireListNewLigneInventaireToAttach);
            }
            ligneInventaireListNew = attachedLigneInventaireListNew;
            tusers.setLigneInventaireList(ligneInventaireListNew);
            List<Tlignecommande> attachedTlignecommandeListNew = new ArrayList<Tlignecommande>();
            for (Tlignecommande tlignecommandeListNewTlignecommandeToAttach : tlignecommandeListNew) {
                tlignecommandeListNewTlignecommandeToAttach = em.getReference(tlignecommandeListNewTlignecommandeToAttach.getClass(), tlignecommandeListNewTlignecommandeToAttach.getId());
                attachedTlignecommandeListNew.add(tlignecommandeListNewTlignecommandeToAttach);
            }
            tlignecommandeListNew = attachedTlignecommandeListNew;
            tusers.setTlignecommandeList(tlignecommandeListNew);
            List<AffectTournerUser> attachedAffectTournerUserListNew = new ArrayList<AffectTournerUser>();
            for (AffectTournerUser affectTournerUserListNewAffectTournerUserToAttach : affectTournerUserListNew) {
                affectTournerUserListNewAffectTournerUserToAttach = em.getReference(affectTournerUserListNewAffectTournerUserToAttach.getClass(), affectTournerUserListNewAffectTournerUserToAttach.getId());
                attachedAffectTournerUserListNew.add(affectTournerUserListNewAffectTournerUserToAttach);
            }
            affectTournerUserListNew = attachedAffectTournerUserListNew;
            tusers.setAffectTournerUserList(affectTournerUserListNew);
            List<TtraitementTicket> attachedTtraitementTicketListNew = new ArrayList<TtraitementTicket>();
            for (TtraitementTicket ttraitementTicketListNewTtraitementTicketToAttach : ttraitementTicketListNew) {
                ttraitementTicketListNewTtraitementTicketToAttach = em.getReference(ttraitementTicketListNewTtraitementTicketToAttach.getClass(), ttraitementTicketListNewTtraitementTicketToAttach.getId());
                attachedTtraitementTicketListNew.add(ttraitementTicketListNewTtraitementTicketToAttach);
            }
            ttraitementTicketListNew = attachedTtraitementTicketListNew;
            tusers.setTtraitementTicketList(ttraitementTicketListNew);
            List<SortieCaisse> attachedSortieCaisseListNew = new ArrayList<SortieCaisse>();
            for (SortieCaisse sortieCaisseListNewSortieCaisseToAttach : sortieCaisseListNew) {
                sortieCaisseListNewSortieCaisseToAttach = em.getReference(sortieCaisseListNewSortieCaisseToAttach.getClass(), sortieCaisseListNewSortieCaisseToAttach.getId());
                attachedSortieCaisseListNew.add(sortieCaisseListNewSortieCaisseToAttach);
            }
            sortieCaisseListNew = attachedSortieCaisseListNew;
            tusers.setSortieCaisseList(sortieCaisseListNew);
            List<LigneAccount> attachedLigneAccountListNew = new ArrayList<LigneAccount>();
            for (LigneAccount ligneAccountListNewLigneAccountToAttach : ligneAccountListNew) {
                ligneAccountListNewLigneAccountToAttach = em.getReference(ligneAccountListNewLigneAccountToAttach.getClass(), ligneAccountListNewLigneAccountToAttach.getId());
                attachedLigneAccountListNew.add(ligneAccountListNewLigneAccountToAttach);
            }
            ligneAccountListNew = attachedLigneAccountListNew;
            tusers.setLigneAccountList(ligneAccountListNew);
            List<Userplainte> attachedUserplainteListNew = new ArrayList<Userplainte>();
            for (Userplainte userplainteListNewUserplainteToAttach : userplainteListNew) {
                userplainteListNewUserplainteToAttach = em.getReference(userplainteListNewUserplainteToAttach.getClass(), userplainteListNewUserplainteToAttach.getId());
                attachedUserplainteListNew.add(userplainteListNewUserplainteToAttach);
            }
            userplainteListNew = attachedUserplainteListNew;
            tusers.setUserplainteList(userplainteListNew);
            List<LigneCommandeFournisseur> attachedLigneCommandeFournisseurListNew = new ArrayList<LigneCommandeFournisseur>();
            for (LigneCommandeFournisseur ligneCommandeFournisseurListNewLigneCommandeFournisseurToAttach : ligneCommandeFournisseurListNew) {
                ligneCommandeFournisseurListNewLigneCommandeFournisseurToAttach = em.getReference(ligneCommandeFournisseurListNewLigneCommandeFournisseurToAttach.getClass(), ligneCommandeFournisseurListNewLigneCommandeFournisseurToAttach.getId());
                attachedLigneCommandeFournisseurListNew.add(ligneCommandeFournisseurListNewLigneCommandeFournisseurToAttach);
            }
            ligneCommandeFournisseurListNew = attachedLigneCommandeFournisseurListNew;
            tusers.setLigneCommandeFournisseurList(ligneCommandeFournisseurListNew);
            List<Taffectzone> attachedTaffectzoneListNew = new ArrayList<Taffectzone>();
            for (Taffectzone taffectzoneListNewTaffectzoneToAttach : taffectzoneListNew) {
                taffectzoneListNewTaffectzoneToAttach = em.getReference(taffectzoneListNewTaffectzoneToAttach.getClass(), taffectzoneListNewTaffectzoneToAttach.getId());
                attachedTaffectzoneListNew.add(taffectzoneListNewTaffectzoneToAttach);
            }
            taffectzoneListNew = attachedTaffectzoneListNew;
            tusers.setTaffectzoneList(taffectzoneListNew);
            List<Tincidents> attachedTincidentsListNew = new ArrayList<Tincidents>();
            for (Tincidents tincidentsListNewTincidentsToAttach : tincidentsListNew) {
                tincidentsListNewTincidentsToAttach = em.getReference(tincidentsListNewTincidentsToAttach.getClass(), tincidentsListNewTincidentsToAttach.getId());
                attachedTincidentsListNew.add(tincidentsListNewTincidentsToAttach);
            }
            tincidentsListNew = attachedTincidentsListNew;
            tusers.setTincidentsList(tincidentsListNew);
            List<LigneSortie> attachedLigneSortieListNew = new ArrayList<LigneSortie>();
            for (LigneSortie ligneSortieListNewLigneSortieToAttach : ligneSortieListNew) {
                ligneSortieListNewLigneSortieToAttach = em.getReference(ligneSortieListNewLigneSortieToAttach.getClass(), ligneSortieListNewLigneSortieToAttach.getId());
                attachedLigneSortieListNew.add(ligneSortieListNewLigneSortieToAttach);
            }
            ligneSortieListNew = attachedLigneSortieListNew;
            tusers.setLigneSortieList(ligneSortieListNew);
            List<RoleApk> attachedRoleApkListNew = new ArrayList<RoleApk>();
            for (RoleApk roleApkListNewRoleApkToAttach : roleApkListNew) {
                roleApkListNewRoleApkToAttach = em.getReference(roleApkListNewRoleApkToAttach.getClass(), roleApkListNewRoleApkToAttach.getId());
                attachedRoleApkListNew.add(roleApkListNewRoleApkToAttach);
            }
            roleApkListNew = attachedRoleApkListNew;
            tusers.setRoleApkList(roleApkListNew);
            List<Account> attachedAccountListNew = new ArrayList<Account>();
            for (Account accountListNewAccountToAttach : accountListNew) {
                accountListNewAccountToAttach = em.getReference(accountListNewAccountToAttach.getClass(), accountListNewAccountToAttach.getId());
                attachedAccountListNew.add(accountListNewAccountToAttach);
            }
            accountListNew = attachedAccountListNew;
            tusers.setAccountList(accountListNew);
            tusers = em.merge(tusers);
            if (societeOld != null && !societeOld.equals(societeNew)) {
                societeOld.getTusersList().remove(tusers);
                societeOld = em.merge(societeOld);
            }
            if (societeNew != null && !societeNew.equals(societeOld)) {
                societeNew.getTusersList().add(tusers);
                societeNew = em.merge(societeNew);
            }
            if (groupeidOld != null && !groupeidOld.equals(groupeidNew)) {
                groupeidOld.getTusersList().remove(tusers);
                groupeidOld = em.merge(groupeidOld);
            }
            if (groupeidNew != null && !groupeidNew.equals(groupeidOld)) {
                groupeidNew.getTusersList().add(tusers);
                groupeidNew = em.merge(groupeidNew);
            }
            if (serviceidOld != null && !serviceidOld.equals(serviceidNew)) {
                serviceidOld.getTusersList().remove(tusers);
                serviceidOld = em.merge(serviceidOld);
            }
            if (serviceidNew != null && !serviceidNew.equals(serviceidOld)) {
                serviceidNew.getTusersList().add(tusers);
                serviceidNew = em.merge(serviceidNew);
            }
            if (magasinOld != null && !magasinOld.equals(magasinNew)) {
                magasinOld.setMagasigner(null);
                magasinOld = em.merge(magasinOld);
            }
            if (magasinNew != null && !magasinNew.equals(magasinOld)) {
                Tusers oldMagasignerOfMagasin = magasinNew.getMagasigner();
                if (oldMagasignerOfMagasin != null) {
                    oldMagasignerOfMagasin.setMagasin(null);
                    oldMagasignerOfMagasin = em.merge(oldMagasignerOfMagasin);
                }
                magasinNew.setMagasigner(tusers);
                magasinNew = em.merge(magasinNew);
            }
            for (LigneInventaire ligneInventaireListOldLigneInventaire : ligneInventaireListOld) {
                if (!ligneInventaireListNew.contains(ligneInventaireListOldLigneInventaire)) {
                    ligneInventaireListOldLigneInventaire.setOperateur(null);
                    ligneInventaireListOldLigneInventaire = em.merge(ligneInventaireListOldLigneInventaire);
                }
            }
            for (LigneInventaire ligneInventaireListNewLigneInventaire : ligneInventaireListNew) {
                if (!ligneInventaireListOld.contains(ligneInventaireListNewLigneInventaire)) {
                    Tusers oldOperateurOfLigneInventaireListNewLigneInventaire = ligneInventaireListNewLigneInventaire.getOperateur();
                    ligneInventaireListNewLigneInventaire.setOperateur(tusers);
                    ligneInventaireListNewLigneInventaire = em.merge(ligneInventaireListNewLigneInventaire);
                    if (oldOperateurOfLigneInventaireListNewLigneInventaire != null && !oldOperateurOfLigneInventaireListNewLigneInventaire.equals(tusers)) {
                        oldOperateurOfLigneInventaireListNewLigneInventaire.getLigneInventaireList().remove(ligneInventaireListNewLigneInventaire);
                        oldOperateurOfLigneInventaireListNewLigneInventaire = em.merge(oldOperateurOfLigneInventaireListNewLigneInventaire);
                    }
                }
            }
            for (Tlignecommande tlignecommandeListOldTlignecommande : tlignecommandeListOld) {
                if (!tlignecommandeListNew.contains(tlignecommandeListOldTlignecommande)) {
                    tlignecommandeListOldTlignecommande.setPreselleur(null);
                    tlignecommandeListOldTlignecommande = em.merge(tlignecommandeListOldTlignecommande);
                }
            }
            for (Tlignecommande tlignecommandeListNewTlignecommande : tlignecommandeListNew) {
                if (!tlignecommandeListOld.contains(tlignecommandeListNewTlignecommande)) {
                    Tusers oldPreselleurOfTlignecommandeListNewTlignecommande = tlignecommandeListNewTlignecommande.getPreselleur();
                    tlignecommandeListNewTlignecommande.setPreselleur(tusers);
                    tlignecommandeListNewTlignecommande = em.merge(tlignecommandeListNewTlignecommande);
                    if (oldPreselleurOfTlignecommandeListNewTlignecommande != null && !oldPreselleurOfTlignecommandeListNewTlignecommande.equals(tusers)) {
                        oldPreselleurOfTlignecommandeListNewTlignecommande.getTlignecommandeList().remove(tlignecommandeListNewTlignecommande);
                        oldPreselleurOfTlignecommandeListNewTlignecommande = em.merge(oldPreselleurOfTlignecommandeListNewTlignecommande);
                    }
                }
            }
            for (AffectTournerUser affectTournerUserListNewAffectTournerUser : affectTournerUserListNew) {
                if (!affectTournerUserListOld.contains(affectTournerUserListNewAffectTournerUser)) {
                    Tusers oldUserOfAffectTournerUserListNewAffectTournerUser = affectTournerUserListNewAffectTournerUser.getUser();
                    affectTournerUserListNewAffectTournerUser.setUser(tusers);
                    affectTournerUserListNewAffectTournerUser = em.merge(affectTournerUserListNewAffectTournerUser);
                    if (oldUserOfAffectTournerUserListNewAffectTournerUser != null && !oldUserOfAffectTournerUserListNewAffectTournerUser.equals(tusers)) {
                        oldUserOfAffectTournerUserListNewAffectTournerUser.getAffectTournerUserList().remove(affectTournerUserListNewAffectTournerUser);
                        oldUserOfAffectTournerUserListNewAffectTournerUser = em.merge(oldUserOfAffectTournerUserListNewAffectTournerUser);
                    }
                }
            }
            for (TtraitementTicket ttraitementTicketListOldTtraitementTicket : ttraitementTicketListOld) {
                if (!ttraitementTicketListNew.contains(ttraitementTicketListOldTtraitementTicket)) {
                    ttraitementTicketListOldTtraitementTicket.setUser(null);
                    ttraitementTicketListOldTtraitementTicket = em.merge(ttraitementTicketListOldTtraitementTicket);
                }
            }
            for (TtraitementTicket ttraitementTicketListNewTtraitementTicket : ttraitementTicketListNew) {
                if (!ttraitementTicketListOld.contains(ttraitementTicketListNewTtraitementTicket)) {
                    Tusers oldUserOfTtraitementTicketListNewTtraitementTicket = ttraitementTicketListNewTtraitementTicket.getUser();
                    ttraitementTicketListNewTtraitementTicket.setUser(tusers);
                    ttraitementTicketListNewTtraitementTicket = em.merge(ttraitementTicketListNewTtraitementTicket);
                    if (oldUserOfTtraitementTicketListNewTtraitementTicket != null && !oldUserOfTtraitementTicketListNewTtraitementTicket.equals(tusers)) {
                        oldUserOfTtraitementTicketListNewTtraitementTicket.getTtraitementTicketList().remove(ttraitementTicketListNewTtraitementTicket);
                        oldUserOfTtraitementTicketListNewTtraitementTicket = em.merge(oldUserOfTtraitementTicketListNewTtraitementTicket);
                    }
                }
            }
            for (SortieCaisse sortieCaisseListNewSortieCaisse : sortieCaisseListNew) {
                if (!sortieCaisseListOld.contains(sortieCaisseListNewSortieCaisse)) {
                    Tusers oldOperateurOfSortieCaisseListNewSortieCaisse = sortieCaisseListNewSortieCaisse.getOperateur();
                    sortieCaisseListNewSortieCaisse.setOperateur(tusers);
                    sortieCaisseListNewSortieCaisse = em.merge(sortieCaisseListNewSortieCaisse);
                    if (oldOperateurOfSortieCaisseListNewSortieCaisse != null && !oldOperateurOfSortieCaisseListNewSortieCaisse.equals(tusers)) {
                        oldOperateurOfSortieCaisseListNewSortieCaisse.getSortieCaisseList().remove(sortieCaisseListNewSortieCaisse);
                        oldOperateurOfSortieCaisseListNewSortieCaisse = em.merge(oldOperateurOfSortieCaisseListNewSortieCaisse);
                    }
                }
            }
            for (LigneAccount ligneAccountListOldLigneAccount : ligneAccountListOld) {
                if (!ligneAccountListNew.contains(ligneAccountListOldLigneAccount)) {
                    ligneAccountListOldLigneAccount.setOperateur(null);
                    ligneAccountListOldLigneAccount = em.merge(ligneAccountListOldLigneAccount);
                }
            }
            for (LigneAccount ligneAccountListNewLigneAccount : ligneAccountListNew) {
                if (!ligneAccountListOld.contains(ligneAccountListNewLigneAccount)) {
                    Tusers oldOperateurOfLigneAccountListNewLigneAccount = ligneAccountListNewLigneAccount.getOperateur();
                    ligneAccountListNewLigneAccount.setOperateur(tusers);
                    ligneAccountListNewLigneAccount = em.merge(ligneAccountListNewLigneAccount);
                    if (oldOperateurOfLigneAccountListNewLigneAccount != null && !oldOperateurOfLigneAccountListNewLigneAccount.equals(tusers)) {
                        oldOperateurOfLigneAccountListNewLigneAccount.getLigneAccountList().remove(ligneAccountListNewLigneAccount);
                        oldOperateurOfLigneAccountListNewLigneAccount = em.merge(oldOperateurOfLigneAccountListNewLigneAccount);
                    }
                }
            }
            for (Userplainte userplainteListOldUserplainte : userplainteListOld) {
                if (!userplainteListNew.contains(userplainteListOldUserplainte)) {
                    userplainteListOldUserplainte.setIduser(null);
                    userplainteListOldUserplainte = em.merge(userplainteListOldUserplainte);
                }
            }
            for (Userplainte userplainteListNewUserplainte : userplainteListNew) {
                if (!userplainteListOld.contains(userplainteListNewUserplainte)) {
                    Tusers oldIduserOfUserplainteListNewUserplainte = userplainteListNewUserplainte.getIduser();
                    userplainteListNewUserplainte.setIduser(tusers);
                    userplainteListNewUserplainte = em.merge(userplainteListNewUserplainte);
                    if (oldIduserOfUserplainteListNewUserplainte != null && !oldIduserOfUserplainteListNewUserplainte.equals(tusers)) {
                        oldIduserOfUserplainteListNewUserplainte.getUserplainteList().remove(userplainteListNewUserplainte);
                        oldIduserOfUserplainteListNewUserplainte = em.merge(oldIduserOfUserplainteListNewUserplainte);
                    }
                }
            }
            for (LigneCommandeFournisseur ligneCommandeFournisseurListNewLigneCommandeFournisseur : ligneCommandeFournisseurListNew) {
                if (!ligneCommandeFournisseurListOld.contains(ligneCommandeFournisseurListNewLigneCommandeFournisseur)) {
                    Tusers oldOperateurOfLigneCommandeFournisseurListNewLigneCommandeFournisseur = ligneCommandeFournisseurListNewLigneCommandeFournisseur.getOperateur();
                    ligneCommandeFournisseurListNewLigneCommandeFournisseur.setOperateur(tusers);
                    ligneCommandeFournisseurListNewLigneCommandeFournisseur = em.merge(ligneCommandeFournisseurListNewLigneCommandeFournisseur);
                    if (oldOperateurOfLigneCommandeFournisseurListNewLigneCommandeFournisseur != null && !oldOperateurOfLigneCommandeFournisseurListNewLigneCommandeFournisseur.equals(tusers)) {
                        oldOperateurOfLigneCommandeFournisseurListNewLigneCommandeFournisseur.getLigneCommandeFournisseurList().remove(ligneCommandeFournisseurListNewLigneCommandeFournisseur);
                        oldOperateurOfLigneCommandeFournisseurListNewLigneCommandeFournisseur = em.merge(oldOperateurOfLigneCommandeFournisseurListNewLigneCommandeFournisseur);
                    }
                }
            }
            for (Taffectzone taffectzoneListOldTaffectzone : taffectzoneListOld) {
                if (!taffectzoneListNew.contains(taffectzoneListOldTaffectzone)) {
                    taffectzoneListOldTaffectzone.setUsers(null);
                    taffectzoneListOldTaffectzone = em.merge(taffectzoneListOldTaffectzone);
                }
            }
            for (Taffectzone taffectzoneListNewTaffectzone : taffectzoneListNew) {
                if (!taffectzoneListOld.contains(taffectzoneListNewTaffectzone)) {
                    Tusers oldUsersOfTaffectzoneListNewTaffectzone = taffectzoneListNewTaffectzone.getUsers();
                    taffectzoneListNewTaffectzone.setUsers(tusers);
                    taffectzoneListNewTaffectzone = em.merge(taffectzoneListNewTaffectzone);
                    if (oldUsersOfTaffectzoneListNewTaffectzone != null && !oldUsersOfTaffectzoneListNewTaffectzone.equals(tusers)) {
                        oldUsersOfTaffectzoneListNewTaffectzone.getTaffectzoneList().remove(taffectzoneListNewTaffectzone);
                        oldUsersOfTaffectzoneListNewTaffectzone = em.merge(oldUsersOfTaffectzoneListNewTaffectzone);
                    }
                }
            }
            for (Tincidents tincidentsListOldTincidents : tincidentsListOld) {
                if (!tincidentsListNew.contains(tincidentsListOldTincidents)) {
                    tincidentsListOldTincidents.setCreator(null);
                    tincidentsListOldTincidents = em.merge(tincidentsListOldTincidents);
                }
            }
            for (Tincidents tincidentsListNewTincidents : tincidentsListNew) {
                if (!tincidentsListOld.contains(tincidentsListNewTincidents)) {
                    Tusers oldCreatorOfTincidentsListNewTincidents = tincidentsListNewTincidents.getCreator();
                    tincidentsListNewTincidents.setCreator(tusers);
                    tincidentsListNewTincidents = em.merge(tincidentsListNewTincidents);
                    if (oldCreatorOfTincidentsListNewTincidents != null && !oldCreatorOfTincidentsListNewTincidents.equals(tusers)) {
                        oldCreatorOfTincidentsListNewTincidents.getTincidentsList().remove(tincidentsListNewTincidents);
                        oldCreatorOfTincidentsListNewTincidents = em.merge(oldCreatorOfTincidentsListNewTincidents);
                    }
                }
            }
            for (LigneSortie ligneSortieListOldLigneSortie : ligneSortieListOld) {
                if (!ligneSortieListNew.contains(ligneSortieListOldLigneSortie)) {
                    ligneSortieListOldLigneSortie.setOperateur(null);
                    ligneSortieListOldLigneSortie = em.merge(ligneSortieListOldLigneSortie);
                }
            }
            for (LigneSortie ligneSortieListNewLigneSortie : ligneSortieListNew) {
                if (!ligneSortieListOld.contains(ligneSortieListNewLigneSortie)) {
                    Tusers oldOperateurOfLigneSortieListNewLigneSortie = ligneSortieListNewLigneSortie.getOperateur();
                    ligneSortieListNewLigneSortie.setOperateur(tusers);
                    ligneSortieListNewLigneSortie = em.merge(ligneSortieListNewLigneSortie);
                    if (oldOperateurOfLigneSortieListNewLigneSortie != null && !oldOperateurOfLigneSortieListNewLigneSortie.equals(tusers)) {
                        oldOperateurOfLigneSortieListNewLigneSortie.getLigneSortieList().remove(ligneSortieListNewLigneSortie);
                        oldOperateurOfLigneSortieListNewLigneSortie = em.merge(oldOperateurOfLigneSortieListNewLigneSortie);
                    }
                }
            }
            for (RoleApk roleApkListNewRoleApk : roleApkListNew) {
                if (!roleApkListOld.contains(roleApkListNewRoleApk)) {
                    Tusers oldUtilisateurOfRoleApkListNewRoleApk = roleApkListNewRoleApk.getUtilisateur();
                    roleApkListNewRoleApk.setUtilisateur(tusers);
                    roleApkListNewRoleApk = em.merge(roleApkListNewRoleApk);
                    if (oldUtilisateurOfRoleApkListNewRoleApk != null && !oldUtilisateurOfRoleApkListNewRoleApk.equals(tusers)) {
                        oldUtilisateurOfRoleApkListNewRoleApk.getRoleApkList().remove(roleApkListNewRoleApk);
                        oldUtilisateurOfRoleApkListNewRoleApk = em.merge(oldUtilisateurOfRoleApkListNewRoleApk);
                    }
                }
            }
            for (Account accountListOldAccount : accountListOld) {
                if (!accountListNew.contains(accountListOldAccount)) {
                    accountListOldAccount.setOperateur(null);
                    accountListOldAccount = em.merge(accountListOldAccount);
                }
            }
            for (Account accountListNewAccount : accountListNew) {
                if (!accountListOld.contains(accountListNewAccount)) {
                    Tusers oldOperateurOfAccountListNewAccount = accountListNewAccount.getOperateur();
                    accountListNewAccount.setOperateur(tusers);
                    accountListNewAccount = em.merge(accountListNewAccount);
                    if (oldOperateurOfAccountListNewAccount != null && !oldOperateurOfAccountListNewAccount.equals(tusers)) {
                        oldOperateurOfAccountListNewAccount.getAccountList().remove(accountListNewAccount);
                        oldOperateurOfAccountListNewAccount = em.merge(oldOperateurOfAccountListNewAccount);
                    }
                }
            }
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
        return tusers;
    }

    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    @Override
    public void Supprimer(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            Tusers tusers;
            try {
                tusers = em.getReference(Tusers.class, id);
                tusers.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tusers with id " + id + " no longer exists.", enfe);
            }
            Societe societe = tusers.getSociete();
            if (societe != null) {
                societe.getTusersList().remove(tusers);
                societe = em.merge(societe);
            }
            Tgroups groupeid = tusers.getGroupeid();
            if (groupeid != null) {
                groupeid.getTusersList().remove(tusers);
                groupeid = em.merge(groupeid);
            }
            Tservices serviceid = tusers.getServiceid();
            if (serviceid != null) {
                serviceid.getTusersList().remove(tusers);
                serviceid = em.merge(serviceid);
            }
            Magasin magasin = tusers.getMagasin();
            if (magasin != null) {
                magasin.setMagasigner(null);
                magasin = em.merge(magasin);
            }
            List<LigneInventaire> ligneInventaireList = tusers.getLigneInventaireList();
            for (LigneInventaire ligneInventaireListLigneInventaire : ligneInventaireList) {
                ligneInventaireListLigneInventaire.setOperateur(null);
                ligneInventaireListLigneInventaire = em.merge(ligneInventaireListLigneInventaire);
            }
            List<Tlignecommande> tlignecommandeList = tusers.getTlignecommandeList();
            for (Tlignecommande tlignecommandeListTlignecommande : tlignecommandeList) {
                tlignecommandeListTlignecommande.setPreselleur(null);
                tlignecommandeListTlignecommande = em.merge(tlignecommandeListTlignecommande);
            }
            List<TtraitementTicket> ttraitementTicketList = tusers.getTtraitementTicketList();
            for (TtraitementTicket ttraitementTicketListTtraitementTicket : ttraitementTicketList) {
                ttraitementTicketListTtraitementTicket.setUser(null);
                ttraitementTicketListTtraitementTicket = em.merge(ttraitementTicketListTtraitementTicket);
            }
            List<LigneAccount> ligneAccountList = tusers.getLigneAccountList();
            for (LigneAccount ligneAccountListLigneAccount : ligneAccountList) {
                ligneAccountListLigneAccount.setOperateur(null);
                ligneAccountListLigneAccount = em.merge(ligneAccountListLigneAccount);
            }
            List<Userplainte> userplainteList = tusers.getUserplainteList();
            for (Userplainte userplainteListUserplainte : userplainteList) {
                userplainteListUserplainte.setIduser(null);
                userplainteListUserplainte = em.merge(userplainteListUserplainte);
            }
            List<Taffectzone> taffectzoneList = tusers.getTaffectzoneList();
            for (Taffectzone taffectzoneListTaffectzone : taffectzoneList) {
                taffectzoneListTaffectzone.setUsers(null);
                taffectzoneListTaffectzone = em.merge(taffectzoneListTaffectzone);
            }
            List<Tincidents> tincidentsList = tusers.getTincidentsList();
            for (Tincidents tincidentsListTincidents : tincidentsList) {
                tincidentsListTincidents.setCreator(null);
                tincidentsListTincidents = em.merge(tincidentsListTincidents);
            }
            List<LigneSortie> ligneSortieList = tusers.getLigneSortieList();
            for (LigneSortie ligneSortieListLigneSortie : ligneSortieList) {
                ligneSortieListLigneSortie.setOperateur(null);
                ligneSortieListLigneSortie = em.merge(ligneSortieListLigneSortie);
            }
            List<Account> accountList = tusers.getAccountList();
            for (Account accountListAccount : accountList) {
                accountListAccount.setOperateur(null);
                accountListAccount = em.merge(accountListAccount);
            }
            em.remove(tusers);
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @Override
    public List<Tusers> findTusersEntities() {
        return findTusersEntities(true, -1, -1);
    }

    @Override
    public List<Tusers> findTusersEntities(int maxResults, int firstResult) {
        return findTusersEntities(false, maxResults, firstResult);
    }

    private List<Tusers> findTusersEntities(boolean all, int maxResults, int firstResult) {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Tusers.class));
        Query q = getEntityManager().createQuery(cq);
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    @Override
    public Tusers findTusers(Integer id) {
        return getEntityManager().find(Tusers.class, id);
    }

    @Override
    public Tusers findByLogin(String login) {
        Query q = getEntityManager().createNamedQuery("Tusers.findByLogin");
        q.setParameter("login", login);
        if (!q.getResultList().isEmpty() && q.getResultList().size() == 1) {
            return (Tusers) q.getSingleResult();
        } else {
            return null;
        }
    }

    @Override
    public int getTusersCount() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<Tusers> rt = cq.from(Tusers.class);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    @Override
    public List<Tusers> findTclientsByRegion(int region, String societe) {
        Query q = getEntityManager().createNamedQuery("Tusers.findByRegion");
        q.setParameter("region", region);
        q.setParameter("imma", societe);
        return q.getResultList();
    }

    @Override
    public List<Tusers> findAll(String societe) {
        Query q = getEntityManager().createNamedQuery("Tusers.findAll");
        q.setParameter("imma", societe);
        return q.getResultList();
    }

}
