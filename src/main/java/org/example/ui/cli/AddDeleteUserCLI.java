package org.example.ui.cli;

import org.example.IMDB;
import org.example.services.ProductionService;
import org.example.services.UserService;
import org.example.user.*;
import org.example.utils.UserInformationHelper;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class AddDeleteUserCLI {
    //TODO: Nu am testat inca
    public static void addUser() {
        User<?> user = IMDB.getInstance().getUserInterface().getCurrentUser();

        if (!(user instanceof Admin<?> admin))
            throw new RuntimeException("Doar admin ar trebui sa aiba acces la asta");

        System.out.println("Select the type of user you want to add (0 to cancel): ");
        for (int i = 0; i < AccountType.values().length; i++)
            System.out.println("\t" + (i + 1) + ") " + AccountType.values()[i]);

        int input = IMDB.getInstance().getUserInterface().scanNextInt();

        if (input == 0) {
            System.out.println("Canceled!");
            System.out.println();
            return;
        }

        if (input < 0 || input > AccountType.values().length) {
            System.out.println("Invalid user type!");
            System.out.println();
            return;
        }

        AccountType accountType = AccountType.values()[input - 1];

        System.out.println("Email: ");
        String email = IMDB.getInstance().getUserInterface().scanNextLineNonBlank();

        System.out.println("Real name: ");
        String realName = IMDB.getInstance().getUserInterface().scanNextLineNonBlank();

        System.out.println("Country: ");
        String country = IMDB.getInstance().getUserInterface().scanNextLineNonBlank();

        System.out.println("Age: ");
        int age = IMDB.getInstance().getUserInterface().scanNextInt();

        System.out.println("Gender: ");
        String gender = IMDB.getInstance().getUserInterface().scanNextLineNonBlank();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println("Birth date (yyyy-MM-dd): ");
        String birthDate = IMDB.getInstance().getUserInterface().scanNextLineNonBlank();
        while (true) {
            try {
                formatter.parse(birthDate);
                break;
            } catch (Exception e) {
                System.out.println("Invalid date format!");
                birthDate = IMDB.getInstance().getUserInterface().scanNextLineNonBlank();
            }
        }

        String password = UserInformationHelper.generatePassword(8);

        User.Information information = new User.Information.InformationBuilder()
                .setCredentials(new Credentials(email, password))
                .setName(realName)
                .setCountry(country)
                .setAge(age)
                .setGender(gender)
                .setBirthDate(birthDate)
                .build();

        User<?> newUser = new UserFactory().getUser(accountType, information);

        System.out.println("Do you want to add user " + newUser.getUsername() + "?");
        System.out.println("-> User information: ");
        System.out.println(user.infoForCreator());

        System.out.println("\t1) Yes");
        System.out.println("\t2) No");

        input = IMDB.getInstance().getUserInterface().scanNextInt();

        if (input != 1) {
            System.out.println("Canceled!");
            System.out.println();
            return;
        }

        admin.addUser(newUser);

        System.out.println("User added!");
        System.out.println();
    }

    //TODO: Nu am testat inca
    public static void deleteUser() {
        User<?> user = IMDB.getInstance().getUserInterface().getCurrentUser();

        if (!(user instanceof Admin<?> admin))
            throw new RuntimeException("Doar admin ar trebui sa aiba acces la asta");

        System.out.println("Enter the username: ");

        String input = IMDB.getInstance().getUserInterface().scanNextLineNonBlank();

        List<User<?>> matchingUsers = new ArrayList<>();

        for (var u : UserService.getUsers())
            if (u.getUsername().toLowerCase().contains(input.toLowerCase()))
                matchingUsers.add(u);

        if (matchingUsers.isEmpty()) {
            System.out.println("No users found!");
            System.out.println();
            return;
        }

        System.out.println("Enter the number of the user you want to delete (0 to cancel): ");
        for (int i = 0; i < matchingUsers.size(); i++) {
            System.out.print(i + 1 + ". ");
            System.out.println(matchingUsers.get(i).getUsername());
        }

        int input2 = IMDB.getInstance().getUserInterface().scanNextInt();

        if (input2 == 0) {
            System.out.println("Canceled!");
            System.out.println();
            return;
        }

        if (input2 < 0 || input2 > matchingUsers.size()) {
            System.out.println("Invalid input!");
            System.out.println();
            return;
        }

        User<?> userToDelete = matchingUsers.get(input2 - 1);

        System.out.println("Are you sure you want to delete " + userToDelete.getUsername() + "?");
        System.out.println("\t1) Yes");
        System.out.println("\t2) No");

        input2 = IMDB.getInstance().getUserInterface().scanNextInt();

        if (input2 != 1) {
            System.out.println("Canceled!");
            System.out.println();
            return;
        }

        admin.deleteUser(userToDelete);

        System.out.println("User deleted!");
        System.out.println();
    }
}
