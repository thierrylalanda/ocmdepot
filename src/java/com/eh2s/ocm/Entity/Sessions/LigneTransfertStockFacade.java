/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.IllegalOrphanException;
import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.LigneTransfertStock;
import com.eh2s.ocm.Entity.Magasin;
import com.eh2s.ocm.Entity.TransfertStock;
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

/**
 *
 * @author messi01
 */
@Stateless
public class LigneTransfertStockFacade extends AbstractFacade<LigneTransfertStock> implements LigneTransfertStockFacadeLocal {

    @PersistenceContext(unitName = "ccmanagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LigneTransfertStockFacade() {
        super(LigneTransfertStock.class);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public LigneTransfertStock creer(LigneTransfertStock lignetransfertstock) throws RollbackFailureException, Exception {
        if (lignetransfertstock.getTransfertStockList() == null) {
            lignetransfertstock.setTransfertStockList(new ArrayList<TransfertStock>());
        }
        try {
            Magasin mg1 = lignetransfertstock.getMg1();
            if (mg1 != null) {
                mg1 = getEntityManager().getReference(mg1.getClass(), mg1.getId());
                lignetransfertstock.setMg1(mg1);
            }
            Magasin mg2 = lignetransfertstock.getMg2();
            if (mg2 != null) {
                mg2 = getEntityManager().getReference(mg2.getClass(), mg2.getId());
                lignetransfertstock.setMg2(mg2);
            }
            List<TransfertStock> attachedTransfertStockList = new ArrayList<TransfertStock>();
            for (TransfertStock transfertStockListTransfertStockToAttach : lignetransfertstock.getTransfertStockList()) {
                transfertStockListTransfertStockToAttach = getEntityManager().getReference(transfertStockListTransfertStockToAttach.getClass(), transfertStockListTransfertStockToAttach.getId());
                attachedTransfertStockList.add(transfertStockListTransfertStockToAttach);
            }
            lignetransfertstock.setTransfertStockList(attachedTransfertStockList);
            getEntityManager().persist(lignetransfertstock);
            if (mg1 != null) {
                mg1.getLignetransfertstockList().add(lignetransfertstock);
                mg1 = getEntityManager().merge(mg1);
            }
            if (mg2 != null) {
                mg2.getLignetransfertstockList().add(lignetransfertstock);
                mg2 = getEntityManager().merge(mg2);
            }
            for (TransfertStock transfertStockListTransfertStock : lignetransfertstock.getTransfertStockList()) {
                LigneTransfertStock oldLigneTransfertOfTransfertStockListTransfertStock = transfertStockListTransfertStock.getLigneTransfert();
                transfertStockListTransfertStock.setLigneTransfert(lignetransfertstock);
                transfertStockListTransfertStock = getEntityManager().merge(transfertStockListTransfertStock);
                if (oldLigneTransfertOfTransfertStockListTransfertStock != null) {
                    oldLigneTransfertOfTransfertStockListTransfertStock.getTransfertStockList().remove(transfertStockListTransfertStock);
                    oldLigneTransfertOfTransfertStockListTransfertStock = getEntityManager().merge(oldLigneTransfertOfTransfertStockListTransfertStock);
                }
            }
            return lignetransfertstock;
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public LigneTransfertStock MisAJour(LigneTransfertStock lignetransfertstock) throws RollbackFailureException, Exception {
        try {
            LigneTransfertStock persistentLignetransfertstock = getEntityManager().find(LigneTransfertStock.class, lignetransfertstock.getId());
            Magasin mg1Old = persistentLignetransfertstock.getMg1();
            Magasin mg1New = lignetransfertstock.getMg1();
            Magasin mg2Old = persistentLignetransfertstock.getMg2();
            Magasin mg2New = lignetransfertstock.getMg2();
            List<TransfertStock> transfertStockListOld = persistentLignetransfertstock.getTransfertStockList();
            List<TransfertStock> transfertStockListNew = lignetransfertstock.getTransfertStockList();

            if (mg1New != null) {
                mg1New = getEntityManager().getReference(mg1New.getClass(), mg1New.getId());
                lignetransfertstock.setMg1(mg1New);
            }
            if (mg2New != null) {
                mg2New = getEntityManager().getReference(mg2New.getClass(), mg2New.getId());
                lignetransfertstock.setMg2(mg2New);
            }
            List<TransfertStock> attachedTransfertStockListNew = new ArrayList<TransfertStock>();
            for (TransfertStock transfertStockListNewTransfertStockToAttach : transfertStockListNew) {
                transfertStockListNewTransfertStockToAttach = getEntityManager().getReference(transfertStockListNewTransfertStockToAttach.getClass(), transfertStockListNewTransfertStockToAttach.getId());
                attachedTransfertStockListNew.add(transfertStockListNewTransfertStockToAttach);
            }
            transfertStockListNew = attachedTransfertStockListNew;
            lignetransfertstock.setTransfertStockList(transfertStockListNew);
            lignetransfertstock = getEntityManager().merge(lignetransfertstock);
            if (mg1Old != null && !mg1Old.equals(mg1New)) {
                mg1Old.getLignetransfertstockList().remove(lignetransfertstock);
                mg1Old = getEntityManager().merge(mg1Old);
            }
            if (mg1New != null && !mg1New.equals(mg1Old)) {
                mg1New.getLignetransfertstockList().add(lignetransfertstock);
                mg1New = getEntityManager().merge(mg1New);
            }
            if (mg2Old != null && !mg2Old.equals(mg2New)) {
                mg2Old.getLignetransfertstockList().remove(lignetransfertstock);
                mg2Old = getEntityManager().merge(mg2Old);
            }
            if (mg2New != null && !mg2New.equals(mg2Old)) {
                mg2New.getLignetransfertstockList().add(lignetransfertstock);
                mg2New = getEntityManager().merge(mg2New);
            }
            for (TransfertStock transfertStockListNewTransfertStock : transfertStockListNew) {
                if (!transfertStockListOld.contains(transfertStockListNewTransfertStock)) {
                    LigneTransfertStock oldLigneTransfertOfTransfertStockListNewTransfertStock = transfertStockListNewTransfertStock.getLigneTransfert();
                    transfertStockListNewTransfertStock.setLigneTransfert(lignetransfertstock);
                    transfertStockListNewTransfertStock = getEntityManager().merge(transfertStockListNewTransfertStock);
                    if (oldLigneTransfertOfTransfertStockListNewTransfertStock != null && !oldLigneTransfertOfTransfertStockListNewTransfertStock.equals(lignetransfertstock)) {
                        oldLigneTransfertOfTransfertStockListNewTransfertStock.getTransfertStockList().remove(transfertStockListNewTransfertStock);
                        oldLigneTransfertOfTransfertStockListNewTransfertStock = getEntityManager().merge(oldLigneTransfertOfTransfertStockListNewTransfertStock);
                    }
                }
            }
            return lignetransfertstock;
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void Supprimer(Integer id) throws RollbackFailureException, Exception {
        try {
            LigneTransfertStock lignetransfertstock;
            try {
                lignetransfertstock = getEntityManager().getReference(LigneTransfertStock.class, id);
                lignetransfertstock.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The lignetransfertstock with id " + id + " no longer exists.", enfe);
            }

            Magasin mg1 = lignetransfertstock.getMg1();
            if (mg1 != null) {
                mg1.getLignetransfertstockList().remove(lignetransfertstock);
                mg1 = getEntityManager().merge(mg1);
            }
            Magasin mg2 = lignetransfertstock.getMg2();
            if (mg2 != null) {
                mg2.getLignetransfertstockList().remove(lignetransfertstock);
                mg2 = getEntityManager().merge(mg2);
            }
            getEntityManager().remove(lignetransfertstock);
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @Override
    public List<LigneTransfertStock> findLigneTransfertStockEntities() {
        return findLigneTransfertStockEntities(true, -1, -1);
    }

    @Override
    public List<LigneTransfertStock> findLigneTransfertStockEntities(int maxResults, int firstResult) {
        return findLigneTransfertStockEntities(false, maxResults, firstResult);
    }

    private List<LigneTransfertStock> findLigneTransfertStockEntities(boolean all, int maxResults, int firstResult) {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(LigneTransfertStock.class));
        Query q = getEntityManager().createQuery(cq);
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    @Override
    public LigneTransfertStock findLigneTransfertStock(Integer id) {
        return getEntityManager().find(LigneTransfertStock.class, id);
    }
}
