/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.FullControleurs;

import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Services.Message;
import com.eh2s.ocm.Entity.Services.Services;
import com.eh2s.ocm.Entity.Services.encryptedHash;
import com.eh2s.ocm.Entity.Sessions.SocieteFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.SouscriptionLicenceFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TactionsFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TaffectzoneFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TgroupsAssocFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TgroupsFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TregionsFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TservicesFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TsitesFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TusersFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TypeLicenceFacadeLocal;
import com.eh2s.ocm.Entity.Societe;
import com.eh2s.ocm.Entity.SouscriptionLicence;
import com.eh2s.ocm.Entity.Tactions;
import com.eh2s.ocm.Entity.Taffectzone;
import com.eh2s.ocm.Entity.Tgroups;
import com.eh2s.ocm.Entity.TgroupsAssoc;
import com.eh2s.ocm.Entity.Tregions;
import com.eh2s.ocm.Entity.Tservices;
import com.eh2s.ocm.Entity.Tsites;
import com.eh2s.ocm.Entity.Tusers;
import com.eh2s.ocm.Entity.TypeLicence;
import com.eh2s.ocm.UtilityFonctions.EnvoiEmail;
import com.eh2s.ocm.UtilityFonctions.date_du_jour;
import java.io.IOException;
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
@WebServlet(name = "FreeAdminConnection", urlPatterns = {"/FreeAdmingetconnexion"})
public class FreeAdmin extends HttpServlet {

    @EJB
    private TaffectzoneFacadeLocal taffectzoneFacade;

    @EJB
    private TypeLicenceFacadeLocal typeLicenceFacade;

    @EJB
    private SouscriptionLicenceFacadeLocal souscriptionLicenceFacade;

    @EJB
    private TgroupsAssocFacadeLocal tgroupsAssocFacade;

    @EJB
    private TactionsFacadeLocal tactionsFacade;

    @EJB
    private TgroupsFacadeLocal tgroupsFacade;

    @EJB
    private TservicesFacadeLocal tservicesFacade;

    @EJB
    private TsitesFacadeLocal tsitesFacade;

    @EJB
    private TregionsFacadeLocal tregionsFacade;

    @EJB
    private TusersFacadeLocal tusersFacade;

    @EJB
    private SocieteFacadeLocal societeFacade;

    @EJB
    private Services services;

    private static final String UPLOAD_DIR = "PJ";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session;
        request.setAttribute("q", request.getParameter("q"));
        if (request.getParameter("newIstance") != null) {
            if (request.getParameter("changePassword") != null) {
                Tusers u = tusersFacade.findTusers(Integer.parseInt(request.getParameter("user")));
                u.setDatemodification(date_du_jour.dateJour());
                if (!request.getParameter("email").isEmpty()) {
                    u.setMail(request.getParameter("email"));
                }
                encryptedHash hash = new encryptedHash();
                u.setLogin(request.getParameter("login"));
                try {
                    u.setPassword(hash.encode(request.getParameter("password")));
                } catch (Exception ex) {
                    Logger.getLogger(FreeAdmin.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    u.setChgpwd(1);
                    tusersFacade.MisAJour(u);
                } catch (NonexistentEntityException ex) {
                    Logger.getLogger(FreeAdmin.class.getName()).log(Level.SEVERE, null, ex);
                } catch (RollbackFailureException ex) {
                    Logger.getLogger(FreeAdmin.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(FreeAdmin.class.getName()).log(Level.SEVERE, null, ex);
                }
                request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);

            } else if (request.getParameter("InstantConnection") != null) {
                String password = request.getParameter("password");
                String user = request.getParameter("user");
                if (services.getConnexion(user, password)) {
                    Tusers utilisateur = services.getUserConnect(user, password);
                    request.setAttribute("utilisateur", utilisateur);
                    if (utilisateur.getChgpwd() == 1) {
                        session = request.getSession();
                        session.setAttribute("isconnect", session.getId());
                        utilisateur.getGroupeid().getTgroupsAssocList().stream().forEach((p) -> {
                            session.setAttribute("lien" + p.getAction().getCodeAction(), p.getAction().getCodeAction());
                        });
                        session.setAttribute("utilisateur", utilisateur);
                        session.setAttribute("societe", utilisateur.getSociete());
                        request.getServletContext().getRequestDispatcher("/WEB-INF/main.jsp").forward(request, response);

                    } else {
                        request.setAttribute("message", new Message("vous devez modifier votre Username et Password Avant de continuer"));
                        request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
                    }
                }
            } else if (request.getParameter("genericSocity") != null) {
                getSocietemodel(request);
                request.getServletContext().getRequestDispatcher("/setProfil.jsp").forward(request, response);
            } else if (request.getParameter("UpdateUser") != null) {
                getUsermodel(request);
                request.setAttribute("message", new Message("Merci d'avoir choisir OCM Pour la vente et la gestion de vos produits \n"
                        + " connectez vous pour la configuration"));
                request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            }

        } else if (request.getParameter("verifiImmatriculation") != null) {
            VerifierImmatriculationsociete(request, response);
        }

    }

    void sendNotification(Message message, Societe s) {
        try {

            EnvoiEmail.sendMail(s.getEmail(), message.getSubject(), message.getMessage());
        } catch (Exception ex) {
            System.err.println("Echec Notification par Mail");
        }
    }

    void getSocietemodel(HttpServletRequest request) {
        Societe s = new Societe();
        s.setId(genererIDsociete());
        s.setAutoSaveClient(0);
        s.setImmatriculation(request.getParameter("imma"));
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

        }else{
              s.setMaintenance(0);
        }
        
        if (!request.getParameter("centreImpot").isEmpty()) {
            s.setCentreImpot(request.getParameter("centreImpot"));
        }
        s.setGestStock(0);
        s.setGestmarge(0);

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
            s = societeFacade.creer(s);
        } catch (RollbackFailureException ex) {
            System.out.println("Societe non enregistre erreur " + ex.getCause());
            Logger.getLogger(FreeAdmin.class.getName()).log(Level.SEVERE, null, ex.fillInStackTrace());
        } catch (Exception ex) {
            Logger.getLogger(FreeAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }

        Tregions r = null;
        Tsites si = null;
        Tservices ser = null;
        Tgroups gr = null;
        Tusers u = null;

        if (s != null) {
            r = getRegionmodel(s);
        }
        if (r != null) {
            si = getSitemodel(r);
        }
        if (si != null) {
           ser = getServicesmodel(si);
        }
        if (s != null) {
            gr = getTgroupsmodel(s);
        }
        if (gr != null && ser != null && s != null) {
            u = getUsermodel1(s, ser, gr);
        }
        if (u != null && gr != null) {
            getTgroupsAssocmodel(gr, u);
        }
        if (s != null) {
            getSouscriptionLicencemodel(s);
        }
        if (s != null && u != null) {
            Message message = new Message();
            message.setType("error");
            message.setTitle("Operation réussir");
            message.setSubject("Création nouvelle sociéte dans OCM");
            message.setMessage("Info Nouvelle Societe creer: \n Nom: '" + s.getNom() + "'\n Email: '" + s.getEmail() + "'\n Teléphone: '" + s.getTel() + "'"
                    + " \n Immatriculation: '" + s.getImmatriculation() + "' \n Adresse: '" + s.getAdresse() + "' \n numeros: '" + s.getId() + "' "
                    + "\n UserName: '" + u.getLogin() + "' \n Password: '" + u.getPsd() + "'");
            message.setNotification("Merci d'avoir choisir OCM Pour la vente et la gestion de vos produits ");
            try {
                EnvoiEmail.sendMail("eh2s.sarl@gmail.com", message.getSubject(), message.getMessage());
            } catch (MessagingException ex) {
                Logger.getLogger(FreeAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.setAttribute("societe", s);
            request.setAttribute("utilisateur", u);
            request.setAttribute("username", u.getLogin());
            request.setAttribute("password", u.getPassword());
            request.setAttribute("message", new Message(message.getSubject()
                    + " vous devez modifier votre UserName et password pour plus de sécurité"));

        } else {
            request.setAttribute("message", new Message("Operation à  échouer"));
        }

    }

    Tregions getRegionmodel(Societe s) {

        Tregions r = new Tregions();
        r.setSociete(s);

        r.setName("none_" + s.getId());
        r.setTel1(s.getTel());
        r.setMail(s.getEmail());
        try {
            r = tregionsFacade.creer(r);
        } catch (Exception ex) {
            r = null;
            System.out.println("Region non enregistre erreur " + ex.getCause());
            Logger.getLogger(FreeAdmin.class.getName()).log(Level.SEVERE, null, ex.fillInStackTrace());
        }
        return r;
    }

    Tsites getSitemodel(Tregions r) {
        Tsites s = new Tsites();
        s.setName("none_" + r.getId());
        s.setRegionid(r);
        try {
          s =  tsitesFacade.creer(s);
        } catch (Exception ex) {
            s = null;
            System.out.println("Site non enregistre erreur " + ex.getCause());
            Logger.getLogger(FreeAdmin.class.getName()).log(Level.SEVERE, null, ex.fillInStackTrace());
        }
        return s;
    }

    Tservices getServicesmodel(Tsites si) {
        Tservices s = new Tservices();
        s.setName("none_" + si.getId());
        s.setSite(si);
       
        try {
           s = tservicesFacade.creer(s);
        } catch (Exception ex) {
            s = null;
            System.out.println("service non enregistre erreur " + ex.getCause());
            Logger.getLogger(FreeAdmin.class.getName()).log(Level.SEVERE, null, ex.fillInStackTrace());
        }
        return s;
    }

    Tgroups getTgroupsmodel(Societe s) {
        Tgroups d = new Tgroups();
        d.setSociete(s);
        d.setType(0);
        d.setName("Groupe_generic_" + s.getId());
        try {
           d = tgroupsFacade.creer(d);
        } catch (Exception ex) {
            d = null;
            System.out.println("group non enregistre erreur " + ex.getCause());
            Logger.getLogger(FreeAdmin.class.getName()).log(Level.SEVERE, null, ex.fillInStackTrace());
        }
        return d;
    }

    boolean getTgroupsAssocmodel(Tgroups g, Tusers u) {
        boolean error = false;
        List<Tactions> al = tactionsFacade.findAll();
        for (Tactions ac : al) {
            TgroupsAssoc d = new TgroupsAssoc();
            d.setAction(ac);
            d.setGroup1(g);
            try {
                tgroupsAssocFacade.creer(d);
            } catch (Exception ex) {
                error = true;
                System.out.println("Sousgroup non enregistre erreur " + ex.getCause());
                Logger.getLogger(FreeAdmin.class.getName()).log(Level.SEVERE, null, ex.fillInStackTrace());
            }

        }
        if (!error) {

            g.setTgroupsAssocList(tgroupsAssocFacade.GroupAssocieteByGroup(g.getId()));
            try {
                tgroupsFacade.MisAJour(g);
            } catch (RollbackFailureException ex) {
                Logger.getLogger(FreeAdmin.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(FreeAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
            u.setGroupeid(g);
            try {
                tusersFacade.MisAJour(u);
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(FreeAdmin.class.getName()).log(Level.SEVERE, null, ex);
            } catch (RollbackFailureException ex) {
                Logger.getLogger(FreeAdmin.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(FreeAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return error;
    }

    Tusers getUsermodel1(Societe s, Tservices ser, Tgroups gr) {
        Tusers u = new Tusers();
        u.setSociete(s);
        u.setDisable(1);
        u.setChgpwd(0);
        u.setDatecreation(date_du_jour.dateJour());
        u.setFi(1);
        u.setFirstname("admin" + s.getId());
        u.setPhone(s.getEmail());
        u.setMail(s.getEmail());
        u.setAddress1(s.getAdresse());
        u.setFonction(s.getNom());
        u.setLogin("admin" + s.getId());
        encryptedHash hash = new encryptedHash();
        try {
            u.setPassword(hash.encode("password" + s.getId()));
        } catch (Exception ex) {
            Logger.getLogger(FreeAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
        u.setPsd("password" + s.getId());
        u.setFi(1);

        u.setServiceid(ser);
        u.setGroupeid(gr);
        try {
            u = tusersFacade.creer(u);
        } catch (Exception ex) {
            u = null;
            System.out.println("Sousgroup non enregistre erreur " + ex.getCause());
            Logger.getLogger(FreeAdmin.class.getName()).log(Level.SEVERE, null, ex.fillInStackTrace());
        }

        if (u != null) {
            Taffectzone aff = new Taffectzone();
            aff.setRegion(ser.getSite().getRegionid().getId());
            aff.setUsers(u);
            taffectzoneFacade.create(aff);
            u.setTaffectzoneList(taffectzoneFacade.findByUser(u.getId()));
            try {
                tusersFacade.MisAJour(u);
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(Administration.class.getName()).log(Level.SEVERE, null, ex);
            } catch (RollbackFailureException ex) {
                Logger.getLogger(Administration.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(Administration.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return u;
    }

    SouscriptionLicence getSouscriptionLicencemodel(Societe s) {
        SouscriptionLicence d = new SouscriptionLicence();
        TypeLicence type = typeLicenceFacade.findTypeLicence(4);
        d.setDateSous(date_du_jour.dateJourUniqueDate());
        d.setUpUser(type.getInitUser());
        d.setDateFinTest(date_du_jour.TicketDelaisResolution(30));
        d.setSociete(s);
        d.setType(type);
        try {
            souscriptionLicenceFacade.creer(d);
        } catch (Exception ex) {
            d = null;
            Logger.getLogger(FreeAdmin.class.getName()).log(Level.SEVERE, null, ex.fillInStackTrace());
        }
        return d;
    }

    void getUsermodel(HttpServletRequest request) {
        Tusers u = tusersFacade.findTusers(Integer.parseInt(request.getParameter("user")));
        u.setDatemodification(date_du_jour.dateJour());
        u.setChgpwd(1);
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
            try {
                u.setPassword(hash.encode(request.getParameter("password")));
            } catch (Exception ex) {
                Logger.getLogger(FreeAdmin.class.getName()).log(Level.SEVERE, null, ex);
            }
            u.setPsd(request.getParameter("password"));
        }
        try {
            tusersFacade.MisAJour(u);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(FreeAdmin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RollbackFailureException ex) {
            Logger.getLogger(FreeAdmin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(FreeAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    int genererIDsociete() {
        boolean repeat = true;
        int i = 0;
        while (repeat) {
            int id = (int) (Math.random() * 100000);
            List<Societe> so = societeFacade.findAllByForIdRepeat(id);
            if (so.isEmpty()) {
                repeat = false;
                i = id;
            }
        }
        return i;
    }

    void VerifierImmatriculationsociete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean repeat = false;
        while (repeat) {
            List<Societe> so = societeFacade.findAllByImma(Integer.parseInt(request.getParameter("imma")));
            if (!so.isEmpty()) {
                repeat = true;
            }
        }
        JSONArray er = new JSONArray();
        er.put(repeat);
        response.setContentType("application/json");
        response.getWriter().print(er);
    }
}
