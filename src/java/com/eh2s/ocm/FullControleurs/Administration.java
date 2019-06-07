/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.FullControleurs;

import com.eh2s.ocm.Entity.AffectTournerUser;
import com.eh2s.ocm.Entity.Emballage;
import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Fournisseur;
import com.eh2s.ocm.Entity.Magasin;
import com.eh2s.ocm.Entity.RoleApk;
import com.eh2s.ocm.Entity.Services.Message;
import com.eh2s.ocm.Entity.Services.Services;
import com.eh2s.ocm.Entity.Services.ServicesInitialize;
import com.eh2s.ocm.Entity.Services.encryptedHash;
import com.eh2s.ocm.Entity.Sessions.ActionAPKFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.AffectTournerUserFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.EmballageFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.FournisseurFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.MagasinFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.RoleApkFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.SocieteFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.SourceMouvementCaisseFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.SouscriptionLicenceFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.StatutIncidentFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.StockMgFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TactionsFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TaffectzoneFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TarticlesFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TcategorieFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TclientsFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TcommandesFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TdistrictsFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TetatcFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TgroupsAssocFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TgroupsFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TincidentsFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TlignecommandeFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TmenuFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TournerFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TpriorityFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TregionsFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TrubriquesFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TsecteursFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TservicesFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TsitesFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TsmenuFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TsourcesFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TsrubriquesFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TtypeclientsFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TusersFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TypeLicenceFacadeLocal;
import com.eh2s.ocm.Entity.Societe;
import com.eh2s.ocm.Entity.SourceMouvementCaisse;
import com.eh2s.ocm.Entity.SouscriptionLicence;
import com.eh2s.ocm.Entity.StatutIncident;
import com.eh2s.ocm.Entity.StockMg;
import com.eh2s.ocm.Entity.Tactions;
import com.eh2s.ocm.Entity.Taffectzone;
import com.eh2s.ocm.Entity.Tarticles;
import com.eh2s.ocm.Entity.Tcategorie;
import com.eh2s.ocm.Entity.Tclients;
import com.eh2s.ocm.Entity.Tcommandes;
import com.eh2s.ocm.Entity.Tdistricts;
import com.eh2s.ocm.Entity.Tetatc;
import com.eh2s.ocm.Entity.Tgroups;
import com.eh2s.ocm.Entity.TgroupsAssoc;
import com.eh2s.ocm.Entity.Tlignecommande;
import com.eh2s.ocm.Entity.Tmenu;
import com.eh2s.ocm.Entity.Tourner;
import com.eh2s.ocm.Entity.Tpriority;
import com.eh2s.ocm.Entity.Tregions;
import com.eh2s.ocm.Entity.Trubriques;
import com.eh2s.ocm.Entity.Tsecteurs;
import com.eh2s.ocm.Entity.Tservices;
import com.eh2s.ocm.Entity.Tsites;
import com.eh2s.ocm.Entity.Tsmenu;
import com.eh2s.ocm.Entity.Tsources;
import com.eh2s.ocm.Entity.Tsrubriques;
import com.eh2s.ocm.Entity.Ttypeclients;
import com.eh2s.ocm.Entity.Tusers;
import com.eh2s.ocm.Entity.TypeLicence;
import com.eh2s.ocm.UtilityFonctions.EnvoiEmail;
import com.eh2s.ocm.UtilityFonctions.date_du_jour;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;

/**
 *
 * @author messi
 */
@WebServlet(name = "Administration", urlPatterns = {"/administration"})
@MultipartConfig(fileSizeThreshold = 6291456, // 6 MB
        maxFileSize = 10485760L, // 10 MB
        maxRequestSize = 20971520L, // 20 MB
        location = "/"
)
public class Administration extends HttpServlet {

    @EJB
    private SourceMouvementCaisseFacadeLocal sourceMouvementCaisseFacade;

    @EJB
    private FournisseurFacadeLocal fournisseurFacade;

    @EJB
    private EmballageFacadeLocal emballageFacade;

    @EJB
    private StockMgFacadeLocal stockMgFacade;

    @EJB
    private MagasinFacadeLocal magasinFacade;

    @EJB
    private TournerFacadeLocal tournerFacade;

    @EJB
    private AffectTournerUserFacadeLocal affectTournerUserFacade;

    @EJB
    private ActionAPKFacadeLocal actionAPKFacade;

    @EJB
    private RoleApkFacadeLocal roleApkFacade;

    @EJB
    private TlignecommandeFacadeLocal tlignecommandeFacade;

    @EJB
    private TaffectzoneFacadeLocal taffectzoneFacade;

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
    private SouscriptionLicenceFacadeLocal souscriptionLicenceFacade;

    @EJB
    private TypeLicenceFacadeLocal typeLicenceFacade;

    @EJB
    private TincidentsFacadeLocal tincidentsFacade;

    @EJB
    private TsmenuFacadeLocal tsmenuFacade;

    @EJB
    private TmenuFacadeLocal tmenuFacade;

    @EJB
    private StatutIncidentFacadeLocal statutIncidentFacade;

    @EJB
    private TclientsFacadeLocal tclientsFacade;

    @EJB
    private TtypeclientsFacadeLocal ttypeclientsFacade;

    @EJB
    private TsrubriquesFacadeLocal tsrubriquesFacade;

    @EJB
    private TrubriquesFacadeLocal trubriquesFacade;

    @EJB
    private TpriorityFacadeLocal tpriorityFacade;

    @EJB
    private TsourcesFacadeLocal tsourcesFacade;

    @EJB
    private TusersFacadeLocal tusersFacade;

    @EJB
    private TgroupsAssocFacadeLocal tgroupsAssocFacade;

    @EJB
    private TactionsFacadeLocal tactionsFacade;

    @EJB
    private TgroupsFacadeLocal tgroupsFacade;

    @EJB
    private TsecteursFacadeLocal tsecteursFacade;

    @EJB
    private TdistrictsFacadeLocal tdistrictsFacade;

    @EJB
    private TservicesFacadeLocal tservicesFacade;

    @EJB
    private TsitesFacadeLocal tsitesFacade;

    @EJB
    private TregionsFacadeLocal tregionsFacade;

    @EJB
    private SocieteFacadeLocal societeFacade;

    @EJB
    private Services services;

    private static final long serialVersionUID = 1L;

    private static final String UPLOAD_DIR = "PJ";

    private static final String UPLOAD_DIRARTICLE = "articles";

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
            Tusers utilisateur = (Tusers) session.getAttribute("utilisateur");
            request.setAttribute("actionAPK", actionAPKFacade.findAll());
            String actions = request.getParameter("action");
            request.setAttribute("q", request.getParameter("q"));

//            PrinterService printerService = new PrinterService();
//		
//		System.out.println(printerService.getPrinters());
//				
//		//print some stuff
//		printerService.printString("OneNote", "\n\n testing testing 1 2 3eeeee \n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
// 
//		// cut that paper!
//		byte[] cutP = new byte[] { 0x1d, 'V', 1 };
// 
//		printerService.printBytes("OneNote", cutP);
            switch (actions) {
                case "model":
                    String model = request.getParameter("model");
                    switch (model) {
                        case "societe":
                            Societe s = getSocietemodel(request);
                            if (Integer.parseInt(request.getParameter("isNew")) == 0) {
                                try {
                                    s = societeFacade.creer(s);
                                } catch (RollbackFailureException ex) {
                                    Logger.getLogger(Administration.class.getName()).log(Level.SEVERE, null, ex.getMessage());
                                } catch (Exception ex) {
                                    Logger.getLogger(Administration.class.getName()).log(Level.SEVERE, null, ex.getMessage());
                                }
                                request.setAttribute("societe", s);
                            } else {
                                try {
                                    societeFacade.MisAJour(s);
                                    request.setAttribute("societe", s);
                                } catch (NonexistentEntityException ex) {
                                    Logger.getLogger(Administration.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (RollbackFailureException ex) {
                                    Logger.getLogger(Administration.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (Exception ex) {
                                    Logger.getLogger(Administration.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            session.setAttribute("societe", s);
                            break;
                        case "getsociete":
                            Societe so = societeFacade.findSociete(Integer.parseInt(request.getParameter("id")));
                            request.setAttribute("onesociete", so);
                            session.setAttribute("societe", so);
                            break;
                        case "deleteSociete":

                            String[] ids = request.getParameterValues("id");
                            for (String id : ids) {
                                try {
                                    societeFacade.supprimer(Integer.parseInt(id));
                                } catch (RollbackFailureException ex) {
                                    Logger.getLogger(Administration.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (Exception ex) {
                                    Logger.getLogger(Administration.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            request.setAttribute("societes", societeFacade.findAll());
                            break;
                        case "region":
                            switch (Integer.parseInt(request.getParameter("isNew"))) {
                                case 0:
                                    try {
                                        tregionsFacade.creer(getRegionmodel(request));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                case 1:
                                    try {
                                        tregionsFacade.MisAJour(getRegionmodel(request));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                case 2:
                                    try {
                                        request.setAttribute("region", tregionsFacade.findTregions(Integer.parseInt(request.getParameter("id"))));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                default:
                                    try {
                                        String[] id = request.getParameterValues("id");
                                        for (String str : id) {
                                            tregionsFacade.Supprimer(Integer.parseInt(str));
                                        }
                                    } catch (NonexistentEntityException ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    } catch (RollbackFailureException ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                            }
                            break;
                        case "site":
                            switch (Integer.parseInt(request.getParameter("isNew"))) {
                                case 0:
                                    try {
                                        tsitesFacade.creer(getSitemodel(request));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                case 1:
                                    try {
                                        tsitesFacade.MisAJour(getSitemodel(request));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                case 2:
                                    try {
                                        request.setAttribute("site", tsitesFacade.findTsites(Integer.parseInt(request.getParameter("id"))));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                default:
                                    try {
                                        String[] id = request.getParameterValues("id");
                                        for (String str : id) {
                                            tsitesFacade.Supprimer(Integer.parseInt(str));
                                        }
                                    } catch (NonexistentEntityException ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    } catch (RollbackFailureException ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                            }
                            break;
                        case "service":
                            switch (Integer.parseInt(request.getParameter("isNew"))) {
                                case 0:
                                    try {
                                        tservicesFacade.creer(getServicesmodel(request));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                case 1:
                                    try {
                                        tservicesFacade.MisAJour(getServicesmodel(request));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                case 2:
                                    try {
                                        request.setAttribute("service", tservicesFacade.findTservices(Integer.parseInt(request.getParameter("id"))));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                default:
                                    try {
                                        String[] id = request.getParameterValues("id");
                                        for (String str : id) {
                                            tservicesFacade.Supprimer(Integer.parseInt(str));
                                        }
                                    } catch (NonexistentEntityException ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    } catch (RollbackFailureException ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                            }
                            break;
                        case "district":
                            switch (Integer.parseInt(request.getParameter("isNew"))) {
                                case 0:
                                    try {
                                        tdistrictsFacade.creer(getdistrictsmodel(request));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                case 1:
                                    try {
                                        tdistrictsFacade.MisAJour(getdistrictsmodel(request));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                case 2:
                                    try {
                                        request.setAttribute("district", tdistrictsFacade.findTdistricts(Integer.parseInt(request.getParameter("id"))));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                default:
                                    try {
                                        String[] id = request.getParameterValues("id");
                                        for (String str : id) {
                                            tdistrictsFacade.Supprimer(Integer.parseInt(str));
                                        }
                                    } catch (NonexistentEntityException ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    } catch (RollbackFailureException ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                            }
                            break;
                        case "secteur":
                            switch (Integer.parseInt(request.getParameter("isNew"))) {
                                case 0:
                                    try {
                                        tsecteursFacade.creer(getTsecteursmodel(request));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                case 1:
                                    try {
                                        tsecteursFacade.MisAJour(getTsecteursmodel(request));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                case 2:
                                    try {
                                        request.setAttribute("secteur", tsecteursFacade.findTsecteurs(Integer.parseInt(request.getParameter("id"))));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                default:
                                    try {
                                        String[] id = request.getParameterValues("id");
                                        for (String str : id) {
                                            tsecteursFacade.Supprimer(Integer.parseInt(str));
                                        }
                                    } catch (NonexistentEntityException ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    } catch (RollbackFailureException ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                            }
                            break;
                        case "group":
                            switch (Integer.parseInt(request.getParameter("isNew"))) {
                                case 0:
                                    try {
                                        tgroupsFacade.creer(getTgroupsmodel(request));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                case 1:
                                    try {
                                        tgroupsFacade.MisAJour(getTgroupsmodel(request));
                                        getTgroupsAssocmodel(request);

                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                case 2:
                                    try {
                                        Tgroups gr = tgroupsFacade.findTgroups(Integer.parseInt(request.getParameter("id")));
                                        List<Tactions> ass = tactionsFacade.findAll();
                                        List<Tactions> as = new ArrayList<>();
                                        gr.getTgroupsAssocList().stream().forEach((ag) -> {
                                            as.add(ag.getAction());
                                        });
                                        ass.removeAll(as);
                                        List<Tusers> t = tusersFacade.findAll(gr.getSociete().getImmatriculation());
                                        t.removeAll(gr.getTusersList());
                                        request.setAttribute("tous", t);
                                        request.setAttribute("access", ass);
                                        request.setAttribute("groupe", gr);
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                default:
                                    try {
                                        String[] id = request.getParameterValues("id");
                                        for (String str : id) {
                                            tgroupsFacade.Supprimer(Integer.parseInt(str));
                                        }
                                    } catch (NonexistentEntityException ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    } catch (RollbackFailureException ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                            }
                            break;
                        case "addmenu":
                            switch (Integer.parseInt(request.getParameter("isNew"))) {
                                case 0:
                                    try {
                                        tmenuFacade.create(getTmenumodel(request));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                case 1:
                                    try {

                                        tmenuFacade.edit(getTmenumodel(request));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                case 2:
                                    try {
                                        request.setAttribute("menu", tmenuFacade.find(Integer.parseInt(request.getParameter("id"))));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                default:
                                    try {
                                        String[] id = request.getParameterValues("id");
                                        for (String str : id) {
                                            tmenuFacade.remove(tmenuFacade.find(Integer.parseInt(str)));
                                        }
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                            }
                            break;
                        case "addsmenu":
                            switch (Integer.parseInt(request.getParameter("isNew"))) {
                                case 0:
                                    try {
                                        tsmenuFacade.create(getTsmenumodel(request));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                case 1:
                                    try {

                                        tsmenuFacade.edit(getTsmenumodel(request));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                case 2:
                                    try {
                                        request.setAttribute("smenu", tsmenuFacade.find(Integer.parseInt(request.getParameter("id"))));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                default:
                                    try {
                                        String[] id = request.getParameterValues("id");
                                        for (String str : id) {
                                            tsmenuFacade.remove(tsmenuFacade.find(Integer.parseInt(str)));
                                        }
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                            }
                            break;
                        case "droitAction":
                            switch (Integer.parseInt(request.getParameter("isNew"))) {
                                case 0:
                                    try {
                                        tactionsFacade.creer(getTactionsmodel(request));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                case 1:
                                    try {

                                        tactionsFacade.MisAJour(getTactionsmodel(request));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                case 2:
                                    try {
                                        request.setAttribute("action", tactionsFacade.findTactions(Integer.parseInt(request.getParameter("id"))));

                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                default:
                                    try {
                                        String[] id = request.getParameterValues("id");
                                        for (String str : id) {
                                            tactionsFacade.Supprimer(Integer.parseInt(str));
                                        }
                                    } catch (NonexistentEntityException ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    } catch (RollbackFailureException ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                            }
                            break;
                        case "sourceMouvement":
                            switch (Integer.parseInt(request.getParameter("isNew"))) {
                                case 0:
                                    try {
                                        sourceMouvementCaisseFacade.create(getSourceMouvementmodel(request));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                case 1:
                                    try {

                                        sourceMouvementCaisseFacade.edit(getSourceMouvementmodel(request));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                case 2:
                                    try {
                                        request.setAttribute("source", sourceMouvementCaisseFacade.find(Integer.parseInt(request.getParameter("id"))));

                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                default:
                                    String[] id = request.getParameterValues("id");
                                    for (String str : id) {
                                        sourceMouvementCaisseFacade.remove(sourceMouvementCaisseFacade.find(Integer.parseInt(str)));
                                    }

                                    break;
                            }
                            break;
                        case "accessGroup":
                            try {
                                getTgroupsAssocmodel(request);
                            } catch (Exception ex) {
                                request.setAttribute("erreur", ex.getMessage());
                            }
                            break;
                        case "user":
                            switch (Integer.parseInt(request.getParameter("isNew"))) {
                                case 0:
                                    try {
                                        getUsermodel(request);
                                        //  tusersFacade.creer(getUsermodel(request));

                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                case 1:
                                    try {
                                        getUsermodel(request);
                                        // tusersFacade.MisAJour(getUsermodel(request));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                case 2:
                                    try {
                                        request.setAttribute("user", tusersFacade.findTusers(Integer.parseInt(request.getParameter("id"))));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                default:
                                    try {
                                        String[] id = request.getParameterValues("id");
                                        for (String str : id) {
                                            tusersFacade.Supprimer(Integer.parseInt(str));
                                        }
                                    } catch (NonexistentEntityException ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    } catch (RollbackFailureException ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                            }
                            break;
                        case "source":
                            switch (Integer.parseInt(request.getParameter("isNew"))) {
                                case 0:
                                    try {
                                        tsourcesFacade.creer(getTsourcesmodel(request));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                case 1:
                                    try {
                                        tsourcesFacade.MisAJour(getTsourcesmodel(request));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                case 2:
                                    try {
                                        request.setAttribute("source", tsourcesFacade.findTsources(Integer.parseInt(request.getParameter("id"))));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                default:
                                    try {
                                        String[] id = request.getParameterValues("id");
                                        for (String str : id) {
                                            tsourcesFacade.Supprimer(Integer.parseInt(str));
                                        }
                                    } catch (NonexistentEntityException ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    } catch (RollbackFailureException ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                            }
                            break;
                        case "priorite":
                            switch (Integer.parseInt(request.getParameter("isNew"))) {
                                case 0:
                                    try {
                                        tpriorityFacade.creer(getTprioritymodel(request));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                case 1:
                                    try {
                                        tpriorityFacade.MisAJour(getTprioritymodel(request));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                case 2:
                                    try {
                                        request.setAttribute("priorite", tpriorityFacade.findTpriority(Integer.parseInt(request.getParameter("id"))));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                default:
                                    try {
                                        String[] id = request.getParameterValues("id");
                                        for (String str : id) {
                                            tpriorityFacade.Supprimer(Integer.parseInt(str));
                                        }
                                    } catch (NonexistentEntityException ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    } catch (RollbackFailureException ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                            }
                            break;
                        case "rubrique":
                            switch (Integer.parseInt(request.getParameter("isNew"))) {
                                case 0:
                                    try {
                                        trubriquesFacade.create(getTrubriquesmodel(request));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                case 1:
                                    try {
                                        trubriquesFacade.MisAJour(getTrubriquesmodel(request));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                case 2:
                                    try {
                                        request.setAttribute("rubrique", trubriquesFacade.findTrubriques(Integer.parseInt(request.getParameter("id"))));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                default:
                                    try {
                                        String[] id = request.getParameterValues("id");
                                        for (String str : id) {
                                            trubriquesFacade.Supprimer(Integer.parseInt(str));
                                        }
                                    } catch (NonexistentEntityException ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    } catch (RollbackFailureException ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                            }
                            break;
                        case "srubrique":
                            switch (Integer.parseInt(request.getParameter("isNew"))) {
                                case 0:
                                    try {
                                        tsrubriquesFacade.creer(getTsrubriquesmodel(request));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                case 1:
                                    try {
                                        tsrubriquesFacade.MisAJour(getTsrubriquesmodel(request));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                case 2:
                                    try {
                                        request.setAttribute("srubrique", tsrubriquesFacade.findTsrubriques(Integer.parseInt(request.getParameter("id"))));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                default:
                                    try {
                                        String[] id = request.getParameterValues("id");
                                        for (String str : id) {
                                            tsrubriquesFacade.Supprimer(Integer.parseInt(str));
                                        }
                                    } catch (NonexistentEntityException ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    } catch (RollbackFailureException ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                            }
                            break;
                        case "typeclient":
                            switch (Integer.parseInt(request.getParameter("isNew"))) {
                                case 0:
                                    try {
                                        ttypeclientsFacade.creer(getTtypeclientsmodel(request));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                case 1:
                                    try {
                                        ttypeclientsFacade.MisAJour(getTtypeclientsmodel(request));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                case 2:
                                    try {
                                        request.setAttribute("typeclient", ttypeclientsFacade.findTtypeclients(Integer.parseInt(request.getParameter("id"))));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                default:
                                    try {
                                        String[] id = request.getParameterValues("id");
                                        for (String str : id) {
                                            ttypeclientsFacade.Supprimer(Integer.parseInt(str));
                                        }
                                    } catch (NonexistentEntityException ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    } catch (RollbackFailureException ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                            }
                            break;
                        case "client":
                            switch (Integer.parseInt(request.getParameter("isNew"))) {
                                case 0:
                                    try {
                                        Societe societe;
                                        if (session.getAttribute("societe") != null) {
                                            societe = (Societe) session.getAttribute("societe");
                                        } else {
                                            societe = societeFacade.find(Integer.parseInt(request.getParameter("societe")));
                                        }
                                        Message m = new Message();
                                        int taille = tclientsFacade.findAll(societe.getImmatriculation()).size();
                                        if (services.verifiLicenceMaxUser(societe, taille, m)) {
                                            tclientsFacade.creer(getTclientsmodel(request));
                                        } else {
                                            m.setType("error");
                                            m.setSubject("Licence OCM Full");
                                            m.setNotification("Votre licence ne vous permet pas d'ajouter un nouveau client\n"
                                                    + " veuillez contacter EH2S votre fournisseur de service à l'adresse eh2s-sarl@eh2s.com upgrade votre licence");
                                            request.setAttribute("message", m);
                                            try {
                                                EnvoiEmail.sendMail(societe.getEmail(), m.getSubject(), m.getNotification());
                                            } catch (Exception ex) {
                                                System.err.println("Echec Notification par Mail");
                                            }
                                        }

                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                case 1:
                                    try {
                                        tclientsFacade.MisAJour(getTclientsmodel(request));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                case 2:
                                    try {
                                        request.setAttribute("client", tclientsFacade.findTclients(Integer.parseInt(request.getParameter("id"))));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                case 3: {
                                    try {
                                        getAffectationTournerClients(request);
                                    } catch (Exception ex) {
                                        Logger.getLogger(Administration.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                break;
                                default:
                                    try {
                                        String[] id = request.getParameterValues("id");
                                        for (String str : id) {
                                            tclientsFacade.Supprimer(Integer.parseInt(str));
                                        }
                                    } catch (NonexistentEntityException ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    } catch (RollbackFailureException ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                            }
                            break;
                        case "statut":
                            switch (Integer.parseInt(request.getParameter("isNew"))) {
                                case 0:
                                    try {
                                        statutIncidentFacade.create(getStatutIncidentmodel(request));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                case 1:
                                    try {
                                        statutIncidentFacade.MisAJour(getStatutIncidentmodel(request));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                case 2:
                                    try {
                                        request.setAttribute("statut", statutIncidentFacade.findStatutIncident(Integer.parseInt(request.getParameter("id"))));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                default:
                                    try {
                                        String[] id = request.getParameterValues("id");
                                        for (String str : id) {
                                            statutIncidentFacade.Supprimer(Integer.parseInt(str));
                                        }
                                    } catch (NonexistentEntityException ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    } catch (RollbackFailureException ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                            }
                            break;
                        case "typelicence":
                            switch (Integer.parseInt(request.getParameter("isNew"))) {
                                case 0:
                                    try {
                                        typeLicenceFacade.MisAJour(getTypeLicencemodel(request));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                case 2:
                                    try {
                                        request.setAttribute("typelicence", typeLicenceFacade.findTypeLicence(Integer.parseInt(request.getParameter("id"))));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                default:
                                    break;
                            }
                            break;
                        case "souscriptionlicence":
                            switch (Integer.parseInt(request.getParameter("isNew"))) {
                                case 0:
                                    try {
                                        getSouscriptionLicencemodel(request);
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                case 1:
                                    try {
                                        getSouscriptionLicencemodel(request);
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                case 2:
                                    try {
                                        request.setAttribute("souscrirelicence", souscriptionLicenceFacade.findSouscriptionLicence(Integer.parseInt(request.getParameter("id"))));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                default:
                                    break;
                            }
                            break;
                        case "statutCommande":
                            switch (Integer.parseInt(request.getParameter("isNew"))) {
                                case 0:
                                    try {
                                        tetatcFacade.creer(getEtatCommandeModel(request));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                case 1:
                                    try {
                                        tetatcFacade.MisAJour(getEtatCommandeModel(request));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                case 2:
                                    try {
                                        request.setAttribute("statutC", tetatcFacade.findTetatc(Integer.parseInt(request.getParameter("id"))));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                default:
                                    try {
                                        String[] id = request.getParameterValues("id");
                                        for (String str : id) {
                                            tetatcFacade.Supprimer(Integer.parseInt(str));
                                        }
                                    } catch (NonexistentEntityException ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    } catch (RollbackFailureException ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                            }
                            break;
                        case "tourner":
                            switch (Integer.parseInt(request.getParameter("isNew"))) {
                                case 0: {
                                    try {
                                        AddTournerModel(request, utilisateur);
                                    } catch (Exception ex) {
                                        Logger.getLogger(Administration.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                break;
                                case 1: {
                                    try {
                                        UpdateTournerModel(request);
                                    } catch (Exception ex) {
                                        Logger.getLogger(Administration.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                break;
                                case 2:
                                    Tourner tr = tournerFacade.find(Integer.parseInt(request.getParameter("id")));
                                     {
                                        try {
                                            tournerFacade.Supprimer(tr.getId());
                                        } catch (RollbackFailureException ex) {
                                            Logger.getLogger(Administration.class.getName()).log(Level.SEVERE, null, ex);
                                        } catch (Exception ex) {
                                            Logger.getLogger(Administration.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                    break;
                                case 3: {
                                    request.setAttribute("tournerToUpdate", tournerFacade.findTourner(Integer.parseInt(request.getParameter("id"))));
                                }
                                break;
                                default: {
                                    try {
                                        getAffectationTournerToMagasin(request);
                                    } catch (Exception ex) {
                                        Logger.getLogger(Administration.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                break;
                            }
                            break;
                        case "Magasin":
                            switch (Integer.parseInt(request.getParameter("isNew"))) {
                                case 0:
                                    //creer
                                    try {
                                        getMagasinModel(request);
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                case 1:
                                    //mis a jour
                                    try {
                                        getMagasinModel(request);
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                case 2:
                                    try {
                                        Societe societe = (Societe) request.getSession().getAttribute("societe");
                                        request.setAttribute("magasin", magasinFacade.findMagasin(Integer.parseInt(request.getParameter("id"))));
                                        request.setAttribute("magasins", magasinFacade.findMagasinBySociete(societe.getId()));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                case 3:
                                    try {
                                        Societe societe = (Societe) request.getSession().getAttribute("societe");
                                        request.setAttribute("magasins", magasinFacade.findMagasinBySociete(societe.getId()));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                default:
                                    Societe societe = (Societe) request.getSession().getAttribute("societe");
                                    try {
                                        String[] id = request.getParameterValues("id");
                                        for (String str : id) {
                                            SupprimerMagasinModel(Integer.parseInt(str));
                                        }
                                        request.setAttribute("magasins", magasinFacade.findMagasinBySociete(societe.getId()));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                            }
                            break;
                        case "Fournisseur":
                            switch (Integer.parseInt(request.getParameter("isNew"))) {
                                case 0:
                                    //creer
                                    try {
                                        getFournisseurModel(request);
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                case 1:
                                    //mis a jour
                                    try {
                                        getFournisseurModel(request);
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                case 2:
                                    try {
                                        Societe societe = (Societe) request.getSession().getAttribute("societe");

                                        request.setAttribute("fournisseur", fournisseurFacade.find(Integer.parseInt(request.getParameter("id"))));
                                        request.setAttribute("fournisseurs", fournisseurFacade.findFournisseurBySociete(societe.getId()));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                case 3:
                                    try {
                                        Societe societe = (Societe) request.getSession().getAttribute("societe");
                                        request.setAttribute("fournisseurs", fournisseurFacade.findFournisseurBySociete(societe.getId()));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                default:
                                    Societe societe = (Societe) request.getSession().getAttribute("societe");
                                    try {
                                        String[] id = request.getParameterValues("id");
                                        for (String str : id) {
                                            Fournisseur four = fournisseurFacade.find(Integer.parseInt(str));
                                            fournisseurFacade.remove(four);
                                        }
                                        request.setAttribute("fournisseurs", fournisseurFacade.findFournisseurBySociete(societe.getId()));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                            }
                            break;
                        case "MagasinStocks":
                            switch (Integer.parseInt(request.getParameter("isNew"))) {
                                case 0:
                                    //creer
                                    try {
                                        getStockMagasinModel(request);
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                case 1:
                                    //mis a jour
                                    try {
                                        getStockMagasinModel(request);
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                case 2:
                                    try {
                                        request.setAttribute("stock", stockMgFacade.find(Integer.parseInt(request.getParameter("id"))));
                                        Magasin mg = (Magasin) request.getSession().getAttribute("magasinEncour");
                                        request.setAttribute("stocks", stockMgFacade.findStockByMagasin(mg.getId()));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                case 3:
                                    try {
                                        if (request.getParameter("id") != null) {
                                            request.getSession().setAttribute("magasinEncour", magasinFacade.findMagasin(Integer.parseInt(request.getParameter("id"))));
                                            Magasin mg = (Magasin) request.getSession().getAttribute("magasinEncour");
                                            request.setAttribute("stocks", stockMgFacade.findStockByMagasin(mg.getId()));
                                        } else {
                                            request.getSession().removeAttribute("magasinEncour");
                                        }

                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                default:
                                    try {
                                        String[] id = request.getParameterValues("id");
                                        for (String str : id) {
                                            SupprimerStockMagasinModel(request, Integer.parseInt(str));
                                        }
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    Magasin mg = (Magasin) request.getSession().getAttribute("magasinEncour");
                                    request.getSession().setAttribute("magasinEncour", mg);
                                    request.setAttribute("stocks", stockMgFacade.findStockByMagasin(mg.getId()));
                                    break;
                            }
                            break;
                        case "CategorieArticle":
                            switch (Integer.parseInt(request.getParameter("isNew"))) {
                                case 0:
                                    try {
                                        tcategorieFacade.creer(getCategorieModel(request));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                case 1:
                                    try {
                                        tcategorieFacade.MisAJour(getCategorieModel(request));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                case 2:
                                    try {
                                        request.setAttribute("categorie", tcategorieFacade.findTcategorie(Integer.parseInt(request.getParameter("id"))));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                default:
                                    try {
                                        String[] id = request.getParameterValues("id");
                                        for (String str : id) {
                                            tcategorieFacade.Supprimer(Integer.parseInt(str));
                                        }
                                    } catch (NonexistentEntityException ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    } catch (RollbackFailureException ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                            }
                            break;
                        case "Articles":
                            switch (Integer.parseInt(request.getParameter("isNew"))) {
                                case 0:
                                    try {
                                        Tarticles ar = tarticlesFacade.creer(getArticlesModel(request));

                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                case 1:
                                    try {
                                        Tarticles ar = tarticlesFacade.MiSaJour(getArticlesModel(request));

                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                case 2:
                                    try {
                                        request.setAttribute("article", tarticlesFacade.findTarticles(Integer.parseInt(request.getParameter("id"))));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                default:
                                    try {
                                        String[] id = request.getParameterValues("id");
                                        for (String str : id) {
                                            tarticlesFacade.Supprimer(Integer.parseInt(str));
                                        }
                                    } catch (NonexistentEntityException ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    } catch (RollbackFailureException ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                            }
                            break;
                        case "Emballage":
                            switch (Integer.parseInt(request.getParameter("isNew"))) {
                                case 0:
                                    try {
                                        emballageFacade.create(getEmballageModel(request));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                case 1:
                                    try {
                                        emballageFacade.edit(getEmballageModel(request));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                case 2:
                                    try {
                                        request.setAttribute("emballage", emballageFacade.find(Integer.parseInt(request.getParameter("id"))));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                default:
                                    try {
                                        String[] id = request.getParameterValues("id");
                                        for (String str : id) {
                                            Emballage emb = emballageFacade.find(Integer.parseInt(str));
                                            emballageFacade.remove(emb);
                                        }
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                            }
                            break;
                        case "HistoriqueCommande":
                            switch (Integer.parseInt(request.getParameter("isNew"))) {
                                case 0:
                                    try {
                                        // request.setAttribute("commandes", HistoriqueCommande(request, societe));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                case 1:
                                    try {
                                        //  request.setAttribute("commandes", HistoriqueCommandeByEtat(request, societe));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }

                                    break;
                                case 2:
                                    try {
                                        Tlignecommande lig = tlignecommandeFacade.findTlignecommande(Integer.parseInt(request.getParameter("id")));
                                        request.setAttribute("commande", lig.getTcommandesList());
                                        request.setAttribute("commandes", HistoriqueCommande(request, lig.getClient().getSociete()));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                default:
                                    break;

                            }

                            if (utilisateur.getId() == -1) {
                                servicesInitializeImple.initiationSuperAdmin(request);
                            } else {
                                servicesInitializeImple.initialize(request, utilisateur);
                            }

                            request.setAttribute("q", request.getParameter("q"));
                            request.getServletContext().getRequestDispatcher("/WEB-INF/main.jsp").forward(request, response);
                            break;
                        case "updateMarge":
                            switch (Integer.parseInt(request.getParameter("isNew"))) {
                                case 0:
                                    try {
                                        updateMarge(request);
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;

                                default:
                                    break;

                            }

                            if (utilisateur.getId() == -1) {
                                servicesInitializeImple.initiationSuperAdmin(request);
                            } else {
                                servicesInitializeImple.initialize(request, utilisateur);
                            }

                            request.setAttribute("q", request.getParameter("q"));
                            request.getServletContext().getRequestDispatcher("/WEB-INF/main.jsp").forward(request, response);
                            break;
                        case "saveTicket":
                            switch (Integer.parseInt(request.getParameter("isNew"))) {
                                case 0:
                                    try {
                                        //  getTicketmodel(request, utilisateur);
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                case 1:
                                    try {
                                        //  getTicketmodel(request, utilisateur);
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                case 2:
                                    try {
                                        request.setAttribute("incident", tincidentsFacade.findTincidents(Integer.parseInt(request.getParameter("id"))));
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                    }
                                    break;
                                default:
                                    try {
                                        String[] id = request.getParameterValues("id");
                                        for (String str : id) {
                                            //tincidentsFacade.remove(tincidentsFacade.findTincidents(Integer.parseInt(str)));
                                            tincidentsFacade.Supprimer(Integer.parseInt(str));
                                        }
                                        Message sg = new Message();
                                        sg.setNotification("Incident(s) supprimé(s) avec succès");
                                        sg.setType("success");
                                        sg.setTitle("SUCCES");
                                        request.setAttribute("message", sg);
                                    } catch (Exception ex) {
                                        request.setAttribute("erreur", ex.getMessage());
                                        Message sg = new Message();
                                        sg.setNotification("Problème survenu lors de la suppression");
                                        sg.setType("error");
                                        sg.setTitle("Oups!!!");
                                        request.setAttribute("message", sg);
                                    }
                                    break;
                            }

                            if (utilisateur.getId() == -1) {
                                servicesInitializeImple.initiationSuperAdmin(request);
                            } else {
                                servicesInitializeImple.initialize(request, utilisateur);
                            }
                            request.setAttribute("q", request.getParameter("q"));
                            request.getServletContext().getRequestDispatcher("/WEB-INF/main.jsp").forward(request, response);
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
                case "getActionForGroupe":
                    JSONArray Allpages = new JSONArray();
                    Tgroups groupes = tgroupsFacade.find(Integer.parseInt(request.getParameter("groupe")));
                    List<Tactions> pss = new ArrayList<>();
                    List<TgroupsAssoc> pagespermise = groupes.getTgroupsAssocList();
                    pagespermise.stream().forEach((permission) -> {
                        pss.add(permission.getAction());
                    });
                    Charger_page(Allpages, pss);
                    response.setContentType("application/json");
                    response.getWriter().print(Allpages);
                    break;
                case "verifiImmatriculation":
                    boolean verif = VerifierImmatriculationsociete(request.getParameter("imma"));
                    JSONArray er = new JSONArray();
                    er.put(verif);
                    response.setContentType("application/json");
                    response.getWriter().print(er);
                    break;
                case "verifiusername":
                    JSONArray err = new JSONArray();
                    String username = request.getParameter("username");

                    Tusers us = tusersFacade.findByLogin(username);

                    if (us != null) {
                        err.put(false);
                    } else {
                        err.put(true);
                    }
                    response.setContentType("application/json");
                    response.getWriter().print(err);
                    break;
                case "verificodeclient":
                    JSONArray errr = new JSONArray();
                    String code = request.getParameter("code");
                    boolean inc = false;
                    Tclients usc = tclientsFacade.findByCodeclient(code);

                    if (usc != null) {
                        errr.put(false);
                    } else {
                        errr.put(true);
                    }
                    response.setContentType("application/json");
                    response.getWriter().print(errr);
                    break;
                case "getusersfermePlainte":
                    JSONArray Allusers = new JSONArray();
                    List<Tusers> use = tusersFacade.findAll(utilisateur.getServiceid().getSite().getRegionid().getSociete().getImmatriculation())
                            .stream().filter(u -> u.getServiceid().getSite().getRegionid().equals(utilisateur.getServiceid().getSite().getRegionid())
                            && u.getFi() == 1).collect(Collectors.toList());
                    use.stream().forEach((u) -> {
                        HashMap r = new HashMap();
                        r.put("id", u.getId());
                        r.put("nom", u.getFirstname());
                        Allusers.put(r);
                    });
                    response.setContentType("application/json");
                    response.getWriter().print(Allusers);
                    break;
                case "verifiSeuilArticle":
                    Societe societe = (Societe) request.getSession().getAttribute("societe");
                    JSONArray seuil = new JSONArray();
                    List<Tarticles> ars = tarticlesFacade.findAll(societe.getImmatriculation()).stream().
                            filter(ar -> ar.getStock() <= ar.getSeuil()).collect(Collectors.toList());

                    if (ars.isEmpty()) {
                        seuil.put(false);
                    } else {
                        seuil.put(true);
                        seuil.put("Vous avez " + ars.size() + " Article(s) donc le stock a atteind le seuil merci de ravitailler votre stock");
                    }
                    response.setContentType("application/json");
                    response.getWriter().print(seuil);
                    break;
                case "desactivationUser":
                    getEntity(request).stream().map((it) -> tusersFacade.findTusers(it)).map((u) -> {
                        if (u.getDisable() == 0) {
                            u.setDisable(1);
                        } else {
                            u.setDisable(0);
                        }
                        return u;
                    }).forEach((u) -> {
                        try {
                            tusersFacade.MisAJour(u);

                        } catch (NonexistentEntityException ex) {
                            Logger.getLogger(Administration.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (RollbackFailureException ex) {
                            Logger.getLogger(Administration.class.getName()).log(Level.SEVERE, null, ex);
                        } catch (Exception ex) {
                            Logger.getLogger(Administration.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                    if (utilisateur.getId() == -1) {
                        servicesInitializeImple.initiationSuperAdmin(request);
                    } else {
                        servicesInitializeImple.initialize(request, utilisateur);
                    }

                    request.getServletContext().getRequestDispatcher("/WEB-INF/main.jsp").forward(request, response);
                    break;
                default:
                    if (utilisateur.getId() == -1) {
                        servicesInitializeImple.initiationSuperAdmin(request);
                    } else {
                        servicesInitializeImple.initialize(request, utilisateur);
                    }

                    request.getServletContext().getRequestDispatcher("/WEB-INF/main.jsp").forward(request, response);
                    break;
            }

        } else {
            request.setAttribute("message", new Message("votre session a expirer veuillez vous reconnecter"));
            request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        }

    }

// fonctions en charge de remplire les modeles
    Societe getSocietemodel(HttpServletRequest request) {
        Societe s;

        if (Integer.parseInt(request.getParameter("isNew")) == 0) {
            s = new Societe();
            s.setId(genererIDsociete());
            s.setAutoSaveClient(0);
            s.setImmatriculation(request.getParameter("imma"));
        } else {
            s = societeFacade.find(Integer.parseInt(request.getParameter("id")));
            s.setAutoSaveClient(Integer.parseInt(request.getParameter("autoclient")));
        }
        List<String> chemin = new ArrayList<>();
        boolean error = false;
        s.setNom(request.getParameter("nom"));
        if (!request.getParameter("adresse").isEmpty()) {
            s.setAdresse(request.getParameter("adresse"));
        }
        if (!request.getParameter("tel").isEmpty()) {
            s.setTel(request.getParameter("tel"));
        }
        if (!request.getParameter("email").isEmpty()) {
            s.setEmail(request.getParameter("email"));
        }
        if (!request.getParameter("codeposte").isEmpty()) {
            s.setCodePostal(request.getParameter("codeposte"));
        }
        if (request.getParameter("maintenance") != null) {
            if (!request.getParameter("maintenance").isEmpty()) {
                s.setMaintenance(Integer.parseInt(request.getParameter("maintenance")));
            } else {
                s.setMaintenance(0);
            }

        }
        if (!request.getParameter("centreImpot").isEmpty()) {
            s.setCentreImpot(request.getParameter("centreImpot"));
        }
        if (!request.getParameter("geststock").isEmpty()) {
            s.setGestStock(Integer.parseInt(request.getParameter("geststock")));
        }
        if (!request.getParameter("gestmarge").isEmpty()) {
            s.setGestmarge(Integer.parseInt(request.getParameter("gestmarge")));
        }

        if (!request.getParameter("gesttourner").isEmpty()) {
            s.setGesttourner(Integer.parseInt(request.getParameter("gesttourner")));
        }
        if (request.getParameter("gestmagasin") != null) {
            if (!request.getParameter("gestmagasin").isEmpty()) {
                s.setGestmagasin(Integer.parseInt(request.getParameter("gestmagasin")));
            }
        }
        if (request.getParameter("gestfournisseur") != null) {
            if (!request.getParameter("gestfournisseur").isEmpty()) {
                s.setGestfournisseur(Integer.parseInt(request.getParameter("gestfournisseur")));
            }
        }
        if (request.getParameter("gestcaisse") != null) {
            if (!request.getParameter("gestcaisse").isEmpty()) {
                s.setGestcaisse(Integer.parseInt(request.getParameter("gestcaisse")));
            }
        }
        if (request.getParameter("gestemballage") != null) {
            if (!request.getParameter("gestemballage").isEmpty()) {
                s.setGestemballage(Integer.parseInt(request.getParameter("gestemballage")));
            }
        }
        if (request.getParameter("gestcassier") != null) {
            if (!request.getParameter("gestcassier").isEmpty()) {
                if (s.getGestcassier() != Integer.parseInt(request.getParameter("gestcassier"))) {
//                    if (s.getGestcassier() == 1) {
//                        AddPrixAmballage(s, 1);
//                    } else {
//                        AddPrixAmballage(s, 0);
//                    }
                    s.setGestcassier(Integer.parseInt(request.getParameter("gestcassier")));
                }
            }

        }
        if (!request.getParameter("sigle").isEmpty()) {
            s.setSigle(request.getParameter("sigle"));
        }
        if (!request.getParameter("cnirc").isEmpty()) {
            s.setCniRc(request.getParameter("cnirc"));

        }
        if (!request.getParameter("regimeFiscal").isEmpty()) {
            s.setRegimeFiscal(request.getParameter("regimeFiscal"));
        }
        if (!request.getParameter("activite").isEmpty()) {
            s.setActivite(request.getParameter("activite"));
        }
        try {
            chemin = services.SavePJ(request, UPLOAD_DIR);
        } catch (ServletException | IOException ex) {
            error = true;
        }
        if (!error && !chemin.isEmpty()) {
            try {
                s.setLogo(chemin.get(0));
                s.setLogoBase64(services.encodeImageToString(chemin.get(0)));
            } catch (IOException ex) {
                Logger.getLogger(Administration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        try {
            if (s.getId() != null) {
                s.setSouscriptionLicenceList(souscriptionLicenceFacade.findAll(s.getImmatriculation()));
            }

        } catch (Exception e) {
        }
        return s;
    }

    Tregions getRegionmodel(HttpServletRequest request) {
        Societe societe;
        if (request.getSession().getAttribute("societe") != null) {
            societe = (Societe) request.getSession().getAttribute("societe");
        } else {
            societe = societeFacade.findSociete(Integer.parseInt(request.getParameter("societe")));
        }
        Tregions r;
        if (Integer.parseInt(request.getParameter("isNew")) == 0) {
            r = new Tregions();
            r.setSociete(societe);
        } else {
            r = tregionsFacade.findTregions(Integer.parseInt(request.getParameter("region")));
        }
        r.setName(request.getParameter("nom"));
        if (!request.getParameter("tel").isEmpty()) {
            r.setTel1(request.getParameter("tel"));
        }
        if (!request.getParameter("tel1").isEmpty()) {
            r.setTel2(request.getParameter("tel1"));
        }
        if (!request.getParameter("email").isEmpty()) {
            r.setMail(request.getParameter("email"));
        }

        return r;
    }

    Tsites getSitemodel(HttpServletRequest request) {
        Tsites s;
        if (Integer.parseInt(request.getParameter("isNew")) == 0) {
            s = new Tsites();
        } else {
            s = tsitesFacade.findTsites(Integer.parseInt(request.getParameter("site")));
        }
        s.setName(request.getParameter("nom"));
        s.setRegionid(tregionsFacade.findTregions(Integer.parseInt(request.getParameter("region"))));
        return s;
    }

    Tservices getServicesmodel(HttpServletRequest request) {
        Tservices s;
        if (Integer.parseInt(request.getParameter("isNew")) == 0) {
            s = new Tservices();
        } else {
            s = tservicesFacade.findTservices(Integer.parseInt(request.getParameter("service")));
        }
        s.setName(request.getParameter("nom"));
        s.setSite(tsitesFacade.findTsites(Integer.parseInt(request.getParameter("site"))));
        return s;
    }

    Tdistricts getdistrictsmodel(HttpServletRequest request) {
        Tdistricts d;
        if (Integer.parseInt(request.getParameter("isNew")) == 0) {
            d = new Tdistricts();
        } else {
            d = tdistrictsFacade.findTdistricts(Integer.parseInt(request.getParameter("district")));
        }
        d.setName(request.getParameter("nom"));
        d.setRegionid(tregionsFacade.findTregions(Integer.parseInt(request.getParameter("region"))));
        return d;
    }

    Tsecteurs getTsecteursmodel(HttpServletRequest request) {
        Tsecteurs d;
        if (Integer.parseInt(request.getParameter("isNew")) == 0) {
            d = new Tsecteurs();
        } else {
            d = tsecteursFacade.findTsecteurs(Integer.parseInt(request.getParameter("secteur")));
        }
        d.setName(request.getParameter("nom"));
        d.setDistrictid(tdistrictsFacade.findTdistricts(Integer.parseInt(request.getParameter("district"))));
        return d;
    }

    Tgroups getTgroupsmodel(HttpServletRequest request) {
        Societe societe;
        if (request.getSession().getAttribute("societe") != null) {
            societe = (Societe) request.getSession().getAttribute("societe");
        } else {
            societe = societeFacade.findSociete(Integer.parseInt(request.getParameter("societe")));
        }
        Tgroups d;
        if (Integer.parseInt(request.getParameter("isNew")) == 0) {
            d = new Tgroups();
            d.setSociete(societe);

        } else {
            d = tgroupsFacade.findTgroups(Integer.parseInt(request.getParameter("group")));
        }
        if (request.getParameter("type") != null && !request.getParameter("type").isEmpty()) {
            d.setType(Integer.parseInt(request.getParameter("type")));
        }
        d.setName(request.getParameter("nom"));
        // d.setType(0);
        return d;
    }

    Tmenu getTmenumodel(HttpServletRequest request) {
        Tmenu d;
        if (Integer.parseInt(request.getParameter("isNew")) == 0) {
            d = new Tmenu();
        } else {
            d = tmenuFacade.find(Integer.parseInt(request.getParameter("menu")));
        }
        d.setNom(request.getParameter("nom"));
        return d;
    }

    Tsmenu getTsmenumodel(HttpServletRequest request) {
        Tsmenu d;
        if (Integer.parseInt(request.getParameter("isNew")) == 0) {
            d = new Tsmenu();
        } else {
            d = tsmenuFacade.find(Integer.parseInt(request.getParameter("smenu")));
        }
        d.setMenu(tmenuFacade.find(Integer.parseInt(request.getParameter("menu"))));
        d.setNom(request.getParameter("nom"));
        return d;
    }

    Tactions getTactionsmodel(HttpServletRequest request) {
        Tactions d;
        if (Integer.parseInt(request.getParameter("isNew")) == 0) {
            d = new Tactions();
            d.setCodeAction(Integer.parseInt(request.getParameter("code")));
        } else {
            d = tactionsFacade.findTactions(Integer.parseInt(request.getParameter("idaction")));
        }
        d.setSmenu(tsmenuFacade.find(Integer.parseInt(request.getParameter("smenu"))));
        d.setNomAction(request.getParameter("nom"));

        return d;
    }

    SourceMouvementCaisse getSourceMouvementmodel(HttpServletRequest request) {
        SourceMouvementCaisse d;
        if (Integer.parseInt(request.getParameter("isNew")) == 0) {
            d = new SourceMouvementCaisse();
        } else {
            d = sourceMouvementCaisseFacade.find(Integer.parseInt(request.getParameter("id")));
        }
        d.setNom(request.getParameter("nom"));

        return d;
    }

    void getTgroupsAssocmodel(HttpServletRequest request) throws Exception {
        Tgroups g = tgroupsFacade.findTgroups(Integer.parseInt(request.getParameter("group")));
        tgroupsAssocFacade.deletteByGroup(g.getId());

        String[] actions;
        String[] user;
        if (request.getParameterValues("access").length != 0) {
            actions = request.getParameterValues("access");
            for (String ac : actions) {
                TgroupsAssoc d = new TgroupsAssoc();
                d.setAction(tactionsFacade.findTactions(Integer.parseInt(ac)));
                d.setGroup1(g);
                tgroupsAssocFacade.creer(d);
            }
        }
        g.setTgroupsAssocList(tgroupsAssocFacade.GroupAssocieteByGroup(g.getId()));
        user = request.getParameterValues("users");
        tgroupsFacade.MisAJour(g);
        for (Tusers us : g.getTusersList()) {
            us.setGroupeid(null);
            tusersFacade.MisAJour(us);
        }
        for (String ac : user) {
            Tusers us = tusersFacade.findTusers(Integer.parseInt(ac));
            us.setGroupeid(g);
            tusersFacade.MisAJour(us);
        }

    }

    void getUsermodel(HttpServletRequest request) throws Exception {
        Societe societe;
        if (request.getSession().getAttribute("societe") != null) {
            societe = (Societe) request.getSession().getAttribute("societe");
        } else {
            societe = societeFacade.findSociete(Integer.parseInt(request.getParameter("societe")));
        }
        Tusers u;
        if (Integer.parseInt(request.getParameter("isNew")) == 0) {
            u = new Tusers();
            u.setSociete(societe);
            u.setChgpwd(1);
        } else {
            u = tusersFacade.findTusers(Integer.parseInt(request.getParameter("user")));
            u.setDatemodification(date_du_jour.dateJour());

            u.setChgpwd(1);
        }
        u.setDisable(Integer.parseInt(request.getParameter("etat")));
        if (!request.getParameter("nom").isEmpty()) {
            u.setFirstname(request.getParameter("nom"));
        }
        if (!request.getParameter("nom1").isEmpty()) {
            u.setLastname(request.getParameter("nom1"));
        }
        if (!request.getParameter("tel").isEmpty()) {
            u.setPhone(request.getParameter("tel"));
        }
        if (!request.getParameter("email").isEmpty()) {
            u.setMail(request.getParameter("email"));
        }
        if (!request.getParameter("adresse").isEmpty()) {
            u.setAddress1(request.getParameter("adresse"));
        }
        if (!request.getParameter("fonction").isEmpty()) {
            u.setFonction(request.getParameter("fonction"));
        }
        if (!request.getParameter("login").isEmpty()) {
            u.setLogin(request.getParameter("login"));
        }
        if (!request.getParameter("password").isEmpty()) {
            encryptedHash hash = new encryptedHash();
            u.setPassword(hash.encode(request.getParameter("password")));
            u.setPsd(request.getParameter("password"));
        }
        if (!request.getParameter("fi").isEmpty()) {
            u.setFi(Integer.parseInt(request.getParameter("fi")));
        }
        if (request.getParameter("magasin") != null) {
            if (!request.getParameter("magasin").isEmpty()) {
                if (Integer.parseInt(request.getParameter("magasin")) == 0) {
                    u.setMagasin(null);
                } else {
                    u.setMagasin(magasinFacade.findMagasin(Integer.parseInt(request.getParameter("magasin"))));
                }
            }
        }
        u.setServiceid(tservicesFacade.findTservices(Integer.parseInt(request.getParameter("service"))));
        u.setGroupeid(tgroupsFacade.findTgroups(Integer.parseInt(request.getParameter("group"))));
        if (Integer.parseInt(request.getParameter("isNew")) == 0) {
            u = tusersFacade.creer(u);
        }
        if (request.getParameter("role") != null) {
            if (!request.getParameter("role").isEmpty()) {
                AddRoleAPK(request, u);
            }

        }

        getAffectationTournerUsers(request, u);

        request.setAttribute("actionAPK", actionAPKFacade.findAll());
        if (request.getParameter("zone") != null) {
            if (!request.getParameter("zone").isEmpty()) {
                getAffectationZone(request, u);
            }

        }

    }

    Tsources getTsourcesmodel(HttpServletRequest request) {
        Societe societe;
        if (request.getSession().getAttribute("societe") != null) {
            societe = (Societe) request.getSession().getAttribute("societe");
        } else {
            societe = societeFacade.findSociete(Integer.parseInt(request.getParameter("societe")));
        }
        Tsources d;
        if (Integer.parseInt(request.getParameter("isNew")) == 0) {
            d = new Tsources();
            d.setSociete(societe);
        } else {
            d = tsourcesFacade.findTsources(Integer.parseInt(request.getParameter("source")));
        }
        d.setName(request.getParameter("nom"));
        return d;
    }

    Tpriority getTprioritymodel(HttpServletRequest request) {
        Tpriority d;
        if (Integer.parseInt(request.getParameter("isNew")) == 0) {
            d = new Tpriority();
        } else {
            d = tpriorityFacade.findTpriority(Integer.parseInt(request.getParameter("priorite")));
        }
        Societe societe;
        if (request.getSession().getAttribute("societe") != null) {
            societe = (Societe) request.getSession().getAttribute("societe");
        } else {
            societe = societeFacade.findSociete(Integer.parseInt(request.getParameter("societe")));
        }
        d.setSociete(societe);
        d.setName(request.getParameter("nom"));
        d.setNumber(Integer.parseInt(request.getParameter("niv")));
        return d;
    }

    Trubriques getTrubriquesmodel(HttpServletRequest request) {
        Societe societe;
        if (request.getSession().getAttribute("societe") != null) {
            societe = (Societe) request.getSession().getAttribute("societe");
        } else {
            societe = societeFacade.findSociete(Integer.parseInt(request.getParameter("societe")));
        }
        Trubriques d;
        if (Integer.parseInt(request.getParameter("isNew")) == 0) {
            d = new Trubriques();
            d.setSociete(societe);
        } else {
            d = trubriquesFacade.findTrubriques(Integer.parseInt(request.getParameter("rubrique")));
            d.setSociete(societe);
        }
        d.setName(request.getParameter("nom"));
        return d;
    }

    Tsrubriques getTsrubriquesmodel(HttpServletRequest request) {
        Tsrubriques d;
        if (Integer.parseInt(request.getParameter("isNew")) == 0) {
            d = new Tsrubriques();
        } else {
            d = tsrubriquesFacade.findTsrubriques(Integer.parseInt(request.getParameter("srubrique")));
        }
        d.setName(request.getParameter("nom"));
        d.setRubriqueid(trubriquesFacade.findTrubriques(Integer.parseInt(request.getParameter("rubrique"))));
        d.setDelais(Integer.parseInt(request.getParameter("delais")));
        return d;
    }

    Ttypeclients getTtypeclientsmodel(HttpServletRequest request) {
        Societe societe;
        if (request.getSession().getAttribute("societe") != null) {
            societe = (Societe) request.getSession().getAttribute("societe");
        } else {
            societe = societeFacade.findSociete(Integer.parseInt(request.getParameter("societe")));
        }
        Ttypeclients d;
        if (Integer.parseInt(request.getParameter("isNew")) == 0) {
            d = new Ttypeclients();
            d.setSociete(societe);
        } else {
            d = ttypeclientsFacade.findTtypeclients(Integer.parseInt(request.getParameter("typeclient")));
        }
        d.setName(request.getParameter("nom"));
        return d;
    }

    StatutIncident getStatutIncidentmodel(HttpServletRequest request) {
        StatutIncident d;
        if (Integer.parseInt(request.getParameter("isNew")) == 0) {
            d = new StatutIncident();
            d.setCode(Integer.parseInt(request.getParameter("codestatut")));
        } else {
            d = statutIncidentFacade.findStatutIncident(Integer.parseInt(request.getParameter("codestatut")));
        }
        d.setNom(request.getParameter("nom"));
        return d;
    }

    TypeLicence getTypeLicencemodel(HttpServletRequest request) {
        TypeLicence d = typeLicenceFacade.findTypeLicence(Integer.parseInt(request.getParameter("id")));
        if (!request.getParameter("max").isEmpty()) {
            d.setMaxUser(Integer.parseInt(request.getParameter("max")));
        }
        d.setTestMode(Integer.parseInt(request.getParameter("test")));
        return d;
    }

    void getSouscriptionLicencemodel(HttpServletRequest request) {
        SouscriptionLicence d;
        Societe s = societeFacade.findSociete(Integer.parseInt(request.getParameter("societe")));
        TypeLicence type;
        if (Integer.parseInt(request.getParameter("isNew")) == 0) {
            d = new SouscriptionLicence();
            type = typeLicenceFacade.findTypeLicence(Integer.parseInt(request.getParameter("type")));
            d.setDateSous(date_du_jour.dateJourUniqueDate());
            d.setUpUser(type.getInitUser());
            if (!s.getSouscriptionLicenceList().isEmpty()) {
                s.getSouscriptionLicenceList().forEach((ss) -> {
                    souscriptionLicenceFacade.remove(ss);
                });

            }
        } else {
            d = souscriptionLicenceFacade.findSouscriptionLicence(Integer.parseInt(request.getParameter("souscription")));
            type = d.getType();
            int us;
            us = d.getUpUser() + Integer.parseInt(request.getParameter("user"));

            if (d.getType().getMaxUser() >= us) {
                d.setUpUser(us);

                Message sg = new Message();
                sg.setNotification("Nombre de client Update pour cette Souscription");
                sg.setType("info");
                sg.setTitle("Alert!");
                request.setAttribute("message", sg);
            } else {
                Message sg = new Message();
                sg.setNotification("Nombre Maximum de client atteind pour cette GAMME");
                sg.setType("info");
                sg.setTitle("Alert!");
                request.setAttribute("message", sg);
            }

        }
        if (type.getTestMode() == -1) {
            if (request.getParameter("njs") != null) {
                d.setDateFinTest(date_du_jour.TicketDelaisResolution(Integer.parseInt(request.getParameter("njs"))));
            }
        }

        d.setSociete(s);
        d.setType(type);
        try {
            typeLicenceFacade.MisAJour(type);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(Administration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RollbackFailureException ex) {
            Logger.getLogger(Administration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Administration.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (Integer.parseInt(request.getParameter("isNew")) == 0) {
                souscriptionLicenceFacade.creer(d);
            } else {
                souscriptionLicenceFacade.MisAJour(d);
            }

        } catch (RollbackFailureException ex) {
            Logger.getLogger(Administration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Administration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    Tclients getTclientsmodel(HttpServletRequest request) throws Exception {
        Societe societe;
        if (request.getParameter("societe") == null) {
            societe = (Societe) request.getSession().getAttribute("societe");
        } else {
            societe = societeFacade.findSociete(Integer.parseInt(request.getParameter("societe")));
        }
        Tclients u;

        if (Integer.parseInt(request.getParameter("isNew")) == 0) {
            u = new Tclients();
            u.setDatecreation(date_du_jour.dateJour());
            u.setSociete(societe);
            u.setChgpwd(0);
        } else {
            u = tclientsFacade.findTclients(Integer.parseInt(request.getParameter("client")));
            u.setDatemodification(date_du_jour.dateJour());
        }

        if (request.getParameter("password") != null) {
            if (!request.getParameter("password").isEmpty()) {
                try {
                    encryptedHash hash = new encryptedHash();
                    u.setPassword(hash.encode(request.getParameter("password")));
                    u.setPsd(request.getParameter("password"));
                    u.setChgpwd(1);
                } catch (Exception ex) {
                    System.out.println("erreur " + ex.getMessage());
                }
            }

        }

        if (!request.getParameter("nom").isEmpty()) {
            u.setNom(request.getParameter("nom"));
        }
        if (!request.getParameter("nom1").isEmpty()) {
            u.setPrenom(request.getParameter("nom1"));
        }

        if (!request.getParameter("tel1").isEmpty()) {
            u.setTelephone(request.getParameter("tel1"));
        }
        if (!request.getParameter("tel2").isEmpty()) {
            u.setTelephone2(request.getParameter("tel2"));
        }
        if (!request.getParameter("email").isEmpty()) {
            u.setMail(request.getParameter("email"));
        }

        if (!request.getParameter("adresse").isEmpty()) {
            u.setAdresse(request.getParameter("adresse"));
        }

        if (!request.getParameter("codeclient").isEmpty()) {
            u.setCodeclient(request.getParameter("codeclient"));
        }

        if (request.getParameter("tourner") != null) {
            if (!request.getParameter("tourner").isEmpty()) {
                u.setTourner(tournerFacade.findTourner(Integer.parseInt(request.getParameter("tourner"))));
            }
        }
        u.setSecteurid(tsecteursFacade.findTsecteurs(Integer.parseInt(request.getParameter("secteur"))));
        u.setTypeclientid(ttypeclientsFacade.findTtypeclients(Integer.parseInt(request.getParameter("typeclient"))));

        return u;
    }

    Tetatc getEtatCommandeModel(HttpServletRequest request) throws Exception {
        Tetatc et;
        if (Integer.parseInt(request.getParameter("isNew")) == 0) {
            et = new Tetatc();
            et.setCode(Integer.parseInt(request.getParameter("code")));
        } else {
            et = tetatcFacade.findTetatc(Integer.parseInt(request.getParameter("etatc")));
        }
        et.setNom(request.getParameter("nom"));
        return et;
    }

    Tcategorie getCategorieModel(HttpServletRequest request) throws Exception {
        Societe societe;
        if (request.getParameter("societe") == null) {
            societe = (Societe) request.getSession().getAttribute("societe");
        } else {
            societe = societeFacade.findSociete(Integer.parseInt(request.getParameter("societe")));
        }
        Tcategorie cat;
        if (Integer.parseInt(request.getParameter("isNew")) == 0) {
            cat = new Tcategorie();
        } else {
            cat = tcategorieFacade.findTcategorie(Integer.parseInt(request.getParameter("categorie")));
        }
        cat.setNom(request.getParameter("nom"));
        cat.setSociete(societe);
        return cat;
    }

    Tarticles getArticlesModel(HttpServletRequest request) throws Exception {
        Societe societe;
        if (request.getSession().getAttribute("societe") != null) {
            societe = (Societe) request.getSession().getAttribute("societe");
        } else {
            societe = societeFacade.find(Integer.parseInt(request.getParameter("societe")));
        }
        Tarticles ar;
        List<String> chemin = new ArrayList<>();
        boolean error = false;
        if (Integer.parseInt(request.getParameter("isNew")) == 0) {
            ar = new Tarticles();
            if (request.getParameter("stock") != null) {
                if (!request.getParameter("stock").isEmpty()) {
                    ar.setStock(Double.parseDouble(request.getParameter("stock")));
                } else {
                    ar.setStock(0.0);
                }
            }

        } else {
            ar = tarticlesFacade.findTarticles(Integer.parseInt(request.getParameter("article")));
            if (request.getParameter("stock") != null) {
                if (!request.getParameter("stock").isEmpty()) {
                    ar.setStock(Double.parseDouble(request.getParameter("stock")));
                }
            }

        }
        if (request.getParameter("emballage") != null) {
            if (!request.getParameter("emballage").isEmpty()) {
                ar.setEmballage(emballageFacade.find(Integer.parseInt(request.getParameter("emballage"))));
            }
        }

        if (request.getParameter("seuil") != null) {
            if (!request.getParameter("seuil").isEmpty()) {
                ar.setSeuil(Integer.parseInt(request.getParameter("seuil")));
            } else {
                ar.setSeuil(0);
            }
        }

        if (request.getParameter("nom") != null) {
            if (!request.getParameter("nom").isEmpty()) {
                ar.setNom(request.getParameter("nom"));
            }
        }
        if (request.getParameter("prix") != null) {
            if (!request.getParameter("prix").isEmpty()) {
                ar.setPrix(Double.parseDouble(request.getParameter("prix")));
            }
        }

        if (request.getParameter("unite") != null) {
            if (!request.getParameter("unite").isEmpty()) {
                ar.setUnite(request.getParameter("unite"));
            }
        }
        if (request.getParameter("code") != null) {
            ar.setCode(request.getParameter("code"));
        }
        if (request.getParameter("cat") != null) {
            ar.setCategorie(tcategorieFacade.findTcategorie(Integer.parseInt(request.getParameter("cat"))));
        }

        if (societe.getGestfournisseur() == 1) {
            if (request.getParameter("prix_achat") != null) {
                if (!request.getParameter("prix_achat").isEmpty()) {
                    ar.setPrixAchat(Double.parseDouble(request.getParameter("prix_achat")));
                }
            }
            if (request.getParameter("fournisseur") != null) {
                if (!request.getParameter("fournisseur").isEmpty()) {
                    Fournisseur four = fournisseurFacade.find(Integer.parseInt(request.getParameter("fournisseur")));
                    ar.setFournisseur(four);
                }
            }
        }
        if (societe.getGestmarge() == 1) {
            if (request.getParameter("margefournisseur") != null) {
                if (!request.getParameter("margefournisseur").isEmpty()) {
                    ar.setMargeFournisseur(Double.parseDouble(request.getParameter("margefournisseur")));
                } else {
                    if (societe.getGestfournisseur() == 1) {
                        ar.setMargeFournisseur(ar.getPrix() - ar.getPrixAchat());
                    } else {
                        ar.setMargeFournisseur(0.0);
                    }

                }
            }
            ar.setMargeFournisseur(ar.getPrix() - ar.getPrixAchat());
            if (request.getParameter("margeclient") != null) {
                if (!request.getParameter("margeclient").isEmpty()) {
                    ar.setMargeClient(Double.parseDouble(request.getParameter("margeclient")));
                } else {
                    ar.setMargeClient(0.0);
                }
            }

        }
        try {
            chemin = services.SavePJ(request, UPLOAD_DIRARTICLE);
        } catch (IOException ex) {
            error = true;
        }
        if (!error && !chemin.isEmpty()) {
            try {
                ar.setChemin(chemin.get(0));
                ar.setPhoto(services.encodeImageToString(chemin.get(0)));
            } catch (IOException ex) {
                Logger.getLogger(Administration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //tarticlesFacade.edit(ar);
        return ar;
    }

    private void Charger_page(JSONArray Allpages, List<Tactions> l) {
        int i = 1;
        Set<Tmenu> cgps = new HashSet<>();
        tactionsFacade.findAll().stream().forEach((p) -> {
            cgps.add(p.getSmenu().getMenu());
        });
        for (Tmenu cat : cgps) {
            HashMap results = new HashMap();
            i++;
            results.put("id", i);
            results.put("text", cat.getNom());
            JSONArray Allp = new JSONArray();
            for (Tsmenu sous : cat.getTsmenuList()) {
                List<Tactions> ps = sous.getTactionsList();
                if (!ps.isEmpty()) {
                    i++;
                    HashMap resul = new HashMap();
                    resul.put("id", i);
                    resul.put("text", sous.getNom());
                    JSONArray Allpa = new JSONArray();
                    for (Tactions page : ps) {
                        i++;
                        boolean actif = false;
                        HashMap resull = new HashMap();
                        for (Tactions pa : l) {
                            if (page.getIdAction().compareTo(pa.getIdAction()) == 0) {
                                resull.put("id", i);
                                resull.put("idpage", page.getIdAction());
                                resull.put("text", page.getNomAction());
                                resull.put("checked", true);
                                actif = true;
                                break;
                            }
                        }
                        if (!actif) {
                            resull.put("id", i);
                            resull.put("idpage", page.getIdAction());
                            resull.put("text", page.getNomAction());
                            resull.put("checked", false);
                        }
                        Allpa.put(resull);
                    }
                    resul.put("items", Allpa);
                    Allp.put(resul);
                }
            }
            results.put("items", Allp);
            Allpages.put(results);
        }

    }

    List<Integer> getEntity(HttpServletRequest request) {
        List<Integer> entg = new ArrayList<>();
        String[] ent = request.getParameterValues("id");
        for (String str : ent) {
            entg.add(Integer.parseInt(str));
        }
        return entg;
    }

    List<Tlignecommande> HistoriqueCommande(HttpServletRequest request, Societe societe) {
        List<Tlignecommande> listCommandesH;

        if (request.getParameter("client") != null) {
            listCommandesH = tclientsFacade.findTclients(Integer.parseInt(request.getParameter("client"))).getTlignecommandeList();

        } else {
            Tusers us = (Tusers) request.getSession().getAttribute("utilisateur");
            listCommandesH = servicesInitializeImple.getCommandesByZoneAutorize(us);
        }

        return listCommandesH;
    }

    int genererIDsociete() {
        boolean repeat = true;
        int i = 0;
        while (repeat) {
            int id = (int) (Math.random() * 10000);
            List<Societe> so = societeFacade.findAllByForIdRepeat(id);
            if (so.isEmpty()) {
                repeat = false;
                i = id;
            }
        }
        return i;
    }

    boolean VerifierImmatriculationsociete(String imma) {
        boolean repeat = true;
        List<Societe> so = societeFacade.findAll();
        for (Societe s : so) {
            if (s.getImmatriculation() == null ? imma == null : s.getImmatriculation().equals(imma)) {
                repeat = true;
                break;
            }
        }
        return repeat;
    }

    void getAffectationZone(HttpServletRequest request, Tusers user) throws Exception {

        String zone = request.getParameter("zone");
        for (Taffectzone aff : user.getTaffectzoneList()) {
            taffectzoneFacade.Supprimer(aff.getId());
        }
        switch (zone) {
            case "region": {
                String[] reg = request.getParameterValues("region");
                System.out.println("REGION SEZI " + reg.length);
                System.out.println("REGIONID " + reg[0]);
                for (String str : reg) {
                    System.out.println("REGION " + str);
                    Taffectzone aff = new Taffectzone();
                    aff.setRegion(Integer.parseInt(str));
                    aff.setUsers(user);
                    taffectzoneFacade.creer(aff);
                }
                break;
            }
            case "district": {
                String[] reg = request.getParameterValues("district");
                for (String str : reg) {
                    Taffectzone aff = new Taffectzone();
                    aff.setDistrict(Integer.parseInt(str));
                    aff.setUsers(user);
                    taffectzoneFacade.creer(aff);
                }
                break;
            }
            case "secteur": {
                String[] reg = request.getParameterValues("secteur");
                for (String str : reg) {
                    Taffectzone aff = new Taffectzone();
                    aff.setSecteur(Integer.parseInt(str));
                    aff.setUsers(user);
                    taffectzoneFacade.creer(aff);
                }
                break;
            }
            default:
                break;
        }
        user.setTaffectzoneList(taffectzoneFacade.findByUser(user.getId()));
        try {
            tusersFacade.MisAJour(user);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(Administration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RollbackFailureException ex) {
            Logger.getLogger(Administration.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Administration.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void getAffectationTournerUsers(HttpServletRequest request, Tusers user) throws RollbackFailureException, Exception {
        if (request.getParameterValues("tourner") != null && request.getParameterValues("tourner").length > 0) {
            String[] zone = request.getParameterValues("tourner");
            for (AffectTournerUser aff : affectTournerUserFacade.findAffectTournerUserEntities(user.getId())) {
                affectTournerUserFacade.Supprimer(aff.getId());
            }
            for (String str : zone) {
                AffectTournerUser af = new AffectTournerUser();
                Tourner tr = tournerFacade.findTourner(Integer.parseInt(str));
                af.setTourner(tr);
                af.setUser(user);
                affectTournerUserFacade.creer(af);
            }
            user.setAffectTournerUserList(affectTournerUserFacade.findAffectTournerUserEntities(user.getId()));
            tusersFacade.MisAJour(user);
        } else {
            for (AffectTournerUser aff : affectTournerUserFacade.findAffectTournerUserEntities(user.getId())) {
                affectTournerUserFacade.Supprimer(aff.getId());
            }
            user.setAffectTournerUserList(affectTournerUserFacade.findAffectTournerUserEntities(user.getId()));
            tusersFacade.MisAJour(user);
        }

    }

    void getAffectationTournerUserMultiple(HttpServletRequest request) throws RollbackFailureException, Exception {
        Tourner tr = tournerFacade.findTourner(Integer.parseInt(request.getParameter("tourner")));
        String[] us = request.getParameterValues("user");
        for (AffectTournerUser aff : tr.getAffectTournerUserList()) {
            affectTournerUserFacade.Supprimer(aff.getId());
        }
        for (String str : us) {
            Tusers user = tusersFacade.findTusers(Integer.parseInt(str));
            for (AffectTournerUser aff : user.getAffectTournerUserList()) {
                affectTournerUserFacade.Supprimer(aff.getId());
            }
            AffectTournerUser af = new AffectTournerUser();
            af.setTourner(tr);
            af.setUser(user);
            affectTournerUserFacade.creer(af);
            user.setAffectTournerUserList(affectTournerUserFacade.findAffectTournerUserEntities(user.getId()));
            tusersFacade.MisAJour(user);
        }
    }

    void getAffectationTournerClients(HttpServletRequest request) throws Exception {
        Tourner tr = tournerFacade.find(Integer.parseInt(request.getParameter("tourner")));
        String[] clients = request.getParameterValues("client");
        for (Tclients cli : tr.getTclientsList()) {
            cli.setTourner(null);
            tclientsFacade.MisAJour(cli);
        }
        for (String str : clients) {
            Tclients cli = tclientsFacade.findTclients(Integer.parseInt(str));
            cli.setTourner(tr);
            tclientsFacade.MisAJour(cli);
        }
    }

    void AddTournerModel(HttpServletRequest request, Tusers u) throws Exception {
        Tourner tr = new Tourner();
        tr.setNumc(request.getParameter("numtr"));
        tr.setSociete(u.getSociete());
        tournerFacade.create(tr);
    }

    void UpdateTournerModel(HttpServletRequest request) throws RollbackFailureException, Exception {
        Tourner tr = tournerFacade.findTourner(Integer.parseInt(request.getParameter("tourner")));
        tr.setNumc(request.getParameter("numtr"));

        if (request.getParameterValues("client").length > 0) {
            getAffectationTournerClients(request);
            tournerFacade.MisAJour(tr);
        }
        if (request.getParameterValues("user") != null && request.getParameterValues("user").length > 0) {
            getAffectationTournerUserMultiple(request);
            tr.setAffectTournerUserList(affectTournerUserFacade.findAffectTournerTournerEntities(tr.getId()));
            tournerFacade.MisAJour(tr);
        } else {
            for (AffectTournerUser aff : tr.getAffectTournerUserList()) {
                affectTournerUserFacade.Supprimer(aff.getId());
            }
        }

    }

    void AddRoleAPK(HttpServletRequest request, Tusers u) {
        String[] s = request.getParameterValues("role");
        List<RoleApk> lrole = new ArrayList<>();
        for (RoleApk role : u.getRoleApkList()) {
            roleApkFacade.remove(role);
        }
        for (String str : s) {
            RoleApk role = new RoleApk();
            role.setRole(actionAPKFacade.find(Integer.parseInt(str)));
            role.setUtilisateur(u);
            role.setCode(role.getRole().getCode());
            role = roleApkFacade.creer(role);
            lrole.add(role);
        }
        u.setRoleApkList(lrole);
        tusersFacade.edit(u);
    }

    void getMagasinModel(HttpServletRequest request) throws Exception {
        Magasin mg;
        Societe s = (Societe) request.getSession().getAttribute("societe");
        if (Integer.parseInt(request.getParameter("isNew")) == 0) {
            mg = new Magasin();
        } else {
            mg = magasinFacade.findMagasin(Integer.parseInt(request.getParameter("magasin")));
        }
        mg.setNom(request.getParameter("nom"));
        mg.setSociete(s);
        mg.setRegion(tregionsFacade.findTregions(Integer.parseInt(request.getParameter("reg"))));
        if (request.getParameter("magasignier") != null) {
            if (!request.getParameter("magasignier").isEmpty()) {
                mg.setMagasigner(tusersFacade.find(Integer.parseInt(request.getParameter("magasignier"))));
            }

        }
        if (Integer.parseInt(request.getParameter("isNew")) == 0) {
            magasinFacade.creer(mg);
        } else {
            magasinFacade.MisAJour(mg);
        }
        request.setAttribute("magasins", magasinFacade.findMagasinBySociete(s.getId()));
    }

    void SupprimerMagasinModel(int id) throws Exception {
        Magasin mg = magasinFacade.findMagasin(id);
        magasinFacade.Supprimer(mg.getId());
    }

    void getStockMagasinModel(HttpServletRequest request) throws Exception {
        StockMg stmg;
        Magasin mg = null;
        if (Integer.parseInt(request.getParameter("isNew")) == 0) {
            stmg = new StockMg();
        } else {
            stmg = stockMgFacade.findStockMg(Integer.parseInt(request.getParameter("id")));
        }

        mg = (Magasin) request.getSession().getAttribute("magasinEncour");

        Tarticles ar = tarticlesFacade.findTarticles(Integer.parseInt(request.getParameter("article")));
        stmg.setMagasin(mg);
        stmg.setArticle(ar);
        stmg.setPrix(ar.getPrix());
        stmg.setStock(Double.parseDouble(request.getParameter("stock")));
        stmg.setStockV(Double.parseDouble(request.getParameter("stockv")));
        stmg.setPrixTotal(stmg.getStock() + stmg.getPrix());
        if (Integer.parseInt(request.getParameter("isNew")) == 0) {
            stmg.setPrixTotal(0.0);
            boolean exist = false;
            for (StockMg stk : mg.getStockMgList()) {
                if (stk.getArticle().getId() == ar.getId()) {
                    exist = true;
                    break;
                }
            }
            if (exist) {
                Message message = new Message();
                message.setType("error");
                message.setTitle("Impossible");
                message.setNotification("cet Article existe déjà dans le stock du magasin");
                request.setAttribute("message", message);
            } else {
                stmg = stockMgFacade.creer(stmg);
            }

        } else {
            stockMgFacade.MisAJour(stmg);
        }
        request.getSession().setAttribute("magasinEncour", mg);
        request.setAttribute("stocks", stockMgFacade.findStockByMagasin(mg.getId()));
    }

    void SupprimerStockMagasinModel(HttpServletRequest request, int id) throws Exception {
        StockMg stmg = stockMgFacade.findStockMg(id);
        stockMgFacade.Supprimer(stmg.getId());
    }

    void getAffectationTournerToMagasin(HttpServletRequest request) throws Exception {
        Magasin mg = magasinFacade.findMagasin(Integer.parseInt(request.getParameter("magasin")));
        String[] trs = request.getParameterValues("tourner");
        for (Tourner cli : mg.getTournerList()) {
            cli.setId(null);
            tournerFacade.MisAJour(cli);
        }
        for (String str : trs) {
            Tourner tr = tournerFacade.findTourner(Integer.parseInt(str));
            tr.setMagasin(mg);
            tournerFacade.MisAJour(tr);
        }
        request.setAttribute("tournes", tournerFacade.findAll(mg.getSociete().getId()));
    }

    private Emballage getEmballageModel(HttpServletRequest request) {
        Societe societe;
        if (request.getSession().getAttribute("societe") != null) {
            societe = (Societe) request.getSession().getAttribute("societe");
        } else {
            societe = societeFacade.find(Integer.parseInt(request.getParameter("societe")));
        }
        Emballage ar;
        List<String> chemin = new ArrayList<>();
        boolean error = false;
        if (Integer.parseInt(request.getParameter("isNew")) == 0) {
            ar = new Emballage();
            if (request.getParameter("stock") != null) {
                if (!request.getParameter("stock").isEmpty()) {
                    ar.setStock(Double.parseDouble(request.getParameter("stock")));
                } else {
                    ar.setStock(0.0);
                }
            }else{
                ar.setStock(0.0);
            }

        } else {
            ar = emballageFacade.find(Integer.parseInt(request.getParameter("emballage")));
            if (request.getParameter("stock") != null) {
                if (!request.getParameter("stock").isEmpty()) {
                    ar.setStock(ar.getStock() + Integer.parseInt(request.getParameter("stock")));
                }
            }

        }
        if (request.getParameter("seuil") != null) {
            if (!request.getParameter("seuil").isEmpty()) {
                ar.setSeuil(Integer.parseInt(request.getParameter("seuil")));
            } else {
                ar.setSeuil(0);
            }
        }

        if (request.getParameter("nom") != null) {
            if (!request.getParameter("nom").isEmpty()) {
                ar.setNom(request.getParameter("nom"));
            }
        }
        if (request.getParameter("prix") != null) {
            if (!request.getParameter("prix").isEmpty()) {
                ar.setPrix(Double.parseDouble(request.getParameter("prix")));
            }
        }
        if (request.getParameter("code") != null) {
            ar.setCode(request.getParameter("code"));
        }
        ar.setPrixTotal(ar.getPrix() * ar.getStock());
        ar.setSociete(societe);
        return ar;
    }

    private void getFournisseurModel(HttpServletRequest request) throws Exception {
        Fournisseur fournisseur;
        Societe s = (Societe) request.getSession().getAttribute("societe");
        if (Integer.parseInt(request.getParameter("isNew")) == 0) {
            fournisseur = new Fournisseur();
        } else {
            fournisseur = fournisseurFacade.find(Integer.parseInt(request.getParameter("fournisseur")));
        }
        fournisseur.setNom(request.getParameter("nom"));
        fournisseur.setCode(request.getParameter("code"));
        fournisseur.setSociete(s);
        if (request.getParameter("adresse") != null) {
            if (!request.getParameter("adresse").isEmpty()) {
                fournisseur.setAdresse(request.getParameter("adresse"));
            }

        }
        if (request.getParameter("telephone") != null) {
            if (!request.getParameter("telephone").isEmpty()) {
                fournisseur.setTelephone(request.getParameter("telephone"));
            }

        }
        if (request.getParameter("mail") != null) {
            if (!request.getParameter("mail").isEmpty()) {
                fournisseur.setMail(request.getParameter("mail"));
            }

        }
        if (Integer.parseInt(request.getParameter("isNew")) == 0) {
            fournisseurFacade.create(fournisseur);
        } else {
            fournisseurFacade.edit(fournisseur);
        }
        request.setAttribute("fournisseurs", fournisseurFacade.findFournisseurBySociete(s.getId()));
    }

    private void updateMarge(HttpServletRequest request) throws RollbackFailureException, Exception {
        int idSoc = Integer.parseInt(request.getParameter("id"));
        List<Tlignecommande> lignes = tlignecommandeFacade.findAllBySociete(idSoc);
        for (Tlignecommande ligne : lignes) {
            ligne.setMargeFournisseur(0.0);
            ligne.setMargeClient(0.0);
            for (Tcommandes cmd : ligne.getTcommandesList()) {
                cmd.setMargeFournisseur(cmd.getQuantite() * cmd.getArticle().getMargeFournisseur());
                cmd = tcommandesFacade.MisAJour(cmd);
                ligne.setMargeFournisseur(ligne.getMargeFournisseur() + cmd.getMargeFournisseur());
                ligne.setMargeClient(ligne.getMargeClient() + cmd.getMargeClient());
            }
            tlignecommandeFacade.MisAJour(ligne);
        }

    }

}
