package com.cai.strawberryapp.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.cai.strawberryapp.R;

public class AboutActivity extends Activity {

    private Button but_about_back;
    private TextView tv_about_version;

    public static String getVerName(Context paramContext) {
        String versionName = "";
        try {
            versionName = paramContext.getPackageManager().getPackageInfo("com.cai.strawberryapp", 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    public void backButClick() {
        this.but_about_back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                AboutActivity.this.finish();
            }
        });
    }

    public void init() {
        this.but_about_back = ((Button) findViewById(R.id.but_about_back));
        this.tv_about_version = ((TextView) findViewById(R.id.tv_about_version));
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_about);
        init();
        backButClick();
        String str = getVerName(this);
        this.tv_about_version.setText("������������������(" + str + ")");
    }
}
