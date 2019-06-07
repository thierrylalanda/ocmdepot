/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.IllegalOrphanException;
import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Tsites;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface TsitesFacadeLocal {

    Tsites creer(Tsites tsites) throws RollbackFailureException, Exception;

    void MisAJour(Tsites tsites) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception;

    void Supprimer(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception;

    List<Tsites> findTsitesEntities();

    List<Tsites> findTsitesEntities(int maxResults, int firstResult);

    Tsites findTsites(Integer id);

    int getTsitesCount();

//fonctions extends AbstractFacade class
    void create(Tsites tsites);

    void edit(Tsites tsites);

    void remove(Tsites tsites);

    Tsites find(Object id);

    List<Tsites> findAll(String societe);

    List<Tsites> findRange(int[] range);

    int count();

}
