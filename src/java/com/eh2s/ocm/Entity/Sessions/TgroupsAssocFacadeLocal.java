/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.IllegalOrphanException;
import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.TgroupsAssoc;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface TgroupsAssocFacadeLocal {

    TgroupsAssoc creer(TgroupsAssoc tgroupsAssoc) throws RollbackFailureException, Exception;

    void MisAJour(TgroupsAssoc tgroupsAssoc) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception;

    void Supprimer(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception;

    List<TgroupsAssoc> findTgroupsAssocEntities();

    List<TgroupsAssoc> findTgroupsAssocEntities(int maxResults, int firstResult);

    TgroupsAssoc findTgroupsAssoc(Integer id);

    int getTgroupsAssocCount();
    
    void deletteByGroup(Integer group);
    
    List<TgroupsAssoc>  GroupAssocieteByGroup(Integer group);

//fonctions extends AbstractFacade class
    void create(TgroupsAssoc tgroupsAssoc);

    void edit(TgroupsAssoc tgroupsAssoc);

    void remove(TgroupsAssoc tgroupsAssoc);

    TgroupsAssoc find(Object id);

    List<TgroupsAssoc> findAll();

    List<TgroupsAssoc> findRange(int[] range);

    int count();

}
