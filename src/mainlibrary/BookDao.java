package mainlibrary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookDao {

    private static final Logger LOGGER = Logger.getLogger(BookDao.class.getName());

    public static int save(String callno, String name, String author, String publisher, int quantity) {
        int status = 0;
        String query = "INSERT INTO books(callno, name, author, publisher, quantity) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, callno);
            ps.setString(2, name);
            ps.setString(3, author);
            ps.setString(4, publisher);
            ps.setInt(5, quantity);
            status = ps.executeUpdate();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error saving book", e);
        }
        return status;
    }

    /**
     *
     * @param Publisher
     * @return
     */
    public static boolean PublisherValidate(String publisher) {
        boolean status = false;
        String query = "SELECT 1 FROM Publisher WHERE PublisherName = ?";
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, publisher);
            try (ResultSet rs = ps.executeQuery()) {
                status = rs.next();
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error validating publisher", e);
        }
        return status;
    }

    public static int AddPublisher(String publisher) {
        int status = 0;
        String query = "INSERT INTO Publisher(PublisherName) VALUES(?)";
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, publisher);
            status = ps.executeUpdate();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error adding publisher", e);
        }
        return status;
    }

    public static int SaveBook(String bookName, String author, String publisher, String shelf, String row, String genre) {
        int status = 0;
        String query = "INSERT INTO Books(BookName, Author, Genre, Publisher, Shelf, Row) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, bookName);
            ps.setString(2, author);
            ps.setString(3, genre);
            ps.setString(4, publisher);
            ps.setString(5, shelf);
            ps.setString(6, row);
            status = ps.executeUpdate();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error saving book details", e);
        }
        return status;
    }

    public static int Delete(int bookID) {
        int status = 0;
        String query = "DELETE FROM Books WHERE BookID = ?";
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, bookID);
            status = ps.executeUpdate();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error deleting book with ID: " + bookID, e);
        }
        return status;
    }
} 