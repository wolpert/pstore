package com.codeheadsystems.pstore;

import com.codeheadsystems.crypto.CryptoException;
import com.codeheadsystems.crypto.manager.ParanoidManager;
import com.codeheadsystems.crypto.password.SecretKeyExpiredException;
import com.codeheadsystems.pstore.datastore.DataStore;
import com.codeheadsystems.pstore.datastore.SecureEncryptionException;
import com.codeheadsystems.pstore.datastore.impl.TmpFileStorage;
import com.codeheadsystems.pstore.exceptions.VaultExistsException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * BSD-Style License 2016
 */

public class ParanoidVaultManagerTest {

    private static String PASSWORD = "PASSWORD";
    private TmpFileStorage dataStore;
    private ParanoidManager paranoidManager;

    @Before
    public void setup() throws IOException {
        Path storeDir = Files.createTempDirectory("pstoretest-");
        dataStore = new TmpFileStorage(storeDir.toFile());
        paranoidManager = new ParanoidManager(14);
    }

    @After
    public void teardown() {
        dataStore.erase();
    }

    @Test
    public void createDeleteTest() throws IOException, VaultExistsException, SecretKeyExpiredException, CryptoException, SecureEncryptionException {
        assertEquals(0, dataStore.countFilesInDataStore());
        ParanoidVaultManager manager = new ParanoidVaultManager(dataStore, paranoidManager);
        assertEquals(0, manager.listVaults().size());
        assertEquals(1, dataStore.countFilesInDataStore()); // vault list
        assertNotNull(manager.initVault("1", PASSWORD));
        assertEquals(1, manager.listVaults().size());
        assertEquals(2, dataStore.countFilesInDataStore()); // vault list and vault
        assertTrue(manager.listVaults().contains("1"));
        manager.deleteVault("1");
        assertEquals(0, manager.listVaults().size());
        assertEquals(1, dataStore.countFilesInDataStore());
    }

}
