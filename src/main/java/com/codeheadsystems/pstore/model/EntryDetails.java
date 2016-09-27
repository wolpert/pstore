package com.codeheadsystems.pstore.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * BSD-Style License 2016
 */
public class EntryDetails {

    private String username;
    private String password;
    private String notes;
    private Map<String, String> attributes = new HashMap<String, String>();

    public EntryDetails() {
    }

    @JsonCreator
    public EntryDetails(@JsonProperty(value = "username") String username,
                        @JsonProperty(value = "password") String password,
                        @JsonProperty(value = "notes") String notes,
                        @JsonProperty(value = "attributes", required = true) Map<String, String> attributes) {
        this.username = username;
        this.password = password;
        this.notes = notes;
        this.attributes = Objects.requireNonNull(attributes);
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
