package android.openlibraries.vb.com.easysecurity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.vb.openlibraries.android.security.AESCrypto;
import com.vb.openlibraries.android.security.ActualKeyGenerator;
import com.vb.openlibraries.android.security.KeyGenerator;

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
                KeyGenerator keyGenerator = ActualKeyGenerator.getInstance();
                byte[] key = keyGenerator.generateKey(16);
                byte[] iv = keyGenerator.generateKey(16);
                byte[] data = "salut".getBytes();
                byte[] crypted = AESCrypto.encrypt(data, key, iv, "AES/CBC/PKCS5Padding");
                byte[] decrypted = AESCrypto.decrypt(crypted, key, 16, "AES/CBC/PKCS5Padding");
                String result = new String(decrypted);
                Log.d("crypto", result);
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
