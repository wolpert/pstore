package com.codeheadsystems.pstore.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * BSD-Style License 2016
 */
public class Jsonifier<T> {

    private final ObjectMapper objectMapper;
    private final Class<T> clazz;

    public Jsonifier(ObjectMapper objectMapper, Class<T> clazz) {
        this.objectMapper = objectMapper;
        this.clazz = clazz;
    }

    public String toJson(T object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    public T fromJson(String json) throws IOException {
        return objectMapper.readValue(json, clazz);
    }
}
