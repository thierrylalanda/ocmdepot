/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Tlignecommande;
import com.eh2s.ocm.UtilityFonctions.statistique;
import java.sql.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi01
 */
@Local
public interface TlignecommandeFacadeLocal {

    void create(Tlignecommande tlignecommande);

    void edit(Tlignecommande tlignecommande);

    void remove(Tlignecommande tlignecommande);

    Tlignecommande find(Object id);
    
    List<Tlignecommande> findAll(String imma);

    List<Tlignecommande> findAllBySociete(int societe);

    List<Tlignecommande> findRange(int[] range);

    int count();
    
    void SortCommande(Tlignecommande lg);

    Tlignecommande creer(Tlignecommande tlignecommande) throws RollbackFailureException, Exception;

    Tlignecommande MisAJour(Tlignecommande tlignecommande) throws NonexistentEntityException, RollbackFailureException, Exception;

    void Supprimer(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception;

    List<Tlignecommande> findTlignecommandeEntities();

    List<Tlignecommande> findTlignecommandeEntities(int maxResults, int firstResult);

    Tlignecommande findTlignecommande(Integer id);

    int getTlignecommandeCount();

    Tlignecommande findByNumc(int numc);

    List<Tlignecommande> findTcommandesByPeriode(Date d, Date d1, String imma);

    List<Tlignecommande> findTcommandesByPeriodeForReporting(Date d, Date d1, int imma, int etat);

    List<Tlignecommande> findTcommandesByClientByPeriode(Date d, Date d1, int client);

    List<Tlignecommande> findForFeuilleRoutePeriode(Date d, Date d1, int client, int code);

    List<Tlignecommande> findByClient(int cli);

    Tlignecommande Creerlignecommande(Tlignecommande ligne);

    // Statistique Commandes
    List<Tlignecommande> VenteAnnuel(int annee, int societe, int etat);

    List<statistique> findTTopClientsNational(Date d, Date d1, int max, int societe, int etat);

    List<statistique> findTTopPresellerNational(Date d, Date d1, int max, int societe, int etat);

    List<statistique> findTTopSecteursNational(Date d, Date d1, int max, int societe, int etat);

    List<statistique> findTTopVillesNational(Date d, Date d1, int max, int societe, int etat);

    List<statistique> findTTopRegionsNational(Date d, Date d1, int max, int societe, int etat);

    List<statistique> findTTopClientsRegional(Date d, Date d1, int max, int societe, int etat, int reg);

    List<statistique> findTTopPresellerRegional(Date d, Date d1, int max, int societe, int etat, int reg);

    List<statistique> findTTopSecteursRegional(Date d, Date d1, int max, int societe, int etat, int reg);

    List<statistique> findTTopVillesRegional(Date d, Date d1, int max, int societe, int etat, int reg);

    List<statistique> findTTopArticlesNational(Date d, Date d1, int max, int societe, int etat);

    List<statistique> findTTopArticleMoinVenduNational(Date d, Date d1, int max, int societe, int etat);

    List<statistique> findTTopCategorieNational(Date d, Date d1, int max, int societe, int etat);

    List<statistique> findTTopArticlesRegion(Date d, Date d1, int max, int societe, int etat, int reg);

    List<statistique> findTTopCategorieRegional(Date d, Date d1, int max, int societe, int etat, int reg);
    
    List<statistique> findTTopCategorieMoinCommanderNational(Date d, Date d1, int max, int societe, int etat);
    
    List<statistique> findTTopArticleMoinVenduRegional(Date d, Date d1, int max, int societe, int etat, int reg);

}
