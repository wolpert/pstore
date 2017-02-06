package com.codeheadsystems.pstore.datastore;

import com.codeheadsystems.crypto.Utilities;
import com.codeheadsystems.pstore.utils.Jsonifier;
import com.codeheadsystems.pstore.utils.JsonifierBuilder;
import com.codeheadsystems.shash.impl.RandomProvider;
import com.fasterxml.jackson.core.type.TypeReference;

import org.junit.Test;

import java.io.IOException;
import java.util.Random;

import static com.codeheadsystems.crypto.Utilities.isSame;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by wolpert on 10/17/16.
 */

public class StoredSecondaryKeyTest {

    @Test
    public void testMarshaling() throws IOException {
        RandomProvider randomProvider = RandomProvider.generate(Random::new);
        StoredSecondaryKey key = new StoredSecondaryKey(randomProvider.getRandomBytes(3), randomProvider.getRandomBytes(3));
        Jsonifier<StoredSecondaryKey> jsonifier = new JsonifierBuilder().build(new TypeReference<StoredSecondaryKey>(){});
        String json = jsonifier.toJson(key);
        StoredSecondaryKey result = jsonifier.fromJson(json);

        assertTrue(isSame(key.getEncryptedKey(), result.getEncryptedKey()));
        assertTrue(isSame(key.getSalt(), result.getSalt()));
    }

}
