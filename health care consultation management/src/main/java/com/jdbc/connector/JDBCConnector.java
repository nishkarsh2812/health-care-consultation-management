package com.jdbc.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnector {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/healthcare";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "password";

    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
        } catch (SQLException e) {
            throw new SQLException("Error establishing database connection.", e);
        }
    }
}
