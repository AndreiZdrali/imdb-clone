package org.example.production;
import java.util.List;

public abstract class Production implements Comparable<Production> {
    private String title;
    private List<String> directors;
    private List<String> actors;
    private List<Genre> genres;
    private List<Rating> ratings;
    private String description;
    private double grade;

    public abstract void displayInfo();

    @Override
    public int compareTo(Production other) {
        return title.compareTo(other.title);
    }
}
