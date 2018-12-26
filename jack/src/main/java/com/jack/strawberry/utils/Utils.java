package com.jack.strawberry.utils;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by huangdong on 18/9/27.
 * antony.huang@yeahmobi.com
 */
public class Utils {

    public static String getStrFromAssets(Context context, String path) {
        String str;

        try {
            AssetManager assetManager = context.getAssets();
            InputStream is = assetManager.open(path);
            str = inputStreamToString(is);
        } catch (IOException e) {
            str = null;
            e.printStackTrace();
        }
        return str;
    }

    private static String inputStreamToString(InputStream inputStream) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];
        int length;
        while ((length = inputStream.read(bytes)) != -1) {
            baos.write(bytes, 0, length);
        }
        baos.flush();
        return baos.toString();
    }


    public static void openBrowser(Context context, String linkUrl) {

        try {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(linkUrl));
            browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(browserIntent);
        } catch (Exception e) {
            e.fillInStackTrace();
        }
    }


}
