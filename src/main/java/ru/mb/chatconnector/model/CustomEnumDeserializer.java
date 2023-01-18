package ru.mb.chatconnector.model;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class CustomEnumDeserializer extends StdDeserializer<EventType> {

    public CustomEnumDeserializer() {
        super(String.class);
    }

    @Override
    public EventType deserialize(JsonParser p, DeserializationContext ctx) throws IllegalArgumentException, IOException {
        JsonNode node = p.getCodec().readTree(p);
        String value = node.textValue();
        try {
            return EventType.valueOf(value);
        } catch (IllegalArgumentException ignored) {
            throw new IllegalArgumentException("Argument 'type' in not valid");
        }
    }
}