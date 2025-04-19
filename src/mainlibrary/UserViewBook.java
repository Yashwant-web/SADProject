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
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Yash
 */
public class UserViewBook extends javax.swing.JFrame {

    private static final String NOT_ISSUED = "Not Issued";
    private static final String ISSUED = "Issued";
    private static final Logger logger = Logger.getLogger(UserViewBook.class.getName());

    public UserViewBook() {
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        initComponents();
        loadData(null, false);
    }

    private void loadData(final String searchTerm, final boolean filterIssued) {
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                try {
                    DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                    model.setRowCount(0); // Clear existing data

                    String query = "SELECT Books.BookID, Books.BookName, Books.Genre, Books.Author, Books.Publisher, Books.Row, Books.Shelf, IssuedBook.UserID "
                            + "FROM Books LEFT OUTER JOIN IssuedBook ON Books.BookID = IssuedBook.BookID";

                    if (searchTerm != null && !searchTerm.isEmpty()) {
                        query += " WHERE Books.BookName LIKE ? OR Books.Author LIKE ?";
                    }

                    try (Connection con = DB.getConnection();
                         PreparedStatement ps = con.prepareStatement(query, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {

                        if (searchTerm != null && !searchTerm.isEmpty()) {
                            ps.setString(1, "%" + searchTerm + "%");
                            ps.setString(2, "%" + searchTerm + "%");
                        }

                        try (ResultSet rs = ps.executeQuery()) {
                            ResultSetMetaData rsmd = rs.getMetaData();
                            int colnum = rsmd.getColumnCount();
                            String row[] = new String[colnum];

                            while (rs.next()) {
                                for (int i = 1; i <= colnum; i++) {
                                    if (i == colnum) {
                                        String issuedStatus = rs.getString(i) == null ? NOT_ISSUED : ISSUED;
                                        if (filterIssued && ISSUED.equals(issuedStatus)) {
                                            continue;
                                        }
                                        row[i - 1] = issuedStatus;
                                    } else {
                                        row[i - 1] = rs.getString(i);
                                    }
                                }
                                model.addRow(row);
                            }
                        }
                    }
                } catch (SQLException e) {
                    logger.log(Level.SEVERE, "Error loading data: " + e.getMessage(), e);
                    JOptionPane.showMessageDialog(UserViewBook.this, "Error loading data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
                }
                return null;
            }
        }.execute();
    }

    private void handleSearchAction() {
        String searchTerm = SearchField.getText().trim();
        if (searchTerm.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Search Field is Empty", "Search Error!", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean filterIssued = NotIssued.isSelected();
        loadData(searchTerm, filterIssued);
    }

    private void showAllBooks() {
        loadData(null, false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        buttonGroup1 = new javax.swing.ButtonGroup();

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        NameRadio = new javax.swing.JRadioButton();
        AuthorRadio = new javax.swing.JRadioButton();
        ALL = new javax.swing.JRadioButton();
        NotIssued = new javax.swing.JRadioButton();
        SearchField = new javax.swing.JTextField();
        Search = new javax.swing.JButton();
        ShowALL = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.HIDE_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},
            new String [] {
                "Book ID", "Name", "Genre", "Author", "Publisher", "Shelf", "Row", "Available"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Long.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Ubuntu", 0, 24));
        jLabel1.setText("Books");

        jButton1.setText("Close");
        jButton1.addActionListener(evt -> this.dispose());

        NameRadio.setText("Name");
        NameRadio.addActionListener(evt -> AuthorRadio.setSelected(false));

        AuthorRadio.setText("Author");
        AuthorRadio.addActionListener(evt -> NameRadio.setSelected(false));

        ALL.setText("ALL");
        ALL.addActionListener(evt -> NotIssued.setSelected(false));

        NotIssued.setText("NOT ISSUED");
        NotIssued.addActionListener(evt -> ALL.setSelected(false));

        Search.setText("Search");
        Search.addActionListener(evt -> handleSearchAction());

        ShowALL.setText("Show All");
        ShowALL.addActionListener(evt -> showAllBooks());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(NameRadio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(AuthorRadio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ALL)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(NotIssued)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(SearchField)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Search)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ShowALL))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(NameRadio)
                    .addComponent(AuthorRadio)
                    .addComponent(ALL)
                    .addComponent(NotIssued)
                    .addComponent(SearchField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Search)
                    .addComponent(ShowALL))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new UserViewBook().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton ALL;
    private javax.swing.JRadioButton AuthorRadio;
    private javax.swing.JRadioButton NameRadio;
    private javax.swing.JRadioButton NotIssued;
    private javax.swing.JButton Search;
    private javax.swing.JTextField SearchField;
    private javax.swing.JButton ShowALL;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
}
