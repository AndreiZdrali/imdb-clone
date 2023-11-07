package org.example.production;

import java.util.List;
import java.util.Map;

public class Series extends Production {
    private int duration;
    private int year;
    private int seasons;
    private Map<String, List<Episode>> episodes;

    public Series(SeriesBuilder builder) {
        super(builder.title, builder.directors, builder.actors, builder.genres, builder.description);
        this.duration = builder.duration;
        this.year = builder.year;
        this.seasons = builder.seasons;
        this.episodes = builder.episodes;

    }

    @Override
    public void displayInfo() {
        System.out.println("Production: " + title);
        System.out.println("Directors: " + directors);
        System.out.println("Actors: " + actors);
        System.out.println("Genres: " + genres);
        System.out.println("Ratings: " + ratings);
        System.out.println("Description: " + description);
        System.out.println("Grade: " + grade);

        System.out.println("Duration: " + duration);
        System.out.println("Year: " + year);
        System.out.println("Seasons: " + seasons);
    }

    public static class SeriesBuilder extends ProductionBuilder {
        private int duration;
        private int year;
        private int seasons;
        private Map<String, List<Episode>> episodes;

        public SeriesBuilder(String title, List<String> directors, List<String> actors, List<Genre> genres, String description) {
            this.title = title;
            this.directors = directors;
            this.actors = actors;
            this.genres = genres;
            this.description = description;
        }

        public SeriesBuilder setDuration(int duration) {
            this.duration = duration;
            return this;
        }

        public SeriesBuilder setYear(int year) {
            this.year = year;
            return this;
        }

        public SeriesBuilder setSeasons(int seasons) {
            this.seasons = seasons;
            return this;
        }

        public SeriesBuilder setEpisodes(Map<String, List<Episode>> episodes) {
            this.episodes = episodes;
            return this;
        }

        public Series build() {
            return new Series(this);
        }
    }

    public static void main(String[] args) {
        Series series = new SeriesBuilder("Game of Thrones", null, null, null, null)
                .setDuration(60)
                .setYear(2011)
                .setSeasons(8)
                .setEpisodes(null)
                .build();
    }
}
