package com.cai.strawberryapp.BaseClass;

import java.util.UUID;

public class IDUtil
{
    public static String getID()
    {
        return UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
    }
}
