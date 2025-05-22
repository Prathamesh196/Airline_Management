package AMS;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class Index extends JFrame implements ActionListener {
    JButton adminLoginButton, userLoginButton;

    Index() {
        setTitle("USER OR ADMIN LOGIN");
        setSize(600, 400); // Reduced height to make it more compact
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 

       
        getContentPane().setBackground(new Color(240, 240, 240));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1, 10, 10)); // Grid layout with 2 rows and 1 column
        panel.setBackground(new Color(240, 240, 240)); // Match the background color

        // Use a nice font and color for the buttons
        Font buttonFont = new Font("Segoe UI", Font.BOLD, 22);

        adminLoginButton = new JButton("Admin Login");
        adminLoginButton.setFont(buttonFont);
        adminLoginButton.setForeground(new Color(255, 255, 255)); // White text
        adminLoginButton.setBackground(new Color(0, 123, 255)); // Blue background
        adminLoginButton.addActionListener(this);
        panel.add(adminLoginButton);

        userLoginButton = new JButton("User Login");
        userLoginButton.setFont(buttonFont);
        userLoginButton.setForeground(new Color(255, 255, 255)); // White text
        userLoginButton.setBackground(new Color(34, 139, 34)); // Green background
        userLoginButton.addActionListener(this);
        panel.add(userLoginButton);

        add(panel, BorderLayout.CENTER); // Use BorderLayout to center the panel

        // Add a title label with a nice font and color
        JLabel titleLabel = new JLabel("Login Selection");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 0, 128)); // Dark blue text
        add(titleLabel, BorderLayout.NORTH); // Add to the top of the frame

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == adminLoginButton) {
            // Open AdminLogin.java
            new AdminLogin();
        } else if (e.getSource() == userLoginButton) {
            // Open Login.java
            new Login();
        }
    }

    public static void main(String[] args) {
        new Index();
    }
}