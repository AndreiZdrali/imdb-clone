package org.example.ui.cli.flow;

import org.example.IMDB;
import org.example.management.NotificationType;
import org.example.management.Request;
import org.example.management.RequestStatus;
import org.example.services.RequestService;
import org.example.user.Staff;
import org.example.user.User;

import java.util.ArrayList;
import java.util.List;

public class SolveRequestCLI {
    public static void viewPendingRequests() {
        User<?> user = IMDB.getInstance().getUserInterface().getCurrentUser();

        if (!(user instanceof Staff<?> staff))
            throw new RuntimeException("Only staff members can view pending requests!");

        List<Request> requestList = new ArrayList<>();
        requestList.addAll(RequestService.getPersonalRequests(staff));
        requestList.addAll(RequestService.getSharedRequests());

        if (requestList.isEmpty()) {
            System.out.println("There are no pending requests!");
            System.out.println();
            return;
        }

        System.out.println("You have " + requestList.size() + " pending requests: ");
        for (int i = 0; i < requestList.size(); i++) {
            System.out.print(i + 1 + ". ");
            requestList.get(i).displayInfo();
            if (i != requestList.size() - 1)
                System.out.println("--------------------");
        }
        System.out.println();
    }

    //TODO: Nu am testat asta inca
    public static void markRequestAsSolved() {
        User<?> user = IMDB.getInstance().getUserInterface().getCurrentUser();

        if (!(user instanceof Staff<?> staff))
            throw new RuntimeException("Only staff members can mark requests as solved!");

        List<Request> requestList = new ArrayList<>();
        requestList.addAll(RequestService.getPersonalRequests(staff));
        requestList.addAll(RequestService.getSharedRequests());

        if (requestList.isEmpty()) {
            System.out.println("There are no pending requests!");
            System.out.println();
            return;
        }

        System.out.println("Select the request you want to mark as handled (0 to cancel): ");
        for (int i = 0; i < requestList.size(); i++) {
            System.out.print("\t" + (i + 1) + ") ");
            requestList.get(i).displayShortInfoForCreator();
        }

        int input = IMDB.getInstance().getUserInterface().scanNextInt();

        if (input == 0) {
            System.out.println();
            return;
        }

        if (input < 0 || input > requestList.size()) {
            System.out.println("Invalid request number!");
            System.out.println();
            return;
        }

        Request request = requestList.get(input - 1);

        System.out.println("What do you want to mark this request as:");
        System.out.println("\t1) Solved");
        System.out.println("\t2) Rejected");
        System.out.println("\t3) Cancel");

        input = IMDB.getInstance().getUserInterface().scanNextInt();

        if (input == 3) {
            System.out.println();
            return;
        }

        if (input < 1 || input > 2) {
            System.out.println("Invalid option!");
            System.out.println();
            return;
        }

        if (input == 1) {
            staff.solveRequest(request, RequestStatus.SOLVED);
            System.out.println("Request marked as solved!");
        } else {
            staff.solveRequest(request, RequestStatus.REJECTED);
            System.out.println("Request marked as rejected!");
        }
        System.out.println();
    }
}
