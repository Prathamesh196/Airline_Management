package AMS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Signup extends JFrame implements ActionListener {
    JLabel titleLabel, usernameLabel, passwordLabel, dobLabel, phoneLabel;
    JTextField usernameField, dobField, phoneField; // Declaring dobField as a class variable
    JPasswordField passwordField;
    JButton submitButton, backButton;
    JFrame frame;

    Signup() {
        frame = new JFrame("Sign up");
        
        frame.setLayout(null);

        titleLabel = new JLabel("Sign up");
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 38));
        titleLabel.setBounds(190, 20, 280, 50);
        frame.add(titleLabel);

        usernameLabel = new JLabel("Username ");
        usernameLabel.setBounds(50, 100, 190, 25);
        usernameLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        frame.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(240, 100, 200, 25);
        usernameField.setFont(new Font("Lato", Font.PLAIN, 17));
        frame.add(usernameField);

        passwordLabel = new JLabel("Password ");
        passwordLabel.setBounds(50, 150, 190, 25);
        passwordLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        frame.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(240, 150, 200, 25);
        passwordField.setFont(new Font("Lato", Font.PLAIN, 17));
        frame.add(passwordField);

        dobLabel = new JLabel("Date of Birth");
        dobLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        dobLabel.setBounds(50, 200, 190, 25);
        frame.add(dobLabel);

        dobField = new JTextField(); // Initializing dobField
        dobField.setBounds(240, 200, 200, 25);
        dobField.setFont(new Font("Lato", Font.PLAIN, 17));
        frame.add(dobField);

        phoneLabel = new JLabel("Phone Number ");
        phoneLabel.setFont(new Font("Verdana", Font.BOLD, 20));
        phoneLabel.setBounds(50, 250, 190, 25);
        frame.add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(240, 250, 200, 25);
        phoneField.setFont(new Font("Lato", Font.PLAIN, 17));
        frame.add(phoneField);

        submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Garamond", Font.BOLD, 22));
        submitButton.setBounds(80, 320, 150, 40);
        submitButton.addActionListener(this);
        submitButton.setBackground(Color.GREEN);
        frame.add(submitButton);

        backButton = new JButton("Back");
        backButton.setFont(new Font("Garamond", Font.BOLD, 22));
        backButton.setBounds(280, 320, 150, 40);
        backButton.addActionListener(this);
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        frame.add(backButton);

        frame.setSize(500, 450);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        createSignupTable(); // Call the method to create the Signup table
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            // Add signup data to the database
            if (addSignupData()) {
                JOptionPane.showMessageDialog(frame, "Sign up Successfully");
            }
        } else if (e.getSource() == backButton) {
            int response = JOptionPane.showConfirmDialog(frame, "Are you Sure to cancel signup?", "Confirm",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (response == JOptionPane.YES_OPTION) {
                frame.dispose();
                // You may add code to navigate back to the previous screen
            }
        }
    }

    // Method to create the Signup table
    private void createSignupTable() {
        try {
            ConnectionClass obj = new ConnectionClass();
            String query = "CREATE TABLE IF NOT EXISTS Signup ("
                    + "username VARCHAR(50) PRIMARY KEY,"
                    + "password VARCHAR(50),"
                    + "dob DATE,"
                    + "phone_number VARCHAR(20)"
                    + ")";
            obj.stm.executeUpdate(query);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public boolean addSignupData() {
        try {
            ConnectionClass obj = new ConnectionClass();
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String dob = dobField.getText();
            String phone_number = phoneField.getText();

            String query = "INSERT INTO Signup (username, password, dob, phone_number) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = obj.con.prepareStatement(query);
            pst.setString(1, username);
            pst.setString(2, password);
            pst.setString(3, dob);
            pst.setString(4, phone_number);

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        new Signup();
    }
}
