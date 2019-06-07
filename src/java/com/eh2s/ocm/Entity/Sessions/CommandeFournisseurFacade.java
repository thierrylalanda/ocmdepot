/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.CommandeFournisseur;
import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.LigneCommandeFournisseur;
import com.eh2s.ocm.Entity.Tarticles;
import com.eh2s.ocm.Entity.Tcommandes;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.CacheRetrieveMode;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Administrateur
 */
@Stateless
public class CommandeFournisseurFacade extends AbstractFacade<CommandeFournisseur> implements CommandeFournisseurFacadeLocal {

    @PersistenceContext(unitName = "ccmanagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CommandeFournisseurFacade() {
        super(CommandeFournisseur.class);
    }


    @Override
    public CommandeFournisseur creer(CommandeFournisseur commandeFournisseur) throws RollbackFailureException, Exception {
        try {
            LigneCommandeFournisseur ligneCommandeFournisseur = commandeFournisseur.getLigneCommandeFournisseur();
            if (ligneCommandeFournisseur != null) {
                ligneCommandeFournisseur = getEntityManager().getReference(ligneCommandeFournisseur.getClass(), ligneCommandeFournisseur.getId());
                commandeFournisseur.setLigneCommandeFournisseur(ligneCommandeFournisseur);
            }
            Tarticles article = commandeFournisseur.getArticle();
            if (article != null) {
                article = getEntityManager().getReference(article.getClass(), article.getId());
                commandeFournisseur.setArticle(article);
            }
            getEntityManager().persist(commandeFournisseur);
            if (ligneCommandeFournisseur != null) {
                ligneCommandeFournisseur.getCommandeFournisseurList().add(commandeFournisseur);
                ligneCommandeFournisseur = getEntityManager().merge(ligneCommandeFournisseur);
            }
            if (article != null) {
                article.getCommandeFournisseurList().add(commandeFournisseur);
                article = getEntityManager().merge(article);
            }
        } catch (Exception ex) {
               throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);
            
        }  
        return commandeFournisseur;
    }

    @Override
    public CommandeFournisseur MisAjour(CommandeFournisseur commandeFournisseur) throws NonexistentEntityException, RollbackFailureException, Exception {
    try {
            CommandeFournisseur persistentCommandeFournisseur = getEntityManager().find(CommandeFournisseur.class, commandeFournisseur.getId());
            LigneCommandeFournisseur ligneCommandeFournisseurOld = persistentCommandeFournisseur.getLigneCommandeFournisseur();
            LigneCommandeFournisseur ligneCommandeFournisseurNew = commandeFournisseur.getLigneCommandeFournisseur();
            Tarticles articleOld = persistentCommandeFournisseur.getArticle();
            Tarticles articleNew = commandeFournisseur.getArticle();
            if (ligneCommandeFournisseurNew != null) {
                ligneCommandeFournisseurNew = getEntityManager().getReference(ligneCommandeFournisseurNew.getClass(), ligneCommandeFournisseurNew.getId());
                commandeFournisseur.setLigneCommandeFournisseur(ligneCommandeFournisseurNew);
            }
            if (articleNew != null) {
                articleNew = getEntityManager().getReference(articleNew.getClass(), articleNew.getId());
                commandeFournisseur.setArticle(articleNew);
            }
            commandeFournisseur = getEntityManager().merge(commandeFournisseur);
            if (ligneCommandeFournisseurOld != null && !ligneCommandeFournisseurOld.equals(ligneCommandeFournisseurNew)) {
                ligneCommandeFournisseurOld.getCommandeFournisseurList().remove(commandeFournisseur);
                ligneCommandeFournisseurOld = getEntityManager().merge(ligneCommandeFournisseurOld);
            }
            if (ligneCommandeFournisseurNew != null && !ligneCommandeFournisseurNew.equals(ligneCommandeFournisseurOld)) {
                ligneCommandeFournisseurNew.getCommandeFournisseurList().add(commandeFournisseur);
                ligneCommandeFournisseurNew = getEntityManager().merge(ligneCommandeFournisseurNew);
            }
            if (articleOld != null && !articleOld.equals(articleNew)) {
                articleOld.getCommandeFournisseurList().remove(commandeFournisseur);
                articleOld = getEntityManager().merge(articleOld);
            }
            if (articleNew != null && !articleNew.equals(articleOld)) {
                articleNew.getCommandeFournisseurList().add(commandeFournisseur);
                articleNew = getEntityManager().merge(articleNew);
            }
        } catch (Exception ex) {
              throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);
          
        } 
    return commandeFournisseur;
    }

    @Override
    public void Supprimer(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
      try {
            CommandeFournisseur commandeFournisseur;
            try {
                commandeFournisseur = getEntityManager().getReference(CommandeFournisseur.class, id);
                commandeFournisseur.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The commandeFournisseur with id " + id + " no longer exists.", enfe);
            }
            LigneCommandeFournisseur ligneCommandeFournisseur = commandeFournisseur.getLigneCommandeFournisseur();
            if (ligneCommandeFournisseur != null) {
                ligneCommandeFournisseur.getCommandeFournisseurList().remove(commandeFournisseur);
                ligneCommandeFournisseur = getEntityManager().merge(ligneCommandeFournisseur);
            }
            Tarticles article = commandeFournisseur.getArticle();
            if (article != null) {
                article.getCommandeFournisseurList().remove(commandeFournisseur);
                article = getEntityManager().merge(article);
            }
            getEntityManager().remove(commandeFournisseur);
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);
           
        } 
    }
    
     public CommandeFournisseur findTcommandes(Integer id) {
        getEntityManager().setProperty("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        return getEntityManager().find(CommandeFournisseur.class, id);
    }

    @Override
    public List<CommandeFournisseur> findTcommandesByPeriodeBySociete(Date d, Date d1, int societe, int etat) {
     String sql = "SELECT SUM(commande_fournisseur.quantite),SUM(commande_fournisseur.quantite_recu),SUM(commande_fournisseur.prix_total), tarticles.nom, tarticles.code, tarticles.id, tetatc.code FROM commande_fournisseur, ligne_commande_fournisseur, tetatc, fournisseur , tarticles\n"
                + "WHERE commande_fournisseur.article = tarticles.id AND ligne_commande_fournisseur.etat = tetatc.id \n"
                + "AND commande_fournisseur.ligne_commande_fournisseur = ligne_commande_fournisseur.id  AND\n"
                + "ligne_commande_fournisseur.fournisseur = fournisseur.id AND fournisseur.societe = ?1 AND ligne_commande_fournisseur.date_livraison >= ?2 \n"
                + "AND ligne_commande_fournisseur.date_livraison <= ?3 AND tetatc.code = ?4 GROUP BY 4,5,6,7;";
        Query q = getEntityManager().createNativeQuery(sql);
        q.setParameter("1", societe);
        q.setParameter("2", d);
        q.setParameter("3", d1);
        q.setParameter("4", etat);

        List<Object[]> rs = (List<Object[]>) q.getResultList();
        List<CommandeFournisseur> commandes = new ArrayList<>();
        for (Object[] r : rs) {
            Tarticles ar =new Tarticles();
            if (new Double(r[0].toString()) != 0.0) {
                ar.setNom((String) r[3]);
                ar.setCode((String) r[4]);
                ar.setId((Integer) r[5]);
                ar.setStock(new Double(r[0].toString()));
            } else {
                ar.setNom((String) r[3]);
                ar.setCode((String) r[4]);
                ar.setId((Integer) r[5]);
                ar.setStock(0.0);
            }
            CommandeFournisseur cmd=new CommandeFournisseur();
            cmd.setArticle(ar);
            cmd.setQuantite(new Double(r[0].toString()));
            cmd.setQuantiteRecu(new Double(r[1].toString()));
            cmd.setPrixTotal((double) r[2]);
            commandes.add(cmd);
        }
        return commandes;
    }

    @Override
    public Set<CommandeFournisseur> findTcommandesByArticleByPeriode(Date d, Date d1, Integer article, int etat) {
    Set<CommandeFournisseur> ar = new HashSet<>();
        getEntityManager().setProperty("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        Query q = getEntityManager().createNamedQuery("CommandeFournisseur.findForArticlePeriode");
        q.setParameter("article", article);
        q.setParameter("d", d);
        q.setParameter("d2", d1);
        q.setParameter("etat", etat);
        List<CommandeFournisseur> l = q.getResultList();
        for (CommandeFournisseur cc : l) {
            ar.add(cc);
        }
        return ar;
    }

    @Override
    public List<CommandeFournisseur> findAllByLigne(int ligneCommande) {
    List<CommandeFournisseur> ar = new ArrayList<>();
        getEntityManager().setProperty("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        Query q = getEntityManager().createNamedQuery("CommandeFournisseur.findByLigneCommandeFournisseur");
        q.setParameter("ligne", ligneCommande);
       
        return q.getResultList();
    }
    
}
