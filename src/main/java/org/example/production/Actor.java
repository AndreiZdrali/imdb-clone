package org.example.production;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Map;

public class Actor implements Listing {
    private String name;
    private Map<String, Genre> appearances;
    private String biography;

    public Actor() {
        //TODO: Implement constructor
    }
}
