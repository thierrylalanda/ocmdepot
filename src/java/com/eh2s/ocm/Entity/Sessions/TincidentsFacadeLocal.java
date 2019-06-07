/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.IllegalOrphanException;
import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Tincidents;
import java.sql.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface TincidentsFacadeLocal {

    Tincidents creer(Tincidents tincidents) throws RollbackFailureException, Exception;

    Tincidents MisAJour(Tincidents tincidents) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception;

    void Supprimer(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception;

    List<Tincidents> findTincidentsEntities();

    List<Tincidents> findTicketHorsDelais(String societe);

    List<Tincidents> findTincidentsEntities(int maxResults, int firstResult);

    Tincidents findTincidents(Integer id);

    List<Tincidents> findTicketByAnnee(int annee, String societe);

    int getTincidentsCount();

    List<Tincidents> findTincidentsEntitiesByPeriode(Date d1, Date d2, String societe);

//fonctions extends AbstractFacade class
    void create(Tincidents tincidents);

    void edit(Tincidents tincidents);

    void remove(Tincidents tincidents);

    Tincidents find(Object id);

    List<Tincidents> findAll(String societe);

    List<Tincidents> findRange(int[] range);

    int count();

    List<Tincidents> findAllByClient(int client);

}
