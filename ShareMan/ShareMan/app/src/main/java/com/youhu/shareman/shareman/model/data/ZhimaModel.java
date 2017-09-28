package com.youhu.shareman.shareman.model.data;

/**
 * Created by Touch on 2017/9/27.
 */

public class ZhimaModel {


    /**
     * zmxy_score : Y
     * hygz_level : 0
     */

    private String zmxy_score;
    private String hygz_level;

    public String getZmxy_score() {
        return zmxy_score;
    }

    public void setZmxy_score(String zmxy_score) {
        this.zmxy_score = zmxy_score;
    }

    public String getHygz_level() {
        return hygz_level;
    }

    public void setHygz_level(String hygz_level) {
        this.hygz_level = hygz_level;
    }

    @Override
    public String toString() {
        return "ZhimaModel{" +
                "zmxy_score='" + zmxy_score + '\'' +
                ", hygz_level='" + hygz_level + '\'' +
                '}';
    }
}
