/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.FullControleurs;

import com.eh2s.ocm.Entity.Services.Message;
import com.eh2s.ocm.Entity.Services.ServicesInitialize;
import com.eh2s.ocm.Entity.Sessions.TclientsFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TdistrictsFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TincidentsFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TregionsFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TrubriquesFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TsecteursFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TsourcesFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TusersFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.UserplainteFacadeLocal;
import com.eh2s.ocm.Entity.Societe;
import com.eh2s.ocm.Entity.Tclients;
import com.eh2s.ocm.Entity.Tdistricts;
import com.eh2s.ocm.Entity.Tincidents;
import com.eh2s.ocm.Entity.Tregions;
import com.eh2s.ocm.Entity.Trubriques;
import com.eh2s.ocm.Entity.Tsecteurs;
import com.eh2s.ocm.Entity.Tsources;
import com.eh2s.ocm.Entity.Tusers;
import com.eh2s.ocm.Entity.temporaire.reportingresponsable;
import com.eh2s.ocm.UtilityFonctions.date_du_jour;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
@WebServlet(name = "getStatistiquesTicket", urlPatterns = {"/jtzxbkui4528f725jhf"})
public class getStatistiquesTicket extends HttpServlet {

    @EJB
    private ServicesInitialize servicesInitializeImple;

    @EJB
    private UserplainteFacadeLocal userplainteFacade;

    @EJB
    private TincidentsFacadeLocal tincidentsFacade;

    @EJB
    private TclientsFacadeLocal tclientsFacade;

    @EJB
    private TrubriquesFacadeLocal trubriquesFacade;

    @EJB
    private TsourcesFacadeLocal tsourcesFacade;

    @EJB
    private TusersFacadeLocal tusersFacade;

    @EJB
    private TsecteursFacadeLocal tsecteursFacade;

    @EJB
    private TdistrictsFacadeLocal tdistrictsFacade;

    @EJB
    private TregionsFacadeLocal tregionsFacade;

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
            Tusers utilisateur = (Tusers) session.getAttribute("utilisateur");
            String actions = request.getParameter("action");
            if (actions.equals("model")) {
                String model = request.getParameter("model");
                switch (model) {
                    case "TicketmodelByPeriode":
                        switch (Integer.parseInt(request.getParameter("isNew"))) {
                            case 0:
                                try {
                                    switch (Integer.parseInt(request.getParameter("periode"))) {
                                        case 0:

                                            TicketmodelByRegion(request, response, true, false, societe.getImmatriculation());
                                            break;
                                        case 1:

                                            TicketmodelByRegion(request, response, false, true, societe.getImmatriculation());
                                            break;
                                        default:
                                            TicketmodelByRegion(request, response, false, false, societe.getImmatriculation());
                                            break;
                                    }
                                } catch (Exception ex) {
                                    request.setAttribute("erreur", ex.getMessage());
                                }
                                break;

                            case 3:
                                try {
                                    switch (Integer.parseInt(request.getParameter("periode"))) {
                                        case 0:

                                            TicketmodelByResponsable(request, response, false, false, societe.getImmatriculation());
                                            break;
                                        case 1:
                                            TicketmodelByResponsable(request, response, false, true, societe.getImmatriculation());
                                            break;
                                        default:

                                            TicketmodelByResponsable(request, response, true, false, societe.getImmatriculation());
                                            break;
                                    }
                                } catch (Exception ex) {
                                    request.setAttribute("erreur", ex.getMessage());
                                }
                                break;
                            case 4:
                                try {
                                    switch (Integer.parseInt(request.getParameter("periode"))) {
                                        case 0:

                                            TicketmodelByRubriques(request, response, false, false, societe.getImmatriculation());
                                            break;
                                        case 1:
                                            TicketmodelByRubriques(request, response, false, true, societe.getImmatriculation());
                                            break;
                                        default:

                                            TicketmodelByRubriques(request, response, true, false, societe.getImmatriculation());
                                            break;
                                    }
                                } catch (Exception ex) {
                                    request.setAttribute("erreur", ex.getMessage());
                                }
                                break;
                            case 5:
                                try {
                                    switch (Integer.parseInt(request.getParameter("periode"))) {
                                        case 0:
                                            TicketmodelBySecteur(request, response, false, utilisateur);
                                            break;
                                        case 1:
                                            TicketmodelBySecteur(request, response, false, utilisateur);
                                            break;
                                        default:
                                            if (Integer.parseInt(request.getParameter("entity")) == 0) {
                                                TicketmodelBySecteur(request, response, true, utilisateur);
                                            } else {
                                                TicketmodelByDistrict(request, response, true, false, societe.getImmatriculation());
                                            }

                                            break;
                                    }
                                } catch (Exception ex) {
                                    request.setAttribute("erreur", ex.getMessage());
                                }
                                break;
                            case 7:
                                try {
                                    switch (Integer.parseInt(request.getParameter("periode"))) {
                                        case 0:
                                            TicketmodelBySource(request, response, false, false, societe.getImmatriculation());
                                            break;
                                        case 1:
                                            TicketmodelBySource(request, response, false, true, societe.getImmatriculation());
                                            break;
                                        default:
                                            TicketmodelBySource(request, response, true, false, societe.getImmatriculation());
                                            break;
                                    }
                                } catch (Exception ex) {
                                    request.setAttribute("erreur", ex.getMessage());
                                }
                                break;
                            default:
                                break;
                        }
                        break;
                    case "getTicketmodelPrint":
                        try {
                            getJsonDataOneTicket(request, response);
                        } catch (JSONException ex) {
                            Logger.getLogger(getStatistiquesTicket.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    case "getTicketmodelPrintSociete":
                        try {
                            getJsonDataSociete(request, response, societe);
                        } catch (JSONException ex) {
                            Logger.getLogger(getStatistiquesTicket.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
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

    double arrondi(double A, int B) {
        return (double) ((int) (A * Math.pow(10, B) + .5)) / Math.pow(10, B);
    }

    List<Integer> getEntity(HttpServletRequest request) {
        List<Integer> entg = new ArrayList<>();
        String[] ent = request.getParameterValues("entity");
        for (String str : ent) {
            entg.add(Integer.parseInt(str));
        }
        return entg;
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

    reportingresponsable ChagerReportingresponsable(HttpServletRequest request, List<Tincidents> listIncidents) throws Exception {
        reportingresponsable rep = new reportingresponsable();
        rep.setPlainteDansDelais(TicketmodelByIsInDelais(request, listIncidents).get(0));
        rep.setPlainteHorsDelais(TicketmodelByIsInDelais(request, listIncidents).get(1));
        rep.setStatut(TicketmodelByStatut(request, listIncidents, 200).size());
        rep.setStatut1(TicketmodelByStatut(request, listIncidents, 401).size());
        rep.setStatut2(TicketmodelByStatut(request, listIncidents, 404).size());
        rep.setStatut3(TicketmodelByStatut(request, listIncidents, 501).size());
        rep.setStatut4(TicketmodelByStatut(request, listIncidents, 502).size());
        rep.setTotalPlainteRecus(listIncidents.size());
        rep.setTotalPlainteTraiter(rep.getPlainteDansDelais() + rep.getPlainteHorsDelais());
        rep.setPourcentagePlainteDansDelais(arrondi(((double) rep.getPlainteDansDelais() / (double) rep.getTotalPlainteRecus()) * 100, 2));
        rep.setPourcentagePlainteTraiter(arrondi(((double) rep.getTotalPlainteTraiter() / (double) rep.getTotalPlainteRecus()) * 100, 2));
        return rep;
    }

    void TicketmodelByRegion(HttpServletRequest request, HttpServletResponse response, boolean periode, boolean annee, String societe) throws Exception {
        List<Tincidents> listIncidents;
        JSONArray sing;
        if (annee) {
            sing = new JSONArray();
            List<Tincidents> listIncidents1 = new ArrayList<>();
            List<reportingresponsable> l1 = new ArrayList<>();
            Tregions regs = tregionsFacade.findTregions(Integer.parseInt(request.getParameter("id")));
            int ann = Integer.parseInt(request.getParameter("annee"));
            listIncidents = tincidentsFacade.findTicketByAnnee(ann, regs.getSociete().getImmatriculation());
            for (int i = 1; i <= 12; i++) {
                for (Tincidents in : listIncidents) {
                    if (getMonth(in.getDateCreate()) == i && in.getClientid().getSecteurid().getDistrictid().getRegionid().equals(regs)) {
                        listIncidents1.add(in);
                    }

                }
                l1.add(ChagerReportingresponsable(request, listIncidents1));
                listIncidents1.clear();
            }
            sing.put(getJsonData(l1));
        } else {
            listIncidents = tincidentsFacade.findTicketByAnnee(date_du_jour.getYEAR(), societe);
            List<Tincidents> listIncidents1 = new ArrayList<>();
            List<reportingresponsable> l1 = new ArrayList<>();
            sing = new JSONArray();
            for (int i = 1; i <= 12; i++) {
                for (Tincidents in : listIncidents) {
                    if (getMonth(in.getDateCreate()) == i) {
                        listIncidents1.add(in);
                    }

                }
                l1.add(ChagerReportingresponsable(request, listIncidents1));
                listIncidents1.clear();
            }
            sing.put(getJsonData(l1));
        }
        response.setContentType("application/json");
        response.getWriter().print(sing);
    }

    void TicketmodelBySecteur(HttpServletRequest request, HttpServletResponse response, boolean periode, Tusers utilisateur) throws Exception {
        List<Tincidents> listIncidents;
        List<reportingresponsable> l = new ArrayList<>();
        JSONArray sing;
        if (periode) {
            Tregions regs = tregionsFacade.findTregions(Integer.parseInt(request.getParameter("id")));
            listIncidents = TicketmodelByPeriode(request, -1, utilisateur.getSociete().getImmatriculation());
            List<Tsecteurs> s1 = tsecteursFacade.findAll(utilisateur.getSociete().getImmatriculation()).stream().filter(sec -> sec.getDistrictid()
                    .getRegionid().equals(regs)).collect(Collectors.toList());
            for (Tsecteurs re : s1) {
                List<Tincidents> listIncidents1 = listIncidents.stream()
                        .filter(in -> in.getClientid().getSecteurid().equals(re))
                        .collect(Collectors.toList());
                reportingresponsable res = ChagerReportingresponsable(request, listIncidents1);
                res.setEntite(re.getName());
                res.setTitre("TRAITEMENT DES PLAINTES CLIENTS : PAR SECTEUR CUMULER");
                l.add(res);
            }

        } else {
            listIncidents = tincidentsFacade.findTicketByAnnee(date_du_jour.getYEAR(), utilisateur.getSociete().getImmatriculation());
            List<Tsecteurs> s1 = tsecteursFacade.findAll(utilisateur.getSociete().getImmatriculation()).stream().filter(sec -> sec.getDistrictid()
                    .getRegionid().equals(utilisateur.getServiceid().getSite().getRegionid())).collect(Collectors.toList());
            for (Tsecteurs re : s1) {
                List<Tincidents> listIncidents1 = listIncidents.stream()
                        .filter(in -> in.getClientid().getSecteurid().equals(re))
                        .collect(Collectors.toList());
                reportingresponsable res = ChagerReportingresponsable(request, listIncidents1);
                res.setEntite(re.getName());
                res.setTitre("TRAITEMENT DES PLAINTES CLIENTS : PAR SECTEUR CUMULER");
                l.add(res);
            }
        }

        sing = getJsonData(l);
        response.setContentType("application/json");
        response.getWriter().print(sing);

    }

    void TicketmodelByDistrict(HttpServletRequest request, HttpServletResponse response, boolean periode, boolean annee, String societe) throws Exception {

        List<Tincidents> listIncidents;
        List<reportingresponsable> l = new ArrayList<>();
        JSONArray sing;
        if (periode) {
            Tregions regs = tregionsFacade.findTregions(Integer.parseInt(request.getParameter("id")));
            listIncidents = TicketmodelByPeriode(request, -1, societe);
            List<Tdistricts> s1 = tdistrictsFacade.findAll(societe).stream()
                    .filter(sec -> sec.getRegionid().equals(regs)).collect(Collectors.toList());
            for (Tdistricts re : s1) {
                List<Tincidents> listIncidents1 = listIncidents.stream()
                        .filter(in -> in.getClientid().getSecteurid().getDistrictid().equals(re))
                        .collect(Collectors.toList());
                reportingresponsable res = ChagerReportingresponsable(request, listIncidents1);
                res.setEntite(re.getName());
                res.setTitre("TRAITEMENT DES PLAINTES CLIENTS : PAR SECTEUR CUMULER");
                l.add(res);
            }

        }
        sing = getJsonData(l);
        response.setContentType("application/json");
        response.getWriter().print(sing);

    }

    void TicketmodelByRubriques(HttpServletRequest request, HttpServletResponse response, boolean periode, boolean annee, String societe) throws Exception {
        List<Tincidents> listIncidents;
        List<reportingresponsable> l = new ArrayList<>();
        JSONArray sing;
        if (periode) {
            listIncidents = TicketmodelByPeriode(request, -1, societe);
            for (Trubriques re : trubriquesFacade.findAll(societe)) {
                List<Tincidents> listIncidents1 = listIncidents.stream()
                        .filter(in -> in.getSrubriqueid().getRubriqueid().equals(re))
                        .collect(Collectors.toList());
                reportingresponsable res = ChagerReportingresponsable(request, listIncidents1);
                res.setEntite(re.getName());
                res.setTitre("TRAITEMENT DES PLAINTES CLIENTS : PAR RUBRIQUE CUMULER");
                l.add(res);
            }

        } else {
            listIncidents = tincidentsFacade.findTicketByAnnee(date_du_jour.getYEAR(), societe);
            for (Trubriques re : trubriquesFacade.findAll(societe)) {
                List<Tincidents> listIncidents1 = listIncidents.stream()
                        .filter(in -> in.getSrubriqueid().getRubriqueid().equals(re))
                        .collect(Collectors.toList());
                reportingresponsable res = ChagerReportingresponsable(request, listIncidents1);
                res.setEntite(re.getName());
                res.setTitre("TRAITEMENT DES PLAINTES CLIENTS : PAR RUBRIQUE CUMULER");
                l.add(res);
            }
        }

        sing = getJsonData(l);
        response.setContentType("application/json");
        response.getWriter().print(sing);
    }

    void TicketmodelBySource(HttpServletRequest request, HttpServletResponse response, boolean periode, boolean annee, String societe) throws Exception {
        List<reportingresponsable> l = new ArrayList<>();
        JSONArray sing;
        List<Tincidents> listIncidents = tincidentsFacade.findTicketByAnnee(date_du_jour.getYEAR(), societe);
        for (Tsources re : tsourcesFacade.findAll(societe)) {
            List<Tincidents> listIncidents1 = listIncidents.stream().filter(in -> in.getSourceid().equals(re))
                    .collect(Collectors.toList());
            reportingresponsable res = ChagerReportingresponsable(request, listIncidents1);
            res.setEntite(re.getName());
            res.setTitre("TRAITEMENT DES PLAINTES CLIENTS : PAR SOURCE CUMULER");
            l.add(res);
        }
        sing = getJsonData(l);
        response.setContentType("application/json");
        response.getWriter().print(sing);
    }

    void TicketmodelByResponsable(HttpServletRequest request, HttpServletResponse response, boolean periode, boolean annee, String societe) throws Exception {
        List<Tincidents> listIncidents;
        List<reportingresponsable> l = new ArrayList<>();
        JSONArray sing;
        if (periode) {

            Tregions r1 = tregionsFacade.findTregions(Integer.parseInt(request.getParameter("id")));
            listIncidents = TicketmodelByPeriode(request, -1, societe);

            if (Integer.parseInt(request.getParameter("entity")) == 0) {

                List<Tusers> t1 = tusersFacade.findAll(societe).stream().filter(in -> in.getServiceid().getSite().getRegionid()
                        .equals(r1)).collect(Collectors.toList());

                for (Tusers re : t1) {
                    List<Tincidents> listIncidents1 = listIncidents.stream().filter(in -> in.getIsaffect() == 1 && in.getUserplainteList().get(0).getIduser()
                            .equals(re)).collect(Collectors.toList());
                    reportingresponsable res = ChagerReportingresponsable(request, listIncidents1);
                    res.setEntite(re.getFirstname() + " " + re.getLastname());
                    res.setTitre("TRAITEMENT DES PLAINTES CLIENTS : PAR RESPONSABLE CUMULER DE LA PERIODE DU: " + request.getAttribute("SMS").toString());
                    l.add(res);
                }
            } else {
                List<Tclients> t1 = tclientsFacade.findAll(societe).stream().filter(in -> in.getSecteurid().getDistrictid().getRegionid()
                        .equals(r1)).collect(Collectors.toList());
                for (Tclients re : t1) {
                    List<Tincidents> listIncidents1 = listIncidents.stream().filter(in -> in.getIsaffect() == 1 && in.getClientid()
                            .equals(re)).collect(Collectors.toList());
                    reportingresponsable res = ChagerReportingresponsable(request, listIncidents1);
                    res.setEntite(re.getNom() + " " + re.getPrenom());
                    res.setTitre("TRAITEMENT DES PLAINTES CLIENTS : PAR CLIENT CUMULER DE LA PERIODE DU: " + request.getAttribute("SMS").toString());
                    l.add(res);
                }
            }
            sing = getJsonData(l);
        } else {
            listIncidents = tincidentsFacade.findTicketByAnnee(date_du_jour.getYEAR(), societe);
            for (Tusers re : tusersFacade.findAll(societe)) {
                List<Tincidents> listIncidents1 = listIncidents.stream().filter(in -> in.getIsaffect() == 1 && in.getUserplainteList().get(0).getIduser()
                        .equals(re)).collect(Collectors.toList());
                reportingresponsable res = ChagerReportingresponsable(request, listIncidents1);
                res.setEntite(re.getFirstname() + " " + re.getLastname());
                res.setTitre("TRAITEMENT DES PLAINTES CLIENTS : PAR RESPONSABLE CUMULER");
                l.add(res);
            }
            sing = getJsonData(l);
        }

        response.setContentType("application/json");
        response.getWriter().print(sing);
    }

    JSONArray getJsonData(List<reportingresponsable> rep) throws JSONException {
        JSONArray sing = new JSONArray();

        rep.stream().map((re) -> {
            HashMap sig = new HashMap();
            sig.put("PticketTraiter", re.getPourcentagePlainteTraiter());
            sig.put("PticketTraiterDansDelais", re.getPourcentagePlainteDansDelais());
            sig.put("TotalTicket", re.getTotalPlainteRecus());
            sig.put("TotalticketReponse", re.getTotalPlainteTraiter());
            sig.put("TotalticketHorsDelais", re.getPlainteHorsDelais());
            sig.put("TotalticketDansDelais", re.getPlainteDansDelais());
            sig.put("titre", re.getTitre());
            sig.put("entite", re.getEntite());
            sig.put("TotalticketOK", re.getStatut());
            sig.put("TotalticketInsolvable", re.getStatut1());
            sig.put("TotalticketNonResolut", re.getStatut2());
            sig.put("TotalticketEncour", re.getStatut3());
            sig.put("TotalticketDemandeFermeture", re.getStatut4());
            return sig;
        }).forEach((sig) -> {
            sing.put(sig);
        });

        return sing;
    }

    void getJsonDataSociete(HttpServletRequest request, HttpServletResponse response, Societe s) throws JSONException, IOException {
        JSONArray sing = new JSONArray();
        HashMap sig = new HashMap();
        sig.put("adressesociete", s.getAdresse());
        sig.put("codepostalsociete", s.getCodePostal());
        sig.put("mailsociete", s.getEmail());
        sig.put("logosociete", s.getLogoBase64());
        sig.put("nomsociete", s.getNom());
        sig.put("telsociete", s.getTel());

        sing.put(sig);

        response.setContentType("application/json");
        response.getWriter().print(sing);
    }

    void getJsonDataOneTicket(HttpServletRequest request, HttpServletResponse response) throws JSONException, IOException {
        Tincidents re = tincidentsFacade.findTincidents(Integer.parseInt(request.getParameter("id")));
        Societe s = re.getClientid().getSociete();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        DateFormat dff = new SimpleDateFormat("dd/MM/yyyy");
        JSONArray sing = new JSONArray();
        HashMap sig = new HashMap();
        sig.put("adressesociete", s.getAdresse());
        sig.put("codepostalsociete", s.getCodePostal());
        sig.put("mailsociete", s.getEmail());
        sig.put("logosociete", s.getLogoBase64());
        sig.put("nomsociete", s.getNom());
        sig.put("telsociete", s.getTel());

        sig.put("codeclient", re.getClientid().getCodeclient());
        sig.put("adresseclient", re.getClientid().getAdresse());
        sig.put("mailclient", re.getClientid().getMail());
        sig.put("nomclient", re.getClientid().getNom().concat(" " + re.getClientid().getPrenom()));
        sig.put("telclient", re.getClientid().getTelephone());
        sig.put("secteurclient", re.getClientid().getSecteurid().getName());
        sig.put("typeclient", re.getClientid().getTypeclientid().getName());
        sig.put("districtclient", re.getClientid().getSecteurid().getDistrictid().getName());
        sig.put("regionclient", re.getClientid().getSecteurid().getDistrictid().getRegionid().getName());
        sig.put("initiateurticket", re.getCreator().getFirstname().concat(" " + re.getCreator().getLastname()));
        sig.put("responsable", re.getUserplainteList().get(0).getIduser().getFirstname().concat(" " + re.getCreator().getLastname()));
        sig.put("datecreationticket", df.format(re.getDateCreate()));
        if (re.getDateModif() != null) {
            sig.put("datemodifticket", df.format(re.getDateModif()));
        }
        if (re.getDateHope() != null) {
            sig.put("dateecheanceticket", dff.format(re.getDateHope()));
        }
        if (re.getDateFer() != null) {
            sig.put("datefermetureticket", dff.format(re.getDateFer()));
        }

        sig.put("descriptionticket", re.getDescription());
        sig.put("descripfermetureticket", re.getDescriptionFermeture());
        sig.put("tempsmisreponseticket", re.getDiffday());
        sig.put("numerosticket", re.getId());
        if (null != re.getIsInDelais()) {
            switch (re.getIsInDelais()) {
                case 0:
                    sig.put("delaisticket", "OUI");
                    break;
                case 1:
                    sig.put("delaisticket", "NON");
                    break;
                default:
                    sig.put("delaisticket", "ENCOUR");
                    break;
            }
        }
        sig.put("nbrsPJticket", re.getPieceJointeList().size());
        sig.put("prioriteticket", re.getPrioriteid().getName());
        sig.put("sourceticket", re.getSourceid().getName());
        sig.put("srubriqueticket", re.getSrubriqueid().getName());
        sig.put("rubriqueticket", re.getSrubriqueid().getRubriqueid().getName());
        sig.put("statutticket", re.getState().getNom());
        sig.put("objetticket", re.getTitle());
        if (re.getTtraitementTicketList().size() == 1) {
            sig.put("reponseticket0", re.getTtraitementTicketList().get(0).getCommentaire());
            sig.put("datereponseticket0", df.format(re.getTtraitementTicketList().get(0).getDateComment()));
        } else if (re.getTtraitementTicketList().size() > 1) {
            for (int i = 0; i < 2; i++) {
                sig.put("reponseticket" + i, re.getTtraitementTicketList().get(i).getCommentaire());
                sig.put("datereponseticket" + i, df.format(re.getTtraitementTicketList().get(i).getDateComment()));
            }
        }

        sing.put(sig);

        response.setContentType("application/json");
        response.getWriter().print(sing);
    }

    List<Integer> TicketmodelByIsInDelais(HttpServletRequest request, List<Tincidents> incid) throws Exception {
        List<Integer> nj = new ArrayList<>();
        List<Tincidents> listIncidents = incid.stream()
                .filter(in -> in.getIsInDelais() == 0)
                .collect(Collectors.toList());
        nj.add(0, listIncidents.size());
        //request.setAttribute("incidentsDelaisOk", listIncidents);
        List<Tincidents> listIncidents1 = incid.stream()
                .filter(in -> in.getIsInDelais() == 1)
                .collect(Collectors.toList());
        nj.add(1, listIncidents1.size());
        //  request.setAttribute("incidentsHorDelais", listIncidents1);
        return nj;
    }

    List<Tincidents> TicketmodelByStatut(HttpServletRequest request, List<Tincidents> incid, int stat) throws Exception {
        List<Integer> nj = new ArrayList<>();
        List<Tincidents> listIncidents = incid.stream()
                .filter(in -> in.getState().getCode() == stat)
                .collect(Collectors.toList());

        return listIncidents;
    }

    public int getMonth(java.util.Date d) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String[] d2 = df.format(d).split("-");

        return Integer.parseInt(d2[1]);
    }

}
