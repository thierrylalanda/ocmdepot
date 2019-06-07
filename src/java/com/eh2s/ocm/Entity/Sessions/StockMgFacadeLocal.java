/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.StockMg;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi01
 */
@Local
public interface StockMgFacadeLocal {

    void create(StockMg stockMg);

    void edit(StockMg stockMg);

    void remove(StockMg stockMg);

    StockMg find(Object id);

    List<StockMg> findAll();

    List<StockMg> findRange(int[] range);

    int count();

    StockMg creer(StockMg stockMg) throws RollbackFailureException, Exception;

    StockMg MisAJour(StockMg stockMg) throws RollbackFailureException, Exception;

    void Supprimer(Integer id) throws RollbackFailureException, Exception;

    List<StockMg> findStockMgEntities();

    List<StockMg> findStockMgEntities(int maxResults, int firstResult);

    StockMg findStockMg(Integer id);

    StockMg findStockArticleMg(Integer article, Integer Magasin);

    List<StockMg> findStockByMagasin(Integer id);

}
