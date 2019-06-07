/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.IllegalOrphanException;
import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Tclients;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface TclientsFacadeLocal {

    Tclients creer(Tclients tclients) throws RollbackFailureException, Exception;

    Tclients MisAJour(Tclients tclients) throws NonexistentEntityException, RollbackFailureException, Exception;

    void Supprimer(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception;

    List<Tclients> findTclientsEntities();

    List<Tclients> findTclientsEntities(int maxResults, int firstResult);

    Tclients findTclients(Integer id);

    int getTclientsCount();

//fonctions extends AbstractFacade class
    void create(Tclients tclients);

    void edit(Tclients tclients);

    void remove(Tclients tclients);

    Tclients find(Object id);

    Tclients findByCodeclient(String codeclient);

    List<Tclients> findAll(String societe);

    List<Tclients> findRange(int[] range);

    int count();

    List<Tclients> findTclientsByRegion(int region, String societe, int entity);
}
