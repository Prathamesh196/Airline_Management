package AMS;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.*;

public class ViewPassenger extends JFrame implements ActionListener {
    JButton closeButton, searchButton;
    JTable detailsTable;
    JScrollPane scrollPane;
    JFrame frame;
    DefaultTableModel model;
    JTextField usernameField, passportField;

    ViewPassenger() {
        frame = new JFrame("View Passenger Details");
        frame.setLayout(new BorderLayout());
        frame.setBackground(new Color(240, 240, 240)); // Set background color

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new FlowLayout());

        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField(20);
       usernameLabel.setFont(new Font("Verdana", Font.BOLD, 23));
        
        JLabel passportLabel = new JLabel("Passport No:");
        passportField = new JTextField(20);
        
         passportLabel.setFont(new Font("Verdana", Font.BOLD, 23));

        searchButton = new JButton("Search");
             searchButton.setBackground(new Color(0xFFD700));
        searchButton.setFont(new Font("Verdana", Font.BOLD, 18));
        
        searchButton.addActionListener(this);
        

        searchPanel.add(usernameLabel);
        searchPanel.add(usernameField);
        searchPanel.add(passportLabel);
        searchPanel.add(passportField);
        searchPanel.add(searchButton);

        frame.add(searchPanel, BorderLayout.NORTH);

        String[] columnNames = {"Username", "Name", "Age", "Date of Birth", "Address", "Phone", "Email", "Nationality", "Gender", "Passport No"};
        model = new DefaultTableModel(columnNames, 0);
        detailsTable = new JTable(model);
        detailsTable.setFont(new Font("Lato", Font.PLAIN, 23));
        detailsTable.setRowHeight(30);
        detailsTable.getTableHeader().setFont(new Font("Verdana", Font.BOLD, 17));
        detailsTable.setBackground(new Color(255, 255, 255)); // Set table background color
        detailsTable.setForeground(new Color(0, 0, 0)); // Set table foreground color

        scrollPane = new JScrollPane(detailsTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Remove scroll pane border
        frame.add(scrollPane, BorderLayout.CENTER);

        closeButton = new JButton("Close");
        closeButton.setBackground(new Color(51, 153, 255)); // Set button background color
        closeButton.setForeground(Color.WHITE); // Set button foreground color
        closeButton.addActionListener(this);
        closeButton.setPreferredSize(new Dimension(100, 40));
        closeButton.setFont(new Font("Arial", Font.BOLD, 14)); // Set button font
        closeButton.setBorder(BorderFactory.createLineBorder(new Color(51, 153, 255), 2)); // Set button border
        frame.add(closeButton, BorderLayout.SOUTH);

        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void displayPassengerDetails(String username, String passport) {
        model.setRowCount(0); // Clear existing rows
        try {
            ConnectionClass obj = new ConnectionClass();
            String query = "SELECT * FROM passenger WHERE username = ? AND passport = ?";
            PreparedStatement pst = obj.con.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, passport);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String[] rowData = {
                        rs.getString("username"),
                        rs.getString("name"),
                        rs.getString("age"),
                        rs.getString("dob"),
                        rs.getString("address"),
                        rs.getString("phone"),
                        rs.getString("email"),
                        rs.getString("nationality"),
                        rs.getString("gender"),
                        rs.getString("passport")
                };
                model.addRow(rowData);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            String username = usernameField.getText();
            String passport = passportField.getText();
            displayPassengerDetails(username, passport);
        } else if (e.getSource() == closeButton) {
            frame.dispose();
            // Add code here to navigate back to the previous screen
        }
    }

    public static void main(String[] args) {
        new ViewPassenger();
    }
}
