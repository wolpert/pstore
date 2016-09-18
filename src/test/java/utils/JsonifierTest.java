package utils;

import com.codeheadsystems.crypto.manager.ParanoidManager;
import com.codeheadsystems.pstore.model.Entry;
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

/**
 * BSD-Style License 2016
 */
@RunWith(value = MockitoJUnitRunner.class)
public class JsonifierTest {

    @Mock
    ParanoidManager paranoidManager;

    @Test
    public void defaultTest() throws IOException {
        Entry entry = getDefaultEntry();

        Jsonifier<Entry> jsonifier = new Jsonifier<Entry>(new ObjectMapper(), paranoidManager, Entry.class);
        String json = jsonifier.toJson(entry);
        Entry e2 = jsonifier.fromJson(json);
        validateEntriesAreEqual(entry, e2);
    }

    private Entry getDefaultEntry() {
        Entry entry;
        entry = new Entry("http://www.google.com/",
                new Date(),
                new Date(),
                "string123");
        return entry;
    }

    @Test(expected = NullPointerException.class)
    public void badEntry() {
        new Entry(null);
    }

    public void validateEntry(Entry entry) {
        assertNotNull(entry);
        String entryDetails = entry.getEntryDetailsString();
        assertNotNull(entryDetails);
    }

    public void validateEntriesAreEqual(Entry e1, Entry e2) {
        validateEntry(e1);
        validateEntry(e2);
        assertEquals(e1.getUrl(), e2.getUrl());
        assertEquals(e1.getCreateDate(), e2.getCreateDate());
        assertEquals(e1.getUpdateDate(), e2.getUpdateDate());
        assertEquals(e1.getEntryDetailsString(), e2.getEntryDetailsString());
    }

}
