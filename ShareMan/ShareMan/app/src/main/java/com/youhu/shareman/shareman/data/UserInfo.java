package com.youhu.shareman.shareman.data;

/**
 * Created by Touch on 2017/8/11.
 */

public class UserInfo {
    private int ImageId;
    private String UserInfoText;

    public int getImageId() {
        return ImageId;
    }

    public void setImageId(int imageId) {
        ImageId = imageId;
    }

    public String getUserInfoText() {
        return UserInfoText;
    }

    public void setUserInfoText(String userInfoText) {
        UserInfoText = userInfoText;
    }

    public UserInfo(int imageId, String userInfoText) {
        ImageId = imageId;
        UserInfoText = userInfoText;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "ImageId=" + ImageId +
                ", UserInfoText='" + UserInfoText + '\'' +
                '}';
    }
}
