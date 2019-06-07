/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.CommandeFournisseur;
import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Tcommandes;
import java.sql.Date;
import java.util.List;
import java.util.Set;
import javax.ejb.Local;

/**
 *
 * @author Administrateur
 */
@Local
public interface CommandeFournisseurFacadeLocal {

    void create(CommandeFournisseur commandeFournisseur);

    void edit(CommandeFournisseur commandeFournisseur);

    void remove(CommandeFournisseur commandeFournisseur);

    CommandeFournisseur find(Object id);

    List<CommandeFournisseur> findAll();

    List<CommandeFournisseur> findRange(int[] range);

    CommandeFournisseur creer(CommandeFournisseur commandeFournisseur) throws RollbackFailureException, Exception;

    CommandeFournisseur MisAjour(CommandeFournisseur commandeFournisseur) throws NonexistentEntityException, RollbackFailureException, Exception;

    List<CommandeFournisseur> findTcommandesByPeriodeBySociete(Date d, Date d1, int societe, int etat);

    Set<CommandeFournisseur> findTcommandesByArticleByPeriode(Date d, Date d1, Integer article, int etat);
    
    List<CommandeFournisseur> findAllByLigne(int ligneCommande);
    
    void Supprimer(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception;
    
    int count();
    
}
