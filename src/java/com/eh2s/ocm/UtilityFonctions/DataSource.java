package com.eh2s.ocm.UtilityFonctions;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
//ocmproduction P@ssw0rd2018 root
    private static DataSource ds = null;
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/depotformation?useUnicode=true";
    private static final String USER = "root";
    private static final String PASS = "P@ssw0rd2018";

    private DataSource() {

    }

    public static DataSource getInstace() {
        if (ds == null) {
            ds = new DataSource();
        }
        return ds;
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);

        } catch (ClassNotFoundException | SQLException e) {

            System.out.println(e.getMessage());
            e.fillInStackTrace();
        }
        return conn;
    }
}
