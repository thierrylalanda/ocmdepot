/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.FullControleurs;

import com.eh2s.ocm.Entity.Services.Message;
import com.eh2s.ocm.Entity.Services.Services;
import com.eh2s.ocm.Entity.Services.ServicesInitialize;
import com.eh2s.ocm.Entity.Services.encryptedHash;
import com.eh2s.ocm.Entity.Sessions.NotificationFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.SocieteFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TclientsFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TdistrictsFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TregionsFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TtypeclientsFacadeLocal;
import com.eh2s.ocm.Entity.Societe;
import com.eh2s.ocm.Entity.Tclients;
import com.eh2s.ocm.Entity.Tdistricts;
import com.eh2s.ocm.Entity.Tregions;
import com.eh2s.ocm.Entity.Tsecteurs;
import com.eh2s.ocm.Entity.Ttypeclients;
import com.eh2s.ocm.UtilityFonctions.EnvoiEmail;
import com.eh2s.ocm.UtilityFonctions.date_du_jour;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
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
@WebServlet(name = "ConnectionClients", urlPatterns = {"/getconnexionClient"})
public class ConnectionClients extends HttpServlet {

    @EJB
    private TtypeclientsFacadeLocal ttypeclientsFacade;

    @EJB
    private TdistrictsFacadeLocal tdistrictsFacade;

    @EJB
    private TregionsFacadeLocal tregionsFacade;

    @EJB
    private NotificationFacadeLocal notificationFacade;

    @EJB
    private TclientsFacadeLocal tclientsFacade;

    @EJB
    private SocieteFacadeLocal societeFacade;

    @EJB
    private ServicesInitialize servicesInitializeImple;

    @EJB
    private Services services;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
          if (request.getParameter("getCreationclient") != null) {
              doPost(request, response);
        }else{
               HttpSession session = request.getSession();
        session.invalidate();
        request.getServletContext().getRequestDispatcher("/indexclient.jsp").forward(request, response);
          }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        if (request.getParameter("getCreationclient") != null) {
            List<Societe> ss = societeFacade.findAllByAutoSaveClient(1);
            request.setAttribute("societe", ss);
            request.getServletContext().getRequestDispatcher("/addClient.jsp").forward(request, response);
        }else if (request.getParameter("getEntityJson") != null) {
            switch (request.getParameter("entity")) {
                case "getregion": {
                    JSONArray object = new JSONArray();
                    HashMap dat = new HashMap();
                    List<Tregions> t = societeFacade.findSociete(Integer.parseInt(request.getParameter("id"))).getTregionsList();
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
                case "getDistricts": {
                    JSONArray object1 = new JSONArray();
                    HashMap dat1 = new HashMap();
                    Tregions t1 = tregionsFacade.findTregions(Integer.parseInt(request.getParameter("id")));
                    t1.getTdistrictsList().stream().map((st) -> {
                        dat1.put("id", st.getId());
                        return st;
                    }).map((st) -> {
                        dat1.put("name", st.getName());
                        return st;
                    }).forEach((_item) -> {
                        object1.put(dat1);
                    });
                    response.setContentType("application/json");
                    response.getWriter().print(object1);
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
                 case "getTypeClient": {
                    JSONArray object = new JSONArray();
                    HashMap dat = new HashMap();
                    List<Ttypeclients> t = societeFacade.findSociete(Integer.parseInt(request.getParameter("id"))).getTtypeclientsList();
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
                default:
                    break;
            }
        } else if (request.getParameter("creerClient") != null) {
            Message m = new Message();
            Societe s = societeFacade.findSociete(Integer.parseInt(request.getParameter("societe")));
            int taille = tclientsFacade.findAll(s.getImmatriculation()).size();

            if (services.verifiLicenceMaxUser(s, taille, m)) {
                try {
                    Tclients cli = getTclientsmodel(request);
                    cli = tclientsFacade.creer(cli);
                    m.setType("success");
                    m.setSubject("Creation Nouveau Client OCM");
                    m.setTitle("Operation réussir");
                    m.setNotification("vous avez été enregistrer comme client de la sociéte " + cli.getSociete().getNom());
                    m.setMessage("création d'un nouveau client dans votre plateforme OCM \n"
                            + " Nom: " + cli.getNom() + " \n Tel: " + cli.getTelephone() + " \n Email: " + cli.getMail()
                            + " \n N°= " + cli.getId() + " \n Ville: " + cli.getSecteurid().getDistrictid().getName());
                    request.setAttribute("message", m);
                    try {
                        EnvoiEmail.sendMail(s.getEmail(), m.getSubject(), m.getMessage());
                    } catch (MessagingException ex) {
                        System.err.println("Echec Notification par Mail");
                    }
                } catch (Exception ex) {
                    Logger.getLogger(ConnectionClients.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                m.setType("error");
                m.setSubject("Licence OCM Clients");
                m.setTitle("Operation échouer");
                m.setNotification("Votre licence ne vous permet pas d'ajouter un nouveau client\n"
                        + " veuillez contacter EH2S votre fournisseur de service à l'adresse eh2s-sarl@eh2s.com "
                        + "où appelez au +237 690 62 33 74 pour mettre à jour votre licence");
                request.setAttribute("message", m);
                try {
                    EnvoiEmail.sendMail(s.getEmail(), m.getSubject(), m.getNotification());
                } catch (MessagingException ex) {
                    System.err.println("Echec Notification par Mail");
                }
            }
            request.getServletContext().getRequestDispatcher("/indexclient.jsp").forward(request, response);
        } else {

            String code = request.getParameter("code");
            String password = request.getParameter("password");
            if (!services.getConnectionClient(code, password).isEmpty()) {
                Tclients utilisateur = services.getConnectionClient(code, password).get(0);
                if (!services.verifiLicenceTest(utilisateur.getSociete())) {
                    HttpSession session = request.getSession();
                    session.setAttribute("isconnect", session.getId());
                    session.setAttribute("client", utilisateur);
                    session.setAttribute("societe", utilisateur.getSociete());
                    request.setAttribute("q", request.getParameter("q"));

                    servicesInitializeImple.initiationClients(request, utilisateur);
                    request.getServletContext().getRequestDispatcher("/WEB-INF/main.jsp").forward(request, response);

                } else {
                    request.setAttribute("message", new Message("vous n’êtes plus autorisés à utiliser ce service"));
                    request.getServletContext().getRequestDispatcher("/indexclient.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("message", new Message("Code Client incorrect"));
                request.getServletContext().getRequestDispatcher("/indexclient.jsp").forward(request, response);
            }
        }
    }

    Tclients getTclientsmodel(HttpServletRequest request) throws Exception {
        Tclients u = new Tclients();
        u.setDatecreation(date_du_jour.dateJour());
        u.setSociete(new Societe(Integer.parseInt(request.getParameter("societe"))));
        u.setChgpwd(1);

        if (request.getParameter("password") != null) {
            if (!request.getParameter("password").isEmpty()) {
                try {
                    encryptedHash hash = new encryptedHash();
                    u.setPassword(hash.encode(request.getParameter("password")));
                    u.setPsd(request.getParameter("password"));
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
        u.setSecteurid(new Tsecteurs(Integer.parseInt(request.getParameter("secteur"))));
        u.setTypeclientid(new Ttypeclients(Integer.parseInt(request.getParameter("typeclient"))));

        return u;
    }

}
