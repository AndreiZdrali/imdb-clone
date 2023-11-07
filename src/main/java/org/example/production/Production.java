package org.example.production;
import java.util.ArrayList;
import java.util.List;

public abstract class Production implements Comparable<Production> {
    protected String title;
    protected List<String> directors;
    protected List<String> actors;
    protected List<Genre> genres;
    protected List<Rating> ratings;
    protected String description;
    protected double grade;

    public Production(String title, List<String> directors, List<String> actors, List<Genre> genres, String description) {
        this.title = title;
        this.directors = directors;
        this.actors = actors;
        this.genres = genres;
        this.description = description;

        // ratings si grade se fac pe parcurs
        this.ratings = new ArrayList<Rating>();
        this.grade = 0;
    }

    public abstract void displayInfo();

    @Override
    public int compareTo(Production other) {
        return title.compareTo(other.title);
    }

    public static abstract class ProductionBuilder {
        protected String title;
        protected List<String> directors;
        protected List<String> actors;
        protected List<Genre> genres;
        protected String description;
    }
}
