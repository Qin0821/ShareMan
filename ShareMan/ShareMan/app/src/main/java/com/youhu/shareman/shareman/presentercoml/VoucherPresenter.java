package com.youhu.shareman.shareman.presentercoml;

import android.content.Context;
import android.util.Log;

import com.youhu.shareman.shareman.base.BaseView;
import com.youhu.shareman.shareman.model.constant.DataManager;
import com.youhu.shareman.shareman.model.data.BaseData;
import com.youhu.shareman.shareman.model.data.VoucherModel;
import com.youhu.shareman.shareman.presenter.BasePresenter;
import com.youhu.shareman.shareman.view.VoucherView;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Touch on 2017/9/27.
 */

public class VoucherPresenter implements BasePresenter {

    private static String Tag="VoucherPresenter";
    private Context context;
    private DataManager manager;
    private BaseData<List<VoucherModel>> voucherModel;
    private VoucherView voucherView;
    private CompositeSubscription compositeSubscription;

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
        voucherView= (VoucherView) view;
    }


    public void getVoucher(String phoneNumber,String token){
        compositeSubscription.add(manager.getVoucher(phoneNumber,token)
                //事件消费在主线程
                .observeOn(AndroidSchedulers.mainThread())
                //事件消费在子线程
                .subscribeOn(Schedulers.io())
                //指定一个观察者
                .subscribe(new Observer<BaseData<List<VoucherModel>>>() {
                    @Override
                    public void onCompleted() {
                        if(voucherModel!=null){
                            voucherView.doGetVoucher(voucherModel);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(Tag,"获取失败"+e.getMessage());
                    }

                    @Override
                    public void onNext(BaseData<List<VoucherModel>> voucherData) {
                        Log.i(Tag,voucherData.getData().toString());
                        voucherModel=voucherData;
                    }
                })
        );
    }

}
