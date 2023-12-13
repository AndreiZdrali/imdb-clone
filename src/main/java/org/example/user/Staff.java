package org.example.user;

import java.util.ArrayList;
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
import org.example.serializers.ActorToStringSerializer;
import org.example.serializers.ProductionToStringSerializer;
import org.example.services.ActorService;
import org.example.services.ProductionService;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public abstract class Staff extends User implements StaffInterface {
    @JsonIgnore
    protected List<Request> personalRequests;
    @JsonIgnore
    public SortedSet<Comparable> contributions;
    @JsonProperty("productionsContribution")
    @JsonSerialize(using = ProductionToStringSerializer.class, as = String.class)
    protected SortedSet<Production> productionsContribution;
    @JsonProperty("actorsContribution")
    @JsonSerialize(using = ActorToStringSerializer.class, as = String.class)
    protected SortedSet<Actor> actorsContribution;

    public Staff(StaffBuilder builder) {
        super(builder);
        this.productionsContribution = builder.productionsContribution;
        this.actorsContribution = builder.actorsContribution;

        this.contributions = new TreeSet<>();
        this.contributions.addAll(this.productionsContribution);
        this.contributions.addAll(this.actorsContribution);

        this.personalRequests = new ArrayList<>();
    }

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

    //StaffInterface methods
    public void addPersonalRequest(Request request) {
        //TODO: Implement logic dupa enunt (observer)
        //TODO: Posibil sa trb defapt sa o fac in RequestService, DAR NU CRED
        personalRequests.add(request);
    }

    public void removePersonalRequest(Request request) {
        //TODO: Implement logic dupa enunt, experienta utilizator (observer?)
        //TODO: Posibil sa trb defapt sa o fac in RequestService, DAR NU CRED
        personalRequests.remove(request);
    }

    //TODO: Implement methods from StaffInterface
    public void addProductionSystem(Production production) {
        productionsContribution.add(production);
        contributions.add(production);

        ProductionService.addProduction(production);
    }

    public void addActorSystem(Actor a) {
        actorsContribution.add(a);
        contributions.add(a);

        ActorService.addActor(a);
    }

    public void removeProductionSystem(String name) {
        //TODO: Posibil sa trb sa scot experienta de la user
    }

    public void removeActorSystem(String name) {
        //TODO: Implement removeActorSystem
    }

    public void updateProduction(Production p) {

    }

    public void updateActor(Actor a) {

    }

    public static class StaffBuilder extends UserBuilder {
        private SortedSet<Production> productionsContribution;
        private SortedSet<Actor> actorsContribution;

        public StaffBuilder(@JsonProperty("username") String username,
                            @JsonProperty("experience") int experience,
                            @JsonProperty("information") Information information,
                            @JsonProperty("userType") AccountType userType) {
            super(username, experience, information, userType);

            this.productionsContribution = new TreeSet<>();
            this.actorsContribution = new TreeSet<>();
        }

        /** Se apeleaza doar la load */
        @JsonProperty("productionsContribution")
        public StaffBuilder setProductionsContribution(List<String> productionsContribution) {
            for (String productionName : productionsContribution) {
                Production p = ProductionService.getProductionByTitle(productionName);
                if (p != null)
                    this.productionsContribution.add(p);
            }
            return this;
        }

        public StaffBuilder setProductionsContribution(SortedSet<Production> productionsContribution) {
            this.productionsContribution = productionsContribution;
            return this;
        }

        /** Se apeleaza doar la load */
        @JsonProperty("actorsContribution")
        public StaffBuilder setActorsContribution(List<String> actorsContribution) {
            for (String actorName : actorsContribution) {
                Actor a = ActorService.getActorByName(actorName);
                if (a != null)
                    this.actorsContribution.add(a);
            }
            return this;
        }

        public StaffBuilder setActorsContribution(SortedSet<Actor> actorsContribution) {
            this.actorsContribution = actorsContribution;
            return this;
        }
    }
}
