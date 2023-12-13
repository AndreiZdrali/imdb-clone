package org.example.services;

import org.example.IMDB;
import org.example.user.User;

import java.util.List;

public class UserService {
    public static List<User> getUsers() {
        return IMDB.getInstance().users;
    }

    public static void setUsers(List<User> users) {
        IMDB.getInstance().users = users;
    }

    public static void addUser(User user) {
        IMDB.getInstance().users.add(user);
    }

    public static void removeUser(User user) {
        IMDB.getInstance().users.remove(user);
    }

    public static User getUserByUsername(String username) {
        for (User user : IMDB.getInstance().users)
            if (user.getUsername().equals(username))
                return user;
        return null;
    }

    public static void updateUser(User user) {
        //TODO: Implement updateUser
    }
}
