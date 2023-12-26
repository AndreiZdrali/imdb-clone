package org.example.ui;

import kotlin.NotImplementedError;
import org.example.ui.gui.AppFrame;

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
        appFrame = new AppFrame();
    }
}
