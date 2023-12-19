package org.example.ui.cli;

import org.example.IMDB;
import org.example.production.Production;
import org.example.production.ProductionType;
import org.example.services.ProductionService;
import org.example.utils.filters.*;

import java.util.Comparator;
import java.util.List;

public class ProductionsDetailsCLI {
    public static void viewSortedAndFilteredProductions() {
        List<Production> filteredProductions = ProductionService.getSortedAndFilteredProductions();

        if (filteredProductions.isEmpty()) {
            System.out.println("No productions found!");
            return;
        }

        System.out.println("There are " + filteredProductions.size() + " matching productions: ");
        for (int i = 0; i < filteredProductions.size(); i++) {
            System.out.print(i + 1 + ". ");
            filteredProductions.get(i).displayShortInfo();
        }
        System.out.println();
    }

    public static void setProductionType() {
        System.out.println("Choose a production type: ");
        for (int i = 0; i < ProductionType.values().length; i++)
            System.out.println("\t" + (i + 1) + ") " + ProductionType.values()[i]);

        int input = IMDB.getInstance().getUserInterface().scanNextInt();

        if (input < 0 || input > ProductionType.values().length) {
            System.out.println("Invalid production type!");
            return;
        }

        Filter<Production> filter = new ProductionTypeFilter<>(ProductionType.values()[input - 1]);
        ProductionService.addProductionFilter(filter);
    }

    public static void setMinRating() {
        System.out.println("Enter the minimum rating: ");

        double input = IMDB.getInstance().getUserInterface().scanNextDouble();

        if (input < 0 || input > 10) {
            System.out.println("Invalid rating!");
            return;
        }

        Filter<Production> filter = new MinRatingFilter<>(input);
        ProductionService.addProductionFilter(filter);
    }

    public static void setMinReviews() {
        System.out.println("Enter the minimum number of reviews: ");

        int input = IMDB.getInstance().getUserInterface().scanNextInt();

        if (input < 0) {
            System.out.println("Invalid number of reviews!");
            return;
        }

        Filter<Production> filter = new MinReviewsFilter<>(input);
        ProductionService.addProductionFilter(filter);
    }

    public static void setSortingMethod() {
        System.out.println("Choose a sorting method: ");
        System.out.println("\t1) By ID (ascending)");
        System.out.println("\t2) By ID (descending)");
        System.out.println("\t3) By title (alphabetically)");
        System.out.println("\t4) By title (reverse alphabetically)");

        int input = IMDB.getInstance().getUserInterface().scanNextInt();

        if (input < 1 || input > 4) {
            System.out.println("Invalid sorting method!");
            return;
        }

        Comparator<Production> sortingMethod;
        switch (input) {
            case 1 -> sortingMethod = Comparator.comparing(Production::getId);
            case 2 -> sortingMethod = Comparator.comparing(Production::getId).reversed();
            case 3 -> sortingMethod = Comparator.comparing(Production::getTitle);
            case 4 -> sortingMethod = Comparator.comparing(Production::getTitle).reversed();
            default -> throw new RuntimeException("Invalid sorting method! Nu inteleg cum de s-a ajuns aici.");
        }

        ProductionService.setSortingMethod(sortingMethod);
    }

    public static void clearSortingAndFilters() {
        ProductionService.clearSortingAndFilters();
    }

}
