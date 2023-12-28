package org.example.ui;

import kotlin.NotImplementedError;
import org.example.IMDB;
import org.example.services.UserService;
import org.example.ui.gui.AppFrame;
import org.example.ui.gui.LoginFrame;

import javax.swing.*;

public class GUI extends UserInterface {
    private AppFrame appFrame;

    public GUI() { }

    @Override
    public void setView(String viewName) {
        appFrame.setView(viewName);
    }

    @Override
    public void run() {
        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            System.err.println("Uncaught exception in thread " + t.getName());
            e.printStackTrace();
            System.exit(1);
        });

//        LoginFrame loginFrame = new LoginFrame();
//        loginFrame.setVisible(true);

        currentUser = UserService.getUsers().get(1); //regular

        if (IMDB.getInstance().getUserInterface().getCurrentUser() == null) {
            System.out.println("No user logged in!");
            return;
        }

        appFrame = new AppFrame();
        appFrame.setVisible(true);
    }
}
