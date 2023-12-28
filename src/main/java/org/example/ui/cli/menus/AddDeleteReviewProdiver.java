package org.example.ui.cli.menus;

import org.example.ui.cli.flow.AddDeleteReviewCLI;

import java.util.ArrayList;
import java.util.List;

public class AddDeleteReviewProdiver extends MenuProvider {
    private static AddDeleteReviewProdiver instance = null;

    private AddDeleteReviewProdiver() { }

    public static AddDeleteReviewProdiver getInstance() {
        if (instance == null)
            instance = new AddDeleteReviewProdiver();
        return instance;
    }

    @Override
    public List<MenuOption> getRegularOptions() {
        List<MenuOption> regularOptions = new ArrayList<>();

        regularOptions.add(new MenuOption(
                "View your reviews",
                AddDeleteReviewCLI::viewReviews,
                null
        ));
        regularOptions.add(new MenuOption(
                "Add a review",
                AddDeleteReviewCLI::addReview,
                null
        ));
        regularOptions.add(new MenuOption(
                "Delete a review",
                AddDeleteReviewCLI::deleteReview,
                null
        ));
        regularOptions.add(MenuOption.List.BACK_TO_MAIN_MENU);

        return regularOptions;
    }
}
