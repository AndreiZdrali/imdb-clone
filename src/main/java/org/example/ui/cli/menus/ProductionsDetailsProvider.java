package org.example.ui.cli.menus;

import org.example.ui.cli.flow.ProductionsDetailsCLI;

import java.util.List;
import java.util.ArrayList;

public class ProductionsDetailsProvider extends MenuProvider {
    private static ProductionsDetailsProvider instance = null;

    private ProductionsDetailsProvider() { }

    public static ProductionsDetailsProvider getInstance() {
        if (instance == null)
            instance = new ProductionsDetailsProvider();
        return instance;
    }

    @Override
    public List<MenuOption> getRegularOptions() {
        List<MenuOption> regularOptions = new ArrayList<>();

        regularOptions.add(new MenuOption(
                "View sorted and filtered results",
                ProductionsDetailsCLI::viewSortedAndFilteredProductions
        ));
        regularOptions.add(new MenuOption(
                "Set production type",
                ProductionsDetailsCLI::setProductionType
        ));
        regularOptions.add(new MenuOption(
                "Set minimum rating",
                ProductionsDetailsCLI::setMinRating
        ));
        regularOptions.add(new MenuOption(
                "Set minimum number of reviews",
                ProductionsDetailsCLI::setMinReviews
        ));
        regularOptions.add(new MenuOption(
                "Set sorting method",
                ProductionsDetailsCLI::setSortingMethod
        ));
        regularOptions.add(new MenuOption(
                "Clear filters",
                ProductionsDetailsCLI::clearSortingAndFilters
        ));
        regularOptions.add(MenuOption.List.BACK_TO_MAIN_MENU);

        return regularOptions;
    }
}
