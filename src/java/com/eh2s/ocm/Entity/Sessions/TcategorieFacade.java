/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Societe;
import com.eh2s.ocm.Entity.Tarticles;
import com.eh2s.ocm.Entity.Tcategorie;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author messi
 */
@Stateless
public class TcategorieFacade extends AbstractFacade<Tcategorie> implements TcategorieFacadeLocal {

    @PersistenceContext(unitName = "ccmanagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TcategorieFacade() {
        super(Tcategorie.class);
    }

    @Override
    public Tcategorie creer(Tcategorie tcategorie) throws RollbackFailureException, Exception {
        if (tcategorie.getTarticlesList() == null) {
            tcategorie.setTarticlesList(new ArrayList<Tarticles>());
        }
        try {
            Societe societe = tcategorie.getSociete();
            if (societe != null) {
                societe = getEntityManager().getReference(societe.getClass(), societe.getId());
                tcategorie.setSociete(societe);
            }
            List<Tarticles> attachedTarticlesList = new ArrayList<Tarticles>();
            for (Tarticles tarticlesListTarticlesToAttach : tcategorie.getTarticlesList()) {
                tarticlesListTarticlesToAttach = getEntityManager().getReference(tarticlesListTarticlesToAttach.getClass(), tarticlesListTarticlesToAttach.getId());
                attachedTarticlesList.add(tarticlesListTarticlesToAttach);
            }
            tcategorie.setTarticlesList(attachedTarticlesList);
            tcategorie = getEntityManager().merge(tcategorie);
            if (societe != null) {
                societe.getTcategorieList().add(tcategorie);
                societe = getEntityManager().merge(societe);
            }
            for (Tarticles tarticlesListTarticles : tcategorie.getTarticlesList()) {
                Tcategorie oldCategorieOfTarticlesListTarticles = tarticlesListTarticles.getCategorie();
                tarticlesListTarticles.setCategorie(tcategorie);
                tarticlesListTarticles = getEntityManager().merge(tarticlesListTarticles);
                if (oldCategorieOfTarticlesListTarticles != null) {
                    oldCategorieOfTarticlesListTarticles.getTarticlesList().remove(tarticlesListTarticles);
                    oldCategorieOfTarticlesListTarticles = getEntityManager().merge(oldCategorieOfTarticlesListTarticles);
                }
            }
            return tcategorie;
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @Override
    public Tcategorie MisAJour(Tcategorie tcategorie) throws NonexistentEntityException, RollbackFailureException, Exception {
        try {
            Tcategorie persistentTcategorie = getEntityManager().find(Tcategorie.class, tcategorie.getId());
            Societe societeOld = persistentTcategorie.getSociete();
            Societe societeNew = tcategorie.getSociete();
            List<Tarticles> tarticlesListOld = persistentTcategorie.getTarticlesList();
            List<Tarticles> tarticlesListNew = tcategorie.getTarticlesList();

            if (societeNew != null) {
                societeNew = getEntityManager().getReference(societeNew.getClass(), societeNew.getId());
                tcategorie.setSociete(societeNew);
            }
            List<Tarticles> attachedTarticlesListNew = new ArrayList<Tarticles>();
            for (Tarticles tarticlesListNewTarticlesToAttach : tarticlesListNew) {
                tarticlesListNewTarticlesToAttach = getEntityManager().getReference(tarticlesListNewTarticlesToAttach.getClass(), tarticlesListNewTarticlesToAttach.getId());
                attachedTarticlesListNew.add(tarticlesListNewTarticlesToAttach);
            }
            tarticlesListNew = attachedTarticlesListNew;
            tcategorie.setTarticlesList(tarticlesListNew);
            tcategorie = getEntityManager().merge(tcategorie);
            if (societeOld != null && !societeOld.equals(societeNew)) {
                societeOld.getTcategorieList().remove(tcategorie);
                societeOld = getEntityManager().merge(societeOld);
            }
            if (societeNew != null && !societeNew.equals(societeOld)) {
                societeNew.getTcategorieList().add(tcategorie);
                societeNew = getEntityManager().merge(societeNew);
            }
            for (Tarticles tarticlesListNewTarticles : tarticlesListNew) {
                if (!tarticlesListOld.contains(tarticlesListNewTarticles)) {
                    Tcategorie oldCategorieOfTarticlesListNewTarticles = tarticlesListNewTarticles.getCategorie();
                    tarticlesListNewTarticles.setCategorie(tcategorie);
                    tarticlesListNewTarticles = getEntityManager().merge(tarticlesListNewTarticles);
                    if (oldCategorieOfTarticlesListNewTarticles != null && !oldCategorieOfTarticlesListNewTarticles.equals(tcategorie)) {
                        oldCategorieOfTarticlesListNewTarticles.getTarticlesList().remove(tarticlesListNewTarticles);
                        oldCategorieOfTarticlesListNewTarticles = getEntityManager().merge(oldCategorieOfTarticlesListNewTarticles);
                    }
                }
            }
            return tcategorie;
        } catch (Exception ex) {

            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tcategorie.getId();
                if (findTcategorie(id) == null) {
                    throw new NonexistentEntityException("The tcategorie with id " + id + " no longer exists.");
                }
            }
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @Override
    public void Supprimer(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        try {
            Tcategorie tcategorie;
            try {
                tcategorie = getEntityManager().getReference(Tcategorie.class, id);
                tcategorie.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tcategorie with id " + id + " no longer exists.", enfe);
            }

            Societe societe = tcategorie.getSociete();
            if (societe != null) {
                societe.getTcategorieList().remove(tcategorie);
                societe = getEntityManager().merge(societe);
            }
            getEntityManager().remove(tcategorie);
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @Override
    public List<Tcategorie> findTcategorieEntities() {
        return findTcategorieEntities(true, -1, -1);
    }

    @Override
    public List<Tcategorie> findTcategorieEntities(int maxResults, int firstResult) {
        return findTcategorieEntities(false, maxResults, firstResult);
    }

    private List<Tcategorie> findTcategorieEntities(boolean all, int maxResults, int firstResult) {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Tcategorie.class));
        Query q = getEntityManager().createQuery(cq);
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    @Override
    public Tcategorie findTcategorie(Integer id) {
        return getEntityManager().find(Tcategorie.class, id);
    }

    public int getTcategorieCount() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<Tcategorie> rt = cq.from(Tcategorie.class);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();

    }

    @Override
    public List<Tcategorie> findAll(String imma) {
        Query q = getEntityManager().createNamedQuery("Tcategorie.findAll");
        q.setParameter("imma", imma);
        return q.getResultList();
    }

    @Override
    public List<Tcategorie> findAllBySociete(int societe) {
        Query q = getEntityManager().createNamedQuery("Tcategorie.findAllBySociete");
        q.setParameter("id", societe);
        return q.getResultList();
    }

}
