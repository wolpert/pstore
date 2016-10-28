package com.codeheadsystems.pstore.datastore;

import com.codeheadsystems.crypto.CryptoException;
import com.codeheadsystems.crypto.manager.ParanoidManager;
import com.codeheadsystems.crypto.manager.SecondaryKey;
import com.codeheadsystems.crypto.password.SecretKeyExpiredException;
import com.codeheadsystems.pstore.utils.Jsonifier;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.IOException;

/**
 * BSD-Style License 2016
 */

public class ParanoidEncrypter<T> implements SecureEncrypter<T> {

    private final Jsonifier<T> jsonifier;
    private final ParanoidManager paranoidManager;

    public ParanoidEncrypter(Jsonifier<T> jsonifier, ParanoidManager paranoidManager) {
        this.jsonifier = jsonifier;
        this.paranoidManager = paranoidManager;
    }

    @Override
    public byte[] convertToString(SecondaryKey secondaryKey, T object) throws SecureEncryptionException, SecretKeyExpiredException {
        try {
            String json = jsonifier.toJson(object);
            return paranoidManager.encode(json, secondaryKey);
        } catch (JsonProcessingException e) {
            throw new SecureEncryptionException("Could not convert object to json", e);
        } catch (CryptoException e) {
            throw new SecureEncryptionException("Crypto decode failure", e);
        } catch (IOException e) {
            throw new SecureEncryptionException(e.getMessage(), e);
        }
    }

    @Override
    public T convertFromString(SecondaryKey secondaryKey, byte[] bytes) throws SecureEncryptionException, SecretKeyExpiredException {
        try {
            String json = paranoidManager.decode(bytes, secondaryKey);
            return jsonifier.fromJson(json);
        } catch (JsonProcessingException e) {
            throw new SecureEncryptionException("Could not convert object to json", e);
        } catch (CryptoException e) {
            throw new SecureEncryptionException("Crypto decode failure", e);
        } catch (IOException e) {
            throw new SecureEncryptionException(e.getMessage(), e);
        }
    }
}
