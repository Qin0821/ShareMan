package com.youhu.shareman.shareman.view;

import com.youhu.shareman.shareman.base.BaseView;
import com.youhu.shareman.shareman.model.data.BaseData;
import com.youhu.shareman.shareman.model.data.PayWayModel;

/**
 * Created by Touch on 2017/9/27.
 */

public interface PaymentWayView extends BaseView {

    //显示优惠券和价格
    void doGetPayPrice(BaseData<PayWayModel> payData);

    //拉取支付宝信息
    void doGetOrderInfo(BaseData<String> orderInfoData);
}
