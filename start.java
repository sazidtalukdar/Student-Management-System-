import registation.StudentEnrollmentSystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class start {
    private static JFrame frame;
    private static JPanel panel;
    private static JTextField usernameField;
    private static JPasswordField passwordField;
    private static JCheckBox showPasswordCheckBox; // Added checkbox for showing password

    private static final String VALID_USERNAME = "admin";
    private static final String VALID_PASSWORD = "admin";

    public static void main(String[] args) {
        frame = new JFrame("Login System");
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(135, 206, 235)); // Sky Blue color
        // Use absolute positioning

        ImageIcon icon = new ImageIcon("Image/logo.png"); // Change "logo.png" to your actual logo file path
        JLabel logL = new JLabel(icon);
        logL.setBounds(14, 18, 170, 170);
        // Make the logoLabel round
        logL.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        logL.setHorizontalAlignment(SwingConstants.CENTER);
        logL.setVerticalAlignment(SwingConstants.CENTER);
        logL.setOpaque(true);
        logL.setBackground(Color.WHITE); // Change the background color to match the panel's color
        logL.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Create a black border
        logL.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Adjust the padding
        panel.add(logL);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(191, 30, 80, 25);
        panel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(270, 30, 150, 25);
        panel.add(usernameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(191, 60, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(270, 60, 150, 25);
        panel.add(passwordField);

        // Checkbox to show/hide password
        showPasswordCheckBox = new JCheckBox("Show Password");
        showPasswordCheckBox.setBounds(270, 85, 150, 25);
        showPasswordCheckBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (showPasswordCheckBox.isSelected()) {
                    passwordField.setEchoChar((char) 0); // Show password
                } else {
                    passwordField.setEchoChar('\u2022'); // Hide password
                }
            }
        });
        panel.add(showPasswordCheckBox);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(270, 115, 80, 25);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = String.valueOf(passwordField.getPassword());
                if (authenticate(username, password)) {
                    JOptionPane.showMessageDialog(frame, "Login successful! Welcome, " + username + "!");
                    openSE();
                    frame.dispose();
                    System.exit(
                        
                    );
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid username or password. Please try again.");
                }
            }
        });
        panel.add(loginButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    private static void openSE() {
        // Instantiate and open the StudentEnrollmentSystem
        StudentEnrollmentSystem S1 = new StudentEnrollmentSystem();
        S1.setVisible(true); // Assuming StudentEnrollmentSystem extends JFrame
    }


    private static boolean authenticate(String username, String password) {
        return username.equals(VALID_USERNAME) && password.equals(VALID_PASSWORD);
    }
}
