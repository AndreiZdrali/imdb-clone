package org.example.ui.cli.flow;

import org.example.IMDB;
import org.example.production.Production;
import org.example.production.Rating;
import org.example.services.ProductionService;
import org.example.user.Regular;
import org.example.user.User;

import java.util.List;

public class AddDeleteReviewCLI {
    public static void viewReviews() {
        User<?> user = IMDB.getInstance().getUserInterface().getCurrentUser();

        if (!(user instanceof Regular<?> regular))
            throw new RuntimeException("Nu ar trb sa ai acces la reviews!");

        List<Rating> reviews = regular.getReviews();

        if (reviews.isEmpty()) {
            System.out.println("You don't have any reviews yet!");
            System.out.println();
            return;
        }

        System.out.println("You have " + reviews.size() + " reviews: ");
        for (int i = 0; i < reviews.size(); i++) {
            System.out.print(i + 1 + ". ");
            System.out.println(reviews.get(i).compactInfoForCreator());
        }
        System.out.println();
    }

    public static void addReview() {
        User<?> user = IMDB.getInstance().getUserInterface().getCurrentUser();

        if (!(user instanceof Regular<?> regular))
            throw new RuntimeException("Nu ar trb sa ai acces la reviews!");

        System.out.println("Enter the title of the production: ");

        String productionTitle = IMDB.getInstance().getUserInterface().scanNextLineNonBlank();

        List<Production> matchingProductions = ProductionService.getProductions().stream()
                .filter(production -> production.getTitle().toLowerCase().contains(productionTitle.toLowerCase()))
                .toList();

        if (matchingProductions.isEmpty()) {
            System.out.println("No productions found!");
            System.out.println();
            return;
        }

        System.out.println("Enter the number of the production you want to select (0 to cancel): ");
        for (int i = 0; i < matchingProductions.size(); i++) {
            System.out.print("\t" + (i + 1) + ") ");
            matchingProductions.get(i).displayShortInfo();
        }

        int input = IMDB.getInstance().getUserInterface().scanNextInt();

        if (input == 0) {
            System.out.println();
            return;
        }

        if (input < 0 || input > matchingProductions.size()) {
            System.out.println("Invalid production number!");
            System.out.println();
            return;
        }

        Production production = matchingProductions.get(input - 1);

        System.out.println("Enter the rating (1-10) *: ");

        int rating = IMDB.getInstance().getUserInterface().scanNextInt();

        if (rating < 1 || rating > 10) {
            System.out.println("Invalid rating!");
            System.out.println();
            return;
        }

        System.out.println("Enter the comment *: ");

        String comment = IMDB.getInstance().getUserInterface().scanNextLineNonBlank();

        Rating review = new Rating(regular.getUsername(), rating, comment);

        System.out.println("Do you want to post this review?");
        System.out.println("-> Review contents: ");
        System.out.println(review.compactInfoForCreator());

        System.out.println("\t1) Yes");
        System.out.println("\t2) No");

        input = IMDB.getInstance().getUserInterface().scanNextInt();

        if (input != 1) {
            System.out.println("Review discarded!");
            System.out.println();
            return;
        }

        regular.addRating(production, review);

        System.out.println("Review added!");
        System.out.println();
    }

    //TODO: Implement deleteReview (nu e obligatoriu)
    public static void deleteReview() {
        throw new RuntimeException("Not implemented yet!");
    }
}
