package com.youhu.shareman.shareman.model.data;

/**
 * Created by Touch on 2017/9/25.
 */

public class InformationModel {


    /**
     * address : 杭州市气死你区气死你路气死你家园7栋741号
     * company : 气死你有限公司
     * id_card_a : /image/userInfo/f9c3b158-8a84-4dac-b8fc-bf8bcb1ffc3a.jpg
     * id_card_b : /image/userInfo/f49e946a-2794-4d91-a0fe-c42535358e36.jpg
     * id_card_no : 7474741747474174
     * id_card_on_hand : /image/userInfo/4743e042-f37c-4c97-9170-3f60991dd4e5.jpg
     * name : 王柏松
     * service_password : 741741
     * student_id_card : /image/userInfo/d22d8f70-c35a-4d47-a63f-56fffd79e3fe.jpg
     */

    private String address;
    private String company;
    private String id_card_a;
    private String id_card_b;
    private String id_card_no;
    private String id_card_on_hand;
    private String name;
    private String service_password;
    private String student_id_card;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getId_card_a() {
        return id_card_a;
    }

    public void setId_card_a(String id_card_a) {
        this.id_card_a = id_card_a;
    }

    public String getId_card_b() {
        return id_card_b;
    }

    public void setId_card_b(String id_card_b) {
        this.id_card_b = id_card_b;
    }

    public String getId_card_no() {
        return id_card_no;
    }

    public void setId_card_no(String id_card_no) {
        this.id_card_no = id_card_no;
    }

    public String getId_card_on_hand() {
        return id_card_on_hand;
    }

    public void setId_card_on_hand(String id_card_on_hand) {
        this.id_card_on_hand = id_card_on_hand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getService_password() {
        return service_password;
    }

    public void setService_password(String service_password) {
        this.service_password = service_password;
    }

    public String getStudent_id_card() {
        return student_id_card;
    }

    public void setStudent_id_card(String student_id_card) {
        this.student_id_card = student_id_card;
    }


    @Override
    public String toString() {
        return "InformationModel{" +
                "address='" + address + '\'' +
                ", company='" + company + '\'' +
                ", id_card_a='" + id_card_a + '\'' +
                ", id_card_b='" + id_card_b + '\'' +
                ", id_card_no='" + id_card_no + '\'' +
                ", id_card_on_hand='" + id_card_on_hand + '\'' +
                ", name='" + name + '\'' +
                ", service_password='" + service_password + '\'' +
                ", student_id_card='" + student_id_card + '\'' +
                '}';
    }
}
