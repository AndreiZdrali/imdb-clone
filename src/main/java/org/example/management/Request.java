package org.example.management;

import java.time.LocalDateTime;

public class Request {
    private RequestType type;
    private LocalDateTime creationDate;
    private String subject; //numele productiei sau al actorului
    private String description;
    private String creatorUsername;
    private String handlerUsername;

    public Request() {
        //TODO: Implement constructor
    }
}
