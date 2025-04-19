/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainlibrary;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Yash
 */
public class AllStudent extends javax.swing.JFrame {

    /**
     * Creates new form ViewBook
     *
     * @throws java.sql.SQLException
     */
    public AllStudent() throws SQLException {
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        initComponents();
        populateTableWithAllUsers();
    }

    private void populateTableWithAllUsers() {
        fetchAndDisplayUsers("SELECT UserID, RegDate, UserName, Email FROM Users", null);
    }

    private void fetchAndDisplayUsers(String query, String param) {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);

        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            if (param != null) {
                ps.setString(1, param);
            }

            try (ResultSet rs = ps.executeQuery()) {
                ResultSetMetaData rsmd = rs.getMetaData();
                int colnum = rsmd.getColumnCount();
                String[] row = new String[colnum];

                while (rs.next()) {
                    for (int i = 1; i <= colnum; i++) {
                        row[i - 1] = rs.getString(i);
                    }
                    model.addRow(row);
                }

                if (model.getRowCount() == 0) {
                    model.addRow(new Object[]{"", "NO", "RESULT", "", ""});
                }
            }
        } catch (SQLException e) {
            Logger.getLogger(AllStudent.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    private void initComponents() {

        buttonGroup = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        NameRadio = new javax.swing.JRadioButton();
        SearchField = new javax.swing.JTextField();
        Search = new javax.swing.JButton();
        EmailRadio = new javax.swing.JRadioButton();
        AllRadio = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{"User ID", "RegDate", "UserName", "Email"}
        ) {
            boolean[] canEdit = new boolean[]{false, false, false, false};
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Ubuntu", 0, 24));
        jLabel1.setText("Users");

        jButton1.setText("Close");
        jButton1.addActionListener(evt -> this.dispose());

        buttonGroup.add(NameRadio);
        NameRadio.setText("Name");

        buttonGroup.add(EmailRadio);
        EmailRadio.setText("Email");

        buttonGroup.add(AllRadio);
        AllRadio.setText("ALL");
        AllRadio.addActionListener(evt -> populateTableWithAllUsers());

        Search.setText("Search");
        Search.addActionListener(evt -> performSearch());

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(NameRadio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(EmailRadio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(AllRadio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(SearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Search))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(NameRadio)
                        .addComponent(EmailRadio)
                        .addComponent(AllRadio)
                        .addComponent(SearchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Search)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(20, 20, 20)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(370, 370, 370)
                                                .addComponent(jLabel1))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(370, 370, 370)
                                                .addComponent(jButton1))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(50, 50, 50)
                                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(20, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel1)
                                .addGap(20, 20, 20)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20)
                                .addComponent(jButton1)
                                .addContainerGap(20, Short.MAX_VALUE))
        );

        pack();
    }

    private void performSearch() {
        String input = SearchField.getText().trim();

        if (input.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a search term.", "Input Required", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String likePattern = "%" + input + "%";

        if (NameRadio.isSelected()) {
            fetchAndDisplayUsers("SELECT UserID, RegDate, UserName, Email FROM Users WHERE UserName LIKE ?", likePattern);
        } else if (EmailRadio.isSelected()) {
            fetchAndDisplayUsers("SELECT UserID, RegDate, UserName, Email FROM Users WHERE Email LIKE ?", likePattern);
        } else {
            JOptionPane.showMessageDialog(this, "Select either Name or Email as a search criterion.", "No Selection", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new AllStudent().setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(AllStudent.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JRadioButton AllRadio;
    private javax.swing.JRadioButton EmailRadio;
    private javax.swing.JRadioButton NameRadio;
    private javax.swing.JButton Search;
    private javax.swing.JTextField SearchField;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

}

