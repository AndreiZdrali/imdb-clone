package org.example.services;

import org.example.IMDB;
import org.example.production.Production;
import org.example.utils.filters.Filter;
import org.example.utils.filters.FilterList;

import java.util.Comparator;
import java.util.List;

public class ProductionService {
    private static FilterList<Production> filters = new FilterList<>();
    private static Comparator<Production> sortingMethod = Comparator.comparing(Production::getTitle);

    public static List<Production> getProductions() {
        return IMDB.getInstance().getProductions();
    }

    public static void setProductions(List<Production> productions) {
        IMDB.getInstance().setProductions(productions);
    }

    public static void addProduction(Production production) {
        IMDB.getInstance().getProductions().add(production);
    }

    public static void removeProduction(Production production) {
        IMDB.getInstance().getProductions().remove(production);
    }

    public static Production getProductionByTitle(String name) {
        for (Production production : getProductions())
            if (production.getTitle().equals(name))
                return production;
        return null;
    }

    public static void updateProduction(Production production) {
        //TODO: Implement updateProduction
    }

    public static void addProductionFilter(Filter<Production> filter) {
        filters.add(filter);
    }

    public static void clearProductionFilters() {
        filters.clear();
    }

    public static void setSortingMethod(Comparator<Production> comparator) {
        sortingMethod = comparator;
    }

    public static void clearSortingMethod() {
        sortingMethod = Comparator.comparing(Production::getId);
    }

    public static void clearSortingAndFilters() {
        clearProductionFilters();
        clearSortingMethod();
    }

    public static List<Production> getSortedAndFilteredProductions() {
        return getProductions().stream()
                .filter(p -> filters.stream().allMatch(f -> f.check(p)))
                .sorted(sortingMethod)
                .toList();
    }
}
