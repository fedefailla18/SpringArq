package com.helper.work.domain.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ZipCodeListDeserializer extends JsonDeserializer<List<String>> {

    @Override
    public List<String> deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException {
        List<String> zipCodes = new ArrayList<>();

        JsonToken t = p.getCurrentToken();
        if (t == JsonToken.VALUE_STRING) {
            while (p.nextToken() != JsonToken.END_ARRAY) {
                String zipCode = p.getValueAsString();
                zipCodes.add(zipCode);
            }
        }

        return zipCodes;
    }
}
