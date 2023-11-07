package org.example.management;

import java.util.List;

//TODO: Implement RequestHolder functionality
public class RequestHolder {
    private static List<Request> requests;

    public static List<Request> getAllRequests() {
        return requests;
    }

    public static void addRequest(Request request) {
        requests.add(request);
    }

    public static void removeRequest(Request request) {
        requests.remove(request);
    }
}
