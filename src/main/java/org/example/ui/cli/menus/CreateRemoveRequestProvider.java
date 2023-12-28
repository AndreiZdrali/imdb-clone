package org.example.ui.cli.menus;

import org.example.ui.cli.flow.CreateRemoveRequestCLI;

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
                CreateRemoveRequestCLI::viewMyRequests
        ));
        regularOptions.add(new MenuOption(
                "Create a request",
                CreateRemoveRequestCLI::createRequest
        ));
        regularOptions.add(new MenuOption(
                "Remove a request",
                CreateRemoveRequestCLI::removeRequest
        ));
        regularOptions.add(MenuOption.List.BACK_TO_MAIN_MENU);

        return regularOptions;
    }

}
