package bank.management.system;

import java.sql.*;

// Class to manage database connection
public class Conn {
    Connection conn;
    Statement s;

    // Constructor to establish connection
    public Conn() {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish connection to the database
            // Replace "jdbc:mysql:///bankmanagementsystem" with the appropriate JDBC URL
            // Replace "root" with the username and "universo7" with the password
            conn = DriverManager.getConnection("jdbc:mysql:///bankmanagementsystem", "root", "universo7");

            // Create a statement object
            s = conn.createStatement();

        } catch (Exception e) {
            // Print any exceptions that occur during connection establishment
            System.out.println(e);
        }
    }
}
