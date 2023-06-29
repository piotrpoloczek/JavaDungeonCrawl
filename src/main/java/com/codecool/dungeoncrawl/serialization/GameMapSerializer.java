package com.codecool.dungeoncrawl.serialization;

import com.codecool.dungeoncrawl.logic.map.GameMap;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.util.Arrays;

public class GameMapSerializer extends StdSerializer<GameMap> {

    public GameMapSerializer(Class<GameMap> t) {
        super(t);
    }

    @Override
    public void serialize(GameMap gameMap, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("map", Arrays.toString(gameMap.getCells()));
        jsonGenerator.writeEndObject();
    }
}
