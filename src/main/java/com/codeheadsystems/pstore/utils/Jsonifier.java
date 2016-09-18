package com.codeheadsystems.pstore.utils;

import com.codeheadsystems.crypto.CryptoException;
import com.codeheadsystems.crypto.manager.ParanoidManager;
import com.codeheadsystems.crypto.manager.SecondaryKey;
import com.codeheadsystems.crypto.password.SecretKeyExpiredException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Base64;

/**
 * BSD-Style License 2016
 */
public class Jsonifier<T> {

    private final ObjectMapper objectMapper;
    private final ParanoidManager paranoidManager;
    private final Class<T> clazz;

    public Jsonifier(ObjectMapper objectMapper, ParanoidManager paranoidManager, Class<T> clazz) {
        this.objectMapper = objectMapper;
        this.paranoidManager = paranoidManager;
        this.clazz = clazz;
    }

    public String toJson(T object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    public T fromJson(String json) throws IOException {
        return objectMapper.readValue(json, clazz);
    }

    public String toEncryptedString(T object, SecondaryKey secondaryKey) throws IOException, SecretKeyExpiredException, CryptoException {
        String json = toJson(object);
        byte[] bytes = paranoidManager.encode(json, secondaryKey);
        return Base64.getEncoder().encodeToString(bytes);
    }

    public T fromEncryptedString(String encryptedString, SecondaryKey secondaryKey) throws SecretKeyExpiredException, IOException, CryptoException {
        byte[] bytes = Base64.getDecoder().decode(encryptedString);
        String json = paranoidManager.decode(bytes, secondaryKey);
        return fromJson(json);
    }
}
