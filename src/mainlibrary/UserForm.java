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
 * @author Yash
 */
public class UserForm extends javax.swing.JFrame {

    /**
     * Creates new form BookForm
     */
    public UserForm() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        // Generated code for UI components...

        // Add action listener for jButton1
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        // Add action listener for jButton2
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
    }// </editor-fold>

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Get user input from the text fields
        String User = UserName.getText().trim();
        String UserPass = String.valueOf(Password.getPassword()).trim();
        String UserEmail = Email.getText().trim();

        // Validate input to prevent empty or invalid values
        if (User.isEmpty() || UserPass.isEmpty() || UserEmail.isEmpty()) {
            JOptionPane.showMessageDialog(UserForm.this, "All fields must be filled!", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check if the username already exists
        if (UsersDao.CheckIfAlready(User)) {
            JOptionPane.showMessageDialog(UserForm.this, "UserName already taken!", "Adding new User Error!", JOptionPane.ERROR_MESSAGE);
        } else {
            // Get current date for user creation
            Calendar cal = Calendar.getInstance();
            String Date;
            String RDate = String.valueOf(cal.get(Calendar.DATE));
            String RMonth = String.valueOf(cal.get(Calendar.MONTH) + 1);  // Adding 1 to month as Calendar.MONTH is zero-based
            String RYear = String.valueOf(cal.get(Calendar.YEAR));
            Date = RYear + "-" + RMonth + "-" + RDate;

            // Add the user to the database
            if (UsersDao.AddUser(User, UserPass, UserEmail, Date) != 0) {
                JOptionPane.showMessageDialog(UserForm.this, "User is Added Successfully!", "Adding New User!", JOptionPane.INFORMATION_MESSAGE);
                // Clear input fields
                UserName.setText("");
                Password.setText("");
                Email.setText("");
                Position.setText("");
                Program.setText("");
                Year.setText("");
            } else {
                JOptionPane.showMessageDialog(UserForm.this, "Unable to add new User", "Adding new User Error!", JOptionPane.ERROR_MESSAGE);
            }

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // Close the current window and open the main screen
        this.dispose();
        LibrarianSuccess.ThisLogined.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    // Other event handlers for text fields...

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        // Set the Nimbus look and feel
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(UserForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new UserForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JTextField Email;
    private javax.swing.JPasswordField Password;
    private javax.swing.JTextField Position;
    private javax.swing.JTextField Program;
    private javax.swing.JTextField UserName;
    private javax.swing.JTextField Year;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    // End of variables declaration
}
