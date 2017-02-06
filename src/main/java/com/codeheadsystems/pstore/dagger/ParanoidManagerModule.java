package com.codeheadsystems.pstore.dagger;

import com.codeheadsystems.crypto.manager.ParanoidManager;
import com.codeheadsystems.crypto.manager.ParanoidManagerException;
import com.codeheadsystems.crypto.manager.SecuredParanoidManager;
import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

import static java.util.Objects.requireNonNull;

@Module
public class ParanoidManagerModule {

    private final ParanoidManager paranoidManager;

    public ParanoidManagerModule() {
        this(generateSecuredParanoidManager());
    }

    protected ParanoidManagerModule(ParanoidManager paranoidManager) {
        this.paranoidManager = requireNonNull(paranoidManager);
    }

    private static final ParanoidManager generateSecuredParanoidManager() {
        try {
            return new SecuredParanoidManager();
        } catch (ParanoidManagerException e) {
            throw new IllegalStateException(e);
        }
    }

    @Singleton
    @Provides
    public ParanoidManager paranoidManager() {
        return paranoidManager;
    }

}
