/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.LigneSortie;
import java.sql.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Administrateur
 */
@Local
public interface LigneSortieFacadeLocal {

    void create(LigneSortie ligneSortie);

    void edit(LigneSortie ligneSortie);

    void remove(LigneSortie ligneSortie);

    LigneSortie find(Object id);

    LigneSortie creer(LigneSortie ligneSortie) throws RollbackFailureException, Exception;

    LigneSortie MisAJour(LigneSortie ligneSortie) throws RollbackFailureException, Exception;

    void Supprimer(Integer id) throws RollbackFailureException, Exception;

    List<LigneSortie> findAll();

    List<LigneSortie> findAll(int societe);

    List<LigneSortie> findRange(int[] range);

    List<LigneSortie> findByFournisseur(int fournisseur);

    List<LigneSortie> historiqueSortie(Date debut, Date fin, Integer type, Integer entite, Integer etat);

    int count();

    public List<LigneSortie> findByPeriodeSociete(Date debut, Date fin, int societe);

}
