package com.codeheadsystems.pstore.secure;

import com.codeheadsystems.crypto.manager.ParanoidManager;
import com.codeheadsystems.pstore.utils.Jsonifier;
import com.codeheadsystems.pstore.utils.JsonifierBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Objects;

/**
 * BSD-Style License 2016
 */

public class ParanoidStoreBuilder {

    private ParanoidManager paranoidManager;
    private ObjectMapper objectMapper;
    private JsonifierBuilder jsonifierBuilder;

    public ParanoidStoreBuilder() {
        this.jsonifierBuilder = new JsonifierBuilder();
    }

    public ParanoidStoreBuilder paranoidManager(ParanoidManager paranoidManager) {
        this.paranoidManager = paranoidManager;
        return this;
    }

    public ParanoidStoreBuilder objectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        jsonifierBuilder.objectMapper(objectMapper);
        return this;
    }

    public <T> ParanoidStore<T> build(Class<T> clazz) {
        Objects.requireNonNull(paranoidManager);
        if (objectMapper == null) {
            objectMapper(new ObjectMapper());
        }
        Jsonifier<T> jsonifier = jsonifierBuilder.build(clazz);
        return new ParanoidStore<>(jsonifier, paranoidManager);
    }
}
