package org.example.ui.cli;

import kotlin.NotImplementedError;
import org.example.IMDB;
import org.example.production.Listing;
import org.example.production.Production;
import org.example.services.ActorService;
import org.example.services.ProductionService;
import org.example.ui.menus.*;
import org.example.user.User;

import java.util.ArrayList;
import java.util.List;

public class MainMenuCLI {
    public static void viewProductionsDetails() {
        IMDB.getInstance().getUserInterface().setMenuProvider(ProductionsDetailsProvider.getInstance());
    }

    public static void viewActorsDetails() {
        IMDB.getInstance().getUserInterface().setMenuProvider(ActorsDetailsProvider.getInstance());
    }

    public static void viewNotifications() {
        User<?> user = IMDB.getInstance().getUserInterface().getCurrentUser();
        System.out.println("You have " + user.getNotifications().size() + " notifications: ");
        for (var notification : user.getNotifications())
            System.out.println("\t-> " + notification);
        System.out.println();

        IMDB.getInstance().getUserInterface().setMenuProvider(NotificationsProvider.getInstance());
    }

    public static void searchForListing() {
        System.out.println("Enter the listing name: ");

        String input = IMDB.getInstance().getUserInterface().scanNextLine();

        while (input.isBlank())
            input = IMDB.getInstance().getUserInterface().scanNextLine();

        List<Listing> matchingListings = new ArrayList<>();

        for (var production : ProductionService.getProductions())
            if (production.getTitle().toLowerCase().contains(input.toLowerCase()))
                matchingListings.add(production);
        for (var actor : ActorService.getActors())
            if (actor.getName().toLowerCase().contains(input.toLowerCase()))
                matchingListings.add(actor);

        if (matchingListings.isEmpty()) {
            System.out.println("No listings found!");
            System.out.println();
            return;
        }

        System.out.println("There are " + matchingListings.size() + " matching listings: ");
        for (int i = 0; i < matchingListings.size(); i++) {
            System.out.print(i + 1 + ". ");
            matchingListings.get(i).displayInfo();
            if (i != matchingListings.size() - 1)
                System.out.println("--------------------");
        }
        System.out.println();
    }

    public static void addDeleteFavorite() {
        IMDB.getInstance().getUserInterface().setMenuProvider(FavoritesProvider.getInstance());
    }

    public static void createRemoveRequest() {
        IMDB.getInstance().getUserInterface().setMenuProvider(CreateRemoveRequestProvider.getInstance());
    }

    public static void addDeleteListing() {
        IMDB.getInstance().getUserInterface().setMenuProvider(AddDeleteListingProvider.getInstance());
    }

    //TODO: Implement the rest of the options
    public static void updateListing() {
        throw new NotImplementedError("Not implemented yet");
    }

    public static void solveRequest() {
        throw new NotImplementedError("Not implemented yet");
    }

    public static void addDeleteReview() {
        throw new NotImplementedError("Not implemented yet");
    }

    public static void addDeleteUser() {
        throw new NotImplementedError("Not implemented yet");
    }

    public static void logout() {
        throw new NotImplementedError("Not implemented yet");
    }
}
