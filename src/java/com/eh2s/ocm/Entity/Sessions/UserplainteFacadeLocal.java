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
import com.eh2s.ocm.Entity.Userplainte;
import java.sql.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface UserplainteFacadeLocal {

    Userplainte creer(Userplainte userplainte) throws RollbackFailureException, Exception;

    void MisAJour(Userplainte userplainte) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception;

    void Supprimer(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception;

    List<Userplainte> findUserplainteEntities();

    List<Userplainte> findUserplainteEntities(int maxResults, int firstResult);

    Userplainte findUserplainte(Integer id);

    int getUserplainteCount();

    List<Tincidents> findTincidentsEntitiesByPeriode(Date d1, Date d2, int responsable);

//fonctions extends AbstractFacade class
    void create(Userplainte userplainte);

    void edit(Userplainte userplainte);

    void remove(Userplainte userplainte);

    Userplainte find(Object id);

    List<Userplainte> findAll();

    List<Userplainte> findRange(int[] range);

    int count();

    Userplainte findUserplainteByIdTicket(Integer id);
}
