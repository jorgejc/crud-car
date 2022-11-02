package com.iudigital.crudcar.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

    private static final String URL = "jdbc:postgresql://localhost:5432/transito-car";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123456";
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
