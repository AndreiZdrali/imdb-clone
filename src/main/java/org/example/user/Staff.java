package org.example.user;

import java.util.List;
import java.util.SortedSet;
import org.example.management.Request;
import org.example.production.*;

public class Staff extends User implements StaffInterface {
    private List<Request> personalRequests;
    private SortedSet<Listing> addedByMe; //TODO: Sa definesc T (Listings) si sa schimb numele variabilei

    public Staff() {
        //TODO: Implement constructor
    }

    //TODO: Implement methods from StaffInterface
    public void addProductionSystem(Production production) {

    }

    public void addActorSystem(Actor a) {

    }

    public void removeProductionSystem(String name) {

    }

    public void removeActorSystem(String name) {

    }

    public void updateProduction(Production p) {

    }

    public void updateActor(Actor a) {

    }
}
