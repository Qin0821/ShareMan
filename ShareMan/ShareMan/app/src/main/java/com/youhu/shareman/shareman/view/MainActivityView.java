package com.youhu.shareman.shareman.view;

import com.youhu.shareman.shareman.base.BaseView;
import com.youhu.shareman.shareman.model.data.BannerModel;
import com.youhu.shareman.shareman.model.data.BaseData;

import java.util.List;

/**
 * Created by Touch on 2017/9/25.
 */

public interface MainActivityView  extends BaseView{
    //主页轮播图
    void doBanner(BaseData<List<BannerModel>> bannerData);
}
