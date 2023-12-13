package org.example.production;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Episode {
    @JsonProperty("episodeName")
    private String episodeName;
    @JsonProperty("duration")
    private int duration;

    @JsonCreator
    public Episode(@JsonProperty("episodeName") String episodeName,
                   @JsonProperty("duration") String duration) {
        this.episodeName = episodeName;
        this.duration = Integer.parseInt(duration.split(" ")[0]);
    }

    public Episode(String episodeName, int duration) {
        this.episodeName = episodeName;
        this.duration = duration;
    }

    public int getDuration() {
        return duration;
    }

    public String toString() {
        return "Episode name: " + episodeName + "\n" +
                "Duration: " + duration + "\n";
    }
}
