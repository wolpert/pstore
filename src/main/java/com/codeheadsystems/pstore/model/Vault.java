package com.codeheadsystems.pstore.model;

import com.codeheadsystems.crypto.CryptoException;
import com.codeheadsystems.crypto.manager.SecondaryKey;
import com.codeheadsystems.crypto.password.SecretKeyExpiredException;
import com.codeheadsystems.pstore.ParanoidStore;
import com.codeheadsystems.pstore.datastore.SecureEncryptionException;
import com.codeheadsystems.pstore.datastore.StoredSecondaryKey;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;

/**
 * Created by wolpert on 9/19/16.
 */
public class Vault {

    private Collection<Entry> entries;

    @JsonIgnore
    private SecondaryKey vaultSecondaryKey;

    @JsonIgnore
    private ParanoidStore<Vault> vaultParanoidStore;

    @JsonIgnore
    private ParanoidStore<EntryDetails> entryDetailsParanoidStore;

    public Vault() {
        this(new HashSet<Entry>());
    }

    @JsonCreator
    public Vault(@JsonProperty Collection<Entry> entries) {
        this.entries = entries;
    }

    public Collection<Entry> getEntries() {
        return entries;
    }

    public SecondaryKey getVaultSecondaryKey() {
        return vaultSecondaryKey;
    }

    public void setVaultSecondaryKey(SecondaryKey vaultSecondaryKey) {
        this.vaultSecondaryKey = vaultSecondaryKey;
    }

    public void addEntry(CompleteEntry completeEntry) throws SecretKeyExpiredException, CryptoException, SecureEncryptionException, IOException {
        this.addEntry(completeEntry.getEntry(), completeEntry.getEntryDetails());
    }

    public void updateEntry(CompleteEntry completeEntry) throws SecretKeyExpiredException, CryptoException, SecureEncryptionException, IOException {
        this.updateEntry(completeEntry.getEntry(), completeEntry.getEntryDetails());
    }


    public void addEntry(Entry entry, EntryDetails entryDetails) throws SecretKeyExpiredException, IOException, SecureEncryptionException, CryptoException {
        SecondaryKey entryDetailsSecondaryKey = entryDetailsParanoidStore.generateFreshSecondaryKey(vaultSecondaryKey);
        String storedId = entryDetailsParanoidStore.put(entryDetailsSecondaryKey, entryDetails);
        entry.setStoredSecondaryKey(new StoredSecondaryKey(entryDetailsSecondaryKey));
        entry.setEntryDetailIdentifier(storedId);
        entries.add(entry);
        storeVault();
    }

    public void updateEntry(Entry entry, EntryDetails entryDetails) throws CryptoException, SecretKeyExpiredException, IOException, SecureEncryptionException {
        SecondaryKey entryDetailsSecondaryKey = entryDetailsParanoidStore.generateFreshSecondaryKey(vaultSecondaryKey);
        String oldStoreId = entry.getEntryDetailIdentifier();
        String storedId = entryDetailsParanoidStore.put(entryDetailsSecondaryKey, entryDetails);
        entry.setStoredSecondaryKey(new StoredSecondaryKey(entryDetailsSecondaryKey));
        entry.setEntryDetailIdentifier(storedId);
        if(oldStoreId!=null) {
            entryDetailsParanoidStore.delete(oldStoreId);
        }
        entries.remove(entry);
        entries.add(entry);
        storeVault();
    }

    public void storeVault() throws SecretKeyExpiredException, IOException, SecureEncryptionException {
        vaultParanoidStore.put(vaultSecondaryKey, this);
    }

    public void lock() {
        vaultSecondaryKey.getKeyParameterWrapper().expire();
        vaultSecondaryKey = null;
        vaultParanoidStore = null;
        entryDetailsParanoidStore = null;
    }

    public void setVaultParanoidStore(ParanoidStore<Vault> vaultParanoidStore) {
        this.vaultParanoidStore = vaultParanoidStore;
    }

    public void setEntryDetailsParanoidStore(ParanoidStore<EntryDetails> entryDetailsParanoidStore) {
        this.entryDetailsParanoidStore = entryDetailsParanoidStore;
    }
}
