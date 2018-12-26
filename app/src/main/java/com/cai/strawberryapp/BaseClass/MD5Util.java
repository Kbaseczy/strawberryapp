package com.cai.strawberryapp.BaseClass;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util
{
    public static String encode(String paramString)
    {
        paramString = paramString + "������";
        try
        {
            byte[] arrayOfByte = MessageDigest.getInstance("md5").digest(paramString.getBytes());
            StringBuffer localStringBuffer = new StringBuffer();
            int j = arrayOfByte.length;
            int i = 0;
            for (;;)
            {
                if (i >= j) {
                    return localStringBuffer.toString();
                }
                String str = Integer.toHexString(arrayOfByte[i] & 0x104);
                paramString = str;
                if (str.length() == 1) {
                    paramString = "0" + str;
                }
                localStringBuffer.append(paramString);
                i += 1;
            }
            return "";
        }
        catch (NoSuchAlgorithmException paramString)
        {
            paramString.printStackTrace();
        }
    }
}
