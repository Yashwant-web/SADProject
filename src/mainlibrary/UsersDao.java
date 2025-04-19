/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainlibrary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author Yash
 */
public class UsersDao {

    // Validate user credentials using prepared statements to prevent SQL injection
    public static boolean validate(String name, String password) {
        boolean status = false;
        try {
            // Get database connection
            Connection con = DB.getConnection();

            // Use a prepared statement to safely query the database
            String query = "SELECT * FROM Users WHERE UserName = ? AND UserPass = ?";
            PreparedStatement ps = con.prepareStatement(query);
            
            // Set the parameters for the prepared statement
            ps.setString(1, name);
            ps.setString(2, password);
            
            // Execute the query
            ResultSet rs = ps.executeQuery();
            
            // If the query returns a result, login is successful
            status = rs.next();
            con.close();
        } catch (Exception e) {
           // Log the error to System.err instead of printing stack trace
           System.err.println("Error validating user credentials: " + e.getMessage());
        }
        return status;
    }

    // Check if a user with the given username already exists using prepared statements
    public static boolean CheckIfAlready(String UserName) {
        boolean status = false;
        try {
            // Get database connection
            Connection con = DB.getConnection();

            // Use a prepared statement to safely query the database
            String query = "SELECT * FROM Users WHERE UserName = ?";
            PreparedStatement ps = con.prepareStatement(query);
            
            // Set the parameter for the prepared statement
            ps.setString(1, UserName);
            
            // Execute the query
            ResultSet rs = ps.executeQuery();
            
            // If the query returns a result, the username already exists
            status = rs.next();
            con.close();
        } catch (Exception e) {
            // Log the error to System.err instead of printing stack trace
            System.err.println("Error checking if username already exists: " + e.getMessage());
        }
            return status;
        
    }

    // Add a new user to the database using prepared statements
    public static int AddUser(String User, String UserPass, String UserEmail, String Date) {
        int status = 0;
        try {
            // Get database connection
            Connection con = DB.getConnection();

            // SQL query to insert a new user
            String query = "INSERT INTO Users (UserPass, RegDate, UserName, Email) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);

            // Set the parameters for the prepared statement
            ps.setString(1, UserPass);
            ps.setString(2, Date);
            ps.setString(3, User);
            ps.setString(4, UserEmail);

            // Execute the query (insert the user)
            status = ps.executeUpdate();
            con.close();
        } catch (Exception e) {
            // Log the error to System.err instead of printing stack trace
            System.err.println("Error adding user: " + e.getMessage());
        }
        return status;
   
    }

}
