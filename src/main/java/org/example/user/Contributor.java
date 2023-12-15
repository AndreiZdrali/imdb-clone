package org.example.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.example.management.RequestsManager;
import org.example.management.Request;
import org.example.production.Actor;
import org.example.production.Production;

import java.util.List;
import java.util.SortedSet;

@JsonDeserialize(builder = Contributor.ContributorBuilder.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Contributor<T> extends Staff<T> implements RequestsManager {
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
        public ContributorBuilder(@JsonProperty("username") String username,
                                  @JsonProperty("experience") int experience,
                                  @JsonProperty("information") Information information,
                                  @JsonProperty("userType") AccountType userType) {
            super(username, experience, information, userType);
        }

        public Contributor build() {
            return new Contributor(this);
        }
    }
}
