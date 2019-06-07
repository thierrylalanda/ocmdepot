/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.LigneAccount;
import java.sql.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Administrateur
 */
@Local
public interface LigneAccountFacadeLocal {

    void create(LigneAccount ligneAccount);

    void edit(LigneAccount ligneAccount);

    void remove(LigneAccount ligneAccount);

    LigneAccount find(Object id);

    List<LigneAccount> findAll();

    List<LigneAccount> findRange(int[] range);

    List<LigneAccount> findBySociete(int societe);
    
    List<LigneAccount> findByClient(int client);
    
    List<LigneAccount> historiqueAccount(Date debut, Date fin,Integer type,Integer entite, Integer etat);

    int count();

    LigneAccount creer(LigneAccount ligneAccount) throws RollbackFailureException, Exception;

    LigneAccount MisAjour(LigneAccount ligneAccount) throws NonexistentEntityException, RollbackFailureException, Exception;

    void Supprimer(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception;
    
    List<LigneAccount> findByPeriodeSociete(Date debut, Date fin, int societe);
}
