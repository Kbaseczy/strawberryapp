package com.cai.strawberryapp.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.cai.strawberryapp.R;

public class MainActivity
        extends Activity {

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_main);

        Intent localIntent = new Intent(MainActivity.this, LoginActivity.class);
        MainActivity.this.startActivity(localIntent);
        MainActivity.this.finish();
    }
}
