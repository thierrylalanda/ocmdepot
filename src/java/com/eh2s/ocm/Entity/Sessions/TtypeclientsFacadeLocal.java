/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.IllegalOrphanException;
import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Ttypeclients;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface TtypeclientsFacadeLocal {

    Ttypeclients creer(Ttypeclients ttypeclients) throws RollbackFailureException, Exception;

    void MisAJour(Ttypeclients ttypeclients) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception;

    void Supprimer(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception;

    List<Ttypeclients> findTtypeclientsEntities();

    List<Ttypeclients> findTtypeclientsEntities(int maxResults, int firstResult);

    Ttypeclients findTtypeclients(Integer id);

    int getTtypeclientsCount();

//fonctions extends AbstractFacade class
    void create(Ttypeclients ttypeclients);

    void edit(Ttypeclients ttypeclients);

    void remove(Ttypeclients ttypeclients);

    Ttypeclients find(Object id);

    List<Ttypeclients> findAll(String societe);

    List<Ttypeclients> findRange(int[] range);

    int count();

}
