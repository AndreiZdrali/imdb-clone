package org.example.ui;

import org.example.ui.menus.MenuProvider;
import org.example.user.User;

import java.util.Scanner;

public abstract class UserInterface {
    protected User<?> currentUser = null;
    protected MenuProvider menuProvider = null;
    public abstract void run();

    public User<?> getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User<?> currentUser) {
        this.currentUser = currentUser;
    }

    public MenuProvider getMenuProvider() {
        return menuProvider;
    }

    public void setMenuProvider(MenuProvider menuProvider) {
        this.menuProvider = menuProvider;
    }

    public Scanner getScanner() {
        throw new RuntimeException("Not implemented");
    }
}
