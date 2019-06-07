/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.ActionAPK;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi01
 */
@Local
public interface ActionAPKFacadeLocal {

    void create(ActionAPK actionAPK);

    void edit(ActionAPK actionAPK);

    void remove(ActionAPK actionAPK);

    ActionAPK find(Object id);

    List<ActionAPK> findAll();

    List<ActionAPK> findRange(int[] range);

    int count();
    
}
