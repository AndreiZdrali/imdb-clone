package org.example.production;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

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
public abstract class Production implements Comparable, Listing {
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
    @JsonProperty("averageRating")
    protected double averageRating;

    public Production(ProductionBuilder builder) {
        this.title = builder.title;
        this.type = builder.type;
        this.directors = builder.directors;
        this.actors = builder.actors;
        this.genres = builder.genres;
        this.ratings = builder.ratings;
        this.plot = builder.plot;
        this.averageRating = builder.averageRating;
    }

    public abstract void displayInfo();

    @Override
    public int compareTo(Object object) {
        if (object instanceof Production prod)
            return title.compareTo(prod.getTitle());
        else if (object instanceof Actor actor)
            return title.compareTo(actor.getName());
        else
            return 0;
    }

    public String getTitle() {
        return title;
    }

//    @JsonProperty("type")
//    public String getType() {
//        if (type != null)
//            return type;
//        else if (this instanceof Movie)
//            return "Movie";
//        else if (this instanceof Series)
//            return "Series";
//        else
//            throw new RuntimeException("Unknown type, nu stiu cum s-a ajuns aici");
//    }

    public static abstract class ProductionBuilder {
        protected String title;
        protected ProductionType type;
        protected List<String> directors;
        protected List<String> actors;
        protected List<Genre> genres;
        protected List<Rating> ratings;
        protected String plot;
        protected double averageRating;

        // astea sunt obligatorii pentru fiecare productie
        public ProductionBuilder(@JsonProperty("title") String title,
                                 @JsonProperty("type") ProductionType type,
                                 @JsonProperty("directors") List<String> directors,
                                 @JsonProperty("actors") List<String> actors,
                                 @JsonProperty("genres") List<Genre> genres,
                                 @JsonProperty("ratings") List<Rating> ratings,
                                 @JsonProperty("plot") String plot,
                                 @JsonProperty("averageRating") double averageRating) {
            this.title = title;
            this.type = type;
            this.directors = directors;
            this.actors = actors;
            this.genres = genres;
            this.ratings = ratings;
            this.plot = plot;
            this.averageRating = averageRating;
        }
    }
}
