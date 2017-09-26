package com.youhu.shareman.shareman.view;

import com.youhu.shareman.shareman.base.BaseView;
import com.youhu.shareman.shareman.model.data.BaseData;
import com.youhu.shareman.shareman.model.data.VoucherModel;

import java.util.List;

/**
 * Created by Touch on 2017/9/27.
 */

public interface VoucherView extends BaseView {
    void doGetVoucher(BaseData<List<VoucherModel>> voucherData);
}
