/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.LigneInventaire;
import java.sql.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi01
 */
@Local
public interface LigneInventaireFacadeLocal {

    void create(LigneInventaire ligneInventaire);

    void edit(LigneInventaire ligneInventaire);

    void remove(LigneInventaire ligneInventaire);

    LigneInventaire find(Object id);

    List<LigneInventaire> findAll();

    List<LigneInventaire> findRange(int[] range);

    int count();

    LigneInventaire creer(LigneInventaire ligneInventaire) throws RollbackFailureException, Exception;

    LigneInventaire MisAJour(LigneInventaire ligneInventaire) throws RollbackFailureException, Exception;

    void Supprimer(Integer id) throws RollbackFailureException, Exception;

    List<LigneInventaire> findLigneInventaireEntities();

    List<LigneInventaire> findLigneInventaireEntities(int maxResults, int firstResult);

    LigneInventaire findLigneInventaire(Integer id);

    List<LigneInventaire> historiqueInventaire(Date debut, Date fin, Integer magasin);

    List<LigneInventaire> historiqueInventaireSociete(Date debut, Date fin, Integer societe);

    List<LigneInventaire> findAllLigneInventaireSociete(Integer societe);
}
