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
    private List<Regular> regular;
    private List<Contribuitor> contributors;
    private List<Admin> admins;
    private List<Actor> actors;
    private List<Movie> movies;
    private List<Series> series;

    private static IMDB instance = null;

    private IMDB() {
        // CONSTRUCTOR
    }

    public static IMDB getInstance() {
        if (instance == null)
            instance = new IMDB();
        return instance;
    }

    public void run() {
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
            RequestHolder.setRequests(mapper.readValue(requestsFile, mapper.getTypeFactory().constructCollectionType(List.class, Request.class)));
        } catch (IOException e) {
            e.printStackTrace();
            return; //TODO: rescrie partea asta sa fie mai eleganta
        }

        for (Request r : RequestHolder.getAllRequests()) {
            System.out.println(r);
        }
    }

    public static void main(String[] args) {
        IMDB.getInstance().run();
    }
}
