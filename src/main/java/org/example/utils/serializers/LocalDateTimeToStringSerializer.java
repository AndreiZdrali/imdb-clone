package org.example.utils.serializers;

import com.fasterxml.jackson.databind.JsonSerializer;

import java.time.LocalDateTime;

public class LocalDateTimeToStringSerializer extends JsonSerializer<LocalDateTime> {
    @Override
    public void serialize(LocalDateTime localDateTime, com.fasterxml.jackson.core.JsonGenerator jsonGenerator, com.fasterxml.jackson.databind.SerializerProvider serializerProvider) throws java.io.IOException {
        jsonGenerator.writeString(localDateTime.format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        //TODO: De facut asta pt mai multe formate
    }
}
