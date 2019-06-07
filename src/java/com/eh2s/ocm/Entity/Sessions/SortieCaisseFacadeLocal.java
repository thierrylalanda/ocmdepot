/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.SortieCaisse;
import java.sql.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Administrateur
 */
@Local
public interface SortieCaisseFacadeLocal {

    void create(SortieCaisse sortieCaisse);

    void edit(SortieCaisse sortieCaisse);

    void remove(SortieCaisse sortieCaisse);
    
     SortieCaisse creer(SortieCaisse sortieCaisse) throws RollbackFailureException, Exception;

    SortieCaisse MisAJour(SortieCaisse sortieCaisse) throws RollbackFailureException, Exception;

    void Supprimer(Integer id) throws RollbackFailureException, Exception;

    SortieCaisse find(Object id);

    List<SortieCaisse> findAll();
    
    List<SortieCaisse> findAll(int societe);
    
    List<SortieCaisse> findAllToDay(int societe);
    
    List<SortieCaisse> findAllByPeriode(Date d, Date d1, int societe);

    List<SortieCaisse> findRange(int[] range);

    int count();
    
}
