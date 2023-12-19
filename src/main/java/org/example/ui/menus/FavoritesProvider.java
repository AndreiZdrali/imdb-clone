package org.example.ui.menus;

import org.example.ui.cli.FavoritesCLI;

import java.util.ArrayList;
import java.util.List;

public class FavoritesProvider extends MenuProvider{
    public static FavoritesProvider instance = null;

    private FavoritesProvider() { }

    public static FavoritesProvider getInstance() {
        if (instance == null)
            instance = new FavoritesProvider();
        return instance;
    }

    @Override
    public List<MenuOption> getRegularOptions() {
        List<MenuOption> regularOptions = new ArrayList<>();

        regularOptions.add(new MenuOption(
                "View favorites",
                FavoritesCLI::viewFavorites,
                null
        ));
        regularOptions.add(new MenuOption(
                "Add favorite",
                FavoritesCLI::addFavorite,
                null
        ));
        regularOptions.add(new MenuOption(
                "Remove favorite",
                FavoritesCLI::removeFavorite,
                null
        ));
        regularOptions.add(MenuOption.List.BACK_TO_MAIN_MENU);

        return regularOptions;
    }
}
