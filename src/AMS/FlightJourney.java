package AMS;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel; // Import DefaultTableModel
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;

public class FlightJourney extends JFrame implements ActionListener {
    JComboBox<String> sourceComboBox, destinationComboBox;
    JButton searchButton, closeButton;
    JTable detailsTable;
    DefaultTableModel model; // Declare model variable
    JFrame frame;

    FlightJourney() {
        frame = new JFrame("Flight Journey");
        frame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        topPanel.setBackground(new Color(240, 240, 240)); // Set panel background color

        JLabel sourceLabel = new JLabel("Source:");
        sourceLabel.setFont(new Font("Arial", Font.PLAIN, 14)); // Set label font
        topPanel.add(sourceLabel);

        sourceComboBox = new JComboBox<>();
        sourceComboBox.setFont(new Font("Arial", Font.PLAIN, 14)); // Set combo box font
        topPanel.add(sourceComboBox);

        JLabel destinationLabel = new JLabel("Destination:");
        destinationLabel.setFont(new Font("Arial", Font.PLAIN, 14)); // Set label font
        topPanel.add(destinationLabel);

        destinationComboBox = new JComboBox<>();
        destinationComboBox.setFont(new Font("Arial", Font.PLAIN, 14)); // Set combo box font
        topPanel.add(destinationComboBox);

        searchButton = new JButton("Search");
        searchButton.setFont(new Font("Arial", Font.PLAIN, 14)); // Set button font
        searchButton.setBackground(new Color(51, 153, 255)); // Set button background color
        searchButton.setForeground(Color.WHITE); // Set button text color
        searchButton.addActionListener(this);
        topPanel.add(searchButton);

        closeButton = new JButton("Close");
        closeButton.setFont(new Font("Arial", Font.PLAIN, 14)); // Set button font
        closeButton.setBackground(Color.RED); // Set button background color
        closeButton.setForeground(Color.WHITE); // Set button text color
        closeButton.addActionListener(this);
        topPanel.add(closeButton);

        frame.add(topPanel, BorderLayout.NORTH);

        String[] columnNames = {"Ticket ID", "Source", "Destination", "Class", "Price", "Flight Code", "Flight Name", "Journey Date", "Journey Time", "Username", "Name"};
        model = new DefaultTableModel(columnNames, 0);
        detailsTable = new JTable(model);
        detailsTable.setFont(new Font("Arial", Font.PLAIN, 12));
        detailsTable.setRowHeight(25);
        detailsTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));

        JScrollPane scrollPane = new JScrollPane(detailsTable);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        populateSourceAndDestination();
    }

    public void populateSourceAndDestination() {
        try {
            ConnectionClass obj = new ConnectionClass();
            String query = "SELECT DISTINCT source, destination FROM bookflight";
            ResultSet rs = obj.stm.executeQuery(query);

            ArrayList<String> sources = new ArrayList<>();
            ArrayList<String> destinations = new ArrayList<>();

            while (rs.next()) {
                String source = rs.getString("source");
               String destination = rs.getString("destination");
                sources.add(source);
                destinations.add(destination);
            }

            for (String source : sources) {
                sourceComboBox.addItem(source);
            }

            for (String destination : destinations) {
                destinationComboBox.addItem(destination);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == searchButton) {
            String selectedSource = (String) sourceComboBox.getSelectedItem();
            String selectedDestination = (String) destinationComboBox.getSelectedItem();

            searchFlights(selectedSource, selectedDestination);
        } else if (e.getSource() == closeButton) {
            frame.dispose();
        }
    }

    public void searchFlights(String source, String destination) {
        model.setRowCount(0); // Clear previous search results

        try {
            ConnectionClass obj = new ConnectionClass();
            String query = "SELECT * FROM bookflight WHERE source = ? AND destination = ?";
            PreparedStatement pst = obj.con.prepareStatement(query);
            pst.setString(1, source);
            pst.setString(2, destination);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String[] rowData = {
                        rs.getString("ticketId"),
                        rs.getString("source"),
                        rs.getString("destination"),
                        rs.getString("flightClass"),
                        rs.getString("price"),
                        rs.getString("flightCode"),
                        rs.getString("flightName"),
                        rs.getString("journeyDate"),
                        rs.getString("journeyTime"),
                        rs.getString("username"),
                        rs.getString("name")
                };
                model.addRow(rowData);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new FlightJourney();
    }
}
