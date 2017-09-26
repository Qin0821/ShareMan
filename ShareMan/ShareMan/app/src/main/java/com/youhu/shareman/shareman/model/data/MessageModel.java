package com.youhu.shareman.shareman.model.data;

/**
 * Created by Touch on 2017/9/26.
 */

public class MessageModel {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "MessageModel{" +
                "message='" + message + '\'' +
                '}';
    }
}
