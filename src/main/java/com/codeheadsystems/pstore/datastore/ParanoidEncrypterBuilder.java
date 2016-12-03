package com.codeheadsystems.pstore.datastore;

import com.codeheadsystems.crypto.manager.ParanoidManager;
import com.codeheadsystems.pstore.utils.Jsonifier;
import com.codeheadsystems.pstore.utils.JsonifierBuilder;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Objects;

/**
 * BSD-Style License 2016
 */

public class ParanoidEncrypterBuilder {

    private ParanoidManager paranoidManager;
    private ObjectMapper objectMapper;
    private JsonifierBuilder jsonifierBuilder;

    public ParanoidEncrypterBuilder() {
        this.jsonifierBuilder = new JsonifierBuilder();
    }

    public ParanoidEncrypterBuilder paranoidManager(ParanoidManager paranoidManager) {
        this.paranoidManager = paranoidManager;
        return this;
    }

    public ParanoidEncrypterBuilder objectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        jsonifierBuilder.objectMapper(objectMapper);
        return this;
    }

    public <T> ParanoidEncrypter<T> build(TypeReference<T> typeReference) {
        Objects.requireNonNull(paranoidManager);
        if (objectMapper == null) {
            objectMapper(new ObjectMapper());
        }
        Jsonifier<T> jsonifier = jsonifierBuilder.build(typeReference);
        return new ParanoidEncrypter<T>(jsonifier, paranoidManager);
    }
}
