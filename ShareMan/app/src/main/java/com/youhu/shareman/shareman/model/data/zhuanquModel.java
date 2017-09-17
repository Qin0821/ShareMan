package com.youhu.shareman.shareman.model.data;

import java.util.List;

/**
 * Created by Touch on 2017/9/16.
 */

public class zhuanquModel {

    /**
     * code : 0
     * data : [{"bot_show":"/image/topBotShow/352ad90a-b633-4606-84ed-a725ea9fb60c.jpg","top_show":"/image/topBotShow/4f7c4a7f-54fc-41ac-8847-a85d07e0d951.jpg","version":"oppo r11"},{"bot_show":"/image/topBotShow/24884727-4b57-4aa2-af04-1ed60d0b0733.jpg","top_show":"/image/topBotShow/75dcb3ce-c07f-498f-8a0f-216986b294b6.jpg","version":"oppo r9s"}]
     * message : success
     */

    private String code;
    private String message;
    private List<DataBean> data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
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
            return "DataBean{" +
                    "bot_show='" + bot_show + '\'' +
                    ", top_show='" + top_show + '\'' +
                    ", version='" + version + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "zhuanquModel{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }


}
