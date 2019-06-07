/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.TransfertStock;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi01
 */
@Local
public interface TransfertStockFacadeLocal {

    void create(TransfertStock transfertStock);

    void edit(TransfertStock transfertStock);

    void remove(TransfertStock transfertStock);

    TransfertStock find(Object id);

    List<TransfertStock> findAll();

    List<TransfertStock> findRange(int[] range);

    int count();

    TransfertStock creer(TransfertStock transfertStock) throws RollbackFailureException, Exception;

    TransfertStock MisAJour(TransfertStock transfertStock) throws RollbackFailureException, Exception;

    void Supprimer(Integer id) throws RollbackFailureException, Exception;

    List<TransfertStock> findTransfertStockEntities();

    List<TransfertStock> findTransfertStockEntities(int maxResults, int firstResult);

    TransfertStock findTransfertStock(Integer id);
    
    List<TransfertStock> findTransfertStockByLigne(int ligne);

}
