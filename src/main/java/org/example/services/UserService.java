package org.example.services;

import org.example.IMDB;
import org.example.user.*;

import java.util.List;
import java.util.stream.Collectors;

public class UserService {
    public static List<User<?>> getUsers() {
        return IMDB.getInstance().getUsers();
    }

    public static List<Regular<?>> getRegulars() {
        return IMDB.getInstance().getUsers().stream()
                .filter(Regular.class::isInstance)
                .map(u -> (Regular<?>) u)
                .collect(Collectors.toList());
    }

    public static List<Staff<?>> getStaff() {
        return IMDB.getInstance().getUsers().stream()
                .filter(Staff.class::isInstance)
                .map(u -> (Staff<?>) u)
                .collect(Collectors.toList());
    }

    public static List<Contributor<?>> getContributors() {
        return IMDB.getInstance().getUsers().stream()
                .filter(Contributor.class::isInstance)
                .map(u -> (Contributor<?>) u)
                .collect(Collectors.toList());
    }

    public static List<Admin<?>> getAdmins() {
        return IMDB.getInstance().getUsers().stream()
                .filter(Admin.class::isInstance)
                .map(u -> (Admin<?>) u)
                .collect(Collectors.toList());
    }

    public static void setUsers(List<User<?>> users) {
        IMDB.getInstance().setUsers(users);
    }

    public static void addUser(User<?> user) {
        //TODO: Posibil sa nu mearga, sa trb sa folosesc setUsers
        IMDB.getInstance().getUsers().add(user);
    }

    public static void removeUser(User<?> user) {
        IMDB.getInstance().getUsers().remove(user);
    }

    public static User<?> getUserByUsername(String username) {
        for (User<?> user : getUsers())
            if (user.getUsername().equals(username))
                return user;
        return null;
    }

    public static void updateUser(User<?> user) {
        //TODO: Implement updateUser
    }
}
