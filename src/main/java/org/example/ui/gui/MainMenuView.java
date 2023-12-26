package org.example.ui.gui;

import javax.swing.*;
import java.awt.*;

public class MainMenuView extends JPanel {
    private JButton logoutButton;

    public MainMenuView() {
        setLayout(new GridLayout(1, 1));

        logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> System.exit(0));

        add(logoutButton);
    }
}
