package com.cai.strawberryapp.BaseClass;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonUtil
{
    private static final Gson g = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy[] { myExclusionStrategy }).create();
    private static ExclusionStrategy myExclusionStrategy = new ExclusionStrategy()
    {
        public boolean shouldSkipClass(Class<?> paramAnonymousClass)
        {
            return false;
        }

        public boolean shouldSkipField(FieldAttributes paramAnonymousFieldAttributes)
        {
            return paramAnonymousFieldAttributes.getName().startsWith("log");
        }
    };

    public static Gson getGson()
    {
        return g;
    }

    public static void main(String[] paramArrayOfString)
    {
        paramArrayOfString = new HashMap();
        ArrayList localArrayList = new ArrayList();
        HashMap localHashMap = new HashMap();
        localHashMap.put("id", Integer.valueOf(12313));
        localHashMap.put("name", "afadfdf");
        localArrayList.add(localHashMap);
        paramArrayOfString.put("list", localArrayList);
        paramArrayOfString.put("num", "212");
        paramArrayOfString.put("ret", "success");
        paramArrayOfString = g.toJson(paramArrayOfString);
        System.out.println(paramArrayOfString);
        paramArrayOfString = (Map)g.fromJson(paramArrayOfString, new TypeToken() {}.getType());
        paramArrayOfString = (Map)g.fromJson(paramArrayOfString.toString(), new TypeToken() {}.getType());
        System.out.println(paramArrayOfString);
        System.out.println("success".equals(paramArrayOfString.get("ret")));
    }
}
