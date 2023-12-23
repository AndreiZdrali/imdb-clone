package org.example.utils.experience;

import org.example.production.Production;
import org.example.user.ExperienceStrategy;
import org.example.user.User;

public class NewRatingExperienceStrategy implements ExperienceStrategy {
    private User<?> user;
    private Production production;

    public NewRatingExperienceStrategy(User<?> user, Production production) {
        this.user = user;
        this.production = production;
    }

    @Override
    public int calculateExperience() {
        for (var rating : production.getRatings()) {
            if (rating.getUsername().equals(user.getUsername())) {
                return 0;
            }
        }

        return 1;
    }
}
