package org.example.ui.menus;

import kotlin.NotImplementedError;
import org.example.ui.UserOption;
import org.example.user.AccountType;

import java.util.List;

public interface MenuProvider {
    List<UserOption> getUserOptions(AccountType accountType);
}
