package com.tute.bitcoiner.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tute.bitcoiner.exceptions.JsonParsingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Utils {

    private final ObjectMapper objectMapper;

    @Autowired
    public Utils(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public JsonNode fromJson(String json) {
        try {
            return this.objectMapper.readTree(json);
        } catch (JsonProcessingException e) {
            throw new JsonParsingException("Failed from string to jsonNode");
        }
    }
}
