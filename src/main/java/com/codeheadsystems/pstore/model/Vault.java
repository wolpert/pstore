package com.codeheadsystems.pstore.model;

import com.codeheadsystems.crypto.manager.SecondaryKey;

import java.util.Collection;

/**
 * Created by wolpert on 9/19/16.
 */
public class Vault {

    private SecondaryKey secondaryKey;
    private String encryptedEntries;
    private transient volatile Collection<Entry> entries;

    public Collection<Entry> getEntries() {
        return entries;
    }

    public void setEntries(Collection<Entry> entries) {
        this.entries = entries;
    }

    public SecondaryKey getSecondaryKey() {
        return secondaryKey;
    }

    public void setSecondaryKey(SecondaryKey secondaryKey) {
        this.secondaryKey = secondaryKey;
    }
}
