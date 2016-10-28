package com.codeheadsystems.pstore.datastore;

import static java.util.Objects.requireNonNull;

/**
 * Created by wolpert on 9/27/16.
 */
public class DataStoreEntry {

    private final String identifier;
    private final byte[] encryptedKey;
    private final byte[] salt;
    private final byte[] content; // encrypted

    private boolean dirty = true;

    public DataStoreEntry(String identifier, byte[] encryptedKey, byte[] salt, byte[] content) {
        this.identifier = requireNonNull(identifier);
        this.encryptedKey = requireNonNull(encryptedKey);
        this.content = requireNonNull(content);
        this.salt = salt;
    }

    public String getIdentifier() {
        return identifier;
    }

    public byte[] getEncryptedKey() {
        return encryptedKey;
    }

    public byte[] getSalt() {
        return salt;
    }

    public byte[] getContent() {
        return content;
    }

    public boolean isDirty() {
        return dirty;
    }

    public void setDirty(boolean dirty) {
        this.dirty = dirty;
    }
}
