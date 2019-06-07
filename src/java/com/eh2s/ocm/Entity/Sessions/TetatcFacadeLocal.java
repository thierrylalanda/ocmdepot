/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Tetatc;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface TetatcFacadeLocal {

    Tetatc creer(Tetatc tetatc) throws RollbackFailureException, Exception;

    Tetatc MisAJour(Tetatc tetatc) throws NonexistentEntityException, RollbackFailureException, Exception;

    void Supprimer(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception;

    List<Tetatc> findTetatcEntities();

    List<Tetatc> findTetatcEntities(int maxResults, int firstResult);

    Tetatc findTetatc(Integer id);

    int getTetatcCount();

    void create(Tetatc tetatc);

    void edit(Tetatc tetatc);

    void remove(Tetatc tetatc);

    Tetatc find(Object id);
    
    Tetatc findByCode(int code);

    List<Tetatc> findAll();

    List<Tetatc> findRange(int[] range);

    int count();

}
