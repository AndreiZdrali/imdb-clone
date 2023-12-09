package org.example.ui;

import kotlin.NotImplementedError;

import java.util.List;

public class UserOption {
    private String description;
    private Runnable actionCli;
    private Runnable actionGui;
    private static List<UserOption> regularOptions = null;
    private static List<UserOption> contributorOptions = null;
    private static List<UserOption> adminOptions = null;

    public UserOption(String description, Runnable actionCli, Runnable actionGui) {
        this.description = description;
        this.actionCli = actionCli;
        this.actionGui = actionGui;
    }

    public String getDescription() {
        return description;
    }

    public Runnable getActionCli() {
        return actionCli;
    }

    public Runnable getActionGui() {
        return actionGui;
    }

    public void executeCLI() {
        actionCli.run();
    }

    public void executeGUI() {
        actionGui.run();
        //TODO: Remove asta, incredibil de inutila
        throw new NotImplementedError("GUI not implemented yet");
    }
}
