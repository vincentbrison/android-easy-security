package com.vb.openlibraries.android.security.crypto.models;

import java.io.InputStream;

/**
 * Created by Brize on 19/08/2014.
 */
public class CryptedInputStream {
    private InputStream mInputStream;
    private byte[] mIV;

    public InputStream getInputStream() {
        return mInputStream;
    }

    public void setInputStream(InputStream mInputStream) {
        this.mInputStream = mInputStream;
    }

    public byte[] getIV() {
        return mIV;
    }

    public void setIV(byte[] mIV) {
        this.mIV = mIV;
    }
}
