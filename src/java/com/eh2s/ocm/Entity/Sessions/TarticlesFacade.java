/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.CommandeFournisseur;
import com.eh2s.ocm.Entity.Emballage;
import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Fournisseur;
import com.eh2s.ocm.Entity.Inventaire;
import com.eh2s.ocm.Entity.StockMg;
import com.eh2s.ocm.Entity.Tarticles;
import com.eh2s.ocm.Entity.Tcategorie;
import com.eh2s.ocm.Entity.Tcommandes;
import com.eh2s.ocm.Entity.TransfertStock;
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
public class TarticlesFacade extends AbstractFacade<Tarticles> implements TarticlesFacadeLocal {

    @PersistenceContext(unitName = "ccmanagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TarticlesFacade() {
        super(Tarticles.class);
    }

    @Override
    public Tarticles creer(Tarticles tarticles) throws RollbackFailureException, Exception {
        if (tarticles.getCommandeFournisseurList() == null) {
            tarticles.setCommandeFournisseurList(new ArrayList<CommandeFournisseur>());
        }
        if (tarticles.getTransfertStockList() == null) {
            tarticles.setTransfertStockList(new ArrayList<TransfertStock>());
        }
        if (tarticles.getTcommandesList() == null) {
            tarticles.setTcommandesList(new ArrayList<Tcommandes>());
        }
        if (tarticles.getInventaireList() == null) {
            tarticles.setInventaireList(new ArrayList<Inventaire>());
        }
        if (tarticles.getStockMgList() == null) {
            tarticles.setStockMgList(new ArrayList<StockMg>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            Tcategorie categorie = tarticles.getCategorie();
            if (categorie != null) {
                categorie = em.getReference(categorie.getClass(), categorie.getId());
                tarticles.setCategorie(categorie);
            }
            Fournisseur fournisseur = tarticles.getFournisseur();
            if (fournisseur != null) {
                fournisseur = em.getReference(fournisseur.getClass(), fournisseur.getId());
                tarticles.setFournisseur(fournisseur);
            }
            Emballage emballage = tarticles.getEmballage();
            if (emballage != null) {
                emballage = em.getReference(emballage.getClass(), emballage.getId());
                tarticles.setEmballage(emballage);
            }
            List<CommandeFournisseur> attachedCommandeFournisseurList = new ArrayList<CommandeFournisseur>();
            for (CommandeFournisseur commandeFournisseurListCommandeFournisseurToAttach : tarticles.getCommandeFournisseurList()) {
                commandeFournisseurListCommandeFournisseurToAttach = em.getReference(commandeFournisseurListCommandeFournisseurToAttach.getClass(), commandeFournisseurListCommandeFournisseurToAttach.getId());
                attachedCommandeFournisseurList.add(commandeFournisseurListCommandeFournisseurToAttach);
            }
            tarticles.setCommandeFournisseurList(attachedCommandeFournisseurList);
            List<TransfertStock> attachedTransfertStockList = new ArrayList<TransfertStock>();
            for (TransfertStock transfertStockListTransfertStockToAttach : tarticles.getTransfertStockList()) {
                transfertStockListTransfertStockToAttach = em.getReference(transfertStockListTransfertStockToAttach.getClass(), transfertStockListTransfertStockToAttach.getId());
                attachedTransfertStockList.add(transfertStockListTransfertStockToAttach);
            }
            tarticles.setTransfertStockList(attachedTransfertStockList);
            List<Tcommandes> attachedTcommandesList = new ArrayList<Tcommandes>();
            for (Tcommandes tcommandesListTcommandesToAttach : tarticles.getTcommandesList()) {
                tcommandesListTcommandesToAttach = em.getReference(tcommandesListTcommandesToAttach.getClass(), tcommandesListTcommandesToAttach.getId());
                attachedTcommandesList.add(tcommandesListTcommandesToAttach);
            }
            tarticles.setTcommandesList(attachedTcommandesList);
            List<Inventaire> attachedInventaireList = new ArrayList<Inventaire>();
            for (Inventaire inventaireListInventaireToAttach : tarticles.getInventaireList()) {
                inventaireListInventaireToAttach = em.getReference(inventaireListInventaireToAttach.getClass(), inventaireListInventaireToAttach.getId());
                attachedInventaireList.add(inventaireListInventaireToAttach);
            }
            tarticles.setInventaireList(attachedInventaireList);
            List<StockMg> attachedStockMgList = new ArrayList<StockMg>();
            for (StockMg stockMgListStockMgToAttach : tarticles.getStockMgList()) {
                stockMgListStockMgToAttach = em.getReference(stockMgListStockMgToAttach.getClass(), stockMgListStockMgToAttach.getId());
                attachedStockMgList.add(stockMgListStockMgToAttach);
            }
            tarticles.setStockMgList(attachedStockMgList);
            em.persist(tarticles);
            if (categorie != null) {
                categorie.getTarticlesList().add(tarticles);
                categorie = em.merge(categorie);
            }
            if (fournisseur != null) {
                fournisseur.getTarticlesList().add(tarticles);
                fournisseur = em.merge(fournisseur);
            }
            if (emballage != null) {
                emballage.getTarticlesList().add(tarticles);
                emballage = em.merge(emballage);
            }
            for (CommandeFournisseur commandeFournisseurListCommandeFournisseur : tarticles.getCommandeFournisseurList()) {
                Tarticles oldArticleOfCommandeFournisseurListCommandeFournisseur = commandeFournisseurListCommandeFournisseur.getArticle();
                commandeFournisseurListCommandeFournisseur.setArticle(tarticles);
                commandeFournisseurListCommandeFournisseur = em.merge(commandeFournisseurListCommandeFournisseur);
                if (oldArticleOfCommandeFournisseurListCommandeFournisseur != null) {
                    oldArticleOfCommandeFournisseurListCommandeFournisseur.getCommandeFournisseurList().remove(commandeFournisseurListCommandeFournisseur);
                    oldArticleOfCommandeFournisseurListCommandeFournisseur = em.merge(oldArticleOfCommandeFournisseurListCommandeFournisseur);
                }
            }
            for (TransfertStock transfertStockListTransfertStock : tarticles.getTransfertStockList()) {
                Tarticles oldArticleOfTransfertStockListTransfertStock = transfertStockListTransfertStock.getArticle();
                transfertStockListTransfertStock.setArticle(tarticles);
                transfertStockListTransfertStock = em.merge(transfertStockListTransfertStock);
                if (oldArticleOfTransfertStockListTransfertStock != null) {
                    oldArticleOfTransfertStockListTransfertStock.getTransfertStockList().remove(transfertStockListTransfertStock);
                    oldArticleOfTransfertStockListTransfertStock = em.merge(oldArticleOfTransfertStockListTransfertStock);
                }
            }
            for (Tcommandes tcommandesListTcommandes : tarticles.getTcommandesList()) {
                Tarticles oldArticleOfTcommandesListTcommandes = tcommandesListTcommandes.getArticle();
                tcommandesListTcommandes.setArticle(tarticles);
                tcommandesListTcommandes = em.merge(tcommandesListTcommandes);
                if (oldArticleOfTcommandesListTcommandes != null) {
                    oldArticleOfTcommandesListTcommandes.getTcommandesList().remove(tcommandesListTcommandes);
                    oldArticleOfTcommandesListTcommandes = em.merge(oldArticleOfTcommandesListTcommandes);
                }
            }
            for (Inventaire inventaireListInventaire : tarticles.getInventaireList()) {
                Tarticles oldArticleOfInventaireListInventaire = inventaireListInventaire.getArticle();
                inventaireListInventaire.setArticle(tarticles);
                inventaireListInventaire = em.merge(inventaireListInventaire);
                if (oldArticleOfInventaireListInventaire != null) {
                    oldArticleOfInventaireListInventaire.getInventaireList().remove(inventaireListInventaire);
                    oldArticleOfInventaireListInventaire = em.merge(oldArticleOfInventaireListInventaire);
                }
            }
            for (StockMg stockMgListStockMg : tarticles.getStockMgList()) {
                Tarticles oldArticleOfStockMgListStockMg = stockMgListStockMg.getArticle();
                stockMgListStockMg.setArticle(tarticles);
                stockMgListStockMg = em.merge(stockMgListStockMg);
                if (oldArticleOfStockMgListStockMg != null) {
                    oldArticleOfStockMgListStockMg.getStockMgList().remove(stockMgListStockMg);
                    oldArticleOfStockMgListStockMg = em.merge(oldArticleOfStockMgListStockMg);
                }
            }
            return tarticles;
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @Override
    public Tarticles MiSaJour(Tarticles tarticles) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            Tarticles persistentTarticles = em.find(Tarticles.class, tarticles.getId());
            Tcategorie categorieOld = persistentTarticles.getCategorie();
            Tcategorie categorieNew = tarticles.getCategorie();
            Fournisseur fournisseurOld = persistentTarticles.getFournisseur();
            Fournisseur fournisseurNew = tarticles.getFournisseur();
            Emballage emballageOld = persistentTarticles.getEmballage();
            Emballage emballageNew = tarticles.getEmballage();
            List<CommandeFournisseur> commandeFournisseurListOld = persistentTarticles.getCommandeFournisseurList();
            List<CommandeFournisseur> commandeFournisseurListNew = tarticles.getCommandeFournisseurList();
            List<TransfertStock> transfertStockListOld = persistentTarticles.getTransfertStockList();
            List<TransfertStock> transfertStockListNew = tarticles.getTransfertStockList();
            List<Tcommandes> tcommandesListOld = persistentTarticles.getTcommandesList();
            List<Tcommandes> tcommandesListNew = tarticles.getTcommandesList();
            List<Inventaire> inventaireListOld = persistentTarticles.getInventaireList();
            List<Inventaire> inventaireListNew = tarticles.getInventaireList();
            List<StockMg> stockMgListOld = persistentTarticles.getStockMgList();
            List<StockMg> stockMgListNew = tarticles.getStockMgList();

            if (categorieNew != null) {
                categorieNew = em.getReference(categorieNew.getClass(), categorieNew.getId());
                tarticles.setCategorie(categorieNew);
            }
            if (fournisseurNew != null) {
                fournisseurNew = em.getReference(fournisseurNew.getClass(), fournisseurNew.getId());
                tarticles.setFournisseur(fournisseurNew);
            }
            if (emballageNew != null) {
                emballageNew = em.getReference(emballageNew.getClass(), emballageNew.getId());
                tarticles.setEmballage(emballageNew);
            }
            List<CommandeFournisseur> attachedCommandeFournisseurListNew = new ArrayList<CommandeFournisseur>();
            for (CommandeFournisseur commandeFournisseurListNewCommandeFournisseurToAttach : commandeFournisseurListNew) {
                commandeFournisseurListNewCommandeFournisseurToAttach = em.getReference(commandeFournisseurListNewCommandeFournisseurToAttach.getClass(), commandeFournisseurListNewCommandeFournisseurToAttach.getId());
                attachedCommandeFournisseurListNew.add(commandeFournisseurListNewCommandeFournisseurToAttach);
            }
            commandeFournisseurListNew = attachedCommandeFournisseurListNew;
            tarticles.setCommandeFournisseurList(commandeFournisseurListNew);
            List<TransfertStock> attachedTransfertStockListNew = new ArrayList<TransfertStock>();
            for (TransfertStock transfertStockListNewTransfertStockToAttach : transfertStockListNew) {
                transfertStockListNewTransfertStockToAttach = em.getReference(transfertStockListNewTransfertStockToAttach.getClass(), transfertStockListNewTransfertStockToAttach.getId());
                attachedTransfertStockListNew.add(transfertStockListNewTransfertStockToAttach);
            }
            transfertStockListNew = attachedTransfertStockListNew;
            tarticles.setTransfertStockList(transfertStockListNew);
            List<Tcommandes> attachedTcommandesListNew = new ArrayList<Tcommandes>();
            for (Tcommandes tcommandesListNewTcommandesToAttach : tcommandesListNew) {
                tcommandesListNewTcommandesToAttach = em.getReference(tcommandesListNewTcommandesToAttach.getClass(), tcommandesListNewTcommandesToAttach.getId());
                attachedTcommandesListNew.add(tcommandesListNewTcommandesToAttach);
            }
            tcommandesListNew = attachedTcommandesListNew;
            tarticles.setTcommandesList(tcommandesListNew);
            List<Inventaire> attachedInventaireListNew = new ArrayList<Inventaire>();
            for (Inventaire inventaireListNewInventaireToAttach : inventaireListNew) {
                inventaireListNewInventaireToAttach = em.getReference(inventaireListNewInventaireToAttach.getClass(), inventaireListNewInventaireToAttach.getId());
                attachedInventaireListNew.add(inventaireListNewInventaireToAttach);
            }
            inventaireListNew = attachedInventaireListNew;
            tarticles.setInventaireList(inventaireListNew);
            List<StockMg> attachedStockMgListNew = new ArrayList<StockMg>();
            for (StockMg stockMgListNewStockMgToAttach : stockMgListNew) {
                stockMgListNewStockMgToAttach = em.getReference(stockMgListNewStockMgToAttach.getClass(), stockMgListNewStockMgToAttach.getId());
                attachedStockMgListNew.add(stockMgListNewStockMgToAttach);
            }
            stockMgListNew = attachedStockMgListNew;
            tarticles.setStockMgList(stockMgListNew);
            tarticles = em.merge(tarticles);
            if (categorieOld != null && !categorieOld.equals(categorieNew)) {
                categorieOld.getTarticlesList().remove(tarticles);
                categorieOld = em.merge(categorieOld);
            }
            if (categorieNew != null && !categorieNew.equals(categorieOld)) {
                categorieNew.getTarticlesList().add(tarticles);
                categorieNew = em.merge(categorieNew);
            }
            if (fournisseurOld != null && !fournisseurOld.equals(fournisseurNew)) {
                fournisseurOld.getTarticlesList().remove(tarticles);
                fournisseurOld = em.merge(fournisseurOld);
            }
            if (fournisseurNew != null && !fournisseurNew.equals(fournisseurOld)) {
                fournisseurNew.getTarticlesList().add(tarticles);
                fournisseurNew = em.merge(fournisseurNew);
            }
            if (emballageOld != null && !emballageOld.equals(emballageNew)) {
                emballageOld.getTarticlesList().remove(tarticles);
                emballageOld = em.merge(emballageOld);
            }
            if (emballageNew != null && !emballageNew.equals(emballageOld)) {
                emballageNew.getTarticlesList().add(tarticles);
                emballageNew = em.merge(emballageNew);
            }
            for (CommandeFournisseur commandeFournisseurListNewCommandeFournisseur : commandeFournisseurListNew) {
                if (!commandeFournisseurListOld.contains(commandeFournisseurListNewCommandeFournisseur)) {
                    Tarticles oldArticleOfCommandeFournisseurListNewCommandeFournisseur = commandeFournisseurListNewCommandeFournisseur.getArticle();
                    commandeFournisseurListNewCommandeFournisseur.setArticle(tarticles);
                    commandeFournisseurListNewCommandeFournisseur = em.merge(commandeFournisseurListNewCommandeFournisseur);
                    if (oldArticleOfCommandeFournisseurListNewCommandeFournisseur != null && !oldArticleOfCommandeFournisseurListNewCommandeFournisseur.equals(tarticles)) {
                        oldArticleOfCommandeFournisseurListNewCommandeFournisseur.getCommandeFournisseurList().remove(commandeFournisseurListNewCommandeFournisseur);
                        oldArticleOfCommandeFournisseurListNewCommandeFournisseur = em.merge(oldArticleOfCommandeFournisseurListNewCommandeFournisseur);
                    }
                }
            }
            for (TransfertStock transfertStockListNewTransfertStock : transfertStockListNew) {
                if (!transfertStockListOld.contains(transfertStockListNewTransfertStock)) {
                    Tarticles oldArticleOfTransfertStockListNewTransfertStock = transfertStockListNewTransfertStock.getArticle();
                    transfertStockListNewTransfertStock.setArticle(tarticles);
                    transfertStockListNewTransfertStock = em.merge(transfertStockListNewTransfertStock);
                    if (oldArticleOfTransfertStockListNewTransfertStock != null && !oldArticleOfTransfertStockListNewTransfertStock.equals(tarticles)) {
                        oldArticleOfTransfertStockListNewTransfertStock.getTransfertStockList().remove(transfertStockListNewTransfertStock);
                        oldArticleOfTransfertStockListNewTransfertStock = em.merge(oldArticleOfTransfertStockListNewTransfertStock);
                    }
                }
            }
            for (Tcommandes tcommandesListOldTcommandes : tcommandesListOld) {
                if (!tcommandesListNew.contains(tcommandesListOldTcommandes)) {
                    tcommandesListOldTcommandes.setArticle(null);
                    tcommandesListOldTcommandes = em.merge(tcommandesListOldTcommandes);
                }
            }
            for (Tcommandes tcommandesListNewTcommandes : tcommandesListNew) {
                if (!tcommandesListOld.contains(tcommandesListNewTcommandes)) {
                    Tarticles oldArticleOfTcommandesListNewTcommandes = tcommandesListNewTcommandes.getArticle();
                    tcommandesListNewTcommandes.setArticle(tarticles);
                    tcommandesListNewTcommandes = em.merge(tcommandesListNewTcommandes);
                    if (oldArticleOfTcommandesListNewTcommandes != null && !oldArticleOfTcommandesListNewTcommandes.equals(tarticles)) {
                        oldArticleOfTcommandesListNewTcommandes.getTcommandesList().remove(tcommandesListNewTcommandes);
                        oldArticleOfTcommandesListNewTcommandes = em.merge(oldArticleOfTcommandesListNewTcommandes);
                    }
                }
            }
            for (Inventaire inventaireListOldInventaire : inventaireListOld) {
                if (!inventaireListNew.contains(inventaireListOldInventaire)) {
                    inventaireListOldInventaire.setArticle(null);
                    inventaireListOldInventaire = em.merge(inventaireListOldInventaire);
                }
            }
            for (Inventaire inventaireListNewInventaire : inventaireListNew) {
                if (!inventaireListOld.contains(inventaireListNewInventaire)) {
                    Tarticles oldArticleOfInventaireListNewInventaire = inventaireListNewInventaire.getArticle();
                    inventaireListNewInventaire.setArticle(tarticles);
                    inventaireListNewInventaire = em.merge(inventaireListNewInventaire);
                    if (oldArticleOfInventaireListNewInventaire != null && !oldArticleOfInventaireListNewInventaire.equals(tarticles)) {
                        oldArticleOfInventaireListNewInventaire.getInventaireList().remove(inventaireListNewInventaire);
                        oldArticleOfInventaireListNewInventaire = em.merge(oldArticleOfInventaireListNewInventaire);
                    }
                }
            }
            for (StockMg stockMgListNewStockMg : stockMgListNew) {
                if (!stockMgListOld.contains(stockMgListNewStockMg)) {
                    Tarticles oldArticleOfStockMgListNewStockMg = stockMgListNewStockMg.getArticle();
                    stockMgListNewStockMg.setArticle(tarticles);
                    stockMgListNewStockMg = em.merge(stockMgListNewStockMg);
                    if (oldArticleOfStockMgListNewStockMg != null && !oldArticleOfStockMgListNewStockMg.equals(tarticles)) {
                        oldArticleOfStockMgListNewStockMg.getStockMgList().remove(stockMgListNewStockMg);
                        oldArticleOfStockMgListNewStockMg = em.merge(oldArticleOfStockMgListNewStockMg);
                    }
                }
            }
            return tarticles;
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @Override
    public void Supprimer(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            Tarticles tarticles;
            try {
                tarticles = em.getReference(Tarticles.class, id);
                tarticles.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tarticles with id " + id + " no longer exists.", enfe);
            }

            Tcategorie categorie = tarticles.getCategorie();
            if (categorie != null) {
                categorie.getTarticlesList().remove(tarticles);
                categorie = em.merge(categorie);
            }
            Fournisseur fournisseur = tarticles.getFournisseur();
            if (fournisseur != null) {
                fournisseur.getTarticlesList().remove(tarticles);
                fournisseur = em.merge(fournisseur);
            }
            Emballage emballage = tarticles.getEmballage();
            if (emballage != null) {
                emballage.getTarticlesList().remove(tarticles);
                emballage = em.merge(emballage);
            }
            List<Tcommandes> tcommandesList = tarticles.getTcommandesList();
            for (Tcommandes tcommandesListTcommandes : tcommandesList) {
                tcommandesListTcommandes.setArticle(null);
                tcommandesListTcommandes = em.merge(tcommandesListTcommandes);
            }
            List<Inventaire> inventaireList = tarticles.getInventaireList();
            for (Inventaire inventaireListInventaire : inventaireList) {
                inventaireListInventaire.setArticle(null);
                inventaireListInventaire = em.merge(inventaireListInventaire);
            }
            em.remove(tarticles);
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @Override
    public List<Tarticles> findTarticlesEntities() {
        return findTarticlesEntities(true, -1, -1);
    }

    public List<Tarticles> findTarticlesEntities(int maxResults, int firstResult) {
        return findTarticlesEntities(false, maxResults, firstResult);
    }

    private List<Tarticles> findTarticlesEntities(boolean all, int maxResults, int firstResult) {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Tarticles.class));
        Query q = getEntityManager().createQuery(cq);
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    public Tarticles findTarticles(Integer id) {
        return getEntityManager().find(Tarticles.class, id);
    }

    public int getTarticlesCount() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<Tarticles> rt = cq.from(Tarticles.class);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    @Override
    public List<Tarticles> findAll(String imma) {
        Query q = getEntityManager().createNamedQuery("Tarticles.findAll");
        q.setParameter("imma", imma);
        return q.getResultList();
    }

    @Override
    public List<Tarticles> findAllByCategorie(int cat) {
        Query q = getEntityManager().createNamedQuery("Tarticles.findALLByCat");
        q.setParameter("cat", cat);
        return q.getResultList();
    }

    @Override
    public List<Tarticles> findAllBySociete(int societe) {
        Query q = getEntityManager().createNamedQuery("Tarticles.findAllBySociete");
        q.setParameter("id", societe);
        return q.getResultList();
    }

}
