package org.example.services;

import org.example.IMDB;
import org.example.user.User;

import java.util.List;

public class UserService {
    public static List<User<?>> getUsers() {
        return IMDB.getInstance().getUsers();
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
