package org.example.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.time.LocalDateTime;

public class LocalDateTimeToStringSerializer extends JsonSerializer<LocalDateTime> {
        @Override
        public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws java.io.IOException {
            jsonGenerator.writeString(localDateTime.format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }
}
