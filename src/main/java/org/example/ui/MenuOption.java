package org.example.ui;

import kotlin.NotImplementedError;
import org.example.ui.cli.MainMenuCLI;

public class MenuOption {
    private String description;
    private Runnable actionCli;
    private Runnable actionGui;

    public MenuOption(String description, Runnable actionCli, Runnable actionGui) {
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
        public static final MenuOption VIEW_PRODUCTIONS_DETAILS = new MenuOption(
                "View productions details",
                MainMenuCLI::viewProductionsDetails,
                null
        );

        public static final MenuOption VIEW_ACTORS_DETAILS = new MenuOption(
                "View actors details",
                MainMenuCLI::viewActorsDetails,
                null
        );

        public static final MenuOption VIEW_NOTIFICATIONS = new MenuOption(
                "View your notifications",
                null,
                null
        );

        public static final MenuOption SEARCH_FOR_LISTING = new MenuOption(
                "Search for a listing",
                null,
                null
        );

        public static final MenuOption ADD_DELETE_FAVORITE = new MenuOption(
                "Manage your favorites",
                null,
                null
        );

        public static final MenuOption CREATE_REMOVE_REQUEST = new MenuOption(
                "Create/Remove a request",
                null,
                null
        );

        public static final MenuOption ADD_DELETE_LISTING = new MenuOption(
                "Add/Delete a listing",
                null,
                null
        );

        public static final MenuOption UPDATE_LISTING = new MenuOption(
                "Update a listing",
                null,
                null
        );

        public static final MenuOption SOLVE_REQUEST = new MenuOption(
                "Solve a request",
                null,
                null
        );
        
        public static final MenuOption ADD_DELETE_REVIEW = new MenuOption(
                "Add/Delete a review",
                null,
                null
        );

        public static final MenuOption ADD_DELETE_USER = new MenuOption(
                "Add/Delete user",
                null,
                null
        );

        public static final MenuOption LOGOUT = new MenuOption(
                "Logout",
                null,
                null
        );
    }
}
