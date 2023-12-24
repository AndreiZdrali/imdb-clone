package org.example;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import kotlin.NotImplementedError;
import org.example.management.Request;
import org.example.management.RequestHolder;
import org.example.management.RequestsManager;
import org.example.production.Actor;
import org.example.production.Production;
import org.example.production.Rating;
import org.example.services.ActorService;
import org.example.services.ProductionService;
import org.example.services.RequestService;
import org.example.services.UserService;
import org.example.user.Admin;
import org.example.user.Staff;
import org.example.user.User;
import org.example.utils.Subject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//TODO: Implement write methods
public class JSONContext {
    private String accountsJSONPath;
    private String actorsJSONPath;
    private String productionsJSONPath;
    private String requestsJSONPath;
    ObjectMapper mapper;

    public JSONContext() {
        accountsJSONPath = "src/main/resources/input/accounts.json";
        actorsJSONPath = "src/main/resources/input/actors.json";
        productionsJSONPath = "src/main/resources/input/production.json";
        requestsJSONPath = "src/main/resources/input/requests.json";

        mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
    }

    public String getAccountsJSONPath() {
        return accountsJSONPath;
    }

    public void setAccountsJSONPath(String accountsJSONPath) {
        this.accountsJSONPath = accountsJSONPath;
    }

    public String getActorsJSONPath() {
        return actorsJSONPath;
    }

    public void setActorsJSONPath(String actorsJSONPath) {
        this.actorsJSONPath = actorsJSONPath;
    }

    public String getProductionsJSONPath() {
        return productionsJSONPath;
    }

    public void setProductionsJSONPath(String productionsJSONPath) {
        this.productionsJSONPath = productionsJSONPath;
    }

    public String getRequestsJSONPath() {
        return requestsJSONPath;
    }

    public void setRequestsJSONPath(String requestsJSONPath) {
        this.requestsJSONPath = requestsJSONPath;
    }

    public void LoadJSONData() {
        LoadActorsJSONData();
        LoadProductionsJSONData();
        LoadRequestsJSONData();
        LoadAccountsJSONData();

        LinkRequestsToUsers();
        LinkObserversToSubjects();
    }

    public void LoadAccountsJSONData() {
        try {
            UserService.setUsers(mapper
                    .readValue(new File(accountsJSONPath), mapper.getTypeFactory().constructCollectionType(List.class, User.class)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void SaveAccountsJSONData() {
        try {
            mapper.writeValue(new File(accountsJSONPath), UserService.getUsers());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void LoadActorsJSONData() {
        File actorsFile = new File(actorsJSONPath);
        try {
            ActorService.setActors(mapper
                    .readValue(actorsFile, mapper.getTypeFactory().constructCollectionType(List.class, Actor.class)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void SaveActorsJSONData() {
        try {
            mapper.writeValue(new File(actorsJSONPath), ActorService.getActors());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void LoadProductionsJSONData() {
        File productionsFile = new File(productionsJSONPath);
        try {
            ProductionService.setProductions(mapper
                    .readValue(productionsFile, mapper.getTypeFactory().constructCollectionType(List.class, Production.class)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void LoadRequestsJSONData() {
        File requestsFile = new File(requestsJSONPath);
        try {
            IMDB.getInstance().setRequests(mapper
                    .readValue(requestsFile, mapper.getTypeFactory().constructCollectionType(List.class, Request.class)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void LinkRequestsToUsers() {
        //Shared requests
        for (Request request : RequestService.getAllRequests())
            if (request.getUsername().equals("ADMIN"))
                RequestHolder.addSharedRequest(request);

        //Personal requests
        for (User user : UserService.getUsers()) {
            if (!(user instanceof Staff s))
                continue;
            for (Request request : RequestService.getAllRequests())
                if (request.getTo().equals(s.getUsername()))
                    s.addPersonalRequest(request);
        }
    }

    public void LinkObserversToSubjects() {
        // requests publice
        for (Request request : RequestService.getSharedRequests())
            for (Admin<?> admin : UserService.getAdmins())
                request.addObserver(admin);

        // requests personale
        for (Staff<?> staff : UserService.getStaff())
            for (Request request : staff.getPersonalRequests())
                request.addObserver(staff);

        // requests create de user
        for (Request request : RequestService.getAllRequests()) {
            User<?> user = UserService.getUserByUsername(request.getUsername());
            if (user instanceof RequestsManager)
                request.addObserver(user);
        }

        // productions + actors pentru staff
        for (Staff<?> staff : UserService.getStaff())
            for (Object listing : staff.getContributions())
                if (listing instanceof Subject subject)
                    subject.addObserver(staff);

        // productions cu review pentru useri
        for (Production production : ProductionService.getProductions()) {
            for (Rating rating : production.getRatings()) {
                User<?> user = UserService.getUserByUsername(rating.getUsername());
                if (user instanceof RequestsManager) {
                    production.addObserver(user);
                    rating.addObserver(user);
                }
            }
        }

        // favorite pentru useri
        for (User<?> user : UserService.getUsers()) {
            for (Comparable listing : user.getFavorites()) {
                if (!(listing instanceof Subject subject))
                    throw new RuntimeException("Cum nu e asta subject???????");
                subject.addObserver(user);
            }
        }
    }
}
