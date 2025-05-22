package AMS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AdminLogin extends JFrame implements ActionListener {
    JButton loginButton, signupButton;
    JLabel headingLabel, nameLabel, passwordLabel, setCodeLabel;
    JTextField nameField, passwordField, setCodeField;
    JFrame frame;

    AdminLogin() {
        frame = new JFrame("Admin Login");
        frame.setContentPane(new JLabel(new ImageIcon(ClassLoader.getSystemResource("AMS/icons/admin.png"))));

        frame.setLayout(null);

        headingLabel = new JLabel("ADMIN LOGIN");
        headingLabel.setBounds(150, 50, 270, 30);
        headingLabel.setFont(new Font("Verdana", Font.BOLD, 28));
        headingLabel.setForeground(new Color(0xe60e40));
        frame.add(headingLabel);

        nameLabel = new JLabel("NAME");
        nameLabel.setBounds(50, 150, 120, 30);
        nameLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        nameLabel.setForeground(Color.BLACK);
        frame.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(260, 150, 150, 30);
        nameField.setFont(new Font("Verdana", Font.PLAIN, 16));
        frame.add(nameField);

        passwordLabel = new JLabel("PASSWORD");
        passwordLabel.setBounds(50, 200, 140, 30);
        passwordLabel.setFont(new Font("Verdana", Font.BOLD, 19));
        passwordLabel.setForeground(Color.BLACK);
        frame.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(260, 200, 150, 30);
        passwordField.setFont(new Font("Verdana", Font.PLAIN, 16));
        frame.add(passwordField);

        setCodeLabel = new JLabel("SET CODE");
        setCodeLabel.setBounds(50, 250, 140, 30);
        setCodeLabel.setFont(new Font("Verdana", Font.BOLD, 19));
        setCodeLabel.setForeground(Color.BLACK);
        frame.add(setCodeLabel);

        setCodeField = new JTextField();
        setCodeField.setBounds(260, 250, 150, 30);
        setCodeField.setFont(new Font("Verdana", Font.PLAIN, 16));
        frame.add(setCodeField);

        loginButton = new JButton("LOGIN");
        loginButton.setBackground(new Color(0x0077FF));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBounds(100, 350, 100, 40);
        loginButton.addActionListener(this);
        frame.add(loginButton);

        signupButton = new JButton("SIGNUP");
        signupButton.setBackground(new Color(0x0077FF));
        signupButton.setForeground(Color.WHITE);
        signupButton.setBounds(250, 350, 100, 40);
        signupButton.addActionListener(this);
        frame.add(signupButton);

        frame.setVisible(true);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String name = nameField.getText();String password = passwordField.getText();
            String setCode = setCodeField.getText();

            try {
                ConnectionClass obj = new ConnectionClass();
                String query = "SELECT * FROM AdminSignup WHERE Name='" + name + "' AND Password='" + password + "' AND SetCode='"+ setCode + "'";
                ResultSet rs = obj.stm.executeQuery(query);
                if (rs.next()) {
                    JOptionPane.showMessageDialog(frame, "Login Successful!");
                    frame.dispose();
                    new AdminHomepage();
                } else {
                    JOptionPane.showMessageDialog(frame, "Enter valid details!");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            
        } else if (e.getSource() == signupButton) {
            frame.dispose();
            new AdminSignup();
        }
    }

    public static void main(String[] args) {
        new AdminLogin();
    }
}