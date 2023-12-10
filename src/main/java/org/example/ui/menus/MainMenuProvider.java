package org.example.ui.menus;

import kotlin.NotImplementedError;
import org.example.ui.UserOption;
import org.example.user.AccountType;

import java.util.ArrayList;
import java.util.List;

public class MainMenuProvider implements MenuProvider {
    private static List<UserOption> regularOptions = null;
    private static List<UserOption> contributorOptions = null;
    private static List<UserOption> adminOptions = null;

    private static MainMenuProvider instance = null;

    private MainMenuProvider() { }

    public static MainMenuProvider getInstance() {
        if (instance == null)
            instance = new MainMenuProvider();
        return instance;
    }

    public List<UserOption> getUserOptions(AccountType accountType) {
        switch (accountType) {
            case Regular:
                return getRegularOptions();
            case Contributor:
                return getContributorOptions();
            case Admin:
                return getAdminOptions();
            default:
                throw new NotImplementedError("Account type not existent");
        }
    }

    private List<UserOption> getRegularOptions() {
        if (regularOptions != null)
            return regularOptions;

        regularOptions = new ArrayList<>();

        regularOptions.add(UserOption.List.VIEW_PRODUCTIONS_DETAILS);
        regularOptions.add(UserOption.List.VIEW_ACTORS_DETAILS);
        regularOptions.add(UserOption.List.VIEW_NOTIFICATIONS);

        return regularOptions;
    }

    private List<UserOption> getContributorOptions() {
        if (contributorOptions != null)
            return contributorOptions;

        contributorOptions = getRegularOptions();

        return null;
    }

    private List<UserOption> getAdminOptions() {
        if (adminOptions != null)
            return adminOptions;

        adminOptions = getContributorOptions();

        return null;
    }
}
