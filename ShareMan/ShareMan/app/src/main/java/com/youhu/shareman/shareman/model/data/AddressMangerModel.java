package com.youhu.shareman.shareman.model.data;

/**
 * Created by Touch on 2017/9/20.
 */

public class AddressMangerModel {

    /**
     * consignee_address : 杭州市西湖风景区
     * consignee_name : 小明
     * consignee_tel : 18745683407
     * default_flag : 0
     * del_flag : 0
     * detail_address : 学院路123号
     * id : 12
     * user_id : 1
     */

    private String consignee_address;
    private String consignee_name;
    private String consignee_tel;
    private int default_flag;
    private int del_flag;
    private String detail_address;
    private int id;
    private int user_id;

    public String getConsignee_address() {
        return consignee_address;
    }

    public void setConsignee_address(String consignee_address) {
        this.consignee_address = consignee_address;
    }

    public String getConsignee_name() {
        return consignee_name;
    }

    public void setConsignee_name(String consignee_name) {
        this.consignee_name = consignee_name;
    }

    public String getConsignee_tel() {
        return consignee_tel;
    }

    public void setConsignee_tel(String consignee_tel) {
        this.consignee_tel = consignee_tel;
    }

    public int getDefault_flag() {
        return default_flag;
    }

    public void setDefault_flag(int default_flag) {
        this.default_flag = default_flag;
    }

    public int getDel_flag() {
        return del_flag;
    }

    public void setDel_flag(int del_flag) {
        this.del_flag = del_flag;
    }

    public String getDetail_address() {
        return detail_address;
    }

    public void setDetail_address(String detail_address) {
        this.detail_address = detail_address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
