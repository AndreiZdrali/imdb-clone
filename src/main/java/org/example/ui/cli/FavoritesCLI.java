package org.example.ui.cli;

import org.example.IMDB;
import org.example.production.Actor;
import org.example.production.Listing;
import org.example.production.Production;
import org.example.services.ActorService;
import org.example.services.ProductionService;
import org.example.user.User;

import java.util.ArrayList;
import java.util.List;

public class FavoritesCLI {
    public static void viewFavorites() {
        User<?> user = IMDB.getInstance().getUserInterface().getCurrentUser();

        if (user.getFavorites().isEmpty()) {
            System.out.println("You don't have any favorites yet!");
            System.out.println();
            return;
        }

        int i = 1;
        System.out.println("You have " + user.getFavorites().size() + " favorites: ");
        for (var favorite : user.getFavorites()) {
            System.out.print(i++ + ". ");
            if (favorite instanceof Production production)
                production.displayShortInfo();
            else if (favorite instanceof Actor actor)
                actor.displayShortInfo();
        }
        System.out.println();
    }

    public static void addFavorite() {
        User<?> user = IMDB.getInstance().getUserInterface().getCurrentUser();

        System.out.println("Enter the listing name: ");

        String input = IMDB.getInstance().getUserInterface().scanNextLineNonBlank();

        List<Listing> matchingListings = new ArrayList<>();

        for (var production : ProductionService.getProductions())
            if (production.getTitle().toLowerCase().contains(input.toLowerCase()) &&
                    !user.getFavorites().contains(production))
                matchingListings.add(production);

        for (var actor : ActorService.getActors())
            if (actor.getName().toLowerCase().contains(input.toLowerCase()) &&
                    !user.getFavorites().contains(actor))
                matchingListings.add(actor);

        if (matchingListings.isEmpty()) {
            System.out.println("No listings found!");
            System.out.println();
            return;
        }

        System.out.println("Enter the number of the listing you want to add to favorites (0 to cancel): ");
        for (int i = 0; i < matchingListings.size(); i++) {
            System.out.print("\t" + (i + 1) + ") ");
            matchingListings.get(i).displayShortInfo();
        }
        System.out.println();

        int input2 = IMDB.getInstance().getUserInterface().scanNextInt();

        if (input2 == 0) {
            System.out.println();
            return;
        }

        if (input2 < 0 || input2 > matchingListings.size()) {
            System.out.println("Invalid listing number!");
            System.out.println();
            return;
        }

        user.addFavorite((Comparable) matchingListings.get(input2 - 1));

        System.out.println("Added to favorites!");
        System.out.println();
    }

    public static void removeFavorite() {
        User<?> user = IMDB.getInstance().getUserInterface().getCurrentUser();

        System.out.println("Enter the number of the listing you want to remove from favorites (0 to cancel): ");
        for (int i = 0; i < user.getFavorites().size(); i++) {
            System.out.print("\t" + (i + 1) + ") ");
            if (user.getFavorites().toArray()[i] instanceof Production production)
                production.displayShortInfo();
            else if (user.getFavorites().toArray()[i] instanceof Actor actor)
                actor.displayShortInfo();
        }

        int input = IMDB.getInstance().getUserInterface().scanNextInt();

        if (input == 0) {
            System.out.println();
            return;
        }

        if (input < 0 || input > user.getFavorites().size()) {
            System.out.println("Invalid listing number!");
            System.out.println();
            return;
        }

        user.removeFavorite((Comparable) user.getFavorites().toArray()[input - 1]);
    }
}
