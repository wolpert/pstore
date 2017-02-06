package com.codeheadsystems.pstore.dagger;

import com.codeheadsystems.pstore.datastore.impl.TmpFileStorage;
import dagger.Module;

import javax.annotation.PreDestroy;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Created by wolpert on 2/5/17.
 */
@Module
public class TestDataStoreModule extends DataStoreModules {

    public TestDataStoreModule() {
        super(getTmpFileStorage());
    }

    private static final TmpFileStorage getTmpFileStorage() {
        try {
            Path storeDir = Files.createTempDirectory("pstoretest-");
            return new TmpFileStorage(storeDir.toFile());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    @PreDestroy
    public void destroy() {
        ((TmpFileStorage) dataStore()).erase();
    }

}
