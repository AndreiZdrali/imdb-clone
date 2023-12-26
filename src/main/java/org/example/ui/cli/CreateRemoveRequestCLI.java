package org.example.ui.cli;

import kotlin.NotImplementedError;
import org.example.IMDB;
import org.example.management.Request;
import org.example.management.RequestType;
import org.example.management.RequestsManager;
import org.example.production.Actor;
import org.example.production.Production;
import org.example.services.ActorService;
import org.example.services.ProductionService;
import org.example.services.RequestService;
import org.example.services.UserService;
import org.example.user.Contributor;
import org.example.user.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CreateRemoveRequestCLI {
    public static void viewMyRequests() {
        User<?> user = IMDB.getInstance().getUserInterface().getCurrentUser();

        if (!(user instanceof RequestsManager requestsManager))
            throw new RuntimeException("Nu ar trb sa ai acces la requests!");

        List<Request> requests = RequestService.getRequestsCreatedByUser(user);

        if (requests.isEmpty()) {
            System.out.println("You don't have any active requests!");
            System.out.println();
            return;
        }

        for (int i = 0; i < requests.size(); i++) {
            System.out.print(i + 1 + ". ");
            requests.get(i).displayShortInfoForCreator();
        }
        System.out.println();
    }

    public static void createRequest() {
        User<?> user = IMDB.getInstance().getUserInterface().getCurrentUser();

        if (!(user instanceof RequestsManager requestsManager))
            throw new RuntimeException("Nu ar trb sa ai acces la requests!");

        System.out.println("Select the type of request you want to make (0 to cancel): ");
        for (int i = 0; i < RequestType.values().length; i++)
            System.out.println("\t" + (i + 1) + ") " + RequestType.values()[i]);

        int input = IMDB.getInstance().getUserInterface().scanNextInt();

        if (input == 0) {
            System.out.println("Request canceled!");
            System.out.println();
            return;
        }

        if (input < 0 || input > RequestType.values().length) {
            System.out.println("Invalid request type!");
            System.out.println();
            return;
        }

        RequestType requestType = RequestType.values()[input - 1];

        Request.RequestBuilder requestBuilder = new Request.RequestBuilder();
        requestBuilder.setType(requestType);
        requestBuilder.setCreatedDate(LocalDateTime.now());
        requestBuilder.setUsername(IMDB.getInstance().getUserInterface().getCurrentUser().getUsername());

        boolean completed = false;
        switch (requestType) {
            case DELETE_ACCOUNT, OTHERS -> completed = createDeleteAccountOrOthersRequest(requestBuilder);
            case ACTOR_ISSUE -> completed = createActorIssueRequest(requestBuilder);
            case MOVIE_ISSUE -> completed = createMovieIssueRequest(requestBuilder);
        }

        if (!completed)
            return;

        Request request = requestBuilder.build();

        System.out.println("Do you want to send this request?");
        System.out.print("-> Request contents: ");
        request.displayShortInfoForCreator();

        System.out.println("\t1) Yes");
        System.out.println("\t2) No");

        input = IMDB.getInstance().getUserInterface().scanNextInt();

        if (input != 1) {
            System.out.println("Request canceled!");
            System.out.println();
            return;
        }

        requestsManager.createRequest(request);

        System.out.println("Request sent!");
        System.out.println();
    }

    public static void removeRequest() {
        User<?> user = IMDB.getInstance().getUserInterface().getCurrentUser();

        if (!(user instanceof RequestsManager requestsManager))
            throw new RuntimeException("Nu ar trb sa ai acces la requests!");

        List<Request> requests = RequestService.getRequestsCreatedByUser(user);

        if (requests.isEmpty()) {
            System.out.println("You don't have any active requests!");
            System.out.println();
            return;
        }

        System.out.println("Select the request you want to remove (0 to cancel): ");
        for (int i = 0; i < requests.size(); i++) {
            System.out.print("\t" + (i + 1) + ") ");
            requests.get(i).displayShortInfoForCreator();
        }

        int input = IMDB.getInstance().getUserInterface().scanNextInt();

        if (input == 0) {
            System.out.println();
            return;
        }

        if (input < 0 || input > requests.size()) {
            System.out.println("Invalid request number!");
            System.out.println();
            return;
        }

        Request request = requests.get(input - 1);

        System.out.println("Do you want to remove this request?");
        System.out.print("-> Request contents: ");
        request.displayShortInfoForCreator();

        System.out.println("\t1) Yes");
        System.out.println("\t2) No");

        input = IMDB.getInstance().getUserInterface().scanNextInt();

        if (input != 1) {
            System.out.println("Request removal canceled!");
            System.out.println();
            return;
        }

        requestsManager.removeRequest(request);

        System.out.println("Request removed!");
        System.out.println();
    }

    private static boolean createDeleteAccountOrOthersRequest(Request.RequestBuilder requestBuilder) {
        System.out.println("Enter the description of your request: ");

        String description = IMDB.getInstance().getUserInterface().scanNextLineNonBlank();

        requestBuilder.setDescription(description);
        requestBuilder.setTo("ADMIN");

        return true;
    }

    private static boolean createActorIssueRequest(Request.RequestBuilder requestBuilder) {
        System.out.println("Enter the name of the actor: ");

        String actorName = IMDB.getInstance().getUserInterface().scanNextLineNonBlank();

        List<Actor> matchingActors = ActorService.getActors().stream()
                .filter(actor -> actor.getName().toLowerCase().contains(actorName.toLowerCase()))
                .toList();

        User<?> user = IMDB.getInstance().getUserInterface().getCurrentUser();
        if (user instanceof Contributor<?> contributor)
            matchingActors = matchingActors.stream()
                    .filter(actor -> !contributor.getContributions().contains(actor))
                    .toList();

        if (matchingActors.isEmpty()) {
            System.out.println("No actors found!");
            System.out.println();
            return false;
        }

        System.out.println("Enter the number of the actor you want to select (0 to cancel): ");
        for (int i = 0; i < matchingActors.size(); i++) {
            System.out.print("\t" + (i + 1) + ") ");
            matchingActors.get(i).displayShortInfo();
        }

        int input = IMDB.getInstance().getUserInterface().scanNextInt();

        if (input == 0) {
            System.out.println();
            return false;
        }

        if (input < 0 || input > matchingActors.size()) {
            System.out.println("Invalid actor number!");
            System.out.println();
            return false;
        }

        Actor actor = matchingActors.get(input - 1);

        requestBuilder.setActorName(actor.getName());

        System.out.println("Enter the description of your request: ");

        String description = IMDB.getInstance().getUserInterface().scanNextLine();

        while (description.isBlank())
            description = IMDB.getInstance().getUserInterface().scanNextLine();

        requestBuilder.setDescription(description);
        requestBuilder.setTo(ActorService.getCreatorOfActor(actor).getUsername());

        return true;
    }

    private static boolean createMovieIssueRequest(Request.RequestBuilder requestBuilder) {
        System.out.println("Enter the title of the production: ");

        String productionTitle = IMDB.getInstance().getUserInterface().scanNextLineNonBlank();

        List<Production> matchingProductions = ProductionService.getProductions().stream()
                .filter(production -> production.getTitle().toLowerCase().contains(productionTitle.toLowerCase()))
                .toList();

        User<?> user = IMDB.getInstance().getUserInterface().getCurrentUser();
        if (user instanceof Contributor<?> contributor)
            matchingProductions = matchingProductions.stream()
                    .filter(production -> !contributor.getContributions().contains(production))
                    .toList();

        if (matchingProductions.isEmpty()) {
            System.out.println("No productions found!");
            System.out.println();
            return false;
        }

        System.out.println("Enter the number of the production you want to select (0 to cancel): ");
        for (int i = 0; i < matchingProductions.size(); i++) {
            System.out.print("\t" + (i + 1) + ") ");
            matchingProductions.get(i).displayShortInfo();
        }

        int input = IMDB.getInstance().getUserInterface().scanNextInt();

        if (input == 0) {
            System.out.println();
            return false;
        }

        if (input < 0 || input > matchingProductions.size()) {
            System.out.println("Invalid production number!");
            System.out.println();
            return false;
        }

        Production production = matchingProductions.get(input - 1);

        requestBuilder.setMovieTitle(production.getTitle());

        System.out.println("Enter the description of your request *: ");

        String description = IMDB.getInstance().getUserInterface().scanNextLineNonBlank();

        requestBuilder.setDescription(description);
        requestBuilder.setTo(ProductionService.getCreatorOfProduction(production).getUsername());

        return true;
    }
}
