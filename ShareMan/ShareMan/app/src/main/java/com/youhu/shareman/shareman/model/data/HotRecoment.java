package com.youhu.shareman.shareman.model.data;

/**
 * Created by Touch on 2017/9/26.
 */

public class HotRecoment {


    /**
     * category_url : /image/seal/seal.jpg
     * version : OPPO R11
     */

    private String category_url;
    private String version;

    public String getCategory_url() {
        return category_url;
    }

    public void setCategory_url(String category_url) {
        this.category_url = category_url;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "HotRecoment{" +
                "category_url='" + category_url + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
