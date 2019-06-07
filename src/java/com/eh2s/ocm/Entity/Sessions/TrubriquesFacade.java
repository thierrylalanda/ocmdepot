/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.IllegalOrphanException;
import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Societe;
import com.eh2s.ocm.Entity.Trubriques;
import com.eh2s.ocm.Entity.Tsrubriques;
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
import javax.persistence.criteria.Root;

/**
 *
 * @author messi
 */
@Stateless
public class TrubriquesFacade extends AbstractFacade<Trubriques> implements TrubriquesFacadeLocal {

    @PersistenceContext(unitName = "ccmanagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TrubriquesFacade() {
        super(Trubriques.class);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public Trubriques creer(Trubriques trubriques) throws RollbackFailureException, Exception {
        try {
            Societe societe = trubriques.getSociete();
            if (societe != null) {
                societe = getEntityManager().getReference(societe.getClass(), societe.getId());
                trubriques.setSociete(societe);
            }
            List<Tsrubriques> attachedTsrubriquesList = new ArrayList<Tsrubriques>();
            for (Tsrubriques tsrubriquesListTsrubriquesToAttach : trubriques.getTsrubriquesList()) {
                tsrubriquesListTsrubriquesToAttach = getEntityManager().getReference(tsrubriquesListTsrubriquesToAttach.getClass(), tsrubriquesListTsrubriquesToAttach.getId());
                attachedTsrubriquesList.add(tsrubriquesListTsrubriquesToAttach);
            }
            trubriques.setTsrubriquesList(attachedTsrubriquesList);
            trubriques = getEntityManager().merge(trubriques);
            if (societe != null) {
                societe.getTrubriquesList().add(trubriques);
                societe = getEntityManager().merge(societe);
            }
            for (Tsrubriques tsrubriquesListTsrubriques : trubriques.getTsrubriquesList()) {
                Trubriques oldRubriqueidOfTsrubriquesListTsrubriques = tsrubriquesListTsrubriques.getRubriqueid();
                tsrubriquesListTsrubriques.setRubriqueid(trubriques);
                tsrubriquesListTsrubriques = getEntityManager().merge(tsrubriquesListTsrubriques);
                if (oldRubriqueidOfTsrubriquesListTsrubriques != null) {
                    oldRubriqueidOfTsrubriquesListTsrubriques.getTsrubriquesList().remove(tsrubriquesListTsrubriques);
                    oldRubriqueidOfTsrubriquesListTsrubriques = getEntityManager().merge(oldRubriqueidOfTsrubriquesListTsrubriques);
                }
            }
            return trubriques;
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void MisAJour(Trubriques trubriques) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        try {
            Trubriques persistentTrubriques = getEntityManager().find(Trubriques.class, trubriques.getId());
            Societe societeOld = persistentTrubriques.getSociete();
            Societe societeNew = trubriques.getSociete();
            List<Tsrubriques> tsrubriquesListOld = persistentTrubriques.getTsrubriquesList();
            List<Tsrubriques> tsrubriquesListNew = trubriques.getTsrubriquesList();

            if (societeNew != null) {
                societeNew = getEntityManager().getReference(societeNew.getClass(), societeNew.getId());
                trubriques.setSociete(societeNew);
            }
            List<Tsrubriques> attachedTsrubriquesListNew = new ArrayList<Tsrubriques>();
            for (Tsrubriques tsrubriquesListNewTsrubriquesToAttach : tsrubriquesListNew) {
                tsrubriquesListNewTsrubriquesToAttach = getEntityManager().getReference(tsrubriquesListNewTsrubriquesToAttach.getClass(), tsrubriquesListNewTsrubriquesToAttach.getId());
                attachedTsrubriquesListNew.add(tsrubriquesListNewTsrubriquesToAttach);
            }
            tsrubriquesListNew = attachedTsrubriquesListNew;
            trubriques.setTsrubriquesList(tsrubriquesListNew);
            trubriques = getEntityManager().merge(trubriques);
            if (societeOld != null && !societeOld.equals(societeNew)) {
                societeOld.getTrubriquesList().remove(trubriques);
                societeOld = getEntityManager().merge(societeOld);
            }
            if (societeNew != null && !societeNew.equals(societeOld)) {
                societeNew.getTrubriquesList().add(trubriques);
                societeNew = getEntityManager().merge(societeNew);
            }
            for (Tsrubriques tsrubriquesListNewTsrubriques : tsrubriquesListNew) {
                if (!tsrubriquesListOld.contains(tsrubriquesListNewTsrubriques)) {
                    Trubriques oldRubriqueidOfTsrubriquesListNewTsrubriques = tsrubriquesListNewTsrubriques.getRubriqueid();
                    tsrubriquesListNewTsrubriques.setRubriqueid(trubriques);
                    tsrubriquesListNewTsrubriques = getEntityManager().merge(tsrubriquesListNewTsrubriques);
                    if (oldRubriqueidOfTsrubriquesListNewTsrubriques != null && !oldRubriqueidOfTsrubriquesListNewTsrubriques.equals(trubriques)) {
                        oldRubriqueidOfTsrubriquesListNewTsrubriques.getTsrubriquesList().remove(tsrubriquesListNewTsrubriques);
                        oldRubriqueidOfTsrubriquesListNewTsrubriques = getEntityManager().merge(oldRubriqueidOfTsrubriquesListNewTsrubriques);
                    }
                }
            }

        } catch (Exception ex) {

            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = trubriques.getId();
                if (findTrubriques(id) == null) {
                    throw new NonexistentEntityException("The trubriques with id " + id + " no longer exists.");
                }
            }
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void Supprimer(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        try {
            Trubriques trubriques;
            try {
                trubriques = getEntityManager().getReference(Trubriques.class, id);
                trubriques.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The trubriques with id " + id + " no longer exists.", enfe);
            }

            Societe societe = trubriques.getSociete();
            if (societe != null) {
                societe.getTrubriquesList().remove(trubriques);
                societe = getEntityManager().merge(societe);
            }
            getEntityManager().remove(trubriques);
        } catch (NonexistentEntityException ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);
        }
    }

    @Override
    public List<Trubriques> findTrubriquesEntities() {
        return findTrubriquesEntities(true, -1, -1);
    }

    @Override
    public List<Trubriques> findTrubriquesEntities(int maxResults, int firstResult) {
        return findTrubriquesEntities(false, maxResults, firstResult);
    }

    private List<Trubriques> findTrubriquesEntities(boolean all, int maxResults, int firstResult) {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Trubriques.class));
        Query q = getEntityManager().createQuery(cq);
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    @Override
    public Trubriques findTrubriques(Integer id) {
        return getEntityManager().find(Trubriques.class, id);
    }

    @Override
    public int getTrubriquesCount() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<Trubriques> rt = cq.from(Trubriques.class);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    @Override
    public List<Trubriques> findAll(String societe) {
        Query q = getEntityManager().createNamedQuery("Trubriques.findAll");
        q.setParameter("imma", societe);
        return q.getResultList();
    }

}
