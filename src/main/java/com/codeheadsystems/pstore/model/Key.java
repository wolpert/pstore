package com.codeheadsystems.pstore.model;

import com.codeheadsystems.crypto.manager.ParanoidManager;
import com.codeheadsystems.crypto.manager.SecondaryKey;
import com.codeheadsystems.pstore.datastore.StoredSecondaryKey;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * BSD-Style License 2016
 */
public class Key {

    private final StoredSecondaryKey storedSecondaryKey;
    private final String vaultId;

    @JsonCreator
    public Key(@JsonProperty StoredSecondaryKey storedSecondaryKey,
               @JsonProperty String vaultId) {
        this.storedSecondaryKey = storedSecondaryKey;
        this.vaultId = vaultId;
    }

    public StoredSecondaryKey getStoredSecondaryKey() {
        return storedSecondaryKey;
    }

    public String getVaultId() {
        return vaultId;
    }
}
