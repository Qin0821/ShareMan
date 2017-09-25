package com.youhu.shareman.shareman.data;

/**
 * Created by Touch on 2017/8/11.
 */

public class HotRecomentInfo {

    //暂时用本地资源充当假数据,所以用int，后面使用String记录图片的URL
//    private Url imageUrl;
//
//    public HotRecomentInfo(Url imageUrl) {
//        this.imageUrl = imageUrl;
//    }
//
//    public Url getImageUrl() {
//        return imageUrl;
//    }
//
//    public void setImageUrl(Url imageUrl) {
//        this.imageUrl = imageUrl;
//    }

    private int imageUrl;

    public HotRecomentInfo(int imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(int imageUrl) {
        this.imageUrl = imageUrl;
    }
}
