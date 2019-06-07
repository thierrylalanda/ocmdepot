/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.IllegalOrphanException;
import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.TtraitementTicket;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface TtraitementTicketFacadeLocal {

    TtraitementTicket creer(TtraitementTicket ttraitementTicket) throws RollbackFailureException, Exception;

    void MisAJour(TtraitementTicket ttraitementTicket) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception;

    void Supprimer(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception;

    List<TtraitementTicket> findTtraitementTicketEntities();

    List<TtraitementTicket> findTtraitementTicketEntities(int maxResults, int firstResult);

    TtraitementTicket findTtraitementTicket(Integer id);

    int getTtraitementTicketCount();
    
    List<TtraitementTicket> findAllTickect(int ticket);

//fonctions extends AbstractFacade class
    void create(TtraitementTicket ttraitementTicket);

    void edit(TtraitementTicket ttraitementTicket);

    void remove(TtraitementTicket ttraitementTicket);

    TtraitementTicket find(Object id);

    List<TtraitementTicket> findAll();

    List<TtraitementTicket> findRange(int[] range);

    int count();

}
