package org.example.user;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.example.management.Request;
import org.example.production.*;
import org.example.utils.serializers.ActorToStringSerializer;
import org.example.utils.serializers.ProductionToStringSerializer;

public class Staff extends User implements StaffInterface {
    @JsonIgnore
    private List<Request> personalRequests;
    @JsonProperty("productionsContribution")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonSerialize(using = ProductionToStringSerializer.class, as = String.class)
    private SortedSet<Production> productionsContribution;
    @JsonProperty("actorsContribution")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonSerialize(using = ActorToStringSerializer.class, as = String.class)
    private SortedSet<Actor> actorsContribution;

    public Staff(StaffBuilder builder) {
        super(builder);
        this.productionsContribution = builder.productionsContribution;
        this.actorsContribution = builder.actorsContribution;
        //TODO: Sa vad ce fac cu personalRequests
    }

    //TODO: Implement methods from StaffInterface
    public void addProductionSystem(Production production) {

    }

    public void addActorSystem(Actor a) {

    }

    public void removeProductionSystem(String name) {

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
            //TODO: pentru fiecare string sa gasesc productia aia
            return this;
        }

        public StaffBuilder setProductionsContribution(SortedSet<Production> productionsContribution) {
            this.productionsContribution = productionsContribution;
            return this;
        }

        @JsonProperty("productionsContribution")
        public StaffBuilder setActorsContribution(List<String> actorsContribution) {
            //TODO: pentru fiecare string sa gasesc actorul ala
            return this;
        }

        public StaffBuilder setActorsContribution(SortedSet<Actor> actorsContribution) {
            this.actorsContribution = actorsContribution;
            return this;
        }
    }
}
