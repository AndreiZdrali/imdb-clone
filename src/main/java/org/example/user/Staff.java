package org.example.user;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import kotlin.NotImplementedError;
import org.example.IMDB;
import org.example.management.NotificationType;
import org.example.management.NotificationWrapper;
import org.example.management.Request;
import org.example.management.RequestStatus;
import org.example.production.*;
import org.example.serializers.ActorToStringSerializer;
import org.example.serializers.ProductionToStringSerializer;
import org.example.services.ActorService;
import org.example.services.ProductionService;
import org.example.services.RequestService;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public abstract class Staff<T> extends User<T> implements StaffInterface {
    @JsonIgnore
    protected List<Request> personalRequests;
    @JsonIgnore
    protected SortedSet<Comparable> contributions;
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
        //TODO: sa bag si alea shared
        return contributions;
    }

    public void setContributions(SortedSet<Comparable> contributions) {
        this.contributions = contributions;
    }

    public SortedSet<Production> getProductionsContribution() {
        //TODO: sa bag si alea shared
        return productionsContribution;
    }

    public void setProductionsContribution(SortedSet<Production> productionsContribution) {
        this.productionsContribution = productionsContribution;
    }

    public SortedSet<Actor> getActorsContribution() {
        //TODO: sa bag si alea shared
        return actorsContribution;
    }

    public void setActorsContribution(SortedSet<Actor> actorsContribution) {
        this.actorsContribution = actorsContribution;
    }

    //StaffInterface methods
    public void addPersonalRequest(Request request) {
        personalRequests.add(request);
    }

    public void removePersonalRequest(Request request) {
        personalRequests.remove(request);
    }

    public void addProductionSystem(Production production) {
        productionsContribution.add(production);
        contributions.add(production);

        production.addObserver(this);

        ProductionService.addProduction(production);
        //TODO: Posibil sa trb sa adaug experienta la user
    }

    public void addActorSystem(Actor actor) {
        actorsContribution.add(actor);
        contributions.add(actor);

        actor.addObserver(this);

        ActorService.addActor(actor);
        //TODO: Posibil sa trb sa adaug experienta la user
    }

    public void removeProductionSystem(Production production) {
        if (!productionsContribution.contains(production) || !contributions.contains(production))
            throw new RuntimeException("Dc incearca sa stearga o productie pe care n-a facut-o?");

        productionsContribution.remove(production);
        contributions.remove(production);

        production.removeObserver(this);

        ProductionService.removeProduction(production);
        //TODO: Posibil sa trb sa scot experienta de la user
    }

    public void removeActorSystem(Actor actor) {
        if (!actorsContribution.contains(actor) || !contributions.contains(actor))
            throw new RuntimeException("Dc incearca sa stearga un actor pe care nu l-a adaugat?");

        actorsContribution.remove(actor);
        contributions.remove(actor);

        actor.removeObserver(this);

        ActorService.removeActor(actor);
        //TODO: Posibil sa trb sa scot experienta de la user
    }

    public void updateProduction(Production p) {
        throw new NotImplementedError();
    }

    public void updateActor(Actor a) {
        throw new NotImplementedError();
    }

    public void solveRequest(Request request, RequestStatus status) {
        if (!personalRequests.contains(request) || !RequestService.getAllRequests().contains(request))
            throw new RuntimeException("Dc incearca sa marcheze o cerere care nu-i a lui?");
        if (request.getStatus() != RequestStatus.PENDING)
            throw new RuntimeException("Dc e deja rezolvata?");

        request.setStatus(status);

        NotificationWrapper notification = new NotificationWrapper(NotificationType.REQUEST_SOLVED, null, null, request);
        request.notifyObservers(notification);

        RequestService.removeRequest(request);
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
