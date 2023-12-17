package org.example.services;

import org.example.IMDB;
import org.example.production.Actor;
import org.example.utils.filters.FilterList;
import org.example.utils.filters.Filter;

import java.util.Comparator;
import java.util.List;

public class ActorService {
    private static FilterList<Actor> filters = new FilterList<>();
    private static Comparator<Actor> sortingMethod = Comparator.comparing(Actor::getName);

    public static List<Actor> getActors() {
        return IMDB.getInstance().getActors();
    }

    public static void setActors(List<Actor> actors) {
        IMDB.getInstance().setActors(actors);
    }

    public static void addActor(Actor actor) {
        IMDB.getInstance().getActors().add(actor);
    }

    public static void removeActor(Actor actor) {
        IMDB.getInstance().getActors().remove(actor);
    }

    public static Actor getActorByName(String name) {
        for (Actor actor : getActors())
            if (actor.getName().equals(name))
                return actor;
        return null;
    }

    public static void updateActor(Actor actor) {
        //TODO: Implement updateActor
    }

    public static void addActorFilter(Filter<Actor> filter) {
        filters.add(filter);
    }

    public static void clearActorFilters() {
        filters.clear();
    }

    public static void setSortingMethod(Comparator<Actor> comparator) {
        sortingMethod = comparator;
    }

    public static void clearSortingMethod() {
        sortingMethod = Comparator.comparing(Actor::getId);
    }

    public static void clearSortingAndFilters() {
        clearActorFilters();
        clearSortingMethod();
    }

    public static List<Actor> getSortedAndFilteredActors() {
        return getActors().stream()
                .filter(a -> filters.stream().allMatch(f -> f.check(a)))
                .sorted(sortingMethod)
                .toList();
    }
}
