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
