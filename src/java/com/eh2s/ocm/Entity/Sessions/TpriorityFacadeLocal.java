/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.IllegalOrphanException;
import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Tpriority;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface TpriorityFacadeLocal {

    Tpriority creer(Tpriority tpriority) throws RollbackFailureException, Exception;

    void MisAJour(Tpriority tpriority) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception;

    void Supprimer(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception;

    List<Tpriority> findTpriorityEntities();

    List<Tpriority> findTpriorityEntities(int maxResults, int firstResult);

    Tpriority findTpriority(Integer id);

    int getTpriorityCount();

//fonctions extends AbstractFacade class
    void create(Tpriority tpriority);

    void edit(Tpriority tpriority);

    void remove(Tpriority tpriority);

    Tpriority find(Object id);

    List<Tpriority> findAll(String societe);

    List<Tpriority> findRange(int[] range);

    int count();

}
