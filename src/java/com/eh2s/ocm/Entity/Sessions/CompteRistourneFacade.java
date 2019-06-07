/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.CompteRistourne;
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
public class CompteRistourneFacade extends AbstractFacade<CompteRistourne> implements CompteRistourneFacadeLocal {

    @PersistenceContext(unitName = "ccmanagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompteRistourneFacade() {
        super(CompteRistourne.class);
    }

    @Override
    public List<CompteRistourne> findAll(int societe) {
        Query q = getEntityManager().createNamedQuery("CompteRistourne.findBySociete");
        q.setParameter("societe", societe);
        return q.getResultList();
    }

    @Override
    public List<CompteRistourne> findAllByClient(int client) {
        Query q = getEntityManager().createNamedQuery("CompteRistourne.findByClient");
        q.setParameter("client", client);
        return q.getResultList();
    }

    @Override
    public CompteRistourne creer(CompteRistourne compteRistourne) throws RollbackFailureException, Exception {
        try {
            Tclients client = compteRistourne.getClient();
            if (client != null) {
                client = getEntityManager().getReference(client.getClass(), client.getId());
                compteRistourne.setClient(client);
            }
            getEntityManager().persist(compteRistourne);
            if (client != null) {
                client.getCompteRistourneList().add(compteRistourne);
                client = getEntityManager().merge(client);
            }
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
        return compteRistourne;
    }

    @Override
    public CompteRistourne MisAjour(CompteRistourne compteRistourne) throws NonexistentEntityException, RollbackFailureException, Exception {
        try {
            CompteRistourne persistentCompteRistourne = getEntityManager().find(CompteRistourne.class, compteRistourne.getId());
            Tclients clientOld = persistentCompteRistourne.getClient();
            Tclients clientNew = compteRistourne.getClient();
            if (clientNew != null) {
                clientNew = getEntityManager().getReference(clientNew.getClass(), clientNew.getId());
                compteRistourne.setClient(clientNew);
            }
            compteRistourne = getEntityManager().merge(compteRistourne);
            if (clientOld != null && !clientOld.equals(clientNew)) {
                clientOld.getCompteRistourneList().remove(compteRistourne);
                clientOld = getEntityManager().merge(clientOld);
            }
            if (clientNew != null && !clientNew.equals(clientOld)) {
                clientNew.getCompteRistourneList().add(compteRistourne);
                clientNew = getEntityManager().merge(clientNew);
            }
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
        return compteRistourne;
    }

    @Override
    public void Supprimer(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        try {
            CompteRistourne compteRistourne;
            try {
                compteRistourne = getEntityManager().getReference(CompteRistourne.class, id);
                compteRistourne.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The compteRistourne with id " + id + " no longer exists.", enfe);
            }
            Tclients client = compteRistourne.getClient();
            if (client != null) {
                client.getCompteRistourneList().remove(compteRistourne);
                client = getEntityManager().merge(client);
            }
            getEntityManager().remove(compteRistourne);
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }
    
    public List<CompteRistourne> findCompteRistourneEntities() {
        return findCompteRistourneEntities(true, -1, -1);
    }

    public List<CompteRistourne> findCompteRistourneEntities(int maxResults, int firstResult) {
        return findCompteRistourneEntities(false, maxResults, firstResult);
    }

    private List<CompteRistourne> findCompteRistourneEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CompteRistourne.class));
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

    public CompteRistourne findCompteRistourne(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CompteRistourne.class, id);
        } finally {
            em.close();
        }
    }

    public int getCompteRistourneCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CompteRistourne> rt = cq.from(CompteRistourne.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
