package org.example.ui.gui;

import javax.swing.*;
import java.awt.*;

public class AppFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;

    public AppFrame() {
        super("IMDB");

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        setLayout(cardLayout);

        cardPanel.add(new LoginView(), ViewsList.LOGIN);
        cardPanel.add(new MainMenuView(), ViewsList.MAIN_MENU);

        add(cardPanel);

        cardLayout.show(cardPanel, ViewsList.LOGIN);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }

    public void setView(String viewName) {
        cardLayout.show(cardPanel, viewName);
    }

    public static class ViewsList {
        public static final String LOGIN = "login";
        public static final String MAIN_MENU = "mainMenu";
    }
}
