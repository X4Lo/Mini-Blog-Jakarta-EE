package com.blog.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import org.apache.logging.log4j.Level;

public class ConnectionDB {
    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            final String DB_NAME = "blogeDatabase";
            final String DB_URL = "jdbc:mysql://127.0.0.1:3306/" + DB_NAME + "?useSSL=false";
            final String DB_USERNAME = "root";
            final String DB_PASSWORD = "password";

            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);

            Tools.log(Level.INFO, "Connecté au base de données.");
        } catch (Exception e) {
            Tools.log(Level.ERROR, "Impossible de se connecter a la base de données.");
            Tools.log(Level.ERROR, e.getMessage());
        }
    }

    private ConnectionDB() throws InstantiationException {
        throw new InstantiationException("YOU CANNOT INSTANTIATE THIS CLASS, use getInstance()");
    }

    public static Connection getConnection() {
        return connection;
    }
}
