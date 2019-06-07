/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.IllegalOrphanException;
import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Tusers;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface TusersFacadeLocal {

    Tusers creer(Tusers Tusers) throws RollbackFailureException, Exception;

    Tusers MisAJour(Tusers Tusers) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception;

    void Supprimer(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception;

    List<Tusers> findTusersEntities();

    List<Tusers> findTusersEntities(int maxResults, int firstResult);

    Tusers findTusers(Integer id);

    int getTusersCount();

//fonctions extends AbstractFacade class
    void create(Tusers tusers);

    void edit(Tusers tusers);

    void remove(Tusers tusers);

    Tusers find(Object id);

    Tusers findByLogin(String login);

    List<Tusers> findAll(String societe);

    List<Tusers> findRange(int[] range);

    int count();

    List<Tusers> findTclientsByRegion(int region, String societe);
}
