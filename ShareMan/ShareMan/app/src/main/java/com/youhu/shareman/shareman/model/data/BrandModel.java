package com.youhu.shareman.shareman.model.data;

/**
 * Created by Touch on 2017/9/15.
 */

public class BrandModel {

    /**
     * bot_show : /image/topBotShow/352ad90a-b633-4606-84ed-a725ea9fb60c.jpg
     * top_show : /image/topBotShow/4f7c4a7f-54fc-41ac-8847-a85d07e0d951.jpg
     * version : oppo r11
     */

    private String bot_show;
    private String top_show;
    private String version;

    public String getBot_show() {
        return bot_show;
    }

    public void setBot_show(String bot_show) {
        this.bot_show = bot_show;
    }

    public String getTop_show() {
        return top_show;
    }

    public void setTop_show(String top_show) {
        this.top_show = top_show;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "BrandModel{" +
                "bot_show='" + bot_show + '\'' +
                ", top_show='" + top_show + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
