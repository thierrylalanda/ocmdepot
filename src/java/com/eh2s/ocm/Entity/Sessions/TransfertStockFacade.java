/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.LigneTransfertStock;
import com.eh2s.ocm.Entity.Tarticles;
import com.eh2s.ocm.Entity.TransfertStock;
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
public class TransfertStockFacade extends AbstractFacade<TransfertStock> implements TransfertStockFacadeLocal {

    @PersistenceContext(unitName = "ccmanagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TransfertStockFacade() {
        super(TransfertStock.class);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public TransfertStock creer(TransfertStock transfertStock) throws RollbackFailureException, Exception {
        try {
            LigneTransfertStock ligneTransfert = transfertStock.getLigneTransfert();
            if (ligneTransfert != null) {
                ligneTransfert = getEntityManager().getReference(ligneTransfert.getClass(), ligneTransfert.getId());
                transfertStock.setLigneTransfert(ligneTransfert);
            }
            Tarticles article = transfertStock.getArticle();
            if (article != null) {
                article = getEntityManager().getReference(article.getClass(), article.getId());
                transfertStock.setArticle(article);
            }
            transfertStock = getEntityManager().merge(transfertStock);
            if (ligneTransfert != null) {
                ligneTransfert.getTransfertStockList().add(transfertStock);
                ligneTransfert = getEntityManager().merge(ligneTransfert);
            }
            if (article != null) {
                article.getTransfertStockList().add(transfertStock);
                article = getEntityManager().merge(article);
            }
            return transfertStock;
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public TransfertStock MisAJour(TransfertStock transfertStock) throws RollbackFailureException, Exception {
        try {
            TransfertStock persistentTransfertStock = getEntityManager().find(TransfertStock.class, transfertStock.getId());
            LigneTransfertStock ligneTransfertOld = persistentTransfertStock.getLigneTransfert();
            LigneTransfertStock ligneTransfertNew = transfertStock.getLigneTransfert();
            Tarticles articleOld = persistentTransfertStock.getArticle();
            Tarticles articleNew = transfertStock.getArticle();
            if (ligneTransfertNew != null) {
                ligneTransfertNew = getEntityManager().getReference(ligneTransfertNew.getClass(), ligneTransfertNew.getId());
                transfertStock.setLigneTransfert(ligneTransfertNew);
            }
            if (articleNew != null) {
                articleNew = getEntityManager().getReference(articleNew.getClass(), articleNew.getId());
                transfertStock.setArticle(articleNew);
            }
            transfertStock = getEntityManager().merge(transfertStock);
            if (ligneTransfertOld != null && !ligneTransfertOld.equals(ligneTransfertNew)) {
                ligneTransfertOld.getTransfertStockList().remove(transfertStock);
                ligneTransfertOld = getEntityManager().merge(ligneTransfertOld);
            }
            if (ligneTransfertNew != null && !ligneTransfertNew.equals(ligneTransfertOld)) {
                ligneTransfertNew.getTransfertStockList().add(transfertStock);
                ligneTransfertNew = getEntityManager().merge(ligneTransfertNew);
            }
            if (articleOld != null && !articleOld.equals(articleNew)) {
                articleOld.getTransfertStockList().remove(transfertStock);
                articleOld = getEntityManager().merge(articleOld);
            }
            if (articleNew != null && !articleNew.equals(articleOld)) {
                articleNew.getTransfertStockList().add(transfertStock);
                articleNew = getEntityManager().merge(articleNew);
            }
            return transfertStock;
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void Supprimer(Integer id) throws RollbackFailureException, Exception {
        try {
            TransfertStock transfertStock;
            try {
                transfertStock = getEntityManager().getReference(TransfertStock.class, id);
                transfertStock.getId();
            } catch (Exception enfe) {
                throw new Exception("The transfertStock with id " + id + " no longer exists.", enfe);
            }
            LigneTransfertStock ligneTransfert = transfertStock.getLigneTransfert();
            if (ligneTransfert != null) {
                ligneTransfert.getTransfertStockList().remove(transfertStock);
                ligneTransfert = getEntityManager().merge(ligneTransfert);
            }
            Tarticles article = transfertStock.getArticle();
            if (article != null) {
                article.getTransfertStockList().remove(transfertStock);
                article = getEntityManager().merge(article);
            }
            getEntityManager().remove(transfertStock);
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @Override
    public List<TransfertStock> findTransfertStockEntities() {
        return findTransfertStockEntities(true, -1, -1);
    }

    @Override
    public List<TransfertStock> findTransfertStockEntities(int maxResults, int firstResult) {
        return findTransfertStockEntities(false, maxResults, firstResult);
    }

    private List<TransfertStock> findTransfertStockEntities(boolean all, int maxResults, int firstResult) {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(TransfertStock.class));
        Query q = getEntityManager().createQuery(cq);
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    public TransfertStock findTransfertStock(Integer id) {
        return getEntityManager().find(TransfertStock.class, id);
    }
    
    @Override
    public List<TransfertStock> findTransfertStockByLigne(int ligne) {
        Query q = getEntityManager().createNamedQuery("TransfertStock.findByLigne");
        q.setParameter("id", ligne);
        return q.getResultList();
    }

}
