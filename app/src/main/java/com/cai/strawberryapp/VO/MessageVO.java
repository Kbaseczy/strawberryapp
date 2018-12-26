package com.cai.strawberryapp.VO;

public class MessageVO
{
    private String MesCome;
    private String MesContent;
    private String MesId;
    private byte[] MesImage;
    private String MesImagePath;
    private String MesTime;
    private String MesTitle;

    public String getMesCome()
    {
        return this.MesCome;
    }

    public String getMesContent()
    {
        return this.MesContent;
    }

    public String getMesId()
    {
        return this.MesId;
    }

    public byte[] getMesImage()
    {
        return this.MesImage;
    }

    public String getMesImagePath()
    {
        return this.MesImagePath;
    }

    public String getMesTime()
    {
        return this.MesTime;
    }

    public String getMesTitle()
    {
        return this.MesTitle;
    }

    public void setMesCome(String paramString)
    {
        this.MesCome = paramString;
    }

    public void setMesContent(String paramString)
    {
        this.MesContent = paramString;
    }

    public void setMesId(String paramString)
    {
        this.MesId = paramString;
    }

    public void setMesImage(byte[] paramArrayOfByte)
    {
        this.MesImage = paramArrayOfByte;
    }

    public void setMesImagePath(String paramString)
    {
        this.MesImagePath = paramString;
    }

    public void setMesTime(String paramString)
    {
        this.MesTime = paramString;
    }

    public void setMesTitle(String paramString)
    {
        this.MesTitle = paramString;
    }
}
