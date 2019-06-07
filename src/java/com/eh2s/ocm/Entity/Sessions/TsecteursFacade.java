/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Sessions;

import com.eh2s.ocm.Entity.Exceptions.IllegalOrphanException;
import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Tclients;
import com.eh2s.ocm.Entity.Tdistricts;
import com.eh2s.ocm.Entity.Tsecteurs;
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
public class TsecteursFacade extends AbstractFacade<Tsecteurs> implements TsecteursFacadeLocal {

    @PersistenceContext(unitName = "ccmanagerPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TsecteursFacade() {
        super(Tsecteurs.class);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public Tsecteurs creer(Tsecteurs tsecteurs) throws RollbackFailureException, Exception {
        if (tsecteurs.getTclientsList() == null) {
            tsecteurs.setTclientsList(new ArrayList<Tclients>());
        }
        try {
            Tdistricts districtid = tsecteurs.getDistrictid();
            if (districtid != null) {
                districtid = getEntityManager().getReference(districtid.getClass(), districtid.getId());
                tsecteurs.setDistrictid(districtid);
            }
            List<Tclients> attachedTclientsList = new ArrayList<Tclients>();
            for (Tclients tclientsListTclientsToAttach : tsecteurs.getTclientsList()) {
                tclientsListTclientsToAttach = getEntityManager().getReference(tclientsListTclientsToAttach.getClass(), tclientsListTclientsToAttach.getId());
                attachedTclientsList.add(tclientsListTclientsToAttach);
            }
            tsecteurs.setTclientsList(attachedTclientsList);
            Tsecteurs secteur = getEntityManager().merge(tsecteurs);
            if (districtid != null) {
                districtid.getTsecteursList().add(secteur);
                districtid = getEntityManager().merge(districtid);
            }
            for (Tclients tclientsListTclients : secteur.getTclientsList()) {
                Tsecteurs oldSecteuridOfTclientsListTclients = tclientsListTclients.getSecteurid();
                tclientsListTclients.setSecteurid(secteur);
                tclientsListTclients = getEntityManager().merge(tclientsListTclients);
                if (oldSecteuridOfTclientsListTclients != null) {
                    oldSecteuridOfTclientsListTclients.getTclientsList().remove(tclientsListTclients);
                    oldSecteuridOfTclientsListTclients = getEntityManager().merge(oldSecteuridOfTclientsListTclients);
                }
            }
            return secteur;
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void MisAJour(Tsecteurs tsecteurs) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        try {
            Tsecteurs persistentTsecteurs = getEntityManager().find(Tsecteurs.class, tsecteurs.getId());
            Tdistricts districtidOld = persistentTsecteurs.getDistrictid();
            Tdistricts districtidNew = tsecteurs.getDistrictid();
            List<Tclients> tclientsListOld = persistentTsecteurs.getTclientsList();
            List<Tclients> tclientsListNew = tsecteurs.getTclientsList();
            List<String> illegalOrphanMessages = null;
            for (Tclients tclientsListOldTclients : tclientsListOld) {
                if (!tclientsListNew.contains(tclientsListOldTclients)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Tclients " + tclientsListOldTclients + " since its secteurid field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (districtidNew != null) {
                districtidNew = getEntityManager().getReference(districtidNew.getClass(), districtidNew.getId());
                tsecteurs.setDistrictid(districtidNew);
            }
            List<Tclients> attachedTclientsListNew = new ArrayList<Tclients>();
            for (Tclients tclientsListNewTclientsToAttach : tclientsListNew) {
                tclientsListNewTclientsToAttach = getEntityManager().getReference(tclientsListNewTclientsToAttach.getClass(), tclientsListNewTclientsToAttach.getId());
                attachedTclientsListNew.add(tclientsListNewTclientsToAttach);
            }
            tclientsListNew = attachedTclientsListNew;
            tsecteurs.setTclientsList(tclientsListNew);
            tsecteurs = getEntityManager().merge(tsecteurs);
            if (districtidOld != null && !districtidOld.equals(districtidNew)) {
                districtidOld.getTsecteursList().remove(tsecteurs);
                districtidOld = getEntityManager().merge(districtidOld);
            }
            if (districtidNew != null && !districtidNew.equals(districtidOld)) {
                districtidNew.getTsecteursList().add(tsecteurs);
                districtidNew = getEntityManager().merge(districtidNew);
            }
            for (Tclients tclientsListNewTclients : tclientsListNew) {
                if (!tclientsListOld.contains(tclientsListNewTclients)) {
                    Tsecteurs oldSecteuridOfTclientsListNewTclients = tclientsListNewTclients.getSecteurid();
                    tclientsListNewTclients.setSecteurid(tsecteurs);
                    tclientsListNewTclients = getEntityManager().merge(tclientsListNewTclients);
                    if (oldSecteuridOfTclientsListNewTclients != null && !oldSecteuridOfTclientsListNewTclients.equals(tsecteurs)) {
                        oldSecteuridOfTclientsListNewTclients.getTclientsList().remove(tclientsListNewTclients);
                        oldSecteuridOfTclientsListNewTclients = getEntityManager().merge(oldSecteuridOfTclientsListNewTclients);
                    }
                }
            }
        } catch (Exception ex) {

            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tsecteurs.getId();
                if (findTsecteurs(id) == null) {
                    throw new NonexistentEntityException("The tsecteurs with id " + id + " no longer exists.");
                }
            }
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    @Override
    public void Supprimer(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        try {
            Tsecteurs tsecteurs;
            try {
                tsecteurs = getEntityManager().getReference(Tsecteurs.class, id);
                tsecteurs.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tsecteurs with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Tclients> tclientsListOrphanCheck = tsecteurs.getTclientsList();
            for (Tclients tclientsListOrphanCheckTclients : tclientsListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Tsecteurs (" + tsecteurs + ") cannot be destroyed since the Tclients " + tclientsListOrphanCheckTclients + " in its tclientsList field has a non-nullable secteurid field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Tdistricts districtid = tsecteurs.getDistrictid();
            if (districtid != null) {
                districtid.getTsecteursList().remove(tsecteurs);
                districtid = getEntityManager().merge(districtid);
            }
            getEntityManager().remove(tsecteurs);
        } catch (Exception ex) {
            throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", ex);

        }
    }

    @Override
    public List<Tsecteurs> findTsecteursEntities() {
        return findTsecteursEntities(true, -1, -1);
    }

    @Override
    public List<Tsecteurs> findTsecteursEntities(int maxResults, int firstResult) {
        return findTsecteursEntities(false, maxResults, firstResult);
    }

    private List<Tsecteurs> findTsecteursEntities(boolean all, int maxResults, int firstResult) {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(Tsecteurs.class));
        Query q = getEntityManager().createQuery(cq);
        if (!all) {
            q.setMaxResults(maxResults);
            q.setFirstResult(firstResult);
        }
        return q.getResultList();
    }

    @Override
    public Tsecteurs findTsecteurs(Integer id) {
        return getEntityManager().find(Tsecteurs.class, id);
    }

    @Override
    public int getTsecteursCount() {
        CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
        Root<Tsecteurs> rt = cq.from(Tsecteurs.class);
        cq.select(getEntityManager().getCriteriaBuilder().count(rt));
        Query q = getEntityManager().createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }

    @Override
    public List<Tsecteurs> findAll(String societe) {
        Query q = getEntityManager().createNamedQuery("Tsecteurs.findAll");
        q.setParameter("imma", societe);
        return q.getResultList();
    }

    @Override
    public List<Tsecteurs> findAllByRegion(int region) {
        Query q = getEntityManager().createNamedQuery("Tsecteurs.findByRegion");
        q.setParameter("reg", region);
        return q.getResultList();
    }

}
