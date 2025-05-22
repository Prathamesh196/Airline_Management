package AMS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AdminAddFlight extends JFrame implements ActionListener {
    // Text fields
    private JTextField sourceField;
    private JTextField destinationField;
    private JTextField classField;
    private JTextField priceField;
    private JTextField flightCodeField;
    private JTextField flightNameField;
    private JTextField journeyDateField;
    private JTextField journeyTimeField;
    private JTextField boardingTimeField;

    // Constructor
    public AdminAddFlight() {
        setTitle("Add Flight");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.weightx = 1.0;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        // Labels and text fields
        JLabel sourceLabel = new JLabel("Source:");
        sourceLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(sourceLabel, constraints);

        sourceField = new JTextField();
        sourceField.setFont(new Font("Verdana", Font.PLAIN, 15));
        constraints.gridx = 1;
        constraints.gridy = 0;
        add(sourceField, constraints);

        JLabel destinationLabel = new JLabel("Destination:");
        destinationLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        constraints.gridx = 0;
        constraints.gridy = 1;
        add(destinationLabel, constraints);

        destinationField = new JTextField();
        destinationField.setFont(new Font("Verdana", Font.PLAIN, 15));
        constraints.gridx = 1;
        constraints.gridy = 1;
        add(destinationField, constraints);

        JLabel classLabel = new JLabel("Class:");
        classLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        constraints.gridx = 0;
        constraints.gridy = 2;
        add(classLabel, constraints);

        classField = new JTextField();
        classField.setFont(new Font("Verdana", Font.PLAIN, 15));
        constraints.gridx = 1;
        constraints.gridy = 2;
        add(classField, constraints);

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        constraints.gridx = 0;
        constraints.gridy = 3;
        add(priceLabel, constraints);

        priceField = new JTextField();
        priceField.setFont(new Font("Verdana", Font.PLAIN, 15));
        constraints.gridx = 1;
        constraints.gridy = 3;
        add(priceField, constraints);

        JLabel flightCodeLabel = new JLabel("Flight Code:");
        flightCodeLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        constraints.gridx = 0;
        constraints.gridy = 4;
        add(flightCodeLabel, constraints);

        flightCodeField = new JTextField();
        flightCodeField.setFont(new Font("Verdana", Font.PLAIN, 15));
        constraints.gridx = 1;
        constraints.gridy = 4;
        add(flightCodeField, constraints);

        JLabel flightNameLabel = new JLabel("Flight Name:");
        flightNameLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        constraints.gridx = 0;
        constraints.gridy = 5;
        add(flightNameLabel, constraints);

        flightNameField = new JTextField();
        flightNameField.setFont(new Font("Verdana", Font.PLAIN, 15));
        constraints.gridx = 1;
        constraints.gridy = 5;
        add(flightNameField, constraints);

        JLabel journeyDateLabel = new JLabel("Journey Date:");
        journeyDateLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        constraints.gridx = 0;
        constraints.gridy = 6;
        add(journeyDateLabel, constraints);

        journeyDateField = new JTextField();
        journeyDateField.setFont(new Font("Verdana", Font.PLAIN, 15));
        constraints.gridx = 1;
        constraints.gridy = 6;
        add(journeyDateField, constraints);

        JLabel journeyTimeLabel = new JLabel("Journey Time:");
        journeyTimeLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        constraints.gridx = 0;
        constraints.gridy = 7;
        add(journeyTimeLabel, constraints);

        journeyTimeField = new JTextField();
        journeyTimeField.setFont(new Font("Verdana", Font.PLAIN, 15));
        constraints.gridx = 1;
        constraints.gridy = 7;
        add(journeyTimeField, constraints);

        JLabel boardingTimeLabel = new JLabel("Boarding Time:");
        boardingTimeLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        constraints.gridx = 0;
        constraints.gridy = 8;
        add(boardingTimeLabel, constraints);

        boardingTimeField = new JTextField();
        boardingTimeField.setFont(new Font("Verdana", Font.PLAIN, 15));
        constraints.gridx = 1;
        constraints.gridy = 8;
        add(boardingTimeField, constraints);

        // Buttons
        JButton addButton = new JButton("Add Flight");
        addButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        addButton.setBackground(new Color(0x4CAF50));
        addButton.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 9;
        constraints.gridwidth = 2;
        add(addButton, constraints);
        addButton.addActionListener(this);

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backButton.setBackground(new Color(0x4CAF50));
        backButton.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 10;
        constraints.gridwidth = 2;
        add(backButton, constraints);
        backButton.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Add Flight")) {
            addFlight();
        } else if (e.getActionCommand().equals("Back")) {
            dispose();
            // Code to navigate back to the previous screen
        }
    }

    private void addFlight() {
        String source = sourceField.getText();
        String destination = destinationField.getText();
        String flightClass = classField.getText();
        String price = priceField.getText();
        String flightCode = flightCodeField.getText();
        String flightName = flightNameField.getText();
        String journeyDate = journeyDateField.getText();
        String journeyTime = journeyTimeField.getText();
        String boardingTime = boardingTimeField.getText();

        try {
            ConnectionClass obj = new ConnectionClass();
            String query = "INSERT INTO adminflights (source, destination, flightClass, price, flightCode, flightName, journeyDate, journeyTime, boarding_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = obj.con.prepareStatement(query);
            pst.setString(1, source);
            pst.setString(2, destination);
            pst.setString(3, flightClass);
            pst.setString(4, price);
            pst.setString(5, flightCode);
            pst.setString(6, flightName);
            pst.setString(7, journeyDate);
            pst.setString(8, journeyTime);
            pst.setString(9, boardingTime);

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Flight added successfully");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to add flight");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new AdminAddFlight();
    }
}
