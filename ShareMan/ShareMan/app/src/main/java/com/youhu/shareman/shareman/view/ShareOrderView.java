package com.youhu.shareman.shareman.view;

import com.youhu.shareman.shareman.base.BaseView;
import com.youhu.shareman.shareman.model.data.BaseData;
import com.youhu.shareman.shareman.model.data.NormalModel;
import com.youhu.shareman.shareman.model.data.ShareOrderModel;

import java.util.List;

/**
 * Created by Touch on 2017/9/21.
 */

public interface ShareOrderView extends BaseView {
    void doShareOrder(BaseData<List<ShareOrderModel>> shareOrderData);

    //删除订单
    void doDeleteOrder(NormalModel deleteOrderData);

    //取消订单
    void doCancelOrder(NormalModel cancelOrderData);
}
