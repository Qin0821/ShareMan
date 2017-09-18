package com.youhu.shareman.shareman.presentercoml;

import android.content.Context;
import android.util.Log;

import com.youhu.shareman.shareman.base.BaseView;
import com.youhu.shareman.shareman.model.constant.DataManager;
import com.youhu.shareman.shareman.model.data.BaseData;
import com.youhu.shareman.shareman.model.data.LoginCodeModel;
import com.youhu.shareman.shareman.presenter.BasePresenter;
import com.youhu.shareman.shareman.ui.view.LoginCodeView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Touch on 2017/9/7.
 */

public class LoginCodePresenter implements BasePresenter {
    private static String Tag="LoginPresenter";
    private Context context;
    LoginCodeView loginCodeView;
    private BaseData<LoginCodeModel> codeModelBaseData;
    private DataManager manager;
    private CompositeSubscription compositeSubscription;

    public LoginCodePresenter(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate() {
        manager = new DataManager(context);
        compositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onStop() {
        if (compositeSubscription.hasSubscriptions()){
            compositeSubscription.unsubscribe();
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void attachView(BaseView view) {
        loginCodeView= (LoginCodeView) view;
    }

    public void dologin(String phoneNumber,String code) {
        compositeSubscription.add(manager.dologin(phoneNumber,code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseData<LoginCodeModel>>() {
                    @Override
                    public void onCompleted() {
                        if (codeModelBaseData != null){
                            loginCodeView.onLoginResult(codeModelBaseData);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(Tag,"获取失败"+e.getMessage());
                    }

                    @Override
                    public void onNext(BaseData<LoginCodeModel> data) {
                        Log.i(Tag,data.toString());
                        codeModelBaseData=data;
                    }
                })
        );
    }

}
