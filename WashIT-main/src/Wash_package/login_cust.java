package Wash_package;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class login_customer {
    private JFrame frame;
    private JPanel panel;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public login_customer() {
        frame = new JFrame("CUSTOMER LOGIN");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1920, 1080);

        panel = new JPanel();
        frame.getContentPane().add(panel);
        panel.setLayout(null);
        
        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setBounds(600, 300, 80, 25);
        panel.add(lblUsername);

        usernameField = new JTextField();
        usernameField.setBounds(700, 300, 200, 25);
        panel.add(usernameField);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(600, 340, 80, 25);
        panel.add(lblPassword);

        passwordField = new JPasswordField();
        passwordField.setBounds(700, 340, 200, 25);
        panel.add(passwordField);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(750, 390, 100, 25);
        panel.add(loginButton);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\amans\\OneDrive\\Pictures\\JAVA\\LoginImage.jpg"));
        lblNewLabel.setBounds(0, 0, 1920, 1080);
        panel.add(lblNewLabel);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logincustomer();
            }
        });

        frame.setVisible(true);
    }

    private void logincustomer() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/wash_itdb", "root", "");

            String sql = "SELECT * FROM customer WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Successful login
                JOptionPane.showMessageDialog(frame, "Login successful!");
                openCustomerServicesPage();
            } else {
                // Failed login
                JOptionPane.showMessageDialog(frame, "Invalid username or password. Please try again.");
            }

            // Close resources
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            // Handle database-related errors, show an error message, or log the exception
            JOptionPane.showMessageDialog(frame, "Error during login. Please try again.");
        }
    }

    private void openCustomerServicesPage() {
        frame.dispose();
        SwingUtilities.invokeLater(() -> {
            new CustomerServices();
        });
    }
}

public class login_cust {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new login_customer();
        });
    }
}
