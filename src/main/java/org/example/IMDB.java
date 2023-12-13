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

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    public UserInterface getUserInterface() {
        return userInterface;
    }

    public void addProduction(Production p) {
        productions.add(p);
    }

    public void removeProduction(Production p) {
        //TODO: Implement removeProduction dupa enunt
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
