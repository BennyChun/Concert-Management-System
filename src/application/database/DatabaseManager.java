package application.database;

import java.sql.*;

public class DatabaseManager {

    private static Connection con;

    /**
     * Connects to database and sets field con for database use
     */
    public static void initializeConnection() {
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@dbhost.ugrad.cs.ubc.ca:1522:ug", "ora_t3m0b", "a35437145");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes connection to database
     */
    public static void closeConnection() {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
