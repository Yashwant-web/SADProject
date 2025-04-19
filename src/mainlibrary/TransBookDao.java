package mainlibrary;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TransBookDao {

    private static final Logger LOGGER = Logger.getLogger(TransBookDao.class.getName());
    private static final int MAX_BOOKS_PER_USER = 5;

    public static boolean isBookExistsById(String bookId) {
        try (Connection con = DB.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT 1 FROM Books WHERE BookID = ?");
            ps.setString(1, bookId);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error validating book by ID: " + bookId, e);
        }
        return false;
    }

    public static boolean isUserExists(String userId) {
        try (Connection con = DB.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT 1 FROM Users WHERE UserID = ?");
            ps.setString(1, userId);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error validating user by ID: " + userId, e);
        }
        return false;
    }

    public static int updateBookInventory(String callNo) {
        int status = 0;
        try (Connection con = DB.getConnection()) {
            con.setAutoCommit(false);

            PreparedStatement ps = con.prepareStatement("SELECT quantity, issued FROM books WHERE callno = ? FOR UPDATE");
            ps.setString(1, callNo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int quantity = rs.getInt("quantity");
                int issued = rs.getInt("issued");

                if (quantity > 0) {
                    PreparedStatement ps2 = con.prepareStatement("UPDATE books SET quantity = ?, issued = ? WHERE callno = ?");
                    ps2.setInt(1, quantity - 1);
                    ps2.setInt(2, issued + 1);
                    ps2.setString(3, callNo);
                    status = ps2.executeUpdate();
                    con.commit();
                } else {
                    LOGGER.log(Level.WARNING, "Book not available for callNo: " + callNo);
                }
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error updating book inventory for callNo: " + callNo, e);
        }
        return status;
    }

    public static int issueBook(int bookId, int userId, String issueDate, String returnDate) {
        if (!isBookExistsById(String.valueOf(bookId))) return -1;
        if (!isUserExists(String.valueOf(userId))) return -2;
        if (isBookAlreadyIssued(bookId)) return -3;
        if (!canUserBorrowMoreBooks(userId)) return -4;

        int status = 0;
        try (Connection con = DB.getConnection()) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO IssuedBook (BookID, UserID, IssueDate, ReturnDate) VALUES (?, ?, ?, ?)");
            ps.setInt(1, bookId);
            ps.setInt(2, userId);
            ps.setString(3, issueDate);
            ps.setString(4, returnDate);
            status = ps.executeUpdate();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error issuing book. BookID=" + bookId + ", UserID=" + userId, e);
        }
        return status;
    }

    public static int returnBook(int bookId, int userId) {
        int status = 0;
        try (Connection con = DB.getConnection()) {
            PreparedStatement ps = con.prepareStatement("DELETE FROM IssuedBook WHERE BookID = ? AND UserID = ?");
            ps.setInt(1, bookId);
            ps.setInt(2, userId);
            status = ps.executeUpdate();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error returning book. BookID=" + bookId + ", UserID=" + userId, e);
        }
        return status;
    }

    public static boolean isBookAlreadyIssued(int bookId) {
        try (Connection con = DB.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT 1 FROM IssuedBook WHERE BookID = ?");
            ps.setInt(1, bookId);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error checking issued status for BookID=" + bookId, e);
        }
        return false;
    }

    public static boolean canUserBorrowMoreBooks(int userId) {
        try (Connection con = DB.getConnection()) {
            PreparedStatement ps = con.prepareStatement("SELECT BookNo FROM Book_Count WHERE UserID = ?");
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int num = rs.getInt("BookNo");
                return num < MAX_BOOKS_PER_USER;
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error checking book limit for UserID=" + userId, e);
        }
        return false;
    }

    // Legacy wrapper for backward compatibility
    public static boolean checkBook(String bookcallno) {
        return isBookExistsById(bookcallno);
    }
} 
