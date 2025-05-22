package AMS;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class FlightZone extends JFrame implements ActionListener {
    JComboBox<String> flightCodeComboBox;
    JButton showDetailsButton;
    JLabel flightNameLabel, sourceLabel, destinationLabel, capacityLabel, classNameLabel, priceLabel ;
    JTextField flightNameField, sourceField, destinationField, capacityField, classNameField, priceField;
    JPanel panel;

    FlightZone() {
        setTitle("Flight Information");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(Color.BLUE);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);

        JLabel flightCodeLabel = new JLabel("Flight Code:");
        flightCodeLabel.setFont(new Font("Arial", Font.BOLD, 18));
        flightCodeLabel.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(flightCodeLabel, constraints);

        flightCodeComboBox = new JComboBox<>();
        flightCodeComboBox.setFont(new Font("Arial", Font.PLAIN, 16));
        constraints.gridx = 1;
        constraints.gridy = 0;
        panel.add(flightCodeComboBox, constraints);

        showDetailsButton = new JButton("Show Details");
        showDetailsButton.setFont(new Font("Arial", Font.BOLD, 16));
        
        showDetailsButton.addActionListener(this);
        constraints.gridx = 1;
        constraints.gridy = 1;
        panel.add(showDetailsButton, constraints);

        flightNameLabel = new JLabel("Flight Name:");
        flightNameLabel.setFont(new Font("Arial", Font.BOLD, 16));
         flightNameLabel.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(flightNameLabel, constraints);

        flightNameField = new JTextField();
        flightNameField.setFont(new Font("Arial", Font.PLAIN, 16));
        flightNameField.setEditable(false);
        flightNameField.setBackground(Color.WHITE);
        flightNameField.setForeground(Color.BLUE);
        flightNameField.setColumns(30); // Increase the number of columns to increase the size of the text field
        constraints.gridx = 1;
        constraints.gridy = 2;
        panel.add(flightNameField, constraints);

        sourceLabel = new JLabel("Source:");
        sourceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        sourceLabel.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(sourceLabel, constraints);

        sourceField = new JTextField();
        sourceField.setFont(new Font("Arial", Font.PLAIN, 16));
        sourceField.setEditable(false);
        sourceField.setBackground(Color.WHITE);
        sourceField.setForeground(Color.BLUE);
        sourceField.setColumns(30); // Increase the number of columns to increase the size of the text field
        constraints.gridx = 1;
        constraints.gridy = 3;
        panel.add(sourceField, constraints);

        destinationLabel = new JLabel("Destination:");
        destinationLabel.setFont(new Font("Arial", Font.BOLD, 16));
        destinationLabel.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 4;
        panel.add(destinationLabel, constraints);

        destinationField = new JTextField();
        destinationField.setFont(new Font("Arial", Font.PLAIN, 16));
        destinationField.setEditable(false);
        destinationField.setBackground(Color.WHITE);
        destinationField.setForeground(Color.BLUE);
        destinationField.setColumns(30); // Increase the number of columns to increase the size of the text field
        constraints.gridx = 1;
        constraints.gridy = 4;
        panel.add(destinationField, constraints);

        capacityLabel = new JLabel("Capacity:");
        capacityLabel.setFont(new Font("Arial", Font.BOLD, 16));
        capacityLabel.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 5;
        panel.add(capacityLabel, constraints);

        capacityField = new JTextField();
        capacityField.setFont(new Font("Arial", Font.PLAIN, 16));
        capacityField.setEditable(false);
        capacityField.setBackground(Color.WHITE);
        capacityField.setForeground(Color.BLUE);
        capacityField.setColumns(30); // Increase the number of columns to increase the size of the text field
        constraints.gridx = 1;
        constraints.gridy = 5;
        panel.add(capacityField, constraints);

        classNameLabel = new JLabel("Class Name:");
        classNameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        classNameLabel.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 6;
        panel.add(classNameLabel, constraints);

        classNameField = new JTextField();
        classNameField.setFont(new Font("Arial", Font.PLAIN, 16));
        classNameField.setEditable(false);
        classNameField.setBackground(Color.WHITE);
        classNameField.setForeground(Color.BLUE);
        classNameField.setColumns(30); // Increase the number of columns to increase the size of the text field
        constraints.gridx = 1;
        constraints.gridy = 6;
        panel.add(classNameField, constraints);

        priceLabel = new JLabel("Price:");
        priceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        priceLabel.setForeground(Color.WHITE);
        constraints.gridx = 0;
        constraints.gridy = 7;
        panel.add(priceLabel, constraints);

        priceField = new JTextField();
        priceField.setFont(new Font("Arial", Font.PLAIN, 16));
        priceField.setEditable(false);
        priceField.setBackground(Color.WHITE);
        priceField.setForeground(Color.BLUE);
        priceField.setColumns(30); // Increase the number of columns to increase the size of the text field
        constraints.gridx = 1;
        constraints.gridy = 7;
        panel.add(priceField, constraints);

        add(panel, BorderLayout.CENTER);
        setVisible(true);

        populateFlightCodes();
    }

    public void populateFlightCodes() {
        try {
            ConnectionClass obj = new ConnectionClass();
            String query = "SELECT flightCode FROM bookflight";
            ResultSet rs = obj.stm.executeQuery(query);

            while (rs.next()) {
                String flightCode = rs.getString("flightCode");
                flightCodeComboBox.addItem(flightCode);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == showDetailsButton) {
            String selectedFlightCode = (String) flightCodeComboBox.getSelectedItem();
            showFlightDetails(selectedFlightCode);
        }
    }

    public void showFlightDetails(String flightCode) {
        try {
            ConnectionClass obj = new ConnectionClass();
            String query = "SELECT b.flightName, b.source, b.destination, s.capacity, b.flightclass, b.price " +
                           "FROM bookflight b INNER JOIN space s ON b.flightCode = s.flightCode " +
                           "WHERE b.flightCode = ?";
            PreparedStatement pst = obj.con.prepareStatement(query);
            pst.setString(1, flightCode);ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                flightNameField.setText(rs.getString("flightName"));
                sourceField.setText(rs.getString("source"));
                destinationField.setText(rs.getString("destination"));
                capacityField.setText(rs.getString("capacity"));
                classNameField.setText(rs.getString("flightClass"));
                priceField.setText(rs.getString("price"));
            } else {
                JOptionPane.showMessageDialog(this, "Flight details not found for the selected flight code.");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new FlightZone();
    }
}