package com.youhu.shareman.shareman.model.data;

/**
 * Created by Touch on 2017/9/27.
 */

public class ZhimaModel {


    /**
     * totalPrice : 1
     * count : 2
     */

    private int totalPrice;
    private int count;

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "ZhimaModel{" +
                "totalPrice=" + totalPrice +
                ", count=" + count +
                '}';
    }
}
