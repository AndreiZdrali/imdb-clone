package org.example.ui.gui;

import org.example.IMDB;
import org.example.services.UserService;
import org.example.user.User;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class LoginView extends JPanel {
    private JTextField userName;
    private JPasswordField password;
    private JButton loginButton;
    private JButton exitButton;

    public LoginView() {
        setLayout(new GridLayout(3, 2));

        userName = new JTextField();
        password = new JPasswordField();

        loginButton = new JButton("Login");
        exitButton = new JButton("Exit");

        loginButton.addActionListener(e -> {
            String userNameText = userName.getText();
            String passwordText = new String(password.getPassword());

            for (User<?> user : IMDB.getInstance().getUsers()) {
                if (user.checkLogin(userNameText, passwordText)) {
                    IMDB.getInstance().getUserInterface().setCurrentUser(user);
                    IMDB.getInstance().getUserInterface().setView(AppFrame.ViewsList.MAIN_MENU);
                    return;
                }
            }
            JOptionPane.showMessageDialog(this, "Invalid username or password!", "Error", JOptionPane.ERROR_MESSAGE);
        });

        exitButton.addActionListener(e -> System.exit(0));

        add(new JLabel("Username:"));
        add(userName);
        add(new JLabel("Password:"));
        add(password);
        add(loginButton);
        add(exitButton);
    }
}
