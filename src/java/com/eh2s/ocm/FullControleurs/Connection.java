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
import com.eh2s.ocm.Entity.Sessions.TusersFacadeLocal;
import com.eh2s.ocm.Entity.Tusers;
import com.eh2s.ocm.UtilityFonctions.EnvoiEmail;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author messi
 */
@WebServlet(name = "Connection", urlPatterns = {"/getconnexion"})
public class Connection extends HttpServlet {

    @EJB
    private ServicesInitialize servicesInitializeImple;

    @EJB
    private TusersFacadeLocal tusersFacade;

    @EJB
    private Services services;

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

        if (request.getParameter("recovery") != null) {
            String mail = request.getParameter("email");
            String tel = request.getParameter("tel");
            boolean error = false;

            Tusers utilisateur = services.getpasswordrecovery(mail, tel);
            if (utilisateur != null) {
                String pass = "password" + (int) (Math.random() * 10000);

                try {
                    EnvoiEmail.sendMail(mail, "Réinitiatilisation Mot passe CCMANAGER",
                            "Votre mot de passe a été réinitialiser le nouveau mot de passe est:\n password = '" + pass + "'\n userName = " + utilisateur.getLogin());
                } catch (Exception ex) {
                    error = true;
                    request.setAttribute("message", new Message("Echec Notification par Mail Adresse email invalide où connection internet indisponible"));
                }
                if (!error) {
                    encryptedHash hash = new encryptedHash();
                    try {
                        utilisateur.setPassword(hash.encode(pass));
                        tusersFacade.MisAJour(utilisateur);
                    } catch (Exception ex) {
                        Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    request.setAttribute("message", new Message("un email contenant vos identifiant a été envoyer a votre adresse email"));
                }

            } else {
                request.setAttribute("message", new Message("Adresse email où Numéros de téléphone incorrect"));
            }
            request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        } else {
            HttpSession session;
            String q = request.getParameter("q");
            String password = request.getParameter("password");
            String user = request.getParameter("user");
            if (services.getConnexion(user, password)) {
                Tusers utilisateur = services.getUserConnect(user, password);
                if (utilisateur.getChgpwd() == 1) {
                    if (!services.verifiLicenceTest(utilisateur.getSociete())) {
                        if (utilisateur.getSociete().getMaintenance() == 0) {
                            if (utilisateur.getDisable() == 1) {
                                session = request.getSession();
                                session.setAttribute("isconnect", session.getId());
                                utilisateur.getGroupeid().getTgroupsAssocList().stream().forEach((p) -> {
                                    session.setAttribute("lien" + p.getAction().getCodeAction(), p.getAction().getCodeAction());
                                });
                                session.setAttribute("utilisateur", utilisateur);
                                session.setAttribute("societe", utilisateur.getSociete());
                                request.setAttribute("q", q);

                                servicesInitializeImple.initialize(request, utilisateur);

                                // services.ChangerStatutTicketHorsDelais(utilisateur.getSociete().getImmatriculation());
                                // services.DeleteAllNotificationByLut();
                                request.getServletContext().getRequestDispatcher("/WEB-INF/main.jsp").forward(request, response);
                            } else {
                                request.setAttribute("message", new Message("votre profile a été desactiver veillez contacter votre administrateur"));
                                request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
                            }
                        } else {
                            request.getServletContext().getRequestDispatcher("/under-maintenance.jsp").forward(request, response);
                            request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
                        }
                    } else {
                        request.setAttribute("message", new Message("Votre période d'essai est terminer\n"
                                + " veillez souscrire a un abonnement auprès de l'éditeur prestataire EH2S SARL"));
                        request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
                    }
                } else {
                    request.setAttribute("message", new Message("vous devez modifier votre Usernam et Password Avant de continuer"));
                    request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("message", new Message("Nom utilisateur où mot de passe incorrect"));
                request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            }
        }
    }

}
