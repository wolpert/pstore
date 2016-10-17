package com.codeheadsystems.pstore.model;

import com.codeheadsystems.pstore.secure.StoredSecondaryKey;

import java.util.Collection;

/**
 * Created by wolpert on 9/19/16.
 */
public class Vault {

    private StoredSecondaryKey storedSecondaryKey;
    private String encryptedEntries;
    private transient volatile Collection<Entry> entries;

    public Collection<Entry> getEntries() {
        return entries;
    }

    public void setEntries(Collection<Entry> entries) {
        this.entries = entries;
    }

    public StoredSecondaryKey getSecondaryKey() {
        return storedSecondaryKey;
    }

    public void setSecondaryKey(StoredSecondaryKey storedSecondaryKey) {
        this.storedSecondaryKey = storedSecondaryKey;
    }
}
