package org.example.production;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import org.example.management.NotificationWrapper;
import org.example.services.ProductionService;
import org.example.utils.Observer;
import org.example.utils.Subject;

@JsonTypeInfo (
    use = JsonTypeInfo.Id.NAME,
    property = "type",
    visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Movie.class, name = "Movie"),
        @JsonSubTypes.Type(value = Series.class, name = "Series")
})
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public abstract class Production implements Comparable<Production>, Listing, Subject {
    @JsonIgnore
    protected Set<Observer> observers;

    @JsonProperty("title")
    protected String title;
    @JsonProperty("type")
    protected ProductionType type; // pentru deserializare
    @JsonProperty("directors")
    protected List<String> directors;
    @JsonProperty("actors")
    protected List<String> actors;
    @JsonProperty("genres")
    protected List<Genre> genres;
    @JsonProperty("ratings")
    protected List<Rating> ratings;
    @JsonProperty("plot")
    protected String plot;
    @JsonProperty("releaseYear")
    protected int releaseYear;

    public Production(ProductionBuilder builder) {

        this.title = builder.title;
        this.type = builder.type;
        this.directors = builder.directors;
        this.actors = builder.actors;
        this.genres = builder.genres;
        this.ratings = builder.ratings;
        this.plot = builder.plot;
        this.releaseYear = builder.releaseYear;

        this.observers = new HashSet<>();

        //in caz ca e null in builder
        if (this.directors == null)
            this.directors = new ArrayList<>();
        if (this.actors == null)
            this.actors = new ArrayList<>();
        if (this.genres == null)
            this.genres = new ArrayList<>();
        if (this.ratings == null)
            this.ratings = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public ProductionType getType() {
        return type;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    // pentru sortare in ProductionService
    public int getId() {
        return ProductionService.getProductions().indexOf(this);
    }

    @JsonProperty("averageRating")
    public double getAverageRating() {
        if (ratings == null || ratings.isEmpty())
            return 0;
        return ratings.stream()
                .mapToDouble(Rating::getRating)
                .average()
                .orElse(0);
    }

    @Override
    public Set<Observer> getObservers() {
        return observers;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(NotificationWrapper notification) {
        for (Observer observer : observers)
            if (observer != null)
                observer.update(notification);
    }

    public abstract String info();

    public abstract void displayInfo();

    public abstract void displayShortInfo();

    @Override
    public int compareTo(Production object) {
//        if (object instanceof Production prod)
//            return title.compareTo(prod.getTitle());
//        else if (object instanceof Actor actor)
//            return title.compareTo(actor.getName());
//        else
//            return 0;
    return title.compareTo(object.getTitle());
    }

    @JsonIgnoreProperties({"averageRating"})
    public static abstract class ProductionBuilder {
        protected String title;
        protected ProductionType type;
        protected List<String> directors;
        protected List<String> actors;
        protected List<Genre> genres;
        protected List<Rating> ratings;
        protected String plot;
        protected int releaseYear;

        // astea sunt obligatorii pentru fiecare productie
        public ProductionBuilder(@JsonProperty("title") String title,
                                 @JsonProperty("type") ProductionType type,
                                 @JsonProperty("directors") List<String> directors,
                                 @JsonProperty("actors") List<String> actors,
                                 @JsonProperty("genres") List<Genre> genres,
                                 @JsonProperty("ratings") List<Rating> ratings,
                                 @JsonProperty("plot") String plot,
                                 @JsonProperty("releaseYear") int releaseYear) {
            this.title = title;
            this.type = type;
            this.directors = directors;
            this.actors = actors;
            this.genres = genres;
            this.ratings = ratings;
            this.plot = plot;
            this.releaseYear = releaseYear;
        }
    }
}
