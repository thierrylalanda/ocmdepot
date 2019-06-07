/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.IllegalOrphanException;
import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Tregions;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface TregionsFacadeLocal {

    Tregions creer(Tregions tregions) throws RollbackFailureException, Exception;

    void MisAJour(Tregions regions) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception;

    void Supprimer(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception;

    List<Tregions> findTregionsEntities();

    List<Tregions> findTregionsEntities(int maxResults, int firstResult);

    Tregions findTregions(Integer id);

    int getTregionsCount();

//fonctions extends AbstractFacade class
    void create(Tregions tregions);

    void edit(Tregions tregions);

    void remove(Tregions tregions);

    Tregions find(Object id);

    List<Tregions> findAll(String societe);

    List<Tregions> findRange(int[] range);

    int count();

}
