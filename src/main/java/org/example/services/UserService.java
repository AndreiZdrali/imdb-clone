package org.example.services;

import org.example.IMDB;
import org.example.production.Rating;
import org.example.user.*;
import org.example.utils.Subject;

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

    /** Deletes the user and all of its reviews and requests
     *  If the user is a contributor also move his contributions
     *  to the shared pool and remove him from their observers list
     */
    public static void deleteUser(User<?> user) {
        IMDB.getInstance().getUsers().remove(user);

        for (var production : ProductionService.getProductions())
            production.getRatings().removeIf(rating -> rating.getUsername().equals(user.getUsername()));

        for (var request : RequestService.getRequestsCreatedByUser(user))
            RequestService.removeRequest(request);

        if (user instanceof Contributor<?> contributor) {
            for (var c : contributor.getContributions()) {
                ((Subject) c).removeObserver(contributor);
                //TODO: Add to shared pool
            }
        }
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
