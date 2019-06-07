/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.IllegalOrphanException;
import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Tsources;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface TsourcesFacadeLocal {

    Tsources creer(Tsources Tsources) throws RollbackFailureException, Exception;

    void MisAJour(Tsources Tsources) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception;

    void Supprimer(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception;

    List<Tsources> findTsourcesEntities();

    List<Tsources> findTsourcesEntities(int maxResults, int firstResult);

    Tsources findTsources(Integer id);

    int getTsourcesCount();

//fonctions extends AbstractFacade class
    void create(Tsources tsources);

    void edit(Tsources tsources);

    void remove(Tsources tsources);

    Tsources find(Object id);

    List<Tsources> findAll(String societe);

    List<Tsources> findRange(int[] range);

    int count();

}
