package AMS;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class AddAirlineEmployee extends JFrame implements ActionListener {
    JButton bt1, bt2;
    JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12;
    JTextField tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8, tf9, tf10;
    JFrame f;

    AddAirlineEmployee() {
        f = new JFrame("New Employee");
        f.setBackground(Color.WHITE);
        f.setLayout(null);

        l1 = new JLabel();
        l1.setBounds(0, 0, 900, 600);
        l1.setLayout(null);

        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("AMS/icons/laptop.png"));
        Image i1 = img.getImage().getScaledInstance(900, 600, Image.SCALE_SMOOTH);
        ImageIcon img2 = new ImageIcon(i1);
        l1.setIcon(img2);

        l2 = new JLabel("Name");
        l2.setBounds(50, 150, 150, 30);
        l2.setFont(new Font("Arial", Font.BOLD, 20));
        l2.setForeground(new Color(0xFFD700));
        l1.add(l2);

        l3 = new JLabel("New Employee Details");
        l3.setBounds(270, 50, 550, 50);
        l3.setFont(new Font("Times New Roman", Font.BOLD, 37));
        l3.setForeground(new Color(0xedf2fa));
        l1.add(l3);

        tf1 = new JTextField();
        tf1.setBounds(210, 150, 150, 30);
        tf1.setFont(new Font("Verdana", Font.PLAIN, 16)); // Change font here
        tf1.setBorder(BorderFactory.createLineBorder(new Color(0xFFD700), 2));
        l1.add(tf1);

        l4 = new JLabel("Address");
        l4.setBounds(450, 150, 200, 30);
        l4.setFont(new Font("Verdana", Font.BOLD, 20));
        l4.setForeground(new Color(0xFFD700));
        l1.add(l4);

        tf2 = new JTextField();
        tf2.setBounds(620, 150, 150, 30);
        tf2.setFont(new Font("Verdana", Font.PLAIN, 16)); // Change font here
        tf2.setBorder(BorderFactory.createLineBorder(new Color(0xFFD700), 2));
        l1.add(tf2);

        l5 = new JLabel("Age");
        l5.setBounds(50, 200, 100, 30);
        l5.setFont(new Font("Verdana", Font.BOLD, 20));
        l5.setForeground(new Color(0xFFD700));
        l1.add(l5);

        tf3 = new JTextField();
        tf3.setBounds(210, 200, 150, 30);
        tf3.setFont(new Font("Verdana", Font.PLAIN, 16)); // Change font here
        tf3.setBorder(BorderFactory.createLineBorder(new Color(0xFFD700), 2));
        l1.add(tf3);

        l6 = new JLabel("Date of Birth");
       l6.setBounds(450, 200, 200, 30);
        l6.setFont(new Font("Verdana", Font.BOLD, 20));
        l6.setForeground(new Color(0xFFD700));
        l1.add(l6);

        tf4 = new JTextField();
        tf4.setBounds(620, 200, 150, 30);
        tf4.setFont(new Font("Verdana", Font.PLAIN, 16)); // Change font here
        tf4.setBorder(BorderFactory.createLineBorder(new Color(0xFFD700), 2));
        l1.add(tf4);

        l8 = new JLabel("Phone");
        l8.setBounds(450, 250, 200, 30);
        l8.setFont(new Font("Verdana", Font.BOLD, 20));
        l8.setForeground(new Color(0xFFD700));
        l1.add(l8);

        tf5 = new JTextField();
        tf5.setBounds(620, 250, 150, 30);
        tf5.setFont(new Font("Verdana", Font.PLAIN, 16)); // Change font here
        tf5.setBorder(BorderFactory.createLineBorder(new Color(0xFFD700), 2));
        l1.add(tf5);

        l9 = new JLabel("Email-id");
        l9.setBounds(50, 250, 100, 30);
        l9.setFont(new Font("Verdana", Font.BOLD, 20));
        l9.setForeground(new Color(0xFFD700));
        l1.add(l9);

        tf6 = new JTextField();
        tf6.setBounds(210, 250, 150, 30);
        tf6.setFont(new Font("Verdana", Font.PLAIN, 16)); // Change font here
        tf6.setBorder(BorderFactory.createLineBorder(new Color(0xFFD700), 2));
        l1.add(tf6);

        l10 = new JLabel("Nationality");
        l10.setBounds(450, 300, 150, 30);
        l10.setFont(new Font("Verdana", Font.BOLD, 20));
        l10.setForeground(new Color(0xFFD700));
        l1.add(l10);

        tf7 = new JTextField();
        tf7.setBounds(620, 300, 150, 30);
        tf7.setFont(new Font("Verdana", Font.PLAIN, 16)); // Change font here
        tf7.setBorder(BorderFactory.createLineBorder(new Color(0xFFD700), 2));
        l1.add(tf7);

        l11 = new JLabel("Gender");
        l11.setBounds(50, 300, 100, 30);
        l11.setFont(new Font("Verdana", Font.BOLD, 20));
        l11.setForeground(new Color(0xFFD700));
        l1.add(l11);

        tf8 = new JTextField();
        tf8.setBounds(210, 300, 150, 30);
        tf8.setFont(new Font("Verdana", Font.PLAIN, 16)); // Change font here
        tf8.setBorder(BorderFactory.createLineBorder(new Color(0xFFD700), 2));
        l1.add(tf8);

        l12 = new JLabel("Date of Joining");
        l12.setBounds(50, 350, 160, 30);
        l12.setFont(new Font("Verdana", Font.BOLD, 17));
        l12.setForeground(new Color(0xFFD700));
        l1.add(l12);

        tf9 = new JTextField();tf9.setBounds(210, 350, 150, 30);
        tf9.setFont(new Font("Verdana", Font.PLAIN, 16)); // Change font here
        tf9.setBorder(BorderFactory.createLineBorder(new Color(0xFFD700), 2));
        l1.add(tf9);

        bt1 = new JButton("Save");
        bt1.setBackground(new Color(0xFFD700));
        bt1.setForeground(Color.BLACK);
        bt1.setBounds(240, 500, 150, 40);
        bt1.setFont(new Font("Arial", Font.BOLD, 16));
        bt1.setBorder(BorderFactory.createLineBorder(new Color(0xFFD700), 2));
        l1.add(bt1);

        bt2 = new JButton("Close");
        bt2.setBackground(new Color(0xFFD700));
        bt2.setForeground(Color.BLACK);
        bt2.setBounds(510, 500, 150, 40);
        bt2.setFont(new Font("Arial", Font.BOLD, 16));
        bt2.setBorder(BorderFactory.createLineBorder(new Color(0xFFD700), 2));
        l1.add(bt2);

        bt1.addActionListener(this);
        bt2.addActionListener(this);

        f.add(l1);
        f.setVisible(true);
        f.setSize(900, 600);
        f.setLocation(310, 100);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == bt1) {
            String name = tf1.getText();
            String address = tf2.getText();
            String age = tf3.getText();
            String dob = tf4.getText();
            String phone = tf5.getText();
            String email = tf6.getText();
            String nationality = tf7.getText();
            String gender = tf8.getText();
            String dateofjoining = tf9.getText();

            try {
                ConnectionClass obj = new ConnectionClass();
                String q = "insert into AddEmployee values('" + name + "' , '" + address + "' ,'" + age + "' ,'" + dob + "' , '" + phone + "' ,'" + email + "' ,'" + nationality + "' ,'" + gender + "' ,'" + dateofjoining + "')";
                obj.stm.executeUpdate(q);
                JOptionPane.showMessageDialog(null, "Details added successfully");
                f.setVisible(false);
                // new Homepage();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == bt2) {
            JOptionPane.showMessageDialog(null, "Are you sure to Cancel");
            f.setVisible(false);
        }
    }

    public static void main(String[] args) {
        new AddAirlineEmployee();
    }
}