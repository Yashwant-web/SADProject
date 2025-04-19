package mainlibrary;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TransBookDao {

    private static final Logger LOGGER = Logger.getLogger(TransBookDao.class.getName());
    private static final int MAX_BOOKS_PER_USER = 5;

    // Method to check if the book is already issued
    public static boolean CheckIssuedBook(int bookID) {
        String query = "SELECT 1 FROM IssuedBook WHERE BookID = ?";
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, bookID);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // true if the book is issued
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error checking if book is issued. BookID=" + bookID, e);
            return false; // Fail-safe default
        }
    }

    // Method to validate if the book exists in the database
    public static boolean BookValidate(String bookID) {
        String query = "SELECT 1 FROM Books WHERE BookID = ?";
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, bookID);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // true if book exists
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error validating book by ID: " + bookID, e);
            return false;
        }
    }

    // Method to validate if the user exists in the database
    public static boolean UserValidate(String userID) {
        String query = "SELECT 1 FROM Users WHERE UserID = ?";
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, userID);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // true if user exists
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error validating user by ID: " + userID, e);
            return false;
        }
    }

    // Method to issue a book to the user
    public static int IssueBook(int bookID, int userID, String issueDate, String returnDate) {
        int status = 0;
        String query = "INSERT INTO IssuedBook (BookID, UserID, IssueDate, ReturnDate) VALUES (?, ?, ?, ?)";
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, bookID);
            ps.setInt(2, userID);
            ps.setString(3, issueDate);
            ps.setString(4, returnDate);
            status = ps.executeUpdate();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error issuing book. BookID=" + bookID + ", UserID=" + userID, e);
        }
        return status;
    }

    // Method to return a book
    public static int ReturnBook(int bookID, int userID) {
        int status = 0;
        String query = "DELETE FROM IssuedBook WHERE BookID = ? AND UserID = ?";
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, bookID);
            ps.setInt(2, userID);
            status = ps.executeUpdate();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error returning book. BookID=" + bookID + ", UserID=" + userID, e);
        }
        return status;
    }
}
