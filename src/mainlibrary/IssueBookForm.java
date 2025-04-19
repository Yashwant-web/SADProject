/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainlibrary;

import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 *
 * @author bikash
 */
public class IssueBookForm extends javax.swing.JFrame {

    /**
     * Creates new form IssueBookForm
     */
    public IssueBookForm() {
        initComponents(); // Initialize GUI components
        int year;
        Calendar cal = Calendar.getInstance();
        year = cal.get(Calendar.YEAR);
        String TodayYear;
        TodayYear = String.valueOf(year);
        IYear.setText(TodayYear);
        String TodayMonth = String.valueOf(cal.get(Calendar.MONTH) + 1);
        IMonth.setText(TodayMonth);
        String TodayDate = String.valueOf(cal.get(Calendar.DATE));
        IDate.setText(TodayDate);

        Calendar addcal = null;
        addcal = cal;
        addcal.add(Calendar.DAY_OF_YEAR, 15);

        RDate.setText(String.valueOf(addcal.get(Calendar.DATE)));
        RMonth.setText(String.valueOf(addcal.get(Calendar.MONTH) + 1));
        RYear.setText(String.valueOf(addcal.get(Calendar.YEAR)));
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {
        // Issue Book Button action
        int BookIDV = Integer.parseInt(BookID.getText());
        int UserIDV = Integer.parseInt(UserID.getText());

        String IFDate = IYear.getText() + "-" + IMonth.getText() + "-" + IDate.getText();
        String RFDate = RYear.getText() + "-" + RMonth.getText() + "-" + RDate.getText();

        if (TransBookDao.BookValidate(BookID.getText()) && TransBookDao.UserValidate(UserID.getText())) {
            if (TransBookDao.CheckIssuedBook(BookIDV)) {
                JOptionPane.showMessageDialog(IssueBookForm.this, "Book is already issued", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                if (TransBookDao.IssueBook(BookIDV, UserIDV, IFDate, RFDate) != 0) {
                    JOptionPane.showMessageDialog(IssueBookForm.this, "Book is issued successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    BookID.setText("");
                    UserID.setText("");
                } else {
                    JOptionPane.showMessageDialog(IssueBookForm.this, "Unable to issue the book", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            if (TransBookDao.UserValidate(UserID.getText())) {
                JOptionPane.showMessageDialog(IssueBookForm.this, "The Book is not available in the database", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (TransBookDao.BookValidate(BookID.getText())) {
                JOptionPane.showMessageDialog(IssueBookForm.this, "The User is not available in the database", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(IssueBookForm.this, "The Book and User are not available in the database", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {
        // Back Button action
        this.dispose();
        LibrarianSuccess.ThisLogined.setVisible(true);
    }

    private void UserIDActionPerformed(java.awt.event.ActionEvent evt) {
        // Handle UserID field action
    }

    private void BookIDActionPerformed(java.awt.event.ActionEvent evt) {
        // Handle BookID field action
    }



    private void RYearActionPerformed(java.awt.event.ActionEvent evt) {
        // Handle Return Year field action
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IssueBookForm().setVisible(true);
            }
        });
    }

    // Declare components as needed
    private javax.swing.JTextField BookID; // Declare BookID as a class-level variable
    private javax.swing.JTextField UserID; // Declare UserID as a class-level variable
    private javax.swing.JTextField IYear;  // Declare IYear as a class-level variable
    private javax.swing.JTextField IMonth; // Declare IMonth as a class-level variable
    private javax.swing.JTextField IDate;  // Declare IDate as a class-level variable
    private javax.swing.JTextField RYear;  // Declare RYear as a class-level variable
    private javax.swing.JTextField RMonth; // Declare RMonth as a class-level variable
    private javax.swing.JTextField RDate;  // Declare RDate as a class-level variable

    private void initComponents() {
        // Add initialization code for GUI components here
        BookID = new javax.swing.JTextField();
        UserID = new javax.swing.JTextField();
        UserID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                UserIDActionPerformed(evt);
            }
        });

        BookID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BookIDActionPerformed(evt);
            }
        });
        // Add other components and layout setup as required

        //Add an "Issue Book" button and linking its action listener
        javax.swing.JButton issueBookButton = new javax.swing.JButton("Issue Book");
        issueBookButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        // Add issueBookButton to the layout as needed
        IYear = new javax.swing.JTextField();
        IMonth = new javax.swing.JTextField();
        IDate = new javax.swing.JTextField();
        RYear = new javax.swing.JTextField();
        RYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RYearActionPerformed(evt);
            }
        });
        RMonth = new javax.swing.JTextField();
        RDate = new javax.swing.JTextField();
        IMonth = new javax.swing.JTextField();
        IDate = new javax.swing.JTextField();
        RYear = new javax.swing.JTextField();
        RMonth = new javax.swing.JTextField();
        RDate = new javax.swing.JTextField();
        // Add other components and layout setup as required

        // Add "Back" button and linking its action listener
        javax.swing.JButton backButton = new javax.swing.JButton("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
       
    }
}
