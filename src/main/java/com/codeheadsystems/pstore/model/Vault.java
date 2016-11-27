package com.codeheadsystems.pstore.model;

import java.util.Collection;

/**
 * Created by wolpert on 9/19/16.
 */
public class Vault {

    private transient volatile Collection<Entry> entries;

    public Collection<Entry> getEntries() {
        return entries;
    }

    public void setEntries(Collection<Entry> entries) {
        this.entries = entries;
    }

}
