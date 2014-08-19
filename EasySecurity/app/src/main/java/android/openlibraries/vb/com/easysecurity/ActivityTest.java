package android.openlibraries.vb.com.easysecurity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.vb.openlibraries.android.security.crypto.ciphers.aes.EasyAESCrypto;
import com.vb.openlibraries.android.security.crypto.generators.EasyKeyGenerator;
import com.vb.openlibraries.android.security.crypto.models.CryptedInputStream;
import com.vb.openlibraries.android.security.crypto.models.CryptedObject;
import com.vb.openlibraries.android.security.crypto.models.CryptedOutputStream;
import com.vb.openlibraries.android.security.crypto.generators.interfaces.KeyGenerator;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class ActivityTest extends ActionBarActivity {

    private Button buttonTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        buttonTest = (Button) findViewById(R.id.buttonTest);
        buttonTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                KeyGenerator keyGenerator = EasyKeyGenerator.getInstance();
                byte[] key = keyGenerator.generateKey(16);
                CryptedObject cryptedObject = EasyAESCrypto.encrypt("salut".getBytes(), key);
                Log.d("crypto", new String(EasyAESCrypto.decrypt(cryptedObject, key)));

                try {
                    OutputStream stream = openFileOutput("test", Context.MODE_PRIVATE);
                    CryptedOutputStream cryptedOutputStream = EasyAESCrypto.encryptOutputStream(stream, key);
                    cryptedOutputStream.getOutputStream().write("salut".getBytes());
                    cryptedOutputStream.getOutputStream().close();

                    FileInputStream inputStream = openFileInput("test");
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder out = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        out.append(line);
                    }
                    Log.d("crypto", "content of file crypted : " + out.toString());
                    reader.close();

                    FileInputStream inputStreamDecrypt = openFileInput("test");
                    CryptedInputStream input = new CryptedInputStream();
                    input.setIV(cryptedOutputStream.getIV());
                    input.setInputStream(inputStreamDecrypt);
                    BufferedReader readerDecrypted = new BufferedReader(new InputStreamReader(EasyAESCrypto.decryptInputStream(input, key)));
                    StringBuilder outDecrypted = new StringBuilder();
                    String lineDecrypted;
                    while ((lineDecrypted = readerDecrypted.readLine()) != null) {
                        outDecrypted.append(lineDecrypted);
                    }
                    Log.d("crypto", "content of file decrypted : " + outDecrypted.toString());
                    readerDecrypted.close();

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
