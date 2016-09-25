package com.codeheadsystems.pstore;

import com.codeheadsystems.crypto.manager.SecondaryKey;
import com.codeheadsystems.crypto.password.SecretKeyExpiredException;

/**
 * BSD-Style License 2016
 */

public interface SecureStore<T> {

    String convertToString(SecondaryKey secondaryKey, T object) throws SecureStoreException, SecretKeyExpiredException;

    T convertFromString(SecondaryKey secondaryKey, String string) throws SecureStoreException, SecretKeyExpiredException;

}
