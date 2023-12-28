package org.example.ui.cli.menus;

import org.example.ui.cli.flow.SolveRequestCLI;

import java.util.ArrayList;
import java.util.List;

public class SolveRequestProvider extends MenuProvider {
    private static SolveRequestProvider instance;

    private SolveRequestProvider() { }

    public static SolveRequestProvider getInstance() {
        if (instance == null)
            instance = new SolveRequestProvider();
        return instance;
    }

    @Override
    public List<MenuOption> getRegularOptions() {
        List<MenuOption> regularOptions = new ArrayList<>();

        regularOptions.add(new MenuOption(
                "View your pending requests",
                SolveRequestCLI::viewPendingRequests,
                null
        ));
        regularOptions.add(new MenuOption(
                "Mark a request as solved",
                SolveRequestCLI::markRequestAsSolved,
                null
        ));
        regularOptions.add(MenuOption.List.BACK_TO_MAIN_MENU);

        return regularOptions;
    }
}
