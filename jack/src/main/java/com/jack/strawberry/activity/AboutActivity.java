package com.jack.strawberry.activity;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jack.strawberry.R;


public class AboutActivity extends Activity {


    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_about);
        init();
    }

    public void init() {
        Button button = findViewById(R.id.but_about_back);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        String versionName = getVerName(this.getApplicationContext());
        ((TextView) findViewById(R.id.tv_about_version)).setText("草莓只是百科（" + versionName + ")");
    }

    public static String getVerName(Context context) {
        String versionName = "";
        try {
            versionName = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

}
