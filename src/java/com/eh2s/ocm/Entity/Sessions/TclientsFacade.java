/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.CompteEmballage;
import com.eh2s.ocm.Entity.CompteRistourne;
import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.LigneAccount;
import com.eh2s.ocm.Entity.Societe;
import com.eh2s.ocm.Entity.Tclients;
import com.eh2s.ocm.Entity.Tincidents;
import com.eh2s.ocm.Entity.Tlignecommande;
import com.eh2s.ocm.Entity.Tourner;
import com.eh2s.ocm.Entity.Tsecteurs;
import com.eh2s.ocm.Entity.Ttypeclients;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.CacheRetrieveMode;
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
public class TclientsFacade extends AbstractFacade<Tclients> implements TclientsFacadeLocal {

    @PersistenceContext(unitName = "ccmanagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TclientsFacade() {
        super(Tclients.class);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public Tclients creer(Tclients tclients) throws RollbackFailureException, Exception {
        if (tclients.getTlignecommandeList() == null) {
            tclients.setTlignecommandeList(new ArrayList<Tlignecommande>());
        }
        if (tclients.getCompteRistourneList() == null) {
            tclients.setCompteRistourneList(new ArrayList<CompteRistourne>());
        }
        if (tclients.getLigneAccountList() == null) {
            tclients.setLigneAccountList(new ArrayList<LigneAccount>());
        }
        if (tclients.getCompteEmballageList() == null) {
            tclients.setCompteEmballageList(new ArrayList<CompteEmballage>());
        }
        if (tclients.getTincidentsList() == null) {
            tclients.setTincidentsList(new ArrayList<Tincidents>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            Ttypeclients typeclientid = tclients.getTypeclientid();
            if (typeclientid != null) {
                typeclientid = em.getReference(typeclientid.getClass(), typeclientid.getId());
                tclients.setTypeclientid(typeclientid);
            }
            Societe societe = tclients.getSociete();
            if (societe != null) {
                societe = em.getReference(societe.getClass(), societe.getId());
                tclients.setSociete(societe);
            }
            Tsecteurs secteurid = tclients.getSecteurid();
            if (secteurid != null) {
                secteurid = em.getReference(secteurid.getClass(), secteurid.getId());
                tclients.setSecteurid(secteurid);
            }
            Tourner tourner = tclients.getTourner();
            if (tourner != null) {
                tourner = em.getReference(tourner.getClass(), tourner.getId());
                tclients.setTourner(tourner);
            }
            List<Tlignecommande> attachedTlignecommandeList = new ArrayList<Tlignecommande>();
            for (Tlignecommande tlignecommandeListTlignecommandeToAttach : tclients.getTlignecommandeList()) {
                tlignecommandeListTlignecommandeToAttach = em.getReference(tlignecommandeListTlignecommandeToAttach.getClass(), tlignecommandeListTlignecommandeToAttach.getId());
                attachedTlignecommandeList.add(tlignecommandeListTlignecommandeToAttach);
            }
            tclients.setTlignecommandeList(attachedTlignecommandeList);
            List<CompteRistourne> attachedCompteRistourneList = new ArrayList<CompteRistourne>();
            for (CompteRistourne compteRistourneListCompteRistourneToAttach : tclients.getCompteRistourneList()) {
                compteRistourneListCompteRistourneToAttach = em.getReference(compteRistourneListCompteRistourneToAttach.getClass(), compteRistourneListCompteRistourneToAttach.getId());
                attachedCompteRistourneList.add(compteRistourneListCompteRistourneToAttach);
            }
            tclients.setCompteRistourneList(attachedCompteRistourneList);
            List<LigneAccount> attachedLigneAccountList = new ArrayList<LigneAccount>();
            for (LigneAccount ligneAccountListLigneAccountToAttach : tclients.getLigneAccountList()) {
                ligneAccountListLigneAccountToAttach = em.getReference(ligneAccountListLigneAccountToAttach.getClass(), ligneAccountListLigneAccountToAttach.getId());
                attachedLigneAccountList.add(ligneAccountListLigneAccountToAttach);
            }
            tclients.setLigneAccountList(attachedLigneAccountList);
            List<CompteEmballage> attachedCompteEmballageList = new ArrayList<CompteEmballage>();
            for (CompteEmballage compteEmballageListCompteEmballageToAttach : tclients.getCompteEmballageList()) {
                compteEmballageListCompteEmballageToAttach = em.getReference(compteEmballageListCompteEmballageToAttach.getClass(), compteEmballageListCompteEmballageToAttach.getId());
                attachedCompteEmballageList.add(compteEmballageListCompteEmballageToAttach);
            }
            tclients.setCompteEmballageList(attachedCompteEmballageList);
            List<Tincidents> attachedTincidentsList = new ArrayList<Tincidents>();
            for (Tincidents tincidentsListTincidentsToAttach : tclients.getTincidentsList()) {
                tincidentsListTincidentsToAttach = em.getReference(tincidentsListTincidentsToAttach.getClass(), tincidentsListTincidentsToAttach.getId());
                attachedTincidentsList.add(tincidentsListTincidentsToAttach);
            }
            tclients.setTincidentsList(attachedTincidentsList);
            em.persist(tclients);
            if (typeclientid != null) {
                typeclientid.getTclientsList().add(tclients);
                typeclientid = em.merge(typeclientid);
            }
            if (societe != null) {
                societe.getTclientsList().add(tclients);
                societe = em.merge(societe);
            }
            if (secteurid != null) {
                secteurid.getTclientsList().add(tclients);
                secteurid = em.merge(secteurid);
            }
            if (tourner != null) {
                tourner.getTclientsList().add(tclients);
                tourner = em.merge(tourner);
            }
            for (Tlignecommande tlignecommandeListTlignecommande : tclients.getTlignecommandeList()) {
                Tclients oldClientOfTlignecommandeListTlignecommande = tlignecommandeListTlignecommande.getClient();
                tlignecommandeListTlignecommande.setClient(tclients);
                tlignecommandeListTlignecommande = em.merge(tlignecommandeListTlignecommande);
                if (oldClientOfTlignecommandeListTlignecommande != null) {
                    oldClientOfTlignecommandeListTlignecommande.getTlignecommandeList().remove(tlignecommandeListTlignecommande);
                    oldClientOfTlignecommandeListTlignecommande = em.merge(oldClientOfTlignecommandeListTlignecommande);
                }
            }
            for (CompteRistourne compteRistourneListCompteRistourne : tclients.getCompteRistourneList()) {
                Tclients oldClientOfCompteRistourneListCompteRistourne = compteRistourneListCompteRistourne.getClient();
                compteRistourneListCompteRistourne.setClient(tclients);
                compteRistourneListCompteRistourne = em.merge(compteRistourneListCompteRistourne);
                if (oldClientOfCompteRistourneListCompteRistourne != null) {
                    oldClientOfCompteRistourneListCompteRistourne.getCompteRistourneList().remove(compteRistourneListCompteRistourne);
                    oldClientOfCompteRistourneListCompteRistourne = em.merge(oldClientOfCompteRistourneListCompteRistourne);
                }
            }
            for (LigneAccount ligneAccountListLigneAccount : tclients.getLigneAccountList()) {
                Tclients oldClientOfLigneAccountListLigneAccount = ligneAccountListLigneAccount.getClient();
                ligneAccountListLigneAccount.setClient(tclients);
                ligneAccountListLigneAccount = em.merge(ligneAccountListLigneAccount);
                if (oldClientOfLigneAccountListLigneAccount != null) {
                    oldClientOfLigneAccountListLigneAccount.getLigneAccountList().remove(ligneAccountListLigneAccount);
                    oldClientOfLigneAccountListLigneAccount = em.merge(oldClientOfLigneAccountListLigneAccount);
                }
            }
            for (CompteEmballage compteEmballageListCompteEmballage : tclients.getCompteEmballageList()) {
                Tclients oldClientOfCompteEmballageListCompteEmballage = compteEmballageListCompteEmballage.getClient();
                compteEmballageListCompteEmballage.setClient(tclients);
                compteEmballageListCompteEmballage = em.merge(compteEmballageListCompteEmballage);
                if (oldClientOfCompteEmballageListCompteEmballage != null) {
                    oldClientOfCompteEmballageListCompteEmballage.getCompteEmballageList().remove(compteEmballageListCompteEmballage);
                    oldClientOfCompteEmballageListCompteEmballage = em.merge(oldClientOfCompteEmballageListCompteEmballage);
                }
            }
            for (Tincidents tincidentsListTincidents : tclients.getTincidentsList()) {
                Tclients oldClientidOfTincidentsListTincidents = tincidentsListTincidents.getClientid();
                tincidentsListTincidents.setClientid(tclients);
                tincidentsListTincidents = em.merge(tincidentsListTincidents);
                if (oldClientidOfTincidentsListTincidents != null) {
                    oldClientidOfTincidentsListTincidents.getTincidentsList().remove(tincidentsListTincidents);
                    oldClientidOfTincidentsListTincidents = em.merge(oldClientidOfTincidentsListTincidents);
                }
            }
            return tclients;
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public Tclients MisAJour(Tclients tclients) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            Tclients persistentTclients = em.find(Tclients.class, tclients.getId());
            Ttypeclients typeclientidOld = persistentTclients.getTypeclientid();
            Ttypeclients typeclientidNew = tclients.getTypeclientid();
            Societe societeOld = persistentTclients.getSociete();
            Societe societeNew = tclients.getSociete();
            Tsecteurs secteuridOld = persistentTclients.getSecteurid();
            Tsecteurs secteuridNew = tclients.getSecteurid();
            Tourner tournerOld = persistentTclients.getTourner();
            Tourner tournerNew = tclients.getTourner();
            List<Tlignecommande> tlignecommandeListOld = persistentTclients.getTlignecommandeList();
            List<Tlignecommande> tlignecommandeListNew = tclients.getTlignecommandeList();
            List<CompteRistourne> compteRistourneListOld = persistentTclients.getCompteRistourneList();
            List<CompteRistourne> compteRistourneListNew = tclients.getCompteRistourneList();
            List<LigneAccount> ligneAccountListOld = persistentTclients.getLigneAccountList();
            List<LigneAccount> ligneAccountListNew = tclients.getLigneAccountList();
            List<CompteEmballage> compteEmballageListOld = persistentTclients.getCompteEmballageList();
            List<CompteEmballage> compteEmballageListNew = tclients.getCompteEmballageList();
            List<Tincidents> tincidentsListOld = persistentTclients.getTincidentsList();
            List<Tincidents> tincidentsListNew = tclients.getTincidentsList();

            if (typeclientidNew != null) {
                typeclientidNew = em.getReference(typeclientidNew.getClass(), typeclientidNew.getId());
                tclients.setTypeclientid(typeclientidNew);
            }
            if (societeNew != null) {
                societeNew = em.getReference(societeNew.getClass(), societeNew.getId());
                tclients.setSociete(societeNew);
            }
            if (secteuridNew != null) {
                secteuridNew = em.getReference(secteuridNew.getClass(), secteuridNew.getId());
                tclients.setSecteurid(secteuridNew);
            }
            if (tournerNew != null) {
                tournerNew = em.getReference(tournerNew.getClass(), tournerNew.getId());
                tclients.setTourner(tournerNew);
            }
            List<Tlignecommande> attachedTlignecommandeListNew = new ArrayList<Tlignecommande>();
            for (Tlignecommande tlignecommandeListNewTlignecommandeToAttach : tlignecommandeListNew) {
                tlignecommandeListNewTlignecommandeToAttach = em.getReference(tlignecommandeListNewTlignecommandeToAttach.getClass(), tlignecommandeListNewTlignecommandeToAttach.getId());
                attachedTlignecommandeListNew.add(tlignecommandeListNewTlignecommandeToAttach);
            }
            tlignecommandeListNew = attachedTlignecommandeListNew;
            tclients.setTlignecommandeList(tlignecommandeListNew);
            List<CompteRistourne> attachedCompteRistourneListNew = new ArrayList<CompteRistourne>();
            for (CompteRistourne compteRistourneListNewCompteRistourneToAttach : compteRistourneListNew) {
                compteRistourneListNewCompteRistourneToAttach = em.getReference(compteRistourneListNewCompteRistourneToAttach.getClass(), compteRistourneListNewCompteRistourneToAttach.getId());
                attachedCompteRistourneListNew.add(compteRistourneListNewCompteRistourneToAttach);
            }
            compteRistourneListNew = attachedCompteRistourneListNew;
            tclients.setCompteRistourneList(compteRistourneListNew);
            List<LigneAccount> attachedLigneAccountListNew = new ArrayList<LigneAccount>();
            for (LigneAccount ligneAccountListNewLigneAccountToAttach : ligneAccountListNew) {
                ligneAccountListNewLigneAccountToAttach = em.getReference(ligneAccountListNewLigneAccountToAttach.getClass(), ligneAccountListNewLigneAccountToAttach.getId());
                attachedLigneAccountListNew.add(ligneAccountListNewLigneAccountToAttach);
            }
            ligneAccountListNew = attachedLigneAccountListNew;
            tclients.setLigneAccountList(ligneAccountListNew);
            List<CompteEmballage> attachedCompteEmballageListNew = new ArrayList<CompteEmballage>();
            for (CompteEmballage compteEmballageListNewCompteEmballageToAttach : compteEmballageListNew) {
                compteEmballageListNewCompteEmballageToAttach = em.getReference(compteEmballageListNewCompteEmballageToAttach.getClass(), compteEmballageListNewCompteEmballageToAttach.getId());
                attachedCompteEmballageListNew.add(compteEmballageListNewCompteEmballageToAttach);
            }
            compteEmballageListNew = attachedCompteEmballageListNew;
            tclients.setCompteEmballageList(compteEmballageListNew);
            List<Tincidents> attachedTincidentsListNew = new ArrayList<Tincidents>();
            for (Tincidents tincidentsListNewTincidentsToAttach : tincidentsListNew) {
                tincidentsListNewTincidentsToAttach = em.getReference(tincidentsListNewTincidentsToAttach.getClass(), tincidentsListNewTincidentsToAttach.getId());
                attachedTincidentsListNew.add(tincidentsListNewTincidentsToAttach);
            }
            tincidentsListNew = attachedTincidentsListNew;
            tclients.setTincidentsList(tincidentsListNew);
            tclients = em.merge(tclients);
            if (typeclientidOld != null && !typeclientidOld.equals(typeclientidNew)) {
                typeclientidOld.getTclientsList().remove(tclients);
                typeclientidOld = em.merge(typeclientidOld);
            }
            if (typeclientidNew != null && !typeclientidNew.equals(typeclientidOld)) {
                typeclientidNew.getTclientsList().add(tclients);
                typeclientidNew = em.merge(typeclientidNew);
            }
            if (societeOld != null && !societeOld.equals(societeNew)) {
                societeOld.getTclientsList().remove(tclients);
                societeOld = em.merge(societeOld);
            }
            if (societeNew != null && !societeNew.equals(societeOld)) {
                societeNew.getTclientsList().add(tclients);
                societeNew = em.merge(societeNew);
            }
            if (secteuridOld != null && !secteuridOld.equals(secteuridNew)) {
                secteuridOld.getTclientsList().remove(tclients);
                secteuridOld = em.merge(secteuridOld);
            }
            if (secteuridNew != null && !secteuridNew.equals(secteuridOld)) {
                secteuridNew.getTclientsList().add(tclients);
                secteuridNew = em.merge(secteuridNew);
            }
            if (tournerOld != null && !tournerOld.equals(tournerNew)) {
                tournerOld.getTclientsList().remove(tclients);
                tournerOld = em.merge(tournerOld);
            }
            if (tournerNew != null && !tournerNew.equals(tournerOld)) {
                tournerNew.getTclientsList().add(tclients);
                tournerNew = em.merge(tournerNew);
            }
            for (Tlignecommande tlignecommandeListOldTlignecommande : tlignecommandeListOld) {
                if (!tlignecommandeListNew.contains(tlignecommandeListOldTlignecommande)) {
                    tlignecommandeListOldTlignecommande.setClient(null);
                    tlignecommandeListOldTlignecommande = em.merge(tlignecommandeListOldTlignecommande);
                }
            }
            for (Tlignecommande tlignecommandeListNewTlignecommande : tlignecommandeListNew) {
                if (!tlignecommandeListOld.contains(tlignecommandeListNewTlignecommande)) {
                    Tclients oldClientOfTlignecommandeListNewTlignecommande = tlignecommandeListNewTlignecommande.getClient();
                    tlignecommandeListNewTlignecommande.setClient(tclients);
                    tlignecommandeListNewTlignecommande = em.merge(tlignecommandeListNewTlignecommande);
                    if (oldClientOfTlignecommandeListNewTlignecommande != null && !oldClientOfTlignecommandeListNewTlignecommande.equals(tclients)) {
                        oldClientOfTlignecommandeListNewTlignecommande.getTlignecommandeList().remove(tlignecommandeListNewTlignecommande);
                        oldClientOfTlignecommandeListNewTlignecommande = em.merge(oldClientOfTlignecommandeListNewTlignecommande);
                    }
                }
            }
            for (CompteRistourne compteRistourneListNewCompteRistourne : compteRistourneListNew) {
                if (!compteRistourneListOld.contains(compteRistourneListNewCompteRistourne)) {
                    Tclients oldClientOfCompteRistourneListNewCompteRistourne = compteRistourneListNewCompteRistourne.getClient();
                    compteRistourneListNewCompteRistourne.setClient(tclients);
                    compteRistourneListNewCompteRistourne = em.merge(compteRistourneListNewCompteRistourne);
                    if (oldClientOfCompteRistourneListNewCompteRistourne != null && !oldClientOfCompteRistourneListNewCompteRistourne.equals(tclients)) {
                        oldClientOfCompteRistourneListNewCompteRistourne.getCompteRistourneList().remove(compteRistourneListNewCompteRistourne);
                        oldClientOfCompteRistourneListNewCompteRistourne = em.merge(oldClientOfCompteRistourneListNewCompteRistourne);
                    }
                }
            }
            for (LigneAccount ligneAccountListOldLigneAccount : ligneAccountListOld) {
                if (!ligneAccountListNew.contains(ligneAccountListOldLigneAccount)) {
                    ligneAccountListOldLigneAccount.setClient(null);
                    ligneAccountListOldLigneAccount = em.merge(ligneAccountListOldLigneAccount);
                }
            }
            for (LigneAccount ligneAccountListNewLigneAccount : ligneAccountListNew) {
                if (!ligneAccountListOld.contains(ligneAccountListNewLigneAccount)) {
                    Tclients oldClientOfLigneAccountListNewLigneAccount = ligneAccountListNewLigneAccount.getClient();
                    ligneAccountListNewLigneAccount.setClient(tclients);
                    ligneAccountListNewLigneAccount = em.merge(ligneAccountListNewLigneAccount);
                    if (oldClientOfLigneAccountListNewLigneAccount != null && !oldClientOfLigneAccountListNewLigneAccount.equals(tclients)) {
                        oldClientOfLigneAccountListNewLigneAccount.getLigneAccountList().remove(ligneAccountListNewLigneAccount);
                        oldClientOfLigneAccountListNewLigneAccount = em.merge(oldClientOfLigneAccountListNewLigneAccount);
                    }
                }
            }
            for (CompteEmballage compteEmballageListNewCompteEmballage : compteEmballageListNew) {
                if (!compteEmballageListOld.contains(compteEmballageListNewCompteEmballage)) {
                    Tclients oldClientOfCompteEmballageListNewCompteEmballage = compteEmballageListNewCompteEmballage.getClient();
                    compteEmballageListNewCompteEmballage.setClient(tclients);
                    compteEmballageListNewCompteEmballage = em.merge(compteEmballageListNewCompteEmballage);
                    if (oldClientOfCompteEmballageListNewCompteEmballage != null && !oldClientOfCompteEmballageListNewCompteEmballage.equals(tclients)) {
                        oldClientOfCompteEmballageListNewCompteEmballage.getCompteEmballageList().remove(compteEmballageListNewCompteEmballage);
                        oldClientOfCompteEmballageListNewCompteEmballage = em.merge(oldClientOfCompteEmballageListNewCompteEmballage);
                    }
                }
            }
            for (Tincidents tincidentsListOldTincidents : tincidentsListOld) {
                if (!tincidentsListNew.contains(tincidentsListOldTincidents)) {
                    tincidentsListOldTincidents.setClientid(null);
                    tincidentsListOldTincidents = em.merge(tincidentsListOldTincidents);
                }
            }
            for (Tincidents tincidentsListNewTincidents : tincidentsListNew) {
                if (!tincidentsListOld.contains(tincidentsListNewTincidents)) {
                    Tclients oldClientidOfTincidentsListNewTincidents = tincidentsListNewTincidents.getClientid();
                    tincidentsListNewTincidents.setClientid(tclients);
                    tincidentsListNewTincidents = em.merge(tincidentsListNewTincidents);
                    if (oldClientidOfTincidentsListNewTincidents != null && !oldClientidOfTincidentsListNewTincidents.equals(tclients)) {
                        oldClientidOfTincidentsListNewTincidents.getTincidentsList().remove(tincidentsListNewTincidents);
                        oldClientidOfTincidentsListNewTincidents = em.merge(oldClientidOfTincidentsListNewTincidents);
                    }
                }
            }
            return tclients;
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void Supprimer(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            Tclients tclients;
            try {
                tclients = em.getReference(Tclients.class, id);
                tclients.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tclients with id " + id + " no longer exists.", enfe);
            }

            Ttypeclients typeclientid = tclients.getTypeclientid();
            if (typeclientid != null) {
                typeclientid.getTclientsList().remove(tclients);
                typeclientid = em.merge(typeclientid);
            }
            Societe societe = tclients.getSociete();
            if (societe != null) {
                societe.getTclientsList().remove(tclients);
                societe = em.merge(societe);
            }
            Tsecteurs secteurid = tclients.getSecteurid();
            if (secteurid != null) {
                secteurid.getTclientsList().remove(tclients);
                secteurid = em.merge(secteurid);
            }
            Tourner tourner = tclients.getTourner();
            if (tourner != null) {
                tourner.getTclientsList().remove(tclients);
                tourner = em.merge(tourner);
            }
            List<Tlignecommande> tlignecommandeList = tclients.getTlignecommandeList();
            for (Tlignecommande tlignecommandeListTlignecommande : tlignecommandeList) {
                tlignecommandeListTlignecommande.setClient(null);
                tlignecommandeListTlignecommande = em.merge(tlignecommandeListTlignecommande);
            }
            List<LigneAccount> ligneAccountList = tclients.getLigneAccountList();
            for (LigneAccount ligneAccountListLigneAccount : ligneAccountList) {
                ligneAccountListLigneAccount.setClient(null);
                ligneAccountListLigneAccount = em.merge(ligneAccountListLigneAccount);
            }
            List<Tincidents> tincidentsList = tclients.getTincidentsList();
            for (Tincidents tincidentsListTincidents : tincidentsList) {
                tincidentsListTincidents.setClientid(null);
                tincidentsListTincidents = em.merge(tincidentsListTincidents);
            }
            em.remove(tclients);
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @Override
    public List<Tclients> findTclientsEntities() {
        return findTclientsEntities(true, -1, -1);
    }

    @Override
    public List<Tclients> findTclientsEntities(int maxResults, int firstResult) {
        return findTclientsEntities(false, maxResults, firstResult);
    }

    private List<Tclients> findTclientsEntities(boolean all, int maxResults, int firstResult) {
        getEntityManager().setProperty("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Tclients.class));
        Query q = getEntityManager().createQuery(cq);
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    @Override

    public Tclients findTclients(Integer id) {
        getEntityManager().setProperty("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        return getEntityManager().find(Tclients.class, id);
    }

    @Override
    public Tclients findByCodeclient(String codedclient) {
        Query q = getEntityManager().createNamedQuery("Tclients.findByCodeclient");
        q.setParameter("codeclient", codedclient);
        if (!q.getResultList().isEmpty()) {
            return (Tclients) q.getSingleResult();
        } else {
            return null;
        }
    }

    @Override
    public int getTclientsCount() {
        getEntityManager().setProperty("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<Tclients> rt = cq.from(Tclients.class);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    @Override
    public List<Tclients> findTclientsByRegion(int region, String societe, int entity) {
        getEntityManager().setProperty("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        Query q = null;
        switch (entity) {
            case 0:
                q = getEntityManager().createNamedQuery("Tclients.findByRegion");
                break;
            case 1:
                q = getEntityManager().createNamedQuery("Tclients.findByDistrict");
                break;
            case 2:
                q = getEntityManager().createNamedQuery("Tclients.findBySecteur");
                break;
            default:
                q = getEntityManager().createNamedQuery("Tclients.findAll");
                break;
        }

        q.setParameter("region", region);
        q.setParameter("imma", societe);
        return q.getResultList();
    }

    @Override
    public List<Tclients> findAll(String societe) {
        getEntityManager().setProperty("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        Query q = getEntityManager().createNamedQuery("Tclients.findAll");
        q.setParameter("imma", societe);
        return q.getResultList();
    }
}
