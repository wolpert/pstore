package com.codeheadsystems.pstore.dagger;

import com.codeheadsystems.pstore.datastore.DataStore;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class DataStoreModules {

    protected final DataStore dataStore;

    public DataStoreModules(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    @Provides
    @Singleton
    public DataStore dataStore() {
        return dataStore;
    }

}
