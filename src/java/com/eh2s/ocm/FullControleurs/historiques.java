/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.FullControleurs;

import com.eh2s.ocm.Entity.Account;
import com.eh2s.ocm.Entity.Caisse;
import com.eh2s.ocm.Entity.CommandeFournisseur;
import com.eh2s.ocm.Entity.Fournisseur;
import com.eh2s.ocm.Entity.LigneAccount;
import com.eh2s.ocm.Entity.LigneCommandeFournisseur;
import com.eh2s.ocm.Entity.LigneSortie;
import com.eh2s.ocm.Entity.Services.Message;
import com.eh2s.ocm.Entity.Services.ServicesInitialize;
import com.eh2s.ocm.Entity.Sessions.AccountFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.CaisseFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.CommandeFournisseurFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.FournisseurFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.LigneAccountFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.LigneCommandeFournisseurFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.LigneSortieFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.SortieCaisseFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TarticlesFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TclientsFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TcommandesFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TlignecommandeFacadeLocal;
import com.eh2s.ocm.Entity.Societe;
import com.eh2s.ocm.Entity.SortieCaisse;
import com.eh2s.ocm.Entity.Tarticles;
import com.eh2s.ocm.Entity.Tclients;
import com.eh2s.ocm.Entity.Tcommandes;
import com.eh2s.ocm.Entity.Tlignecommande;
import com.eh2s.ocm.Entity.Tusers;
import com.eh2s.ocm.UtilityFonctions.AutresDepense;
import com.eh2s.ocm.UtilityFonctions.AutresEntree;
import com.eh2s.ocm.UtilityFonctions.FactureBonCommande;
import com.eh2s.ocm.UtilityFonctions.FactureClient;
import com.eh2s.ocm.UtilityFonctions.Recouvrement;
import com.eh2s.ocm.UtilityFonctions.RecouvrementBon;
import com.eh2s.ocm.UtilityFonctions.date_du_jour;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrateur
 */
@WebServlet(name = "historiques", urlPatterns = {"/historiques"})
public class historiques extends HttpServlet {

    @EJB
    private CommandeFournisseurFacadeLocal commandeFournisseurFacade;

    @EJB
    private TcommandesFacadeLocal tcommandesFacade;

    @EJB
    private LigneSortieFacadeLocal ligneSortieFacade;

    @EJB
    private LigneAccountFacadeLocal ligneAccountFacade;

    @EJB
    private CaisseFacadeLocal caisseFacade;

    @EJB
    private SortieCaisseFacadeLocal sortieCaisseFacade;

    @EJB
    private AccountFacadeLocal accountFacade;

    @EJB
    private LigneCommandeFournisseurFacadeLocal ligneCommandeFournisseurFacade;

    @EJB
    private FournisseurFacadeLocal fournisseurFacade;

    @EJB
    private TarticlesFacadeLocal tarticlesFacade;

    @EJB
    private TlignecommandeFacadeLocal tlignecommandeFacade;

    @EJB
    private TclientsFacadeLocal tclientsFacade;

    @EJB
    private ServicesInitialize servicesInitializeImple;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        if (session.getAttribute("isconnect") != null) {
            Societe societe = (Societe) session.getAttribute("societe");
            Tusers t = (Tusers) session.getAttribute("utilisateur");
            String actions = request.getParameter("action");
            request.setAttribute("q", request.getParameter("q"));
            if (actions.equals("model")) {
                String model = request.getParameter("model");
                switch (model) {
                    case "historiqueVente":
                        switch (Integer.parseInt(request.getParameter("isNew"))) {
                            case 0:
                                request.setAttribute("lignesAccount", ligneAccountFacade.findBySociete(societe.getId()));
                                break;
                            case 1:
                                List<Tlignecommande> listCommandesH = historiqueVentes(request, societe);
                                request.setAttribute("commandes", listCommandesH);

                                break;
                            case 2:
                                //ventes par produits
                                List<Tcommandes> listCommandesHs = historiqueVentesArticle(request, societe);
                                request.setAttribute("commandes", listCommandesHs);

                                break;
                            default:
                                break;
                        }
                        if (session.getAttribute("utilisateur") != null) {
                            servicesInitializeImple.initialize(request, (Tusers) session.getAttribute("utilisateur"));

                        } else if (session.getAttribute("client") != null) {
                            servicesInitializeImple.initiationClients(request, (Tclients) session.getAttribute("client"));
                        }
                        request.setAttribute("q", request.getParameter("q"));
                        request.getServletContext().getRequestDispatcher("/WEB-INF/main.jsp").forward(request, response);
                        break;
                    case "historiqueAchat":
                        switch (Integer.parseInt(request.getParameter("isNew"))) {
                            case 0:
                                request.setAttribute("lignesSortie", ligneSortieFacade.findAll(societe.getId()));
                                break;
                            case 1:
                                historiqueAchats(request, societe);
                                break;
                            case 2:
                                //achats par produits
                                List<CommandeFournisseur> listCommandesHs = historiqueAchatsProduits(request, societe);
                                request.setAttribute("achats", listCommandesHs);
                                break;
                            default:
                                break;
                        }
                        if (session.getAttribute("utilisateur") != null) {
                            servicesInitializeImple.initialize(request, (Tusers) session.getAttribute("utilisateur"));

                        } else if (session.getAttribute("client") != null) {
                            servicesInitializeImple.initiationClients(request, (Tclients) session.getAttribute("client"));
                        }
                        request.setAttribute("q", request.getParameter("q"));
                        request.getServletContext().getRequestDispatcher("/WEB-INF/main.jsp").forward(request, response);
                        break;
                    case "historiqueCaisse":
                        switch (Integer.parseInt(request.getParameter("isNew"))) {
                            case 0:
                                break;
                            case 1:
                                if (caisseIsOpen(request, t)) {
                                    historiqueCaisseToday(request, societe);
                                }

                                break;
                            case 2:
                                historiqueCaisse(request, societe);
                                break;
                            case 3:
                                historiqueCaisseByDate(request, societe);
                                break;
                            case 4:
                                historiqueVenteCaisse(request, t);
                                break;
                            case 5:
                                historiqueAchatCaisse(request, t);
                                break;
                            case 6:
                                //historique des entrees
                                historiqueEntreesCaisse(request, t);
                                break;
                            case 7:
                                //historique des sorties
                                historiqueSortiesCaisse(request, t);
                                break;
                            default:
                                break;
                        }
                        if (session.getAttribute("utilisateur") != null) {
                            servicesInitializeImple.initialize(request, (Tusers) session.getAttribute("utilisateur"));

                        } else if (session.getAttribute("client") != null) {
                            servicesInitializeImple.initiationClients(request, (Tclients) session.getAttribute("client"));
                        }
                        request.setAttribute("q", request.getParameter("q"));
                        request.getServletContext().getRequestDispatcher("/WEB-INF/main.jsp").forward(request, response);
                        break;
                    case "recouvrement":
                        switch (Integer.parseInt(request.getParameter("isNew"))) {
                            case 0:
                                Date date;
                                if (request.getParameter("date") != null) {
                                    String date1 = request.getParameter("date");
                                    Date d1 = date_du_jour.dateConvert(date1);
                                    date = d1;
                                } else {
                                    date = date_du_jour.dateJourUniqueDate();
                                }

                                List<Account> recouvrements = accountFacade.findAllByPeriode(date, date, societe.getId())
                                        .stream().filter(sort -> sort.getLigneAccount() != null && sort.getLigneAccount().getDateCreate().compareTo(date) != 0).collect(Collectors.toList());

                                request.setAttribute("recouvrements", recouvrements);
                                request.setAttribute("messageTable", "Recouvrement Clients  du : "+date);
                                break;
                            case 1:
                                Date dateF;
                                if (request.getParameter("date") != null) {
                                    String date1 = request.getParameter("date");
                                    Date d1 = date_du_jour.dateConvert(date1);
                                    dateF = d1;
                                } else {
                                    dateF = date_du_jour.dateJourUniqueDate();
                                }

                                List<SortieCaisse> recouvrementsF = sortieCaisseFacade.findAllByPeriode(dateF, dateF, societe.getId())
                                        .stream().filter(sort -> sort.getLigneSortie() != null && sort.getLigneSortie().getDateCreate().compareTo(dateF) != 0).collect(Collectors.toList());
                                request.setAttribute("recouvrements", recouvrementsF);
                                 request.setAttribute("messageTable", "Recouvrement chez les Fournisseurs à la date du : "+dateF);
                                break;

                            default:
                                break;
                        }
                        if (session.getAttribute("utilisateur") != null) {
                            servicesInitializeImple.initialize(request, (Tusers) session.getAttribute("utilisateur"));

                        } else if (session.getAttribute("client") != null) {
                            servicesInitializeImple.initiationClients(request, (Tclients) session.getAttribute("client"));
                        }
                        request.setAttribute("q", request.getParameter("q"));
                        request.getServletContext().getRequestDispatcher("/WEB-INF/main.jsp").forward(request, response);
                        break;

                }
            } else {
                if (session.getAttribute("utilisateur") != null) {
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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private boolean caisseIsOpen(HttpServletRequest request, Tusers utilisateur) {
        boolean open = false;
        Caisse caisse = caisseFacade.findCaisseDuJour(utilisateur.getSociete().getId());
        if (caisse.getId() != 0) {
            open = true;
        } else {
            Message message = new Message();
            message.setType("warning");
            message.setTitle("Attention !!!");
            message.setNotification("La caisse n'a pas encore été ouverte");
            request.setAttribute("message", message);
        }

        return open;
    }

    private List<Tlignecommande> historiqueVentes(HttpServletRequest request, Societe societe) {
        int etat = Integer.parseInt(request.getParameter("statut"));
        String[] date1 = request.getParameter("interval").split("-");
        System.out.println("interval " + date1);
        Date d1 = date_du_jour.dateConvert(date1[0]);
        Date d2 = date_du_jour.dateConvert(date1[1]);
        int idcl = Integer.parseInt(request.getParameter("client"));

        List<Tlignecommande> listCommandesH;
        if (idcl != 0) {
            Tclients cl = tclientsFacade.findTclients(idcl);
            if (etat == 0) {
                listCommandesH = tlignecommandeFacade.findTcommandesByPeriode(d1, d2, societe.getImmatriculation()).stream()
                        .filter(in -> in.getClient().getId().equals(cl.getId()))
                        .collect(Collectors.toList());
            } else {
                listCommandesH = tlignecommandeFacade.findTcommandesByPeriode(d1, d2, societe.getImmatriculation()).stream()
                        .filter(in -> in.getEtatc().getCode() == Integer.parseInt(request.getParameter("statut"))
                        && in.getClient().getId().equals(cl.getId()))
                        .collect(Collectors.toList());
            }
            request.setAttribute("messageTable", "Vente consernant le client : " + cl.getNom() + " du " + date1[0] + " au " + date1[1]);
        } else {
            if (etat == 0) {
                listCommandesH = tlignecommandeFacade.findTcommandesByPeriode(d1, d2, societe.getImmatriculation()).stream()
                        .collect(Collectors.toList());
            } else {
                listCommandesH = tlignecommandeFacade.findTcommandesByPeriode(d1, d2, societe.getImmatriculation()).stream()
                        .filter(in -> in.getEtatc().getCode() == Integer.parseInt(request.getParameter("statut"))
                        )
                        .collect(Collectors.toList());
            }
            request.setAttribute("messageTable", "Vente du " + date1[0] + " au " + date1[1]);
        }
        return listCommandesH;
    }

    private void historiqueAchats(HttpServletRequest request, Societe societe) {
        int etat = Integer.parseInt(request.getParameter("statut"));
        String[] date1 = request.getParameter("interval").split("-");
        Date d1 = date_du_jour.dateConvert(date1[0]);
        Date d2 = date_du_jour.dateConvert(date1[1]);
        int idcl = Integer.parseInt(request.getParameter("fournisseur"));

        List<LigneCommandeFournisseur> listCommandesH;
        if (idcl != 0) {
            Fournisseur fr = fournisseurFacade.find(idcl);
            if (etat == 0) {
                listCommandesH = ligneCommandeFournisseurFacade.findBonCommandeByFournisseurByPeriode(d1, d2, fr.getId());
            } else {
                listCommandesH = ligneCommandeFournisseurFacade.findBonCommandeByFournisseurByPeriode(d1, d2, fr.getId()).stream()
                        .filter(in -> in.getEtat().getCode() == Integer.parseInt(request.getParameter("statut"))
                        )
                        .collect(Collectors.toList());
            }
            request.setAttribute("messageTable", "Achat consernant le fournisseur : " + fr.getNom() + " du " + date1[0] + " au " + date1[1]);
        } else {
            if (etat == 0) {
                listCommandesH = ligneCommandeFournisseurFacade.findBonCommandeByPeriode(d1, d2, societe.getId()).stream()
                        .collect(Collectors.toList());
            } else {
                listCommandesH = ligneCommandeFournisseurFacade.findBonCommandeByPeriode(d1, d2, societe.getId()).stream()
                        .filter(in -> in.getEtat().getCode() == Integer.parseInt(request.getParameter("statut"))
                        )
                        .collect(Collectors.toList());
            }
            request.setAttribute("messageTable", "ACHATS du " + date1[0] + " au " + date1[1]);
        }
        request.setAttribute("achats", listCommandesH);
    }

    private void historiqueCaisse(HttpServletRequest request, Societe societe) {
        String[] date1 = request.getParameter("interval").split("-");
        Date d1 = date_du_jour.dateConvert(date1[0]);
        Date d2 = date_du_jour.dateConvert(date1[1]);
        List<Account> allAccount = accountFacade.findAllByPeriode(d1, d2, societe.getId());
        List<SortieCaisse> allSortie = sortieCaisseFacade.findAllByPeriode(d1, d2, societe.getId());
        request.setAttribute("sorties", allSortie);
        request.setAttribute("entrees", allAccount);
        request.setAttribute("messageTable", "Etat de la caisse du " + date1[0] + " au " + date1[1]);
    }

    private void historiqueCaisseToday(HttpServletRequest request, Societe societe) {
        List<Account> allAccount = accountFacade.findAllToday(societe.getId())
                .stream().filter(sort -> sort.getLigneAccount() == null).collect(Collectors.toList());
        List<SortieCaisse> allSortie;
        allSortie = sortieCaisseFacade.findAllToDay(societe.getId())
                .stream().filter(sort -> sort.getLigneSortie() == null).collect(Collectors.toList());
        Caisse caisse = caisseFacade.findCaisseDuJour(societe.getId());
        List<LigneAccount> ligneAccount = ligneAccountFacade.findByPeriodeSociete(date_du_jour.dateJourUniqueDate(), date_du_jour.dateJourUniqueDate(), societe.getId());
        List<LigneSortie> ligneSorties = ligneSortieFacade.findByPeriodeSociete(date_du_jour.dateJourUniqueDate(), date_du_jour.dateJourUniqueDate(), societe.getId());
        Date date = date_du_jour.dateJourUniqueDate();
        List<Account> recouvrements = accountFacade.findAllToday(societe.getId())
                .stream().filter(sort -> sort.getLigneAccount() != null && sort.getLigneAccount().getDateCreate().compareTo(date) != 0).collect(Collectors.toList());
        List<SortieCaisse> Decaissements;
        Decaissements = sortieCaisseFacade.findAllToDay(societe.getId())
                .stream().filter(sort -> sort.getLigneSortie() != null && sort.getLigneSortie().getDateCreate().compareTo(date) != 0).collect(Collectors.toList());
        Recouvrement recouvrement = new Recouvrement();
        for (Account la : recouvrements) {
            recouvrement.setQuantite(recouvrement.getQuantite() + 1);
            recouvrement.setMontantencaisse(recouvrement.getMontantencaisse() + la.getAvance());
        }
        RecouvrementBon recouvrementBon = new RecouvrementBon();
        for (SortieCaisse la : Decaissements) {
            recouvrementBon.setQuantite(recouvrementBon.getQuantite() + 1);
            recouvrementBon.setMontantdecaisse(recouvrementBon.getMontantdecaisse() + la.getAvance());
        }
        FactureClient factureclient = new FactureClient();
        for (LigneAccount la : ligneAccount) {

            factureclient.setNetfacture(factureclient.getNetfacture() + la.getMontantNet());
            factureclient.setQuantite(factureclient.getQuantite() + 1);
            Double allavance = 0.0;
            for (Account account : la.getAccountList()) {
                if (account.getDate().compareTo(date) == 0) {
                    allavance += account.getAvance();
                }
            }
            factureclient.setDettclient(factureclient.getDettclient() + (la.getMontantNet() - allavance));
            factureclient.setMontantencaisse(factureclient.getMontantencaisse() + allavance);
            if (la.getLigneCommande() != null) {
                factureclient.setRemise(factureclient.getRemise() + la.getLigneCommande().getRemise());
                factureclient.setMargeFournisseur(factureclient.getMargeFournisseur() + la.getLigneCommande().getMargeFournisseur());
            }

        }
        FactureBonCommande factureboncommande = new FactureBonCommande();
        for (LigneSortie la : ligneSorties) {
            Double allavance = 0.0;
            for (SortieCaisse account : la.getSortietList()) {
                if (account.getDate().compareTo(date) == 0) {
                    allavance += account.getAvance();
                }
            }
            factureboncommande.setDettFournisseur(factureboncommande.getDettFournisseur() + (la.getMontantNet() - allavance));
            factureboncommande.setMontantdecaisse(factureboncommande.getMontantdecaisse() + allavance);
            factureboncommande.setNetfacture(factureboncommande.getNetfacture() + la.getMontantNet());
            factureboncommande.setQuantite(factureboncommande.getQuantite() + 1);
        }

        AutresDepense autredepense = new AutresDepense();
        for (SortieCaisse la : allSortie) {
            autredepense.setMontantdecaisse(autredepense.getMontantdecaisse() + la.getMontant());
            autredepense.setQuantite(autredepense.getQuantite() + 1);
        }

        AutresEntree autreentree = new AutresEntree();
        for (Account la : allAccount) {
            autreentree.setMontantencaisse(autreentree.getMontantencaisse() + la.getAvance());
            autreentree.setQuantite(autreentree.getQuantite() + 1);
        }
        request.setAttribute("recouvrement", recouvrement);
        request.setAttribute("recouvrementBon", recouvrementBon);
        request.setAttribute("factureclient", factureclient);
        request.setAttribute("factureboncommande", factureboncommande);
        request.setAttribute("autredepense", autredepense);
        request.setAttribute("autreentree", autreentree);
        request.setAttribute("sorties", allSortie);
        request.setAttribute("caisse", caisse);
        request.setAttribute("entrees", allAccount);
        request.setAttribute("messageTable", "Etat de la caisse du " + date_du_jour.dateJourUniqueDate());
    }

    private void historiqueCaisseByDate(HttpServletRequest request, Societe societe) {
        String date1 = request.getParameter("date");
        Date d1 = date_du_jour.dateConvert(date1);
        Date date = d1;

        Caisse caisse = caisseFacade.findCaisseByDate(d1, societe.getId());
        if (caisse.getId() != 0) {
            List<Account> allAccount = accountFacade.findAllByPeriode(d1, d1, societe.getId())
                    .stream().filter(sort -> sort.getLigneAccount() == null).collect(Collectors.toList());
            List<SortieCaisse> allSortie;
            allSortie = sortieCaisseFacade.findAllByPeriode(d1, d1, societe.getId())
                    .stream().filter(sort -> sort.getLigneSortie() == null).collect(Collectors.toList());
            List<LigneAccount> ligneAccount = ligneAccountFacade.findByPeriodeSociete(date, date, societe.getId());
            List<LigneSortie> ligneSorties = ligneSortieFacade.findByPeriodeSociete(date, date, societe.getId());

            List<Account> recouvrements = accountFacade.findAllByPeriode(d1, d1, societe.getId())
                    .stream().filter(sort -> sort.getLigneAccount() != null && sort.getLigneAccount().getDateCreate().compareTo(date) != 0).collect(Collectors.toList());
            List<SortieCaisse> Decaissements;
            Decaissements = sortieCaisseFacade.findAllByPeriode(d1, d1, societe.getId())
                    .stream().filter(sort -> sort.getLigneSortie() != null && sort.getLigneSortie().getDateCreate().compareTo(date) != 0).collect(Collectors.toList());
            Recouvrement recouvrement = new Recouvrement();
            for (Account la : recouvrements) {
                recouvrement.setQuantite(recouvrement.getQuantite() + 1);
                recouvrement.setMontantencaisse(recouvrement.getMontantencaisse() + la.getAvance());
            }
            RecouvrementBon recouvrementBon = new RecouvrementBon();
            for (SortieCaisse la : Decaissements) {
                recouvrementBon.setQuantite(recouvrementBon.getQuantite() + 1);
                recouvrementBon.setMontantdecaisse(recouvrementBon.getMontantdecaisse() + la.getAvance());
            }
            FactureClient factureclient = new FactureClient();
            for (LigneAccount la : ligneAccount) {
                factureclient.setNetfacture(factureclient.getNetfacture() + la.getMontantNet());
                Double allavance = 0.0;
                for (Account account : la.getAccountList()) {
                    if (account.getDate().compareTo(date) == 0) {
                        allavance += account.getAvance();
                    }
                }
                factureclient.setDettclient(factureclient.getDettclient() + (la.getMontantNet() - allavance));
                factureclient.setMontantencaisse(factureclient.getMontantencaisse() + allavance);
                factureclient.setQuantite(factureclient.getQuantite() + 1);
                if (la.getLigneCommande() != null) {
                    factureclient.setRemise(factureclient.getRemise() + la.getLigneCommande().getRemise());
                    factureclient.setMargeFournisseur(factureclient.getMargeFournisseur() + la.getLigneCommande().getMargeFournisseur());
                }

            }
            FactureBonCommande factureboncommande = new FactureBonCommande();
            for (LigneSortie la : ligneSorties) {

                Double allavance = 0.0;
                for (SortieCaisse account : la.getSortietList()) {
                    if (account.getDate().compareTo(date) == 0) {
                        allavance += account.getAvance();
                    }
                }
                factureboncommande.setDettFournisseur(factureboncommande.getDettFournisseur() + (la.getMontantNet() - allavance));
                factureboncommande.setMontantdecaisse(factureboncommande.getMontantdecaisse() + allavance);
                factureboncommande.setNetfacture(factureboncommande.getNetfacture() + la.getMontantNet());
                factureboncommande.setQuantite(factureboncommande.getQuantite() + 1);
            }

            AutresDepense autredepense = new AutresDepense();
            for (SortieCaisse la : allSortie) {
                autredepense.setMontantdecaisse(autredepense.getMontantdecaisse() + la.getMontant());
                autredepense.setQuantite(autredepense.getQuantite() + 1);
            }

            AutresEntree autreentree = new AutresEntree();
            for (Account la : allAccount) {
                autreentree.setMontantencaisse(autreentree.getMontantencaisse() + la.getAvance());
                autreentree.setQuantite(autreentree.getQuantite() + 1);
            }
            request.setAttribute("recouvrement", recouvrement);
            request.setAttribute("recouvrementBon", recouvrementBon);
            request.setAttribute("factureclient", factureclient);
            request.setAttribute("factureboncommande", factureboncommande);
            request.setAttribute("autredepense", autredepense);
            request.setAttribute("autreentree", autreentree);
            request.setAttribute("sorties", allSortie);
            request.setAttribute("caisse", caisse);
            request.setAttribute("entrees", allAccount);
            request.setAttribute("messageTable", "Etat de la caisse du " + date);
        } else {
            Message message = new Message();
            message.setType("info");
            message.setTitle("INFORMATION !!!");
            message.setNotification("La caisse de ce jour n'existe pas");
            request.setAttribute("message", message);
        }

    }

    private void historiqueVenteCaisse(HttpServletRequest request, Tusers utilisateur) {
        List<LigneAccount> ligneAccount = null;
        String[] date1 = request.getParameter("interval").split("-");
        Date d1 = date_du_jour.dateConvert(date1[0]);
        Date d2 = date_du_jour.dateConvert(date1[1]);
        int idCl = Integer.parseInt(request.getParameter("client"));
        int etat = Integer.parseInt(request.getParameter("etat"));
        String message = "Ventes du " + d1 + " au " + d2;
        if (idCl != 0) {
            Tclients cl = tclientsFacade.find(idCl);
            message += " de " + cl.getNom();
            ligneAccount = ligneAccountFacade.historiqueAccount(d1, d2, 1, idCl, etat);
        } else {
            message += " de la societé";
            idCl = utilisateur.getSociete().getId();
            ligneAccount = ligneAccountFacade.historiqueAccount(d1, d2, 0, idCl, etat);
        }

        request.setAttribute("messageTable", message);
        request.getSession().setAttribute("lignesAccount", ligneAccount);
    }

    private void historiqueAchatCaisse(HttpServletRequest request, Tusers utilisateur) {
        List<LigneSortie> ligneAccount = null;
        String[] date1 = request.getParameter("interval").split("-");
        Date d1 = date_du_jour.dateConvert(date1[0]);
        Date d2 = date_du_jour.dateConvert(date1[1]);
        int idCl = Integer.parseInt(request.getParameter("fournisseur"));
        int etat = Integer.parseInt(request.getParameter("etat"));
        String message = "Sortie pour Achat du " + d1 + " au " + d2;
        if (idCl != 0) {
            Fournisseur cl = fournisseurFacade.find(idCl);
            message += " de " + cl.getNom();
            ligneAccount = ligneSortieFacade.historiqueSortie(d1, d2, 1, idCl, etat);
        } else {
            message += " de la societé";
            idCl = utilisateur.getSociete().getId();
            ligneAccount = ligneSortieFacade.historiqueSortie(d1, d2, 0, idCl, etat);
        }

        request.setAttribute("messageTable", message);
        request.getSession().setAttribute("lignesSortie", ligneAccount);
    }

    private List<CommandeFournisseur> historiqueAchatsProduits(HttpServletRequest request, Societe societe) {
        int etat = Integer.parseInt(request.getParameter("statut"));
        String[] date1 = request.getParameter("interval").split("-");
        Date d1 = date_du_jour.dateConvert(date1[0]);
        Date d2 = date_du_jour.dateConvert(date1[1]);
        int idcl = Integer.parseInt(request.getParameter("article"));

        List<CommandeFournisseur> listCommandesH = null;
        if (idcl != 0) {
            Tarticles cl = tarticlesFacade.findTarticles(idcl);
            if (etat == 0) {
                listCommandesH = commandeFournisseurFacade.findTcommandesByArticleByPeriode(d1, d2, cl.getId(), etat).stream()
                        .collect(Collectors.toList());
            } else {
                listCommandesH = commandeFournisseurFacade.findTcommandesByArticleByPeriode(d1, d2, cl.getId(), etat).stream()
                        .collect(Collectors.toList());
            }
            request.setAttribute("messageTable", "Achats consernant l'article : " + cl.getNom() + " du " + date1[0] + " au " + date1[1]);
        } else {
            if (etat == 0) {
                listCommandesH = commandeFournisseurFacade.findTcommandesByPeriodeBySociete(d1, d2, societe.getId(), etat);
            } else {
                listCommandesH = commandeFournisseurFacade.findTcommandesByPeriodeBySociete(d1, d2, societe.getId(), etat);
            }
            request.setAttribute("messageTable", "Achats du " + date1[0] + " au " + date1[1]);
        }
        return listCommandesH;
    }

    private List<Tcommandes> historiqueVentesArticle(HttpServletRequest request, Societe societe) {
        int etat = Integer.parseInt(request.getParameter("statut"));
        String[] date1 = request.getParameter("interval").split("-");
        Date d1 = date_du_jour.dateConvert(date1[0]);
        Date d2 = date_du_jour.dateConvert(date1[1]);
        int idcl = Integer.parseInt(request.getParameter("article"));

        List<Tcommandes> listCommandesH = null;
        if (idcl != 0) {
            Tarticles cl = tarticlesFacade.findTarticles(idcl);
            if (etat == 0) {
                listCommandesH = tcommandesFacade.findTcommandesByArticleByPeriode(d1, d2, cl.getId(), etat).stream()
                        .collect(Collectors.toList());
            } else {
                listCommandesH = tcommandesFacade.findTcommandesByArticleByPeriode(d1, d2, cl.getId(), etat).stream()
                        .collect(Collectors.toList());
            }
            request.setAttribute("messageTable", "Vente consernant l'article : " + cl.getNom() + " du " + date1[0] + " au " + date1[1]);
        } else {
            if (etat == 0) {
                listCommandesH = tcommandesFacade.findTcommandesByPeriodeBySociete(d1, d2, societe.getId(), etat);
            } else {
                listCommandesH = tcommandesFacade.findTcommandesByPeriodeBySociete(d1, d2, societe.getId(), etat);
            }
            request.setAttribute("messageTable", "Vente du " + date1[0] + " au " + date1[1]);
        }
        return listCommandesH;
    }

    private void historiqueEntreesCaisse(HttpServletRequest request, Tusers t) {
        String[] date1 = request.getParameter("interval").split("-");
        Date d1 = date_du_jour.dateConvert(date1[0]);
        Date d2 = date_du_jour.dateConvert(date1[1]);
        int source = Integer.parseInt(request.getParameter("source"));
        List<Account> entrees = accountFacade.findAllByPeriode(d1, d2, t.getSociete().getId());

        if (source != 0) {
            entrees = entrees.stream().filter(ent -> ent.getSource().getId() == source).collect(Collectors.toList());
        }

        request.setAttribute("entrees", entrees);
    }

    private void historiqueSortiesCaisse(HttpServletRequest request, Tusers t) {
        String[] date1 = request.getParameter("interval").split("-");
        Date d1 = date_du_jour.dateConvert(date1[0]);
        Date d2 = date_du_jour.dateConvert(date1[1]);
        int source = Integer.parseInt(request.getParameter("source"));
        List<SortieCaisse> sorties = sortieCaisseFacade.findAllByPeriode(d1, d2, t.getSociete().getId());

        if (source != 0) {
            sorties = sorties.stream().filter(ent -> ent.getSource().getId() == source).collect(Collectors.toList());
        }
        request.setAttribute("sorties", sorties);
    }

}
