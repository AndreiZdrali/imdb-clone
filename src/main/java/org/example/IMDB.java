package org.example;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.example.user.*;
import org.example.production.*;
import org.example.management.*;


public class IMDB {
    public List<User> users;
    public List<Actor> actors;
    public List<Production> productions;
    public List<Request> requests;

    private static IMDB instance = null;

    private IMDB() {
        // CONSTRUCTOR
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

    //TODO: Implement run
    public void run() {
        System.out.println(User.Information.InformationBuilder.class.getCanonicalName());

        File productionsFile = new File("src/main/resources/input/production.json");
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<Production> productions;
        try {
            productions = mapper.readValue(productionsFile, mapper.getTypeFactory().constructCollectionType(List.class, Production.class));
        } catch (IOException e) {
            e.printStackTrace();
            return; //TODO: rescrie partea asta sa fie mai eleganta
        }

        File actorsFile = new File("src/main/resources/input/actors.json");
        try {
            actors = mapper.readValue(actorsFile, mapper.getTypeFactory().constructCollectionType(List.class, Actor.class));
        } catch (IOException e) {
            e.printStackTrace();
            return; //TODO: rescrie partea asta sa fie mai eleganta
        }

        File requestsFile = new File("src/main/resources/input/requests.json");
        try {
            requests =  mapper.readValue(requestsFile, mapper.getTypeFactory().constructCollectionType(List.class, Request.class));
        } catch (IOException e) {
            e.printStackTrace();
            return; //TODO: rescrie partea asta sa fie mai eleganta
        }

        for (Request request : requests) {
            System.out.println(request);
        }

        //Series s = new Series.SeriesBuilder()

        //TODO: Implement Jackson pt User si subclase, ca la Production
    }

    public static void main(String[] args) {
        IMDB.getInstance().run();
    }
}
