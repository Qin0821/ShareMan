package com.youhu.shareman.shareman.view;

import com.youhu.shareman.shareman.base.BaseView;
import com.youhu.shareman.shareman.model.data.AddressMangerModel;
import com.youhu.shareman.shareman.model.data.BaseData;
import com.youhu.shareman.shareman.model.data.NormalModel;

import java.util.List;

/**
 * Created by Touch on 2017/9/20.
 */

public interface AddressMangerView extends BaseView {
    //获取收货地址
    void doPostDetail(BaseData<List<AddressMangerModel>> addressMangerModel);
    //删除收货地址
    void doDeletePostDetail(NormalModel deleteAddressModel);
}
