package com.codeheadsystems.pstore.model;

/**
 * Created by wolpert on 9/19/16.
 */
public class Vault {

    private byte[] encryptedEntries;
    private byte[] encryptedKey;
    private byte[] salt;

    public byte[] getEncryptedEntries() {
        return encryptedEntries;
    }

    public void setEncryptedEntries(byte[] encryptedEntries) {
        this.encryptedEntries = encryptedEntries;
    }

    public byte[] getEncryptedKey() {
        return encryptedKey;
    }

    public void setEncryptedKey(byte[] encryptedKey) {
        this.encryptedKey = encryptedKey;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }
}
