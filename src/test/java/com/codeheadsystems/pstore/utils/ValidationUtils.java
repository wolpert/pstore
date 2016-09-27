package com.codeheadsystems.pstore.utils;

import com.codeheadsystems.pstore.model.Entry;
import com.codeheadsystems.pstore.model.EntryDetails;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * BSD-Style License 2016
 */

public class ValidationUtils {

    public static void validate(Entry entry) {
        assertNotNull(entry);
        String entryDetails = entry.getEntryDetailIdentifier();
        assertNotNull(entryDetails);
    }

    public static void validate(EntryDetails entryDetails) {
        assertNotNull(entryDetails);
        assertNotNull(entryDetails.getAttributes());
    }

    public static void validateEqual(Entry e1, Entry e2) {
        validate(e1);
        validate(e2);
        assertEquals(e1.getUrl(), e2.getUrl());
        assertEquals(e1.getCreateDate(), e2.getCreateDate());
        assertEquals(e1.getUpdateDate(), e2.getUpdateDate());
        assertEquals(e1.getEntryDetailIdentifier(), e2.getEntryDetailIdentifier());
    }

    public static void validateEqual(EntryDetails ed1, EntryDetails ed2) {
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
