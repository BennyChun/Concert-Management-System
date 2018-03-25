package application.database;

import java.sql.*;

public class DatabaseManager {

    private static Connection con;
    private static Statement stmt;

    /**
     * Connects to database and sets field con for database use
     */
    public static void initializeConnection() {
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@dbhost.ugrad.cs.ubc.ca:1522:ug", "ora_t3m0b", "a35437145");
            System.out.println("Connection was successful");

        } catch (SQLException e) {
            System.out.println("Connection to Oracle failed!");
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

    /**
     * Sends insert, update, delete SQL statements to Oracle database and returns rowCount
     * @param updateString      a data manipulation SQL string (e.g., insert, update, delete)
     * @return int rowCount     number of rows for SQL Data Munipulation Statements
     */
    public static int sendUpdate (String updateString) {
        int rowCount = 0;
        try {
            stmt = con.createStatement();
            rowCount = stmt.executeUpdate(updateString);
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowCount;
    }

    /**
     * Sends query SQL statements to Oracle database and returns data in ResultSet
     * @param queryString           a SQL query string (e.g. SELECT * FROM STUDENTS)
     * @return ResultSet results    object containing data from query (need to parse)
     */
    public static ResultSet sendQuery (String queryString) {
        ResultSet results = null;
        try {
            stmt = con.createStatement();
            results = stmt.executeQuery(queryString);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    /**
     * Closes statement to free up memory (caller's job to close statement after parsing a query)
     *
     */
    public static void closeStatement () {
        try {
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
