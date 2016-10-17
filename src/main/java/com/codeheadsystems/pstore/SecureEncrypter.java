package com.codeheadsystems.pstore;

import com.codeheadsystems.crypto.manager.SecondaryKey;
import com.codeheadsystems.crypto.password.SecretKeyExpiredException;

/**
 * BSD-Style License 2016
 */

public interface SecureEncrypter<T> {

    byte[] convertToString(SecondaryKey secondaryKey, T object) throws SecureEncryptionException, SecretKeyExpiredException;

    T convertFromString(SecondaryKey secondaryKey, byte[] bytes) throws SecureEncryptionException, SecretKeyExpiredException;

}
