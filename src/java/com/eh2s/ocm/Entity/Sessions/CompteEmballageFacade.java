/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.CompteEmballage;
import com.eh2s.ocm.Entity.Emballage;
import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Tclients;
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
public class CompteEmballageFacade extends AbstractFacade<CompteEmballage> implements CompteEmballageFacadeLocal {

    @PersistenceContext(unitName = "ccmanagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompteEmballageFacade() {
        super(CompteEmballage.class);
    }

    @Override
    public List<CompteEmballage> findAll(int societe) {
        Query q = getEntityManager().createNamedQuery("CompteEmballage.findBySociete");
        q.setParameter("societe", societe);
        return q.getResultList();
    }

    @Override
    public List<CompteEmballage> findAllByCLient(int client) {
        Query q = getEntityManager().createNamedQuery("CompteEmballage.findByClient");
        q.setParameter("client", client);
        return q.getResultList();
    }

    @Override
    public CompteEmballage findAllByCLientAndEmballage(int client, int emballage) {
        Query q = getEntityManager().createNamedQuery("CompteEmballage.findByClientAndEmballage");
        q.setParameter("client", client);
        q.setParameter("emballage", emballage);
        if (q.getSingleResult() != null) {
            return (CompteEmballage) q.getSingleResult();
        }
        return new CompteEmballage(0);
    }

    @Override
    public CompteEmballage creer(CompteEmballage compteEmballage) throws RollbackFailureException, Exception {
        try {
            Emballage emballage = compteEmballage.getEmballage();
            if (emballage != null) {
                emballage = getEntityManager().getReference(emballage.getClass(), emballage.getId());
                compteEmballage.setEmballage(emballage);
            }
            Tclients client = compteEmballage.getClient();
            if (client != null) {
                client = getEntityManager().getReference(client.getClass(), client.getId());
                compteEmballage.setClient(client);
            }
            getEntityManager().persist(compteEmballage);
            if (emballage != null) {
                emballage.getCompteEmballageList().add(compteEmballage);
                emballage = getEntityManager().merge(emballage);
            }
            if (client != null) {
                client.getCompteEmballageList().add(compteEmballage);
                client = getEntityManager().merge(client);
            }
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
        return compteEmballage;
    }

    @Override
    public CompteEmballage MisAjour(CompteEmballage compteEmballage) throws NonexistentEntityException, RollbackFailureException, Exception {
        try {
            CompteEmballage persistentCompteEmballage = getEntityManager().find(CompteEmballage.class, compteEmballage.getId());
            Emballage emballageOld = persistentCompteEmballage.getEmballage();
            Emballage emballageNew = compteEmballage.getEmballage();
            Tclients clientOld = persistentCompteEmballage.getClient();
            Tclients clientNew = compteEmballage.getClient();
            if (emballageNew != null) {
                emballageNew = getEntityManager().getReference(emballageNew.getClass(), emballageNew.getId());
                compteEmballage.setEmballage(emballageNew);
            }
            if (clientNew != null) {
                clientNew = getEntityManager().getReference(clientNew.getClass(), clientNew.getId());
                compteEmballage.setClient(clientNew);
            }
            compteEmballage = getEntityManager().merge(compteEmballage);
            if (emballageOld != null && !emballageOld.equals(emballageNew)) {
                emballageOld.getCompteEmballageList().remove(compteEmballage);
                emballageOld = getEntityManager().merge(emballageOld);
            }
            if (emballageNew != null && !emballageNew.equals(emballageOld)) {
                emballageNew.getCompteEmballageList().add(compteEmballage);
                emballageNew = getEntityManager().merge(emballageNew);
            }
            if (clientOld != null && !clientOld.equals(clientNew)) {
                clientOld.getCompteEmballageList().remove(compteEmballage);
                clientOld = getEntityManager().merge(clientOld);
            }
            if (clientNew != null && !clientNew.equals(clientOld)) {
                clientNew.getCompteEmballageList().add(compteEmballage);
                clientNew = getEntityManager().merge(clientNew);
            }
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
        return compteEmballage;
    }

    @Override
    public void Supprimer(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
     try {
            CompteEmballage compteEmballage;
            try {
                compteEmballage = getEntityManager().getReference(CompteEmballage.class, id);
                compteEmballage.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The compteEmballage with id " + id + " no longer exists.", enfe);
            }
            Emballage emballage = compteEmballage.getEmballage();
            if (emballage != null) {
                emballage.getCompteEmballageList().remove(compteEmballage);
                emballage = getEntityManager().merge(emballage);
            }
            Tclients client = compteEmballage.getClient();
            if (client != null) {
                client.getCompteEmballageList().remove(compteEmballage);
                client = getEntityManager().merge(client);
            }
            getEntityManager().remove(compteEmballage);
        } catch (Exception ex) {
             throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);
           
        } 
    }

    public List<CompteEmballage> findCompteEmballageEntities() {
        return findCompteEmballageEntities(true, -1, -1);
    }

    public List<CompteEmballage> findCompteEmballageEntities(int maxResults, int firstResult) {
        return findCompteEmballageEntities(false, maxResults, firstResult);
    }

    private List<CompteEmballage> findCompteEmballageEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CompteEmballage.class));
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

    public CompteEmballage findCompteEmballage(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CompteEmballage.class, id);
        } finally {
            em.close();
        }
    }

    public int getCompteEmballageCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CompteEmballage> rt = cq.from(CompteEmballage.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
