package com.codeheadsystems.pstore;

import com.codeheadsystems.crypto.manager.SecondaryKey;

/**
 * Provides the base of the datastore facility. Objects are encoded to strings, compressed and encrypted
 * before storage. This interface does not define what the storage mechanism is, rather juts outlines
 * the access patterns to the stored content.
 * 
 * BSD-Style License 2016
 */
public interface ParanoidStore<T> {

    /**
     * Stores the object encrypted in the data store. Returns the identifier for the object
     *
     * @param secondaryKey The key used to encrypt the data
     * @param data         The data that is to be stored
     * @return A string identifier that can be used to get the data back
     */
    String put(SecondaryKey secondaryKey, T data);

    /**
     * Retrieves the data from the store decrypted with the secondary key given
     *
     * @param secondaryKey The key used to encrypt the data
     * @param identifier   The identifier that was used originally to store the data
     * @return The data that was originally stored
     */
    T get(SecondaryKey secondaryKey, String identifier);

    /**
     * Removes the data from the store with ths identifier
     *
     * @param identifier that is to be deleted if it exists. Does nothing if it does not exist.
     */
    void delete(String identifier);
}
