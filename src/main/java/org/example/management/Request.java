package org.example.management;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@JsonDeserialize(builder = Request.RequestBuilder.class)
public class Request {
    //private List<Observer> observers = new ArrayList<Observer>();

    private RequestType type;
    private LocalDateTime createdDate;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String movieTitle;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String actorName;
    private String username;
    private String to;
    private String description;

    public Request(RequestBuilder builder) {
        this.type = builder.type;
        this.createdDate = builder.createdDate;
        this.movieTitle = builder.movieTitle;
        this.actorName = builder.actorName;
        this.username = builder.username;
        this.to = builder.to;
        this.description = builder.description;
    }

    //TODO: Implement getters and setters
    public String getUsername() {
        return username;
    }

    public RequestType getType() {
        return type;
    }

    public String getTo() {
        return to;
    }

    public void notifyObservers() {
        //TODO: Subject notify specific observers
    }

    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return "Request: " + type + " " + createdDate.format(formatter) + " " + movieTitle + " " + actorName + " " + username + " " + to + " " + description;
    }

    public static class RequestBuilder {
        @JsonProperty("type")
        private RequestType type;
        @JsonProperty("createdDate")
        private LocalDateTime createdDate;
        @JsonProperty("movieTitle")
        private String movieTitle;
        @JsonProperty("actorName")
        private String actorName;
        @JsonProperty("username")
        private String username;
        @JsonProperty("to")
        private String to;
        @JsonProperty("description")
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
