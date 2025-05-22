package AMS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.sql.*;

public class BookFlight extends JFrame implements ActionListener {
    JButton bookFlightButton, backButton, searchFlightButton; // Added searchFlightButton
    JLabel headingLabel, imageLabel, ticketIdLabel, sourceLabel, destinationLabel, classLabel, priceLabel, flightCodeLabel, flightNameLabel, journeyDateLabel, journeyTimeLabel, boardingTimeLabel, usernameLabel, nameLabel;
    JTextField ticketIdField, classField, priceField, flightCodeField, flightNameField, journeyDateField, journeyTimeField, boardingTimeField, usernameField, nameField;
    JComboBox<String> sourceComboBox, destinationComboBox, flightNameComboBox; // Added flightNameComboBox
    JPanel leftPanel, rightPanel;
    JFrame frame;

    BookFlight() {
        frame = new JFrame("Book Airlines India Flight");
        frame.setLayout(null);

        // Heading
        headingLabel = new JLabel("Book Airlines India Flight");
        headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 38));
        headingLabel.setForeground(new Color(0xfc3503));
        headingLabel.setBounds(256, 20, 490, 30);
        frame.add(headingLabel);

        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/AMS/icons/mapp.jpg"));
        Image image = imageIcon.getImage();
        Image newimg = image.getScaledInstance(300, 200, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newimg);
        imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(300, 60, 300, 200);
        frame.add(imageLabel);

        // Left Panel
        leftPanel = new JPanel();
        leftPanel.setLayout(new GridLayout(6, 20, 0, 10));
        leftPanel.setBounds(50, 350, 250, 250);
        frame.add(leftPanel);

        // Right Panel
        rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(6, 30, 0, 10));
        rightPanel.setBounds(500, 350, 250, 250);
        frame.add(rightPanel);

        // Labels and Text Fields
        ticketIdLabel = new JLabel("Ticket ID:");
        ticketIdLabel.setFont(new Font("Verdana", Font.BOLD, 15));
        ticketIdField = new JTextField(generateTicketID());
        ticketIdField.setForeground(Color.RED);
        ticketIdField.setFont(new Font("Verdana", Font.PLAIN, 16));
        ticketIdField.setEditable(false);

        sourceLabel = new JLabel("Source:");
        sourceLabel.setFont(new Font("Verdana", Font.BOLD, 17));
        sourceComboBox = new JComboBox<>();
        sourceComboBox.setFont(new Font("Verdana", Font.PLAIN, 16));
        populateComboBox("source", sourceComboBox);

        destinationLabel = new JLabel("Destination:");
        destinationComboBox = new JComboBox<>();
        destinationComboBox.setFont(new Font("Verdana", Font.PLAIN, 16));
        destinationLabel.setFont(new Font("Verdana", Font.BOLD, 17));
        populateComboBox("destination", destinationComboBox);

        classLabel = new JLabel("Class:");
        classLabel.setFont(new Font("Verdana", Font.BOLD, 15));
        classField = new JTextField();
        classField.setFont(new Font("Verdana", Font.PLAIN, 16));
        classField.setEditable(false);

        priceLabel = new JLabel("Price:");
        priceLabel.setFont(new Font("Verdana", Font.BOLD, 15));
        priceField = new JTextField();
        priceField.setFont(new Font("Verdana", Font.PLAIN, 16));
        priceField.setEditable(false);

        flightCodeLabel = new JLabel("Flight Code:");
        flightCodeLabel.setFont(new Font("Verdana", Font.BOLD, 15));
        flightCodeField = new JTextField();
        flightCodeField.setFont(new Font("Verdana", Font.PLAIN, 16));
        flightCodeField.setEditable(false);

        flightNameLabel = new JLabel("Flight Name:");
        flightNameLabel.setFont(new Font("Verdana", Font.BOLD, 15));
        flightNameComboBox = new JComboBox<>();
        flightNameComboBox.setFont(new Font("Verdana", Font.PLAIN, 16));
        flightNameComboBox.addItem("");
        flightNameComboBox.addActionListener(this);

        journeyDateLabel = new JLabel("Journey Date:");
        journeyDateLabel.setFont(new Font("Verdana", Font.BOLD, 15));
        journeyDateField = new JTextField();
        journeyDateField.setFont(new Font("Verdana", Font.PLAIN, 16));
        journeyDateField.setEditable(false);

        journeyTimeLabel = new JLabel("Journey Time:");
        journeyTimeLabel.setFont(new Font("Verdana", Font.BOLD, 14));
        journeyTimeField = new JTextField();
        journeyTimeField.setFont(new Font("Verdana", Font.PLAIN, 16));
        journeyTimeField.setEditable(false);

        boardingTimeLabel = new JLabel("Boarding Time:");
        boardingTimeLabel.setFont(new Font("Verdana", Font.BOLD, 14));
        boardingTimeField = new JTextField();
        boardingTimeField.setFont(new Font("Verdana", Font.PLAIN, 16));
        boardingTimeField.setEditable(false);

        usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("Verdana", Font.BOLD, 15));
        usernameField = new JTextField();
        usernameField.setFont(new Font("Verdana", Font.PLAIN, 16));

        nameLabel = new JLabel("Name:");
        nameLabel.setFont(new Font("Verdana", Font.BOLD, 15));
        nameField = new JTextField();
        nameField.setFont(new Font("Verdana", Font.PLAIN, 16));

        // Add labels and fields to left and right panels
        leftPanel.add(ticketIdLabel);
        leftPanel.add(ticketIdField);
        rightPanel.add(sourceLabel);
        rightPanel.add(sourceComboBox);
        leftPanel.add(destinationLabel);
        leftPanel.add(destinationComboBox);
        rightPanel.add(classLabel);
        rightPanel.add(classField);
        leftPanel.add(priceLabel);
        leftPanel.add(priceField);
        rightPanel.add(flightCodeLabel);
        rightPanel.add(flightCodeField);
        leftPanel.add(flightNameLabel);
        leftPanel.add(flightNameComboBox);
        rightPanel.add(journeyDateLabel);
        rightPanel.add(journeyDateField);
        leftPanel.add(journeyTimeLabel);
        leftPanel.add(journeyTimeField);
        rightPanel.add(usernameLabel);
        rightPanel.add(usernameField);
        leftPanel.add(nameLabel);
        leftPanel.add(nameField);
        rightPanel.add(boardingTimeLabel);
        rightPanel.add(boardingTimeField);

        // Buttons
        int buttonY = 650;
        int buttonWidth = 150;

        bookFlightButton = new JButton("Book Flight");
        bookFlightButton.setBackground(Color.ORANGE);
        bookFlightButton.setForeground(Color.WHITE);
        bookFlightButton.setBounds(150, buttonY, buttonWidth, 40);
        bookFlightButton.setFont(new Font("Verdana", Font.PLAIN, 16));
        bookFlightButton.addActionListener(this);
        frame.add(bookFlightButton);

        backButton = new JButton("Back");
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.setBounds(360, buttonY, buttonWidth, 40);
        backButton.setFont(new Font("Verdana", Font.PLAIN, 16));
        backButton.addActionListener(this);
        frame.add(backButton);

        searchFlightButton = new JButton("Search Flight");
        searchFlightButton.setBackground(Color.BLUE);
        searchFlightButton.setForeground(Color.WHITE);
        searchFlightButton.setBounds(560, buttonY, buttonWidth, 40);
        searchFlightButton.setFont(new Font("Verdana", Font.PLAIN, 16));
        searchFlightButton.addActionListener(this);
        frame.add(searchFlightButton);

        frame.setSize(900, 900);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bookFlightButton) {
            String ticketId = ticketIdField.getText();
            String source = sourceComboBox.getSelectedItem().toString();
            String destination = destinationComboBox.getSelectedItem().toString();
            String flightName = flightNameComboBox.getSelectedItem().toString();
            String flightClass = classField.getText();
            String price = priceField.getText();
            String flightCode = flightCodeField.getText();
            String journeyDate = journeyDateField.getText();
            String journeyTime = journeyTimeField.getText();
            String boardingTime = boardingTimeField.getText();
            String username = usernameField.getText();
            String name = nameField.getText();

            insertBooking(ticketId, source, destination, flightClass, price, flightCode, flightName, journeyDate, journeyTime, boardingTime, username, name);
        } else if (e.getSource() == backButton) {
            frame.dispose();
        } else if (e.getSource() == searchFlightButton) {
            String source = sourceComboBox.getSelectedItem().toString();
            String destination = destinationComboBox.getSelectedItem().toString();
            fetchFlightDetails(source, destination);
        } else if (e.getSource() == flightNameComboBox) {
            String source = sourceComboBox.getSelectedItem().toString();
            String destination = destinationComboBox.getSelectedItem().toString();
            String flightName = flightNameComboBox.getSelectedItem().toString();

            if (!flightName.isEmpty()) {
                fetchFlightDetailsByFlightName(source, destination, flightName);
            }
        }
    }

    private void fetchFlightDetailsByFlightName(String source, String destination, String flightName) {
        try {
            ConnectionClass obj = new ConnectionClass();
            String query = "SELECT * FROM AdminFlights WHERE source = ? AND destination = ? AND flightName = ?";
            PreparedStatement pstmt = obj.con.prepareStatement(query);
            pstmt.setString(1, source);
            pstmt.setString(2, destination);
            pstmt.setString(3, flightName);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                classField.setText(rs.getString("flightClass"));
                priceField.setText(rs.getString("price"));
                flightCodeField.setText(rs.getString("flightCode"));
                journeyDateField.setText(rs.getString("journeyDate"));
                journeyTimeField.setText(rs.getString("journeyTime"));
                boardingTimeField.setText(rs.getTime("boarding_time").toString());
            } else {
                JOptionPane.showMessageDialog(null, "No flights found for the selected source, destination, and flight name.");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void fetchFlightDetails(String source, String destination) {
        try {
            ConnectionClass obj = new ConnectionClass();
            String query = "SELECT * FROM AdminFlights WHERE source = ? AND destination = ?";
            PreparedStatement pstmt = obj.con.prepareStatement(query);
            pstmt.setString(1, source);
            pstmt.setString(2, destination);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                classField.setText(rs.getString("flightClass"));
                priceField.setText(rs.getString("price"));
                flightCodeField.setText(rs.getString("flightCode"));
                flightNameComboBox.removeAllItems();
                flightNameComboBox.addItem("");
                do {
                    flightNameComboBox.addItem(rs.getString("flightName"));
                } while (rs.next());
            } else {
                JOptionPane.showMessageDialog(null, "No flights found for the selected source and destination.");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void insertBooking(String ticketId, String source, String destination, String flightClass, String price, String flightCode, String flightName, String journeyDate, String journeyTime, String boardingTime, String username, String name) {
        try {
            ConnectionClass obj = new ConnectionClass();
            String query = "INSERT INTO bookflight (ticketId, source, destination, flightClass, price, flightCode, flightName, journeyDate, journeyTime, boardingTime, username, name) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = obj.con.prepareStatement(query);
            pstmt.setString(1, ticketId);
            pstmt.setString(2, source);
            pstmt.setString(3, destination);
            pstmt.setString(4, flightClass);
            pstmt.setString(5, price);
            pstmt.setString(6, flightCode);
            pstmt.setString(7, flightName);
            pstmt.setString(8, journeyDate);
            pstmt.setString(9, journeyTime);
            pstmt.setString(10, boardingTime);
            pstmt.setString(11, username);
            pstmt.setString(12, name);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Booking successful!");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error occurred while booking: " + ex.getMessage());
        }
    }

    private String generateTicketID() {
        Random rand = new Random();
        int ticketID = rand.nextInt(90000) + 10000;
        return String.valueOf(ticketID);
    }

    private void populateComboBox(String columnName, JComboBox<String> comboBox) {
        try {
            ConnectionClass obj = new ConnectionClass();
            String query = "SELECT DISTINCT " + columnName + " FROM AdminFlights";
            ResultSet rs = obj.stm.executeQuery(query);
            while (rs.next()) {
                comboBox.addItem(rs.getString(columnName));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new BookFlight();
    }
}
