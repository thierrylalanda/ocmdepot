/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Taffectzone;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi01
 */
@Local
public interface TaffectzoneFacadeLocal {

    void create(Taffectzone taffectzone);

    void edit(Taffectzone taffectzone);

    void remove(Taffectzone taffectzone);

    Taffectzone find(Object id);

    List<Taffectzone> findAll();

    List<Taffectzone> findRange(int[] range);

    List<Taffectzone> findByUser(int user);

    int count();

    Taffectzone creer(Taffectzone taffectzone) throws RollbackFailureException, Exception;

    Taffectzone MisAJour(Taffectzone taffectzone) throws NonexistentEntityException, RollbackFailureException, Exception;

    void Supprimer(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception;

    List<Taffectzone> findTaffectzoneEntities();

    Taffectzone findTaffectzone(Integer id);
}
