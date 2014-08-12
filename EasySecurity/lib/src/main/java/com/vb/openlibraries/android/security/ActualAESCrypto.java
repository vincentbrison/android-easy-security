package com.vb.openlibraries.android.security;

/**
 * Created by Brize on 12/08/2014.
 */
public abstract class ActualAESCrypto {

    private ActualAESCrypto() {

    }

    public static byte[] encrypt(byte[] clearData, byte[] key, byte[] iv, String algo) {

        // Do encryption
        byte[] encryptedData = AESCrypto.encrypt(clearData, key, iv, algo);

        byte[] ivAndEncryptedData = null;

        // Generate iv + encrypted data
        ivAndEncryptedData = new byte[encryptedData.length + iv.length];

        // Set IV in result
        for (int i = 0; i < iv.length; i++) {
            ivAndEncryptedData[i] = iv[i];
        }

        // Set encrypted data
        for (int i = iv.length; i < ivAndEncryptedData.length; i++) {
            ivAndEncryptedData[i] = encryptedData[i - iv.length];
        }
        return ivAndEncryptedData;
    }

    public static byte[] decrypt(byte[] encryptedData, byte[] key, int ivLength, String algo) {

        // Extract iv
        byte[] iv = new byte[ivLength];
        for (int i = 0; i < ivLength; i++) {
            iv[i] = encryptedData[i];
        }

        // Extract data to decrypt
        byte[] dataToDecrypt = new byte[encryptedData.length - ivLength];
        for (int i = 0; i < encryptedData.length - ivLength; i++) {
            dataToDecrypt[i] = encryptedData[i + ivLength];
        }

        byte[] clearData = AESCrypto.decrypt(dataToDecrypt, key, iv, algo);

        return clearData;
    }
}
