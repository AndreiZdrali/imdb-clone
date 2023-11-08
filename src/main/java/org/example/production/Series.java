package org.example.production;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;
import java.util.Map;

@JsonDeserialize(builder = Series.SeriesBuilder.class)
public class Series extends Production {
    private int duration;
    private int year;
    private int seasons;
    private Map<String, List<Episode>> episodes;

    public Series(SeriesBuilder builder) {
        super(builder.title, builder.directors, builder.actors, builder.genres, builder.ratings, builder.plot, builder.averageRating);
        this.duration = builder.duration;
        this.year = builder.year;
        this.seasons = builder.numSeasons;
        this.episodes = builder.seasons;

    }

    @Override
    public void displayInfo() {
        System.out.println("Production: " + title);
        System.out.println("Directors: " + directors);
        System.out.println("Actors: " + actors);
        System.out.println("Genres: " + genres);
        System.out.println("Ratings: " + ratings);
        System.out.println("Description: " + plot);
        System.out.println("Grade: " + averageRating);

        System.out.println("Duration: " + duration);
        System.out.println("Year: " + year);
        System.out.println("Seasons: " + seasons);
    }

    public String toString() {
        return title + " " + type;
    }

    public static class SeriesBuilder extends ProductionBuilder {
        private int duration;
        private int year;
        private int numSeasons;
        private Map<String, List<Episode>> seasons;

        @JsonCreator
        public SeriesBuilder(@JsonProperty("title") String title,
                            @JsonProperty("type") String type,
                            @JsonProperty("directors") List<String> directors,
                            @JsonProperty("actors") List<String> actors,
                            @JsonProperty("genres") List<Genre> genres,
                            @JsonProperty("ratings") List<Rating> ratings,
                            @JsonProperty("description") String description,
                            @JsonProperty("averageRating") double averageRating) {
            super(title, type, directors, actors, genres, ratings, description, averageRating);
        }

        public SeriesBuilder setDuration(int duration) {
            this.duration = duration;
            return this;
        }

        public SeriesBuilder setYear(int year) {
            this.year = year;
            return this;
        }

        public SeriesBuilder setNumSeasons(int numSeasons) {
            this.numSeasons = numSeasons;
            return this;
        }

        public SeriesBuilder setSeasons(Map<String, List<Episode>> seasons) {
            this.seasons = seasons;
            return this;
        }

        public Series build() {
            return new Series(this);
        }
    }
}
