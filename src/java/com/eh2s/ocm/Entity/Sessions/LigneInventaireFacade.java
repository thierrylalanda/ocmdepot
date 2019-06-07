/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.IllegalOrphanException;
import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Inventaire;
import com.eh2s.ocm.Entity.LigneInventaire;
import com.eh2s.ocm.Entity.Magasin;
import com.eh2s.ocm.Entity.Societe;
import com.eh2s.ocm.Entity.Tusers;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author messi01
 */
@Stateless
public class LigneInventaireFacade extends AbstractFacade<LigneInventaire> implements LigneInventaireFacadeLocal {

    @PersistenceContext(unitName = "ccmanagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LigneInventaireFacade() {
        super(LigneInventaire.class);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public LigneInventaire creer(LigneInventaire ligneInventaire) throws RollbackFailureException, Exception {
         if (ligneInventaire.getInventaireList() == null) {
            ligneInventaire.setInventaireList(new ArrayList<Inventaire>());
        }
         try {
            Tusers operateur = ligneInventaire.getOperateur();
            if (operateur != null) {
                operateur = getEntityManager().getReference(operateur.getClass(), operateur.getId());
                ligneInventaire.setOperateur(operateur);
            }
            Magasin magasin = ligneInventaire.getMagasin();
            if (magasin != null) {
                magasin = getEntityManager().getReference(magasin.getClass(), magasin.getId());
                ligneInventaire.setMagasin(magasin);
            }
            Societe societe = ligneInventaire.getSociete();
            if (societe != null) {
                societe = getEntityManager().getReference(societe.getClass(), societe.getId());
                ligneInventaire.setSociete(societe);
            }
            List<Inventaire> attachedInventaireList = new ArrayList<Inventaire>();
            for (Inventaire inventaireListInventaireToAttach : ligneInventaire.getInventaireList()) {
                inventaireListInventaireToAttach = getEntityManager().getReference(inventaireListInventaireToAttach.getClass(), inventaireListInventaireToAttach.getId());
                attachedInventaireList.add(inventaireListInventaireToAttach);
            }
            ligneInventaire.setInventaireList(attachedInventaireList);
            getEntityManager().persist(ligneInventaire);
            if (operateur != null) {
                operateur.getLigneInventaireList().add(ligneInventaire);
                operateur = getEntityManager().merge(operateur);
            }
            if (magasin != null) {
                magasin.getLigneInventaireList().add(ligneInventaire);
                magasin = getEntityManager().merge(magasin);
            }
            if (societe != null) {
                societe.getLigneInventaireList().add(ligneInventaire);
                societe = getEntityManager().merge(societe);
            }
            for (Inventaire inventaireListInventaire : ligneInventaire.getInventaireList()) {
                LigneInventaire oldLigneInvOfInventaireListInventaire = inventaireListInventaire.getLigneInv();
                inventaireListInventaire.setLigneInv(ligneInventaire);
                inventaireListInventaire = getEntityManager().merge(inventaireListInventaire);
                if (oldLigneInvOfInventaireListInventaire != null) {
                    oldLigneInvOfInventaireListInventaire.getInventaireList().remove(inventaireListInventaire);
                    oldLigneInvOfInventaireListInventaire = getEntityManager().merge(oldLigneInvOfInventaireListInventaire);
                }
            }
            return ligneInventaire;
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);
          
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public LigneInventaire MisAJour(LigneInventaire ligneInventaire) throws RollbackFailureException, Exception {
        try {
            LigneInventaire persistentLigneInventaire = getEntityManager().find(LigneInventaire.class, ligneInventaire.getId());
            Tusers operateurOld = persistentLigneInventaire.getOperateur();
            Tusers operateurNew = ligneInventaire.getOperateur();
            Magasin magasinOld = persistentLigneInventaire.getMagasin();
            Magasin magasinNew = ligneInventaire.getMagasin();
            Societe societeOld = persistentLigneInventaire.getSociete();
            Societe societeNew = ligneInventaire.getSociete();
            List<Inventaire> inventaireListOld = persistentLigneInventaire.getInventaireList();
            List<Inventaire> inventaireListNew = ligneInventaire.getInventaireList();
           
            if (operateurNew != null) {
                operateurNew = getEntityManager().getReference(operateurNew.getClass(), operateurNew.getId());
                ligneInventaire.setOperateur(operateurNew);
            }
            if (magasinNew != null) {
                magasinNew = getEntityManager().getReference(magasinNew.getClass(), magasinNew.getId());
                ligneInventaire.setMagasin(magasinNew);
            }
            if (societeNew != null) {
                societeNew = getEntityManager().getReference(societeNew.getClass(), societeNew.getId());
                ligneInventaire.setSociete(societeNew);
            }
            List<Inventaire> attachedInventaireListNew = new ArrayList<Inventaire>();
            for (Inventaire inventaireListNewInventaireToAttach : inventaireListNew) {
                inventaireListNewInventaireToAttach = getEntityManager().getReference(inventaireListNewInventaireToAttach.getClass(), inventaireListNewInventaireToAttach.getId());
                attachedInventaireListNew.add(inventaireListNewInventaireToAttach);
            }
            inventaireListNew = attachedInventaireListNew;
            ligneInventaire.setInventaireList(inventaireListNew);
            ligneInventaire = getEntityManager().merge(ligneInventaire);
            if (operateurOld != null && !operateurOld.equals(operateurNew)) {
                operateurOld.getLigneInventaireList().remove(ligneInventaire);
                operateurOld = getEntityManager().merge(operateurOld);
            }
            if (operateurNew != null && !operateurNew.equals(operateurOld)) {
                operateurNew.getLigneInventaireList().add(ligneInventaire);
                operateurNew = getEntityManager().merge(operateurNew);
            }
            if (magasinOld != null && !magasinOld.equals(magasinNew)) {
                magasinOld.getLigneInventaireList().remove(ligneInventaire);
                magasinOld = getEntityManager().merge(magasinOld);
            }
            if (magasinNew != null && !magasinNew.equals(magasinOld)) {
                magasinNew.getLigneInventaireList().add(ligneInventaire);
                magasinNew = getEntityManager().merge(magasinNew);
            }
            if (societeOld != null && !societeOld.equals(societeNew)) {
                societeOld.getLigneInventaireList().remove(ligneInventaire);
                societeOld = getEntityManager().merge(societeOld);
            }
            if (societeNew != null && !societeNew.equals(societeOld)) {
                societeNew.getLigneInventaireList().add(ligneInventaire);
                societeNew = getEntityManager().merge(societeNew);
            }
            for (Inventaire inventaireListNewInventaire : inventaireListNew) {
                if (!inventaireListOld.contains(inventaireListNewInventaire)) {
                    LigneInventaire oldLigneInvOfInventaireListNewInventaire = inventaireListNewInventaire.getLigneInv();
                    inventaireListNewInventaire.setLigneInv(ligneInventaire);
                    inventaireListNewInventaire = getEntityManager().merge(inventaireListNewInventaire);
                    if (oldLigneInvOfInventaireListNewInventaire != null && !oldLigneInvOfInventaireListNewInventaire.equals(ligneInventaire)) {
                        oldLigneInvOfInventaireListNewInventaire.getInventaireList().remove(inventaireListNewInventaire);
                        oldLigneInvOfInventaireListNewInventaire = getEntityManager().merge(oldLigneInvOfInventaireListNewInventaire);
                    }
                }
            }
            return ligneInventaire;
        } catch (Exception ex) {
              throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);
           
        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void Supprimer(Integer id) throws RollbackFailureException, Exception {
         try {
            LigneInventaire ligneInventaire;
            try {
                ligneInventaire = getEntityManager().getReference(LigneInventaire.class, id);
                ligneInventaire.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ligneInventaire with id " + id + " no longer exists.", enfe);
            }
            
            Tusers operateur = ligneInventaire.getOperateur();
            if (operateur != null) {
                operateur.getLigneInventaireList().remove(ligneInventaire);
                operateur = getEntityManager().merge(operateur);
            }
            Magasin magasin = ligneInventaire.getMagasin();
            if (magasin != null) {
                magasin.getLigneInventaireList().remove(ligneInventaire);
                magasin = getEntityManager().merge(magasin);
            }
            Societe societe = ligneInventaire.getSociete();
            if (societe != null) {
                societe.getLigneInventaireList().remove(ligneInventaire);
                societe = getEntityManager().merge(societe);
            }
            getEntityManager().remove(ligneInventaire);
        } catch (Exception ex) {
              throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);
         }
    }

    @Override
    public List<LigneInventaire> findLigneInventaireEntities() {
        return findLigneInventaireEntities(true, -1, -1);
    }

    @Override
    public List<LigneInventaire> findLigneInventaireEntities(int maxResults, int firstResult) {
        return findLigneInventaireEntities(false, maxResults, firstResult);
    }

    private List<LigneInventaire> findLigneInventaireEntities(boolean all, int maxResults, int firstResult) {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(LigneInventaire.class));
        Query q = getEntityManager().createQuery(cq);
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    @Override
    public LigneInventaire findLigneInventaire(Integer id) {
        return getEntityManager().find(LigneInventaire.class, id);
    }

    @Override
    public List<LigneInventaire> historiqueInventaire(Date debut, Date fin,Integer magasin) {
    Query q = getEntityManager().createNamedQuery("LigneInventaire.findLigneMagasinByPeriode");
        q.setParameter("d", debut);
        q.setParameter("d1", fin);
        q.setParameter("magasin", magasin);
        return q.getResultList();
    }

    @Override
    public List<LigneInventaire> historiqueInventaireSociete(Date debut, Date fin, Integer societe) {
     Query q = getEntityManager().createNamedQuery("LigneInventaire.findLigneSocieteByPeriode");
        q.setParameter("d", debut);
        q.setParameter("d1", fin);
        q.setParameter("societe", societe);
        return q.getResultList(); 
    }

    @Override
    public List<LigneInventaire> findAllLigneInventaireSociete(Integer societe) {
    Query q = getEntityManager().createNamedQuery("LigneInventaire.findAllLigneSociete");
         q.setParameter("societe", societe);
        return q.getResultList();
    }
}