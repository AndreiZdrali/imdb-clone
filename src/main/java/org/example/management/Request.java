package org.example.management;

import org.example.production.Movie;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Request {
    private RequestType type;
    private LocalDateTime creationDate;
    private String subject; //numele productiei sau al actorului
    private String description;
    private String creatorUsername;
    private String handlerUsername;

    public Request(RequestType type, String subject, String description, String creatorUsername) {
        this.type = type;
        this.creationDate = LocalDateTime.now();
        this.subject = subject;
        this.description = description;
        this.creatorUsername = creatorUsername;

        if (type == RequestType.DELETE_ACCOUNT || type == RequestType.OTHERS) {
            //TODO: In lista tuturor adminilor
        } else if  (type == RequestType.ACTOR_ISSUE) {
            //TODO: Gaseste user-ul care l-a introdus
        } else {
            //TODO: Gaseste user-ul care l-a introdus
        }

        //Mai usor daca fac o interfata comuna pentru Production si Actor
    }

    //TODO: Implement getters and setters

    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //TODO: Implement toString
        return "";
    }
}
