package org.example.services;

import org.example.IMDB;
import org.example.production.Actor;

import java.util.List;

public class ActorService {
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
}
