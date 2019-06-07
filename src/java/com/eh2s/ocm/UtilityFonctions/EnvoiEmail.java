/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eh2s.ocm.UtilityFonctions;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author messi
 */
@Stateless
public class EnvoiEmail {

    public static void sendMail(String To, String subject, String messages) throws MessagingException {
//eh2s.sarl@gmail.com P@ssw0rd2000
        final String serveur = "smtp.gmail.com";
        final String username = "infoocmcloud@gmail.com";
        final String password = "eh2s2019";
        final int port = 587;
        Properties prop = System.getProperties();
        prop.setProperty("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.host", serveur);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.port", port);
        prop.put("mail-smtp-socketFactory-class", "javax.net.ssl.SSLSocketFactory");
        prop.put("mail-smtp-socketFactory-port", port);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail-smtp-socketFactory-fallback ", "false");

        Session session = Session.getDefaultInstance(prop, null);
        session.setDebug(false);

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(To));
        message.setSubject(subject);
        message.setContent(messages, "text/html; charset=UTF-8");
        message.setSentDate(new Date());
        message.saveChanges();

        try {
            Transport transport = session.getTransport("smtp");
            transport.connect(serveur, username, password);
            transport.sendMessage(message, message.getAllRecipients());
        } catch (Exception e) {
            System.out.println("message echec");
        }
    }

    public static void main(String[] args) {
        String inputString1 = "18-03-2019";
        String inputString2 = "20-03-2019";

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String dat = df.format(new Date());
        System.out.println(Integer.parseInt(dat.split("/")[1]));
    }
}
