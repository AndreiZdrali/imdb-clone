package org.example.user;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sun.source.tree.Tree;
import org.example.production.Actor;
import org.example.production.Production;
import org.example.production.Listing;
import org.example.utils.serializers.ActorToStringSerializer;
import org.example.utils.serializers.ProductionToStringSerializer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public abstract class User {
    @JsonProperty("username")
    private String username;
    @JsonProperty("experience")
    private int experience;
    @JsonProperty("information")
    private Information information;
    @JsonProperty("userType")
    private AccountType userType;
    @JsonProperty("favoriteProductions")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonSerialize(using = ProductionToStringSerializer.class, as = String.class)
    private SortedSet<Production> favoriteProductions;
    @JsonProperty("favoriteActors")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonSerialize(using = ActorToStringSerializer.class, as = String.class)
    private SortedSet<Actor> favoriteActors;

    @JsonProperty("notifications")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> notifications;

    // posibil sa trb sa adaug @JsonProperty la fiecare
    public User(UserBuilder builder) {
        this.username = builder.username;
        this.experience = builder.experience;
        this.information = builder.information;
        this.userType = builder.userType;
        this.favoriteProductions = builder.favoriteProductions;
        this.favoriteActors = builder.favoriteActors;
        this.notifications = builder.notifications;
    }

    public void addFavorite(Listing favorite) {
        //TODO: Implement addFavorite
    }

    public void removeFavorite(Listing favorite) {
        //TODO: Implement removeFavorite
    }

    public void updateExperience() {
        //TODO: Implement updateExperience
    }

    public void logout() {
        //TODO: Implement logout
    }

    public static class Information {
        @JsonProperty("credentials")
        private Credentials credentials;
        @JsonProperty("name")
        private String name;
        @JsonProperty("country")
        private String country;
        @JsonProperty("age")
        private int age;
        @JsonProperty("gender")
        private String gender;
        @JsonProperty("birthDate")
        private LocalDateTime birthDate;

        private Information(InformationBuilder builder) {
            this.credentials = builder.credentials;
            this.name = builder.name;
            this.country = builder.country;
            this.age = builder.age;
            this.gender = builder.gender;
            this.birthDate = builder.birthDate;
        }

        public static class InformationBuilder {
            @JsonProperty("credentials")
            private Credentials credentials;
            @JsonProperty("name")
            @JsonAlias({"nume"}) // doar pt deserializare
            private String name;
            @JsonProperty("country")
            private String country;
            @JsonProperty("age")
            private int age;
            @JsonProperty("gender")
            private String gender;
            @JsonProperty("birthDate")
            private LocalDateTime birthDate;

            public InformationBuilder() {

            }

            @JsonProperty("credentials")
            public InformationBuilder setCredentials(Credentials credentials) {
                this.credentials = credentials;
                return this;
            }

            @JsonProperty("name")
            public InformationBuilder setName(String name) {
                this.name = name;
                return this;
            }

            @JsonProperty("country")
            public InformationBuilder setCountry(String country) {
                this.country = country;
                return this;
            }

            @JsonProperty("age")
            public InformationBuilder setAge(int age) {
                this.age = age;
                return this;
            }

            @JsonProperty("gender")
            public InformationBuilder setGender(String gender) {
                this.gender = gender;
                return this;
            }

            @JsonProperty("birthDate")
            public InformationBuilder setBirthDate(String birthDate) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                this.birthDate = LocalDateTime.parse(birthDate, formatter);
                return this;
            }

            public InformationBuilder setBirthDate(LocalDateTime birthDate) {
                this.birthDate = birthDate;
                return this;
            }

            public Information build() {
                return new Information(this);
            }
        }
    }

    public static class UserBuilder {
        @JsonProperty("username")
        private String username;
        @JsonProperty("experience")
        private int experience;
        @JsonProperty("information")
        private Information information;
        @JsonProperty("userType")
        private AccountType userType;
        @JsonProperty("favoriteProductions")
        private SortedSet<Production> favoriteProductions;
        @JsonProperty("favoriteActors")
        private SortedSet<Actor> favoriteActors;
        @JsonProperty("notifications")
        private List<String> notifications;

        public UserBuilder(@JsonProperty("username") String username,
                           @JsonProperty("experience") int experience,
                           @JsonProperty("information") Information information,
                           @JsonProperty("userType") AccountType userType) {
            this.username = username;
            this.experience = experience;
            this.information = information;
            this.userType = userType;
        }

        @JsonProperty("favoriteProductions")
        public UserBuilder setFavoriteProductions(List<String> favoriteProductions) {
            //TODO: pentru fiecare string sa gasesc productia aia
            return this;
        }

        public UserBuilder setFavoriteProductions(SortedSet<Production> favoriteProductions) {
            this.favoriteProductions = favoriteProductions;
            return this;
        }

        @JsonProperty("favoriteActors")
        public UserBuilder setFavoriteActors(List<String> favoriteActors) {
            //TODO: pentru fiecare string sa gasesc actorul ala
            return this;
        }

        public UserBuilder setFavoriteActors(SortedSet<Actor> favoriteActors) {
            this.favoriteActors = favoriteActors;
            return this;
        }

        @JsonProperty("notifications")
        public UserBuilder setNotifications(List<String> notifications) {
            this.notifications = notifications;
            return this;
        }
    }
}
