package org.example.production;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Map;

public class Actor implements Comparable, Listing {
    @JsonProperty("name")
    private String name;
    @JsonProperty("performances")
    private List<Performance> performances;
    @JsonProperty("biography")
    private String biography;

    public Actor(@JsonProperty("name") String name,
                 @JsonProperty("performances") List<Performance> performances,
                 @JsonProperty("biography") String biography) {
        this.name = name;
        this.performances = performances;
        this.biography = biography;
    }

    public String getName() {
        return name;
    }

    public List<Performance> getPerformances() {
        return performances;
    }

    public String getBiography() {
        return biography;
    }

    public int compareTo(Object object) {
        if (object instanceof Production) {
            Production prod = (Production) object;
            return name.compareTo(prod.getTitle());
        } else if (object instanceof Actor) {
            Actor actor = (Actor) object;
            return name.compareTo(actor.name);
        } else {
            return 0;
        }
    }

    public String toString() {
        return "Name: " + name + "\n" +
                "Performances: " + performances + "\n" +
                "Biography: " + biography + "\n";
    }

    public static class Performance {
        @JsonProperty("title")
        private String title;
        @JsonProperty("type")
        private String type;

        public Performance(@JsonProperty("title") String title,
                           @JsonProperty("type") String type) {
            this.title = title;
            this.type = type;
        }

        public String toString() {
            return "Title: " + title + "\n" +
                    "Type: " + type + "\n";
        }
    }
}
