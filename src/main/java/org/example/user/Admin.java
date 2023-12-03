package org.example.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.management.Request;
import org.example.production.Actor;
import org.example.production.Production;

import java.util.List;
import java.util.SortedSet;

public class Admin extends Staff {
    public Admin(AdminBuilder builder) {
        super(builder);
    }

    //TODO: Override methods from Staff

    public static class AdminBuilder extends StaffBuilder {
        public AdminBuilder(@JsonProperty("username") String username,
                            @JsonProperty("experience") int experience,
                            @JsonProperty("information") Information information,
                            @JsonProperty("userType") AccountType userType) {
            super(username, experience, information, userType);
        }
    }
}
