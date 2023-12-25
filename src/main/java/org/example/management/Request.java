package org.example.management;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.example.serializers.LocalDateTimeToStringSerializer;
import org.example.utils.Observer;
import org.example.utils.Subject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@JsonDeserialize(builder = Request.RequestBuilder.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Request implements Subject {
    @JsonIgnore
    private Set<Observer> observers;

    private RequestType type;
    @JsonSerialize(using = LocalDateTimeToStringSerializer.class, as = String.class)
    private LocalDateTime createdDate;
    private String movieTitle;
    private String actorName;
    private String username;
    private String to;
    private String description;
    @JsonIgnore
    private RequestStatus status;

    public Request(RequestBuilder builder) {
        this.type = builder.type;
        this.createdDate = builder.createdDate;
        this.movieTitle = builder.movieTitle;
        this.actorName = builder.actorName;
        this.username = builder.username;
        this.to = builder.to;
        this.description = builder.description;

        this.status = RequestStatus.PENDING;
        this.observers = new HashSet<>();
    }

    public String getUsername() {
        return username;
    }

    public RequestType getType() {
        return type;
    }

    public String getTo() {
        return to;
    }

    public RequestStatus getStatus() {
        return status;
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
        for (var observer : observers)
            if (observer != null)
                observer.update(notificationWrapper);
    }

    public String shortInfoForCreator() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(type).append(" created on ").append(createdDate.format(formatter));
        switch(type) {
            case DELETE_ACCOUNT, OTHERS -> stringBuilder.append(": ").append(description);
            case MOVIE_ISSUE -> stringBuilder.append(" for movie ").append(movieTitle).append(": ").append(description);
            case ACTOR_ISSUE -> stringBuilder.append(" for actor ").append(actorName).append(": ").append(description);
        }
        return stringBuilder.toString();
    }

    public void displayShortInfoForCreator() {
        System.out.print(shortInfoForCreator());
        System.out.println();
    }

    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return "Request: " + type + " " + createdDate.format(formatter) + " " + movieTitle + " " + actorName + " " + username + " " + to + " " + description;
    }

    public static class RequestBuilder {
        private RequestType type;
        private LocalDateTime createdDate;
        private String movieTitle;
        private String actorName;
        private String username;
        private String to;
        private String description;

        @JsonCreator
        public RequestBuilder() { }

        @JsonProperty("type")
        public RequestBuilder setType(RequestType type) {
            this.type = type;
            return this;
        }

        @JsonProperty("createdDate")
        public RequestBuilder setCreatedDate(String createdDate) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
            this.createdDate = LocalDateTime.parse(createdDate, formatter);
            return this;
        }

        // Pentru cand o creez din cod, ii dau direct LocalDateTime
        public RequestBuilder setCreatedDate(LocalDateTime createdDate) {
            this.createdDate = createdDate;
            return this;
        }

        @JsonProperty("movieTitle")
        public RequestBuilder setMovieTitle(String movieTitle) {
            this.movieTitle = movieTitle;
            return this;
        }

        @JsonProperty("actorName")
        public RequestBuilder setActorName(String actorName) {
            this.actorName = actorName;
            return this;
        }

        @JsonProperty("username")
        public RequestBuilder setUsername(String username) {
            this.username = username;
            return this;
        }

        @JsonProperty("to")
        public RequestBuilder setTo(String to) {
            this.to = to;
            return this;
        }

        @JsonProperty("description")
        public RequestBuilder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Request build() {
            return new Request(this);
        }
    }
}
