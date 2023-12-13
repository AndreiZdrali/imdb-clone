package org.example.serializers;

import com.fasterxml.jackson.databind.JsonDeserializer;

import java.time.LocalDateTime;

//TODO: Momentan nu il folosesc, poate in RequestBuilder.createdDate si UserBuilder.birthDate
public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
    @Override
    public LocalDateTime deserialize(com.fasterxml.jackson.core.JsonParser jsonParser, com.fasterxml.jackson.databind.DeserializationContext deserializationContext) throws java.io.IOException, com.fasterxml.jackson.core.JsonProcessingException {
        return LocalDateTime.parse(jsonParser.getText(), java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }
}
