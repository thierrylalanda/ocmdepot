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
import com.eh2s.ocm.Entity.Sessions.TsrubriquesFacadeLocal;
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
import com.eh2s.ocm.Entity.Tsrubriques;
import com.eh2s.ocm.Entity.Tusers;
import com.eh2s.ocm.Entity.temporaire.reportingresponsable;
import com.eh2s.ocm.UtilityFonctions.date_du_jour;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
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

/**
 *
 * @author messi
 */
@WebServlet(name = "getReportingTicket", urlPatterns = {"/gxwzy14iyf"})
@MultipartConfig(fileSizeThreshold = 6291456, // 6 MB
        maxFileSize = 10485760L, // 10 MB
        maxRequestSize = 20971520L, // 20 MB
        location = "/"
)
public class getReportingTicket extends HttpServlet {

    @EJB
    private ServicesInitialize servicesInitializeImple;

    @EJB
    private UserplainteFacadeLocal userplainteFacade;

    @EJB
    private TincidentsFacadeLocal tincidentsFacade;

    @EJB
    private TclientsFacadeLocal tclientsFacade;

    @EJB
    private TsrubriquesFacadeLocal tsrubriquesFacade;

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

    private static final long serialVersionUID = 1L;

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
                if (model.equals("TicketmodelByPeriode")) {
                    switch (Integer.parseInt(request.getParameter("isNew"))) {
                        case 0:
                            try {
                                if (Integer.parseInt(request.getParameter("periode")) == 0) {
                                    TicketmodelByRegion(request, false, societe.getImmatriculation());
                                } else {
                                    TicketmodelByRegion(request, true, societe.getImmatriculation());
                                }
                            } catch (Exception ex) {
                                request.setAttribute("erreur", ex.getMessage());
                            }
                            break;
                        case 1:
                            try {
                                if (Integer.parseInt(request.getParameter("periode")) == 0) {
                                    TicketmodelByClient(request, false, societe.getImmatriculation());
                                } else {
                                    TicketmodelByClient(request, true, societe.getImmatriculation());
                                }
                            } catch (Exception ex) {
                                request.setAttribute("erreur", ex.getMessage());
                            }
                            break;
                        case 2:
                            try {
                                if (Integer.parseInt(request.getParameter("periode")) == 0) {
                                    TicketmodelByDistrict(request, false, societe.getImmatriculation());
                                } else {
                                    TicketmodelByDistrict(request, true, societe.getImmatriculation());
                                }
                            } catch (Exception ex) {
                                request.setAttribute("erreur", ex.getMessage());
                            }
                            break;
                        case 3:
                            try {
                                if (Integer.parseInt(request.getParameter("periode")) == 0) {
                                    TicketmodelByResponsable(request, false, societe.getImmatriculation());
                                } else {
                                    TicketmodelByResponsable(request, true, societe.getImmatriculation());
                                }
                            } catch (Exception ex) {
                                request.setAttribute("erreur", ex.getMessage());
                            }
                            break;
                        case 4:
                            try {
                                if (Integer.parseInt(request.getParameter("periode")) == 0) {
                                    TicketmodelByRubriques(request, false, societe.getImmatriculation());
                                } else {
                                    TicketmodelByRubriques(request, true, societe.getImmatriculation());
                                }
                            } catch (Exception ex) {
                                request.setAttribute("erreur", ex.getMessage());
                            }
                            break;
                        case 5:
                            try {
                                if (Integer.parseInt(request.getParameter("periode")) == 0) {
                                    TicketmodelBySecteur(request, false, societe.getImmatriculation());
                                } else {
                                    TicketmodelBySecteur(request, true, societe.getImmatriculation());
                                }
                            } catch (Exception ex) {
                                request.setAttribute("erreur", ex.getMessage());
                            }
                            break;
                        case 6:
                            try {
                                if (Integer.parseInt(request.getParameter("periode")) == 0) {
                                    TicketmodelBySrubriques(request, false, societe.getImmatriculation());
                                } else {
                                    TicketmodelBySrubriques(request, true, societe.getImmatriculation());
                                }
                            } catch (Exception ex) {
                                request.setAttribute("erreur", ex.getMessage());
                            }
                            break;
                        case 7:
                            try {
                                if (Integer.parseInt(request.getParameter("periode")) == 0) {
                                    TicketmodelBySource(request, false, societe.getImmatriculation());
                                } else {
                                    TicketmodelBySource(request, true, societe.getImmatriculation());
                                }
                            } catch (Exception ex) {
                                request.setAttribute("erreur", ex.getMessage());
                            }
                            break;
                        case 8: {
                            try {
                                FindByHistorique(request, utilisateur);
                            } catch (Exception ex) {
                                Logger.getLogger(getReportingTicket.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        break;
                        case 9: {
                            try {
                                FindIncident(request, utilisateur);
                            } catch (Exception ex) {
                                Logger.getLogger(getReportingTicket.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        break;
                        default:
                            break;
                    }
                    servicesInitializeImple.initialize(request, utilisateur);
                    request.setAttribute("q", request.getParameter("q"));
                    request.setAttribute("iq", request.getParameter("iq"));
                    request.setAttribute("isNew", request.getParameter("isNew"));
                    request.getServletContext().getRequestDispatcher("/WEB-INF/main.jsp").forward(request, response);
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
        rep.setTotalPlainteRecus(listIncidents.size());
        rep.setTotalPlainteTraiter(rep.getPlainteDansDelais() + rep.getPlainteHorsDelais());
        rep.setPourcentagePlainteDansDelais(arrondi(((double) rep.getPlainteDansDelais() / (double) rep.getTotalPlainteRecus()) * 100, 2));
        rep.setPourcentagePlainteTraiter(arrondi(((double) rep.getTotalPlainteTraiter() / (double) rep.getTotalPlainteRecus()) * 100, 2));
        return rep;
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

    void FindIncident(HttpServletRequest request, Tusers tuser) throws Exception {

        Tincidents incident = null;
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            incident = tincidentsFacade.findTincidents(id);
        } catch (Exception e) {
            e.getMessage();
        }

        request.setAttribute("inc", incident);
    }

    void TicketmodelByRegion(HttpServletRequest request, boolean periode, String societe) throws Exception {
        List<Tincidents> listIncidents;
        List<reportingresponsable> l = new ArrayList<>();
        if (periode) {
            for (Integer reg : getEntity(request)) {
                Tregions regs = tregionsFacade.findTregions(reg);
                listIncidents = TicketmodelByPeriode(request, -1, regs.getSociete().getImmatriculation()).stream()
                        .filter(in -> in.getClientid().getSecteurid().getDistrictid().getRegionid().equals(regs))
                        .collect(Collectors.toList());
                reportingresponsable res = ChagerReportingresponsable(request, listIncidents);
                res.setRegion(regs);
                res.setTitre("TRAITEMENT DES PLAINTES CLIENTS : PAR REGION CUMULER DE LA PERIODE DU: " + request.getAttribute("SMS").toString());
                l.add(res);
            }
        } else {
            for (Tregions re : tregionsFacade.findAll(societe)) {
                listIncidents = tincidentsFacade.findTincidentsEntities().stream()
                        .filter(in -> in.getClientid().getSecteurid().getDistrictid().getRegionid().equals(re))
                        .collect(Collectors.toList());

                try {
                    reportingresponsable res = ChagerReportingresponsable(request, listIncidents);
                    res.setRegion(re);
                    res.setTitre("TRAITEMENT DES PLAINTES CLIENTS : PAR REGION CUMULER");
                    l.add(res);
                } catch (Exception e) {
                }

            }

        }
        request.setAttribute("incidentsEntity", l);
    }

    void TicketmodelBySecteur(HttpServletRequest request, boolean periode, String societe) throws Exception {
        List<Tincidents> listIncidents;
        List<reportingresponsable> l = new ArrayList<>();
        if (periode) {
            for (Integer reg : getEntity(request)) {
                Tsecteurs regs = tsecteursFacade.findTsecteurs(reg);
                listIncidents = TicketmodelByPeriode(request, -1, regs.getDistrictid().getRegionid().getSociete().getImmatriculation()).stream()
                        .filter(in -> in.getClientid().getSecteurid().equals(regs))
                        .collect(Collectors.toList());
                reportingresponsable res = ChagerReportingresponsable(request, listIncidents);
                res.setSecteur(regs);
                res.setTitre("TRAITEMENT DES PLAINTES CLIENTS : PAR SECTEUR CUMULER DE LA PERIODE DU: " + request.getAttribute("SMS").toString());
                l.add(res);
            }
        } else {
            for (Tsecteurs re : tsecteursFacade.findAll(societe)) {
                listIncidents = tincidentsFacade.findTincidentsEntities().stream()
                        .filter(in -> in.getClientid().getSecteurid().equals(re))
                        .collect(Collectors.toList());
                reportingresponsable res = ChagerReportingresponsable(request, listIncidents);
                res.setSecteur(re);
                res.setTitre("TRAITEMENT DES PLAINTES CLIENTS : PAR SECTEUR CUMULER");
                l.add(res);
            }
        }
        request.setAttribute("incidentsEntity", l);
    }

    void TicketmodelByDistrict(HttpServletRequest request, boolean periode, String societe) throws Exception {
        List<Tincidents> listIncidents;
        List<reportingresponsable> l = new ArrayList<>();
        if (periode) {
            for (Integer reg : getEntity(request)) {
                Tdistricts regs = tdistrictsFacade.findTdistricts(reg);
                listIncidents = TicketmodelByPeriode(request, -1, regs.getRegionid().getSociete().getImmatriculation()).stream()
                        .filter(in -> in.getClientid().getSecteurid().getDistrictid().equals(regs))
                        .collect(Collectors.toList());
                reportingresponsable res = ChagerReportingresponsable(request, listIncidents);
                res.setDistrict(regs);
                res.setTitre("TRAITEMENT DES PLAINTES CLIENTS : PAR DISTRICT CUMULER DE LA PERIODE DU: " + request.getAttribute("SMS").toString());
                l.add(res);
            }
        } else {
            for (Tdistricts re : tdistrictsFacade.findAll(societe)) {
                listIncidents = tincidentsFacade.findTincidentsEntities().stream()
                        .filter(in -> in.getClientid().getSecteurid().getDistrictid().equals(re))
                        .collect(Collectors.toList());
                reportingresponsable res = ChagerReportingresponsable(request, listIncidents);
                res.setDistrict(re);
                res.setTitre("TRAITEMENT DES PLAINTES CLIENTS : PAR DISTRICT CUMULER");
                l.add(res);
            }
        }
        request.setAttribute("incidentsEntity", l);
    }

    void TicketmodelByClient(HttpServletRequest request, boolean periode, String societe) throws Exception {
        List<Tincidents> listIncidents;
        List<reportingresponsable> l = new ArrayList<>();
        if (periode) {
            for (Integer reg : getEntity(request)) {
                Tclients regs = tclientsFacade.findTclients(reg);
                listIncidents = TicketmodelByPeriode(request, -1, regs.getSociete().getImmatriculation()).stream()
                        .filter(in -> in.getClientid().equals(regs))
                        .collect(Collectors.toList());
                reportingresponsable res = ChagerReportingresponsable(request, listIncidents);
                res.setClient(regs);
                res.setTitre("TRAITEMENT DES PLAINTES CLIENTS : PAR CLIENT CUMULER DE LA PERIODE DU: " + request.getAttribute("SMS").toString());
                l.add(res);
            }
        } else {
            for (Tclients re : tclientsFacade.findAll(societe)) {
                listIncidents = tincidentsFacade.findTincidentsEntities().stream()
                        .filter(in -> in.getClientid().equals(re))
                        .collect(Collectors.toList());
                reportingresponsable res = ChagerReportingresponsable(request, listIncidents);
                res.setClient(re);
                res.setTitre("TRAITEMENT DES PLAINTES CLIENTS : PAR CLIENT CUMULER");
                l.add(res);
            }
        }
        request.setAttribute("incidentsEntity", l);
    }

    void TicketmodelBySrubriques(HttpServletRequest request, boolean periode, String societe) throws Exception {
        List<Tincidents> listIncidents;
        List<reportingresponsable> l = new ArrayList<>();
        if (periode) {
            for (Integer reg : getEntity(request)) {
                Tsrubriques regs = tsrubriquesFacade.findTsrubriques(reg);
                listIncidents = TicketmodelByPeriode(request, -1, regs.getRubriqueid().getSociete().getImmatriculation()).stream()
                        .filter(in -> in.getSrubriqueid().equals(regs))
                        .collect(Collectors.toList());
                reportingresponsable res = ChagerReportingresponsable(request, listIncidents);
                res.setSrubrique(regs);
                res.setTitre("TRAITEMENT DES PLAINTES CLIENTS : PAR SOUS RUBRIQUE CUMULER DE LA PERIODE DU: " + request.getAttribute("SMS").toString());
                l.add(res);
            }
        } else {
            for (Tsrubriques re : tsrubriquesFacade.findAll(societe)) {
                listIncidents = tincidentsFacade.findTincidentsEntities().stream()
                        .filter(in -> in.getSrubriqueid().equals(re))
                        .collect(Collectors.toList());
                reportingresponsable res = ChagerReportingresponsable(request, listIncidents);
                res.setSrubrique(re);
                res.setTitre("TRAITEMENT DES PLAINTES CLIENTS : PAR SOUS RUBRIQUE CUMULER");
                l.add(res);
            }
        }
        request.setAttribute("incidentsEntity", l);
    }

    void TicketmodelByRubriques(HttpServletRequest request, boolean periode, String societe) throws Exception {
        List<Tincidents> listIncidents;
        List<reportingresponsable> l = new ArrayList<>();
        if (periode) {
            for (Integer reg : getEntity(request)) {
                Trubriques regs = trubriquesFacade.findTrubriques(reg);
                listIncidents = TicketmodelByPeriode(request, -1, regs.getSociete().getImmatriculation()).stream()
                        .filter(in -> in.getSrubriqueid().getRubriqueid().equals(regs))
                        .collect(Collectors.toList());
                reportingresponsable res = ChagerReportingresponsable(request, listIncidents);
                res.setRubrique(regs);
                res.setTitre("TRAITEMENT DES PLAINTES CLIENTS : PAR RUBRIQUE CUMULER DE LA PERIODE DU: " + request.getAttribute("SMS").toString());
                l.add(res);
            }
        } else {
            for (Trubriques re : trubriquesFacade.findAll(societe)) {
                listIncidents = tincidentsFacade.findTincidentsEntities().stream()
                        .filter(in -> in.getSrubriqueid().getRubriqueid().equals(re))
                        .collect(Collectors.toList());
                reportingresponsable res = ChagerReportingresponsable(request, listIncidents);
                res.setRubrique(re);
                res.setTitre("TRAITEMENT DES PLAINTES CLIENTS : PAR RUBRIQUE CUMULER");
                l.add(res);
            }
        }
        request.setAttribute("incidentsEntity", l);
    }

    void TicketmodelBySource(HttpServletRequest request, boolean periode, String societe) throws Exception {
        List<Tincidents> listIncidents;
        List<reportingresponsable> l = new ArrayList<>();
        if (periode) {
            for (Integer reg : getEntity(request)) {
                Tsources regs = tsourcesFacade.findTsources(reg);
                listIncidents = TicketmodelByPeriode(request, -1, societe).stream()
                        .filter(in -> in.getSourceid().equals(regs))
                        .collect(Collectors.toList());
                reportingresponsable res = ChagerReportingresponsable(request, listIncidents);
                res.setSource(regs);
                res.setTitre("TRAITEMENT DES PLAINTES CLIENTS : PAR SOURCE CUMULER DE LA PERIODE DU: " + request.getAttribute("SMS").toString());
                l.add(res);
            }
        } else {
            for (Tsources re : tsourcesFacade.findAll(societe)) {
                listIncidents = tincidentsFacade.findTincidentsEntities().stream()
                        .filter(in -> in.getSourceid().equals(re))
                        .collect(Collectors.toList());
                reportingresponsable res = ChagerReportingresponsable(request, listIncidents);
                res.setSource(re);
                res.setTitre("TRAITEMENT DES PLAINTES CLIENTS : PAR SOURCE CUMULER");
                l.add(res);
            }
        }
        request.setAttribute("incidentsEntity", l);
    }

    void TicketmodelByResponsable(HttpServletRequest request, boolean periode, String societe) throws Exception {
        List<Tincidents> listIncidents;
        List<reportingresponsable> l = new ArrayList<>();
        if (periode) {
            for (Integer reg : getEntity(request)) {
                Tusers regs = tusersFacade.findTusers(reg);
                listIncidents = TicketmodelByPeriode(request, reg, regs.getSociete().getImmatriculation());
                reportingresponsable res = ChagerReportingresponsable(request, listIncidents);
                res.setResponsable(regs);
                res.setTitre("TRAITEMENT DES PLAINTES CLIENTS : PAR RESPONSABLE CUMULER DE LA PERIODE DU: " + request.getAttribute("SMS").toString());
                l.add(res);
            }
        } else {
            for (Tusers re : tusersFacade.findAll(societe)) {
                listIncidents = tincidentsFacade.findTincidentsEntities().stream()
                        .filter(in -> in.getUserplainteList().get(0).getIduser().equals(re))
                        .collect(Collectors.toList());
                reportingresponsable res = ChagerReportingresponsable(request, listIncidents);
                res.setResponsable(re);
                res.setTitre("TRAITEMENT DES PLAINTES CLIENTS : PAR RESPONSABLE CUMULER");
                l.add(res);
            }
        }
        request.setAttribute("incidentsEntity", l);
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

}
