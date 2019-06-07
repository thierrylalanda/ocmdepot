/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.CommandeEmballage;
import com.eh2s.ocm.Entity.CompteEmballage;
import com.eh2s.ocm.Entity.Emballage;
import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Inventaire;
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
public class EmballageFacade extends AbstractFacade<Emballage> implements EmballageFacadeLocal {

    @PersistenceContext(unitName = "ccmanagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmballageFacade() {
        super(Emballage.class);
    }

    @Override
    public List<Emballage> findAll(int societe) {
        Query q = getEntityManager().createNamedQuery("Emballage.findAll");
        q.setParameter("societe", societe);
        return q.getResultList();
    }

    @Override
    public Emballage creer(Emballage emballage) throws RollbackFailureException, Exception {
        if (emballage.getCommandeEmballageList() == null) {
            emballage.setCommandeEmballageList(new ArrayList<CommandeEmballage>());
        }
        if (emballage.getCompteEmballageList() == null) {
            emballage.setCompteEmballageList(new ArrayList<CompteEmballage>());
        }
        if (emballage.getInventaireList() == null) {
            emballage.setInventaireList(new ArrayList<Inventaire>());
        }
        if (emballage.getTarticlesList() == null) {
            emballage.setTarticlesList(new ArrayList<Tarticles>());
        }
        try {
            Societe societe = emballage.getSociete();
            if (societe != null) {
                societe = getEntityManager().getReference(societe.getClass(), societe.getId());
                emballage.setSociete(societe);
            }
            List<CommandeEmballage> attachedCommandeEmballageList = new ArrayList<CommandeEmballage>();
            for (CommandeEmballage commandeEmballageListCommandeEmballageToAttach : emballage.getCommandeEmballageList()) {
                commandeEmballageListCommandeEmballageToAttach = getEntityManager().getReference(commandeEmballageListCommandeEmballageToAttach.getClass(), commandeEmballageListCommandeEmballageToAttach.getId());
                attachedCommandeEmballageList.add(commandeEmballageListCommandeEmballageToAttach);
            }
            emballage.setCommandeEmballageList(attachedCommandeEmballageList);
            List<CompteEmballage> attachedCompteEmballageList = new ArrayList<CompteEmballage>();
            for (CompteEmballage compteEmballageListCompteEmballageToAttach : emballage.getCompteEmballageList()) {
                compteEmballageListCompteEmballageToAttach = getEntityManager().getReference(compteEmballageListCompteEmballageToAttach.getClass(), compteEmballageListCompteEmballageToAttach.getId());
                attachedCompteEmballageList.add(compteEmballageListCompteEmballageToAttach);
            }
            emballage.setCompteEmballageList(attachedCompteEmballageList);
            List<Inventaire> attachedInventaireList = new ArrayList<Inventaire>();
            for (Inventaire inventaireListInventaireToAttach : emballage.getInventaireList()) {
                inventaireListInventaireToAttach = getEntityManager().getReference(inventaireListInventaireToAttach.getClass(), inventaireListInventaireToAttach.getId());
                attachedInventaireList.add(inventaireListInventaireToAttach);
            }
            emballage.setInventaireList(attachedInventaireList);
            List<Tarticles> attachedTarticlesList = new ArrayList<Tarticles>();
            for (Tarticles tarticlesListTarticlesToAttach : emballage.getTarticlesList()) {
                tarticlesListTarticlesToAttach = getEntityManager().getReference(tarticlesListTarticlesToAttach.getClass(), tarticlesListTarticlesToAttach.getId());
                attachedTarticlesList.add(tarticlesListTarticlesToAttach);
            }
            emballage.setTarticlesList(attachedTarticlesList);
            getEntityManager().persist(emballage);
            if (societe != null) {
                societe.getEmballageList().add(emballage);
                societe = getEntityManager().merge(societe);
            }
            for (CommandeEmballage commandeEmballageListCommandeEmballage : emballage.getCommandeEmballageList()) {
                Emballage oldEmballageOfCommandeEmballageListCommandeEmballage = commandeEmballageListCommandeEmballage.getEmballage();
                commandeEmballageListCommandeEmballage.setEmballage(emballage);
                commandeEmballageListCommandeEmballage = getEntityManager().merge(commandeEmballageListCommandeEmballage);
                if (oldEmballageOfCommandeEmballageListCommandeEmballage != null) {
                    oldEmballageOfCommandeEmballageListCommandeEmballage.getCommandeEmballageList().remove(commandeEmballageListCommandeEmballage);
                    oldEmballageOfCommandeEmballageListCommandeEmballage = getEntityManager().merge(oldEmballageOfCommandeEmballageListCommandeEmballage);
                }
            }
            for (CompteEmballage compteEmballageListCompteEmballage : emballage.getCompteEmballageList()) {
                Emballage oldEmballageOfCompteEmballageListCompteEmballage = compteEmballageListCompteEmballage.getEmballage();
                compteEmballageListCompteEmballage.setEmballage(emballage);
                compteEmballageListCompteEmballage = getEntityManager().merge(compteEmballageListCompteEmballage);
                if (oldEmballageOfCompteEmballageListCompteEmballage != null) {
                    oldEmballageOfCompteEmballageListCompteEmballage.getCompteEmballageList().remove(compteEmballageListCompteEmballage);
                    oldEmballageOfCompteEmballageListCompteEmballage = getEntityManager().merge(oldEmballageOfCompteEmballageListCompteEmballage);
                }
            }
            for (Inventaire inventaireListInventaire : emballage.getInventaireList()) {
                Emballage oldEmballageOfInventaireListInventaire = inventaireListInventaire.getEmballage();
                inventaireListInventaire.setEmballage(emballage);
                inventaireListInventaire = getEntityManager().merge(inventaireListInventaire);
                if (oldEmballageOfInventaireListInventaire != null) {
                    oldEmballageOfInventaireListInventaire.getInventaireList().remove(inventaireListInventaire);
                    oldEmballageOfInventaireListInventaire = getEntityManager().merge(oldEmballageOfInventaireListInventaire);
                }
            }
            for (Tarticles tarticlesListTarticles : emballage.getTarticlesList()) {
                Emballage oldEmballageOfTarticlesListTarticles = tarticlesListTarticles.getEmballage();
                tarticlesListTarticles.setEmballage(emballage);
                tarticlesListTarticles = getEntityManager().merge(tarticlesListTarticles);
                if (oldEmballageOfTarticlesListTarticles != null) {
                    oldEmballageOfTarticlesListTarticles.getTarticlesList().remove(tarticlesListTarticles);
                    oldEmballageOfTarticlesListTarticles = getEntityManager().merge(oldEmballageOfTarticlesListTarticles);
                }
            }
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
        return emballage;
    }

    @Override
    public Emballage MisAjour(Emballage emballage) throws NonexistentEntityException, RollbackFailureException, Exception {
        try {
            Emballage persistentEmballage = getEntityManager().find(Emballage.class, emballage.getId());
            Societe societeOld = persistentEmballage.getSociete();
            Societe societeNew = emballage.getSociete();
            List<CommandeEmballage> commandeEmballageListOld = persistentEmballage.getCommandeEmballageList();
            List<CommandeEmballage> commandeEmballageListNew = emballage.getCommandeEmballageList();
            List<CompteEmballage> compteEmballageListOld = persistentEmballage.getCompteEmballageList();
            List<CompteEmballage> compteEmballageListNew = emballage.getCompteEmballageList();
            List<Inventaire> inventaireListOld = persistentEmballage.getInventaireList();
            List<Inventaire> inventaireListNew = emballage.getInventaireList();
            List<Tarticles> tarticlesListOld = persistentEmballage.getTarticlesList();
            List<Tarticles> tarticlesListNew = emballage.getTarticlesList();

            if (societeNew != null) {
                societeNew = getEntityManager().getReference(societeNew.getClass(), societeNew.getId());
                emballage.setSociete(societeNew);
            }
            List<CommandeEmballage> attachedCommandeEmballageListNew = new ArrayList<CommandeEmballage>();
            for (CommandeEmballage commandeEmballageListNewCommandeEmballageToAttach : commandeEmballageListNew) {
                commandeEmballageListNewCommandeEmballageToAttach = getEntityManager().getReference(commandeEmballageListNewCommandeEmballageToAttach.getClass(), commandeEmballageListNewCommandeEmballageToAttach.getId());
                attachedCommandeEmballageListNew.add(commandeEmballageListNewCommandeEmballageToAttach);
            }
            commandeEmballageListNew = attachedCommandeEmballageListNew;
            emballage.setCommandeEmballageList(commandeEmballageListNew);
            List<CompteEmballage> attachedCompteEmballageListNew = new ArrayList<CompteEmballage>();
            for (CompteEmballage compteEmballageListNewCompteEmballageToAttach : compteEmballageListNew) {
                compteEmballageListNewCompteEmballageToAttach = getEntityManager().getReference(compteEmballageListNewCompteEmballageToAttach.getClass(), compteEmballageListNewCompteEmballageToAttach.getId());
                attachedCompteEmballageListNew.add(compteEmballageListNewCompteEmballageToAttach);
            }
            compteEmballageListNew = attachedCompteEmballageListNew;
            emballage.setCompteEmballageList(compteEmballageListNew);
            List<Inventaire> attachedInventaireListNew = new ArrayList<Inventaire>();
            for (Inventaire inventaireListNewInventaireToAttach : inventaireListNew) {
                inventaireListNewInventaireToAttach = getEntityManager().getReference(inventaireListNewInventaireToAttach.getClass(), inventaireListNewInventaireToAttach.getId());
                attachedInventaireListNew.add(inventaireListNewInventaireToAttach);
            }
            inventaireListNew = attachedInventaireListNew;
            emballage.setInventaireList(inventaireListNew);
            List<Tarticles> attachedTarticlesListNew = new ArrayList<Tarticles>();
            for (Tarticles tarticlesListNewTarticlesToAttach : tarticlesListNew) {
                tarticlesListNewTarticlesToAttach = getEntityManager().getReference(tarticlesListNewTarticlesToAttach.getClass(), tarticlesListNewTarticlesToAttach.getId());
                attachedTarticlesListNew.add(tarticlesListNewTarticlesToAttach);
            }
            tarticlesListNew = attachedTarticlesListNew;
            emballage.setTarticlesList(tarticlesListNew);
            emballage = getEntityManager().merge(emballage);
            if (societeOld != null && !societeOld.equals(societeNew)) {
                societeOld.getEmballageList().remove(emballage);
                societeOld = getEntityManager().merge(societeOld);
            }
            if (societeNew != null && !societeNew.equals(societeOld)) {
                societeNew.getEmballageList().add(emballage);
                societeNew = getEntityManager().merge(societeNew);
            }
            for (CommandeEmballage commandeEmballageListNewCommandeEmballage : commandeEmballageListNew) {
                if (!commandeEmballageListOld.contains(commandeEmballageListNewCommandeEmballage)) {
                    Emballage oldEmballageOfCommandeEmballageListNewCommandeEmballage = commandeEmballageListNewCommandeEmballage.getEmballage();
                    commandeEmballageListNewCommandeEmballage.setEmballage(emballage);
                    commandeEmballageListNewCommandeEmballage = getEntityManager().merge(commandeEmballageListNewCommandeEmballage);
                    if (oldEmballageOfCommandeEmballageListNewCommandeEmballage != null && !oldEmballageOfCommandeEmballageListNewCommandeEmballage.equals(emballage)) {
                        oldEmballageOfCommandeEmballageListNewCommandeEmballage.getCommandeEmballageList().remove(commandeEmballageListNewCommandeEmballage);
                        oldEmballageOfCommandeEmballageListNewCommandeEmballage = getEntityManager().merge(oldEmballageOfCommandeEmballageListNewCommandeEmballage);
                    }
                }
            }
            for (CompteEmballage compteEmballageListNewCompteEmballage : compteEmballageListNew) {
                if (!compteEmballageListOld.contains(compteEmballageListNewCompteEmballage)) {
                    Emballage oldEmballageOfCompteEmballageListNewCompteEmballage = compteEmballageListNewCompteEmballage.getEmballage();
                    compteEmballageListNewCompteEmballage.setEmballage(emballage);
                    compteEmballageListNewCompteEmballage = getEntityManager().merge(compteEmballageListNewCompteEmballage);
                    if (oldEmballageOfCompteEmballageListNewCompteEmballage != null && !oldEmballageOfCompteEmballageListNewCompteEmballage.equals(emballage)) {
                        oldEmballageOfCompteEmballageListNewCompteEmballage.getCompteEmballageList().remove(compteEmballageListNewCompteEmballage);
                        oldEmballageOfCompteEmballageListNewCompteEmballage = getEntityManager().merge(oldEmballageOfCompteEmballageListNewCompteEmballage);
                    }
                }
            }
            for (Inventaire inventaireListOldInventaire : inventaireListOld) {
                if (!inventaireListNew.contains(inventaireListOldInventaire)) {
                    inventaireListOldInventaire.setEmballage(null);
                    inventaireListOldInventaire = getEntityManager().merge(inventaireListOldInventaire);
                }
            }
            for (Inventaire inventaireListNewInventaire : inventaireListNew) {
                if (!inventaireListOld.contains(inventaireListNewInventaire)) {
                    Emballage oldEmballageOfInventaireListNewInventaire = inventaireListNewInventaire.getEmballage();
                    inventaireListNewInventaire.setEmballage(emballage);
                    inventaireListNewInventaire = getEntityManager().merge(inventaireListNewInventaire);
                    if (oldEmballageOfInventaireListNewInventaire != null && !oldEmballageOfInventaireListNewInventaire.equals(emballage)) {
                        oldEmballageOfInventaireListNewInventaire.getInventaireList().remove(inventaireListNewInventaire);
                        oldEmballageOfInventaireListNewInventaire = getEntityManager().merge(oldEmballageOfInventaireListNewInventaire);
                    }
                }
            }
            for (Tarticles tarticlesListOldTarticles : tarticlesListOld) {
                if (!tarticlesListNew.contains(tarticlesListOldTarticles)) {
                    tarticlesListOldTarticles.setEmballage(null);
                    tarticlesListOldTarticles = getEntityManager().merge(tarticlesListOldTarticles);
                }
            }
            for (Tarticles tarticlesListNewTarticles : tarticlesListNew) {
                if (!tarticlesListOld.contains(tarticlesListNewTarticles)) {
                    Emballage oldEmballageOfTarticlesListNewTarticles = tarticlesListNewTarticles.getEmballage();
                    tarticlesListNewTarticles.setEmballage(emballage);
                    tarticlesListNewTarticles = getEntityManager().merge(tarticlesListNewTarticles);
                    if (oldEmballageOfTarticlesListNewTarticles != null && !oldEmballageOfTarticlesListNewTarticles.equals(emballage)) {
                        oldEmballageOfTarticlesListNewTarticles.getTarticlesList().remove(tarticlesListNewTarticles);
                        oldEmballageOfTarticlesListNewTarticles = getEntityManager().merge(oldEmballageOfTarticlesListNewTarticles);
                    }
                }
            }
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
        return emballage;
    }

    @Override
    public void Supprimer(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        try {
            Emballage emballage;
            try {
                emballage = getEntityManager().getReference(Emballage.class, id);
                emballage.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The emballage with id " + id + " no longer exists.", enfe);
            }

            Societe societe = emballage.getSociete();
            if (societe != null) {
                societe.getEmballageList().remove(emballage);
                societe = getEntityManager().merge(societe);
            }
            List<Inventaire> inventaireList = emballage.getInventaireList();
            for (Inventaire inventaireListInventaire : inventaireList) {
                inventaireListInventaire.setEmballage(null);
                inventaireListInventaire = getEntityManager().merge(inventaireListInventaire);
            }
            List<Tarticles> tarticlesList = emballage.getTarticlesList();
            for (Tarticles tarticlesListTarticles : tarticlesList) {
                tarticlesListTarticles.setEmballage(null);
                tarticlesListTarticles = getEntityManager().merge(tarticlesListTarticles);
            }
            getEntityManager().remove(emballage);
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

}
