package com.youhu.shareman.shareman.model.data;

/**
 * Created by Touch on 2017/9/27.
 */

public class ChooseVoucherModel {


    /**
     * amount : 68
     * totalPrice : 2134
     */

    private int amount;
    private int totalPrice;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "ChooseVoucherModel{" +
                "amount=" + amount +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
