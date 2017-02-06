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
        assertEquals(0, pc().paranoidVaultManager().listVaults().size());
        assertNotNull(pc().paranoidVaultManager().initVault("1", PASSWORD));
        assertEquals(1, pc().paranoidVaultManager().listVaults().size());
        assertNotNull(pc().paranoidVaultManager().unlockVault("1", PASSWORD));
        assertEquals(2, dataStore().countFilesInDataStore());
        pc().paranoidVaultManager().reReadVaults();
        assertEquals(1, pc().paranoidVaultManager().listVaults().size());
    }

    @Test
    public void createDeleteTest() throws IOException, VaultExistsException, SecretKeyExpiredException, CryptoException, SecureEncryptionException {
        assertEquals(0, dataStore().countFilesInDataStore());
        ParanoidVaultManager manager = pc().paranoidVaultManager();
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
