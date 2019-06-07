/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.FullControleurs;

import com.eh2s.ocm.Entity.Notification;
import com.eh2s.ocm.Entity.Services.Message;
import com.eh2s.ocm.Entity.Services.ServicesInitialize;
import com.eh2s.ocm.Entity.Sessions.NotificationFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.SocieteFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TarticlesFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TcategorieFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TclientsFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TcommandesFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TdistrictsFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TetatcFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TlignecommandeFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TournerFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TregionsFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TsecteursFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TusersFacadeLocal;
import com.eh2s.ocm.Entity.Societe;
import com.eh2s.ocm.Entity.Taffectzone;
import com.eh2s.ocm.Entity.Tarticles;
import com.eh2s.ocm.Entity.Tcategorie;
import com.eh2s.ocm.Entity.Tclients;
import com.eh2s.ocm.Entity.Tcommandes;
import com.eh2s.ocm.Entity.Tdistricts;
import com.eh2s.ocm.Entity.Tetatc;
import com.eh2s.ocm.Entity.Tlignecommande;
import com.eh2s.ocm.Entity.Tourner;
import com.eh2s.ocm.Entity.Tregions;
import com.eh2s.ocm.Entity.Tsecteurs;
import com.eh2s.ocm.Entity.Tusers;
import com.eh2s.ocm.UtilityFonctions.EnvoiEmail;
import com.eh2s.ocm.UtilityFonctions.date_du_jour;
import com.eh2s.ocm.UtilityFonctions.statistique;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
@WebServlet(name = "gestionStatistiquesCommandes", urlPatterns = {"/gestionStatistiquesCommandes"})

public class gestionStatistiquesCommandes extends HttpServlet {

    @EJB
    private TusersFacadeLocal tusersFacade;

    @EJB
    private TsecteursFacadeLocal tsecteursFacade;

    @EJB
    private TdistrictsFacadeLocal tdistrictsFacade;

    @EJB
    private TregionsFacadeLocal tregionsFacade;

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
            Tusers t = (Tusers) session.getAttribute("personnel");
            String actions = request.getParameter("action");
            request.setAttribute("q", request.getParameter("q"));
            if (firs) {
                // MisAJourMarge(societe);
                // CopyArticle();
                //UpDateLG(societe);
                firs = false;
            }

            if (actions.equals("model")) {
                String model = request.getParameter("model");
                switch (model) {
                    case "getVenteAnnuel": {
                        try {
                            getVenteAnnuel(request, response, societe);
                        } catch (JSONException ex) {
                            Logger.getLogger(gestionStatistiquesCommandes.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    }
                    case "getVenteAllAnnee": {
                        try {
                            getVenteAllAnnee(request, response, societe);
                        } catch (Exception ex) {
                            Logger.getLogger(gestionStatistiquesCommandes.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    }
                    case "getVenteAnnuelByEntity": {
                        try {
                            getVenteAnnuelByEntity(request, response, societe);
                        } catch (JSONException ex) {
                            Logger.getLogger(gestionStatistiquesCommandes.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    }
                    case "getVenteAnnuelByCategorie": {
                        try {
                            getVenteAnnuelCategorie(request, response, societe);
                        } catch (JSONException ex) {
                            Logger.getLogger(gestionStatistiquesCommandes.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    }
                    case "getVenteAnnuelCategorieByEntity": {
                        try {
                            getVenteAnnuelCategorieByEntity(request, response, societe);
                        } catch (JSONException ex) {
                            Logger.getLogger(gestionStatistiquesCommandes.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    }
                    case "CommandeTOP10": {
                        try {
                            CommandeTOP10(request, response, societe);
                        } catch (IOException ex) {
                            Logger.getLogger(gestionStatistiquesCommandes.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    }
                    case "getEntityZone": {
                        try {
                            getEntity((Tusers) session.getAttribute("utilisateur"), response);
                        } catch (IOException ex) {
                            Logger.getLogger(gestionStatistiquesCommandes.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    }
                }

            } else {
                if (session.getAttribute("utilisateur") != null) {
                    request.setAttribute("commandes", servicesInitializeImple.getCommandesByZoneAutorize((Tusers) session.getAttribute("utilisateur")));
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
// Statistiques

    void getVenteAllAnnee(HttpServletRequest request, HttpServletResponse response, Societe societe) throws IOException {
        JSONArray sing = new JSONArray();
        String[] annee = request.getParameter("annee").split(",");
        List<Tlignecommande> listCommandesH;
        for (String str : annee) {
            int an = Integer.parseInt(str);
            listCommandesH = tlignecommandeFacade.VenteAnnuel(an, societe.getId(), 200);
            HashMap cmd = new HashMap();
            cmd.put("annee", str);
            cmd.put("QT", venteDeAnnee(an, listCommandesH).get("qt"));
            cmd.put("MT", venteDeAnnee(an, listCommandesH).get("mt"));
            sing.put(cmd);
        }

        response.setContentType("application/json");
        response.getWriter().print(sing);
    }

    void getVenteAnnuel(HttpServletRequest request, HttpServletResponse response, Societe societe) throws JSONException, IOException {
        JSONArray sing = new JSONArray();
        int annee = Integer.parseInt(request.getParameter("annee"));
        // String etat = request.getParameter("etat");
        List<Tlignecommande> listCommandesH = tlignecommandeFacade.VenteAnnuel(annee, societe.getId(), 200);
        for (int i = 1; i <= 12; i++) {
            HashMap cmd = new HashMap();
            cmd.put("Mois", getMONTH(i));
            cmd.put("QT", venteDuMois(i, listCommandesH).get("qt"));
            cmd.put("MT", venteDuMois(i, listCommandesH).get("mt"));
            sing.put(cmd);
        }
        response.setContentType("application/json");
        response.getWriter().print(sing);
    }

    void getVenteAnnuelByEntity(HttpServletRequest request, HttpServletResponse response, Societe societe) throws JSONException, IOException {
        JSONArray sing = new JSONArray();
        int annee = Integer.parseInt(request.getParameter("annee"));
        int who = Integer.parseInt(request.getParameter("who"));
        // String etat = request.getParameter("etat");
        List<Tlignecommande> listCommandesH = tlignecommandeFacade.VenteAnnuel(annee, societe.getId(), 200);
        listCommandesH = getLigneByEntity(request, who, listCommandesH);
        for (int i = 1; i <= 12; i++) {
            HashMap cmd = new HashMap();
            cmd.put("Mois", getMONTH(i));
            cmd.put("QT", venteDuMois(i, listCommandesH).get("qt"));
            cmd.put("MT", venteDuMois(i, listCommandesH).get("mt"));
            sing.put(cmd);
        }
        response.setContentType("application/json");
        response.getWriter().print(sing);
    }

    void getVenteAnnuelCategorie(HttpServletRequest request, HttpServletResponse response, Societe societe) throws JSONException, IOException {
        JSONArray sing = new JSONArray();
        int annee = Integer.parseInt(request.getParameter("annee"));
        List<Tlignecommande> listCommandesH = tlignecommandeFacade.VenteAnnuel(annee, societe.getId(), 200);
        List<Tcategorie> lcat = tcategorieFacade.findAll(societe.getImmatriculation());

        for (Tcategorie cat : lcat) {
            HashMap cate = new HashMap();
            JSONArray sin = new JSONArray();
            for (int i = 1; i <= 12; i++) {
                HashMap cmd = new HashMap();
                cmd.put("Mois", getMONTH(i));
                cmd.put("QT", venteDuMoisCategorie(cat.getId(), i, listCommandesH).get("qt"));
                cmd.put("MT", venteDuMoisCategorie(cat.getId(), i, listCommandesH).get("mt"));
                sin.put(cmd);
            }
            cate.put("nom", cat.getNom());
            cate.put("data", sin);
            sing.put(cate);
        }
        response.setContentType("application/json");
        response.getWriter().print(sing);
    }

    void getVenteAnnuelCategorieByEntity(HttpServletRequest request, HttpServletResponse response, Societe societe) throws JSONException, IOException {
        JSONArray sing = new JSONArray();
        int annee = Integer.parseInt(request.getParameter("annee"));
        int who = Integer.parseInt(request.getParameter("who"));
        //  String etat = request.getParameter("etat");
        List<Tlignecommande> listCommandesH = tlignecommandeFacade.VenteAnnuel(annee, societe.getId(), 200);

        listCommandesH = getLigneByEntity(request, who, listCommandesH);
        List<Tcategorie> lcat = tcategorieFacade.findAllBySociete(societe.getId());
        for (Tcategorie cat : lcat) {
            HashMap cate = new HashMap();
            JSONArray sin = new JSONArray();
            for (int i = 1; i <= 12; i++) {
                HashMap cmd = new HashMap();
                cmd.put("Mois", getMONTH(i));
                cmd.put("QT", venteDuMoisCategorie(cat.getId(), i, listCommandesH).get("qt"));
                cmd.put("MT", venteDuMoisCategorie(cat.getId(), i, listCommandesH).get("mt"));
                sin.put(cmd);
            }
            cate.put("nom", cat.getNom());
            cate.put("data", sin);
            sing.put(cate);
        }

        response.setContentType("application/json");
        response.getWriter().print(sing);
    }

    void CommandeTOP10(HttpServletRequest request, HttpServletResponse response, Societe societe) throws IOException {
        // int etat = Integer.parseInt(request.getParameter("statut"));
        String[] date1 = request.getParameter("interval").split("-");
        Date d1 = date_du_jour.dateConvert(date1[0]);
        Date d2 = date_du_jour.dateConvert(date1[1]);
        int who = Integer.parseInt(request.getParameter("who"));
        int is = Integer.parseInt(request.getParameter("is"));
        int top = Integer.parseInt(request.getParameter("top"));
        List<statistique> listTopEntity = null;
        JSONArray reg2 = new JSONArray();
        if (is == 0) {
            if (who == 7) {
                listTopEntity = tlignecommandeFacade.findTTopClientsNational(d1, d2, top, societe.getId(), 200);
            } else if (who == 6) {
                listTopEntity = tlignecommandeFacade.findTTopPresellerNational(d1, d2, top, societe.getId(), 200);
            } else if (who == 5) {
                listTopEntity = tlignecommandeFacade.findTTopVillesNational(d1, d2, top, societe.getId(), 200);
            } else if (who == 4) {
                listTopEntity = tlignecommandeFacade.findTTopSecteursNational(d1, d2, top, societe.getId(), 200);
            } else if (who == 3) {
                listTopEntity = tlignecommandeFacade.findTTopRegionsNational(d1, d2, top, societe.getId(), 200);
            } else if (who == 2) {
                listTopEntity = tlignecommandeFacade.findTTopArticlesNational(d1, d2, top, societe.getId(), 200);
            } else if (who == 1) {
                listTopEntity = tlignecommandeFacade.findTTopCategorieNational(d1, d2, top, societe.getId(), 200);
            } else if (who == 0) {
                listTopEntity = tlignecommandeFacade.findTTopArticleMoinVenduNational(d1, d2, top, societe.getId(), 200);
            } else if (who == 8) {
                listTopEntity = tlignecommandeFacade.findTTopCategorieMoinCommanderNational(d1, d2, top, societe.getId(), 200);
            }
        } else {
            int reg = Integer.parseInt(request.getParameter("region"));
            if (who == 7) {
                listTopEntity = tlignecommandeFacade.findTTopClientsRegional(d1, d2, top, societe.getId(), 200, reg);
            } else if (who == 6) {
                listTopEntity = tlignecommandeFacade.findTTopPresellerRegional(d1, d2, top, societe.getId(), 200, reg);
            } else if (who == 5) {
                listTopEntity = tlignecommandeFacade.findTTopVillesRegional(d1, d2, top, societe.getId(), 200, reg);
            } else if (who == 4) {
                listTopEntity = tlignecommandeFacade.findTTopSecteursRegional(d1, d2, top, societe.getId(), 200, reg);
            } else if (who == 3) {
                listTopEntity = tlignecommandeFacade.findTTopArticlesRegion(d1, d2, top, societe.getId(), 200, reg);
            } else if (who == 2) {
                listTopEntity = tlignecommandeFacade.findTTopArticleMoinVenduRegional(d1, d2, top, societe.getId(), 200, reg);
            } else if (who == 1) {
                listTopEntity = tlignecommandeFacade.findTTopCategorieRegional(d1, d2, top, societe.getId(), 200, reg);
            } else if (who == 0) {
                // listTopEntity = tlignecommandeFacade.findTTopArticleMoinVenduNational(d2, d1, 10, societe.getId(), 200);
            }
        }
        for (statistique sta : listTopEntity) {
            HashMap cmd = new HashMap();
            cmd.put("nom", sta.getNom());
            cmd.put("montant", sta.getMontant());
            cmd.put("qt", sta.getQt());
            reg2.put(cmd);
        }
        response.setContentType("application/json");
        response.getWriter().print(reg2);
    }

    HashMap venteDuMois(int mois, List<Tlignecommande> listCommandesH) {
        HashMap cmd = new HashMap();
        int qt = 0;
        double montant = 0;
        for (Tlignecommande lg : listCommandesH) {
            if (getMONTH(lg.getDatelivraison()) == mois) {
                montant += lg.getPrixtotal();
                qt += lg.getQtotal();
            }
        }
        cmd.put("qt", qt);
        cmd.put("mt", montant);
        return cmd;
    }

    HashMap venteDeAnnee(int annee, List<Tlignecommande> listCommandesH) {
        HashMap cmd = new HashMap();
        int qt = 0;
        double montant = 0;
        for (Tlignecommande lg : listCommandesH) {
            if (getYear(lg.getDatelivraison()) == annee) {
                montant += lg.getPrixtotal();
                qt += lg.getQtotal();
            }
        }
        cmd.put("qt", qt);
        cmd.put("mt", montant);
        return cmd;
    }

    HashMap venteDuMoisCategorie(int cat, int mois, List<Tlignecommande> listCommandesH) {
        HashMap cmd = new HashMap();
        int qt = 0;
        double montant = 0;
        for (Tlignecommande lg : listCommandesH) {
            if (getMONTH(lg.getDatelivraison()) == mois) {
                for (Tcommandes cc : lg.getTcommandesList()) {
                    if (cc.getArticle().getCategorie().getId() == cat) {
                        qt += cc.getQuantite();
                        montant += cc.getPrixTotal();
                    }
                }

            }
        }
        cmd.put("qt", qt);
        cmd.put("mt", montant);
        return cmd;
    }

    List<Tlignecommande> getLigneByEntity(HttpServletRequest request, int who, List<Tlignecommande> listCommandesH) {
        List<Tlignecommande> listCommandes = new ArrayList<>();
        if (who == 0) {
            String[] reg = request.getParameterValues("region");
            for (String str : reg) {
                Tregions rg = tregionsFacade.findTregions(Integer.parseInt(str));
                for (Tlignecommande lg : listCommandesH) {
                    if (lg.getSociete().getTregionsList().contains(rg)) {
                        listCommandes.add(lg);
                    }
                }
            }
        } else if (who == 1) {
            String[] reg = request.getParameterValues("ville");
            for (String str : reg) {
                Tdistricts rg = tdistrictsFacade.findTdistricts(Integer.parseInt(str));
                for (Tlignecommande lg : listCommandesH) {
                    if (lg.getClient().getSecteurid().getDistrictid().equals(rg)) {
                        listCommandes.add(lg);
                    }
                }
            }
        } else if (who == 2) {
            String[] reg = request.getParameterValues("secteur");
            for (String str : reg) {
                Tsecteurs rg = tsecteursFacade.findTsecteurs(Integer.parseInt(str));
                for (Tlignecommande lg : listCommandesH) {
                    if (lg.getClient().getSecteurid().equals(rg)) {
                        listCommandes.add(lg);
                    }
                }
            }
        } else if (who == 3) {
            String[] reg = request.getParameterValues("client");
            for (String str : reg) {
                Tclients rg = tclientsFacade.findTclients(Integer.parseInt(str));
                for (Tlignecommande lg : listCommandesH) {
                    if (lg.getClient().equals(rg)) {
                        listCommandes.add(lg);
                    }
                }
            }
        } else if (who == 4) {
            String[] reg = request.getParameterValues("tourner");
            for (String str : reg) {
                Tourner rg = tournerFacade.findTourner(Integer.parseInt(str));
                for (Tlignecommande lg : listCommandesH) {
                    if (lg.getTourner().equals(rg)) {
                        listCommandes.add(lg);
                    }
                }
            }
        } else if (who == 5) {
            String[] reg = request.getParameterValues("presalleur");
            for (String str : reg) {
                Tusers rg = tusersFacade.findTusers(Integer.parseInt(str));
                for (Tlignecommande lg : listCommandesH) {
                    if (lg.getTourner().getAffectTournerUserList().containsAll(rg.getAffectTournerUserList())) {
                        listCommandes.add(lg);
                    }
                }
            }
        }

        return listCommandes;
    }

    int getMONTH(java.util.Date date) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String dat = df.format(date);
        return Integer.parseInt(dat.split("/")[1]);
    }

    int getYear(java.util.Date date) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String dat = df.format(date);
        return Integer.parseInt(dat.split("/")[2]);
    }

    String getMONTH(int mois) {
        switch (mois) {
            case 1:
                return "Janvier";
            case 2:
                return "Février";
            case 3:
                return "Mars";
            case 4:
                return "Avril";
            case 5:
                return "Mai";
            case 6:
                return "Juin";
            case 7:
                return "Juiellet";
            case 8:
                return "Aoùt";
            case 9:
                return "Septembre";
            case 10:
                return "Octobre";
            case 11:
                return "Novembre";
            case 12:
                return "Décembre";
            default:
                return "Not Fount";
        }
    }

    void getEntity(Tusers u, HttpServletResponse response) throws IOException {
        HashMap<String, String> map = new HashMap();
        JSONArray reg2 = new JSONArray();
        JSONArray cl1 = new JSONArray();
        JSONArray tr2 = new JSONArray();
        JSONArray pr2 = new JSONArray();
        HashMap<String, Object> map1 = new HashMap();
        Set<Tusers> preselleur = new HashSet<>();
        Set<Tourner> tourne = new HashSet<>();
        JSONArray sing = new JSONArray();
        for (Taffectzone aff : u.getTaffectzoneList()) {
            if (aff.getRegion() != null) {
                Tregions rg = tregionsFacade.findTregions(aff.getRegion());
                map.put("id", rg.getId().toString());
                map.put("nom", rg.getName());
                reg2.put(map);
                for (Tclients cl : tclientsFacade.findTclientsByRegion(rg.getId(), u.getSociete().getImmatriculation(), 0)) {
                    HashMap<String, String> c1 = new HashMap();
                    c1.put("id", cl.getId().toString());
                    c1.put("nom", cl.getNom());
                    cl1.put(c1);
                    if (cl.getTourner() != null) {
                        tourne.add(cl.getTourner());
                        if (!cl.getTourner().getAffectTournerUserList().isEmpty()) {

                            preselleur.add(cl.getTourner().getAffectTournerUserList().get(0).getUser());
                        }
                    }

                }
                for (Tourner tr : tourne) {
                    HashMap<String, String> tr1 = new HashMap();
                    tr1.put("id", tr.getId().toString());
                    tr1.put("nom", tr.getNumc());
                    tr2.put(tr1);
                }
                for (Tusers us : preselleur) {
                    HashMap<String, String> pr1 = new HashMap();
                    pr1.put("id", us.getId().toString());
                    pr1.put("nom", us.getFirstname());
                    pr2.put(pr1);
                }

            } else if (aff.getDistrict() != null) {
                Tdistricts rg = tdistrictsFacade.findTdistricts(aff.getDistrict());
                map.put("id", rg.getId().toString());
                map.put("nom", rg.getName());
                reg2.put(map);
                for (Tclients cl : tclientsFacade.findTclientsByRegion(rg.getId(), u.getSociete().getImmatriculation(), 1)) {
                    HashMap<String, String> c1 = new HashMap();
                    c1.put("id", cl.getId().toString());
                    c1.put("nom", cl.getNom());
                    cl1.put(c1);

                    if (cl.getTourner() != null) {
                        tourne.add(cl.getTourner());
                        if (!cl.getTourner().getAffectTournerUserList().isEmpty()) {

                            preselleur.add(cl.getTourner().getAffectTournerUserList().get(0).getUser());
                        }
                    }
                }
                for (Tourner tr : tourne) {
                    HashMap<String, String> tr1 = new HashMap();
                    tr1.put("id", tr.getId().toString());
                    tr1.put("nom", tr.getNumc());
                    tr2.put(tr1);
                }
                for (Tusers us : preselleur) {
                    HashMap<String, String> pr1 = new HashMap();
                    pr1.put("id", us.getId().toString());
                    pr1.put("nom", us.getFirstname());
                    pr2.put(pr1);
                }
            } else if (aff.getSecteur() != null) {
                Tsecteurs rg = tsecteursFacade.findTsecteurs(aff.getSecteur());
                map.put("id", rg.getId().toString());
                map.put("nom", rg.getName());
                reg2.put(map);
                for (Tclients cl : tclientsFacade.findTclientsByRegion(rg.getId(), u.getSociete().getImmatriculation(), 2)) {
                    HashMap<String, String> c1 = new HashMap();
                    c1.put("id", cl.getId().toString());
                    c1.put("nom", cl.getNom());
                    cl1.put(c1);
                    if (cl.getTourner() != null) {
                        tourne.add(cl.getTourner());
                        if (!cl.getTourner().getAffectTournerUserList().isEmpty()) {

                            preselleur.add(cl.getTourner().getAffectTournerUserList().get(0).getUser());
                        }
                    }
                }
                for (Tourner tr : tourne) {
                    HashMap<String, String> tr1 = new HashMap();
                    tr1.put("id", tr.getId().toString());
                    tr1.put("nom", tr.getNumc());
                    tr2.put(tr1);
                }
                for (Tusers us : preselleur) {
                    HashMap<String, String> pr1 = new HashMap();
                    pr1.put("id", us.getId().toString());
                    pr1.put("nom", us.getFirstname());
                    pr2.put(pr1);
                }
            }
        }
        map1.put("entity", reg2);
        map1.put("clients", cl1);
        map1.put("tourner", tr2);
        map1.put("preselleur", pr2);
        sing.put(map1);
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
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
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

    void TOP10ClientNational(HttpServletRequest request, HttpServletResponse response, Societe societe) throws IOException {

    }

}
