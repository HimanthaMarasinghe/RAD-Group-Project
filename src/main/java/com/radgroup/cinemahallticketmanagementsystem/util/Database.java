package com.radgroup.cinemahallticketmanagementsystem.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "";
        String database = "cinema";
        return DriverManager.getConnection(url + database, user, password);
    }
}