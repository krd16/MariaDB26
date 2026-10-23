package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SGDBConnection {
    private static final String URL = "jdbc:mariadb://localhost:3006";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";

    public Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
