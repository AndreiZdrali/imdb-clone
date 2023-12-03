package org.example.utils.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.example.production.Actor;

public class ActorToStringSerializer extends JsonSerializer<Actor> {
    @Override
    public void serialize(Actor actor, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws java.io.IOException {
        jsonGenerator.writeString(actor.getName());
    }
}
