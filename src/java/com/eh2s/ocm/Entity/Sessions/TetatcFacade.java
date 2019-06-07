/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.LigneCommandeFournisseur;
import com.eh2s.ocm.Entity.Tetatc;
import com.eh2s.ocm.Entity.Tlignecommande;
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
public class TetatcFacade extends AbstractFacade<Tetatc> implements TetatcFacadeLocal {

    @PersistenceContext(unitName = "ccmanagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TetatcFacade() {
        super(Tetatc.class);
    }

    @Override
    public Tetatc creer(Tetatc tetatc) throws RollbackFailureException, Exception {
        if (tetatc.getTlignecommandeList() == null) {
            tetatc.setTlignecommandeList(new ArrayList<Tlignecommande>());
        }
        if (tetatc.getLigneCommandeFournisseurList() == null) {
            tetatc.setLigneCommandeFournisseurList(new ArrayList<LigneCommandeFournisseur>());
        }
        try {
            List<Tlignecommande> attachedTlignecommandeList = new ArrayList<Tlignecommande>();
            for (Tlignecommande tlignecommandeListTlignecommandeToAttach : tetatc.getTlignecommandeList()) {
                tlignecommandeListTlignecommandeToAttach = getEntityManager().getReference(tlignecommandeListTlignecommandeToAttach.getClass(), tlignecommandeListTlignecommandeToAttach.getId());
                attachedTlignecommandeList.add(tlignecommandeListTlignecommandeToAttach);
            }
            tetatc.setTlignecommandeList(attachedTlignecommandeList);
            tetatc = getEntityManager().merge(tetatc);
            for (Tlignecommande tlignecommandeListTlignecommande : tetatc.getTlignecommandeList()) {
                Tetatc oldEtatcOfTlignecommandeListTlignecommande = tlignecommandeListTlignecommande.getEtatc();
                tlignecommandeListTlignecommande.setEtatc(tetatc);
                tlignecommandeListTlignecommande = getEntityManager().merge(tlignecommandeListTlignecommande);
                if (oldEtatcOfTlignecommandeListTlignecommande != null) {
                    oldEtatcOfTlignecommandeListTlignecommande.getTlignecommandeList().remove(tlignecommandeListTlignecommande);
                    oldEtatcOfTlignecommandeListTlignecommande = getEntityManager().merge(oldEtatcOfTlignecommandeListTlignecommande);
                }
            }
            List<LigneCommandeFournisseur> attachedLigneCommandeFournisseurList = new ArrayList<LigneCommandeFournisseur>();
            for (LigneCommandeFournisseur ligneCommandeFournisseurListLigneCommandeFournisseurToAttach : tetatc.getLigneCommandeFournisseurList()) {
                ligneCommandeFournisseurListLigneCommandeFournisseurToAttach = getEntityManager().getReference(ligneCommandeFournisseurListLigneCommandeFournisseurToAttach.getClass(), ligneCommandeFournisseurListLigneCommandeFournisseurToAttach.getId());
                attachedLigneCommandeFournisseurList.add(ligneCommandeFournisseurListLigneCommandeFournisseurToAttach);
            }
            tetatc.setLigneCommandeFournisseurList(attachedLigneCommandeFournisseurList);
            for (LigneCommandeFournisseur ligneCommandeFournisseurListLigneCommandeFournisseur : tetatc.getLigneCommandeFournisseurList()) {
                Tetatc oldEtatOfLigneCommandeFournisseurListLigneCommandeFournisseur = ligneCommandeFournisseurListLigneCommandeFournisseur.getEtat();
                ligneCommandeFournisseurListLigneCommandeFournisseur.setEtat(tetatc);
                ligneCommandeFournisseurListLigneCommandeFournisseur = getEntityManager().merge(ligneCommandeFournisseurListLigneCommandeFournisseur);
                if (oldEtatOfLigneCommandeFournisseurListLigneCommandeFournisseur != null) {
                    oldEtatOfLigneCommandeFournisseurListLigneCommandeFournisseur.getLigneCommandeFournisseurList().remove(ligneCommandeFournisseurListLigneCommandeFournisseur);
                    oldEtatOfLigneCommandeFournisseurListLigneCommandeFournisseur = getEntityManager().merge(oldEtatOfLigneCommandeFournisseurListLigneCommandeFournisseur);
                }
            }
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
        return tetatc;
    }

    @Override
    public Tetatc MisAJour(Tetatc tetatc) throws NonexistentEntityException, RollbackFailureException, Exception {
        try {
            Tetatc persistentTetatc = getEntityManager().find(Tetatc.class, tetatc.getId());
            List<Tlignecommande> tlignecommandeListOld = persistentTetatc.getTlignecommandeList();
            List<Tlignecommande> tlignecommandeListNew = tetatc.getTlignecommandeList();
            List<Tlignecommande> attachedTlignecommandeListNew = new ArrayList<Tlignecommande>();
            for (Tlignecommande tlignecommandeListNewTlignecommandeToAttach : tlignecommandeListNew) {
                tlignecommandeListNewTlignecommandeToAttach = getEntityManager().getReference(tlignecommandeListNewTlignecommandeToAttach.getClass(), tlignecommandeListNewTlignecommandeToAttach.getId());
                attachedTlignecommandeListNew.add(tlignecommandeListNewTlignecommandeToAttach);
            }
            tlignecommandeListNew = attachedTlignecommandeListNew;
            tetatc.setTlignecommandeList(tlignecommandeListNew);
            tetatc = getEntityManager().merge(tetatc);
            for (Tlignecommande tlignecommandeListOldTlignecommande : tlignecommandeListOld) {
                if (!tlignecommandeListNew.contains(tlignecommandeListOldTlignecommande)) {
                    tlignecommandeListOldTlignecommande.setEtatc(null);
                    tlignecommandeListOldTlignecommande = getEntityManager().merge(tlignecommandeListOldTlignecommande);
                }
            }
            for (Tlignecommande tlignecommandeListNewTlignecommande : tlignecommandeListNew) {
                if (!tlignecommandeListOld.contains(tlignecommandeListNewTlignecommande)) {
                    Tetatc oldEtatcOfTlignecommandeListNewTlignecommande = tlignecommandeListNewTlignecommande.getEtatc();
                    tlignecommandeListNewTlignecommande.setEtatc(tetatc);
                    tlignecommandeListNewTlignecommande = getEntityManager().merge(tlignecommandeListNewTlignecommande);
                    if (oldEtatcOfTlignecommandeListNewTlignecommande != null && !oldEtatcOfTlignecommandeListNewTlignecommande.equals(tetatc)) {
                        oldEtatcOfTlignecommandeListNewTlignecommande.getTlignecommandeList().remove(tlignecommandeListNewTlignecommande);
                        oldEtatcOfTlignecommandeListNewTlignecommande = getEntityManager().merge(oldEtatcOfTlignecommandeListNewTlignecommande);
                    }
                }
            }
        } catch (Exception ex) {

            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tetatc.getId();
                if (findTetatc(id) == null) {
                    throw new NonexistentEntityException("The tetatc with id " + id + " no longer exists.");
                }
            }
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
        return tetatc;
    }

    @Override
    public void Supprimer(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        try {
            Tetatc tetatc;
            try {
                tetatc = getEntityManager().getReference(Tetatc.class, id);
                tetatc.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tetatc with id " + id + " no longer exists.", enfe);
            }
            List<Tlignecommande> tlignecommandeList = tetatc.getTlignecommandeList();
            for (Tlignecommande tlignecommandeListTlignecommande : tlignecommandeList) {
                tlignecommandeListTlignecommande.setEtatc(null);
                tlignecommandeListTlignecommande = getEntityManager().merge(tlignecommandeListTlignecommande);
            }
            getEntityManager().remove(tetatc);
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @Override
    public List<Tetatc> findTetatcEntities() {
        return findTetatcEntities(true, -1, -1);
    }

    @Override
    public List<Tetatc> findTetatcEntities(int maxResults, int firstResult) {
        return findTetatcEntities(false, maxResults, firstResult);
    }

    private List<Tetatc> findTetatcEntities(boolean all, int maxResults, int firstResult) {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Tetatc.class));
        Query q = getEntityManager().createQuery(cq);
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    @Override
    public Tetatc findTetatc(Integer id) {
        return getEntityManager().find(Tetatc.class, id);
    }

    @Override
    public int getTetatcCount() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<Tetatc> rt = cq.from(Tetatc.class);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    @Override
    public Tetatc findByCode(int code) {
        Query q = getEntityManager().createNamedQuery("Tetatc.findByCode");
        q.setParameter("code", code);
        return (Tetatc) q.getSingleResult();
    }
}
