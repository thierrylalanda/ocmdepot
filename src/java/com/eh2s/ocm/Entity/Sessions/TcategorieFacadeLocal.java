/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Tcategorie;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface TcategorieFacadeLocal {

    Tcategorie creer(Tcategorie tcategorie) throws RollbackFailureException, Exception;

    Tcategorie MisAJour(Tcategorie tcategorie) throws NonexistentEntityException, RollbackFailureException, Exception;

    void Supprimer(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception;

    List<Tcategorie> findTcategorieEntities();

    List<Tcategorie> findTcategorieEntities(int maxResults, int firstResult);

    Tcategorie findTcategorie(Integer id);

    void create(Tcategorie tcategorie);

    void edit(Tcategorie tcategorie);

    void remove(Tcategorie tcategorie);

    Tcategorie find(Object id);

    List<Tcategorie> findAll();

    List<Tcategorie> findAll(String imma);
    
    List<Tcategorie> findAllBySociete(int societe);

    List<Tcategorie> findRange(int[] range);

    int count();

}
