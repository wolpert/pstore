package com.codeheadsystems.pstore;

import com.codeheadsystems.crypto.manager.ParanoidManager;
import com.codeheadsystems.crypto.manager.SecondaryKey;
import com.codeheadsystems.crypto.password.SecretKeyExpiredException;
import com.codeheadsystems.pstore.datastore.DataStore;
import com.codeheadsystems.pstore.datastore.SecureEncryptionException;
import com.codeheadsystems.pstore.model.Entry;
import com.codeheadsystems.pstore.model.EntryDetails;

import java.io.IOException;
import java.util.List;

/**
 * BSD-Style License 2016
 */

public class ParanoidVaultManager implements VaultManager {

    private final ParanoidStore<EntryDetails> entryDetailsParanoidStore;

    public ParanoidVaultManager(final DataStore dataStore, final SecondaryKey secondaryKey) {
        this(dataStore, new ParanoidManager());
    }

    public ParanoidVaultManager(final DataStore dataStore, final ParanoidManager paranoidManager) {
        ParanoidStoreBuilder paranoidStoreBuilder = new ParanoidStoreBuilder()
                .dataStore(dataStore)
                .paranoidManager(paranoidManager);
        this.entryDetailsParanoidStore = paranoidStoreBuilder.build(EntryDetails.class);
    }

    @Override
    public List<Entry> entries(SecondaryKey secondaryKey) {
        return null;
    }

    @Override
    public List<Entry> entries(SecondaryKey secondaryKey, String searchString) {
        return null;
    }

    @Override
    public EntryDetails entryDetails(SecondaryKey secondaryKey, String identifier) throws SecretKeyExpiredException, IOException, SecureEncryptionException {
        return entryDetailsParanoidStore.get(secondaryKey, identifier);
    }

    @Override
    public String store(SecondaryKey secondaryKey, EntryDetails entryDetails) throws SecretKeyExpiredException, IOException, SecureEncryptionException {
        return entryDetailsParanoidStore.put(secondaryKey, entryDetails);
    }

    @Override
    public void delete(String identifier) {

    }
}
