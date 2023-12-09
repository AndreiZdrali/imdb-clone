package org.example.ui.views;

import kotlin.NotImplementedError;
import org.example.ui.UserOption;
import org.example.user.AccountType;

import java.util.ArrayList;
import java.util.List;

public class MainView {
    private static List<UserOption> regularOptions = null;
    private static List<UserOption> contributorOptions = null;
    private static List<UserOption> adminOptions = null;

    public static List<UserOption> getUserOptions(AccountType accountType) {
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

    private static List<UserOption> getRegularOptions() {
        if (regularOptions != null)
            return regularOptions;

        regularOptions = new ArrayList<>();

        regularOptions.add(new UserOption("View productions details", null, null));
        regularOptions.add(new UserOption("View actors details", null, null));
        regularOptions.add(new UserOption("View notifications", null, null));

        return regularOptions;
    }

    private static List<UserOption> getContributorOptions() {
        if (contributorOptions != null)
            return contributorOptions;

        contributorOptions = getRegularOptions();

        return null;
    }

    private static List<UserOption> getAdminOptions() {
        if (adminOptions != null)
            return adminOptions;

        adminOptions = getContributorOptions();

        return null;
    }
}
