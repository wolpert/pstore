package com.codeheadsystems.pstore.datastore;

import java.io.IOException;

/**
 * Generic data store that can be used to store byte arrays.
 * <p>
 * BSD-Style License 2016
 */
public interface DataStore {

    /**
     * Stores the dataset and returns an identifier usable to retrieve it later.
     *
     * @param data to be stored in the datastore
     * @return an identifier representing the data in the datastore.
     * @throws IOException if we could not store the data into the datastore
     */
    String put(byte[] data) throws IOException;

    /**
     * Stores the dataset and returns an identifier usable to retrieve it later.
     *
     * @param data to be stored in the datastore
     * @param identifier that will be used to update the datastore
     * @return an identifier representing the data in the datastore.
     * @throws IOException if we could not store the data into the datastore
     */
    String put(byte[] data, String identifier) throws IOException;

    /**
     * Given the identifier, returns the data set the identifier pointed to.
     * Can be null if its not available.
     *
     * @param identifier representing the data in the datastore.
     * @return a null if the entry does not exist in the datastore, or the data itself if it does exist.
     * @throws IOException if we could not read the data from the datastore
     */
    byte[] get(String identifier) throws IOException;

    /**
     * Removes the data for this identifier from the system.
     *
     * @param identifier representing the data in the datastore.
     */
    void delete(String identifier);

}
