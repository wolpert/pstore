package com.codeheadsystems.pstore;

import com.codeheadsystems.crypto.manager.ParanoidManagerException;
import com.codeheadsystems.pstore.dagger.DaggerProductionComponent;
import com.codeheadsystems.pstore.dagger.ProductionComponent;
import com.codeheadsystems.pstore.dagger.TestDataStoreModule;
import com.codeheadsystems.pstore.dagger.TestParanoidManagerModule;
import com.codeheadsystems.pstore.datastore.impl.TmpFileStorage;
import org.junit.Before;

import java.io.IOException;

public abstract class InjectedBaseTest {

    private ProductionComponent productionComponent;
    private TmpFileStorage tmpFileStorage;

    public ProductionComponent pc() {
        return productionComponent;
    }

    public TmpFileStorage dataStore() {
        return tmpFileStorage;
    }

    @Before
    public void setup() throws IOException, ParanoidManagerException {
        productionComponent = DaggerProductionComponent.builder()
                .paranoidManagerModule(new TestParanoidManagerModule())
                .dataStoreModules(new TestDataStoreModule())
                .build();
        tmpFileStorage = (TmpFileStorage) productionComponent.dataStore();
    }


}
