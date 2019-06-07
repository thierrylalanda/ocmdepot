/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.IllegalOrphanException;
import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Tactions;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface TactionsFacadeLocal {

    Tactions creer(Tactions tactions) throws RollbackFailureException, Exception;

    void MisAJour(Tactions tactions) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception;

    void Supprimer(Integer id) throws  NonexistentEntityException, RollbackFailureException, Exception;

    List<Tactions> findTactionsEntities();

    List<Tactions> findTactionsEntities(int maxResults, int firstResult);

    Tactions findTactions(Integer id);

    int getTactionsCount();

//fonctions extends AbstractFacade class
    void create(Tactions tactions);

    void edit(Tactions tactions);

    void remove(Tactions tactions);

    Tactions find(Object id);

    List<Tactions> findAll();

    List<Tactions> findRange(int[] range);

    int count();

}
