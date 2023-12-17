package org.example.utils.filters;

import org.example.production.Actor;

public class MinPerformancesFilter<T extends Actor> implements Filter<T> {
    private int minPerformances;

    public MinPerformancesFilter(int minPerformances) {
        this.minPerformances = minPerformances;
    }

    @Override
    public boolean check(T actor) {
        return actor.getPerformances().size() >= minPerformances;
    }
}
