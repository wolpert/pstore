package com.codeheadsystems.pstore.utils;

import com.codeheadsystems.pstore.model.Entry;
import com.fasterxml.jackson.core.type.TypeReference;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.codeheadsystems.pstore.utils.ValidationUtils.validateEqual;

/**
 * BSD-Style License 2016
 */
public class JsonifierTest {

    @Test
    public void defaultTest() throws IOException {
        Entry entry = getDefaultEntry();

        Jsonifier<Entry> jsonifier = new JsonifierBuilder().build(new TypeReference<Entry>(){});
        String json = jsonifier.toJson(entry);
        Entry e2 = jsonifier.fromJson(json);
        validateEqual(entry, e2);
    }

    private Entry getDefaultEntry() {
        Entry entry;
        entry = new Entry("Google",
                "http://www.google.com/",
                new Date(),
                new Date(),
                "string123", null);
        return entry;
    }
}
