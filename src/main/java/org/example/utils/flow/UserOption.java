package org.example.utils.flow;

public class UserOption {
    private String description;
    private Runnable actionCLI;
    private Runnable actionGUI;

    public UserOption(String description, Runnable actionCLI, Runnable actionGUI) {
        this.description = description;
        this.actionCLI = actionCLI;
        this.actionGUI = actionGUI;
    }

    public String getDescription() {
        return description;
    }

    public Runnable getActionCLI() {
        return actionCLI;
    }

    public Runnable getActionGUI() {
        return actionGUI;
    }

    public void executeCLI() {
        actionCLI.run();
    }

    public void executeGUI() {
        actionGUI.run();
        throw new UnsupportedOperationException("GUI not implemented yet");
    }
}
