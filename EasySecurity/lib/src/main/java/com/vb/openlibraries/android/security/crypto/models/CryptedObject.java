package com.vb.openlibraries.android.security.crypto.models;

/**
 * Created by Brize on 19/08/2014.
 */
public class CryptedObject {
    private byte[] cryptedData;
    private byte[] iv;

    public byte[] getCryptedData() {
        return cryptedData;
    }

    public void setCryptedData(byte[] cryptedData) {
        this.cryptedData = cryptedData;
    }

    public byte[] getIv() {
        return iv;
    }

    public void setIv(byte[] iv) {
        this.iv = iv;
    }

}
