package org.example.services;

import kotlin.NotImplementedError;
import org.example.IMDB;
import org.example.management.Request;
import org.example.management.RequestHolder;
import org.example.management.RequestType;
import org.example.user.Staff;
import org.example.user.User;

import java.util.ArrayList;
import java.util.List;

public class RequestService {
    public static List<Request> getAllRequests() {
        return IMDB.getInstance().getRequests();
    }

    public static void setAllRequests(List<Request> requests) {
        IMDB.getInstance().setRequests(requests);
    }

    public static List<Request> getSharedRequests() {
        return RequestHolder.getSharedRequests();
    }

    public static void setSharedRequests(List<Request> requests) {
        RequestHolder.setSharedRequests(requests);
    }

    /** Request-urile care trb rezolvate de user */
    public static List<Request> getPersonalRequests(String username) {
        User<?> u = UserService.getUserByUsername(username);
        if (!(u instanceof Staff<?> s))
            return null;
        return s.getPersonalRequests();
    }

    /** Request-urile care trb rezolvate de user */
    public static List<Request> getPersonalRequests(Staff<?> staff) {
        return staff.getPersonalRequests();
    }

    /** Request-urile care trb rezolvate de user */
    public static void setPersonalRequests(String username, List<Request> requests) {
        User<?> u = UserService.getUserByUsername(username);
        if (!(u instanceof Staff<?> s))
            return;
        s.setPersonalRequests(requests);
    }

    /** Request-urile care trb rezolvate de user */
    public static void setPersonalRequests(Staff<?> staff, List<Request> requests) {
        staff.setPersonalRequests(requests);
    }

    public static List<Request> getRequestsCreatedByUser(User<?> user) {
        List<Request> requests = new ArrayList<>();
        for (Request request : RequestService.getAllRequests())
            if (request.getUsername().equals(user.getUsername()))
                requests.add(request);
        return requests;
    }

    public static List<Request> getRequestsCreatedByUser(String username) {
        User<?> u = UserService.getUserByUsername(username);
        if (u == null)
            return null;
        return getRequestsCreatedByUser(u);
    }

    /** Adauga in IMDB.requests si shared/personal in functie de tip */
    public static void addRequest(Request request) {
        IMDB.getInstance().getRequests().add(request);

        RequestType type = request.getType();
        if (type == RequestType.DELETE_ACCOUNT || type == RequestType.OTHERS) {
            RequestHolder.addSharedRequest(request);
        } else {
            User<?> u = UserService.getUserByUsername(request.getTo());
            if (!(u instanceof Staff<?> s))
                throw new RuntimeException("Ar trebui sa fie staff");
            s.addPersonalRequest(request);
        }
    }

    /** Sterge din IMDB.requests si shared/personal in functie de tip */
    public static void removeRequest(Request request) {
        IMDB.getInstance().getRequests().remove(request);

        RequestType type = request.getType();
        if (type == RequestType.DELETE_ACCOUNT || type == RequestType.OTHERS) {
            RequestHolder.removeSharedRequest(request);
        } else {
            User<?> u = UserService.getUserByUsername(request.getTo());
            if (!(u instanceof Staff<?> s))
                throw new RuntimeException("Ar trebui sa fie staff");
            s.removePersonalRequest(request);
        }
    }
}
