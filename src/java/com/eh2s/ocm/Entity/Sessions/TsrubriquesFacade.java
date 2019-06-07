/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.IllegalOrphanException;
import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Tincidents;
import com.eh2s.ocm.Entity.Trubriques;
import com.eh2s.ocm.Entity.Tsrubriques;
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
import javax.persistence.criteria.Root;

/**
 *
 * @author messi
 */
@Stateless
public class TsrubriquesFacade extends AbstractFacade<Tsrubriques> implements TsrubriquesFacadeLocal {

    @PersistenceContext(unitName = "ccmanagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TsrubriquesFacade() {
        super(Tsrubriques.class);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public Tsrubriques creer(Tsrubriques tsrubriques) throws RollbackFailureException, Exception {
        if (tsrubriques.getTincidentsList() == null) {
            tsrubriques.setTincidentsList(new ArrayList<Tincidents>());
        }
        EntityManager emf = null;
        try {
            emf = getEntityManager();
            Trubriques rubriqueid = tsrubriques.getRubriqueid();
            if (rubriqueid != null) {
                rubriqueid = emf.getReference(rubriqueid.getClass(), rubriqueid.getId());
                tsrubriques.setRubriqueid(rubriqueid);
            }

            List<Tincidents> attachedTincidentsList = new ArrayList<Tincidents>();
            for (Tincidents tincidentsListTincidentsToAttach : tsrubriques.getTincidentsList()) {
                tincidentsListTincidentsToAttach = emf.getReference(tincidentsListTincidentsToAttach.getClass(), tincidentsListTincidentsToAttach.getId());
                attachedTincidentsList.add(tincidentsListTincidentsToAttach);
            }
            tsrubriques.setTincidentsList(attachedTincidentsList);
            Tsrubriques tsrubriques1 = emf.merge(tsrubriques);
            if (rubriqueid != null) {
                rubriqueid.getTsrubriquesList().add(tsrubriques1);
                rubriqueid = emf.merge(rubriqueid);
            }

            for (Tincidents tincidentsListTincidents : tsrubriques1.getTincidentsList()) {
                Tsrubriques oldSrubriqueidOfTincidentsListTincidents = tincidentsListTincidents.getSrubriqueid();
                tincidentsListTincidents.setSrubriqueid(tsrubriques1);
                tincidentsListTincidents = emf.merge(tincidentsListTincidents);
                if (oldSrubriqueidOfTincidentsListTincidents != null) {
                    oldSrubriqueidOfTincidentsListTincidents.getTincidentsList().remove(tincidentsListTincidents);
                    oldSrubriqueidOfTincidentsListTincidents = emf.merge(oldSrubriqueidOfTincidentsListTincidents);
                }
            }
            return tsrubriques1;
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void MisAJour(Tsrubriques tsrubriques) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager emf = null;
        try {
            emf = getEntityManager();
            Tsrubriques persistentTsrubriques = emf.find(Tsrubriques.class, tsrubriques.getId());
            Trubriques rubriqueidOld = persistentTsrubriques.getRubriqueid();
            Trubriques rubriqueidNew = tsrubriques.getRubriqueid();
            List<Tincidents> tincidentsListOld = persistentTsrubriques.getTincidentsList();
            List<Tincidents> tincidentsListNew = tsrubriques.getTincidentsList();
            List<String> illegalOrphanMessages = null;
            for (Tincidents tincidentsListOldTincidents : tincidentsListOld) {
                if (!tincidentsListNew.contains(tincidentsListOldTincidents)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tincidents " + tincidentsListOldTincidents + " since its srubriqueid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (rubriqueidNew != null) {
                rubriqueidNew = emf.getReference(rubriqueidNew.getClass(), rubriqueidNew.getId());
                tsrubriques.setRubriqueid(rubriqueidNew);
            }

            List<Tincidents> attachedTincidentsListNew = new ArrayList<Tincidents>();
            for (Tincidents tincidentsListNewTincidentsToAttach : tincidentsListNew) {
                tincidentsListNewTincidentsToAttach = emf.getReference(tincidentsListNewTincidentsToAttach.getClass(), tincidentsListNewTincidentsToAttach.getId());
                attachedTincidentsListNew.add(tincidentsListNewTincidentsToAttach);
            }
            tincidentsListNew = attachedTincidentsListNew;
            tsrubriques.setTincidentsList(tincidentsListNew);
            tsrubriques = emf.merge(tsrubriques);
            if (rubriqueidOld != null && !rubriqueidOld.equals(rubriqueidNew)) {
                rubriqueidOld.getTsrubriquesList().remove(tsrubriques);
                rubriqueidOld = emf.merge(rubriqueidOld);
            }
            if (rubriqueidNew != null && !rubriqueidNew.equals(rubriqueidOld)) {
                rubriqueidNew.getTsrubriquesList().add(tsrubriques);
                rubriqueidNew = emf.merge(rubriqueidNew);
            }

            for (Tincidents tincidentsListNewTincidents : tincidentsListNew) {
                if (!tincidentsListOld.contains(tincidentsListNewTincidents)) {
                    Tsrubriques oldSrubriqueidOfTincidentsListNewTincidents = tincidentsListNewTincidents.getSrubriqueid();
                    tincidentsListNewTincidents.setSrubriqueid(tsrubriques);
                    tincidentsListNewTincidents = emf.merge(tincidentsListNewTincidents);
                    if (oldSrubriqueidOfTincidentsListNewTincidents != null && !oldSrubriqueidOfTincidentsListNewTincidents.equals(tsrubriques)) {
                        oldSrubriqueidOfTincidentsListNewTincidents.getTincidentsList().remove(tincidentsListNewTincidents);
                        oldSrubriqueidOfTincidentsListNewTincidents = emf.merge(oldSrubriqueidOfTincidentsListNewTincidents);
                    }
                }
            }
        } catch (Exception ex) {

            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tsrubriques.getId();
                if (findTsrubriques(id) == null) {
                    throw new NonexistentEntityException("The tsrubriques with id " + id + " no longer exists.");
                }
            }
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void Supprimer(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager emf = null;
        try {
            emf = getEntityManager();
            Tsrubriques tsrubriques;
            try {
                tsrubriques = emf.getReference(Tsrubriques.class, id);
                tsrubriques.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tsrubriques with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Tincidents> tincidentsListOrphanCheck = tsrubriques.getTincidentsList();
            for (Tincidents tincidentsListOrphanCheckTincidents : tincidentsListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tsrubriques (" + tsrubriques + ") cannot be destroyed since the Tincidents " + tincidentsListOrphanCheckTincidents + " in its tincidentsList field has a non-nullable srubriqueid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Trubriques rubriqueid = tsrubriques.getRubriqueid();
            if (rubriqueid != null) {
                rubriqueid.getTsrubriquesList().remove(tsrubriques);
                rubriqueid = emf.merge(rubriqueid);
            }

            emf.remove(tsrubriques);
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);
        }
    }

    @Override
    public List<Tsrubriques> findTsrubriquesEntities() {
        return findTsrubriquesEntities(true, -1, -1);
    }

    @Override
    public List<Tsrubriques> findTsrubriquesEntities(int maxResults, int firstResult) {
        return findTsrubriquesEntities(false, maxResults, firstResult);
    }

    private List<Tsrubriques> findTsrubriquesEntities(boolean all, int maxResults, int firstResult) {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Tsrubriques.class));
        Query q = getEntityManager().createQuery(cq);
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    @Override
    public Tsrubriques findTsrubriques(Integer id) {
        return getEntityManager().find(Tsrubriques.class, id);
    }

    @Override
    public int getTsrubriquesCount() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<Tsrubriques> rt = cq.from(Tsrubriques.class);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    @Override
    public List<Tsrubriques> findAll(String societe) {
        Query q = getEntityManager().createNamedQuery("Tsrubriques.findAll");
        q.setParameter("imma", societe);
        return q.getResultList();
    }

    @Override
    public List<Tsrubriques> findTsrubriquesByRubrique(int rubrique) {
        Query q = getEntityManager().createNamedQuery("Tsrubriques.findByRubrique");
        q.setParameter("id", rubrique);
        return q.getResultList();
    }
}
