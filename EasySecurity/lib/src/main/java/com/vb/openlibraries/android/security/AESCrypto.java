package com.vb.openlibraries.android.security;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by Brize on 12/08/2014.
 */
public abstract class AESCrypto {

    private AESCrypto() {
    }

    public static byte[] encrypt(byte[] clearData, byte[] key, byte[] iv, String algo) {

        SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        Cipher cipher = null;
        byte[] encryptedData = null;
        byte[] ivAndEncryptedData = null;
        try {
            cipher = Cipher.getInstance(algo);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivSpec);
            encryptedData = cipher.doFinal(clearData);

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
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
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

        SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        Cipher cipher = null;
        byte[] clearData = null;
        try {
            cipher = Cipher.getInstance(algo);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivSpec);
            clearData = cipher.doFinal(dataToDecrypt);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return clearData;
    }
}
