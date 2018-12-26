package com.cai.strawberryapp.Activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageView;

import com.cai.strawberryapp.BaseClass.UriToPath;
import com.cai.strawberryapp.R;

import java.io.PrintStream;

public class AllScreamShowActivity extends Activity {

    private ImageView iv;
    private int windowHeight;
    private int windowWidth;

    public void getWindowData() {
        this.windowWidth = getWindowManager().getDefaultDisplay().getWidth();
        this.windowHeight = getWindowManager().getDefaultDisplay().getHeight();
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.activity_all_scream_show);
        getWindowData();

        this.iv = ((ImageView) findViewById(R.id.iv_allscream));

        String path = UriToPath.getRealPathFromUri(this, Uri.parse(getIntent().getExtras().getString("uri")));
        BitmapFactory.Options localOptions = new BitmapFactory.Options();
        localOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, localOptions);
        int i = localOptions.outHeight;
        int j = localOptions.outWidth;
        System.out.println("imageHeight" + i);
        System.out.println("imageWidth" + j);
        System.out.println("windowWidth" + this.windowWidth);
        System.out.println("windowHeight" + this.windowHeight);
        int k = j / this.windowWidth;
        int m = i / this.windowHeight;
        j = 1;
        i = j;
        if (k >= m) {
            i = j;
            if (m >= 1) {
                i = k;
            }
        }
        j = i;
        if (m >= k) {
            j = i;
            if (k >= 1) {
                j = m;
            }
        }
        localOptions.inJustDecodeBounds = false;
        System.out.println("scaleaaa" + j);
        localOptions.inSampleSize = j;
        Bitmap bitmap = BitmapFactory.decodeFile(path, localOptions);
        this.iv.setImageBitmap(bitmap);
        this.iv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View paramAnonymousView) {
                AllScreamShowActivity.this.finish();
            }
        });
    }
}
