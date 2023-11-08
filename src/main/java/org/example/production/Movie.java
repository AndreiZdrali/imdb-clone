package org.example.production;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = Movie.MovieBuilder.class)
public class Movie extends Production {
    private String duration;
    private int releaseYear;

    public Movie(MovieBuilder builder) {
        super(builder.title, builder.directors, builder.actors, builder.genres, builder.ratings, builder.plot, builder.averageRating);
        this.duration = builder.duration;
        this.releaseYear = builder.releaseYear;
    }

    @Override
    public void displayInfo() {
        System.out.println("Title: " + title);
        System.out.println("Directors: " + directors);
        System.out.println("Actors: " + actors);
        System.out.println("Genres: " + genres);
        System.out.println("Ratings: " + ratings);
        System.out.println("Description: " + plot);
        System.out.println("Rating: " + averageRating);

        System.out.println("Duration: " + duration);
        System.out.println("Year: " + releaseYear);
    }

    public String toString() {
        return title + " - " + type;
    }

    public static class MovieBuilder extends ProductionBuilder {
        @JsonProperty("duration")
        private String duration;
        @JsonProperty("releaseYear")
        private int releaseYear;

        @JsonCreator
        public MovieBuilder(@JsonProperty("title") String title,
                            @JsonProperty("type") String type,
                            @JsonProperty("directors") List<String> directors,
                            @JsonProperty("actors") List<String> actors,
                            @JsonProperty("genres") List<Genre> genres,
                            @JsonProperty("ratings") List<Rating> ratings,
                            @JsonProperty("description") String description,
                            @JsonProperty("averageRating") double averageRating) {
            super(title, type, directors, actors, genres, ratings, description, averageRating);
        }

        // Durata e in format "[duration] minutes" si trebuie luat doar numarul
        @JsonProperty("duration")
        public MovieBuilder setDuration(String duration) {
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
