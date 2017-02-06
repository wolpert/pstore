package com.codeheadsystems.pstore.dagger;

import com.codeheadsystems.crypto.manager.ParanoidManager;
import com.codeheadsystems.pstore.ParanoidStoreBuilder;
import com.codeheadsystems.pstore.datastore.DataStore;
import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {CommonModules.class, DataStoreModules.class, ParanoidManagerModule.class})
public interface ProductionComponent {

    DataStore dataStore();
    ParanoidManager paranoidManager();
    ParanoidStoreBuilder paranoidStoreBuilder();

}
