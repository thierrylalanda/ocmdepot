/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.CommandeFournisseur;
import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Fournisseur;
import com.eh2s.ocm.Entity.LigneCommandeFournisseur;
import com.eh2s.ocm.Entity.LigneSortie;
import com.eh2s.ocm.Entity.SortieCaisse;
import com.eh2s.ocm.Entity.Tetatc;
import com.eh2s.ocm.Entity.Tusers;
import java.sql.Date;
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

/**
 *
 * @author Administrateur
 */
@Stateless
public class LigneCommandeFournisseurFacade extends AbstractFacade<LigneCommandeFournisseur> implements LigneCommandeFournisseurFacadeLocal {

    @PersistenceContext(unitName = "ccmanagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LigneCommandeFournisseurFacade() {
        super(LigneCommandeFournisseur.class);
    }

    @Override
    public List<LigneCommandeFournisseur> findAll(int societe) {

        Query q = getEntityManager().createNamedQuery("LigneCommandeFournisseur.findBySociete");
        q.setParameter("societe", societe);
        return q.getResultList();
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public LigneCommandeFournisseur creer(LigneCommandeFournisseur ligneCommandeFournisseur) throws RollbackFailureException, Exception {
        if (ligneCommandeFournisseur.getCommandeFournisseurList() == null) {
            ligneCommandeFournisseur.setCommandeFournisseurList(new ArrayList<CommandeFournisseur>());
        }
        if (ligneCommandeFournisseur.getSortieCaisseList() == null) {
            ligneCommandeFournisseur.setSortieCaisseList(new ArrayList<SortieCaisse>());
        }
        try {
            Tusers operateur = ligneCommandeFournisseur.getOperateur();
            if (operateur != null) {
                operateur = getEntityManager().getReference(operateur.getClass(), operateur.getId());
                ligneCommandeFournisseur.setOperateur(operateur);
            }
            Tetatc etat = ligneCommandeFournisseur.getEtat();
            if (etat != null) {
                etat = getEntityManager().getReference(etat.getClass(), etat.getId());
                ligneCommandeFournisseur.setEtat(etat);
            }
            Fournisseur fournisseur = ligneCommandeFournisseur.getFournisseur();
            if (fournisseur != null) {
                fournisseur = getEntityManager().getReference(fournisseur.getClass(), fournisseur.getId());
                ligneCommandeFournisseur.setFournisseur(fournisseur);
            }
            List<CommandeFournisseur> attachedCommandeFournisseurList = new ArrayList<CommandeFournisseur>();
            for (CommandeFournisseur commandeFournisseurListCommandeFournisseurToAttach : ligneCommandeFournisseur.getCommandeFournisseurList()) {
                commandeFournisseurListCommandeFournisseurToAttach = getEntityManager().getReference(commandeFournisseurListCommandeFournisseurToAttach.getClass(), commandeFournisseurListCommandeFournisseurToAttach.getId());
                attachedCommandeFournisseurList.add(commandeFournisseurListCommandeFournisseurToAttach);
            }
            ligneCommandeFournisseur.setCommandeFournisseurList(attachedCommandeFournisseurList);
            List<SortieCaisse> attachedSortieCaisseList = new ArrayList<SortieCaisse>();
            for (SortieCaisse sortieCaisseListSortieCaisseToAttach : ligneCommandeFournisseur.getSortieCaisseList()) {
                sortieCaisseListSortieCaisseToAttach = getEntityManager().getReference(sortieCaisseListSortieCaisseToAttach.getClass(), sortieCaisseListSortieCaisseToAttach.getId());
                attachedSortieCaisseList.add(sortieCaisseListSortieCaisseToAttach);
            }
            ligneCommandeFournisseur.setSortieCaisseList(attachedSortieCaisseList);
            getEntityManager().persist(ligneCommandeFournisseur);
            if (operateur != null) {
                operateur.getLigneCommandeFournisseurList().add(ligneCommandeFournisseur);
                operateur = getEntityManager().merge(operateur);
            }
            if (etat != null) {
                etat.getLigneCommandeFournisseurList().add(ligneCommandeFournisseur);
                etat = getEntityManager().merge(etat);
            }
            if (fournisseur != null) {
                fournisseur.getLigneCommandeFournisseurList().add(ligneCommandeFournisseur);
                fournisseur = getEntityManager().merge(fournisseur);
            }
            for (CommandeFournisseur commandeFournisseurListCommandeFournisseur : ligneCommandeFournisseur.getCommandeFournisseurList()) {
                LigneCommandeFournisseur oldLigneCommandeFournisseurOfCommandeFournisseurListCommandeFournisseur = commandeFournisseurListCommandeFournisseur.getLigneCommandeFournisseur();
                commandeFournisseurListCommandeFournisseur.setLigneCommandeFournisseur(ligneCommandeFournisseur);
                commandeFournisseurListCommandeFournisseur = getEntityManager().merge(commandeFournisseurListCommandeFournisseur);
                if (oldLigneCommandeFournisseurOfCommandeFournisseurListCommandeFournisseur != null) {
                    oldLigneCommandeFournisseurOfCommandeFournisseurListCommandeFournisseur.getCommandeFournisseurList().remove(commandeFournisseurListCommandeFournisseur);
                    oldLigneCommandeFournisseurOfCommandeFournisseurListCommandeFournisseur = getEntityManager().merge(oldLigneCommandeFournisseurOfCommandeFournisseurListCommandeFournisseur);
                }
            }
            for (SortieCaisse sortieCaisseListSortieCaisse : ligneCommandeFournisseur.getSortieCaisseList()) {
                LigneCommandeFournisseur oldFactureOfSortieCaisseListSortieCaisse = sortieCaisseListSortieCaisse.getFacture();
                sortieCaisseListSortieCaisse.setFacture(ligneCommandeFournisseur);
                sortieCaisseListSortieCaisse = getEntityManager().merge(sortieCaisseListSortieCaisse);
                if (oldFactureOfSortieCaisseListSortieCaisse != null) {
                    oldFactureOfSortieCaisseListSortieCaisse.getSortieCaisseList().remove(sortieCaisseListSortieCaisse);
                    oldFactureOfSortieCaisseListSortieCaisse = getEntityManager().merge(oldFactureOfSortieCaisseListSortieCaisse);
                }
            }
            for (LigneSortie ligneSortieListLigneSortie : ligneCommandeFournisseur.getLigneSortieList()) {
                LigneCommandeFournisseur oldNumeroBonOfLigneSortieListLigneSortie = ligneSortieListLigneSortie.getNumeroBon();
                ligneSortieListLigneSortie.setNumeroBon(ligneCommandeFournisseur);
                ligneSortieListLigneSortie = em.merge(ligneSortieListLigneSortie);
                if (oldNumeroBonOfLigneSortieListLigneSortie != null) {
                    oldNumeroBonOfLigneSortieListLigneSortie.getLigneSortieList().remove(ligneSortieListLigneSortie);
                    oldNumeroBonOfLigneSortieListLigneSortie = em.merge(oldNumeroBonOfLigneSortieListLigneSortie);
                }
            }
            return ligneCommandeFournisseur;
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @Override
    public LigneCommandeFournisseur MisAJour(LigneCommandeFournisseur ligneCommandeFournisseur) throws RollbackFailureException, Exception {
        try {
            LigneCommandeFournisseur persistentLigneCommandeFournisseur = getEntityManager().find(LigneCommandeFournisseur.class, ligneCommandeFournisseur.getId());
            Tusers operateurOld = persistentLigneCommandeFournisseur.getOperateur();
            Tusers operateurNew = ligneCommandeFournisseur.getOperateur();
            Tetatc etatOld = persistentLigneCommandeFournisseur.getEtat();
            Tetatc etatNew = ligneCommandeFournisseur.getEtat();
            Fournisseur fournisseurOld = persistentLigneCommandeFournisseur.getFournisseur();
            Fournisseur fournisseurNew = ligneCommandeFournisseur.getFournisseur();
            List<CommandeFournisseur> commandeFournisseurListOld = persistentLigneCommandeFournisseur.getCommandeFournisseurList();
            List<CommandeFournisseur> commandeFournisseurListNew = ligneCommandeFournisseur.getCommandeFournisseurList();
            List<SortieCaisse> sortieCaisseListOld = persistentLigneCommandeFournisseur.getSortieCaisseList();
            List<SortieCaisse> sortieCaisseListNew = ligneCommandeFournisseur.getSortieCaisseList();
            List<LigneSortie> ligneSortieListOld = persistentLigneCommandeFournisseur.getLigneSortieList();
            List<LigneSortie> ligneSortieListNew = ligneCommandeFournisseur.getLigneSortieList();
            if (operateurNew != null) {
                operateurNew = getEntityManager().getReference(operateurNew.getClass(), operateurNew.getId());
                ligneCommandeFournisseur.setOperateur(operateurNew);
            }
            if (etatNew != null) {
                etatNew = getEntityManager().getReference(etatNew.getClass(), etatNew.getId());
                ligneCommandeFournisseur.setEtat(etatNew);
            }
            if (fournisseurNew != null) {
                fournisseurNew = getEntityManager().getReference(fournisseurNew.getClass(), fournisseurNew.getId());
                ligneCommandeFournisseur.setFournisseur(fournisseurNew);
            }
            List<CommandeFournisseur> attachedCommandeFournisseurListNew = new ArrayList<CommandeFournisseur>();
            for (CommandeFournisseur commandeFournisseurListNewCommandeFournisseurToAttach : commandeFournisseurListNew) {
                commandeFournisseurListNewCommandeFournisseurToAttach = getEntityManager().getReference(commandeFournisseurListNewCommandeFournisseurToAttach.getClass(), commandeFournisseurListNewCommandeFournisseurToAttach.getId());
                attachedCommandeFournisseurListNew.add(commandeFournisseurListNewCommandeFournisseurToAttach);
            }
            commandeFournisseurListNew = attachedCommandeFournisseurListNew;
            ligneCommandeFournisseur.setCommandeFournisseurList(commandeFournisseurListNew);
            List<SortieCaisse> attachedSortieCaisseListNew = new ArrayList<SortieCaisse>();
            for (SortieCaisse sortieCaisseListNewSortieCaisseToAttach : sortieCaisseListNew) {
                sortieCaisseListNewSortieCaisseToAttach = getEntityManager().getReference(sortieCaisseListNewSortieCaisseToAttach.getClass(), sortieCaisseListNewSortieCaisseToAttach.getId());
                attachedSortieCaisseListNew.add(sortieCaisseListNewSortieCaisseToAttach);
            }
            sortieCaisseListNew = attachedSortieCaisseListNew;
            ligneCommandeFournisseur.setSortieCaisseList(sortieCaisseListNew);
            List<LigneSortie> attachedLigneSortieListNew = new ArrayList<LigneSortie>();
            for (LigneSortie ligneSortieListNewLigneSortieToAttach : ligneSortieListNew) {
                ligneSortieListNewLigneSortieToAttach = getEntityManager().getReference(ligneSortieListNewLigneSortieToAttach.getClass(), ligneSortieListNewLigneSortieToAttach.getId());
                attachedLigneSortieListNew.add(ligneSortieListNewLigneSortieToAttach);
            }
            ligneSortieListNew = attachedLigneSortieListNew;
            ligneCommandeFournisseur.setLigneSortieList(ligneSortieListNew);
            ligneCommandeFournisseur = getEntityManager().merge(ligneCommandeFournisseur);
            if (operateurOld != null && !operateurOld.equals(operateurNew)) {
                operateurOld.getLigneCommandeFournisseurList().remove(ligneCommandeFournisseur);
                operateurOld = getEntityManager().merge(operateurOld);
            }
            if (operateurNew != null && !operateurNew.equals(operateurOld)) {
                operateurNew.getLigneCommandeFournisseurList().add(ligneCommandeFournisseur);
                operateurNew = getEntityManager().merge(operateurNew);
            }
            if (etatOld != null && !etatOld.equals(etatNew)) {
                etatOld.getLigneCommandeFournisseurList().remove(ligneCommandeFournisseur);
                etatOld = getEntityManager().merge(etatOld);
            }
            if (etatNew != null && !etatNew.equals(etatOld)) {
                etatNew.getLigneCommandeFournisseurList().add(ligneCommandeFournisseur);
                etatNew = getEntityManager().merge(etatNew);
            }
            if (fournisseurOld != null && !fournisseurOld.equals(fournisseurNew)) {
                fournisseurOld.getLigneCommandeFournisseurList().remove(ligneCommandeFournisseur);
                fournisseurOld = getEntityManager().merge(fournisseurOld);
            }
            if (fournisseurNew != null && !fournisseurNew.equals(fournisseurOld)) {
                fournisseurNew.getLigneCommandeFournisseurList().add(ligneCommandeFournisseur);
                fournisseurNew = getEntityManager().merge(fournisseurNew);
            }
            for (CommandeFournisseur commandeFournisseurListNewCommandeFournisseur : commandeFournisseurListNew) {
                if (!commandeFournisseurListOld.contains(commandeFournisseurListNewCommandeFournisseur)) {
                    LigneCommandeFournisseur oldLigneCommandeFournisseurOfCommandeFournisseurListNewCommandeFournisseur = commandeFournisseurListNewCommandeFournisseur.getLigneCommandeFournisseur();
                    commandeFournisseurListNewCommandeFournisseur.setLigneCommandeFournisseur(ligneCommandeFournisseur);
                    commandeFournisseurListNewCommandeFournisseur = getEntityManager().merge(commandeFournisseurListNewCommandeFournisseur);
                    if (oldLigneCommandeFournisseurOfCommandeFournisseurListNewCommandeFournisseur != null && !oldLigneCommandeFournisseurOfCommandeFournisseurListNewCommandeFournisseur.equals(ligneCommandeFournisseur)) {
                        oldLigneCommandeFournisseurOfCommandeFournisseurListNewCommandeFournisseur.getCommandeFournisseurList().remove(commandeFournisseurListNewCommandeFournisseur);
                        oldLigneCommandeFournisseurOfCommandeFournisseurListNewCommandeFournisseur = getEntityManager().merge(oldLigneCommandeFournisseurOfCommandeFournisseurListNewCommandeFournisseur);
                    }
                }
            }
            for (SortieCaisse sortieCaisseListOldSortieCaisse : sortieCaisseListOld) {
                if (!sortieCaisseListNew.contains(sortieCaisseListOldSortieCaisse)) {
                    sortieCaisseListOldSortieCaisse.setFacture(null);
                    sortieCaisseListOldSortieCaisse = getEntityManager().merge(sortieCaisseListOldSortieCaisse);
                }
            }
            for (SortieCaisse sortieCaisseListNewSortieCaisse : sortieCaisseListNew) {
                if (!sortieCaisseListOld.contains(sortieCaisseListNewSortieCaisse)) {
                    LigneCommandeFournisseur oldFactureOfSortieCaisseListNewSortieCaisse = sortieCaisseListNewSortieCaisse.getFacture();
                    sortieCaisseListNewSortieCaisse.setFacture(ligneCommandeFournisseur);
                    sortieCaisseListNewSortieCaisse = getEntityManager().merge(sortieCaisseListNewSortieCaisse);
                    if (oldFactureOfSortieCaisseListNewSortieCaisse != null && !oldFactureOfSortieCaisseListNewSortieCaisse.equals(ligneCommandeFournisseur)) {
                        oldFactureOfSortieCaisseListNewSortieCaisse.getSortieCaisseList().remove(sortieCaisseListNewSortieCaisse);
                        oldFactureOfSortieCaisseListNewSortieCaisse = getEntityManager().merge(oldFactureOfSortieCaisseListNewSortieCaisse);
                    }
                }
            }
            for (LigneSortie ligneSortieListOldLigneSortie : ligneSortieListOld) {
                if (!ligneSortieListNew.contains(ligneSortieListOldLigneSortie)) {
                    ligneSortieListOldLigneSortie.setNumeroBon(null);
                    ligneSortieListOldLigneSortie = em.merge(ligneSortieListOldLigneSortie);
                }
            }
            for (LigneSortie ligneSortieListNewLigneSortie : ligneSortieListNew) {
                if (!ligneSortieListOld.contains(ligneSortieListNewLigneSortie)) {
                    LigneCommandeFournisseur oldNumeroBonOfLigneSortieListNewLigneSortie = ligneSortieListNewLigneSortie.getNumeroBon();
                    ligneSortieListNewLigneSortie.setNumeroBon(ligneCommandeFournisseur);
                    ligneSortieListNewLigneSortie = em.merge(ligneSortieListNewLigneSortie);
                    if (oldNumeroBonOfLigneSortieListNewLigneSortie != null && !oldNumeroBonOfLigneSortieListNewLigneSortie.equals(ligneCommandeFournisseur)) {
                        oldNumeroBonOfLigneSortieListNewLigneSortie.getLigneSortieList().remove(ligneSortieListNewLigneSortie);
                        oldNumeroBonOfLigneSortieListNewLigneSortie = em.merge(oldNumeroBonOfLigneSortieListNewLigneSortie);
                    }
                }
            }
            return ligneCommandeFournisseur;
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @Override
    public void Supprimer(Integer id) throws RollbackFailureException, Exception {
        try {
            LigneCommandeFournisseur ligneCommandeFournisseur;
            try {
                ligneCommandeFournisseur = getEntityManager().getReference(LigneCommandeFournisseur.class, id);
                ligneCommandeFournisseur.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ligneCommandeFournisseur with id " + id + " no longer exists.", enfe);
            }
            Tusers operateur = ligneCommandeFournisseur.getOperateur();
            if (operateur != null) {
                operateur.getLigneCommandeFournisseurList().remove(ligneCommandeFournisseur);
                operateur = getEntityManager().merge(operateur);
            }
            Tetatc etat = ligneCommandeFournisseur.getEtat();
            if (etat != null) {
                etat.getLigneCommandeFournisseurList().remove(ligneCommandeFournisseur);
                etat = getEntityManager().merge(etat);
            }
            Fournisseur fournisseur = ligneCommandeFournisseur.getFournisseur();
            if (fournisseur != null) {
                fournisseur.getLigneCommandeFournisseurList().remove(ligneCommandeFournisseur);
                fournisseur = getEntityManager().merge(fournisseur);
            }
            List<SortieCaisse> sortieCaisseList = ligneCommandeFournisseur.getSortieCaisseList();
            for (SortieCaisse sortieCaisseListSortieCaisse : sortieCaisseList) {
                sortieCaisseListSortieCaisse.setFacture(null);
                sortieCaisseListSortieCaisse = getEntityManager().merge(sortieCaisseListSortieCaisse);
            }
            List<LigneSortie> ligneSortieList = ligneCommandeFournisseur.getLigneSortieList();
            for (LigneSortie ligneSortieListLigneSortie : ligneSortieList) {
                ligneSortieListLigneSortie.setNumeroBon(null);
                ligneSortieListLigneSortie = em.merge(ligneSortieListLigneSortie);
            }
            getEntityManager().remove(ligneCommandeFournisseur);
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @Override
    public List<LigneCommandeFournisseur> findBonCommandeByPeriode(Date d, Date d1, int societe) {
        getEntityManager().setProperty("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        Query q = getEntityManager().createNamedQuery("LigneCommandeFournisseur.findByPeriodeBySociete");
        q.setParameter("d1", d);
        q.setParameter("d2", d1);
        q.setParameter("societe", societe);
        return q.getResultList();
    }

    @Override
    public List<LigneCommandeFournisseur> findBonCommandeByFournisseurByPeriode(Date d, Date d1, int fournisseur) {
        getEntityManager().setProperty("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        Query q = getEntityManager().createNamedQuery("LigneCommandeFournisseur.findByPeriodeByFournisseur");
        q.setParameter("d1", d);
        q.setParameter("d2", d1);
        q.setParameter("fournisseur", fournisseur);
        return q.getResultList();
    }

}
