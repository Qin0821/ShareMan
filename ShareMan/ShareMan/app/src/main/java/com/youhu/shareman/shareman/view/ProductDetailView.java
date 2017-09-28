package com.youhu.shareman.shareman.view;

import com.youhu.shareman.shareman.base.BaseView;
import com.youhu.shareman.shareman.model.data.BaseData;
import com.youhu.shareman.shareman.model.data.NormalModel;
import com.youhu.shareman.shareman.model.data.ProductDetailModel;
import com.youhu.shareman.shareman.model.data.ZhimaModel;

/**
 * Created by Touch on 2017/9/25.
 */

public interface ProductDetailView extends BaseView{
    //获取详情
    void getProductDetail(BaseData<ProductDetailModel> productDetailModel);

    //开始预约
    void doStartBooking(NormalModel startData);

    //芝麻信用
    void doGetZhima(BaseData<ZhimaModel> zhimaData);
}
