package com.codeheadsystems.pstore.model;

import com.codeheadsystems.pstore.datastore.DataStore;
import com.codeheadsystems.pstore.datastore.StoredSecondaryKey;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.HashSet;

/**
 * BSD-Style License 2016
 */
public class Key {

    private final StoredSecondaryKey storedSecondaryKey;
    private final String vaultId;
    private Collection<String> entryDetails = new HashSet<>();

    @JsonCreator
    public Key(@JsonProperty StoredSecondaryKey storedSecondaryKey,
               @JsonProperty String vaultId,
               @JsonProperty Collection<String> entryDetails) {
        this.storedSecondaryKey = storedSecondaryKey;
        this.vaultId = vaultId;
        this.entryDetails = entryDetails;
    }

    public StoredSecondaryKey getStoredSecondaryKey() {
        return storedSecondaryKey;
    }

    public String getVaultId() {
        return vaultId;
    }

    public Collection<String> getEntryDetails() {
        return entryDetails;
    }

    public void addEntryDetail(Entry entry) {
        entryDetails.add(entry.getEntryDetailIdentifier());
    }

    public void removeEntryDetail(Entry entry) {
        entryDetails.remove(entry.getEntryDetailIdentifier());
    }

    public void deleteVault(DataStore dataStore) {
        for(String id : entryDetails) {
            dataStore.delete(id);
        }
        dataStore.delete(vaultId);
    }
}
