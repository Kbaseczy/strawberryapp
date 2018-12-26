package com.jack.strawberry.vo;


public class KnowVO {

    private String KnowId;
    private int KnowImage;
    private String KnowTime;
    private String KnowTitle;
    private String KnowLinkUrl;

    public String getKnowId() {
        return this.KnowId;
    }

    public int getKnowImage() {
        return this.KnowImage;
    }


    public String getKnowTime() {
        return this.KnowTime;
    }

    public String getKnowTitle() {
        return this.KnowTitle;
    }


    public void setKnowId(String paramString) {
        this.KnowId = paramString;
    }

    public void setKnowImage(int knowImage) {
        this.KnowImage = knowImage;
    }


    public void setKnowTime(String paramString) {
        this.KnowTime = paramString;
    }

    public void setKnowTitle(String paramString) {
        this.KnowTitle = paramString;
    }

    public String getKnowLinkUrl() {
        return KnowLinkUrl;
    }

    public void setKnowLinkUrl(String knowLinkUrl) {
        KnowLinkUrl = knowLinkUrl;
    }
}
