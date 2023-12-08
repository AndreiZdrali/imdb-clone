package org.example;

import java.util.List;

import org.example.user.*;
import org.example.production.*;
import org.example.management.*;


public class IMDB {
    public List<User> users;
    public List<Actor> actors;
    public List<Production> productions;
    public List<Request> requests;
    public JSONContext jsonContext;

    private static IMDB instance = null;

    private IMDB() {
        jsonContext = new JSONContext();
    }

    public static IMDB getInstance() {
        if (instance == null)
            instance = new IMDB();
        return instance;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public List<Production> getProductions() {
        return productions;
    }

    public void setProductions(List<Production> productions) {
        this.productions = productions;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }


    public void addActor(Actor a) {
        actors.add(a);
    }

    public void removeActor(Actor a) {
        //TODO: Implement removeActor dupa enunt
    }

    public void addProduction(Production p) {
        productions.add(p);
    }

    public void removeProduction(Production p) {
        //TODO: Implement removeProduction dupa enunt
    }

    /** Adauga si in shared/personal requests */
    public void addRequest(Request r) {
        requests.add(r);

        RequestType type = r.getType();
        if (type == RequestType.DELETE_ACCOUNT || type == RequestType.OTHERS) {
            RequestHolder.addSharedRequest(r);
        } else {
            for (User u : users) {
                if (!(u instanceof Staff s))
                    continue;
                if (s.getUsername().equals(r.getTo())) {
                    s.addPersonalRequest(r);
                    break;
                }
            }
        }

        //TODO: Save JSON
    }

    public void removeRequest(Request r) {
        //TODO: Implement removeRequest dupa enunt
    }

    //TODO: Implement run
    public void run() {
        System.out.println(User.Information.InformationBuilder.class.getCanonicalName());

        jsonContext.LoadJSONData();

        for (User u : users) {
            System.out.println(u);
        }
    }

    public static void main(String[] args) {
        IMDB.getInstance().run();
    }
}
