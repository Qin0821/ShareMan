package com.youhu.shareman.shareman.view;

import com.youhu.shareman.shareman.base.BaseView;
import com.youhu.shareman.shareman.model.data.BaseData;
import com.youhu.shareman.shareman.model.data.NewNumberLoginCodeModel;

/**
 * Created by Touch on 2017/9/19.
 */

public interface NewPhoneNumberView extends BaseView {

    //修改手机号码的方法
    void doChangePhoneNumber(BaseData<String> phoneNumberData);

    //新手机号码的验证
    void doNewPhoneNumber(BaseData<NewNumberLoginCodeModel> newPhoneNumberData);
}
