package User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import registation.*;
import Class.*;

public class start extends JFrame implements ActionListener, MouseListener {
    private JPanel panel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JCheckBox showPasswordCheckBox;
    private JButton submitButton,backButton,forgotPasswordButton;
    private ImageIcon icon;
    private JLabel logL ,passwordLabel,usernameLabel;

    public start() {
        super("Login Page");
        this.setSize(500, 300);
        this.setLocationRelativeTo(null);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(135, 206, 235));

        
        icon = new ImageIcon("Image/logo.png");
        logL = new JLabel(icon);
        logL.setBounds(14, 18, 170, 170);
        logL.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        panel.add(logL);

      
        usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(191, 30, 80, 25);
        panel.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(270, 30, 150, 25);
        panel.add(usernameField);

    
        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(191, 60, 80, 25);
        panel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(270, 60, 150, 25);
        panel.add(passwordField);

        showPasswordCheckBox = new JCheckBox("Show Password");
        showPasswordCheckBox.setBounds(270, 85, 150, 25);
        showPasswordCheckBox.addActionListener(this);
        panel.add(showPasswordCheckBox);

        
        submitButton = new JButton("Login");
        submitButton.setBounds(190, 115, 90, 30);
        submitButton.setForeground(Color.WHITE);
        submitButton.setFont(new Font("Arial", Font.BOLD, 14));
        submitButton.setBackground(new Color(46, 139, 87));
        submitButton.addActionListener(this);
        submitButton.addMouseListener(this); 
        panel.add(submitButton);

        
        backButton = new JButton("SignUp");
        backButton.setBounds(290, 115, 90, 30);
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(new Color(70, 130, 180));
        backButton.addActionListener(this);
        backButton.addMouseListener(this);
        panel.add(backButton);

        
        forgotPasswordButton = new JButton("Forgot Password");
        forgotPasswordButton.setBounds(190, 155, 190, 30);
        forgotPasswordButton.setForeground(Color.WHITE);
        forgotPasswordButton.setFont(new Font("Arial", Font.BOLD, 14));
        forgotPasswordButton.setBackground(new Color(255, 69, 0));
        forgotPasswordButton.addActionListener(this);
        forgotPasswordButton.addMouseListener(this); 
        panel.add(forgotPasswordButton);

        this.add(panel);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submitButton) {
            String username = usernameField.getText();
            String password = passwordField.getText();
            
            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(start.this, "All Fields Needed");
            } else {
                account rr = new account();
                if (rr.checkAccount(username, password).equals("sir ace")) {
                    StudentEnrollmentSystem st = new StudentEnrollmentSystem(username);
                    JOptionPane.showMessageDialog(start.this, "Login successful! Welcome, " + username + "!");
                    st.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(start.this, "Invalid username or password. Please try again.");
                }
            }
        }


        if (ae.getSource() == backButton) {
            RegistrationPage dd = new RegistrationPage();
            dd.setVisible(true);
            dispose();
        }
        if (ae.getSource() == forgotPasswordButton) {
            forgot var = new forgot();
            var.setVisible(true);
            dispose();
        }
        if(ae.getSource()==showPasswordCheckBox){
            if (showPasswordCheckBox.isSelected()) {
                passwordField.setEchoChar((char) 0);
            }

        }
    }

    public void mouseClicked(MouseEvent me) {
    }

    public void mousePressed(MouseEvent me) {
    }

    public void mouseReleased(MouseEvent me) {
    }

    public void mouseEntered(MouseEvent me) {
        if (me.getSource() == submitButton) {
            submitButton.setBackground(Color.BLUE);
            submitButton.setForeground(Color.WHITE);
        } else if (me.getSource() == backButton) {
            backButton.setBackground(Color.BLUE);
            backButton.setForeground(Color.WHITE);
        } else if (me.getSource() == forgotPasswordButton) {
            forgotPasswordButton.setBackground(Color.BLUE);
            forgotPasswordButton.setForeground(Color.WHITE);
        }
    }

    public void mouseExited(MouseEvent me) {
        if (me.getSource() == submitButton) {
            submitButton.setBackground(new Color(46, 139, 87));
            submitButton.setForeground(Color.WHITE);
        } else if (me.getSource() == backButton) {
            backButton.setBackground(new Color(70, 130, 180));
            backButton.setForeground(Color.WHITE);
        } else if (me.getSource() == forgotPasswordButton) {
            forgotPasswordButton.setBackground(new Color(255, 69, 0));
            forgotPasswordButton.setForeground(Color.WHITE);
        }
    }
    
}
