/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Societe;
import com.eh2s.ocm.Entity.SouscriptionLicence;
import com.eh2s.ocm.Entity.TypeLicence;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author messi
 */
@Stateless
public class SouscriptionLicenceFacade extends AbstractFacade<SouscriptionLicence> implements SouscriptionLicenceFacadeLocal {

    @PersistenceContext(unitName = "ccmanagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SouscriptionLicenceFacade() {
        super(SouscriptionLicence.class);
    }

    @Override
    public void creer(SouscriptionLicence souscriptionLicence) throws RollbackFailureException, Exception {
        try {
            Societe societe = souscriptionLicence.getSociete();
            if (societe != null) {
                societe = getEntityManager().getReference(societe.getClass(), societe.getId());
                souscriptionLicence.setSociete(societe);
            }
            TypeLicence type = souscriptionLicence.getType();
            if (type != null) {
                type = getEntityManager().getReference(type.getClass(), type.getId());
                souscriptionLicence.setType(type);
            }
            SouscriptionLicence s = getEntityManager().merge(souscriptionLicence);
            if (societe != null) {
                societe.getSouscriptionLicenceList().add(s);
                societe = getEntityManager().merge(societe);
            }
            if (type != null) {
                type.getSouscriptionLicenceList().add(s);
                type = getEntityManager().merge(type);
            }
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @Override
    public void MisAJour(SouscriptionLicence souscriptionLicence) throws NonexistentEntityException, RollbackFailureException, Exception {
        try {
            SouscriptionLicence persistentSouscriptionLicence = getEntityManager().find(SouscriptionLicence.class, souscriptionLicence.getId());
            Societe societeOld = persistentSouscriptionLicence.getSociete();
            Societe societeNew = souscriptionLicence.getSociete();
            TypeLicence typeOld = persistentSouscriptionLicence.getType();
            TypeLicence typeNew = souscriptionLicence.getType();
            if (societeNew != null) {
                societeNew = getEntityManager().getReference(societeNew.getClass(), societeNew.getId());
                souscriptionLicence.setSociete(societeNew);
            }
            if (typeNew != null) {
                typeNew = getEntityManager().getReference(typeNew.getClass(), typeNew.getId());
                souscriptionLicence.setType(typeNew);
            }
            souscriptionLicence = getEntityManager().merge(souscriptionLicence);
            if (societeOld != null && !societeOld.equals(societeNew)) {
                societeOld.getSouscriptionLicenceList().remove(souscriptionLicence);
                societeOld = getEntityManager().merge(societeOld);
            }
            if (societeNew != null && !societeNew.equals(societeOld)) {
                societeNew.getSouscriptionLicenceList().add(souscriptionLicence);
                societeNew = getEntityManager().merge(societeNew);
            }
            if (typeOld != null && !typeOld.equals(typeNew)) {
                typeOld.getSouscriptionLicenceList().remove(souscriptionLicence);
                typeOld = getEntityManager().merge(typeOld);
            }
            if (typeNew != null && !typeNew.equals(typeOld)) {
                typeNew.getSouscriptionLicenceList().add(souscriptionLicence);
                typeNew = getEntityManager().merge(typeNew);
            }
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = souscriptionLicence.getId();
                if (findSouscriptionLicence(id) == null) {
                    throw new NonexistentEntityException("The souscriptionLicence with id " + id + " no longer exists.");
                }
            }
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @Override
    public void supprimer(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        try {
            SouscriptionLicence souscriptionLicence;
            try {
                souscriptionLicence = getEntityManager().getReference(SouscriptionLicence.class, id);
                souscriptionLicence.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The souscriptionLicence with id " + id + " no longer exists.", enfe);
            }
            Societe societe = souscriptionLicence.getSociete();
            if (societe != null) {
                societe.getSouscriptionLicenceList().remove(souscriptionLicence);
                societe = getEntityManager().merge(societe);
            }
            TypeLicence type = souscriptionLicence.getType();
            if (type != null) {
                type.getSouscriptionLicenceList().remove(souscriptionLicence);
                type = getEntityManager().merge(type);
            }
            getEntityManager().remove(souscriptionLicence);
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @Override
    public List<SouscriptionLicence> findSouscriptionLicenceEntities() {
        return findSouscriptionLicenceEntities(true, -1, -1);
    }

    @Override
    public List<SouscriptionLicence> findSouscriptionLicenceEntities(int maxResults, int firstResult) {
        return findSouscriptionLicenceEntities(false, maxResults, firstResult);
    }

    private List<SouscriptionLicence> findSouscriptionLicenceEntities(boolean all, int maxResults, int firstResult) {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(SouscriptionLicence.class));
        Query q = getEntityManager().createQuery(cq);
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    @Override
    public SouscriptionLicence findSouscriptionLicence(Integer id) {
        return getEntityManager().find(SouscriptionLicence.class, id);
    }

    @Override
    public List<SouscriptionLicence> findAll(String societe) {
        Query q = getEntityManager().createNamedQuery("SouscriptionLicence.findAll");
        q.setParameter("imma", societe);
        return q.getResultList();
    }
}
