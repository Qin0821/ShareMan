package com.youhu.shareman.shareman.ui.view;

/**
 * Created by Touch on 2017/9/15.
 */

import com.youhu.shareman.shareman.base.BaseView;
import com.youhu.shareman.shareman.model.data.BaseData;
import com.youhu.shareman.shareman.model.data.LoginCodeModel;

/**
 * 这是验证码界面要实现的方法类
 */
public interface LoginCodeView extends BaseView {

    void onLoginResult(BaseData<LoginCodeModel> data);

}
