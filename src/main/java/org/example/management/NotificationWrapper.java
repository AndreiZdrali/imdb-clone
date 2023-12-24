package org.example.management;

import kotlin.NotImplementedError;
import org.example.production.Listing;
import org.example.production.Rating;

/** Notificare si detalii despre, se trimite prin notify */
public class NotificationWrapper {
    private NotificationType type;
    private Listing listing;
    private Request request;

    public NotificationWrapper(NotificationType type, Listing listing, Request request) {
        this.type = type;
        this.listing = listing;
        this.request = request;

        switch (type) {
            case REQUEST_SOLVED:
                break;
            case NEW_REVIEW:
                if (listing == null)
                    throw new IllegalArgumentException("Rating notification should have details");
                break;
            case NEW_REQUEST:
                if (request == null)
                    throw new IllegalArgumentException("Request notification should have details");
                break;
            default:
                throw new IllegalArgumentException("Invalid notification type: " + type);
        }
    }

    public NotificationType getType() {
        return type;
    }

    public Listing getListing() {
        return listing;
    }

    public Request getRequest() {
        return request;
    }

    public String getNotificationMessage() {
        throw new NotImplementedError();
    }
}
