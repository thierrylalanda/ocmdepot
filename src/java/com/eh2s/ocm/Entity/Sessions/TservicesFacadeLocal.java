/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.IllegalOrphanException;
import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Tservices;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface TservicesFacadeLocal {

    Tservices creer(Tservices tservices) throws RollbackFailureException, Exception;

    void MisAJour(Tservices tservices) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception;

    void Supprimer(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception;

    List<Tservices> findTservicesEntities();

    List<Tservices> findTservicesEntities(int maxResults, int firstResult);

    Tservices findTservices(Integer id);

    int getTservicesCount();

//fonctions extends AbstractFacade class
    void create(Tservices tservices);

    void edit(Tservices tservices);

    void remove(Tservices tservices);

    Tservices find(Object id);

    List<Tservices> findAll(String societe);

    List<Tservices> findRange(int[] range);

    int count();

    List<Tservices> findAllByRegion(int region);

}
