/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.RoleApk;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi01
 */
@Local
public interface RoleApkFacadeLocal {

    void create(RoleApk roleApk);
    
    RoleApk creer(RoleApk roleApk);

    void edit(RoleApk roleApk);

    void remove(RoleApk roleApk);

    RoleApk find(Object id);

    List<RoleApk> findAll();

    List<RoleApk> findRange(int[] range);

    int count();
    
}
