package com.youhu.shareman.shareman.model.data;

/**
 * Created by Touch on 2017/9/14.
 */

public class LoginCodeModel {
    private String phoneNumber;
    private String token;

    public LoginCodeModel(String phoneNumber, String token) {
        this.phoneNumber = phoneNumber;
        this.token = token;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "LoginCodeModel{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
