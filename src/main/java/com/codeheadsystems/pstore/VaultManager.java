package com.codeheadsystems.pstore;

import com.codeheadsystems.crypto.manager.SecondaryKey;
import com.codeheadsystems.crypto.password.SecretKeyExpiredException;
import com.codeheadsystems.pstore.datastore.SecureEncryptionException;
import com.codeheadsystems.pstore.model.Entry;
import com.codeheadsystems.pstore.model.EntryDetails;

import java.io.IOException;
import java.util.List;

/**
 * Created by wolpert on 9/19/16.
 */
public interface VaultManager {

    List<Entry> entries(SecondaryKey secondaryKey);

    List<Entry> entries(SecondaryKey secondaryKey, String searchString);

    EntryDetails entryDetails(SecondaryKey secondaryKey, String identifier) throws SecretKeyExpiredException, IOException, SecureEncryptionException;

    String store(SecondaryKey secondaryKey, EntryDetails entryDetails) throws SecretKeyExpiredException, IOException, SecureEncryptionException;

    void delete(String identifier);

}
