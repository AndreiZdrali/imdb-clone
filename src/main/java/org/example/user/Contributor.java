package org.example.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import kotlin.NotImplementedError;
import org.example.management.NotificationType;
import org.example.management.NotificationWrapper;
import org.example.management.RequestsManager;
import org.example.management.Request;
import org.example.production.Actor;
import org.example.production.Listing;
import org.example.production.Production;
import org.example.production.Rating;
import org.example.services.RequestService;
import org.example.services.UserService;
import org.example.utils.NotificationsBuilder;

import java.util.List;
import java.util.SortedSet;

@JsonDeserialize(builder = Contributor.ContributorBuilder.class)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Contributor<T> extends Staff<T> implements RequestsManager {
    public Contributor(ContributorBuilder builder) {
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

    public void removeRequest(Request r) {
        if (!r.getUsername().equals(this.username))
            throw new RuntimeException("Nici nu stiu cum s-a ajuns aici");
        RequestService.removeRequest(r);
    }

    @Override
    public void update(NotificationWrapper notification) {
        Listing listing = notification.getListing();
        Rating rating = notification.getRating();
        Request request = notification.getRequest();

        String notificationMessage = null;

        switch (notification.getType()) {
            case NEW_REQUEST:
                // cerere catre contributor
                if (request.getTo().equals(this.username)) {
                    notificationMessage = NotificationsBuilder.newRequest(request);
                    break;
                }
                // cerere facuta de contributor
                else {
                    break;
                }
            case REQUEST_SOLVED:
                // cerere catre contributor
                if (notification.getRequest().getTo().equals(this.username)) {
                    break;
                }
                // cerere facuta de contributor
                else {
                    notificationMessage = NotificationsBuilder.requestSolved(request);
                    break;
                }
            case NEW_REVIEW:
                boolean addedByMe = this.contributions.contains((Comparable<?>) listing);

                notificationMessage = NotificationsBuilder.newReview(listing, rating, addedByMe);
                break;
            default:
                throw new IllegalStateException("Unexpected NotificationType value: " + notification.getType());
        }

        if (notificationMessage != null)
            this.notifications.add(notificationMessage);
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
