package com.codeheadsystems.pstore.model;

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
    private String entryDetailsString;

    public Entry(String url) {
        this.url = Objects.requireNonNull(url);
    }

    @JsonCreator
    public Entry(@JsonProperty(value = "url", required = true) String url,
                 @JsonProperty(value = "createDate") Date createDate,
                 @JsonProperty(value = "updateDate") Date updateDate,
                 @JsonProperty(value = "entryDetails") String entryDetailsString) {
        this.url = Objects.requireNonNull(url);
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.entryDetailsString = entryDetailsString;
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

    public String getEntryDetailsString() {
        return entryDetailsString;
    }

    public void setEntryDetailsString(String entryDetailsString) {
        this.entryDetailsString = entryDetailsString;
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
