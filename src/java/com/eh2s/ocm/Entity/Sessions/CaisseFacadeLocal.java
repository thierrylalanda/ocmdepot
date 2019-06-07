/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Caisse;
import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import java.sql.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Administrateur
 */
@Local
public interface CaisseFacadeLocal {

    void create(Caisse caisse);

    void edit(Caisse caisse);

    void remove(Caisse caisse);

    Caisse find(Object id);

    Caisse findCaisseDuJour(int societe);
    
    Caisse findCaisseByDate(Date d, int societe);

    List<Caisse> findAll();

    List<Caisse> findAll(int societe);

    List<Caisse> findRange(int[] range);

    Caisse creer(Caisse caisse) throws RollbackFailureException, Exception;

    Caisse MisAjour(Caisse caisse) throws NonexistentEntityException, RollbackFailureException, Exception;

    void Supprimer(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception;
    int count();

}
