package org.example.ui.cli;

import kotlin.NotImplementedError;
import org.example.IMDB;
import org.example.ui.menus.ActorsDetailsProvider;
import org.example.ui.menus.ProductionsDetailsProvider;

public class MainMenuCLI {
    public static void viewProductionsDetails() {
        IMDB.getInstance().getUserInterface().setMenuProvider(ProductionsDetailsProvider.getInstance());
    }

    public static void viewActorsDetails() {
        IMDB.getInstance().getUserInterface().setMenuProvider(ActorsDetailsProvider.getInstance());
    }

    //TODO: Implement the rest of the options
    public static void viewNotifications() {
        throw new NotImplementedError("Not implemented yet");
    }

    public static void searchForListing() {
        throw new NotImplementedError("Not implemented yet");
    }

    public static void addDeleteFavorite() {
        throw new NotImplementedError("Not implemented yet");
    }

    public static void createRemoveRequest() {
        throw new NotImplementedError("Not implemented yet");
    }

    public static void addDeleteListing() {
        throw new NotImplementedError("Not implemented yet");
    }

    public static void updateListing() {
        throw new NotImplementedError("Not implemented yet");
    }

    public static void solveRequest() {
        throw new NotImplementedError("Not implemented yet");
    }

    public static void addDeleteReview() {
        throw new NotImplementedError("Not implemented yet");
    }

    public static void addDeleteUser() {
        throw new NotImplementedError("Not implemented yet");
    }

    public static void logout() {
        throw new NotImplementedError("Not implemented yet");
    }
}
