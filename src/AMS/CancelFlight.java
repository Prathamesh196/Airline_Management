package AMS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class CancelFlight extends JFrame implements ActionListener {
    JComboBox<String> ticketIdDropdown;
    JTextField sourceField, destinationField, classField, priceField, flightCodeField, flightNameField, journeyDateField, journeyTimeField, usernameField, nameField, reasonField;
    JButton cancelFlightButton, backButton;
    JFrame frame;
    JLabel imageLabel , headingLabel;

    CancelFlight() {
        frame = new JFrame("Cancel Ticket");
        frame.setLayout(null);
        
         headingLabel = new JLabel("Cancel Your Booked Flight");
        headingLabel.setFont(new Font("Georgia", Font.BOLD, 29));
        headingLabel.setForeground(Color.RED); // Set the foreground color to red
        headingLabel.setBounds(430, 20, 400, 30);
        frame.add(headingLabel);

           imageLabel = new JLabel(new ImageIcon(getClass().getResource("/AMS/icons/windowside.jpg")));
        imageLabel.setBounds(50, 100, 400, 450);
        frame.add(imageLabel);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(13, 12, 5, 5)); 
        rightPanel.setBounds(580, 90, 500, 500);

        JLabel ticketIdLabel = new JLabel("Ticket ID:");
         ticketIdLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        rightPanel.add(ticketIdLabel);

        ticketIdDropdown = new JComboBox<>();
        ticketIdDropdown.setFont(new Font("Georgia", Font.PLAIN, 16));
        ticketIdDropdown.addActionListener(this);
        rightPanel.add(ticketIdDropdown);

        JLabel sourceLabel = new JLabel("Source:");
        sourceLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        rightPanel.add(sourceLabel);

        sourceField = new JTextField();
         sourceField.setFont(new Font("Cambria", Font.PLAIN, 16));
        rightPanel.add(sourceField);

        JLabel destinationLabel = new JLabel("Destination:");
        destinationLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        rightPanel.add(destinationLabel);

        destinationField = new JTextField();
        destinationField.setFont(new Font("Cambria", Font.PLAIN, 16));
        rightPanel.add(destinationField);

        JLabel classLabel = new JLabel("Class:");
        classLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        rightPanel.add(classLabel);

        classField = new JTextField();
        classField.setFont(new Font("Cambria", Font.PLAIN, 16));
        rightPanel.add(classField);

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        rightPanel.add(priceLabel);

        priceField = new JTextField();
        rightPanel.add(priceField);

        JLabel flightCodeLabel = new JLabel("Flight Code:");
        flightCodeLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        rightPanel.add(flightCodeLabel);

        flightCodeField = new JTextField();
        flightCodeField.setFont(new Font("Cambria", Font.PLAIN, 16));
        rightPanel.add(flightCodeField);

        JLabel flightNameLabel = new JLabel("Flight Name:");
        flightNameLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        rightPanel.add(flightNameLabel);

        flightNameField = new JTextField();
        flightNameField.setFont(new Font("Cambria", Font.PLAIN, 16));
        rightPanel.add(flightNameField);

        JLabel journeyDateLabel = new JLabel("Journey Date:");
        journeyDateLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        rightPanel.add(journeyDateLabel);

        journeyDateField = new JTextField();
        journeyDateField.setFont(new Font("Cambria", Font.PLAIN, 16));
        rightPanel.add(journeyDateField);

        JLabel journeyTimeLabel = new JLabel("Journey Time:");
        
        journeyTimeLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        rightPanel.add(journeyTimeLabel);

        journeyTimeField = new JTextField();
        journeyTimeField.setFont(new Font("Cambria", Font.PLAIN, 16));
        rightPanel.add(journeyTimeField);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        rightPanel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setFont(new Font("Cambria", Font.PLAIN, 16));
        
        rightPanel.add(usernameField);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        rightPanel.add(nameLabel);

        nameField = new JTextField();
        nameField.setFont(new Font("Cambria", Font.PLAIN, 16));
        rightPanel.add(nameField);

        JLabel reasonLabel = new JLabel("Reason:");
        reasonLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        rightPanel.add(reasonLabel);

        reasonField = new JTextField();
        reasonField.setFont(new Font("Cambria", Font.PLAIN, 16));
        rightPanel.add(reasonField);

        cancelFlightButton = new JButton("Cancel Flight");
        cancelFlightButton.addActionListener(this);
        cancelFlightButton.setBackground(Color.RED);
        cancelFlightButton.setForeground(Color.WHITE); // Button textcolor white
        rightPanel.add(cancelFlightButton);

        backButton = new JButton("Back");
        backButton.addActionListener(this);
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE); // Button text color white
        rightPanel.add(backButton);

        frame.add(rightPanel);

        frame.setSize(1200, 700); // Adjust frame size
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        populateTicketIds();
    }

    public void populateTicketIds() {
        try {
            ConnectionClass obj = new ConnectionClass();
            String query = "SELECT ticketId FROM bookflight";
            PreparedStatement pst = obj.con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                ticketIdDropdown.addItem(rs.getString("ticketId"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == ticketIdDropdown) {
            String ticketId = (String) ticketIdDropdown.getSelectedItem();
            fetchFlightDetails(ticketId);
        } else if (e.getSource() == cancelFlightButton) {
            String ticketId = (String) ticketIdDropdown.getSelectedItem();
            if (ticketId != null && !ticketId.isEmpty()) {
                cancelFlight(ticketId);
            }
        } else if (e.getSource() == backButton) {
            frame.dispose(); // Close the current frame
        }
    }

    public void fetchFlightDetails(String ticketId) {
        try {
            ConnectionClass obj = new ConnectionClass();
            String query = "SELECT * FROM bookflight WHERE ticketId = ?";
            PreparedStatement pst = obj.con.prepareStatement(query);
            pst.setString(1, ticketId);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                sourceField.setText(rs.getString("source"));
                destinationField.setText(rs.getString("destination"));
                classField.setText(rs.getString("flightClass"));
                priceField.setText(rs.getString("price"));
                flightCodeField.setText(rs.getString("flightCode"));
                flightNameField.setText(rs.getString("flightName"));
                journeyDateField.setText(rs.getString("journeyDate"));
                journeyTimeField.setText(rs.getString("journeyTime"));
                usernameField.setText(rs.getString("username"));
                nameField.setText(rs.getString("name"));
            } else {
                clearFlightDetails();
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void clearFlightDetails() {
        sourceField.setText("");
        destinationField.setText("");
        classField.setText("");
        priceField.setText("");
        flightCodeField.setText("");
        flightNameField.setText("");
        journeyDateField.setText("");
        journeyTimeField.setText("");
        usernameField.setText("");
        nameField.setText("");
        reasonField.setText("");
    }

    public void cancelFlight(String ticketId) {
        try {
            ConnectionClass obj = new ConnectionClass(); // Connect to the airlines database

            // Select flight details from the bookflight table
            String selectQuery = "SELECT * FROM bookflight WHERE ticketId = ?";
            PreparedStatement selectPst = obj.con.prepareStatement(selectQuery);
            selectPst.setString(1, ticketId);
            ResultSet rs = selectPst.executeQuery();

            if (rs.next()) {
                // Get flight details
                String source = rs.getString("source");
                String destination = rs.getString("destination");
                String flightClass = rs.getString("flightClass");
                double price = rs.getDouble("price");
                String flightCode =rs.getString("flightCode");
                String flightName = rs.getString("flightName");
                String journeyDate = rs.getString("journeyDate");
                String journeyTime = rs.getString("journeyTime");
                String username = rs.getString("username");
                String name = rs.getString("name");
                String reason = reasonField.getText(); // Get the reason for cancelation

                // Insert canceled flight details into the cancelflight table
                String insertQuery = "INSERT INTO cancelflight (ticketId, source, destination, flightClass, price, flightCode, flightName, journeyDate, journeyTime, username, name, reason) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement insertPst = obj.con.prepareStatement(insertQuery);
                insertPst.setString(1, ticketId);
                insertPst.setString(2, source);
                insertPst.setString(3, destination);
                insertPst.setString(4, flightClass);
                insertPst.setDouble(5, price);
                insertPst.setString(6, flightCode);
                insertPst.setString(7, flightName);
                insertPst.setString(8, journeyDate);
                insertPst.setString(9, journeyTime);
                insertPst.setString(10, username);
                insertPst.setString(11, name);
                insertPst.setString(12, reason);

                // Execute the insert query
                int rowsAffected = insertPst.executeUpdate();
                if (rowsAffected > 0) {
                    // If insert successful, delete the flight from the bookflight table
                    String deleteQuery = "DELETE FROM bookflight WHERE ticketId = ?";
                    PreparedStatement deletePst = obj.con.prepareStatement(deleteQuery);
                    deletePst.setString(1, ticketId);
                    int deleteRowsAffected = deletePst.executeUpdate();
                    if (deleteRowsAffected > 0) {
                        JOptionPane.showMessageDialog(frame, "Flight with Ticket ID " + ticketId + " canceled successfully.");
                        clearFlightDetails();
                        ticketIdDropdown.removeItem(ticketId);
                    } else {
                        JOptionPane.showMessageDialog(frame, "Failed to cancel the flight with Ticket ID " + ticketId);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Failed to cancel the flight with Ticket ID " + ticketId);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "No flight found with Ticket ID " + ticketId);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new CancelFlight();
    }
}
