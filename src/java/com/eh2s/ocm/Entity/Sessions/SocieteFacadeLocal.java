/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.PreexistingEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Societe;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface SocieteFacadeLocal {

    Societe creer(Societe societe) throws PreexistingEntityException, RollbackFailureException, Exception;

    void MisAJour(Societe societe) throws NonexistentEntityException, RollbackFailureException, Exception;

    void supprimer(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception;

    List<Societe> findSocieteEntities();

    Societe findSociete(Integer id);

    Societe findSocieteByImma(int imma);

    List<Societe> findAllByForIdRepeat(int imma);

    void create(Societe societe);

    void edit(Societe societe);

    void remove(Societe societe);

    Societe find(Object id);

    List<Societe> findAll();

    List<Societe> findAllByImma(int imma);

    List<Societe> findRange(int[] range);

    int count();

    List<Societe> findAllByAutoSaveClient(int auto);

}
