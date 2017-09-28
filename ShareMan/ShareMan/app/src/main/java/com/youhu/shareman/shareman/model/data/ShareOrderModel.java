package com.youhu.shareman.shareman.model.data;

/**
 * Created by Touch on 2017/9/21.
 */

public class ShareOrderModel {


    /**
     * address : 杭州市气死你区气死你路气死你家园7栋741号
     * cancel_time : 2017-09-22 18:40:25
     * color_name : 太空银
     * create_time : 2017-09-22 18:40:25
     * id_card_no : 7474741747474174
     * imei : 1113332211321
     * memory : 16
     * name : 王柏松
     * order_id : 1
     * real_price : 3000
     * sign_date_a : 2017-09-22 18:40:25
     * status : 0
     * version : OPPO R11
     */

    private String address;
    private String cancel_time;
    private String color_name;
    private String create_time;
    private String id_card_no;
    private String imei;
    private int memory;
    private String name;
    private int order_id;
    private int real_price;
    private String sign_date_a;
    private int status;
    private String version;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCancel_time() {
        return cancel_time;
    }

    public void setCancel_time(String cancel_time) {
        this.cancel_time = cancel_time;
    }

    public String getColor_name() {
        return color_name;
    }

    public void setColor_name(String color_name) {
        this.color_name = color_name;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getId_card_no() {
        return id_card_no;
    }

    public void setId_card_no(String id_card_no) {
        this.id_card_no = id_card_no;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
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

    public int getReal_price() {
        return real_price;
    }

    public void setReal_price(int real_price) {
        this.real_price = real_price;
    }

    public String getSign_date_a() {
        return sign_date_a;
    }

    public void setSign_date_a(String sign_date_a) {
        this.sign_date_a = sign_date_a;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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
                ", cancel_time='" + cancel_time + '\'' +
                ", color_name='" + color_name + '\'' +
                ", create_time='" + create_time + '\'' +
                ", id_card_no='" + id_card_no + '\'' +
                ", imei='" + imei + '\'' +
                ", memory=" + memory +
                ", name='" + name + '\'' +
                ", order_id=" + order_id +
                ", real_price=" + real_price +
                ", sign_date_a='" + sign_date_a + '\'' +
                ", status=" + status +
                ", version='" + version + '\'' +
                '}';
    }
}
