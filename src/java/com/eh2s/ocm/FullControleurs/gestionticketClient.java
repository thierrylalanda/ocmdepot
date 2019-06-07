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
import com.eh2s.ocm.Entity.Sessions.StatutIncidentFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TclientsFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TincidentsFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TsourcesFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TsrubriquesFacadeLocal;
import com.eh2s.ocm.Entity.Tclients;
import com.eh2s.ocm.Entity.Tincidents;
import com.eh2s.ocm.Entity.Tsrubriques;
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
@WebServlet(name = "gestionticketClient", urlPatterns = {"/ticketClient"})
@MultipartConfig(fileSizeThreshold = 6291456, // 6 MB
        maxFileSize = 10485760L, // 10 MB
        maxRequestSize = 20971520L, // 20 MB
        location = "/"
)
public class gestionticketClient extends HttpServlet {

    @EJB
    private TclientsFacadeLocal tclientsFacade;

    @EJB
    private NotificationFacadeLocal notificationFacade;

    @EJB
    private PieceJointeFacadeLocal pieceJointeFacade;

    @EJB
    private TincidentsFacadeLocal tincidentsFacade;

    @EJB
    private StatutIncidentFacadeLocal statutIncidentFacade;

    @EJB
    private TsrubriquesFacadeLocal tsrubriquesFacade;

    @EJB
    private ServicesInitialize servicesInitializeImple;

    @EJB
    private TsourcesFacadeLocal tsourcesFacade;

    @EJB
    private Services services;

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
            request.getServletContext().getRequestDispatcher("/indexclient.jsp").forward(request, response);
        }

    }

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        if (session.getAttribute("isconnect") != null) {
            Tclients utilisateur = (Tclients) session.getAttribute("client");
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
                                } catch (Exception ex) {
                                    request.setAttribute("erreur", ex.getMessage());
                                }
                                break;
                            default:
                                try {
                                    String[] id = request.getParameterValues("id");
                                    for (String str : id) {
                                        // tincidentsFacade.remove(tincidentsFacade.findTincidents(Integer.parseInt(str)));
                                        tincidentsFacade.Supprimer(Integer.parseInt(str));
                                    }
                                } catch (Exception ex) {
                                    request.setAttribute("erreur", ex.getMessage());
                                }
                                break;
                        }
                        servicesInitializeImple.initiationClients(request, utilisateur);
                        request.setAttribute("q", request.getParameter("q"));
                        request.getServletContext().getRequestDispatcher("/WEB-INF/main.jsp").forward(request, response);
                        break;
                    case "getClientsNotification":
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
                            dat.put("dateNotif", df.format(st.getDateNotif()));
                            return st;
                        }).forEach((_item) -> {
                            object.put(dat);
                        });
                        response.setContentType("application/json");
                        response.getWriter().print(object);
                        break;
                    case "getClientsHistorique":
                        try {
                            FindByHistoriqueClient(request, utilisateur);
                        } catch (Exception ex) {
                            request.setAttribute("erreur", ex.getMessage());
                        }
                        request.setAttribute("statuts", statutIncidentFacade.findAll());
                        request.setAttribute("q", request.getParameter("q"));
                        request.getServletContext().getRequestDispatcher("/WEB-INF/main.jsp").forward(request, response);
                        break;
                    case "changeProfil": {
                        try {
                            Tclients cli = changeProfil(request);
                            session.setAttribute("client", cli);
                        } catch (Exception ex) {
                            Logger.getLogger(gestionticketClient.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    request.setAttribute("q", request.getParameter("q"));
                    request.getServletContext().getRequestDispatcher("/WEB-INF/main.jsp").forward(request, response);
                    break;
                    default:
                        break;
                }
            } else {
                servicesInitializeImple.initiationClients(request, utilisateur);
                request.getServletContext().getRequestDispatcher("/WEB-INF/main.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("message", new Message("votre session a expirer veuillez vous reconnecter"));
            request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        }

    }

    Tclients changeProfil(HttpServletRequest request) throws Exception {
        Tclients cli = tclientsFacade.find(Integer.parseInt(request.getParameter("client")));

        if (!request.getParameter("adresse").isEmpty()) {
            cli.setAdresse(request.getParameter("adresse"));
        }
        if (!request.getParameter("tel1").isEmpty()) {
            cli.setTelephone(request.getParameter("tel1"));
        }
        if (!request.getParameter("tel2").isEmpty()) {
            cli.setTelephone2(request.getParameter("tel2"));
        }
        if (!request.getParameter("email").isEmpty()) {
            cli.setMail(request.getParameter("email"));
        }

        if (!request.getParameter("password").isEmpty()) {
            encryptedHash hash = new encryptedHash();
            cli.setPassword(hash.encode(request.getParameter("password")));
        }
        tclientsFacade.MisAJour(cli);
        return tclientsFacade.find(Integer.parseInt(request.getParameter("client")));

    }
// fonctions en charge de remplire les modeles

    void getTicketmodel(HttpServletRequest request, Tclients utilisateur) throws Exception {
        Tincidents r;
        Tincidents ic = null;
        String UPLOAD_DIRECTORY = "PJ";
        boolean error = false;
        int is = Integer.parseInt(request.getParameter("isNew"));
        if (is == 0) {
            r = new Tincidents();
            r.setDateCreate(date_du_jour.dateJour());
            Tsrubriques s = tsrubriquesFacade.findTsrubriques(Integer.parseInt(request.getParameter("srubrique")));
            r.setSrubriqueid(s);
            r.setDateHope(date_du_jour.TicketDelaisResolution(s.getDelais()));
        } else {
            r = tincidentsFacade.findTincidents(Integer.parseInt(request.getParameter("ticket")));
            r.setDateModif(date_du_jour.dateJour());
            r.setSrubriqueid(tsrubriquesFacade.findTsrubriques(Integer.parseInt(request.getParameter("srubrique"))));
        }

        r.setDescription(request.getParameter("description"));
        r.setTitle(request.getParameter("object"));
        r.setSourceid(tsourcesFacade.findTsources(4));
        r.setClientid(utilisateur);
        r.setState(statutIncidentFacade.findStatutIncident(501));
        r.setIsInDelais(-1);
        r.setDiffday(-1);
        r.setIsaffect(0);

        try {
            if (is == 0) {
                ic = tincidentsFacade.creer(r);
                Message message = new Message();
                message.setNotification("Un Ticket a été Ouvert à intention de: " + ic.getClientid().getSociete().getNom() + "\n Veuillez consulter le ticket N°= '" + ic.getId() + "' pour plus de details");
                message.setTitle("CCMANAGER Alerte: Ouverture d'un Ticket de plainte");
                //message.setEmail("Un Ticket a été Ouvert à votre intention par: " + utilisateur.getFirstname() + "\n Veuillez consulter le ticket N°= '" + ic.getId() + "' dans la plate forme CCMANAGER pour plus de details");
                //message.setSubject("CCMANAGER Alerte: Ouverture d'un Ticket de plainte");
                sendNotification(message, utilisateur);
                request.setAttribute("message", message);
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
                Logger.getLogger(gestionticket.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    List<Tincidents> TicketmodelByPeriode(HttpServletRequest request, int reg, String societe) throws Exception {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String[] date1 = request.getParameter("interval").split("-");
        Date d1 = date_du_jour.dateConvert(date1[0]);
        Date d2 = date_du_jour.dateConvert(date1[1]);
        List<Tincidents> listIncidents = null;
        if (reg == -1) {
            listIncidents = tincidentsFacade.findTincidentsEntitiesByPeriode(d1, d2, societe);
        }
        request.setAttribute("SMS", df.format(d1) + " au " + df.format(d2));
        return listIncidents;
    }

    void FindByHistoriqueClient(HttpServletRequest request, Tclients client) throws Exception {
        List<Tincidents> listIncidents;
        if (request.getParameter("statut") != null && !request.getParameter("statut").isEmpty()) {
            listIncidents = TicketmodelByPeriode(request, -1, client.getSociete().getImmatriculation()).stream()
                    .filter(in -> in.getState().getCode() == Integer.parseInt(request.getParameter("statut"))
                    && in.getClientid().equals(client))
                    .collect(Collectors.toList());
        } else if (request.getParameter("statut").isEmpty()) {
            listIncidents = TicketmodelByPeriode(request, -1, client.getSociete().getImmatriculation()).stream()
                    .filter(in -> in.getClientid().equals(client)).collect(Collectors.toList());
        } else {
            listIncidents = tincidentsFacade.findTincidentsEntities();
        }
        request.setAttribute("incidents", listIncidents);
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

}
