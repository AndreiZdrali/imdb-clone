package org.example.services;

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
        return IMDB.getInstance().requests;
    }

    public static void setAllRequests(List<Request> requests) {
        IMDB.getInstance().requests = requests;
    }

    public static List<Request> getSharedRequests() {
        return RequestHolder.getSharedRequests();
    }

    public static void setSharedRequests(List<Request> requests) {
        RequestHolder.setSharedRequests(requests);
    }

    /** Request-urile care trb rezolvate de user */
    public static List<Request> getPersonalRequests(String username) {
        User u = UserService.getUserByUsername(username);
        if (!(u instanceof Staff s))
            return null;
        return s.getPersonalRequests();
    }

    /** Request-urile care trb rezolvate de user */
    public static List<Request> getPersonalRequests(Staff staff) {
        return staff.getPersonalRequests();
    }

    /** Request-urile care trb rezolvate de user */
    public static void setPersonalRequests(String username, List<Request> requests) {
        User u = UserService.getUserByUsername(username);
        if (!(u instanceof Staff s))
            return;
        s.setPersonalRequests(requests);
    }

    /** Request-urile care trb rezolvate de user */
    public static void setPersonalRequests(Staff staff, List<Request> requests) {
        staff.setPersonalRequests(requests);
    }

    public static List<Request> getRequestsMadeByUser(User user) {
        List<Request> requests = new ArrayList<>();
        for (Request request : RequestService.getAllRequests())
            if (request.getUsername().equals(user.getUsername()))
                requests.add(request);
        return requests;
    }

    public static List<Request> getRequestsMadeByUser(String username) {
        User u = UserService.getUserByUsername(username);
        if (u == null)
            return null;
        return getRequestsMadeByUser(u);
    }

    /** Adauga si in shared/personal requests */
    public static void addRequest(Request request) {
        IMDB.getInstance().requests.add(request);

        RequestType type = request.getType();
        if (type == RequestType.DELETE_ACCOUNT || type == RequestType.OTHERS) {
            RequestHolder.addSharedRequest(request);
        } else {
            User u = UserService.getUserByUsername(request.getUsername());
            if (!(u instanceof Staff s))
                return;
            s.getPersonalRequests().add(request);
        }

        //TODO: Save JSON
    }

    public static void removeRequest(Request request) {
        //TODO: Implement removeRequest dupa enunt
        //Shared il scot cu RequestHolder.removeSharedRequest, personal direct din IMDB
    }
}
