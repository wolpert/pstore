package com.codeheadsystems.pstore.datastore;

/**
 * BSD-Style License 2016
 */

public class SecureEncryptionException extends Exception {
    public SecureEncryptionException(String message) {
        super(message);
    }

    public SecureEncryptionException(String message, Throwable cause) {
        super(message, cause);
    }
}
