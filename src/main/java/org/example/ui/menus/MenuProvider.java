package org.example.ui.menus;

import kotlin.NotImplementedError;
import org.example.exceptions.UnauthorizedAccessException;
import org.example.user.AccountType;

import java.util.List;

public abstract class MenuProvider {
    protected List<MenuOption> regularOptions = null;
    protected List<MenuOption> contributorOptions = null;
    protected List<MenuOption> adminOptions = null;

    public List<MenuOption> getUserOptions(AccountType accountType) {
        return switch (accountType) {
            case Regular -> getRegularOptions();
            case Contributor -> getContributorOptions();
            case Admin -> getAdminOptions();
            default -> throw new NotImplementedError("Account type not existent");
        };
    }

    protected List<MenuOption> getRegularOptions() {
        throw new UnauthorizedAccessException("Regular users should not have access to this menu");
    }

    protected List<MenuOption> getContributorOptions() {
        throw new UnauthorizedAccessException("Contributors should not have access to this menu");
    }

    protected List<MenuOption> getAdminOptions() {
        throw new UnauthorizedAccessException("Admins should not have access to this menu");
    }
}
