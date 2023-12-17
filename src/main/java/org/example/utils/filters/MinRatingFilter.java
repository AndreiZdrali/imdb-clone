package org.example.utils.filters;

import org.example.production.Production;

public class MinRatingFilter<T extends Production> implements Filter<T> {
    private double minRating;

    public MinRatingFilter(double minRating) {
        this.minRating = minRating;
    }

    @Override
    public boolean check(T production) {
        return production.getAverageRating() >= minRating;
    }
}
