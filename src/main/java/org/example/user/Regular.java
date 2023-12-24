package org.example.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import kotlin.NotImplementedError;
import org.example.IMDB;
import org.example.management.NotificationType;
import org.example.management.NotificationWrapper;
import org.example.management.Request;
import org.example.management.RequestsManager;
import org.example.production.Actor;
import org.example.production.Production;
import org.example.production.Series;
import org.example.services.RequestService;
import org.example.services.UserService;

import java.util.List;
import java.util.SortedSet;

@JsonDeserialize(builder = Regular.RegularBuilder.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Regular<T> extends User<T> implements RequestsManager {
    public Regular(RegularBuilder builder) {
        super(builder);
    }

    public void createRequest(Request request) {
        request.addObserver(this);
        if (request.getTo().equals("ADMIN"))
            for (Admin<?> admin : UserService.getAdmins())
                request.addObserver(admin);
        else
            request.addObserver(UserService.getUserByUsername(request.getTo()));

        NotificationWrapper notification = new NotificationWrapper(NotificationType.NEW_REQUEST, null, request);
        request.notifyObservers(notification);

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

    @Override
    public void update(NotificationWrapper notification) {
        switch (notification.getType()) {
            case NEW_REQUEST:
                break;
            case REQUEST_SOLVED:
                throw new NotImplementedError("Sa dau notificare la user ca i-a fost rezolvata cererea");
            case NEW_REVIEW:
                throw new NotImplementedError("Sa dau notificare la user ca a fost adaugata o recenzie");
            default:
                throw new IllegalStateException("Unexpected NotificationType value: " + notification.getType());
        }
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
