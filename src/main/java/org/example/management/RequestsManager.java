package org.example.management;

public interface RequestsManager {
    /** Attaches observers to the request and notifies them
     *  Adds the request to the list of requests
     */
    public void createRequest(Request r);

    public void removeRequest(Request r);
}
