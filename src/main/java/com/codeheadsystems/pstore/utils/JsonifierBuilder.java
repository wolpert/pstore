package com.codeheadsystems.pstore.utils;

import com.codeheadsystems.crypto.manager.SecuredParanoidManager;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Objects;

/**
 * BSD-Style License 2016
 */
public class JsonifierBuilder {

    private ObjectMapper objectMapper;
    private SecuredParanoidManager paranoidManager;

    JsonifierBuilder objectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        return this;
    }

    JsonifierBuilder manager(SecuredParanoidManager paranoidManager) {
        this.paranoidManager = paranoidManager;
        return this;
    }

    <T> Jsonifier<T> build(Class<T> clazz) {
        Objects.requireNonNull(paranoidManager);
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        return new Jsonifier<T>(objectMapper, paranoidManager, clazz);
    }

}
