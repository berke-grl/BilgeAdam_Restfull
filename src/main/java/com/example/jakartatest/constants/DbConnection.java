package com.example.jakartatest.constants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/OBS";
    private static final String USER_NAME = "postgres";
    private static final String PASSWORD = "root";

    public static Connection connect() throws SQLException {
        try {
            //Bu satır yazılmazsa war dosyma benin postresql dependencyimi görmüyor
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return DriverManager.getConnection(URL, USER_NAME, PASSWORD);
    }
}
