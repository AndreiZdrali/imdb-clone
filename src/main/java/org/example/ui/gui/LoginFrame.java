package org.example.ui.gui;

import org.example.ui.gui.views.LoginView;

import javax.swing.*;

public class LoginFrame extends JDialog {
    private LoginView loginView;

    public LoginFrame() {
        loginView = new LoginView(this);

        //ca sa astepte main thread-ul pana se inchide
        setModalityType(ModalityType.APPLICATION_MODAL);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setTitle("Login");
        setContentPane(loginView);
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }
}
