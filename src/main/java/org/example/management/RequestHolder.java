package org.example.management;

import java.util.List;

//TODO: Implement RequestHolder functionality
public class RequestHolder {
    //Lista pentru toti adminii
    private static List<Request> sharedRequests;
    private boolean initialized = false; //cred ca o sa folosesc asta

    public static List<Request> getAllSharedRequests() {
        return sharedRequests;
    }

    public static void addSharedRequest(Request request) {
        sharedRequests.add(request);
    }

    public static void removeSharedRequest(Request request) {
        sharedRequests.remove(request);
    }

    public static void setSharedRequests(List<Request> requests) {
        sharedRequests = requests;
    }
}
