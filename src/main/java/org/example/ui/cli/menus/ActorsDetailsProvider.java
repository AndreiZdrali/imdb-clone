package org.example.ui.cli.menus;

import org.example.ui.cli.flow.ActorsDetailsCLI;

import java.util.ArrayList;
import java.util.List;

public class ActorsDetailsProvider extends MenuProvider {
    private static ActorsDetailsProvider instance = null;

    private ActorsDetailsProvider() { }

    public static ActorsDetailsProvider getInstance() {
        if (instance == null)
            instance = new ActorsDetailsProvider();
        return instance;
    }

    @Override
    public List<MenuOption> getRegularOptions() {
        List<MenuOption> regularOptions = new ArrayList<>();

        regularOptions.add(new MenuOption(
                "View sorted and filtered results",
                ActorsDetailsCLI::viewSortedAndFilteredActors
        ));
        regularOptions.add(new MenuOption(
                "Set minimum number of performances",
                ActorsDetailsCLI::setMinPerformances
        ));
        regularOptions.add(new MenuOption(
                "Set sorting method",
                ActorsDetailsCLI::setSortingMethod
        ));
        regularOptions.add(new MenuOption(
                "Clear sorting and filters",
                ActorsDetailsCLI::clearSortingAndFilters
        ));
        regularOptions.add(MenuOption.List.BACK_TO_MAIN_MENU);

        return regularOptions;
    }
}
