package com.youhu.shareman.shareman.data;

/**
 * Created by Touch on 2017/8/23.
 */

public class ProductDetailInfo {
    private int imageId;

    public ProductDetailInfo(int imageId) {
        this.imageId = imageId;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    @Override
    public String toString() {
        return "ProductDetailInfo{" +
                "imageId=" + imageId +
                '}';
    }
}
