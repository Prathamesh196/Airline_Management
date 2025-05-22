package AMS;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class CheckPaymentDetails extends JFrame implements ActionListener {
    JTextField usernameField;
    JButton showButton;
    JFrame frame;

    CheckPaymentDetails() {
        frame = new JFrame("Check Payment Details");
        frame.setLayout(null);

        JLabel headingLabel = new JLabel("Check Payment Details");
        headingLabel.setBounds(200, 20, 300, 30);
        headingLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headingLabel.setForeground(Color.BLUE);
        frame.add(headingLabel);

        JLabel usernameLabel = new JLabel("Enter Username:");
        usernameLabel.setBounds(100, 70, 150, 20);
        frame.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(250, 70, 200, 20);
        frame.add(usernameField);

        showButton = new JButton("Show");
        showButton.setBounds(250, 100, 100, 30);
        showButton.addActionListener(this);
        frame.add(showButton);

        frame.setSize(600, 200);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == showButton) {
            String username = usernameField.getText();
            fetchPaymentDetails(username);
        }
    }

    public void fetchPaymentDetails(String username) {
        try {
            ConnectionClass obj = new ConnectionClass();
            String query = "SELECT bf.ticketId, bf.price, bf.journeyDate, bf.journeyTime, bf.username, COALESCE(cp.status, 'Success') AS status " +
                    "FROM bookflight bf LEFT JOIN checkpayment cp ON bf.username = cp.username " +
                    "WHERE bf.username = ?";
            PreparedStatement pst = obj.con.prepareStatement(query);
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();

            JFrame paymentFrame = new JFrame("Payment Details");
            paymentFrame.setLayout(new BorderLayout());

            JTable paymentTable = new JTable();
            JScrollPane scrollPane = new JScrollPane(paymentTable);
            paymentFrame.add(scrollPane, BorderLayout.CENTER);

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Ticket ID");
            model.addColumn("Price");
            model.addColumn("Journey Date");
            model.addColumn("Journey Time");
            model.addColumn("Username");
            model.addColumn("Status");

            while (rs.next()) {
                Object[] row = new Object[6];
                row[0] = rs.getString("ticketId");
                row[1] = rs.getDouble("price");
                row[2] = rs.getString("journeyDate");
                row[3] = rs.getString("journeyTime");
                row[4] = rs.getString("username");
                row[5] = rs.getString("status");
                model.addRow(row);
            }

            paymentTable.setModel(model);

            paymentFrame.setSize(800, 400);
            paymentFrame.setLocationRelativeTo(null);
            paymentFrame.setVisible(true);

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new CheckPaymentDetails();
    }
}



