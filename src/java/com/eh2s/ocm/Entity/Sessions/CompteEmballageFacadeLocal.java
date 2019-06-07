/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.CommandeFournisseur;
import com.eh2s.ocm.Entity.CompteEmballage;
import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Administrateur
 */
@Local
public interface CompteEmballageFacadeLocal {

    void create(CompteEmballage compteEmballage);

    void edit(CompteEmballage compteEmballage);

    void remove(CompteEmballage compteEmballage);

     CompteEmballage creer(CompteEmballage compteEmballage) throws RollbackFailureException, Exception;

    CompteEmballage MisAjour(CompteEmballage compteEmballage) throws NonexistentEntityException, RollbackFailureException, Exception;

    void Supprimer(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception;
    
    CompteEmballage find(Object id);

    List<CompteEmballage> findAll();
    
    List<CompteEmballage> findAll(int societe);
    
    List<CompteEmballage> findAllByCLient(int client);
    
    CompteEmballage findAllByCLientAndEmballage(int client, int emballage);

    List<CompteEmballage> findRange(int[] range);

    int count();
    
}
