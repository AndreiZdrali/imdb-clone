package org.example.ui;

import org.example.ui.menus.MenuProvider;
import org.example.user.User;

import javax.swing.*;

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

    /** For CLI */
    public MenuProvider getMenuProvider() {
        throw new RuntimeException("Not implemented");
    }

    /** For CLI */
    public void setMenuProvider(MenuProvider menuProvider) {
        throw new RuntimeException("Not implemented");
    }

    /** For GUI */
    public String getView() {
        throw new RuntimeException("Not implemented");
    }

    /** For GUI */
    public void setView(String viewName) {
        throw new RuntimeException("Not implemented");
    }

    /** Display the input cursor, consumes the next line and returns the input int */
    public int scanNextInt() {
        throw new RuntimeException("Not implemented");
    }

    /** Display the input cursor, consumes the next line and returns the input double */
    public double scanNextDouble() {
        throw new RuntimeException("Not implemented");
    }

    /** Display the input cursor and returns the input string */
    public String scanNextLine() {
        throw new RuntimeException("Not implemented");
    }

    /** Display the input cursor, scan the next non-blank line; whitespace is considered blank */
    public String scanNextLineNonBlank() {
        throw new RuntimeException("Not implemented");
    }
}
