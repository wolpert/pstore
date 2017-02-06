package com.codeheadsystems.pstore;

import com.codeheadsystems.crypto.manager.ParanoidManager;
import com.codeheadsystems.pstore.datastore.DataStore;
import com.codeheadsystems.pstore.datastore.ParanoidEncrypterBuilder;
import com.codeheadsystems.pstore.datastore.impl.EncrypterParanoidStore;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Objects;

/**
 * BSD-Style License 2016
 *
 * Provides the base functionality of this class. Can store encrypted data where we
 * only have the identifiers associated with the encrypted data.
 */
@Singleton
public class ParanoidStoreBuilder {

    private final DataStore dataStore;
    private final ParanoidManager paranoidManager;
    private final ObjectMapper objectMapper;

    @Inject
    public ParanoidStoreBuilder(DataStore dataStore, ParanoidManager paranoidManager, ObjectMapper objectMapper) {
        this.dataStore = dataStore;
        this.paranoidManager = paranoidManager;
        this.objectMapper = objectMapper;
    }

    public <T> ParanoidStore<T> build(TypeReference<T> typeReference) {
        Objects.requireNonNull(dataStore);
        ParanoidEncrypterBuilder builder = new ParanoidEncrypterBuilder();
        builder.objectMapper(objectMapper);
        builder.paranoidManager(paranoidManager);
        return new EncrypterParanoidStore<>(builder.build(typeReference), dataStore);
    }

}
