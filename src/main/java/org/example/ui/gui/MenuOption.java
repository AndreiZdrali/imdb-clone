package org.example.ui.gui;

import org.example.ui.gui.views.ActorsView;
import org.example.ui.gui.views.FavoritesView;
import org.example.ui.gui.views.HomeView;
import org.example.ui.gui.views.ProductionsView;

import javax.swing.*;

public class MenuOption {
    private JPanel panel;
    private String name;

    public MenuOption(JPanel panel, String name) {
        this.panel = panel;
        this.name = name;
    }

    public JPanel getPanel() {
        return panel;
    }

    public String getName() {
        return name;
    }

    public static class List {
        public static final MenuOption HOME = new MenuOption(
                new HomeView(),
                "Home");

        public static final MenuOption PRODUCTIONS = new MenuOption(
                new ProductionsView(),
                "View productions");

        public static final MenuOption ACTORS = new MenuOption(
                new ActorsView(),
                "View actors");

        public static final MenuOption FAVORITES = new MenuOption(
                new FavoritesView(),
                "View favorites");
    }
}
