package org.example.ui.menus;

import kotlin.NotImplementedError;
import org.example.exceptions.UnauthorizedAccessException;
import org.example.user.AccountType;

import java.util.List;

/**
 * A MenuProvider that provides different options for each account type.
 * If the same options are to be used for all account types, the <i>sameOptions</i> field should be
 * set to true in the child's constructor and only the getRegularOptions() method should be overridden.
 */
public abstract class MenuProvider {
    /**
     * If true, the same options will be used for all account types and are taken from the getRegularOptions() method.
     */
    protected boolean sameOptions = false;

    public List<MenuOption> getUserOptions(AccountType accountType) {
        if (sameOptions)
            return getRegularOptions();

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
