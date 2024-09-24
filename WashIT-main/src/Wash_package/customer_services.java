package Wash_package;

import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

class CustomerServices {
    private JFrame frame;
    private JPanel panel;
    private JButton placeOrderButton;
    private String customerUsername;

    public CustomerServices() {
        frame = new JFrame("Our Services");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1920, 1080);

        panel = new JPanel();
        frame.getContentPane().add(panel);
        panel.setLayout(null);
        
        Font labelFont = new Font("SansSerif", Font.BOLD, 50);
        JLabel lblMessage = new JLabel("OUR SERVICES");
        lblMessage.setFont(labelFont);
        lblMessage.setBounds(630, 50, 800, 60);
        lblMessage.setForeground(Color.WHITE);
        panel.add(lblMessage);

        JButton machineWashButton = new JButton("Machine Wash");
        machineWashButton.setBounds(650, 150, 150, 25);
        panel.add(machineWashButton);

        JButton clothHandwashButton = new JButton("Cloth Handwash");
        clothHandwashButton.setBounds(850, 150, 150, 25);
        panel.add(clothHandwashButton);

        JButton dryCleaningButton = new JButton("Dry Cleaning");
        dryCleaningButton.setBounds(650, 200, 150, 25);
        panel.add(dryCleaningButton);

        JButton bleachingWashButton = new JButton("Bleaching Wash");
        bleachingWashButton.setBounds(850, 200, 150, 25);
        panel.add(bleachingWashButton);

        JButton stainTreatmentButton = new JButton("Stain Treatment");
        stainTreatmentButton.setBounds(650, 250, 150, 25);
        panel.add(stainTreatmentButton);

        JButton ironingButton = new JButton("Ironing");
        ironingButton.setBounds(850, 250, 150, 25);
        panel.add(ironingButton);

        placeOrderButton = new JButton("Place Order");
        placeOrderButton.setBounds(850, 350, 150, 25);
        panel.add(placeOrderButton);
        placeOrderButton.setVisible(false);  // Initially set to invisible

        JButton addAddressButton = new JButton("Add Address");
        addAddressButton.setBounds(650, 350, 150, 25);
        panel.add(addAddressButton);

        frame.setVisible(true);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\amans\\OneDrive\\Pictures\\JAVA\\ServicesImage.jpg"));
        lblNewLabel.setBounds(0, 0, 1920, 1080);
        panel.add(lblNewLabel);
        
        // Add action listeners for the buttons
        machineWashButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processServiceSelection();
            }
        });

        clothHandwashButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processServiceSelection();
            }
        });

        dryCleaningButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processServiceSelection();
            }
        });

        bleachingWashButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processServiceSelection();
            }
        });

        stainTreatmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processServiceSelection();
            }
        });

        ironingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processServiceSelection();
            }
        });

        addAddressButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processAddress();
            }
        });

        // Add action listener for the "Place Order" button
        placeOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                placeOrder();
            }
        });
    }

    private void processServiceSelection() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/wash_orders", "root", "");

            String sql = "SELECT cloth_type, cloth_price FROM bill_db";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            StringBuilder message = new StringBuilder("Select the type of cloth and their price:\n");
            while (resultSet.next()) {
                String clothType = resultSet.getString("cloth_type");
                double clothPrice = resultSet.getDouble("cloth_price");
                message.append(clothType).append(" - $").append(clothPrice).append("\n");
            }

            String selectedClothType = JOptionPane.showInputDialog(frame, message.toString());

            if (selectedClothType != null) {
                int numOfTrousers = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter the number of trousers:"));
                int numOfShirtsTshirts = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter the number of shirts/t-shirts:"));

                double totalCost = calculateTotalCost(selectedClothType, numOfTrousers, numOfShirtsTshirts);

                JOptionPane.showMessageDialog(frame, "Total Cost: $" + totalCost);

                placeOrderButton.setVisible(true);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error fetching data from the database. Please try again.");
        }
    }

    	private void processAddress() {
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/wash_orders", "root", "");

                String checkAddressSql = "SELECT username FROM customer_address WHERE username = ?";
                PreparedStatement checkAddressStatement = connection.prepareStatement(checkAddressSql);
                checkAddressStatement.setString(1, customerUsername);
                ResultSet addressResultSet = checkAddressStatement.executeQuery();

                if (addressResultSet.next()) {
                    String currentAddress = addressResultSet.getString("address");
                    int choice = JOptionPane.showConfirmDialog(frame, "You already have an address on file:\n" + currentAddress +
                            "\nDo you want to edit it?", "Address Exists", JOptionPane.YES_NO_OPTION);

                    if (choice == JOptionPane.YES_OPTION) {
                        // Allow the user to edit the existing address (you can implement this logic)
                    }
                } else {
                    // Ask for additional details
                	String username = JOptionPane.showInputDialog(frame, "Enter username:");
                    String landmark = JOptionPane.showInputDialog(frame, "Enter landmark:");
                    String pincode = JOptionPane.showInputDialog(frame, "Enter pincode:");
                    String city = JOptionPane.showInputDialog(frame, "Enter city:");
                    String phoneNo = JOptionPane.showInputDialog(frame, "Enter phone number:");

                    // Store the new address in the database
                    String insertAddressSql = "INSERT INTO customer_address (username, landmark, pincode, city, phoneno) VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement insertAddressStatement = connection.prepareStatement(insertAddressSql);
                    insertAddressStatement.setString(1, username);
                    insertAddressStatement.setString(2, landmark);
                    insertAddressStatement.setString(3, pincode);
                    insertAddressStatement.setString(4, city);
                    insertAddressStatement.setString(5, phoneNo);
                    insertAddressStatement.executeUpdate();
                }

                addressResultSet.close();
                checkAddressStatement.close();
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(frame, "Error processing address. Please try again.");
            }
        }
    private void placeOrder() {
        JOptionPane.showMessageDialog(frame, "Order placed successfully!");
        // Implement the logic to store order details in the database
    }

    private double calculateTotalCost(String selectedClothType, int numOfTrousers, int numOfShirtsTshirts) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/wash_orders", "root", "");

            String sql = "SELECT cloth_price FROM bill_db WHERE cloth_type = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, selectedClothType);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                double clothPrice = resultSet.getDouble("cloth_price");

                double totalCost = clothPrice * (numOfTrousers + numOfShirtsTshirts);

                resultSet.close();
                preparedStatement.close();
                connection.close();

                return totalCost;
            } else {
                JOptionPane.showMessageDialog(frame, "Cloth type not found in the database.");
                return -1.0;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error fetching data from the database. Please try again.");
            return -1.0;
        }
    }
}

public class customer_services {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new CustomerServices();
        });
    }
}
