/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Inventaire;
import java.sql.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi01
 */
@Local
public interface InventaireFacadeLocal {

    void create(Inventaire inventaire);

    void edit(Inventaire inventaire);

    void remove(Inventaire inventaire);

    Inventaire find(Object id);

    List<Inventaire> findAll();

    List<Inventaire> findRange(int[] range);

    int count();

    Inventaire creer(Inventaire inventaire) throws RollbackFailureException, Exception;

    Inventaire MisAjour(Inventaire inventaire) throws NonexistentEntityException, RollbackFailureException, Exception;

    void Supprimer(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception;

    List<Inventaire> findInventaireEntities();

    Inventaire findInventaire(Integer id);
    /**
     * 
     * @param debut date de debut des inventaires
     * @param fin date de fin des inventaires
     * @param magasin magasin pour le lequel on effectue l'inventaire
     * @param entite entite pour lequel on effectue la recherche (article ou categorie)
     * @param type type d'entite 0 pour categorie et 1 pour article
     * @return List 
     */
    List<Inventaire> historiqueInventaire(Date debut, Date fin,Integer magasin, Integer entite,Integer type);
     /**
     * 
     * @param debut date de debut des inventaires
     * @param fin date de fin des inventaires
     * @param societe societe pour la quelle on effectue l'inventaire
     * @param entite entite pour lequel on effectue la recherche (article ou categorie)
     * @param type type d'entite 0 pour categorie et 1 pour article
     * @param type1 type d'entite article (0) ou emballage (1)
     * @return List 
     */
    List<Inventaire> historiqueInventaireSociete(Date debut, Date fin,Integer societe, Integer entite,Integer type,Integer type1);

}