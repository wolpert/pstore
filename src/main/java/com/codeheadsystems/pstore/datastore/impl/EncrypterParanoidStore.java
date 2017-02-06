package com.codeheadsystems.pstore.datastore.impl;

import com.codeheadsystems.crypto.CryptoException;
import com.codeheadsystems.crypto.manager.SecondaryKey;
import com.codeheadsystems.crypto.password.SecretKeyExpiredException;
import com.codeheadsystems.pstore.ParanoidStore;
import com.codeheadsystems.pstore.datastore.DataStore;
import com.codeheadsystems.pstore.datastore.SecureEncrypter;
import com.codeheadsystems.pstore.datastore.SecureEncryptionException;

import java.io.IOException;

/**
 * BSD-Style License 2016
 */

public class EncrypterParanoidStore<T> implements ParanoidStore<T> {

    private final SecureEncrypter<T> secureEncrypter;
    private final DataStore dataStore;

    public EncrypterParanoidStore(SecureEncrypter<T> secureEncrypter, DataStore dataStore) {
        this.secureEncrypter = secureEncrypter;
        this.dataStore = dataStore;
    }

    @Override
    public String put(SecondaryKey secondaryKey, T data) throws SecretKeyExpiredException, SecureEncryptionException, IOException {
        byte[] dataToUpload = secureEncrypter.convertToString(secondaryKey, data);
        return dataStore.put(dataToUpload);
    }

    @Override
    public String put(SecondaryKey secondaryKey, T data, String identifier) throws SecretKeyExpiredException, SecureEncryptionException, IOException {
        byte[] dataToUpload = secureEncrypter.convertToString(secondaryKey, data);
        return dataStore.put(dataToUpload, identifier);
    }

    @Override
    public T get(SecondaryKey secondaryKey, String identifier) throws IOException, SecretKeyExpiredException, SecureEncryptionException {
        byte[] dataFromStore = dataStore.get(identifier);
        if (dataFromStore == null) {
            return null;
        } else {
            return secureEncrypter.convertFromString(secondaryKey, dataFromStore);
        }
    }

    @Override
    public void delete(String identifier) {
        dataStore.delete(identifier);
    }

    @Override
    public SecondaryKey generateFreshSecondaryKey(SecondaryKey secondaryKey) throws CryptoException, SecretKeyExpiredException {
        return secureEncrypter.freshSecondayKey(secondaryKey);
    }
}
