package com.codeheadsystems.pstore.model;

import com.codeheadsystems.pstore.datastore.StoredSecondaryKey;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * BSD-Style License 2016
 */

public class EncryptedVault {

    private StoredSecondaryKey key;
    private String encryptedEntries;

    @JsonCreator
    public EncryptedVault(@JsonProperty(value = "key") StoredSecondaryKey key,
                          @JsonProperty(value = "encryptedEntries") String encryptedEntries) {
        this.key = key;
        this.encryptedEntries = encryptedEntries;
    }

    public StoredSecondaryKey getKey() {
        return key;
    }

    public String getEncryptedEntries() {
        return encryptedEntries;
    }
}
