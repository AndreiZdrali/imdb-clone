package org.example.management;

import java.util.List;

//TODO: Implement RequestHolder functionality
public class RequestHolder {
    //Lista pentru toti adminii
    private static List<Request> requests;
    private boolean initialized = false; //cred ca o sa folosesc asta

    public static List<Request> getAllRequests() {
        return requests;
    }

    public static void addRequest(Request request) {
        requests.add(request);
    }

    public static void removeRequest(Request request) {
        requests.remove(request);
    }

    //TODO: Sa vad cum fac sa nu mai fac handle prin setRequests
    public static void setRequests(List<Request> o) {
        requests = o;
    }
}
