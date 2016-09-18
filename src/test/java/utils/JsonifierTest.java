package utils;

import com.codeheadsystems.crypto.CryptoException;
import com.codeheadsystems.crypto.manager.ParanoidManager;
import com.codeheadsystems.crypto.manager.SecondaryKey;
import com.codeheadsystems.crypto.password.SecretKeyExpiredException;
import com.codeheadsystems.pstore.model.Entry;
import com.codeheadsystems.pstore.model.EntryDetails;
import com.codeheadsystems.pstore.utils.Jsonifier;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.util.Date;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * BSD-Style License 2016
 */
@RunWith(value = MockitoJUnitRunner.class)
public class JsonifierTest {

    @Mock
    ParanoidManager paranoidManager;

    @Mock
    SecondaryKey secondaryKey;

    @Test
    public void defaultTest() throws IOException {
        Entry entry = getDefaultEntry();

        Jsonifier<Entry> jsonifier = new Jsonifier<Entry>(new ObjectMapper(), paranoidManager, Entry.class);
        String json = jsonifier.toJson(entry);
        Entry e2 = jsonifier.fromJson(json);
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

    private EntryDetails getDefaultEntryDetails() {
        EntryDetails entryDetails = new EntryDetails();
        entryDetails.getAttributes().put("k1", "v1");
        entryDetails.getAttributes().put("k2", "k2");
        entryDetails.setUsername("un1");
        entryDetails.setPassword("pw1");
        entryDetails.setNotes("blah");
        return entryDetails;
    }

    @Test(expected = NullPointerException.class)
    public void badEntry() {
        new Entry(null);
    }

    @Test
    public void testEntryDetails() throws IOException, SecretKeyExpiredException, CryptoException {
        EntryDetails ed = getDefaultEntryDetails();
        byte[] bytes = new byte[1];
        bytes[0] = 5;
        Jsonifier<EntryDetails> jsonifier = new Jsonifier<EntryDetails>(new ObjectMapper(), paranoidManager, EntryDetails.class);
        String json = jsonifier.toJson(ed);

        when(paranoidManager.encode(json, secondaryKey)).thenReturn(bytes);
        when(paranoidManager.decode(bytes, secondaryKey)).thenReturn(json);

        String in = jsonifier.toEncryptedString(ed, secondaryKey);
        EntryDetails ed2 = jsonifier.fromEncryptedString(in, secondaryKey);
        validateEqual(ed, ed2);
    }

    public void validate(Entry entry) {
        assertNotNull(entry);
        String entryDetails = entry.getEntryDetailsString();
        assertNotNull(entryDetails);
    }

    public void validate(EntryDetails entryDetails) {
        assertNotNull(entryDetails);
        assertNotNull(entryDetails.getAttributes());
    }

    public void validateEqual(Entry e1, Entry e2) {
        validate(e1);
        validate(e2);
        assertEquals(e1.getUrl(), e2.getUrl());
        assertEquals(e1.getCreateDate(), e2.getCreateDate());
        assertEquals(e1.getUpdateDate(), e2.getUpdateDate());
        assertEquals(e1.getEntryDetailsString(), e2.getEntryDetailsString());
    }

    public void validateEqual(EntryDetails ed1, EntryDetails ed2) {
        validate(ed1);
        validate(ed2);
        assertEquals(ed1.getUsername(), ed2.getUsername());
        assertEquals(ed1.getNotes(), ed2.getNotes());
        assertEquals(ed1.getPassword(), ed2.getPassword());
        assertEquals(ed1.getAttributes().size(), ed2.getAttributes().size());
        for (String key : ed1.getAttributes().keySet()) {
            assertEquals(ed1.getAttributes().get(key), ed2.getAttributes().get(key));
        }
    }

}
