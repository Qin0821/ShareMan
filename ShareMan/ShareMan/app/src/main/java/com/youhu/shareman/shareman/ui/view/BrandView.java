package com.youhu.shareman.shareman.ui.view;

import com.youhu.shareman.shareman.base.BaseView;
import com.youhu.shareman.shareman.model.data.BaseData;
import com.youhu.shareman.shareman.model.data.BrandModel;

import java.util.List;

/**
 * Created by Touch on 2017/9/15.
 */

public interface BrandView extends BaseView{
    void getBrandData(BaseData<List<BrandModel>> baseData);
}
