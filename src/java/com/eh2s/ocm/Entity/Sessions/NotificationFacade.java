/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Notification;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author messi
 */
@Stateless
public class NotificationFacade extends AbstractFacade<Notification> implements NotificationFacadeLocal {

    @PersistenceContext(unitName = "ccmanagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NotificationFacade() {
        super(Notification.class);
    }

    @Override
    public List<Notification> findAllByUser(int user, int lut, boolean all) {
        Query query;
        if (all) {
            query = getEntityManager().createNamedQuery("Notification.findByUser");
        } else {
            query = getEntityManager().createNamedQuery("Notification.findByUserAndStatus");
            query.setParameter("lut", lut);
        }
        query.setParameter("user", user);
        return query.getResultList();
    }

    @Override
    public List<Notification> findAllByClient(int user, int lut, boolean all) {
        Query query;
        if (all) {
            query = getEntityManager().createNamedQuery("Notification.findByClient");
        } else {
            query = getEntityManager().createNamedQuery("Notification.findByClientAndStatus");
            query.setParameter("lut", lut);
        }
        query.setParameter("client", user);
        return query.getResultList();
    }
    
    
}
