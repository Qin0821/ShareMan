package com.youhu.shareman.shareman.ui.view;

import com.youhu.shareman.shareman.base.BaseView;
import com.youhu.shareman.shareman.model.data.BaseData;
import com.youhu.shareman.shareman.model.data.LoginCodeModel;
import com.youhu.shareman.shareman.model.data.NormalModel;

/**
 * Created by Touch on 2017/9/19.
 */

public interface NewPhoneNumberView extends BaseView {

    //修改手机号码的方法
    void doChangePhoneNumber(NormalModel phoneNumberData);

    //新手机号码的验证
    void doNewPhoneNumber(BaseData<LoginCodeModel> newPhoneNumberData);
}
