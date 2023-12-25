package org.example.user;

import org.example.management.Request;
import org.example.management.RequestStatus;
import org.example.production.Actor;
import org.example.production.Production;

public interface StaffInterface {
    /** Attaches observers to the production and notifies them
     *  Adds the production to the list of contributions
     *  Adds the production to the list of productions
     */
    public void addProductionSystem(Production production);

    /** Attaches observers to the actor and notifies them
     *  Adds the actor to the list of actors
     */
    public void addActorSystem(Actor actor);

    /** Removes the production from the list of productions
     *  Removes the production from the list of contributions
     *  Removes the staff member from the list of observers
     */
    public void removeProductionSystem(Production production);

    /** Removes the actor from the list of actors
     *  Removes the actor from the list of contributions
     *  Removes the staff member from the list of observers
     */
    public void removeActorSystem(Actor actor);

    public void updateProduction(Production production);

    public void updateActor(Actor actor);

    /** Removes the request from the lists
     *  Sets the request's status and notifies the observers
     *  Updates the creating user's experience
     */
    public void solveRequest(Request request, RequestStatus status);
}
