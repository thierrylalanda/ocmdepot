/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Caisse;
import com.eh2s.ocm.Entity.Emballage;
import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Administrateur
 */
@Local
public interface EmballageFacadeLocal {

    void create(Emballage emballage);

    void edit(Emballage emballage);

    void remove(Emballage emballage);
    
    Emballage creer(Emballage emballage) throws RollbackFailureException, Exception;

    Emballage MisAjour(Emballage emballage) throws NonexistentEntityException, RollbackFailureException, Exception;

    void Supprimer(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception;

    Emballage find(Object id);

    List<Emballage> findAll();
    
    List<Emballage> findAll(int societe);

    List<Emballage> findRange(int[] range);

    int count();
    
}
