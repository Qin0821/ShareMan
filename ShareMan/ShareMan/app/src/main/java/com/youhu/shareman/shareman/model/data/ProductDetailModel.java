package com.youhu.shareman.shareman.model.data;

import java.util.List;

/**
 * Created by Touch on 2017/9/25.
 */

public class ProductDetailModel {


    /**
     * depreciation_count : 0
     * graphics : /image/20170920/6523445551_62.jpg
     * original_price : 6000
     * params : /image/20170920/6523445551_63.jpg
     * productImages : ["/image/20170920/34851258_18.jpg","/image/20170920/3488555758_19.jpg","/image/20170920/364111544_22.jpg","/image/20170920/6523445551_61.jpg"]
     * real_price : 2500
     * tagBean : [{"cover_fir":"/image/20170920/2452254122411_14.jpg","depreciation_count":0,"insurance":2,"introduce_title":"【太空银 16G】","original_price":6000,"real_price":3000},{"cover_fir":"/image/20170920/2452254122411_14.jpg","depreciation_count":0,"insurance":4,"introduce_title":"【腮红金 8G】","original_price":4000,"real_price":3500},{"cover_fir":"/image/20170920/2452254122411_13.jpg","depreciation_count":0,"insurance":4,"introduce_title":"【腮红金 256G】","original_price":3000,"real_price":2500}]
     */

    private int depreciation_count;
    private String graphics;
    private int original_price;
    private String params;
    private int real_price;
    private List<String> productImages;
    private List<TagBeanBean> tagBean;

    public int getDepreciation_count() {
        return depreciation_count;
    }

    public void setDepreciation_count(int depreciation_count) {
        this.depreciation_count = depreciation_count;
    }

    public String getGraphics() {
        return graphics;
    }

    public void setGraphics(String graphics) {
        this.graphics = graphics;
    }

    public int getOriginal_price() {
        return original_price;
    }

    public void setOriginal_price(int original_price) {
        this.original_price = original_price;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public int getReal_price() {
        return real_price;
    }

    public void setReal_price(int real_price) {
        this.real_price = real_price;
    }

    public List<String> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<String> productImages) {
        this.productImages = productImages;
    }

    public List<TagBeanBean> getTagBean() {
        return tagBean;
    }

    public void setTagBean(List<TagBeanBean> tagBean) {
        this.tagBean = tagBean;
    }

    public static class TagBeanBean {
        /**
         * cover_fir : /image/20170920/2452254122411_14.jpg
         * depreciation_count : 0
         * insurance : 2
         * introduce_title : 【太空银 16G】
         * original_price : 6000
         * real_price : 3000
         */

        private String cover_fir;
        private int depreciation_count;
        private int insurance;
        private String introduce_title;
        private int original_price;
        private int real_price;

        public String getCover_fir() {
            return cover_fir;
        }

        public void setCover_fir(String cover_fir) {
            this.cover_fir = cover_fir;
        }

        public int getDepreciation_count() {
            return depreciation_count;
        }

        public void setDepreciation_count(int depreciation_count) {
            this.depreciation_count = depreciation_count;
        }

        public int getInsurance() {
            return insurance;
        }

        public void setInsurance(int insurance) {
            this.insurance = insurance;
        }

        public String getIntroduce_title() {
            return introduce_title;
        }

        public void setIntroduce_title(String introduce_title) {
            this.introduce_title = introduce_title;
        }

        public int getOriginal_price() {
            return original_price;
        }

        public void setOriginal_price(int original_price) {
            this.original_price = original_price;
        }

        public int getReal_price() {
            return real_price;
        }

        public void setReal_price(int real_price) {
            this.real_price = real_price;
        }
    }

    @Override
    public String toString() {
        return "ProductDetailModel{" +
                "depreciation_count=" + depreciation_count +
                ", graphics='" + graphics + '\'' +
                ", original_price=" + original_price +
                ", params='" + params + '\'' +
                ", real_price=" + real_price +
                ", productImages=" + productImages +
                ", tagBean=" + tagBean +
                '}';
    }
}
