package org.example.user;

import org.example.production.Actor;
import org.example.production.Production;

public interface StaffInterface {
    /** Attaches observers to the production and notifies them
     *  Adds the production to the list of productions
     */
    public void addProductionSystem(Production production);

    /** Attaches observers to the actor and notifies them
     *  Adds the actor to the list of actors
     */
    public void addActorSystem(Actor a);

    public void removeProductionSystem(String name);

    public void removeActorSystem(String name);

    public void updateProduction(Production p);

    public void updateActor(Actor a);

    //TODO: Methods to handle requests
}
