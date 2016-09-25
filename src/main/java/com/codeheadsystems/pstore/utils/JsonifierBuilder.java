package com.codeheadsystems.pstore.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * BSD-Style License 2016
 */
public class JsonifierBuilder {

    private ObjectMapper objectMapper;

    public JsonifierBuilder objectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        return this;
    }

    public <T> Jsonifier<T> build(Class<T> clazz) {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        return new Jsonifier<T>(objectMapper, clazz);
    }

}
