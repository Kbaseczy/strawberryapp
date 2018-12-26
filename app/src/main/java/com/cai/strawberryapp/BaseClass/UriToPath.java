package com.cai.strawberryapp.BaseClass;

import android.content.ContentResolver;
import android.content.Context;
import android.content.CursorLoader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;

import java.io.PrintStream;

public class UriToPath {

    public static String getRealPathFromUri(Context paramContext, Uri paramUri) {

        return getRealPathFromUri_AboveApi19(paramContext, paramUri);
    }

    private static String getRealPathFromUri_AboveApi19(Context paramContext, Uri paramUri) {
        Object localObject1 = null;
        Object localObject2 = android.provider.DocumentsContract.getDocumentId(paramUri).split(":")[1];
        String[] strings = new String[1];
        paramUri[0] = "_data";
        localObject2 = paramContext.getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, paramUri, "_id=?", new String[]{localObject2}, null);
        int i = ((Cursor) localObject2).getColumnIndex(paramUri[0]);
        paramContext = (Context) localObject1;
        if (((Cursor) localObject2).moveToFirst()) {
            paramContext = ((Cursor) localObject2).getString(i);
        }
        ((Cursor) localObject2).close();
        return paramContext;
    }

}
