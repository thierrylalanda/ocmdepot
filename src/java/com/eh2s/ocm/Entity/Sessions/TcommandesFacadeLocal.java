/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Tarticles;
import com.eh2s.ocm.Entity.Tcommandes;
import java.sql.Date;
import java.util.List;
import java.util.Set;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface TcommandesFacadeLocal {

    Tcommandes creer(Tcommandes tcommandes) throws RollbackFailureException, Exception;

    Tcommandes MisAJour(Tcommandes tcommandes) throws NonexistentEntityException, RollbackFailureException, Exception;

    void Supprimer(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception;

    List<Tcommandes> findTcommandesEntities();

//    List<Tcommandes> findTcommandesByNumc(int numc);
//
    //List<Tcommandes> findTcommandesByPeriode(Date d, Date d1, int immat);
//    List<Tcommandes> findTcommandesByToDay();
    List<Tcommandes> findTcommandesEntities(int maxResults, int firstResult);

    Tcommandes findTcommandes(Integer id);

    int getTcommandesCount();

    void create(Tcommandes tcommandes);

    void edit(Tcommandes tcommandes);

    void remove(Tcommandes tcommandes);

    Tcommandes find(Object id);

    List<Tcommandes> findAllByLigne(int ligne);

    List<Tcommandes> findRange(int[] range);

    List<Tcommandes> findTcommandesByPeriodeByClientSUMForMarge(Date d, Date d1, int client, int etat);

    int count();

//    List<Tcommandes> findTcommandesByPeriodeByClient(Date d, Date d1, int client);
//
//    List<Tcommandes> findTcommandesByClient(int client);
    Set<Tarticles> findTcommandesArticleByPeriode(Date d, Date d1, int etat, int s);

    List<Tcommandes> findTcommandesByPeriodeBySociete(Date d, Date d1, int societe, int etat);

    Set<Tcommandes> findTcommandesByArticleByPeriode(Date d, Date d1, Integer cat, int etat);
    
    Set<Tcommandes> findTcommandesCategorieByPeriode(Date d, Date d1, Integer cat, int etat);

    List<Tcommandes> findTcommandesByPeriodeByClientSUM(Date d, Date d1, int client, int etat);

    List<Tcommandes> findTcommandesByPeriodeByTournerSUM(Date d, Date d1, int tourner, int etat);

    Tarticles getSumCommander(Date d, Date d1, int societe, int etat, int article);

}
