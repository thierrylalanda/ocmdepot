/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.FullControleurs;

import com.eh2s.ocm.Entity.Notification;
import com.eh2s.ocm.Entity.PieceJointe;
import com.eh2s.ocm.Entity.Services.Message;
import com.eh2s.ocm.Entity.Services.Services;
import com.eh2s.ocm.Entity.Services.ServicesInitialize;
import com.eh2s.ocm.Entity.Services.encryptedHash;
import com.eh2s.ocm.Entity.Sessions.NotificationFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.PieceJointeFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.SocieteFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.StatutIncidentFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TcategorieFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TclientsFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TdistrictsFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TincidentsFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TpriorityFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TregionsFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TrubriquesFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TsecteursFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TservicesFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TsitesFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TsourcesFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TsrubriquesFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TtraitementTicketFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TtypeclientsFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TusersFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.UserplainteFacadeLocal;
import com.eh2s.ocm.Entity.Societe;
import com.eh2s.ocm.Entity.StatutIncident;
import com.eh2s.ocm.Entity.Tcategorie;
import com.eh2s.ocm.Entity.Tclients;
import com.eh2s.ocm.Entity.Tdistricts;
import com.eh2s.ocm.Entity.Tgroups;
import com.eh2s.ocm.Entity.Tincidents;
import com.eh2s.ocm.Entity.Tregions;
import com.eh2s.ocm.Entity.Trubriques;
import com.eh2s.ocm.Entity.Tsecteurs;
import com.eh2s.ocm.Entity.Tservices;
import com.eh2s.ocm.Entity.Tsites;
import com.eh2s.ocm.Entity.Tsrubriques;
import com.eh2s.ocm.Entity.TtraitementTicket;
import com.eh2s.ocm.Entity.Ttypeclients;
import com.eh2s.ocm.Entity.Tusers;
import com.eh2s.ocm.Entity.Userplainte;
import com.eh2s.ocm.UtilityFonctions.EnvoiEmail;
import com.eh2s.ocm.UtilityFonctions.date_du_jour;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
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
@WebServlet(name = "gestionticket", urlPatterns = {"/ticket"})
@MultipartConfig(fileSizeThreshold = 6291456, // 6 MB
        maxFileSize = 10485760L, // 10 MB
        maxRequestSize = 20971520L, // 20 MB
        location = "/"
)
public class gestionticket extends HttpServlet {

    @EJB
    private ServicesInitialize servicesInitializeImple;

    @EJB
    private TcategorieFacadeLocal tcategorieFacade;

    @EJB
    private NotificationFacadeLocal notificationFacade;

    @EJB
    private TtraitementTicketFacadeLocal ttraitementTicketFacade;

    @EJB
    private UserplainteFacadeLocal userplainteFacade;

    @EJB
    private PieceJointeFacadeLocal pieceJointeFacade;

    @EJB
    private TincidentsFacadeLocal tincidentsFacade;

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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("isconnect") != null) {
            if (request.getParameter("getfile") != null) {
                try {
                    getFichier(request, response);
                } catch (IOException | ServletException e) {
                }

            } else {
                doPost(request, response);
            }
        } else {
            request.setAttribute("message", new Message("votre session a expirer veillez vous reconnecter"));
            request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        }

    }

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
            if (actions.equals("model")) {
                String model = request.getParameter("model");
                switch (model) {

                    case "saveTicket":
                        switch (Integer.parseInt(request.getParameter("isNew"))) {
                            case 0:
                                try {
                                    getTicketmodel(request, utilisateur);
                                } catch (Exception ex) {
                                    request.setAttribute("erreur", ex.getMessage());
                                }
                                break;
                            case 1:
                                try {
                                    getTicketmodel(request, utilisateur);
                                } catch (Exception ex) {
                                    request.setAttribute("erreur", ex.getMessage());
                                }
                                break;
                            case 2:
                                try {
                                    request.setAttribute("incident", tincidentsFacade.findTincidents(Integer.parseInt(request.getParameter("id"))));
                                    FindByHistorique(request, utilisateur);
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
                                } catch (Exception ex) {
                                    request.setAttribute("erreur", ex.getMessage());
                                }
                                break;
                        }
                        servicesInitializeImple.initialize(request, utilisateur);
                        request.setAttribute("q", request.getParameter("q"));
                        request.getServletContext().getRequestDispatcher("/WEB-INF/main.jsp").forward(request, response);
                        break;
                    case "traitementTicket":
                        switch (Integer.parseInt(request.getParameter("isNew"))) {
                            case 0:
                                try {
                                    TraitementTicketmodel(request, utilisateur);
                                } catch (Exception ex) {
                                    request.setAttribute("erreur", ex.getMessage());
                                }
                                break;
                            case 1:
                                try {
                                    request.setAttribute("incident", tincidentsFacade.findTincidents(Integer.parseInt(request.getParameter("id"))));
                                } catch (Exception ex) {
                                    request.setAttribute("erreur", ex.getMessage());
                                }
                                request.setAttribute("traiter", "pourTraitement");
                                break;
                            case 2:
                                try {
                                    request.setAttribute("incident", tincidentsFacade.findTincidents(Integer.parseInt(request.getParameter("id"))));
                                } catch (Exception ex) {
                                    request.setAttribute("erreur", ex.getMessage());
                                }
                                request.setAttribute("ferme", "pourFermeture");
                                break;
                            default:
                                break;
                        }
                        servicesInitializeImple.initialize(request, utilisateur);
                        request.setAttribute("q", request.getParameter("q"));
                        request.getServletContext().getRequestDispatcher("/WEB-INF/main.jsp").forward(request, response);
                        break;
                    case "getUpdateUtilisateurmodel":
                        switch (Integer.parseInt(request.getParameter("isNew"))) {
                            case 0:
                                try {
                                    tusersFacade.MisAJour(getUpdateUtilisateurmodel(request, utilisateur));
                                } catch (Exception ex) {
                                    request.setAttribute("erreur", ex.getMessage());
                                }
                                break;
                            default:
                                break;
                        }
                        servicesInitializeImple.initialize(request, utilisateur);
                        request.setAttribute("q", request.getParameter("q"));
                        request.getServletContext().getRequestDispatcher("/WEB-INF/main.jsp").forward(request, response);
                        break;
                    case "getSrubrique": {
                        JSONArray object = new JSONArray();
                        HashMap dat = new HashMap();
                        Trubriques t = trubriquesFacade.findTrubriques(Integer.parseInt(request.getParameter("id")));
                        t.getTsrubriquesList().stream().map((st) -> {
                            dat.put("id", st.getId());
                            return st;
                        }).map((st) -> {
                            dat.put("name", st.getName());
                            return st;
                        }).map((st) -> {
                            dat.put("delais", st.getDelais());
                            return st;
                        }).forEach((_item) -> {
                            object.put(dat);
                        });
                        response.setContentType("application/json");
                        response.getWriter().print(object);
                        break;
                    }
                    case "getSites": {
                        JSONArray object = new JSONArray();
                        HashMap dat = new HashMap();
                        Tregions t = tregionsFacade.findTregions(Integer.parseInt(request.getParameter("id")));
                        t.getTsitesList().stream().map((st) -> {
                            dat.put("id", st.getId());
                            return st;
                        }).map((st) -> {
                            dat.put("name", st.getName());
                            return st;
                        }).forEach((_item) -> {
                            object.put(dat);
                        });
                        response.setContentType("application/json");
                        response.getWriter().print(object);
                        break;
                    }
                    case "getServices": {
                        JSONArray object = new JSONArray();
                        HashMap dat = new HashMap();
                        Tsites t = tsitesFacade.findTsites(Integer.parseInt(request.getParameter("id")));
                        t.getTservicesList().stream().map((st) -> {
                            dat.put("id", st.getId());
                            return st;
                        }).map((st) -> {
                            dat.put("name", st.getName());
                            return st;
                        }).forEach((_item) -> {
                            object.put(dat);
                        });
                        response.setContentType("application/json");
                        response.getWriter().print(object);
                        break;
                    }
                    case "getUsers": {
                        JSONArray object = new JSONArray();
                        HashMap dat = new HashMap();
                        Tservices t = tservicesFacade.findTservices(Integer.parseInt(request.getParameter("id")));
                        t.getTusersList().stream().map((st) -> {
                            dat.put("id", st.getId());
                            return st;
                        }).map((st) -> {
                            if (st.getLastname() != null && !st.getLastname().isEmpty()) {
                                dat.put("name", st.getFirstname().concat(" " + st.getLastname()));
                            } else {
                                dat.put("name", st.getFirstname());
                            }

                            return st;
                        }).forEach((_item) -> {
                            object.put(dat);
                        });
                        response.setContentType("application/json");
                        response.getWriter().print(object);
                        break;
                    }
                    case "getDistricts": {
                        JSONArray object = new JSONArray();
                        HashMap dat = new HashMap();
                        Tregions t = tregionsFacade.findTregions(Integer.parseInt(request.getParameter("id")));
                        t.getTdistrictsList().stream().map((st) -> {
                            dat.put("id", st.getId());
                            return st;
                        }).map((st) -> {
                            dat.put("name", st.getName());
                            return st;
                        }).forEach((_item) -> {
                            object.put(dat);
                        });
                        response.setContentType("application/json");
                        response.getWriter().print(object);
                        break;
                    }
                    case "getSecteurs": {
                        JSONArray object = new JSONArray();
                        HashMap dat = new HashMap();
                        Tdistricts t = tdistrictsFacade.findTdistricts(Integer.parseInt(request.getParameter("id")));
                        t.getTsecteursList().stream().map((st) -> {
                            dat.put("id", st.getId());
                            return st;
                        }).map((st) -> {
                            dat.put("name", st.getName());
                            return st;
                        }).forEach((_item) -> {
                            object.put(dat);
                        });
                        response.setContentType("application/json");
                        response.getWriter().print(object);
                        break;
                    }
                    case "getClients": {
                        JSONArray object = new JSONArray();
                        HashMap dat = new HashMap();
                        Tsecteurs t = tsecteursFacade.findTsecteurs(Integer.parseInt(request.getParameter("id")));
                        t.getTclientsList().stream().map((st) -> {
                            dat.put("id", st.getId());
                            return st;
                        }).map((st) -> {
                            if (st.getPrenom() != null && !st.getPrenom().isEmpty()) {
                                dat.put("name", st.getNom().concat(" " + st.getPrenom()));
                            } else {
                                dat.put("name", st.getNom());
                            }
                            return st;
                        }).forEach((_item) -> {
                            object.put(dat);
                        });
                        response.setContentType("application/json");
                        response.getWriter().print(object);
                        break;
                    }
                    case "getAllClientsByRegion": {
                        JSONArray object = new JSONArray();
                        HashMap dat = new HashMap();
                        for (Tclients st : tclientsFacade.findTclientsByRegion(Integer.parseInt(request.getParameter("id")), societe.getImmatriculation(), 0)) {
                            dat.put("id", st.getId());
                            if (st.getPrenom() != null && !st.getPrenom().isEmpty()) {
                                dat.put("name", st.getNom().concat(" " + st.getPrenom()));
                            } else {
                                dat.put("name", st.getNom());
                            }

                            object.put(dat);
                        }
                        response.setContentType("application/json");
                        response.getWriter().print(object);
                        break;
                    }
                    case "getAllUsersByRegion": {
                        JSONArray object = new JSONArray();
                        HashMap dat = new HashMap();
                        for (Tusers st : tusersFacade.findTclientsByRegion(Integer.parseInt(request.getParameter("id")), societe.getImmatriculation())) {
                            dat.put("id", st.getId());
                            if (st.getFirstname() != null && !st.getFirstname().isEmpty()) {
                                dat.put("name", st.getFirstname().concat(" " + st.getLastname()));
                            } else {
                                dat.put("name", st.getFirstname());
                            }
                            object.put(dat);
                        }
                        response.setContentType("application/json");
                        response.getWriter().print(object);
                        break;
                    }
                    case "getAllClientsByType": {
                        JSONArray object = new JSONArray();
                        HashMap dat = new HashMap();
                        Ttypeclients t = ttypeclientsFacade.findTtypeclients(Integer.parseInt(request.getParameter("id")));
                        t.getTclientsList().stream().map((st) -> {
                            dat.put("id", st.getId());
                            return st;
                        }).map((st) -> {
                            if (st.getPrenom() != null && !st.getPrenom().isEmpty()) {
                                dat.put("name", st.getNom().concat(" " + st.getPrenom()));
                            } else {
                                dat.put("name", st.getNom());
                            }
                            return st;
                        }).forEach((_item) -> {
                            object.put(dat);
                        });
                        response.setContentType("application/json");
                        response.getWriter().print(object);
                        break;
                    }
                    case "getArticlesByCategorie": {
                        JSONArray object = new JSONArray();
                        HashMap dat = new HashMap();
                        Tcategorie t = tcategorieFacade.findTcategorie(Integer.parseInt(request.getParameter("id")));
                        t.getTarticlesList().stream().map((st) -> {
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
                    case "getRegionsBySociete": {
                        JSONArray object = new JSONArray();
                        HashMap dat = new HashMap();
                        List<Tregions> t = societeFacade.findSociete(Integer.parseInt(request.getParameter("societe"))).getTregionsList();
                        t.stream().map((st) -> {
                            dat.put("id", st.getId());
                            return st;
                        }).map((st) -> {
                            dat.put("name", st.getName());
                            return st;
                        }).forEach((_item) -> {
                            object.put(dat);
                        });
                        response.setContentType("application/json");
                        response.getWriter().print(object);
                        break;
                    }
                    case "getGroupsBySociete": {
                        JSONArray object = new JSONArray();
                        HashMap dat = new HashMap();
                        List<Tgroups> t = societeFacade.findSociete(Integer.parseInt(request.getParameter("societe"))).getTgroupsList();
                        t.stream().map((st) -> {
                            dat.put("id", st.getId());
                            return st;
                        }).map((st) -> {
                            dat.put("name", st.getName());
                            return st;
                        }).forEach((_item) -> {
                            object.put(dat);
                        });
                        response.setContentType("application/json");
                        response.getWriter().print(object);
                        break;
                    }
                    case "getRubriquesBySociete": {
                        JSONArray object = new JSONArray();
                        HashMap dat = new HashMap();
                        List<Trubriques> t = societeFacade.findSociete(Integer.parseInt(request.getParameter("societe"))).getTrubriquesList();
                        t.stream().map((st) -> {
                            dat.put("id", st.getId());
                            return st;
                        }).map((st) -> {
                            dat.put("name", st.getName());
                            return st;
                        }).forEach((_item) -> {
                            object.put(dat);
                        });
                        response.setContentType("application/json");
                        response.getWriter().print(object);
                        break;
                    }
                    case "getCategorieBySociete": {
                        JSONArray object = new JSONArray();
                        HashMap dat = new HashMap();
                        List<Tcategorie> t = societeFacade.findSociete(Integer.parseInt(request.getParameter("societe"))).getTcategorieList();
                        t.stream().map((st) -> {
                            dat.put("id", st.getId());
                            return st;
                        }).map((st) -> {
                            dat.put("name", st.getNom());
                            return st;
                        }).forEach((_item) -> {
                            object.put(dat);
                        });
                        response.setContentType("application/json");
                        response.getWriter().print(object);
                        break;
                    }
                    case "getTypeclientsBySociete": {
                        JSONArray object = new JSONArray();
                        HashMap dat = new HashMap();
                        List<Ttypeclients> t = societeFacade.findSociete(Integer.parseInt(request.getParameter("societe"))).getTtypeclientsList();
                        t.stream().map((st) -> {
                            dat.put("id", st.getId());
                            return st;
                        }).map((st) -> {
                            dat.put("name", st.getName());
                            return st;
                        }).forEach((_item) -> {
                            object.put(dat);
                        });
                        response.setContentType("application/json");
                        response.getWriter().print(object);
                        break;
                    }
                    case "getSitesByRegion": {
                        JSONArray object = new JSONArray();
                        HashMap dat = new HashMap();
                        List<Tsites> t = tregionsFacade.findTregions(Integer.parseInt(request.getParameter("region"))).getTsitesList();
                        t.stream().map((st) -> {
                            dat.put("id", st.getId());
                            return st;
                        }).map((st) -> {
                            dat.put("name", st.getName());
                            return st;
                        }).forEach((_item) -> {
                            object.put(dat);
                        });
                        response.setContentType("application/json");
                        response.getWriter().print(object);
                        break;
                    }
                    case "getDistrictByRegion": {
                        JSONArray object = new JSONArray();
                        HashMap dat = new HashMap();
                        List<Tdistricts> t = tregionsFacade.findTregions(Integer.parseInt(request.getParameter("region"))).getTdistrictsList();
                        t.stream().map((st) -> {
                            dat.put("id", st.getId());
                            return st;
                        }).map((st) -> {
                            dat.put("name", st.getName());
                            return st;
                        }).forEach((_item) -> {
                            object.put(dat);
                        });
                        response.setContentType("application/json");
                        response.getWriter().print(object);
                        break;
                    }
                    case "getServicesBySite": {
                        JSONArray object = new JSONArray();
                        HashMap dat = new HashMap();
                        List<Tservices> t = tsitesFacade.findTsites(Integer.parseInt(request.getParameter("site"))).getTservicesList();
                        t.stream().map((st) -> {
                            dat.put("id", st.getId());
                            return st;
                        }).map((st) -> {
                            dat.put("name", st.getName());
                            return st;
                        }).forEach((_item) -> {
                            object.put(dat);
                        });
                        response.setContentType("application/json");
                        response.getWriter().print(object);
                        break;
                    }
                    case "getSecteurByDistrict": {
                        JSONArray object = new JSONArray();
                        HashMap dat = new HashMap();
                        List<Tsecteurs> t = tdistrictsFacade.findTdistricts(Integer.parseInt(request.getParameter("district"))).getTsecteursList();
                        t.stream().map((st) -> {
                            dat.put("id", st.getId());
                            return st;
                        }).map((st) -> {
                            dat.put("name", st.getName());
                            return st;
                        }).forEach((_item) -> {
                            object.put(dat);
                        });
                        response.setContentType("application/json");
                        response.getWriter().print(object);
                        break;
                    }
                    case "getUserByService": {
                        JSONArray object = new JSONArray();
                        HashMap dat = new HashMap();
                        List<Tusers> t = tservicesFacade.findTservices(Integer.parseInt(request.getParameter("service"))).getTusersList();
                        t.stream().map((st) -> {
                            dat.put("id", st.getId());
                            return st;
                        }).map((st) -> {
                            dat.put("name", st.getFirstname());
                            return st;
                        }).forEach((_item) -> {
                            object.put(dat);
                        });
                        response.setContentType("application/json");
                        response.getWriter().print(object);
                        break;
                    }
                    case "getClientsNotification": {
                        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                        JSONArray object = new JSONArray();
                        HashMap dat = new HashMap();
                        List<Notification> notifs = notificationFacade.findAllByClient(Integer.parseInt(request.getParameter("id")), -1, true);
                        notifs.stream().map((st) -> {
                            dat.put("id", st.getId());
                            return st;
                        }).map((st) -> {
                            dat.put("message", st.getMessage());
                            return st;
                        }).map((st) -> {
                            dat.put("etat", st.getLut());
                            return st;
                        }).map((st) -> {
                            dat.put("titre", st.getTitel());
                            return st;
                        }).map((st) -> {
                            dat.put("dateNotif", df.format(st.getDateNotif()));
                            return st;
                        }).forEach((_item) -> {
                            object.put(dat);
                        });
                        response.setContentType("application/json");
                        response.getWriter().print(object);
                        break;
                    }
                    case "getUserssNotification": {
                        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                        JSONArray object = new JSONArray();
                        HashMap dat = new HashMap();
                        List<Notification> notifs = notificationFacade.findAllByUser(Integer.parseInt(request.getParameter("id")), -1, true);
                        notifs.stream().map((st) -> {
                            dat.put("id", st.getId());
                            return st;
                        }).map((st) -> {
                            dat.put("message", st.getMessage());
                            return st;
                        }).map((st) -> {
                            dat.put("etat", st.getLut());
                            return st;
                        }).map((st) -> {
                            dat.put("titre", st.getTitel());
                            return st;
                        }).map((st) -> {
                            dat.put("dateNotif", df.format(st.getDateNotif()));
                            return st;
                        }).forEach((_item) -> {
                            object.put(dat);
                        });
                        response.setContentType("application/json");
                        response.getWriter().print(object);
                        break;
                    }
                    case "setUserssNotification": {
                        JSONArray object = new JSONArray();
                        HashMap dat = new HashMap();
                        String[] ids = request.getParameterValues("id");
                        for (String id : ids) {
                            Notification notif = notificationFacade.find(Integer.parseInt(id));
                            notif.setLut(1);
                            notificationFacade.edit(notif);
                        }
                        response.setContentType("application/json");
                        response.getWriter().print(object);
                        break;
                    }
                    default:
                        break;
                }

            } else {
                servicesInitializeImple.initialize(request, utilisateur);
                request.getServletContext().getRequestDispatcher("/WEB-INF/main.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("message", new Message("votre session a expirer veuillez vous reconnecter"));
            request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        }

    }
// fonctions en charge de remplire les modeles

    void getTicketmodel(HttpServletRequest request, Tusers utilisateur) throws Exception {
        Tincidents r;
        Userplainte u;
        Tincidents ic = null;
        String UPLOAD_DIRECTORY = "PJ";
        boolean error = false;
        boolean error1 = false;
        boolean error2 = false;
        int is = Integer.parseInt(request.getParameter("isNew"));
        if (is == 0) {
            r = new Tincidents();
            u = new Userplainte();
            r.setDateCreate(date_du_jour.dateJour());
            Tsrubriques s = tsrubriquesFacade.findTsrubriques(Integer.parseInt(request.getParameter("srubrique")));
            r.setSrubriqueid(s);
            r.setDateHope(date_du_jour.TicketDelaisResolution(s.getDelais()));
        } else {
            r = tincidentsFacade.findTincidents(Integer.parseInt(request.getParameter("ticket")));
            r.setDateModif(date_du_jour.dateJour());
            try {
                u = userplainteFacade.findUserplainteByIdTicket(r.getId());
            } catch (Exception e) {
                u = new Userplainte();
                error2 = true;
            }

            r.setSrubriqueid(tsrubriquesFacade.findTsrubriques(Integer.parseInt(request.getParameter("srubrique"))));
        }

        r.setCreator(utilisateur);
        r.setDescription(request.getParameter("description"));
        r.setTitle(request.getParameter("object"));
        r.setSourceid(tsourcesFacade.findTsources(Integer.parseInt(request.getParameter("source"))));
        r.setClientid(tclientsFacade.findTclients(Integer.parseInt(request.getParameter("client"))));
        r.setPrioriteid(tpriorityFacade.findTpriority(Integer.parseInt(request.getParameter("priorite"))));
        r.setState(statutIncidentFacade.findStatutIncident(Integer.parseInt(request.getParameter("statut"))));
        r.setIsInDelais(-1);
        r.setDiffday(-1);
        r.setIsaffect(0);
        if (request.getParameter("responsable") != null) {
            if (!request.getParameter("responsable").isEmpty()) {
                r.setIsaffect(1);
            }
        }
        try {
            if (is == 0) {
                ic = tincidentsFacade.creer(r);
            } else {
                tincidentsFacade.MisAJour(r);
                ic = r;
            }

        } catch (Exception ex) {
            error = true;
        }

        if (!error) {
            PieceJointe pj1 = new PieceJointe();
            try {
                List<String> chemin = services.SavePJ(request, UPLOAD_DIRECTORY);
                for (String str : chemin) {
                    pj1.setElementJoint(str);
                    pj1.setIncident(ic);
                    pj1.setIswho(0);
                    pieceJointeFacade.Creer(pj1);
                }

            } catch (IOException ex) {
                error1 = true;
                Logger.getLogger(gestionticket.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        if (!error1) {
            if (request.getParameter("responsable") != null) {
                if (!request.getParameter("responsable").isEmpty()) {
                    Tusers responsable = tusersFacade.findTusers(Integer.parseInt(request.getParameter("responsable")));
                    u.setIduser(responsable);
                    u.setIdplainte(ic);
                    if (is == 0 || error2) {
                        userplainteFacade.creer(u);
                        Message message = new Message();
                        message.setNotification("Un Ticket a été Ouvert à votre intention par: " + utilisateur.getFirstname() + "\n Veuillez consulter le ticket N°= '" + ic.getId() + "' dans la plate forme CCMANAGER pour plus de details");
                        message.setTitle("OCM Alerte: Ouverture d'un Ticket de plainte");
                        message.setType("info");
                        message.setEmail("Un Ticket a été Ouvert à votre intention par: " + utilisateur.getFirstname() + "\n Veuillez consulter le ticket N°= '" + ic.getId() + "' dans la plate forme OCM pour plus de details");
                        message.setSubject("OCM Alerte: Ouverture d'un Ticket de plainte");
                        sendNotification(message, responsable);
                        request.setAttribute("message", message);
                    } else {
                        userplainteFacade.MisAJour(u);
                    }
                }
            }

        }
    }

    void TraitementTicketmodel(HttpServletRequest request, Tusers utilisateur) throws Exception {
        String UPLOAD_DIRECTORY = "PJ";
        boolean error = false;
        Message message = new Message();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Tincidents r = tincidentsFacade.findTincidents(Integer.parseInt(request.getParameter("ticket")));
        StatutIncident sta = statutIncidentFacade.find(Integer.parseInt(request.getParameter("statut")));
        r.setState(sta);

        if (sta.getCode() == 200 || sta.getCode() == 401) {
            String st;
            if (!request.getParameter("descriptionFer").isEmpty()) {
                st = request.getParameter("descriptionFer");
            } else {
                st = "Aucun Commentaire";
            }
            String str = "le ticket N°= '" + r.getId() + "' a été cloturer par : " + "<strong>" + utilisateur.getFirstname() + "</strong>"
                    + " \n avec pour statut '" + r.getState().getNom() + "' le: " + sf.format(date_du_jour.dateJour())
                    + "\nCommentaire de Fermeture " + "<strong>" + request.getParameter("descriptionFer") + "</strong>";
            r.setDescriptionFermeture(str);
            r.setDateFer(date_du_jour.dateJour());

            message.setNotification(str);
            message.setTitle("OCM Alerte: Fermeture d'un Ticket de plainte");
            message.setType("success");
            message.setEmail(str);
            message.setSubject("OCM Alerte: Fermeture d'un Ticket de plainte");
            sendNotification(message, r.getCreator());
            request.setAttribute("message", message);
        }
        if (r.getIsInDelais() == -1) {
            Date d1 = date_du_jour.dateConvert(df.format(r.getDateHope()));
            Date d = date_du_jour.dateJourUniqueDate();
            r.setDiffday(date_du_jour.GetDelaisResolution(d, d1));
            if (date_du_jour.GetIfInDelaisResolution(r.getDateHope())) {
                r.setIsInDelais(1);
            } else {
                r.setIsInDelais(0);
            }
        }
        if (request.getParameterValues("commentaire") != null) {
            if (request.getParameterValues("commentaire").length > 0) {
                String[] commen = request.getParameterValues("commentaire");
                for (String str : commen) {
                    TtraitementTicket tt = new TtraitementTicket();
                    tt.setCommentaire(utilisateur.getFirstname() + " : " + str);
                    tt.setDateComment(date_du_jour.dateJour());
                    tt.setIncident(r);
                    tt.setUser(utilisateur);
                    try {
                        TtraitementTicket t = ttraitementTicketFacade.creer(tt);
                    } catch (Exception ex) {
                        error = true;
                    }
                }
                if (r.getTtraitementTicketList().isEmpty()) {
                    r.setDateModif(date_du_jour.dateJour());
                }
                r.setTtraitementTicketList(ttraitementTicketFacade.findAllTickect(r.getId()));
                tincidentsFacade.edit(r);
                String str = "le ticket N°= '" + r.getId() + "' a été Commenter par : " + utilisateur.getFirstname()
                        + " \n avec pour statut '" + r.getState().getNom() + "' le: " + df.format(date_du_jour.dateJourUniqueDate()) + " à " + sf.format(date_du_jour.dateJour());
                message.setNotification(str);
                message.setTitle("OCM Alerte: Commentaire sur un Ticket de plainte");
                message.setType("success");
                message.setEmail(str);
                message.setSubject("OCM Alerte: Fermeture d'un Ticket de plainte");
                try {
                    sendNotification(message, r.getCreator());
                } catch (Exception e) {
                    sendNotification(message, r.getClientid());
                }
                request.setAttribute("message", message);
            }
        }
        if (!error) {
            try {
                List<String> chemin = services.SavePJ(request, UPLOAD_DIRECTORY);
                for (String str : chemin) {
                    PieceJointe pj1 = new PieceJointe();
                    pj1.setElementJoint(str);
                    pj1.setIncident(r);
                    pj1.setIswho(1);
                    PieceJointe p = pieceJointeFacade.Creer(pj1);
                }
                r.setPieceJointeList(pieceJointeFacade.findAllTickect(r.getId()));
            } catch (IOException ex) {
                Logger.getLogger(gestionticket.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        tincidentsFacade.MisAJour(r);
    }

    List<Tincidents> TicketmodelByPeriode(HttpServletRequest request, int reg, String societe) throws Exception {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String[] date1 = request.getParameter("interval").split("-");
        Date d1 = date_du_jour.dateConvert(date1[0]);
        Date d2 = date_du_jour.dateConvert(date1[1]);
        List<Tincidents> listIncidents;
        if (reg == -1) {
            listIncidents = tincidentsFacade.findTincidentsEntitiesByPeriode(d1, d2, societe);
        } else {
            listIncidents = userplainteFacade.findTincidentsEntitiesByPeriode(d1, d2, reg);
        }
        request.setAttribute("SMS", df.format(d1) + " au " + df.format(d2));
        return listIncidents;
    }

    void FindByHistorique(HttpServletRequest request, Tusers tuser) throws Exception {
        List<Tincidents> listIncidents = null;
        List<Tincidents> listIncidents1 = null;
        if (request.getParameter("statut") == null) {
            listIncidents = tincidentsFacade.findTincidentsEntities();
            listIncidents1 = tuser.getTincidentsList();
        } else if (request.getParameter("statut") != null && !request.getParameter("statut").isEmpty()) {
            listIncidents = TicketmodelByPeriode(request, -1, tuser.getSociete().getImmatriculation()).stream()
                    .filter(in -> in.getState().getCode() == Integer.parseInt(request.getParameter("statut")))
                    .collect(Collectors.toList());
            listIncidents1 = TicketmodelByPeriode(request, tuser.getId(), tuser.getSociete().getImmatriculation()).stream()
                    .filter(in -> in.getState().getCode() == Integer.parseInt(request.getParameter("statut")))
                    .collect(Collectors.toList());
        } else if (request.getParameter("statut").isEmpty()) {
            listIncidents = TicketmodelByPeriode(request, -1, tuser.getSociete().getImmatriculation());
            listIncidents1 = TicketmodelByPeriode(request, tuser.getId(), tuser.getSociete().getImmatriculation());
        }
        request.setAttribute("allincidents", listIncidents);
        request.setAttribute("incidentsUser", listIncidents1);
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
            EnvoiEmail.sendMail(p.getMail(), message.getSubject(), message.getEmail());
        } catch (Exception ex) {
            System.err.println("Echec Notification par Mail");
        }
    }

    void sendNotification(Message message, Tclients p) {
        Notification n = new Notification();
        n.setMessage(message.getNotification());
        n.setClient(p.getId());
        n.setLut(0);
        n.setTitel(message.getTitle());
        n.setDateNotif(date_du_jour.dateJour());
        notificationFacade.create(n);
        /*
        try {
            EnvoiEmail.sendMail(p.getAddress1(), message.getSubject(), message.getEmail());
        } catch (Exception ex) {
            System.err.println("Echec Notification par Mail");
        }*/
    }

    void getFichier(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // String fileName = request.getParameter("fileName");
        PieceJointe p = pieceJointeFacade.findPieceJointe(Integer.parseInt(request.getParameter("id")));
        String[] fil = p.getElementJoint().split("/");
        String fileName = fil[fil.length - 1];
        if (fileName == null || fileName.equals("")) {
            throw new ServletException("File Name can't be null or empty");
        }
        File file = new File(p.getElementJoint());
        if (!file.exists()) {
            throw new ServletException("File doesn't exists on server.");
        }

        ServletContext ctx = getServletContext();
        InputStream fis = new FileInputStream(file);
        String mimeType = ctx.getMimeType(file.getAbsolutePath());
        response.setContentType(mimeType != null ? mimeType : "application/octet-stream");
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

        ServletOutputStream os = response.getOutputStream();
        byte[] bufferData = new byte[1024];
        int read = 0;
        while ((read = fis.read(bufferData)) != -1) {
            os.write(bufferData, 0, read);
        }
        os.flush();
        os.close();
        fis.close();
    }

    Tusers getUpdateUtilisateurmodel(HttpServletRequest request, Tusers utilisateur) throws Exception {
        Tusers u = tusersFacade.findTusers(Integer.parseInt(request.getParameter("user")));
        u.setDatemodification(date_du_jour.dateJour());

        if (!request.getParameter("tel").isEmpty()) {
            u.setPhone(request.getParameter("tel"));
        }
        if (!request.getParameter("email").isEmpty()) {
            u.setMail(request.getParameter("email"));
        }
        if (!request.getParameter("adresse").isEmpty()) {
            u.setAddress1(request.getParameter("adresse"));
        }
        if (!request.getParameter("password").isEmpty()) {
            encryptedHash hash = new encryptedHash();
            u.setPassword(hash.encode(request.getParameter("password")));
        }
        return u;
    }

}
