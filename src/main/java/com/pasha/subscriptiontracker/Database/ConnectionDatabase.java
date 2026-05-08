package com.pasha.subscriptiontracker.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDatabase {
    private static final String URL = "jdbc:mysql://localhost:3306/subscription_tracker";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection Connect() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to the database");
        } catch (SQLException e) {
            System.out.println("Failed to connect to database, error in ConnectionDatabase");
            e.printStackTrace();
        }
        return con;
    }

}
