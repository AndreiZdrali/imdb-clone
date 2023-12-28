package org.example.ui.cli.menus;

import org.example.ui.cli.flow.AddDeleteUserCLI;

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
                AddDeleteUserCLI::addUser
        ));
        regularOptions.add(new MenuOption(
                "Delete a user",
                AddDeleteUserCLI::deleteUser
        ));
        regularOptions.add(MenuOption.List.BACK_TO_MAIN_MENU);

        return regularOptions;
    }
}
