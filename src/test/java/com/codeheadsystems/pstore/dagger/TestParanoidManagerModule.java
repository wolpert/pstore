package com.codeheadsystems.pstore.dagger;

import com.codeheadsystems.crypto.manager.ParanoidManager;
import com.codeheadsystems.crypto.manager.ParanoidManagerException;
import com.codeheadsystems.shash.impl.RandomProvider;
import dagger.Module;

import java.util.Random;

@Module
public class TestParanoidManagerModule extends ParanoidManagerModule {

    public TestParanoidManagerModule() throws ParanoidManagerException {
        super(new ParanoidManager(14, RandomProvider.generate(Random::new)));
    }

}
