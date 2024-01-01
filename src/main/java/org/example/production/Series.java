package org.example.production;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.List;
import java.util.Map;

@JsonDeserialize(builder = Series.SeriesBuilder.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Series extends Production {
    @JsonProperty("duration")
    private int duration;
    @JsonProperty("numSeasons")
    private int numSeasons;
    private Map<String, List<Episode>> seasons;

    public Series(SeriesBuilder builder) {
        super(builder);

        this.numSeasons = builder.numSeasons;
        this.seasons = builder.seasons;

        if (this.seasons == null)
            this.seasons = Map.of();

        this.duration = this.seasons.values().stream()
                .flatMap(List::stream)
                .mapToInt(Episode::getDuration)
                .sum();
    }

    @JsonProperty("duration")
    public int getDuration() {
        duration = seasons.values().stream()
                .flatMap(List::stream)
                .mapToInt(Episode::getDuration)
                .sum();

        return duration;
    }

    @Override
    public String info() {
        StringBuilder sb = new StringBuilder();

        sb.append("Title: ").append(title).append(" (").append(type).append(")\n");
        if (directors != null)
            sb.append("Directors: ").append(String.join(", ", directors)).append("\n");
        if (actors != null)
            sb.append("Actors: ").append(String.join(", ", actors)).append("\n");
        if (genres != null)
            sb.append("Genres: ").append(String.join(", ", genres.stream().map(Enum::toString).toList())).append("\n");
        if (ratings != null)
            sb.append("Ratings: ").append(String.join(", ", ratings.stream().map(Rating::compactInfo).toList())).append("\n");
        if (plot != null)
            sb.append("Description: ").append(plot).append("\n");
        sb.append("Rating: ").append(getAverageRating()).append("\n");

        sb.append("Duration: ").append(duration).append("\n");
        sb.append("Year: ").append(releaseYear).append("\n");

        sb.append("Number of seasons: ").append(numSeasons).append("\n");
        sb.append("Seasons: ").append(seasons).append("\n");

        return sb.toString();
    }

    @Override
    public void displayInfo() {
        System.out.println(info());

        //TODO: Rewrite
//        System.out.println("Title: " + title + " (" + type + ")");
//        if (directors != null)
//            System.out.println("Directors: " + String.join(", ", directors));
//        if (actors != null)
//            System.out.println("Actors: " + actors);
//        if (genres != null)
//            System.out.println("Genres: " + String.join(", ", genres.stream().map(Enum::toString).toList()));
//        if (ratings != null)
//            System.out.println("Ratings: " + String.join(", ", ratings.stream().map(Rating::compactInfo).toList()));
//        if (plot != null)
//            System.out.println("Description: " + plot);
//        System.out.println("Rating: " + getAverageRating());
//
//        System.out.println("Duration: " + duration);
//        System.out.println("Year: " + releaseYear);
//        System.out.println("Number of seasons: " + numSeasons);
//        System.out.println("Seasons: " + seasons);
    }

    @Override
    public void displayShortInfo() {
        //format: "{title} ({type}) | Year: {releaseYear} | Seasons: {numSeasons} ({duration} min total) | Rating: {averageRating} ({numberOfReviews} reviews)"
        System.out.println(title + " (" + type + ") | Year: " + releaseYear + " | Seasons: " + numSeasons + " (" + duration + " min total) | Rating: " + getAverageRating() + " (" + ratings.size() + " reviews)");
    }

    @JsonIgnoreProperties({"averageRating"})
    public static class SeriesBuilder extends ProductionBuilder {
        private int numSeasons;
        private Map<String, List<Episode>> seasons;

        @JsonCreator
        public SeriesBuilder(@JsonProperty("title") String title,
                             @JsonProperty("type") ProductionType type,
                             @JsonProperty("directors") List<String> directors,
                             @JsonProperty("actors") List<String> actors,
                             @JsonProperty("genres") List<Genre> genres,
                             @JsonProperty("ratings") List<Rating> ratings,
                             @JsonProperty("plot") String plot,
                             @JsonProperty("releaseYear") int releaseYear) {
            super(title, type, directors, actors, genres, ratings, plot, releaseYear);
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
