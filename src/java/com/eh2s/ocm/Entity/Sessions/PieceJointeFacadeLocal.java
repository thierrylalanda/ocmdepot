/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.PieceJointe;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface PieceJointeFacadeLocal {

    PieceJointe Creer(PieceJointe pieceJointe) throws RollbackFailureException, Exception;

    void MisAJour(PieceJointe pieceJointe) throws NonexistentEntityException, RollbackFailureException, Exception;

    void Supprimer(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception;

    PieceJointe findPieceJointe(Integer id);

    List<PieceJointe> findPieceJointeEntities(int maxResults, int firstResult);

    int getPieceJointeCount();
    
    List<PieceJointe> findAllTickect(int ticket);

//fonctions extends AbstractFacade class
    void create(PieceJointe pieceJointe);

    void edit(PieceJointe pieceJointe);

    void remove(PieceJointe pieceJointe);

    PieceJointe find(Object id);

    List<PieceJointe> findPieceJointeEntities();

    List<PieceJointe> findAll();

    List<PieceJointe> findRange(int[] range);

    int count();
}
