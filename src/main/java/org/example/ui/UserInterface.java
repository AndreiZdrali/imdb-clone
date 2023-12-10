package org.example.ui;

import org.example.ui.menus.MenuProvider;
import org.example.user.User;

public abstract class UserInterface {
    protected User currentUser = null;
    protected MenuProvider menuProvider = null;
    public abstract void run();
}
