package com.youhu.shareman.shareman.model.data;

/**
 * Created by Touch on 2017/9/20.
 */

public class NewNumberLoginCodeModel {
    private String newPhoneNumber;
    private String token;

    public String getNewPhoneNumber() {
        return newPhoneNumber;
    }

    public void setNewPhoneNumber(String newPhoneNumber) {
        this.newPhoneNumber = newPhoneNumber;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    @Override
    public String toString() {
        return "NewNumberLoginCodeModel{" +
                "newPhoneNumber='" + newPhoneNumber + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
