package com.vb.openlibraries.android.security.crypto.models;

import java.io.OutputStream;

/**
 * Created by Brize on 19/08/2014.
 */
public class CryptedOutputStream {
    private OutputStream mOutputStream;
    private byte[] mIV;

    public OutputStream getOutputStream() {
        return mOutputStream;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.mOutputStream = outputStream;
    }

    public byte[] getIV() {
        return mIV;
    }

    public void setIV(byte[] mIV) {
        this.mIV = mIV;
    }
}
