package com.codeheadsystems.pstore.model;

import com.codeheadsystems.pstore.datastore.StoredSecondaryKey;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * BSD-Style License 2016
 */
public class CompleteEntry {

    @JsonIgnore
    private final String title;

    @JsonIgnore
    private String url;

    @JsonIgnore
    private Date createDate;

    @JsonIgnore
    private Date updateDate;

    @JsonIgnore
    private String entryDetailIdentifier;

    @JsonIgnore
    private StoredSecondaryKey storedSecondaryKey;

    @JsonIgnore
    private String username;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private String notes;

    @JsonIgnore
    private Map<String, String> attributes = new HashMap<String, String>();

    public CompleteEntry(String title) {
        this.title = title;
    }

    public CompleteEntry(String title, String url, Date createDate, Date updateDate, String entryDetailIdentifier, StoredSecondaryKey storedSecondaryKey, String username, String password, String notes, Map<String, String> attributes) {
        this.title = title;
        this.url = url;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.entryDetailIdentifier = entryDetailIdentifier;
        this.storedSecondaryKey = storedSecondaryKey;
        this.username = username;
        this.password = password;
        this.notes = notes;
        this.attributes = attributes;
    }

    public CompleteEntry(Entry entry) {
        this(entry.getTitle());
        update(entry);
    }

    public void update(Entry entry) {
        url = entry.getUrl();
        createDate = entry.getCreateDate();
        updateDate = entry.getUpdateDate();
        entryDetailIdentifier = entry.getEntryDetailIdentifier();
        storedSecondaryKey = entry.getStoredSecondaryKey();
    }

    public Entry getEntry() {
        return new Entry(title, url, createDate, updateDate, entryDetailIdentifier, storedSecondaryKey);
    }

    public EntryDetails getEntryDetails() {
        return new EntryDetails(username, password, notes, attributes);
    }

    public void update(EntryDetails entryDetails) {
        username = entryDetails.getUsername();
        password = entryDetails.getPassword();
        notes = entryDetails.getNotes();
        attributes = entryDetails.getAttributes();
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getEntryDetailIdentifier() {
        return entryDetailIdentifier;
    }

    public void setEntryDetailIdentifier(String entryDetailIdentifier) {
        this.entryDetailIdentifier = entryDetailIdentifier;
    }

    public StoredSecondaryKey getStoredSecondaryKey() {
        return storedSecondaryKey;
    }

    public void setStoredSecondaryKey(StoredSecondaryKey storedSecondaryKey) {
        this.storedSecondaryKey = storedSecondaryKey;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }
}
