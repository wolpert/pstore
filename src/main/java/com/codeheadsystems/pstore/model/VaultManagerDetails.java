package com.codeheadsystems.pstore.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * BSD-Style License 2016
 */

public class VaultManagerDetails {

    private Map<String, Key> ids = new HashMap<>();

    @JsonCreator
    public VaultManagerDetails(@JsonProperty Map<String, Key> ids) {
        this.ids = ids;
    }

    public Collection<String> getIds() {
        return ids.keySet();
    }

    public void delete(String id) {
        ids.remove(id);
    }

    public void addId(String id, Key key) {
        ids.put(id, key);
    }

    public Key getKey(String id) {
        return ids.get(id);
    }
}
