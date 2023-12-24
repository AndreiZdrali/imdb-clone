package org.example.utils;

import org.example.management.Request;
import org.example.management.RequestStatus;
import org.example.production.*;

public final class NotificationsBuilder {
    private NotificationsBuilder() {}

    /**
     * "Ai primit o cerere de tipul {type} noua de la utilizatorul {username}"
     */
    public static String newRequest(Request request) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Ai primit o cerere noua de tipul ");
        stringBuilder.append(request.getType()).append(" de la utilizatorul ");
        stringBuilder.append(request.getUsername());

        return stringBuilder.toString();
    }

    /**
     * "Cererea ta "{requestInfo}" a fost {status}"
     */
    public static String requestSolved(Request request) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Cererea ta \"").append(request.shortInfoForCreator());
        if (request.getStatus() == RequestStatus.SOLVED)
            stringBuilder.append("\" a fost rezolvata");
        else
            stringBuilder.append("\" a fost respinsa");

        return stringBuilder.toString();
    }

    /**
     * "{type} {title} pe care {addedByMe} a primit un review de la utilizatorul {username} -> {rating}"
     * @param addedByMe true => "l-ai adaugat",
     *                  false => "il ai in lista de favorite"
     */
    public static String newReview(Listing listing, Rating rating, boolean addedByMe) {
        StringBuilder stringBuilder = new StringBuilder();

        if (listing instanceof Movie movie)
            stringBuilder.append("Filmul \"").append(movie.getTitle()).append("\"");
        else if (listing instanceof Series series)
            stringBuilder.append("Serialul \"").append(series.getTitle()).append("\"");
        else if (listing instanceof Actor actor)
            stringBuilder.append("Actorul \"").append(actor.getName()).append("\"");
        else
            throw new IllegalStateException("Unexpected Listing value: " + listing);

        if (addedByMe)
            stringBuilder.append(" pe care l-ai adaugat");
        else
            stringBuilder.append(" pe care il ai in lista de favorite");

        stringBuilder.append(" a primit un review de la utilizatorul \"");
        stringBuilder.append(rating.getUsername()).append("\"").append(" -> ").append(rating.getRating());

        return stringBuilder.toString();
    }

}
