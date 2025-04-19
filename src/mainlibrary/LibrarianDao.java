package mainlibrary;

import java.sql.*;

public class LibrarianDao {

    // Save new librarian details to the database
    public static int save(String name, String password, String email, String address, String city, String contact) {
        int status = 0;
        try {
            // Get database connection
            Connection con = DB.getConnection();

            // SQL query to insert librarian details into the database
            String query = "INSERT INTO librarian (name, password, email, address, city, contact) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);

            // Set the parameters for the prepared statement
            ps.setString(1, name);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.setString(4, address);
            ps.setString(5, city);
            ps.setString(6, contact);

            // Execute the update (insert) query
            status = ps.executeUpdate();

            // Close the connection
            con.close();
        } catch (Exception e) {
            // Instead of printing stack trace, log the exception message
            System.err.println("Error saving librarian details: " + e.getMessage());
        }
        return status;
    }

    // Delete librarian by ID from the database
    public static int delete(int id) {
        int status = 0;
        try {
            // Get database connection
            Connection con = DB.getConnection();

            // SQL query to delete librarian by ID
            String query = "DELETE FROM librarian WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(query);

            // Set the ID parameter
            ps.setInt(1, id);

            // Execute the delete query
            status = ps.executeUpdate();

            // Close the connection
            con.close();
        } catch (Exception e) {
            // Instead of printing stack trace, log the exception message
            System.err.println("Error deleting librarian: " + e.getMessage());
        }
        return status;
    }

    // Validate librarian's username and password
    public static boolean validate(String name, String password) {
        boolean status = false;
        try {
            // Get database connection
            Connection con = DB.getConnection();

            // SQL query to validate librarian's credentials
            String query = "SELECT * FROM librarian WHERE UserName = ? AND Password = ?";
            PreparedStatement ps = con.prepareStatement(query);

            // Set the parameters for the prepared statement
            ps.setString(1, name);
            ps.setString(2, password);

            // Execute the query
            ResultSet rs = ps.executeQuery();

            // If the query returns a result, the login is successful
            status = rs.next();

            // Close the connection
            con.close();
        } catch (Exception e) {
            // Instead of printing stack trace, log the exception message
            System.err.println("Error validating librarian credentials: " + e.getMessage());
        }
        return status;
    }

}
