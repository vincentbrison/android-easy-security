/*
 * Copyright 2014 Vincent Brison.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.vb.openlibraries.android.security.crypto.ciphers.aes;

import com.vb.openlibraries.android.security.crypto.models.CryptedInputStream;
import com.vb.openlibraries.android.security.crypto.models.CryptedObject;
import com.vb.openlibraries.android.security.crypto.models.CryptedOutputStream;
import com.vb.openlibraries.android.security.crypto.generators.EasyKeyGenerator;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * This class provide an easy to use, fast API to do encryption and decryption jobs on Android,
 * using AES cryptographic tools. This class use the tools provided by {@link com.vb.openlibraries.android.security.crypto.ciphers.aes.AESCrypto}.
 * Randoms IV are produced through {@link com.vb.openlibraries.android.security.crypto.generators.EasyKeyGenerator} and
 * send back trough holder in result of encryption methods.
 */
public abstract class EasyAESCrypto {

    private static final String DEFAULT_ALGO = "AES/CBC/PKCS5Padding";
    private static final int IV_SIZE = 16;

    private EasyAESCrypto() {

    }

    /**
     * Encrypt data.
     * @param clearData is the data to encrypt.
     * @param key is the key used to do the encryption.
     * @return an holder on the data encrypted and the IV (automatically generated).
     */
    public static CryptedObject encrypt(byte[] clearData, byte[] key) {

        CryptedObject result = new CryptedObject();
        byte[] iv = EasyKeyGenerator.getInstance().generateKey(IV_SIZE);

        // Do encryption
        result.setCryptedData(AESCrypto.encrypt(clearData, key, iv, DEFAULT_ALGO));

        // Set IV in result
        result.setIv(iv);

        return result;
    }

    /**
     * Decrypt data.
     * @param cryptedData is the data to decrypt.
     * @param key is the key used to decrypt data (same used for the encryption).
     * @return the decrypted data.
     */
    public static byte[] decrypt(CryptedObject cryptedData, byte[] key) {

        // Extract data to decryptInputStream
        byte[] clearData = AESCrypto.decrypt(cryptedData.getCryptedData(), key, cryptedData.getIv(), DEFAULT_ALGO);

        return clearData;
    }

    /**
     * Encrypt an output stream, allowing you to directly encrypted what you are writing in a stream.
     * @param clearData is the based output stream to encrypt.
     * @param key is the key used to do the encryption.
     * @return the encrypted output stream and the random IV used, generated through {@link com.vb.openlibraries.android.security.crypto.generators.EasyKeyGenerator}.
     */
    public static CryptedOutputStream encryptOutputStream(OutputStream clearData, byte[] key) {

        CryptedOutputStream result = new CryptedOutputStream();
        byte[] iv = EasyKeyGenerator.getInstance().generateKey(IV_SIZE);

        // Do encryption
        result.setOutputStream(AESCrypto.encryptOutputStream(clearData, key, iv, DEFAULT_ALGO));

        // Set IV in result
        result.setIV(iv);

        return result;
    }

    /**
     * Decrypt an input stream.
     * @param cryptedData is the input stream to decrypt and the IV used for the encryption.
     * @param key is the key used to decrypt stream (same used for the encryption).
     * @return the decrypted stream.
     */
    public static InputStream decryptInputStream(CryptedInputStream cryptedData, byte[] key) {

        // Extract data to decryptInputStream
        return AESCrypto.decryptInputStream(cryptedData.getInputStream(), key, cryptedData.getIV(), DEFAULT_ALGO);
    }
}
