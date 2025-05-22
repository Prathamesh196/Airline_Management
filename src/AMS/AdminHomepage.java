package AMS;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.border.EmptyBorder;

public class AdminHomepage extends JFrame implements ActionListener {
    JButton addFlightButton, addEmployeeButton, updateFlightButton, signupButton;
    JLabel welcomeLabel, dashboardLabel;
    JPanel panel, cardPanel;
    CardLayout cardLayout;

    AdminHomepage() {
        setTitle("Admin Dashboard");
        
        setSize(640, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame

        panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0, 1, 0, 10)); // Vertical layout with spacing
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Add padding

        welcomeLabel = new JLabel("Welcome to Airlines Management System");
        welcomeLabel.setFont(new Font("Verdana", Font.BOLD, 25));
        buttonPanel.add(welcomeLabel);

        dashboardLabel = new JLabel("Admin Dashboard");
        dashboardLabel.setBounds(300, 50, 270, 30);
        dashboardLabel.setFont(new Font("Times New Roman", Font.PLAIN, 25));
        buttonPanel.add(dashboardLabel);

        addFlightButton = new JButton("Add Flight");
        addFlightButton.addActionListener(this);
        addFlightButton.setFont(new Font("Verdana", Font.BOLD, 20));
        addFlightButton.setBackground(new Color(0x4CAF50));
       
        addFlightButton.setForeground(Color.WHITE);
        buttonPanel.add(addFlightButton);

        addEmployeeButton = new JButton("Add Airline Employee");
        addEmployeeButton.addActionListener(this);
        addEmployeeButton.setFont(new Font("Verdana", Font.BOLD, 20));
        addEmployeeButton.setBackground(new Color(0x4CAF50));
        addEmployeeButton.setForeground(Color.WHITE);
        buttonPanel.add(addEmployeeButton);

        updateFlightButton = new JButton("Update Flight");
        updateFlightButton.addActionListener(this);
        updateFlightButton.setFont(new Font("Verdana", Font.BOLD, 20));
        updateFlightButton.setBackground(new Color(0x4CAF50));
        updateFlightButton.setForeground(Color.WHITE);
        buttonPanel.add(updateFlightButton);

        signupButton = new JButton("Admin Signup");
        signupButton.addActionListener(this);
        signupButton.setFont(new Font("Verdana", Font.BOLD, 20));
        signupButton.setBackground(new Color(0x4CAF50));
        signupButton.setForeground(Color.WHITE);
        buttonPanel.add(signupButton);

        panel.add(buttonPanel, BorderLayout.WEST);

        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        panel.add(cardPanel, BorderLayout.CENTER);

        add(panel);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addFlightButton) {
            cardLayout.show(cardPanel, "AddFlight");
            new AdminAddFlight();
        } else if (e.getSource() == addEmployeeButton) {
            cardLayout.show(cardPanel, "AddEmployee");
            new AddAirlineEmployee();
        } else if (e.getSource() == updateFlightButton) {
            cardLayout.show(cardPanel, "UpdateFlight");
            new AdminUpdateFlight();
        } else if (e.getSource() == signupButton) {
            cardLayout.show(cardPanel, "AdminSignup");
            new AdminSignup();
        }
    }

    public static void main(String[] args) {
        new AdminHomepage();
    }
}