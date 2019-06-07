/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.SouscriptionLicence;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface SouscriptionLicenceFacadeLocal {

    void creer(SouscriptionLicence souscriptionLicence) throws RollbackFailureException, Exception;

    void MisAJour(SouscriptionLicence souscriptionLicence) throws NonexistentEntityException, RollbackFailureException, Exception;

    void supprimer(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception;

    List<SouscriptionLicence> findSouscriptionLicenceEntities();

    List<SouscriptionLicence> findSouscriptionLicenceEntities(int maxResults, int firstResult);

    SouscriptionLicence findSouscriptionLicence(Integer id);

    void create(SouscriptionLicence souscriptionLicence);

    void edit(SouscriptionLicence souscriptionLicence);

    void remove(SouscriptionLicence souscriptionLicence);

    SouscriptionLicence find(Object id);

    List<SouscriptionLicence> findAll(String societe);

    List<SouscriptionLicence> findRange(int[] range);

    int count();

}
