package org.example.utils.filters;

import org.example.production.Production;

public class MinReviewsFilter<T extends Production> implements Filter<T> {
    private final int minReviews;

    public MinReviewsFilter(int minReviews) {
        this.minReviews = minReviews;
    }

    public boolean check(Production object) {
        return object.getRatings().size() >= minReviews;
    }
}
