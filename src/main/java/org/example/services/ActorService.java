package org.example.services;

import org.example.IMDB;
import org.example.production.Actor;

import java.util.List;

public class ActorService {
    public static List<Actor> getActors() {
        return IMDB.getInstance().actors;
    }

    public static void setActors(List<Actor> actors) {
        IMDB.getInstance().actors = actors;
    }

    public static void addActor(Actor actor) {
        IMDB.getInstance().actors.add(actor);
    }

    public static void removeActor(Actor actor) {
        IMDB.getInstance().actors.remove(actor);
    }

    public static Actor getActorByName(String name) {
        for (Actor actor : IMDB.getInstance().actors)
            if (actor.getName().equals(name))
                return actor;
        return null;
    }

    public static void updateActor(Actor actor) {
        //TODO: Implement updateActor
    }
}
