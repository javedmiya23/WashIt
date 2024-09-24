package Wash_package;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class sample {
    private JFrame frame;
    private JPanel panel;
    private JButton customerButton;
    private JButton ownerButton;
    private JButton loginownerButton;
    private JButton logincustomerButton;
    

    public sample() {
        frame = new JFrame("WASH IT");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);

        panel = new JPanel(); // Use the generic JPanel
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        Font labelFont = new Font("SansSerif", Font.BOLD, 50);
        JLabel lblLogin = new JLabel("WELCOME TO WASH IT");
        lblLogin.setFont(labelFont);
        lblLogin.setForeground(Color.BLACK);
        lblLogin.setBounds(500, 50, 800, 60);
        panel.add(lblLogin);

        customerButton = new JButton("REGISTER AS CUSTOMER");
        customerButton.setBounds(500, 150, 250, 40); // Adjust width to 250
        panel.add(customerButton);

        int gap = 20;
        ownerButton = new JButton("REGISTER AS OWNER");
        ownerButton.setBounds(500, 225, 250, 40);
        panel.add(ownerButton);
        frame.setVisible(true);
        
         gap = 40;
        loginownerButton = new JButton("LOGIN AS OWNER");
        loginownerButton.setBounds(825, 225, 250, 40);
        panel.add(loginownerButton);
        frame.setVisible(true);
        
         gap = 60;
        logincustomerButton = new JButton("LOGIN AS CUSTOMER");
        logincustomerButton.setBounds(825, 150, 250, 40);
        panel.add(logincustomerButton);
        frame.setVisible(true);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon("C:\\Users\\amans\\OneDrive\\Pictures\\JAVA\\BackgroundImage.jpg"));
        lblNewLabel.setBounds(0, 0, 1920, 1080);
        panel.add(lblNewLabel);
        
        customerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	opencustomerPage();
            }
        });

        ownerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	openownerPage();
            }
        });
        
        loginownerButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		openloginownerpage();
        	}
        	});
        
        logincustomerButton.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		openlogincustomerpage();
        	}
        	});

    }
    private void openownerPage() {
        frame.dispose();
        SwingUtilities.invokeLater(() -> {
             new ownerRegistration(); // Consider creating a separate class for login page
        });
    }

    private void opencustomerPage() {
        frame.dispose();
        SwingUtilities.invokeLater(() -> {
             new CustomerRegistration(); // Open the CustomerRegistration class
        });
    }
    
    private void openloginownerpage() {
        frame.dispose();
        SwingUtilities.invokeLater(() -> {
             new loginowner(); // Consider creating a separate class for login page
        });
    }
    
    private void openlogincustomerpage() {
        frame.dispose();
        SwingUtilities.invokeLater(() -> {
             new login_customer(); // Consider creating a separate class for login page
        });
    }



    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new sample();
        });
    }
}
