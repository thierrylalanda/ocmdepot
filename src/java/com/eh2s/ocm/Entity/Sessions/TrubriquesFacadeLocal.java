/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.IllegalOrphanException;
import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Trubriques;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface TrubriquesFacadeLocal {

    Trubriques creer(Trubriques trubriques) throws RollbackFailureException, Exception;

    void MisAJour(Trubriques trubriques) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception;

    void Supprimer(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception;

    List<Trubriques> findTrubriquesEntities();

    List<Trubriques> findTrubriquesEntities(int maxResults, int firstResult);

    Trubriques findTrubriques(Integer id);

    int getTrubriquesCount();

//fonctions extends AbstractFacade class
    void create(Trubriques trubriques);

    void edit(Trubriques trubriques);

    void remove(Trubriques trubriques);

    Trubriques find(Object id);

    List<Trubriques> findAll(String societe);

    List<Trubriques> findRange(int[] range);

    int count();

}
