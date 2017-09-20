package com.youhu.shareman.shareman.ui.view;

import com.youhu.shareman.shareman.base.BaseView;
import com.youhu.shareman.shareman.model.data.AddressMangerModel;
import com.youhu.shareman.shareman.model.data.BaseData;

import java.util.List;

/**
 * Created by Touch on 2017/9/20.
 */

public interface AddressMangerView extends BaseView {
    void doPostDetail(BaseData<List<AddressMangerModel>> addressMangerModel);
}
