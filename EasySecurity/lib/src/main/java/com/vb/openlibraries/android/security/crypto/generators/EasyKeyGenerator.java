package com.vb.openlibraries.android.security.crypto.generators;


import com.vb.openlibraries.android.security.crypto.generators.interfaces.KeyGenerator;
import com.vb.openlibraries.android.security.crypto.generators.patchs.PRNGFixes;

import java.security.SecureRandom;

/**
 * Created by Brize on 11/08/2014.
 */
public class EasyKeyGenerator implements KeyGenerator {

    private boolean mSecurityPatchesHasBeenApplied = false;

    private final SecureRandom mSecureRandom;

    private static EasyKeyGenerator sInstance = null;

    public static EasyKeyGenerator getInstance() {
        if (sInstance == null) {
            sInstance = new EasyKeyGenerator();
        }
        return sInstance;
    }

    private EasyKeyGenerator() {
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
