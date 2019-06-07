/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.IllegalOrphanException;
import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Tsecteurs;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface TsecteursFacadeLocal {

    Tsecteurs creer(Tsecteurs tsecteurs) throws RollbackFailureException, Exception;

    void MisAJour(Tsecteurs tsecteurs) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception;

    void Supprimer(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception;

    List<Tsecteurs> findTsecteursEntities();

    List<Tsecteurs> findTsecteursEntities(int maxResults, int firstResult);

    Tsecteurs findTsecteurs(Integer id);

    int getTsecteursCount();

//fonctions extends AbstractFacade class
    void create(Tsecteurs tsecteurs);

    void edit(Tsecteurs tsecteurs);

    void remove(Tsecteurs tsecteurs);

    Tsecteurs find(Object id);

    List<Tsecteurs> findAll(String societe);

    List<Tsecteurs> findRange(int[] range);

    int count();

    List<Tsecteurs> findAllByRegion(int region);

}
