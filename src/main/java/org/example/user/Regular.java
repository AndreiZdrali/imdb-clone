package org.example.user;

import org.example.management.Request;
import org.example.management.RequestsManager;
import org.example.production.Actor;
import org.example.production.Production;

import java.util.List;
import java.util.SortedSet;

public class Regular extends User implements RequestsManager {
    public Regular(RegularBuilder builder) {
        super(builder);
    }

    public void createRequest(Request r) {
        //TODO: Implement createRequest
    }

    public void removeRequest(Request r) {
        //TODO: Implement removeRequest
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
