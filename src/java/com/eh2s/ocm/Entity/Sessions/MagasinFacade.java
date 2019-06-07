/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.IllegalOrphanException;
import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.LigneInventaire;
import com.eh2s.ocm.Entity.LigneTransfertStock;
import com.eh2s.ocm.Entity.Magasin;
import com.eh2s.ocm.Entity.Societe;
import com.eh2s.ocm.Entity.StockMg;
import com.eh2s.ocm.Entity.Tourner;
import com.eh2s.ocm.Entity.Tregions;
import com.eh2s.ocm.Entity.Tusers;
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

/**
 *
 * @author messi01
 */
@Stateless
public class MagasinFacade extends AbstractFacade<Magasin> implements MagasinFacadeLocal {

    @PersistenceContext(unitName = "ccmanagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MagasinFacade() {
        super(Magasin.class);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public Magasin creer(Magasin magasin) throws RollbackFailureException, Exception {
        if (magasin.getLigneInventaireList() == null) {
            magasin.setLigneInventaireList(new ArrayList<LigneInventaire>());
        }
        if (magasin.getTusersList() == null) {
            magasin.setTusersList(new ArrayList<Tusers>());
        }
        if (magasin.getLignetransfertstockList() == null) {
            magasin.setLignetransfertstockList(new ArrayList<LigneTransfertStock>());
        }
        if (magasin.getLignetransfertstockList1() == null) {
            magasin.setLignetransfertstockList1(new ArrayList<LigneTransfertStock>());
        }
        if (magasin.getTournerList() == null) {
            magasin.setTournerList(new ArrayList<Tourner>());
        }
        if (magasin.getStockMgList() == null) {
            magasin.setStockMgList(new ArrayList<StockMg>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            Tusers magasigner = magasin.getMagasigner();
            if (magasigner != null) {
                magasigner = em.getReference(magasigner.getClass(), magasigner.getId());
                magasin.setMagasigner(magasigner);
            }
            Societe societe = magasin.getSociete();
            if (societe != null) {
                societe = em.getReference(societe.getClass(), societe.getId());
                magasin.setSociete(societe);
            }
            Tregions region = magasin.getRegion();
            if (region != null) {
                region = em.getReference(region.getClass(), region.getId());
                magasin.setRegion(region);
            }
            List<LigneInventaire> attachedLigneInventaireList = new ArrayList<LigneInventaire>();
            for (LigneInventaire ligneInventaireListLigneInventaireToAttach : magasin.getLigneInventaireList()) {
                ligneInventaireListLigneInventaireToAttach = em.getReference(ligneInventaireListLigneInventaireToAttach.getClass(), ligneInventaireListLigneInventaireToAttach.getId());
                attachedLigneInventaireList.add(ligneInventaireListLigneInventaireToAttach);
            }
            magasin.setLigneInventaireList(attachedLigneInventaireList);
            List<Tusers> attachedTusersList = new ArrayList<Tusers>();
            for (Tusers tusersListTusersToAttach : magasin.getTusersList()) {
                tusersListTusersToAttach = em.getReference(tusersListTusersToAttach.getClass(), tusersListTusersToAttach.getId());
                attachedTusersList.add(tusersListTusersToAttach);
            }
            magasin.setTusersList(attachedTusersList);
            List<LigneTransfertStock> attachedLignetransfertstockList = new ArrayList<LigneTransfertStock>();
            for (LigneTransfertStock lignetransfertstockListLignetransfertstockToAttach : magasin.getLignetransfertstockList()) {
                lignetransfertstockListLignetransfertstockToAttach = em.getReference(lignetransfertstockListLignetransfertstockToAttach.getClass(), lignetransfertstockListLignetransfertstockToAttach.getId());
                attachedLignetransfertstockList.add(lignetransfertstockListLignetransfertstockToAttach);
            }
            magasin.setLignetransfertstockList(attachedLignetransfertstockList);
            List<LigneTransfertStock> attachedLignetransfertstockList1 = new ArrayList<LigneTransfertStock>();
            for (LigneTransfertStock lignetransfertstockList1LignetransfertstockToAttach : magasin.getLignetransfertstockList1()) {
                lignetransfertstockList1LignetransfertstockToAttach = em.getReference(lignetransfertstockList1LignetransfertstockToAttach.getClass(), lignetransfertstockList1LignetransfertstockToAttach.getId());
                attachedLignetransfertstockList1.add(lignetransfertstockList1LignetransfertstockToAttach);
            }
            magasin.setLignetransfertstockList1(attachedLignetransfertstockList1);
            List<Tourner> attachedTournerList = new ArrayList<Tourner>();
            for (Tourner tournerListTournerToAttach : magasin.getTournerList()) {
                tournerListTournerToAttach = em.getReference(tournerListTournerToAttach.getClass(), tournerListTournerToAttach.getId());
                attachedTournerList.add(tournerListTournerToAttach);
            }
            magasin.setTournerList(attachedTournerList);
            List<StockMg> attachedStockMgList = new ArrayList<StockMg>();
            for (StockMg stockMgListStockMgToAttach : magasin.getStockMgList()) {
                stockMgListStockMgToAttach = em.getReference(stockMgListStockMgToAttach.getClass(), stockMgListStockMgToAttach.getId());
                attachedStockMgList.add(stockMgListStockMgToAttach);
            }
            magasin.setStockMgList(attachedStockMgList);
            em.persist(magasin);
            if (magasigner != null) {
                Magasin oldMagasinOfMagasigner = magasigner.getMagasin();
                if (oldMagasinOfMagasigner != null) {
                    oldMagasinOfMagasigner.setMagasigner(null);
                    oldMagasinOfMagasigner = em.merge(oldMagasinOfMagasigner);
                }
                magasigner.setMagasin(magasin);
                magasigner = em.merge(magasigner);
            }
            if (societe != null) {
                societe.getMagasinList().add(magasin);
                societe = em.merge(societe);
            }
            if (region != null) {
                region.getMagasinList().add(magasin);
                region = em.merge(region);
            }
            for (LigneInventaire ligneInventaireListLigneInventaire : magasin.getLigneInventaireList()) {
                Magasin oldMagasinOfLigneInventaireListLigneInventaire = ligneInventaireListLigneInventaire.getMagasin();
                ligneInventaireListLigneInventaire.setMagasin(magasin);
                ligneInventaireListLigneInventaire = em.merge(ligneInventaireListLigneInventaire);
                if (oldMagasinOfLigneInventaireListLigneInventaire != null) {
                    oldMagasinOfLigneInventaireListLigneInventaire.getLigneInventaireList().remove(ligneInventaireListLigneInventaire);
                    oldMagasinOfLigneInventaireListLigneInventaire = em.merge(oldMagasinOfLigneInventaireListLigneInventaire);
                }
            }
            for (Tusers tusersListTusers : magasin.getTusersList()) {
                Magasin oldMagasinOfTusersListTusers = tusersListTusers.getMagasin();
                tusersListTusers.setMagasin(magasin);
                tusersListTusers = em.merge(tusersListTusers);
                if (oldMagasinOfTusersListTusers != null) {
                    oldMagasinOfTusersListTusers.getTusersList().remove(tusersListTusers);
                    oldMagasinOfTusersListTusers = em.merge(oldMagasinOfTusersListTusers);
                }
            }
            for (LigneTransfertStock lignetransfertstockListLignetransfertstock : magasin.getLignetransfertstockList()) {
                Magasin oldMg1OfLignetransfertstockListLignetransfertstock = lignetransfertstockListLignetransfertstock.getMg1();
                lignetransfertstockListLignetransfertstock.setMg1(magasin);
                lignetransfertstockListLignetransfertstock = em.merge(lignetransfertstockListLignetransfertstock);
                if (oldMg1OfLignetransfertstockListLignetransfertstock != null) {
                    oldMg1OfLignetransfertstockListLignetransfertstock.getLignetransfertstockList().remove(lignetransfertstockListLignetransfertstock);
                    oldMg1OfLignetransfertstockListLignetransfertstock = em.merge(oldMg1OfLignetransfertstockListLignetransfertstock);
                }
            }
            for (LigneTransfertStock lignetransfertstockList1Lignetransfertstock : magasin.getLignetransfertstockList1()) {
                Magasin oldMg2OfLignetransfertstockList1Lignetransfertstock = lignetransfertstockList1Lignetransfertstock.getMg2();
                lignetransfertstockList1Lignetransfertstock.setMg2(magasin);
                lignetransfertstockList1Lignetransfertstock = em.merge(lignetransfertstockList1Lignetransfertstock);
                if (oldMg2OfLignetransfertstockList1Lignetransfertstock != null) {
                    oldMg2OfLignetransfertstockList1Lignetransfertstock.getLignetransfertstockList1().remove(lignetransfertstockList1Lignetransfertstock);
                    oldMg2OfLignetransfertstockList1Lignetransfertstock = em.merge(oldMg2OfLignetransfertstockList1Lignetransfertstock);
                }
            }
            for (Tourner tournerListTourner : magasin.getTournerList()) {
                Magasin oldMagasinOfTournerListTourner = tournerListTourner.getMagasin();
                tournerListTourner.setMagasin(magasin);
                tournerListTourner = em.merge(tournerListTourner);
                if (oldMagasinOfTournerListTourner != null) {
                    oldMagasinOfTournerListTourner.getTournerList().remove(tournerListTourner);
                    oldMagasinOfTournerListTourner = em.merge(oldMagasinOfTournerListTourner);
                }
            }
            for (StockMg stockMgListStockMg : magasin.getStockMgList()) {
                Magasin oldMagasinOfStockMgListStockMg = stockMgListStockMg.getMagasin();
                stockMgListStockMg.setMagasin(magasin);
                stockMgListStockMg = em.merge(stockMgListStockMg);
                if (oldMagasinOfStockMgListStockMg != null) {
                    oldMagasinOfStockMgListStockMg.getStockMgList().remove(stockMgListStockMg);
                    oldMagasinOfStockMgListStockMg = em.merge(oldMagasinOfStockMgListStockMg);
                }
            }
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
        return magasin;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public Magasin MisAJour(Magasin magasin) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            Magasin persistentMagasin = em.find(Magasin.class, magasin.getId());
            Tusers magasignerOld = persistentMagasin.getMagasigner();
            Tusers magasignerNew = magasin.getMagasigner();
            Societe societeOld = persistentMagasin.getSociete();
            Societe societeNew = magasin.getSociete();
            Tregions regionOld = persistentMagasin.getRegion();
            Tregions regionNew = magasin.getRegion();
            List<LigneInventaire> ligneInventaireListOld = persistentMagasin.getLigneInventaireList();
            List<LigneInventaire> ligneInventaireListNew = magasin.getLigneInventaireList();
            List<Tusers> tusersListOld = persistentMagasin.getTusersList();
            List<Tusers> tusersListNew = magasin.getTusersList();
            List<LigneTransfertStock> lignetransfertstockListOld = persistentMagasin.getLignetransfertstockList();
            List<LigneTransfertStock> lignetransfertstockListNew = magasin.getLignetransfertstockList();
            List<LigneTransfertStock> lignetransfertstockList1Old = persistentMagasin.getLignetransfertstockList1();
            List<LigneTransfertStock> lignetransfertstockList1New = magasin.getLignetransfertstockList1();
            List<Tourner> tournerListOld = persistentMagasin.getTournerList();
            List<Tourner> tournerListNew = magasin.getTournerList();
            List<StockMg> stockMgListOld = persistentMagasin.getStockMgList();
            List<StockMg> stockMgListNew = magasin.getStockMgList();

            if (magasignerNew != null) {
                magasignerNew = em.getReference(magasignerNew.getClass(), magasignerNew.getId());
                magasin.setMagasigner(magasignerNew);
            }
            if (societeNew != null) {
                societeNew = em.getReference(societeNew.getClass(), societeNew.getId());
                magasin.setSociete(societeNew);
            }
            if (regionNew != null) {
                regionNew = em.getReference(regionNew.getClass(), regionNew.getId());
                magasin.setRegion(regionNew);
            }
            List<LigneInventaire> attachedLigneInventaireListNew = new ArrayList<LigneInventaire>();
            for (LigneInventaire ligneInventaireListNewLigneInventaireToAttach : ligneInventaireListNew) {
                ligneInventaireListNewLigneInventaireToAttach = em.getReference(ligneInventaireListNewLigneInventaireToAttach.getClass(), ligneInventaireListNewLigneInventaireToAttach.getId());
                attachedLigneInventaireListNew.add(ligneInventaireListNewLigneInventaireToAttach);
            }
            ligneInventaireListNew = attachedLigneInventaireListNew;
            magasin.setLigneInventaireList(ligneInventaireListNew);
            List<Tusers> attachedTusersListNew = new ArrayList<Tusers>();
            for (Tusers tusersListNewTusersToAttach : tusersListNew) {
                tusersListNewTusersToAttach = em.getReference(tusersListNewTusersToAttach.getClass(), tusersListNewTusersToAttach.getId());
                attachedTusersListNew.add(tusersListNewTusersToAttach);
            }
            tusersListNew = attachedTusersListNew;
            magasin.setTusersList(tusersListNew);
            List<LigneTransfertStock> attachedLignetransfertstockListNew = new ArrayList<LigneTransfertStock>();
            for (LigneTransfertStock lignetransfertstockListNewLignetransfertstockToAttach : lignetransfertstockListNew) {
                lignetransfertstockListNewLignetransfertstockToAttach = em.getReference(lignetransfertstockListNewLignetransfertstockToAttach.getClass(), lignetransfertstockListNewLignetransfertstockToAttach.getId());
                attachedLignetransfertstockListNew.add(lignetransfertstockListNewLignetransfertstockToAttach);
            }
            lignetransfertstockListNew = attachedLignetransfertstockListNew;
            magasin.setLignetransfertstockList(lignetransfertstockListNew);
            List<LigneTransfertStock> attachedLignetransfertstockList1New = new ArrayList<LigneTransfertStock>();
            for (LigneTransfertStock lignetransfertstockList1NewLignetransfertstockToAttach : lignetransfertstockList1New) {
                lignetransfertstockList1NewLignetransfertstockToAttach = em.getReference(lignetransfertstockList1NewLignetransfertstockToAttach.getClass(), lignetransfertstockList1NewLignetransfertstockToAttach.getId());
                attachedLignetransfertstockList1New.add(lignetransfertstockList1NewLignetransfertstockToAttach);
            }
            lignetransfertstockList1New = attachedLignetransfertstockList1New;
            magasin.setLignetransfertstockList1(lignetransfertstockList1New);
            List<Tourner> attachedTournerListNew = new ArrayList<Tourner>();
            for (Tourner tournerListNewTournerToAttach : tournerListNew) {
                tournerListNewTournerToAttach = em.getReference(tournerListNewTournerToAttach.getClass(), tournerListNewTournerToAttach.getId());
                attachedTournerListNew.add(tournerListNewTournerToAttach);
            }
            tournerListNew = attachedTournerListNew;
            magasin.setTournerList(tournerListNew);
            List<StockMg> attachedStockMgListNew = new ArrayList<StockMg>();
            for (StockMg stockMgListNewStockMgToAttach : stockMgListNew) {
                stockMgListNewStockMgToAttach = em.getReference(stockMgListNewStockMgToAttach.getClass(), stockMgListNewStockMgToAttach.getId());
                attachedStockMgListNew.add(stockMgListNewStockMgToAttach);
            }
            stockMgListNew = attachedStockMgListNew;
            magasin.setStockMgList(stockMgListNew);
            magasin = em.merge(magasin);
            if (magasignerOld != null && !magasignerOld.equals(magasignerNew)) {
                magasignerOld.setMagasin(null);
                magasignerOld = em.merge(magasignerOld);
            }
            if (magasignerNew != null && !magasignerNew.equals(magasignerOld)) {
                Magasin oldMagasinOfMagasigner = magasignerNew.getMagasin();
                if (oldMagasinOfMagasigner != null) {
                    oldMagasinOfMagasigner.setMagasigner(null);
                    oldMagasinOfMagasigner = em.merge(oldMagasinOfMagasigner);
                }
                magasignerNew.setMagasin(magasin);
                magasignerNew = em.merge(magasignerNew);
            }
            if (societeOld != null && !societeOld.equals(societeNew)) {
                societeOld.getMagasinList().remove(magasin);
                societeOld = em.merge(societeOld);
            }
            if (societeNew != null && !societeNew.equals(societeOld)) {
                societeNew.getMagasinList().add(magasin);
                societeNew = em.merge(societeNew);
            }
            if (regionOld != null && !regionOld.equals(regionNew)) {
                regionOld.getMagasinList().remove(magasin);
                regionOld = em.merge(regionOld);
            }
            if (regionNew != null && !regionNew.equals(regionOld)) {
                regionNew.getMagasinList().add(magasin);
                regionNew = em.merge(regionNew);
            }
            for (LigneInventaire ligneInventaireListOldLigneInventaire : ligneInventaireListOld) {
                if (!ligneInventaireListNew.contains(ligneInventaireListOldLigneInventaire)) {
                    ligneInventaireListOldLigneInventaire.setMagasin(null);
                    ligneInventaireListOldLigneInventaire = em.merge(ligneInventaireListOldLigneInventaire);
                }
            }
            for (LigneInventaire ligneInventaireListNewLigneInventaire : ligneInventaireListNew) {
                if (!ligneInventaireListOld.contains(ligneInventaireListNewLigneInventaire)) {
                    Magasin oldMagasinOfLigneInventaireListNewLigneInventaire = ligneInventaireListNewLigneInventaire.getMagasin();
                    ligneInventaireListNewLigneInventaire.setMagasin(magasin);
                    ligneInventaireListNewLigneInventaire = em.merge(ligneInventaireListNewLigneInventaire);
                    if (oldMagasinOfLigneInventaireListNewLigneInventaire != null && !oldMagasinOfLigneInventaireListNewLigneInventaire.equals(magasin)) {
                        oldMagasinOfLigneInventaireListNewLigneInventaire.getLigneInventaireList().remove(ligneInventaireListNewLigneInventaire);
                        oldMagasinOfLigneInventaireListNewLigneInventaire = em.merge(oldMagasinOfLigneInventaireListNewLigneInventaire);
                    }
                }
            }
            for (Tusers tusersListOldTusers : tusersListOld) {
                if (!tusersListNew.contains(tusersListOldTusers)) {
                    tusersListOldTusers.setMagasin(null);
                    tusersListOldTusers = em.merge(tusersListOldTusers);
                }
            }
            for (Tusers tusersListNewTusers : tusersListNew) {
                if (!tusersListOld.contains(tusersListNewTusers)) {
                    Magasin oldMagasinOfTusersListNewTusers = tusersListNewTusers.getMagasin();
                    tusersListNewTusers.setMagasin(magasin);
                    tusersListNewTusers = em.merge(tusersListNewTusers);
                    if (oldMagasinOfTusersListNewTusers != null && !oldMagasinOfTusersListNewTusers.equals(magasin)) {
                        oldMagasinOfTusersListNewTusers.getTusersList().remove(tusersListNewTusers);
                        oldMagasinOfTusersListNewTusers = em.merge(oldMagasinOfTusersListNewTusers);
                    }
                }
            }
            for (LigneTransfertStock lignetransfertstockListNewLignetransfertstock : lignetransfertstockListNew) {
                if (!lignetransfertstockListOld.contains(lignetransfertstockListNewLignetransfertstock)) {
                    Magasin oldMg1OfLignetransfertstockListNewLignetransfertstock = lignetransfertstockListNewLignetransfertstock.getMg1();
                    lignetransfertstockListNewLignetransfertstock.setMg1(magasin);
                    lignetransfertstockListNewLignetransfertstock = em.merge(lignetransfertstockListNewLignetransfertstock);
                    if (oldMg1OfLignetransfertstockListNewLignetransfertstock != null && !oldMg1OfLignetransfertstockListNewLignetransfertstock.equals(magasin)) {
                        oldMg1OfLignetransfertstockListNewLignetransfertstock.getLignetransfertstockList().remove(lignetransfertstockListNewLignetransfertstock);
                        oldMg1OfLignetransfertstockListNewLignetransfertstock = em.merge(oldMg1OfLignetransfertstockListNewLignetransfertstock);
                    }
                }
            }
            for (LigneTransfertStock lignetransfertstockList1NewLignetransfertstock : lignetransfertstockList1New) {
                if (!lignetransfertstockList1Old.contains(lignetransfertstockList1NewLignetransfertstock)) {
                    Magasin oldMg2OfLignetransfertstockList1NewLignetransfertstock = lignetransfertstockList1NewLignetransfertstock.getMg2();
                    lignetransfertstockList1NewLignetransfertstock.setMg2(magasin);
                    lignetransfertstockList1NewLignetransfertstock = em.merge(lignetransfertstockList1NewLignetransfertstock);
                    if (oldMg2OfLignetransfertstockList1NewLignetransfertstock != null && !oldMg2OfLignetransfertstockList1NewLignetransfertstock.equals(magasin)) {
                        oldMg2OfLignetransfertstockList1NewLignetransfertstock.getLignetransfertstockList1().remove(lignetransfertstockList1NewLignetransfertstock);
                        oldMg2OfLignetransfertstockList1NewLignetransfertstock = em.merge(oldMg2OfLignetransfertstockList1NewLignetransfertstock);
                    }
                }
            }
            for (Tourner tournerListOldTourner : tournerListOld) {
                if (!tournerListNew.contains(tournerListOldTourner)) {
                    tournerListOldTourner.setMagasin(null);
                    tournerListOldTourner = em.merge(tournerListOldTourner);
                }
            }
            for (Tourner tournerListNewTourner : tournerListNew) {
                if (!tournerListOld.contains(tournerListNewTourner)) {
                    Magasin oldMagasinOfTournerListNewTourner = tournerListNewTourner.getMagasin();
                    tournerListNewTourner.setMagasin(magasin);
                    tournerListNewTourner = em.merge(tournerListNewTourner);
                    if (oldMagasinOfTournerListNewTourner != null && !oldMagasinOfTournerListNewTourner.equals(magasin)) {
                        oldMagasinOfTournerListNewTourner.getTournerList().remove(tournerListNewTourner);
                        oldMagasinOfTournerListNewTourner = em.merge(oldMagasinOfTournerListNewTourner);
                    }
                }
            }
            for (StockMg stockMgListNewStockMg : stockMgListNew) {
                if (!stockMgListOld.contains(stockMgListNewStockMg)) {
                    Magasin oldMagasinOfStockMgListNewStockMg = stockMgListNewStockMg.getMagasin();
                    stockMgListNewStockMg.setMagasin(magasin);
                    stockMgListNewStockMg = em.merge(stockMgListNewStockMg);
                    if (oldMagasinOfStockMgListNewStockMg != null && !oldMagasinOfStockMgListNewStockMg.equals(magasin)) {
                        oldMagasinOfStockMgListNewStockMg.getStockMgList().remove(stockMgListNewStockMg);
                        oldMagasinOfStockMgListNewStockMg = em.merge(oldMagasinOfStockMgListNewStockMg);
                    }
                }
            }
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
        return magasin;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void Supprimer(Integer id) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            Magasin magasin;
            try {
                magasin = em.getReference(Magasin.class, id);
                magasin.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The magasin with id " + id + " no longer exists.", enfe);
            }
            Tusers magasigner = magasin.getMagasigner();
            if (magasigner != null) {
                magasigner.setMagasin(null);
                magasigner = em.merge(magasigner);
            }
            Societe societe = magasin.getSociete();
            if (societe != null) {
                societe.getMagasinList().remove(magasin);
                societe = em.merge(societe);
            }
            Tregions region = magasin.getRegion();
            if (region != null) {
                region.getMagasinList().remove(magasin);
                region = em.merge(region);
            }
            List<LigneInventaire> ligneInventaireList = magasin.getLigneInventaireList();
            for (LigneInventaire ligneInventaireListLigneInventaire : ligneInventaireList) {
                ligneInventaireListLigneInventaire.setMagasin(null);
                ligneInventaireListLigneInventaire = em.merge(ligneInventaireListLigneInventaire);
            }
            List<Tusers> tusersList = magasin.getTusersList();
            for (Tusers tusersListTusers : tusersList) {
                tusersListTusers.setMagasin(null);
                tusersListTusers = em.merge(tusersListTusers);
            }
            List<Tourner> tournerList = magasin.getTournerList();
            for (Tourner tournerListTourner : tournerList) {
                tournerListTourner.setMagasin(null);
                tournerListTourner = em.merge(tournerListTourner);
            }
            em.remove(magasin);
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @Override
    public List<Magasin> findMagasinEntities() {
        return findMagasinEntities(true, -1, -1);
    }

    @Override
    public List<Magasin> findMagasinEntities(int maxResults, int firstResult) {
        return findMagasinEntities(false, maxResults, firstResult);
    }

    private List<Magasin> findMagasinEntities(boolean all, int maxResults, int firstResult) {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Magasin.class));
        Query q = getEntityManager().createQuery(cq);
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    @Override
    public Magasin findMagasin(Integer id) {
        return getEntityManager().find(Magasin.class, id);
    }

    @Override
    public List<Magasin> findMagasinBySociete(Integer id) {
        Query q = getEntityManager().createNamedQuery("Magasin.findBySociete");
        q.setParameter("id", id);
        return q.getResultList();
    }

    @Override
    public List<Magasin> findMagasinByRegion(Integer id) {
        Query q = getEntityManager().createNamedQuery("Magasin.findByRegion");
        q.setParameter("id", id);
        return q.getResultList();
    }
}
