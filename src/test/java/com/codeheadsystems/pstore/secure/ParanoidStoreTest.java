package com.codeheadsystems.pstore.secure;

import com.codeheadsystems.crypto.CryptoException;
import com.codeheadsystems.crypto.manager.ParanoidManager;
import com.codeheadsystems.crypto.manager.SecondaryKey;
import com.codeheadsystems.crypto.password.SecretKeyExpiredException;
import com.codeheadsystems.pstore.SecureEncryptionException;
import com.codeheadsystems.pstore.model.Entry;
import com.codeheadsystems.pstore.utils.Jsonifier;
import com.codeheadsystems.pstore.utils.JsonifierBuilder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.Date;

import static com.codeheadsystems.pstore.utils.ValidationUtils.validateEqual;
import static org.mockito.Mockito.when;

/**
 * BSD-Style License 2016
 */
@RunWith(value = MockitoJUnitRunner.class)
public class ParanoidStoreTest {

    @Mock
    ParanoidManager paranoidManager;

    @Mock
    SecondaryKey secondaryKey;


    @Test
    public void defaultTest() throws IOException, SecureEncryptionException, SecretKeyExpiredException, CryptoException {
        Entry entry = getDefaultEntry();
        Jsonifier<Entry> jsonifier = new JsonifierBuilder().build(Entry.class);
        ParanoidEncrypter<Entry> paranoidStore = new ParanoidEncrypter<Entry>(jsonifier, paranoidManager);
        byte[] bytes = new byte[1];
        bytes[0] = 5;
        String jsonExpected = jsonifier.toJson(entry);

        when(paranoidManager.encode(jsonExpected, secondaryKey)).thenReturn(bytes);
        when(paranoidManager.decode(bytes, secondaryKey)).thenReturn(jsonExpected);

        String json = paranoidStore.convertToString(secondaryKey, entry);
        Entry e2 = paranoidStore.convertFromString(secondaryKey, json);
        validateEqual(entry, e2);
    }

    @Test
    public void testWithBuilder() throws IOException, SecretKeyExpiredException, CryptoException, SecureEncryptionException {
        Entry entry = getDefaultEntry();
        Jsonifier<Entry> jsonifier = new JsonifierBuilder().build(Entry.class);
        ParanoidEncrypter<Entry> paranoidStore = new ParanoidEncrypterBuilder().paranoidManager(paranoidManager).build(Entry.class);
        byte[] bytes = new byte[1];
        bytes[0] = 5;
        String jsonExpected = jsonifier.toJson(entry);

        when(paranoidManager.encode(jsonExpected, secondaryKey)).thenReturn(bytes);
        when(paranoidManager.decode(bytes, secondaryKey)).thenReturn(jsonExpected);

        String json = paranoidStore.convertToString(secondaryKey, entry);
        Entry e2 = paranoidStore.convertFromString(secondaryKey, json);
        validateEqual(entry, e2);
    }

    private Entry getDefaultEntry() {
        Entry entry;
        entry = new Entry("http://www.google.com/",
                new Date(),
                new Date(),
                "string123");
        return entry;
    }

}
