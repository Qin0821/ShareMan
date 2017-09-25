package com.youhu.shareman.shareman.model.data;

/**
 * Created by Touch on 2017/9/21.
 */

public class ShareOrderModel {

    /**
     * address : 杭州市犄角旮旯
     * apply_date : 2017-09-10
     * id_card_no : 1234
     * name : 王小麻子
     * order_id : 2
     * price : 7000
     * product_id : 9
     * version : oppo r11
     */

    private String address;
    private String apply_date;
    private String id_card_no;
    private String name;
    private int order_id;
    private int price;
    private int product_id;
    private String version;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getApply_date() {
        return apply_date;
    }

    public void setApply_date(String apply_date) {
        this.apply_date = apply_date;
    }

    public String getId_card_no() {
        return id_card_no;
    }

    public void setId_card_no(String id_card_no) {
        this.id_card_no = id_card_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "ShareOrderModel{" +
                "address='" + address + '\'' +
                ", apply_date='" + apply_date + '\'' +
                ", id_card_no='" + id_card_no + '\'' +
                ", name='" + name + '\'' +
                ", order_id=" + order_id +
                ", price=" + price +
                ", product_id=" + product_id +
                ", version='" + version + '\'' +
                '}';
    }
}
