package org.example.production;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Episode {
    @JsonProperty("episodeName")
    private String episodeName;
    @JsonProperty("duration")
    private int duration;

    // Overload pentru formatarea din json
    public Episode(@JsonProperty("episodeName") String episodeName,
                   @JsonProperty("duration") String duration) {
        this.episodeName = episodeName;
        this.duration = Integer.parseInt(duration.split(" ")[0]);
    }

    public int getDuration() {
        return duration;
    }

    public String toString() {
        return "Episode name: " + episodeName + "\n" +
                "Duration: " + duration + "\n";
    }
}
