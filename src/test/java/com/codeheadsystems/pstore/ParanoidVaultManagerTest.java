package com.codeheadsystems.pstore;

import com.codeheadsystems.crypto.CryptoException;
import com.codeheadsystems.crypto.password.SecretKeyExpiredException;
import com.codeheadsystems.pstore.datastore.SecureEncryptionException;
import com.codeheadsystems.pstore.exceptions.VaultExistsException;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * BSD-Style License 2016
 */

public class ParanoidVaultManagerTest extends InjectedBaseTest {

    private static String PASSWORD = "PASSWORD";

    @Test
    public void unlockTest() throws VaultExistsException, SecretKeyExpiredException, CryptoException, SecureEncryptionException, IOException {
        ParanoidVaultManager manager = new ParanoidVaultManager(dataStore(), paranoidManager(), paranoidStoreBuilder());
        assertEquals(0, manager.listVaults().size());
        assertNotNull(manager.initVault("1", PASSWORD));
        assertEquals(1, manager.listVaults().size());
        assertNotNull(manager.unlockVault("1", PASSWORD));
        assertEquals(2, dataStore().countFilesInDataStore());
        manager.reReadVaults();
        assertEquals(1, manager.listVaults().size());
    }

    @Test
    public void createDeleteTest() throws IOException, VaultExistsException, SecretKeyExpiredException, CryptoException, SecureEncryptionException {
        assertEquals(0, dataStore().countFilesInDataStore());
        ParanoidVaultManager manager = new ParanoidVaultManager(dataStore(), paranoidManager(), paranoidStoreBuilder());
        assertEquals(0, manager.listVaults().size());
        assertEquals(1, dataStore().countFilesInDataStore()); // vault list
        assertNotNull(manager.initVault("1", PASSWORD));
        assertEquals(1, manager.listVaults().size());
        assertEquals(2, dataStore().countFilesInDataStore()); // vault list and vault
        assertTrue(manager.listVaults().contains("1"));
        manager.deleteVault("1");
        assertEquals(0, manager.listVaults().size());
        assertEquals(1, dataStore().countFilesInDataStore());
    }

}
