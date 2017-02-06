package com.codeheadsystems.pstore.dagger;

import com.codeheadsystems.pstore.ParanoidStore;
import com.codeheadsystems.pstore.ParanoidStoreBuilder;
import com.codeheadsystems.pstore.model.EntryDetails;
import com.codeheadsystems.pstore.model.Vault;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

@Module
public class CommonModules {

    @Provides
    @Singleton
    public static ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Provides
    @Singleton
    public static ParanoidStore<Vault> vaultParanoidStore(ParanoidStoreBuilder psb) {
        return psb.build(new TypeReference<Vault>() {
        });
    }

    @Provides
    @Singleton
    public static ParanoidStore<EntryDetails> entryDetailsParanoidStore(ParanoidStoreBuilder psb) {
        return psb.build(new TypeReference<EntryDetails>() {
        });
    }
}
