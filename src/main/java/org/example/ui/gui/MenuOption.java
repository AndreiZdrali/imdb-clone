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
                "Home"
        );

        public static final MenuOption PRODUCTIONS = new MenuOption(
                new ProductionsView(),
                "Productions"
        );

        public static final MenuOption ACTORS = new MenuOption(
                new ActorsView(),
                "Actors"
        );

        public static final MenuOption NOTIFICATIONS = new MenuOption(
                new NotificationsView(),
                "Notifications"
        );

        public static final MenuOption FAVORITES = new MenuOption(
                new FavoritesView(),
                "Favorites"
        );

        public static final MenuOption CREATE_REMOVE_REQUEST = new MenuOption(
                new CreateRemoveRequestView(),
                "My requests"
        );

        public static final MenuOption SOLVE_REQUEST = new MenuOption(
                new SolveRequestView(),
                "Solve a request"
        );

        public static final MenuOption MANAGE_USERS = new MenuOption(
                new ManageUsersView(),
                "Manage Users"
        );
    }
}
