/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.FullControleurs;

import com.eh2s.ocm.Entity.AffectTournerUser;
import com.eh2s.ocm.Entity.CommandeEmballage;
import com.eh2s.ocm.Entity.CompteEmballage;
import com.eh2s.ocm.Entity.Emballage;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.LigneAccount;
import com.eh2s.ocm.Entity.Notification;
import com.eh2s.ocm.Entity.Services.Message;
import com.eh2s.ocm.Entity.Services.ServicesInitialize;
import com.eh2s.ocm.Entity.Sessions.AffectTournerUserFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.CommandeEmballageFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.CompteEmballageFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.EmballageFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.LigneAccountFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.LigneCommandeFournisseurFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.NotificationFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.SocieteFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TarticlesFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TcategorieFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TclientsFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TcommandesFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TetatcFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TlignecommandeFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TournerFacadeLocal;
import com.eh2s.ocm.Entity.Societe;
import com.eh2s.ocm.Entity.Tarticles;
import com.eh2s.ocm.Entity.Tcategorie;
import com.eh2s.ocm.Entity.Tclients;
import com.eh2s.ocm.Entity.Tcommandes;
import com.eh2s.ocm.Entity.Tetatc;
import com.eh2s.ocm.Entity.Tlignecommande;
import com.eh2s.ocm.Entity.Tourner;
import com.eh2s.ocm.Entity.Tusers;
import com.eh2s.ocm.UtilityFonctions.EnvoiEmail;
import com.eh2s.ocm.UtilityFonctions.date_du_jour;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONException;

/**
 *
 * @author messi
 */
@WebServlet(name = "gestionCommandes", urlPatterns = {"/gestionCommandes"})

public class gestionCommandes extends HttpServlet {

    @EJB
    private LigneAccountFacadeLocal ligneAccountFacade;

    @EJB
    private LigneCommandeFournisseurFacadeLocal ligneCommandeFournisseurFacade;

    @EJB
    private CompteEmballageFacadeLocal compteEmballageFacade;

    @EJB
    private EmballageFacadeLocal emballageFacade;

    @EJB
    private CommandeEmballageFacadeLocal commandeEmballageFacade;

    @EJB
    private AffectTournerUserFacadeLocal affectTournerUserFacade;

    @EJB
    private TournerFacadeLocal tournerFacade;

    @EJB
    private SocieteFacadeLocal societeFacade;

    @EJB
    private TlignecommandeFacadeLocal tlignecommandeFacade;

    @EJB
    private ServicesInitialize servicesInitializeImple;

    @EJB
    private TcommandesFacadeLocal tcommandesFacade;

    @EJB
    private TcategorieFacadeLocal tcategorieFacade;

    @EJB
    private TetatcFacadeLocal tetatcFacade;

    @EJB
    private TarticlesFacadeLocal tarticlesFacade;

    @EJB
    private NotificationFacadeLocal notificationFacade;

    @EJB
    private TclientsFacadeLocal tclientsFacade;

    private static final long serialVersionUID = 1L;

    private static boolean firs = true;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        if (session.getAttribute("isconnect") != null) {
            Societe societe = (Societe) session.getAttribute("societe");
            Tusers t = (Tusers) session.getAttribute("utilisateur");
            String actions = request.getParameter("action");
            request.setAttribute("q", request.getParameter("q"));
            if (firs) {
                try {
                    //   MisAJourMarge(societe);
                } catch (Exception ex) {
                    Logger.getLogger(gestionCommandes.class.getName()).log(Level.SEVERE, null, ex);
                }
                // CopyArticle();
                //UpDateLG(societe);
                firs = false;
            }

            if (actions.equals("model")) {
                String model = request.getParameter("model");
                switch (model) {
                    case "init":
                        request.setAttribute("achats", ligneCommandeFournisseurFacade.findBonCommandeByPeriode(date_du_jour.dateJourUniqueDate(), date_du_jour.dateJourUniqueDate(), societe.getId()));
                        request.setAttribute("commandes", tlignecommandeFacade.findTcommandesByPeriode(date_du_jour.dateJourUniqueDate(), date_du_jour.dateJourUniqueDate(), societe.getImmatriculation()));
                        if (session.getAttribute("utilisateur") != null) {
                            servicesInitializeImple.initialize(request, (Tusers) session.getAttribute("utilisateur"));
                        } else if (session.getAttribute("client") != null) {
                            servicesInitializeImple.initiationClients(request, (Tclients) session.getAttribute("client"));
                        }
                        request.setAttribute("q", request.getParameter("q"));
                        request.getServletContext().getRequestDispatcher("/WEB-INF/main.jsp").forward(request, response);
                        break;
                    case "PasserCommandes":
                        switch (Integer.parseInt(request.getParameter("isNew"))) {
                            case 0:
                                try {
                                    getCommandeList(request, t);
                                } catch (Exception ex) {
                                    Logger.getLogger(gestionCommandes.class.getName()).log(Level.SEVERE, null, ex);
                                    request.setAttribute("erreur", ex.getMessage());
                                    ex.getCause();

                                }
                                break;
                            case 1:
                                List<Tcommandes> Commandes3 = (List<Tcommandes>) request.getSession().getAttribute("listCommandes");
                                Tlignecommande lg = (Tlignecommande) request.getSession().getAttribute("lignecommande");

                                try {
                                    saveCommandes(Commandes3, lg, request);
                                } catch (Exception ex) {
                                    request.setAttribute("erreur", ex.getMessage());
                                }
                                break;
                            case 2:
                                try {
                                    EditCommandeModel(request);
                                } catch (Exception ex) {
                                    request.setAttribute("erreur", ex.getMessage());
                                }
                                break;
                            case 3:
                                try {
                                    request.getSession().removeAttribute("listCommandes1");
                                    request.setAttribute("commandes", tlignecommandeFacade.findTlignecommande(Integer.parseInt(request.getParameter("id"))).getTcommandesList());
                                } catch (NumberFormatException ex) {
                                    request.setAttribute("erreur", ex.getMessage());
                                }
                                request.setAttribute("traiter", "pourTraitement");
                                break;
                            case 4:
                                List<Tcommandes> Commandes2 = (List<Tcommandes>) request.getSession().getAttribute("listCommandes");

                                try {
                                    AnnulerCommandes(Commandes2, request);
                                } catch (Exception ex) {
                                    request.setAttribute("erreur", ex.getMessage());
                                }
                                break;
                            case 5:
                                List<Tcommandes> Commandes1 = (List<Tcommandes>) request.getSession().getAttribute("listCommandes");

                                try {
                                    editionrCommande(Commandes1, request, societe);

                                } catch (Exception ex) {
                                    request.setAttribute("erreur", ex.getMessage());
                                }
                                break;
                            case 6:
                                try {
                                    String[] id = request.getParameterValues("id");

                                    for (String str : id) {
                                        SupprimerCommandeNew(Integer.parseInt(str), request, societe);

                                    }
                                    List<Tcommandes> Commandes = (List<Tcommandes>) request.getSession().getAttribute("listCommandes");
                                    if (Commandes.isEmpty()) {
                                        request.getSession().removeAttribute("njr");
                                        request.getSession().removeAttribute("datec");
                                        request.getSession().removeAttribute("cmdclient");
                                    }
                                    request.setAttribute("commandes", Commandes);
                                    request.getSession().setAttribute("listCommandes", Commandes);
                                } catch (NumberFormatException ex) {
                                    request.setAttribute("erreur", ex.getMessage());
                                }
                                break;
                            case 7:
                                List<Tcommandes> listCommandes = (List<Tcommandes>) request.getSession().getAttribute("listCommandes");
                                request.setAttribute("commande", getCommande(listCommandes, Integer.parseInt(request.getParameter("id"))));
                                request.setAttribute("commandes", listCommandes);
                                break;
                            case 8:
                                if (request.getSession().getAttribute("tourner") != null || !request.getParameter("numc").isEmpty()) {
                                    request.getSession().setAttribute("tourner", tournerFacade.findByNum(request.getParameter("numc")));
                                }
                                break;
                            case 9:
                                //request.getSession().setAttribute("tourner", tournerFacade.findByNum(request.getParameter("numc")));
                                break;
                            case 10:
                                List<Tcommandes> Commandess = (List<Tcommandes>) request.getSession().getAttribute("listCommandes");
                                Tlignecommande lg1 = (Tlignecommande) request.getSession().getAttribute("lignecommande");

                                try {
                                    saveVentes(Commandess, lg1, request);
                                } catch (Exception ex) {
                                    request.setAttribute("erreur", ex.getMessage());
                                }
                                break;
                            case 11:

                                try {
                                    String[] id = request.getParameterValues("id");

                                    for (String str : id) {
                                        SupprimerVente(Integer.parseInt(str), request, societe);

                                    }
                                    Message message = new Message();
                                    message.setType("infos");
                                    message.setTitle("INFOS !!!");
                                    message.setMessage("Votre commande a été correctement supprimer");
                                    message.setNotification("Votre commande a été correctement supprimer");
                                    request.setAttribute("message", message);
                                    request.setAttribute("commandes", tlignecommandeFacade.findAllBySociete(societe.getId()));
                                } catch (Exception ex) {
                                    request.setAttribute("erreur", ex.getMessage());
                                }
                                break;
                            case 12: {
                                try {
                                    ChangeInfosCommandeClient(request);
                                } catch (Exception ex) {
                                    Logger.getLogger(gestionCommandes.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            break;
                            default:
                                try {
                                    String[] id = request.getParameterValues("id");
                                    Tcommandes cc = tcommandesFacade.findTcommandes(Integer.parseInt(id[0]));
                                    Tlignecommande l1 = tlignecommandeFacade.findTlignecommande(cc.getLigne().getId());
                                    if (l1.getEtatc().getCode() != 201 && l1.getEtatc().getCode() != 200) {
                                        if (l1.getTcommandesList().size() == id.length) {
                                            tlignecommandeFacade.Supprimer(l1.getId());
                                        } else {
                                            for (String str : id) {
                                                Tcommandes c = tcommandesFacade.findTcommandes(Integer.parseInt(str));
                                                l1.setQtotal(l1.getQtotal() - c.getQuantite());
                                                tcommandesFacade.Supprimer(Integer.parseInt(str));
                                                tlignecommandeFacade.MisAJour(l1);
                                            }
                                        }
                                    } else {
                                        if (l1.getTcommandesList().size() == id.length) {
                                            for (String str : id) {
                                                Tcommandes c1 = tcommandesFacade.findTcommandes(Integer.parseInt(str));
                                                Tarticles ar = tarticlesFacade.findTarticles(c1.getArticle().getId());
                                                ar.setStock(ar.getStock() + c1.getQuantite());
                                                tarticlesFacade.MiSaJour(ar);
                                            }
                                            tlignecommandeFacade.Supprimer(l1.getId());
                                        } else {
                                            for (String str : id) {
                                                Tcommandes c1 = tcommandesFacade.findTcommandes(Integer.parseInt(str));
                                                Tarticles ar = tarticlesFacade.findTarticles(c1.getArticle().getId());
                                                ar.setStock(ar.getStock() + c1.getQuantite());
                                                tarticlesFacade.MiSaJour(ar);
                                                tcommandesFacade.Supprimer(Integer.parseInt(str));
                                                l1.setQtotal(l1.getQtotal() - c1.getQuantite());
                                                tlignecommandeFacade.MisAJour(l1);
                                            }
                                        }
                                    }

                                } catch (Exception ex) {
                                    request.setAttribute("erreur", ex.getMessage());
                                }
                                break;
                        }
                        if (session.getAttribute("utilisateur") != null) {
                            servicesInitializeImple.initialize(request, (Tusers) session.getAttribute("utilisateur"));
                        } else if (session.getAttribute("client") != null) {
                            servicesInitializeImple.initiationClients(request, (Tclients) session.getAttribute("client"));
                        }
                        request.setAttribute("q", request.getParameter("q"));
                        request.getServletContext().getRequestDispatcher("/WEB-INF/main.jsp").forward(request, response);
                        break;
                    case "PropositionCommande":
                        switch (Integer.parseInt(request.getParameter("isNew"))) {
                            case 0:
                                try {
                                    PropositionCommandemodel(request);
                                } catch (Exception ex) {
                                    request.setAttribute("erreur", ex.getMessage());
                                }
                                break;
                            case 1:
                                try {
                                    ValidationPropositionCommande(request);
                                } catch (Exception ex) {
                                    request.setAttribute("erreur", ex.getMessage());
                                }

                                break;
                            case 2:
                                try {
                                    ValidationCommandes(request);
                                } catch (Exception ex) {
                                    request.setAttribute("erreur", ex.getMessage());
                                }
                                break;
                            case 3:
                                Tcommandes r = tcommandesFacade.findTcommandes(Integer.parseInt(request.getParameter("idC")));
                                request.setAttribute("commande", r);
                                request.setAttribute("commandes", tlignecommandeFacade.findTlignecommande(Integer.parseInt(request.getParameter("id"))).getTcommandesList());
                                break;
                            case 4:
                                try {
                                    String[] id = request.getParameterValues("ida");
                                    Tcommandes cc = tcommandesFacade.findTcommandes(Integer.parseInt(id[0]));
                                    Tlignecommande l1 = cc.getLigne();
                                    boolean eta = false;
                                    if (l1.getEtatc().getCode() != 201 && l1.getEtatc().getCode() != 200) {
                                        if (l1.getTcommandesList().size() == id.length) {
                                            tlignecommandeFacade.Supprimer(l1.getId());
                                            eta = true;
                                        } else {
                                            for (String str : id) {
                                                Tcommandes c1 = tcommandesFacade.findTcommandes(Integer.parseInt(str));
                                                if (c1.getArticle().getCode().endsWith("C") || c1.getArticle().getCode().endsWith("CP")) {
                                                    l1.setCassier(l1.getCassier() - c1.getQuantite());
                                                }
                                            }
                                            l1.setQtotal(l1.getQtotal() - cc.getQuantite());
                                            tlignecommandeFacade.MisAJour(l1);
                                            for (String str : id) {
                                                Tcommandes c1 = tcommandesFacade.findTcommandes(Integer.parseInt(str));
                                                tcommandesFacade.Supprimer(c1.getId());
                                            }
                                        }
                                    } else {
                                        if (l1.getTcommandesList().size() == id.length) {

                                            for (String str : id) {
                                                Tcommandes c1 = tcommandesFacade.findTcommandes(Integer.parseInt(str));
                                                Tarticles ar = tarticlesFacade.findTarticles(c1.getArticle().getId());
                                                ar.setStock(ar.getStock() + c1.getQuantite());
                                                tarticlesFacade.MiSaJour(ar);
                                            }
                                            tlignecommandeFacade.Supprimer(l1.getId());
                                            eta = true;
                                        } else {
                                            for (String str : id) {
                                                Tcommandes c1 = tcommandesFacade.findTcommandes(Integer.parseInt(str));
                                                if (!c1.getArticle().getCode().endsWith("C") || !c1.getArticle().getCode().endsWith("CP")) {
                                                    l1.setCassier(l1.getCassier() - c1.getQuantite());
                                                }
                                                if (societe.getGestmarge() == 1) {
                                                    l1.setMargeClient(l1.getMargeClient() - c1.getMargeClient());
                                                    l1.setMargeFournisseur(l1.getMargeFournisseur() - l1.getMargeFournisseur());
                                                }
                                            }
                                            l1.setQtotal(l1.getQtotal() - cc.getQuantite());
                                            tlignecommandeFacade.MisAJour(l1);
                                            for (String str : id) {
                                                Tcommandes c1 = tcommandesFacade.findTcommandes(Integer.parseInt(str));
                                                Tarticles ar = tarticlesFacade.findTarticles(c1.getArticle().getId());
                                                ar.setStock(ar.getStock() + c1.getQuantite());
                                                tarticlesFacade.MiSaJour(ar);
                                                tcommandesFacade.Supprimer(c1.getId());
                                            }
                                        }
                                    }
                                    if (!eta) {
                                        request.setAttribute("commandes", tlignecommandeFacade.findTlignecommande(l1.getId()).getTcommandesList());
                                    }

                                } catch (Exception ex) {
                                    request.setAttribute("erreur", ex.getMessage());
                                }
                                break;
                            case 5:
                                try {
                                    CloturationCommandes(request);
                                } catch (Exception ex) {
                                    request.setAttribute("erreur", ex.getMessage());
                                }

                                break;
                            case 6:
                                try {
                                    EnregistrePropositionCommande(request);
                                } catch (Exception ex) {
                                    request.setAttribute("erreur", ex.getMessage());
                                }

                                break;
                            case 7:
                                try {
                                    AjouterCommandeList(request);
                                } catch (Exception ex) {
                                    request.setAttribute("erreur", ex.getMessage());
                                }
                                break;
                            case 8:
                                try {
                                    CloturationAllCommandes(request, societe);

                                } catch (Exception ex) {
                                    request.setAttribute("erreur", ex.getMessage());
                                }

                                break;
                            default:
                                break;

                        }
                        if (session.getAttribute("utilisateur") != null) {
                            servicesInitializeImple.initialize(request, (Tusers) session.getAttribute("utilisateur"));

                        } else if (session.getAttribute("client") != null) {
                            servicesInitializeImple.initiationClients(request, (Tclients) session.getAttribute("client"));
                        }
                        request.setAttribute("q", request.getParameter("q"));
                        request.getServletContext().getRequestDispatcher("/WEB-INF/main.jsp").forward(request, response);
                        break;
                    case "HistoriqueCommande":
                        switch (Integer.parseInt(request.getParameter("isNew"))) {
                            case 0:
                                try {
                                    request.setAttribute("commandes", HistoriqueCommande(request, societe));
                                } catch (Exception ex) {
                                    Logger.getLogger(gestionCommandes.class.getName()).log(Level.SEVERE, null, ex);

                                    request.setAttribute("erreur", ex.getMessage());
                                }
                                break;
                            case 1:
                                try {
                                    request.setAttribute("commandes", HistoriqueCommandeByEtat(request, societe));
                                } catch (Exception ex) {
                                    Logger.getLogger(gestionCommandes.class.getName()).log(Level.SEVERE, null, ex);

                                    request.setAttribute("erreur", ex.getMessage());
                                }

                                break;
                            case 2:
                                try {
                                    request.setAttribute("commande", tlignecommandeFacade.findTlignecommande(Integer.parseInt(request.getParameter("id"))).getTcommandesList());
                                    request.setAttribute("commandes", HistoriqueCommande(request, societe));

                                } catch (Exception ex) {
                                    request.setAttribute("erreur", ex.getMessage());
                                }
                                break;
                            case 3:
                                try {
                                    if (request.getParameter("type").equals("0")) {
                                        request.setAttribute("commandes", getCommadesBYCategorie(request));
                                    } else {
                                        request.setAttribute("commandes", getCommadesBYArticle(request));
                                    }

                                } catch (Exception ex) {
                                    request.setAttribute("erreur", ex.getMessage());
                                }
                                break;
                            case 4:
                                getImpressionFeuilleDeRouteJSP(request, response, societe);
                                break;
                            case 5:
                                try {
                                    request.setAttribute("commandes", getCommadesBYClient(request));
                                } catch (Exception ex) {
                                    request.setAttribute("erreur", ex.getMessage());
                                }
                            case 6:
//                                try {
//                                    request.setAttribute("commandes", HistoriqueCommandeByTourner(request, societe));
//                                } catch (Exception ex) {
//                                    request.setAttribute("erreur", ex.getMessage());
//                                }
                                break;
                            case 7:
                                try {
                                    request.setAttribute("commandes", HistoriqueCommandeByTournerByEtatByPeriode(request, societe));
                                } catch (Exception ex) {
                                    Logger.getLogger(gestionCommandes.class.getName()).log(Level.SEVERE, null, ex);
                                    request.setAttribute("erreur", ex.getMessage());
                                }
                                break;
                            default:
                                break;

                        }
                        if (session.getAttribute("utilisateur") != null) {
                            servicesInitializeImple.initialize(request, (Tusers) session.getAttribute("utilisateur"));

                        } else if (session.getAttribute("client") != null) {
                            servicesInitializeImple.initiationClients(request, (Tclients) session.getAttribute("client"));
                        }
                        request.setAttribute("q", request.getParameter("q"));
                        request.getServletContext().getRequestDispatcher("/WEB-INF/main.jsp").forward(request, response);
                        break;
                    case "vente":
                        switch (Integer.parseInt(request.getParameter("isNew"))) {
                            case 0:
                                try {
                                    request.setAttribute("isarticle", "yes");
                                    request.setAttribute("ligneCommande", tlignecommandeFacade.find(Integer.parseInt(request.getParameter("id"))));
                                } catch (Exception ex) {
                                    Logger.getLogger(gestionCommandes.class.getName()).log(Level.SEVERE, null, ex);

                                    request.setAttribute("erreur", ex.getMessage());
                                }
                                break;
                            case 1:
                                try {
                                    // ajouter une commande à la vente
                                    updateVente(request, societe);

                                } catch (Exception ex) {
                                    Logger.getLogger(gestionCommandes.class.getName()).log(Level.SEVERE, null, ex);

                                    request.setAttribute("erreur", ex.getMessage());
                                }
                                request.setAttribute("isarticle", "yes");
                                request.setAttribute("ligneCommande", tlignecommandeFacade.find(Integer.parseInt(request.getParameter("id"))));
                                break;
                            case 2:

                                // supprimer une commande
                                Tcommandes cmd = tcommandesFacade.find(Integer.parseInt(request.getParameter("idC")));
                                Tlignecommande ligne = tlignecommandeFacade.find(Integer.parseInt(request.getParameter("id")));
                                ligne.setPrixtotal(ligne.getPrixtotal() - cmd.getPrixTotal());
                                Tarticles art = cmd.getArticle();
                                art.setStock(art.getStock() + cmd.getQuantite());
                                art.setPrixAchat(art.getStock() * art.getPrix());
                                ligne.setRemise(ligne.getRemise() - cmd.getRemise());
                                try {
                                    ligne = tlignecommandeFacade.MisAJour(ligne);
                                    art = tarticlesFacade.MiSaJour(art);
                                    tcommandesFacade.Supprimer(cmd.getId());
                                } catch (RollbackFailureException ex) {
                                    Logger.getLogger(gestionCommandes.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (Exception ex) {
                                    Logger.getLogger(gestionCommandes.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                request.setAttribute("isarticle", "yes");
                                request.setAttribute("ligneCommande", tlignecommandeFacade.find(Integer.parseInt(request.getParameter("id"))));
                                break;
                            case 3:
                                try {
                                    //rechercher le produit à modifier
                                    request.setAttribute("isarticle", "yes");
                                    request.setAttribute("commande", tcommandesFacade.find(Integer.parseInt(request.getParameter("idC"))));
                                    request.setAttribute("ligneCommande", tlignecommandeFacade.find(Integer.parseInt(request.getParameter("id"))));

                                } catch (Exception ex) {
                                    request.setAttribute("erreur", ex.getMessage());
                                }
                                break;
                            case 4:
                                try {
                                    //Mettre à jour une commande
                                    updateVente(request, societe);

                                } catch (Exception ex) {
                                    request.setAttribute("erreur", ex.getMessage());
                                }
                                request.setAttribute("isarticle", "yes");
                                request.setAttribute("ligneCommande", tlignecommandeFacade.find(Integer.parseInt(request.getParameter("id"))));
                                break;

                            case 5:
                                try {
                                    // ajouter un emballage à la vente
                                    updateEmballage(request, societe);

                                } catch (Exception ex) {
                                    Logger.getLogger(gestionCommandes.class.getName()).log(Level.SEVERE, null, ex);

                                    request.setAttribute("erreur", ex.getMessage());
                                }
                                request.setAttribute("isarticle", "no");
                                request.setAttribute("ligneCommande", tlignecommandeFacade.find(Integer.parseInt(request.getParameter("id"))));
                                break;
                            case 6:

                                // supprimer une commande emballage
                                CommandeEmballage cmde = commandeEmballageFacade.find(Integer.parseInt(request.getParameter("idC")));
                                Tlignecommande lignee = tlignecommandeFacade.find(Integer.parseInt(request.getParameter("id")));
                                Tclients cl = lignee.getClient();
                                CompteEmballage compte = compteEmballageFacade.findAllByCLientAndEmballage(cl.getId(), cmde.getEmballage().getId());
                                lignee.setPrixtotal(lignee.getPrixtotal() - cmde.getPrixTotal());
                                Emballage emb = cmde.getEmballage();
                                emb.setStock(emb.getStock() + cmde.getQuantite());
                                lignee.setRemise(lignee.getRemise() - cmde.getRemise());
                                try {
                                    lignee = tlignecommandeFacade.MisAJour(lignee);
                                    emb = emballageFacade.MisAjour(emb);
                                    commandeEmballageFacade.Supprimer(cmde.getId());
                                    compte.setQuantite(compte.getQuantite() - cmde.getQuantite());
                                    compte.setMontant(compte.getQuantite() * compte.getEmballage().getPrix());
                                    compteEmballageFacade.MisAjour(compte);

                                } catch (RollbackFailureException ex) {
                                    Logger.getLogger(gestionCommandes.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (Exception ex) {
                                    Logger.getLogger(gestionCommandes.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                request.setAttribute("isarticle", "no");
                                request.setAttribute("ligneCommande", tlignecommandeFacade.find(Integer.parseInt(request.getParameter("id"))));
                                break;
                            case 7:
                                try {
                                    //rechercher le produit à modifier
                                    request.setAttribute("isarticle", "no");
                                    request.setAttribute("commandeemballage", commandeEmballageFacade.find(Integer.parseInt(request.getParameter("idC"))));
                                    request.setAttribute("ligneCommande", tlignecommandeFacade.find(Integer.parseInt(request.getParameter("id"))));

                                } catch (Exception ex) {
                                    request.setAttribute("erreur", ex.getMessage());
                                }
                                break;
                            case 8:
                                try {
                                    //Mettre à jour une commande
                                    updateEmballage(request, societe);
                                    request.setAttribute("isarticle", "no");
                                    request.setAttribute("ligneCommande", tlignecommandeFacade.find(Integer.parseInt(request.getParameter("id"))));

                                } catch (Exception ex) {
                                    request.setAttribute("erreur", ex.getMessage());
                                }
                                break;
                            default:
                                break;

                        }
                        if (session.getAttribute("utilisateur") != null) {
                            servicesInitializeImple.initialize(request, (Tusers) session.getAttribute("utilisateur"));

                        } else if (session.getAttribute("client") != null) {
                            servicesInitializeImple.initiationClients(request, (Tclients) session.getAttribute("client"));
                        }
                        request.setAttribute("q", request.getParameter("q"));
                        request.getServletContext().getRequestDispatcher("/WEB-INF/main.jsp").forward(request, response);
                        break;
                    case "retour":
                        switch (Integer.parseInt(request.getParameter("isNew"))) {
                            case 0: {
                                List<CompteEmballage> compte = compteEmballageFacade.findAll(societe.getId());
                                request.setAttribute("comptes", compte);
                                request.getSession().removeAttribute("clientEncour");
                            }

                            break;
                            case 1:
                                //initialisation du retour d'emallage d'un client
                                Tclients cl = tclientsFacade.find(Integer.parseInt(request.getParameter("client")));
                                List<CompteEmballage> comptes = compteEmballageFacade.findAllByCLient(cl.getId());
                                request.getSession().setAttribute("clientEncour", cl);
                                request.setAttribute("comptes", comptes);
                                break;
                            case 2:
                                //ajouter un emballage en stock
                                Tclients cl1 = tclientsFacade.find(Integer.parseInt(request.getParameter("client")));
                                List<CompteEmballage> compte = compteEmballageFacade.findAllByCLient(cl1.getId());
                                Emballage emb = emballageFacade.find(Integer.parseInt(request.getParameter("emballage")));
                                int qte = Integer.parseInt(request.getParameter("quantite"));
                                for (CompteEmballage cmp : compte) {
                                    if (Objects.equals(cmp.getEmballage().getId(), emb.getId())) {
                                        if (qte <= cmp.getQuantite()) {
                                            cmp.setQuantite(cmp.getQuantite() - qte);
                                            cmp.setMontant(cmp.getMontant() - (qte * emb.getPrix()));
                                            try {
                                                cmp = compteEmballageFacade.MisAjour(cmp);
                                            } catch (RollbackFailureException ex) {
                                                Logger.getLogger(gestionCommandes.class.getName()).log(Level.SEVERE, null, ex);
                                            } catch (Exception ex) {
                                                Logger.getLogger(gestionCommandes.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                            emb.setStock(qte + emb.getStock());
                                            emb.setPrixTotal(emb.getStock() * emb.getPrix());
                                            try {
                                                emb = emballageFacade.MisAjour(emb);
                                            } catch (RollbackFailureException ex) {
                                                Logger.getLogger(gestionCommandes.class.getName()).log(Level.SEVERE, null, ex);
                                            } catch (Exception ex) {
                                                Logger.getLogger(gestionCommandes.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                            break;
                                        } else {
                                            Message message = new Message();
                                            message.setType("error");
                                            message.setTitle("Impossible");
                                            message.setMessage("Cette quantite est supérieure à la quantité requise");
                                            message.setNotification("Cette quantite est supérieure à la quantité requise");
                                            request.setAttribute("message", message);
                                            break;
                                        }

                                    }
                                }
                                request.getSession().setAttribute("clientEncour", cl1);
                                List<CompteEmballage> res = compteEmballageFacade.findAllByCLient(cl1.getId());
                                request.setAttribute("comptes", res);
                                break;
                            case 3: {
                                // ejecter le client encours
                                List<CompteEmballage> compte2 = compteEmballageFacade.findAll(societe.getId());
                                request.setAttribute("comptes", compte2);
                                request.getSession().removeAttribute("clientEncour");
                            }
                            break;
                            case 4:
                                //supprimer l' inventaire

                                break;
                            case 5:
                                //obtenir  l' inventaire à modifier

                                break;

                        }

                        if (session.getAttribute("utilisateur") != null) {
                            servicesInitializeImple.initialize(request, (Tusers) session.getAttribute("utilisateur"));
                        } else if (session.getAttribute("client") != null) {
                            servicesInitializeImple.initiationClients(request, (Tclients) session.getAttribute("client"));
                        }
                        request.setAttribute("q", request.getParameter("q"));
                        request.getServletContext().getRequestDispatcher("/WEB-INF/main.jsp").forward(request, response);
                        break;
                    case "addEmballageCommande": {
                        JSONArray object = new JSONArray();
                        List<CommandeEmballage> cmdE = new ArrayList<>();
                        if (request.getSession().getAttribute("emballageCommande") != null) {
                            cmdE = (List<CommandeEmballage>) request.getSession().getAttribute("emballageCommande");
                        }

                        CommandeEmballage cdenew = new CommandeEmballage();
                        Emballage emb = emballageFacade.find(Integer.parseInt(request.getParameter("id")));
                        Double qte = Double.parseDouble(request.getParameter("qte"));
                        cdenew.setEmballage(emb);
                        cdenew.setPrix(emb.getPrix());
                        cdenew.setQuantite(qte);
                        cdenew.setPrixTotal(cdenew.getQuantite() * cdenew.getPrix());
                        cmdE.add(cdenew);
                        System.out.println(" sizze " + cmdE.size());
                        object.put("OK");
                        request.getSession().setAttribute("emballageCommande", cmdE);
                        response.setContentType("application/json");
                        response.getWriter().print(object);
                        break;
                    }
                    case "saveEmballageCommande": {
                        JSONArray object = new JSONArray();
                        Tlignecommande lg = (Tlignecommande) request.getSession().getAttribute("lignecommande");
                        lg.setTransport(Integer.parseInt(request.getParameter("transport")));
                        request.getSession().setAttribute("lignecommande", lg);
                        object.put("OK");
                        response.setContentType("application/json");
                        response.getWriter().print(object);
                        break;
                    }
                    case "deleteEmballageCommande": {

                        JSONArray object = new JSONArray();
                        List<CommandeEmballage> cmdE = null;
                        if (request.getSession().getAttribute("emballageCommande") != null) {
                            cmdE = (List<CommandeEmballage>) request.getSession().getAttribute("emballageCommande");
                            Emballage emb = emballageFacade.find(Integer.parseInt(request.getParameter("id")));
                            for (int i = 0; i < cmdE.size(); i++) {
                                if (cmdE.get(i).getEmballage().getId() == emb.getId()) {
                                    cmdE.remove(i);
                                    break;
                                }
                            }
                            request.getSession().setAttribute("emballageCommande", cmdE);
                            object.put("OK");
                        } else {
                            object.put("NON OK");
                        }

                        response.setContentType("application/json");
                        response.getWriter().print(object);
                        break;
                    }
                    case "getArticlesByCategorie": {
                        JSONArray object = new JSONArray();
                        HashMap dat = new HashMap();
                        Tcategorie ta = tcategorieFacade.findTcategorie(Integer.parseInt(request.getParameter("id")));
                        ta.getTarticlesList().stream().map((st) -> {
                            dat.put("id", st.getId());
                            return st;
                        }).map((st) -> {
                            dat.put("id", st.getId());
                            return st;
                        }).map((st) -> {
                            dat.put("name", st.getNom());
                            return st;
                        }).map((st) -> {
                            dat.put("image", st.getPhoto());
                            return st;
                        }).map((st) -> {
                            dat.put("lien", st.getChemin());
                            return st;
                        }).map((st) -> {
                            dat.put("prix", st.getPrix());
                            return st;
                        }).map((st) -> {
                            dat.put("unite", st.getUnite());
                            return st;
                        }).forEach((_item) -> {
                            object.put(dat);
                        });
                        response.setContentType("application/json");
                        response.getWriter().print(object);
                        break;
                    }
                    case "getImpressionFeuilleDeRoute": {
                        try {
                            getImpressionFeuilleDeRoute(request, response, societe);
                        } catch (JSONException ex) {
                            Logger.getLogger(gestionCommandes.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    }
                    case "getImpressionFeuilleDeRouteByClient": {
                        try {
                            getImpressionFeuilleDeRouteByClients(request, response, societe);
                        } catch (JSONException ex) {
                            Logger.getLogger(gestionCommandes.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    }
                    case "getImpressionFeuilleDeRouteByTourner": {
                        try {
                            getImpressionFeuilleDeRouteForTourner(request, response, societe);
                        } catch (JSONException ex) {
                            Logger.getLogger(gestionCommandes.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    }
                    case "getCommandeToPrint": {
                        try {
                            getJsonDataOneCommande(request, response);
                        } catch (JSONException ex) {
                            Logger.getLogger(gestionCommandes.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    }
                    default:
                        break;
                }

            } else {
                if (session.getAttribute("utilisateur") != null) {
                    servicesInitializeImple.initialize(request, (Tusers) session.getAttribute("utilisateur"));
                } else if (session.getAttribute("client") != null) {
                    servicesInitializeImple.initiationClients(request, (Tclients) session.getAttribute("client"));
                }
                request.getServletContext().getRequestDispatcher("/WEB-INF/main.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("message", new Message("votre session a expirer veuillez vous reconnecter"));
            request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        }

    }

    void getJsonDataOneCommande(HttpServletRequest request, HttpServletResponse response) throws JSONException, IOException {

        Tlignecommande re = new Tlignecommande();
        if (request.getParameter("fromcaisse") != null) {
            re = ligneAccountFacade.find(Integer.parseInt(request.getParameter("id"))).getLigneCommande();
        } else {
            re = tlignecommandeFacade.findTlignecommande(Integer.parseInt(request.getParameter("id")));
        }
        Societe s = re.getSociete();
        Tclients cl = re.getClient();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        DateFormat dff = new SimpleDateFormat("dd/MM/yyyy");
        JSONArray sing = new JSONArray();
        HashMap sig = new HashMap();
        Double rist = 0.0;
        for (Tlignecommande lig : cl.getTlignecommandeList()) {
            rist += lig.getMargeClient();
        }
        sig.put("allMargeClient", rist);

        sig.put("adressesociete", s.getAdresse());
        if (s.getCodePostal() != null) {
            sig.put("codepostalsociete", s.getCodePostal());
        }
        sig.put("mailsociete", s.getEmail());
        sig.put("immatriculation", s.getImmatriculation());

        sig.put("cni", s.getCniRc());
        sig.put("centre_impot", s.getCentreImpot());
        if (s.getLogoBase64() != null) {
            sig.put("logosociete", s.getLogoBase64());
        }

        sig.put("nomsociete", s.getNom());
        sig.put("telsociete", s.getTel());
        sig.put("transport", re.getTransport());
        sig.put("codeclient", re.getClient().getCodeclient());
        sig.put("adresseclient", re.getClient().getAdresse());
        sig.put("mailclient", re.getClient().getMail());
        sig.put("nomclient", re.getClient().getNom().concat(" " + re.getClient().getPrenom()));
        sig.put("telclient", re.getClient().getTelephone());
        sig.put("secteurclient", re.getClient().getSecteurid().getName());
        sig.put("typeclient", re.getClient().getTypeclientid().getName());
        sig.put("districtclient", re.getClient().getSecteurid().getDistrictid().getName());
        sig.put("regionclient", re.getClient().getSecteurid().getDistrictid().getRegionid().getName());
        sig.put("datecreation", df.format(re.getDatec()));
        sig.put("gestcassier", re.getSociete().getGestcassier());
        sig.put("gestmarge", re.getSociete().getGestmarge());
        if (re.getSociete().getGestcassier() == 1) {
            if (re.getCassier() != null) {
                sig.put("casier", re.getCassier());
            } else {
                sig.put("casier", 0);
            }

        }

        if (re.getSociete().getGesttourner() == 1) {
            if (re.getTourner() != null) {
                sig.put("tourner", re.getTourner().getNumc());
            }

        }
        if (re.getDatemodif() != null) {
            sig.put("datemodif", df.format(re.getDatemodif()));
        }
        if (re.getDatelivraison() != null) {
            sig.put("dateecheance", dff.format(re.getDatelivraison()));
        }
        if (re.getCommentaire() != null && !re.getCommentaire().isEmpty()) {
            sig.put("commentaireG", re.getCommentaire());
        }
        if (re.getSignature() != null && !re.getSignature().isEmpty()) {
            sig.put("signature", re.getSignature());
        }
        sig.put("qte", re.getQtotal());
        sig.put("total", re.getPrixtotal());
        sig.put("numero", re.getId());
        sig.put("etat", re.getEtatc().getNom());
        sig.put("codeetat", re.getEtatc().getCode());
        sig.put("totalMargeClient", re.getMargeClient());
        sig.put("totalCasierEncour", re.getCassier());
        sig.put("totalMargeFournisseur", re.getMargeFournisseur());
        JSONArray arts = new JSONArray();
        //tlignecommandeFacade.SortCommande(re);
        if (!re.getLigneAccountList().isEmpty()) {
            LigneAccount la = re.getLigneAccountList().get(0);
            sig.put("avancePaiement", la.getMontantNet() - la.getMontantRestant());
            sig.put("restePaiement", la.getMontantRestant());
        }
        for (Tcommandes cmd : re.getTcommandesList()) {
            HashMap art = new HashMap();
            art.put("categorie", cmd.getArticle().getCategorie().getNom());
            art.put("article", cmd.getArticle().getNom());
            art.put("code", cmd.getArticle().getCode());
            art.put("quantite", cmd.getQuantite());
            art.put("margeClient", cmd.getMargeClient());
            art.put("margeFournisseur", cmd.getMargeFournisseur());
            art.put("pu", cmd.getPrix());
            art.put("total", cmd.getPrixTotal());

            if (cmd.getCommantaire() != null) {
                if (!cmd.getCommantaire().isEmpty()) {
                    art.put("commentaire", cmd.getCommantaire());
                }

            }
            if (cmd.getCommantaire1() != null && !cmd.getCommantaire1().isEmpty()) {
                if (!cmd.getCommantaire1().isEmpty()) {
                    art.put("commentaire1", cmd.getCommantaire1());
                }

            }
            arts.put(art);

        }
        sig.put("articles", arts);
        JSONArray emb = new JSONArray();
        if (!re.getCommandeEmballageList().isEmpty()) {
            for (CommandeEmballage cmd : re.getCommandeEmballageList()) {
                HashMap art = new HashMap();
                art.put("article", cmd.getEmballage().getNom());
                art.put("code", cmd.getEmballage().getCode());
                art.put("quantite", cmd.getQuantite());
                art.put("pu", cmd.getPrix());
                art.put("total", cmd.getPrixTotal());

                emb.put(art);

            }
        }
        JSONArray dette = new JSONArray();
        int total = 0;
        if (!cl.getCompteEmballageList().isEmpty()) {

            for (CompteEmballage com : cl.getCompteEmballageList()) {
                HashMap art = new HashMap();
                art.put("emballage", com.getEmballage().getNom());
                art.put("code", com.getEmballage().getCode());
                art.put("montant", com.getMontant());
                art.put("quantite", com.getQuantite());
                total += com.getQuantite();
                dette.put(art);
            }
        }

        sig.put("totalCasier", total);
        sig.put("compteEmballage", dette);
        sig.put("emballages", emb);
        sing.put(sig);

        response.setContentType("application/json");
        response.getWriter().print(sing);
    }

    List<Tlignecommande> HistoriqueCommande(HttpServletRequest request, Societe societe) {
        List<Tlignecommande> listCommandesH;

        if (request.getSession().getAttribute("client") != null) {
            Tclients cl = (Tclients) request.getSession().getAttribute("client");
            listCommandesH = tclientsFacade.findTclients(cl.getId()).getTlignecommandeList();

        } else {
            listCommandesH = tlignecommandeFacade.findAll(societe.getImmatriculation());
        }
        request.setAttribute("messageTable", "Ristournes Général des commandes");
        return listCommandesH;
    }

    List<Tlignecommande> HistoriqueCommandeByEtat(HttpServletRequest request, Societe societe) {
        int etat = Integer.parseInt(request.getParameter("statut"));
        String[] date1 = request.getParameter("interval").split("-");
        Date d1 = date_du_jour.dateConvert(date1[0]);
        Date d2 = date_du_jour.dateConvert(date1[1]);
        List<Tlignecommande> listCommandesH;
        if (request.getSession().getAttribute("client") != null) {
            Tclients cl = (Tclients) request.getSession().getAttribute("client");
            if (etat == 0) {
                listCommandesH = tlignecommandeFacade.findTcommandesByClientByPeriode(d1, d2, cl.getId());
            } else {
                listCommandesH = tlignecommandeFacade.findTcommandesByClientByPeriode(d1, d2, cl.getId()).stream()
                        .filter(in -> in.getEtatc().getCode() == Integer.parseInt(request.getParameter("statut")))
                        .collect(Collectors.toList());
            }

        } else if (etat == 0) {
            // servicesInitializeImple.getCommandesByZoneAutorize(utilisateur);
            listCommandesH = tlignecommandeFacade.findTcommandesByPeriode(d1, d2, societe.getImmatriculation());
        } else {
            listCommandesH = tlignecommandeFacade.findTcommandesByPeriode(d1, d2, societe.getImmatriculation()).stream()
                    .filter(in -> in.getEtatc().getCode() == Integer.parseInt(request.getParameter("statut")))
                    .collect(Collectors.toList());
        }
        request.setAttribute("messageTable", "Ristournes Général des commandes du " + date1[0] + " au " + date1[1]);
        return listCommandesH;
    }

    void getImpressionFeuilleDeRoute(HttpServletRequest request, HttpServletResponse response, Societe societe) throws JSONException, IOException {
        JSONArray sing = new JSONArray();
        int etat = Integer.parseInt(request.getParameter("etat"));
        Tetatc et = tetatcFacade.findByCode(etat);
        String[] date1 = request.getParameter("interval").split("-");
        Date d = date_du_jour.dateConvert(date1[0]);
        Date d1 = date_du_jour.dateConvert(date1[1]);
        List<Integer> l = date_du_jour.getClientCmommande(d, d1, societe.getId(), true, et.getId());
        HashMap soc = new HashMap();
        soc.put("adressesociete", societe.getAdresse());
        soc.put("codepostalsociete", societe.getCodePostal());
        soc.put("mailsociete", societe.getEmail());
        soc.put("logosociete", societe.getLogoBase64());
        soc.put("nomsociete", societe.getNom());
        soc.put("telsociete", societe.getTel());
        soc.put("datedebut", date1[0]);
        soc.put("datefin", date1[1]);
        sing.put(soc);

        for (Integer cl : l) {
            Set<Tarticles> lar = tcommandesFacade.findTcommandesArticleByPeriode(d, d1, etat, societe.getId());
            Tclients cli = tclientsFacade.findTclients(cl);
            HashMap sig1 = new HashMap();
            Set<Tcommandes> lc = new HashSet<>();
            Set<Tarticles> la = new HashSet<>();
            sig1.put("nom", cli.getCodeclient().concat(" " + cli.getNom()));
            List<Tcommandes> listCommandesH = tcommandesFacade.findTcommandesByPeriodeByClientSUM(d, d1, cl, etat);

            for (Tcommandes cc : listCommandesH) {
                la.add(cc.getArticle());
            }
            JSONArray pt = new JSONArray();
            for (Tcommandes cd : listCommandesH) {
                HashMap cmd = new HashMap();
                cmd.put("article", cd.getArticle().getNom());
                cmd.put("quantite", cd.getQuantite());
                cmd.put("total", cd.getPrixTotal());
                cmd.put("code", cd.getArticle().getCode());
                cmd.put("id", cd.getArticle().getId());
                pt.put(cmd);
            }
            lar.removeAll(la);
            for (Tarticles as : lar) {
                Tarticles ar = tarticlesFacade.findTarticles(as.getId());
                HashMap cmd = new HashMap();
                cmd.put("article", ar.getNom());
                cmd.put("quantite", 0);
                cmd.put("total", 0);
                cmd.put("code", ar.getCode());
                cmd.put("id", ar.getId());
                pt.put(cmd);
            }
            la.clear();
            sig1.put("commandes", pt);
            sing.put(sig1);
            lc.clear();
        }
        response.setContentType("application/json");
        response.getWriter().print(sing);
    }

    void getImpressionFeuilleDeRouteByClients(HttpServletRequest request, HttpServletResponse response, Societe societe) throws JSONException, IOException {
        JSONArray sing = new JSONArray();
        int etat = Integer.parseInt(request.getParameter("etat"));
        Tetatc et = tetatcFacade.findByCode(etat);
        String[] date1 = request.getParameter("interval").split("-");
        Date d = date_du_jour.dateConvert(date1[0]);
        Date d1 = date_du_jour.dateConvert(date1[1]);
        String[] l = request.getParameter("client").split(",");
        List<Integer> lct = new ArrayList<>();
        for (String s : l) {
            lct.add(Integer.parseInt(s));
        }
        List<Integer> l1 = date_du_jour.getClientCmommande(d, d1, societe.getId(), true, et.getId());
        HashMap soc = new HashMap();
        soc.put("adressesociete", societe.getAdresse());
        soc.put("codepostalsociete", societe.getCodePostal());
        soc.put("mailsociete", societe.getEmail());
        soc.put("logosociete", societe.getLogoBase64());
        soc.put("nomsociete", societe.getNom());
        soc.put("telsociete", societe.getTel());
        soc.put("datedebut", date1[0]);
        soc.put("datefin", date1[1]);
        sing.put(soc);

        for (Integer cl : l1) {
            if (lct.contains(cl)) {

                Set<Tarticles> lar = tcommandesFacade.findTcommandesArticleByPeriode(d, d1, etat, societe.getId());
                Tclients cli = tclientsFacade.findTclients(cl);
                HashMap sig1 = new HashMap();
                Set<Tcommandes> lc = new HashSet<>();
                Set<Tarticles> la = new HashSet<>();
                sig1.put("nom", cli.getCodeclient().concat(" " + cli.getNom()));
                List<Tcommandes> listCommandesH = tcommandesFacade.findTcommandesByPeriodeByClientSUM(d, d1, cl, etat);

                for (Tcommandes cc : listCommandesH) {
                    la.add(cc.getArticle());
                }
                JSONArray pt = new JSONArray();
                for (Tcommandes cd : listCommandesH) {
                    HashMap cmd = new HashMap();
                    cmd.put("article", cd.getArticle().getNom());
                    cmd.put("quantite", cd.getQuantite());
                    cmd.put("total", cd.getPrixTotal());
                    cmd.put("code", cd.getArticle().getCode());
                    cmd.put("id", cd.getArticle().getId());
                    pt.put(cmd);
                }
                lar.removeAll(la);
                for (Tarticles as : lar) {
                    Tarticles ar = tarticlesFacade.findTarticles(as.getId());
                    HashMap cmd = new HashMap();
                    cmd.put("article", ar.getNom());
                    cmd.put("quantite", 0);
                    cmd.put("total", 0);
                    cmd.put("code", ar.getCode());
                    cmd.put("id", ar.getId());
                    pt.put(cmd);
                }
                la.clear();
                sig1.put("commandes", pt);
                sing.put(sig1);
                lc.clear();
            }
        }
        response.setContentType("application/json");
        response.getWriter().print(sing);
    }

    void getImpressionFeuilleDeRouteForTourner(HttpServletRequest request, HttpServletResponse response, Societe societe) throws JSONException, IOException {
        JSONArray sing = new JSONArray();
        int etat = Integer.parseInt(request.getParameter("etat"));
        Tetatc et = tetatcFacade.findByCode(etat);
        String[] date1 = request.getParameter("interval").split("-");
        Date d = date_du_jour.dateConvert(date1[0]);
        Date d1 = date_du_jour.dateConvert(date1[1]);
        String[] l = request.getParameter("tourner").split(",");
        List<Tclients> lct = new ArrayList<>();
        for (String s : l) {
            Tourner tr = tournerFacade.findTourner(Integer.parseInt(s));
            lct.addAll(tr.getTclientsList());
        }
        List<Integer> l1 = date_du_jour.getClientCmommande(d, d1, societe.getId(), true, et.getId());
        HashMap soc = new HashMap();
        soc.put("adressesociete", societe.getAdresse());
        soc.put("codepostalsociete", societe.getCodePostal());
        soc.put("mailsociete", societe.getEmail());
        soc.put("logosociete", societe.getLogoBase64());
        soc.put("nomsociete", societe.getNom());
        soc.put("telsociete", societe.getTel());
        soc.put("datedebut", date1[0]);
        soc.put("datefin", date1[1]);
        sing.put(soc);

        for (Integer cl : l1) {
            Tclients client = tclientsFacade.findTclients(cl);
            if (lct.contains(client)) {

                Set<Tarticles> lar = tcommandesFacade.findTcommandesArticleByPeriode(d, d1, etat, societe.getId());
                HashMap sig1 = new HashMap();
                Set<Tcommandes> lc = new HashSet<>();
                Set<Tarticles> la = new HashSet<>();
                sig1.put("nom", client.getNom());
                List<Tcommandes> listCommandesH = tcommandesFacade.findTcommandesByPeriodeByClientSUM(d, d1, cl, etat);

                for (Tcommandes cc : listCommandesH) {
                    la.add(cc.getArticle());
                }
                JSONArray pt = new JSONArray();
                for (Tcommandes cd : listCommandesH) {
                    HashMap cmd = new HashMap();
                    cmd.put("article", cd.getArticle().getNom());
                    cmd.put("quantite", cd.getQuantite());
                    cmd.put("total", cd.getPrixTotal());
                    cmd.put("code", cd.getArticle().getCode());
                    cmd.put("id", cd.getArticle().getId());
                    pt.put(cmd);
                }
                lar.removeAll(la);
                for (Tarticles as : lar) {
                    Tarticles ar = tarticlesFacade.findTarticles(as.getId());
                    HashMap cmd = new HashMap();
                    cmd.put("article", ar.getNom());
                    cmd.put("quantite", 0);
                    cmd.put("total", 0);
                    cmd.put("code", ar.getCode());
                    cmd.put("id", ar.getId());
                    pt.put(cmd);
                }
                la.clear();
                sig1.put("commandes", pt);
                sing.put(sig1);
                lc.clear();
            }
        }
        response.setContentType("application/json");
        response.getWriter().print(sing);
    }

    HashMap getSumCommander(Date d, Date d1, int societe, int etat, int id) {
        Tarticles qt = tcommandesFacade.getSumCommander(d, d1, societe, etat, id);
        HashMap cmd = new HashMap();
        cmd.put("id", id);
        cmd.put("qt", qt.getStock());
        return cmd;
    }

    void getImpressionFeuilleDeRouteJSP(HttpServletRequest request, HttpServletResponse response, Societe societe) {
        List<HashMap> feuille = new ArrayList<>();
        List<HashMap> QT = new ArrayList<>();
        int etat = Integer.parseInt(request.getParameter("etat"));
        Tetatc et = tetatcFacade.find(etat);
        String[] date1 = request.getParameter("interval").split("-");
        Date d = date_du_jour.dateConvert(date1[0]);
        Date d1 = date_du_jour.dateConvert(date1[1]);
        List<Integer> l = date_du_jour.getClientCmommande(d, d1, societe.getId(), true, et.getId());

        HashMap soc = new HashMap();
        soc.put("adressesociete", societe.getAdresse());
        soc.put("codepostalsociete", societe.getCodePostal());
        soc.put("mailsociete", societe.getEmail());
        soc.put("logosociete", societe.getLogoBase64());
        soc.put("nomsociete", societe.getNom());
        soc.put("telsociete", societe.getTel());
        soc.put("datedebut", date1[0]);
        soc.put("datefin", date1[1]);
        feuille.add(soc);

        Set<Tarticles> lars = tcommandesFacade.findTcommandesArticleByPeriode(d, d1, etat, societe.getId());
        for (Tarticles ar : lars) {
            QT.add(getSumCommander(d, d1, societe.getId(), etat, ar.getId()));
        }

        for (Integer cl : l) {
            Set<Tarticles> lar = tcommandesFacade.findTcommandesArticleByPeriode(d, d1, etat, societe.getId());
            Tclients cli = tclientsFacade.findTclients(cl);
            HashMap sig1 = new HashMap();
            sig1.put("nom", cli.getCodeclient().concat(" " + cli.getNom()));
            Set<Tarticles> la = new HashSet<>();

            List<Tcommandes> listCommandesH = tcommandesFacade.findTcommandesByPeriodeByClientSUM(d, d1, cl, etat);
            for (Tcommandes cc : listCommandesH) {
                la.add(cc.getArticle());
            }
            List<HashMap> pt = new ArrayList<>();

            for (Tcommandes cd : listCommandesH) {
                HashMap cmd = new HashMap();
                cmd.put("article", cd.getArticle().getNom());
                cmd.put("quantite", cd.getQuantite());
                cmd.put("total", cd.getPrixTotal());
                cmd.put("code", cd.getArticle().getCode());
                cmd.put("id", cd.getArticle().getId());
                pt.add(cmd);
            }

            lar.removeAll(la);
            for (Tarticles as : lar) {
                Tarticles ar = tarticlesFacade.findTarticles(as.getId());
                HashMap cmd = new HashMap();
                cmd.put("article", ar.getNom());
                cmd.put("quantite", 0);
                cmd.put("total", 0);
                cmd.put("code", ar.getCode());
                cmd.put("id", ar.getId());
                pt.add(cmd);
            }
            sig1.put("commandes", pt);
            feuille.add(sig1);
            la.clear();
            lar.clear();
        }
        request.setAttribute("feuilleRoute", feuille);
        request.setAttribute("QT", QT);
    }

// fonctions en charge de remplire les modeles
    List<Tcommandes> getCommadesBtyligne(int ligne) {
        return tlignecommandeFacade.findTlignecommande(ligne).getTcommandesList();
    }

    List<Tcommandes> getCommadesBYClient(HttpServletRequest request) {
        int etat = Integer.parseInt(request.getParameter("statut"));
        int cl = Integer.parseInt(request.getParameter("client"));
        String[] date1 = request.getParameter("interval").split("-");
        Date d1 = date_du_jour.dateConvert(date1[0]);
        Date d2 = date_du_jour.dateConvert(date1[1]);
        Tclients tcl = tclientsFacade.find(cl);
        request.setAttribute("messageTable", "Ristournes du client :" + tcl.getNom() + " du " + date1[0] + " au " + date1[1]);
        return tcommandesFacade.findTcommandesByPeriodeByClientSUMForMarge(d1, d2, cl, etat);
    }

    Set<Tcommandes> getCommadesBYCategorie(HttpServletRequest request) {
        int etat = Integer.parseInt(request.getParameter("statut"));
        int cat = Integer.parseInt(request.getParameter("categorie"));
        String[] date1 = request.getParameter("interval").split("-");
        Date d1 = date_du_jour.dateConvert(date1[0]);
        Date d2 = date_du_jour.dateConvert(date1[1]);
        Tcategorie tcat = tcategorieFacade.find(cat);
        request.setAttribute("messageTable", "Ristournes sur la carégorie de produit :" + tcat.getNom() + " du " + date1[0] + " au " + date1[1]);
        return tcommandesFacade.findTcommandesCategorieByPeriode(d1, d2, cat, etat);
    }

    Set<Tcommandes> getCommadesBYArticle(HttpServletRequest request) {
        int etat = Integer.parseInt(request.getParameter("statut"));
        int cat = Integer.parseInt(request.getParameter("article"));
        String[] date1 = request.getParameter("interval").split("-");
        Date d1 = date_du_jour.dateConvert(date1[0]);
        Date d2 = date_du_jour.dateConvert(date1[1]);
        Tarticles tart = tarticlesFacade.find(cat);
        request.setAttribute("messageTable", "Ristournes sur la carégorie de produit :" + tart.getNom() + " du " + date1[0] + " au " + date1[1]);
        return tcommandesFacade.findTcommandesByArticleByPeriode(d1, d2, cat, etat);
    }

    void getCommandeList(HttpServletRequest request, Tusers t) throws Exception {
        Boolean docommande = true;
        Tclients cl;
        //DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        List<Tcommandes> listCommandes;
        Tlignecommande lg = null;
        if (request.getSession().getAttribute("listCommandes") == null) {
            listCommandes = new ArrayList<>();
            lg = new Tlignecommande();
            cl = tclientsFacade.findTclients(Integer.parseInt(request.getParameter("client")));
            lg.setClient(cl);
            lg.setEtatc(tetatcFacade.findByCode((501)));
            lg.setSociete(cl.getSociete());
            lg.setIsModif(0);
            lg.setDatec(date_du_jour.dateJour());
            lg.setDatelivraison(date_du_jour.dateConvert(request.getParameter("njr")));
            lg.setNumc(0);
            lg.setPrixtotal(0.0);
            lg.setOperateur(t.getFirstname());
            if (cl.getSociete().getGesttourner() == 1) {
                Tourner tr = (Tourner) request.getSession().getAttribute("tourner");
                lg.setTourner(tr);
                if (t.getAffectTournerUserList() != null) {
                    for (AffectTournerUser af : t.getAffectTournerUserList()) {
                        if (af.getTourner().equals(tr)) {
                            lg.setPreselleur(t);
                            break;
                        }
                    }
                }

            }
            request.getSession().setAttribute("lignecommande", lg);
            request.getSession().setAttribute("cmdclient", cl);
            request.getSession().setAttribute("njr", request.getParameter("njr"));

            for (Tlignecommande cmd : cl.getTlignecommandeList()) {
                if (cmd.getClient().getId().compareTo(lg.getClient().getId()) == 0 && cmd.getEtatc().getCode() != 200 && cmd.getEtatc().getCode() != 300) {
                    docommande = false;
                    break;
                }
            }
            request.getSession().setAttribute("listCommandes", listCommandes);
        }
        //    if (docommande) {
        if (true) {
            listCommandes = (List<Tcommandes>) request.getSession().getAttribute("listCommandes");
            int id = Integer.parseInt(request.getParameter("article"));
            if (getCommande(listCommandes, id) == null) {
                Tcommandes r = new Tcommandes();
                Tarticles ar = tarticlesFacade.findTarticles(id);
                r.setArticle(ar);
                r.setId(genererMumC(listCommandes));
                if (request.getParameter("quantite") != null) {
                    if (!request.getParameter("quantite").isEmpty()) {
                        r.setQuantite(Double.parseDouble(request.getParameter("quantite")));
                        if (!request.getParameter("prix").isEmpty()) {
                            r.setPrix(Double.parseDouble(request.getParameter("prix")));
                            r.setRemise(r.getArticle().getPrix() - r.getPrix());
                            r.setQt(-1);
                        } else {
                            r.setPrix(r.getArticle().getPrix());
                            r.setRemise(0.0);
                        }
                        r.setPrixTotal(r.getQuantite() * r.getPrix());
                    }
                }

                if (request.getParameter("commantaire") != null) {
                    if (!request.getParameter("commantaire").isEmpty()) {
                        r.setCommantaire(request.getParameter("commantaire"));
                    }
                }
                if (request.getParameter("commantaire1") != null) {
                    if (!request.getParameter("commantaire1").isEmpty()) {
                        r.setCommantaire1(request.getParameter("commantaire1"));
                    }
                }
                if (request.getParameter("qt") != null) {
                    if (!request.getParameter("qt").isEmpty()) {
                        r.setQt(Integer.parseInt(request.getParameter("qt")));
                        r.setPrixTotal(r.getQt() * r.getArticle().getPrix());
                    }
                }
                listCommandes.add(r);
                if (ar.getCategorie().getSociete().getGestcassier() == 1) {
                    if (request.getSession().getAttribute("lignecommande") != null) {
                        Tlignecommande l = (Tlignecommande) request.getSession().getAttribute("lignecommande");
                        if (ar.getEmballage() != null) {
                            l.setCassier(l.getCassier() + r.getQuantite());
                            request.getSession().setAttribute("lignecommande", l);
                        }
//                        if (ar.getCode().endsWith("C") || ar.getCode().endsWith("CP")) {
//                            l.setCassier(l.getCassier() + r.getQuantite());
//                            request.getSession().setAttribute("lignecommande", l);
//
//                        }
                    }

                }

                request.getSession().setAttribute("listCommandes", listCommandes);
                request.getSession().removeAttribute("listCommandes1");
                request.setAttribute("commandes", listCommandes);
            } else {
                Message message = new Message();
                message.setType("error");
                message.setTitle("Impossible");
                message.setMessage("cet Article existe déjà dans la liste des commandes");
                message.setNotification("cet Article existe déjà dans la liste des commandes");
                request.setAttribute("message", message);
                request.setAttribute("commandes", listCommandes);
            }
        } else {
            cl = tclientsFacade.findTclients(lg.getClient().getId());
            List<Tlignecommande> cmds = cl.getTlignecommandeList().stream().filter(in -> in.getEtatc().getCode() != 200).collect(Collectors.toList());
            Message message = new Message();
            message.setType("error");
            message.setTitle("Alert");
            message.setNotification("Impossible de passer une nouvelle commande sans validation des commandes encours");
            request.setAttribute("message", message);
            request.setAttribute("lastcommande", cmds);
        }
    }

    void AjouterCommandeList(HttpServletRequest request) throws Exception {
        int numc1 = Integer.parseInt(request.getParameter("id"));
        Tarticles ar = tarticlesFacade.findTarticles(Integer.parseInt(request.getParameter("article")));
        Tlignecommande lg = tlignecommandeFacade.findTlignecommande(numc1);
        List<Tcommandes> l1 = lg.getTcommandesList();
        lg.setIsModif(-1);
        if (lg.getSociete().getGesttourner() == 1) {
            Tourner tr = (Tourner) request.getSession().getAttribute("tourner");
            lg.setTourner(tr);
        }
        if (getCommande(l1, ar.getId()) == null) {
            Tcommandes r = new Tcommandes();
            r.setArticle(ar);
            r.setLigne(lg);
            if (request.getParameter("quantite") != null) {
                if (!request.getParameter("quantite").isEmpty()) {
                    r.setQuantite(Double.parseDouble(request.getParameter("quantite")));
                    if (!request.getParameter("prix").isEmpty()) {
                        r.setPrix(Double.parseDouble(request.getParameter("prix")));
                        r.setQt(-1);
                    } else {
                        r.setPrix(r.getArticle().getPrix());
                    }
                    r.setPrixTotal(r.getQuantite() * r.getPrix());
                    lg.setPrixtotal(r.getPrixTotal() + lg.getPrixtotal());

                    if (ar.getCategorie().getSociete().getGestcassier() == 1) {
                        if (!ar.getCode().startsWith("VRACC") || !ar.getCode().startsWith("VIP") || !ar.getCode().startsWith("VRAP")) {
                            lg.setQtotal(lg.getQtotal() + r.getQuantite());
                        }
                        if (lg.getEtatc().getCode() != 201) {
                            if (ar.getCode().endsWith("C") || ar.getCode().endsWith("CP")) {
                                lg.setCassier(lg.getCassier() + r.getQuantite());
                            }
                        }

                    } else {
                        lg.setQtotal(lg.getQtotal() + r.getQuantite());
                    }

                }
            }
            if (lg.getEtatc().getCode() == 201) {
                if (ar.getCategorie().getSociete().getGestcassier() == 1) {
                    if (!ar.getCode().startsWith("VRACC") || !ar.getCode().startsWith("VIP") || !ar.getCode().startsWith("VRAP")) {
                        ar.setStock(ar.getStock() - r.getQuantite());
                        tarticlesFacade.MiSaJour(ar);
                        lg.setQtotal(lg.getQtotal() + r.getQuantite());

                    }
                    if (ar.getCode().endsWith("C") || ar.getCode().endsWith("CP")) {
                        lg.setCassier(lg.getCassier() + r.getQuantite());

                    }
                } else {
                    ar.setStock(ar.getStock() - r.getQuantite());
                    tarticlesFacade.MiSaJour(ar);
                    lg.setQtotal(lg.getQtotal() + r.getQuantite());

                }

            }
            if (request.getParameter("commantaire") != null) {
                if (!request.getParameter("commantaire").isEmpty()) {
                    r.setCommantaire(request.getParameter("commantaire"));
                }
            }
            if (request.getParameter("commantaire1") != null) {
                if (!request.getParameter("commantaire1").isEmpty()) {
                    r.setCommantaire1(request.getParameter("commantaire1"));
                }
            }
            tlignecommandeFacade.MisAJour(lg);
            tcommandesFacade.creer(r);
            request.getSession().removeAttribute("listCommandes1");
            request.setAttribute("commandes", tlignecommandeFacade.find(lg.getId()).getTcommandesList());
        } else {
            Message message = new Message();
            message.setType("error");
            message.setTitle("Impossible");
            message.setMessage("cet Article existe déjà dans la liste des commandes");
            request.setAttribute("message", message);
            request.setAttribute("commandes", getCommadesBtyligne(numc1));
        }

    }

    void EditCommandeModel(HttpServletRequest request) throws Exception {
        Tcommandes r = tcommandesFacade.findTcommandes(Integer.parseInt(request.getParameter("idC")));
        if (r.getLigne().getEtatc().getCode() != 201 && r.getLigne().getEtatc().getCode() != 200) {
            Tlignecommande lg = r.getLigne();
            lg.setDatemodif(date_du_jour.dateJour());
            lg.setPrixtotal(lg.getPrixtotal() - r.getPrixTotal());
            lg.setQtotal(lg.getQtotal() - r.getQuantite());
            lg.setQtotal(lg.getQtotal() + Integer.parseInt(request.getParameter("quantite")));
            if (request.getParameter("quantite") != null) {
                if (!request.getParameter("quantite").isEmpty()) {
                    if (lg.getSociete().getGestcassier() == 1) {
                        if (!r.getArticle().getCode().endsWith("C") || !r.getArticle().getCode().endsWith("CP")) {
                            lg.setCassier(lg.getCassier() - r.getQuantite());
                            lg.setCassier(lg.getCassier() + Integer.parseInt(request.getParameter("quantite")));
                        }
                    }
                    r.setQuantite(Double.parseDouble(request.getParameter("quantite")));
                    if (r.getQt() == -1) {
                        r.setPrixTotal(r.getPrix() * r.getQuantite());
                    } else {
                        r.setPrixTotal(r.getQuantite() * r.getArticle().getPrix());
                    }

                    lg.setPrixtotal(r.getPrixTotal() + lg.getPrixtotal());
                }

            }
            if (request.getParameter("ismodif") != null) {
                r.getLigne().setIsModif(1);
            }

            if (request.getParameter("commantaire") != null) {
                if (!request.getParameter("commantaire").isEmpty()) {
                    r.setCommantaire(request.getParameter("commantaire"));
                }
            }
            if (request.getParameter("commantaire1") != null) {
                if (!request.getParameter("commantaire1").isEmpty()) {
                    r.setCommantaire1(request.getParameter("commantaire1"));
                }
            }
            try {
                tcommandesFacade.MisAJour(r);
                tlignecommandeFacade.MisAJour(lg);
                request.setAttribute("commandes", tlignecommandeFacade.findTlignecommande(r.getLigne().getId()).getTcommandesList());

            } catch (Exception ex) {
                System.out.println("commande non enregistrer");
            }
        } else {
            EditCommande201(request);
            request.setAttribute("commandes", tlignecommandeFacade.findTlignecommande(r.getLigne().getId()).getTcommandesList());
        }
    }

    void saveCommandes(List<Tcommandes> liste, Tlignecommande lg, HttpServletRequest request) throws Exception {

        Tclients cl = tclientsFacade.findTclients(lg.getClient().getId());
        Boolean docommande = true;
        for (Tlignecommande cmd : cl.getTlignecommandeList()) {
            if (cmd.getClient().getId().compareTo(lg.getClient().getId()) == 0 && cmd.getEtatc().getCode() != 200) {
                docommande = false;
                break;
            }
        }

        if (docommande) {
            if (cl.getSociete().getGestcassier() == 1) {
                Double qtar = 0.0;
                try {
                    List<Tcommandes> tc = new ArrayList<>();
                    for (Tcommandes cc : liste) {
                        if (cc.getArticle().getCode().startsWith("VRACC") || cc.getArticle().getCode().startsWith("VIP") || cc.getArticle().getCode().startsWith("VRAP")) {
                            tc.add(cc);
                            qtar += cc.getQuantite();
                        }
                    }
                    liste.removeAll(tc);
                    lg.setQtotal(qtar);
                    liste.addAll(tc);
                    for (Tcommandes c : liste) {
                        lg.setQtotal(lg.getQtotal() + c.getQuantite());
                        lg.setPrixtotal(c.getPrixTotal() + lg.getPrixtotal());
                        if (request.getParameter("commantaire2") != null) {
                            if (!request.getParameter("commantaire2").isEmpty()) {
                                lg.setCommentaire(request.getParameter("commantaire2"));
                            }
                        }
                    }

                    lg = tlignecommandeFacade.creer(lg);
                } catch (Exception ex) {
                    Logger.getLogger(gestionCommandes.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    lg.setQtotal(0.0);
                    for (Tcommandes c : liste) {
                        lg.setQtotal(lg.getQtotal() + c.getQuantite());
                        lg.setPrixtotal(c.getPrixTotal() + lg.getPrixtotal());
                        if (request.getParameter("commantaire2") != null) {
                            if (!request.getParameter("commantaire2").isEmpty()) {
                                lg.setCommentaire(request.getParameter("commantaire2"));
                            }
                        }
                    }

                    lg = tlignecommandeFacade.creer(lg);
                } catch (Exception ex) {
                    Logger.getLogger(gestionCommandes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            for (Tcommandes c : liste) {
                try {
                    c.setLigne(lg);
                    tcommandesFacade.creer(c);
                } catch (Exception ex) {
                    Logger.getLogger(gestionCommandes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (cl.getSociete().getGestemballage() == 1) {
                // entrée des emballages en stocks
                for (Tcommandes c : liste) {
                    try {

                        if (c.getArticle().getEmballage() != null) {
                            Emballage embToStock = c.getArticle().getEmballage();
                            embToStock.setStock(embToStock.getStock() + c.getQuantite());
                            embToStock.setPrixTotal(embToStock.getStock() * embToStock.getPrix());
                            emballageFacade.MisAjour(embToStock);
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(gestionCommandes.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (request.getSession().getAttribute("emballageCommande") != null) {
                    List<CommandeEmballage> cmdE = (List<CommandeEmballage>) request.getSession().getAttribute("emballageCommande");
                    for (CommandeEmballage c : cmdE) {
                        c.setLigne(lg);

                        CompteEmballage cmdEmb = new CompteEmballage();
                        List<CompteEmballage> lastcmdEmb = compteEmballageFacade.findAllByCLient(lg.getClient().getId());
                        if (!lastcmdEmb.isEmpty()) {
                            lastcmdEmb.stream().filter((compteEmballage) -> (Objects.equals(compteEmballage.getEmballage().getId(), c.getId()))).map((compteEmballage) -> compteEmballage).map((cc) -> {
                                cc.setQuantite(cc.getQuantite() + c.getQuantite());
                                return cc;
                            }).map((cc) -> {
                                cc.setMontant(cc.getMontant() + (c.getQuantite() * c.getPrix()));
                                return cc;
                            }).forEachOrdered((cc) -> {

                                try {

                                    compteEmballageFacade.MisAjour(cc);
                                } catch (RollbackFailureException ex) {
                                    Logger.getLogger(gestionCommandes.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (Exception ex) {
                                    Logger.getLogger(gestionCommandes.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            });

                        } else {
                            cmdEmb.setClient(lg.getClient());
                            cmdEmb.setEmballage(c.getEmballage());
                            cmdEmb.setQuantite(c.getQuantite());
                            cmdEmb.setMontant(c.getQuantite() * c.getPrix());
                            compteEmballageFacade.creer(cmdEmb);
                        }
                    }
                }
            }
            request.getSession().removeAttribute("emballageCommande");
            request.getSession().removeAttribute("listCommandes");
            request.getSession().removeAttribute("lignecommande");
            request.getSession().removeAttribute("cmdclient");
            request.getSession().removeAttribute("njr");
            request.getSession().removeAttribute("listCommandes1");

            Message message = new Message();
            message.setType("success");
            message.setTitle("Nouvelle  commande");
            message.setSubject("Nouvelle commande");
            message.setMessage(lg.getClient().getNom() + " a passer une nouvelle commande N°= '" + lg.getId() + "'\n veuillez Traiter la commande pour livraison");
            message.setNotification("Votre commande a été enregistrer avec succès, N° commande : " + lg.getId());

            // sendNotification(message, lg.getClient().getSociete());
            request.setAttribute("message", message);

        } else {
            cl = tclientsFacade.findTclients(lg.getClient().getId());
            List<Tlignecommande> cmds = cl.getTlignecommandeList().stream().filter(in -> in.getEtatc().getCode() != 200).collect(Collectors.toList());
            Message message = new Message();
            message.setType("error");
            message.setTitle("Alert");
            message.setNotification("Impossible de passer une nouvelle commande sans validation des commandes encours");
            request.setAttribute("message", message);
            request.setAttribute("lastcommande", cmds);
        }

    }

    void AnnulerCommandes(List<Tcommandes> liste, HttpServletRequest request) {
        request.getSession().removeAttribute("emballageCommande");
        request.getSession().removeAttribute("listCommandes");
        request.getSession().removeAttribute("lignecommande");
        request.getSession().removeAttribute("listCommandes1");
        request.getSession().removeAttribute("cmdclient");
        request.getSession().removeAttribute("njr");
    }

    void SupprimerCommande(List<Tcommandes> liste, int id, HttpServletRequest request, Societe s) {
        List<Tcommandes> listCommandes = (List<Tcommandes>) request.getSession().getAttribute("listCommandes");
        Tcommandes c = getCommande(liste, id);
        if (s.getGestcassier() == 1) {
            if (c.getArticle().getCode().endsWith("C") || c.getArticle().getCode().endsWith("CP")) {
                c.getLigne().setCassier(c.getLigne().getCassier() - c.getQuantite());
            }
        }
        listCommandes.remove(c);

    }

    void SupprimerCommandeNew(int id, HttpServletRequest request, Societe s) {
        List<Tcommandes> listCommandes = (List<Tcommandes>) request.getSession().getAttribute("listCommandes");

        Tcommandes c = getCommande(listCommandes, id);
        if (s.getGestcassier() == 1) {
            if (c.getArticle().getCode().endsWith("C") || c.getArticle().getCode().endsWith("CP")) {
                Tlignecommande l = (Tlignecommande) request.getSession().getAttribute("lignecommande");
                l.setCassier(l.getCassier() - c.getQuantite());
                request.getSession().setAttribute("lignecommande", l);
            }
        }
        listCommandes.remove(getCommande(listCommandes, id));
        request.getSession().setAttribute("listCommandes", listCommandes);

    }

    void editionrCommande(List<Tcommandes> liste, HttpServletRequest request, Societe s) {
        Tcommandes r = getCommande(liste, Integer.parseInt(request.getParameter("id")));

        r.setId(0);

        for (int i = 0; i < liste.size(); i++) {
            if (liste.get(i).getArticle().getId().compareTo(r.getArticle().getId()) == 0) {
                liste.get(i).setId(0);
                liste.remove(r);
                break;
            }
        }
        if (r.getLigne() != null) {
            Tlignecommande lg = r.getLigne();
            lg.setDatemodif(date_du_jour.dateJour());
            r.setLigne(lg);
        }
        r.setArticle(tarticlesFacade.findTarticles(Integer.parseInt(request.getParameter("id"))));

        if (request.getParameter("quantite") != null) {
            if (s.getGestcassier() == 1) {
                if (r.getArticle().getCode().endsWith("C") || r.getArticle().getCode().endsWith("CP")) {
                    Tlignecommande l = (Tlignecommande) request.getSession().getAttribute("lignecommande");
                    l.setCassier(l.getCassier() - r.getQuantite());
                    l.setCassier(l.getCassier() + Integer.parseInt(request.getParameter("quantite")));
                    request.getSession().setAttribute("lignecommande", l);
                }
            }
            if (request.getParameter("prix") != null) {
                if (!request.getParameter("prix").isEmpty()) {
                    r.setPrix(Double.parseDouble(request.getParameter("prix")));
                    r.setRemise(r.getArticle().getPrix() - r.getPrix());
                } else {
                    r.setPrix(r.getArticle().getPrix());
                    r.setRemise(0.0);
                }
            }
            if (!request.getParameter("quantite").isEmpty()) {
                r.setQuantite(Double.parseDouble(request.getParameter("quantite")));
                if (r.getQt() == -1) {
                    r.setPrixTotal(r.getPrix() * r.getQuantite());
                } else {
                    r.setPrixTotal(r.getQuantite() * r.getArticle().getPrix());
                }
            }
        }

        if (request.getParameter("commantaire") != null) {
            if (!request.getParameter("commantaire").isEmpty()) {
                r.setCommantaire(request.getParameter("commantaire"));
            }
        }
        liste.add(r);
        request.setAttribute("commandes", liste);
        request.getSession().removeAttribute("listCommandes1");
    }

    private Tcommandes getCommande(List<Tcommandes> liste, int id) {
        Tcommandes c = null;
        for (Tcommandes cc : liste) {
            if (cc.getArticle().getId() == id) {
                c = cc;
                break;
            }
        }
        return c;
    }

    void PropositionCommandemodel(HttpServletRequest request) throws Exception {
        Tcommandes r;

        r = tcommandesFacade.findTcommandes(Integer.parseInt(request.getParameter("cc")));
        Tlignecommande lg = r.getLigne();
        lg.setDatemodif(date_du_jour.dateJour());

        r.setArticle(tarticlesFacade.findTarticles(Integer.parseInt(request.getParameter("article"))));
        // lg.setEtatc(tetatcFacade.findByCode(502));
        if (request.getParameter("njr") != null) {
            if (!request.getParameter("njr").isEmpty()) {
                lg.setDatelivraison(date_du_jour.TicketDelaisResolution(Integer.parseInt(request.getParameter("njr"))));
            }
        }

        if (request.getParameter("commantaire1") != null) {
            if (!request.getParameter("commantaire1").isEmpty()) {
                r.setCommantaire1(request.getParameter("commantaire1"));
            }
        }
        if (request.getParameter("qt") != null) {
            if (!request.getParameter("qt").isEmpty()) {
                r.setQt(Integer.parseInt(request.getParameter("qt")));
                r.setPrixTotal(r.getQt() * r.getArticle().getPrix());
            }
        }
        List<Tcommandes> listCommandes1;
        if (request.getSession().getAttribute("listCommandes1") == null) {
            listCommandes1 = new ArrayList<>();
            listCommandes1.addAll(tlignecommandeFacade.findTlignecommande(r.getLigne().getId()).getTcommandesList());
            request.getSession().setAttribute("listCommandes1", listCommandes1);
        }
        listCommandes1 = (List<Tcommandes>) request.getSession().getAttribute("listCommandes1");

        for (int i = 0; i < listCommandes1.size(); i++) {
            if (listCommandes1.get(i).getId().compareTo(r.getId()) == 0) {
                listCommandes1.get(i).setQt(r.getQt());
                listCommandes1.get(i).setCommantaire1(r.getCommantaire1());
                listCommandes1.get(i).setPrixTotal(r.getPrixTotal());
                listCommandes1.get(i).setLigne(lg);

                break;
            }
        }
        //r.setLigne(lg);
        request.getSession().setAttribute("listCommandes1", listCommandes1);
        request.getSession().setAttribute("lignecommande1", lg);
        request.setAttribute("commandes", listCommandes1);

    }

    void ValidationPropositionCommande(HttpServletRequest request) throws Exception {
        Tlignecommande r = tlignecommandeFacade.findTlignecommande(Integer.parseInt(request.getParameter("id")));
        r.setEtatc(tetatcFacade.findByCode(401));
        r.setPrixtotal(0.0);
        for (Tcommandes cc : r.getTcommandesList()) {
            if (cc.getQt() != 0) {
                cc.setQuantite(Double.parseDouble(cc.getQt().toString()));
            }
            cc.setPrix(cc.getArticle().getPrix());
            cc.setPrixTotal(cc.getQuantite() * cc.getPrix());
            r.setPrixtotal(cc.getPrixTotal() + r.getPrixtotal());
            cc = tcommandesFacade.MisAJour(cc);
        }
        r.setTcommandesList(tcommandesFacade.findAllByLigne(r.getId()));
        tlignecommandeFacade.MisAJour(r);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Message message = new Message();
        message.setType("success");
        message.setTitle("Proposition de commande");
        message.setSubject("Validation Proposition de commande");
        message.setMessage(r.getClient().getNom() + " a approuvé la proposition de  commande N°= '" + r.getId() + "'\n du " + df.format(r.getDatec()) + " veuillez valider la commande pour livraison");
        message.setNotification("Merci d'avoir valider la proposition vous serez livrer d'ici peu");

        //sendNotification(message, r.getClient().getSociete());
        request.setAttribute("message", message);
    }

    void EnregistrePropositionCommande(HttpServletRequest request) throws Exception {
        List<Tcommandes> listCommandes1 = (List<Tcommandes>) request.getSession().getAttribute("listCommandes1");
        Tlignecommande t = (Tlignecommande) request.getSession().getAttribute("lignecommande1");
        t.setEtatc(tetatcFacade.findByCode(502));
        t = tlignecommandeFacade.MisAJour(t);
        for (Tcommandes cc : listCommandes1) {
            tcommandesFacade.MisAJour(cc);
        }
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Message message = new Message();
        message.setType("success");
        message.setTitle("Proposition de commande");
        message.setSubject("Proposition de commande");
        message.setMessage("Vous avez reçu une proposition de commande de la commande  N°= '" + t.getId() + "'\n  du " + df.format(t.getDatec()) + " veuillez valider pour livraison");
        message.setNotification("Votre proposition de commande a été enregistrée avec succèss");

        sendNotification(message, t.getClient());
        request.getSession().removeAttribute("listCommandes1");
        request.getSession().removeAttribute("lignecommande1");
        request.setAttribute("message", message);
    }

    void MisAJourMarge(Societe s) throws RollbackFailureException, Exception {
        List<Tlignecommande> lg = tlignecommandeFacade.findAllBySociete(s.getId()).stream().
                filter(li -> li.getEtatc().getCode() == 201 || li.getEtatc().getCode() == 200).
                collect(Collectors.toList());
        for (Tlignecommande l : lg) {
            double totalMC = 0;
            double totalMF = 0;
            double total = 0;
            for (Tcommandes cc : l.getTcommandesList()) {
                try {
                    Tarticles a = cc.getArticle();
                    if (s.getGestmarge() == 1) {
                        cc.setMargeClient(a.getMargeClient() * cc.getQuantite());
                        totalMC += cc.getMargeClient();
                        cc.setMargeFournisseur(a.getMargeFournisseur() * cc.getQuantite());
                        totalMF += cc.getMargeFournisseur();
                        tcommandesFacade.MisAJour(cc);
                    }

                } catch (Exception ex) {
                    Logger.getLogger(gestionCommandes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            l.setMargeClient(totalMC);
            l.setMargeFournisseur(totalMF);
            //l.setPrixtotal(total);
            tlignecommandeFacade.MisAJour(l);
        }
    }

    void EditCommande201(HttpServletRequest request) throws Exception {
        Societe s = (Societe) request.getSession().getAttribute("societe");
        Tcommandes cc = tcommandesFacade.findTcommandes(Integer.parseInt(request.getParameter("idC")));
        Tlignecommande lg = cc.getLigne();
        lg.setDatemodif(date_du_jour.dateJour());
        Double qt = Double.parseDouble(request.getParameter("quantite"));
        Double qt1 = qt - cc.getQuantite();
        Double qcc = cc.getQuantite();
        if (s.getGestStock() == 1) {
            boolean insuffi = false;
            String arin = "";
            Tarticles a = cc.getArticle();
            if (a.getStock() < qt) {
                insuffi = true;
                arin = a.getNom();
            }

            if (!insuffi) {
                double totalMC = 0;
                double totalMF = 0;

                try {
                    if (lg.getSociete().getGestcassier() == 1) {
                        if (a.getCode().endsWith("C") || a.getCode().endsWith("CP")) {
                            lg.setCassier(lg.getCassier() - qcc);
                            lg.setCassier(lg.getCassier() + qt);
                        }
                    }
                    if (qt1 > 0) {
                        a.setStock(a.getStock() - qt1);
                        a = tarticlesFacade.MiSaJour(a);
                    } else {
                        a.setStock(a.getStock() + (-1 * qt1));

                    }
                    a = tarticlesFacade.MiSaJour(a);
                    cc.setQuantite(Double.parseDouble(request.getParameter("quantite")));
                    cc.setPrixTotal(cc.getQuantite() * cc.getPrix());
                    lg.setPrixtotal(lg.getPrixtotal() - (qcc * a.getPrix()));
                    lg.setPrixtotal(lg.getPrixtotal() + cc.getPrixTotal());
                    lg.setQtotal(lg.getQtotal() - qcc);
                    lg.setQtotal(lg.getQtotal() + cc.getQuantite());

                    if (s.getGestmarge() == 1) {
                        cc.setMargeClient(a.getMargeClient() * cc.getQuantite());
                        lg.setMargeClient(lg.getMargeClient() - (a.getMargeClient() * qcc));
                        lg.setMargeClient(lg.getMargeClient() + (a.getMargeClient() * cc.getQuantite()));
                        cc.setMargeFournisseur(a.getMargeFournisseur() * cc.getQuantite());
                        lg.setMargeFournisseur(lg.getMargeFournisseur() - (a.getMargeFournisseur() * qcc));
                        lg.setMargeFournisseur(lg.getMargeFournisseur() + (a.getMargeFournisseur() * cc.getQuantite()));
                        // tcommandesFacade.edit(cc);
                    }
                    tcommandesFacade.MisAJour(cc);
                } catch (RollbackFailureException ex) {
                    Logger.getLogger(gestionCommandes.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(gestionCommandes.class.getName()).log(Level.SEVERE, null, ex);
                }

                lg.setMargeClient(totalMC);
                lg.setMargeFournisseur(totalMF);
                tlignecommandeFacade.MisAJour(lg);
            } else {
                Message notif = new Message();
                notif.setType("error");
                notif.setTitle("Stock Article insuffisant");
                notif.setNotification("le Stock de l'article  " + arin + " est insuffisant pour traiter cette commande");
                request.setAttribute("message", notif);
            }
        } else {
            try {
                Tarticles a = cc.getArticle();
                cc.setQuantite(Double.parseDouble(request.getParameter("quantite")));
                cc.setPrixTotal(cc.getQuantite() * a.getPrix());
                lg.setPrixtotal(lg.getPrixtotal() - (qcc * a.getPrix()));
                lg.setPrixtotal(lg.getPrixtotal() + cc.getPrixTotal());
                lg.setQtotal(lg.getQtotal() - qcc);
                lg.setQtotal(lg.getQtotal() + cc.getQuantite());

                if (lg.getSociete().getGestcassier() == 1) {
                    if (a.getCode().endsWith("C") || a.getCode().endsWith("CP")) {
                        lg.setCassier(lg.getCassier() - qcc);
                        lg.setCassier(lg.getCassier() + qt);
                    }
                }
                if (s.getGestmarge() == 1) {
                    cc.setMargeClient(a.getMargeClient() * cc.getQuantite());
                    lg.setMargeClient(lg.getMargeClient() - (a.getMargeClient() * qcc));
                    lg.setMargeClient(lg.getMargeClient() + (a.getMargeClient() * cc.getQuantite()));
                    cc.setMargeFournisseur(a.getMargeFournisseur() * cc.getQuantite());
                    lg.setMargeFournisseur(lg.getMargeFournisseur() - (a.getMargeFournisseur() * qcc));
                    lg.setMargeFournisseur(lg.getMargeFournisseur() + (a.getMargeFournisseur() * cc.getQuantite()));
                    // tcommandesFacade.edit(cc);
                }

                tlignecommandeFacade.MisAJour(lg);
                tcommandesFacade.MisAJour(cc);
            } catch (RollbackFailureException ex) {
                Logger.getLogger(gestionCommandes.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(gestionCommandes.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    void ValidationCommandes(HttpServletRequest request) throws RollbackFailureException, Exception {
        Societe s = (Societe) request.getSession().getAttribute("societe");
        Tlignecommande r = tlignecommandeFacade.findTlignecommande(Integer.parseInt(request.getParameter("id")));
        if (r.getEtatc().getCode() != 201 && r.getEtatc().getCode() != 200) {
            r.setEtatc(tetatcFacade.findByCode(201));
            r.setPrixtotal(0.0);
            r.setPrixtotal(r.getPrixtotal() + r.getTransport());
            if (r.getCommandeEmballageList() != null) {
                for (CommandeEmballage commandeEmballage : r.getCommandeEmballageList()) {
                    r.setPrixtotal(commandeEmballage.getPrixTotal() + r.getPrixtotal());
                }
            }
            if (s.getGestStock() == 1) {
                boolean insuffi = false;
                String arin = "";
                for (Tcommandes cc : r.getTcommandesList()) {
                    Tarticles a = cc.getArticle();
                    if (a.getCode().startsWith("VRACC") || a.getCode().startsWith("VIP") || a.getCode().startsWith("VRAP")) {

                    } else {
                        if (a.getStock() < cc.getQuantite()) {
                            insuffi = true;
                            arin = a.getNom();
                            break;
                        }
                    }

                }
                if (!insuffi) {
                    double totalMC = 0;
                    double totalMF = 0;

                    for (Tcommandes cc : r.getTcommandesList()) {
                        try {
                            Tarticles a = cc.getArticle();
                            a.setStock(a.getStock() - cc.getQuantite());
                            a = tarticlesFacade.MiSaJour(a);
                            if (s.getGestmarge() == 1) {
                                cc.setMargeClient(a.getMargeClient() * cc.getQuantite());
                                //totalMC += cc.getMargeClient();
                                cc.setMargeFournisseur(a.getMargeFournisseur() * cc.getQuantite());
                                // totalMF += cc.getMargeFournisseur();
                                r.setMargeClient(r.getMargeClient() + cc.getMargeClient());
                                r.setMargeFournisseur(r.getMargeFournisseur() + cc.getMargeFournisseur());
                            }
                            if (cc.getQt() == -1) {
                                cc.setPrixTotal(cc.getPrix() * cc.getQuantite());
                                cc.setQt(0);
                            } else {
                                cc.setPrixTotal(a.getPrix() * cc.getQuantite());
                            }
                            r.setPrixtotal(r.getPrixtotal() + cc.getPrixTotal());
                            tcommandesFacade.MisAJour(cc);
                        } catch (RollbackFailureException ex) {
                            Logger.getLogger(gestionCommandes.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (Exception ex) {
                            Logger.getLogger(gestionCommandes.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    tlignecommandeFacade.MisAJour(r);
                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    Message message = new Message();
                    message.setType("success");
                    message.setTitle("validation reussie");
                    message.setSubject("validation de commande");
                    message.setMessage("Votre commande N°= '" + r.getId() + "'\n du <strong>" + df.format(r.getDatec()) + "</strong> a été validée merci de patienter la livraison");
                    message.setNotification("Commande validée avec succès ");

                    //sendNotification(message, r.getClient());
                    request.setAttribute("message", message);
                } else {

                    Message notif = new Message();
                    notif.setType("error");
                    notif.setTitle("Stock Article insuffisant");
                    notif.setNotification("le Stock de l'article  " + arin + " est insuffisant pour traiter cette commande");
                    request.setAttribute("message", notif);
                }
            } else {
                double totalMC = 0;
                double totalMF = 0;
                try {
                    for (Tcommandes cc : r.getTcommandesList()) {
                        Tarticles a = cc.getArticle();
                        if (s.getGestmarge() == 1) {
                            cc.setMargeClient(a.getMargeClient() * cc.getQuantite());
                            // totalMC += cc.getMargeClient();
                            cc.setMargeFournisseur(a.getMargeFournisseur() * cc.getQuantite());
                            // totalMF += cc.getMargeFournisseur();
                            r.setMargeClient(r.getMargeClient() + cc.getMargeClient());
                            r.setMargeFournisseur(r.getMargeFournisseur() + cc.getMargeFournisseur());

                        }
                        cc.setPrixTotal(a.getPrix() * cc.getQuantite());
                        r.setPrixtotal(r.getPrixtotal() + cc.getPrixTotal());
                        tcommandesFacade.MisAJour(cc);
                    }
                    tlignecommandeFacade.MisAJour(r);
                } catch (RollbackFailureException ex) {
                    Logger.getLogger(gestionCommandes.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(gestionCommandes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    void CloturationCommandes(HttpServletRequest request) throws Exception {
        Tlignecommande r = tlignecommandeFacade.findTlignecommande(Integer.parseInt(request.getParameter("id")));
        r.setEtatc(tetatcFacade.findByCode(200));
        tlignecommandeFacade.MisAJour(r);
        for (Tcommandes cc : r.getTcommandesList()) {
            tcommandesFacade.MisAJour(cc);
        }
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Message message = new Message();
        message.setType("success");
        message.setTitle("Validation de commande");
        message.setSubject("Validation de commande");
        message.setMessage("<strong>" + r.getClient().getNom() + "</strong> a réceptionné avec succès  la  commande N°= '" + r.getId() + "'\n du <strong>" + df.format(r.getDatec()) + "</strong> avec pour réference : <strong>" + r.getId() + "</strong>");
        message.setNotification("Merci d'avoir receptionné nos produits ");

        sendNotification(message, r.getClient().getSociete());
        request.setAttribute("message", message);
    }

    void CloturationAllCommandes(HttpServletRequest request, Societe societe) throws Exception {
        String[] idc = request.getParameterValues("id");
        for (String str : idc) {
            Tlignecommande r = tlignecommandeFacade.findTlignecommande(Integer.parseInt(str));
            r.setEtatc(tetatcFacade.findByCode(200));
            r.setStatut(1);
            tlignecommandeFacade.MisAJour(r);
            for (Tcommandes cc : r.getTcommandesList()) {
                tcommandesFacade.MisAJour(cc);
            }
        }
        request.setAttribute("commandes", tlignecommandeFacade.findAll(societe.getImmatriculation()));
    }

    int genererMumC(List<Tcommandes> listCommandes1) {
        boolean repeat = true;
        int i = 0;
        while (repeat) {
            int id = (int) (Math.random() * 100000);
            if (!listCommandes1.contains(new Tcommandes(id))) {
                i = id;
                repeat = false;
            }

        }
        return i;
    }

    void sendNotification(Message message, Tusers p) {
        Notification n = new Notification();
        n.setMessage(message.getNotification());
        n.setUser(p.getId());
        n.setLut(0);
        n.setTitel(message.getTitle());
        n.setDateNotif(date_du_jour.dateJour());
        notificationFacade.create(n);
        try {
            EnvoiEmail.sendMail(p.getMail(), message.getSubject(), message.getMessage());
        } catch (Exception ex) {
            System.err.println("Echec Notification par Mail");
        }
    }

    void CopyArticle() {
        //List<Tarticles> ars11 = tarticlesFacade.findAllBySociete(6672); 5611
        List<Tarticles> ars2 = tarticlesFacade.findAllBySociete(5917);
        for (Tarticles a : ars2) {
            Tarticles ar = compartCodeArt(a.getCode());
            if (ar != null) {
                ar.setPrix(a.getPrix());
                tarticlesFacade.edit(ar);
            }

        }

    }

    Tarticles compartCodeArt(String code) {
        List<Tarticles> ars11 = tarticlesFacade.findAllBySociete(5611);
        for (Tarticles ca : ars11) {
            if (ca.getCode().equalsIgnoreCase(code)) {
                System.out.println(true);
                return ca;
            }
        }
        return null;
    }

    void UpDateLG(Societe s) {
        List<Tlignecommande> ars11 = tlignecommandeFacade.findAllBySociete(s.getId());
        for (Tlignecommande lg : ars11) {
            double mx = 0;
            for (Tcommandes cc : lg.getTcommandesList()) {
                cc.setPrix(cc.getArticle().getPrix());
                cc.setPrixTotal(cc.getQuantite() * cc.getPrix());
                mx += cc.getPrixTotal();
                tcommandesFacade.edit(cc);
            }
            lg.setPrixtotal(mx);
            tlignecommandeFacade.edit(lg);
        }

    }

    List<Tlignecommande> HistoriqueCommandeByTourner(HttpServletRequest request, Societe societe) {
        List<Tlignecommande> listCommandesH;
        listCommandesH = tlignecommandeFacade.findAll(societe.getImmatriculation()).stream().
                filter(lg -> lg.getTourner().getNumc().equals(request.getParameter("tourner")))
                .collect(Collectors.toList());
        request.setAttribute("messageTable", "Commandes de toutes les Tournées");
        return listCommandesH;
    }

    List<Tlignecommande> HistoriqueCommandeByTournerByEtatByPeriode(HttpServletRequest request, Societe societe) {
        int etat = Integer.parseInt(request.getParameter("statut"));
        String[] date1 = request.getParameter("interval").split("-");
        Date d1 = date_du_jour.dateConvert(date1[0]);
        Date d2 = date_du_jour.dateConvert(date1[1]);
        Tourner tr = tournerFacade.findTourner(Integer.parseInt(request.getParameter("tourner")));
        List<Tlignecommande> listCommandesH;
        if (etat == 0) {
            listCommandesH = tlignecommandeFacade.findTcommandesByPeriode(d1, d2, societe.getImmatriculation()).stream()
                    .filter(in -> in.getTourner().getNumc().equals(tr.getNumc()))
                    .collect(Collectors.toList());
        } else {
            listCommandesH = tlignecommandeFacade.findTcommandesByPeriode(d1, d2, societe.getImmatriculation()).stream()
                    .filter(in -> in.getEtatc().getCode() == Integer.parseInt(request.getParameter("statut"))
                    && in.getTourner().getNumc().equals(tr.getNumc()))
                    .collect(Collectors.toList());
        }
        request.setAttribute("messageTable", "Tourner : " + tr.getNumc() + " du " + date1[0] + " au " + date1[1]);
        return listCommandesH;
    }

    int compart(List<Tcategorie> cat, String str2) {
        int respo = 0;
        for (Tcategorie ca : cat) {
            if (ca.getNom().equalsIgnoreCase(str2)) {
                respo = ca.getId();

                break;
            }
        }
        return respo;
    }

    void Notification(Message message, Tusers p) {
        Notification n = new Notification();
        n.setMessage(message.getNotification());
        n.setUser(p.getId());
        n.setLut(0);
        n.setTitel(message.getTitle());
        n.setDateNotif(date_du_jour.dateJour());
        notificationFacade.create(n);
    }

    void sendNotification(Message message, Tclients p) {
        Notification n = new Notification();
        n.setMessage(message.getNotification());
        n.setClient(p.getId());
        n.setLut(0);
        n.setTitel(message.getTitle());
        n.setDateNotif(date_du_jour.dateJour());
        notificationFacade.create(n);

        try {

            EnvoiEmail.sendMail(p.getMail(), message.getSubject(), message.getMessage());
        } catch (Exception ex) {
            System.err.println("Echec Notification par Mail");
        }
    }

    void sendNotification(Message message, Societe s) {
        try {

            EnvoiEmail.sendMail(s.getEmail(), message.getSubject(), message.getMessage());
        } catch (Exception ex) {
            System.err.println("Echec Notification par Mail");
        }
    }

    private void saveVentes(List<Tcommandes> liste, Tlignecommande lg1, HttpServletRequest request) throws RollbackFailureException, Exception {
        Tclients cl = tclientsFacade.findTclients(lg1.getClient().getId());
        lg1.setEtatc(tetatcFacade.findByCode(201));
        lg1.setStatut(0);
        lg1.setPrixtotal(0.0);
        if (lg1.getTransport() != null) {
            lg1.setPrixtotal(lg1.getPrixtotal() + lg1.getTransport());
        }

        boolean insuffi = false;
        for (Tcommandes cc : liste) {
            Tarticles a = cc.getArticle();
            if (a.getStock() < cc.getQuantite()) {
                insuffi = true;
                break;
            }

        }
        if (!insuffi) {
            for (Tcommandes cc : liste) {
                Tarticles a = cc.getArticle();
                if (cl.getSociete().getGestmarge() == 1) {
                    cc.setMargeClient(a.getMargeClient() * cc.getQuantite());
                    //totalMC += cc.getMargeClient();
                    cc.setMargeFournisseur(a.getMargeFournisseur() * cc.getQuantite());
                    // totalMF += cc.getMargeFournisseur();
                    lg1.setMargeClient(lg1.getMargeClient() + cc.getMargeClient());
                    lg1.setMargeFournisseur(lg1.getMargeFournisseur() + cc.getMargeFournisseur());
                }
            }

            if (cl.getSociete().getGestemballage() == 1) {
                Double qtar = 0.0;
                try {
                    List<Tcommandes> tc = new ArrayList<>();
                    lg1.setCassier(qtar);
                    lg1.setRemise(0.0);
                    for (Tcommandes cc : liste) {
                        if (cc.getArticle().getEmballage() != null) {
                            qtar += cc.getQuantite();

                        }
                        Tarticles a = cc.getArticle();
                        a.setStock(a.getStock() - cc.getQuantite());
                        a = tarticlesFacade.MiSaJour(a);

                        lg1.setRemise(lg1.getRemise() + cc.getRemise());
                        lg1.setPrixtotal(lg1.getPrixtotal() + cc.getPrixTotal());
                    }
                    lg1.setQtotal(qtar);
                    lg1.setCassier(lg1.getCassier() + qtar);
                    for (Tcommandes c : liste) {
                        if (request.getParameter("commantaire2") != null) {
                            if (!request.getParameter("commantaire2").isEmpty()) {
                                lg1.setCommentaire(request.getParameter("commantaire2"));
                            }
                        }
                    }
                    lg1 = tlignecommandeFacade.creer(lg1);
                } catch (Exception ex) {
                    Logger.getLogger(gestionCommandes.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    lg1.setQtotal(0.0);
                    for (Tcommandes c : liste) {
                        lg1.setQtotal(lg1.getQtotal() + c.getQuantite());
                        lg1.setPrixtotal(c.getPrixTotal() + lg1.getPrixtotal());
                        if (request.getParameter("commantaire2") != null) {
                            if (!request.getParameter("commantaire2").isEmpty()) {
                                lg1.setCommentaire(request.getParameter("commantaire2"));
                            }
                        }
                    }

                    lg1 = tlignecommandeFacade.creer(lg1);
                } catch (Exception ex) {
                    Logger.getLogger(gestionCommandes.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (cl.getSociete().getGestemballage() == 1) {
                // entrée des emballages en stocks
                for (Tcommandes c : liste) {
                    try {
                        if (c.getArticle().getEmballage() != null) {
                            Emballage embToStock = c.getArticle().getEmballage();
                            embToStock.setStock(embToStock.getStock() + c.getQuantite());
                            embToStock.setPrixTotal(embToStock.getStock() * embToStock.getPrix());
                            emballageFacade.MisAjour(embToStock);
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(gestionCommandes.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (request.getSession().getAttribute("emballageCommande") != null) {
                    List<CommandeEmballage> cmdE = (List<CommandeEmballage>) request.getSession().getAttribute("emballageCommande");
                    for (CommandeEmballage c : cmdE) {
                        lg1.setPrixtotal(lg1.getPrixtotal() + c.getPrixTotal());
                        lg1 = tlignecommandeFacade.MisAJour(lg1);
                    }
                    for (CommandeEmballage c : cmdE) {
                        System.out.println("cTotal " + c.getPrixTotal());
                        Emballage emb = c.getEmballage();
                        emb.setStock(emb.getStock() - c.getQuantite());
                        emb.setPrixTotal(emb.getStock() * emb.getPrix());
                        emballageFacade.MisAjour(emb);
                        CommandeEmballage cs = new CommandeEmballage();
                        c.setLigne(lg1);
                        cs.setEmballage(emb);
                        cs.setLigne(lg1);
                        cs.setPrix(c.getPrix());
                        cs.setPrixTotal(c.getPrixTotal());
                        cs.setQuantite(c.getQuantite());
                        cs.setQt(0.0);
                        // commandeEmballageFacade.create(c);
                        CompteEmballage cmdEmb = new CompteEmballage();

                        commandeEmballageFacade.creer(cs);
                        List<CompteEmballage> lastcmdEmb = compteEmballageFacade.findAllByCLient(lg1.getClient().getId());
                        boolean isnewComptet = true;
                        for (CompteEmballage compteEmballage : lastcmdEmb) {
                            if (Objects.equals(compteEmballage.getEmballage().getId(), c.getEmballage().getId())) {

                                CompteEmballage cc = compteEmballage;
                                cc.setQuantite(cc.getQuantite() + c.getQuantite());
                                cc.setMontant(cc.getMontant() + (c.getQuantite() * c.getPrix()));
                                try {
                                    compteEmballageFacade.MisAjour(cc);
                                } catch (RollbackFailureException ex) {
                                    Logger.getLogger(gestionCommandes.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (Exception ex) {
                                    Logger.getLogger(gestionCommandes.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                isnewComptet = false;
                                break;
                            }
                        }
                        if (isnewComptet) {
                            cmdEmb.setClient(lg1.getClient());
                            cmdEmb.setEmballage(c.getEmballage());
                            cmdEmb.setQuantite(c.getQuantite());
                            cmdEmb.setMontant(c.getQuantite() * c.getPrix());
                            compteEmballageFacade.creer(cmdEmb);
                        }
//                        if (!lastcmdEmb.isEmpty()) {
//                            lastcmdEmb.stream().filter((compteEmballage) -> (Objects.equals(compteEmballage.getEmballage().getId(), c.getEmballage().getId()))).map((compteEmballage) -> compteEmballage).map((cc) -> {
//                                cc.setQuantite(cc.getQuantite() + c.getQuantite());
//                                return cc;
//                            }).map((cc) -> {
//                                cc.setMontant(cc.getMontant() + (c.getQuantite() * c.getPrix()));
//                                return cc;
//                            }).forEachOrdered((cc) -> {
//                                try {
//                                    compteEmballageFacade.MisAjour(cc);
//                                } catch (RollbackFailureException ex) {
//                                    Logger.getLogger(gestionCommandes.class.getName()).log(Level.SEVERE, null, ex);
//                                } catch (Exception ex) {
//                                    Logger.getLogger(gestionCommandes.class.getName()).log(Level.SEVERE, null, ex);
//                                }
//                            });
//
//                        } else {
//                            System.out.println("Compte Emballage" + c.getEmballage());
//                            cmdEmb.setClient(lg1.getClient());
//                            cmdEmb.setEmballage(c.getEmballage());
//                            cmdEmb.setQuantite(c.getQuantite());
//                            cmdEmb.setMontant(c.getQuantite() * c.getPrix());
//                            compteEmballageFacade.creer(cmdEmb);
//                        }
                    }
                }
                for (Tcommandes c : liste) {
                    try {
                        c.setLigne(lg1);
                        c = tcommandesFacade.creer(c);
                    } catch (Exception ex) {
                        Logger.getLogger(gestionCommandes.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            request.getSession().removeAttribute("emballageCommande");
            request.getSession().removeAttribute("listCommandes");
            request.getSession().removeAttribute("lignecommande");
            request.getSession().removeAttribute("cmdclient");
            request.getSession().removeAttribute("njr");
            request.getSession().removeAttribute("listCommandes1");

            Message message = new Message();
            message.setType("success");
            message.setTitle("Nouvelle  commande");
            message.setSubject("Nouvelle commande");
            message.setMessage(lg1.getClient().getNom() + " a passer une nouvelle commande N°= '" + lg1.getId() + "'\n veuillez Traiter la commande pour livraison");
            message.setNotification("Votre commande a été enregistrer avec succès, N° commande : " + lg1.getId());
            request.setAttribute("message", message);
        } else {
            Message message = new Message();
            message.setType("error");
            message.setTitle("Stock Insuffisant");
            message.setMessage("Certains articles sont insuffisant pour traiter cette commande ");
            message.setNotification("Certains articles sont insuffisant pour traiter cette commande ");
            request.setAttribute("message", message);
            request.setAttribute("commandes", liste);
        }

    }

    private void SupprimerVente(int id, HttpServletRequest request, Societe societe) throws RollbackFailureException, Exception {
        Tlignecommande ligne = tlignecommandeFacade.find(id);
        Tclients cl = ligne.getClient();
        if (ligne.getEtatc().getCode() != 300) {
            if (ligne.getTcommandesList() != null) {
                for (Tcommandes tcommandes : ligne.getTcommandesList()) {
                    Tarticles ar = tcommandes.getArticle();
                    ar.setStock(ar.getStock() + tcommandes.getQuantite());
                    tarticlesFacade.MiSaJour(ar);

                }
            }
            if (ligne.getCommandeEmballageList() != null) {
                for (CommandeEmballage commandeEmballage : ligne.getCommandeEmballageList()) {
                    Emballage emb = commandeEmballage.getEmballage();
                    emb.setStock(emb.getStock() + commandeEmballage.getQuantite());
                    emb.setPrixTotal(emb.getPrix() * emb.getStock());
                    emballageFacade.MisAjour(emb);
                    for (CompteEmballage ce : cl.getCompteEmballageList()) {
                        if (Objects.equals(commandeEmballage.getEmballage().getId(), ce.getEmballage().getId())) {
                            ce.setQuantite(ce.getQuantite() - commandeEmballage.getQuantite());
                            ce.setMontant(ce.getQuantite() * ce.getEmballage().getPrix());
                            compteEmballageFacade.MisAjour(ce);
                        }
                    }

                }
            }
            tlignecommandeFacade.Supprimer(id);

        } else {
            Message message = new Message();
            message.setType("error");
            message.setTitle("ALERT!!!");
            message.setMessage("Cette commande a déjà été facturée impossible de la supprimer");
            message.setNotification("Cette commande a déjà été facturée impossible de la supprimer");
            request.setAttribute("message", message);
        }
    }

    private void updateVente(HttpServletRequest request, Societe societe) throws Exception {
        Tlignecommande ligne = tlignecommandeFacade.findTlignecommande(Integer.parseInt(request.getParameter("id")));
        Tcommandes cmd = new Tcommandes();
        Tarticles art = new Tarticles();
        Emballage embToStock = new Emballage();
        Double quantite = Double.parseDouble(request.getParameter("quantite"));
        if (Integer.parseInt(request.getParameter("isNew")) == 1) {
            art = tarticlesFacade.find(Integer.parseInt(request.getParameter("article")));
            if (art.getEmballage() != null) {
                embToStock = art.getEmballage();
            }
            art.setStock(art.getStock() - quantite);
        } else {
            cmd = tcommandesFacade.find(Integer.parseInt(request.getParameter("idC")));
            ligne.setPrixtotal(ligne.getPrixtotal() - cmd.getPrixTotal());
            ligne.setMargeClient(ligne.getMargeClient() - cmd.getMargeClient());
            ligne.setMargeFournisseur(ligne.getMargeFournisseur() - cmd.getMargeFournisseur());
            art = cmd.getArticle();
            art.setStock(art.getStock() + cmd.getQuantite());
            art.setStock(art.getStock() - quantite);
            if (art.getEmballage() != null) {
                embToStock = art.getEmballage();
                embToStock.setStock(embToStock.getStock() - cmd.getQuantite());
            }

        }
        boolean exist = false;

        if (Integer.parseInt(request.getParameter("isNew")) == 1) {
            embToStock.setStock(embToStock.getStock() + quantite);
            for (Tcommandes tcommandes : ligne.getTcommandesList()) {
                if (Objects.equals(tcommandes.getArticle().getId(), art.getId())) {
                    exist = true;
                    break;
                }
            }
        }
        if (!exist) {
            if (art.getStock() >= cmd.getQuantite()) {

                cmd.setPrix(art.getPrix());
                cmd.setQuantite(quantite);
                if (!request.getParameter("prix_divers").isEmpty()) {
                    ligne.setRemise(ligne.getRemise() - cmd.getRemise());
                    cmd.setPrix(Double.parseDouble(request.getParameter("prix_divers")));
                    cmd.setRemise(cmd.getArticle().getPrix() - cmd.getPrix());
                    ligne.setRemise(ligne.getRemise() + cmd.getRemise());
                }
                cmd.setPrixTotal(cmd.getPrix() * cmd.getQuantite());
                cmd.setMargeClient(cmd.getQuantite() * art.getMargeClient());
                cmd.setMargeFournisseur(cmd.getQuantite() * art.getMargeFournisseur());
                cmd.setQt(0);
                ligne.setMargeClient(ligne.getMargeClient() + cmd.getMargeClient());
                ligne.setMargeFournisseur(ligne.getMargeFournisseur() + cmd.getMargeFournisseur());
                ligne.setPrixtotal(ligne.getPrixtotal() + cmd.getPrixTotal());
                ligne = tlignecommandeFacade.MisAJour(ligne);
                art = tarticlesFacade.MiSaJour(art);
                if (Integer.parseInt(request.getParameter("isNew")) == 1) {
                    cmd.setLigne(ligne);
                    cmd.setArticle(art);

                    embToStock.setPrixTotal(embToStock.getStock() * embToStock.getPrix());
                    emballageFacade.MisAjour(embToStock);
                    cmd = tcommandesFacade.creer(cmd);
                } else {
                    embToStock.setStock(embToStock.getStock() + cmd.getQuantite());
                    embToStock.setPrixTotal(embToStock.getStock() * embToStock.getPrix());
                    emballageFacade.MisAjour(embToStock);
                    cmd = tcommandesFacade.MisAJour(cmd);
                }

            } else {
                Message message = new Message();
                message.setType("infos");
                message.setTitle("ATTENTION !!!");
                message.setMessage("Vous ne disposez pas assez de stock pour ajouter ce produit");
                message.setNotification("Vous ne disposez pas assez de stock pour ajouter ce produit");
                request.setAttribute("message", message);
            }
        } else {
            Message message = new Message();
            message.setType("error");
            message.setTitle("ALERT!!!");
            message.setMessage("Cette Article existe déjà dans cette commande");
            message.setNotification("Cette Article existe déjà dans cette commande");
            request.setAttribute("message", message);
        }
        request.setAttribute("isarticle", "yes");
    }

    private void updateEmballage(HttpServletRequest request, Societe societe) throws Exception {
        Tlignecommande ligne = tlignecommandeFacade.findTlignecommande(Integer.parseInt(request.getParameter("id")));
        CommandeEmballage cmd = new CommandeEmballage();
        Tclients cl = ligne.getClient();
        Emballage art = new Emballage();
        CompteEmballage compte = new CompteEmballage();
        compte.setId(0);
        Double quantite = Double.parseDouble(request.getParameter("quantite"));
        if (Integer.parseInt(request.getParameter("isNew")) == 5) {
            art = emballageFacade.find(Integer.parseInt(request.getParameter("article")));
            art.setStock(art.getStock() - quantite);
            for (CompteEmballage compteEmballage : cl.getCompteEmballageList()) {
                if (Objects.equals(compteEmballage.getEmballage().getId(), art.getId())) {
                    compte = compteEmballage;
                    break;
                }
            }
        } else {
            cmd = commandeEmballageFacade.find(Integer.parseInt(request.getParameter("idC")));
            ligne.setPrixtotal(ligne.getPrixtotal() - cmd.getPrixTotal());
            art = cmd.getEmballage();
            art.setStock(art.getStock() + cmd.getQuantite());
            art.setStock(art.getStock() - quantite);
            for (CompteEmballage compteEmballage : cl.getCompteEmballageList()) {
                if (Objects.equals(compteEmballage.getEmballage().getId(), art.getId())) {
                    compte = compteEmballage;
                    break;
                }
            }
        }
        art.setPrixTotal(art.getStock() * art.getPrix());
        boolean exist = false;

        if (Integer.parseInt(request.getParameter("isNew")) == 5) {
            for (CommandeEmballage tcommandes : ligne.getCommandeEmballageList()) {
                if (Objects.equals(tcommandes.getEmballage().getId(), art.getId())) {
                    exist = true;
                    break;
                }
            }
        }
        if (!exist) {
            if (art.getStock() >= 0.0) {

                cmd.setPrix(art.getPrix());
                cmd.setQuantite(quantite);
                if (!request.getParameter("prix_divers").isEmpty()) {
                    ligne.setRemise(ligne.getRemise() - cmd.getRemise());
                    cmd.setPrix(Double.parseDouble(request.getParameter("prix_divers")));
                    cmd.setRemise(cmd.getEmballage().getPrix() - cmd.getPrix());
                    ligne.setRemise(ligne.getRemise() + cmd.getRemise());
                }
                cmd.setPrixTotal(cmd.getPrix() * cmd.getQuantite());
                ligne.setPrixtotal(ligne.getPrixtotal() + cmd.getPrixTotal());
                tlignecommandeFacade.MisAJour(ligne);
                art = emballageFacade.MisAjour(art);
                if (Integer.parseInt(request.getParameter("isNew")) == 5) {
                    cmd.setEmballage(art);
                    cmd.setLigne(ligne);
                    cmd = commandeEmballageFacade.creer(cmd);

                } else {
                    compte.setQuantite(compte.getQuantite() - cmd.getQuantite());
                    cmd = commandeEmballageFacade.MisAjour(cmd);
                }
                //mettre à jour le compte emballage
                if (compte.getId() != 0) {
                    compte.setQuantite(compte.getQuantite() - cmd.getQuantite());
                    compte.setQuantite(compte.getQuantite() + quantite);
                    compte.setMontant(compte.getQuantite() * compte.getEmballage().getPrix());
                    compteEmballageFacade.MisAjour(compte);
                } else {
                    compte.setQuantite(quantite);
                    compte.setEmballage(art);
                    compte.setClient(cl);
                    compte.setMontant(compte.getQuantite() * compte.getEmballage().getPrix());
                    compte = compteEmballageFacade.creer(compte);
                }
            } else {
                Message message = new Message();
                message.setType("infos");
                message.setTitle("ATTENTION !!!");
                message.setMessage("Vous ne disposez pas assez de stock pour ajouter ce produit");
                message.setNotification("Vous ne disposez pas assez de stock pour ajouter ce produit");
                request.setAttribute("message", message);
            }
        } else {
            Message message = new Message();
            message.setType("error");
            message.setTitle("ALERT!!!");
            message.setMessage("Cette Emballage existe déjà dans cette commande");
            message.setNotification("Cette Emballage existe déjà dans cette commande");
            request.setAttribute("message", message);
        }
        request.setAttribute("isarticle", "no");
    }

    void ChangeInfosCommandeClient(HttpServletRequest request) throws RollbackFailureException, Exception {
        int idlg = Integer.parseInt(request.getParameter("id"));
        Tlignecommande ligne = tlignecommandeFacade.findTlignecommande(idlg);
        if (request.getParameter("client") != null && !request.getParameter("client").isEmpty()) {
            int id = Integer.parseInt(request.getParameter("client"));
            Tclients cli = tclientsFacade.findTclients(id);
            ligne.setClient(cli);
        }
        if (request.getParameter("njr") != null && !request.getParameter("njr").isEmpty()) {
            ligne.setDatelivraison(date_du_jour.dateConvert(request.getParameter("njr")));
        }
        if (request.getParameter("statut") != null && !request.getParameter("statut").isEmpty()) {
            int statut = Integer.parseInt(request.getParameter("statut"));
            ligne.setEtatc(tetatcFacade.findByCode(statut));
        }
        ligne = tlignecommandeFacade.MisAJour(ligne);
        request.setAttribute("ligneCommande", ligne);
        request.setAttribute("isarticle", "yes");
        request.setAttribute("commandes", ligne.getTcommandesList());
    }

}
