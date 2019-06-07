/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Tgroups;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface TgroupsFacadeLocal {

    Tgroups creer(Tgroups tgroups) throws RollbackFailureException, Exception;

    void MisAJour(Tgroups tgroups) throws NonexistentEntityException, RollbackFailureException, Exception;

    void Supprimer(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception;

    List<Tgroups> findTgroupsEntities();

    List<Tgroups> findTgroupsEntities(int maxResults, int firstResult);

    Tgroups findTgroups(Integer id);

    int getTgroupsCount();

//fonctions extends AbstractFacade class
    void create(Tgroups tgroups);

    void edit(Tgroups tgroups);

    void remove(Tgroups tgroups);

    Tgroups find(Object id);

    List<Tgroups> findAll(String societe);

    List<Tgroups> findRange(int[] range);

    int count();

}
