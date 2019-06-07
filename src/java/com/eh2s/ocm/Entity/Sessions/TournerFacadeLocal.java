/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Tourner;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi01
 */
@Local
public interface TournerFacadeLocal {

    void create(Tourner tourner);

    void edit(Tourner tourner);

    void remove(Tourner tourner);

    Tourner find(Object id);

    Tourner findByNum(String numtr);

    List<Tourner> findAll();
    
    List<Tourner> findAll(int societe);

    List<Tourner> findRange(int[] range);

    int count();

    //
    Tourner creer(Tourner tourner) throws RollbackFailureException, Exception;

    Tourner MisAJour(Tourner tourner) throws NonexistentEntityException, RollbackFailureException, Exception;

    void Supprimer(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception;

    List<Tourner> findTournerEntities();

    Tourner findTourner(Integer id);
}
