package com.example.demo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.awt.EventQueue;
import java.sql.*;
import java.util.Collection;

public class JdbcLogin {


    public JdbcLogin()
    {

    }

    public void run() {
        // Votre fonction Run
        // Vos information de connexion à une base de données
        String BDD = "dash_board";
        String url = "jdbc:mysql://localhost:3306/" + BDD;
        String user = "root";
        String passwd = "";
        // L'essaie de connexion à votre base de donées

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, passwd);
            var payload = 0;
            System.out.println("Connecté");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur");
            System.exit(0);
        }

    }
}
