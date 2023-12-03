package org.example.user;

import org.example.management.RequestsManager;
import org.example.management.Request;
import org.example.production.Actor;
import org.example.production.Production;

import java.util.List;
import java.util.SortedSet;

public class Contributor extends Staff implements RequestsManager {
    public Contributor(ContributorBuilder builder) {
        super(builder);
    }

    public void createRequest(Request r) {
        //TODO: Implement createRequest
    }

    public void removeRequest(Request r) {
        //TODO: Implement removeRequest
    }

    public static class ContributorBuilder extends StaffBuilder {
        public ContributorBuilder(String username,
                                  int experience,
                                  Information information,
                                  AccountType userType) {
            super(username, experience, information, userType);
        }

        public Contributor build() {
            return new Contributor(this);
        }
    }
}
