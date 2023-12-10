package org.example;

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.ui.UserInterface;
import org.example.ui.CLI;
import org.example.ui.GUI;
import org.example.user.*;
import org.example.production.*;
import org.example.management.*;


public class IMDB {
    public List<User> users;
    public List<Actor> actors;
    public List<Production> productions;
    public List<Request> requests;
    public JSONContext jsonContext;
    public Scanner scanner;
    public UserInterface userInterface;

    private static IMDB instance = null;

    private IMDB() {
        jsonContext = new JSONContext();
        scanner = new Scanner(System.in);
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

    public UserInterface getUserInterface() {
        return userInterface;
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

    private void askForUserInterface() {
        System.out.println("Choose user interface:");
        System.out.println("\t1) CLI");
        System.out.println("\t2) GUI");
        try {
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    userInterface = new CLI();
                    break;
                case 2:
                    userInterface = new GUI();
                    break;
                default:
                    throw new InputMismatchException();
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid option");
            scanner.next();
            askForUserInterface();
        }
    }

    //TODO: Implement run
    public void run() {
        jsonContext.LoadJSONData();

        askForUserInterface();

        userInterface.run();
    }

    public static void main(String[] args) {
        IMDB.getInstance().run();
    }
}
