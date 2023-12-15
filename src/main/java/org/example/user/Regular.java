package org.example.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.example.IMDB;
import org.example.management.Request;
import org.example.management.RequestsManager;
import org.example.production.Actor;
import org.example.production.Production;
import org.example.production.Series;
import org.example.services.RequestService;

import java.util.List;
import java.util.SortedSet;

@JsonDeserialize(builder = Regular.RegularBuilder.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Regular<T> extends User<T> implements RequestsManager {
    public Regular(RegularBuilder builder) {
        super(builder);
    }

    public void createRequest(Request request) {
        RequestService.addRequest(request);
    }

    public void removeRequest(Request request) {
        //TODO: throw la eroare???
        if (!request.getUsername().equals(this.username))
            return;
        RequestService.removeRequest(request);
    }

    public void addRating() {
        //TODO: Implement addRating
    }

    public static class RegularBuilder extends UserBuilder {
        public RegularBuilder(@JsonProperty("username") String username,
                              @JsonProperty("experience") int experience,
                              @JsonProperty("information") Information information,
                              @JsonProperty("userType") AccountType userType) {
            super(username, experience, information, userType);
        }

        public Regular build() {
            return new Regular(this);
        }
    }
}
