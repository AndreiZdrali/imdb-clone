package org.example.ui.menus;

import org.example.ui.cli.CreateRemoveRequestCLI;

import java.util.ArrayList;
import java.util.List;

public class CreateRemoveRequestProvider extends MenuProvider {
    private static CreateRemoveRequestProvider instance = null;

    private CreateRemoveRequestProvider() { }

    public static CreateRemoveRequestProvider getInstance() {
        if (instance == null)
            instance = new CreateRemoveRequestProvider();
        return instance;
    }

    @Override
    protected List<MenuOption> getRegularOptions() {
        List<MenuOption> regularOptions = new ArrayList<>();

        regularOptions.add(new MenuOption(
                "View your active requests",
                CreateRemoveRequestCLI::viewMyRequests,
                null
        ));
        regularOptions.add(new MenuOption(
                "Create a request",
                CreateRemoveRequestCLI::createRequest,
                null
        ));
        regularOptions.add(new MenuOption(
                "Remove a request",
                CreateRemoveRequestCLI::removeRequest,
                null
        ));
        regularOptions.add(MenuOption.List.BACK_TO_MAIN_MENU);

        return regularOptions;
    }

}
