package org.example.ui.menus;

import java.util.ArrayList;
import java.util.List;

public class NotificationsProvider extends MenuProvider {
    private static NotificationsProvider instance = null;

    private NotificationsProvider() {
        sameOptions = true;
    }

    public static NotificationsProvider getInstance() {
        if (instance == null)
            instance = new NotificationsProvider();
        return instance;
    }

    @Override
    public List<MenuOption> getRegularOptions() {
        List<MenuOption> regularOptions = new ArrayList<>();

        regularOptions.add(MenuOption.List.CLEAR_NOTIFICATIONS);
        regularOptions.add(MenuOption.List.BACK_TO_MAIN_MENU);

        return regularOptions;
    }
}
