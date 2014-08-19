package com.vb.openlibraries.android.security.crypto.ciphers.aes;

import java.io.InputStream;
import java.io.OutputStream;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
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

        try {
            cipher = Cipher.getInstance(algo);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivSpec);
            encryptedData = cipher.doFinal(clearData);
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
        return encryptedData;
    }

    public static byte[] decrypt(byte[] encryptedData, byte[] key, byte[] iv, String algo) {
        SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        Cipher cipher = null;
        byte[] clearData = null;
        try {
            cipher = Cipher.getInstance(algo);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivSpec);
            clearData = cipher.doFinal(encryptedData);
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

    public static OutputStream encryptOutputStream(OutputStream clearOutputStream, byte[] key, byte[] iv, String algo) {
        SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        CipherOutputStream cryptedOutputStream = null;
        Cipher cipher;

        try {
            cipher = Cipher.getInstance(algo);
            cryptedOutputStream = new CipherOutputStream(clearOutputStream, cipher);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivSpec);
            return cryptedOutputStream;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static InputStream decryptInputStream(InputStream cryptedInputStream, byte[] key, byte[] iv, String algo) {
        SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(iv);
        Cipher cipher = null;
        CipherInputStream decryptedInputStream = null;
        try {
            cipher = Cipher.getInstance(algo);
            decryptedInputStream = new CipherInputStream(cryptedInputStream, cipher);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivSpec);
            return decryptedInputStream;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
        return null;
    }
}