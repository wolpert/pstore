package com.codeheadsystems.pstore.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * BSD-Style License 2016
 */
public class Jsonifier<T> {

    private final ObjectMapper objectMapper;
    private final TypeReference<T> typeReference;

    public Jsonifier(ObjectMapper objectMapper, TypeReference<T> typeReference) {
        this.objectMapper = objectMapper;
        this.typeReference = typeReference;
    }

    public String toJson(T object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    public T fromJson(String json) throws IOException {
        return objectMapper.readValue(json, typeReference);
    }
}
