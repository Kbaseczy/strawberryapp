package com.cai.strawberryapp.BaseClass;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class StreamTools
{
    public static String readStream(InputStream paramInputStream)
    {
        try
        {
            ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
            byte[] arrayOfByte = new byte['��'];
            for (;;)
            {
                int i = paramInputStream.read(arrayOfByte);
                if (i == -1)
                {
                    paramInputStream.close();
                    if (!new String(localByteArrayOutputStream.toByteArray()).contains("charset=gb2312")) {
                        break;
                    }
                    return new String(localByteArrayOutputStream.toByteArray(), "gb2312");
                }
                localByteArrayOutputStream.write(arrayOfByte, 0, i);
            }
            paramInputStream = new String(localByteArrayOutputStream.toByteArray());
        }
        catch (Exception paramInputStream)
        {
            paramInputStream.printStackTrace();
            return "";
        }
        return paramInputStream;
    }
}
