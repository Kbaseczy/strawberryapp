package com.cai.strawberryapp.BaseClass;

public class TimeFormat
{
    private static String StrTimeFromat(int paramInt)
    {
        String str = "";
        if (paramInt < 10) {
            str = "0" + paramInt;
        }
        do
        {
            return str;
            if ((paramInt > 10) || (paramInt == 10)) {
                return paramInt;
            }
        } while (paramInt != 0);
        return "00";
    }

    public static String timeFormat(double paramDouble)
    {
        long l = Math.round(paramDouble / 1000.0D);
        int i = (int)(l % 60L);
        l = Math.round((float)(l / 60L));
        int j = (int)(l % 60L);
        return StrTimeFromat((int)(Math.round((float)(l / 60L)) % 60L)) + ":" + StrTimeFromat(j) + ":" + StrTimeFromat(i);
    }
}
