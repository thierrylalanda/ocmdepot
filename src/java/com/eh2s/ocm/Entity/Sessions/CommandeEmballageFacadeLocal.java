/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.CommandeEmballage;
import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Administrateur
 */
@Local
public interface CommandeEmballageFacadeLocal {

    void create(CommandeEmballage commandeEmballage);

    void edit(CommandeEmballage commandeEmballage);

    void remove(CommandeEmballage commandeEmballage);

    CommandeEmballage find(Object id);

    List<CommandeEmballage> findAll();

    CommandeEmballage creer(CommandeEmballage commandeEmballage) throws RollbackFailureException, Exception;

    CommandeEmballage MisAjour(CommandeEmballage commandeEmballage) throws NonexistentEntityException, RollbackFailureException, Exception;

    void Supprimer(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception;
    
    List<CommandeEmballage> findRange(int[] range);

    int count();
    
}
