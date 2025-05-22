package AMS;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class UpdatePassenger extends JFrame implements ActionListener {
    JButton updateButton, backButton;
    JLabel headingLabel, imageLabel, usernameLabel, nameLabel, ageLabel, dobLabel, addressLabel, phoneLabel, emailLabel, nationalityLabel, genderLabel, passportLabel;
    JTextField usernameField, nameField, ageField, dobField, addressField, phoneField, emailField, nationalityField, genderField, passportField;
    JFrame frame;

    UpdatePassenger() {
        frame = new JFrame("Update Passenger Details");
        frame.setLayout(null);

        headingLabel = new JLabel("Update Passenger Details");
        headingLabel.setFont(new Font("Times New Roman", Font.BOLD, 32));
         headingLabel.setForeground(new Color(0xfc3503));
        headingLabel.setBounds(330, 20, 390, 30);
        frame.add(headingLabel);

        imageLabel = new JLabel(new ImageIcon(getClass().getResource("/AMS/icons/plane.jpg")));
        imageLabel.setBounds(50, 70, 400, 450);
        frame.add(imageLabel);

        usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(500, 70, 160, 30);
         usernameLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
        frame.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(650, 70, 200, 30);
         usernameField.setFont(new Font("Cambria", Font.PLAIN, 17));
        frame.add(usernameField);

        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(500, 120, 160, 30);
        nameLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
        frame.add(nameLabel);

        nameField = new JTextField();
        nameField.setBounds(650, 120, 200, 30);
        nameField.setFont(new Font("Cambria", Font.PLAIN, 17));
        frame.add(nameField);

        ageLabel = new JLabel("Age:");
        ageLabel.setBounds(500, 170, 160, 30);
        ageLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
        frame.add(ageLabel);

        ageField = new JTextField();
        ageField.setBounds(650, 170, 200, 30);
        ageField.setFont(new Font("Cambria", Font.PLAIN, 17));
        frame.add(ageField);

        // Add other labels and text fields for passenger details (dob, address, phone, email, nationality, gender, passport) with appropriate bounds

        dobLabel = new JLabel("Date of Birth:");
        dobLabel.setBounds(500, 220, 160, 30);
        dobLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
        frame.add(dobLabel);

        dobField = new JTextField();
        dobField.setBounds(650, 220, 200, 30);
        dobField.setFont(new Font("Cambria", Font.PLAIN, 17));
        frame.add(dobField);

        addressLabel = new JLabel("Address:");
        addressLabel.setBounds(500, 270, 160, 30);
         addressLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
        frame.add(addressLabel);

        addressField = new JTextField();
        addressField.setBounds(650, 270, 200, 30);
        addressField.setFont(new Font("Cambria", Font.PLAIN, 17));
        frame.add(addressField);

        phoneLabel = new JLabel("Phone:");
        phoneLabel.setBounds(500, 320, 160, 30);
         phoneLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
        frame.add(phoneLabel);

        phoneField = new JTextField();
        phoneField.setBounds(650, 320, 200, 30);
        phoneField.setFont(new Font("Cambria", Font.PLAIN, 17));
        frame.add(phoneField);

        emailLabel = new JLabel("Email:");
        emailLabel.setBounds(500, 370, 160, 30);
         emailLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
        frame.add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(650, 370, 200, 30);
        emailField.setFont(new Font("Cambria", Font.PLAIN, 17));
        frame.add(emailField);

        nationalityLabel = new JLabel("Nationality:");
        nationalityLabel.setBounds(500, 420, 160, 30);
        nationalityLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
        frame.add(nationalityLabel);

        nationalityField = new JTextField();
        nationalityField.setBounds(650, 420, 200, 30);
        nationalityField.setFont(new Font("Cambria", Font.PLAIN, 17));
        frame.add(nationalityField);

        genderLabel = new JLabel("Gender:");
        genderLabel.setBounds(500, 470, 160, 30);
        genderLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
        
        frame.add(genderLabel);

        genderField = new JTextField();
        genderField.setBounds(650, 470, 200, 30);
        genderField.setFont(new Font("Cambria", Font.PLAIN, 17));
        frame.add(genderField);

        passportLabel = new JLabel("Passport No:");
        passportLabel.setBounds(500, 520, 160, 30);
        passportLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
        frame.add(passportLabel);

        passportField = new JTextField();
        passportField.setBounds(650, 520, 200, 30);
        passportField.setFont(new Font("Cambria", Font.PLAIN, 17));
        frame.add(passportField);

        updateButton = new JButton("Update");
        updateButton.setBackground(Color.BLUE);
        updateButton.setForeground(Color.WHITE);
         updateButton.setFont(new Font("Verdana", Font.BOLD, 19));
       updateButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFD700), 2));
        updateButton.setBounds(330, 590, 150, 40);
        updateButton.addActionListener(this);
        frame.add(updateButton);

        backButton = new JButton("Back");
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.WHITE);
         backButton.setFont(new Font("Verdana", Font.BOLD, 19));
       backButton.setBorder(BorderFactory.createLineBorder(new Color(0xFFD700), 2));
        backButton.setBounds(600, 590, 150, 40);
        backButton.addActionListener(this);
        frame.add(backButton);

        // Add action listener to passportField
        passportField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Fetch passenger details when passport number is entered
                String passport = passportField.getText();
                fetchPassengerDetails("passport", passport);
            }
        });

        // Add action listener to phoneField
        phoneField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Fetch passenger details when phone number is entered
                String phone = phoneField.getText();
                fetchPassengerDetails("phone", phone);
            }
        });

        frame.setSize(1000, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateButton) {
            String username = usernameField.getText();
            String name = nameField.getText();
            String age = ageField.getText();
            String dob = dobField.getText();
            String address = addressField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();
            String nationality = nationalityField.getText();
            String gender = genderField.getText();
            String passport = passportField.getText();

            try {
                ConnectionClass obj = new ConnectionClass();
                String query = "UPDATE passenger SET name='" + name + "', age='" + age + "', dob='" + dob + "', address='" + address + "', phone='" + phone + "', email='" + email + "', nationality='" + nationality + "', gender='" + gender + "' WHERE username='" + username + "'";
                obj.stm.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Passenger details updated successfully");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == backButton) {
            frame.dispose();
            // Add code here to navigate back to the previous screen
        }
    }

    // Method to fetch passenger details based on the specified column and value
    private void fetchPassengerDetails(String column, String value) {
        try {
            ConnectionClass obj = new ConnectionClass();
            String query = "SELECT * FROM passenger WHERE " + column + "='" + value + "'";
            ResultSet rs = obj.stm.executeQuery(query);
            if (rs.next()) {
                usernameField.setText(rs.getString("username"));
                nameField.setText(rs.getString("name"));
                ageField.setText(rs.getString("age"));
                dobField.setText(rs.getString("dob"));
                addressField.setText(rs.getString("address"));
                phoneField.setText(rs.getString("phone"));
                emailField.setText(rs.getString("email"));
                nationalityField.setText(rs.getString("nationality"));
                genderField.setText(rs.getString("gender"));
                passportField.setText(rs.getString("passport"));
            } else {
                JOptionPane.showMessageDialog(null, "Passenger with " + column + " " + value + " not found");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new UpdatePassenger();
    }
}


