package com.vb.openlibraries.android.security;

/**
 * Created by Brize on 11/08/2014.
 */
public interface KeyGenerator {

    public byte[] generateKey(int length);
}
