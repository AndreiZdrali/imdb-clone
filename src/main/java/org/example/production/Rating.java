package org.example.production;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.utils.Observer;
import org.example.utils.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Rating implements Subject {
    private List<Observer> observers;

    @JsonProperty("username")
    private String username;
    @JsonProperty("rating")
    private int rating;
    @JsonProperty("comment")
    private String comment;

    /** visible = true => rating is visible to everyone
     *  visible = false => rating is deleted but still stored
     */
    @JsonProperty("visible")
    private boolean visible;

    public Rating(@JsonProperty("username") String username,
                  @JsonProperty("rating") int rating,
                  @JsonProperty("comment") String comment,
                  @JsonProperty("visible") Boolean visible) {
        this.username = username;
        this.rating = rating;
        this.comment = comment;

        this.visible = Objects.requireNonNullElse(visible, true);
        this.observers = new ArrayList<>();
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

    public String compactInfo() {
        return "Username: " + username + " | Rating: " + rating + " | Comment: " + comment;
    }

    public void hide() {
        visible = false;
    }

    public void show() {
        visible = true;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public String toString() {
        return "Username: " + username + "\n" +
                "Rating: " + rating + "\n" +
                "Comment: " + comment + "\n";
    }
}
