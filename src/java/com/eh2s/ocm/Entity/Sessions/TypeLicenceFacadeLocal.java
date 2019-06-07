/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.IllegalOrphanException;
import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.TypeLicence;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface TypeLicenceFacadeLocal {

    void creer(TypeLicence typeLicence) throws RollbackFailureException, Exception;

    void MisAJour(TypeLicence typeLicence) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception;

    void supprimer(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception;

    List<TypeLicence> findTypeLicenceEntities();

    List<TypeLicence> findTypeLicenceEntities(int maxResults, int firstResult);

    TypeLicence findTypeLicence(Integer id);

    void create(TypeLicence typeLicence);

    void edit(TypeLicence typeLicence);

    void remove(TypeLicence typeLicence);

    TypeLicence find(Object id);

    List<TypeLicence> findAll();

    List<TypeLicence> findRange(int[] range);

    int count();

}
