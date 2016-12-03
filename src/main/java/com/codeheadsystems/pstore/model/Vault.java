package com.codeheadsystems.pstore.model;

import com.codeheadsystems.crypto.manager.SecondaryKey;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.HashSet;

/**
 * Created by wolpert on 9/19/16.
 */
public class Vault {

    private Collection<Entry> entries;

    @JsonIgnore
    private SecondaryKey vaultSecondaryKey;

    public Vault() {
        this(new HashSet<Entry>());
    }

    @JsonCreator
    public Vault(@JsonProperty Collection<Entry> entries) {
        this.entries = entries;
    }

    public Collection<Entry> getEntries() {
        return entries;
    }

    public SecondaryKey getVaultSecondaryKey() {
        return vaultSecondaryKey;
    }

    public void setVaultSecondaryKey(SecondaryKey vaultSecondaryKey) {
        this.vaultSecondaryKey = vaultSecondaryKey;
    }
}
