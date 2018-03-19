package application.database;

import java.sql.*;
import oracle.jdbc.*;
import oracle.sql.*;

public class DatabaseManager {

    private static Connection con;

    public static void initializeConnection() {
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@dbhost.ugrad.cs.ubc.ca:1522:ug", "ora_t3m0b", "a35437145");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeConnection() {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
