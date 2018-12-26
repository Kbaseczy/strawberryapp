package com.cai.strawberryapp.VO;

import android.graphics.Bitmap;

public class KnowVO
{
    private String KnowCome;
    private String KnowContent;
    private String KnowId;
    private Bitmap KnowImage;
    private String KnowImagePath;
    private String KnowTime;
    private String KnowTitle;

    public String getKnowCome()
    {
        return this.KnowCome;
    }

    public String getKnowContent()
    {
        return this.KnowContent;
    }

    public String getKnowId()
    {
        return this.KnowId;
    }

    public Bitmap getKnowImage()
    {
        return this.KnowImage;
    }

    public String getKnowImagePath()
    {
        return this.KnowImagePath;
    }

    public String getKnowTime()
    {
        return this.KnowTime;
    }

    public String getKnowTitle()
    {
        return this.KnowTitle;
    }

    public void setKnowCome(String paramString)
    {
        this.KnowCome = paramString;
    }

    public void setKnowContent(String paramString)
    {
        this.KnowContent = paramString;
    }

    public void setKnowId(String paramString)
    {
        this.KnowId = paramString;
    }

    public void setKnowImage(Bitmap paramBitmap)
    {
        this.KnowImage = paramBitmap;
    }

    public void setKnowImagePath(String paramString)
    {
        this.KnowImagePath = paramString;
    }

    public void setKnowTime(String paramString)
    {
        this.KnowTime = paramString;
    }

    public void setKnowTitle(String paramString)
    {
        this.KnowTitle = paramString;
    }
}
