/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.IllegalOrphanException;
import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.SouscriptionLicence;
import com.eh2s.ocm.Entity.TypeLicence;
import java.util.ArrayList;
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
public class TypeLicenceFacade extends AbstractFacade<TypeLicence> implements TypeLicenceFacadeLocal {

    @PersistenceContext(unitName = "ccmanagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TypeLicenceFacade() {
        super(TypeLicence.class);
    }
    
    @Override
    public void creer(TypeLicence typeLicence) throws RollbackFailureException, Exception {
        if (typeLicence.getSouscriptionLicenceList() == null) {
            typeLicence.setSouscriptionLicenceList(new ArrayList<SouscriptionLicence>());
        }
        try {
            List<SouscriptionLicence> attachedSouscriptionLicenceList = new ArrayList<SouscriptionLicence>();
            for (SouscriptionLicence souscriptionLicenceListSouscriptionLicenceToAttach : typeLicence.getSouscriptionLicenceList()) {
                souscriptionLicenceListSouscriptionLicenceToAttach = getEntityManager().getReference(souscriptionLicenceListSouscriptionLicenceToAttach.getClass(), souscriptionLicenceListSouscriptionLicenceToAttach.getId());
                attachedSouscriptionLicenceList.add(souscriptionLicenceListSouscriptionLicenceToAttach);
            }
            typeLicence.setSouscriptionLicenceList(attachedSouscriptionLicenceList);
           TypeLicence t =  getEntityManager().merge(typeLicence);
            for (SouscriptionLicence souscriptionLicenceListSouscriptionLicence : t.getSouscriptionLicenceList()) {
               TypeLicence oldTypeOfSouscriptionLicenceListSouscriptionLicence = souscriptionLicenceListSouscriptionLicence.getType();
                souscriptionLicenceListSouscriptionLicence.setType(t);
                souscriptionLicenceListSouscriptionLicence = getEntityManager().merge(souscriptionLicenceListSouscriptionLicence);
                if (oldTypeOfSouscriptionLicenceListSouscriptionLicence != null) {
                    oldTypeOfSouscriptionLicenceListSouscriptionLicence.getSouscriptionLicenceList().remove(souscriptionLicenceListSouscriptionLicence);
                    oldTypeOfSouscriptionLicenceListSouscriptionLicence = getEntityManager().merge(oldTypeOfSouscriptionLicenceListSouscriptionLicence);
                }
            }
        } catch (Exception ex) {
           throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);
           
        } 
    }

    @Override
    public void MisAJour(TypeLicence typeLicence) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        try {
            TypeLicence persistentTypeLicence = getEntityManager().find(TypeLicence.class, typeLicence.getId());
            List<SouscriptionLicence> souscriptionLicenceListOld = persistentTypeLicence.getSouscriptionLicenceList();
            List<SouscriptionLicence> souscriptionLicenceListNew = typeLicence.getSouscriptionLicenceList();
            List<String> illegalOrphanMessages = null;
            for (SouscriptionLicence souscriptionLicenceListOldSouscriptionLicence : souscriptionLicenceListOld) {
                if (!souscriptionLicenceListNew.contains(souscriptionLicenceListOldSouscriptionLicence)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain SouscriptionLicence " + souscriptionLicenceListOldSouscriptionLicence + " since its type field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<SouscriptionLicence> attachedSouscriptionLicenceListNew = new ArrayList<SouscriptionLicence>();
            for (SouscriptionLicence souscriptionLicenceListNewSouscriptionLicenceToAttach : souscriptionLicenceListNew) {
                souscriptionLicenceListNewSouscriptionLicenceToAttach = getEntityManager().getReference(souscriptionLicenceListNewSouscriptionLicenceToAttach.getClass(), souscriptionLicenceListNewSouscriptionLicenceToAttach.getId());
                attachedSouscriptionLicenceListNew.add(souscriptionLicenceListNewSouscriptionLicenceToAttach);
            }
            souscriptionLicenceListNew = attachedSouscriptionLicenceListNew;
            typeLicence.setSouscriptionLicenceList(souscriptionLicenceListNew);
            typeLicence = getEntityManager().merge(typeLicence);
            for (SouscriptionLicence souscriptionLicenceListNewSouscriptionLicence : souscriptionLicenceListNew) {
                if (!souscriptionLicenceListOld.contains(souscriptionLicenceListNewSouscriptionLicence)) {
                    TypeLicence oldTypeOfSouscriptionLicenceListNewSouscriptionLicence = souscriptionLicenceListNewSouscriptionLicence.getType();
                    souscriptionLicenceListNewSouscriptionLicence.setType(typeLicence);
                    souscriptionLicenceListNewSouscriptionLicence = getEntityManager().merge(souscriptionLicenceListNewSouscriptionLicence);
                    if (oldTypeOfSouscriptionLicenceListNewSouscriptionLicence != null && !oldTypeOfSouscriptionLicenceListNewSouscriptionLicence.equals(typeLicence)) {
                        oldTypeOfSouscriptionLicenceListNewSouscriptionLicence.getSouscriptionLicenceList().remove(souscriptionLicenceListNewSouscriptionLicence);
                        oldTypeOfSouscriptionLicenceListNewSouscriptionLicence = getEntityManager().merge(oldTypeOfSouscriptionLicenceListNewSouscriptionLicence);
                    }
                }
            }
        } catch (Exception ex) {
           
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = typeLicence.getId();
                if (findTypeLicence(id) == null) {
                    throw new NonexistentEntityException("The typeLicence with id " + id + " no longer exists.");
                }
            }
           throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);
          
        }
    }

    @Override
    public void supprimer(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        try {
            TypeLicence typeLicence;
            try {
                typeLicence = getEntityManager().getReference(TypeLicence.class, id);
                typeLicence.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The typeLicence with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<SouscriptionLicence> souscriptionLicenceListOrphanCheck = typeLicence.getSouscriptionLicenceList();
            for (SouscriptionLicence souscriptionLicenceListOrphanCheckSouscriptionLicence : souscriptionLicenceListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This TypeLicence (" + typeLicence + ") cannot be destroyed since the SouscriptionLicence " + souscriptionLicenceListOrphanCheckSouscriptionLicence + " in its souscriptionLicenceList field has a non-nullable type field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            getEntityManager().remove(typeLicence);
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);
           
        } 
    }

    @Override
    public List<TypeLicence> findTypeLicenceEntities() {
        return findTypeLicenceEntities(true, -1, -1);
    }

    @Override
    public List<TypeLicence> findTypeLicenceEntities(int maxResults, int firstResult) {
        return findTypeLicenceEntities(false, maxResults, firstResult);
    }

    private List<TypeLicence> findTypeLicenceEntities(boolean all, int maxResults, int firstResult) {
         CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
            cq.select(cq.from(TypeLicence.class));
            Query q = getEntityManager().createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
    }

    @Override
    public TypeLicence findTypeLicence(Integer id) {
        return getEntityManager().find(TypeLicence.class, id);
    }
}
