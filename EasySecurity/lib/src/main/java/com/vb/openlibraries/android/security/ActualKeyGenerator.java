package com.vb.openlibraries.android.security;


import java.security.SecureRandom;

/**
 * Created by Brize on 11/08/2014.
 */
public class ActualKeyGenerator implements KeyGenerator{

    private boolean mSecurityPatchesHasBeenApplied = false;

    private final SecureRandom mSecureRandom;

    private static ActualKeyGenerator sInstance = null;

    public static ActualKeyGenerator getInstance() {
        if (sInstance == null) {
            sInstance = new ActualKeyGenerator();
        }
        return sInstance;
    }

    private ActualKeyGenerator() {
        if (!mSecurityPatchesHasBeenApplied) {
            PRNGFixes.apply();
        }
        mSecureRandom = new SecureRandom();
    }

    @Override
    public byte[] generateKey(int length) {
        byte[] key = new byte[length];
        mSecureRandom.nextBytes(key);
        return key;
    }
}
