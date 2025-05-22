package AMS;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import javax.swing.table.DefaultTableCellRenderer;

public class ViewCanceledTicket extends JFrame {
    JTable canceledTicketsTable;
    JFrame frame;

    ViewCanceledTicket() {
        frame = new JFrame("View Canceled Tickets");
        frame.setLayout(new BorderLayout());

        // Create a table to display canceled tickets
        canceledTicketsTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(canceledTicketsTable);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Add a title label
        JLabel titleLabel = new JLabel("Canceled Tickets", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(51, 153, 255));
        frame.add(titleLabel, BorderLayout.NORTH);

        // Add a close button
        JButton closeButton = new JButton("Close");
        closeButton.setFont(new Font("Arial", Font.PLAIN, 14));
        closeButton.setForeground(Color.WHITE);
        closeButton.setBackground(new Color(51, 153, 255));
        closeButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        closeButton.addActionListener(e -> frame.dispose());
        frame.add(closeButton, BorderLayout.SOUTH);

        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Populate canceled tickets table
        populateCanceledTickets();
    }

    public void populateCanceledTickets() {
        try {
            ConnectionClass obj = new ConnectionClass();
            String query = "SELECT * FROM cancelflight";
            PreparedStatement pst = obj.con.prepareStatement(query);
            ResultSet rs = pst.executeQuery();

            // Create a table model to store the results
            DefaultTableModel model = new DefaultTableModel();
            ResultSetMetaData metaData = rs.getMetaData();

            // Get column count
            int columnCount = metaData.getColumnCount();

            // Add columns to the table model
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                model.addColumn(metaData.getColumnLabel(columnIndex));
            }

            // Add rows to the table model
            while (rs.next()) {
                Object[] row = new Object[columnCount];
                for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                    row[columnIndex - 1] = rs.getObject(columnIndex);
                }
                model.addRow(row);
            }

            // Set the table model to the canceledTicketsTable
            canceledTicketsTable.setModel(model);

            // Set column widths
            for (int columnIndex = 0; columnIndex < columnCount; columnIndex++) {
                canceledTicketsTable.getColumnModel().getColumn(columnIndex).setPreferredWidth(150);
            }

            // Set font and alignment for table cells
            canceledTicketsTable.setFont(new Font("Arial", Font.PLAIN, 14));
            canceledTicketsTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    component.setForeground(Color.BLACK);
                   
                return component;
                }
            });

            // Set row height
            canceledTicketsTable.setRowHeight(30);

            // Set table background color
            canceledTicketsTable.setBackground(new Color(240, 240, 240));

            // Set table border
            canceledTicketsTable.setBorder(BorderFactory.createLineBorder(new Color(51, 153, 255), 2));

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ViewCanceledTicket();
    }
}