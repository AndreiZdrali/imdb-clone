package org.example.ui.cli.menus;

import org.example.ui.cli.flow.FavoritesCLI;

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
                FavoritesCLI::viewFavorites
        ));
        regularOptions.add(new MenuOption(
                "Add favorite",
                FavoritesCLI::addFavorite
        ));
        regularOptions.add(new MenuOption(
                "Remove favorite",
                FavoritesCLI::removeFavorite
        ));
        regularOptions.add(MenuOption.List.BACK_TO_MAIN_MENU);

        return regularOptions;
    }
}
