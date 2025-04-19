package mainlibrary;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.logging.Logger;
import java.util.logging.Level;

public class ViewBook extends javax.swing.JFrame {

    public ViewBook() throws SQLException {
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        initComponents();
        loadData();
    }

    // This method loads data into the table when the frame is created
    private void loadData() throws SQLException {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM Books", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
             ResultSet rs = ps.executeQuery()) {
            
            ResultSetMetaData rsmd = rs.getMetaData();
            int colnum = rsmd.getColumnCount();
            String[] row = new String[colnum];
            
            while (rs.next()) {
                for (int i = 1; i <= colnum; i++) {
                    row[i - 1] = rs.getString(i);
                }
                model.addRow(row);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup3 = new javax.swing.ButtonGroup();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        NameRadio = new javax.swing.JRadioButton();
        SearchField = new javax.swing.JTextField();
        Search = new javax.swing.JButton();
        AuthorRadio = new javax.swing.JRadioButton();
        ALL = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Book ID", "Name", "Genre", "Author", "Publisher", "Shelf", "Row"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Ubuntu", 0, 24)); // NOI18N
        jLabel1.setText("Books");

        jButton1.setText("Close");
        jButton1.addActionListener(evt -> this.dispose());

        NameRadio.setText("Name");
        NameRadio.addActionListener(evt -> toggleSearchCriteria(NameRadio));

        SearchField.addActionListener(evt -> searchBooks());

        Search.setText("Search");
        Search.addActionListener(evt -> searchBooks());

        AuthorRadio.setText("Author");
        AuthorRadio.addActionListener(evt -> toggleSearchCriteria(AuthorRadio));

        ALL.setText("ALL");
        ALL.addActionListener(evt -> searchBooks());

        // Layout setup and event handling code
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(958, 958, 958)
                            .addComponent(SearchField, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(Search))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGap(524, 524, 524)
                            .addComponent(NameRadio)
                            .addGap(42, 42, 42)
                            .addComponent(AuthorRadio)
                            .addGap(359, 359, 359)
                            .addComponent(ALL)))
                    .addContainerGap(843, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(NameRadio)
                        .addComponent(AuthorRadio)
                        .addComponent(ALL))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(SearchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Search))
                    .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(83, 83, 83))
                .addGroup(layout.createSequentialGroup()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(698, 698, 698)
                            .addComponent(jButton1))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(25, 25, 25)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1464, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(673, 673, 673)
                            .addComponent(jLabel1)))
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(28, 28, 28)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jButton1)
                    .addContainerGap(49, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void SearchFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchFieldActionPerformed
        searchBooks();
    }//GEN-LAST:event_SearchFieldActionPerformed

    private void searchBooks() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Clear existing rows

        String searchText = SearchField.getText().trim();
        if (searchText.isEmpty()) {
            try {
                loadData(); // Load all books if no search text is provided
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error loading data", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            if (NameRadio.isSelected()) {
                searchByName(searchText, model);
            } else if (AuthorRadio.isSelected()) {
                searchByAuthor(searchText, model);
            } else if (ALL.isSelected()) {
                searchAll(model);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a search criteria", "No Selection", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void searchByName(String name, DefaultTableModel model) {
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM Books WHERE BookName LIKE ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            ps.setString(1, "%" + name + "%");
            ResultSet rs = ps.executeQuery();
            populateTable(rs, model);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error searching by name", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchByAuthor(String author, DefaultTableModel model) {
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM Books WHERE Author LIKE ?", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            ps.setString(1, "%" + author + "%");
            ResultSet rs = ps.executeQuery();
            populateTable(rs, model);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error searching by author", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchAll(DefaultTableModel model) {
        try (Connection con = DB.getConnection();
             PreparedStatement ps = con.prepareStatement("SELECT * FROM Books", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
            ResultSet rs = ps.executeQuery();
            populateTable(rs, model);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error fetching all books", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void populateTable(ResultSet rs, DefaultTableModel model) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        int colnum = rsmd.getColumnCount();
        while (rs.next()) {
            String[] row = new String[colnum];
            for (int i = 1; i <= colnum; i++) {
                row[i - 1] = rs.getString(i);
            }
            model.addRow(row);
        }
    }

    private void NameRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NameRadioActionPerformed
        AuthorRadio.setSelected(false);
        ALL.setSelected(false);
    }//GEN-LAST:event_NameRadioActionPerformed

    private void AuthorRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AuthorRadioActionPerformed
        NameRadio.setSelected(false);
        ALL.setSelected(false);
    }//GEN-LAST:event_AuthorRadioActionPerformed

    private void ALLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ALLActionPerformed
        NameRadio.setSelected(false);
        AuthorRadio.setSelected(false);
        searchBooks();
    }//GEN-LAST:event_ALLActionPerformed

    private void toggleSearchCriteria(JRadioButton radioButton) {
        if (radioButton == NameRadio) {
            AuthorRadio.setSelected(false);
            ALL.setSelected(false);
        } else if (radioButton == AuthorRadio) {
            NameRadio.setSelected(false);
            ALL.setSelected(false);
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new ViewBook().setVisible(true);
            } catch (SQLException ex) {
                Logger.getLogger(ViewBook.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton ALL;
    private javax.swing.JRadioButton AuthorRadio;
    private javax.swing.JRadioButton NameRadio;
    private javax.swing.JButton Search;
    private javax.swing.JTextField SearchField;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
