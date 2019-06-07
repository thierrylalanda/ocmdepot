/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.CommandeEmballage;
import com.eh2s.ocm.Entity.Emballage;
import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Tlignecommande;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Administrateur
 */
@Stateless
public class CommandeEmballageFacade extends AbstractFacade<CommandeEmballage> implements CommandeEmballageFacadeLocal {

    @PersistenceContext(unitName = "ccmanagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CommandeEmballageFacade() {
        super(CommandeEmballage.class);
    }

    @Override
    public CommandeEmballage creer(CommandeEmballage commandeEmballage) throws RollbackFailureException, Exception {
        try {
            Tlignecommande ligne = commandeEmballage.getLigne();
            if (ligne != null) {
                ligne = getEntityManager().getReference(ligne.getClass(), ligne.getId());
                commandeEmballage.setLigne(ligne);
            }
            Emballage emballage = commandeEmballage.getEmballage();
            if (emballage != null) {
                emballage = getEntityManager().getReference(emballage.getClass(), emballage.getId());
                commandeEmballage.setEmballage(emballage);
            }
            getEntityManager().persist(commandeEmballage);
            if (ligne != null) {
                ligne.getCommandeEmballageList().add(commandeEmballage);
                ligne = getEntityManager().merge(ligne);
            }
            if (emballage != null) {
                emballage.getCommandeEmballageList().add(commandeEmballage);
                emballage = getEntityManager().merge(emballage);
            }
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
        return commandeEmballage;
    }

    @Override
    public CommandeEmballage MisAjour(CommandeEmballage commandeEmballage) throws NonexistentEntityException, RollbackFailureException, Exception {

        try {
            CommandeEmballage persistentCommandeEmballage = getEntityManager().find(CommandeEmballage.class, commandeEmballage.getId());
            Tlignecommande ligneOld = persistentCommandeEmballage.getLigne();
            Tlignecommande ligneNew = commandeEmballage.getLigne();
            Emballage emballageOld = persistentCommandeEmballage.getEmballage();
            Emballage emballageNew = commandeEmballage.getEmballage();
            if (ligneNew != null) {
                ligneNew = getEntityManager().getReference(ligneNew.getClass(), ligneNew.getId());
                commandeEmballage.setLigne(ligneNew);
            }
            if (emballageNew != null) {
                emballageNew = getEntityManager().getReference(emballageNew.getClass(), emballageNew.getId());
                commandeEmballage.setEmballage(emballageNew);
            }
            commandeEmballage = getEntityManager().merge(commandeEmballage);
            if (ligneOld != null && !ligneOld.equals(ligneNew)) {
                ligneOld.getCommandeEmballageList().remove(commandeEmballage);
                ligneOld = getEntityManager().merge(ligneOld);
            }
            if (ligneNew != null && !ligneNew.equals(ligneOld)) {
                ligneNew.getCommandeEmballageList().add(commandeEmballage);
                ligneNew = getEntityManager().merge(ligneNew);
            }
            if (emballageOld != null && !emballageOld.equals(emballageNew)) {
                emballageOld.getCommandeEmballageList().remove(commandeEmballage);
                emballageOld = getEntityManager().merge(emballageOld);
            }
            if (emballageNew != null && !emballageNew.equals(emballageOld)) {
                emballageNew.getCommandeEmballageList().add(commandeEmballage);
                emballageNew = getEntityManager().merge(emballageNew);
            }
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
        return commandeEmballage;
    }

    @Override
    public void Supprimer(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {

        try {
            CommandeEmballage commandeEmballage;
            try {
                commandeEmballage = getEntityManager().getReference(CommandeEmballage.class, id);
                commandeEmballage.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The commandeEmballage with id " + id + " no longer exists.", enfe);
            }
            Tlignecommande ligne = commandeEmballage.getLigne();
            if (ligne != null) {
                ligne.getCommandeEmballageList().remove(commandeEmballage);
                ligne = getEntityManager().merge(ligne);
            }
            Emballage emballage = commandeEmballage.getEmballage();
            if (emballage != null) {
                emballage.getCommandeEmballageList().remove(commandeEmballage);
                emballage = getEntityManager().merge(emballage);
            }
            getEntityManager().remove(commandeEmballage);
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    public List<CommandeEmballage> findCommandeEmballageEntities() {
        return findCommandeEmballageEntities(true, -1, -1);
    }

    public List<CommandeEmballage> findCommandeEmballageEntities(int maxResults, int firstResult) {
        return findCommandeEmballageEntities(false, maxResults, firstResult);
    }

    private List<CommandeEmballage> findCommandeEmballageEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CommandeEmballage.class));
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

    public CommandeEmballage findCommandeEmballage(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CommandeEmballage.class, id);
        } finally {
            em.close();
        }
    }

    public int getCommandeEmballageCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CommandeEmballage> rt = cq.from(CommandeEmballage.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
}
