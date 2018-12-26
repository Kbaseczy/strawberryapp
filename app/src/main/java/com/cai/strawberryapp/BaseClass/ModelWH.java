package com.cai.strawberryapp.BaseClass;

import android.content.Context;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnPreDrawListener;
import java.io.PrintStream;

public class ModelWH
{
    private static int width;

    public static int ModelHeight(View paramView, Context paramContext)
    {
        System.out.println(paramView);
        paramView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener()
        {
            public boolean onPreDraw()
            {
                int i = ModelWH.this.getMeasuredHeight();
                System.out.println(i + "jjjjj");
                return true;
            }
        });
        return 0;
    }

    public static int ModelWidth(View paramView, final Context paramContext)
    {
        System.out.println(paramView);
        paramView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
        {
            public void onGlobalLayout()
            {
                ModelWH.this.getViewTreeObserver().removeGlobalOnLayoutListener((ViewTreeObserver.OnGlobalLayoutListener)paramContext);
                ModelWH.width = ModelWH.this.getWidth();
                System.out.println(ModelWH.width);
            }
        });
        return width;
    }
}
