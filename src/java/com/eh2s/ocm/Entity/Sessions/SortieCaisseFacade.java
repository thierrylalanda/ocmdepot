/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Caisse;
import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.LigneCommandeFournisseur;
import com.eh2s.ocm.Entity.LigneSortie;
import com.eh2s.ocm.Entity.SortieCaisse;
import com.eh2s.ocm.Entity.SourceMouvementCaisse;
import com.eh2s.ocm.Entity.Tusers;
import java.sql.Date;
import java.util.List;
import javax.ejb.Stateless;
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
public class SortieCaisseFacade extends AbstractFacade<SortieCaisse> implements SortieCaisseFacadeLocal {

    @PersistenceContext(unitName = "ccmanagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SortieCaisseFacade() {
        super(SortieCaisse.class);
    }

    @Override
    public List<SortieCaisse> findAll(int societe) {
        Query q = getEntityManager().createNamedQuery("SortieCaisse.findBySociete");
        q.setParameter("societe", societe);
        return q.getResultList();
    }

    @Override
    public SortieCaisse creer(SortieCaisse sortieCaisse) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            Caisse caisse = sortieCaisse.getCaisse();
            if (caisse != null) {
                caisse = em.getReference(caisse.getClass(), caisse.getId());
                sortieCaisse.setCaisse(caisse);
            }
            LigneCommandeFournisseur facture = sortieCaisse.getFacture();
            if (facture != null) {
                facture = em.getReference(facture.getClass(), facture.getId());
                sortieCaisse.setFacture(facture);
            }
            Tusers operateur = sortieCaisse.getOperateur();
            if (operateur != null) {
                operateur = em.getReference(operateur.getClass(), operateur.getId());
                sortieCaisse.setOperateur(operateur);
            }
            SourceMouvementCaisse source = sortieCaisse.getSource();
            if (source != null) {
                source = em.getReference(source.getClass(), source.getId());
                sortieCaisse.setSource(source);
            }
            LigneSortie ligneSortie = sortieCaisse.getLigneSortie();
            if (ligneSortie != null) {
                ligneSortie = getEntityManager().getReference(ligneSortie.getClass(), ligneSortie.getId());
                sortieCaisse.setLigneSortie(ligneSortie);
            }
            em.persist(sortieCaisse);
            if (caisse != null) {
                caisse.getSortieCaisseList().add(sortieCaisse);
                caisse = em.merge(caisse);
            }
            if (facture != null) {
                facture.getSortieCaisseList().add(sortieCaisse);
                facture = em.merge(facture);
            }
            if (operateur != null) {
                operateur.getSortieCaisseList().add(sortieCaisse);
                operateur = em.merge(operateur);
            }
            if (source != null) {
                source.getSortieCaisseList().add(sortieCaisse);
                source = em.merge(source);
            }
            if (ligneSortie != null) {
                ligneSortie.getSortietList().add(sortieCaisse);
                ligneSortie = getEntityManager().merge(ligneSortie);
            }
            return sortieCaisse;
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @Override
    public SortieCaisse MisAJour(SortieCaisse sortieCaisse) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            SortieCaisse persistentSortieCaisse = em.find(SortieCaisse.class, sortieCaisse.getId());
            Caisse caisseOld = persistentSortieCaisse.getCaisse();
            Caisse caisseNew = sortieCaisse.getCaisse();
            LigneCommandeFournisseur factureOld = persistentSortieCaisse.getFacture();
            LigneCommandeFournisseur factureNew = sortieCaisse.getFacture();
            Tusers operateurOld = persistentSortieCaisse.getOperateur();
            Tusers operateurNew = sortieCaisse.getOperateur();
            SourceMouvementCaisse sourceOld = persistentSortieCaisse.getSource();
            SourceMouvementCaisse sourceNew = sortieCaisse.getSource();
            LigneSortie ligneOld = persistentSortieCaisse.getLigneSortie();
            LigneSortie ligneNew = sortieCaisse.getLigneSortie();
            if (ligneNew != null) {
                ligneNew = getEntityManager().getReference(ligneNew.getClass(), ligneNew.getId());
                sortieCaisse.setLigneSortie(ligneNew);
            }
            if (caisseNew != null) {
                caisseNew = em.getReference(caisseNew.getClass(), caisseNew.getId());
                sortieCaisse.setCaisse(caisseNew);
            }
            if (factureNew != null) {
                factureNew = em.getReference(factureNew.getClass(), factureNew.getId());
                sortieCaisse.setFacture(factureNew);
            }
            if (operateurNew != null) {
                operateurNew = em.getReference(operateurNew.getClass(), operateurNew.getId());
                sortieCaisse.setOperateur(operateurNew);
            }
            if (sourceNew != null) {
                sourceNew = em.getReference(sourceNew.getClass(), sourceNew.getId());
                sortieCaisse.setSource(sourceNew);
            }
            sortieCaisse = em.merge(sortieCaisse);
            if (ligneNew != null && !ligneNew.equals(ligneOld)) {
                ligneNew.getSortietList().add(sortieCaisse);
                ligneNew = getEntityManager().merge(ligneNew);
            }
            if (caisseOld != null && !caisseOld.equals(caisseNew)) {
                caisseOld.getSortieCaisseList().remove(sortieCaisse);
                caisseOld = em.merge(caisseOld);
            }
            if (caisseNew != null && !caisseNew.equals(caisseOld)) {
                caisseNew.getSortieCaisseList().add(sortieCaisse);
                caisseNew = em.merge(caisseNew);
            }
            if (factureOld != null && !factureOld.equals(factureNew)) {
                factureOld.getSortieCaisseList().remove(sortieCaisse);
                factureOld = em.merge(factureOld);
            }
            if (factureNew != null && !factureNew.equals(factureOld)) {
                factureNew.getSortieCaisseList().add(sortieCaisse);
                factureNew = em.merge(factureNew);
            }
            if (operateurOld != null && !operateurOld.equals(operateurNew)) {
                operateurOld.getSortieCaisseList().remove(sortieCaisse);
                operateurOld = em.merge(operateurOld);
            }
            if (operateurNew != null && !operateurNew.equals(operateurOld)) {
                operateurNew.getSortieCaisseList().add(sortieCaisse);
                operateurNew = em.merge(operateurNew);
            }
            if (sourceOld != null && !sourceOld.equals(sourceNew)) {
                sourceOld.getSortieCaisseList().remove(sortieCaisse);
                sourceOld = em.merge(sourceOld);
            }
            if (sourceNew != null && !sourceNew.equals(sourceOld)) {
                sourceNew.getSortieCaisseList().add(sortieCaisse);
                sourceNew = em.merge(sourceNew);
            }
            return sortieCaisse;
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @Override
    public void Supprimer(Integer id) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            SortieCaisse sortieCaisse;
            try {
                sortieCaisse = em.getReference(SortieCaisse.class, id);
                sortieCaisse.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sortieCaisse with id " + id + " no longer exists.", enfe);
            }
            Caisse caisse = sortieCaisse.getCaisse();
            if (caisse != null) {
                caisse.getSortieCaisseList().remove(sortieCaisse);
                caisse = em.merge(caisse);
            }
            LigneCommandeFournisseur facture = sortieCaisse.getFacture();
            if (facture != null) {
                facture.getSortieCaisseList().remove(sortieCaisse);
                facture = em.merge(facture);
            }
            Tusers operateur = sortieCaisse.getOperateur();
            if (operateur != null) {
                operateur.getSortieCaisseList().remove(sortieCaisse);
                operateur = em.merge(operateur);
            }
            SourceMouvementCaisse source = sortieCaisse.getSource();
            if (source != null) {
                source.getSortieCaisseList().remove(sortieCaisse);
                source = em.merge(source);
            }
            LigneSortie ligne = sortieCaisse.getLigneSortie();
            if (ligne != null) {
                ligne.getSortietList().remove(sortieCaisse);
                sortieCaisse = getEntityManager().merge(sortieCaisse);
            }
            em.remove(sortieCaisse);
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    public List<SortieCaisse> findSortieCaisseEntities() {
        return findSortieCaisseEntities(true, -1, -1);
    }

    public List<SortieCaisse> findSortieCaisseEntities(int maxResults, int firstResult) {
        return findSortieCaisseEntities(false, maxResults, firstResult);
    }

    private List<SortieCaisse> findSortieCaisseEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SortieCaisse.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<SortieCaisse> findAllToDay(int societe) {
        Query q = getEntityManager().createNamedQuery("SortieCaisse.findSortieSocieteToDay");
        q.setParameter("societe", societe);
        return q.getResultList();
    }

    @Override
    public List<SortieCaisse> findAllByPeriode(Date d, Date d1, int societe) {
        Query q = getEntityManager().createNamedQuery("SortieCaisse.findSortieSocieteByPeriode");
        q.setParameter("societe", societe);
        q.setParameter("d", d);
        q.setParameter("d1", d1);
        return q.getResultList();
    }

}
