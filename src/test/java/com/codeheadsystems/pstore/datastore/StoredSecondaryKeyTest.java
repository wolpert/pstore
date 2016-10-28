package com.codeheadsystems.pstore.datastore;

import com.codeheadsystems.crypto.Utilities;
import com.codeheadsystems.pstore.utils.Jsonifier;
import com.codeheadsystems.pstore.utils.JsonifierBuilder;

import org.junit.Test;

import java.io.IOException;

import static com.codeheadsystems.crypto.Utilities.isSame;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by wolpert on 10/17/16.
 */

public class StoredSecondaryKeyTest {

    @Test
    public void testMarshaling() throws IOException {
        StoredSecondaryKey key = new StoredSecondaryKey(Utilities.randomBytes(3), Utilities.randomBytes(3));
        Jsonifier<StoredSecondaryKey> jsonifier = new JsonifierBuilder().build(StoredSecondaryKey.class);
        String json = jsonifier.toJson(key);
        StoredSecondaryKey result = jsonifier.fromJson(json);

        assertTrue(isSame(key.getEncryptedKey(), result.getEncryptedKey()));
        assertTrue(isSame(key.getSalt(), result.getSalt()));
    }

}
