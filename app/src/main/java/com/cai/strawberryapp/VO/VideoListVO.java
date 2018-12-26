package com.cai.strawberryapp.VO;

public class VideoListVO
{
    private String VideoId;
    private byte[] VideoImage;
    private String VideoImagePath;
    private String VideoPath;
    private String VideoTime;
    private String VideoTitle;
    private String VideoUser;

    public String getVideoId()
    {
        return this.VideoId;
    }

    public byte[] getVideoImage()
    {
        return this.VideoImage;
    }

    public String getVideoImagePath()
    {
        return this.VideoImagePath;
    }

    public String getVideoPath()
    {
        return this.VideoPath;
    }

    public String getVideoTime()
    {
        return this.VideoTime;
    }

    public String getVideoTitle()
    {
        return this.VideoTitle;
    }

    public String getVideoUser()
    {
        return this.VideoUser;
    }

    public void setVideoId(String paramString)
    {
        this.VideoId = paramString;
    }

    public void setVideoImage(byte[] paramArrayOfByte)
    {
        this.VideoImage = paramArrayOfByte;
    }

    public void setVideoImagePath(String paramString)
    {
        this.VideoImagePath = paramString;
    }

    public void setVideoPath(String paramString)
    {
        this.VideoPath = paramString;
    }

    public void setVideoTime(String paramString)
    {
        this.VideoTime = paramString;
    }

    public void setVideoTitle(String paramString)
    {
        this.VideoTitle = paramString;
    }

    public void setVideoUser(String paramString)
    {
        this.VideoUser = paramString;
    }
}
