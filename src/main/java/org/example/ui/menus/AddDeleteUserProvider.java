package org.example.ui.menus;

import org.example.ui.cli.AddDeleteUserCLI;

import java.util.ArrayList;
import java.util.List;

public class AddDeleteUserProvider extends MenuProvider {
    private static AddDeleteUserProvider instance = null;

    private AddDeleteUserProvider() { }

    public static AddDeleteUserProvider getInstance() {
        if (instance == null)
            instance = new AddDeleteUserProvider();
        return instance;
    }

    @Override
    public List<MenuOption> getRegularOptions() {
        List<MenuOption> regularOptions = new ArrayList<>();

        regularOptions.add(new MenuOption(
                "Add a new user",
                AddDeleteUserCLI::addUser,
                null
        ));
        regularOptions.add(new MenuOption(
                "Delete a user",
                AddDeleteUserCLI::deleteUser,
                null
        ));
        regularOptions.add(MenuOption.List.BACK_TO_MAIN_MENU);

        return regularOptions;
    }
}
