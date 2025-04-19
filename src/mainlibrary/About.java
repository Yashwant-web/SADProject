package mainlibrary;

import javax.swing.*;
import java.awt.*;

public class About extends javax.swing.JFrame {

    public About() {
        initComponents();
        setTitle("About - Library Management System");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        JScrollPane developerScrollPane;
        JScrollPane headerScrollPane;
        JTextArea developerTextArea;
        JTextArea headerTextArea;
        JButton backButton;

        developerScrollPane = new JScrollPane();
        developerTextArea = new JTextArea();
        backButton = new JButton();
        headerScrollPane = new JScrollPane();
        headerTextArea = new JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        developerTextArea.setEditable(false);
        developerTextArea.setColumns(20);
        developerTextArea.setRows(5);
        developerTextArea.setText("Developed by:\n             Bikash Kumar Tudu\t\t\tKalkute Piyush Premchand\n             CSE150001006\t\t\tCSE150001013\n");
        developerScrollPane.setViewportView(developerTextArea);

        backButton.setText("Back");
        backButton.addActionListener(evt -> this.setVisible(false));

        headerTextArea.setEditable(false);
        headerTextArea.setBackground(new java.awt.Color(210, 106, 231));
        headerTextArea.setForeground(Color.WHITE);
        headerTextArea.setFont(new Font("SansSerif", Font.BOLD, 16));
        headerTextArea.setColumns(20);
        headerTextArea.setRows(5);
        headerTextArea.setText("\n                                About\n                  Library Management Software");
        headerTextArea.setAutoscrolls(false);
        headerScrollPane.setViewportView(headerTextArea);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(backButton, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
                    .addGap(304, 304, 304))
                .addGroup(layout.createSequentialGroup()
                    .addGap(46, 46, 46)
                    .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(developerScrollPane, GroupLayout.PREFERRED_SIZE, 622, GroupLayout.PREFERRED_SIZE)
                        .addComponent(headerScrollPane, GroupLayout.PREFERRED_SIZE, 571, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(50, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(109, 109, 109)
                    .addComponent(headerScrollPane, GroupLayout.PREFERRED_SIZE, 160, GroupLayout.PREFERRED_SIZE)
                    .addGap(183, 183, 183)
                    .addComponent(developerScrollPane, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(backButton, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
                    .addGap(72, 72, 72))
        );

        pack();
    }

    public static void main(String args[]) {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(About.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> new About().setVisible(true));
    }
} 
