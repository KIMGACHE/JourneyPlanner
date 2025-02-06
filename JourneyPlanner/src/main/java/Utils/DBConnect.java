package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
    private static final String URL = "jdbc:mysql://localhost:3306/plandb";
    private static final String id = "root";
    private static final String pw = "1234";
    
    // Singleton instance
    private static DBConnect instance;
    private Connection connection;

    // Private constructor to prevent instantiation
    private DBConnect() throws SQLException {
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish the connection
            this.connection = DriverManager.getConnection(URL, id, pw);
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
        }
    }

    // Get the singleton instance
    public static DBConnect getInstance() throws SQLException {
        if (instance == null || instance.getConnection().isClosed()) {
            instance = new DBConnect();
        }
        return instance;
    }

    // Get the connection
    public Connection getConnection() {
        return connection;
    }
}
