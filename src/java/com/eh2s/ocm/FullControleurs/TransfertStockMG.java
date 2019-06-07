/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.FullControleurs;

import com.eh2s.ocm.Entity.Inventaire;
import com.eh2s.ocm.Entity.LigneInventaire;
import com.eh2s.ocm.Entity.LigneTransfertStock;
import com.eh2s.ocm.Entity.Magasin;
import com.eh2s.ocm.Entity.Services.Message;
import com.eh2s.ocm.Entity.Services.ServicesInitialize;
import com.eh2s.ocm.Entity.Sessions.InventaireFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.LigneInventaireFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.LigneTransfertStockFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.MagasinFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.SocieteFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.StockMgFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TarticlesFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TclientsFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TcommandesFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TdistrictsFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TincidentsFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TlignecommandeFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TransfertStockFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TregionsFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TrubriquesFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TsecteursFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TsourcesFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TsrubriquesFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TusersFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.UserplainteFacadeLocal;
import com.eh2s.ocm.Entity.Societe;
import com.eh2s.ocm.Entity.StockMg;
import com.eh2s.ocm.Entity.TransfertStock;
import com.eh2s.ocm.Entity.Tusers;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "gestionTransfert", urlPatterns = {"/aoyurdfe148jg"})
@MultipartConfig(fileSizeThreshold = 6291456, // 6 MB
        maxFileSize = 10485760L, // 10 MB
        maxRequestSize = 20971520L, // 20 MB
        location = "/"
)
public class TransfertStockMG extends HttpServlet {

    @EJB
    private TransfertStockFacadeLocal transfertStockFacade;

    @EJB
    private LigneTransfertStockFacadeLocal ligneTransfertStockFacade;

    @EJB
    private TarticlesFacadeLocal tarticlesFacade;

    @EJB
    private StockMgFacadeLocal stockMgFacade;

    @EJB
    private LigneInventaireFacadeLocal ligneInventaireFacade;

    @EJB
    private InventaireFacadeLocal inventaireFacade;

    @EJB
    private MagasinFacadeLocal magasinFacade;

    @EJB
    private SocieteFacadeLocal societeFacade;

    @EJB
    private TcommandesFacadeLocal tcommandesFacade;

    @EJB
    private TlignecommandeFacadeLocal tlignecommandeFacade;

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
            Tusers u = (Tusers) session.getAttribute("utilisateur");
            String actions = request.getParameter("action");
            if (actions.equals("model")) {
                String model = request.getParameter("model");
                if (model.equals("transfertStock")) {
                    switch (Integer.parseInt(request.getParameter("isNew"))) {
                        case 0:
                            InittransfertStock(request, u);
                            break;
                        case 2:

                            deletteTransfertStock(request);
                            break;
                        case 3: {
                            try {
                                SaveTransfertStock(request);
                            } catch (Exception ex) {
                                Logger.getLogger(TransfertStock.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        break;
                        case 4: {
                            try {
                                deletteLigneTransfertStock(request);
                            } catch (Exception ex) {
                                Logger.getLogger(TransfertStock.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        break;
                        case 5: {
                            try {
                                suprimerTransfertStock(request);
                            } catch (Exception ex) {
                                Logger.getLogger(TransfertStock.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        break;
                        case 6: {
                            try {
                                ReceptionsTransfertStock(request);
                            } catch (Exception ex) {
                                Logger.getLogger(TransfertStock.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        break;
                        case 7:
//get transfert to update
                            List<TransfertStock> listInt = (List<TransfertStock>) request.getSession().getAttribute("TransfertStock");
                            TransfertStock tr1 = listInt.get(Integer.parseInt(request.getParameter("id")));
                            request.setAttribute("transfert", tr1);
                            request.setAttribute("TransfertStock", listInt);
                            break;
                        case 8:
// initialisation de la reception de transfert de stocks
                            if (request.getParameter("id") != null) {
                                Magasin mag = magasinFacade.findMagasin(Integer.parseInt(request.getParameter("id")));
                                request.getSession().setAttribute("magasinEncour", mag);
                                request.setAttribute("ligneTransferts", magasinFacade.findMagasin(mag.getId()).getLignetransfertstockList());
                            } else {
                                request.getSession().removeAttribute("magasinEncour");
                            }
                            break;
                        case 9:
                            // voir les details du transfert
                            if (request.getParameter("ligne") != null) {
                                LigneTransfertStock c = ligneTransfertStockFacade.find(Integer.parseInt(request.getParameter("ligne")));
                                request.setAttribute("ligneTransfert", c);
                            }
                            break;
                        default:
                            break;
                    }
                    servicesInitializeImple.initialize(request, u);
                    request.setAttribute("q", request.getParameter("q"));
                    request.setAttribute("iq", request.getParameter("iq"));
                    request.setAttribute("isNew", request.getParameter("isNew"));
                    request.getServletContext().getRequestDispatcher("/WEB-INF/main.jsp").forward(request, response);
                }

            } else {
                servicesInitializeImple.initialize(request, u);
                request.getServletContext().getRequestDispatcher("/WEB-INF/main.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("message", new Message("votre session a expirer veuillez vous reconnecter"));
            request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        }

    }

    private void Do_Inventaire_Mg(HttpServletRequest request, Tusers u) {
        boolean error = false;
        LigneInventaire lv = null;

        if (request.getSession().getAttribute("listInventaire") == null) {
            lv = new LigneInventaire();
            lv.setDateInv(new java.util.Date());
            lv.setMagasin(new Magasin(Integer.parseInt(request.getParameter("magasin"))));
            lv.setOperateur(u);
            try {
                lv = ligneInventaireFacade.creer(lv);
            } catch (Exception ex) {
                error = true;
                Logger.getLogger(TransfertStock.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.getSession().setAttribute("listInventaire", lv);
        }
        int who = Integer.parseInt(request.getParameter("who"));
        if (!error) {
            if (who == 0) {
                InventaireMg(request, lv);
            } else {
                UpdateInventaireMg(request);
            }
        }
    }

    private void InventaireMg(HttpServletRequest request, LigneInventaire lv) {
        List<Inventaire> listInt = new ArrayList<>();
        StockMg sm = stockMgFacade.findStockMg(Integer.parseInt(request.getParameter("stock")));
        Inventaire iv = new Inventaire();
        iv.setLigneInv(lv);
        iv.setId(sm.getId());
        iv.setQtAvant(sm.getStock());
        iv.setArticle(sm.getArticle());
        iv.setQuantite(Double.parseDouble(request.getParameter("qt")));
        iv.setQtApres(iv.getQtAvant() + iv.getQuantite());
        iv.setEcartQt(iv.getQtAvant() - iv.getQuantite());
        listInt.add(iv);
        request.getSession().setAttribute("inventaires", listInt);

    }

    private void UpdateInventaireMg(HttpServletRequest request) {
        List<Inventaire> listInt = (List<Inventaire>) request.getSession().getAttribute("inventaires");
        for (Inventaire iv : listInt) {
            if (iv.getId() == Integer.parseInt(request.getParameter("id"))) {
                listInt.remove(iv);
                iv.setQuantite(Double.parseDouble(request.getParameter("qt")));
                iv.setQtApres(iv.getQtAvant() + iv.getQuantite());
                iv.setEcartQt(iv.getQtAvant() - iv.getQuantite());
                listInt.add(iv);
                request.getSession().setAttribute("inventaires", listInt);
            }
        }
    }

    private void SaveInventaireMg(HttpServletRequest request, LigneInventaire lv) {
        List<Inventaire> listInt = (List<Inventaire>) request.getSession().getAttribute("listInventaire");
        boolean error = false;
        for (Inventaire iv : listInt) {
            StockMg sm = stockMgFacade.findStockMg(iv.getId());
            try {
                inventaireFacade.creer(iv);
            } catch (Exception ex) {
                error = true;
                Logger.getLogger(TransfertStock.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (!error) {
                sm.setStock(iv.getQuantite());
                sm.setStockV(sm.getStock());
                sm.setPrixTotal(sm.getStock() * sm.getPrix());
                try {
                    stockMgFacade.MisAJour(sm);
                } catch (Exception ex) {
                    Logger.getLogger(TransfertStock.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        request.getSession().removeAttribute("inventaires");
        request.getSession().removeAttribute("listInventaire");

    }
// transfert

    protected void InittransfertStock(HttpServletRequest request, Tusers u) {
        boolean error = false;
        if (request.getSession().getAttribute("ligneTransfert") == null) {
            if (request.getParameter("mg1") != null && request.getParameter("mg2") != null) {
                Magasin mag1 = magasinFacade.findMagasin(Integer.parseInt(request.getParameter("mg1")));
                Magasin mag2 = magasinFacade.findMagasin(Integer.parseInt(request.getParameter("mg2")));
                if (!Objects.equals(mag1.getId(), mag2.getId())) {
                    request.getSession().setAttribute("magasinEmetteur", mag1);
                    request.getSession().setAttribute("magasinRecepteur", mag2);
                    LigneTransfertStock ltr = new LigneTransfertStock();
                    ltr.setDateTransf(new java.util.Date());
                    ltr.setMg1(mag1);
                    ltr.setMg2(mag2);
                    ltr.setOperateur(u.getFirstname());
                    try {
                        System.out.println("Ligne 1"+ltr.getMg1());
                         System.out.println("Ligne 2"+ltr.getMg2());
                        ltr = ligneTransfertStockFacade.creer(ltr);
                        System.out.println("Ligne add tt "+ltr);
                    } catch (Exception ex) {
                        error = true;
                        Logger.getLogger(TransfertStock.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    request.getSession().setAttribute("ligneTransfert", ltr);
                    request.setAttribute("ligneTransfert", ltr);

                } else {
                    Message message = new Message();
                    message.setType("error");
                    message.setTitle("Impossible");
                    message.setNotification("Le magasin recepteur doit être différent de l'emetteur");
                    request.setAttribute("message", message);
                }

            } else {
                //  request.getSession().removeAttribute("magasinEmetteur");
                // request.getSession().removeAttribute("magasinRecepteur");
            }

        }

        int who = Integer.parseInt(request.getParameter("isNew"));
        if (!error) {
            if (who == 0) {
                TransfertStock(request);
            } else if (who == 1) {
                UpdateTransfertStock(request);
            }
        }
    }

    protected void TransfertStock(HttpServletRequest request) {
        List<TransfertStock> listr;
        if (request.getSession().getAttribute("TransfertStock") != null) {
            listr = (List<TransfertStock>) request.getSession().getAttribute("TransfertStock");
        } else {
            listr = new ArrayList<>();
        }
        LigneTransfertStock lt = (LigneTransfertStock) request.getSession().getAttribute("ligneTransfert");
        TransfertStock tr = new TransfertStock();
        tr.setArticle(tarticlesFacade.findTarticles(Integer.parseInt(request.getParameter("article"))));
        tr.setLigneTransfert(lt);
        tr.setQuantite(Integer.parseInt(request.getParameter("quantite")));
        tr.setId(tr.getArticle().getId());
        listr.add(tr);
        request.getSession().setAttribute("TransfertStock", listr);
        request.setAttribute("TransfertStock", listr);

    }

    protected void UpdateTransfertStock(HttpServletRequest request) {
        List<TransfertStock> listInt = (List<TransfertStock>) request.getSession().getAttribute("TransfertStock");
        for (TransfertStock iv : listInt) {
            if (iv.getId() == Integer.parseInt(request.getParameter("id"))) {
                listInt.remove(iv);
                iv.setQuantite(Integer.parseInt(request.getParameter("quantite")));
                listInt.add(iv);
                request.getSession().setAttribute("TransfertStock", listInt);
                request.setAttribute("TransfertStock", listInt);
                break;
            }
        }
    }

    protected void deletteTransfertStock(HttpServletRequest request) {
        List<TransfertStock> listInt = (List<TransfertStock>) request.getSession().getAttribute("TransfertStock");
        for (TransfertStock iv : listInt) {
            if (iv.getId() == Integer.parseInt(request.getParameter("id"))) {
                listInt.remove(iv);
                request.getSession().setAttribute("TransfertStock", listInt);
                request.setAttribute("TransfertStock", listInt);
                break;
            }
        }
    }

    protected void SaveTransfertStock(HttpServletRequest request) throws Exception {
        List<TransfertStock> listInt = (List<TransfertStock>) request.getSession().getAttribute("TransfertStock");
        for (TransfertStock iv : listInt) {
            System.out.println("Ligne " + iv.getLigneTransfert());
            transfertStockFacade.creer(iv);
        }
        request.getSession().removeAttribute("magasinEmetteur");
        request.getSession().removeAttribute("magasinRecepteur");
        request.getSession().removeAttribute("TransfertStock");
        request.getSession().removeAttribute("ligneTransfert");
    }

    protected void deletteLigneTransfertStock(HttpServletRequest request) throws Exception {
        LigneTransfertStock ts = ligneTransfertStockFacade.findLigneTransfertStock(Integer.parseInt(request.getParameter("id")));
        ligneTransfertStockFacade.Supprimer(ts.getId());
        request.setAttribute("ligneTransferts", magasinFacade.findMagasin(ts.getMg1().getId()).getLignetransfertstockList());
    }

    protected void suprimerTransfertStock(HttpServletRequest request) throws Exception {
        TransfertStock ts = transfertStockFacade.findTransfertStock(Integer.parseInt(request.getParameter("id")));
        transfertStockFacade.Supprimer(ts.getId());
        request.setAttribute("TransfertStocks", transfertStockFacade.findTransfertStockByLigne(ts.getLigneTransfert().getId()));
    }

    protected void ReceptionsTransfertStock(HttpServletRequest request) throws Exception {
        LigneTransfertStock ts = ligneTransfertStockFacade.findLigneTransfertStock(Integer.parseInt(request.getParameter("id")));
        for (TransfertStock iv : ts.getTransfertStockList()) {
            StockMg st = getStockMg(ts.getMg2().getStockMgList(), iv.getArticle().getId());
            st.setStock(iv.getQuantite() + st.getStock());
            st.setStockV(st.getStock());
            st.setPrixTotal(st.getStock() * st.getPrix());
            stockMgFacade.MisAJour(st);
        }
        request.setAttribute("ligneTransferts", ts.getMg2().getLignetransfertstockList());
    }

    private StockMg getStockMg(List<StockMg> liste, int id) {
        StockMg c = null;
        for (StockMg cc : liste) {
            if (cc.getArticle().getId() == id) {
                c = cc;
                break;
            }
        }
        return c;
    }
}
