package com.youhu.shareman.shareman.model.data;

/**
 * Created by Touch on 2017/9/18.
 */

public class NormalModel {

    /**
     * code : 0
     * message : success
     */

    private String code;
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "NormalModel{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
