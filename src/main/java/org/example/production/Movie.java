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
        System.out.println("Title: " + title + " (" + type + ")");
        if (directors != null)
            System.out.println("Directors: " + String.join(", ", directors));
        if (actors != null)
            System.out.println("Actors: " + String.join(", ", actors));
        if (genres != null)
            System.out.println("Genres: " + String.join(", ", genres.stream().map(Enum::toString).toList()));
        if (ratings != null)
            System.out.println("Ratings: " + String.join(", ", ratings.stream().map(Rating::compactInfo).toList()));
        if (plot != null)
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

        @JsonCreator
        public MovieBuilder(@JsonProperty("title") String title,
                            @JsonProperty("type") ProductionType type,
                            @JsonProperty("directors") List<String> directors,
                            @JsonProperty("actors") List<String> actors,
                            @JsonProperty("genres") List<Genre> genres,
                            @JsonProperty("ratings") List<Rating> ratings,
                            @JsonProperty("plot") String plot,
                            @JsonProperty("releaseYear") int releaseYear) {
            super(title, type, directors, actors, genres, ratings, plot, releaseYear);
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

        public Movie build() {
            return new Movie(this);
        }
    }
}
