package com.codeheadsystems.pstore.model;

import com.codeheadsystems.pstore.datastore.StoredSecondaryKey;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.Objects;

/**
 * BSD-Style License 2016
 */
public class Entry {

    private final String url;
    private Date createDate;
    private Date updateDate;
    private String entryDetailIdentifier;
    private StoredSecondaryKey storedSecondaryKey;


    @JsonCreator
    public Entry(@JsonProperty(value = "url", required = true) String url,
                 @JsonProperty(value = "createDate") Date createDate,
                 @JsonProperty(value = "updateDate") Date updateDate,
                 @JsonProperty(value = "entryDetailIdentifier") String entryDetailIdentifier,
                 @JsonProperty(value = "storedSecondaryKey") StoredSecondaryKey storedSecondaryKey) {
        this(url);
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.entryDetailIdentifier = entryDetailIdentifier;
        this.storedSecondaryKey = storedSecondaryKey;
    }

    public Entry(String url) {
        this.url = Objects.requireNonNull(url);
    }

    public String getUrl() {
        return url;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entry entry = (Entry) o;

        return getUrl().equals(entry.getUrl());
    }

    @Override
    public int hashCode() {
        return getUrl().hashCode();
    }
}
