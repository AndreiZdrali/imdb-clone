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

    public String getUsername() {
        return username;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public String toString() {
        return "Username: " + username + "\n" +
                "Rating: " + rating + "\n" +
                "Comment: " + comment + "\n";
    }
}
