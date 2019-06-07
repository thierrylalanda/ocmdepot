/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.LigneCommandeFournisseur;
import com.eh2s.ocm.Entity.Tlignecommande;
import java.sql.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Administrateur
 */
@Local
public interface LigneCommandeFournisseurFacadeLocal {

    void create(LigneCommandeFournisseur ligneCommandeFournisseur);

    void edit(LigneCommandeFournisseur ligneCommandeFournisseur);

    void remove(LigneCommandeFournisseur ligneCommandeFournisseur);

    LigneCommandeFournisseur find(Object id);

    List<LigneCommandeFournisseur> findAll();

    List<LigneCommandeFournisseur> findRange(int[] range);

    List<LigneCommandeFournisseur> findAll(int societe);

    List<LigneCommandeFournisseur> findBonCommandeByPeriode(Date d, Date d1, int societe);

    List<LigneCommandeFournisseur> findBonCommandeByFournisseurByPeriode(Date d, Date d1, int fournisseur);
            
    LigneCommandeFournisseur creer(LigneCommandeFournisseur ligneCommande) throws RollbackFailureException, Exception;

    LigneCommandeFournisseur MisAJour(LigneCommandeFournisseur ligneCommande) throws RollbackFailureException, Exception;

    void Supprimer(Integer id) throws RollbackFailureException, Exception;

    int count();

}
