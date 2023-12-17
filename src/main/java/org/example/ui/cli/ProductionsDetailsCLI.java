package org.example.ui.cli;

import org.example.IMDB;
import org.example.production.Production;
import org.example.production.ProductionType;
import org.example.services.ProductionService;
import org.example.ui.menus.ProductionsDetailsProvider;
import org.example.utils.filters.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductionsDetailsCLI {
    private static FilterList<Production> filters = new FilterList<>();

    public static void viewFilteredProductions() {
        //TODO: Apply filters
        List<Production> filteredProductions = ProductionService.getProductions().stream()
                .filter(p -> filters.stream().allMatch(f -> f.check(p)))
                .toList();

        System.out.println("There are " + filteredProductions.size() + " matching productions: ");
        for (var production : filteredProductions)
            production.displayShortInfo();

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

        filters.add(new ProductionTypeFilter<>(ProductionType.values()[input - 1]));
    }

    public static void setMinRating() {
        System.out.println("Enter the minimum rating: ");

        double input = IMDB.getInstance().getUserInterface().scanNextDouble();

        if (input < 0 || input > 10) {
            System.out.println("Invalid rating!");
            return;
        }

        filters.add((new MinRatingFilter<>(input)));
    }

    public static void setMinReviews() {
        System.out.println("Enter the minimum number of reviews: ");

        int input = IMDB.getInstance().getUserInterface().scanNextInt();

        if (input < 0) {
            System.out.println("Invalid number of reviews!");
            return;
        }

        filters.add(new MinReviewsFilter<>(input));
    }

    public static void clearFilters() {
        filters.clear();
    }

}
