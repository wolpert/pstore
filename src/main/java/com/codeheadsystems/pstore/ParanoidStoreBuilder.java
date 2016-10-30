package com.codeheadsystems.pstore;

import com.codeheadsystems.crypto.manager.ParanoidManager;
import com.codeheadsystems.pstore.datastore.DataStore;
import com.codeheadsystems.pstore.datastore.ParanoidEncrypterBuilder;
import com.codeheadsystems.pstore.datastore.impl.EncrypterParanoidStore;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Objects;

/**
 * BSD-Style License 2016
 *
 * Provides the base functionality of this class. Can store encrypted data where we
 * only have the identifiers associated with the encrypted data.
 */
public class ParanoidStoreBuilder {

    private DataStore dataStore;
    private ParanoidManager paranoidManager;
    private ObjectMapper objectMapper;

    public ParanoidStoreBuilder dataStore(DataStore dataStore) {
        this.dataStore = dataStore;
        return this;
    }

    public ParanoidStoreBuilder paranoidManager(ParanoidManager paranoidManager) {
        this.paranoidManager = paranoidManager;
        return this;
    }

    public ParanoidStoreBuilder objectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        return this;
    }

    public <T> ParanoidStore<T> build(Class<T> clazz) {
        Objects.requireNonNull(dataStore);
        if (paranoidManager == null) {
            paranoidManager(new ParanoidManager());
        }
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        ParanoidEncrypterBuilder builder = new ParanoidEncrypterBuilder();
        builder.objectMapper(objectMapper);
        builder.paranoidManager(paranoidManager);
        return new EncrypterParanoidStore<T>(builder.build(clazz), dataStore);
    }

}
