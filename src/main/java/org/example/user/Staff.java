package org.example.user;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.example.IMDB;
import org.example.management.Request;
import org.example.production.*;
import org.example.utils.serializers.ActorToStringSerializer;
import org.example.utils.serializers.ProductionToStringSerializer;

public abstract class Staff extends User implements StaffInterface {
    //TODO: Sa vad cum initializez personalRequests, probabil in load
    @JsonIgnore
    protected List<Request> personalRequests;
    @JsonIgnore
    public SortedSet<Comparable> contributions;
    @JsonProperty("productionsContribution")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonSerialize(using = ProductionToStringSerializer.class, as = String.class)
    protected SortedSet<Production> productionsContribution;
    @JsonProperty("actorsContribution")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonSerialize(using = ActorToStringSerializer.class, as = String.class)
    protected SortedSet<Actor> actorsContribution;

    public Staff(StaffBuilder builder) {
        super(builder);
        this.productionsContribution = builder.productionsContribution;
        this.actorsContribution = builder.actorsContribution;

        this.contributions = new TreeSet<>();
        this.contributions.addAll(this.productionsContribution);
        this.contributions.addAll(this.actorsContribution);
    }

    //TODO: Implement setters and getters
    public List<Request> getPersonalRequests() {
        return personalRequests;
    }

    public void setPersonalRequests(List<Request> personalRequests) {
        this.personalRequests = personalRequests;
    }

    public SortedSet<Comparable> getContributions() {
        return contributions;
    }

    public void setContributions(SortedSet<Comparable> contributions) {
        this.contributions = contributions;
    }

    public SortedSet<Production> getProductionsContribution() {
        return productionsContribution;
    }

    public void setProductionsContribution(SortedSet<Production> productionsContribution) {
        this.productionsContribution = productionsContribution;
    }

    public SortedSet<Actor> getActorsContribution() {
        return actorsContribution;
    }

    public void setActorsContribution(SortedSet<Actor> actorsContribution) {
        this.actorsContribution = actorsContribution;
    }

    public void addPersonalRequest(Request request) {
        //TODO: Implement logic dupa enunt (observer)
        personalRequests.add(request);
    }

    public void removePersonalRequest(Request request) {
        //TODO: Implement logic dupa enunt, experienta utilizator (observer?)
        personalRequests.remove(request);
    }

    //TODO: Implement methods from StaffInterface
    public void addProductionSystem(Production production) {
        IMDB imdb = IMDB.getInstance();

        productionsContribution.add(production);
        contributions.add(production);

        imdb.addProduction(production);
    }

    public void addActorSystem(Actor a) {
        IMDB imdb = IMDB.getInstance();

        actorsContribution.add(a);
        contributions.add(a);

        imdb.addActor(a);
    }

    public void removeProductionSystem(String name) {
        IMDB imdb = IMDB.getInstance();
        //TODO: Posibil sa scot experienta de la user
    }

    public void removeActorSystem(String name) {

    }

    public void updateProduction(Production p) {

    }

    public void updateActor(Actor a) {

    }

    public static class StaffBuilder extends UserBuilder {
        @JsonProperty("productionsContribution")
        private SortedSet<Production> productionsContribution;
        @JsonProperty("actorsContribution")
        private SortedSet<Actor> actorsContribution;

        public StaffBuilder(@JsonProperty("username") String username,
                            @JsonProperty("experience") int experience,
                            @JsonProperty("information") Information information,
                            @JsonProperty("userType") AccountType userType) {
            super(username, experience, information, userType);
        }

        @JsonProperty("productionsContribution")
        public StaffBuilder setProductionsContribution(List<String> productionsContribution) {
            //TODO: Sa nu uit ca in load mai intai sa citesc productions si abia dupa users
            List<Production> productions = IMDB.getInstance().getProductions();
            for (String productionName : productionsContribution) {
                Production p = productions.stream()
                        .filter(production -> production.getTitle().equals(productionName))
                        .findFirst()
                        .orElse(null);
                if (p != null) {
                    this.productionsContribution.add(p);
                }
            }
            return this;
        }

        public StaffBuilder setProductionsContribution(SortedSet<Production> productionsContribution) {
            this.productionsContribution = productionsContribution;
            return this;
        }

        @JsonProperty("actorsContribution")
        public StaffBuilder setActorsContribution(List<String> actorsContribution) {
            //TODO: Sa nu uit ca in load mai intai sa citesc actors si abia dupa users
            List<Actor> actors = IMDB.getInstance().getActors();
            for (String actorName : actorsContribution) {
                Actor a = actors.stream()
                        .filter(actor -> actor.getName().equals(actorName))
                        .findFirst()
                        .orElse(null);
                if (a != null) {
                    this.actorsContribution.add(a);
                }
            }
            return this;
        }

        public StaffBuilder setActorsContribution(SortedSet<Actor> actorsContribution) {
            this.actorsContribution = actorsContribution;
            return this;
        }
    }
}
