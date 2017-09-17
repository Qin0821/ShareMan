package com.youhu.shareman.shareman.model.data;



/**
 * 类描述：Http 请求基础类
 * 创建人：Simple
 * 创建时间：2016/11/5
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class BaseData<T> {

    private int code;

    private String message;

    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseData{" +
                "errcode=" + code +
                ", errmsg='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
