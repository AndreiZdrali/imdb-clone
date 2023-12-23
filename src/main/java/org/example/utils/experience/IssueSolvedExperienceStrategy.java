package org.example.utils.experience;

import org.example.management.Request;
import org.example.management.RequestType;
import org.example.management.RequestStatus;
import org.example.user.User;

public class IssueSolvedExperienceStrategy implements ExperienceStrategy {
    private User<?> user;
    private Request request;

    public IssueSolvedExperienceStrategy(User<?> user, Request request) {
        this.user = user;
        this.request = request;
    }

    @Override
    public int calculateExperience() {
        if (!request.getUsername().equals(user.getUsername()))
            return 0;

        if (request.getStatus() == RequestStatus.SOLVED &&
                (request.getType() == RequestType.MOVIE_ISSUE || request.getType() == RequestType.ACTOR_ISSUE))
            return 1;

        return 0;
    }
}
