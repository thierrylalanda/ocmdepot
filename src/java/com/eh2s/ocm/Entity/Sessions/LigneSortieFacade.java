/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Fournisseur;
import com.eh2s.ocm.Entity.LigneCommandeFournisseur;
import com.eh2s.ocm.Entity.LigneSortie;
import com.eh2s.ocm.Entity.Societe;
import com.eh2s.ocm.Entity.Tusers;
import java.sql.Date;
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
public class LigneSortieFacade extends AbstractFacade<LigneSortie> implements LigneSortieFacadeLocal {

    @PersistenceContext(unitName = "ccmanagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LigneSortieFacade() {
        super(LigneSortie.class);
    }

    @Override
    public LigneSortie creer(LigneSortie ligneSortie) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            Tusers operateur = ligneSortie.getOperateur();
            Fournisseur fournisseur = ligneSortie.getFournisseur();
            if (operateur != null) {
                operateur = em.getReference(operateur.getClass(), operateur.getId());
                ligneSortie.setOperateur(operateur);
            }
            Societe societe = ligneSortie.getSociete();
            if (societe != null) {
                societe = em.getReference(societe.getClass(), societe.getId());
                ligneSortie.setSociete(societe);
            }
            LigneCommandeFournisseur numeroBon = ligneSortie.getNumeroBon();
            if (numeroBon != null) {
                numeroBon = em.getReference(numeroBon.getClass(), numeroBon.getId());
                ligneSortie.setNumeroBon(numeroBon);
            }
            em.persist(ligneSortie);
            if (operateur != null) {
                operateur.getLigneSortieList().add(ligneSortie);
                operateur = em.merge(operateur);
            }
            if (fournisseur != null) {
                fournisseur.getLigneSortieList().add(ligneSortie);
                fournisseur = em.merge(fournisseur);
            }
            if (societe != null) {
                societe.getLigneSortieList().add(ligneSortie);
                societe = em.merge(societe);
            }
            if (numeroBon != null) {
                numeroBon.getLigneSortieList().add(ligneSortie);
                numeroBon = em.merge(numeroBon);
            }
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
        return ligneSortie;
    }

    @Override
    public LigneSortie MisAJour(LigneSortie ligneSortie) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            LigneSortie persistentLigneSortie = em.find(LigneSortie.class, ligneSortie.getId());
            Tusers operateurOld = persistentLigneSortie.getOperateur();
            Tusers operateurNew = ligneSortie.getOperateur();
            Fournisseur fournisseurOld = persistentLigneSortie.getFournisseur();
            Fournisseur fournisseurNew = ligneSortie.getFournisseur();
            Societe societeOld = persistentLigneSortie.getSociete();
            Societe societeNew = ligneSortie.getSociete();
            LigneCommandeFournisseur numeroBonOld = persistentLigneSortie.getNumeroBon();
            LigneCommandeFournisseur numeroBonNew = ligneSortie.getNumeroBon();
            if (operateurNew != null) {
                operateurNew = em.getReference(operateurNew.getClass(), operateurNew.getId());
                ligneSortie.setOperateur(operateurNew);
            }
            if (fournisseurNew != null) {
                fournisseurNew = em.getReference(fournisseurNew.getClass(), fournisseurNew.getId());
                ligneSortie.setFournisseur(fournisseurNew);
            }
            if (societeNew != null) {
                societeNew = em.getReference(societeNew.getClass(), societeNew.getId());
                ligneSortie.setSociete(societeNew);
            }
            if (numeroBonNew != null) {
                numeroBonNew = em.getReference(numeroBonNew.getClass(), numeroBonNew.getId());
                ligneSortie.setNumeroBon(numeroBonNew);
            }
            ligneSortie = em.merge(ligneSortie);
            if (operateurOld != null && !operateurOld.equals(operateurNew)) {
                operateurOld.getLigneSortieList().remove(ligneSortie);
                operateurOld = em.merge(operateurOld);
            }
            if (operateurNew != null && !operateurNew.equals(operateurOld)) {
                operateurNew.getLigneSortieList().add(ligneSortie);
                operateurNew = em.merge(operateurNew);
            }
            if (fournisseurOld != null && !fournisseurOld.equals(fournisseurNew)) {
                fournisseurOld.getLigneSortieList().remove(ligneSortie);
                fournisseurOld = em.merge(fournisseurOld);
            }
            if (fournisseurNew != null && !fournisseurNew.equals(fournisseurOld)) {
                fournisseurNew.getLigneSortieList().add(ligneSortie);
                fournisseurNew = em.merge(fournisseurNew);
            }
            if (societeOld != null && !societeOld.equals(societeNew)) {
                societeOld.getLigneSortieList().remove(ligneSortie);
                societeOld = em.merge(societeOld);
            }
            if (societeNew != null && !societeNew.equals(societeOld)) {
                societeNew.getLigneSortieList().add(ligneSortie);
                societeNew = em.merge(societeNew);
            }
            if (numeroBonOld != null && !numeroBonOld.equals(numeroBonNew)) {
                numeroBonOld.getLigneSortieList().remove(ligneSortie);
                numeroBonOld = em.merge(numeroBonOld);
            }
            if (numeroBonNew != null && !numeroBonNew.equals(numeroBonOld)) {
                numeroBonNew.getLigneSortieList().add(ligneSortie);
                numeroBonNew = em.merge(numeroBonNew);
            }
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
        return ligneSortie;
    }

    @Override
    public void Supprimer(Integer id) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            LigneSortie ligneSortie;
            try {
                ligneSortie = em.getReference(LigneSortie.class, id);
                ligneSortie.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ligneSortie with id " + id + " no longer exists.", enfe);
            }
            Tusers operateur = ligneSortie.getOperateur();
            if (operateur != null) {
                operateur.getLigneSortieList().remove(ligneSortie);
                operateur = em.merge(operateur);
            }
            Fournisseur fournisseur = ligneSortie.getFournisseur();
            if (fournisseur != null) {
                fournisseur.getLigneSortieList().remove(ligneSortie);
                fournisseur = em.merge(fournisseur);
            }
            Societe societe = ligneSortie.getSociete();
            if (societe != null) {
                societe.getLigneSortieList().remove(ligneSortie);
                societe = em.merge(societe);
            }
            LigneCommandeFournisseur numeroBon = ligneSortie.getNumeroBon();
            if (numeroBon != null) {
                numeroBon.getLigneSortieList().remove(ligneSortie);
                numeroBon = em.merge(numeroBon);
            }
            em.remove(ligneSortie);
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @Override
    public List<LigneSortie> findAll(int societe) {
        Query q = getEntityManager().createNamedQuery("LigneSortie.findBySociete");
        q.setParameter("societe", societe);
        return q.getResultList();
    }

    @Override
    public List<LigneSortie> findByFournisseur(int fournisseur) {
        Query q = getEntityManager().createNamedQuery("LigneSortie.findByFournisseur");
        q.setParameter("fournisseur", fournisseur);
        return q.getResultList();
    }

    @Override
    public List<LigneSortie> findByPeriodeSociete(Date debut, Date fin, int societe) {
        Query q = getEntityManager().createNamedQuery("LigneSortie.findByPeriodeSociete");
        q.setParameter("d", debut);
        q.setParameter("d1", fin);
        q.setParameter("societe", societe);
        return q.getResultList();
    }

    @Override
    public List<LigneSortie> historiqueSortie(Date debut, Date fin, Integer type, Integer entite, Integer etat) {
        if (etat != -1) {
            if (type == 1) {
                Query q = getEntityManager().createNamedQuery("LigneSortie.findByPeriodeFournisseurAndEtat");
                q.setParameter("d", debut);
                q.setParameter("d1", fin);
                q.setParameter("etat", etat);
                q.setParameter("fourniseur", entite);
                return q.getResultList();
            } else {
                Query q = getEntityManager().createNamedQuery("LigneSortie.findByPeriodeSocieteAndEtat");
                q.setParameter("d", debut);
                q.setParameter("d1", fin);
                q.setParameter("etat", etat);
                q.setParameter("societe", entite);
                return q.getResultList();
            }
        } else {
            if (type == 1) {
                Query q = getEntityManager().createNamedQuery("LigneSortie.findByPeriodeFournisseur");
                q.setParameter("d", debut);
                q.setParameter("d1", fin);
                q.setParameter("fourniseur", entite);
                return q.getResultList();
            } else {
                Query q = getEntityManager().createNamedQuery("LigneSortie.findByPeriodeSociete");
                q.setParameter("d", debut);
                q.setParameter("d1", fin);
                q.setParameter("societe", entite);
                return q.getResultList();
            }
        }
    }

}
