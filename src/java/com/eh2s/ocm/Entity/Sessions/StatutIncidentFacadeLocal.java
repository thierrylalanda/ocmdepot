/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.StatutIncident;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface StatutIncidentFacadeLocal {

    StatutIncident Creer(StatutIncident statutIncident) throws RollbackFailureException, Exception;

    void MisAJour(StatutIncident statutIncident) throws NonexistentEntityException, RollbackFailureException, Exception;

    void Supprimer(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception;

    StatutIncident findStatutIncident(Integer id);

    List<StatutIncident> findStatutIncidentEntities(int maxResults, int firstResult);

    List<StatutIncident> findStatutIncidentEntities();

//fonctions extends AbstractFacade class
    void create(StatutIncident statutIncident);

    void edit(StatutIncident statutIncident);

    void remove(StatutIncident statutIncident);

    StatutIncident find(Object id);

    List<StatutIncident> findAll();

    List<StatutIncident> findRange(int[] range);

    int count();

}
