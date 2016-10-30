package com.codeheadsystems.pstore.datastore.impl;

import com.codeheadsystems.crypto.Utilities;
import com.codeheadsystems.pstore.datastore.DataStore;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Random;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;

/**
 * BSD-Style License 2016
 */

public class TmpFileStorageTest {

    private DataStore dataStore;

    @Before
    public void setUpStorage() throws IOException {
        dataStore = new TmpFileStorage();
    }

    @Test
    public void testNotFound() throws IOException {
        assertNull(dataStore.get("FDSLFSDJF"));
    }

    @Test
    public void testDeleteDoesNotBlowUp() {
        dataStore.delete("SDFDSS");
    }

    @Test
    public void defaultTest() throws IOException {
        byte[] initialArray = new byte[1025];
        new Random().nextBytes(initialArray);
        String id = dataStore.put(initialArray);
        assertNotNull(id);
        byte[] readArray = dataStore.get(id);
        assertNotNull(readArray);
        assertTrue(Utilities.isSame(initialArray, readArray));
        dataStore.delete(id);
        assertNull(dataStore.get(id));
    }

    @Test
    public void testDeletionWorks() throws IOException {
        TmpFileStorage tfs = (TmpFileStorage) dataStore;
        byte[] initialArray = new byte[1025];
        new Random().nextBytes(initialArray);
        String id = tfs.put(initialArray);
        assertTrue(tfs.getFileForIdentifier(id).exists());
        tfs.delete(id);
        assertFalse(tfs.getFileForIdentifier(id).exists());
    }

}
