package org.example.utils.experience;

import org.example.production.Listing;
import org.example.user.Contributor;
import org.example.user.User;

public class NewListingExperienceStrategy implements ExperienceStrategy {
    private User<?> user;
    private Listing listing;

    public NewListingExperienceStrategy(User<?> user, Listing listing) {
        this.user = user;
    }

    @Override
    public int calculateExperience() {
        if (user instanceof Contributor<?>)
            return 1;
        return 0;
    }
}
