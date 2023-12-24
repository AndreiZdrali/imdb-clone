package org.example.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.example.management.*;
import org.example.production.*;
import org.example.services.RequestService;
import org.example.services.UserService;
import org.example.utils.NotificationsBuilder;

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

        NotificationWrapper notification = new NotificationWrapper(NotificationType.NEW_REQUEST, null, null, request);
        request.notifyObservers(notification);

        RequestService.addRequest(request);
    }

    public void removeRequest(Request request) {
        if (!request.getUsername().equals(this.username))
            throw new RuntimeException("Nici nu stiu cum s-a ajuns aici");
        RequestService.removeRequest(request);
    }

    public void addRating() {
        //TODO: Implement addRating
    }

    @Override
    public void update(NotificationWrapper notification) {
        Listing listing = notification.getListing();
        Rating rating = notification.getRating();
        Request request = notification.getRequest();

        String notificationMessage = null;

        switch (notification.getType()) {
            case NEW_REQUEST:
                break;
            case REQUEST_SOLVED:
                notificationMessage = NotificationsBuilder.requestSolved(request);
                break;
            case NEW_REVIEW:
                notificationMessage = NotificationsBuilder.newReview(listing, rating, false);
                break;
            default:
                throw new IllegalStateException("Unexpected NotificationType value: " + notification.getType());
        }

        if (notificationMessage != null)
            this.notifications.add(notificationMessage);
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
