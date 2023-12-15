package org.example.ui.menus;

import java.util.ArrayList;
import java.util.List;

public class MainMenuProvider extends MenuProvider {
    private static MainMenuProvider instance = null;

    private MainMenuProvider() { }

    public static MainMenuProvider getInstance() {
        if (instance == null)
            instance = new MainMenuProvider();
        return instance;
    }

    @Override
    protected List<MenuOption> getRegularOptions() {
        List<MenuOption> regularOptions = new ArrayList<>();

        regularOptions.add(MenuOption.List.VIEW_PRODUCTIONS_DETAILS);
        regularOptions.add(MenuOption.List.VIEW_ACTORS_DETAILS);
        regularOptions.add(MenuOption.List.VIEW_NOTIFICATIONS);
        regularOptions.add(MenuOption.List.SEARCH_FOR_LISTING);
        regularOptions.add(MenuOption.List.ADD_DELETE_FAVORITE);
        regularOptions.add(MenuOption.List.CREATE_REMOVE_REQUEST);
        regularOptions.add(MenuOption.List.ADD_DELETE_REVIEW);
        regularOptions.add(MenuOption.List.LOGOUT);

        return regularOptions;
    }

    @Override
    protected List<MenuOption> getContributorOptions() {
        List<MenuOption> contributorOptions = new ArrayList<>();

        contributorOptions.add(MenuOption.List.VIEW_PRODUCTIONS_DETAILS);
        contributorOptions.add(MenuOption.List.VIEW_ACTORS_DETAILS);
        contributorOptions.add(MenuOption.List.VIEW_NOTIFICATIONS);
        contributorOptions.add(MenuOption.List.SEARCH_FOR_LISTING);
        contributorOptions.add(MenuOption.List.ADD_DELETE_FAVORITE);
        contributorOptions.add(MenuOption.List.CREATE_REMOVE_REQUEST);
        contributorOptions.add(MenuOption.List.ADD_DELETE_LISTING);
        contributorOptions.add(MenuOption.List.UPDATE_LISTING);
        contributorOptions.add(MenuOption.List.SOLVE_REQUEST);
        contributorOptions.add(MenuOption.List.LOGOUT);

        return contributorOptions;
    }

    @Override
    protected List<MenuOption> getAdminOptions() {
        List<MenuOption> adminOptions = new ArrayList<>();

        adminOptions.add(MenuOption.List.VIEW_PRODUCTIONS_DETAILS);
        adminOptions.add(MenuOption.List.VIEW_ACTORS_DETAILS);
        adminOptions.add(MenuOption.List.VIEW_NOTIFICATIONS);
        adminOptions.add(MenuOption.List.SEARCH_FOR_LISTING);
        adminOptions.add(MenuOption.List.ADD_DELETE_FAVORITE);
        adminOptions.add(MenuOption.List.ADD_DELETE_LISTING);
        adminOptions.add(MenuOption.List.UPDATE_LISTING);
        adminOptions.add(MenuOption.List.SOLVE_REQUEST);
        adminOptions.add(MenuOption.List.ADD_DELETE_USER);
        adminOptions.add(MenuOption.List.LOGOUT);

        return adminOptions;
    }
}
