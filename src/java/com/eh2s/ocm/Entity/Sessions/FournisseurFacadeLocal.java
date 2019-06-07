/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Fournisseur;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Administrateur
 */
@Local
public interface FournisseurFacadeLocal {

    void create(Fournisseur fournisseur);

    void edit(Fournisseur fournisseur);

    void remove(Fournisseur fournisseur);

    Fournisseur find(Object id);

    List<Fournisseur> findAll();
    
     List<Fournisseur> findFournisseurBySociete(int societe);

    List<Fournisseur> findRange(int[] range);

     Fournisseur creer(Fournisseur fournisseur) throws RollbackFailureException, Exception;

    Fournisseur MisAjour(Fournisseur fournisseur) throws NonexistentEntityException, RollbackFailureException, Exception;

    void Supprimer(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception;
    int count();
    
}
