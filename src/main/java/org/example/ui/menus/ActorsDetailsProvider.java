package org.example.ui.menus;

public class ActorsDetailsProvider extends MenuProvider {
    private static ActorsDetailsProvider instance = null;

    private ActorsDetailsProvider() { }

    public static ActorsDetailsProvider getInstance() {
        if (instance == null)
            instance = new ActorsDetailsProvider();
        return instance;
    }
}
