package org.example.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class LocalDateToStringSerializer extends JsonSerializer<LocalDate> {
        @Override
        public void serialize(LocalDate localDate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws java.io.IOException {
            jsonGenerator.writeString(localDate.format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }
}
