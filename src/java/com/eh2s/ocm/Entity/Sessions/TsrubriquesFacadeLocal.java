/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.IllegalOrphanException;
import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Tsrubriques;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface TsrubriquesFacadeLocal {

    Tsrubriques creer(Tsrubriques Tsrubriques) throws RollbackFailureException, Exception;

    void MisAJour(Tsrubriques Tsrubriques) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception;

    void Supprimer(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception;

    List<Tsrubriques> findTsrubriquesEntities();

    List<Tsrubriques> findTsrubriquesEntities(int maxResults, int firstResult);

    Tsrubriques findTsrubriques(Integer id);

    int getTsrubriquesCount();

    List<Tsrubriques> findTsrubriquesByRubrique(int rubrique);

//fonctions extends AbstractFacade class
    void create(Tsrubriques tsrubriques);

    void edit(Tsrubriques tsrubriques);

    void remove(Tsrubriques tsrubriques);

    Tsrubriques find(Object id);

    List<Tsrubriques> findAll(String societe);

    List<Tsrubriques> findRange(int[] range);

    int count();

}
