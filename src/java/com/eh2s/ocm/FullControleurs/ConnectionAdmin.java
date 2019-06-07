/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.FullControleurs;

import com.eh2s.ocm.Entity.Services.Message;
import com.eh2s.ocm.Entity.Services.ServicesInitialize;
import com.eh2s.ocm.Entity.Sessions.SocieteFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TincidentsFacadeLocal;
import com.eh2s.ocm.Entity.Tusers;
import java.io.IOException;
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
@WebServlet(name = "ConnectionAdmin", urlPatterns = {"/admin"})
public class ConnectionAdmin extends HttpServlet {

  @EJB
    private ServicesInitialize servicesInitializeImple;

    @EJB
    private TincidentsFacadeLocal tincidentsFacade;

    @EJB
    private SocieteFacadeLocal societeFacade;

    private final String rootpasse = "P@ssw0rdEH2000";
    private final String rootID = "E1000H";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        request.getServletContext().getRequestDispatcher("/loginadmin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String password = request.getParameter("password");
        String user = request.getParameter("user");
        if (password.equals(rootpasse) && user.equals(rootID)) {
            Tusers utilisateur = new Tusers(-1);
            HttpSession session = request.getSession();
            session.setAttribute("isconnect", session.getId());
            session.setAttribute("utilisateur", utilisateur);
            session.setAttribute("root", "EH2S");
            try {
                request.setAttribute("incidents", tincidentsFacade.findTincidentsEntities());
                //request.setAttribute("commandes", getCommandeClients());
                request.setAttribute("societes", societeFacade.findAll());
                 servicesInitializeImple.initiationSuperAdmin(request);
            } catch (Exception e) {
            }
            request.setAttribute("q", request.getParameter("q"));
            request.getServletContext().getRequestDispatcher("/WEB-INF/main.jsp").forward(request, response);
        } else {
            request.setAttribute("message", new Message("Nom utilisateur où mot de passe incorrect"));
            request.getServletContext().getRequestDispatcher("/loginadmin.jsp").forward(request, response);
        }
    }

    
}
