package com.codeheadsystems.pstore.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * BSD-Style License 2016
 */

public class VaultManagerDetails {

    private Map<String, Key> idMap = new HashMap<>();

    @JsonCreator
    public VaultManagerDetails(@JsonProperty(value = "idMap") Map<String, Key> idMap) {
        this.idMap = idMap;
    }

    public Map<String, Key> getIdMap() {
        return idMap;
    }

    @JsonIgnore
    public Collection<String> getIds() {
        return idMap.keySet();
    }

    public void delete(String id) {
        idMap.remove(id);
    }

    public void addId(String id, Key key) {
        idMap.put(id, key);
    }

    public Key getKey(String id) {
        return idMap.get(id);
    }
}
