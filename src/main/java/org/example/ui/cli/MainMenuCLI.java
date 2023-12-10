package org.example.ui.cli;

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
}
