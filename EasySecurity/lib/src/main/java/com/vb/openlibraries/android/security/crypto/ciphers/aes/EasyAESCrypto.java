package com.vb.openlibraries.android.security.crypto.ciphers.aes;

import com.vb.openlibraries.android.security.crypto.models.CryptedInputStream;
import com.vb.openlibraries.android.security.crypto.models.CryptedObject;
import com.vb.openlibraries.android.security.crypto.models.CryptedOutputStream;
import com.vb.openlibraries.android.security.crypto.generators.EasyKeyGenerator;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Brize on 12/08/2014.
 */
public abstract class EasyAESCrypto {

    private static final String DEFAULT_ALGO = "AES/CBC/PKCS5Padding";
    private static final int IV_SIZE = 16;

    private EasyAESCrypto() {

    }

    public static CryptedObject encrypt(byte[] clearData, byte[] key) {

        CryptedObject result = new CryptedObject();
        byte[] iv = EasyKeyGenerator.getInstance().generateKey(IV_SIZE);

        // Do encryption
        result.setCryptedData(AESCrypto.encrypt(clearData, key, iv, DEFAULT_ALGO));

        // Set IV in result
        result.setIv(iv);

        return result;
    }

    public static byte[] decrypt(CryptedObject cryptedData, byte[] key) {

        // Extract data to decryptInputStream
        byte[] clearData = AESCrypto.decrypt(cryptedData.getCryptedData(), key, cryptedData.getIv(), DEFAULT_ALGO);

        return clearData;
    }

    public static CryptedOutputStream encryptOutputStream(OutputStream clearData, byte[] key) {

        CryptedOutputStream result = new CryptedOutputStream();
        byte[] iv = EasyKeyGenerator.getInstance().generateKey(IV_SIZE);

        // Do encryption
        result.setOutputStream(AESCrypto.encryptOutputStream(clearData, key, iv, DEFAULT_ALGO));

        // Set IV in result
        result.setIV(iv);

        return result;
    }

    public static InputStream decryptInputStream(CryptedInputStream cryptedData, byte[] key) {

        // Extract data to decryptInputStream
        return AESCrypto.decryptInputStream(cryptedData.getInputStream(), key, cryptedData.getIV(), DEFAULT_ALGO);

    }
}
