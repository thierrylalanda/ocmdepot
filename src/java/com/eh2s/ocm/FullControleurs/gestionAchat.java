/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.FullControleurs;

import com.eh2s.ocm.Entity.CommandeFournisseur;
import com.eh2s.ocm.Entity.Emballage;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Fournisseur;
import com.eh2s.ocm.Entity.Inventaire;
import com.eh2s.ocm.Entity.LigneCommandeFournisseur;
import com.eh2s.ocm.Entity.LigneInventaire;
import com.eh2s.ocm.Entity.Magasin;
import com.eh2s.ocm.Entity.Services.Message;
import com.eh2s.ocm.Entity.Services.ServicesInitialize;
import com.eh2s.ocm.Entity.Sessions.ActionAPKFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.CommandeFournisseurFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.EmballageFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.FournisseurFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.InventaireFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.LigneCommandeFournisseurFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.LigneInventaireFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.LigneSortieFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.MagasinFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.SocieteFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.StockMgFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TactionsFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TarticlesFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TcategorieFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TetatcFacadeLocal;
import com.eh2s.ocm.Entity.Societe;
import com.eh2s.ocm.Entity.StockMg;
import com.eh2s.ocm.Entity.Tarticles;
import com.eh2s.ocm.Entity.Tcategorie;
import com.eh2s.ocm.Entity.Tclients;
import com.eh2s.ocm.Entity.Tusers;
import com.eh2s.ocm.UtilityFonctions.date_du_jour;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;
import org.json.JSONException;

/**
 *
 * @author Administrateur
 */
@MultipartConfig(fileSizeThreshold = 6291456, // 6 MB
        maxFileSize = 10485760L, // 10 MB
        maxRequestSize = 20971520L, // 20 MB
        location = "/"
)
@WebServlet(name = "gestionAchat", urlPatterns = {"/gestionachat"})
public class gestionAchat extends HttpServlet {

    @EJB
    private LigneSortieFacadeLocal ligneSortieFacade;

    @EJB
    private TetatcFacadeLocal tetatcFacade1;

    @EJB
    private TetatcFacadeLocal tetatcFacade;

    @EJB
    private FournisseurFacadeLocal fournisseurFacade;

    @EJB
    private CommandeFournisseurFacadeLocal commandeFournisseurFacade;

    @EJB
    private LigneCommandeFournisseurFacadeLocal ligneCommandeFournisseurFacade;
    @EJB
    private EmballageFacadeLocal emballageFacade;

    @EJB
    private SocieteFacadeLocal societeFacade;

    @EJB
    private TcategorieFacadeLocal tcategorieFacade;

    @EJB
    private TactionsFacadeLocal tactionsFacade;

    @EJB
    private TarticlesFacadeLocal tarticlesFacade;

    @EJB
    private StockMgFacadeLocal stockMgFacade;

    @EJB
    private LigneInventaireFacadeLocal ligneInventaireFacade;

    @EJB
    private InventaireFacadeLocal inventaireFacade;

    @EJB
    private MagasinFacadeLocal magasinFacade;

    @EJB
    private ServicesInitialize servicesInitializeImple;
    @PersistenceContext(unitName = "ccmanagerPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;

    @EJB
    private ActionAPKFacadeLocal actionAPKFacade;

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
            request.setAttribute("actionAPK", actionAPKFacade.findAll());
            String actions = request.getParameter("action");
            request.setAttribute("q", request.getParameter("q"));
            switch (actions) {
                case "model":
                    String model = request.getParameter("model");
                    switch (model) {
                        case "init":
                            request.setAttribute("achats", ligneCommandeFournisseurFacade.findBonCommandeByPeriode(date_du_jour.dateJourUniqueDate(), date_du_jour.dateJourUniqueDate(), societe.getId()));
                            break;
                        case "achat":
                            switch (Integer.parseInt(request.getParameter("isNew"))) {
                                case 0: {
                                    try {
                                        //initialisation de l'achat
                                        initAchat(request, utilisateur);
                                    } catch (Exception ex) {
                                        Logger.getLogger(Inventaire.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }

                                break;
                                case 1:
                                    //ajouter un produit à la commande
                                    try {
                                        //initialisation de l'inventaire
                                        addAchat(request, false);
                                    } catch (Exception ex) {
                                        Logger.getLogger(Inventaire.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    break;
                                case 2: {
                                    try {
                                        //Enregistrer l' achat
                                        saveAchat(request, utilisateur);
                                    } catch (Exception ex) {
                                        Logger.getLogger(gestionAchat.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }

                                break;
                                case 3: {
                                    try {
                                        //Modifier le produit ajouté à l'achat encours
                                        addAchat(request, true);
                                    } catch (Exception ex) {
                                        Logger.getLogger(Inventaire.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                break;
                                case 4:
                                    //supprimer le produit ajouté
                                    if (request.getSession().getAttribute("ligneAchat") != null) {
                                        LigneCommandeFournisseur ligne = (LigneCommandeFournisseur) request.getSession().getAttribute("ligneAchat");
                                        List<CommandeFournisseur> listinv = ligne.getCommandeFournisseurList();
                                        int id = Integer.parseInt(request.getParameter("id"));

                                        CommandeFournisseur cmd = listinv.get(id);
                                        listinv.remove(id);
                                        ligne.setCommandeFournisseurList(listinv);

                                        request.getSession().setAttribute("ligneAchat", ligne);
                                        request.setAttribute("achats", listinv);

                                    }
                                    break;
                                case 5:
                                    //obtenir  le produit à modifier
                                    if (request.getSession().getAttribute("ligneAchat") != null) {
                                        LigneCommandeFournisseur ligne = (LigneCommandeFournisseur) request.getSession().getAttribute("ligneAchat");
                                        List<CommandeFournisseur> listinv = ligne.getCommandeFournisseurList();
                                        int id = Integer.parseInt(request.getParameter("id"));
                                        request.setAttribute("achat", listinv.get(id));
                                        request.setAttribute("achats", listinv);
                                    }
                                    break;
                                case 6:
                                    //Annuler l' achat
                                    request.getSession().removeAttribute("ligneAchat");
                                    request.getSession().removeAttribute("FournisseurEncour");
                                    break;

                            }
                            break;
                        case "bonAchat":
                            switch (Integer.parseInt(request.getParameter("isNew"))) {
                                /**
                                 * obtenir tous les bons de comande de la
                                 * societe
                                 */
                                case 0:
                                    try {
                                        request.setAttribute("achats", ligneCommandeFournisseurFacade.findAll(utilisateur.getSociete().getId()));
                                    } catch (Exception ex) {
                                        Logger.getLogger(Inventaire.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    break;
                                case 1:
                                    try {
                                        //obtenir l'achat selectionner
                                        int id = Integer.parseInt(request.getParameter("id"));
                                        request.setAttribute("achat", ligneCommandeFournisseurFacade.find(id));
                                        request.setAttribute("achats", ligneCommandeFournisseurFacade.findAll(utilisateur.getSociete().getId()));
                                    } catch (Exception ex) {
                                        Logger.getLogger(Inventaire.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    break;
                                case 2:
                                    try {
                                        //supprimer un bon d'achat
                                        String[] id = request.getParameterValues("id");
                                        for (String string : id) {
                                            LigneCommandeFournisseur lignecmdf = ligneCommandeFournisseurFacade.find(Integer.parseInt(string));
                                            boolean candelete = true;
                                            if (lignecmdf.getLigneSortieList().isEmpty()) {

                                                for (CommandeFournisseur commandeFournisseur : lignecmdf.getCommandeFournisseurList()) {
                                                    if (commandeFournisseur.getQuantiteRecu() != 0) {
                                                        candelete = false;
                                                        break;
                                                    }

                                                }
                                                if (candelete) {

                                                    ligneCommandeFournisseurFacade.Supprimer(Integer.parseInt(string));
                                                    for (CommandeFournisseur commandeFournisseur : lignecmdf.getCommandeFournisseurList()) {
                                                        Emballage embToStock = commandeFournisseur.getArticle().getEmballage();
                                                        embToStock.setStock(embToStock.getStock() + commandeFournisseur.getQuantite());
                                                        embToStock.setPrixTotal(embToStock.getStock() * embToStock.getPrix());
                                                        emballageFacade.MisAjour(embToStock);
                                                    }
                                                } else {
                                                    Message message = new Message();
                                                    message.setType("info");
                                                    message.setTitle("Impossible");
                                                    message.setNotification("Certains produits dans cette commande ont déjà été réceptionnés ");
                                                    request.setAttribute("message", message);
                                                }
                                            } else {
                                                Message message = new Message();
                                                message.setType("info");
                                                message.setTitle("Impossible");
                                                message.setNotification("Certains Commandes ont déjà été facturées ");
                                                request.setAttribute("message", message);
                                                break;
                                            }
                                        }
                                        request.setAttribute("achats", ligneCommandeFournisseurFacade.findAll(utilisateur.getSociete().getId()));
                                    } catch (Exception ex) {
                                        Logger.getLogger(Inventaire.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    break;
                                case 3:
                                    try {
                                        //ajouter une commande à la ligne de commande
                                        int id = Integer.parseInt(request.getParameter("achat"));
                                        LigneCommandeFournisseur ligne = ligneCommandeFournisseurFacade.find(id);
                                        boolean canadd = true;
                                        // empecher l'ajout d'un produit dans un bon quand au moins un produit a déjà été receptionner
//                                        for (CommandeFournisseur commandeFournisseur : ligne.getCommandeFournisseurList()) {
//                                            if (commandeFournisseur.getQuantiteRecu() != 0) {
//                                                canadd = false;
//                                                break;
//                                            }
//                                        }
                                        if (ligne.getEtat().getCode() != 200) {
                                            Tarticles art = tarticlesFacade.findTarticles(Integer.parseInt(request.getParameter("article")));
                                            boolean exist = false;
                                            for (CommandeFournisseur commandeFournisseur : ligne.getCommandeFournisseurList()) {
                                                if (Objects.equals(commandeFournisseur.getArticle().getId(), art.getId())) {
                                                    exist = true;
                                                    break;
                                                }
                                            }
                                            if (!exist) {
                                                CommandeFournisseur cmd = new CommandeFournisseur();
                                                cmd.setArticle(art);
                                                cmd.setQuantite(Double.parseDouble(request.getParameter("quantite")));
                                                if (!request.getParameter("prix").isEmpty()) {
                                                    cmd.setPrix(Double.parseDouble(request.getParameter("prix")));
                                                } else {
                                                    cmd.setPrix(art.getPrixAchat());
                                                }
                                                cmd.setQuantiteRecu(0.0);
                                                cmd.setEtat(0);
                                                cmd.setLigneCommandeFournisseur(ligne);
                                                cmd.setPrixTotal(cmd.getQuantite() * cmd.getPrix());

                                                cmd = commandeFournisseurFacade.creer(cmd);
                                                ligne.setPrixTotal(ligne.getPrixTotal() + cmd.getPrixTotal());
                                                ligne.getCommandeFournisseurList().add(cmd);
                                                ligne = ligneCommandeFournisseurFacade.MisAJour(ligne);
                                            } else {
                                                Message message = new Message();
                                                message.setType("info");
                                                message.setTitle("Impossible");
                                                message.setNotification("Ce produit existe déjà dans la liste ");
                                                request.setAttribute("message", message);
                                            }

                                        } else {
                                            Message message = new Message();
                                            message.setType("info");
                                            message.setTitle("Impossible");
                                            message.setNotification("Cette commande a déjà été entièrement réceptionnée ");
                                            request.setAttribute("message", message);
                                        }

                                        request.setAttribute("achat", ligne);
                                    } catch (Exception ex) {
                                        Logger.getLogger(Inventaire.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                    break;
                                case 4:

                                    LigneCommandeFournisseur ligne = ligneCommandeFournisseurFacade.find(Integer.parseInt(request.getParameter("achat")));
                                    CommandeFournisseur cmd = commandeFournisseurFacade.find(Integer.parseInt(request.getParameter("commande")));

                                    if (request.getParameter("update") != null) {
                                        Double QteRecu = 0.0;
                                        if (request.getParameter("quantite_recu") != null) {
                                            QteRecu = Double.parseDouble(request.getParameter("quantite_recu"));
                                        }
                                        if (request.getParameter("quantite") != null) {
                                            cmd.setQuantite(Double.parseDouble(request.getParameter("quantite")));
                                        }

                                        if (cmd.getQuantiteRecu() == null) {
                                            cmd.setQuantiteRecu(0.0);
                                        }
                                        Double newQteRecu = QteRecu + cmd.getQuantiteRecu();
                                        cmd.setQuantiteRecu(newQteRecu);
                                        // la quantité commandé doit toujours etre plus grande ou egale à celle reçue
                                        if (cmd.getQuantite() >= cmd.getQuantiteRecu()) {
                                            ligne.setPrixTotal(ligne.getPrixTotal() - cmd.getPrixTotal());
                                            if (request.getParameter("prix") != null) {
                                                cmd.setPrix(Double.parseDouble(request.getParameter("prix")));

                                            }
                                            cmd.setPrixTotal(cmd.getQuantite() * cmd.getPrix());
                                            ligne.setPrixTotal(ligne.getPrixTotal() + cmd.getPrixTotal());
                                            Tarticles art = cmd.getArticle();
                                            art.setStock(art.getStock() + QteRecu);
                                            try {
                                                tarticlesFacade.MiSaJour(art);
                                            } catch (RollbackFailureException ex) {
                                                Logger.getLogger(gestionAchat.class.getName()).log(Level.SEVERE, null, ex);
                                            } catch (Exception ex) {
                                                Logger.getLogger(gestionAchat.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                            if (Objects.equals(cmd.getQuantite(), cmd.getQuantiteRecu())) {
                                                cmd.setEtat(1);

                                            }
                                            try {
                                                ligne = ligneCommandeFournisseurFacade.MisAJour(ligne);
                                                commandeFournisseurFacade.MisAjour(cmd);

                                            } catch (Exception ex) {
                                                Logger.getLogger(gestionAchat.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        } else {
                                            Message message = new Message();
                                            message.setType("info");
                                            message.setTitle("Impossible");
                                            message.setNotification("Vous ne pouvez receptionner plus que vous n'avez commandé ");
                                            request.setAttribute("message", message);
                                        }

                                    }
                                    if (request.getParameter("reception") != null) {
                                        try {
                                            Tarticles art = cmd.getArticle();
                                            art.setStock(art.getStock() + (cmd.getQuantite() - cmd.getQuantiteRecu()));
                                            tarticlesFacade.MiSaJour(art);
                                            cmd.setQuantiteRecu(cmd.getQuantite());
                                            cmd.setEtat(1);
                                            ligne = ligneCommandeFournisseurFacade.MisAJour(ligne);
                                            commandeFournisseurFacade.MisAjour(cmd);

                                        } catch (Exception ex) {
                                            Logger.getLogger(gestionAchat.class.getName()).log(Level.SEVERE, null, ex);
                                        }

                                    }
                                    if (request.getParameter("delete") != null) {
                                        try {

                                            if (ligne.getCommandeFournisseurList().size() > 1) {
                                                Tarticles art = cmd.getArticle();
                                                art.setStock(art.getStock() - cmd.getQuantiteRecu());
                                                art = tarticlesFacade.MiSaJour(art);
                                                if (art.getEmballage() != null) {
                                                    Emballage em = art.getEmballage();
                                                    em.setStock(em.getStock() + cmd.getQuantite());
                                                    em = emballageFacade.MisAjour(em);
                                                }
                                                commandeFournisseurFacade.Supprimer(cmd.getId());
                                                ligne.setPrixTotal(ligne.getPrixTotal() - cmd.getPrixTotal());
                                                ligne = ligneCommandeFournisseurFacade.MisAJour(ligne);
//                                                if (cmd.getQuantiteRecu() == 0) {
//
//                                                } else {
//
//                                                    Message message = new Message();
//                                                    message.setType("info");
//                                                    message.setTitle("RAPPEL !!!");
//                                                    message.setNotification("vous ne pouvez supprimer une commande encours de reception ");
//                                                    request.setAttribute("message", message);
//                                                }

                                            } else {
                                                Message message = new Message();
                                                message.setType("error");
                                                message.setTitle("ERROR");
                                                message.setNotification("Impossible de supprimer cette commande à ce niveau ");
                                                request.setAttribute("message", message);
                                            }

                                        } catch (Exception ex) {
                                            Logger.getLogger(gestionAchat.class.getName()).log(Level.SEVERE, null, ex);
                                        }

                                    }
                                    //vérifié si toute les commandes ont été receptionnée
                                    ligne = ligneCommandeFournisseurFacade.find(ligne.getId());
                                    boolean allReceipt = true;
                                    for (CommandeFournisseur commandeFournisseur : ligne.getCommandeFournisseurList()) {
                                        if (commandeFournisseur.getEtat() == 0) {
                                            allReceipt = false;
                                            break;
                                        }
                                    }
                                    if (allReceipt) {
                                        ligne.setEtat(tetatcFacade.findByCode(200));
                                        ligne.setStatut(1);
                                        try {
                                            ligne = ligneCommandeFournisseurFacade.MisAJour(ligne);
                                            Message message = new Message();
                                            message.setType("success");
                                            message.setTitle("OK!!!!");
                                            message.setNotification("Votre commande a été entièrement receptionner ");
                                            request.setAttribute("message", message);
                                        } catch (Exception ex) {
                                            Logger.getLogger(gestionAchat.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }

                                    request.setAttribute("achat", ligne);
                                    break;
                                case 5:
                                    try {
                                        //Receptionner entièrement un bon d'achat
                                        LigneCommandeFournisseur ligneachat = ligneCommandeFournisseurFacade.find(Integer.parseInt(request.getParameter("id")));
                                        ligneachat.setEtat(tetatcFacade.findByCode(200));
                                        ligneachat.setStatut(1);
                                        ligneachat = ligneCommandeFournisseurFacade.MisAJour(ligneachat);
                                        List<CommandeFournisseur> cmdf = commandeFournisseurFacade.findAllByLigne(ligneachat.getId());
                                        for (CommandeFournisseur commandeFournisseur : cmdf) {
                                            CommandeFournisseur cmdfour = commandeFournisseur;
                                            cmdfour.setEtat(1);
                                            Tarticles art = cmdfour.getArticle();
                                            art.setStock(art.getStock() + (cmdfour.getQuantite() - cmdfour.getQuantiteRecu()));
                                            cmdfour.setQuantiteRecu(cmdfour.getQuantite());
                                            tarticlesFacade.MiSaJour(art);
                                            commandeFournisseurFacade.MisAjour(cmdfour);
                                        }
                                        Message message = new Message();
                                        message.setType("success");
                                        message.setTitle("OK!!!!");
                                        message.setNotification("Votre commande a été entièrement receptionner ");
                                        request.setAttribute("message", message);
                                        request.setAttribute("dontshowmodal", "yes");
                                        request.setAttribute("achat", ligneachat);
                                        request.setAttribute("achats", ligneCommandeFournisseurFacade.findAll(utilisateur.getSociete().getId()));
                                    } catch (Exception ex) {
                                        Logger.getLogger(Inventaire.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    break;
                                case 6:
                                    try {

                                        request.setAttribute("achat", updateInfoBon(request));
                                    } catch (Exception ex) {
                                        Logger.getLogger(Inventaire.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    break;

                            }
                            break;
                        case "historiqueInventaire":
                            switch (Integer.parseInt(request.getParameter("isNew"))) {
                                case 0:
                                    try {
                                        //initialisation de l'inventaire
                                        initAchat(request, utilisateur);
                                    } catch (Exception ex) {
                                        Logger.getLogger(Inventaire.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    break;
                                case 1:
                                    try {
                                        //initialisation de l'inventaire
                                        historiqueInventaire(request, utilisateur);
                                    } catch (Exception ex) {
                                        Logger.getLogger(Inventaire.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    break;
                                case 2:
                                    try {
                                        //détails de l'achat
                                        int id = Integer.parseInt(request.getParameter("id"));
                                        LigneInventaire ligne = ligneInventaireFacade.find(id);
                                        request.setAttribute("ligneInventaire", ligne);
                                    } catch (Exception ex) {
                                        Logger.getLogger(Inventaire.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    break;
                                case 3:
                                    try {
                                        //détails de l'inventaire
                                        historiqueDetailleInventaire(request, utilisateur);
                                    } catch (Exception ex) {
                                        Logger.getLogger(Inventaire.class.getName()).log(Level.SEVERE, null, ex);
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
                    request.getServletContext().getRequestDispatcher("/WEB-INF/main.jsp").forward(request, response);

                    break;
                case "getStockArticle":
                    JSONArray stock = new JSONArray();
                    HashMap r = new HashMap();
                    StockMg st = stockMgFacade.findStockArticleMg(Integer.parseInt(request.getParameter("article")), Integer.parseInt(request.getParameter("magasin")));

                    r.put("id", st.getId());
                    r.put("stock", st.getStock());
                    r.put("stockV", st.getStockV());
                    r.put("prix", st.getPrix());
                    r.put("prixTotal", st.getPrixTotal());
                    stock.put(r);
                    response.setContentType("application/json");
                    response.getWriter().print(stock);
                    break;
                case "bordereau": {
                    getBordereau(request, response, utilisateur);

                    break;
                }

            }

        } else {
            request.setAttribute("message", new Message("votre session a expirer veuillez vous reconnecter"));
            request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    private void getBordereau(HttpServletRequest request, HttpServletResponse response, Tusers utilisateur) throws IOException {
        JSONArray stock = new JSONArray();
        HashMap r = new HashMap();
        LigneCommandeFournisseur ligne = new LigneCommandeFournisseur();
        if (request.getParameter("fromcaisse") != null) {
            ligne = ligneSortieFacade.find(Integer.parseInt(request.getParameter("id"))).getNumeroBon();
        } else {
            ligne = ligneCommandeFournisseurFacade.find(Integer.parseInt(request.getParameter("id")));
        }
        Societe s = ligne.getOperateur().getSociete();
        Fournisseur four = ligne.getFournisseur();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        DateFormat dff = new SimpleDateFormat("dd/MM/yyyy");
        JSONArray sing = new JSONArray();
        HashMap sig = new HashMap();
        sig.put("adressesociete", s.getAdresse());
        sig.put("codepostalsociete", s.getCodePostal());
        sig.put("mailsociete", s.getEmail());
        sig.put("immatriculation", s.getImmatriculation());
        sig.put("cni", s.getCniRc());
        sig.put("centre_impot", s.getCentreImpot());
        sig.put("logosociete", s.getLogoBase64());
        sig.put("nomsociete", s.getNom());
        sig.put("telsociete", s.getTel());
        sig.put("transport", ligne.getTransport());
        sig.put("codefournisseur", ligne.getFournisseur().getCode());
        sig.put("adressefournisseur", ligne.getFournisseur().getAdresse());
        sig.put("mailfournisseur", ligne.getFournisseur().getMail());
        sig.put("nomfournisseur", ligne.getFournisseur().getNom());
        sig.put("telfournisseur", ligne.getFournisseur().getTelephone());
        sig.put("total", ligne.getPrixTotal());
        if (ligne.getDateModif() != null) {
            sig.put("datemodif", df.format(ligne.getDateModif()));
        }
        if (ligne.getDateCommande() != null) {
            sig.put("datecreation", dff.format(ligne.getDateCommande()));
        }
        if (ligne.getDateLivraison() != null) {
            sig.put("datelivraison", dff.format(ligne.getDateLivraison()));
        }
        if (ligne.getCommentaire() != null && !ligne.getCommentaire().isEmpty()) {
            sig.put("commentaire", ligne.getCommentaire());
        }

        sig.put("total", ligne.getPrixTotal());
        sig.put("numero", ligne.getId());
        sig.put("etat", ligne.getEtat().getNom());
        JSONArray arts = new JSONArray();
        for (CommandeFournisseur cmd : ligne.getCommandeFournisseurList()) {
            HashMap art = new HashMap();
            art.put("categorie", cmd.getArticle().getCategorie().getNom());
            art.put("article", cmd.getArticle().getNom());
            art.put("code", cmd.getArticle().getCode());
            art.put("quantite", cmd.getQuantite());
            art.put("quantiterecu", cmd.getQuantiteRecu());
            art.put("pu", cmd.getPrix());
            art.put("total", cmd.getPrixTotal());

            arts.put(art);

        }
        sig.put("articles", arts);
        // sing.put(sig);
        stock.put(sig);
        response.setContentType("application/json");
        response.getWriter().print(stock);
    }
// initialisation des inventaires

    public void initAchat(HttpServletRequest request, Tusers t) throws Exception {
        if (request.getParameter("id") != null || request.getParameter("societe") != null) {
            LigneCommandeFournisseur ligneAchat = new LigneCommandeFournisseur();

            if (request.getParameter("id") != null) {
                Fournisseur four = fournisseurFacade.find(Integer.parseInt(request.getParameter("id")));
                request.getSession().setAttribute("fournisseurEncour", four);
                request.getSession().setAttribute("njrl", request.getParameter("njr"));
                ligneAchat.setDateLivraison(date_du_jour.dateConvert(request.getParameter("njr")));
                ligneAchat.setFournisseur(four);
            } else if (request.getParameter("societe") != null) {
                ligneAchat.setOperateur(t);
            }
            // initialisation de la creation d'un inventaire
            ligneAchat.setOperateur(t);
            ligneAchat.setEtat(tetatcFacade.findByCode(501));
            if (request.getParameter("historique") != null) {
                request.setAttribute("achats", ligneCommandeFournisseurFacade.findAll(t.getSociete().getId()));
            }
            ligneAchat.setDateCommande(date_du_jour.dateJour());
            ligneAchat.setPrixTotal(0);
            request.getSession().setAttribute("ligneAchat", ligneAchat);
        } else {
            request.getSession().removeAttribute("fournisseurEncour");
            request.getSession().removeAttribute("njrl");
        }
    }
    // initialisation des inventaires

    public void initTransfert(HttpServletRequest request, Tusers t) throws Exception {
        if (request.getParameter("mg1") != null && request.getParameter("mg2") != null) {
            Magasin mag1 = magasinFacade.findMagasin(Integer.parseInt(request.getParameter("mg1")));
            Magasin mag2 = magasinFacade.findMagasin(Integer.parseInt(request.getParameter("mg2")));
            if (!Objects.equals(mag1.getId(), mag2.getId())) {
                request.getSession().setAttribute("magasinEmetteur", mag1);
                request.getSession().setAttribute("magasinRecepteur", mag2);
            } else {
                Message message = new Message();
                message.setType("error");
                message.setTitle("Impossible");
                message.setNotification("Le magasin recepteur doit être différent de l'emetteur");
                request.setAttribute("message", message);
            }

        } else {
            request.getSession().removeAttribute("magasinEmetteur");
            request.getSession().removeAttribute("magasinRecepteur");
        }
    }
// ajouter et modifier un achat

    public void addAchat(HttpServletRequest request, boolean update) throws Exception {
        if (request.getSession().getAttribute("ligneAchat") != null) {
            LigneCommandeFournisseur ligne = (LigneCommandeFournisseur) request.getSession().getAttribute("ligneAchat");
            List<CommandeFournisseur> listcmd = new ArrayList<>();
            if (ligne.getCommandeFournisseurList() != null) {
                listcmd = ligne.getCommandeFournisseurList();
            }
            Tarticles art = new Tarticles();
            art = tarticlesFacade.findTarticles(Integer.parseInt(request.getParameter("article")));

            CommandeFournisseur commande = null;
            if (!update) {
                commande = new CommandeFournisseur();
                int id = 0;
                if (listcmd != null && !listcmd.isEmpty()) {
                    for (CommandeFournisseur inv : listcmd) {
                        id++;
                    }
                }
                commande.setId(id);
            } else {
                int id = Integer.parseInt(request.getParameter("achat"));
                commande = new CommandeFournisseur(id);
            }
            commande.setArticle(art);

            commande.setQuantite(Double.parseDouble(request.getParameter("quantite")));
            commande.setPrix(art.getPrixAchat());
            if (request.getParameter("prix") != null) {
                if (!request.getParameter("prix").isEmpty()) {
                    commande.setPrix(Double.parseDouble(request.getParameter("prix")));
                }
            }
            commande.setPrixTotal(commande.getQuantite() * commande.getPrix());
            if (!update) {
                if (!exist(listcmd, commande)) {
                    listcmd.add(commande);
                }

            } else {
                int id = Integer.parseInt(request.getParameter("achat"));
                listcmd.set(id, commande);

            }
            ligne.setPrixTotal(commande.getPrixTotal());
            ligne.setCommandeFournisseurList(listcmd);
            request.getSession().setAttribute("ligneAchat", ligne);
            request.setAttribute("achats", listcmd);
        } else {
            request.getSession().removeAttribute("fournisseurEncour");
            request.getSession().removeAttribute("njrl");
        }
    }
// historique des inventaires

    public void historiqueInventaire(HttpServletRequest request, Tusers t) {
        // DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String[] date1 = request.getParameter("interval").split("-");
        Date d1 = date_du_jour.dateConvert(date1[0]);
        Date d2 = date_du_jour.dateConvert(date1[1]);
        if (request.getSession().getAttribute("magasinEncour") != null) {
            Magasin mag = (Magasin) request.getSession().getAttribute("magasinEncour");
            List<LigneInventaire> listInv = ligneInventaireFacade.historiqueInventaire(d1, d2, mag.getId());
            request.setAttribute("inventaires", listInv);
        } else {
            List<LigneInventaire> listInv = ligneInventaireFacade.historiqueInventaireSociete(d1, d2, t.getSociete().getId());
            request.setAttribute("inventaires", listInv);
        }

    }
// historique détaillé des inventaires

    public void historiqueDetailleInventaire(HttpServletRequest request, Tusers t) {
        // DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String[] date1 = request.getParameter("interval").split("-");
        Date d1 = date_du_jour.dateConvert(date1[0]);
        Date d2 = date_du_jour.dateConvert(date1[1]);
        int pour = 1;
        int type = 1;
        if (request.getParameter("for") != null) {
            pour = Integer.parseInt(request.getParameter("for"));
        }
        if (request.getParameter("type") != null) {
            type = Integer.parseInt(request.getParameter("type"));
        }
        int entite;
        if (request.getSession().getAttribute("magasinEncour") != null) {
            Magasin mag = (Magasin) request.getSession().getAttribute("magasinEncour");
            String msg = "Historique Inventaire du magasin " + mag.getNom() + "du " + d1 + " AU " + d2 + " concernant";
            if (pour == 0) {
                entite = Integer.parseInt(request.getParameter("categorie"));
                Tcategorie cat = tcategorieFacade.findTcategorie(entite);
                msg += " la catégorie : " + cat.getNom();
            } else {
                entite = Integer.parseInt(request.getParameter("article"));
                Tarticles art = tarticlesFacade.findTarticles(entite);
                msg += " l'article : " + art.getNom();
            }
            List<Inventaire> listInv = inventaireFacade.historiqueInventaire(d1, d2, mag.getId(), entite, pour);
            request.setAttribute("messageTable", msg);
            request.setAttribute("inventaires", listInv);
        } else {
            List<Inventaire> listInv = new ArrayList<Inventaire>();
            String msg = "Historique Inventaire du " + d1 + " AU " + d2 + " concernant";
            if (pour == 0) {
                entite = Integer.parseInt(request.getParameter("categorie"));
                Tcategorie cat = tcategorieFacade.findTcategorie(entite);
                msg += " la catégorie : " + cat.getNom();
            } else {
                entite = Integer.parseInt(request.getParameter("article"));
                if (type == 0) {
                    Tarticles art = tarticlesFacade.findTarticles(entite);
                    msg += " l'article : " + art.getNom();

                } else if (type == 1) {
                    Emballage emb = emballageFacade.find(entite);
                    msg += " l'emballage : " + emb.getNom();
                }

            }
            listInv = inventaireFacade.historiqueInventaireSociete(d1, d2, t.getSociete().getId(), entite, pour, type);
            request.setAttribute("messageTable", msg);
            request.setAttribute("inventaires", listInv);
        }

    }

    // déterminer si l'inventaire sur un article existe dans une liste d'inventaire
    public boolean exist(List<com.eh2s.ocm.Entity.CommandeFournisseur> listInv, com.eh2s.ocm.Entity.CommandeFournisseur inv) {
        for (com.eh2s.ocm.Entity.CommandeFournisseur inven : listInv) {
            if (inven.getArticle().getId().compareTo(inv.getArticle().getId()) == 0) {
                return true;
            }
        }
        return false;
    }

    private Tarticles getArticle(List<Tarticles> liste, Tarticles ar) {
        for (Tarticles st : liste) {
            if (st.getCode().equals(ar.getCode())) {
                return st;
            }
        }
        return null;
    }

    private List<Tarticles> getListArticle(List<Inventaire> liste) {
        List<Tarticles> a = new ArrayList<>();
        for (Inventaire st : liste) {
            a.add(st.getArticle());
        }
        return a;
    }

    private List<Emballage> getListEmballage(List<Inventaire> liste) {
        List<Emballage> a = new ArrayList<>();
        for (Inventaire st : liste) {
            a.add(st.getEmballage());
        }
        return a;
    }

    private LigneCommandeFournisseur updateInfoBon(HttpServletRequest request) {
        LigneCommandeFournisseur ligne = ligneCommandeFournisseurFacade.find(Integer.parseInt(request.getParameter("id")));
        Fournisseur four = fournisseurFacade.find(Integer.parseInt(request.getParameter("fournisseur")));
        ligne.setDateLivraison(date_du_jour.dateConvert(request.getParameter("njr")));
        ligne.setFournisseur(four);
        try {
            ligne = ligneCommandeFournisseurFacade.MisAJour(ligne);
        } catch (Exception ex) {
            Logger.getLogger(gestionAchat.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ligne;
    }

    private void saveAchat(HttpServletRequest request, Tusers utilisateur) throws Exception {
        if (request.getSession().getAttribute("ligneAchat") != null) {
            boolean error = false;
            LigneCommandeFournisseur ligne = (LigneCommandeFournisseur) request.getSession().getAttribute("ligneAchat");
            List<CommandeFournisseur> listcmd = ligne.getCommandeFournisseurList();
            LigneCommandeFournisseur ligneTosave = new LigneCommandeFournisseur();
            List<CommandeFournisseur> listcmdTosave = ligne.getCommandeFournisseurList();

            ligneTosave.setDateCommande(date_du_jour.dateJourUniqueDate());
            ligneTosave.setEtat(ligne.getEtat());
            ligneTosave.setDateLivraison(ligne.getDateLivraison());

            ligneTosave.setOperateur(ligne.getOperateur());
            ligneTosave.setFournisseur(ligne.getFournisseur());
            ligneTosave.setQuantiteTotal(0.0);
            ligneTosave.setTransport(0);
            Double prix_total = 0.0;
            for (CommandeFournisseur cmd : listcmdTosave) {
                prix_total += cmd.getPrixTotal();
            }
            ligneTosave.setPrixTotal(prix_total);
            try {
                ligneTosave = ligneCommandeFournisseurFacade.creer(ligneTosave);
            } catch (Exception ex) {
                Logger.getLogger(Inventaire.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (CommandeFournisseur cmd : listcmdTosave) {
                CommandeFournisseur cmdTosave = new CommandeFournisseur();
                cmdTosave.setArticle(cmd.getArticle());
                cmdTosave.setQuantite(cmd.getQuantite());
                cmdTosave.setLigneCommandeFournisseur(ligneTosave);
                cmdTosave.setPrix(cmd.getPrix());
                cmdTosave.setPrixTotal(cmd.getPrixTotal());
                cmdTosave.setQuantiteRecu(0.0);
                cmdTosave.setEtat(0);
                commandeFournisseurFacade.creer(cmdTosave);
                if (cmd.getArticle().getEmballage() != null) {
                    Emballage emb = cmd.getArticle().getEmballage();
                    emb.setStock(emb.getStock() - cmd.getQuantite());
                    emb.setPrixTotal(emb.getPrix() * emb.getStock());
                    emballageFacade.MisAjour(emb);
                }

            }
            if (!error) {
                Message message = new Message();
                message.setType("success");
                message.setTitle("ENREGISTREMENT");
                message.setNotification("Votre Commande a été enregistré avec succès.N° commande :" + ligneTosave.getId());
                request.setAttribute("message", message);
            } else {
                Message message = new Message();
                message.setType("error");
                message.setTitle("ERROR");
                message.setNotification("Une ERREUR et survenue veuillez réessayer");
                request.setAttribute("message", message);
            }

        }
        request.getSession().removeAttribute("ligneAchat");
        request.getSession().removeAttribute("njrl");
        request.getSession().removeAttribute("fournisseurEncour");
    }
}
