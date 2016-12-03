package com.codeheadsystems.pstore;

import com.codeheadsystems.crypto.CryptoException;
import com.codeheadsystems.crypto.password.SecretKeyExpiredException;
import com.codeheadsystems.pstore.datastore.SecureEncryptionException;
import com.codeheadsystems.pstore.exceptions.VaultExistsException;
import com.codeheadsystems.pstore.model.Vault;

import java.io.IOException;
import java.util.Collection;

/**
 * Created by wolpert on 9/19/16.
 */
public interface VaultManager {

    Vault initVault(String id, String password) throws VaultExistsException, CryptoException, SecretKeyExpiredException, IOException, SecureEncryptionException;

    Vault unlockVault(String id, String password) throws VaultExistsException, CryptoException, SecretKeyExpiredException, IOException, SecureEncryptionException;

    void deleteVault(String id) throws IOException;

    Collection<String> listVaults();

}
