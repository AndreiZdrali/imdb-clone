package org.example.production;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.management.NotificationWrapper;
import org.example.services.ProductionService;
import org.example.utils.Observer;
import org.example.utils.Subject;

import java.util.*;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Rating implements Subject {
    @JsonIgnore
    private Set<Observer> observers;

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
                  @JsonProperty("comment") String comment) {
        this.username = username;
        this.rating = rating;
        this.comment = comment;

        this.observers = new HashSet<Observer>();

        this.visible = true;
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

    public Listing getListing() {
        for (Production production : ProductionService.getProductions())
            if (production.getRatings().contains(this))
                return production;
        return null;
    }

    /** Format: "Username: {username} | Rating: {rating} | Comment: {comment}" */
    public String compactInfo() {
        return "Username: " + username + " | Rating: " + rating + " | Comment: " + comment;
    }

    /** Format: "Listing: {listing} | Rating: {rating} | Comment: {comment}" */
    public String compactInfoForCreator() {
        Listing listing = getListing();
        if (listing == null) //adica inca nu a fost adaugat
            return "Rating: " + rating + " | Comment: " + comment;
        else if (listing instanceof Production production)
            return "Listing: " + production.getTitle() + " | Rating: " + rating + " | Comment: " + comment;
        else if (listing instanceof Actor actor)
            return "Listing: " + actor.getName() + " | Rating: " + rating + " | Comment: " + comment;
        else
            throw new IllegalStateException("Unknown listing type!");
    }

    public void hide() {
        visible = false;
    }

    public void show() {
        visible = true;
    }

    @Override
    public Set<Observer> getObservers() {
        return observers;
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
    public void notifyObservers(NotificationWrapper notificationWrapper) {
        for (Observer observer : observers)
            if (observer != null)
                observer.update(notificationWrapper);
    }

    public String toString() {
        return "Username: " + username + "\n" +
                "Rating: " + rating + "\n" +
                "Comment: " + comment + "\n";
    }
}
