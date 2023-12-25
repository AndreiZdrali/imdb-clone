package org.example.ui.menus;

import org.example.ui.cli.AddDeleteListingCLI;

import java.util.ArrayList;
import java.util.List;

public class AddDeleteListingProvider extends MenuProvider {
    private static AddDeleteListingProvider instance = null;

    private AddDeleteListingProvider() { }

    public static AddDeleteListingProvider getInstance() {
        if (instance == null)
            instance = new AddDeleteListingProvider();
        return instance;
    }

    @Override
    public List<MenuOption> getRegularOptions() {
        List<MenuOption> regularOptions = new ArrayList<>();

        regularOptions.add(new MenuOption(
                "View your contributions",
                AddDeleteListingCLI::viewContributions,
                null
        ));
        regularOptions.add(new MenuOption(
                "Add a new movie",
                AddDeleteListingCLI::addMovie,
                null
        ));
        regularOptions.add(new MenuOption(
                "Add a new series",
                AddDeleteListingCLI::addSeries,
                null
        ));
        regularOptions.add(new MenuOption(
                "Add a new actor",
                AddDeleteListingCLI::addActor,
                null
        ));
        regularOptions.add(new MenuOption(
                "Delete a listing",
                AddDeleteListingCLI::deleteListing,
                null
        ));
        regularOptions.add(MenuOption.List.BACK_TO_MAIN_MENU);

        return regularOptions;
    }
}
