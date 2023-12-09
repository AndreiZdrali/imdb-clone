package org.example.user;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.example.management.RequestsManager;
import org.example.production.*;
import org.example.serializers.ActorToStringSerializer;
import org.example.serializers.ProductionToStringSerializer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

//TODO: Subtypes (cred ca e gata)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "userType"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = User.class, name = "User"),
        @JsonSubTypes.Type(value = Contributor.class, name = "Contributor"),
        @JsonSubTypes.Type(value = Admin.class, name = "Admin")
})
public abstract class User implements RequestsManager {
    @JsonProperty("username")
    protected String username;
    @JsonProperty("experience")
    protected int experience;
    @JsonProperty("information")
    protected Information information;
    @JsonProperty("userType")
    protected AccountType userType;
    @JsonProperty("favoriteProductions")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonSerialize(using = ProductionToStringSerializer.class, as = String.class)
    protected SortedSet<Production> favoriteProductions;
    @JsonProperty("favoriteActors")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonSerialize(using = ActorToStringSerializer.class, as = String.class)
    protected SortedSet<Actor> favoriteActors;

    @JsonIgnore
    public SortedSet<Comparable> favorites;

    @JsonProperty("notifications")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected List<String> notifications;

    // posibil sa trb sa adaug @JsonProperty la fiecare
    public User(UserBuilder builder) {
        this.username = builder.username;
        this.experience = builder.experience;
        this.information = builder.information;
        this.userType = builder.userType;
        this.favoriteProductions = builder.favoriteProductions;
        this.favoriteActors = builder.favoriteActors;
        this.notifications = builder.notifications;

        this.favorites = new TreeSet<>();
        this.favorites.addAll(this.favoriteProductions);
        this.favorites.addAll(this.favoriteActors);
    }

    public String getUsername() {
        return username;
    }

    public int getExperience() {
        return experience;
    }

    public AccountType getUserType() {
        return userType;
    }

    public boolean checkLogin(String email, String password) {
        return information.credentials.getEmail().equals(email) && information.credentials.getPassword().equals(password);
    }
    public void addFavorite(Comparable favorite) {
        //TODO: Implement addFavorite
    }

    public void removeFavorite(Comparable favorite) {
        //TODO: Implement removeFavorite
    }

    public void updateExperience() {
        //TODO: Implement updateExperience
    }

    public void logout() {
        //TODO: Implement logout
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", experience=" + experience +
                ", information=" + information +
                ", userType=" + userType +
                ", favoriteProductions=" + favoriteProductions +
                ", favoriteActors=" + favoriteActors +
                ", favorites=" + favorites +
                ", notifications=" + notifications +
                '}';
    }

    @JsonDeserialize(builder = Information.InformationBuilder.class)
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
                //TODO: Aici cica sa folosesc LocalDate in loc de LocalDateTime
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
