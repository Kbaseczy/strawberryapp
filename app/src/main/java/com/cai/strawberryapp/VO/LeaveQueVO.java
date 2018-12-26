package com.cai.strawberryapp.VO;

import android.graphics.Bitmap;

public class LeaveQueVO
{
    private String LeaveNum;
    private byte[] QueAudio;
    private String QueAudioPath;
    private String QueAudioTime;
    private String QueContent;
    private String QueId;
    private Bitmap QueImg;
    private String QueImgPath;
    private String QueTime;
    private String QueTitle;
    private String QueUser;

    public String getLeaveNum()
    {
        return this.LeaveNum;
    }

    public byte[] getQueAudio()
    {
        return this.QueAudio;
    }

    public String getQueAudioPath()
    {
        return this.QueAudioPath;
    }

    public String getQueAudioTime()
    {
        return this.QueAudioTime;
    }

    public String getQueContent()
    {
        return this.QueContent;
    }

    public String getQueId()
    {
        return this.QueId;
    }

    public Bitmap getQueImg()
    {
        return this.QueImg;
    }

    public String getQueImgPath()
    {
        return this.QueImgPath;
    }

    public String getQueTime()
    {
        return this.QueTime;
    }

    public String getQueTitle()
    {
        return this.QueTitle;
    }

    public String getQueUser()
    {
        return this.QueUser;
    }

    public void setLeaveNum(String paramString)
    {
        this.LeaveNum = paramString;
    }

    public void setQueAudio(byte[] paramArrayOfByte)
    {
        this.QueAudio = paramArrayOfByte;
    }

    public void setQueAudioPath(String paramString)
    {
        this.QueAudioPath = paramString;
    }

    public void setQueAudioTime(String paramString)
    {
        this.QueAudioTime = paramString;
    }

    public void setQueContent(String paramString)
    {
        this.QueContent = paramString;
    }

    public void setQueId(String paramString)
    {
        this.QueId = paramString;
    }

    public void setQueImg(Bitmap paramBitmap)
    {
        this.QueImg = paramBitmap;
    }

    public void setQueImgPath(String paramString)
    {
        this.QueImgPath = paramString;
    }

    public void setQueTime(String paramString)
    {
        this.QueTime = paramString;
    }

    public void setQueTitle(String paramString)
    {
        this.QueTitle = paramString;
    }

    public void setQueUser(String paramString)
    {
        this.QueUser = paramString;
    }
}
