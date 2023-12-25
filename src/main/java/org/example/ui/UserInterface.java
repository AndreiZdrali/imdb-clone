package org.example.ui;

import org.example.ui.menus.MenuProvider;
import org.example.user.User;

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

    public int scanNextInt() {
        throw new RuntimeException("Not implemented");
    }

    public double scanNextDouble() {
        throw new RuntimeException("Not implemented");
    }

    public String scanNextLine() {
        throw new RuntimeException("Not implemented");
    }

    public String scanNextLineNonBlank() {
        throw new RuntimeException("Not implemented");
    }

    //CRED CA E INUTIL
//    public Scanner getScanner() {
//        throw new RuntimeException("Not implemented");
//    }
}
