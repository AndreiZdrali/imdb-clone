package org.example.ui.cli.menus;

import org.example.ui.cli.flow.AddDeleteListingCLI;

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
                AddDeleteListingCLI::viewContributions
        ));
        regularOptions.add(new MenuOption(
                "Add a new movie",
                AddDeleteListingCLI::addMovie
        ));
        regularOptions.add(new MenuOption(
                "Add a new series",
                AddDeleteListingCLI::addSeries
        ));
        regularOptions.add(new MenuOption(
                "Add a new actor",
                AddDeleteListingCLI::addActor
        ));
        regularOptions.add(new MenuOption(
                "Delete a listing",
                AddDeleteListingCLI::deleteListing
        ));
        regularOptions.add(MenuOption.List.BACK_TO_MAIN_MENU);

        return regularOptions;
    }
}
