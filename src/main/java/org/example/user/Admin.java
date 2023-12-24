package org.example.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import kotlin.NotImplementedError;
import org.example.management.NotificationWrapper;
import org.example.management.NotificationType;
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

    @Override
    public void update(NotificationWrapper notification) {
        switch (notification.getType()) {
            case NEW_REQUEST:
                throw new NotImplementedError("Sa dau notificare la admin ca a primit o cerere noua");
            case REQUEST_SOLVED:
                break;
            case NEW_REVIEW:
                throw new NotImplementedError("Notificare daca e review nou pe un listing adaugat de admin");
            default:
                throw new IllegalStateException("Unexpected NotificationType value: " + notification.getType());
        }
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
