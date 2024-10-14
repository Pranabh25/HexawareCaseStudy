package com.hexaware.vag.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/VAG"; 
    private static final String USERNAME = "root"; 
    private static final String PASSWORD = "2024"; 

    private static Connection connection = null;

    public static Connection getConnection() throws ClassNotFoundException {
        if (connection == null) {
            try {
                //Class.forName("com.mysql.cj.jdbc.Driver"); // for loading driver which now done automatically
                connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                System.out.println("Connected to the database.");
            } catch (SQLException e) {
                System.out.println("Error while connecting to the database: " + e.getMessage());
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                System.out.println("Error while closing the connection: " + e.getMessage());
            }
        }
    }
}


