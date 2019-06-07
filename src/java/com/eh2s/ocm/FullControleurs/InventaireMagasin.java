/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.FullControleurs;

import com.eh2s.ocm.Entity.Emballage;
import com.eh2s.ocm.Entity.Inventaire;
import com.eh2s.ocm.Entity.LigneInventaire;
import com.eh2s.ocm.Entity.Magasin;
import com.eh2s.ocm.Entity.Services.Message;
import com.eh2s.ocm.Entity.Services.ServicesInitialize;
import com.eh2s.ocm.Entity.Sessions.ActionAPKFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.EmballageFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.InventaireFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.LigneInventaireFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.MagasinFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.SocieteFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.StockMgFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TactionsFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TarticlesFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TcategorieFacadeLocal;
import com.eh2s.ocm.Entity.Societe;
import com.eh2s.ocm.Entity.StockMg;
import com.eh2s.ocm.Entity.Tarticles;
import com.eh2s.ocm.Entity.Tcategorie;
import com.eh2s.ocm.Entity.Tusers;
import com.eh2s.ocm.UtilityFonctions.date_du_jour;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.JSONArray;

/**
 *
 * @author Administrateur
 */
@WebServlet(name = "InventaireMagasin", urlPatterns = {"/inventaire"})
@MultipartConfig(fileSizeThreshold = 6291456, // 6 MB
        maxFileSize = 10485760L, // 10 MB
        maxRequestSize = 20971520L, // 20 MB
        location = "/"
)
public class InventaireMagasin extends HttpServlet {
    
    @EJB
    private EmballageFacadeLocal emballageFacade;
    
    @EJB
    private SocieteFacadeLocal societeFacade;
    
    @EJB
    private TcategorieFacadeLocal tcategorieFacade;
    
    @EJB
    private TactionsFacadeLocal tactionsFacade;
    
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
    private ServicesInitialize servicesInitializeImple;
    @PersistenceContext(unitName = "ccmanagerPU")
    private EntityManager em;
    @Resource
    private javax.transaction.UserTransaction utx;
    
    @EJB
    private ActionAPKFacadeLocal actionAPKFacade;

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
            Tusers utilisateur = (Tusers) session.getAttribute("utilisateur");
            Societe societe = (Societe) session.getAttribute("societe");
            request.setAttribute("actionAPK", actionAPKFacade.findAll());
            String actions = request.getParameter("action");
            request.setAttribute("q", request.getParameter("q"));
            if (request.getParameter("type") != null) {
                request.setAttribute("type", Integer.parseInt(request.getParameter("type")));
            }
            switch (actions) {
                case "model":
                    String model = request.getParameter("model");
                    switch (model) {
                        case "inventaire":
                            switch (Integer.parseInt(request.getParameter("isNew"))) {
                                case 0: {
                                    try {
                                        //initialisation de l'inventaire
                                        initInventaire(request, utilisateur);
                                    } catch (Exception ex) {
                                        Logger.getLogger(Inventaire.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                
                                break;
                                case 1:
                                    //ajouter une nouvel inventaire
                                    try {
                                        //initialisation de l'inventaire
                                        addInventaire(request, false);
                                    } catch (Exception ex) {
                                        Logger.getLogger(Inventaire.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    break;
                                case 2:
                                    //Enregistrer l' inventaire
                                    saveInventaire(request, utilisateur);
                                    
                                    break;
                                case 3: {
                                    try {
                                        //Modifier l' inventaire
                                        addInventaire(request, true);
                                    } catch (Exception ex) {
                                        Logger.getLogger(Inventaire.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                                break;
                                case 4:
                                    //supprimer l' inventaire
                                    if (request.getSession().getAttribute("ligneInventaire") != null) {
                                        LigneInventaire ligne = (LigneInventaire) request.getSession().getAttribute("ligneInventaire");
                                        List<Inventaire> listinv = ligne.getInventaireList();
                                        int id = Integer.parseInt(request.getParameter("id"));
                                        
                                        if (listinv.size() == 1) {
                                            listinv.clear();
                                            //request.getSession().removeAttribute("ligneInventaire");
                                            //request.removeAttribute("inventaires");
                                        } else {
                                            listinv.remove(id);
                                            ligne.setInventaireList(listinv);
                                            request.getSession().setAttribute("ligneInventaire", ligne);
                                            request.setAttribute("inventaires", listinv);
                                        }
                                        
                                    }
                                    break;
                                case 5:
                                    //obtenir  l' inventaire à modifier
                                    if (request.getSession().getAttribute("ligneInventaire") != null) {
                                        LigneInventaire ligne = (LigneInventaire) request.getSession().getAttribute("ligneInventaire");
                                        List<Inventaire> listinv = ligne.getInventaireList();
                                        int id = Integer.parseInt(request.getParameter("id"));
                                        request.setAttribute("inventaire", listinv.get(id));
                                        request.setAttribute("inventaires", listinv);
                                    }
                                    break;
                                case 6:
                                    //Annuler l' inventaire
                                    request.getSession().removeAttribute("ligneInventaire");
                                    request.getSession().removeAttribute("magasinEncour");
                                    break;
                                case 7:
                                    //envoyer tous les inventaires
                                    request.setAttribute("inventaires", ligneInventaireFacade.findAllLigneInventaireSociete(societe.getId()));
                                    break;
                                
                            }
                            break;
                        case "historiqueInventaire":
                            switch (Integer.parseInt(request.getParameter("isNew"))) {
                                case 0:
                                    try {
                                        //initialisation de l'inventaire
                                        initInventaire(request, utilisateur);
                                    } catch (Exception ex) {
                                        Logger.getLogger(Inventaire.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    break;
                                case 1:
                                    try {
                                        //initialisation de l'inventaire
                                        historiqueInventaire(request, utilisateur);
                                    } catch (Exception ex) {
                                        Logger.getLogger(Inventaire.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    break;
                                case 2:
                                    try {
                                        //détails de l'inventaire
                                        int id = Integer.parseInt(request.getParameter("id"));
                                        LigneInventaire ligne = ligneInventaireFacade.find(id);
                                        request.setAttribute("ligneInventaire", ligne);
                                    } catch (Exception ex) {
                                        Logger.getLogger(Inventaire.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    break;
                                case 3:
                                    try {
                                        //détails de l'inventaire
                                        historiqueDetailleInventaire(request, utilisateur);
                                    } catch (Exception ex) {
                                        Logger.getLogger(Inventaire.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    break;
                            }
                        case "TransfertStock":
                            switch (Integer.parseInt(request.getParameter("isNew"))) {
                                case 0:
                                    try {
                                        //initialisation du transfert de stock
                                        initTransfert(request, utilisateur);
                                    } catch (Exception ex) {
                                        Logger.getLogger(Inventaire.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    break;
                                
                            }
                        default:
                            break;
                    }
                    if (utilisateur.getId() == -1) {
                        servicesInitializeImple.initiationSuperAdmin(request);
                    } else {
                        if (utilisateur.getGroupeid().getType() == -1) {
                            servicesInitializeImple.initiationEntityAdmin(request, utilisateur);
                        } else if (utilisateur.getGroupeid().getType() == 1) {
                            servicesInitializeImple.initiationEntityAdminLocal(request, utilisateur);
                        }
                        servicesInitializeImple.initialize(request, utilisateur);
                    }
                    
                    request.setAttribute("q", request.getParameter("q"));
                    request.getServletContext().getRequestDispatcher("/WEB-INF/main.jsp").forward(request, response);
                    
                    break;
                case "getStockArticle":
                    JSONArray stock = new JSONArray();
                    HashMap r = new HashMap();
                    StockMg st = stockMgFacade.findStockArticleMg(Integer.parseInt(request.getParameter("article")), Integer.parseInt(request.getParameter("magasin")));
                    
                    r.put("id", st.getId());
                    r.put("stock", st.getStock());
                    r.put("stockV", st.getStockV());
                    r.put("prix", st.getPrix());
                    r.put("prixTotal", st.getPrixTotal());
                    stock.put(r);
                    response.setContentType("application/json");
                    response.getWriter().print(stock);
                    break;
            }
            
        } else {
            request.setAttribute("message", new Message("votre session a expirer veuillez vous reconnecter"));
            request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }
// initialisation des inventaires

    public void initInventaire(HttpServletRequest request, Tusers t) throws Exception {
        if (request.getParameter("id") != null || request.getParameter("societe") != null) {
            LigneInventaire ligneInventaire = new LigneInventaire();
            if (request.getParameter("id") != null) {
                Magasin mag = magasinFacade.findMagasin(Integer.parseInt(request.getParameter("id")));
                request.getSession().setAttribute("magasinEncour", mag);
                ligneInventaire.setMagasin(mag);
            } else if (request.getParameter("societe") != null) {
                ligneInventaire.setSociete(t.getSociete());
            }
            // initialisation de la creation d'un inventaire
            ligneInventaire.setOperateur(t);
            if (request.getParameter("type") != null) {
                ligneInventaire.setType(Integer.parseInt(request.getParameter("type")));
            }
            if (request.getParameter("historique") != null) {
                request.setAttribute("inventaires", ligneInventaireFacade.findAllLigneInventaireSociete(t.getSociete().getId()));
            }
            ligneInventaire.setDateInv(date_du_jour.dateJour());
            request.getSession().setAttribute("ligneInventaire", ligneInventaire);
        } else {
            request.getSession().removeAttribute("magasinEncour");
        }
    }
    // initialisation des inventaires

    public void initTransfert(HttpServletRequest request, Tusers t) throws Exception {
        if (request.getParameter("mg1") != null && request.getParameter("mg2") != null) {
            Magasin mag1 = magasinFacade.findMagasin(Integer.parseInt(request.getParameter("mg1")));
            Magasin mag2 = magasinFacade.findMagasin(Integer.parseInt(request.getParameter("mg2")));
            if (!Objects.equals(mag1.getId(), mag2.getId())) {
                request.getSession().setAttribute("magasinEmetteur", mag1);
                request.getSession().setAttribute("magasinRecepteur", mag2);
            } else {
                Message message = new Message();
                message.setType("error");
                message.setTitle("Impossible");
                message.setNotification("Le magasin recepteur doit être différent de l'emetteur");
                request.setAttribute("message", message);
            }
            
        } else {
            request.getSession().removeAttribute("magasinEmetteur");
            request.getSession().removeAttribute("magasinRecepteur");
        }
    }
// ajouter et modifier un inventaire

    public void addInventaire(HttpServletRequest request, boolean update) throws Exception {
        if (request.getSession().getAttribute("ligneInventaire") != null) {
            LigneInventaire ligne = (LigneInventaire) request.getSession().getAttribute("ligneInventaire");
            List<com.eh2s.ocm.Entity.Inventaire> listinv = new ArrayList<>();
            StockMg stock = null;
            if (ligne.getInventaireList() != null) {
                listinv = ligne.getInventaireList();
            }
            Tarticles art = new Tarticles();
            Emballage emb = new Emballage();
            int type = 0;
            if (request.getParameter("type") != null) {
                type = Integer.parseInt(request.getParameter("type"));
                if (type == 0) {
                    art = tarticlesFacade.findTarticles(Integer.parseInt(request.getParameter("article")));
                } else if (type == 1) {
                    emb = emballageFacade.find(Integer.parseInt(request.getParameter("article")));
                }
                
            }
            
            if (ligne.getMagasin() != null) {
                stock = stockMgFacade.findStockArticleMg(art.getId(), ligne.getMagasin().getId());
            }
            Inventaire inventaire = null;
            if (!update) {
                inventaire = new Inventaire();
                int id = 0;
                if (listinv != null && !listinv.isEmpty()) {
                    for (Inventaire inv : listinv) {
                        id++;
                    }
                }
                
                inventaire.setId(id);
            } else {
                int id = Integer.parseInt(request.getParameter("inventaire"));
                inventaire = new Inventaire(id);
            }
            inventaire.setType(type);
            if (type == 0) {
                inventaire.setArticle(art);
            } else if (type == 1) {
                inventaire.setEmballage(emb);
            }
            
            if (ligne.getMagasin() != null) {
                inventaire.setQtAvant(stock.getStock());
            } else if (ligne.getSociete() != null) {
                if (type == 0) {
                    
                    inventaire.setQtAvant(art.getStock());
                } else if (type == 1) {
                    inventaire.setQtAvant(emb.getStock());
                }
                
            }
            inventaire.setQuantite(Double.parseDouble(request.getParameter("quantite")));
            inventaire.setQtApres(inventaire.getQuantite());
            
            Double ecart = inventaire.getQtApres() - inventaire.getQtAvant();
            inventaire.setEcartQt(ecart);
            if (!update) {
                if (!exist(listinv, inventaire, type)) {
                    listinv.add(inventaire);
                }
                
            } else {
                int id = Integer.parseInt(request.getParameter("inventaire"));
                listinv.set(id, inventaire);
                
            }
            ligne.setInventaireList(listinv);
            request.setAttribute("inventaires", listinv);
        } else {
            request.getSession().removeAttribute("magasinEncour");
        }
    }
// historique des inventaires

    public void historiqueInventaire(HttpServletRequest request, Tusers t) {
        // DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String[] date1 = request.getParameter("interval").split("-");
        Date d1 = date_du_jour.dateConvert(date1[0]);
        Date d2 = date_du_jour.dateConvert(date1[1]);
        if (request.getSession().getAttribute("magasinEncour") != null) {
            Magasin mag = (Magasin) request.getSession().getAttribute("magasinEncour");
            List<LigneInventaire> listInv = ligneInventaireFacade.historiqueInventaire(d1, d2, mag.getId());
            request.setAttribute("inventaires", listInv);
        } else {
            List<LigneInventaire> listInv = ligneInventaireFacade.historiqueInventaireSociete(d1, d2, t.getSociete().getId());
            request.setAttribute("inventaires", listInv);
        }
        
    }
// historique détaillé des inventaires

    public void historiqueDetailleInventaire(HttpServletRequest request, Tusers t) {
        // DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String[] date1 = request.getParameter("interval").split("-");
        Date d1 = date_du_jour.dateConvert(date1[0]);
        Date d2 = date_du_jour.dateConvert(date1[1]);
        int pour = 1;
        int type = 1;
        if (request.getParameter("for") != null) {
            pour = Integer.parseInt(request.getParameter("for"));
        }
        if (request.getParameter("type") != null) {
            type = Integer.parseInt(request.getParameter("type"));
        }
        int entite;
        if (request.getSession().getAttribute("magasinEncour") != null) {
            Magasin mag = (Magasin) request.getSession().getAttribute("magasinEncour");
            String msg = "Historique Inventaire du magasin " + mag.getNom() + "du " + d1 + " AU " + d2 + " concernant";
            if (pour == 0) {
                entite = Integer.parseInt(request.getParameter("categorie"));
                Tcategorie cat = tcategorieFacade.findTcategorie(entite);
                msg += " la catégorie : " + cat.getNom();
            } else {
                entite = Integer.parseInt(request.getParameter("article"));
                Tarticles art = tarticlesFacade.findTarticles(entite);
                msg += " l'article : " + art.getNom();
            }
            List<Inventaire> listInv = inventaireFacade.historiqueInventaire(d1, d2, mag.getId(), entite, pour);
            request.setAttribute("messageTable", msg);
            request.setAttribute("inventaires", listInv);
        } else {
            List<Inventaire> listInv = new ArrayList<Inventaire>();
            String msg = "Historique Inventaire du " + d1 + " AU " + d2 + " concernant";
            if (pour == 0) {
                entite = Integer.parseInt(request.getParameter("categorie"));
                Tcategorie cat = tcategorieFacade.findTcategorie(entite);
                msg += " la catégorie : " + cat.getNom();
            } else {
                entite = Integer.parseInt(request.getParameter("article"));
                if (type == 0) {
                    Tarticles art = tarticlesFacade.findTarticles(entite);
                    msg += " l'article : " + art.getNom();
                    
                } else if (type == 1) {
                    Emballage emb = emballageFacade.find(entite);
                    msg += " l'emballage : " + emb.getNom();
                }
                
            }
            listInv = inventaireFacade.historiqueInventaireSociete(d1, d2, t.getSociete().getId(), entite, pour, type);
            request.setAttribute("messageTable", msg);
            request.setAttribute("inventaires", listInv);
        }
        
    }

    // déterminer si l'inventaire sur un article existe dans une liste d'inventaire
    public boolean exist(List<com.eh2s.ocm.Entity.Inventaire> listInv, com.eh2s.ocm.Entity.Inventaire inv, Integer type) {
        for (com.eh2s.ocm.Entity.Inventaire inven : listInv) {
            if (type == 0) {
                if (inven.getArticle().getId().compareTo(inv.getArticle().getId()) == 0) {
                    return true;
                }
            } else if (type == 1) {
                if (inven.getEmballage().getId().compareTo(inv.getEmballage().getId()) == 0) {
                    return true;
                }
                
            }
        }
        return false;
    }
    
    private Tarticles getArticle(List<Tarticles> liste, Tarticles ar) {
        for (Tarticles st : liste) {
            if (st.getCode().equals(ar.getCode())) {
                return st;
            }
        }
        return null;
    }
    
    private List<Tarticles> getListArticle(List<Inventaire> liste) {
        List<Tarticles> a = new ArrayList<>();
        for (Inventaire st : liste) {
            a.add(st.getArticle());
        }
        return a;
    }
    
    private List<Emballage> getListEmballage(List<Inventaire> liste) {
        List<Emballage> a = new ArrayList<>();
        for (Inventaire st : liste) {
            a.add(st.getEmballage());
        }
        return a;
    }
    
    private void saveInventaire(HttpServletRequest request, Tusers utilisateur) {
        if (request.getSession().getAttribute("ligneInventaire") != null) {
            boolean error = false;
            LigneInventaire ligne = (LigneInventaire) request.getSession().getAttribute("ligneInventaire");
            List<Inventaire> listinv = ligne.getInventaireList();
            LigneInventaire ligneTosave = new LigneInventaire();
            ligneTosave.setDateInv(new java.util.Date());
            int type = 0;
            int isInv = 0;
            if (request.getParameter("type") != null) {
                type = Integer.parseInt(request.getParameter("type"));
            }
            if (request.getParameter("isInv") != null) {
                isInv = Integer.parseInt(request.getParameter("isInv"));
                ligneTosave.setIsInv(Integer.parseInt(request.getParameter("isInv")));
            }
            if (ligne.getMagasin() != null) {
                ligneTosave.setMagasin(ligne.getMagasin());
            } else if (ligne.getSociete() != null) {
                
                ligneTosave.setSociete(ligne.getSociete());
            }
            if (request.getParameter("commentaire") != null) {
                if (!request.getParameter("commentaire").isEmpty()) {
                    ligneTosave.setDescription(request.getParameter("commentaire"));
                }
                
            }
            ligneTosave.setOperateur(ligne.getOperateur());
            ligneTosave.setType(type);
            try {
                ligneTosave = ligneInventaireFacade.creer(ligneTosave);
            } catch (Exception ex) {
                Logger.getLogger(Inventaire.class.getName()).log(Level.SEVERE, null, ex);
            }
            List<Inventaire> listinvTosave = ligne.getInventaireList();
            
            for (Inventaire inv : listinvTosave) {
                Inventaire invTosave = new Inventaire();
                if (type == 0) {
                    invTosave.setArticle(inv.getArticle());
                } else if (type == 1) {
                    invTosave.setEmballage(inv.getEmballage());
                }
                
                invTosave.setQuantite(inv.getQuantite());
                invTosave.setEcartQt(inv.getEcartQt());
                invTosave.setQtApres(inv.getQtApres());
                invTosave.setQtAvant(inv.getQtAvant());
                invTosave.setLigneInv(ligneTosave);
                try {
                    invTosave = inventaireFacade.creer(invTosave);
                    if (ligne.getMagasin() != null) {
                        if (type == 0) {
                            StockMg stk = stockMgFacade.findStockArticleMg(invTosave.getArticle().getId(), ligneTosave.getMagasin().getId());
                            if (isInv == 1) {
                                stk.setStock(stk.getStock() + invTosave.getQtApres());
                            } else {
                                stk.setStock(invTosave.getQtApres());
                            }
                            stk.setStockV(invTosave.getQtApres());
                            stk.setPrixTotal(stk.getPrix() * invTosave.getQtApres());
                            stockMgFacade.MisAJour(stk);
                        }
                        
                    } else if (ligne.getSociete() != null) {
                        if (type == 0) {
                            Tarticles art = invTosave.getArticle();
                            if (isInv == 1) {
                                art.setStock(art.getStock() + invTosave.getQtApres());
                            } else {
                                art.setStock(invTosave.getQtApres());
                            }
                            tarticlesFacade.MiSaJour(art);
                        } else if (type == 1) {
                            Emballage emb = invTosave.getEmballage();
                            if (isInv == 1) {
                                emb.setStock(emb.getStock() + invTosave.getQtApres());
                            } else {
                                emb.setStock(invTosave.getQtApres());
                            }
                            emb.setPrixTotal(emb.getPrix() * emb.getStock());
                            emballageFacade.MisAjour(emb);
                        }
                        
                    }
                    
                } catch (Exception ex) {
                    error = true;
                    Logger.getLogger(Inventaire.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (!error) {

//                if (type == 0) {
//                    List<Tarticles> ars = tarticlesFacade.findAllBySociete(utilisateur.getSociete().getId());
//                    ars.removeAll(getListArticle(listinvTosave));
//                    for (Tarticles ar : ars) {
//                        Inventaire invTosave = new Inventaire();
//                        invTosave.setArticle(ar);
//                        invTosave.setQuantite(0);
//                        invTosave.setEcartQt(0);
//                        invTosave.setQtApres(ar.getStock());
//                        invTosave.setQtAvant(ar.getStock());
//                        invTosave.setLigneInv(ligneTosave);
//                        try {
//                            invTosave = inventaireFacade.creer(invTosave);
//                            if (ligne.getMagasin() != null) {
//                                StockMg stk = stockMgFacade.findStockArticleMg(invTosave.getArticle().getId(), ligneTosave.getMagasin().getId());
//                                stk.setStock(invTosave.getQtApres());
//                                stk.setPrixTotal(stk.getPrix() * invTosave.getQtApres());
//                                stk.setStockV(invTosave.getQtApres());
//                                stockMgFacade.edit(stk);
//                            } else if (ligne.getSociete() != null) {
//                                Tarticles art = invTosave.getArticle();
//                                art.setStock(invTosave.getQtApres());
//                                tarticlesFacade.edit(art);
//                            }
//
//                        } catch (Exception ex) {
//                            Logger.getLogger(Inventaire.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                    }
//                } else if (type == 1) {
//                    List<Emballage> embs = emballageFacade.findAll(utilisateur.getSociete().getId());
//
//                    embs.removeAll(getListEmballage(listinvTosave));
//                    for (Emballage ar : embs) {
//                        Inventaire invTosave = new Inventaire();
//                        invTosave.setEmballage(ar);
//                        invTosave.setQuantite(0);
//                        invTosave.setEcartQt(0);
//                        invTosave.setQtApres(ar.getStock());
//                        invTosave.setQtAvant(ar.getStock());
//                        invTosave.setLigneInv(ligneTosave);
//                        try {
//                            invTosave = inventaireFacade.creer(invTosave);
//                            if (ligne.getSociete() != null) {
//                                Emballage art = invTosave.getEmballage();
//                                art.setStock(invTosave.getQtApres());
//                                emballageFacade.edit(art);
//                            }
//
//                        } catch (Exception ex) {
//                            Logger.getLogger(Inventaire.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                    }
//                }
                Message message = new Message();
                message.setType("success");
                message.setTitle("ENREGISTREMENT");
                message.setNotification("Votre inventaire a été enregistré avec succès");
                request.setAttribute("message", message);
            } else {
                Message message = new Message();
                message.setType("error");
                message.setTitle("ERROR");
                message.setNotification("Une ERREUR et survenue veuillez réessayer");
                request.setAttribute("message", message);
            }
            
        }
        request.getSession().removeAttribute("ligneInventaire");
        request.getSession().removeAttribute("magasinEncour");
    }
}
