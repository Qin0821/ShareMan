package com.youhu.shareman.shareman.model.data;

/**
 * Created by Touch on 2017/9/27.
 */

public class VoucherModel {


    /**
     * amount : 68
     * endDate : 2018-9-20
     * startDate : 2017-09-20
     */

    private int amount;
    private String endDate;
    private String startDate;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }


    @Override
    public String toString() {
        return "VoucherModel{" +
                "amount=" + amount +
                ", endDate='" + endDate + '\'' +
                ", startDate='" + startDate + '\'' +
                '}';
    }
}
