package AMS;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.*;

public class ViewBookedFlight extends JFrame implements ActionListener {
    JButton closeButton, submitButton;
    JTable detailsTable;
    JScrollPane scrollPane;
    JFrame frame;
    DefaultTableModel model;
    JTextField ticketIdField;

    ViewBookedFlight() {
        frame = new JFrame("View Booked Flights");
        frame.setLayout(new BorderLayout());
        frame.setBackground(new Color(240, 240, 240)); // Set background color

        JPanel inputPanel = new JPanel(new GridBagLayout()); // Use GridBagLayout for precise component placement
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(5, 5, 5, 5); // Add spacing between components

        JLabel ticketIdLabel = new JLabel("Enter Ticket ID:");
        ticketIdLabel.setFont(new Font("Verdana", Font.BOLD, 23));
        inputPanel.add(ticketIdLabel, gbc);

        gbc.gridx++;
        ticketIdField = new JTextField(8);
        ticketIdField.setFont(new Font("Cambria", Font.BOLD, 20));
        inputPanel.add(ticketIdField, gbc);

        gbc.gridx++;
        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        submitButton.setFont(new Font("Verdana", Font.PLAIN, 18));
        submitButton.setBackground(new Color(0xfc9d03));
        submitButton.setForeground(Color.WHITE);
        inputPanel.add(submitButton, gbc);

        frame.add(inputPanel, BorderLayout.NORTH);

        String[] columnNames = {"Ticket ID", "Source", "Destination", "Class", "Price", "Flight Code", "Flight Name", "Journey Date", "Journey Time", "Username", "Name"};
        model = new DefaultTableModel(columnNames, 0);
        detailsTable = new JTable(model);
        detailsTable.setFont(new Font("Georgia", Font.PLAIN, 24));
        detailsTable.setRowHeight(30);
        detailsTable.getTableHeader().setFont(new Font("Georgia", Font.BOLD, 19));
        detailsTable.setBackground(new Color(255, 255, 255)); // Set table background color
        detailsTable.setForeground(new Color(0, 0, 0)); // Set table foreground color

        // Enable vertical scrolling for the table
        scrollPane = new JScrollPane(detailsTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
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

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String ticketId = ticketIdField.getText();
            displayBookedFlight(ticketId);
        } else if (e.getSource() == closeButton) {
            frame.dispose();
        }
    }

    public void displayBookedFlight(String ticketId) {
        try {
            ConnectionClass obj = new ConnectionClass();
            String query = "SELECT * FROM bookflight WHERE `ticketId` = ?";
            PreparedStatement ps = obj.con.prepareStatement(query);
            ps.setString(1, ticketId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Clear table before adding new rows
                model.setRowCount(0);
                
                ResultSetMetaData rsmd = rs.getMetaData();
                int numColumns = rsmd.getColumnCount();

                do {
                    String[] rowData = new String[numColumns];
                    for (int i = 1; i <= numColumns; i++) {
                        rowData[i - 1] = rs.getString(i);
                    }
                    model.addRow(rowData);
                } while (rs.next());
            } else {
                JOptionPane.showMessageDialog(frame, "Incorrect Ticket ID, please try again.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ViewBookedFlight();
    }
}
