package AMS;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class AddPassenger extends JFrame implements ActionListener {
    JButton bt1, bt2;
    JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11, l12;
    JTextField tf1, tf2, tf3, tf4, tf5, tf6, tf7, tf8, tf9, tf10;
    JFrame f;

    AddPassenger() {
        f = new JFrame("Add Passenger Details");
        f.setBackground(Color.WHITE);
        f.setLayout(null);

        l1 = new JLabel();
        l1.setBounds(0, 0, 900, 600);
        l1.setLayout(null);

        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("AMS/icons/Passenger.jpg"));
        Image i1 = img.getImage().getScaledInstance(900, 600, Image.SCALE_SMOOTH);
        ImageIcon img2 = new ImageIcon(i1);
        l1.setIcon(img2);

        l2 = new JLabel("Username");
        l2.setBounds(50, 150, 190, 30);
        l2.setFont(new Font("Verdana", Font.BOLD, 25));
        l2.setForeground(Color.WHITE);
        l1.add(l2);

        l3 = new JLabel("Welcome to Airlines India");
        l3.setBounds(250, 50, 550, 50);
        l3.setFont(new Font("TImes New Roman", Font.BOLD, 38));
        l3.setForeground(new Color(0xfc3503));
        l1.add(l3);

        tf1 = new JTextField();
        tf1.setBounds(240, 150, 150, 30);
         tf1.setFont(new Font("Lato", Font.PLAIN, 17));
        l1.add(tf1);

        l4 = new JLabel("Name");
        l4.setBounds(450, 150, 200, 30);
        l4.setFont(new Font("Verdana", Font.BOLD, 25));
        l4.setForeground(Color.WHITE);
        l1.add(l4);

        tf2 = new JTextField();
        tf2.setBounds(660, 150, 150, 30);
         tf2.setFont(new Font("Lato", Font.PLAIN, 17));
        l1.add(tf2);

        l5 = new JLabel("Age");
        l5.setBounds(50, 200, 140, 30);
        l5.setFont(new Font("Verdana", Font.BOLD, 25));
        l5.setForeground(Color.WHITE);
        l1.add(l5);

        tf3 = new JTextField();
        tf3.setBounds(240, 200, 150, 30);
         tf3.setFont(new Font("Lato", Font.PLAIN, 17));
        l1.add(tf3);

        l6 = new JLabel("Date of Birth");
        l6.setBounds(450, 200, 200, 30);
        l6.setFont(new Font("Verdana", Font.BOLD, 25));
        l6.setForeground(Color.WHITE);
        l1.add(l6);

        tf4 = new JTextField();
        tf4.setBounds(660, 200, 150, 30);
         tf4.setFont(new Font("Lato", Font.PLAIN, 17));
        l1.add(tf4);

        l7 = new JLabel("Address");
        l7.setBounds(50, 250, 140, 30);
        l7.setFont(new Font("Verdana", Font.BOLD, 25));
        l7.setForeground(Color.WHITE);
        l1.add(l7);

        tf5 = new JTextField();
        tf5.setBounds(240, 250, 150, 30);
        tf5.setFont(new Font("Lato", Font.PLAIN, 17));
        l1.add(tf5);

        l8 = new JLabel("Phone");
        l8.setBounds(450, 250, 200, 30);
        l8.setFont(new Font("Verdana", Font.BOLD, 25));
        l8.setForeground(Color.WHITE);
        l1.add(l8);

        tf6 = new JTextField();
        tf6.setBounds(660, 250, 150, 30);
         tf6.setFont(new Font("Lato", Font.PLAIN, 17));
        l1.add(tf6);

        l9 = new JLabel("Email-id");
        l9.setBounds(50, 300, 140, 30);
        l9.setFont(new Font("Verdana", Font.BOLD, 25));
        l9.setForeground(Color.WHITE);
        l1.add(l9);

        tf7 = new JTextField();
        tf7.setBounds(240, 300, 150, 30);
         tf7.setFont(new Font("Lato", Font.PLAIN, 17));
        l1.add(tf7);

        l10 = new JLabel("Nationality");
        l10.setBounds(450, 300, 180, 30);
        l10.setFont(new Font("Verdana", Font.BOLD, 25));
        l10.setForeground(Color.WHITE);
        l1.add(l10);

        tf8 = new JTextField();
        tf8.setBounds(660, 300, 150, 30);
         tf8.setFont(new Font("Lato", Font.PLAIN, 17));
        l1.add(tf8);

        l11 = new JLabel("Gender");
        l11.setBounds(50, 350, 140, 30);
        l11.setFont(new Font("Verdana", Font.BOLD, 25));
        l11.setForeground(Color.WHITE);
        l1.add(l11);

        tf9 = new JTextField();
        tf9.setBounds(240, 350, 150, 30);
         tf9.setFont(new Font("Lato", Font.PLAIN, 17));
        l1.add(tf9);

        l12 = new JLabel("Passport No");
        l12.setBounds(450, 350, 190, 30);
        l12.setFont(new Font("Verdana", Font.BOLD, 25));
        l12.setForeground(Color.WHITE);
        l1.add(l12);

        tf10 = new JTextField();
        tf10.setBounds(660, 350, 150, 30);
         tf10.setFont(new Font("Lato", Font.PLAIN, 17));
        l1.add(tf10);

        bt1 = new JButton("Save");
        bt1.setBackground(Color.GREEN);
        bt1.setForeground(Color.WHITE);
        bt1.setFont(new Font("Verdana", Font.BOLD, 22));
        bt1.setBounds(250, 500, 150, 40);
        l1.add(bt1);

        bt2 = new JButton("Close");
        bt2.setBackground(Color.BLACK);
        bt2.setForeground(Color.WHITE);
         bt2.setFont(new Font("Verdana", Font.BOLD, 22));
        bt2.setBounds(450, 500, 150, 40);
        l1.add(bt2);

        bt1.addActionListener(this);
        bt2.addActionListener(this);

        f.add(l1);
        f.setVisible(true);
        f.setSize(900, 600);
        f.setLocation(310, 100);

    }

    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==bt1)
        {
            String username=tf1.getText();
            String name=tf2.getText();
            String age=tf3.getText();
            String dob=tf4.getText();
            String address=tf5.getText();
            String phone=tf6.getText();
            String email=tf7.getText();
            String nationality=tf8.getText();
            String gender=tf9.getText();
            String passport=tf10.getText();
            
            try
            {
                ConnectionClass obj=new ConnectionClass();
                String q="insert into passenger values('"+username+"' , '"+name+"' ,'"+age+"' ,'"+dob+"' ,'"+address+"' ,'"+phone+"' ,'"+email+"' ,'"+nationality+"' ,'"+gender+"' ,'"+passport+"')";
                obj.stm.executeUpdate(q);
                JOptionPane.showMessageDialog(null,"Details added successfully");
                f.setVisible(false);
                // new Homepage();
            }
            catch(Exception ex)
                    {
                        ex.printStackTrace();
                    }
                    
            
        }
        if(e.getSource()==bt2)
        {
             JOptionPane.showMessageDialog(null,"Are you sure to Cancel");
                f.setVisible(false);
        }
        
        
    }

    public static void main(String[] args) {
        new AddPassenger();
    }
}
