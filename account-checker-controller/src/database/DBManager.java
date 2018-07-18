package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DBManager {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/antiban_tribot";

    private static final String DATABASE_USERNAME = "antiban";

    private static final String DATABASE_PASSWORD = "4Fm^4?FmZ&!!-jS^FeSm";

    private static Connection db_connection;

    /**
     * Establishes a connection with the specified database.
     */
    public static void establishConnection() {
        try {
            db_connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the database connection.
     *
     * @return The database connection.
     * */
    public static Connection getDBConnection() {
        return db_connection;
    }

}
