package org.example.utils;

import org.example.services.UserService;

import java.util.Arrays;
import java.util.List;

public class UserInformationHelper {
    private UserInformationHelper() { }

    public static String generateUsername(String name) {
        List<String> usernameParts = Arrays.asList(name.toLowerCase().split(" "));

        while (UserService.getUserByUsername(String.join("_", usernameParts)) != null)
            usernameParts.add(String.valueOf((int) (Math.random() * 10)));

        return String.join("_", usernameParts);
    }

    public static String generatePassword(int length) {
        String passwordSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+";
        char[] password = new char[length];

        for (int i = 0; i < length; i++) {
            int random = (int) (Math.random() * passwordSet.length());
            password[i] = passwordSet.charAt(random);
        }

        return new String(password);
    }
}
