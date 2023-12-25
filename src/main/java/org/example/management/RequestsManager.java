package org.example.management;

public interface RequestsManager {
    /** Adds observers to the request and notifies them
     *  Adds the request to the list of requests
     */
    public void createRequest(Request r);

    /** Removes the request from the list of requests
     *  Removes the observers from the request
     */
    public void removeRequest(Request r);
}
