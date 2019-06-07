/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Societe;
import com.eh2s.ocm.Entity.Tgroups;
import com.eh2s.ocm.Entity.Tusers;
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
public class TgroupsFacade extends AbstractFacade<Tgroups> implements TgroupsFacadeLocal {

    @PersistenceContext(unitName = "ccmanagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TgroupsFacade() {
        super(Tgroups.class);
    }

    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    @Override
    public Tgroups creer(Tgroups tgroups) throws RollbackFailureException, Exception {
        if (tgroups.getTusersList() == null) {
            tgroups.setTusersList(new ArrayList<Tusers>());
        }

        try {
            Societe societe = tgroups.getSociete();
            if (societe != null) {
                societe = getEntityManager().getReference(societe.getClass(), societe.getId());
                tgroups.setSociete(societe);
            }
            List<Tusers> attachedTusersList = new ArrayList<Tusers>();
            for (Tusers tusersListTusersToAttach : tgroups.getTusersList()) {
                tusersListTusersToAttach = getEntityManager().getReference(tusersListTusersToAttach.getClass(), tusersListTusersToAttach.getId());
                attachedTusersList.add(tusersListTusersToAttach);
            }
            tgroups.setTusersList(attachedTusersList);
            tgroups = getEntityManager().merge(tgroups);
            if (societe != null) {
                societe.getTgroupsList().add(tgroups);
                societe = getEntityManager().merge(societe);
            }
            for (Tusers tusersListTusers : tgroups.getTusersList()) {
                Tgroups oldGroupeidOfTusersListTusers = tusersListTusers.getGroupeid();
                tusersListTusers.setGroupeid(tgroups);
                tusersListTusers = getEntityManager().merge(tusersListTusers);
                if (oldGroupeidOfTusersListTusers != null) {
                    oldGroupeidOfTusersListTusers.getTusersList().remove(tusersListTusers);
                    oldGroupeidOfTusersListTusers = getEntityManager().merge(oldGroupeidOfTusersListTusers);
                }
            }
            return tgroups;
        } catch (Exception ex) {
            throw new RollbackFailureException("une erreur est survenue le group n'a pas été enregistrer", ex);

        }
    }

    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    @Override
    public void MisAJour(Tgroups tgroups) throws NonexistentEntityException, RollbackFailureException, Exception {
        try {
            Tgroups persistentTgroups = getEntityManager().find(Tgroups.class, tgroups.getId());
            Societe societeOld = persistentTgroups.getSociete();
            Societe societeNew = tgroups.getSociete();
            List<Tusers> tusersListOld = persistentTgroups.getTusersList();
            List<Tusers> tusersListNew = tgroups.getTusersList();

            if (societeNew != null) {
                societeNew = getEntityManager().getReference(societeNew.getClass(), societeNew.getId());
                tgroups.setSociete(societeNew);
            }
            List<Tusers> attachedTusersListNew = new ArrayList<Tusers>();
            for (Tusers tusersListNewTusersToAttach : tusersListNew) {
                tusersListNewTusersToAttach = getEntityManager().getReference(tusersListNewTusersToAttach.getClass(), tusersListNewTusersToAttach.getId());
                attachedTusersListNew.add(tusersListNewTusersToAttach);
            }
            tusersListNew = attachedTusersListNew;
            tgroups.setTusersList(tusersListNew);
            tgroups = getEntityManager().merge(tgroups);
            if (societeOld != null && !societeOld.equals(societeNew)) {
                societeOld.getTgroupsList().remove(tgroups);
                societeOld = getEntityManager().merge(societeOld);
            }
            if (societeNew != null && !societeNew.equals(societeOld)) {
                societeNew.getTgroupsList().add(tgroups);
                societeNew = getEntityManager().merge(societeNew);
            }
            for (Tusers tusersListNewTusers : tusersListNew) {
                if (!tusersListOld.contains(tusersListNewTusers)) {
                    Tgroups oldGroupeidOfTusersListNewTusers = tusersListNewTusers.getGroupeid();
                    tusersListNewTusers.setGroupeid(tgroups);
                    tusersListNewTusers = getEntityManager().merge(tusersListNewTusers);
                    if (oldGroupeidOfTusersListNewTusers != null && !oldGroupeidOfTusersListNewTusers.equals(tgroups)) {
                        oldGroupeidOfTusersListNewTusers.getTusersList().remove(tusersListNewTusers);
                        oldGroupeidOfTusersListNewTusers = getEntityManager().merge(oldGroupeidOfTusersListNewTusers);
                    }
                }
            }
        } catch (Exception ex) {

            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tgroups.getId();
                if (findTgroups(id) == null) {
                    throw new NonexistentEntityException("le group avec l'id " + id + " n'éxiste pas.");
                }
            }
            throw new RollbackFailureException("une erreur est survenue le group n'a pas été mis à jour.", ex);

        }
    }

    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    @Override
    public void Supprimer(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        try {
            Tgroups tgroups;
            try {
                tgroups = getEntityManager().getReference(Tgroups.class, id);
                tgroups.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tgroups with id " + id + " no longer exists.", enfe);
            }

            Societe societe = tgroups.getSociete();
            if (societe != null) {
                societe.getTgroupsList().remove(tgroups);
                societe = getEntityManager().merge(societe);
            }
            getEntityManager().remove(tgroups);

        } catch (Exception ex) {
            throw new RollbackFailureException("une erreur est survenue le group n'a pas été supprimer.", ex);

        }
    }

    @Override
    public List<Tgroups> findTgroupsEntities() {
        return findTgroupsEntities(true, -1, -1);
    }

    @Override
    public List<Tgroups> findTgroupsEntities(int maxResults, int firstResult) {
        return findTgroupsEntities(false, maxResults, firstResult);
    }

    private List<Tgroups> findTgroupsEntities(boolean all, int maxResults, int firstResult) {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Tgroups.class));
        Query q = getEntityManager().createQuery(cq);
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    @Override
    public Tgroups findTgroups(Integer id) {
        return getEntityManager().find(Tgroups.class, id);
    }

    @Override
    public int getTgroupsCount() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<Tgroups> rt = cq.from(Tgroups.class);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();

    }

    @Override
    public List<Tgroups> findAll(String societe) {
        Query q = getEntityManager().createNamedQuery("Tgroups.findAll");
        q.setParameter("imma", societe);
        return q.getResultList();
    }
}
