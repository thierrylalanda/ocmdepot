/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Account;
import com.eh2s.ocm.Entity.Caisse;
import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Societe;
import com.eh2s.ocm.Entity.SortieCaisse;
import java.sql.Date;
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
 * @author Administrateur
 */
@Stateless
public class CaisseFacade extends AbstractFacade<Caisse> implements CaisseFacadeLocal {

    @PersistenceContext(unitName = "ccmanagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CaisseFacade() {
        super(Caisse.class);
    }

    @Override
    public Caisse findCaisseDuJour(int societe) {
        Query q = getEntityManager().createNamedQuery("Caisse.findByToDay");
        q.setParameter("societe", societe);
        if (!q.getResultList().isEmpty()) {
            return (Caisse) q.getResultList().get(0);
        }
        return new Caisse(0);
    }

    @Override
    public List<Caisse> findAll(int societe) {
        Query q = getEntityManager().createNamedQuery("Caisse.findBySociete");
        q.setParameter("societe", societe);
        return q.getResultList();
    }

    @Override
    public Caisse creer(Caisse caisse) throws RollbackFailureException, Exception {
        if (caisse.getSortieCaisseList() == null) {
            caisse.setSortieCaisseList(new ArrayList<SortieCaisse>());
        }
        if (caisse.getAccountList() == null) {
            caisse.setAccountList(new ArrayList<Account>());
        }
        try {
            Societe societe = caisse.getSociete();
            if (societe != null) {
                societe = getEntityManager().getReference(societe.getClass(), societe.getId());
                caisse.setSociete(societe);
            }
            List<SortieCaisse> attachedSortieCaisseList = new ArrayList<SortieCaisse>();
            for (SortieCaisse sortieCaisseListSortieCaisseToAttach : caisse.getSortieCaisseList()) {
                sortieCaisseListSortieCaisseToAttach = getEntityManager().getReference(sortieCaisseListSortieCaisseToAttach.getClass(), sortieCaisseListSortieCaisseToAttach.getId());
                attachedSortieCaisseList.add(sortieCaisseListSortieCaisseToAttach);
            }
            caisse.setSortieCaisseList(attachedSortieCaisseList);
            List<Account> attachedAccountList = new ArrayList<Account>();
            for (Account accountListAccountToAttach : caisse.getAccountList()) {
                accountListAccountToAttach = getEntityManager().getReference(accountListAccountToAttach.getClass(), accountListAccountToAttach.getId());
                attachedAccountList.add(accountListAccountToAttach);
            }
            caisse.setAccountList(attachedAccountList);
            getEntityManager().persist(caisse);
            if (societe != null) {
                societe.getCaisseList().add(caisse);
                societe = getEntityManager().merge(societe);
            }
            for (SortieCaisse sortieCaisseListSortieCaisse : caisse.getSortieCaisseList()) {
                Caisse oldCaisseOfSortieCaisseListSortieCaisse = sortieCaisseListSortieCaisse.getCaisse();
                sortieCaisseListSortieCaisse.setCaisse(caisse);
                sortieCaisseListSortieCaisse = getEntityManager().merge(sortieCaisseListSortieCaisse);
                if (oldCaisseOfSortieCaisseListSortieCaisse != null) {
                    oldCaisseOfSortieCaisseListSortieCaisse.getSortieCaisseList().remove(sortieCaisseListSortieCaisse);
                    oldCaisseOfSortieCaisseListSortieCaisse = getEntityManager().merge(oldCaisseOfSortieCaisseListSortieCaisse);
                }
            }
            for (Account accountListAccount : caisse.getAccountList()) {
                Caisse oldCaisseOfAccountListAccount = accountListAccount.getCaisse();
                accountListAccount.setCaisse(caisse);
                accountListAccount = getEntityManager().merge(accountListAccount);
                if (oldCaisseOfAccountListAccount != null) {
                    oldCaisseOfAccountListAccount.getAccountList().remove(accountListAccount);
                    oldCaisseOfAccountListAccount = getEntityManager().merge(oldCaisseOfAccountListAccount);
                }
            }
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
        return caisse;
    }

    @Override
    public Caisse MisAjour(Caisse caisse) throws NonexistentEntityException, RollbackFailureException, Exception {

        try {
            Caisse persistentCaisse = getEntityManager().find(Caisse.class, caisse.getId());
            Societe societeOld = persistentCaisse.getSociete();
            Societe societeNew = caisse.getSociete();
            List<SortieCaisse> sortieCaisseListOld = persistentCaisse.getSortieCaisseList();
            List<SortieCaisse> sortieCaisseListNew = caisse.getSortieCaisseList();
            List<Account> accountListOld = persistentCaisse.getAccountList();
            List<Account> accountListNew = caisse.getAccountList();
            List<String> illegalOrphanMessages = null;
            for (SortieCaisse sortieCaisseListOldSortieCaisse : sortieCaisseListOld) {
                if (!sortieCaisseListNew.contains(sortieCaisseListOldSortieCaisse)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain SortieCaisse " + sortieCaisseListOldSortieCaisse + " since its caisse field is not nullable.");
                }
            }
            if (societeNew != null) {
                societeNew = getEntityManager().getReference(societeNew.getClass(), societeNew.getId());
                caisse.setSociete(societeNew);
            }
            List<SortieCaisse> attachedSortieCaisseListNew = new ArrayList<SortieCaisse>();
            for (SortieCaisse sortieCaisseListNewSortieCaisseToAttach : sortieCaisseListNew) {
                sortieCaisseListNewSortieCaisseToAttach = getEntityManager().getReference(sortieCaisseListNewSortieCaisseToAttach.getClass(), sortieCaisseListNewSortieCaisseToAttach.getId());
                attachedSortieCaisseListNew.add(sortieCaisseListNewSortieCaisseToAttach);
            }
            sortieCaisseListNew = attachedSortieCaisseListNew;
            caisse.setSortieCaisseList(sortieCaisseListNew);
            List<Account> attachedAccountListNew = new ArrayList<Account>();
            for (Account accountListNewAccountToAttach : accountListNew) {
                accountListNewAccountToAttach = getEntityManager().getReference(accountListNewAccountToAttach.getClass(), accountListNewAccountToAttach.getId());
                attachedAccountListNew.add(accountListNewAccountToAttach);
            }
            accountListNew = attachedAccountListNew;
            caisse.setAccountList(accountListNew);
            caisse = getEntityManager().merge(caisse);
            if (societeOld != null && !societeOld.equals(societeNew)) {
                societeOld.getCaisseList().remove(caisse);
                societeOld = getEntityManager().merge(societeOld);
            }
            if (societeNew != null && !societeNew.equals(societeOld)) {
                societeNew.getCaisseList().add(caisse);
                societeNew = getEntityManager().merge(societeNew);
            }
            for (SortieCaisse sortieCaisseListNewSortieCaisse : sortieCaisseListNew) {
                if (!sortieCaisseListOld.contains(sortieCaisseListNewSortieCaisse)) {
                    Caisse oldCaisseOfSortieCaisseListNewSortieCaisse = sortieCaisseListNewSortieCaisse.getCaisse();
                    sortieCaisseListNewSortieCaisse.setCaisse(caisse);
                    sortieCaisseListNewSortieCaisse = getEntityManager().merge(sortieCaisseListNewSortieCaisse);
                    if (oldCaisseOfSortieCaisseListNewSortieCaisse != null && !oldCaisseOfSortieCaisseListNewSortieCaisse.equals(caisse)) {
                        oldCaisseOfSortieCaisseListNewSortieCaisse.getSortieCaisseList().remove(sortieCaisseListNewSortieCaisse);
                        oldCaisseOfSortieCaisseListNewSortieCaisse = getEntityManager().merge(oldCaisseOfSortieCaisseListNewSortieCaisse);
                    }
                }
            }
            for (Account accountListOldAccount : accountListOld) {
                if (!accountListNew.contains(accountListOldAccount)) {
                    accountListOldAccount.setCaisse(null);
                    accountListOldAccount = getEntityManager().merge(accountListOldAccount);
                }
            }
            for (Account accountListNewAccount : accountListNew) {
                if (!accountListOld.contains(accountListNewAccount)) {
                    Caisse oldCaisseOfAccountListNewAccount = accountListNewAccount.getCaisse();
                    accountListNewAccount.setCaisse(caisse);
                    accountListNewAccount = getEntityManager().merge(accountListNewAccount);
                    if (oldCaisseOfAccountListNewAccount != null && !oldCaisseOfAccountListNewAccount.equals(caisse)) {
                        oldCaisseOfAccountListNewAccount.getAccountList().remove(accountListNewAccount);
                        oldCaisseOfAccountListNewAccount = getEntityManager().merge(oldCaisseOfAccountListNewAccount);
                    }
                }
            }
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
        return caisse;
    }

    @Override
    public void Supprimer(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {

        try {
            Caisse caisse;
            try {
                caisse = getEntityManager().getReference(Caisse.class, id);
                caisse.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The caisse with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<SortieCaisse> sortieCaisseListOrphanCheck = caisse.getSortieCaisseList();
            for (SortieCaisse sortieCaisseListOrphanCheckSortieCaisse : sortieCaisseListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Caisse (" + caisse + ") cannot be destroyed since the SortieCaisse " + sortieCaisseListOrphanCheckSortieCaisse + " in its sortieCaisseList field has a non-nullable caisse field.");
            }

            Societe societe = caisse.getSociete();
            if (societe != null) {
                societe.getCaisseList().remove(caisse);
                societe = getEntityManager().merge(societe);
            }
            List<Account> accountList = caisse.getAccountList();
            for (Account accountListAccount : accountList) {
                accountListAccount.setCaisse(null);
                accountListAccount = getEntityManager().merge(accountListAccount);
            }
            getEntityManager().remove(caisse);
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    public List<Caisse> findCaisseEntities() {
        return findCaisseEntities(true, -1, -1);
    }

    public List<Caisse> findCaisseEntities(int maxResults, int firstResult) {
        return findCaisseEntities(false, maxResults, firstResult);
    }

    private List<Caisse> findCaisseEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Caisse.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Caisse findCaisse(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Caisse.class, id);
        } finally {
            em.close();
        }
    }

    public int getCaisseCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Caisse> rt = cq.from(Caisse.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    @Override
    public Caisse findCaisseByDate(Date d, int societe) {
        Query q = getEntityManager().createNamedQuery("Caisse.findBySocieteAndDate");
        q.setParameter("societe", societe);
        q.setParameter("d", d);
        if (!q.getResultList().isEmpty()) {
            return (Caisse) q.getResultList().get(q.getResultList().size()-1);
        }
        return new Caisse(0);
    }

}
