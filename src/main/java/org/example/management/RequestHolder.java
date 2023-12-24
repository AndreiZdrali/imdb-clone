package org.example.management;

import java.util.ArrayList;
import java.util.List;

/** Lista pentru toti adminii */
public class RequestHolder {
    private static List<Request> sharedRequests = new ArrayList<Request>();

    public static List<Request> getSharedRequests() {
        return sharedRequests;
    }

    public static void setSharedRequests(List<Request> requests) {
        sharedRequests = requests;
    }

    public static void addSharedRequest(Request request) {
        sharedRequests.add(request);
    }

    public static void removeSharedRequest(Request request) {
        sharedRequests.remove(request);
    }
}
