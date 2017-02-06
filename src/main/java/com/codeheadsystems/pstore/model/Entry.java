package com.codeheadsystems.pstore.model;

import com.codeheadsystems.pstore.datastore.StoredSecondaryKey;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

import static java.util.Objects.requireNonNull;

/**
 * BSD-Style License 2016
 */
public class Entry {

    private final String title;
    private String url;
    private Date createDate;
    private Date updateDate;
    private String entryDetailIdentifier;
    private StoredSecondaryKey storedSecondaryKey;


    @JsonCreator
    public Entry(@JsonProperty(value = "title", required = true) String title,
                 @JsonProperty(value = "url") String url,
                 @JsonProperty(value = "createDate") Date createDate,
                 @JsonProperty(value = "updateDate") Date updateDate,
                 @JsonProperty(value = "entryDetailIdentifier") String entryDetailIdentifier,
                 @JsonProperty(value = "storedSecondaryKey") StoredSecondaryKey storedSecondaryKey) {
        this(title);
        this.url = url;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.entryDetailIdentifier = entryDetailIdentifier;
        this.storedSecondaryKey = storedSecondaryKey;
    }

    public Entry(String title) {
        this.title = requireNonNull(title);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
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
}
