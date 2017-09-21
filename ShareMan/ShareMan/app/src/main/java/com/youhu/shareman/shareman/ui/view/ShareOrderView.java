package com.youhu.shareman.shareman.ui.view;

import com.youhu.shareman.shareman.base.BaseView;
import com.youhu.shareman.shareman.model.data.BaseData;
import com.youhu.shareman.shareman.model.data.ShareOrderModel;

import java.util.List;

/**
 * Created by Touch on 2017/9/21.
 */

public interface ShareOrderView extends BaseView {
    void doShareOrder(BaseData<List<ShareOrderModel>> shareOrderData);
}
