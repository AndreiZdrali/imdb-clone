package org.example.production;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonTypeInfo (
    use = JsonTypeInfo.Id.NAME,
    property = "type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Movie.class, name = "Movie"),
        @JsonSubTypes.Type(value = Series.class, name = "Series")
})
public abstract class Production implements Comparable<Production>, Listing {
    protected String title;
    protected String type; // pentru deserializare
    protected List<String> directors;
    protected List<String> actors;
    protected List<Genre> genres;
    protected List<Rating> ratings;
    protected String plot;
    protected double averageRating;

    // astea sunt obligatorii pentru fiecare productie
    public Production(String title, List<String> directors, List<String> actors, List<Genre> genres, List<Rating> ratings, String plot, double averageRating) {
        this.title = title;
        this.directors = directors;
        this.actors = actors;
        this.genres = genres;
        this.ratings = ratings;
        this.plot = plot;
        this.averageRating = averageRating;
    }

    public String getType() {
        return type;
    }

    public abstract void displayInfo();

    @Override
    public int compareTo(Production other) {
        return title.compareTo(other.title);
    }

    public static abstract class ProductionBuilder {
        @JsonProperty("title")
        protected String title;
        @JsonProperty("type")
        protected String type;
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
        @JsonProperty("averageRating")
        protected double averageRating;

        public ProductionBuilder(@JsonProperty("title") String title,
                                 @JsonProperty("type") String type,
                                 @JsonProperty("directors") List<String> directors,
                                 @JsonProperty("actors") List<String> actors,
                                 @JsonProperty("genres") List<Genre> genres,
                                 @JsonProperty("ratings") List<Rating> ratings,
                                 @JsonProperty("description") String description,
                                 @JsonProperty("averageRating") double averageRating) {
            this.title = title;
            this.type = type;
            this.directors = directors;
            this.actors = actors;
            this.genres = genres;
            this.ratings = ratings;
            this.plot = description;
            this.averageRating = averageRating;
        }
    }
}
