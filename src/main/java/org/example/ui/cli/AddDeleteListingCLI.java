package org.example.ui.cli;

import org.example.IMDB;
import org.example.production.*;
import org.example.user.Staff;
import org.example.user.User;

import java.util.Arrays;
import java.util.List;

public class AddDeleteListingCLI {
    public static void viewContributions() {
        User<?> user = IMDB.getInstance().getUserInterface().getCurrentUser();

        if (!(user instanceof Staff<?> staff))
            throw new RuntimeException("Doar staff ar trebui sa aiba acces la asta");

        if (staff.getContributions().isEmpty()) {
            System.out.println("You don't have any contributions yet!");
            System.out.println();
            return;
        }

        int i = 1;
        System.out.println("You have " + staff.getContributions().size() + " contributions: ");
        for (var contribution : staff.getContributions()) {
            System.out.print(i++ + ". ");
            if (contribution instanceof Production production)
                production.displayShortInfo();
            else if (contribution instanceof Actor actor)
                actor.displayShortInfo();
        }
        System.out.println();
    }

    public static void addMovie() {
        User<?> user = IMDB.getInstance().getUserInterface().getCurrentUser();

        if (!(user instanceof Staff<?> staff))
            throw new RuntimeException("Doar staff ar trebui sa aiba acces la asta");


        System.out.println("The fields marked with \"*\" are mandatory: ");
        System.out.println("Title *: ");

        String title = IMDB.getInstance().getUserInterface().scanNextLine();

        System.out.println("Directors (comma separated) *: ");

        String directors = IMDB.getInstance().getUserInterface().scanNextLine();
        List<String> directorsList = Arrays.stream(directors.split(",")).toList();

        System.out.println("Actors (comma separated) *: ");

        String actors = IMDB.getInstance().getUserInterface().scanNextLine();
        List<String> actorsList = Arrays.stream(actors.split(",")).toList();

        System.out.println("Genres (comma separated) *: ");

        String genres = IMDB.getInstance().getUserInterface().scanNextLine();
        List<Genre> genresList = Arrays.stream(genres.split(","))
                .filter(genre -> {
                    try {
                        Genre.valueOf(genre);
                        return true;
                    } catch (IllegalArgumentException e) {
                        return false;
                    }
                })
                .map(Genre::valueOf)
                .toList();

        System.out.println("Plot *: ");
        String plot = IMDB.getInstance().getUserInterface().scanNextLine();

        Movie.MovieBuilder movieBuilder = new Movie.MovieBuilder(title, ProductionType.Movie,
                directorsList, actorsList, genresList, null, plot);

        //TODO: Sa extrag asta de mai sus intr-o metoda comuna pentru Production
        // si sa mai citesc si parametrii din Builder pt fiecare tip
        // AICI AM RAMAS
    }

    public static void addSeries() {
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    public static void addActor() {
        throw new UnsupportedOperationException("Not implemented yet!");
    }

    public static void deleteListing() {
        throw new UnsupportedOperationException("Not implemented yet!");
    }
}
