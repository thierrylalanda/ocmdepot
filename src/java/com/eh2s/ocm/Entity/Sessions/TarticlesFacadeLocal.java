/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.IllegalOrphanException;
import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Tarticles;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author messi
 */
@Local
public interface TarticlesFacadeLocal {

    void create(Tarticles tarticles);

    void edit(Tarticles tarticles);

    void remove(Tarticles tarticles);

    Tarticles find(Object id);

    List<Tarticles> findAll();

    List<Tarticles> findRange(int[] range);

    int count();

    Tarticles MiSaJour(Tarticles tarticles) throws NonexistentEntityException, RollbackFailureException, Exception;

    Tarticles creer(Tarticles tarticles) throws RollbackFailureException, Exception;

    void Supprimer(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception;

    List<Tarticles> findTarticlesEntities();

    int getTarticlesCount();

    Tarticles findTarticles(Integer id);

    List<Tarticles> findAll(String imma);
    
    List<Tarticles> findAllBySociete(int societe);

    List<Tarticles> findAllByCategorie(int cat);
}
