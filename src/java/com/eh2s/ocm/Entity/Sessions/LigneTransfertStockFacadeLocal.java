/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.LigneTransfertStock;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi01
 */
@Local
public interface LigneTransfertStockFacadeLocal {

    void create(LigneTransfertStock ligneTransfertStock);

    void edit(LigneTransfertStock ligneTransfertStock);

    void remove(LigneTransfertStock ligneTransfertStock);

    LigneTransfertStock find(Object id);

    List<LigneTransfertStock> findAll();

    List<LigneTransfertStock> findRange(int[] range);

    int count();

    LigneTransfertStock creer(LigneTransfertStock ligneTransfertStock) throws RollbackFailureException, Exception;

    LigneTransfertStock MisAJour(LigneTransfertStock ligneTransfertStock) throws RollbackFailureException, Exception;

    void Supprimer(Integer id) throws RollbackFailureException, Exception;

    List<LigneTransfertStock> findLigneTransfertStockEntities();

    List<LigneTransfertStock> findLigneTransfertStockEntities(int maxResults, int firstResult);

    LigneTransfertStock findLigneTransfertStock(Integer id);
}
