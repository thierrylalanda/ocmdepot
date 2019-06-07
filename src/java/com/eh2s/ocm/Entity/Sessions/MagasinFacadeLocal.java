/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Magasin;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi01
 */
@Local
public interface MagasinFacadeLocal {

    void create(Magasin magasin);

    void edit(Magasin magasin);

    void remove(Magasin magasin);

    Magasin find(Object id);

    List<Magasin> findAll();

    List<Magasin> findRange(int[] range);

    int count();

    Magasin creer(Magasin magasin) throws RollbackFailureException, Exception;

    Magasin MisAJour(Magasin magasin) throws RollbackFailureException, Exception;

    void Supprimer(Integer id) throws RollbackFailureException, Exception;

    List<Magasin> findMagasinEntities();

    List<Magasin> findMagasinEntities(int maxResults, int firstResult);

    Magasin findMagasin(Integer id);

    public List<Magasin> findMagasinBySociete(Integer id);

    List<Magasin> findMagasinByRegion(Integer id);

}
