/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.IllegalOrphanException;
import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Tdistricts;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface TdistrictsFacadeLocal {

    Tdistricts creer(Tdistricts tdistricts) throws RollbackFailureException, Exception;

    void MisAJour(Tdistricts tdistricts) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception;

    void Supprimer(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception;

    List<Tdistricts> findTdistrictsEntities();

    List<Tdistricts> findTdistrictsEntities(int maxResults, int firstResult);

    Tdistricts findTdistricts(Integer id);

    int getTdistrictsCount();

//fonctions extends AbstractFacade class
    void create(Tdistricts tdistricts);

    void edit(Tdistricts tdistricts);

    void remove(Tdistricts tdistricts);

    Tdistricts find(Object id);

    List<Tdistricts> findAll(String societe);

    List<Tdistricts> findRange(int[] range);

    int count();

}
