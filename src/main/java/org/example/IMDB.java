package org.example;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.DeserializationFeature;
import org.example.user.*;
import org.example.production.*;

import com.fasterxml.jackson.databind.ObjectMapper;

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
        File file = new File("src/main/resources/input/production.json");
        ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        List<Production> productions;
        try {
            productions = mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(List.class, Production.class));
        } catch (IOException e) {
            e.printStackTrace();
            return; //TODO: rescrie partea asta sa fie mai eleganta
        }

        for (Production p : productions) {
            p.displayInfo();
            System.out.println("=================");
        }
    }

    public static void main(String[] args) {
        IMDB.getInstance().run();
    }
}
