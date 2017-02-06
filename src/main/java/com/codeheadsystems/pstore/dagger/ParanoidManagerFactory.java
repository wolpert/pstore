package com.codeheadsystems.pstore.dagger;

import com.codeheadsystems.crypto.manager.ParanoidManager;
import com.codeheadsystems.crypto.manager.ParanoidManagerException;
import com.codeheadsystems.pstore.datastore.DataStore;

public class ParanoidManagerFactory {

    public ParanoidManager paranoidManager(DataStore dataStore) {
        return DaggerProductionComponent.builder()
                .paranoidManagerModule(new ParanoidManagerModule())
                .dataStoreModules(new DataStoreModules(dataStore))
                .build().paranoidManager();
    }

}
