package org.example.user;

import java.time.LocalDateTime;
import java.util.List;

public abstract class User {
    private Information information;
    private AccountType accountType;
    private String username;
    private int experience;
    private List<String> notifications;
    private SortedSet<T> favorites;

    public User() {
        //TODO: Implement constructor
    }

    public void addFavorite(T favorite) {
        //TODO: Implement addFavorite
    }

    public void removeFavorite(T favorite) {
        //TODO: Implement removeFavorite
    }

    public void updateExperience() {
        //TODO: Implement updateExperience
    }

    public void logout() {
        //TODO: Implement logout
    }

    public class Information {
        private Credentials credentials;
        private String realName;
        private String country;
        private String gender;
        private LocalDateTime birthDate;

        private Information(InformationBuilder builder) {
            //TODO: Implement constructor
        }

        public static class InformationBuilder {
            //TODO: Implement InformationBuilder
        }
    }
}
