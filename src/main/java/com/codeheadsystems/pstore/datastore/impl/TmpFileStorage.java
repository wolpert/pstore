package com.codeheadsystems.pstore.datastore.impl;

import com.codeheadsystems.pstore.datastore.DataStore;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * BSD-Style License 2016
 */

public class TmpFileStorage implements DataStore {

    private final File storageDirectory;

    public TmpFileStorage() throws IOException {
        this(new File("/tmp/pstore"));
    }

    public TmpFileStorage(File storageDirectory) throws IOException {
        this.storageDirectory = storageDirectory;
        if (!storageDirectory.exists() && !storageDirectory.mkdirs()) {
            throw new IllegalStateException("Unable to create storage dir: " + storageDirectory);
        }
    }

    public File getFileForIdentifier(String identifier) {
        return new File(storageDirectory, identifier);
    }

    @Override
    public String put(byte[] data) throws IOException {
        String identifier = UUID.randomUUID().toString();
        File outfile = getFileForIdentifier(identifier);
        FileOutputStream fos = new FileOutputStream(outfile);
        fos.write(data);
        fos.close();
        return identifier;
    }

    @Override
    public byte[] get(String identifier) throws IOException {
        File outfile = getFileForIdentifier(identifier);
        if (!outfile.exists()) {
            return null;
        }
        FileInputStream fis = new FileInputStream(outfile);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = fis.read(buffer);
        while (len >= 0) {
            baos.write(buffer, 0, len);
            len = fis.read(buffer);
        }
        fis.close();
        byte[] result = baos.toByteArray();
        baos.close();
        return result;
    }

    @Override
    public void delete(String identifier) {
        File outfile = getFileForIdentifier(identifier);
        if (outfile.exists()) {
            outfile.delete();
        }
    }
}
