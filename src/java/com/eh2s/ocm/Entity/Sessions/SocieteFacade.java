/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Caisse;
import com.eh2s.ocm.Entity.Emballage;
import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.PreexistingEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Fournisseur;
import com.eh2s.ocm.Entity.LigneAccount;
import com.eh2s.ocm.Entity.LigneInventaire;
import com.eh2s.ocm.Entity.LigneSortie;
import com.eh2s.ocm.Entity.Magasin;
import com.eh2s.ocm.Entity.Societe;
import com.eh2s.ocm.Entity.SouscriptionLicence;
import com.eh2s.ocm.Entity.Tcategorie;
import com.eh2s.ocm.Entity.Tclients;
import com.eh2s.ocm.Entity.Tgroups;
import com.eh2s.ocm.Entity.Tlignecommande;
import com.eh2s.ocm.Entity.Tourner;
import com.eh2s.ocm.Entity.Tpriority;
import com.eh2s.ocm.Entity.Tregions;
import com.eh2s.ocm.Entity.Trubriques;
import com.eh2s.ocm.Entity.Tsources;
import com.eh2s.ocm.Entity.Ttypeclients;
import com.eh2s.ocm.Entity.Tusers;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author messi
 */
@Stateless
public class SocieteFacade extends AbstractFacade<Societe> implements SocieteFacadeLocal {

    @PersistenceContext(unitName = "ccmanagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SocieteFacade() {
        super(Societe.class);
    }

    @Override
    public Societe creer(Societe societe) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (societe.getLigneInventaireList() == null) {
            societe.setLigneInventaireList(new ArrayList<LigneInventaire>());
        }
        if (societe.getTrubriquesList() == null) {
            societe.setTrubriquesList(new ArrayList<Trubriques>());
        }
        if (societe.getFournisseurList() == null) {
            societe.setFournisseurList(new ArrayList<Fournisseur>());
        }
        if (societe.getTlignecommandeList() == null) {
            societe.setTlignecommandeList(new ArrayList<Tlignecommande>());
        }
        if (societe.getTusersList() == null) {
            societe.setTusersList(new ArrayList<Tusers>());
        }
        if (societe.getTpriorityList() == null) {
            societe.setTpriorityList(new ArrayList<Tpriority>());
        }
        if (societe.getTournerList() == null) {
            societe.setTournerList(new ArrayList<Tourner>());
        }
        if (societe.getCaisseList() == null) {
            societe.setCaisseList(new ArrayList<Caisse>());
        }
        if (societe.getLigneAccountList() == null) {
            societe.setLigneAccountList(new ArrayList<LigneAccount>());
        }
        if (societe.getTsourcesList() == null) {
            societe.setTsourcesList(new ArrayList<Tsources>());
        }
        if (societe.getTclientsList() == null) {
            societe.setTclientsList(new ArrayList<Tclients>());
        }
        if (societe.getMagasinList() == null) {
            societe.setMagasinList(new ArrayList<Magasin>());
        }
        if (societe.getTregionsList() == null) {
            societe.setTregionsList(new ArrayList<Tregions>());
        }
        if (societe.getEmballageList() == null) {
            societe.setEmballageList(new ArrayList<Emballage>());
        }
        if (societe.getTgroupsList() == null) {
            societe.setTgroupsList(new ArrayList<Tgroups>());
        }
        if (societe.getTcategorieList() == null) {
            societe.setTcategorieList(new ArrayList<Tcategorie>());
        }
        if (societe.getTtypeclientsList() == null) {
            societe.setTtypeclientsList(new ArrayList<Ttypeclients>());
        }
        if (societe.getSouscriptionLicenceList() == null) {
            societe.setSouscriptionLicenceList(new ArrayList<SouscriptionLicence>());
        }
        try {
            List<LigneInventaire> attachedLigneInventaireList = new ArrayList<LigneInventaire>();
            for (LigneInventaire ligneInventaireListLigneInventaireToAttach : societe.getLigneInventaireList()) {
                ligneInventaireListLigneInventaireToAttach = getEntityManager().getReference(ligneInventaireListLigneInventaireToAttach.getClass(), ligneInventaireListLigneInventaireToAttach.getId());
                attachedLigneInventaireList.add(ligneInventaireListLigneInventaireToAttach);
            }
            societe.setLigneInventaireList(attachedLigneInventaireList);
            List<Trubriques> attachedTrubriquesList = new ArrayList<Trubriques>();
            for (Trubriques trubriquesListTrubriquesToAttach : societe.getTrubriquesList()) {
                trubriquesListTrubriquesToAttach = getEntityManager().getReference(trubriquesListTrubriquesToAttach.getClass(), trubriquesListTrubriquesToAttach.getId());
                attachedTrubriquesList.add(trubriquesListTrubriquesToAttach);
            }
            societe.setTrubriquesList(attachedTrubriquesList);
            List<Fournisseur> attachedFournisseurList = new ArrayList<Fournisseur>();
            for (Fournisseur fournisseurListFournisseurToAttach : societe.getFournisseurList()) {
                fournisseurListFournisseurToAttach = getEntityManager().getReference(fournisseurListFournisseurToAttach.getClass(), fournisseurListFournisseurToAttach.getId());
                attachedFournisseurList.add(fournisseurListFournisseurToAttach);
            }
            societe.setFournisseurList(attachedFournisseurList);
            List<Tlignecommande> attachedTlignecommandeList = new ArrayList<Tlignecommande>();
            for (Tlignecommande tlignecommandeListTlignecommandeToAttach : societe.getTlignecommandeList()) {
                tlignecommandeListTlignecommandeToAttach = getEntityManager().getReference(tlignecommandeListTlignecommandeToAttach.getClass(), tlignecommandeListTlignecommandeToAttach.getId());
                attachedTlignecommandeList.add(tlignecommandeListTlignecommandeToAttach);
            }
            societe.setTlignecommandeList(attachedTlignecommandeList);
            List<Tusers> attachedTusersList = new ArrayList<Tusers>();
            for (Tusers tusersListTusersToAttach : societe.getTusersList()) {
                tusersListTusersToAttach = getEntityManager().getReference(tusersListTusersToAttach.getClass(), tusersListTusersToAttach.getId());
                attachedTusersList.add(tusersListTusersToAttach);
            }
            societe.setTusersList(attachedTusersList);
            List<Tpriority> attachedTpriorityList = new ArrayList<Tpriority>();
            for (Tpriority tpriorityListTpriorityToAttach : societe.getTpriorityList()) {
                tpriorityListTpriorityToAttach = getEntityManager().getReference(tpriorityListTpriorityToAttach.getClass(), tpriorityListTpriorityToAttach.getId());
                attachedTpriorityList.add(tpriorityListTpriorityToAttach);
            }
            societe.setTpriorityList(attachedTpriorityList);
            List<Tourner> attachedTournerList = new ArrayList<Tourner>();
            for (Tourner tournerListTournerToAttach : societe.getTournerList()) {
                tournerListTournerToAttach = getEntityManager().getReference(tournerListTournerToAttach.getClass(), tournerListTournerToAttach.getId());
                attachedTournerList.add(tournerListTournerToAttach);
            }
            societe.setTournerList(attachedTournerList);
            List<Caisse> attachedCaisseList = new ArrayList<Caisse>();
            for (Caisse caisseListCaisseToAttach : societe.getCaisseList()) {
                caisseListCaisseToAttach = getEntityManager().getReference(caisseListCaisseToAttach.getClass(), caisseListCaisseToAttach.getId());
                attachedCaisseList.add(caisseListCaisseToAttach);
            }
            societe.setCaisseList(attachedCaisseList);
            List<LigneAccount> attachedLigneAccountList = new ArrayList<LigneAccount>();
            for (LigneAccount ligneAccountListLigneAccountToAttach : societe.getLigneAccountList()) {
                ligneAccountListLigneAccountToAttach = getEntityManager().getReference(ligneAccountListLigneAccountToAttach.getClass(), ligneAccountListLigneAccountToAttach.getId());
                attachedLigneAccountList.add(ligneAccountListLigneAccountToAttach);
            }
            societe.setLigneAccountList(attachedLigneAccountList);
            List<Tsources> attachedTsourcesList = new ArrayList<Tsources>();
            for (Tsources tsourcesListTsourcesToAttach : societe.getTsourcesList()) {
                tsourcesListTsourcesToAttach = getEntityManager().getReference(tsourcesListTsourcesToAttach.getClass(), tsourcesListTsourcesToAttach.getId());
                attachedTsourcesList.add(tsourcesListTsourcesToAttach);
            }
            societe.setTsourcesList(attachedTsourcesList);
            List<Tclients> attachedTclientsList = new ArrayList<Tclients>();
            for (Tclients tclientsListTclientsToAttach : societe.getTclientsList()) {
                tclientsListTclientsToAttach = getEntityManager().getReference(tclientsListTclientsToAttach.getClass(), tclientsListTclientsToAttach.getId());
                attachedTclientsList.add(tclientsListTclientsToAttach);
            }
            societe.setTclientsList(attachedTclientsList);
            List<Magasin> attachedMagasinList = new ArrayList<Magasin>();
            for (Magasin magasinListMagasinToAttach : societe.getMagasinList()) {
                magasinListMagasinToAttach = getEntityManager().getReference(magasinListMagasinToAttach.getClass(), magasinListMagasinToAttach.getId());
                attachedMagasinList.add(magasinListMagasinToAttach);
            }
            societe.setMagasinList(attachedMagasinList);
            List<Tregions> attachedTregionsList = new ArrayList<Tregions>();
            for (Tregions tregionsListTregionsToAttach : societe.getTregionsList()) {
                tregionsListTregionsToAttach = getEntityManager().getReference(tregionsListTregionsToAttach.getClass(), tregionsListTregionsToAttach.getId());
                attachedTregionsList.add(tregionsListTregionsToAttach);
            }
            societe.setTregionsList(attachedTregionsList);
            List<Emballage> attachedEmballageList = new ArrayList<Emballage>();
            for (Emballage emballageListEmballageToAttach : societe.getEmballageList()) {
                emballageListEmballageToAttach = getEntityManager().getReference(emballageListEmballageToAttach.getClass(), emballageListEmballageToAttach.getId());
                attachedEmballageList.add(emballageListEmballageToAttach);
            }
            societe.setEmballageList(attachedEmballageList);
            List<Tgroups> attachedTgroupsList = new ArrayList<Tgroups>();
            for (Tgroups tgroupsListTgroupsToAttach : societe.getTgroupsList()) {
                tgroupsListTgroupsToAttach = getEntityManager().getReference(tgroupsListTgroupsToAttach.getClass(), tgroupsListTgroupsToAttach.getId());
                attachedTgroupsList.add(tgroupsListTgroupsToAttach);
            }
            societe.setTgroupsList(attachedTgroupsList);
            List<Tcategorie> attachedTcategorieList = new ArrayList<Tcategorie>();
            for (Tcategorie tcategorieListTcategorieToAttach : societe.getTcategorieList()) {
                tcategorieListTcategorieToAttach = getEntityManager().getReference(tcategorieListTcategorieToAttach.getClass(), tcategorieListTcategorieToAttach.getId());
                attachedTcategorieList.add(tcategorieListTcategorieToAttach);
            }
            societe.setTcategorieList(attachedTcategorieList);
            List<Ttypeclients> attachedTtypeclientsList = new ArrayList<Ttypeclients>();
            for (Ttypeclients ttypeclientsListTtypeclientsToAttach : societe.getTtypeclientsList()) {
                ttypeclientsListTtypeclientsToAttach = getEntityManager().getReference(ttypeclientsListTtypeclientsToAttach.getClass(), ttypeclientsListTtypeclientsToAttach.getId());
                attachedTtypeclientsList.add(ttypeclientsListTtypeclientsToAttach);
            }
            societe.setTtypeclientsList(attachedTtypeclientsList);
            List<SouscriptionLicence> attachedSouscriptionLicenceList = new ArrayList<SouscriptionLicence>();
            for (SouscriptionLicence souscriptionLicenceListSouscriptionLicenceToAttach : societe.getSouscriptionLicenceList()) {
                souscriptionLicenceListSouscriptionLicenceToAttach = getEntityManager().getReference(souscriptionLicenceListSouscriptionLicenceToAttach.getClass(), souscriptionLicenceListSouscriptionLicenceToAttach.getId());
                attachedSouscriptionLicenceList.add(souscriptionLicenceListSouscriptionLicenceToAttach);
            }
            societe.setSouscriptionLicenceList(attachedSouscriptionLicenceList);
            getEntityManager().persist(societe);
            for (LigneInventaire ligneInventaireListLigneInventaire : societe.getLigneInventaireList()) {
                Societe oldSocieteOfLigneInventaireListLigneInventaire = ligneInventaireListLigneInventaire.getSociete();
                ligneInventaireListLigneInventaire.setSociete(societe);
                ligneInventaireListLigneInventaire = getEntityManager().merge(ligneInventaireListLigneInventaire);
                if (oldSocieteOfLigneInventaireListLigneInventaire != null) {
                    oldSocieteOfLigneInventaireListLigneInventaire.getLigneInventaireList().remove(ligneInventaireListLigneInventaire);
                    oldSocieteOfLigneInventaireListLigneInventaire = getEntityManager().merge(oldSocieteOfLigneInventaireListLigneInventaire);
                }
            }
            for (Trubriques trubriquesListTrubriques : societe.getTrubriquesList()) {
                Societe oldSocieteOfTrubriquesListTrubriques = trubriquesListTrubriques.getSociete();
                trubriquesListTrubriques.setSociete(societe);
                trubriquesListTrubriques = getEntityManager().merge(trubriquesListTrubriques);
                if (oldSocieteOfTrubriquesListTrubriques != null) {
                    oldSocieteOfTrubriquesListTrubriques.getTrubriquesList().remove(trubriquesListTrubriques);
                    oldSocieteOfTrubriquesListTrubriques = getEntityManager().merge(oldSocieteOfTrubriquesListTrubriques);
                }
            }
            for (Fournisseur fournisseurListFournisseur : societe.getFournisseurList()) {
                Societe oldSocieteOfFournisseurListFournisseur = fournisseurListFournisseur.getSociete();
                fournisseurListFournisseur.setSociete(societe);
                fournisseurListFournisseur = getEntityManager().merge(fournisseurListFournisseur);
                if (oldSocieteOfFournisseurListFournisseur != null) {
                    oldSocieteOfFournisseurListFournisseur.getFournisseurList().remove(fournisseurListFournisseur);
                    oldSocieteOfFournisseurListFournisseur = getEntityManager().merge(oldSocieteOfFournisseurListFournisseur);
                }
            }
            for (Tlignecommande tlignecommandeListTlignecommande : societe.getTlignecommandeList()) {
                Societe oldSocieteOfTlignecommandeListTlignecommande = tlignecommandeListTlignecommande.getSociete();
                tlignecommandeListTlignecommande.setSociete(societe);
                tlignecommandeListTlignecommande = getEntityManager().merge(tlignecommandeListTlignecommande);
                if (oldSocieteOfTlignecommandeListTlignecommande != null) {
                    oldSocieteOfTlignecommandeListTlignecommande.getTlignecommandeList().remove(tlignecommandeListTlignecommande);
                    oldSocieteOfTlignecommandeListTlignecommande = getEntityManager().merge(oldSocieteOfTlignecommandeListTlignecommande);
                }
            }
            for (Tusers tusersListTusers : societe.getTusersList()) {
                Societe oldSocieteOfTusersListTusers = tusersListTusers.getSociete();
                tusersListTusers.setSociete(societe);
                tusersListTusers = getEntityManager().merge(tusersListTusers);
                if (oldSocieteOfTusersListTusers != null) {
                    oldSocieteOfTusersListTusers.getTusersList().remove(tusersListTusers);
                    oldSocieteOfTusersListTusers = getEntityManager().merge(oldSocieteOfTusersListTusers);
                }
            }
            for (Tpriority tpriorityListTpriority : societe.getTpriorityList()) {
                Societe oldSocieteOfTpriorityListTpriority = tpriorityListTpriority.getSociete();
                tpriorityListTpriority.setSociete(societe);
                tpriorityListTpriority = getEntityManager().merge(tpriorityListTpriority);
                if (oldSocieteOfTpriorityListTpriority != null) {
                    oldSocieteOfTpriorityListTpriority.getTpriorityList().remove(tpriorityListTpriority);
                    oldSocieteOfTpriorityListTpriority = getEntityManager().merge(oldSocieteOfTpriorityListTpriority);
                }
            }
            for (Tourner tournerListTourner : societe.getTournerList()) {
                Societe oldSocieteOfTournerListTourner = tournerListTourner.getSociete();
                tournerListTourner.setSociete(societe);
                tournerListTourner = getEntityManager().merge(tournerListTourner);
                if (oldSocieteOfTournerListTourner != null) {
                    oldSocieteOfTournerListTourner.getTournerList().remove(tournerListTourner);
                    oldSocieteOfTournerListTourner = getEntityManager().merge(oldSocieteOfTournerListTourner);
                }
            }
            for (Caisse caisseListCaisse : societe.getCaisseList()) {
                Societe oldSocieteOfCaisseListCaisse = caisseListCaisse.getSociete();
                caisseListCaisse.setSociete(societe);
                caisseListCaisse = getEntityManager().merge(caisseListCaisse);
                if (oldSocieteOfCaisseListCaisse != null) {
                    oldSocieteOfCaisseListCaisse.getCaisseList().remove(caisseListCaisse);
                    oldSocieteOfCaisseListCaisse = getEntityManager().merge(oldSocieteOfCaisseListCaisse);
                }
            }
            for (LigneAccount ligneAccountListLigneAccount : societe.getLigneAccountList()) {
                Societe oldSocieteOfLigneAccountListLigneAccount = ligneAccountListLigneAccount.getSociete();
                ligneAccountListLigneAccount.setSociete(societe);
                ligneAccountListLigneAccount = getEntityManager().merge(ligneAccountListLigneAccount);
                if (oldSocieteOfLigneAccountListLigneAccount != null) {
                    oldSocieteOfLigneAccountListLigneAccount.getLigneAccountList().remove(ligneAccountListLigneAccount);
                    oldSocieteOfLigneAccountListLigneAccount = getEntityManager().merge(oldSocieteOfLigneAccountListLigneAccount);
                }
            }
            for (Tsources tsourcesListTsources : societe.getTsourcesList()) {
                Societe oldSocieteOfTsourcesListTsources = tsourcesListTsources.getSociete();
                tsourcesListTsources.setSociete(societe);
                tsourcesListTsources = getEntityManager().merge(tsourcesListTsources);
                if (oldSocieteOfTsourcesListTsources != null) {
                    oldSocieteOfTsourcesListTsources.getTsourcesList().remove(tsourcesListTsources);
                    oldSocieteOfTsourcesListTsources = getEntityManager().merge(oldSocieteOfTsourcesListTsources);
                }
            }
            for (LigneSortie ligneSortieListLigneSortie : societe.getLigneSortieList()) {
                Societe oldSocieteOfLigneSortieListLigneSortie = ligneSortieListLigneSortie.getSociete();
                ligneSortieListLigneSortie.setSociete(societe);
                ligneSortieListLigneSortie = getEntityManager().merge(ligneSortieListLigneSortie);
                if (oldSocieteOfLigneSortieListLigneSortie != null) {
                    oldSocieteOfLigneSortieListLigneSortie.getLigneSortieList().remove(ligneSortieListLigneSortie);
                    oldSocieteOfLigneSortieListLigneSortie = getEntityManager().merge(oldSocieteOfLigneSortieListLigneSortie);
                }
            }
            for (Tclients tclientsListTclients : societe.getTclientsList()) {
                Societe oldSocieteOfTclientsListTclients = tclientsListTclients.getSociete();
                tclientsListTclients.setSociete(societe);
                tclientsListTclients = getEntityManager().merge(tclientsListTclients);
                if (oldSocieteOfTclientsListTclients != null) {
                    oldSocieteOfTclientsListTclients.getTclientsList().remove(tclientsListTclients);
                    oldSocieteOfTclientsListTclients = getEntityManager().merge(oldSocieteOfTclientsListTclients);
                }
            }
            for (Magasin magasinListMagasin : societe.getMagasinList()) {
                Societe oldSocieteOfMagasinListMagasin = magasinListMagasin.getSociete();
                magasinListMagasin.setSociete(societe);
                magasinListMagasin = getEntityManager().merge(magasinListMagasin);
                if (oldSocieteOfMagasinListMagasin != null) {
                    oldSocieteOfMagasinListMagasin.getMagasinList().remove(magasinListMagasin);
                    oldSocieteOfMagasinListMagasin = getEntityManager().merge(oldSocieteOfMagasinListMagasin);
                }
            }
            for (Tregions tregionsListTregions : societe.getTregionsList()) {
                Societe oldSocieteOfTregionsListTregions = tregionsListTregions.getSociete();
                tregionsListTregions.setSociete(societe);
                tregionsListTregions = getEntityManager().merge(tregionsListTregions);
                if (oldSocieteOfTregionsListTregions != null) {
                    oldSocieteOfTregionsListTregions.getTregionsList().remove(tregionsListTregions);
                    oldSocieteOfTregionsListTregions = getEntityManager().merge(oldSocieteOfTregionsListTregions);
                }
            }
            for (Emballage emballageListEmballage : societe.getEmballageList()) {
                Societe oldSocieteOfEmballageListEmballage = emballageListEmballage.getSociete();
                emballageListEmballage.setSociete(societe);
                emballageListEmballage = getEntityManager().merge(emballageListEmballage);
                if (oldSocieteOfEmballageListEmballage != null) {
                    oldSocieteOfEmballageListEmballage.getEmballageList().remove(emballageListEmballage);
                    oldSocieteOfEmballageListEmballage = getEntityManager().merge(oldSocieteOfEmballageListEmballage);
                }
            }
            for (Tgroups tgroupsListTgroups : societe.getTgroupsList()) {
                Societe oldSocieteOfTgroupsListTgroups = tgroupsListTgroups.getSociete();
                tgroupsListTgroups.setSociete(societe);
                tgroupsListTgroups = getEntityManager().merge(tgroupsListTgroups);
                if (oldSocieteOfTgroupsListTgroups != null) {
                    oldSocieteOfTgroupsListTgroups.getTgroupsList().remove(tgroupsListTgroups);
                    oldSocieteOfTgroupsListTgroups = getEntityManager().merge(oldSocieteOfTgroupsListTgroups);
                }
            }
            for (Tcategorie tcategorieListTcategorie : societe.getTcategorieList()) {
                Societe oldSocieteOfTcategorieListTcategorie = tcategorieListTcategorie.getSociete();
                tcategorieListTcategorie.setSociete(societe);
                tcategorieListTcategorie = getEntityManager().merge(tcategorieListTcategorie);
                if (oldSocieteOfTcategorieListTcategorie != null) {
                    oldSocieteOfTcategorieListTcategorie.getTcategorieList().remove(tcategorieListTcategorie);
                    oldSocieteOfTcategorieListTcategorie = getEntityManager().merge(oldSocieteOfTcategorieListTcategorie);
                }
            }
            for (Ttypeclients ttypeclientsListTtypeclients : societe.getTtypeclientsList()) {
                Societe oldSocieteOfTtypeclientsListTtypeclients = ttypeclientsListTtypeclients.getSociete();
                ttypeclientsListTtypeclients.setSociete(societe);
                ttypeclientsListTtypeclients = getEntityManager().merge(ttypeclientsListTtypeclients);
                if (oldSocieteOfTtypeclientsListTtypeclients != null) {
                    oldSocieteOfTtypeclientsListTtypeclients.getTtypeclientsList().remove(ttypeclientsListTtypeclients);
                    oldSocieteOfTtypeclientsListTtypeclients = getEntityManager().merge(oldSocieteOfTtypeclientsListTtypeclients);
                }
            }
            for (SouscriptionLicence souscriptionLicenceListSouscriptionLicence : societe.getSouscriptionLicenceList()) {
                Societe oldSocieteOfSouscriptionLicenceListSouscriptionLicence = souscriptionLicenceListSouscriptionLicence.getSociete();
                souscriptionLicenceListSouscriptionLicence.setSociete(societe);
                souscriptionLicenceListSouscriptionLicence = getEntityManager().merge(souscriptionLicenceListSouscriptionLicence);
                if (oldSocieteOfSouscriptionLicenceListSouscriptionLicence != null) {
                    oldSocieteOfSouscriptionLicenceListSouscriptionLicence.getSouscriptionLicenceList().remove(souscriptionLicenceListSouscriptionLicence);
                    oldSocieteOfSouscriptionLicenceListSouscriptionLicence = getEntityManager().merge(oldSocieteOfSouscriptionLicenceListSouscriptionLicence);
                }
            }
            return societe;
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @Override
    public void MisAJour(Societe societe) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            Societe persistentSociete = em.find(Societe.class, societe.getId());
            List<LigneInventaire> ligneInventaireListOld = persistentSociete.getLigneInventaireList();
            List<LigneInventaire> ligneInventaireListNew = societe.getLigneInventaireList();
            List<Trubriques> trubriquesListOld = persistentSociete.getTrubriquesList();
            List<Trubriques> trubriquesListNew = societe.getTrubriquesList();
            List<Fournisseur> fournisseurListOld = persistentSociete.getFournisseurList();
            List<Fournisseur> fournisseurListNew = societe.getFournisseurList();
            List<Tlignecommande> tlignecommandeListOld = persistentSociete.getTlignecommandeList();
            List<Tlignecommande> tlignecommandeListNew = societe.getTlignecommandeList();
            List<Tusers> tusersListOld = persistentSociete.getTusersList();
            List<Tusers> tusersListNew = societe.getTusersList();
            List<Tpriority> tpriorityListOld = persistentSociete.getTpriorityList();
            List<Tpriority> tpriorityListNew = societe.getTpriorityList();
            List<Tourner> tournerListOld = persistentSociete.getTournerList();
            List<Tourner> tournerListNew = societe.getTournerList();
            List<Caisse> caisseListOld = persistentSociete.getCaisseList();
            List<Caisse> caisseListNew = societe.getCaisseList();
            List<LigneAccount> ligneAccountListOld = persistentSociete.getLigneAccountList();
            List<LigneAccount> ligneAccountListNew = societe.getLigneAccountList();
            List<Tsources> tsourcesListOld = persistentSociete.getTsourcesList();
            List<Tsources> tsourcesListNew = societe.getTsourcesList();
            List<Tclients> tclientsListOld = persistentSociete.getTclientsList();
            List<Tclients> tclientsListNew = societe.getTclientsList();
            List<Magasin> magasinListOld = persistentSociete.getMagasinList();
            List<Magasin> magasinListNew = societe.getMagasinList();
            List<Tregions> tregionsListOld = persistentSociete.getTregionsList();
            List<Tregions> tregionsListNew = societe.getTregionsList();
            List<Emballage> emballageListOld = persistentSociete.getEmballageList();
            List<Emballage> emballageListNew = societe.getEmballageList();
            List<Tgroups> tgroupsListOld = persistentSociete.getTgroupsList();
            List<Tgroups> tgroupsListNew = societe.getTgroupsList();
            List<Tcategorie> tcategorieListOld = persistentSociete.getTcategorieList();
            List<Tcategorie> tcategorieListNew = societe.getTcategorieList();
            List<Ttypeclients> ttypeclientsListOld = persistentSociete.getTtypeclientsList();
            List<Ttypeclients> ttypeclientsListNew = societe.getTtypeclientsList();
            List<SouscriptionLicence> souscriptionLicenceListOld = persistentSociete.getSouscriptionLicenceList();
            List<SouscriptionLicence> souscriptionLicenceListNew = societe.getSouscriptionLicenceList();
            List<LigneSortie> ligneSortieListOld = persistentSociete.getLigneSortieList();
            List<LigneSortie> ligneSortieListNew = societe.getLigneSortieList();
            
            List<LigneInventaire> attachedLigneInventaireListNew = new ArrayList<LigneInventaire>();
            for (LigneInventaire ligneInventaireListNewLigneInventaireToAttach : ligneInventaireListNew) {
                ligneInventaireListNewLigneInventaireToAttach = em.getReference(ligneInventaireListNewLigneInventaireToAttach.getClass(), ligneInventaireListNewLigneInventaireToAttach.getId());
                attachedLigneInventaireListNew.add(ligneInventaireListNewLigneInventaireToAttach);
            }
            ligneInventaireListNew = attachedLigneInventaireListNew;
            societe.setLigneInventaireList(ligneInventaireListNew);
            List<Trubriques> attachedTrubriquesListNew = new ArrayList<Trubriques>();
            for (Trubriques trubriquesListNewTrubriquesToAttach : trubriquesListNew) {
                trubriquesListNewTrubriquesToAttach = em.getReference(trubriquesListNewTrubriquesToAttach.getClass(), trubriquesListNewTrubriquesToAttach.getId());
                attachedTrubriquesListNew.add(trubriquesListNewTrubriquesToAttach);
            }
            trubriquesListNew = attachedTrubriquesListNew;
            societe.setTrubriquesList(trubriquesListNew);
            List<Fournisseur> attachedFournisseurListNew = new ArrayList<Fournisseur>();
            for (Fournisseur fournisseurListNewFournisseurToAttach : fournisseurListNew) {
                fournisseurListNewFournisseurToAttach = em.getReference(fournisseurListNewFournisseurToAttach.getClass(), fournisseurListNewFournisseurToAttach.getId());
                attachedFournisseurListNew.add(fournisseurListNewFournisseurToAttach);
            }
            fournisseurListNew = attachedFournisseurListNew;
            societe.setFournisseurList(fournisseurListNew);
            List<Tlignecommande> attachedTlignecommandeListNew = new ArrayList<Tlignecommande>();
            for (Tlignecommande tlignecommandeListNewTlignecommandeToAttach : tlignecommandeListNew) {
                tlignecommandeListNewTlignecommandeToAttach = em.getReference(tlignecommandeListNewTlignecommandeToAttach.getClass(), tlignecommandeListNewTlignecommandeToAttach.getId());
                attachedTlignecommandeListNew.add(tlignecommandeListNewTlignecommandeToAttach);
            }
            tlignecommandeListNew = attachedTlignecommandeListNew;
            societe.setTlignecommandeList(tlignecommandeListNew);
            List<Tusers> attachedTusersListNew = new ArrayList<Tusers>();
            for (Tusers tusersListNewTusersToAttach : tusersListNew) {
                tusersListNewTusersToAttach = em.getReference(tusersListNewTusersToAttach.getClass(), tusersListNewTusersToAttach.getId());
                attachedTusersListNew.add(tusersListNewTusersToAttach);
            }
            tusersListNew = attachedTusersListNew;
            societe.setTusersList(tusersListNew);
            List<Tpriority> attachedTpriorityListNew = new ArrayList<Tpriority>();
            for (Tpriority tpriorityListNewTpriorityToAttach : tpriorityListNew) {
                tpriorityListNewTpriorityToAttach = em.getReference(tpriorityListNewTpriorityToAttach.getClass(), tpriorityListNewTpriorityToAttach.getId());
                attachedTpriorityListNew.add(tpriorityListNewTpriorityToAttach);
            }
            tpriorityListNew = attachedTpriorityListNew;
            societe.setTpriorityList(tpriorityListNew);
            List<Tourner> attachedTournerListNew = new ArrayList<Tourner>();
            for (Tourner tournerListNewTournerToAttach : tournerListNew) {
                tournerListNewTournerToAttach = em.getReference(tournerListNewTournerToAttach.getClass(), tournerListNewTournerToAttach.getId());
                attachedTournerListNew.add(tournerListNewTournerToAttach);
            }
            tournerListNew = attachedTournerListNew;
            societe.setTournerList(tournerListNew);
            List<Caisse> attachedCaisseListNew = new ArrayList<Caisse>();
            for (Caisse caisseListNewCaisseToAttach : caisseListNew) {
                caisseListNewCaisseToAttach = em.getReference(caisseListNewCaisseToAttach.getClass(), caisseListNewCaisseToAttach.getId());
                attachedCaisseListNew.add(caisseListNewCaisseToAttach);
            }
            caisseListNew = attachedCaisseListNew;
            societe.setCaisseList(caisseListNew);
            List<LigneAccount> attachedLigneAccountListNew = new ArrayList<LigneAccount>();
            for (LigneAccount ligneAccountListNewLigneAccountToAttach : ligneAccountListNew) {
                ligneAccountListNewLigneAccountToAttach = em.getReference(ligneAccountListNewLigneAccountToAttach.getClass(), ligneAccountListNewLigneAccountToAttach.getId());
                attachedLigneAccountListNew.add(ligneAccountListNewLigneAccountToAttach);
            }
            ligneAccountListNew = attachedLigneAccountListNew;
            societe.setLigneAccountList(ligneAccountListNew);
            List<Tsources> attachedTsourcesListNew = new ArrayList<Tsources>();
            for (Tsources tsourcesListNewTsourcesToAttach : tsourcesListNew) {
                tsourcesListNewTsourcesToAttach = em.getReference(tsourcesListNewTsourcesToAttach.getClass(), tsourcesListNewTsourcesToAttach.getId());
                attachedTsourcesListNew.add(tsourcesListNewTsourcesToAttach);
            }
            tsourcesListNew = attachedTsourcesListNew;
            societe.setTsourcesList(tsourcesListNew);
            List<Tclients> attachedTclientsListNew = new ArrayList<Tclients>();
            for (Tclients tclientsListNewTclientsToAttach : tclientsListNew) {
                tclientsListNewTclientsToAttach = em.getReference(tclientsListNewTclientsToAttach.getClass(), tclientsListNewTclientsToAttach.getId());
                attachedTclientsListNew.add(tclientsListNewTclientsToAttach);
            }
            tclientsListNew = attachedTclientsListNew;
            societe.setTclientsList(tclientsListNew);
            List<Magasin> attachedMagasinListNew = new ArrayList<Magasin>();
            for (Magasin magasinListNewMagasinToAttach : magasinListNew) {
                magasinListNewMagasinToAttach = em.getReference(magasinListNewMagasinToAttach.getClass(), magasinListNewMagasinToAttach.getId());
                attachedMagasinListNew.add(magasinListNewMagasinToAttach);
            }
            magasinListNew = attachedMagasinListNew;
            societe.setMagasinList(magasinListNew);
            List<Tregions> attachedTregionsListNew = new ArrayList<Tregions>();
            for (Tregions tregionsListNewTregionsToAttach : tregionsListNew) {
                tregionsListNewTregionsToAttach = em.getReference(tregionsListNewTregionsToAttach.getClass(), tregionsListNewTregionsToAttach.getId());
                attachedTregionsListNew.add(tregionsListNewTregionsToAttach);
            }
            tregionsListNew = attachedTregionsListNew;
            societe.setTregionsList(tregionsListNew);
            List<Emballage> attachedEmballageListNew = new ArrayList<Emballage>();
            for (Emballage emballageListNewEmballageToAttach : emballageListNew) {
                emballageListNewEmballageToAttach = em.getReference(emballageListNewEmballageToAttach.getClass(), emballageListNewEmballageToAttach.getId());
                attachedEmballageListNew.add(emballageListNewEmballageToAttach);
            }
            emballageListNew = attachedEmballageListNew;
            societe.setEmballageList(emballageListNew);
            List<Tgroups> attachedTgroupsListNew = new ArrayList<Tgroups>();
            for (Tgroups tgroupsListNewTgroupsToAttach : tgroupsListNew) {
                tgroupsListNewTgroupsToAttach = em.getReference(tgroupsListNewTgroupsToAttach.getClass(), tgroupsListNewTgroupsToAttach.getId());
                attachedTgroupsListNew.add(tgroupsListNewTgroupsToAttach);
            }
            tgroupsListNew = attachedTgroupsListNew;
            societe.setTgroupsList(tgroupsListNew);
            List<Tcategorie> attachedTcategorieListNew = new ArrayList<Tcategorie>();
            for (Tcategorie tcategorieListNewTcategorieToAttach : tcategorieListNew) {
                tcategorieListNewTcategorieToAttach = em.getReference(tcategorieListNewTcategorieToAttach.getClass(), tcategorieListNewTcategorieToAttach.getId());
                attachedTcategorieListNew.add(tcategorieListNewTcategorieToAttach);
            }
            tcategorieListNew = attachedTcategorieListNew;
            societe.setTcategorieList(tcategorieListNew);
            List<Ttypeclients> attachedTtypeclientsListNew = new ArrayList<Ttypeclients>();
            for (Ttypeclients ttypeclientsListNewTtypeclientsToAttach : ttypeclientsListNew) {
                ttypeclientsListNewTtypeclientsToAttach = em.getReference(ttypeclientsListNewTtypeclientsToAttach.getClass(), ttypeclientsListNewTtypeclientsToAttach.getId());
                attachedTtypeclientsListNew.add(ttypeclientsListNewTtypeclientsToAttach);
            }
            ttypeclientsListNew = attachedTtypeclientsListNew;
            societe.setTtypeclientsList(ttypeclientsListNew);
            List<SouscriptionLicence> attachedSouscriptionLicenceListNew = new ArrayList<SouscriptionLicence>();
            for (SouscriptionLicence souscriptionLicenceListNewSouscriptionLicenceToAttach : souscriptionLicenceListNew) {
                souscriptionLicenceListNewSouscriptionLicenceToAttach = em.getReference(souscriptionLicenceListNewSouscriptionLicenceToAttach.getClass(), souscriptionLicenceListNewSouscriptionLicenceToAttach.getId());
                attachedSouscriptionLicenceListNew.add(souscriptionLicenceListNewSouscriptionLicenceToAttach);
            }
            souscriptionLicenceListNew = attachedSouscriptionLicenceListNew;
            societe.setSouscriptionLicenceList(souscriptionLicenceListNew);
            societe = em.merge(societe);
            for (LigneInventaire ligneInventaireListOldLigneInventaire : ligneInventaireListOld) {
                if (!ligneInventaireListNew.contains(ligneInventaireListOldLigneInventaire)) {
                    ligneInventaireListOldLigneInventaire.setSociete(null);
                    ligneInventaireListOldLigneInventaire = em.merge(ligneInventaireListOldLigneInventaire);
                }
            }
            for (LigneInventaire ligneInventaireListNewLigneInventaire : ligneInventaireListNew) {
                if (!ligneInventaireListOld.contains(ligneInventaireListNewLigneInventaire)) {
                    Societe oldSocieteOfLigneInventaireListNewLigneInventaire = ligneInventaireListNewLigneInventaire.getSociete();
                    ligneInventaireListNewLigneInventaire.setSociete(societe);
                    ligneInventaireListNewLigneInventaire = em.merge(ligneInventaireListNewLigneInventaire);
                    if (oldSocieteOfLigneInventaireListNewLigneInventaire != null && !oldSocieteOfLigneInventaireListNewLigneInventaire.equals(societe)) {
                        oldSocieteOfLigneInventaireListNewLigneInventaire.getLigneInventaireList().remove(ligneInventaireListNewLigneInventaire);
                        oldSocieteOfLigneInventaireListNewLigneInventaire = em.merge(oldSocieteOfLigneInventaireListNewLigneInventaire);
                    }
                }
            }
            for (Trubriques trubriquesListOldTrubriques : trubriquesListOld) {
                if (!trubriquesListNew.contains(trubriquesListOldTrubriques)) {
                    trubriquesListOldTrubriques.setSociete(null);
                    trubriquesListOldTrubriques = em.merge(trubriquesListOldTrubriques);
                }
            }
            for (Trubriques trubriquesListNewTrubriques : trubriquesListNew) {
                if (!trubriquesListOld.contains(trubriquesListNewTrubriques)) {
                    Societe oldSocieteOfTrubriquesListNewTrubriques = trubriquesListNewTrubriques.getSociete();
                    trubriquesListNewTrubriques.setSociete(societe);
                    trubriquesListNewTrubriques = em.merge(trubriquesListNewTrubriques);
                    if (oldSocieteOfTrubriquesListNewTrubriques != null && !oldSocieteOfTrubriquesListNewTrubriques.equals(societe)) {
                        oldSocieteOfTrubriquesListNewTrubriques.getTrubriquesList().remove(trubriquesListNewTrubriques);
                        oldSocieteOfTrubriquesListNewTrubriques = em.merge(oldSocieteOfTrubriquesListNewTrubriques);
                    }
                }
            }
            for (Fournisseur fournisseurListOldFournisseur : fournisseurListOld) {
                if (!fournisseurListNew.contains(fournisseurListOldFournisseur)) {
                    fournisseurListOldFournisseur.setSociete(null);
                    fournisseurListOldFournisseur = em.merge(fournisseurListOldFournisseur);
                }
            }
            for (Fournisseur fournisseurListNewFournisseur : fournisseurListNew) {
                if (!fournisseurListOld.contains(fournisseurListNewFournisseur)) {
                    Societe oldSocieteOfFournisseurListNewFournisseur = fournisseurListNewFournisseur.getSociete();
                    fournisseurListNewFournisseur.setSociete(societe);
                    fournisseurListNewFournisseur = em.merge(fournisseurListNewFournisseur);
                    if (oldSocieteOfFournisseurListNewFournisseur != null && !oldSocieteOfFournisseurListNewFournisseur.equals(societe)) {
                        oldSocieteOfFournisseurListNewFournisseur.getFournisseurList().remove(fournisseurListNewFournisseur);
                        oldSocieteOfFournisseurListNewFournisseur = em.merge(oldSocieteOfFournisseurListNewFournisseur);
                    }
                }
            }
            for (Tlignecommande tlignecommandeListOldTlignecommande : tlignecommandeListOld) {
                if (!tlignecommandeListNew.contains(tlignecommandeListOldTlignecommande)) {
                    tlignecommandeListOldTlignecommande.setSociete(null);
                    tlignecommandeListOldTlignecommande = em.merge(tlignecommandeListOldTlignecommande);
                }
            }
            for (Tlignecommande tlignecommandeListNewTlignecommande : tlignecommandeListNew) {
                if (!tlignecommandeListOld.contains(tlignecommandeListNewTlignecommande)) {
                    Societe oldSocieteOfTlignecommandeListNewTlignecommande = tlignecommandeListNewTlignecommande.getSociete();
                    tlignecommandeListNewTlignecommande.setSociete(societe);
                    tlignecommandeListNewTlignecommande = em.merge(tlignecommandeListNewTlignecommande);
                    if (oldSocieteOfTlignecommandeListNewTlignecommande != null && !oldSocieteOfTlignecommandeListNewTlignecommande.equals(societe)) {
                        oldSocieteOfTlignecommandeListNewTlignecommande.getTlignecommandeList().remove(tlignecommandeListNewTlignecommande);
                        oldSocieteOfTlignecommandeListNewTlignecommande = em.merge(oldSocieteOfTlignecommandeListNewTlignecommande);
                    }
                }
            }
            for (Tusers tusersListOldTusers : tusersListOld) {
                if (!tusersListNew.contains(tusersListOldTusers)) {
                    tusersListOldTusers.setSociete(null);
                    tusersListOldTusers = em.merge(tusersListOldTusers);
                }
            }
            for (Tusers tusersListNewTusers : tusersListNew) {
                if (!tusersListOld.contains(tusersListNewTusers)) {
                    Societe oldSocieteOfTusersListNewTusers = tusersListNewTusers.getSociete();
                    tusersListNewTusers.setSociete(societe);
                    tusersListNewTusers = em.merge(tusersListNewTusers);
                    if (oldSocieteOfTusersListNewTusers != null && !oldSocieteOfTusersListNewTusers.equals(societe)) {
                        oldSocieteOfTusersListNewTusers.getTusersList().remove(tusersListNewTusers);
                        oldSocieteOfTusersListNewTusers = em.merge(oldSocieteOfTusersListNewTusers);
                    }
                }
            }
            for (Tpriority tpriorityListOldTpriority : tpriorityListOld) {
                if (!tpriorityListNew.contains(tpriorityListOldTpriority)) {
                    tpriorityListOldTpriority.setSociete(null);
                    tpriorityListOldTpriority = em.merge(tpriorityListOldTpriority);
                }
            }
            for (Tpriority tpriorityListNewTpriority : tpriorityListNew) {
                if (!tpriorityListOld.contains(tpriorityListNewTpriority)) {
                    Societe oldSocieteOfTpriorityListNewTpriority = tpriorityListNewTpriority.getSociete();
                    tpriorityListNewTpriority.setSociete(societe);
                    tpriorityListNewTpriority = em.merge(tpriorityListNewTpriority);
                    if (oldSocieteOfTpriorityListNewTpriority != null && !oldSocieteOfTpriorityListNewTpriority.equals(societe)) {
                        oldSocieteOfTpriorityListNewTpriority.getTpriorityList().remove(tpriorityListNewTpriority);
                        oldSocieteOfTpriorityListNewTpriority = em.merge(oldSocieteOfTpriorityListNewTpriority);
                    }
                }
            }
            for (Tourner tournerListNewTourner : tournerListNew) {
                if (!tournerListOld.contains(tournerListNewTourner)) {
                    Societe oldSocieteOfTournerListNewTourner = tournerListNewTourner.getSociete();
                    tournerListNewTourner.setSociete(societe);
                    tournerListNewTourner = em.merge(tournerListNewTourner);
                    if (oldSocieteOfTournerListNewTourner != null && !oldSocieteOfTournerListNewTourner.equals(societe)) {
                        oldSocieteOfTournerListNewTourner.getTournerList().remove(tournerListNewTourner);
                        oldSocieteOfTournerListNewTourner = em.merge(oldSocieteOfTournerListNewTourner);
                    }
                }
            }
            for (Caisse caisseListNewCaisse : caisseListNew) {
                if (!caisseListOld.contains(caisseListNewCaisse)) {
                    Societe oldSocieteOfCaisseListNewCaisse = caisseListNewCaisse.getSociete();
                    caisseListNewCaisse.setSociete(societe);
                    caisseListNewCaisse = em.merge(caisseListNewCaisse);
                    if (oldSocieteOfCaisseListNewCaisse != null && !oldSocieteOfCaisseListNewCaisse.equals(societe)) {
                        oldSocieteOfCaisseListNewCaisse.getCaisseList().remove(caisseListNewCaisse);
                        oldSocieteOfCaisseListNewCaisse = em.merge(oldSocieteOfCaisseListNewCaisse);
                    }
                }
            }
            for (LigneAccount ligneAccountListOldLigneAccount : ligneAccountListOld) {
                if (!ligneAccountListNew.contains(ligneAccountListOldLigneAccount)) {
                    ligneAccountListOldLigneAccount.setSociete(null);
                    ligneAccountListOldLigneAccount = em.merge(ligneAccountListOldLigneAccount);
                }
            }
            for (LigneAccount ligneAccountListNewLigneAccount : ligneAccountListNew) {
                if (!ligneAccountListOld.contains(ligneAccountListNewLigneAccount)) {
                    Societe oldSocieteOfLigneAccountListNewLigneAccount = ligneAccountListNewLigneAccount.getSociete();
                    ligneAccountListNewLigneAccount.setSociete(societe);
                    ligneAccountListNewLigneAccount = em.merge(ligneAccountListNewLigneAccount);
                    if (oldSocieteOfLigneAccountListNewLigneAccount != null && !oldSocieteOfLigneAccountListNewLigneAccount.equals(societe)) {
                        oldSocieteOfLigneAccountListNewLigneAccount.getLigneAccountList().remove(ligneAccountListNewLigneAccount);
                        oldSocieteOfLigneAccountListNewLigneAccount = em.merge(oldSocieteOfLigneAccountListNewLigneAccount);
                    }
                }
            }
            for (Tsources tsourcesListOldTsources : tsourcesListOld) {
                if (!tsourcesListNew.contains(tsourcesListOldTsources)) {
                    tsourcesListOldTsources.setSociete(null);
                    tsourcesListOldTsources = em.merge(tsourcesListOldTsources);
                }
            }
            for (Tsources tsourcesListNewTsources : tsourcesListNew) {
                if (!tsourcesListOld.contains(tsourcesListNewTsources)) {
                    Societe oldSocieteOfTsourcesListNewTsources = tsourcesListNewTsources.getSociete();
                    tsourcesListNewTsources.setSociete(societe);
                    tsourcesListNewTsources = em.merge(tsourcesListNewTsources);
                    if (oldSocieteOfTsourcesListNewTsources != null && !oldSocieteOfTsourcesListNewTsources.equals(societe)) {
                        oldSocieteOfTsourcesListNewTsources.getTsourcesList().remove(tsourcesListNewTsources);
                        oldSocieteOfTsourcesListNewTsources = em.merge(oldSocieteOfTsourcesListNewTsources);
                    }
                }
            }
             for (LigneSortie ligneSortieListOldLigneSortie : ligneSortieListOld) {
                if (!ligneSortieListNew.contains(ligneSortieListOldLigneSortie)) {
                    ligneSortieListOldLigneSortie.setSociete(null);
                    ligneSortieListOldLigneSortie = em.merge(ligneSortieListOldLigneSortie);
                }
            }
            for (LigneSortie ligneSortieListNewLigneSortie : ligneSortieListNew) {
                if (!ligneSortieListOld.contains(ligneSortieListNewLigneSortie)) {
                    Societe oldSocieteOfLigneSortieListNewLigneSortie = ligneSortieListNewLigneSortie.getSociete();
                    ligneSortieListNewLigneSortie.setSociete(societe);
                    ligneSortieListNewLigneSortie = em.merge(ligneSortieListNewLigneSortie);
                    if (oldSocieteOfLigneSortieListNewLigneSortie != null && !oldSocieteOfLigneSortieListNewLigneSortie.equals(societe)) {
                        oldSocieteOfLigneSortieListNewLigneSortie.getLigneSortieList().remove(ligneSortieListNewLigneSortie);
                        oldSocieteOfLigneSortieListNewLigneSortie = em.merge(oldSocieteOfLigneSortieListNewLigneSortie);
                    }
                }
            }
            for (Tclients tclientsListOldTclients : tclientsListOld) {
                if (!tclientsListNew.contains(tclientsListOldTclients)) {
                    tclientsListOldTclients.setSociete(null);
                    tclientsListOldTclients = em.merge(tclientsListOldTclients);
                }
            }
            for (Tclients tclientsListNewTclients : tclientsListNew) {
                if (!tclientsListOld.contains(tclientsListNewTclients)) {
                    Societe oldSocieteOfTclientsListNewTclients = tclientsListNewTclients.getSociete();
                    tclientsListNewTclients.setSociete(societe);
                    tclientsListNewTclients = em.merge(tclientsListNewTclients);
                    if (oldSocieteOfTclientsListNewTclients != null && !oldSocieteOfTclientsListNewTclients.equals(societe)) {
                        oldSocieteOfTclientsListNewTclients.getTclientsList().remove(tclientsListNewTclients);
                        oldSocieteOfTclientsListNewTclients = em.merge(oldSocieteOfTclientsListNewTclients);
                    }
                }
            }
            for (Magasin magasinListNewMagasin : magasinListNew) {
                if (!magasinListOld.contains(magasinListNewMagasin)) {
                    Societe oldSocieteOfMagasinListNewMagasin = magasinListNewMagasin.getSociete();
                    magasinListNewMagasin.setSociete(societe);
                    magasinListNewMagasin = em.merge(magasinListNewMagasin);
                    if (oldSocieteOfMagasinListNewMagasin != null && !oldSocieteOfMagasinListNewMagasin.equals(societe)) {
                        oldSocieteOfMagasinListNewMagasin.getMagasinList().remove(magasinListNewMagasin);
                        oldSocieteOfMagasinListNewMagasin = em.merge(oldSocieteOfMagasinListNewMagasin);
                    }
                }
            }
            for (Tregions tregionsListOldTregions : tregionsListOld) {
                if (!tregionsListNew.contains(tregionsListOldTregions)) {
                    tregionsListOldTregions.setSociete(null);
                    tregionsListOldTregions = em.merge(tregionsListOldTregions);
                }
            }
            for (Tregions tregionsListNewTregions : tregionsListNew) {
                if (!tregionsListOld.contains(tregionsListNewTregions)) {
                    Societe oldSocieteOfTregionsListNewTregions = tregionsListNewTregions.getSociete();
                    tregionsListNewTregions.setSociete(societe);
                    tregionsListNewTregions = em.merge(tregionsListNewTregions);
                    if (oldSocieteOfTregionsListNewTregions != null && !oldSocieteOfTregionsListNewTregions.equals(societe)) {
                        oldSocieteOfTregionsListNewTregions.getTregionsList().remove(tregionsListNewTregions);
                        oldSocieteOfTregionsListNewTregions = em.merge(oldSocieteOfTregionsListNewTregions);
                    }
                }
            }
            for (Emballage emballageListNewEmballage : emballageListNew) {
                if (!emballageListOld.contains(emballageListNewEmballage)) {
                    Societe oldSocieteOfEmballageListNewEmballage = emballageListNewEmballage.getSociete();
                    emballageListNewEmballage.setSociete(societe);
                    emballageListNewEmballage = em.merge(emballageListNewEmballage);
                    if (oldSocieteOfEmballageListNewEmballage != null && !oldSocieteOfEmballageListNewEmballage.equals(societe)) {
                        oldSocieteOfEmballageListNewEmballage.getEmballageList().remove(emballageListNewEmballage);
                        oldSocieteOfEmballageListNewEmballage = em.merge(oldSocieteOfEmballageListNewEmballage);
                    }
                }
            }
            for (Tgroups tgroupsListOldTgroups : tgroupsListOld) {
                if (!tgroupsListNew.contains(tgroupsListOldTgroups)) {
                    tgroupsListOldTgroups.setSociete(null);
                    tgroupsListOldTgroups = em.merge(tgroupsListOldTgroups);
                }
            }
            for (Tgroups tgroupsListNewTgroups : tgroupsListNew) {
                if (!tgroupsListOld.contains(tgroupsListNewTgroups)) {
                    Societe oldSocieteOfTgroupsListNewTgroups = tgroupsListNewTgroups.getSociete();
                    tgroupsListNewTgroups.setSociete(societe);
                    tgroupsListNewTgroups = em.merge(tgroupsListNewTgroups);
                    if (oldSocieteOfTgroupsListNewTgroups != null && !oldSocieteOfTgroupsListNewTgroups.equals(societe)) {
                        oldSocieteOfTgroupsListNewTgroups.getTgroupsList().remove(tgroupsListNewTgroups);
                        oldSocieteOfTgroupsListNewTgroups = em.merge(oldSocieteOfTgroupsListNewTgroups);
                    }
                }
            }
            for (Tcategorie tcategorieListOldTcategorie : tcategorieListOld) {
                if (!tcategorieListNew.contains(tcategorieListOldTcategorie)) {
                    tcategorieListOldTcategorie.setSociete(null);
                    tcategorieListOldTcategorie = em.merge(tcategorieListOldTcategorie);
                }
            }
            for (Tcategorie tcategorieListNewTcategorie : tcategorieListNew) {
                if (!tcategorieListOld.contains(tcategorieListNewTcategorie)) {
                    Societe oldSocieteOfTcategorieListNewTcategorie = tcategorieListNewTcategorie.getSociete();
                    tcategorieListNewTcategorie.setSociete(societe);
                    tcategorieListNewTcategorie = em.merge(tcategorieListNewTcategorie);
                    if (oldSocieteOfTcategorieListNewTcategorie != null && !oldSocieteOfTcategorieListNewTcategorie.equals(societe)) {
                        oldSocieteOfTcategorieListNewTcategorie.getTcategorieList().remove(tcategorieListNewTcategorie);
                        oldSocieteOfTcategorieListNewTcategorie = em.merge(oldSocieteOfTcategorieListNewTcategorie);
                    }
                }
            }
            for (LigneSortie ligneSortieListOldLigneSortie : ligneSortieListOld) {
                if (!ligneSortieListNew.contains(ligneSortieListOldLigneSortie)) {
                    ligneSortieListOldLigneSortie.setSociete(null);
                    ligneSortieListOldLigneSortie = em.merge(ligneSortieListOldLigneSortie);
                }
            }
            for (LigneSortie ligneSortieListNewLigneSortie : ligneSortieListNew) {
                if (!ligneSortieListOld.contains(ligneSortieListNewLigneSortie)) {
                    Societe oldSocieteOfLigneSortieListNewLigneSortie = ligneSortieListNewLigneSortie.getSociete();
                    ligneSortieListNewLigneSortie.setSociete(societe);
                    ligneSortieListNewLigneSortie = em.merge(ligneSortieListNewLigneSortie);
                    if (oldSocieteOfLigneSortieListNewLigneSortie != null && !oldSocieteOfLigneSortieListNewLigneSortie.equals(societe)) {
                        oldSocieteOfLigneSortieListNewLigneSortie.getLigneSortieList().remove(ligneSortieListNewLigneSortie);
                        oldSocieteOfLigneSortieListNewLigneSortie = em.merge(oldSocieteOfLigneSortieListNewLigneSortie);
                    }
                }
            }
            for (Ttypeclients ttypeclientsListOldTtypeclients : ttypeclientsListOld) {
                if (!ttypeclientsListNew.contains(ttypeclientsListOldTtypeclients)) {
                    ttypeclientsListOldTtypeclients.setSociete(null);
                    ttypeclientsListOldTtypeclients = em.merge(ttypeclientsListOldTtypeclients);
                }
            }
            for (Ttypeclients ttypeclientsListNewTtypeclients : ttypeclientsListNew) {
                if (!ttypeclientsListOld.contains(ttypeclientsListNewTtypeclients)) {
                    Societe oldSocieteOfTtypeclientsListNewTtypeclients = ttypeclientsListNewTtypeclients.getSociete();
                    ttypeclientsListNewTtypeclients.setSociete(societe);
                    ttypeclientsListNewTtypeclients = em.merge(ttypeclientsListNewTtypeclients);
                    if (oldSocieteOfTtypeclientsListNewTtypeclients != null && !oldSocieteOfTtypeclientsListNewTtypeclients.equals(societe)) {
                        oldSocieteOfTtypeclientsListNewTtypeclients.getTtypeclientsList().remove(ttypeclientsListNewTtypeclients);
                        oldSocieteOfTtypeclientsListNewTtypeclients = em.merge(oldSocieteOfTtypeclientsListNewTtypeclients);
                    }
                }
            }
            for (SouscriptionLicence souscriptionLicenceListOldSouscriptionLicence : souscriptionLicenceListOld) {
                if (!souscriptionLicenceListNew.contains(souscriptionLicenceListOldSouscriptionLicence)) {
                    souscriptionLicenceListOldSouscriptionLicence.setSociete(null);
                    souscriptionLicenceListOldSouscriptionLicence = em.merge(souscriptionLicenceListOldSouscriptionLicence);
                }
            }
            for (SouscriptionLicence souscriptionLicenceListNewSouscriptionLicence : souscriptionLicenceListNew) {
                if (!souscriptionLicenceListOld.contains(souscriptionLicenceListNewSouscriptionLicence)) {
                    Societe oldSocieteOfSouscriptionLicenceListNewSouscriptionLicence = souscriptionLicenceListNewSouscriptionLicence.getSociete();
                    souscriptionLicenceListNewSouscriptionLicence.setSociete(societe);
                    souscriptionLicenceListNewSouscriptionLicence = em.merge(souscriptionLicenceListNewSouscriptionLicence);
                    if (oldSocieteOfSouscriptionLicenceListNewSouscriptionLicence != null && !oldSocieteOfSouscriptionLicenceListNewSouscriptionLicence.equals(societe)) {
                        oldSocieteOfSouscriptionLicenceListNewSouscriptionLicence.getSouscriptionLicenceList().remove(souscriptionLicenceListNewSouscriptionLicence);
                        oldSocieteOfSouscriptionLicenceListNewSouscriptionLicence = em.merge(oldSocieteOfSouscriptionLicenceListNewSouscriptionLicence);
                    }
                }
            }
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @Override
    public void supprimer(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            Societe societe;
            try {
                societe = em.getReference(Societe.class, id);
                societe.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The societe with id " + id + " no longer exists.", enfe);
            }

            List<LigneInventaire> ligneInventaireList = societe.getLigneInventaireList();
            for (LigneInventaire ligneInventaireListLigneInventaire : ligneInventaireList) {
                ligneInventaireListLigneInventaire.setSociete(null);
                ligneInventaireListLigneInventaire = em.merge(ligneInventaireListLigneInventaire);
            }
            List<Trubriques> trubriquesList = societe.getTrubriquesList();
            for (Trubriques trubriquesListTrubriques : trubriquesList) {
                trubriquesListTrubriques.setSociete(null);
                trubriquesListTrubriques = em.merge(trubriquesListTrubriques);
            }
            List<Fournisseur> fournisseurList = societe.getFournisseurList();
            for (Fournisseur fournisseurListFournisseur : fournisseurList) {
                fournisseurListFournisseur.setSociete(null);
                fournisseurListFournisseur = em.merge(fournisseurListFournisseur);
            }
            List<Tlignecommande> tlignecommandeList = societe.getTlignecommandeList();
            for (Tlignecommande tlignecommandeListTlignecommande : tlignecommandeList) {
                tlignecommandeListTlignecommande.setSociete(null);
                tlignecommandeListTlignecommande = em.merge(tlignecommandeListTlignecommande);
            }
            List<Tusers> tusersList = societe.getTusersList();
            for (Tusers tusersListTusers : tusersList) {
                tusersListTusers.setSociete(null);
                tusersListTusers = em.merge(tusersListTusers);
            }
            List<Tpriority> tpriorityList = societe.getTpriorityList();
            for (Tpriority tpriorityListTpriority : tpriorityList) {
                tpriorityListTpriority.setSociete(null);
                tpriorityListTpriority = em.merge(tpriorityListTpriority);
            }
            List<LigneAccount> ligneAccountList = societe.getLigneAccountList();
            for (LigneAccount ligneAccountListLigneAccount : ligneAccountList) {
                ligneAccountListLigneAccount.setSociete(null);
                ligneAccountListLigneAccount = em.merge(ligneAccountListLigneAccount);
            }
            List<Tsources> tsourcesList = societe.getTsourcesList();
            for (Tsources tsourcesListTsources : tsourcesList) {
                tsourcesListTsources.setSociete(null);
                tsourcesListTsources = em.merge(tsourcesListTsources);
            }
            List<Tclients> tclientsList = societe.getTclientsList();
            for (Tclients tclientsListTclients : tclientsList) {
                tclientsListTclients.setSociete(null);
                tclientsListTclients = em.merge(tclientsListTclients);
            }
            List<Tregions> tregionsList = societe.getTregionsList();
            for (Tregions tregionsListTregions : tregionsList) {
                tregionsListTregions.setSociete(null);
                tregionsListTregions = em.merge(tregionsListTregions);
            }
            List<Tgroups> tgroupsList = societe.getTgroupsList();
            for (Tgroups tgroupsListTgroups : tgroupsList) {
                tgroupsListTgroups.setSociete(null);
                tgroupsListTgroups = em.merge(tgroupsListTgroups);
            }
            List<Tcategorie> tcategorieList = societe.getTcategorieList();
            for (Tcategorie tcategorieListTcategorie : tcategorieList) {
                tcategorieListTcategorie.setSociete(null);
                tcategorieListTcategorie = em.merge(tcategorieListTcategorie);
            }
            List<Ttypeclients> ttypeclientsList = societe.getTtypeclientsList();
            for (Ttypeclients ttypeclientsListTtypeclients : ttypeclientsList) {
                ttypeclientsListTtypeclients.setSociete(null);
                ttypeclientsListTtypeclients = em.merge(ttypeclientsListTtypeclients);
            }
            List<LigneSortie> ligneSortieList = societe.getLigneSortieList();
            for (LigneSortie ligneSortieListLigneSortie : ligneSortieList) {
                ligneSortieListLigneSortie.setSociete(null);
                ligneSortieListLigneSortie = em.merge(ligneSortieListLigneSortie);
            }
            List<SouscriptionLicence> souscriptionLicenceList = societe.getSouscriptionLicenceList();
            for (SouscriptionLicence souscriptionLicenceListSouscriptionLicence : souscriptionLicenceList) {
                souscriptionLicenceListSouscriptionLicence.setSociete(null);
                souscriptionLicenceListSouscriptionLicence = em.merge(souscriptionLicenceListSouscriptionLicence);
            }
            em.remove(societe);
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @Override
    public List<Societe> findSocieteEntities() {
        return findSocieteEntities(true, -1, -1);
    }

    public List<Societe> findSocieteEntities(int maxResults, int firstResult) {
        return findSocieteEntities(false, maxResults, firstResult);
    }

    private List<Societe> findSocieteEntities(boolean all, int maxResults, int firstResult) {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Societe.class));
        Query q = getEntityManager().createQuery(cq);
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    @Override
    public Societe findSociete(Integer id) {
        return getEntityManager().find(Societe.class, id);
    }

    @Override
    public Societe findSocieteByImma(int imma) {
        Query q = getEntityManager().createNamedQuery("Societe.findByImma");
        q.setParameter("imma", imma);
        return (Societe) q.getSingleResult();
    }

    @Override
    public List<Societe> findAllByImma(int imma) {
        Query q = getEntityManager().createNamedQuery("Societe.findByImma");
        q.setParameter("imma", imma);
        return q.getResultList();
    }

    @Override
    public List<Societe> findAllByForIdRepeat(int imma) {
        Query q = getEntityManager().createNamedQuery("Societe.findById");
        q.setParameter("id", imma);
        return q.getResultList();
    }

    @Override
    public List<Societe> findAllByAutoSaveClient(int auto) {
        Query q = getEntityManager().createNamedQuery("Societe.findByAllByAutoSaveClient");
        q.setParameter("auto", auto);
        return q.getResultList();
    }

}
