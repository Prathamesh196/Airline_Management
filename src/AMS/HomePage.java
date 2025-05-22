package AMS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class HomePage extends JFrame implements ActionListener {
    JFrame frame;
    JPanel navigationPanel;
    JLabel imageLabel , headingLabel,l1;

    HomePage() {
        l1 = new JLabel();
        l1.setBounds(0, 0, 900, 600);
        l1.setLayout(null);
        
        frame = new JFrame("Home Page");
        frame.setLayout(new BorderLayout());
        frame.setBackground(new Color(240, 240, 240)); // Set background color
        
        
        imageLabel = new JLabel(new ImageIcon(getClass().getResource("/AMS/icons/airlinelogo.png")));
        imageLabel.setBounds(10, 200, 1600, 670);
        imageLabel.setForeground(Color.BLACK);
        
        frame.add(imageLabel);

        // Navigation panel
        navigationPanel = new JPanel(new GridLayout(1, 6));
        navigationPanel.setPreferredSize(new Dimension(frame.getWidth(), 50));
        navigationPanel.setBackground(new Color(255, 255, 255)); // Set background color

        String[] passengerProfileOptions = {"Add Passenger Details", "View Passenger Profile"};
        JComboBox<String> passengerProfileDropdown = new JComboBox<>(passengerProfileOptions);
        passengerProfileDropdown.addActionListener(this);
        passengerProfileDropdown.setFont(new Font("Verdana", Font.BOLD, 14)); // Set font
        passengerProfileDropdown.setBackground(new Color(255, 255, 255)); // Set background color
        passengerProfileDropdown.setForeground(new Color(0, 0, 0)); // Set foreground color
        navigationPanel.add(passengerProfileDropdown);

        String[] managePassengerOptions = {"Update Passenger Details"};
        JComboBox<String> managePassengerDropdown = new JComboBox<>(managePassengerOptions);
        managePassengerDropdown.addActionListener(this);
        managePassengerDropdown.setFont(new Font("Verdana", Font.BOLD, 14)); // Set font
        managePassengerDropdown.setBackground(new Color(255, 255, 255)); // Set background color
        managePassengerDropdown.setForeground(new Color(0, 0, 0)); // Set foreground color
        navigationPanel.add(managePassengerDropdown);

        String[] yourFlightOptions = {"Book Flight", "View Booked Flight"};
        JComboBox<String> yourFlightDropdown = new JComboBox<>(yourFlightOptions);
        yourFlightDropdown.addActionListener(this);
        yourFlightDropdown.setFont(new Font("Verdana", Font.BOLD, 14)); // Set font
        yourFlightDropdown.setBackground(new Color(255, 255, 255)); // Set background color
        yourFlightDropdown.setForeground(new Color(0, 0, 0)); // Set foreground color
        navigationPanel.add(yourFlightDropdown);

        String[] flightDetailsOptions = {"Journey Details", "Flight Zone"};
        JComboBox<String> flightDetailsDropdown = new JComboBox<>(flightDetailsOptions);
        flightDetailsDropdown.addActionListener(this);
        flightDetailsDropdown.setFont(new Font("Verdana", Font.BOLD, 14)); // Set font
        flightDetailsDropdown.setBackground(new Color(255, 255, 255)); // Set background color
        flightDetailsDropdown.setForeground(new Color(0, 0, 0)); // Set foreground color
        navigationPanel.add(flightDetailsDropdown);

        String[] cancellationOptions = {"Cancel Ticket", "View Canceled Ticket"};
        JComboBox<String> cancellationDropdown = new JComboBox<>(cancellationOptions);
        cancellationDropdown.addActionListener(this);
        cancellationDropdown.setFont(new Font("Verdana", Font.BOLD, 14)); // Set font
        cancellationDropdown.setBackground(new Color(255, 255, 255)); // Set background color
        cancellationDropdown.setForeground(new Color(0, 0, 0)); // Set foreground color
        navigationPanel.add(cancellationDropdown);

        String[] billOptions = {"Check Payment"};
        JComboBox<String> billDropdown = new JComboBox<>(billOptions);
        billDropdown.addActionListener(this);
        billDropdown.setFont(new Font("Verdana", Font.BOLD, 14)); // Set font
        billDropdown.setBackground(new Color(255, 255, 255)); // Set background color
        billDropdown.setForeground(new Color(0, 0, 0)); // Set foreground color
        navigationPanel.add(billDropdown);

        frame.add(navigationPanel, BorderLayout.NORTH);
  
            headingLabel = new JLabel("Fly with Us! Enjoy the Best Services");
        headingLabel.setFont(new Font("Georgia", Font.BOLD, 34));
        headingLabel.setForeground(Color.BLUE); // Set the foreground color to red
        headingLabel.setBounds(500, 120, 670, 30);
        frame.add(headingLabel);
        
        // Image section
        ImageIcon image = new ImageIcon("airlinelogo.png"); // Path to your image
        imageLabel = new JLabel(image);
        frame.add(imageLabel, BorderLayout.CENTER);

        frame.setSize(1200, 1400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComboBox<?> comboBox = (JComboBox<?>) e.getSource();
        String selectedItem = (String) comboBox.getSelectedItem();

        switch (selectedItem) {
            case "Add Passenger Details": // Changed from "Add Passenger Profile"
                new AddPassenger();
                break;
            case "View Passenger Profile":
                new ViewPassenger();
                break;
            case "Update Passenger Details":
                new UpdatePassenger();
                break;
            case "Book Flight":
                new BookFlight();
                break;
            case "View Booked Flight":
                new ViewBookedFlight();
                break;
            case "Journey Details":
                new FlightJourney();
                break;
            case "Flight Zone":
                new FlightZone();
                break;
            case "Cancel Ticket":
                new CancelFlight();
                break;
            case "View Canceled Ticket":
                new ViewCanceledTicket();
                break;
            case "Check Payment":
                new CheckPaymentDetails();
                break;
            default:
                break;
        }
    }

    public static void main(String[] args) {
        new HomePage();
    }
}