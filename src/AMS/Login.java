package AMS;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    JLabel l1, l2, l3, l4, dontHaveAccountLabel; // Added dontHaveAccountLabel
    JButton bt1, bt3; // Removed bt2 since it's not used
    JPasswordField pf;
    JTextField tf;
    JPanel panel; // Added panel to hold components
    JFrame f;

    Login() {
        f = new JFrame();
        f.setTitle("Login Account");
        f.setBackground(Color.WHITE);
        f.setSize(1000, 600);
        f.setLocationRelativeTo(null); // Center the frame on the screen
        f.setLayout(new BorderLayout()); // Set BorderLayout for the frame's content pane

        panel = new JPanel(null); // Use null layout for the panel
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(1000, 600)); // Set preferred size for the panel

        l1 = new JLabel();
        l1.setBounds(0, 0, 1000, 600); // Set bounds to cover the entire frame
        l1.setLayout(null);

        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("AMS/icons/loginn.jpg"));
        Image i1 = img.getImage().getScaledInstance(1000, 600, Image.SCALE_SMOOTH); // Adjusted image size to fit the frame
        ImageIcon img2 = new ImageIcon(i1);
        l1.setIcon(img2);

        l2 = new JLabel("Username");
        l2.setBounds(250, 140, 230, 30); // Adjusted bounds for l2
        l2.setForeground(Color.BLACK);
        l2.setFont(new Font("Verdana", Font.BOLD, 26));
        l1.add(l2);

        l3 = new JLabel("Login Account");
        l3.setBounds(388, 30, 400, 50); // Adjusted bounds for l3
        l3.setForeground(Color.BLUE);
        l3.setFont(new Font("Georgia", Font.BOLD, 40));
        panel.add(l3);

        l4 = new JLabel("Password");
        l4.setBounds(250, 190, 200, 30); // Adjusted bounds for l4
        l4.setForeground(Color.BLACK);
        l4.setFont(new Font("Verdana", Font.BOLD, 26));
        l1.add(l4);

        tf = new JTextField();
        tf.setBounds(430, 140, 250, 30);
        tf.setFont(new Font("Verdana", Font.PLAIN, 17));
        l1.add(tf);

        pf = new JPasswordField();
        pf.setBounds(430, 190, 250, 30);
        pf.setFont(new Font("Verdana", Font.PLAIN, 17));// Adjusted bounds for pf
        l1.add(pf);

        bt1 = new JButton("Login");
        bt1.setBackground(Color.GREEN);
        bt1.setForeground(Color.WHITE);
        bt1.setFont(new Font("Garamond", Font.BOLD, 22));
        bt1.setBounds(420, 280, 150, 40); // Modified bounds for bt1
        l1.add(bt1);

        bt3 = new JButton("Signup"); // Added signup button below login button
        bt3.setBackground(Color.BLACK);
        bt3.setFont(new Font("Garamond", Font.BOLD, 22));
        bt3.setForeground(Color.WHITE);
        bt3.setBounds(420, 380, 150, 40); // Modified bounds for bt3
        l1.add(bt3);

        // Added dontHaveAccountLabel
        dontHaveAccountLabel = new JLabel("Don't have an account?then signup");
        dontHaveAccountLabel.setForeground(Color.BLUE);
        dontHaveAccountLabel.setFont(new Font("Cambria", Font.ITALIC, 25));
        dontHaveAccountLabel.setBounds(330, 340, 540, 35);
        l1.add(dontHaveAccountLabel);

        bt1.addActionListener(this);
        bt3.addActionListener(this); // Added action listener for signup button

        panel.add(l1); // Add the label to the panel
        f.add(panel, BorderLayout.CENTER); // Add the panel to the center of the frame

        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bt1) {
            String username = tf.getText();
            String password = pf.getText();
            try {
                ConnectionClass obj = new ConnectionClass();
                String q = "SELECT * FROM signup WHERE username=? AND password=?";
                PreparedStatement pst = obj.con.prepareStatement(q);
                pst.setString(1, username);
                pst.setString(2, password);
                ResultSet rs = pst.executeQuery();
                if (rs.next()) {
                    new HomePage().setVisible(true);
                    f.setVisible(false);
                    // Open AddPassenger when login is successful
                   
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter valid username or password");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } 
         else if (e.getSource() == bt3) { // Handle signup button click
            f.setVisible(false);
            new Signup().setVisible(true); // Open Signup.java
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}




