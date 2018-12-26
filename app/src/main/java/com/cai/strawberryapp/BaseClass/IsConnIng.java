package com.cai.strawberryapp.BaseClass;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;

public class IsConnIng
{
    public static boolean isNetworkAvailable(Context paramContext)
    {
        paramContext = (ConnectivityManager)paramContext.getSystemService("connectivity");
        int i;
        if (paramContext != null)
        {
            paramContext = paramContext.getAllNetworkInfo();
            if (paramContext != null) {
                i = 0;
            }
        }
        for (;;)
        {
            if (i >= paramContext.length) {
                return false;
            }
            if (paramContext[i].getState() == NetworkInfo.State.CONNECTED) {
                return true;
            }
            i += 1;
        }
    }
}
