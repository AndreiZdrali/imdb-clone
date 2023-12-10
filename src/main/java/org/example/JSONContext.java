package org.example;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.management.Request;
import org.example.management.RequestHolder;
import org.example.production.Actor;
import org.example.production.Production;
import org.example.user.Staff;
import org.example.user.User;

import java.io.File;
import java.io.IOException;
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

        mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
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
    }

    //TODO: Implement Jackson pt User si subclase, ca la Production (cred ca e gata)
    public void LoadAccountsJSONData() {
        try {
            IMDB.getInstance().setUsers(mapper
                    .readValue(new File(accountsJSONPath), mapper.getTypeFactory().constructCollectionType(List.class, User.class)));
        } catch (IOException e) {
            e.printStackTrace();
            //TODO: Posibil sa dau throw la eroare
        }
    }

    public void LoadActorsJSONData() {
        File actorsFile = new File(actorsJSONPath);
        try {
            IMDB.getInstance().setActors(mapper
                    .readValue(actorsFile, mapper.getTypeFactory().constructCollectionType(List.class, Actor.class)));
        } catch (IOException e) {
            e.printStackTrace();
            //TODO: Posibil sa dau throw la eroare
        }
    }

    public void LoadProductionsJSONData() {
        File productionsFile = new File(productionsJSONPath);
        try {
            IMDB.getInstance().setProductions(mapper
                    .readValue(productionsFile, mapper.getTypeFactory().constructCollectionType(List.class, Production.class)));
        } catch (IOException e) {
            e.printStackTrace();
            //TODO: Posibil sa dau throw la eroare
        }
    }

    public void LoadRequestsJSONData() {
        File requestsFile = new File(requestsJSONPath);
        try {
            IMDB.getInstance().setRequests(mapper
                    .readValue(requestsFile, mapper.getTypeFactory().constructCollectionType(List.class, Request.class)));
        } catch (IOException e) {
            e.printStackTrace();
            //TODO: Posibil sa dau throw la eroare
        }
    }

    public void LinkRequestsToUsers() {
        //Shared requests
        for (Request request : IMDB.getInstance().getRequests())
            if (request.getUsername() == "ADMIN")
                RequestHolder.addSharedRequest(request);

        //Personal requests
        for (User user : IMDB.getInstance().getUsers()) {
            if (!(user instanceof Staff s))
                continue;
            for (Request request : IMDB.getInstance().getRequests())
                if (request.getUsername().equals(s.getUsername()))
                    s.addPersonalRequest(request);
        }
    }
}
