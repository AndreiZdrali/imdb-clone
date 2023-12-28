package org.example.ui.gui.views;

import org.example.IMDB;
import org.example.ui.gui.MenuOption;
import org.example.user.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

public class LoginView extends JPanel {
    private JTextField email;
    private JPasswordField password;
    private JButton loginButton;
    private JButton exitButton;

    public LoginView(JDialog parent) {
        setLayout(new GridLayout(3, 2));
        setPreferredSize(new Dimension(300, 100));

        email = new JTextField();
        email.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER)
                    loginButtonActionListener(parent);
            }
        });

        password = new JPasswordField();
        password.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                if (evt.getKeyCode() == KeyEvent.VK_ENTER)
                    loginButtonActionListener(parent);
            }
        });

        loginButton = new JButton("Login");
        loginButton.addActionListener(e -> loginButtonActionListener(parent));


        exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));

        add(new JLabel("Email:"));
        add(email);
        add(new JLabel("Password:"));
        add(password);
        add(loginButton);
        add(exitButton);
    }

    private void loginButtonActionListener(JDialog parent) {
        String userNameText = email.getText();
        String passwordText = new String(password.getPassword());

        for (User<?> user : IMDB.getInstance().getUsers()) {
            if (user.checkLogin(userNameText, passwordText)) {
                IMDB.getInstance().getUserInterface().setCurrentUser(user);
                parent.dispose();
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Invalid email or password!", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
