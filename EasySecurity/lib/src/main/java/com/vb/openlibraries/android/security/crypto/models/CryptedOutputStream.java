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

import java.io.OutputStream;

/**
 * Created by Brize on 19/08/2014.
 */
public class CryptedOutputStream {
    private OutputStream mOutputStream;
    private byte[] mIV;

    public OutputStream getOutputStream() {
        return mOutputStream;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.mOutputStream = outputStream;
    }

    public byte[] getIV() {
        return mIV;
    }

    public void setIV(byte[] mIV) {
        this.mIV = mIV;
    }
}
