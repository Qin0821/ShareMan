package com.youhu.shareman.shareman.presenter;

/**
 * Created by Touch on 2017/9/6.
 */

public interface ILoginCodePresenter  {

    //获取验证码
    public void doGetCode(String phoneNumber,String code);
}
