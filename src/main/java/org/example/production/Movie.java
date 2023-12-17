package org.example.production;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import kotlin.NotImplementedError;

@JsonDeserialize(builder = Movie.MovieBuilder.class)
public class Movie extends Production {
    private int duration;
    private int releaseYear;

    public Movie(MovieBuilder builder) {
        super(builder);
        this.duration = builder.duration;
        this.releaseYear = builder.releaseYear;
    }

    @Override
    public void displayInfo() {
        //TODO: Rewrite
        System.out.println("Title: " + title);
        System.out.println("Directors: " + directors);
        System.out.println("Actors: " + actors);
        System.out.println("Genres: " + genres);
        System.out.println("Ratings: " + ratings);
        System.out.println("Description: " + plot);
        System.out.println("Rating: " + getAverageRating());

        System.out.println("Duration: " + duration);
        System.out.println("Year: " + releaseYear);
    }

    @Override
    public void displayShortInfo() {
        //format: "{title} ({type}) | Year: {releaseYear} | Duration: {duration} min | Rating: {averageRating} ({numberOfReviews} reviews)"
        System.out.println(title + " (" + type + ") | Year: " + releaseYear + " | Duration: " + duration + " min | Rating: " + getAverageRating() + " (" + ratings.size() + " reviews)");
    }

    @JsonIgnoreProperties({"averageRating"})
    public static class MovieBuilder extends ProductionBuilder {
        private int duration;
        private int releaseYear;

        @JsonCreator
        public MovieBuilder(@JsonProperty("title") String title,
                            @JsonProperty("type") ProductionType type,
                            @JsonProperty("directors") List<String> directors,
                            @JsonProperty("actors") List<String> actors,
                            @JsonProperty("genres") List<Genre> genres,
                            @JsonProperty("ratings") List<Rating> ratings,
                            @JsonProperty("plot") String plot) {
            super(title, type, directors, actors, genres, ratings, plot);
        }

        @JsonProperty("duration")
        public MovieBuilder setDuration(String duration) {
            this.duration = Integer.parseInt(duration.split(" ")[0]);
            return this;
        }

        public MovieBuilder setDuration(int duration) {
            this.duration = duration;
            return this;
        }

        @JsonProperty("releaseYear")
        public MovieBuilder setReleaseYear(int releaseYear) {
            this.releaseYear = releaseYear;
            return this;
        }

        public Movie build() {
            return new Movie(this);
        }
    }
}
