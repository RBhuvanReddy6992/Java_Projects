package com.example.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtilR {
    // UPDATE THESE WITH YOUR ACTUAL DATABASE CREDENTIALS
    private static final String URL = "jdbc:mysql://localhost:3306/vottingdb";
    private static final String USER = "root";
    private static final String PASS = "root";

    public static Connection getConnection() {
        Connection con = null;
        try {
            // Load driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Database connection successful!");
        } catch (Exception e) {
            System.err.println("Database connection failed!");
            e.printStackTrace();
        }
        return con;
    }
}