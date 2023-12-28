package org.example.ui.gui.views;

import javax.swing.*;
import java.awt.*;

public class HomeView extends JPanel {
    private JButton logoutButton;

    public HomeView() {
        setLayout(new GridLayout(1, 1));

        logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> System.exit(0));

        add(logoutButton);
    }
}
