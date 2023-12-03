package org.example.utils.serializers;

import com.fasterxml.jackson.databind.JsonSerializer;
import org.example.production.Production;

public class ProductionToStringSerializer extends JsonSerializer<Production> {
    @Override
    public void serialize(Production production, com.fasterxml.jackson.core.JsonGenerator jsonGenerator, com.fasterxml.jackson.databind.SerializerProvider serializerProvider) throws java.io.IOException {
        jsonGenerator.writeString(production.getTitle());
    }
}
