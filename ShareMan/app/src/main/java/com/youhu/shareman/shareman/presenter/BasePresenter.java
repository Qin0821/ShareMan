package com.youhu.shareman.shareman.presenter;

import com.youhu.shareman.shareman.base.BaseView;

/**
 * Created by Touch on 2017/8/7.
 */

public interface BasePresenter {
    /*
    作用：将当前Presenter持有的View在合适的时候给清除掉
     */
    void onCreate();

    void onStart();//暂时没用到

    void onStop();

    void pause();//暂时没用到

    void attachView(BaseView view);
}
