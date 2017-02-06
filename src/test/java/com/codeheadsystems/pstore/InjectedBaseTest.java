package com.codeheadsystems.pstore;

import com.codeheadsystems.crypto.manager.ParanoidManager;
import com.codeheadsystems.crypto.manager.ParanoidManagerException;
import com.codeheadsystems.pstore.dagger.DaggerProductionComponent;
import com.codeheadsystems.pstore.dagger.ProductionComponent;
import com.codeheadsystems.pstore.dagger.TestDataStoreModule;
import com.codeheadsystems.pstore.dagger.TestParanoidManagerModule;
import com.codeheadsystems.pstore.datastore.DataStore;
import com.codeheadsystems.pstore.datastore.impl.TmpFileStorage;
import org.junit.Before;

import java.io.IOException;

public abstract class InjectedBaseTest {

    private TmpFileStorage dataStore;
    private ParanoidManager manager;
    private ParanoidStoreBuilder paranoidStoreBuilder;

    public ParanoidManager paranoidManager() {
        return manager;
    }

    public ParanoidStoreBuilder paranoidStoreBuilder() {
        return paranoidStoreBuilder;
    }

    public TmpFileStorage dataStore() {
        return dataStore;
    }

    @Before
    public void setup() throws IOException, ParanoidManagerException {
        ProductionComponent pc = DaggerProductionComponent.builder()
                .paranoidManagerModule(new TestParanoidManagerModule())
                .dataStoreModules(new TestDataStoreModule())
                .build();
        manager = pc.paranoidManager();
        paranoidStoreBuilder = pc.paranoidStoreBuilder();
        dataStore = (TmpFileStorage) pc.dataStore();
    }



}
