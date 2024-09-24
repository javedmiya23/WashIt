package Wash_package;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

 class ownerRegistration {
    private JFrame frame;
    private JPanel panel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField emailField;

    public ownerRegistration() {
        frame = new JFrame("owner2 Registration");
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
        
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(600, 380, 80, 25);
        panel.add(lblEmail);

        emailField = new JTextField();
        emailField.setBounds(700, 380, 200, 25);
        panel.add(emailField);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(750, 420, 100, 25);
        panel.add(registerButton);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\amans\\OneDrive\\Pictures\\JAVA\\LoginImage.jpg"));
        lblNewLabel.setBounds(0, 0, 1920, 1080);
        panel.add(lblNewLabel);

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerowner();
            }
        });

        frame.setVisible(true);
    }
    
    

    private void registerowner() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String email = emailField.getText();

        try {
            // Establish database connection
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/wash_itdb", "root", "");

            // Prepare the SQL statement
            String sql = "INSERT INTO laundry_owner (username, email,password) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);

            // Execute the query
            preparedStatement.executeUpdate();

            // Close resources
            preparedStatement.close();
            connection.close();

            // Optionally, you can show a success message or navigate to another page
            JOptionPane.showMessageDialog(frame, "Customer registered successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle database-related errors, show an error message, or log the exception
            JOptionPane.showMessageDialog(frame, "Error registering customer. Please try again.");
        }
    }
 public class owner_reg {
	    public static void main(String[] args) {
	        SwingUtilities.invokeLater(() -> {
	            new ownerRegistration();
	        });
	    }
	}
 }
