/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Tmenu;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface TmenuFacadeLocal {

    void create(Tmenu tmenu);

    void edit(Tmenu tmenu);

    void remove(Tmenu tmenu);

    Tmenu find(Object id);

    List<Tmenu> findAll();

    List<Tmenu> findRange(int[] range);

    int count();

}
