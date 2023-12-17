package org.example.ui.menus;

import org.example.IMDB;
import org.example.ui.cli.ProductionsDetailsCLI;

import java.util.List;
import java.util.ArrayList;

public class ProductionsDetailsProvider extends MenuProvider {
    private static ProductionsDetailsProvider instance = null;

    private ProductionsDetailsProvider() {
        sameOptions = true;
    }

    public static ProductionsDetailsProvider getInstance() {
        if (instance == null)
            instance = new ProductionsDetailsProvider();
        return instance;
    }

    @Override
    public List<MenuOption> getRegularOptions() {
        List<MenuOption> regularOptions = new ArrayList<>();

        regularOptions.add(new MenuOption(
                "View filtered results",
                ProductionsDetailsCLI::viewFilteredProductions,
                null
        ));
        regularOptions.add(new MenuOption(
                "Set production type",
                ProductionsDetailsCLI::setProductionType,
                null
        ));
        regularOptions.add(new MenuOption(
                "Set minimum rating",
                ProductionsDetailsCLI::setMinRating,
                null
        ));
        regularOptions.add(new MenuOption(
                "Set minimum number of reviews",
                ProductionsDetailsCLI::setMinReviews,
                null
        ));
        regularOptions.add(new MenuOption(
                "Clear filters",
                ProductionsDetailsCLI::clearFilters,
                null
        ));
        regularOptions.add(MenuOption.List.BACK_TO_MAIN_MENU);

        return regularOptions;
    }
}
