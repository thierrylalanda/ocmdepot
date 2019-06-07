/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;
import com.eh2s.ocm.Entity.CompteRistourne;
import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Administrateur
 */
@Local
public interface CompteRistourneFacadeLocal {

    void create(CompteRistourne compteRistourne);

    void edit(CompteRistourne compteRistourne);

    void remove(CompteRistourne compteRistourne);

    CompteRistourne creer(CompteRistourne compteRistourne) throws RollbackFailureException, Exception;

    CompteRistourne MisAjour(CompteRistourne compteRistourne) throws NonexistentEntityException, RollbackFailureException, Exception;

    void Supprimer(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception;
    CompteRistourne find(Object id);

    List<CompteRistourne> findAll();

    List<CompteRistourne> findAll(int societe);

    List<CompteRistourne> findAllByClient(int client);

    List<CompteRistourne> findRange(int[] range);

    int count();

}
