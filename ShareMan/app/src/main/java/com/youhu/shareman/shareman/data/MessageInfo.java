package com.youhu.shareman.shareman.data;

/**
 * Created by Touch on 2017/9/2.
 */

public class MessageInfo {
    private String detailText;
    private String linkText;

    public MessageInfo(String detailText, String linkText) {
        this.detailText = detailText;
        this.linkText = linkText;
    }

    public String getDetailText() {
        return detailText;
    }

    public void setDetailText(String detailText) {
        this.detailText = detailText;
    }

    public String getLinkText() {
        return linkText;
    }

    public void setLinkText(String linkText) {
        this.linkText = linkText;
    }

    @Override
    public String toString() {
        return "MessageInfo{" +
                "detailText=" + detailText +
                ", linkText=" + linkText +
                '}';
    }
}
