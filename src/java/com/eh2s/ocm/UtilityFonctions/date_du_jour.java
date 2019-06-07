package com.eh2s.ocm.UtilityFonctions;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class date_du_jour {

    public date_du_jour() {
    }

    public static Timestamp dateJour() {     
        return new Timestamp(new java.util.Date().getTime());
    }

    public static Timestamp caseDateToTimestamp(Date d) {
        Timestamp d1 = new Timestamp(d.getTime());
        return d1;
    }

    public static Date dateJourUniqueDate() {
        java.util.Date date = new java.util.Date();
        return new Date(date.getTime());
    }

    public static Date dateJourUniqueDate(Date d1) {     
        return new Date(d1.getTime());
    }

    public static Date TicketDelaisResolution(int jrs) {
        Date d = null;
        try {
            DataSource ds = DataSource.getInstace();
            Connection c = DataSource.getConnection();
            Statement s = c.createStatement();
            String sql = "SELECT ADDDATE(DATE(now()), INTERVAL '" + jrs + "' DAY) AS date_interval";
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                d = rs.getDate("date_interval");
            }

        } catch (SQLException ex) {
            Logger.getLogger(date_du_jour.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d;
    }

    public static int GetDelaisResolution(Date date1, Date date) {
        int d = 0;
        try {
            DataSource ds = DataSource.getInstace();
            Connection c = DataSource.getConnection();
            Statement s = c.createStatement();
            String sql = "SELECT DATEDIFF('" + date + "','" + date1 + "') AS date_interval";
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                d = rs.getInt("date_interval");
            }

        } catch (SQLException ex) {
            Logger.getLogger(date_du_jour.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d;
    }

    public static boolean GetIfInDelaisResolution(java.util.Date date) {
        long d = 0;
        SimpleDateFormat myFormat = new SimpleDateFormat("dd-MM-yyyy");
        String inputString1 = myFormat.format(new java.util.Date());
        String inputString2 = myFormat.format(date);
        try {
            java.util.Date date1 = myFormat.parse(inputString1);
            java.util.Date date2 = myFormat.parse(inputString2);
            long diff = date2.getTime() - date1.getTime();
            d = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        } catch (ParseException ex) {
            Logger.getLogger(date_du_jour.class.getName()).log(Level.SEVERE, null, ex);
        }

        return d <= 0;
    }

    public static int GetDelaisResolutionDay(Date date1, Date date) {
        int d = 0;
        try {
            DataSource ds = DataSource.getInstace();
            Connection c = DataSource.getConnection();
            Statement s = c.createStatement();
            String sql = "SELECT DATEDIFF('" + date + "','" + date1 + "') AS date_interval";
            ResultSet rs = s.executeQuery(sql);
            if (rs.next()) {
                d = rs.getInt("date_interval");
            }

        } catch (SQLException ex) {
            Logger.getLogger(date_du_jour.class.getName()).log(Level.SEVERE, null, ex);
        }

        return d;
    }

   

    public static java.util.Date GetTimeZone() {
        java.util.Date date = null;
        SimpleDateFormat dateFormatGmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        dateFormatGmt.setTimeZone(TimeZone.getTimeZone("Africa/Douala"));
        SimpleDateFormat dateFormatLocal = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        try {
            date = dateFormatLocal.parse(dateFormatGmt.format(new java.util.Date()));
        } catch (ParseException ex) {
            Logger.getLogger(date_du_jour.class.getName()).log(Level.SEVERE, null, ex);
        }
        return date;
    }

    public static Date dateConvert(String date) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        java.util.Date parsed = null;
        try {
            parsed = format.parse(date);
        } catch (ParseException ex) {
            Logger.getLogger(date_du_jour.class.getName()).log(Level.SEVERE, null, ex);
        }
        java.sql.Date sql = new java.sql.Date(parsed.getTime());
        return sql;
    }

    public static int getMonth(java.util.Date d) {      
        LocalDate localDate = d.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.getMonthValue();
    }

    public static int getYEAR() {
        
        java.util.Date date = new java.util.Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.getYear();
    }

    public static List<Integer> getClientCmommande(Date d, Date d1, int societe, boolean isperiode, int etat) {
        List<Integer> l1 = new ArrayList<>();
        try {
            DataSource ds = DataSource.getInstace();
            Connection c = DataSource.getConnection();
            Statement s = c.createStatement();
            String sql;
            if (isperiode) {
                sql = "SELECT DISTINCT tlignecommande.client AS idcli FROM tlignecommande,tclients WHERE tlignecommande.client = tclients.id "
                        + "AND tlignecommande.societe = '" + societe + "'AND DATE(tlignecommande.datelivraison) >= '" + d + "' AND DATE(tlignecommande.datelivraison) <= '" + d1 + "'"
                        + " AND tlignecommande.etatc = '" + etat + "'";
            } else {
                sql = "SELECT DISTINCT tlignecommande.client AS idcli FROM tlignecommande,tclients WHERE tlignecommande.client = tclients.id "
                        + "AND tlignecommande.societe = '" + societe + "' AND tlignecommande.etatc = '" + etat + "'";
            }

            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                l1.add(rs.getInt("idcli"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(date_du_jour.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l1;
    }

    public static List<Integer> getTournerCmommande(Date d, Date d1, int societe, boolean isperiode, int etat) {
        List<Integer> l1 = new ArrayList<>();
        try {
            DataSource ds = DataSource.getInstace();
            Connection c = DataSource.getConnection();
            Statement s = c.createStatement();
            String sql;
            if (isperiode) {
                sql = "SELECT DISTINCT tlignecommande.tourner AS idcli FROM tlignecommande,tourner WHERE tlignecommande.tourner = tourner.id "
                        + "AND tlignecommande.societe = '" + societe + "'AND tlignecommande.datelivraison >= '" + d + "' AND tlignecommande.datelivraison <= '" + d1 + "'"
                        + " AND tlignecommande.etatc = '" + etat + "'";
            } else {
                sql = "SELECT DISTINCT tlignecommande.tourner AS idcli FROM tlignecommande,tourner WHERE tlignecommande.tourner = tourner.id "
                        + "AND tlignecommande.societe = '" + societe + "' AND tlignecommande.etatc = '" + etat + "'";
            }

            ResultSet rs = s.executeQuery(sql);
            while (rs.next()) {
                l1.add(rs.getInt("idcli"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(date_du_jour.class.getName()).log(Level.SEVERE, null, ex);
        }
        return l1;
    }
}
