package com.youhu.shareman.shareman.data;

/**
 * Created by Touch on 2017/8/18.
 */

public class VoucherInfo {
    private int ImageId;
    private String voucherTime;

    public VoucherInfo(int imageId, String voucherTime) {
        ImageId = imageId;
        this.voucherTime = voucherTime;
    }

    public int getImageId() {
        return ImageId;
    }

    public void setImageId(int imageId) {
        ImageId = imageId;
    }

    public String getVoucherTime() {
        return voucherTime;
    }

    public void setVoucherTime(String voucherTime) {
        this.voucherTime = voucherTime;
    }

    @Override
    public String toString() {
        return "VoucherInfo{" +
                "ImageId=" + ImageId +
                ", voucherTime='" + voucherTime + '\'' +
                '}';
    }
}
