/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.Entity.Services;

import com.eh2s.ocm.Entity.Societe;
import com.eh2s.ocm.Entity.Tclients;
import com.eh2s.ocm.Entity.Tusers;
import java.io.IOException;
import java.util.List;
import javax.ejb.Local;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author messi
 */
@Local
public interface Services {

    boolean getConnexion(String userName, String Password);

    Tusers getUserConnect(String userName, String Password);

    String SaveImages(HttpServletRequest request, String context, String UPLOAD_DIRECTORY) throws IOException;

    List<String> SavePJ(HttpServletRequest request, String UPLOAD_DIR) throws ServletException, IOException;

    String encodeImageToString(String filePath) throws IOException;

    void ChangerStatutTicketHorsDelais(String societe);

    void DeleteAllNotificationByLut();

    Tusers getpasswordrecovery(String mail, String tel);

    List<Tclients> getConnectionClient(String code, String password);

    boolean verifiLicenceTest(Societe s);

    boolean verifiLicenceMaxUser(Societe s, int count, Message sg);
}
