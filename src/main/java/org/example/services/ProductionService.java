package org.example.services;

import org.example.IMDB;
import org.example.production.Production;

import java.util.List;

//TODO: Sa-l numesc ProductionsService sau ProductionService?
public class ProductionService {
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
}
