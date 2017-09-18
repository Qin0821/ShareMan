package com.youhu.shareman.shareman.model.data;

/**
 * Created by Touch on 2017/9/18.
 */

public class NormalData {

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
        return "NormalData{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
