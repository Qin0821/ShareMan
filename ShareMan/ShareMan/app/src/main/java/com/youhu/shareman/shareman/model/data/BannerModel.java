package com.youhu.shareman.shareman.model.data;

/**
 * Created by Touch on 2017/9/25.
 */

public class BannerModel {

    /**
     * carousel : /image/carousel/44d44f0f-ade7-489f-b4e2-67fba397bfc0.jpg
     * version : oppo r9s
     */

    private String carousel;
    private String version;

    public String getCarousel() {
        return carousel;
    }

    public void setCarousel(String carousel) {
        this.carousel = carousel;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "BannerModel{" +
                "carousel='" + carousel + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
