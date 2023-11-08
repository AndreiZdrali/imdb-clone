package org.example.production;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Rating {
    @JsonProperty("username")
    private String username;
    @JsonProperty("rating")
    private int rating;
    @JsonProperty("comment")
    private String comment;

    public Rating(@JsonProperty("username") String username,
                  @JsonProperty("rating") int rating,
                  @JsonProperty("comment") String comment) {
        this.username = username;
        this.rating = rating;
        this.comment = comment;
    }

    public String toString() {
        //TODO: Implement toString
        return "TO IMPLEMENT";
    }
}
