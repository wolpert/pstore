package com.codeheadsystems.pstore;

import com.codeheadsystems.pstore.datastore.DataStoreEntry;
import com.codeheadsystems.pstore.model.Entry;
import com.codeheadsystems.pstore.model.EntryDetails;
import com.codeheadsystems.pstore.model.Vault;

/**
 * BSD-Style License 2016
 */
public interface ParanoidStore {

    Vault generateNewDataStore(String password);

    DataStoreEntry generateDataStoreEntry(Vault vault, Entry entry, EntryDetails entryDetails)

}
