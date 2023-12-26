package org.example.ui;

import org.example.IMDB;
import org.example.services.UserService;
import org.example.ui.menus.MainMenuProvider;
import org.example.ui.menus.MenuOption;
import org.example.ui.menus.MenuProvider;
import org.example.user.AccountType;
import org.example.user.User;

import java.util.List;
import java.util.Scanner;

//TODO: Posibil s-o fac singleton
public class CLI extends UserInterface {
    private final Scanner scanner;

    public CLI() {
        menuProvider = MainMenuProvider.getInstance();
        scanner = new Scanner(System.in);
    }

    @Override
    public MenuProvider getMenuProvider() {
        return menuProvider;
    }

    @Override
    public void setMenuProvider(MenuProvider menuProvider) {
        this.menuProvider = menuProvider;
    }


    @Override
    public int scanNextInt() {
        System.out.print("> ");
        int input = 0;
        try {
            input = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Invalid input! Expected an integer!");
            scanner.nextLine(); // consume the \n character
            input = scanNextInt();
        }
        scanner.nextLine();
        System.out.println();
        return input;
    }

    @Override
    public double scanNextDouble() {
        System.out.print("> ");
        double input = 0;
        try {
            input = scanner.nextDouble();
        } catch (Exception e) {
            System.out.println("Invalid input! Expected a double!");
            scanner.nextLine(); // consume the \n character
            input = scanNextDouble();
        }
        scanner.nextLine();
        System.out.println();
        return input;
    }

    @Override
    public String scanNextLine() {
        System.out.print("> ");
        String input = scanner.nextLine();
        System.out.println();
        return input;
    }

    @Override
    public String scanNextLineNonBlank() {
        String input = scanNextLine();
        while (input.isBlank()) {
            System.out.println("Invalid input! Expected a non-blank line!");
            input = scanNextLine();
        }
        return input;
    }

    @Override
    public void run() {
        do {
            currentUser = login();
            while (currentUser != null) {
                List<MenuOption> options = menuProvider.getUserOptions(currentUser.getUserType());
                handleOptions(options);
            }
        } while (!askForExit());
    }

    private void handleOptions(List<MenuOption> options) {
        System.out.println("Choose an option:");
        for (int i = 0; i < options.size(); i++)
            System.out.println("\t" + (i + 1) + ") " + options.get(i).toString());

        int option = scanNextInt();

        if (option < 1 || option > options.size()) {
            System.out.println("Invalid option!");
            System.out.println();
            return;
        }

        MenuOption selectedOption = options.get(option - 1);

        selectedOption.executeCLI();
    }

    private boolean askForExit() {
        System.out.println("Do you want to exit?");
        System.out.println("\t1) Yes");
        System.out.println("\t2) No");

        int option = scanNextInt();

        if (option == 1)
            return true;
        else if (option == 2)
            return false;
        else {
            System.out.println("Invalid option!");
            System.out.println();
            return askForExit();
        }
    }

    private User<?> login() {
        System.out.println("Welcome back! Enter your credentials!");

        System.out.print("\tEmail: ");
        String email = scanner.nextLine();
        System.out.print("\tPassword: ");
        String password = scanner.nextLine();
        System.out.println();

        for (User<?> u : UserService.getUsers()) {
            if (u.checkLogin(email, password)) {
                System.out.println("Welcome back, " + u.getUsername() + "!");
                System.out.println("You have " + u.getNotifications().size() + " notifications!");
                if (u.getUserType() == AccountType.Admin)
                    System.out.println("User experience: -");
                else
                    System.out.println("User experience: " + u.getExperience());
                return u;
            }
        }

        System.out.println("Invalid credentials!");
        return login();
    }
}
