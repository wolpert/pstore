package com.codeheadsystems.pstore;

import com.codeheadsystems.crypto.manager.SecondaryKey;
import com.codeheadsystems.crypto.password.SecretKeyExpiredException;
import com.codeheadsystems.pstore.datastore.SecureEncryptionException;

import java.io.IOException;

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
     * @throws SecureEncryptionException if the encryption failed
     * @throws SecretKeyExpiredException if the key had expired
     * @throws IOException               If we could not store the data
     */
    String put(SecondaryKey secondaryKey, T data) throws SecretKeyExpiredException, SecureEncryptionException, IOException;

    /**
     * Retrieves the data from the store decrypted with the secondary key given
     *
     * @param secondaryKey The key used to encrypt the data
     * @param identifier   The identifier that was used originally to store the data
     * @return The data that was originally stored
     * @throws SecureEncryptionException if the decryption failed
     * @throws SecretKeyExpiredException if the key had expired
     * @throws IOException               if we could not retrieve the data
     */
    T get(SecondaryKey secondaryKey, String identifier) throws IOException, SecretKeyExpiredException, SecureEncryptionException;

    /**
     * Removes the data from the store with ths identifier
     *
     * @param identifier that is to be deleted if it exists. Does nothing if it does not exist.
     */
    void delete(String identifier);
}
