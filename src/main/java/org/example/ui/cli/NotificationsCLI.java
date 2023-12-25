package org.example.ui.cli;

import org.example.IMDB;
import org.example.ui.menus.MainMenuProvider;
import org.example.user.User;

public class NotificationsCLI {
    public static void clearNotifications() {
        User<?> user = IMDB.getInstance().getUserInterface().getCurrentUser();

        user.getNotifications().clear();

        System.out.println("Notifications cleared!");
        System.out.println();

        IMDB.getInstance().getUserInterface().setMenuProvider(MainMenuProvider.getInstance());
    }
}
