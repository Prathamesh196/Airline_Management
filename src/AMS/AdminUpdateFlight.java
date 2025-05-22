package AMS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AdminUpdateFlight extends JFrame implements ActionListener {
    JLabel titleLabel, flightCodeLabel, priceLabel, flightNameLabel, sourceLabel, destinationLabel, flightClassLabel, journeyDateLabel, journeyTimeLabel, boardingTimeLabel;
    JComboBox<String> flightCodeDropdown; // Changed to JComboBox
    JTextField priceField, flightNameField, sourceField, destinationField, flightClassField, journeyDateField, journeyTimeField, boardingTimeField;
    JButton updateButton, backButton;
    JFrame frame;

    AdminUpdateFlight() {
        frame = new JFrame("Update Flight Details");
        frame.setLayout(null);

        titleLabel = new JLabel("Update Flight Details");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBounds(150, 20, 300, 30);
        frame.add(titleLabel);

        flightCodeLabel = new JLabel("Flight Code:");
        flightCodeLabel.setBounds(50, 60, 100, 30);
        frame.add(flightCodeLabel);

        flightCodeDropdown = new JComboBox<>(); // Dropdown menu for flight codes
        flightCodeDropdown.setBounds(160, 60, 200, 30);
        populateFlightCodes(); // Populate flight codes in the dropdown
        flightCodeDropdown.addActionListener(this); // Add action listener to handle selection
        frame.add(flightCodeDropdown);

        priceLabel = new JLabel("Price:");
        priceLabel.setBounds(50, 100, 100, 30);
        frame.add(priceLabel);

        priceField = new JTextField();
        priceField.setBounds(160, 100, 200, 30);
        frame.add(priceField);

        flightNameLabel = new JLabel("Flight Name:");
        flightNameLabel.setBounds(50, 140, 100, 30);
        frame.add(flightNameLabel);

        flightNameField = new JTextField();
        flightNameField.setBounds(160, 140, 200, 30);
        frame.add(flightNameField);

        sourceLabel = new JLabel("Source:");
        sourceLabel.setBounds(50, 180, 100, 30);
        frame.add(sourceLabel);

        sourceField = new JTextField();
        sourceField.setBounds(160, 180, 200, 30);
        frame.add(sourceField);

        destinationLabel = new JLabel("Destination:");
        destinationLabel.setBounds(50, 220, 100, 30);
        frame.add(destinationLabel);

        destinationField = new JTextField();
        destinationField.setBounds(160, 220, 200, 30);
        frame.add(destinationField);

        flightClassLabel = new JLabel("Class:");
        flightClassLabel.setBounds(50, 260, 100, 30);
        frame.add(flightClassLabel);

        flightClassField = new JTextField();
        flightClassField.setBounds(160, 260, 200, 30);
        frame.add(flightClassField);

        journeyDateLabel = new JLabel("Journey Date:");
        journeyDateLabel.setBounds(50, 300, 100, 30);
        frame.add(journeyDateLabel);

        journeyDateField = new JTextField();
        journeyDateField.setBounds(160, 300, 200, 30);
        frame.add(journeyDateField);

        journeyTimeLabel = new JLabel("Journey Time:");
        journeyTimeLabel.setBounds(50, 340, 100, 30);
        frame.add(journeyTimeLabel);

        journeyTimeField = new JTextField();
        journeyTimeField.setBounds(160, 340, 200, 30);
        frame.add(journeyTimeField);

        boardingTimeLabel = new JLabel("Boarding Time:");
        boardingTimeLabel.setBounds(50, 380, 100, 30);
        frame.add(boardingTimeLabel);

        boardingTimeField = new JTextField();
        boardingTimeField.setBounds(160, 380, 200, 30);
        frame.add(boardingTimeField);

        updateButton = new JButton("Update");
        updateButton.setBounds(120, 440, 100, 40);
        updateButton.addActionListener(this);
        frame.add(updateButton);

        backButton = new JButton("Back");
        backButton.setBounds(250, 440, 100, 40);
        backButton.addActionListener(this);
        frame.add(backButton);

        frame.setSize(450, 550);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateButton) {
            String flightCode = flightCodeDropdown.getSelectedItem().toString(); // Fetch selected flight code from dropdown
            String price = priceField.getText();
            String flightName = flightNameField.getText();
            String source = sourceField.getText();
            String destination = destinationField.getText();
            String flightClass = flightClassField.getText();
            String journeyDate = journeyDateField.getText();
            String journeyTime = journeyTimeField.getText();
            String boardingTime = boardingTimeField.getText();

            updateFlightDetails(flightCode, price, flightName, source, destination, flightClass, journeyDate, journeyTime, boardingTime);
        } else if (e.getSource() == backButton) {
            // Handle back button action
            frame.dispose(); // Close current window
            // Open the previous window or navigate back to the main menu
        } else if (e.getSource() == flightCodeDropdown) {
            // Fetch flight details based on selected flight code
            String selectedFlightCode = flightCodeDropdown.getSelectedItem().toString();
            fetchFlightDetails(selectedFlightCode);
        }
    }

    private void populateFlightCodes() {
        try {
            ConnectionClass obj = new ConnectionClass();
            String query = "SELECT flightCode FROM AdminFlights";
            ResultSet rs = obj.stm.executeQuery(query);
            while (rs.next()) {
                flightCodeDropdown.addItem(rs.getString("flightCode"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void fetchFlightDetails(String flightCode) {
        try {
            ConnectionClass obj = new ConnectionClass();
            String query = "SELECT * FROM AdminFlights WHERE flightCode = ?";
            PreparedStatement pstmt = obj.con.prepareStatement(query);
            pstmt.setString(1, flightCode);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // Populate fields with fetched details
                priceField.setText(rs.getString("price"));
                flightNameField.setText(rs.getString("flightName"));
                sourceField.setText(rs.getString("source"));
                destinationField.setText(rs.getString("destination"));
                flightClassField.setText(rs.getString("flightClass"));
                journeyDateField.setText(rs.getString("journeyDate"));
                journeyTimeField.setText(rs.getString("journeyTime"));
                boardingTimeField.setText(rs.getString("boarding_time"));
            } else {
                JOptionPane.showMessageDialog(null, "No flight found with the provided flight code.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void updateFlightDetails(String flightCode, String price, String flightName, String source, String destination, String flightClass, String journeyDate, String journeyTime, String boardingTime) {
        try {
            ConnectionClass obj = new ConnectionClass();
            String query = "UPDATE AdminFlights SET price = ?, flightName = ?, source = ?, destination = ?, flightClass = ?, journeyDate = ?, journeyTime = ?, boarding_time = ? WHERE flightCode = ?";
            PreparedStatement pstmt = obj.con.prepareStatement(query);
            pstmt.setString(1, price);
            pstmt.setString(2, flightName);
            pstmt.setString(3, source);
            pstmt.setString(4, destination);
            pstmt.setString(5, flightClass);
            pstmt.setString(6, journeyDate);
            pstmt.setString(7, journeyTime);
            pstmt.setString(8, boardingTime);
            pstmt.setString(9, flightCode);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Flight details updated successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "No flight found with the provided flight code.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new AdminUpdateFlight();
    }
}
