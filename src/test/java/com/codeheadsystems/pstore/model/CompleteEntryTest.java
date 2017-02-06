package com.codeheadsystems.pstore.model;

import com.codeheadsystems.pstore.datastore.StoredSecondaryKey;

import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * BSD-Style License 2016
 */

public class CompleteEntryTest {

    private String title = "title-123";
    private String url = "http://www.xyzzy.com/";
    private Date createDate = new Date();
    private Date updateDate = new Date();
    private String entryDetailIdentifier = "id-zzz";
    private StoredSecondaryKey storedSecondaryKey = new StoredSecondaryKey(new byte[1], new byte[2]);
    private String username = "un";
    private String password = "pw";
    private String notes = "ns";
    private Map<String, String> attributes = new HashMap<String, String>();

    @Test
    public void testCreatingCompleteEntry() {
        Entry entry = new Entry(title, url, createDate, updateDate, entryDetailIdentifier, storedSecondaryKey);
        EntryDetails entryDetails = new EntryDetails(username, password, notes, attributes);
        CompleteEntry completeEntry = new CompleteEntry(entry);
        completeEntry.update(entryDetails);

        assertEquals(title, completeEntry.getTitle());
        assertEquals(url, completeEntry.getUrl());
        assertEquals(createDate, completeEntry.getCreateDate());
        assertEquals(updateDate, completeEntry.getUpdateDate());
        assertEquals(entryDetailIdentifier, completeEntry.getEntryDetailIdentifier());
        assertEquals(storedSecondaryKey.getEncryptedKey().length, completeEntry.getStoredSecondaryKey().getEncryptedKey().length);
        assertEquals(storedSecondaryKey.getSalt().length, completeEntry.getStoredSecondaryKey().getSalt().length);
        assertEquals(username, completeEntry.getUsername());
        assertEquals(password, completeEntry.getPassword());
        assertEquals(notes, completeEntry.getNotes());
    }

    @Test
    public void testCreatingEntryAndDetails() {
        CompleteEntry completeEntry = new CompleteEntry(title, url, createDate, updateDate, entryDetailIdentifier, storedSecondaryKey, username, password, notes, attributes);
        Entry entry = completeEntry.getEntry();
        EntryDetails entryDetails = completeEntry.getEntryDetails();

        assertEquals(title, entry.getTitle());
        assertEquals(url, entry.getUrl());
        assertEquals(createDate, entry.getCreateDate());
        assertEquals(updateDate, entry.getUpdateDate());
        assertEquals(entryDetailIdentifier, entry.getEntryDetailIdentifier());
        assertEquals(storedSecondaryKey.getEncryptedKey().length, entry.getStoredSecondaryKey().getEncryptedKey().length);
        assertEquals(storedSecondaryKey.getSalt().length, entry.getStoredSecondaryKey().getSalt().length);
        assertEquals(username, entryDetails.getUsername());
        assertEquals(password, entryDetails.getPassword());
        assertEquals(notes, entryDetails.getNotes());
    }

}
