/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.IllegalOrphanException;
import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Tsmenu;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface TsmenuFacadeLocal {

    Tsmenu creer(Tsmenu tsmenu) throws RollbackFailureException, Exception;

    void MisAJour(Tsmenu tsmenu) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception;

    List<Tsmenu> findTsmenuEntities();

    Tsmenu findTsmenu(Integer id);

    void create(Tsmenu tsmenu);

    void edit(Tsmenu tsmenu);

    void remove(Tsmenu tsmenu);

    Tsmenu find(Object id);

    List<Tsmenu> findAll();

    List<Tsmenu> findRange(int[] range);

    int count();

}
