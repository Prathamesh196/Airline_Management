package AMS;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class AdminSignup extends JFrame implements ActionListener {
    JButton signupButton, backButton;
    JLabel headingLabel, nameLabel, passwordLabel, setCodeLabel, emailLabel;
    JTextField nameField, passwordField, setCodeField, emailField;
    JFrame frame;

    AdminSignup() {
      
        
        frame = new JFrame("Admin Signup");
         frame.setContentPane(new JLabel(new ImageIcon(ClassLoader.getSystemResource("AMS/icons/adminicon.png"))));
        frame.setBackground(Color.WHITE);
        frame.setLayout(null);

        headingLabel = new JLabel("Signup");
        headingLabel.setBounds(185, 50, 220, 30);
        headingLabel.setFont(new Font("Verdana", Font.BOLD, 33));
        headingLabel.setForeground(new Color(0x0077FF));
        frame.add(headingLabel);

        nameLabel = new JLabel("Name");
        nameLabel.setBounds(80, 150, 140, 30);
        nameLabel.setFont(new Font("Verdana", Font.BOLD, 25));
        frame.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(250, 150, 150, 30);
        frame.add(nameField);

        passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(80, 200, 140, 30);
        passwordLabel.setFont(new Font("Verdana", Font.BOLD, 25));
        frame.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(250, 200, 150, 30);
        frame.add(passwordField);

        setCodeLabel = new JLabel("Set Code");
        setCodeLabel.setBounds(80, 250, 140, 30);
        setCodeLabel.setFont(new Font("Verdana", Font.BOLD, 25));
        frame.add(setCodeLabel);

        setCodeField = new JTextField();
        setCodeField.setBounds(250, 250, 150, 30);
        setCodeField.setToolTipText("Enter 4 digits only");
        setCodeField.setFont(new Font("Arial", Font.PLAIN, 16));
        frame.add(setCodeField);

        emailLabel = new JLabel("Email");
        emailLabel.setBounds(80, 300, 140, 30);
        emailLabel.setFont(new Font("Verdana", Font.BOLD, 25));
        frame.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(250, 300, 150, 30);
        emailField.setFont(new Font("Arial", Font.PLAIN, 16));
        frame.add(emailField);

        signupButton = new JButton("Signup");
        signupButton.setBackground(new Color(0x0077FF));
        signupButton.setForeground(Color.WHITE);
        signupButton.setBounds(100, 400, 100, 40);
        signupButton.addActionListener(this);
        signupButton.setFont(new Font("Arial", Font.BOLD, 16));
        frame.add(signupButton);

        backButton = new JButton("Back");
        backButton.setBackground(new Color(0x0077FF));
        backButton.setForeground(Color.WHITE);
        backButton.setBounds(250, 400, 100, 40);
        backButton.addActionListener(this);
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        frame.add(backButton);

        frame.setVisible(true);
        frame.setSize(500, 500);
        frame.setLocation(600, 200);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signupButton) {
            String name = nameField.getText();
            String password = passwordField.getText();
            String setCode = setCodeField.getText();
            String email = emailField.getText();

            try {
                ConnectionClass obj = new ConnectionClass();
                String query = "SELECT * FROM AddEmployee WHERE Name='" + name + "'";
                ResultSet rs = obj.stm.executeQuery(query);
                if (rs.next()) {
                    String insertQuery = "INSERT INTO AdminSignup (Name, Password, SetCode, Email) VALUES ('" + name + "', '" + password + "', '" + setCode + "', '" + email + "')";
                    obj.stm.executeUpdate(insertQuery);
                    JOptionPane.showMessageDialog(frame, " Signup successful!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Please first add employee details then sign up!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == backButton) {
            frame.setVisible(false);
        }
    }

    public static void main(String[] args) {
        new AdminSignup();
    }
}