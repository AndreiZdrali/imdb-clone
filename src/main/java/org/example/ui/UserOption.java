package org.example.ui;

import kotlin.NotImplementedError;

import java.util.List;

public class UserOption {
    private String description;
    private Runnable actionCli;
    private Runnable actionGui;

    public UserOption(String description, Runnable actionCli, Runnable actionGui) {
        this.description = description;
        this.actionCli = actionCli;
        this.actionGui = actionGui;
    }

    public String getDescription() {
        return description;
    }

    public Runnable getActionCli() {
        return actionCli;
    }

    public Runnable getActionGui() {
        return actionGui;
    }

    public void executeCLI() {
        actionCli.run();
    }

    public void executeGUI() {
        actionGui.run();
        //TODO: Remove asta, incredibil de inutila
        throw new NotImplementedError("GUI not implemented yet");
    }

    public static class List {
        public static final UserOption VIEW_PRODUCTIONS_DETAILS = new UserOption(
                "View productions details",
                null,
                null
        );

        public static final UserOption VIEW_ACTORS_DETAILS = new UserOption(
                "View actors details",
                null,
                null
        );

        public static final UserOption VIEW_NOTIFICATIONS = new UserOption(
                "View notifications",
                null,
                null
        );

        public static final UserOption SEARCH_FOR_LISTING = new UserOption(
                "Search for listing",
                null,
                null
        );

        public static final UserOption MANAGE_FAVORITES = new UserOption(
                "Manage favorites",
                null,
                null
        );

        public static final UserOption MANAGE_USERS = new UserOption(
                "Manage users",
                null,
                null
        );

        public static final UserOption MANAGE_LISTINGS = new UserOption(
                "Manage listings",
                null,
                null
        );

        public static final UserOption MANAGE_REQUESTS = new UserOption(
                "Manage requests",
                null,
                null
        );

        public static final UserOption LOGOUT = new UserOption(
                "Logout",
                null,
                null
        );
    }
}
