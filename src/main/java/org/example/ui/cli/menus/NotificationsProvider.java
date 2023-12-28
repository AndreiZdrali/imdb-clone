package org.example.ui.cli.menus;

import org.example.ui.cli.flow.NotificationsCLI;

import java.util.ArrayList;
import java.util.List;

public class NotificationsProvider extends MenuProvider {
    private static NotificationsProvider instance = null;

    private NotificationsProvider() { }

    public static NotificationsProvider getInstance() {
        if (instance == null)
            instance = new NotificationsProvider();
        return instance;
    }

    @Override
    public List<MenuOption> getRegularOptions() {
        List<MenuOption> regularOptions = new ArrayList<>();

        regularOptions.add(new MenuOption(
                "Clear your notifications",
                NotificationsCLI::clearNotifications,
                null
        ));
        regularOptions.add(MenuOption.List.BACK_TO_MAIN_MENU);

        return regularOptions;
    }
}
