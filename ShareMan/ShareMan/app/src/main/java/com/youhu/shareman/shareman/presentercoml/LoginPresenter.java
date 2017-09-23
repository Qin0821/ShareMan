package com.youhu.shareman.shareman.presentercoml;


import android.content.Context;
import android.util.Log;

import com.youhu.shareman.shareman.base.BaseView;
import com.youhu.shareman.shareman.model.constant.DataManager;
import com.youhu.shareman.shareman.model.data.BaseData;
import com.youhu.shareman.shareman.presenter.BasePresenter;
import com.youhu.shareman.shareman.view.LoginView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Touch on 2017/9/6.
 */

public class LoginPresenter implements BasePresenter {
    private static String Tag="LoginPresenter";
    private Context context;
    private DataManager manager;
    private BaseData<String> codeModel;
    private LoginView loginView;
    private CompositeSubscription compositeSubscription;

    public LoginPresenter(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate() {
        manager=new DataManager(context);
        compositeSubscription=new CompositeSubscription();
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
        loginView= (LoginView) view;
    }

    public void getCode(String phoneNumber){
        compositeSubscription.add(manager.getCode(phoneNumber)
                //事件消费在主线程
                .observeOn(AndroidSchedulers.mainThread())
                //事件消费在子线程
                .subscribeOn(Schedulers.newThread())
                //指定一个观察者
                .subscribe(new Observer<BaseData<String>>() {
                    @Override
                    public void onCompleted() {
                        if(codeModel!=null){
                            loginView.onSuccess(codeModel.getMessage());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(Tag,"获取失败"+e.getMessage());
                    }

                    @Override
                    public void onNext(BaseData<String> codeModelBaseData) {
                        Log.i(Tag,codeModelBaseData.toString());
                        codeModel=codeModelBaseData;
                    }
                })
        );
    }
}
