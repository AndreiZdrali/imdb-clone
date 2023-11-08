package org.example.production;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class Actor implements Listing {
    @JsonProperty("name")
    private String name;
    @JsonProperty("performances")
    private Map<String, Genre> performances;
    @JsonProperty("biography")
    private String biography;

    public Actor(@JsonProperty("name") String name,
                 @JsonProperty("performances") Map<String, Genre> performances,
                 @JsonProperty("biography") String biography) {
        this.name = name;
        this.performances = performances;
        this.biography = biography;
    }
}
