package com.youhu.shareman.shareman.view;

import com.youhu.shareman.shareman.base.BaseView;
import com.youhu.shareman.shareman.model.data.BaseData;
import com.youhu.shareman.shareman.model.data.ProductDetailModel;

/**
 * Created by Touch on 2017/9/25.
 */

public interface ProductDetailView extends BaseView{
    void getProductDetail(BaseData<ProductDetailModel> productDetailModel);
}
