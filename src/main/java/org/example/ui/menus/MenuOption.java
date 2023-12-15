package org.example.ui.menus;

import kotlin.NotImplementedError;
import org.example.IMDB;
import org.example.ui.cli.MainMenuCLI;
import org.example.ui.cli.NotificationsCLI;

import java.awt.*;

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
        //TODO: Remove asta, incredibil de inutila, gui o sa mearga altfel cred
        throw new NotImplementedError("GUI not implemented yet");
    }

    @Override
    public String toString() {
        return getDescription();
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
                MainMenuCLI::viewNotifications,
                null
        );

        public static final MenuOption CLEAR_NOTIFICATIONS = new MenuOption(
                "Clear your notifications",
                NotificationsCLI::clearNotifications,
                null
        );

        public static final MenuOption SEARCH_FOR_LISTING = new MenuOption(
                "Search for a listing",
                MainMenuCLI::searchForListing,
                null
        );

        public static final MenuOption ADD_DELETE_FAVORITE = new MenuOption(
                "Manage your favorites",
                MainMenuCLI::addDeleteFavorite,
                null
        );

        public static final MenuOption CREATE_REMOVE_REQUEST = new MenuOption(
                "Create/Remove a request",
                MainMenuCLI::createRemoveRequest,
                null
        );

        public static final MenuOption ADD_DELETE_LISTING = new MenuOption(
                "Add/Delete a listing",
                MainMenuCLI::addDeleteListing,
                null
        );

        public static final MenuOption UPDATE_LISTING = new MenuOption(
                "Update a listing",
                MainMenuCLI::updateListing,
                null
        );

        public static final MenuOption SOLVE_REQUEST = new MenuOption(
                "Solve a request",
                MainMenuCLI::solveRequest,
                null
        );
        
        public static final MenuOption ADD_DELETE_REVIEW = new MenuOption(
                "Add/Delete a review",
                MainMenuCLI::addDeleteReview,
                null
        );

        public static final MenuOption ADD_DELETE_USER = new MenuOption(
                "Add/Delete user",
                MainMenuCLI::addDeleteUser,
                null
        );

        public static final MenuOption BACK_TO_MAIN_MENU = new MenuOption(
                "Back to main menu",
                () -> {
                    IMDB.getInstance().getUserInterface().setMenuProvider(MainMenuProvider.getInstance());
                },
                null
        );

        public static final MenuOption LOGOUT = new MenuOption(
                "Logout",
                MainMenuCLI::logout,
                null
        );
    }
}
