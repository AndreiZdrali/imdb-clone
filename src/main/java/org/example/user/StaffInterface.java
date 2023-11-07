package org.example.user;

import org.example.production.Actor;
import org.example.production.Production;

public interface StaffInterface {
    public void addProductionSystem(Production production);

    public void addActorSystem(Actor a);

    public void removeProductionSystem(String name);

    public void removeActorSystem(String name);

    public void updateProduction(Production p);

    public void updateActor(Actor a);

    //TODO: Methods to handle requests
}
