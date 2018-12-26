package com.cai.strawberryapp.BaseClass;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Message;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class ConnWeb
{
    public static String ConnForJson(Activity paramActivity, String paramString1, String paramString2, String paramString3, int paramInt, List<String> paramList, List<Object> paramList1)
    {
        String str = paramString1;
        try
        {
            if (paramString3.equals("GET")) {
                str = paramString1 + "?" + paramString2;
            }
            paramString1 = (HttpURLConnection)new URL(str).openConnection();
            paramString1.setRequestMethod(paramString3);
            int i = 0;
            for (;;)
            {
                if (i >= paramInt)
                {
                    if (paramString3.equals("POST"))
                    {
                        paramString1.setDoOutput(true);
                        paramString1.setUseCaches(false);
                        paramString1.setRequestProperty("Content-Length", paramString2.getBytes().length);
                        paramString1.setRequestProperty("Charset", "UTF-8");
                        paramString3 = paramString1.getOutputStream();
                        paramString3.write(paramString2.getBytes("UTF-8"));
                        paramString3.flush();
                        paramString3.close();
                    }
                    if (paramString1.getResponseCode() != 200) {
                        break;
                    }
                    paramString2 = paramString1.getInputStream();
                    paramString3 = StreamTools.readStream(paramString2);
                    paramString2.close();
                    System.out.println("is���");
                    if (paramString1 == null) {
                        return paramString3;
                    }
                    paramString1.disconnect();
                    System.out.println("huc���");
                    return paramString3;
                }
                paramString1.setRequestProperty((String)paramList.get(i), (String)paramList1.get(i));
                i += 1;
            }
            return "0";
        }
        catch (IOException paramString1)
        {
            System.out.println("aasss");
            showConnFailDialog(paramActivity);
            paramString1.printStackTrace();
            return "0";
        }
        return paramString3;
    }

    public static Message ConnForMsg(String paramString1, String paramString2, String paramString3, int paramInt, List<String> paramList, List<Object> paramList1)
            throws IOException
    {
        String str = paramString1;
        if (paramString3.equals("GET")) {
            str = paramString1 + "?" + paramString2;
        }
        paramString1 = (HttpURLConnection)new URL(str).openConnection();
        paramString1.setRequestMethod(paramString3);
        int i = 0;
        for (;;)
        {
            if (i >= paramInt)
            {
                if (paramString3.equals("POST"))
                {
                    paramString1.setRequestProperty("Content-Length", paramString2.length());
                    paramString1.setRequestProperty("Charset", "UTF-8");
                    paramString1.setDoOutput(true);
                    paramString3 = paramString1.getOutputStream();
                    paramString3.write(paramString2.getBytes());
                    paramString3.close();
                }
                if (paramString1.getResponseCode() != 200) {
                    break;
                }
                paramString3 = paramString1.getInputStream();
                paramString2 = StreamTools.readStream(paramString3);
                paramString3.close();
                paramString1.disconnect();
                paramString1 = Message.obtain();
                paramString1.what = 1;
                paramString1.obj = paramString2;
                return paramString1;
            }
            paramString1.setRequestProperty((String)paramList.get(i), (String)paramList1.get(i));
            i += 1;
        }
        paramString3 = paramString1.getInputStream();
        paramString2 = StreamTools.readStream(paramString3);
        paramString3.close();
        paramString1.disconnect();
        paramString1 = Message.obtain();
        paramString1.what = -1;
        paramString1.obj = paramString2;
        return paramString1;
    }

    public static Bitmap downLoadImg(String paramString)
    {
        Object localObject2 = null;
        Object localObject1 = localObject2;
        try
        {
            paramString = (HttpURLConnection)new URL(paramString).openConnection();
            localObject1 = localObject2;
            paramString.setConnectTimeout(6000);
            localObject1 = localObject2;
            paramString.setDoInput(true);
            localObject1 = localObject2;
            paramString.setUseCaches(false);
            localObject1 = localObject2;
            paramString.connect();
            localObject1 = localObject2;
            InputStream localInputStream = paramString.getInputStream();
            localObject1 = localObject2;
            paramString = BitmapFactory.decodeStream(localInputStream);
            localObject1 = paramString;
            localInputStream.close();
            return paramString;
        }
        catch (Exception paramString)
        {
            paramString.printStackTrace();
        }
        return (Bitmap)localObject1;
    }

    private static void showConnFailDialog(Activity paramActivity)
    {
        paramActivity = new AlertDialog.Builder(paramActivity);
        paramActivity.setMessage("���������������������������������������");
        paramActivity.setPositiveButton("������", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
            {
                System.exit(0);
            }
        });
        paramActivity.show();
    }

    public static String upLoadFile(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt, List<String> paramList, List<Object> paramList1)
    {
        String str = paramString1;
        for (;;)
        {
            try
            {
                if (paramString4.equals("GET")) {
                    str = paramString1 + "?" + paramString2;
                }
                paramString1 = (HttpURLConnection)new URL(str).openConnection();
                paramString1.setRequestMethod(paramString4);
                i = 0;
                if (i >= paramInt) {
                    if (paramString4.equals("POST"))
                    {
                        paramString1.setDoOutput(true);
                        paramString1.setUseCaches(false);
                        paramString1.setUseCaches(false);
                        paramString1.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + "*****");
                        paramString4 = paramString1.getOutputStream();
                        paramList = new DataOutputStream(paramString4);
                    }
                }
            }
            catch (IOException paramString1)
            {
                int i;
                continue;
            }
            try
            {
                paramList.writeBytes("--" + "*****" + "\r\n");
                paramList.writeBytes("Content-Disposition: form-data; name=\"file111\";filename=\"chengdu\"" + "\r\n");
                paramList.writeBytes(paramString2);
                paramList.writeBytes("\r\n");
                paramString2 = new FileInputStream(paramString3);
                paramString3 = new byte['��'];
                paramInt = paramString2.read(paramString3);
                if (paramInt == -1)
                {
                    paramList.writeBytes("\r\n");
                    paramString2.close();
                    paramList.writeBytes("--" + "*****" + "--" + "\r\n");
                    paramList.flush();
                    paramString4.close();
                    System.out.println("os���");
                    paramList.close();
                    System.out.println("out���");
                    if (paramString1.getResponseCode() == 200)
                    {
                        paramString2 = paramString1.getInputStream();
                        paramString3 = StreamTools.readStream(paramString2);
                        paramString2.close();
                        System.out.println("is���");
                        if (paramString1 == null) {
                            return paramString3;
                        }
                        paramString1.disconnect();
                        System.out.println("huc���");
                        return paramString3;
                        paramString1.setRequestProperty((String)paramList.get(i), (String)paramList1.get(i));
                        i += 1;
                    }
                }
                else
                {
                    paramList.write(paramString3, 0, paramInt);
                    continue;
                }
                return "0";
            }
            catch (IOException paramString1)
            {
                paramString1.printStackTrace();
                return "0";
            }
        }
        return paramString3;
    }

    public static String upLoadImg(String paramString1, String paramString2, String paramString3, int paramInt, List<String> paramList, List<Object> paramList1)
    {
        String str = paramString1;
        try
        {
            if (paramString3.equals("GET")) {
                str = paramString1 + "?" + paramString2;
            }
            paramString1 = (HttpURLConnection)new URL(str).openConnection();
            paramString1.setRequestMethod(paramString3);
            int i = 0;
            for (;;)
            {
                if (i >= paramInt)
                {
                    if (paramString3.equals("POST"))
                    {
                        paramString1.setDoOutput(true);
                        paramString1.setUseCaches(false);
                        System.out.println(paramString2.getBytes().length);
                        paramString1.setRequestProperty("Content-Length", paramString2.getBytes().length);
                        paramString3 = paramString1.getOutputStream();
                        paramList = new DataOutputStream(paramString3);
                        paramList.write(paramString2.getBytes());
                        paramList.flush();
                        paramString3.close();
                        System.out.println("os���");
                        paramList.close();
                        System.out.println("out���");
                    }
                    if (paramString1.getResponseCode() != 200) {
                        break;
                    }
                    paramString2 = paramString1.getInputStream();
                    paramString3 = StreamTools.readStream(paramString2);
                    paramString2.close();
                    System.out.println("is���");
                    if (paramString1 == null) {
                        return paramString3;
                    }
                    paramString1.disconnect();
                    System.out.println("huc���");
                    return paramString3;
                }
                paramString1.setRequestProperty((String)paramList.get(i), (String)paramList1.get(i));
                i += 1;
            }
            return "0";
        }
        catch (IOException paramString1)
        {
            paramString1.printStackTrace();
            return "0";
        }
        return paramString3;
    }
}
