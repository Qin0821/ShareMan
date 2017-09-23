package com.youhu.shareman.shareman.presentercoml;

import android.content.Context;
import android.util.Log;

import com.youhu.shareman.shareman.base.BaseView;
import com.youhu.shareman.shareman.model.constant.DataManager;
import com.youhu.shareman.shareman.model.data.BaseData;
import com.youhu.shareman.shareman.model.data.ShareOrderModel;
import com.youhu.shareman.shareman.presenter.BasePresenter;
import com.youhu.shareman.shareman.view.ShareOrderView;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Touch on 2017/9/21.
 */

public class ShareOrderPresenter implements BasePresenter {

    private static String Tag="ShareOrderPresenter";
    private Context context;
    private DataManager manager;
    private BaseData<List<ShareOrderModel>> shareOrderModel;
    private ShareOrderView shareOrderView;
    private CompositeSubscription compositeSubscription;

    @Override
    public void onCreate() {

    }

    @Override
    public void onStart() {
        manager=new DataManager(context);
        compositeSubscription=new CompositeSubscription();
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
        shareOrderView= (ShareOrderView) view;
    }


    public void getShareOrder(String phoneNumber, String token, int status){
        compositeSubscription.add(manager.getShareOrder(phoneNumber,token,status)
                //事件消费在主线程
                .observeOn(AndroidSchedulers.mainThread())
                //事件消费在子线程
                .subscribeOn(Schedulers.io())
                //指定一个观察者
                .subscribe(new Observer<BaseData<List<ShareOrderModel>>>() {
                    @Override
                    public void onCompleted() {
                        if(shareOrderModel!=null){
                            shareOrderView.doShareOrder(shareOrderModel);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(Tag,"获取失败"+e.getMessage());
                    }

                    @Override
                    public void onNext(BaseData<List<ShareOrderModel>> shareOrderData) {
                        Log.i(Tag,shareOrderData.getData().toString());
                        shareOrderModel=shareOrderData;
                    }
                })

        );
    }
}
