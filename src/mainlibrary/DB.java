/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainlibrary;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 *
 * @author bikash
 */
public class DB {

    private static Properties properties = new Properties();

    static {
        try (FileInputStream input = new FileInputStream("config.properties")) {
            properties.load(input);  // Load configuration from file
        } catch (IOException ex) {
            ex.printStackTrace();  // Handle potential exceptions (could log to a file)
        }
    }

    public static Connection getConnection() {
        try {
            String user = properties.getProperty("db.user");
            String password = properties.getProperty("db.password");
            String url = properties.getProperty("db.url");

            // Load the MySQL JDBC driver
            Class.forName("com.mysql.jdbc.Driver");

            // Return the connection
            return DriverManager.getConnection(url, user, password);

        } catch (Exception e) {
            e.printStackTrace();  // Log any exceptions that occur
        }
        return null;  // Return null if the connection fails
    }
}