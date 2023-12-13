package org.example.production;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;
import java.util.Map;

@JsonDeserialize(builder = Series.SeriesBuilder.class)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Series extends Production {
    private String duration;
    private int releaseYear;
    private int numSeasons;
    private Map<String, List<Episode>> seasons;

    public Series(SeriesBuilder builder) {
        super(builder);
        this.duration = builder.duration;
        this.releaseYear = builder.releaseYear;
        this.numSeasons = builder.numSeasons;
        this.seasons = builder.seasons;
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
        System.out.println("Year: " + releaseYear);
        System.out.println("Number of seasons: " + numSeasons);
        System.out.println("Seasons: " + seasons);
    }

    public String toString() {
        return title + " - " + type;
    }

    public static class SeriesBuilder extends ProductionBuilder {
        private String duration;
        private int releaseYear;
        private int numSeasons;
        private Map<String, List<Episode>> seasons;

        @JsonCreator
        public SeriesBuilder(@JsonProperty("title") String title,
                            @JsonProperty("type") String type,
                            @JsonProperty("directors") List<String> directors,
                            @JsonProperty("actors") List<String> actors,
                            @JsonProperty("genres") List<Genre> genres,
                            @JsonProperty("ratings") List<Rating> ratings,
                            @JsonProperty("plot") String plot,
                            @JsonProperty("averageRating") double averageRating) {
            super(title, type, directors, actors, genres, ratings, plot, averageRating);
        }

        @JsonProperty("duration")
        public SeriesBuilder setDuration(String duration) {
            this.duration = duration;
            return this;
        }

        @JsonProperty("releaseYear")
        public SeriesBuilder setReleaseYear(int releaseYear) {
            this.releaseYear = releaseYear;
            return this;
        }

        @JsonProperty("numSeasons")
        public SeriesBuilder setNumSeasons(int numSeasons) {
            this.numSeasons = numSeasons;
            return this;
        }

        @JsonProperty("seasons")
        public SeriesBuilder setSeasons(Map<String, List<Episode>> seasons) {
            this.seasons = seasons;
            return this;
        }

        public Series build() {
            return new Series(this);
        }
    }
}
