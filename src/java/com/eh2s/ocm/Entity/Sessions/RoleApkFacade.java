/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.RoleApk;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author messi01
 */
@Stateless
public class RoleApkFacade extends AbstractFacade<RoleApk> implements RoleApkFacadeLocal {

    @PersistenceContext(unitName = "ccmanagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RoleApkFacade() {
        super(RoleApk.class);
    }

    @Override
    public RoleApk creer(RoleApk roleApk) {
       return getEntityManager().merge(roleApk);
    }
    
}
