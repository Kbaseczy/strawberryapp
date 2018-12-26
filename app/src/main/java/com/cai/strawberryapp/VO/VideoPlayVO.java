package com.cai.strawberryapp.VO;

public class VideoPlayVO
{
    private String VideoId;
    private String VideoPath;
    private String VideoTitle;

    public String getVideoId()
    {
        return this.VideoId;
    }

    public String getVideoPath()
    {
        return this.VideoPath;
    }

    public String getVideoTitle()
    {
        return this.VideoTitle;
    }

    public void setVideoId(String paramString)
    {
        this.VideoId = paramString;
    }

    public void setVideoPath(String paramString)
    {
        this.VideoPath = paramString;
    }

    public void setVideoTitle(String paramString)
    {
        this.VideoTitle = paramString;
    }
}
