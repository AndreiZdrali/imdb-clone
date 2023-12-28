package org.example.ui.cli.menus;

import kotlin.NotImplementedError;
import org.example.IMDB;
import org.example.ui.cli.flow.MainMenuCLI;

public class MenuOption {
    private String description;
    private Runnable actionCli;

    public MenuOption(String description, Runnable actionCli) {
        this.description = description;
        this.actionCli = actionCli;
    }

    public String getDescription() {
        return description;
    }

    public Runnable getActionCli() {
        return actionCli;
    }


    public void executeCLI() {
        actionCli.run();
    }

    @Override
    public String toString() {
        return getDescription();
    }

    public static class List {
        public static final MenuOption VIEW_PRODUCTIONS_DETAILS = new MenuOption(
                "View productions details",
                MainMenuCLI::viewProductionsDetails
        );

        public static final MenuOption VIEW_ACTORS_DETAILS = new MenuOption(
                "View actors details",
                MainMenuCLI::viewActorsDetails
        );

        public static final MenuOption VIEW_NOTIFICATIONS = new MenuOption(
                "View your notifications",
                MainMenuCLI::viewNotifications
        );

        public static final MenuOption SEARCH_FOR_LISTING = new MenuOption(
                "Search for a listing",
                MainMenuCLI::searchForListing
        );

        public static final MenuOption ADD_DELETE_FAVORITE = new MenuOption(
                "Manage your favorites",
                MainMenuCLI::addDeleteFavorite
        );

        public static final MenuOption CREATE_REMOVE_REQUEST = new MenuOption(
                "Create/Remove a request",
                MainMenuCLI::createRemoveRequest
        );

        public static final MenuOption ADD_DELETE_LISTING = new MenuOption(
                "Add/Delete a listing",
                MainMenuCLI::addDeleteListing
        );

        public static final MenuOption UPDATE_LISTING = new MenuOption(
                "Update a listing",
                MainMenuCLI::updateListing
        );

        public static final MenuOption SOLVE_REQUEST = new MenuOption(
                "Solve a request",
                MainMenuCLI::solveRequest
        );
        
        public static final MenuOption ADD_DELETE_REVIEW = new MenuOption(
                "Add/Delete a review",
                MainMenuCLI::addDeleteReview
        );

        public static final MenuOption ADD_DELETE_USER = new MenuOption(
                "Add/Delete user",
                MainMenuCLI::addDeleteUser
        );

        public static final MenuOption BACK_TO_MAIN_MENU = new MenuOption(
                "Back to main menu",
                () -> {
                    IMDB.getInstance().getUserInterface().setMenuProvider(MainMenuProvider.getInstance());
                }
        );

        public static final MenuOption LOGOUT = new MenuOption(
                "Logout",
                MainMenuCLI::logout
        );
    }
}
