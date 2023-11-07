package org.example.production;

import java.util.List;

public class Movie extends Production {
    private int duration;
    private int year;

    public Movie(MovieBuilder builder) {
        super(builder.title, builder.directors, builder.actors, builder.genres, builder.description);
        this.duration = builder.duration;
        this.year = builder.year;
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
    }

    public static class MovieBuilder extends ProductionBuilder {
        private int duration;
        private int year;

        public MovieBuilder(String title, List<String> directors, List<String> actors, List<Genre> genres, String description) {
            this.title = title;
            this.directors = directors;
            this.actors = actors;
            this.genres = genres;
            this.description = description;
        }

        public MovieBuilder setDuration(int duration) {
            this.duration = duration;
            return this;
        }

        public MovieBuilder setYear(int year) {
            this.year = year;
            return this;
        }

        public Movie build() {
            return new Movie(this);
        }
    }
}
