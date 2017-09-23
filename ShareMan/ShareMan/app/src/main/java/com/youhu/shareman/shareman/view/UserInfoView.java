package com.youhu.shareman.shareman.view;

import com.youhu.shareman.shareman.base.BaseView;
import com.youhu.shareman.shareman.model.data.NormalModel;

/**
 * Created by Touch on 2017/9/18.
 */

public interface UserInfoView extends BaseView {
    //修改昵称的方法
    void doChangeNickname(NormalModel nickNameData);

    //修改性别的方法
    void doChangeSex(NormalModel sexData);

    //修改头像的方法
    void doChangeUserImage(NormalModel userImageData);

}
