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
            return;
        }

        //TODO: Din lista de matching listings, sa aleg unul si sa il adaug la favorite
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    public static void removeFavorite() {
        throw new UnsupportedOperationException("Not implemented yet!");
    }
}
