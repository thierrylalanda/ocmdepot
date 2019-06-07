/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Tarticles;
import com.eh2s.ocm.Entity.Tcommandes;
import com.eh2s.ocm.Entity.Tetatc;
import com.eh2s.ocm.Entity.Tlignecommande;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
 * @author messi
 */
@Stateless
public class TcommandesFacade extends AbstractFacade<Tcommandes> implements TcommandesFacadeLocal {

    @PersistenceContext(unitName = "ccmanagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TcommandesFacade() {
        super(Tcommandes.class);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public Tcommandes creer(Tcommandes tcommandes) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            Tlignecommande ligne = tcommandes.getLigne();
            if (ligne != null) {
                ligne = em.getReference(ligne.getClass(), ligne.getId());
                tcommandes.setLigne(ligne);
            }
            Tarticles article = tcommandes.getArticle();
            if (article != null) {
                article = em.getReference(article.getClass(), article.getId());
                tcommandes.setArticle(article);
            }
            em.persist(tcommandes);
            if (ligne != null) {
                ligne.getTcommandesList().add(tcommandes);
                ligne = em.merge(ligne);
            }
            if (article != null) {
                article.getTcommandesList().add(tcommandes);
                article = em.merge(article);
            }
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
        return tcommandes;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public Tcommandes MisAJour(Tcommandes tcommandes) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            Tcommandes persistentTcommandes = em.find(Tcommandes.class, tcommandes.getId());
            Tlignecommande ligneOld = persistentTcommandes.getLigne();
            Tlignecommande ligneNew = tcommandes.getLigne();
            Tarticles articleOld = persistentTcommandes.getArticle();
            Tarticles articleNew = tcommandes.getArticle();
            if (ligneNew != null) {
                ligneNew = em.getReference(ligneNew.getClass(), ligneNew.getId());
                tcommandes.setLigne(ligneNew);
            }
            if (articleNew != null) {
                articleNew = em.getReference(articleNew.getClass(), articleNew.getId());
                tcommandes.setArticle(articleNew);
            }
            tcommandes = em.merge(tcommandes);
            if (ligneOld != null && !ligneOld.equals(ligneNew)) {
                ligneOld.getTcommandesList().remove(tcommandes);
                ligneOld = em.merge(ligneOld);
            }
            if (ligneNew != null && !ligneNew.equals(ligneOld)) {
                ligneNew.getTcommandesList().add(tcommandes);
                ligneNew = em.merge(ligneNew);
            }
            if (articleOld != null && !articleOld.equals(articleNew)) {
                articleOld.getTcommandesList().remove(tcommandes);
                articleOld = em.merge(articleOld);
            }
            if (articleNew != null && !articleNew.equals(articleOld)) {
                articleNew.getTcommandesList().add(tcommandes);
                articleNew = em.merge(articleNew);
            }
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
        return tcommandes;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void Supprimer(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            Tcommandes tcommandes;
            try {
                tcommandes = em.getReference(Tcommandes.class, id);
                tcommandes.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tcommandes with id " + id + " no longer exists.", enfe);
            }
            Tlignecommande ligne = tcommandes.getLigne();
            if (ligne != null) {
                ligne.getTcommandesList().remove(tcommandes);
                ligne = em.merge(ligne);
            }
            Tarticles article = tcommandes.getArticle();
            if (article != null) {
                article.getTcommandesList().remove(tcommandes);
                article = em.merge(article);
            }
            em.remove(tcommandes);
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @Override
    public List<Tcommandes> findTcommandesEntities() {
        return findTcommandesEntities(true, -1, -1);
    }

    @Override
    public List<Tcommandes> findTcommandesEntities(int maxResults, int firstResult) {
        return findTcommandesEntities(false, maxResults, firstResult);
    }

    private List<Tcommandes> findTcommandesEntities(boolean all, int maxResults, int firstResult) {
        getEntityManager().setProperty("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Tcommandes.class));
        Query q = getEntityManager().createQuery(cq);
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    @Override
    public Tcommandes findTcommandes(Integer id) {
        getEntityManager().setProperty("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        return getEntityManager().find(Tcommandes.class, id);
    }

    @Override
    public int getTcommandesCount() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<Tcommandes> rt = cq.from(Tcommandes.class);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

//    @Override
//    public List<Tcommandes> findTcommandesByNumc(int numc) {
//        Query q = getEntityManager().createNamedQuery("Tcommandes.findByNumc");
//        q.setParameter("numc", numc);
//        return q.getResultList();
//    }
//    @Override
//    public List<Tcommandes> findTcommandesByPeriode(Date d, Date d1, int immatriculation) {
//        Query q = getEntityManager().createNamedQuery("Tcommandes.findByPeriode");
//        q.setParameter("d1", d);
//        q.setParameter("d2", d1);
//        q.setParameter("imma", immatriculation);
//        return q.getResultList();
//    }
//
    @Override
    public Set<Tarticles> findTcommandesArticleByPeriode(Date d, Date d1, int etat, int s) {
        Set<Tarticles> ar = new HashSet<>();
        getEntityManager().setProperty("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        String sql = "SELECT DISTINCT tarticles.id FROM tcommandes, tlignecommande, tetatc, tarticles\n"
                + "WHERE tcommandes.article = tarticles.id AND tlignecommande.etatc = tetatc.id \n"
                + "AND tcommandes.ligne = tlignecommande.id AND\n"
                + "tlignecommande.societe = ?1 AND tlignecommande.datelivraison >= ?2 \n"
                + "AND tlignecommande.datelivraison <= ?3 AND tetatc.code = ?4";
        Query q = getEntityManager().createNativeQuery(sql);
        q.setParameter("1", s);
        q.setParameter("2", d);
        q.setParameter("3", d1);
        q.setParameter("4", etat);
        List<Object> rs = (List<Object>) q.getResultList();
        for (Object r : rs) {
            if (new Integer(r.toString()) != 0.0) {
                Tarticles as = new Tarticles(new Integer(r.toString()));
                ar.add(as);
            }
        }
        return ar;
    }

    @Override
    public List<Tcommandes> findTcommandesByPeriodeByClientSUM(Date d, Date d1, int client, int etat) {
        getEntityManager().setProperty("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        List<Tcommandes> l1 = new ArrayList<>();
        String sql = "SELECT SUM(tcommandes.quantite), tarticles.nom, tarticles.code, tarticles.id, tetatc.code FROM tcommandes, tlignecommande, tetatc, tarticles\n"
                + "WHERE tcommandes.article = tarticles.id AND tlignecommande.etatc = tetatc.id \n"
                + "AND tcommandes.ligne = tlignecommande.id AND\n"
                + "tlignecommande.client = ?1 AND tlignecommande.datelivraison >= ?2 \n"
                + "AND tlignecommande.datelivraison <= ?3 AND tetatc.code = ?4 GROUP BY 2,3,4,5;";
        Query q = getEntityManager().createNativeQuery(sql);
        q.setParameter("1", client);
        q.setParameter("2", d);
        q.setParameter("3", d1);
        q.setParameter("4", etat);
        int i = 0;
        List<Object[]> rs = (List<Object[]>) q.getResultList();
        for (Object[] r : rs) {
            if (new Double(r[0].toString()) != 0.0) {
                Tcommandes b = new Tcommandes();
                Tarticles ar = new Tarticles();
                Tetatc t = new Tetatc();
                b.setQuantite(new Double(r[0].toString()));
                ar.setNom((String) r[1]);
                ar.setCode((String) r[2]);
                ar.setId((Integer) r[3]);
                t.setCode(new Integer(r[4].toString()));
                b.setArticle(ar);
                l1.add(b);
            }
        }

        return l1;
    }

    @Override
    public List<Tcommandes> findTcommandesByPeriodeByClientSUMForMarge(Date d, Date d1, int client, int etat) {
        getEntityManager().setProperty("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        List<Tcommandes> l1 = new ArrayList<>();
        String sql = "SELECT SUM(tcommandes.quantite),SUM(tcommandes.margeclient),SUM(tcommandes.prix_total),SUM(tcommandes.margefournisseur), tarticles.nom, tarticles.code, tarticles.id, tetatc.code,tarticles.prix FROM tcommandes, tlignecommande, tetatc, tarticles\n"
                + "WHERE tcommandes.article = tarticles.id AND tlignecommande.etatc = tetatc.id \n"
                + "AND tcommandes.ligne = tlignecommande.id AND\n"
                + "tlignecommande.client = ?1 AND tlignecommande.datelivraison >= ?2 \n"
                + "AND tlignecommande.datelivraison <= ?3 AND tetatc.code = ?4 GROUP BY 5,6,7,8;";
        Query q = getEntityManager().createNativeQuery(sql);
        q.setParameter("1", client);
        q.setParameter("2", d);
        q.setParameter("3", d1);
        q.setParameter("4", etat);
        int i = 0;
        List<Object[]> rs = (List<Object[]>) q.getResultList();
        for (Object[] r : rs) {
            if (new Double(r[0].toString()) != 0.0) {
                Tcommandes b = new Tcommandes();
                Tarticles ar = new Tarticles();
                Tetatc t = new Tetatc();
                b.setQuantite(new Double(r[0].toString()));
                b.setMargeClient(new Double(r[1].toString()));
                b.setPrixTotal(new Double(r[2].toString()));
                b.setMargeFournisseur(new Double(r[3].toString()));

                ar.setNom((String) r[4]);
                ar.setCode((String) r[5]);
                ar.setId((Integer) r[6]);
                ar.setPrix(new Double(r[8].toString()));
                b.setArticle(ar);
                t.setCode(new Integer(r[7].toString()));
                b.setArticle(ar);
                l1.add(b);
            }
        }

        return l1;
    }

    @Override
    public Tarticles getSumCommander(Date d, Date d1, int societe, int etat, int article) {
        getEntityManager().setProperty("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        String sql = "SELECT SUM(tcommandes.quantite), tarticles.nom, tarticles.code, tarticles.id, tetatc.code FROM tcommandes, tlignecommande, tetatc, tarticles\n"
                + "WHERE tcommandes.article = tarticles.id AND tlignecommande.etatc = tetatc.id \n"
                + "AND tcommandes.ligne = tlignecommande.id AND tcommandes.article = ?5 AND\n"
                + "tlignecommande.societe = ?1 AND tlignecommande.datelivraison >= ?2 \n"
                + "AND tlignecommande.datelivraison <= ?3 AND tetatc.code = ?4 GROUP BY 2,3,4,5;";
        Query q = getEntityManager().createNativeQuery(sql);
        q.setParameter("1", societe);
        q.setParameter("2", d);
        q.setParameter("3", d1);
        q.setParameter("4", etat);
        q.setParameter("5", article);

        List<Object[]> rs = (List<Object[]>) q.getResultList();
        Tarticles ar = new Tarticles();
        for (Object[] r : rs) {
            if (new Double(r[0].toString()) != 0.0) {
                ar.setNom((String) r[1]);
                ar.setCode((String) r[2]);
                ar.setId((Integer) r[3]);
                ar.setStock(new Double(r[0].toString()));
            } else {
                ar.setNom((String) r[1]);
                ar.setCode((String) r[2]);
                ar.setId((Integer) r[3]);
                ar.setStock(0.0);
            }
        }

        return ar;
    }

    @Override
    public List<Tcommandes> findAllByLigne(int ligne) {
        getEntityManager().setProperty("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        Query q = getEntityManager().createNamedQuery("Tcommandes.findByLigne");
        q.setParameter("ligne", ligne);
        return q.getResultList();
    }

    @Override
    public Set<Tcommandes> findTcommandesCategorieByPeriode(Date d, Date d1, Integer cat, int etat) {
        Set<Tcommandes> ar = new HashSet<>();
        getEntityManager().setProperty("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        Query q = getEntityManager().createNamedQuery("Tcommandes.findForCategorie");
        q.setParameter("id", cat);
        q.setParameter("d", d);
        q.setParameter("d2", d1);
        q.setParameter("etat", etat);
        List<Tcommandes> l = q.getResultList();
        for (Tcommandes cc : l) {
            ar.add(cc);
        }
        return ar;
    }

    @Override
    public Set<Tcommandes> findTcommandesByArticleByPeriode(Date d, Date d1, Integer cat, int etat) {
        Set<Tcommandes> ar = new HashSet<>();
        getEntityManager().setProperty("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        Query q = getEntityManager().createNamedQuery("Tcommandes.findForArticlePeriode");
        q.setParameter("id", cat);
        q.setParameter("d", d);
        q.setParameter("d2", d1);
        q.setParameter("etat", etat);
        List<Tcommandes> l = q.getResultList();
        for (Tcommandes cc : l) {
            ar.add(cc);
        }
        return ar;
    }

    @Override
    public List<Tcommandes> findTcommandesByPeriodeByTournerSUM(Date d, Date d1, int tourner, int etat) {
        getEntityManager().setProperty("javax.persistence.cache.retrieveMode", CacheRetrieveMode.BYPASS);
        List<Tcommandes> l1 = new ArrayList<>();
        String sql = "SELECT SUM(tcommandes.quantite), tarticles.nom, tarticles.code, tarticles.id, tetatc.code FROM tcommandes, tlignecommande, tetatc, tarticles\n"
                + "WHERE tcommandes.article = tarticles.id AND tlignecommande.etatc = tetatc.id \n"
                + "AND tcommandes.ligne = tlignecommande.id AND\n"
                + "tlignecommande.tourner = ?1 AND tlignecommande.datelivraison >= ?2 \n"
                + "AND tlignecommande.datelivraison <= ?3 AND tetatc.code = ?4 GROUP BY 2,3,4,5;";
        Query q = getEntityManager().createNativeQuery(sql);
        q.setParameter("1", tourner);
        q.setParameter("2", d);
        q.setParameter("3", d1);
        q.setParameter("4", etat);
        int i = 0;
        List<Object[]> rs = (List<Object[]>) q.getResultList();
        for (Object[] r : rs) {
            if (new Double(r[0].toString()) != 0.0) {
                Tcommandes b = new Tcommandes();
                Tarticles ar = new Tarticles();
                Tetatc t = new Tetatc();
                b.setQuantite(new Double(r[0].toString()));
                ar.setNom((String) r[1]);
                ar.setCode((String) r[2]);
                ar.setId((Integer) r[3]);
                t.setCode(new Integer(r[4].toString()));
                b.setArticle(ar);
                l1.add(b);
            }
        }

        return l1;
    }

    @Override
    public List<Tcommandes> findTcommandesByPeriodeBySociete(Date d, Date d1, int societe, int etat) {
         String sql = "SELECT SUM(tcommandes.quantite),SUM(tcommandes.prix_total),SUM(tcommandes.remise), tarticles.nom, tarticles.code, tarticles.id, tetatc.code FROM tcommandes, tlignecommande, tetatc, tarticles\n"
                + "WHERE tcommandes.article = tarticles.id AND tlignecommande.etatc = tetatc.id \n"
                + "AND tcommandes.ligne = tlignecommande.id  AND\n"
                + "tlignecommande.societe = ?1 AND tlignecommande.datelivraison >= ?2 \n"
                + "AND tlignecommande.datelivraison <= ?3 AND tetatc.code = ?4 GROUP BY 4,5,6,7;";
        Query q = getEntityManager().createNativeQuery(sql);
        q.setParameter("1", societe);
        q.setParameter("2", d);
        q.setParameter("3", d1);
        q.setParameter("4", etat);

        List<Object[]> rs = (List<Object[]>) q.getResultList();
        List<Tcommandes> commandes = new ArrayList<>();
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
            Tcommandes cmd=new Tcommandes();
            cmd.setArticle(ar);
            cmd.setQuantite(new Double(r[0].toString()));
            cmd.setPrixTotal((double) r[1]);
            cmd.setRemise((double) r[2]);
            commandes.add(cmd);
        }
        return commandes;
    }

}
