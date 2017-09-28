package com.youhu.shareman.shareman.view;

import com.youhu.shareman.shareman.base.BaseView;
import com.youhu.shareman.shareman.model.data.BaseData;
import com.youhu.shareman.shareman.model.data.ChooseVoucherModel;
import com.youhu.shareman.shareman.model.data.VoucherModel;

import java.util.List;

/**
 * Created by Touch on 2017/9/27.
 */

public interface VoucherView extends BaseView {
    //获取优惠券
    void doGetVoucher(BaseData<List<VoucherModel>> voucherData);
    //使用优惠券
    void doUseVoucher(BaseData<ChooseVoucherModel> useVoucherData);
}
