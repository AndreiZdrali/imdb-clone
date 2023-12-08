package org.example.user;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.example.IMDB;
import org.example.management.Request;
import org.example.management.RequestsManager;
import org.example.production.Actor;
import org.example.production.Production;
import org.example.production.Series;

import java.util.List;
import java.util.SortedSet;

@JsonDeserialize(builder = Regular.RegularBuilder.class)
public class Regular extends User implements RequestsManager {
    public Regular(RegularBuilder builder) {
        super(builder);
    }

    public void createRequest(Request request) {
        IMDB.getInstance().addRequest(request);
    }

    public void removeRequest(Request request) {
        //TODO: throw la eroare???
        if (!request.getUsername().equals(this.username))
            return;
        IMDB.getInstance().removeRequest(request);
    }

    public void addRating() {
        //TODO: Implement addRating
    }

    public static class RegularBuilder extends UserBuilder {
        public RegularBuilder(String username,
                              int experience,
                              Information information,
                              AccountType userType) {
            super(username, experience, information, userType);
        }

        public Regular build() {
            return new Regular(this);
        }
    }
}
