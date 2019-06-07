/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.SourceMouvementCaisse;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Administrateur
 */
@Local
public interface SourceMouvementCaisseFacadeLocal {

    void create(SourceMouvementCaisse sourceMouvementCaisse);

    void edit(SourceMouvementCaisse sourceMouvementCaisse);

    void remove(SourceMouvementCaisse sourceMouvementCaisse);

     SourceMouvementCaisse creer(SourceMouvementCaisse sourceMouvementCaisse) throws RollbackFailureException, Exception;

    SourceMouvementCaisse MisAJour(SourceMouvementCaisse sourceMouvementCaisse) throws RollbackFailureException, Exception;

    void Supprimer(Integer id) throws RollbackFailureException, Exception;
    SourceMouvementCaisse find(Object id);

    List<SourceMouvementCaisse> findAll();

    List<SourceMouvementCaisse> findRange(int[] range);

    int count();
    
}
