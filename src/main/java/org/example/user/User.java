package org.example.user;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.example.management.NotificationWrapper;
import org.example.management.RequestsManager;
import org.example.production.*;
import org.example.serializers.ActorToStringSerializer;
import org.example.serializers.LocalDateToStringSerializer;
import org.example.serializers.ProductionToStringSerializer;
import org.example.services.ActorService;
import org.example.services.ProductionService;
import org.example.utils.Observer;
import org.example.utils.Subject;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

//TODO: Subtypes (cred ca e gata)
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "userType",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Regular.class, name = "Regular"),
        @JsonSubTypes.Type(value = Contributor.class, name = "Contributor"),
        @JsonSubTypes.Type(value = Admin.class, name = "Admin")
})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public abstract class User<T> implements Observer {
    @JsonProperty("username")
    protected String username;
    @JsonProperty("experience")
    protected int experience;
    @JsonProperty("information")
    protected Information information;
    @JsonProperty("userType")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    protected AccountType userType;
    @JsonProperty("favoriteProductions")
    @JsonSerialize(using = ProductionToStringSerializer.class, as = String.class)
    protected SortedSet<Production> favoriteProductions;
    @JsonProperty("favoriteActors")
    @JsonSerialize(using = ActorToStringSerializer.class, as = String.class)
    protected SortedSet<Actor> favoriteActors;
    @JsonIgnore
    protected SortedSet<Comparable> favorites;

    @JsonProperty("notifications")
    protected List<String> notifications;

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

    public List<String> getNotifications() {
        return notifications;
    }

    public AccountType getUserType() {
        return userType;
    }

    public Information getInformation() {
        return information;
    }

    public SortedSet<Production> getFavoriteProductions() {
        return favoriteProductions;
    }

    public SortedSet<Actor> getFavoriteActors() {
        return favoriteActors;
    }

    public SortedSet<Comparable> getFavorites() {
        return favorites;
    }

    public boolean checkLogin(String email, String password) {
        return information.credentials.getEmail().equals(email) && information.credentials.getPassword().equals(password);
    }

    /** Adauga un listing la favorite il adauga ca observer */
    public void addFavorite(Comparable favorite) {
        if (!(favorite instanceof Subject subject))
            throw new RuntimeException("Nu e subiect????????");

        subject.addObserver(this);

        if (favorites.contains(favorite))
            throw new RuntimeException("Nu ar trb sa pot sa adaug ceva ce e deja in favorites");

        favorites.add(favorite);

        if (favorite instanceof Production production)
            favoriteProductions.add(production);
        else if (favorite instanceof Actor actor)
            favoriteActors.add(actor);
    }

    /** Scoate un listing din favorite il scoate ca observer */
    public void removeFavorite(Comparable favorite) {
        if (!(favorite instanceof Subject subject))
            throw new RuntimeException("Nu e subiect????????");

        subject.removeObserver(this);

        if (!favorites.contains(favorite))
            throw new RuntimeException("Nu ar trb sa pot sa scot ceva ce nu e in favorites");

        favorites.remove(favorite);

        if (favorite instanceof Production production)
            favoriteProductions.remove(production);
        else if (favorite instanceof Actor actor)
            favoriteActors.remove(actor);
    }

    @Override
    public abstract void update(NotificationWrapper notificationWrapper);

    public void updateExperience(ExperienceStrategy strategy) {
        //TODO: Implement updateExperience
    }

    public void logout() {
        //TODO: Implement logout
    }

    public String infoForCreator() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Username: ").append(username).append("\n");
        stringBuilder.append("Account Type: ").append(userType).append("\n");
        stringBuilder.append("\tEmail: ").append(information.credentials.getEmail()).append("\n");
        stringBuilder.append("\tName: ").append(information.name).append("\n");
        stringBuilder.append("\tCountry: ").append(information.country).append("\n");
        stringBuilder.append("\tAge: ").append(information.age).append("\n");
        stringBuilder.append("\tGender: ").append(information.gender).append("\n");
        stringBuilder.append("\tBirth date: ").append(information.birthDate).append("\n");

        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", experience=" + experience +
                ", information=" + information +
                ", userType=" + userType +
                ", favorites=" + favorites +
                ", notifications=" + notifications +
                '}';
    }

    @JsonDeserialize(builder = Information.InformationBuilder.class)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
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
        @JsonSerialize(using = LocalDateToStringSerializer.class)
        private LocalDate birthDate;

        private Information(InformationBuilder builder) {
            this.credentials = builder.credentials;
            this.name = builder.name;
            this.country = builder.country;
            this.age = builder.age;
            this.gender = builder.gender;
            this.birthDate = builder.birthDate;
        }

        public Credentials getCredentials() {
            return credentials;
        }

        public String getName() {
            return name;
        }

        public String getCountry() {
            return country;
        }

        public int getAge() {
            return age;
        }

        public String getGender() {
            return gender;
        }

        public LocalDate getBirthDate() {
            return birthDate;
        }

        public static class InformationBuilder {
            private Credentials credentials;
            private String name;
            private String country;
            private int age;
            private String gender;
            private LocalDate birthDate;

            public InformationBuilder() {

            }

            @JsonProperty("credentials")
            public InformationBuilder setCredentials(Credentials credentials) {
                this.credentials = credentials;
                return this;
            }

            @JsonProperty("name")
            @JsonAlias("nume")
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
                this.birthDate = LocalDate.parse(birthDate, formatter);
                return this;
            }

            public InformationBuilder setBirthDate(LocalDate birthDate) {
                this.birthDate = birthDate;
                return this;
            }

            public Information build() {
                return new Information(this);
            }
        }
    }

    public static class UserBuilder {
        private String username;
        private int experience;
        private Information information;
        private AccountType userType;
        private SortedSet<Production> favoriteProductions;
        private SortedSet<Actor> favoriteActors;
        private List<String> notifications;

        public UserBuilder(@JsonProperty("username") String username,
                           @JsonProperty("experience") int experience,
                           @JsonProperty("information") Information information,
                           @JsonProperty("userType") AccountType userType) {
            this.username = username;
            this.experience = experience;
            this.information = information;
            this.userType = userType;

            this.favoriteProductions = new TreeSet<>();
            this.favoriteActors = new TreeSet<>();

            this.notifications = new ArrayList<>();
        }

        @JsonProperty("favoriteProductions")
        public UserBuilder setFavoriteProductions(List<String> favoriteProductions) {
            for (String production : favoriteProductions) {
                Production p = ProductionService.getProductionByTitle(production);
                if (p != null)
                    this.favoriteProductions.add(p);
            }
            return this;
        }

        public UserBuilder setFavoriteProductions(SortedSet<Production> favoriteProductions) {
            this.favoriteProductions = favoriteProductions;
            return this;
        }

        @JsonProperty("favoriteActors")
        public UserBuilder setFavoriteActors(List<String> favoriteActors) {
            for (String actor : favoriteActors) {
                Actor a = ActorService.getActorByName(actor);
                if (a != null)
                    this.favoriteActors.add(a);
            }
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
