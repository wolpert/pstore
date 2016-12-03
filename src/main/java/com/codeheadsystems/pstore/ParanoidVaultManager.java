package com.codeheadsystems.pstore;

import com.codeheadsystems.crypto.CryptoException;
import com.codeheadsystems.crypto.manager.ParanoidManager;
import com.codeheadsystems.crypto.manager.SecondaryKey;
import com.codeheadsystems.crypto.password.SecretKeyExpiredException;
import com.codeheadsystems.pstore.datastore.DataStore;
import com.codeheadsystems.pstore.datastore.SecureEncryptionException;
import com.codeheadsystems.pstore.datastore.StoredSecondaryKey;
import com.codeheadsystems.pstore.exceptions.VaultExistsException;
import com.codeheadsystems.pstore.model.Key;
import com.codeheadsystems.pstore.model.Vault;
import com.codeheadsystems.pstore.model.VaultManagerDetails;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.NoSuchElementException;

/**
 * BSD-Style License 2016
 */

public class ParanoidVaultManager implements VaultManager {

    private static final String VAULT_LIST = "vaults";

    private final DataStore dataStore;
    private final ParanoidManager paranoidManager;
    private final ParanoidStore<Vault> vaultParanoidStore;
    private final ObjectMapper objectMapper;
    private VaultManagerDetails vaultManagerDetails;

    public ParanoidVaultManager(final DataStore dataStore) throws IOException {
        this(dataStore, new ParanoidManager());
    }

    public ParanoidVaultManager(final DataStore dataStore, final ParanoidManager paranoidManager) throws IOException {
        ParanoidStoreBuilder paranoidStoreBuilder = new ParanoidStoreBuilder()
                .dataStore(dataStore)
                .paranoidManager(paranoidManager);
        this.dataStore = dataStore;
        this.paranoidManager = paranoidManager;
        this.vaultParanoidStore = paranoidStoreBuilder.build(Vault.class);
        this.objectMapper = new ObjectMapper();
        initVaultManagerDetails(dataStore);
    }

    private void initVaultManagerDetails(DataStore dataStore) throws IOException {
        byte[] listBytes = dataStore.get(VAULT_LIST);
        if (listBytes != null) {
            vaultManagerDetails = objectMapper.readValue(listBytes, VaultManagerDetails.class);
        } else {
            vaultManagerDetails = new VaultManagerDetails(new HashMap<String, Key>());
            updateVaultManagerDetails(dataStore);
        }
    }

    private void updateVaultManagerDetails(DataStore dataStore) throws IOException {
        byte[] listBytes = objectMapper.writeValueAsBytes(vaultManagerDetails);
        dataStore.put(listBytes, VAULT_LIST);
    }

    @Override
    public Vault initVault(String id, String password) throws VaultExistsException, CryptoException, SecretKeyExpiredException, IOException, SecureEncryptionException {
        if (vaultManagerDetails.getIds().contains(id)) {
            throw new VaultExistsException();
        }
        Vault vault = new Vault();
        SecondaryKey secondaryKey = paranoidManager.generateFreshSecondary(password);
        vault.setVaultSecondaryKey(secondaryKey);
        String vaultId = vaultParanoidStore.put(secondaryKey, vault);
        try {
            Key key = new Key(new StoredSecondaryKey(secondaryKey), vaultId, new HashSet<String>());
            vaultManagerDetails.addId(id, key);
            updateVaultManagerDetails(dataStore);
        } catch (IOException e) { // we stored the vault... if we can't store any other data we should remove it to not clutter up the datastore
            vaultParanoidStore.delete(vaultId);
            throw (e);
        }
        return vault;
    }

    @Override
    public Vault unlockVault(String id, String password) throws VaultExistsException, CryptoException, SecretKeyExpiredException, IOException, SecureEncryptionException {
        Key key = vaultManagerDetails.getKey(id);
        if (key == null) {
            throw new VaultExistsException();
        }
        SecondaryKey secondaryKey = key.getStoredSecondaryKey().regenerate(paranoidManager, password);
        Vault vault = vaultParanoidStore.get(secondaryKey, key.getVaultId());
        vault.setVaultSecondaryKey(secondaryKey);
        return vault;
    }

    @Override
    public void deleteVault(String id) throws IOException, NoSuchElementException {
        Key key = vaultManagerDetails.getKey(id);
        if (key == null) {
            throw new NoSuchElementException("Key not found: " + id);
        }
        key.deleteVault(dataStore);
        vaultParanoidStore.delete(id);
        vaultManagerDetails.delete(id);
        updateVaultManagerDetails(dataStore);
    }

    @Override
    public Collection<String> listVaults() {
        return vaultManagerDetails.getIds();
    }
}
