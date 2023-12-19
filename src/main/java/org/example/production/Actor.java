package org.example.production;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.services.ActorService;

import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
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

    // pentru sortare in ActorService
    public int getId() {
        return ActorService.getActors().indexOf(this);
    }

    public void displayInfo() {
        System.out.println("Name: " + name);

        String performancesString = String.join(", ",
                performances.stream()
                        .map(Performance::getTitle)
                        .toList());
        System.out.println("Performances: " + performancesString);

        System.out.println("Biography: " + biography);
    }

    public void displayShortInfo() {
        //format: "{name} | Performances: {numberOfPerformances} | Biography: {biography}"
        System.out.println(name + " | Performances: " + performances.size() + " | Biography: " + biography);
    }

    public int compareTo(Object object) {
        if (object instanceof Production prod)
            return name.compareTo(prod.getTitle());
        else if (object instanceof Actor actor)
            return name.compareTo(actor.name);
        else
            return 0;
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

        public String getTitle() {
            return title;
        }

        public String getType() {
            return type;
        }

        public String toString() {
            return title + " (" + type + ")";
        }
    }
}
