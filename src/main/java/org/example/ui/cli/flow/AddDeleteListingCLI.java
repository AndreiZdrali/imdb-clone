package org.example.ui.cli.flow;

import org.example.IMDB;
import org.example.production.*;
import org.example.user.Staff;
import org.example.user.User;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;

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

        Movie.MovieBuilder movieBuilder = (Movie.MovieBuilder) readProductionFields(ProductionType.Movie);

        System.out.println("Duration (minutes)*: ");
        int duration = IMDB.getInstance().getUserInterface().scanNextInt();

        while (duration < 0) {
            System.out.println("Duration (minutes)*: ");
            duration = IMDB.getInstance().getUserInterface().scanNextInt();
        }

        movieBuilder.setDuration(duration);

        System.out.println("Do you want to add this movie?");
        System.out.print("-> Movie contents: ");
        movieBuilder.build().displayShortInfo();

        System.out.println("\t1) Yes");
        System.out.println("\t2) No");

        int input = IMDB.getInstance().getUserInterface().scanNextInt();

        if (input != 1) {
            System.out.println("Movie canceled!");
            System.out.println();
            return;
        }

        staff.addProductionSystem(movieBuilder.build());

        System.out.println("Movie added!");
        System.out.println();
    }

    public static void addSeries() {
        User<?> user = IMDB.getInstance().getUserInterface().getCurrentUser();

        if (!(user instanceof Staff<?> staff))
            throw new RuntimeException("Doar staff ar trebui sa aiba acces la asta");

        Series.SeriesBuilder seriesBuilder = (Series.SeriesBuilder) readProductionFields(ProductionType.Series);

        System.out.println("Number of seasons*: ");
        int numberOfSeasons = IMDB.getInstance().getUserInterface().scanNextInt();
        while (numberOfSeasons < 0) {
            System.out.println("Number of seasons*: ");
            numberOfSeasons = IMDB.getInstance().getUserInterface().scanNextInt();
        }

        seriesBuilder.setNumSeasons(numberOfSeasons);

        System.out.println("Do you want to add this series?");
        System.out.print("-> Series contents: ");
        seriesBuilder.build().displayShortInfo();

        System.out.println("\t1) Yes");
        System.out.println("\t2) No");

        int input = IMDB.getInstance().getUserInterface().scanNextInt();

        if (input != 1) {
            System.out.println("Series canceled!");
            System.out.println();
            return;
        }

        staff.addProductionSystem(seriesBuilder.build());

        System.out.println("Series added!");
        System.out.println();
    }

    public static void addActor() {
        User<?> user = IMDB.getInstance().getUserInterface().getCurrentUser();

        if (!(user instanceof Staff<?> staff))
            throw new RuntimeException("Doar staff ar trebui sa aiba acces la asta");

        System.out.println("The fields marked with \"*\" are mandatory: ");

        System.out.println("Name *: ");
        String name = IMDB.getInstance().getUserInterface().scanNextLineNonBlank();

        System.out.println("Performances (comma separated): ");
        String performances = IMDB.getInstance().getUserInterface().scanNextLine();
        List<String> performancesList;
        if (performances.isBlank())
            performancesList = null;
        else
            performancesList = Arrays.stream(performances.split(",")).toList();

        System.out.println("Biography: ");
        String bio = IMDB.getInstance().getUserInterface().scanNextLine();

        Actor actor = new Actor(name, null, bio);

        System.out.println("Do you want to add this actor?");
        System.out.print("-> Actor contents: ");
        actor.displayShortInfo();

        System.out.println("\t1) Yes");
        System.out.println("\t2) No");

        int input = IMDB.getInstance().getUserInterface().scanNextInt();

        if (input != 1) {
            System.out.println("Actor canceled!");
            System.out.println();
            return;
        }

        staff.addActorSystem(actor);

        System.out.println("Actor added!");
        System.out.println();
    }

    public static void deleteListing() {
        User<?> user = IMDB.getInstance().getUserInterface().getCurrentUser();

        if (!(user instanceof Staff<?> staff))
            throw new RuntimeException("Doar staff ar trebui sa aiba acces la asta");

        SortedSet<Comparable> listings = staff.getContributions();

        if (listings.isEmpty()) {
            System.out.println("You don't have any listings!");
            System.out.println();
            return;
        }

        System.out.println("Select the listing you want to delete (0 to cancel): ");
        for (int i = 0; i < listings.size(); i++) {
            System.out.print("\t" + (i + 1) + ") ");
            ((Listing) listings.toArray()[i]).displayShortInfo();
        }

        int input = IMDB.getInstance().getUserInterface().scanNextInt();

        if (input == 0) {
            System.out.println();
            return;
        }

        if (input < 0 || input > listings.size()) {
            System.out.println("Invalid listing number!");
            System.out.println();
            return;
        }

        Listing listing = (Listing) listings.toArray()[input - 1];

        System.out.println("Do you want to delete this listing?");
        System.out.print("-> Listing contents: ");
        listing.displayShortInfo();

        System.out.println("\t1) Yes");
        System.out.println("\t2) No");

        input = IMDB.getInstance().getUserInterface().scanNextInt();

        if (input != 1) {
            System.out.println("Listing canceled!");
            System.out.println();
            return;
        }

        if (listing instanceof Production production)
            staff.removeProductionSystem(production);
        else if (listing instanceof Actor actor)
            staff.removeActorSystem(actor);
        else
            throw new IllegalStateException("Invalid listing type! Cum nu e nici productie nici actor?");
    }

    private static Production.ProductionBuilder readProductionFields(ProductionType type) {
        if (type != ProductionType.Movie && type != ProductionType.Series)
            throw new IllegalArgumentException("Invalid production type!");

        User<?> user = IMDB.getInstance().getUserInterface().getCurrentUser();

        if (!(user instanceof Staff<?> staff))
            throw new RuntimeException("Doar staff ar trebui sa aiba acces la asta");


        System.out.println("The fields marked with \"*\" are mandatory: ");
        System.out.println("Title *: ");
        String title = IMDB.getInstance().getUserInterface().scanNextLineNonBlank();

        System.out.println("Directors (comma separated): ");
        String directors = IMDB.getInstance().getUserInterface().scanNextLine();
        List<String> directorsList;
        if (directors.isBlank())
            directorsList = null;
        else
            directorsList = Arrays.stream(directors.split(",")).toList();

        System.out.println("Actors (comma separated): ");
        String actors = IMDB.getInstance().getUserInterface().scanNextLine();
        List<String> actorsList;
        if (actors.isBlank())
            actorsList = null;
        else
            actorsList = Arrays.stream(actors.split(",")).toList();

        System.out.println("Genres (comma separated): ");

        String genres = IMDB.getInstance().getUserInterface().scanNextLine();
        List<Genre> genresList;
        if (genres.isBlank())
            genresList = null;
        else
            genresList = Arrays.stream(genres.split(","))
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
        String plot = IMDB.getInstance().getUserInterface().scanNextLineNonBlank();

        System.out.println("Release year *: ");
        int releaseYear = IMDB.getInstance().getUserInterface().scanNextInt();
        while (releaseYear < 0) {
            System.out.println("Release year *: ");
            releaseYear = IMDB.getInstance().getUserInterface().scanNextInt();
        }

        if (type == ProductionType.Movie)
            return new Movie.MovieBuilder(title, type, directorsList,
                    actorsList, genresList, null, plot, releaseYear);
        else return new Series.SeriesBuilder(title, type, directorsList,
                actorsList, genresList, null, plot, releaseYear);
    }
}
