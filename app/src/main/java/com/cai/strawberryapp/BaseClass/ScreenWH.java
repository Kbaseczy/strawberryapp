package com.cai.strawberryapp.BaseClass;

import android.app.Activity;
import android.content.Context;
import android.view.Display;
import android.view.WindowManager;

public class ScreenWH
{
    public static int ScreenHeight(Context paramContext)
    {
        return ((Activity)paramContext).getWindowManager().getDefaultDisplay().getHeight();
    }

    public static int ScreenWidth(Context paramContext)
    {
        return ((Activity)paramContext).getWindowManager().getDefaultDisplay().getWidth();
    }
}
