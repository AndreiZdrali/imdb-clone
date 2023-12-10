package org.example.ui.menus;

public class ProductionsDetailsProvider extends MenuProvider {
    private static ProductionsDetailsProvider instance = null;

    private ProductionsDetailsProvider() { }

    public static ProductionsDetailsProvider getInstance() {
        if (instance == null)
            instance = new ProductionsDetailsProvider();
        return instance;
    }

    //TODO: Implement ProductionsDetailsProvider
}
