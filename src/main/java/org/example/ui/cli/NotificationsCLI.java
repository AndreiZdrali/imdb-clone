package org.example.ui.cli;

import org.example.IMDB;
import org.example.ui.menus.MainMenuProvider;
import org.example.user.User;

public class NotificationsCLI {
    public static void clearNotifications() {
        User<?> user = IMDB.getInstance().getUserInterface().getCurrentUser();

        //TODO: Sa vad cum fac sa salvez dupa asta, probabil in CLI.run() intre menu switch-uri
        user.getNotifications().clear();

        System.out.println("Notifications cleared!");

        IMDB.getInstance().getUserInterface().setMenuProvider(MainMenuProvider.getInstance());
    }
}
