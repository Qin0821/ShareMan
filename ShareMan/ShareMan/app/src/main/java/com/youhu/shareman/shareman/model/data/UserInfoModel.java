package com.youhu.shareman.shareman.model.data;

/**
 * Created by Touch on 2017/9/25.
 */

public class UserInfoModel {


    /**
     * gender : 1
     * nickname : 碧萝
     * phone_number : 15701236749
     * portrait : 头像
     */

    private int gender;
    private String nickname;
    private String phone_number;
    private String portrait;

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    @Override
    public String toString() {
        return "UserInfoModel{" +
                "gender=" + gender +
                ", nickname='" + nickname + '\'' +
                ", phone_number='" + phone_number + '\'' +
                ", portrait='" + portrait + '\'' +
                '}';
    }
}
