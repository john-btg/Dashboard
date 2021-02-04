package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class JdbcLogin {
    public String Login;
    public String MotDePasse;
    private boolean Logged = false;
    private Connection conn;

    public Connection run() {
        // Votre fonction Run
        // Vos information de connexion à une base de données
        String BDD = "dashboard";
        String url = "jdbc:mysql://localhost:3306/" + BDD;
        String user = "root";
        String passwd = "";
        // L'essaie de connexion à votre base de donées

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.conn = DriverManager.getConnection(url, user, passwd);
            Logged = true;
            System.out.println("Connecté");
            return this.conn;
        } catch (Exception e) {
            e.printStackTrace();
            Logged = false;
            System.out.println("Erreur");
            System.exit(0);
            return null;

        }

    }
    public void close(){
        try {
            this.conn.close();
            System.out.println("Déconnecté");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}

