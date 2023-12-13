package org.example.services;

import org.example.IMDB;
import org.example.production.Production;

import java.util.List;

//TODO: Sa-l numesc ProductionsService sau ProductionService?
public class ProductionService {
    public static List<Production> getProductions() {
        return IMDB.getInstance().productions;
    }

    public static void setProductions(List<Production> productions) {
        IMDB.getInstance().productions = productions;
    }

    public static void addProduction(Production production) {
        IMDB.getInstance().productions.add(production);
    }

    public static void removeProduction(Production production) {
        IMDB.getInstance().productions.remove(production);
    }

    public static Production getProductionByTitle(String name) {
        for (Production production : IMDB.getInstance().productions)
            if (production.getTitle().equals(name))
                return production;
        return null;
    }

    public static void updateProduction(Production production) {
        //TODO: Implement updateProduction
    }
}
