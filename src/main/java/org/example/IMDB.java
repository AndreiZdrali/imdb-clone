package org.example;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.example.ui.UserInterface;
import org.example.ui.CLI;
import org.example.ui.GUI;
import org.example.user.*;
import org.example.production.*;
import org.example.management.*;


public class IMDB {
    private List<User<?>> users;
    private List<Actor> actors;
    private List<Production> productions;
    private List<Request> requests;
    private JSONContext jsonContext;
    private UserInterface userInterface;

    private static IMDB instance = null;

    private IMDB() {
        jsonContext = new JSONContext();
    }

    public static IMDB getInstance() {
        if (instance == null)
            instance = new IMDB();
        return instance;
    }

    public List<User<?>> getUsers() {
        return users;
    }

    public void setUsers(List<User<?>> users) {
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

    public void setUserInterface(UserInterface userInterface) {
        this.userInterface = userInterface;
    }

    private void askForUserInterface() {
        Scanner scanner = new Scanner(System.in);

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
