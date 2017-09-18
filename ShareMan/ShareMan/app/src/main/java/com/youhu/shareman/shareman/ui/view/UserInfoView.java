package com.youhu.shareman.shareman.ui.view;

import com.youhu.shareman.shareman.base.BaseView;
import com.youhu.shareman.shareman.model.data.NormalData;

/**
 * Created by Touch on 2017/9/18.
 */

public interface UserInfoView extends BaseView {
    //修改昵称的方法
    void doChangeNickname(NormalData nickNameData);

    //修改性别的方法
    void doChangeSex(NormalData sexData);
}
