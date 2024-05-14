package User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Class.account;

public class forgot extends JFrame implements ActionListener, MouseListener {
    private JTextField field1, field2, field3, field4, field5;
    private JLabel titleLabel, usernameLabel, passwordLabel, newPasswordLabel, q1, q2;
    private JButton submitButton, backButton;

    public forgot() {
        super("Forgot Password");
        this.setSize(490, 480);
        this.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(240, 248, 255));

        titleLabel = new JLabel("Forgot Password");
        titleLabel.setBounds(160, 10, 200, 30);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        panel.add(titleLabel);

        usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(30, 60, 100, 25);
        panel.add(usernameLabel);

        field1 = new JTextField();
        field1.setBounds(150, 60, 250, 25);
        panel.add(field1);

        passwordLabel = new JLabel("New Password:");
        passwordLabel.setBounds(30, 100, 150, 25);
        panel.add(passwordLabel);

        field2 = new JPasswordField();
        field2.setBounds(150, 100, 250, 25);
        panel.add(field2);

        newPasswordLabel = new JLabel("Confirm Password:");
        newPasswordLabel.setBounds(30, 140, 150, 25);
        panel.add(newPasswordLabel);

        field3 = new JPasswordField();
        field3.setBounds(150, 140, 250, 25);
        panel.add(field3);

        q1 = new JLabel("Your Favourite Car ?");
        q1.setBounds(30, 180, 150, 25);
        panel.add(q1);

        field4 = new JTextField();
        field4.setBounds(150, 180, 250, 25);
        panel.add(field4);

        q2 = new JLabel("Your Birth City?");
        q2.setBounds(30, 220, 150, 25);
        panel.add(q2);

        field5 = new JTextField();
        field5.setBounds(150, 220, 250, 25);
        panel.add(field5);

        submitButton = new JButton("Submit");
        submitButton.setBounds(150, 280, 100, 30);
        submitButton.addMouseListener(this);
        submitButton.addActionListener(this);
        panel.add(submitButton);

        backButton = new JButton("Back");
        backButton.setBounds(270, 280, 100, 30);
        backButton.addMouseListener(this);
        backButton.addActionListener(this);
        panel.add(backButton);

        add(panel);
    }

    public void mouseClicked(MouseEvent me) {}

    public void mousePressed(MouseEvent me) {}

    public void mouseReleased(MouseEvent me) {}

    public void mouseEntered(MouseEvent me) {
        if (me.getSource() == submitButton) {
            submitButton.setBackground(Color.BLUE);
            submitButton.setForeground(Color.WHITE);
        } else if (me.getSource() == backButton) {
            backButton.setBackground(Color.BLUE);
            backButton.setForeground(Color.WHITE);
        }
    }

    public void mouseExited(MouseEvent me) {
        if (me.getSource() == submitButton) {
            submitButton.setBackground(new Color(46, 139, 87));
            submitButton.setForeground(Color.WHITE);
        } else if (me.getSource() == backButton) {
            backButton.setBackground(new Color(70, 130, 180));
            backButton.setForeground(Color.WHITE);
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submitButton) {
            String user = field1.getText();
            String pass = field2.getText();
            String newPass = field3.getText();
            String q1Text = field4.getText();
            String q2Text = field5.getText(); 
            account var = new account();
            if (!user.isEmpty() && !pass.isEmpty() && !newPass.isEmpty() && !q1Text.isEmpty() && !q2Text.isEmpty()) {
                if (pass.equals(newPass)) {
                    var.updatepass(user, newPass, q1Text, q2Text);
                } else {
                    JOptionPane.showMessageDialog(null, "New password and confirm password do not match.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please fill all fields.");
            }
        }

        if (ae.getSource() == backButton) {
            start er = new start();
            er.setVisible(true);
            dispose();
        }
    }
}
