/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Emballage;
import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Inventaire;
import com.eh2s.ocm.Entity.LigneInventaire;
import com.eh2s.ocm.Entity.Tarticles;
import java.sql.Date;
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
public class InventaireFacade extends AbstractFacade<Inventaire> implements InventaireFacadeLocal {

    @PersistenceContext(unitName = "ccmanagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InventaireFacade() {
        super(Inventaire.class);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public Inventaire creer(Inventaire inventaire) throws RollbackFailureException, Exception {
        try {
            Tarticles article = inventaire.getArticle();
            if (article != null) {
                article = getEntityManager().getReference(article.getClass(), article.getId());
                inventaire.setArticle(article);
            }
            LigneInventaire ligneInv = inventaire.getLigneInv();
            if (ligneInv != null) {
                ligneInv = getEntityManager().getReference(ligneInv.getClass(), ligneInv.getId());
                inventaire.setLigneInv(ligneInv);
            }
            Emballage emballage = inventaire.getEmballage();
            if (emballage != null) {
                emballage = getEntityManager().getReference(emballage.getClass(), emballage.getId());
                inventaire.setEmballage(emballage);
            }
            getEntityManager().persist(inventaire);
            if (article != null) {
                article.getInventaireList().add(inventaire);
                article = getEntityManager().merge(article);
            }
            if (ligneInv != null) {
                ligneInv.getInventaireList().add(inventaire);
                ligneInv = getEntityManager().merge(ligneInv);
            }
            if (emballage != null) {
                emballage.getInventaireList().add(inventaire);
                emballage = getEntityManager().merge(emballage);
            }
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
        return inventaire;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public Inventaire MisAjour(Inventaire inventaire) throws NonexistentEntityException, RollbackFailureException, Exception {
        try {
            Inventaire persistentInventaire = getEntityManager().find(Inventaire.class, inventaire.getId());
            Tarticles articleOld = persistentInventaire.getArticle();
            Tarticles articleNew = inventaire.getArticle();
            LigneInventaire ligneInvOld = persistentInventaire.getLigneInv();
            LigneInventaire ligneInvNew = inventaire.getLigneInv();
            Emballage emballageOld = persistentInventaire.getEmballage();
            Emballage emballageNew = inventaire.getEmballage();
            if (articleNew != null) {
                articleNew = getEntityManager().getReference(articleNew.getClass(), articleNew.getId());
                inventaire.setArticle(articleNew);
            }
            if (ligneInvNew != null) {
                ligneInvNew = getEntityManager().getReference(ligneInvNew.getClass(), ligneInvNew.getId());
                inventaire.setLigneInv(ligneInvNew);
            }
            if (emballageNew != null) {
                emballageNew = getEntityManager().getReference(emballageNew.getClass(), emballageNew.getId());
                inventaire.setEmballage(emballageNew);
            }
            inventaire = getEntityManager().merge(inventaire);
            if (articleOld != null && !articleOld.equals(articleNew)) {
                articleOld.getInventaireList().remove(inventaire);
                articleOld = getEntityManager().merge(articleOld);
            }
            if (articleNew != null && !articleNew.equals(articleOld)) {
                articleNew.getInventaireList().add(inventaire);
                articleNew = getEntityManager().merge(articleNew);
            }
            if (ligneInvOld != null && !ligneInvOld.equals(ligneInvNew)) {
                ligneInvOld.getInventaireList().remove(inventaire);
                ligneInvOld = getEntityManager().merge(ligneInvOld);
            }
            if (ligneInvNew != null && !ligneInvNew.equals(ligneInvOld)) {
                ligneInvNew.getInventaireList().add(inventaire);
                ligneInvNew = getEntityManager().merge(ligneInvNew);
            }
            if (emballageOld != null && !emballageOld.equals(emballageNew)) {
                emballageOld.getInventaireList().remove(inventaire);
                emballageOld = getEntityManager().merge(emballageOld);
            }
            if (emballageNew != null && !emballageNew.equals(emballageOld)) {
                emballageNew.getInventaireList().add(inventaire);
                emballageNew = getEntityManager().merge(emballageNew);
            }
            return inventaire;
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void Supprimer(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        try {
            Inventaire inventaire;
            try {
                inventaire = getEntityManager().getReference(Inventaire.class, id);
                inventaire.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The inventaire with id " + id + " no longer exists.", enfe);
            }
            Tarticles article = inventaire.getArticle();
            if (article != null) {
                article.getInventaireList().remove(inventaire);
                article = getEntityManager().merge(article);
            }
            LigneInventaire ligneInv = inventaire.getLigneInv();
            if (ligneInv != null) {
                ligneInv.getInventaireList().remove(inventaire);
                ligneInv = getEntityManager().merge(ligneInv);
            }
            Emballage emballage = inventaire.getEmballage();
            if (emballage != null) {
                emballage.getInventaireList().remove(inventaire);
                emballage = getEntityManager().merge(emballage);
            }
            getEntityManager().remove(inventaire);
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @Override
    public List<Inventaire> findInventaireEntities() {
        return findInventaireEntities(true, -1, -1);
    }

    public List<Inventaire> findInventaireEntities(int maxResults, int firstResult) {
        return findInventaireEntities(false, maxResults, firstResult);
    }

    private List<Inventaire> findInventaireEntities(boolean all, int maxResults, int firstResult) {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Inventaire.class));
        Query q = getEntityManager().createQuery(cq);
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    @Override
    public Inventaire findInventaire(Integer id) {
        return getEntityManager().find(Inventaire.class, id);
    }

    @Override
    public List<Inventaire> historiqueInventaire(Date debut, Date fin, Integer magasin, Integer entite, Integer type) {
        if (type == 1) {
            Query q = getEntityManager().createNamedQuery("Inventaire.findByMagasinByPeriodeByArticle");
            q.setParameter("d", debut);
            q.setParameter("d1", fin);
            q.setParameter("article", entite);
            q.setParameter("magasin", magasin);
            return q.getResultList();
        } else {
            Query q = getEntityManager().createNamedQuery("Inventaire.findByMagasinByPeriodeByCategorie");
            q.setParameter("d", debut);
            q.setParameter("d1", fin);
            q.setParameter("categorie", entite);
            q.setParameter("magasin", magasin);
            return q.getResultList();
        }

    }

    @Override
    public List<Inventaire> historiqueInventaireSociete(Date debut, Date fin, Integer societe, Integer entite, Integer type, Integer type1) {
        if (type == 1) {
            if (type1 == 0) {
                Query q = getEntityManager().createNamedQuery("Inventaire.findBySocieteByPeriodeByArticle");
                q.setParameter("d", debut);
                q.setParameter("d1", fin);
                q.setParameter("emballage", entite);
                q.setParameter("societe", societe);
                return q.getResultList();
            } else {
                Query q = getEntityManager().createNamedQuery("Inventaire.findBySocieteByPeriodeByEmballage");
                q.setParameter("d", debut);
                q.setParameter("d1", fin);
                q.setParameter("emballage", entite);
                q.setParameter("societe", societe);
                return q.getResultList();
            }

        } else {
            Query q = getEntityManager().createNamedQuery("Inventaire.findBySocieteByPeriodeByCategorie");
            q.setParameter("d", debut);
            q.setParameter("d1", fin);
            q.setParameter("categorie", entite);
            q.setParameter("societe", societe);
            return q.getResultList();
        }
    }
}
