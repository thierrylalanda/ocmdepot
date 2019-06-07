/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.AffectTournerUser;
import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Tusers;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi01
 */
@Local
public interface AffectTournerUserFacadeLocal {

    void create(AffectTournerUser affectTournerUser);

    AffectTournerUser save(AffectTournerUser affectTournerUser);

    void edit(AffectTournerUser affectTournerUser);

    void remove(AffectTournerUser affectTournerUser);

    AffectTournerUser find(Object id);

    List<AffectTournerUser> findAll();

    List<AffectTournerUser> findRange(int[] range);

    int count();

    //
    AffectTournerUser creer(AffectTournerUser affectTournerUser) throws RollbackFailureException, Exception;

    AffectTournerUser MisAJour(AffectTournerUser affectTournerUser) throws NonexistentEntityException, RollbackFailureException, Exception;

    void Supprimer(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception;

    List<AffectTournerUser> findAffectTournerUserEntities();

    List<AffectTournerUser> findAffectTournerUserEntities(int user);

    List<Tusers> findUserAffectByTourner(int tourner);

    List<AffectTournerUser> findAffectTournerTournerEntities(int tourner);

    AffectTournerUser findAffectTournerUser(Integer id);
}
