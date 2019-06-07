/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.FullControleurs;

import com.eh2s.ocm.Entity.Account;
import com.eh2s.ocm.Entity.Caisse;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Fournisseur;
import com.eh2s.ocm.Entity.Inventaire;
import com.eh2s.ocm.Entity.LigneAccount;
import com.eh2s.ocm.Entity.LigneCommandeFournisseur;
import com.eh2s.ocm.Entity.LigneSortie;
import com.eh2s.ocm.Entity.Services.Message;
import com.eh2s.ocm.Entity.Services.ServicesInitialize;
import com.eh2s.ocm.Entity.Sessions.AccountFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.CaisseFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.FournisseurFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.LigneAccountFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.LigneCommandeFournisseurFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.LigneSortieFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.SortieCaisseFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.SourceMouvementCaisseFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TclientsFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TetatcFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TlignecommandeFacadeLocal;
import com.eh2s.ocm.Entity.Societe;
import com.eh2s.ocm.Entity.SortieCaisse;
import com.eh2s.ocm.Entity.SourceMouvementCaisse;
import com.eh2s.ocm.Entity.Tclients;
import com.eh2s.ocm.Entity.Tlignecommande;
import com.eh2s.ocm.Entity.Tusers;
import com.eh2s.ocm.UtilityFonctions.date_du_jour;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrateur
 */
@WebServlet(name = "gestionCaisse", urlPatterns = {"/gestioncaisse"})
public class gestionCaisse extends HttpServlet {

    @EJB
    private FournisseurFacadeLocal fournisseurFacade;

    @EJB
    private LigneSortieFacadeLocal ligneSortieFacade;

    @EJB
    private LigneAccountFacadeLocal ligneAccountFacade1;

    @EJB
    private TetatcFacadeLocal tetatcFacade;

    @EJB
    private SourceMouvementCaisseFacadeLocal sourceMouvementCaisseFacade;

    @EJB
    private LigneCommandeFournisseurFacadeLocal ligneCommandeFournisseurFacade;

    @EJB
    private SortieCaisseFacadeLocal sortieCaisseFacade;

    @EJB
    private CaisseFacadeLocal caisseFacade;

    @EJB
    private AccountFacadeLocal accountFacade;

    @EJB
    private TlignecommandeFacadeLocal tlignecommandeFacade;

    @EJB
    private TclientsFacadeLocal tclientsFacade;

    @EJB
    private LigneAccountFacadeLocal ligneAccountFacade;

    @EJB
    private ServicesInitialize servicesInitializeImple;

    @PersistenceContext(unitName = "ccmanagerPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        if (session.getAttribute("isconnect") != null) {
            Tusers utilisateur = (Tusers) session.getAttribute("utilisateur");
            Societe societe = (Societe) session.getAttribute("societe");
            String actions = request.getParameter("action");
            request.setAttribute("q", request.getParameter("q"));
            switch (actions) {
                case "model":
                    String model = request.getParameter("model");
                    switch (model) {
                        case "init":
                            request.getSession().removeAttribute("ligneEncour");
                            if (request.getParameter("forpaiement") != null) {
                                request.setAttribute("lignesAccount", ligneSortieFacade.findByPeriodeSociete(date_du_jour.dateJourUniqueDate(), date_du_jour.dateJourUniqueDate(), societe.getId()));
                            } else {
                                request.setAttribute("lignesAccount", ligneAccountFacade.findByPeriodeSociete(date_du_jour.dateJourUniqueDate(), date_du_jour.dateJourUniqueDate(), societe.getId()));
                            }
                            request.setAttribute("lignesSortie", ligneSortieFacade.findByPeriodeSociete(date_du_jour.dateJourUniqueDate(), date_du_jour.dateJourUniqueDate(), societe.getId()));
                            request.setAttribute("entrees", accountFacade.findAllByPeriode(date_du_jour.dateJourUniqueDate(), date_du_jour.dateJourUniqueDate(), societe.getId()));
                            request.setAttribute("sorties", sortieCaisseFacade.findAllByPeriode(date_du_jour.dateJourUniqueDate(), date_du_jour.dateJourUniqueDate(), societe.getId()));
                            request.setAttribute("achats", ligneCommandeFournisseurFacade.findBonCommandeByPeriode(date_du_jour.dateJourUniqueDate(), date_du_jour.dateJourUniqueDate(), societe.getId()));
                            request.setAttribute("commandes", tlignecommandeFacade.findTcommandesByPeriode(date_du_jour.dateJourUniqueDate(), date_du_jour.dateJourUniqueDate(), societe.getImmatriculation()));
                            break;
                        case "addAccount":
                            switch (Integer.parseInt(request.getParameter("isNew"))) {
                                case 0: {
                                    try {
                                        //nouveau prêt 
                                        if (caisseIsOpen(request, utilisateur)) {
                                            addCredit(request, utilisateur);
                                        } else {
                                            Message message = new Message();
                                            message.setType("info");
                                            message.setTitle("INFORMATION");
                                            message.setNotification("Aucune caisse n'est ouverte pour le moment donc aucune transaction n'est possible");
                                            request.setAttribute("message", message);
                                        }

                                    } catch (Exception ex) {
                                        Message message = new Message();
                                        message.setType("info");
                                        message.setTitle("INFORMATION");
                                        message.setNotification("Aucune caisse n'est ouverte pour le moment donc aucune transaction n'est possible");
                                        request.setAttribute("message", message);
                                        Logger.getLogger(Inventaire.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }

                                break;
                                case 1: {
                                    try {
                                        //mis à jour prêt 
                                        if (caisseIsOpen(request, utilisateur)) {
                                            LigneAccount ligne = ligneAccountFacade.find(Integer.parseInt(request.getParameter("ligne")));
                                            if (!hasCredited(ligne)) {
                                                addCredit(request, utilisateur);
                                            } else {
                                                Message message = new Message();
                                                message.setType("warning");
                                                message.setTitle("Message");
                                                message.setNotification("Impossible de mettre cette élément à jour");
                                                //  request.setAttribute("message", message);
                                            }
                                        }

                                    } catch (Exception ex) {
                                        Logger.getLogger(Inventaire.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                break;
                                case 2: {
                                    try {
                                        //get ligne account to update 
                                        LigneAccount ligne = ligneAccountFacade.find(Integer.parseInt(request.getParameter("id")));
                                        request.getSession().setAttribute("ligneEncour", ligne);
                                        if (!hasCredited(ligne)) {
                                            request.setAttribute("ligneToUpdate", ligne);
                                            request.setAttribute("ligne", ligne);
                                        }
//                                        else {
//                                            Message message = new Message();
//                                            message.setType("warning");
//                                            message.setTitle("Message");
//                                            message.setNotification("Impossible de mettre cette élément à jour");
//                                            request.setAttribute("message", message);
//                                        }
                                        request.setAttribute("ligne", ligne);
                                    } catch (Exception ex) {
                                        Logger.getLogger(Inventaire.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                break;
                                case 3: {
                                    try {
                                        //supprimer un account
                                        String[] ids = request.getParameterValues("id");
                                        for (String id : ids) {
                                            try {
                                                LigneAccount ligne = ligneAccountFacade.find(Integer.parseInt(id));
                                                if (!hasCredited(ligne)) {
                                                    ligneAccountFacade.Supprimer(ligne.getId());
                                                } else {
                                                    Message message = new Message();
                                                    message.setType("error");
                                                    message.setTitle("ERROR");
                                                    message.setNotification("Impossible de supprimer certains élement");
                                                    //  request.setAttribute("message", message);
                                                }
                                            } catch (Exception ex) {
                                                Logger.getLogger(Administration.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        }

                                    } catch (Exception ex) {
                                        Logger.getLogger(Inventaire.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                break;
                                case 4: {
                                    try {
                                        //Enregistrement de plusieurs facture
                                        if (caisseIsOpen(request, utilisateur)) {
                                            String[] ids = request.getParameterValues("id");
                                            for (String id : ids) {
                                                try {
                                                    ReceptionTotaleFacture(request, utilisateur, Integer.parseInt(id));

                                                } catch (Exception ex) {
                                                    Logger.getLogger(Administration.class.getName()).log(Level.SEVERE, null, ex);
                                                }
                                            }
                                        } else {
                                            Message message = new Message();
                                            message.setType("info");
                                            message.setTitle("INFORMATION");
                                            message.setNotification("Aucune caisse n'est ouverte pour le moment donc aucune transaction n'est possible");
                                            request.setAttribute("message", message);
                                        }

                                    } catch (Exception ex) {
                                        Logger.getLogger(Inventaire.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    List<Tlignecommande> listCommandesH = tlignecommandeFacade.findAll(societe.getImmatriculation());
                                    request.setAttribute("commandes", listCommandesH);
                                }
                                break;
                            }
                            break;
                        case "addBonCommande":
                            switch (Integer.parseInt(request.getParameter("isNew"))) {
                                case 0: {
                                    try {
                                        //nouveau bon de commande 
                                        if (caisseIsOpen(request, utilisateur)) {
                                            addBonCommande(request, utilisateur, false);
                                        } else {
                                            Message message = new Message();
                                            message.setType("info");
                                            message.setTitle("INFROMATION");
                                            message.setNotification("Aucune caisse n'est ouverte pour le moment donc aucune transaction n'est possible");
                                            request.setAttribute("message", message);
                                        }

                                    } catch (Exception ex) {
                                        Message message = new Message();
                                        message.setType("info");
                                        message.setTitle("INFROMATION");
                                        message.setNotification("Aucune caisse n'est ouverte pour le moment donc aucune transaction n'est possible");
                                        request.setAttribute("message", message);
                                        Logger.getLogger(Inventaire.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }

                                break;
                                case 1: {
                                    try {
                                        //mis à jour prêt 
                                        if (caisseIsOpen(request, utilisateur)) {
                                            LigneSortie ligne = ligneSortieFacade.find(Integer.parseInt(request.getParameter("ligne")));
                                            if (!hasCredited(ligne)) {
                                                addBonCommande(request, utilisateur, true);
                                            } else {
                                                Message message = new Message();
                                                message.setType("warning");
                                                message.setTitle("Message");
                                                message.setNotification("Impossible de mettre cette élément à jour");
                                                request.setAttribute("message", message);
                                            }
                                        }

                                    } catch (Exception ex) {
                                        Logger.getLogger(Inventaire.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                break;
                                case 2: {
                                    try {
                                        //get ligne sortie to update 
                                        LigneSortie ligne = ligneSortieFacade.find(Integer.parseInt(request.getParameter("id")));
                                        request.getSession().setAttribute("ligneEncour", ligne);
                                        request.setAttribute("ligne", ligne);
                                        if (!hasCredited(ligne)) {

                                            request.setAttribute("ligneToUpdate", ligne);
                                        } else {
                                            Message message = new Message();
                                            message.setType("warning");
                                            message.setTitle("Message");
                                            message.setNotification("Impossible de mettre cette élément à jour");
                                            // request.setAttribute("message", message);
                                        }

                                    } catch (Exception ex) {
                                        Logger.getLogger(Inventaire.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                break;
                                case 3: {
                                    try {
                                        //supprimer une sortie
                                        String[] ids = request.getParameterValues("id");
                                        for (String id : ids) {
                                            try {
                                                LigneSortie ligne = ligneSortieFacade.find(Integer.parseInt(id));
                                                if (!hasCredited(ligne)) {
                                                    ligneSortieFacade.Supprimer(ligne.getId());
                                                } else {
                                                    Message message = new Message();
                                                    message.setType("error");
                                                    message.setTitle("ERROR");
                                                    message.setNotification("Impossible de supprimer certains élement");
                                                    request.setAttribute("message", message);
                                                }
                                            } catch (Exception ex) {
                                                Logger.getLogger(Administration.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        }

                                    } catch (Exception ex) {
                                        Logger.getLogger(Inventaire.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                break;

                            }
                            break;
                        case "reglementAccount":
                            switch (Integer.parseInt(request.getParameter("isNew"))) {
                                case 0: {
                                    try {
                                        //ajouter un reglement 
                                        if (caisseIsOpen(request, utilisateur)) {
                                            addAccount(request, utilisateur);
                                        }

                                    } catch (Exception ex) {
                                        Logger.getLogger(Inventaire.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }

                                break;
                                case 1: {
                                    try {
                                        //mis à jour prêt 
                                        if (caisseIsOpen(request, utilisateur)) {
                                            addAccount(request, utilisateur);
                                        }

                                    } catch (Exception ex) {
                                        Logger.getLogger(Inventaire.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                break;
                                case 2: {
                                    try {
                                        //get ligne account to update 
                                        Account account = accountFacade.find(Integer.parseInt(request.getParameter("id")));

                                        request.setAttribute("account", account);

                                    } catch (Exception ex) {
                                        Logger.getLogger(Inventaire.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                break;
                                case 3: {
                                    try {
                                        //supprimer un account
                                        if (request.getSession().getAttribute("ligneEncour") != null) {
                                            String[] ids = request.getParameterValues("id");
                                            for (String id : ids) {
                                                try {
                                                    Account account = accountFacade.find(Integer.parseInt(id));
                                                    accountFacade.Supprimer(account.getId());
                                                    LigneAccount ligneAccount = (LigneAccount) request.getSession().getAttribute("ligneEncour");
                                                    ligneAccount.getAccountList().remove(account);
                                                    ligneAccount.setDateUpdate(date_du_jour.dateJourUniqueDate());
                                                    ligneAccount.setMontantRestant(ligneAccount.getMontantRestant() + account.getAvance());
                                                    ligneAccountFacade.MisAjour(ligneAccount);
                                                    request.getSession().setAttribute("ligneEncour", ligneAccount);

                                                } catch (Exception ex) {
                                                    Logger.getLogger(Administration.class.getName()).log(Level.SEVERE, null, ex);
                                                }
                                            }

                                        }

                                    } catch (Exception ex) {
                                        Logger.getLogger(Inventaire.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                break;
                                case 4: {
                                    try {
                                        //ajouter un reglement 
                                        if (caisseIsOpen(request, utilisateur)) {
                                            addSortieBon(request, utilisateur);
                                        }

                                    } catch (Exception ex) {
                                        Logger.getLogger(Inventaire.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }

                                break;
                                case 5: {
                                    try {
                                        //mis à jour prêt 
                                        if (caisseIsOpen(request, utilisateur)) {
                                            addSortieBon(request, utilisateur);
                                        }

                                    } catch (Exception ex) {
                                        Logger.getLogger(Inventaire.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                break;

                            }
                            break;
                        case "sortieAccount":
                            switch (Integer.parseInt(request.getParameter("isNew"))) {
                                case 0: {
                                    try {
                                        //initialisation d'une sortie de caisse 
                                    } catch (Exception ex) {
                                        Logger.getLogger(Inventaire.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    request.setAttribute("sorties", sortieCaisseFacade.findAllByPeriode(date_du_jour.dateJourUniqueDate(), date_du_jour.dateJourUniqueDate(), utilisateur.getSociete().getId()));
                                }

                                break;
                                case 1: {
                                    try {
                                        //creer une sortie
                                        if (caisseIsOpen(request, utilisateur)) {
                                            addSortie(request, utilisateur);
                                        }

                                    } catch (Exception ex) {
                                        Logger.getLogger(Inventaire.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    request.setAttribute("sorties", sortieCaisseFacade.findAllByPeriode(date_du_jour.dateJourUniqueDate(), date_du_jour.dateJourUniqueDate(), utilisateur.getSociete().getId()));
                                }
                                break;
                                case 2: {
                                    try {
                                        //voir les détails d'une sortie 
                                        SortieCaisse sorti = sortieCaisseFacade.find(Integer.parseInt(request.getParameter("id")));

                                        request.setAttribute("sortie", sorti);

                                    } catch (Exception ex) {
                                        Logger.getLogger(Inventaire.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    request.setAttribute("sorties", sortieCaisseFacade.findAllByPeriode(date_du_jour.dateJourUniqueDate(), date_du_jour.dateJourUniqueDate(), utilisateur.getSociete().getId()));
                                }
                                break;
                                case 3: {
                                    try {
                                        //supprimer une sortie

                                    } catch (Exception ex) {
                                        Logger.getLogger(Inventaire.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    request.setAttribute("sorties", sortieCaisseFacade.findAllByPeriode(date_du_jour.dateJourUniqueDate(), date_du_jour.dateJourUniqueDate(), utilisateur.getSociete().getId()));
                                }
                                break;

                            }
                            break;
                        case "entreeAccount":
                            switch (Integer.parseInt(request.getParameter("isNew"))) {
                                case 0: {
                                    try {
                                        //initialisation d'une entrée en caisse 
                                    } catch (Exception ex) {
                                        Logger.getLogger(Inventaire.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    request.setAttribute("entrees", accountFacade.findAll(utilisateur.getSociete().getId()));
                                }

                                break;
                                case 1: {
                                    try {
                                        //creer une entree
                                        if (caisseIsOpen(request, utilisateur)) {
                                            addEntree(request, utilisateur);
                                        }

                                    } catch (Exception ex) {
                                        Logger.getLogger(Inventaire.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    request.setAttribute("entrees", accountFacade.findAllByPeriode(date_du_jour.dateJourUniqueDate(), date_du_jour.dateJourUniqueDate(), societe.getId()));
                                }
                                break;
                                case 2: {
                                    try {
                                        //voir les détails d'une sortie 
                                        Account sorti = accountFacade.find(Integer.parseInt(request.getParameter("id")));

                                        request.setAttribute("entree", sorti);

                                    } catch (Exception ex) {
                                        Logger.getLogger(Inventaire.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    request.setAttribute("entrees", accountFacade.findAllByPeriode(date_du_jour.dateJourUniqueDate(), date_du_jour.dateJourUniqueDate(), societe.getId()));
                                }
                                break;
                                case 3: {
                                    try {
                                        //supprimer une sortie

                                    } catch (Exception ex) {
                                        Logger.getLogger(Inventaire.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    request.setAttribute("entrees", accountFacade.findAllByPeriode(date_du_jour.dateJourUniqueDate(), date_du_jour.dateJourUniqueDate(), societe.getId()));
                                }
                                break;
                                case 4: {
                                    try {
                                        //supprimer une facture
                                        supprimerVente(request, utilisateur);
                                    } catch (Exception ex) {
                                        Logger.getLogger(Inventaire.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    request.setAttribute("lignesAccount", ligneAccountFacade.findByPeriodeSociete(date_du_jour.dateJourUniqueDate(), date_du_jour.dateJourUniqueDate(), utilisateur.getSociete().getId()));
                                }
                                break;

                            }
                            break;
                        case "caisse":
                            switch (Integer.parseInt(request.getParameter("isNew"))) {
                                case 0: {
                                    try {
                                        Caisse caisse = caisseFacade.findCaisseDuJour(utilisateur.getSociete().getId());
                                        if (caisseIsOpen(request, utilisateur)) {
                                            request.setAttribute("caisse", caisse);
                                        }

                                    } catch (Exception ex) {
                                        Message message = new Message();
                                        message.setType("info");
                                        message.setTitle("INFORMATION");
                                        message.setNotification("Vous ne disposez pas encore de compte caisse veuillez créer un pour vos opérations");
                                        request.setAttribute("message", message);
                                        Logger.getLogger(Inventaire.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    request.setAttribute("caisses", caisseFacade.findAll(utilisateur.getSociete().getId()));
                                }

                                break;
                                case 1: {
                                    try {
                                        //ouverture de la caisse 
                                        if (!caisseIsOpen(request, utilisateur)) {
                                            openCaisse(request, utilisateur);
                                        }
                                        request.setAttribute("caisse", caisseFacade.findCaisseDuJour(utilisateur.getSociete().getId()));
                                    } catch (Exception ex) {
                                        Logger.getLogger(Inventaire.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    request.setAttribute("caisses", caisseFacade.findAll(utilisateur.getSociete().getId()));
                                }

                                break;
                                case 2: {
                                    try {
                                        //fermer la caisse 
                                        if (caisseIsOpen(request, utilisateur)) {
                                            Caisse caisse = closeCaisse(request, utilisateur);
                                            request.setAttribute("caisse", caisse);
                                        } else {
                                            Message message = new Message();
                                            message.setType("info");
                                            message.setTitle("INFORMATION");
                                            message.setNotification("Aucune caisse n'est ouverte pour le moment");
                                            request.setAttribute("message", message);
                                        }

                                    } catch (Exception ex) {
                                        Logger.getLogger(Inventaire.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    request.setAttribute("caisses", caisseFacade.findAll(utilisateur.getSociete().getId()));
                                }
                                break;
                                case 3: {
                                    try {
                                        //creer un compte 
                                        Caisse caisse = creerCaisse(request, utilisateur);
                                        request.setAttribute("caisse", caisse);

                                    } catch (Exception ex) {
                                        Logger.getLogger(Inventaire.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    request.setAttribute("caisses", caisseFacade.findAll(utilisateur.getSociete().getId()));
                                }
                                break;

                            }
                            break;
                        case "facturationAccount":
                            switch (Integer.parseInt(request.getParameter("isNew"))) {

                                case 0: {
                                    try {
                                        //nouvelle facture 
                                        if (caisseIsOpen(request, utilisateur)) {
                                            addCredit(request, utilisateur);
                                        } else {
                                            Message message = new Message();
                                            message.setType("info");
                                            message.setTitle("INFORMATION");
                                            message.setNotification("Aucune caisse n'est ouverte pour le moment donc aucune transaction n'est possible");
                                            request.setAttribute("message", message);
                                            if (request.getParameter("ligne_commande") != null) {
                                                if (!request.getParameter("ligne_commande").isEmpty()) {
                                                    Tlignecommande ligne_cm = tlignecommandeFacade.find(Integer.parseInt(request.getParameter("ligne_commande")));
                                                    request.setAttribute("ligne", ligne_cm);
                                                }
                                            }

                                        }
                                    } catch (Exception ex) {
                                        Logger.getLogger(gestionCaisse.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                break;
                                case 1: {
                                    try {
                                        Tlignecommande ligne = tlignecommandeFacade.findTlignecommande(Integer.parseInt(request.getParameter("id")));
                                        request.setAttribute("ligne", ligne);
                                    } catch (Exception ex) {
                                        Logger.getLogger(gestionCaisse.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                break;
                                case 2: {
                                    try {
                                        LigneCommandeFournisseur ligne = ligneCommandeFournisseurFacade.find(Integer.parseInt(request.getParameter("id")));
                                        request.setAttribute("ligne", ligne);
                                    } catch (Exception ex) {
                                        Logger.getLogger(Inventaire.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                break;
                                case 3: {
                                    try {
                                        //nouvelle Sortie pour bon de commande
                                        if (caisseIsOpen(request, utilisateur)) {
                                            addBonCommande(request, utilisateur, false);
                                        } else {
                                            Message message = new Message();
                                            message.setType("info");
                                            message.setTitle("INFORMATION");
                                            message.setNotification("Aucune caisse n'est ouverte pour le moment donc aucune transaction n'est possible");
                                            request.setAttribute("message", message);
                                        }
                                    } catch (Exception ex) {
                                        Logger.getLogger(Inventaire.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                break;
                                case 4: {
                                    try {
                                        //nouveau prêt 
                                        if (caisseIsOpen(request, utilisateur)) {
                                            addDette(request, utilisateur);
                                        } else {
                                            Message message = new Message();
                                            message.setType("info");
                                            message.setTitle("INFROMATION");
                                            message.setNotification("Aucune caisse n'est ouverte pour le moment donc aucune transaction n'est possible");
                                            request.setAttribute("message", message);
                                        }

                                    } catch (Exception ex) {
                                        Message message = new Message();
                                        message.setType("info");
                                        message.setTitle("INFROMATION");
                                        message.setNotification("Aucune caisse n'est ouverte pour le moment donc aucune transaction n'est possible");
                                        request.setAttribute("message", message);
                                        Logger.getLogger(Inventaire.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }

                                break;
                                case 5: {
                                    try {
                                        //nouvelle dette pour fournisseur
                                        if (caisseIsOpen(request, utilisateur)) {
                                            addDetteFournisseur(request, utilisateur, false);
                                        } else {
                                            Message message = new Message();
                                            message.setType("info");
                                            message.setTitle("INFORMATION");
                                            message.setNotification("Aucune caisse n'est ouverte pour le moment donc aucune transaction n'est possible");
                                            request.setAttribute("message", message);
                                        }
                                    } catch (Exception ex) {
                                        Logger.getLogger(Inventaire.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                break;
                            }

                            break;
                        case "historiqueAccount":
                            switch (Integer.parseInt(request.getParameter("isNew"))) {
                                case 0: {
                                    try {
                                        request.getSession().setAttribute("allAccount", ligneAccountFacade.findBySociete(societe.getId()));
                                    } catch (Exception ex) {
                                        Logger.getLogger(Inventaire.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                break;
                                case 1: {
                                    try {
                                        historiqueAccount(request, utilisateur);
                                    } catch (Exception ex) {
                                        Logger.getLogger(Inventaire.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                break;
                                case 2: {
                                    try {
                                        LigneAccount lg = ligneAccountFacade.find(Integer.parseInt(request.getParameter("id")));
                                        request.setAttribute("ligne", lg);
                                    } catch (Exception ex) {
                                        Logger.getLogger(Inventaire.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                break;
                            }

                            break;
                        default:
                            break;
                    }
                    if (utilisateur.getId() == -1) {
                        servicesInitializeImple.initiationSuperAdmin(request);
                    } else {
                        if (utilisateur.getGroupeid().getType() == -1) {
                            servicesInitializeImple.initiationEntityAdmin(request, utilisateur);
                        } else if (utilisateur.getGroupeid().getType() == 1) {
                            servicesInitializeImple.initiationEntityAdminLocal(request, utilisateur);
                        }
                        servicesInitializeImple.initialize(request, utilisateur);
                    }

                    request.setAttribute("q", request.getParameter("q"));
                    if (request.getParameter("forpaiement") != null) {
                        request.setAttribute("lignesAccount", ligneSortieFacade.findByPeriodeSociete(date_du_jour.dateJourUniqueDate(), date_du_jour.dateJourUniqueDate(), societe.getId()));
                    } else {
                        request.setAttribute("lignesAccount", ligneAccountFacade.findByPeriodeSociete(date_du_jour.dateJourUniqueDate(), date_du_jour.dateJourUniqueDate(), societe.getId()));
                    }
                    request.getServletContext().getRequestDispatcher("/WEB-INF/main.jsp").forward(request, response);

                    break;
            }

        } else {
            request.setAttribute("message", new Message("votre session a expirer veuillez vous reconnecter"));
            request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public void persist(Object object) {
        try {
            utx.begin();
            em.persist(object);
            utx.commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            throw new RuntimeException(e);
        }
    }

    private boolean hasCredited(LigneAccount ligne) {

        if (ligne.getAccountList().isEmpty()) {
            return false;
        }
        return true;
    }

    private boolean hasCredited(LigneSortie ligne) {

        if (ligne.getSortietList().isEmpty()) {
            return false;
        }
        return true;
    }
    // reception totale d'une facture

    private void ReceptionTotaleFacture(HttpServletRequest request, Tusers utilisateur, int IdLigneCommande) throws Exception {
        LigneAccount ligneAccount = new LigneAccount();
        ligneAccount.setEtat(1);
        Account account = new Account();
        Double avance = 0.0;
        Tlignecommande ligne_cm = new Tlignecommande();
        Caisse caisse = caisseFacade.findCaisseDuJour(utilisateur.getSociete().getId());
        ligne_cm = tlignecommandeFacade.find(IdLigneCommande);
        boolean error = false;

        ligneAccount.setClient(ligne_cm.getClient());
        ligneAccount.setOperateur(utilisateur);
        ligneAccount.setSociete(utilisateur.getSociete());
        ligneAccount.setDateCreate(date_du_jour.dateJourUniqueDate());

        ligneAccount.setMontantNet(ligne_cm.getPrixtotal());
        avance = ligneAccount.getMontantNet();
        ligneAccount.setMontantRestant(0.0);
        ligneAccount.setCommentaire("Encaissemnet de la commande N° " + ligne_cm.getId());

        if (!error) {
            if (ligne_cm.getPrixtotal() == ligneAccount.getMontantNet()) {
                if (ligneAccount.getMontantRestant() <= ligneAccount.getMontantNet()) {
                    ligneAccount.setDateUpdate(date_du_jour.dateJourUniqueDate());

                    ligneAccount = ligneAccountFacade.creer(ligneAccount);
                    if (avance != 0.0) {
                        account.setAvance(avance);
                        account.setCaisse(caisse);
                        account.setDate(ligneAccount.getDateCreate());
                        if (!ligneAccount.getCommentaire().isEmpty()) {
                            account.setCommentaire(ligneAccount.getCommentaire());
                        }
                        account.setOperateur(utilisateur);
                        account.setLigneAccount(ligneAccount);
                        account.setMontantInit(ligneAccount.getMontantNet());
                        account.setMontantRestant(ligneAccount.getMontantRestant());
                        SourceMouvementCaisse source = sourceMouvementCaisseFacade.find(3);
                        account.setSource(source);
                        account.setSoldeCaisse(caisse.getMontantRestant() + account.getAvance());
                        account = accountFacade.creer(account);
                        caisse.setMontantRestant(caisse.getMontantRestant() + account.getAvance());
                        caisseFacade.MisAjour(caisse);

                    }
                    ligne_cm.setEtatc(tetatcFacade.findByCode(300));
                    ligne_cm = tlignecommandeFacade.MisAJour(ligne_cm);
                    Message message = new Message();
                    message.setType("success");
                    message.setTitle("FACTURATION");
                    message.setNotification("commande correctement facturée");
                    request.setAttribute("message", message);

                } else {
                    Message message = new Message();
                    message.setType("warning");
                    message.setTitle("Attention!!!");
                    message.setNotification("l'avance ne peut être plus grande que la somme à payer");
                    request.setAttribute("message", message);
                }

            } else {
                Message message = new Message();
                message.setType("warning");
                message.setTitle("Attention !!!");
                message.setNotification("Vous ne pouvez surfacturer cette commande");
                request.setAttribute("message", message);
            }
        }
    }
// entréé d'une facture

    private void addCredit(HttpServletRequest request, Tusers utilisateur) throws Exception {
        LigneAccount ligneAccount = null;
        Account account = new Account();
        Double avance = 0.0;
        Caisse caisse = caisseFacade.findCaisseDuJour(utilisateur.getSociete().getId());
        boolean error = false;
        if (Integer.parseInt(request.getParameter("isNew")) == 0) {
            ligneAccount = new LigneAccount();
            ligneAccount.setEtat(0);
        } else {
            if (request.getParameter("ligne") != null) {
                ligneAccount = ligneAccountFacade.find(Integer.parseInt(request.getParameter("ligne")));
            }

        }
        if (!request.getParameter("client").isEmpty()) {
            ligneAccount.setClient(tclientsFacade.find(Integer.parseInt(request.getParameter("client"))));
        }
        ligneAccount.setOperateur(utilisateur);
        ligneAccount.setSociete(utilisateur.getSociete());
        ligneAccount.setDateCreate(date_du_jour.dateJourUniqueDate());
        Tlignecommande ligne_cm = new Tlignecommande();
        if (request.getParameter("ligne_commande") != null) {
            if (!request.getParameter("ligne_commande").isEmpty()) {
                ligne_cm = tlignecommandeFacade.find(Integer.parseInt(request.getParameter("ligne_commande")));
                // vérifier si la commande appartient réellement au client en question
                if (Objects.equals(ligne_cm.getClient().getId(), ligneAccount.getClient().getId())) {
                    ligneAccount.setLigneCommande(ligne_cm);
                } else {
                    error = true;
                    Message message = new Message();
                    message.setType("error");
                    message.setTitle("ERROR");
                    message.setNotification("cette commande n'appartient pas à ce client");
                    request.setAttribute("message", message);

                }

            }
        }

        if (request.getParameter("montant_net") != null) {
            if (!request.getParameter("montant_net").isEmpty()) {
                ligneAccount.setMontantNet(Double.parseDouble(request.getParameter("montant_net")));
            }
        } else {
            ligneAccount.setMontantNet(ligne_cm.getPrixtotal());
        }
        if (request.getParameter("allencaissement") != null && request.getParameter("avance") == null && request.getParameter("commentaire") == null) {
            ligneAccount.setEtat(1);
            ligneAccount.setMontantRestant(0.0);
            avance = ligneAccount.getMontantNet();
            ligneAccount.setCommentaire("Encaissemnet de la commande N° " + ligne_cm.getId());
        } else {
            if (!request.getParameter("commentaire").isEmpty()) {
                ligneAccount.setCommentaire(request.getParameter("commentaire"));
            }
            if (!request.getParameter("avance").isEmpty()) {
                if (Double.parseDouble(request.getParameter("avance")) != 0.0) {
                    avance = Double.parseDouble(request.getParameter("avance"));
                    ligneAccount.setMontantRestant(ligneAccount.getMontantNet() - avance);
                    if (ligneAccount.getMontantNet() == avance) {
                        ligneAccount.setEtat(1);
                    }
                } else {
                    ligneAccount.setMontantRestant(ligneAccount.getMontantNet());
                }

            }
        }
        if (!error) {
            if (ligne_cm.getPrixtotal() == ligneAccount.getMontantNet()) {
                if (ligneAccount.getMontantRestant() <= ligneAccount.getMontantNet()) {
                    if (Integer.parseInt(request.getParameter("isNew")) == 0) {

                        ligneAccount.setDateUpdate(date_du_jour.dateJourUniqueDate());

                        ligneAccount = ligneAccountFacade.creer(ligneAccount);
                        if (avance != 0.0) {
                            account.setAvance(avance);
                            account.setCaisse(caisse);
                            account.setDate(ligneAccount.getDateCreate());
                            if (!ligneAccount.getCommentaire().isEmpty()) {
                                account.setCommentaire(ligneAccount.getCommentaire());
                            }
                            account.setOperateur(utilisateur);
                            account.setLigneAccount(ligneAccount);
                            account.setMontantInit(ligneAccount.getMontantNet());
                            account.setMontantRestant(ligneAccount.getMontantRestant());
                            SourceMouvementCaisse source = sourceMouvementCaisseFacade.find(3);
                            account.setSource(source);
                            account.setSoldeCaisse(caisse.getMontantRestant() + account.getAvance());
                            account = accountFacade.creer(account);
                            caisse.setMontantRestant(caisse.getMontantRestant() + account.getAvance());
                            caisseFacade.MisAjour(caisse);

                        }
                        ligne_cm.setEtatc(tetatcFacade.findByCode(300));
                        ligne_cm = tlignecommandeFacade.MisAJour(ligne_cm);
                        Message message = new Message();
                        message.setType("success");
                        message.setTitle("FACTURATION");
                        message.setNotification("votre commande a été correctement facturée");
                        request.setAttribute("message", message);

                    }

//                else {
//                    ligneAccountFacade.MisAjour(ligneAccount);
//                }
                } else {
                    Message message = new Message();
                    message.setType("warning");
                    message.setTitle("Attention!!!");
                    message.setNotification("l'avance ne peut être plus grande que la somme à payer");
                    request.setAttribute("message", message);
                }

            } else {
                Message message = new Message();
                message.setType("warning");
                message.setTitle("Attention !!!");
                message.setNotification("Vous ne pouvez surfacturer cette commande");
                request.setAttribute("message", message);
            }
        }
        request.setAttribute("ligne", ligne_cm);
    }

    // entréé d'une facture
    private void addDette(HttpServletRequest request, Tusers utilisateur) throws Exception {
        LigneAccount ligneAccount = null;
        Account account = new Account();
        Double avance = 0.0;
        Caisse caisse = caisseFacade.findCaisseDuJour(utilisateur.getSociete().getId());
        boolean error = false;
        if (Integer.parseInt(request.getParameter("isNew")) == 4) {
            ligneAccount = new LigneAccount();
            ligneAccount.setEtat(0);
        } else {
            if (request.getParameter("ligne") != null) {
                ligneAccount = ligneAccountFacade.find(Integer.parseInt(request.getParameter("ligne")));
            }

        }
        if (!request.getParameter("client").isEmpty()) {
            ligneAccount.setClient(tclientsFacade.find(Integer.parseInt(request.getParameter("client"))));
        }
        ligneAccount.setOperateur(utilisateur);
        ligneAccount.setSociete(utilisateur.getSociete());
        ligneAccount.setDateCreate(date_du_jour.dateJourUniqueDate());
        Tlignecommande ligne_cm = new Tlignecommande();

        if (request.getParameter("montant_net") != null) {
            if (!request.getParameter("montant_net").isEmpty()) {
                ligneAccount.setMontantNet(Double.parseDouble(request.getParameter("montant_net")));
            }
        } else {
            ligneAccount.setMontantNet(ligne_cm.getPrixtotal());
        }
        if (request.getParameter("allencaissement") != null && request.getParameter("avance") == null && request.getParameter("commentaire") == null) {
            ligneAccount.setEtat(1);
            ligneAccount.setMontantRestant(0.0);
            avance = ligneAccount.getMontantNet();
            ligneAccount.setCommentaire("Encaissemnet de la commande N° " + ligne_cm.getId());
        } else {
            if (!request.getParameter("commentaire").isEmpty()) {
                ligneAccount.setCommentaire(request.getParameter("commentaire"));
            }
            if (!request.getParameter("avance").isEmpty()) {
                if (Double.parseDouble(request.getParameter("avance")) != 0.0) {
                    avance = Double.parseDouble(request.getParameter("avance"));
                    ligneAccount.setMontantRestant(ligneAccount.getMontantNet() - avance);
                    if (ligneAccount.getMontantNet() == avance) {
                        ligneAccount.setEtat(1);
                    }
                } else {
                    ligneAccount.setMontantRestant(ligneAccount.getMontantNet());
                }

            }
        }

        if (!error) {
            if (ligneAccount.getMontantRestant() <= ligneAccount.getMontantNet()) {
                if (Integer.parseInt(request.getParameter("isNew")) == 4) {

                    ligneAccount.setDateUpdate(date_du_jour.dateJourUniqueDate());

                    ligneAccount = ligneAccountFacade.creer(ligneAccount);
                    if (avance != 0.0) {
                        account.setAvance(avance);
                        account.setCaisse(caisse);
                        account.setDate(ligneAccount.getDateCreate());
                        if (!ligneAccount.getCommentaire().isEmpty()) {
                            account.setCommentaire(ligneAccount.getCommentaire());
                        }
                        account.setOperateur(utilisateur);
                        account.setLigneAccount(ligneAccount);
                        account.setMontantInit(ligneAccount.getMontantNet());
                        account.setMontantRestant(ligneAccount.getMontantRestant());
                        SourceMouvementCaisse source = sourceMouvementCaisseFacade.find(3);
                        account.setSource(source);
                        account.setSoldeCaisse(caisse.getMontantRestant() + account.getAvance());
                        account = accountFacade.creer(account);
                        caisse.setMontantRestant(caisse.getMontantRestant() + account.getAvance());
                        caisseFacade.MisAjour(caisse);

                    }
                    Message message = new Message();
                    message.setType("success");
                    message.setTitle("DETTE");
                    message.setNotification("CETTE DETTE a été correctement Enregistrée");
                    request.setAttribute("message", message);

                }
            } else {
                Message message = new Message();
                message.setType("warning");
                message.setTitle("Attention!!!");
                message.setNotification("l'avance ne peut être plus grande que la somme à payer");
                request.setAttribute("message", message);
            }

        }
    }
//sortie pour bon de commande

    private void addBonCommande(HttpServletRequest request, Tusers utilisateur, boolean isupdate) throws Exception {
        LigneSortie ligneSortie = null;
        SortieCaisse sortie = new SortieCaisse();
        Double avance = 0.0;
        Caisse caisse = caisseFacade.findCaisseDuJour(utilisateur.getSociete().getId());
        boolean error = false;
        if (!isupdate) {
            ligneSortie = new LigneSortie();
            ligneSortie.setEtat(0);
        } else {
            ligneSortie = ligneSortieFacade.find(Integer.parseInt(request.getParameter("ligne")));
        }
        ligneSortie.setOperateur(utilisateur);
        ligneSortie.setSociete(utilisateur.getSociete());
        ligneSortie.setDateCreate(date_du_jour.dateJourUniqueDate());
        LigneCommandeFournisseur ligne_cm = new LigneCommandeFournisseur();
        if (!request.getParameter("ligne_commande").isEmpty()) {
            ligne_cm = ligneCommandeFournisseurFacade.find(Integer.parseInt(request.getParameter("ligne_commande")));
            // vérifier si la commande appartient réellement au client en question
            // ligneSortie.setNumeroBon(ligne_cm);

        }

        if (request.getParameter("montant_net") != null) {
            if (!request.getParameter("montant_net").isEmpty()) {
                ligneSortie.setMontantNet(Double.parseDouble(request.getParameter("montant_net")));
            }
        } else {
            ligneSortie.setMontantNet(ligne_cm.getPrixTotal());
        }
        if (request.getParameter("allencaissement") != null && request.getParameter("avance") == null && request.getParameter("commentaire") == null) {
            ligneSortie.setEtat(1);
            ligneSortie.setMontantRestant(0.0);
            avance = ligneSortie.getMontantNet();
            ligneSortie.setCommentaire("Decaissement pour le bon de commande N° " + ligne_cm.getId());
        } else {
            if (!request.getParameter("commentaire").isEmpty()) {
                ligneSortie.setCommentaire(request.getParameter("commentaire"));
            }
            if (!request.getParameter("avance").isEmpty()) {
                if (Double.parseDouble(request.getParameter("avance")) != 0.0) {
                    avance = Double.parseDouble(request.getParameter("avance"));
                    ligneSortie.setMontantRestant(ligneSortie.getMontantNet() - avance);
                    if (ligneSortie.getMontantNet() == avance) {
                        ligneSortie.setEtat(1);
                    }
                } else {
                    ligneSortie.setMontantRestant(ligneSortie.getMontantNet());
                }

            }
        }

        if (!error) {
            if (caisse.getMontantRestant() >= avance) {

                if (ligne_cm.getPrixTotal() == ligneSortie.getMontantNet()) {
                    if (ligneSortie.getMontantRestant() <= ligneSortie.getMontantNet()) {
                        if (!isupdate) {
                            ligne_cm.setEtat(tetatcFacade.findByCode(300));

                            ligne_cm = ligneCommandeFournisseurFacade.MisAJour(ligne_cm);
                            ligneSortie.setDateUpdate(date_du_jour.dateJourUniqueDate());
                            ligneSortie.setNumeroBon(ligne_cm);
                            ligneSortie.setFournisseur(ligne_cm.getFournisseur());
                            ligneSortie = ligneSortieFacade.creer(ligneSortie);
                            if (avance != 0.0) {
                                sortie.setAvance(avance);
                                sortie.setCaisse(caisse);
                                sortie.setDate(ligneSortie.getDateCreate());
                                if (!ligneSortie.getCommentaire().isEmpty()) {
                                    sortie.setCommentaire(ligneSortie.getCommentaire());
                                }
                                sortie.setOperateur(utilisateur);
                                sortie.setLigneSortie(ligneSortie);
                                sortie.setMontant(ligneSortie.getMontantNet());
                                sortie.setMontantRestant(ligneSortie.getMontantRestant());
                                SourceMouvementCaisse source = sourceMouvementCaisseFacade.find(3);
                                sortie.setSource(source);
                                sortie.setSoldeCaisse(caisse.getMontantRestant() - sortie.getAvance());
                                sortie = sortieCaisseFacade.creer(sortie);
                                caisse.setMontantRestant(caisse.getMontantRestant() - sortie.getAvance());
                                caisseFacade.MisAjour(caisse);

                                Message message = new Message();
                                message.setType("success");
                                message.setTitle("FACTURATION");
                                message.setNotification("votre bon de commande a été correctement décaissé");
                                request.setAttribute("message", message);
                            }

                        }

//                else {
//                    ligneAccountFacade.MisAjour(ligneAccount);
//                }
                    } else {
                        Message message = new Message();
                        message.setType("warning");
                        message.setTitle("Attention!!!");
                        message.setNotification("l'avance ne peut être plus grande que la somme à payer");
                        request.setAttribute("message", message);
                    }

                } else {
                    Message message = new Message();
                    message.setType("warning");
                    message.setTitle("Attention !!!");
                    message.setNotification("Vous ne pouvez surfacturer cette commande");
                    request.setAttribute("message", message);
                }
            } else {
                Message message = new Message();
                message.setType("warning");
                message.setTitle("IMPOSSIBLE !!!");
                message.setNotification("La caisse ne dispose pas assez de fond pour effectuer cette operation");
                request.setAttribute("message", message);
            }
        }
        request.setAttribute("ligne", ligne_cm);
    }

    //sortie pour bon de commande
    private void addDetteFournisseur(HttpServletRequest request, Tusers utilisateur, boolean isupdate) throws Exception {
        LigneSortie ligneSortie = null;
        SortieCaisse sortie = new SortieCaisse();
        Double avance = 0.0;
        Caisse caisse = caisseFacade.findCaisseDuJour(utilisateur.getSociete().getId());
        boolean error = false;
        if (!isupdate) {
            ligneSortie = new LigneSortie();
            ligneSortie.setEtat(0);
        }
        ligneSortie.setOperateur(utilisateur);
        ligneSortie.setSociete(utilisateur.getSociete());
        ligneSortie.setDateCreate(date_du_jour.dateJourUniqueDate());
        LigneCommandeFournisseur ligne_cm = new LigneCommandeFournisseur();

        if (request.getParameter("fournisseur") != null) {
            if (!request.getParameter("fournisseur").isEmpty()) {
                Fournisseur fourn = fournisseurFacade.find(Integer.parseInt(request.getParameter("fournisseur")));
                ligneSortie.setFournisseur(fourn);
            }
        }
        if (request.getParameter("montant_net") != null) {
            if (!request.getParameter("montant_net").isEmpty()) {
                ligneSortie.setMontantNet(Double.parseDouble(request.getParameter("montant_net")));
            }
        } else {
            ligneSortie.setMontantNet(ligne_cm.getPrixTotal());
        }
        if (request.getParameter("allencaissement") != null && request.getParameter("avance") == null && request.getParameter("commentaire") == null) {
            ligneSortie.setEtat(1);
            ligneSortie.setMontantRestant(0.0);
            avance = ligneSortie.getMontantNet();
            ligneSortie.setCommentaire("Decaissement pour le bon de commande N° " + ligne_cm.getId());
        } else {
            if (!request.getParameter("commentaire").isEmpty()) {
                ligneSortie.setCommentaire(request.getParameter("commentaire"));
            }
            if (!request.getParameter("avance").isEmpty()) {
                if (Double.parseDouble(request.getParameter("avance")) != 0.0) {
                    avance = Double.parseDouble(request.getParameter("avance"));
                    ligneSortie.setMontantRestant(ligneSortie.getMontantNet() - avance);
                    if (ligneSortie.getMontantNet() == avance) {
                        ligneSortie.setEtat(1);
                    }
                } else {
                    ligneSortie.setMontantRestant(ligneSortie.getMontantNet());
                }

            }
        }

        if (!error) {
            if (caisse.getMontantRestant() >= avance) {

                if (ligneSortie.getMontantRestant() <= ligneSortie.getMontantNet()) {
                    if (!isupdate) {

                        ligneSortie.setDateUpdate(date_du_jour.dateJourUniqueDate());
                        ligneSortie = ligneSortieFacade.creer(ligneSortie);
                        if (avance != 0.0) {
                            sortie.setAvance(avance);
                            sortie.setCaisse(caisse);
                            sortie.setDate(ligneSortie.getDateCreate());
                            if (!ligneSortie.getCommentaire().isEmpty()) {
                                sortie.setCommentaire(ligneSortie.getCommentaire());
                            }
                            sortie.setOperateur(utilisateur);
                            sortie.setLigneSortie(ligneSortie);
                            sortie.setMontant(ligneSortie.getMontantNet());
                            sortie.setMontantRestant(ligneSortie.getMontantRestant());
                            SourceMouvementCaisse source = sourceMouvementCaisseFacade.find(3);
                            sortie.setSource(source);
                            sortie.setSoldeCaisse(caisse.getMontantRestant() - sortie.getAvance());
                            sortie = sortieCaisseFacade.creer(sortie);
                            caisse.setMontantRestant(caisse.getMontantRestant() - sortie.getAvance());
                            caisseFacade.MisAjour(caisse);

                            Message message = new Message();
                            message.setType("success");
                            message.setTitle("FACTURATION");
                            message.setNotification("votre bon de commande a été correctement décaissé");
                            request.setAttribute("message", message);
                        }

                    }

//                else {
//                    ligneAccountFacade.MisAjour(ligneAccount);
//                }
                } else {
                    Message message = new Message();
                    message.setType("warning");
                    message.setTitle("Attention!!!");
                    message.setNotification("l'avance ne peut être plus grande que la somme à payer");
                    request.setAttribute("message", message);
                }

            } else {
                Message message = new Message();
                message.setType("warning");
                message.setTitle("IMPOSSIBLE !!!");
                message.setNotification("La caisse ne dispose pas assez de fond pour effectuer cette operation");
                request.setAttribute("message", message);
            }
        }
    }
    // ajouter une avance du règelement de credit

    private void addAccount(HttpServletRequest request, Tusers utilisateur) throws Exception {
        LigneAccount ligneAccount = new LigneAccount();
        Caisse caisse = caisseFacade.findCaisseDuJour(utilisateur.getSociete().getId());
        if (request.getSession().getAttribute("ligneEncour") != null) {
            ligneAccount = (LigneAccount) request.getSession().getAttribute("ligneEncour");
            Account account = new Account();
            boolean error = false;
            if (Integer.parseInt(request.getParameter("isNew")) == 0) {
                account = new Account();
                account.setLigneAccount(ligneAccount);
            } else {
                account = accountFacade.find(Integer.parseInt(request.getParameter("account")));
            }
            Double avance = Double.parseDouble(request.getParameter("avance"));
            account.setAvance(avance);
            account.setOperateur(utilisateur);
            if (Integer.parseInt(request.getParameter("isNew")) == 0) {

                account.setDate(date_du_jour.dateJourUniqueDate());
                account.setMontantInit(ligneAccount.getMontantRestant());
                account.setMontantRestant(account.getMontantInit() - avance);

                if (account.getMontantInit() >= avance) {
                    ligneAccount.setMontantRestant(account.getMontantRestant());
                    if (ligneAccount.getMontantRestant() == 0) {
                        ligneAccount.setEtat(1);
                    }
                    ligneAccount.setDateUpdate(account.getDate());
                    account.setCaisse(caisse);
                    SourceMouvementCaisse source = sourceMouvementCaisseFacade.find(3);
                    account.setSource(source);
                    account.setSoldeCaisse(caisse.getMontantRestant() + account.getAvance());
                    accountFacade.creer(account);
                    ligneAccount.setAccountList(ligneAccountFacade.find(ligneAccount.getId()).getAccountList());
                    ligneAccount = ligneAccountFacade.MisAjour(ligneAccount);
                    caisse.setMontantRestant(caisse.getMontantRestant() + account.getAvance());
                    caisseFacade.MisAjour(caisse);
                } else {
                    Message message = new Message();
                    message.setType("warning");
                    message.setTitle("Attention!!!");
                    message.setNotification("l'avance ne peut être plus grande que la somme à payer");
                    request.setAttribute("message", message);
                }

            } else {
                account.setMontantRestant(account.getMontantInit() - avance);
                ligneAccount.setMontantRestant(account.getMontantRestant());
                if (ligneAccount.getMontantRestant() == 0) {
                    ligneAccount.setEtat(1);
                }
                if (account.getMontantInit() >= avance) {
                    accountFacade.MisAjour(account);
                    int index = ligneAccount.getAccountList().indexOf(account);
                    ligneAccount = ligneAccountFacade.MisAjour(ligneAccount);

                } else {
                    Message message = new Message();
                    message.setType("warning");
                    message.setTitle("Attention!!!");
                    message.setNotification("l'avance ne peut être plus grande que la somme à payer");
                    request.setAttribute("message", message);
                }

            }
            if (ligneAccount.getMontantRestant() == 0) {
                request.getSession().removeAttribute("ligneEncour");

            } else {
                request.getSession().setAttribute("ligneEncour", ligneAccount);
            }
            request.setAttribute("ligne", ligneAccount);

        }
    }

    // ajouter une avance au règlement du bon de commande
    private void addSortieBon(HttpServletRequest request, Tusers utilisateur) throws Exception {
        LigneSortie ligneSortie = new LigneSortie();
        Caisse caisse = caisseFacade.findCaisseDuJour(utilisateur.getSociete().getId());
        if (request.getSession().getAttribute("ligneEncour") != null) {
            ligneSortie = (LigneSortie) request.getSession().getAttribute("ligneEncour");
            SortieCaisse sortie = new SortieCaisse();
            boolean error = false;
            if (Integer.parseInt(request.getParameter("isNew")) == 4) {
                sortie = new SortieCaisse();
                sortie.setLigneSortie(ligneSortie);
            } else {
                sortie = sortieCaisseFacade.find(Integer.parseInt(request.getParameter("account")));
            }
            Double avance = Double.parseDouble(request.getParameter("avance"));
            sortie.setAvance(avance);
            sortie.setOperateur(utilisateur);
            if (caisse.getMontantRestant() >= sortie.getAvance()) {
                if (Integer.parseInt(request.getParameter("isNew")) == 4) {

                    sortie.setDate(date_du_jour.dateJourUniqueDate());
                    sortie.setMontant(ligneSortie.getMontantRestant());

                    if (sortie.getMontant() >= avance) {
                        sortie.setMontantRestant(sortie.getMontant() - avance);
                        ligneSortie.setMontantRestant(sortie.getMontantRestant());
                        if (ligneSortie.getMontantRestant() == 0) {
                            ligneSortie.setEtat(1);
                        }
                        ligneSortie.setDateUpdate(sortie.getDate());
                        sortie.setCaisse(caisse);
                        SourceMouvementCaisse source = sourceMouvementCaisseFacade.find(3);
                        sortie.setSource(source);
                        sortie.setCommentaire(request.getParameter("commentaire"));
                        sortie.setSoldeCaisse(caisse.getMontantRestant() - sortie.getAvance());
                        sortieCaisseFacade.creer(sortie);
                        ligneSortie.setSortietList(ligneSortieFacade.find(ligneSortie.getId()).getSortietList());
                        ligneSortie = ligneSortieFacade.MisAJour(ligneSortie);
                        caisse.setMontantRestant(caisse.getMontantRestant() - sortie.getAvance());
                        caisseFacade.MisAjour(caisse);
                    } else {
                        Message message = new Message();
                        message.setType("warning");
                        message.setTitle("Attention!!!");
                        message.setNotification("l'avance ne peut être plus grande que la somme à payer");
                        request.setAttribute("message", message);
                    }

                } else {
                    sortie.setMontantRestant(sortie.getMontant() - avance);
                    ligneSortie.setMontantRestant(sortie.getMontantRestant());
                    if (ligneSortie.getMontantRestant() == 0) {
                        ligneSortie.setEtat(1);
                    }
                    if (sortie.getMontant() >= avance) {
                        sortieCaisseFacade.MisAJour(sortie);
                        int index = ligneSortie.getSortietList().indexOf(sortie);
                        ligneSortie = ligneSortieFacade.MisAJour(ligneSortie);

                    } else {
                        Message message = new Message();
                        message.setType("warning");
                        message.setTitle("Attention!!!");
                        message.setNotification("l'avance ne peut être plus grande que la somme à payer");
                        request.setAttribute("message", message);
                    }

                }
            } else {
                Message message = new Message();
                message.setType("warning");
                message.setTitle("Attention!!!");
                message.setNotification("La caisse ne peut supporter cette operation");
                request.setAttribute("message", message);
            }
            if (ligneSortie.getMontantRestant() == 0) {
                request.getSession().removeAttribute("ligneEncour");

            } else {
                request.getSession().setAttribute("ligneEncour", ligneSortie);
            }

        }
        request.setAttribute("ligne", ligneSortie);
    }

    private void historiqueAccount(HttpServletRequest request, Tusers utilisateur) {
        // 
        List<LigneAccount> ligneAccount = null;
        String[] date1 = request.getParameter("interval").split("-");
        Date d1 = date_du_jour.dateConvert(date1[0]);
        Date d2 = date_du_jour.dateConvert(date1[1]);
        int idCl = Integer.parseInt(request.getParameter("client"));
        int etat = Integer.parseInt(request.getParameter("etat"));
        String message = "Dette du " + d1 + " au " + d2;
        if (idCl != -1) {
            Tclients cl = tclientsFacade.find(idCl);
            message += " de " + cl.getNom();
            ligneAccount = ligneAccountFacade.historiqueAccount(d1, d2, 1, idCl, etat);
        } else {
            message += " de la societé";
            idCl = utilisateur.getSociete().getId();
            ligneAccount = ligneAccountFacade.historiqueAccount(d1, d2, 0, idCl, etat);
        }

        request.setAttribute("messageTable", message);
        request.getSession().setAttribute("allAccount", ligneAccount);
    }

    private Caisse openCaisse(HttpServletRequest request, Tusers utilisateur) throws Exception {
        List<Caisse> caisses = utilisateur.getSociete().getCaisseList();
        Caisse caisseJ = caisseFacade.findCaisseDuJour(utilisateur.getSociete().getId());
        Caisse newCaisse = new Caisse();
        if (caisseJ.getId() == 0) {
            for (Caisse caisse : caisses) {
                if (caisse.getEtatCaisse() == 1) {
                    caisse.setEtatCaisse(0);
                    caisse.setMontantVerser(caisse.getMontantRestant());
                    caisse.setEcart(0.0);
                    caisse.setCommentaire("Fermeture automatique de la caisse");
                    caisse.setDateFermeture(date_du_jour.dateJourUniqueDate());
                    caisseFacade.MisAjour(caisse);
                }
            }
            if (!caisses.isEmpty()) {
                if (!caisseIsOpen(request, utilisateur)) {
                    Caisse caisselast = caisses.get(caisses.size() - 1);
                    newCaisse.setDate(date_du_jour.dateJourUniqueDate());
                    newCaisse.setEtatCaisse(1);
                    newCaisse.setSociete(utilisateur.getSociete());
                    newCaisse.setMontant(caisselast.getMontantVerser());
                    newCaisse.setMontantRestant(newCaisse.getMontant());
                    newCaisse = caisseFacade.creer(newCaisse);
                }
            }
        } else {
            Message message = new Message();
            message.setType("warning");
            message.setTitle("Attention Caisse Fermé!!!");
            message.setNotification("La caisse du jour a déjà été fermée Impossible de l'ouvrir à nouveau");
            request.setAttribute("message", message);
        }

        return newCaisse;
    }

    private Caisse closeCaisse(HttpServletRequest request, Tusers utilisateur) throws RollbackFailureException, Exception {
        Caisse caisse = caisseFacade.find(Integer.parseInt(request.getParameter("id")));
        caisse.setEtatCaisse(0);
        caisse.setDateFermeture(date_du_jour.dateJourUniqueDate());
        caisse.setMontantVerser(Double.parseDouble(request.getParameter("montant_verser")));
        caisse.setEcart(caisse.getMontantRestant() - caisse.getMontantVerser());
        caisse.setCommentaire(request.getParameter("commentaire"));
        caisse = caisseFacade.MisAjour(caisse);
        return caisse;
    }

    private boolean caisseIsOpen(HttpServletRequest request, Tusers utilisateur) {
        boolean open = false;
        Caisse caisse = caisseFacade.findCaisseDuJour(utilisateur.getSociete().getId());
        if (caisse.getId() != 0) {
            if (caisse.getEtatCaisse() == 1) {
                open = true;
            } else {
                Message message = new Message();
                message.setType("warning");
                message.setTitle("Attention Caisse Fermé!!!");
                message.setNotification("Vous ne pouvez effectuer de mouvement dans la caisse ");
                request.setAttribute("message", message);
            }
        } else {
            Message message = new Message();
            message.setType("warning");
            message.setTitle("Attention !!!");
            message.setNotification("La caisse n'a pas encore été ouverte");
            request.setAttribute("message", message);
        }

        return open;
    }

    private Caisse creerCaisse(HttpServletRequest request, Tusers utilisateur) throws Exception {
        Caisse caisse = new Caisse();
        caisse.setDate(date_du_jour.dateJourUniqueDate());
        caisse.setMontant(Double.parseDouble(request.getParameter("montant")));
        caisse.setSociete(utilisateur.getSociete());
        caisse.setMontantRestant(caisse.getMontant());
        caisse.setEtatCaisse(1);
        caisse = caisseFacade.creer(caisse);
        return caisse;
    }

    private void addSortie(HttpServletRequest request, Tusers utilisateur) throws RollbackFailureException, Exception {
        SortieCaisse sorti = new SortieCaisse();
        Caisse caisse = caisseFacade.findCaisseDuJour(utilisateur.getSociete().getId());
        if (caisse.getEtatCaisse() == 1) {
            sorti.setCaisse(caisse);
            if (request.getParameter("commentaire") != null) {
                if (!request.getParameter("commentaire").isEmpty()) {
                    sorti.setCommentaire(request.getParameter("commentaire"));
                }
            }
            if (request.getParameter("facture") != null) {
                if (!request.getParameter("facture").isEmpty()) {
                    LigneCommandeFournisseur ligneCmd = ligneCommandeFournisseurFacade.find(Integer.parseInt(request.getParameter("facture")));
                    sorti.setFacture(ligneCmd);
                }
            }

            sorti.setDate(date_du_jour.dateJourUniqueDate());
            sorti.setMontant(Double.parseDouble(request.getParameter("montant")));
            sorti.setOperateur(utilisateur);
            if (caisse.getMontantRestant() >= sorti.getMontant()) {
                SourceMouvementCaisse source = sourceMouvementCaisseFacade.find(Integer.parseInt(request.getParameter("source")));
                sorti.setSource(source);
                sorti.setSoldeCaisse(caisse.getMontantRestant() - sorti.getMontant());
                sortieCaisseFacade.creer(sorti);
                caisse.setMontantRestant(caisse.getMontantRestant() - sorti.getMontant());
                caisseFacade.MisAjour(caisse);
            } else {
                Message message = new Message();
                message.setType("warning");
                message.setTitle("Attention Caisse Vide!!!");
                message.setNotification("Vous ne disposez pas assez de sous pour effectuer cette sortie");
                request.setAttribute("message", message);
            }

        } else {
            Message message = new Message();
            message.setType("warning");
            message.setTitle("Attention Caisse Fermé!!!");
            message.setNotification("Vous ne pouvez effectuer de mouvement dans la caisse ");
            request.setAttribute("message", message);
        }

    }

    private void addEntree(HttpServletRequest request, Tusers utilisateur) throws Exception {
        Account entree = new Account();
        Caisse caisse = caisseFacade.findCaisseDuJour(utilisateur.getSociete().getId());
        if (caisse.getEtatCaisse() == 1) {
            entree.setCaisse(caisse);
            if (request.getParameter("commentaire") != null) {
                if (!request.getParameter("commentaire").isEmpty()) {
                    entree.setCommentaire(request.getParameter("commentaire"));
                }
            }
            if (request.getParameter("source") != null) {
                if (!request.getParameter("source").isEmpty()) {
                    SourceMouvementCaisse source = sourceMouvementCaisseFacade.find(Integer.parseInt(request.getParameter("source")));
                    entree.setSource(source);
                }
            }

            entree.setDate(date_du_jour.dateJourUniqueDate());
            entree.setAvance(Double.parseDouble(request.getParameter("montant")));
            entree.setMontantInit(entree.getAvance());
            entree.setMontantRestant(0.0);
            entree.setOperateur(utilisateur);
            entree.setSoldeCaisse(caisse.getMontantRestant() + entree.getAvance());
            accountFacade.creer(entree);
            caisse.setMontantRestant(caisse.getMontantRestant() + entree.getAvance());
            caisseFacade.MisAjour(caisse);
        } else {
            Message message = new Message();
            message.setType("warning");
            message.setTitle("Attention Caisse Fermé!!!");
            message.setNotification("Vous ne pouvez effectuer de mouvement dans la caisse ");
            request.setAttribute("message", message);
        }
    }

    private void supprimerVente(HttpServletRequest request, Tusers utilisateur) throws Exception {

        if (caisseIsOpen(request, utilisateur)) {
            LigneAccount ligne = ligneAccountFacade.find(Integer.parseInt(request.getParameter("id")));
            Caisse caisseJour = caisseFacade.findCaisseDuJour(utilisateur.getSociete().getId());
            SortieCaisse sortie = new SortieCaisse();
            Tlignecommande ligneCmd = ligne.getLigneCommande();
            Double avance = ligne.getMontantNet() - ligne.getMontantRestant();
            if (avance != 0.0) {
                sortie.setAvance(avance);
                sortie.setMontant(avance);
                sortie.setMontantRestant(0);
                sortie.setCaisse(caisseJour);
                Double resteCaisse = caisseJour.getMontantRestant() - sortie.getAvance();
                if (resteCaisse >= 0) {
                    sortie.setSoldeCaisse(caisseJour.getMontantRestant() - sortie.getAvance());
                    sortie.setSource(sourceMouvementCaisseFacade.find(3));
                    if (ligneCmd != null) {
                        sortie.setCommentaire("Annulation de la facture N°" + ligneCmd.getId() + " Concernant le client " + ligneCmd.getClient().getNom());
                    } else {
                        sortie.setCommentaire("Annulation de la dette  Concernant le client " + ligne.getClient().getNom());
                    }

                    sortie.setOperateur(utilisateur);
                    sortie.setDate(date_du_jour.dateJourUniqueDate());
                    sortieCaisseFacade.creer(sortie);
                    caisseJour.setMontantRestant(caisseJour.getMontantRestant() - sortie.getMontant());
                    caisseFacade.MisAjour(caisseJour);
                    if (ligneCmd != null) {
                        ligneCmd.setEtatc(tetatcFacade.findByCode(200));
                        tlignecommandeFacade.MisAJour(ligneCmd);
                    }

                    ligneAccountFacade.Supprimer(ligne.getId());
                    Message message = new Message();
                    message.setType("success");
                    message.setTitle("OK !!!");
                    if (ligneCmd != null) {
                        message.setNotification("Vous venez d'effectuer avec succès une sortie de caisse de " + avance + " FCFA pour remboursement de la facture N°" + ligneCmd.getId() + ".Vous pouvez supprimer cette facture pour un retour en stock de vos produits concernant cette commande");
                    } else {
                        message.setNotification("Vous venez d'effectuer avec succès une sortie de caisse de " + avance + " FCFA pour remboursement .");
                    }

                    request.setAttribute("message", message);
                } else {
                    Message message = new Message();
                    message.setType("warning");
                    message.setTitle("Attention !!!");
                    message.setNotification("Impossible d'effectuer cette operation car la caisse manque de fond  ");
                    request.setAttribute("message", message);
                }

            }
        } else {
            Message message = new Message();
            message.setType("warning");
            message.setTitle("Attention Caisse Fermé!!!");
            message.setNotification("Vous ne pouvez effectuer de mouvement dans la caisse ");
            request.setAttribute("message", message);
        }

    }
}
