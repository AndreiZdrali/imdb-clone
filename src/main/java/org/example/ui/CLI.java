package org.example.ui;

import org.example.IMDB;
import org.example.services.UserService;
import org.example.ui.menus.MainMenuProvider;
import org.example.ui.menus.MenuOption;
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

    /** Display the input cursor, consumes the next line and returns the input int */
    public int scanNextInt() {
        System.out.print("> ");
        int input = scanner.nextInt();
        scanner.nextLine();
        System.out.println();
        return input;
    }

    /** Display the input cursor, consumes the next line and returns the input double */
    public double scanNextDouble() {
        System.out.print("> ");
        double input = scanner.nextDouble();
        scanner.nextLine();
        System.out.println();
        return input;
    }

    /** Display the input cursor and returns the input string */
    public String scanNextLine() {
        System.out.print("> ");
        String input = scanner.nextLine();
        System.out.println();
        return input;
    }

    public void run() {
        currentUser = login();

        while (currentUser != null) {
            List<MenuOption> options = menuProvider.getUserOptions(currentUser.getUserType());
            handleOptions(options);
        }
    }

    private void handleOptions(List<MenuOption> options) {
        System.out.println("Choose an option:");
        for (int i = 0; i < options.size(); i++) {
            System.out.println("\t" + (i + 1) + ") " + options.get(i).toString());
        }

        int option = scanNextInt();

        MenuOption selectedOption = options.get(option - 1);

        selectedOption.executeCLI();
    }

    private User<?> login() {
        System.out.println("Welcome back! Enter your credentials!");

        System.out.print("\tEmail: ");
        String email = scanner.nextLine();
        System.out.print("\tPassword: ");
        String password = scanner.nextLine();
        System.out.println();

        //FIXME: AICI CEVA EROARE
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
        login();
        throw new RuntimeException("Eroare la login, nu stiu cum s-a ajuns aici");
    }
}
