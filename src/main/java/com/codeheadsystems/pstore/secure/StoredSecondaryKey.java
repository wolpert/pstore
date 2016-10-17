package com.codeheadsystems.pstore.secure;

import com.codeheadsystems.crypto.CryptoException;
import com.codeheadsystems.crypto.manager.Manager;
import com.codeheadsystems.crypto.manager.SecondaryKey;
import com.codeheadsystems.crypto.password.SecretKeyExpiredException;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by wolpert on 10/17/16.
 */

public class StoredSecondaryKey {

    private final byte[] encryptedKey;
    private final byte[] salt;

    @JsonCreator
    public StoredSecondaryKey(@JsonProperty(value = "encryptedKey") byte[] encryptedKey,
                              @JsonProperty(value = "salt") byte[] salt) {
        this.encryptedKey = encryptedKey;
        this.salt = salt;
    }

    public StoredSecondaryKey(SecondaryKey secondaryKey) {
        this(secondaryKey.getEncryptedKey(), secondaryKey.getSalt());
    }

    public SecondaryKey regenerate(Manager manager, String password) throws CryptoException, SecretKeyExpiredException {
        return manager.regenerateSecondary(password, salt, encryptedKey);
    }

    public SecondaryKey regenerate(Manager manager, SecondaryKey secondaryKey) throws CryptoException, SecretKeyExpiredException {
        return manager.regenerateSecondary(secondaryKey, encryptedKey);
    }

    public byte[] getEncryptedKey() {
        return encryptedKey;
    }

    public byte[] getSalt() {
        return salt;
    }
}
