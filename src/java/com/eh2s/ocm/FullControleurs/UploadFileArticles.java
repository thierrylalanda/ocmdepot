package com.eh2s.ocm.FullControleurs;

import Decoder.BASE64Encoder;
import com.eh2s.ocm.Entity.Services.Message;
import com.eh2s.ocm.Entity.Services.ServicesInitialize;
import com.eh2s.ocm.Entity.Services.encryptedHash;
import com.eh2s.ocm.Entity.Sessions.SocieteFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TarticlesFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TcategorieFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TclientsFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TsecteursFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TtypeclientsFacadeLocal;
import com.eh2s.ocm.Entity.Societe;
import com.eh2s.ocm.Entity.Tarticles;
import com.eh2s.ocm.Entity.Tcategorie;
import com.eh2s.ocm.Entity.Tclients;
import com.eh2s.ocm.Entity.Tusers;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author messi
 */
@WebServlet(name = "UploadFileArticles", urlPatterns = {"/UploadFileArticles"})
public class UploadFileArticles extends HttpServlet {

    @EJB
    private ServicesInitialize servicesInitializeImple;

    @EJB
    private TtypeclientsFacadeLocal ttypeclientsFacade;

    @EJB
    private TsecteursFacadeLocal tsecteursFacade;

    @EJB
    private TclientsFacadeLocal tclientsFacade;

    @EJB
    private SocieteFacadeLocal societeFacade;

    @EJB
    private TarticlesFacadeLocal tarticlesFacade;

    @EJB
    private TcategorieFacadeLocal tcategorieFacade;

    private static final long serialVersionUID = 1L;

    private static final String UPLOAD_DIRECTORY = "xml";
    private static final int THRESHOLD_SIZE = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        if (session.getAttribute("isconnect") != null) {
            Societe societe;
            Tusers utilisateur = (Tusers) session.getAttribute("utilisateur");
            if (request.getParameter("societe") == null) {
                societe = (Societe) request.getSession().getAttribute("societe");
            } else {
                societe = societeFacade.findSociete(Integer.parseInt(request.getParameter("societe")));
            }
// checks if the request actually contains upload file
            if (!ServletFileUpload.isMultipartContent(request)) {
                PrintWriter writer = response.getWriter();
                writer.println("Aucun fichier selectionner");
                writer.flush();
                return;
            }

            // configures upload settings
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(THRESHOLD_SIZE);
            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setFileSizeMax(MAX_FILE_SIZE);
            upload.setSizeMax(MAX_REQUEST_SIZE);

            // constructs the directory path to store upload file
            String fileName = "";
            String uploadPath = getServletContext().getRealPath("")
                    + File.separator + UPLOAD_DIRECTORY;

            // creates the directory if it does not exist
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            try {
                // parses the request's content to extract file data
                List formItems = upload.parseRequest(request);
                Iterator iter = formItems.iterator();

                // iterates over form's fields
                while (iter.hasNext()) {
                    FileItem item = (FileItem) iter.next();
                    // processes only fields that are not form fields
                    if (!item.isFormField()) {
                        fileName = new File(item.getName()).getName();
                        String filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);

                        // saves the file on disk
                        item.write(storeFile);
                    }
                }
                request.setAttribute("message", "Upload has been done successfully!" + fileName);
            } catch (Exception ex) {
                request.setAttribute("message", "There was an error: " + ex.getMessage());
            }
            //debut de la lecture du fichier Excel et de l'enregistrement des elements dans la BD em fonction de la societe
            if (request.getParameter("fileArticles") != null) {
                traitementArticle(request, fileName, uploadPath, societe);
            } else if (request.getParameter("fileCategorie") != null) {
                traitementCategorieArticle(request, fileName, uploadPath, societe);
            } else if (request.getParameter("fileclients") != null) {
                traitementClients(request, fileName, uploadPath, societe);
            }
            servicesInitializeImple.initialize(request, utilisateur);
            request.setAttribute("q", request.getParameter("q"));
            request.getServletContext().getRequestDispatcher("/WEB-INF/main.jsp").forward(request, response);
        } else {
            request.setAttribute("message", new Message("votre session a expirer veuillez vous reconnecter"));
            request.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    void traitementArticle(HttpServletRequest request, String fileName, String uploadPath, Societe s) {

        if (fileName.endsWith(".xlsx") || fileName.endsWith(".xls")) {

            List<Tarticles> lists = lireExcelArticle(fileName, uploadPath);

            List<Tarticles> listarticle = tarticlesFacade.findAll(s.getImmatriculation());
            List<Tarticles> listnewarticle = new ArrayList<>();
            if (!listarticle.isEmpty()) {
                for (Tarticles article : lists) {
                    for (Tarticles arti : listarticle) {
                        if (article.getCode().equalsIgnoreCase(arti.getCode())) {
                            listnewarticle.add(arti);
                        }
                    }
                }
            }
            if (!listnewarticle.isEmpty()) {
                for (int i = 0; i < listnewarticle.size(); i++) {
                    lists.remove(i);
                }
            }
            if (!lists.isEmpty()) {
                for (Tarticles article : lists) {
                    tarticlesFacade.create(article);
                }
                List<Tcategorie> cp = tcategorieFacade.findAll(s.getImmatriculation());
                for (Tcategorie ca : cp) {
                    List<Tarticles> l = tarticlesFacade.findAllByCategorie(ca.getId());
                    ca.setTarticlesList(l);
                    tcategorieFacade.edit(ca);
                }

                request.setAttribute("articles", lists);
                Message message = new Message("Ces articles ont étés enregistrer avec succes");
                message.setTitle("info");
                message.setType("success");
                message.setNotification("Ces articles ont étés enregistrer avec succes");
                request.setAttribute("message", message);
            } else {
                Message message = new Message("Une erreur est apparue lors de la lecture du fichier vérifier le formatage du fichier excel et l'éxactitude de la rubrique de catégorie de produit .");
                message.setTitle("Alert");
                message.setType("error");
                message.setNotification("Une erreur est apparue lors de la lecture du fichier vérifier le formatage du fichier excel et l'éxactitude de la rubrique de catégorie de produit .");
                request.setAttribute("message", message);
            }

            lists.clear();

        } else {
            Message message = new Message("Veiller choisir un fichier Excel");
            message.setTitle("Alert");
            message.setType("info");
            message.setNotification("Veiller choisir un fichier Excel");
            request.setAttribute("message", message);
        }//fin  
    }

    public List<Tarticles> lireExcelArticle(String namefile, String chemin) {

        List<Tarticles> list = new ArrayList<>();

        File excel = new File(chemin + "/" + namefile);

        try (FileInputStream fis = new FileInputStream(excel)) {
            XSSFWorkbook book = new XSSFWorkbook(chemin + "/" + namefile);
            List<XSSFSheet> sheets = new ArrayList<>();
            for (int i = 0; i < book.getNumberOfSheets(); i++) {
                sheets.add(book.getSheetAt(i));
            }
            boolean error = false;
            for (XSSFSheet sheet : sheets) {

                for (Iterator rowIt = sheet.rowIterator(); rowIt.hasNext();) {

                    Row row = (Row) rowIt.next();
                    error = Article(row, list);
                    if (error) {
                        break;
                    }
                }
                if (error) {
                    list.clear();
                    break;
                }
            }

        } catch (FileNotFoundException fe) {
            System.out.println("error1243 " + fe.getMessage());
            fe.printStackTrace();
        } catch (IOException ie) {
            System.out.println("error5434 " + ie.getMessage());
            ie.getCause();
        }
        return list;
    }

    public boolean Article(Row row, List<Tarticles> list) throws IOException {
        Tarticles s = new Tarticles();
        boolean error = false;
        int cat = (int) Double.parseDouble(row.getCell(0).toString());
        if (row.getCell(1) != null) {
            if (!row.getCell(1).toString().isEmpty()) {
                s.setCode(row.getCell(1).toString());
            }

        }
        if (row.getCell(2) != null) {
            if (!row.getCell(2).toString().isEmpty()) {
                s.setNom(row.getCell(2).toString());
            }

        }
        if (row.getCell(3) != null) {
            s.setPrix(Double.parseDouble(row.getCell(3).toString()));
        }
        if (row.getCell(4) != null) {
            if (!row.getCell(4).toString().isEmpty()) {
                s.setStock((double) Double.parseDouble(row.getCell(4).toString()));
            }
        }
        if (row.getCell(5) != null) {
            if (!row.getCell(5).toString().isEmpty()) {
                s.setUnite(row.getCell(5).toString());
            }

        }
         if (row.getCell(6) != null) {
            if (!row.getCell(6).toString().isEmpty()) {
                s.setMargeClient(Double.parseDouble(row.getCell(6).toString()));
            }

        }
         if (row.getCell(7) != null) {
            if (!row.getCell(7).toString().isEmpty()) {
                s.setMargeFournisseur(Double.parseDouble(row.getCell(7).toString()));
            }

        }
        try {
            s.setCategorie(tcategorieFacade.findTcategorie(cat));
        } catch (Exception e) {
            error = true;
            System.err.println(e.getMessage());
        }
//        if (row.getCell(6) != null) {
//            if (!row.getCell(6).toString().isEmpty()) {
//                try {
//                    BufferedImage image = ImageIO.read(new File(row.getCell(6).toString()));
//                    String imgstr = encodeToString(image);
//                    s.setPhoto(imgstr);
//                } catch (Exception e) {
//                    error = true;
//                    System.err.println(e.getMessage());
//                }
//            }
//
//        }

        list.add(s);
        return error;
    }

    public List<Tcategorie> lireExcelCategorieArticle(String namefile, String chemin, Societe s) {

        List<Tcategorie> list = new ArrayList<>();

        File excel = new File(chemin + "/" + namefile);

        try (FileInputStream fis = new FileInputStream(excel)) {
            XSSFWorkbook book = new XSSFWorkbook(chemin + "/" + namefile);
            List<XSSFSheet> sheets = new ArrayList<>();
            for (int i = 0; i < book.getNumberOfSheets(); i++) {
                sheets.add(book.getSheetAt(i));
            }
            boolean error = false;
            for (XSSFSheet sheet : sheets) {

                for (Iterator rowIt = sheet.rowIterator(); rowIt.hasNext();) {

                    Row row = (Row) rowIt.next();
                    error = Categorie(row, list, s);
                    if (error) {
                        break;
                    }
                }
                if (error) {
                    list.clear();
                    break;
                }
            }

        } catch (FileNotFoundException fe) {
            System.out.println("error1243 " + fe.getMessage());
            fe.printStackTrace();
        } catch (IOException ie) {
            System.out.println("error5434 " + ie.getMessage());
            ie.getCause();
        }
        return list;
    }

    public boolean Categorie(Row row, List<Tcategorie> list, Societe s) throws IOException {
        Tcategorie c = new Tcategorie();
        // CategorieProduit produit = null;
        boolean error = false;
        c.setSociete(s);
        if (row.getCell(0) != null) {
            if (!row.getCell(0).toString().isEmpty()) {
                c.setNom(row.getCell(0).toString());
            }
        }

        list.add(c);
        return error;
    }

    void traitementCategorieArticle(HttpServletRequest request, String fileName, String uploadPath, Societe s) {

        if (fileName.endsWith(".xlsx") || fileName.endsWith(".xls")) {

            List<Tcategorie> lists = lireExcelCategorieArticle(fileName, uploadPath, s);

            List<Tcategorie> listarticle = tcategorieFacade.findAll(s.getImmatriculation());
            List<Tcategorie> listnewarticle = new ArrayList<>();
            if (!listarticle.isEmpty()) {
                for (Tcategorie article : lists) {
                    for (Tcategorie arti : listarticle) {
                        if (article.getNom().equalsIgnoreCase(arti.getNom())) {
                            listnewarticle.add(arti);
                        }
                    }
                }
            }
            if (!listnewarticle.isEmpty()) {
                for (int i = 0; i < listnewarticle.size(); i++) {
                    lists.remove(i);
                }
            }
            if (!lists.isEmpty()) {
                for (Tcategorie c : lists) {
                    tcategorieFacade.create(c);
                }
                List<Tcategorie> cp = tcategorieFacade.findAll(s.getImmatriculation());
                s.setTcategorieList(cp);
                societeFacade.edit(s);
                request.setAttribute("categories", lists);
                Message message = new Message("Ces categories ont étés enregistrer avec succes");
                message.setTitle("info");
                message.setType("success");
                message.setNotification("Ces categories ont étés enregistrer avec succes");
                request.setAttribute("message", message);
            } else {
                Message message = new Message("Une erreur est apparue lors de la lecture du fichier vérifier le formatage du fichier excel");
                message.setTitle("Alert");
                message.setType("error");
                message.setNotification("Une erreur est apparue lors de la lecture du fichier vérifier le formatage du fichier excel.");
                request.setAttribute("message", message);
            }

            lists.clear();

        } else {
            Message message = new Message("Veiller choisir un fichier Excel");
            message.setTitle("Alert");
            message.setType("info");
            message.setNotification("Veiller choisir un fichier Excel");
            request.setAttribute("message", message);
        }//fin  
    }

    //Clients 
    void traitementClients(HttpServletRequest request, String fileName, String uploadPath, Societe s) {

        if (fileName.endsWith(".xlsx") || fileName.endsWith(".xls")) {

            List<Tclients> lists = lireExcelClients(fileName, uploadPath, s);

            List<Tclients> listclient = tclientsFacade.findAll(s.getImmatriculation());
            List<String> tt = new ArrayList<>();
            if (!listclient.isEmpty()) {
                for (Tclients cli : lists) {
                    for (Tclients arti : listclient) {
                        if (cli.getCodeclient().equalsIgnoreCase(arti.getCodeclient())) {
                            tt.add(cli.getCodeclient());
                        }
                    }
                }
            }
            if (!tt.isEmpty()) {
                for (int i = 0; i < lists.size(); i++) {
                    for (int j = 0; j < tt.size(); j++) {
                        if (tt.get(j).equalsIgnoreCase(lists.get(i).getCodeclient())) {
                            lists.remove(i);
                        }
                    }
                }
            }
            if (!lists.isEmpty()) {
                for (Tclients cli : lists) {
                    try {
                        Tclients cl = tclientsFacade.creer(cli);
                    } catch (Exception ex) {
                        Logger.getLogger(UploadFileArticles.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                List<Tclients> cp = tclientsFacade.findAll(s.getImmatriculation());
                s.setTclientsList(cp);
                societeFacade.edit(s);
                request.setAttribute("clients", lists);
                Message message = new Message("Ces clients ont étés enregistrer avec succes");
                message.setTitle("info");
                message.setType("success");
                message.setNotification("Ces clients ont étés enregistrer avec succes");
                request.setAttribute("message", message);
            } else {
                Message message = new Message("Une erreur est apparue lors de la lecture du fichier vérifier le formatage du fichier excel");
                message.setTitle("Alert");
                message.setType("error");
                message.setNotification("Une erreur est apparue lors de la lecture du fichier vérifier le formatage du fichier excel");
                request.setAttribute("message", message);
            }

            lists.clear();

        } else {
            Message message = new Message("Veiller choisir un fichier Excel");
            message.setTitle("Alert");
            message.setType("info");
            message.setNotification("Veiller choisir un fichier Excel");
            request.setAttribute("message", message);
        }
    }

    public List<Tclients> lireExcelClients(String namefile, String chemin, Societe s) {

        List<Tclients> list = new ArrayList<>();

        File excel = new File(chemin + "/" + namefile);

        try (FileInputStream fis = new FileInputStream(excel)) {
            XSSFWorkbook book = new XSSFWorkbook(chemin + "/" + namefile);
            List<XSSFSheet> sheets = new ArrayList<>();
            for (int i = 0; i < book.getNumberOfSheets(); i++) {
                sheets.add(book.getSheetAt(i));
            }
            boolean error = false;
            for (XSSFSheet sheet : sheets) {

                for (Iterator rowIt = sheet.rowIterator(); rowIt.hasNext();) {

                    Row row = (Row) rowIt.next();
                    error = Clients(row, list, s);
                    if (error) {
                        break;
                    }
                }
                if (error) {
                    list.clear();
                    break;
                }
            }

        } catch (FileNotFoundException fe) {
            System.out.println("error1243 " + fe.getMessage());
            fe.printStackTrace();
        } catch (IOException ie) {
            System.out.println("error5434 " + ie.getMessage());
            ie.getCause();
        }
        return list;
    }

    public boolean Clients(Row row, List<Tclients> list, Societe so) throws IOException {
        // Tclients s = new Tclients();
        boolean error = false;
        Tclients u = new Tclients();
        u.setDatecreation(new Date());
        u.setSociete(so);
        u.setChgpwd(0);
        if (row.getCell(2) != null) {
            if (!row.getCell(2).toString().isEmpty()) {
                u.setCodeclient(row.getCell(2).toString());
            }

        }

        if (row.getCell(9) != null) {
            if (!row.getCell(9).toString().isEmpty()) {
                try {
                    encryptedHash hash = new encryptedHash();
                    u.setPassword(hash.encode(row.getCell(9).toString()));
                    u.setPsd(row.getCell(9).toString());
                } catch (Exception ex) {
                    System.out.println("erreur " + ex.getMessage());
                }
            }

        }

        u.setSecteurid(tsecteursFacade.findTsecteurs((int) Double.parseDouble(row.getCell(0).toString())));
        u.setTypeclientid(ttypeclientsFacade.findTtypeclients((int) Double.parseDouble(row.getCell(1).toString())));
        if (row.getCell(3) != null) {
            if (!row.getCell(3).toString().isEmpty()) {
                u.setNom(row.getCell(3).toString());
            }
        }

        if (row.getCell(4) != null) {
            if (!row.getCell(4).toString().isEmpty()) {
                u.setPrenom(row.getCell(4).toString());
            }

        }

        if (row.getCell(5) != null) {
            if (!row.getCell(5).toString().isEmpty()) {
                u.setTelephone(row.getCell(5).toString());
            }

        }

        if (row.getCell(6) != null) {
            if (!row.getCell(6).toString().isEmpty()) {
                u.setTelephone2(row.getCell(6).toString());
            }

        }

        if (row.getCell(7) != null) {
            if (!row.getCell(7).toString().isEmpty()) {
                u.setMail(row.getCell(7).toString());
            }

        }

        if (row.getCell(8) != null) {
            if (!row.getCell(8).toString().isEmpty()) {
                u.setAdresse(row.getCell(8).toString());
            }

        }

        list.add(u);
        return error;
    }

    String encodeToString(BufferedImage image) {
        String type = "png";
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            ImageIO.write(image, type, bos);
            byte[] imageBytes = bos.toByteArray();

            BASE64Encoder encoder = new BASE64Encoder();
            imageString = encoder.encode(imageBytes);
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageString;
    }
}
