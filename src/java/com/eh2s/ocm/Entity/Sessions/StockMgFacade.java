/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Magasin;
import com.eh2s.ocm.Entity.StockMg;
import com.eh2s.ocm.Entity.Tarticles;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author messi01
 */
@Stateless
public class StockMgFacade extends AbstractFacade<StockMg> implements StockMgFacadeLocal {

    @PersistenceContext(unitName = "ccmanagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public StockMgFacade() {
        super(StockMg.class);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public StockMg creer(StockMg stockMg) throws RollbackFailureException, Exception {
        try {
            Magasin magasin = stockMg.getMagasin();
            if (magasin != null) {
                magasin = getEntityManager().getReference(magasin.getClass(), magasin.getId());
                stockMg.setMagasin(magasin);
            }
            Tarticles article = stockMg.getArticle();
            if (article != null) {
                article = getEntityManager().getReference(article.getClass(), article.getId());
                stockMg.setArticle(article);
            }
            stockMg = getEntityManager().merge(stockMg);
            if (magasin != null) {
                magasin.getStockMgList().add(stockMg);
                magasin = getEntityManager().merge(magasin);
            }
            if (article != null) {
                article.getStockMgList().add(stockMg);
                article = getEntityManager().merge(article);
            }
            return stockMg;
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public StockMg MisAJour(StockMg stockMg) throws RollbackFailureException, Exception {
        try {
            StockMg persistentStockMg = getEntityManager().find(StockMg.class, stockMg.getId());
            Magasin magasinOld = persistentStockMg.getMagasin();
            Magasin magasinNew = stockMg.getMagasin();
            Tarticles articleOld = persistentStockMg.getArticle();
            Tarticles articleNew = stockMg.getArticle();
            if (magasinNew != null) {
                magasinNew = getEntityManager().getReference(magasinNew.getClass(), magasinNew.getId());
                stockMg.setMagasin(magasinNew);
            }
            if (articleNew != null) {
                articleNew = getEntityManager().getReference(articleNew.getClass(), articleNew.getId());
                stockMg.setArticle(articleNew);
            }
            stockMg = getEntityManager().merge(stockMg);
            if (magasinOld != null && !magasinOld.equals(magasinNew)) {
                magasinOld.getStockMgList().remove(stockMg);
                magasinOld = getEntityManager().merge(magasinOld);
            }
            if (magasinNew != null && !magasinNew.equals(magasinOld)) {
                magasinNew.getStockMgList().add(stockMg);
                magasinNew = getEntityManager().merge(magasinNew);
            }
            if (articleOld != null && !articleOld.equals(articleNew)) {
                articleOld.getStockMgList().remove(stockMg);
                articleOld = getEntityManager().merge(articleOld);
            }
            if (articleNew != null && !articleNew.equals(articleOld)) {
                articleNew.getStockMgList().add(stockMg);
                articleNew = getEntityManager().merge(articleNew);
            }
            return stockMg;
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }

    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void Supprimer(Integer id) throws RollbackFailureException, Exception {
        try {
            StockMg stockMg;
            try {
                stockMg = getEntityManager().getReference(StockMg.class, id);
                stockMg.getId();
            } catch (Exception enfe) {
                throw new RollbackFailureException("The stockMg with id " + id + " no longer exists.", enfe);
            }
            Magasin magasin = stockMg.getMagasin();
            if (magasin != null) {
                magasin.getStockMgList().remove(stockMg);
                magasin = getEntityManager().merge(magasin);
            }
            Tarticles article = stockMg.getArticle();
            if (article != null) {
                article.getStockMgList().remove(stockMg);
                article = getEntityManager().merge(article);
            }
            getEntityManager().remove(stockMg);
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @Override
    public List<StockMg> findStockMgEntities() {
        return findStockMgEntities(true, -1, -1);
    }

    @Override
    public List<StockMg> findStockMgEntities(int maxResults, int firstResult) {
        return findStockMgEntities(false, maxResults, firstResult);
    }

    private List<StockMg> findStockMgEntities(boolean all, int maxResults, int firstResult) {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(StockMg.class));
        Query q = getEntityManager().createQuery(cq);
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    @Override
    public StockMg findStockMg(Integer id) {
        return getEntityManager().find(StockMg.class, id);
    }
    @Override
    public List<StockMg> findStockByMagasin(Integer id) {
        Query q = getEntityManager().createNamedQuery("StockMg.findByMagasin");
        q.setParameter("id", id);
        return q.getResultList();
    }

    @Override
    public StockMg findStockArticleMg(Integer article, Integer Magasin) {
        Query q = getEntityManager().createNamedQuery("StockMg.findByArticleMagasin");
        q.setParameter("article", article);
        q.setParameter("magasin", Magasin);
        return (StockMg)q.getSingleResult();
    }

}
