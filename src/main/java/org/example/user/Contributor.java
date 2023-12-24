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
import org.example.production.Production;
import org.example.services.RequestService;
import org.example.services.UserService;

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

        NotificationWrapper notification = new NotificationWrapper(NotificationType.NEW_REQUEST, null, request);
        request.notifyObservers(notification);

        RequestService.addRequest(request);
    }

    public void removeRequest(Request r) {
        //TODO: Implement removeRequest
    }

    @Override
    public void update(NotificationWrapper notification) {
        switch (notification.getType()) {
            case NEW_REQUEST:
                // cerere catre contributor
                if (notification.getRequest().getTo().equals(this.username)) {
                    throw new NotImplementedError("Notificare ca am primit o cerere noua");
                }
                // cerere facuta de contributor
                else {
                    break;
                }
            case REQUEST_SOLVED:
                // cerere catre contributor
                if (notification.getRequest().getTo().equals(this.username)) {
                    throw new NotImplementedError("Notificare ca a fost rezolvata cererea");
                }
                // cerere facuta de contributor
                else {
                    throw new NotImplementedError("Sa dau notificare la contributor ca i-a fost rezolvata cererea");
                }
            case NEW_REVIEW:
                throw new NotImplementedError("Sa dau notificare la contributor ca a fost adaugata o recenzie");
            default:
                throw new IllegalStateException("Unexpected NotificationType value: " + notification.getType());
        }
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
