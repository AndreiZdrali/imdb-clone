package org.example.ui.gui;

import org.example.IMDB;
import org.example.user.User;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JPanel {
    private JTextField email;
    private JPasswordField password;
    private JButton loginButton;
    private JButton exitButton;

    public LoginView() {
        setLayout(new GridLayout(3, 2));

        email = new JTextField();
        password = new JPasswordField();

        loginButton = new JButton("Login");
        loginButton.addActionListener(e -> loginButtonActionListener());

        exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));

        add(new JLabel("Email:"));
        add(email);
        add(new JLabel("Password:"));
        add(password);
        add(loginButton);
        add(exitButton);
    }

    private void loginButtonActionListener() {
        String userNameText = email.getText();
        String passwordText = new String(password.getPassword());

        for (User<?> user : IMDB.getInstance().getUsers()) {
            if (user.checkLogin(userNameText, passwordText)) {
                IMDB.getInstance().getUserInterface().setCurrentUser(user);
                IMDB.getInstance().getUserInterface().setView(MenuOption.List.MAIN_MENU.getName());
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Invalid email or password!", "Error", JOptionPane.ERROR_MESSAGE);
    }
}
