package org.example.ui;

import org.example.ui.UserInterface;
import org.example.ui.menus.MainMenuProvider;
import org.example.ui.menus.MenuProvider;
import org.example.user.User;

import java.util.List;

//TODO: Posibil s-o fac singleton
public class CLI extends UserInterface {
    public CLI(User user, MenuProvider menuProvider) {
        this.currentUser = user;
        this.menuProvider = menuProvider;
    }

    public CLI(User user) {
        this(user, MainMenuProvider.getInstance());
    }

    public void run() {
        List<UserOption> options = menuProvider.getUserOptions(currentUser.getUserType());
    }
}
