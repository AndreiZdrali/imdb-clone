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
public abstract class Production implements Comparable, Listing {
    protected String title;
    protected String type; // pentru deserializare
    protected List<String> directors;
    protected List<String> actors;
    protected List<Genre> genres;
    protected List<Rating> ratings;
    protected String plot;
    protected double averageRating;

    public Production(ProductionBuilder builder) {
        this.title = builder.title;
        this.directors = builder.directors;
        this.actors = builder.actors;
        this.genres = builder.genres;
        this.ratings = builder.ratings;
        this.plot = builder.plot;
        this.averageRating = builder.averageRating;
    }

    public abstract void displayInfo();

    @Override
    public int compareTo(Object o) {
        Production other = (Production) o;
        return title.compareTo(other.title);
    }

    public String getTitle() {
        return title;
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

        // astea sunt obligatorii pentru fiecare productie
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
