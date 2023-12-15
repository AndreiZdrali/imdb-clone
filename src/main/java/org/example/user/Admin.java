package org.example.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.example.management.Request;
import org.example.production.Actor;
import org.example.production.Production;

import java.util.List;
import java.util.SortedSet;

@JsonDeserialize(builder = Admin.AdminBuilder.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Admin<T> extends Staff<T> {
    public Admin(AdminBuilder builder) {
        super(builder);
    }

    //TODO: Override methods from Staff
    public void addProductionSystem(Production production) {

    }

    public void addActorSystem(Actor a) {

    }

    public void removeProductionSystem(String name) {

    }

    public void removeActorSystem(String name) {

    }

    public void updateProduction(Production p) {

    }

    public void updateActor(Actor a) {

    }

    public static class AdminBuilder extends StaffBuilder {
        public AdminBuilder(@JsonProperty("username") String username,
                            @JsonProperty("experience") int experience,
                            @JsonProperty("information") Information information,
                            @JsonProperty("userType") AccountType userType) {
            super(username, experience, information, userType);
        }

        public Admin build() {
            return new Admin(this);
        }
    }
}
