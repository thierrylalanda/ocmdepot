/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Services;

import Decoder.BASE64Encoder;
import com.eh2s.ocm.Entity.Exceptions.NonexistentEntityException;
import com.eh2s.ocm.Entity.Exceptions.RollbackFailureException;
import com.eh2s.ocm.Entity.Notification;
import com.eh2s.ocm.Entity.Sessions.NotificationFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.StatutIncidentFacadeLocal;
import com.eh2s.ocm.Entity.Sessions.TincidentsFacadeLocal;
import com.eh2s.ocm.Entity.Societe;
import com.eh2s.ocm.Entity.SouscriptionLicence;
import com.eh2s.ocm.Entity.Tclients;
import com.eh2s.ocm.Entity.Tusers;
import com.eh2s.ocm.UtilityFonctions.date_du_jour;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.imageio.ImageIO;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author messi
 */
@Stateless
public class ServicesImpl implements Services {

    @EJB
    private NotificationFacadeLocal notificationFacade;

    @EJB
    private TincidentsFacadeLocal tincidentsFacade;

    @EJB
    private StatutIncidentFacadeLocal statutIncidentFacade;

    @PersistenceContext(unitName = "ccmanagerPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    @Override
    public boolean getConnexion(String userName, String Password) {
        try {
            encryptedHash hash = new encryptedHash();
            Password = hash.encode(Password);
        } catch (Exception ex) {
            System.out.println("erreur " + ex.getMessage());
        }
        Query query = getEntityManager().createNamedQuery("Tusers.findByPassword");
        query.setParameter("user", userName);
        query.setParameter("password", Password);
        return !query.getResultList().isEmpty();
    }

    @Override
    public Tusers getUserConnect(String userName, String Password) {
        try {
            encryptedHash hash = new encryptedHash();
            Password = hash.encode(Password);
        } catch (Exception ex) {
            System.out.println("erreur " + ex.getMessage());
        }
        Query query = getEntityManager().createNamedQuery("Tusers.findByPassword");
        query.setParameter("user", userName);
        query.setParameter("password", Password);
        return (Tusers) query.getSingleResult();
    }

    @Override
    public Tusers getpasswordrecovery(String mail, String tel) {

        Query query = getEntityManager().createNamedQuery("Tusers.findByPhone");
        query.setParameter("mail", mail);
        query.setParameter("tel", tel);
        if (!query.getResultList().isEmpty()) {
            return (Tusers) query.getSingleResult();
        } else {
            return null;
        }
    }

    @Override
    public List<Tclients> getConnectionClient(String code, String password) {
        try {
            encryptedHash hash = new encryptedHash();
            password = hash.encode(password);
        } catch (Exception ex) {
            System.out.println("erreur " + ex.getMessage());
        }
        Query query = getEntityManager().createNamedQuery("Tclients.findByCodeclientAndPassword");
        query.setParameter("codeclient", code);
        query.setParameter("password", password);
        return query.getResultList();
    }

    @Override
    public String SaveImages(HttpServletRequest request, String context, String UPLOAD_DIRECTORY) throws IOException {
        int THRESHOLD_SIZE = 1024 * 1024 * 3;  // 3MB
        int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
        int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB}
        String filePath = "";
        // checks if the request actually contains upload file
        if (!ServletFileUpload.isMultipartContent(request)) {
            System.out.println("Aucun fichier selectionner");
        } else {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(THRESHOLD_SIZE);
            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setFileSizeMax(MAX_FILE_SIZE);
            upload.setSizeMax(MAX_REQUEST_SIZE);

            // constructs the directory path to store upload file
            String fileName = "";
            String uploadPath = context + File.separator + UPLOAD_DIRECTORY;
            // creates the directory if it does not exist
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                System.out.println("fichier inexistant");
                uploadDir.mkdir();
            }
            try {
                List formItems = upload.parseRequest(request);
                Iterator iter = formItems.iterator();

                while (iter.hasNext()) {
                    FileItem item = (FileItem) iter.next();

                    if (!item.isFormField()) {

                        fileName = new File(item.getName()).getName();

                        filePath = uploadPath + File.separator + fileName;
                        File storeFile = new File(filePath);

                        item.write(storeFile);
                    }
                }

            } catch (Exception ex) {
                System.out.println("Aucun fichier selectionner");
                filePath = "";
            }
        }
        return filePath;
    }

    @Override
    public String encodeImageToString(String filePath) throws IOException {
        BufferedImage image = ImageIO.read(new File(filePath));
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
        }
        return imageString;
    }

    @Override
    public List<String> SavePJ(HttpServletRequest request, String UPLOAD_DIR) throws ServletException, IOException {
        List<String> chemin = new ArrayList<>();
        try {

            // Gets absolute path to root directory of web app.
            String appPath = request.getServletContext().getRealPath("");
            // The directory to save uploaded file
            String fullSavePath = null;
            if (appPath.endsWith("/")) {
                fullSavePath = appPath + UPLOAD_DIR;
            } else {
                fullSavePath = appPath + "/" + UPLOAD_DIR;
            }

            // Creates the save directory if it does not exists
            File fileSaveDir = new File(fullSavePath);
            if (!fileSaveDir.exists()) {
                fileSaveDir.mkdir();
            }
            // Part list (multi files).
            for (Part part : request.getParts()) {
                String fileName = extractFileName(part);
                if (fileName != null && fileName.length() > 0) {
                    String filePath = fullSavePath + File.separator + fileName;
                    part.write(filePath);
                    chemin.add(filePath);

                }
            }
        } catch (IOException | ServletException e) {

        }
        return chemin;
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
                String clientFileName = s.substring(s.indexOf("=") + 2, s.length() - 1);
                clientFileName = clientFileName.replace("\\", "/");
                int i = clientFileName.lastIndexOf('/');
                return clientFileName.substring(i + 1);
            }
        }
        return null;
    }

    @Override
    public void ChangerStatutTicketHorsDelais(String societe) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        tincidentsFacade.findTicketHorsDelais(societe).stream().map((t) -> {
            t.setState(statutIncidentFacade.find(404));
            t.setDiffday(date_du_jour.GetDelaisResolution(date_du_jour.dateConvert(df.format(t.getDateHope())), date_du_jour.dateJourUniqueDate()));
            t.setIsInDelais(-1);
            t.setDateFer(date_du_jour.dateJourUniqueDate());
            return t;
        }).forEach((t) -> {
            try {
                tincidentsFacade.MisAJour(t);
            } catch (NonexistentEntityException ex) {
                Logger.getLogger(ServicesImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (RollbackFailureException ex) {
                Logger.getLogger(ServicesImpl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(ServicesImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    @Override
    public void DeleteAllNotificationByLut() {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Query query = getEntityManager().createNamedQuery("Notification.findByLut");
        query.setParameter("lut", 1);
        List<Notification> notifs = query.getResultList();
        notifs.stream().filter((n) -> (date_du_jour.GetDelaisResolution(date_du_jour.dateConvert(df.format(n.getDateNotif())), date_du_jour.dateJourUniqueDate()) >= 2)).forEach((n) -> {
            notificationFacade.remove(n);
        });
    }

    @Override
    public boolean verifiLicenceTest(Societe s) {
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        if (!s.getSouscriptionLicenceList().isEmpty()) {
            if (s.getSouscriptionLicenceList().get(0).getType().getTestMode() == -1) {
                SouscriptionLicence sc = s.getSouscriptionLicenceList().get(0);
               // Date d = date_du_jour.dateConvert(df.format(sc.getDateSous()));
                Date d1 = date_du_jour.dateConvert(df.format(sc.getDateFinTest()));
                return sc.getType().getTestMode() == -1 && date_du_jour.GetIfInDelaisResolution(d1);
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean verifiLicenceMaxUser(Societe s, int count, Message sg) {
        boolean res = false;
        SouscriptionLicence sc = s.getSouscriptionLicenceList().get(0);
        // int o = sc.getType().getInitUser()+sc.getUpUser();
        if ((count < sc.getUpUser() && count < sc.getType().getMaxUser()) || sc.getType().getMaxUser() == -1) {
            res = true;
        } else {
            sg.setMessage("Nombre Maximum de client est atteind pour cet Abonnement");
            sg.setNotification("Nombre Maximum de client est atteind pour cet Abonnement");
            sg.setType("info");
        }
        return res;
    }

}
