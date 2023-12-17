package org.example.ui.cli;

import org.example.IMDB;
import org.example.production.Actor;
import org.example.services.ActorService;
import org.example.utils.filters.Filter;
import org.example.utils.filters.FilterList;
import org.example.utils.filters.MinPerformancesFilter;

import java.util.Comparator;
import java.util.List;

public class ActorsDetailsCLI {
    public static void viewSortedAndFilteredActors() {
        List<Actor> filteredActors = ActorService.getSortedAndFilteredActors();

        System.out.println("There are " + filteredActors.size() + " matching actors: ");
        for (var actor : filteredActors)
            actor.displayShortInfo();
        System.out.println();
    }

    public static void setMinPerformances() {
        System.out.println("Enter the minimum number of performances: ");

        int input = IMDB.getInstance().getUserInterface().scanNextInt();

        if (input < 0) {
            System.out.println("Invalid number of performances!");
            return;
        }

        Filter<Actor> filter = new MinPerformancesFilter<>(input);
        ActorService.addActorFilter(filter);
    }

    public static void setSortingMethod() {
        System.out.println("Choose a sorting method: ");
        System.out.println("\t1) By ID (ascending)");
        System.out.println("\t2) By ID (descending)");
        System.out.println("\t3) By name (alphabetically)");
        System.out.println("\t4) By name (reverse alphabetically)");

        int input = IMDB.getInstance().getUserInterface().scanNextInt();

        if (input < 1 || input > 4) {
            System.out.println("Invalid sorting method!");
            return;
        }

        Comparator<Actor> sortingMethod;
        switch (input) {
            case 1 -> sortingMethod = Comparator.comparing(Actor::getId);
            case 2 -> sortingMethod = Comparator.comparing(Actor::getId).reversed();
            case 3 -> sortingMethod = Comparator.comparing(Actor::getName);
            case 4 -> sortingMethod = Comparator.comparing(Actor::getName).reversed();
            default -> throw new RuntimeException("Invalid sorting method! Nu inteleg cum de s-a ajuns aici.");
        }

        ActorService.setSortingMethod(sortingMethod);

    }

    public static void clearSortingAndFilters() {
        ActorService.clearSortingAndFilters();
    }
}
