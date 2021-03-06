package com.codeheadsystems.pstore;

import com.codeheadsystems.crypto.CryptoException;
import com.codeheadsystems.crypto.manager.SecondaryKey;
import com.codeheadsystems.crypto.password.SecretKeyExpiredException;
import com.codeheadsystems.pstore.datastore.SecureEncryptionException;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * BSD-Style License 2016
 */
public class ParanoidStoreBuilderTest extends InjectedBaseTest {

    @Test
    public void testDefaultUsecase() throws IOException, CryptoException, SecretKeyExpiredException, SecureEncryptionException {
        Map<String, Integer> firstMap = getDefaultMap();
        ParanoidStore<Map<String, Integer>> store = pc().paranoidStoreBuilder().build(new TypeReference<Map<String, Integer>>() {
        });
        SecondaryKey secondaryKey = pc().paranoidManager().generateFreshSecondary("xyzzy");
        String id = store.put(secondaryKey, firstMap);
        try {
            assertNotNull(id);
            Map<String, Integer> secondMap = store.get(secondaryKey, id); // Need to fix generics here
            assertEquals(firstMap, secondMap);
        } finally {
            store.delete(id);
        }
    }

    @Test
    public void testNamedDatastore() throws IOException, CryptoException, SecretKeyExpiredException, SecureEncryptionException {
        Map<String, Integer> firstMap = getDefaultMap();
        String id = "blah";
        ParanoidStore<Map<String, Integer>> store = pc().paranoidStoreBuilder().build(new TypeReference<Map<String, Integer>>() {
        });
        SecondaryKey secondaryKey = pc().paranoidManager().generateFreshSecondary("xyzzy");
        String idReturned = store.put(secondaryKey, firstMap, id);
        try {
            assertEquals(id, idReturned);
            Map<String, Integer> secondMap = store.get(secondaryKey, id); // Need to fix generics here
            assertEquals(firstMap, secondMap);
        } finally {
            store.delete(id);
        }
    }

    private Map<String, Integer> getDefaultMap() {
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("xyzzy", 3212);
        return map;
    }

}
