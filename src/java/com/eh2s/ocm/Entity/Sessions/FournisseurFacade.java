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
import com.eh2s.ocm.Entity.Societe;
import com.eh2s.ocm.Entity.Tarticles;
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
public class FournisseurFacade extends AbstractFacade<Fournisseur> implements FournisseurFacadeLocal {

    @PersistenceContext(unitName = "ccmanagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FournisseurFacade() {
        super(Fournisseur.class);
    }

    @Override
    public List<Fournisseur> findFournisseurBySociete(int societe) {
        Query q = getEntityManager().createNamedQuery("Fournisseur.findBySociete");
        q.setParameter("societe", societe);
        return q.getResultList();
    }

    @Override
    public Fournisseur creer(Fournisseur fournisseur) throws RollbackFailureException, Exception {
     if (fournisseur.getLigneCommandeFournisseurList() == null) {
            fournisseur.setLigneCommandeFournisseurList(new ArrayList<LigneCommandeFournisseur>());
        }
        if (fournisseur.getTarticlesList() == null) {
            fournisseur.setTarticlesList(new ArrayList<Tarticles>());
        }
        try {
            Societe societe = fournisseur.getSociete();
            if (societe != null) {
                societe = getEntityManager().getReference(societe.getClass(), societe.getId());
                fournisseur.setSociete(societe);
            }
            List<LigneCommandeFournisseur> attachedLigneCommandeFournisseurList = new ArrayList<LigneCommandeFournisseur>();
            for (LigneCommandeFournisseur ligneCommandeFournisseurListLigneCommandeFournisseurToAttach : fournisseur.getLigneCommandeFournisseurList()) {
                ligneCommandeFournisseurListLigneCommandeFournisseurToAttach = getEntityManager().getReference(ligneCommandeFournisseurListLigneCommandeFournisseurToAttach.getClass(), ligneCommandeFournisseurListLigneCommandeFournisseurToAttach.getId());
                attachedLigneCommandeFournisseurList.add(ligneCommandeFournisseurListLigneCommandeFournisseurToAttach);
            }
            fournisseur.setLigneCommandeFournisseurList(attachedLigneCommandeFournisseurList);
            List<Tarticles> attachedTarticlesList = new ArrayList<Tarticles>();
            for (Tarticles tarticlesListTarticlesToAttach : fournisseur.getTarticlesList()) {
                tarticlesListTarticlesToAttach = getEntityManager().getReference(tarticlesListTarticlesToAttach.getClass(), tarticlesListTarticlesToAttach.getId());
                attachedTarticlesList.add(tarticlesListTarticlesToAttach);
            }
            fournisseur.setTarticlesList(attachedTarticlesList);
            getEntityManager().persist(fournisseur);
            if (societe != null) {
                societe.getFournisseurList().add(fournisseur);
                societe = getEntityManager().merge(societe);
            }
            for (LigneCommandeFournisseur ligneCommandeFournisseurListLigneCommandeFournisseur : fournisseur.getLigneCommandeFournisseurList()) {
                Fournisseur oldFournisseurOfLigneCommandeFournisseurListLigneCommandeFournisseur = ligneCommandeFournisseurListLigneCommandeFournisseur.getFournisseur();
                ligneCommandeFournisseurListLigneCommandeFournisseur.setFournisseur(fournisseur);
                ligneCommandeFournisseurListLigneCommandeFournisseur = getEntityManager().merge(ligneCommandeFournisseurListLigneCommandeFournisseur);
                if (oldFournisseurOfLigneCommandeFournisseurListLigneCommandeFournisseur != null) {
                    oldFournisseurOfLigneCommandeFournisseurListLigneCommandeFournisseur.getLigneCommandeFournisseurList().remove(ligneCommandeFournisseurListLigneCommandeFournisseur);
                    oldFournisseurOfLigneCommandeFournisseurListLigneCommandeFournisseur = getEntityManager().merge(oldFournisseurOfLigneCommandeFournisseurListLigneCommandeFournisseur);
                }
            }
            for (Tarticles tarticlesListTarticles : fournisseur.getTarticlesList()) {
                Fournisseur oldFournisseurOfTarticlesListTarticles = tarticlesListTarticles.getFournisseur();
                tarticlesListTarticles.setFournisseur(fournisseur);
                tarticlesListTarticles = getEntityManager().merge(tarticlesListTarticles);
                if (oldFournisseurOfTarticlesListTarticles != null) {
                    oldFournisseurOfTarticlesListTarticles.getTarticlesList().remove(tarticlesListTarticles);
                    oldFournisseurOfTarticlesListTarticles = getEntityManager().merge(oldFournisseurOfTarticlesListTarticles);
                }
            }
        } catch (Exception ex) {
             throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);
           
        } 
        return fournisseur;
    }

    @Override
    public Fournisseur MisAjour(Fournisseur fournisseur) throws NonexistentEntityException, RollbackFailureException, Exception {
    try {
            Fournisseur persistentFournisseur = getEntityManager().find(Fournisseur.class, fournisseur.getId());
            Societe societeOld = persistentFournisseur.getSociete();
            Societe societeNew = fournisseur.getSociete();
            List<LigneCommandeFournisseur> ligneCommandeFournisseurListOld = persistentFournisseur.getLigneCommandeFournisseurList();
            List<LigneCommandeFournisseur> ligneCommandeFournisseurListNew = fournisseur.getLigneCommandeFournisseurList();
            List<Tarticles> tarticlesListOld = persistentFournisseur.getTarticlesList();
            List<Tarticles> tarticlesListNew = fournisseur.getTarticlesList();
           
            if (societeNew != null) {
                societeNew = getEntityManager().getReference(societeNew.getClass(), societeNew.getId());
                fournisseur.setSociete(societeNew);
            }
            List<LigneCommandeFournisseur> attachedLigneCommandeFournisseurListNew = new ArrayList<LigneCommandeFournisseur>();
            for (LigneCommandeFournisseur ligneCommandeFournisseurListNewLigneCommandeFournisseurToAttach : ligneCommandeFournisseurListNew) {
                ligneCommandeFournisseurListNewLigneCommandeFournisseurToAttach = getEntityManager().getReference(ligneCommandeFournisseurListNewLigneCommandeFournisseurToAttach.getClass(), ligneCommandeFournisseurListNewLigneCommandeFournisseurToAttach.getId());
                attachedLigneCommandeFournisseurListNew.add(ligneCommandeFournisseurListNewLigneCommandeFournisseurToAttach);
            }
            ligneCommandeFournisseurListNew = attachedLigneCommandeFournisseurListNew;
            fournisseur.setLigneCommandeFournisseurList(ligneCommandeFournisseurListNew);
            List<Tarticles> attachedTarticlesListNew = new ArrayList<Tarticles>();
            for (Tarticles tarticlesListNewTarticlesToAttach : tarticlesListNew) {
                tarticlesListNewTarticlesToAttach = getEntityManager().getReference(tarticlesListNewTarticlesToAttach.getClass(), tarticlesListNewTarticlesToAttach.getId());
                attachedTarticlesListNew.add(tarticlesListNewTarticlesToAttach);
            }
            tarticlesListNew = attachedTarticlesListNew;
            fournisseur.setTarticlesList(tarticlesListNew);
            fournisseur = getEntityManager().merge(fournisseur);
            if (societeOld != null && !societeOld.equals(societeNew)) {
                societeOld.getFournisseurList().remove(fournisseur);
                societeOld = getEntityManager().merge(societeOld);
            }
            if (societeNew != null && !societeNew.equals(societeOld)) {
                societeNew.getFournisseurList().add(fournisseur);
                societeNew = getEntityManager().merge(societeNew);
            }
            for (LigneCommandeFournisseur ligneCommandeFournisseurListNewLigneCommandeFournisseur : ligneCommandeFournisseurListNew) {
                if (!ligneCommandeFournisseurListOld.contains(ligneCommandeFournisseurListNewLigneCommandeFournisseur)) {
                    Fournisseur oldFournisseurOfLigneCommandeFournisseurListNewLigneCommandeFournisseur = ligneCommandeFournisseurListNewLigneCommandeFournisseur.getFournisseur();
                    ligneCommandeFournisseurListNewLigneCommandeFournisseur.setFournisseur(fournisseur);
                    ligneCommandeFournisseurListNewLigneCommandeFournisseur = getEntityManager().merge(ligneCommandeFournisseurListNewLigneCommandeFournisseur);
                    if (oldFournisseurOfLigneCommandeFournisseurListNewLigneCommandeFournisseur != null && !oldFournisseurOfLigneCommandeFournisseurListNewLigneCommandeFournisseur.equals(fournisseur)) {
                        oldFournisseurOfLigneCommandeFournisseurListNewLigneCommandeFournisseur.getLigneCommandeFournisseurList().remove(ligneCommandeFournisseurListNewLigneCommandeFournisseur);
                        oldFournisseurOfLigneCommandeFournisseurListNewLigneCommandeFournisseur = getEntityManager().merge(oldFournisseurOfLigneCommandeFournisseurListNewLigneCommandeFournisseur);
                    }
                }
            }
            for (Tarticles tarticlesListOldTarticles : tarticlesListOld) {
                if (!tarticlesListNew.contains(tarticlesListOldTarticles)) {
                    tarticlesListOldTarticles.setFournisseur(null);
                    tarticlesListOldTarticles = getEntityManager().merge(tarticlesListOldTarticles);
                }
            }
            for (Tarticles tarticlesListNewTarticles : tarticlesListNew) {
                if (!tarticlesListOld.contains(tarticlesListNewTarticles)) {
                    Fournisseur oldFournisseurOfTarticlesListNewTarticles = tarticlesListNewTarticles.getFournisseur();
                    tarticlesListNewTarticles.setFournisseur(fournisseur);
                    tarticlesListNewTarticles = getEntityManager().merge(tarticlesListNewTarticles);
                    if (oldFournisseurOfTarticlesListNewTarticles != null && !oldFournisseurOfTarticlesListNewTarticles.equals(fournisseur)) {
                        oldFournisseurOfTarticlesListNewTarticles.getTarticlesList().remove(tarticlesListNewTarticles);
                        oldFournisseurOfTarticlesListNewTarticles = getEntityManager().merge(oldFournisseurOfTarticlesListNewTarticles);
                    }
                }
            }
        } catch (Exception ex) {
             throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);
            
        } 
    return fournisseur;
    }

    @Override
    public void Supprimer(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
   try {
            Fournisseur fournisseur;
            try {
                fournisseur = getEntityManager().getReference(Fournisseur.class, id);
                fournisseur.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The fournisseur with id " + id + " no longer exists.", enfe);
            }
            Societe societe = fournisseur.getSociete();
            if (societe != null) {
                societe.getFournisseurList().remove(fournisseur);
                societe = getEntityManager().merge(societe);
            }
            List<Tarticles> tarticlesList = fournisseur.getTarticlesList();
            for (Tarticles tarticlesListTarticles : tarticlesList) {
                tarticlesListTarticles.setFournisseur(null);
                tarticlesListTarticles = getEntityManager().merge(tarticlesListTarticles);
            }
            getEntityManager().remove(fournisseur);
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);
           
        } 
    }



}
