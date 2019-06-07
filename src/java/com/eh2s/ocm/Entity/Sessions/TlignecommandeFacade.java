/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.CommandeEmballage;
import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.LigneAccount;
import com.eh2s.ocm.Entity.Societe;
import com.eh2s.ocm.Entity.Tclients;
import com.eh2s.ocm.Entity.Tcommandes;
import com.eh2s.ocm.Entity.Tetatc;
import com.eh2s.ocm.Entity.Tlignecommande;
import com.eh2s.ocm.Entity.Tourner;
import com.eh2s.ocm.Entity.Tusers;
import com.eh2s.ocm.UtilityFonctions.statistique;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author messi01
 */
@Stateless
public class TlignecommandeFacade extends AbstractFacade<Tlignecommande> implements TlignecommandeFacadeLocal {

    @PersistenceContext(unitName = "ccmanagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TlignecommandeFacade() {
        super(Tlignecommande.class);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public Tlignecommande creer(Tlignecommande tlignecommande) throws RollbackFailureException, Exception {
        if (tlignecommande.getCommandeEmballageList() == null) {
            tlignecommande.setCommandeEmballageList(new ArrayList<CommandeEmballage>());
        }
        if (tlignecommande.getLigneAccountList() == null) {
            tlignecommande.setLigneAccountList(new ArrayList<LigneAccount>());
        }
        if (tlignecommande.getTcommandesList() == null) {
            tlignecommande.setTcommandesList(new ArrayList<Tcommandes>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            Tetatc etatc = tlignecommande.getEtatc();
            if (etatc != null) {
                etatc = em.getReference(etatc.getClass(), etatc.getId());
                tlignecommande.setEtatc(etatc);
            }
            Societe societe = tlignecommande.getSociete();
            if (societe != null) {
                societe = em.getReference(societe.getClass(), societe.getId());
                tlignecommande.setSociete(societe);
            }
            Tclients client = tlignecommande.getClient();
            if (client != null) {
                client = em.getReference(client.getClass(), client.getId());
                tlignecommande.setClient(client);
            }
            Tourner tourner = tlignecommande.getTourner();
            if (tourner != null) {
                tourner = em.getReference(tourner.getClass(), tourner.getId());
                tlignecommande.setTourner(tourner);
            }
            Tusers preselleur = tlignecommande.getPreselleur();
            if (preselleur != null) {
                preselleur = em.getReference(preselleur.getClass(), preselleur.getId());
                tlignecommande.setPreselleur(preselleur);
            }
            List<CommandeEmballage> attachedCommandeEmballageList = new ArrayList<CommandeEmballage>();
            for (CommandeEmballage commandeEmballageListCommandeEmballageToAttach : tlignecommande.getCommandeEmballageList()) {
                commandeEmballageListCommandeEmballageToAttach = em.getReference(commandeEmballageListCommandeEmballageToAttach.getClass(), commandeEmballageListCommandeEmballageToAttach.getId());
                attachedCommandeEmballageList.add(commandeEmballageListCommandeEmballageToAttach);
            }
            tlignecommande.setCommandeEmballageList(attachedCommandeEmballageList);
            List<LigneAccount> attachedLigneAccountList = new ArrayList<LigneAccount>();
            for (LigneAccount ligneAccountListLigneAccountToAttach : tlignecommande.getLigneAccountList()) {
                ligneAccountListLigneAccountToAttach = em.getReference(ligneAccountListLigneAccountToAttach.getClass(), ligneAccountListLigneAccountToAttach.getId());
                attachedLigneAccountList.add(ligneAccountListLigneAccountToAttach);
            }
            tlignecommande.setLigneAccountList(attachedLigneAccountList);
            List<Tcommandes> attachedTcommandesList = new ArrayList<Tcommandes>();
            for (Tcommandes tcommandesListTcommandesToAttach : tlignecommande.getTcommandesList()) {
                tcommandesListTcommandesToAttach = em.getReference(tcommandesListTcommandesToAttach.getClass(), tcommandesListTcommandesToAttach.getId());
                attachedTcommandesList.add(tcommandesListTcommandesToAttach);
            }
            tlignecommande.setTcommandesList(attachedTcommandesList);
            em.persist(tlignecommande);
            if (etatc != null) {
                etatc.getTlignecommandeList().add(tlignecommande);
                etatc = em.merge(etatc);
            }
            if (societe != null) {
                societe.getTlignecommandeList().add(tlignecommande);
                societe = em.merge(societe);
            }
            if (client != null) {
                client.getTlignecommandeList().add(tlignecommande);
                client = em.merge(client);
            }
            if (tourner != null) {
                tourner.getTlignecommandeList().add(tlignecommande);
                tourner = em.merge(tourner);
            }
            if (preselleur != null) {
                preselleur.getTlignecommandeList().add(tlignecommande);
                preselleur = em.merge(preselleur);
            }
            for (CommandeEmballage commandeEmballageListCommandeEmballage : tlignecommande.getCommandeEmballageList()) {
                Tlignecommande oldLigneOfCommandeEmballageListCommandeEmballage = commandeEmballageListCommandeEmballage.getLigne();
                commandeEmballageListCommandeEmballage.setLigne(tlignecommande);
                commandeEmballageListCommandeEmballage = em.merge(commandeEmballageListCommandeEmballage);
                if (oldLigneOfCommandeEmballageListCommandeEmballage != null) {
                    oldLigneOfCommandeEmballageListCommandeEmballage.getCommandeEmballageList().remove(commandeEmballageListCommandeEmballage);
                    oldLigneOfCommandeEmballageListCommandeEmballage = em.merge(oldLigneOfCommandeEmballageListCommandeEmballage);
                }
            }
            for (LigneAccount ligneAccountListLigneAccount : tlignecommande.getLigneAccountList()) {
                Tlignecommande oldLigneCommandeOfLigneAccountListLigneAccount = ligneAccountListLigneAccount.getLigneCommande();
                ligneAccountListLigneAccount.setLigneCommande(tlignecommande);
                ligneAccountListLigneAccount = em.merge(ligneAccountListLigneAccount);
                if (oldLigneCommandeOfLigneAccountListLigneAccount != null) {
                    oldLigneCommandeOfLigneAccountListLigneAccount.getLigneAccountList().remove(ligneAccountListLigneAccount);
                    oldLigneCommandeOfLigneAccountListLigneAccount = em.merge(oldLigneCommandeOfLigneAccountListLigneAccount);
                }
            }
            for (Tcommandes tcommandesListTcommandes : tlignecommande.getTcommandesList()) {
                Tlignecommande oldLigneOfTcommandesListTcommandes = tcommandesListTcommandes.getLigne();
                tcommandesListTcommandes.setLigne(tlignecommande);
                tcommandesListTcommandes = em.merge(tcommandesListTcommandes);
                if (oldLigneOfTcommandesListTcommandes != null) {
                    oldLigneOfTcommandesListTcommandes.getTcommandesList().remove(tcommandesListTcommandes);
                    oldLigneOfTcommandesListTcommandes = em.merge(oldLigneOfTcommandesListTcommandes);
                }
            }
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
        return tlignecommande;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public Tlignecommande MisAJour(Tlignecommande tlignecommande) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            Tlignecommande persistentTlignecommande = em.find(Tlignecommande.class, tlignecommande.getId());
            Tetatc etatcOld = persistentTlignecommande.getEtatc();
            Tetatc etatcNew = tlignecommande.getEtatc();
            Societe societeOld = persistentTlignecommande.getSociete();
            Societe societeNew = tlignecommande.getSociete();
            Tclients clientOld = persistentTlignecommande.getClient();
            Tclients clientNew = tlignecommande.getClient();
            Tourner tournerOld = persistentTlignecommande.getTourner();
            Tourner tournerNew = tlignecommande.getTourner();
            Tusers preselleurOld = persistentTlignecommande.getPreselleur();
            Tusers preselleurNew = tlignecommande.getPreselleur();
            List<CommandeEmballage> commandeEmballageListOld = persistentTlignecommande.getCommandeEmballageList();
            List<CommandeEmballage> commandeEmballageListNew = tlignecommande.getCommandeEmballageList();
            List<LigneAccount> ligneAccountListOld = persistentTlignecommande.getLigneAccountList();
            List<LigneAccount> ligneAccountListNew = tlignecommande.getLigneAccountList();
            List<Tcommandes> tcommandesListOld = persistentTlignecommande.getTcommandesList();
            List<Tcommandes> tcommandesListNew = tlignecommande.getTcommandesList();
            if (etatcNew != null) {
                etatcNew = em.getReference(etatcNew.getClass(), etatcNew.getId());
                tlignecommande.setEtatc(etatcNew);
            }
            if (societeNew != null) {
                societeNew = em.getReference(societeNew.getClass(), societeNew.getId());
                tlignecommande.setSociete(societeNew);
            }
            if (clientNew != null) {
                clientNew = em.getReference(clientNew.getClass(), clientNew.getId());
                tlignecommande.setClient(clientNew);
            }
            if (tournerNew != null) {
                tournerNew = em.getReference(tournerNew.getClass(), tournerNew.getId());
                tlignecommande.setTourner(tournerNew);
            }
            if (preselleurNew != null) {
                preselleurNew = em.getReference(preselleurNew.getClass(), preselleurNew.getId());
                tlignecommande.setPreselleur(preselleurNew);
            }
            List<CommandeEmballage> attachedCommandeEmballageListNew = new ArrayList<CommandeEmballage>();
            for (CommandeEmballage commandeEmballageListNewCommandeEmballageToAttach : commandeEmballageListNew) {
                commandeEmballageListNewCommandeEmballageToAttach = em.getReference(commandeEmballageListNewCommandeEmballageToAttach.getClass(), commandeEmballageListNewCommandeEmballageToAttach.getId());
                attachedCommandeEmballageListNew.add(commandeEmballageListNewCommandeEmballageToAttach);
            }
            commandeEmballageListNew = attachedCommandeEmballageListNew;
            tlignecommande.setCommandeEmballageList(commandeEmballageListNew);
            List<LigneAccount> attachedLigneAccountListNew = new ArrayList<LigneAccount>();
            for (LigneAccount ligneAccountListNewLigneAccountToAttach : ligneAccountListNew) {
                ligneAccountListNewLigneAccountToAttach = em.getReference(ligneAccountListNewLigneAccountToAttach.getClass(), ligneAccountListNewLigneAccountToAttach.getId());
                attachedLigneAccountListNew.add(ligneAccountListNewLigneAccountToAttach);
            }
            ligneAccountListNew = attachedLigneAccountListNew;
            tlignecommande.setLigneAccountList(ligneAccountListNew);
            List<Tcommandes> attachedTcommandesListNew = new ArrayList<Tcommandes>();
            for (Tcommandes tcommandesListNewTcommandesToAttach : tcommandesListNew) {
                tcommandesListNewTcommandesToAttach = em.getReference(tcommandesListNewTcommandesToAttach.getClass(), tcommandesListNewTcommandesToAttach.getId());
                attachedTcommandesListNew.add(tcommandesListNewTcommandesToAttach);
            }
            tcommandesListNew = attachedTcommandesListNew;
            tlignecommande.setTcommandesList(tcommandesListNew);
            tlignecommande = em.merge(tlignecommande);
            if (etatcOld != null && !etatcOld.equals(etatcNew)) {
                etatcOld.getTlignecommandeList().remove(tlignecommande);
                etatcOld = em.merge(etatcOld);
            }
            if (etatcNew != null && !etatcNew.equals(etatcOld)) {
                etatcNew.getTlignecommandeList().add(tlignecommande);
                etatcNew = em.merge(etatcNew);
            }
            if (societeOld != null && !societeOld.equals(societeNew)) {
                societeOld.getTlignecommandeList().remove(tlignecommande);
                societeOld = em.merge(societeOld);
            }
            if (societeNew != null && !societeNew.equals(societeOld)) {
                societeNew.getTlignecommandeList().add(tlignecommande);
                societeNew = em.merge(societeNew);
            }
            if (clientOld != null && !clientOld.equals(clientNew)) {
                clientOld.getTlignecommandeList().remove(tlignecommande);
                clientOld = em.merge(clientOld);
            }
            if (clientNew != null && !clientNew.equals(clientOld)) {
                clientNew.getTlignecommandeList().add(tlignecommande);
                clientNew = em.merge(clientNew);
            }
            if (tournerOld != null && !tournerOld.equals(tournerNew)) {
                tournerOld.getTlignecommandeList().remove(tlignecommande);
                tournerOld = em.merge(tournerOld);
            }
            if (tournerNew != null && !tournerNew.equals(tournerOld)) {
                tournerNew.getTlignecommandeList().add(tlignecommande);
                tournerNew = em.merge(tournerNew);
            }
            if (preselleurOld != null && !preselleurOld.equals(preselleurNew)) {
                preselleurOld.getTlignecommandeList().remove(tlignecommande);
                preselleurOld = em.merge(preselleurOld);
            }
            if (preselleurNew != null && !preselleurNew.equals(preselleurOld)) {
                preselleurNew.getTlignecommandeList().add(tlignecommande);
                preselleurNew = em.merge(preselleurNew);
            }
            for (CommandeEmballage commandeEmballageListOldCommandeEmballage : commandeEmballageListOld) {
                if (!commandeEmballageListNew.contains(commandeEmballageListOldCommandeEmballage)) {
                    commandeEmballageListOldCommandeEmballage.setLigne(null);
                    commandeEmballageListOldCommandeEmballage = em.merge(commandeEmballageListOldCommandeEmballage);
                }
            }
            for (CommandeEmballage commandeEmballageListNewCommandeEmballage : commandeEmballageListNew) {
                if (!commandeEmballageListOld.contains(commandeEmballageListNewCommandeEmballage)) {
                    Tlignecommande oldLigneOfCommandeEmballageListNewCommandeEmballage = commandeEmballageListNewCommandeEmballage.getLigne();
                    commandeEmballageListNewCommandeEmballage.setLigne(tlignecommande);
                    commandeEmballageListNewCommandeEmballage = em.merge(commandeEmballageListNewCommandeEmballage);
                    if (oldLigneOfCommandeEmballageListNewCommandeEmballage != null && !oldLigneOfCommandeEmballageListNewCommandeEmballage.equals(tlignecommande)) {
                        oldLigneOfCommandeEmballageListNewCommandeEmballage.getCommandeEmballageList().remove(commandeEmballageListNewCommandeEmballage);
                        oldLigneOfCommandeEmballageListNewCommandeEmballage = em.merge(oldLigneOfCommandeEmballageListNewCommandeEmballage);
                    }
                }
            }
            for (LigneAccount ligneAccountListOldLigneAccount : ligneAccountListOld) {
                if (!ligneAccountListNew.contains(ligneAccountListOldLigneAccount)) {
                    ligneAccountListOldLigneAccount.setLigneCommande(null);
                    ligneAccountListOldLigneAccount = em.merge(ligneAccountListOldLigneAccount);
                }
            }
            for (LigneAccount ligneAccountListNewLigneAccount : ligneAccountListNew) {
                if (!ligneAccountListOld.contains(ligneAccountListNewLigneAccount)) {
                    Tlignecommande oldLigneCommandeOfLigneAccountListNewLigneAccount = ligneAccountListNewLigneAccount.getLigneCommande();
                    ligneAccountListNewLigneAccount.setLigneCommande(tlignecommande);
                    ligneAccountListNewLigneAccount = em.merge(ligneAccountListNewLigneAccount);
                    if (oldLigneCommandeOfLigneAccountListNewLigneAccount != null && !oldLigneCommandeOfLigneAccountListNewLigneAccount.equals(tlignecommande)) {
                        oldLigneCommandeOfLigneAccountListNewLigneAccount.getLigneAccountList().remove(ligneAccountListNewLigneAccount);
                        oldLigneCommandeOfLigneAccountListNewLigneAccount = em.merge(oldLigneCommandeOfLigneAccountListNewLigneAccount);
                    }
                }
            }
            for (Tcommandes tcommandesListOldTcommandes : tcommandesListOld) {
                if (!tcommandesListNew.contains(tcommandesListOldTcommandes)) {
                    tcommandesListOldTcommandes.setLigne(null);
                    tcommandesListOldTcommandes = em.merge(tcommandesListOldTcommandes);
                }
            }
            for (Tcommandes tcommandesListNewTcommandes : tcommandesListNew) {
                if (!tcommandesListOld.contains(tcommandesListNewTcommandes)) {
                    Tlignecommande oldLigneOfTcommandesListNewTcommandes = tcommandesListNewTcommandes.getLigne();
                    tcommandesListNewTcommandes.setLigne(tlignecommande);
                    tcommandesListNewTcommandes = em.merge(tcommandesListNewTcommandes);
                    if (oldLigneOfTcommandesListNewTcommandes != null && !oldLigneOfTcommandesListNewTcommandes.equals(tlignecommande)) {
                        oldLigneOfTcommandesListNewTcommandes.getTcommandesList().remove(tcommandesListNewTcommandes);
                        oldLigneOfTcommandesListNewTcommandes = em.merge(oldLigneOfTcommandesListNewTcommandes);
                    }
                }
            }
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
        return tlignecommande;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void Supprimer(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            Tlignecommande tlignecommande;
            try {
                tlignecommande = em.getReference(Tlignecommande.class, id);
                tlignecommande.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tlignecommande with id " + id + " no longer exists.", enfe);
            }
            Tetatc etatc = tlignecommande.getEtatc();
            if (etatc != null) {
                etatc.getTlignecommandeList().remove(tlignecommande);
                etatc = em.merge(etatc);
            }
            Societe societe = tlignecommande.getSociete();
            if (societe != null) {
                societe.getTlignecommandeList().remove(tlignecommande);
                societe = em.merge(societe);
            }
            Tclients client = tlignecommande.getClient();
            if (client != null) {
                client.getTlignecommandeList().remove(tlignecommande);
                client = em.merge(client);
            }
            Tourner tourner = tlignecommande.getTourner();
            if (tourner != null) {
                tourner.getTlignecommandeList().remove(tlignecommande);
                tourner = em.merge(tourner);
            }
            Tusers preselleur = tlignecommande.getPreselleur();
            if (preselleur != null) {
                preselleur.getTlignecommandeList().remove(tlignecommande);
                preselleur = em.merge(preselleur);
            }
            List<CommandeEmballage> commandeEmballageList = tlignecommande.getCommandeEmballageList();
            for (CommandeEmballage commandeEmballageListCommandeEmballage : commandeEmballageList) {
                commandeEmballageListCommandeEmballage.setLigne(null);
                commandeEmballageListCommandeEmballage = em.merge(commandeEmballageListCommandeEmballage);
            }
            List<LigneAccount> ligneAccountList = tlignecommande.getLigneAccountList();
            for (LigneAccount ligneAccountListLigneAccount : ligneAccountList) {
                ligneAccountListLigneAccount.setLigneCommande(null);
                ligneAccountListLigneAccount = em.merge(ligneAccountListLigneAccount);
            }
            List<Tcommandes> tcommandesList = tlignecommande.getTcommandesList();
            for (Tcommandes tcommandesListTcommandes : tcommandesList) {
                tcommandesListTcommandes.setLigne(null);
                tcommandesListTcommandes = em.merge(tcommandesListTcommandes);
            }
            em.remove(tlignecommande);
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @Override
    public List<Tlignecommande> findTlignecommandeEntities() {
        return findTlignecommandeEntities(true, -1, -1);
    }

    @Override
    public List<Tlignecommande> findTlignecommandeEntities(int maxResults, int firstResult) {
        return findTlignecommandeEntities(false, maxResults, firstResult);
    }

    private List<Tlignecommande> findTlignecommandeEntities(boolean all, int maxResults, int firstResult) {
        getEntityManager().setProperty("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Tlignecommande.class));
        Query q = getEntityManager().createQuery(cq);
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    @Override
    public Tlignecommande findTlignecommande(Integer id) {
        getEntityManager().setProperty("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        return getEntityManager().find(Tlignecommande.class, id);
    }

    @Override
    public Tlignecommande Creerlignecommande(Tlignecommande ligne) {
        return getEntityManager().merge(ligne);
    }

    @Override
    public int getTlignecommandeCount() {
        getEntityManager().setProperty("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<Tlignecommande> rt = cq.from(Tlignecommande.class);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    @Override
    public List<Tlignecommande> findAll(String imma) {
        getEntityManager().setProperty("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        Query q = getEntityManager().createNamedQuery("Tlignecommande.findAll");
        q.setParameter("imma", imma);
        return q.getResultList();
    }

    @Override
    public List<Tlignecommande> findAllBySociete(int societe) {
        getEntityManager().setProperty("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        Query q = getEntityManager().createNamedQuery("Tlignecommande.findAllBySociete");
        q.setParameter("id", societe);
        return q.getResultList();
    }

    @Override
    public Tlignecommande findByNumc(int numc) {
        getEntityManager().setProperty("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        Query q = getEntityManager().createNamedQuery("Tlignecommande.findByNumc");
        q.setParameter("numc", numc);
        return (Tlignecommande) q.getSingleResult();
    }

    @Override
    public List<Tlignecommande> findByClient(int cli) {
        getEntityManager().setProperty("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        Query q = getEntityManager().createNamedQuery("Tlignecommande.findByClient");
        q.setParameter("clit", cli);
        return q.getResultList();
    }

    @Override
    public List<Tlignecommande> findTcommandesByPeriode(Date d, Date d1, String imma) {
        getEntityManager().setProperty("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        Query q = getEntityManager().createNamedQuery("Tlignecommande.findByDatec01");
        q.setParameter("d1", d);
        q.setParameter("d2", d1);
        q.setParameter("imma", imma);
        return q.getResultList();
    }

    @Override
    public List<Tlignecommande> findTcommandesByClientByPeriode(Date d, Date d1, int client) {
        getEntityManager().setProperty("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        Query q = getEntityManager().createNamedQuery("Tlignecommande.findByDateByClient01");
        q.setParameter("d1", d);
        q.setParameter("d2", d1);
        q.setParameter("cli", client);
        return q.getResultList();
    }

    @Override
    public List<Tlignecommande> findForFeuilleRoutePeriode(Date d, Date d1, int client, int code) {
        getEntityManager().setProperty("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        Query q = getEntityManager().createNamedQuery("Tlignecommande.findForFeuilleRoute");
        q.setParameter("d1", d);
        q.setParameter("d2", d1);
        q.setParameter("cli", client);
        q.setParameter("code", code);
        return q.getResultList();
    }

    @Override
    public List<Tlignecommande> findTcommandesByPeriodeForReporting(Date d, Date d1, int imma, int etat) {
        getEntityManager().setProperty("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        Query q = getEntityManager().createNamedQuery("Tlignecommande.findByPeriode");
        q.setParameter("d1", d);
        q.setParameter("d2", d1);
        q.setParameter("imma", imma);
        q.setParameter("code", etat);
        return q.getResultList();
    }

    @Override
    public List<Tlignecommande> VenteAnnuel(int annee, int societe, int etat) {
        getEntityManager().setProperty("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        Query q = getEntityManager().createNamedQuery("Tlignecommande.findByVenteAnnuel");
        q.setParameter("annee", annee);
        q.setParameter("id", societe);
        q.setParameter("code", etat);
        return q.getResultList();
    }

    // top National
    @Override
    public List<statistique> findTTopClientsNational(Date d, Date d1, int max, int societe, int etat) {
        getEntityManager().setProperty("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        List<statistique> l1 = new ArrayList<>();
        String sql = "SELECT SUM(tcommandes.quantite) AS qt, SUM(tcommandes.prix_total) AS pt, tclients.nom \n"
                + "FROM tcommandes, tlignecommande, tetatc, tclients \n"
                + "WHERE tcommandes.ligne = tlignecommande.id  AND tlignecommande.client = tclients.id \n"
                + "AND tlignecommande.societe = ?1 \n"
                + "AND tlignecommande.datelivraison >= ?2 AND tlignecommande.datelivraison <= ?3  \n"
                + "AND tlignecommande.etatc = tetatc.id AND tetatc.code = ?4 \n"
                + "GROUP BY 3 ORDER BY pt DESC LIMIT ?5;";
        Query q = getEntityManager().createNativeQuery(sql);
        q.setParameter("1", societe);
        q.setParameter("4", etat);
        q.setParameter("5", max);
        q.setParameter("2", d);
        q.setParameter("3", d1);
        List<Object[]> rs = (List<Object[]>) q.getResultList();
        for (Object[] r : rs) {
            statistique b = new statistique();
            b.setMontant(Double.parseDouble(r[1].toString()));
            b.setQt(Double.parseDouble(r[0].toString()));
            b.setNom(r[2].toString());
            l1.add(b);
        }
        return l1;
    }

    @Override
    public List<statistique> findTTopPresellerNational(Date d, Date d1, int max, int societe, int etat) {
        getEntityManager().setProperty("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        List<statistique> l1 = new ArrayList<>();
        String sql = "SELECT SUM(tcommandes.quantite) AS qt, SUM(tcommandes.prix_total) AS pt, tusers.firstname \n"
                + "FROM tcommandes, tlignecommande, tetatc, tusers \n"
                + "WHERE tcommandes.ligne = tlignecommande.id  AND tlignecommande.preselleur = tusers.id \n"
                + "AND tlignecommande.societe = ?1 AND tlignecommande.datelivraison >= ?2 \n"
                + "AND tlignecommande.datelivraison <= ?3 AND tlignecommande.etatc = tetatc.id AND tetatc.code = ?4 \n"
                + "GROUP BY 3 ORDER BY pt DESC LIMIT ?5;";
        Query q = getEntityManager().createNativeQuery(sql);
        q.setParameter("1", societe);
        q.setParameter("4", etat);
        q.setParameter("5", max);
        q.setParameter("2", d);
        q.setParameter("3", d1);
        List<Object[]> rs = (List<Object[]>) q.getResultList();
        for (Object[] r : rs) {
            statistique b = new statistique();
            b.setMontant(Double.parseDouble(r[1].toString()));
            b.setQt(Double.parseDouble(r[0].toString()));
            b.setNom(r[2].toString());
            l1.add(b);
        }
        return l1;
    }

    @Override
    public List<statistique> findTTopSecteursNational(Date d, Date d1, int max, int societe, int etat) {
        getEntityManager().setProperty("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        List<statistique> l1 = new ArrayList<>();
        String sql = "SELECT SUM(tcommandes.quantite) AS qt, SUM(tcommandes.prix_total) AS pt, tsecteurs.`name` \n"
                + "FROM tcommandes, tlignecommande, tetatc, tclients, tsecteurs\n"
                + "WHERE tcommandes.ligne = tlignecommande.id  AND tlignecommande.client = tclients.id \n"
                + "AND tclients.secteurid = tsecteurs.id AND tlignecommande.societe = ?1 \n"
                + "AND tlignecommande.datelivraison >= ?2 AND tlignecommande.datelivraison <= ?3  \n"
                + "AND tlignecommande.etatc = tetatc.id AND tetatc.code = ?4 \n"
                + "GROUP BY 3 ORDER BY pt DESC LIMIT ?5;";
        Query q = getEntityManager().createNativeQuery(sql);
        q.setParameter("1", societe);
        q.setParameter("4", etat);
        q.setParameter("5", max);
        q.setParameter("2", d);
        q.setParameter("3", d1);
        List<Object[]> rs = (List<Object[]>) q.getResultList();
        for (Object[] r : rs) {
            statistique b = new statistique();
            b.setMontant(Double.parseDouble(r[1].toString()));
            b.setQt(Double.parseDouble(r[0].toString()));
            b.setNom(r[2].toString());
            l1.add(b);
        }
        return l1;
    }

    @Override
    public List<statistique> findTTopVillesNational(Date d, Date d1, int max, int societe, int etat) {
        getEntityManager().setProperty("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        List<statistique> l1 = new ArrayList<>();
        String sql = "SELECT SUM(tcommandes.quantite) AS qt, SUM(tcommandes.prix_total) AS pt, tdistricts.`name` \n"
                + "FROM tcommandes, tlignecommande, tetatc, tclients, tsecteurs, tdistricts \n"
                + "WHERE tcommandes.ligne = tlignecommande.id  AND tlignecommande.client = tclients.id \n"
                + "AND tclients.secteurid = tsecteurs.id AND tsecteurs.districtid = tdistricts.id \n"
                + "AND tlignecommande.societe = ?1 AND tlignecommande.datelivraison >= ?2 \n"
                + "AND tlignecommande.datelivraison <= ?3 AND tlignecommande.etatc = tetatc.id AND tetatc.code = ?4 \n"
                + "GROUP BY 3 ORDER BY pt DESC LIMIT ?5;";
        Query q = getEntityManager().createNativeQuery(sql);
        q.setParameter("1", societe);
        q.setParameter("4", etat);
        q.setParameter("5", max);
        q.setParameter("2", d);
        q.setParameter("3", d1);
        List<Object[]> rs = (List<Object[]>) q.getResultList();
        for (Object[] r : rs) {
            statistique b = new statistique();
            b.setMontant(Double.parseDouble(r[1].toString()));
            b.setQt(Double.parseDouble(r[0].toString()));
            b.setNom(r[2].toString());
            l1.add(b);
        }
        return l1;
    }
    // top Regional

    @Override
    public List<statistique> findTTopRegionsNational(Date d, Date d1, int max, int societe, int etat) {
        getEntityManager().setProperty("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        List<statistique> l1 = new ArrayList<>();
        String sql = "SELECT SUM(tcommandes.quantite) AS qt, SUM(tcommandes.prix_total) AS pt, tregions.`name` \n"
                + "FROM tcommandes, tlignecommande, tetatc, tclients, tsecteurs, tdistricts, tregions \n"
                + "WHERE tcommandes.ligne = tlignecommande.id  AND tlignecommande.client = tclients.id \n"
                + "AND tclients.secteurid = tsecteurs.id AND tsecteurs.districtid = tdistricts.id AND tdistricts.regionid = tregions.id \n"
                + "AND tlignecommande.societe = ?1 AND tlignecommande.datelivraison >= ?2 \n"
                + "AND tlignecommande.datelivraison <= ?3 AND tlignecommande.etatc = tetatc.id AND tetatc.code = ?4 \n"
                + "GROUP BY 3 ORDER BY pt DESC LIMIT ?5;";
        Query q = getEntityManager().createNativeQuery(sql);
        q.setParameter("1", societe);
        q.setParameter("4", etat);
        q.setParameter("5", max);
        q.setParameter("2", d);
        q.setParameter("3", d1);
        List<Object[]> rs = (List<Object[]>) q.getResultList();
        for (Object[] r : rs) {
            statistique b = new statistique();
            b.setMontant(Double.parseDouble(r[1].toString()));
            b.setQt(Double.parseDouble(r[0].toString()));
            b.setNom(r[2].toString());
            l1.add(b);
        }
        return l1;
    }

    @Override
    public List<statistique> findTTopClientsRegional(Date d, Date d1, int max, int societe, int etat, int reg) {
        getEntityManager().setProperty("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        List<statistique> l1 = new ArrayList<>();
        String sql = "SELECT SUM(tcommandes.quantite) AS qt, SUM(tcommandes.prix_total) AS pt, tclients.nom \n"
                + "FROM tcommandes, tlignecommande, tetatc, tclients, tsecteurs, tdistricts \n"
                + "WHERE tcommandes.ligne = tlignecommande.id  AND tlignecommande.client = tclients.id \n"
                + "AND tclients.secteurid = tsecteurs.id AND tsecteurs.districtid = tdistricts.id \n"
                + "AND tdistricts.regionid = ?6 \n"
                + "AND tlignecommande.societe = ?1 AND tlignecommande.datelivraison >= ?2 \n"
                + "AND tlignecommande.datelivraison <= ?3 AND tlignecommande.etatc = tetatc.id AND tetatc.code = ?4 \n"
                + "GROUP BY 3 ORDER BY pt DESC LIMIT ?5;";
        Query q = getEntityManager().createNativeQuery(sql);
        q.setParameter("1", societe);
        q.setParameter("4", etat);
        q.setParameter("5", max);
        q.setParameter("2", d);
        q.setParameter("3", d1);
        q.setParameter("6", reg);
        List<Object[]> rs = (List<Object[]>) q.getResultList();
        for (Object[] r : rs) {
            statistique b = new statistique();
            b.setMontant(Double.parseDouble(r[1].toString()));
            b.setQt(Double.parseDouble(r[0].toString()));
            b.setNom(r[2].toString());
            l1.add(b);
        }
        return l1;
    }

    @Override
    public List<statistique> findTTopPresellerRegional(Date d, Date d1, int max, int societe, int etat, int reg) {
        getEntityManager().setProperty("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        List<statistique> l1 = new ArrayList<>();
        String sql = "SELECT SUM(tcommandes.quantite) AS qt, SUM(tcommandes.prix_total) AS pt, tusers.firstname\n"
                + "FROM tcommandes, tlignecommande, tetatc, tusers, tclients, tsecteurs, tdistricts \n"
                + "WHERE tcommandes.ligne = tlignecommande.id  AND tlignecommande.client = tclients.id \n"
                + "AND tlignecommande.preselleur = tusers.id \n"
                + "AND tclients.secteurid = tsecteurs.id AND tsecteurs.districtid = tdistricts.id AND tdistricts.regionid = ?6 \n"
                + "AND tlignecommande.societe = ?1 AND tlignecommande.datelivraison >= ?2 \n"
                + "AND tlignecommande.datelivraison <= ?3 AND tlignecommande.etatc = tetatc.id AND tetatc.code = ?4 \n"
                + "GROUP BY 3 ORDER BY pt DESC LIMIT ?5;";
        Query q = getEntityManager().createNativeQuery(sql);
        q.setParameter("1", societe);
        q.setParameter("4", etat);
        q.setParameter("5", max);
        q.setParameter("2", d);
        q.setParameter("3", d1);
        q.setParameter("6", reg);
        List<Object[]> rs = (List<Object[]>) q.getResultList();
        for (Object[] r : rs) {
            statistique b = new statistique();
            b.setMontant(Double.parseDouble(r[1].toString()));
            b.setQt(Double.parseDouble(r[0].toString()));
            b.setNom(r[2].toString());
            l1.add(b);
        }
        return l1;
    }

    @Override
    public List<statistique> findTTopSecteursRegional(Date d, Date d1, int max, int societe, int etat, int reg) {
        getEntityManager().setProperty("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        List<statistique> l1 = new ArrayList<>();
        String sql = "SELECT SUM(tcommandes.quantite) AS qt, SUM(tcommandes.prix_total) AS pt, tsecteurs.`name`\n"
                + "FROM tcommandes, tlignecommande, tetatc, tclients, tsecteurs, tdistricts\n"
                + "WHERE tcommandes.ligne = tlignecommande.id  AND tlignecommande.client = tclients.id \n"
                + "AND tclients.secteurid = tsecteurs.id AND tsecteurs.districtid = tdistricts.id AND tdistricts.regionid = ?6 \n"
                + "AND tlignecommande.societe = ?1 AND tlignecommande.datelivraison >= ?2 \n"
                + "AND tlignecommande.datelivraison <= ?3 AND tlignecommande.etatc = tetatc.id AND tetatc.code = ?4 \n"
                + "GROUP BY 3 ORDER BY pt DESC LIMIT ?5;";
        Query q = getEntityManager().createNativeQuery(sql);
        q.setParameter("1", societe);
        q.setParameter("4", etat);
        q.setParameter("5", max);
        q.setParameter("2", d);
        q.setParameter("3", d1);
        q.setParameter("6", reg);
        List<Object[]> rs = (List<Object[]>) q.getResultList();
        for (Object[] r : rs) {
            statistique b = new statistique();
            b.setMontant(Double.parseDouble(r[1].toString()));
            b.setQt(Double.parseDouble(r[0].toString()));
            b.setNom(r[2].toString());
            l1.add(b);
        }
        return l1;
    }

    @Override
    public List<statistique> findTTopVillesRegional(Date d, Date d1, int max, int societe, int etat, int reg) {
        getEntityManager().setProperty("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        List<statistique> l1 = new ArrayList<>();
        String sql = "SELECT SUM(tcommandes.quantite) AS qt, SUM(tcommandes.prix_total) AS pt, tdistricts.`name`\n"
                + "FROM tcommandes, tlignecommande, tetatc, tclients, tsecteurs, tdistricts\n"
                + "WHERE tcommandes.ligne = tlignecommande.id  AND tlignecommande.client = tclients.id \n"
                + "AND tclients.secteurid = tsecteurs.id AND tsecteurs.districtid = tdistricts.id AND tdistricts.regionid = ?6 \n"
                + "AND tlignecommande.societe = ?1 AND tlignecommande.datelivraison >= ?2 \n"
                + "AND tlignecommande.datelivraison <= ?3 AND tlignecommande.etatc = tetatc.id AND tetatc.code = ?4 \n"
                + "GROUP BY 3 ORDER BY pt DESC LIMIT ?5;";
        Query q = getEntityManager().createNativeQuery(sql);
        q.setParameter("1", societe);
        q.setParameter("4", etat);
        q.setParameter("5", max);
        q.setParameter("2", d);
        q.setParameter("3", d1);
        q.setParameter("6", reg);
        List<Object[]> rs = (List<Object[]>) q.getResultList();
        for (Object[] r : rs) {
            statistique b = new statistique();
            b.setMontant(Double.parseDouble(r[1].toString()));
            b.setQt(Double.parseDouble(r[0].toString()));
            b.setNom(r[2].toString());
            l1.add(b);
        }
        return l1;
    }

    // top Article Nationale
    @Override
    public List<statistique> findTTopArticlesNational(Date d, Date d1, int max, int societe, int etat) {
        getEntityManager().setProperty("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        List<statistique> l1 = new ArrayList<>();
        String sql = "SELECT SUM(tcommandes.quantite) AS qt, SUM(tcommandes.prix_total) AS pt, tarticles.nom, tarticles.code \n"
                + "FROM tcommandes, tarticles, tlignecommande, tetatc \n"
                + "WHERE tcommandes.article = tarticles.id AND tcommandes.ligne = tlignecommande.id AND tlignecommande.societe = ?1 \n"
                + "AND tlignecommande.datelivraison >= ?2 \n"
                + "AND tlignecommande.datelivraison <= ?3 AND tlignecommande.etatc = tetatc.id AND tetatc.code = ?4 \n"
                + "GROUP BY 3,4 ORDER BY pt DESC LIMIT ?5;";
        Query q = getEntityManager().createNativeQuery(sql);
        q.setParameter("1", societe);
        q.setParameter("4", etat);
        q.setParameter("5", max);
        q.setParameter("2", d);
        q.setParameter("3", d1);
        List<Object[]> rs = (List<Object[]>) q.getResultList();
        for (Object[] r : rs) {
            statistique b = new statistique();
            b.setMontant(Double.parseDouble(r[1].toString()));
            b.setQt(Double.parseDouble(r[0].toString()));
            b.setNom(r[2].toString());
            b.setCode(r[3].toString());
            l1.add(b);
        }
        return l1;
    }

    @Override
    public List<statistique> findTTopArticleMoinVenduNational(Date d, Date d1, int max, int societe, int etat) {
        getEntityManager().setProperty("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        List<statistique> l1 = new ArrayList<>();
        String sql = "SELECT SUM(tcommandes.quantite) AS qt, SUM(tcommandes.prix_total) AS pt, tarticles.nom, tarticles.code \n"
                + "FROM tcommandes, tarticles, tlignecommande, tetatc \n"
                + "WHERE tcommandes.article = tarticles.id AND tcommandes.ligne = tlignecommande.id AND tlignecommande.societe = ?1 \n"
                + "AND tlignecommande.datelivraison >= ?2 \n"
                + "AND tlignecommande.datelivraison <= ?3 AND tlignecommande.etatc = tetatc.id AND tetatc.code = ?4 \n"
                + "GROUP BY 3,4 ORDER BY pt ASC LIMIT ?5;";
        Query q = getEntityManager().createNativeQuery(sql);
        q.setParameter("1", societe);
        q.setParameter("4", etat);
        q.setParameter("5", max);
        q.setParameter("2", d);
        q.setParameter("3", d1);
        List<Object[]> rs = (List<Object[]>) q.getResultList();
        for (Object[] r : rs) {
            statistique b = new statistique();
            b.setMontant(Double.parseDouble(r[1].toString()));
            b.setQt(Double.parseDouble(r[0].toString()));
            b.setNom(r[2].toString());
            b.setCode(r[3].toString());
            l1.add(b);
        }
        return l1;
    }

    @Override
    public List<statistique> findTTopCategorieNational(Date d, Date d1, int max, int societe, int etat) {
        getEntityManager().setProperty("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        List<statistique> l1 = new ArrayList<>();
        String sql = "SELECT SUM(tcommandes.quantite) AS qt, SUM(tcommandes.prix_total) AS pt, tcategorie.nom \n"
                + "FROM tcommandes, tarticles, tlignecommande, tetatc, tcategorie\n"
                + "WHERE tcommandes.article = tarticles.id AND tarticles.categorie = tcategorie.id \n"
                + "AND tcommandes.ligne = tlignecommande.id AND tlignecommande.societe = ?1 \n"
                + "AND tlignecommande.datelivraison >= ?2 AND tlignecommande.datelivraison <= ?3 \n"
                + "AND tlignecommande.etatc = tetatc.id AND tetatc.code = ?4 \n"
                + "GROUP BY 3 ORDER BY pt DESC LIMIT ?5;";
        Query q = getEntityManager().createNativeQuery(sql);
        q.setParameter("1", societe);
        q.setParameter("4", etat);
        q.setParameter("5", max);
        q.setParameter("2", d);
        q.setParameter("3", d1);
        List<Object[]> rs = (List<Object[]>) q.getResultList();
        for (Object[] r : rs) {
            statistique b = new statistique();
            b.setMontant(Double.parseDouble(r[1].toString()));
            b.setQt(Double.parseDouble(r[0].toString()));
            b.setNom(r[2].toString());
            l1.add(b);
        }
        return l1;
    }

    @Override
    public List<statistique> findTTopCategorieMoinCommanderNational(Date d, Date d1, int max, int societe, int etat) {
        getEntityManager().setProperty("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        List<statistique> l1 = new ArrayList<>();
        String sql = "SELECT SUM(tcommandes.quantite) AS qt, SUM(tcommandes.prix_total) AS pt, tcategorie.nom \n"
                + "FROM tcommandes, tarticles, tlignecommande, tetatc, tcategorie\n"
                + "WHERE tcommandes.article = tarticles.id AND tarticles.categorie = tcategorie.id \n"
                + "AND tcommandes.ligne = tlignecommande.id AND tlignecommande.societe = ?1 \n"
                + "AND tlignecommande.datelivraison >= ?2 AND tlignecommande.datelivraison <= ?3 \n"
                + "AND tlignecommande.etatc = tetatc.id AND tetatc.code = ?4 \n"
                + "GROUP BY 3 ORDER BY pt ASC LIMIT ?5;";
        Query q = getEntityManager().createNativeQuery(sql);
        q.setParameter("1", societe);
        q.setParameter("4", etat);
        q.setParameter("5", max);
        q.setParameter("2", d);
        q.setParameter("3", d1);
        List<Object[]> rs = (List<Object[]>) q.getResultList();
        for (Object[] r : rs) {
            statistique b = new statistique();
            b.setMontant(Double.parseDouble(r[1].toString()));
            b.setQt(Double.parseDouble(r[0].toString()));
            b.setNom(r[2].toString());
            l1.add(b);
        }
        return l1;
    }

    //top Articles regionale 
    @Override
    public List<statistique> findTTopArticlesRegion(Date d, Date d1, int max, int societe, int etat, int reg) {
        getEntityManager().setProperty("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        List<statistique> l1 = new ArrayList<>();
        String sql = "SELECT SUM(tcommandes.quantite) AS qt, SUM(tcommandes.prix_total) AS pt, tarticles.nom, tarticles.code \n"
                + "               FROM tcommandes, tarticles, tlignecommande, tetatc, tclients, tsecteurs, tdistricts \n"
                + "               WHERE tcommandes.article = tarticles.id AND tcommandes.ligne = tlignecommande.id AND tlignecommande.client = tclients.id \n"
                + "                 AND  tclients.secteurid = tsecteurs.id AND tsecteurs.id = tdistricts.id AND tdistricts.regionid = ?6 \n"
                + "                 AND tlignecommande.societe = ?1 AND tlignecommande.datelivraison >= ?2 \n"
                + "                AND tlignecommande.datelivraison <= ?3 AND tlignecommande.etatc = tetatc.id AND tetatc.code = ?4 \n"
                + "               GROUP BY 3,4 ORDER BY pt DESC LIMIT ?5;";
        Query q = getEntityManager().createNativeQuery(sql);
        q.setParameter("1", societe);
        q.setParameter("4", etat);
        q.setParameter("5", max);
        q.setParameter("2", d);
        q.setParameter("3", d1);
        q.setParameter("6", reg);
        List<Object[]> rs = (List<Object[]>) q.getResultList();
        for (Object[] r : rs) {
            statistique b = new statistique();
            b.setMontant(Double.parseDouble(r[1].toString()));
            b.setQt(Double.parseDouble(r[0].toString()));
            b.setNom(r[2].toString());
            b.setCode(r[3].toString());
            l1.add(b);
        }
        return l1;
    }

    @Override
    public List<statistique> findTTopArticleMoinVenduRegional(Date d, Date d1, int max, int societe, int etat, int reg) {
        getEntityManager().setProperty("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        List<statistique> l1 = new ArrayList<>();
        String sql = "SELECT SUM(tcommandes.quantite) AS qt, SUM(tcommandes.prix_total) AS pt, tarticles.nom, tarticles.code \n"
                + "               FROM tcommandes, tarticles, tlignecommande, tetatc, tclients, tsecteurs, tdistricts \n"
                + "               WHERE tcommandes.article = tarticles.id AND tcommandes.ligne = tlignecommande.id AND tlignecommande.client = tclients.id \n"
                + "                 AND  tclients.secteurid = tsecteurs.id AND tsecteurs.id = tdistricts.id AND tdistricts.regionid = ?6 \n"
                + "                 AND tlignecommande.societe = ?1 AND tlignecommande.datelivraison >= ?2 \n"
                + "                AND tlignecommande.datelivraison <= ?3 AND tlignecommande.etatc = tetatc.id AND tetatc.code = ?4 \n"
                + "               GROUP BY 3,4 ORDER BY pt ASC LIMIT ?5;";
        Query q = getEntityManager().createNativeQuery(sql);
        q.setParameter("1", societe);
        q.setParameter("4", etat);
        q.setParameter("5", max);
        q.setParameter("2", d);
        q.setParameter("3", d1);
        q.setParameter("6", reg);
        List<Object[]> rs = (List<Object[]>) q.getResultList();
        for (Object[] r : rs) {
            statistique b = new statistique();
            b.setMontant(Double.parseDouble(r[1].toString()));
            b.setQt(Double.parseDouble(r[0].toString()));
            b.setNom(r[2].toString());
            b.setCode(r[3].toString());
            l1.add(b);
        }
        return l1;
    }

    @Override
    public List<statistique> findTTopCategorieRegional(Date d, Date d1, int max, int societe, int etat, int reg) {
        getEntityManager().setProperty("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        List<statistique> l1 = new ArrayList<>();
        String sql = "SELECT SUM(tcommandes.quantite) AS qt, SUM(tcommandes.prix_total) AS pt, tcategorie.nom \n"
                + "               FROM tcommandes, tarticles, tlignecommande, tetatc, tclients, tsecteurs, tdistricts, tcategorie \n"
                + "               WHERE tcommandes.article = tarticles.id AND tcommandes.ligne = tlignecommande.id \n"
                + "                 AND tlignecommande.client = tclients.id AND tarticles.categorie = tcategorie.id\n"
                + "                 AND  tclients.secteurid = tsecteurs.id AND tsecteurs.id = tdistricts.id AND tdistricts.regionid = ?6 \n"
                + "                 AND tlignecommande.societe = ?1 AND tlignecommande.datelivraison >= ?2 \n"
                + "                AND tlignecommande.datelivraison <= ?3 AND tlignecommande.etatc = tetatc.id AND tetatc.code = ?4\n"
                + "               GROUP BY 3 ORDER BY pt DESC LIMIT ?5;";
        Query q = getEntityManager().createNativeQuery(sql);
        q.setParameter("1", societe);
        q.setParameter("4", etat);
        q.setParameter("5", max);
        q.setParameter("2", d);
        q.setParameter("3", d1);
        q.setParameter("6", reg);
        List<Object[]> rs = (List<Object[]>) q.getResultList();
        for (Object[] r : rs) {
            statistique b = new statistique();
            b.setMontant(Double.parseDouble(r[1].toString()));
            b.setQt(Double.parseDouble(r[0].toString()));
            b.setNom(r[2].toString());
            l1.add(b);
        }
        return l1;
    }

    @Override
    public void SortCommande(Tlignecommande lg) {
        lg.getTcommandesList().sort(Comparator.comparingDouble(Tcommandes::getQuantite).reversed());
    }
}