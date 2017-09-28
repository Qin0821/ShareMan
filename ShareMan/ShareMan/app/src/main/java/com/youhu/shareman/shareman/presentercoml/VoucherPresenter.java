package com.youhu.shareman.shareman.presentercoml;

import android.content.Context;
import android.util.Log;

import com.youhu.shareman.shareman.base.BaseView;
import com.youhu.shareman.shareman.model.constant.DataManager;
import com.youhu.shareman.shareman.model.data.BaseData;
import com.youhu.shareman.shareman.model.data.ChooseVoucherModel;
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
    private BaseData<ChooseVoucherModel> useVoucherModel;
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


    //使用优惠券
    public void useVoucher(String phoneNumber, String token, String voucherId, String orderId){
        compositeSubscription.add(manager.useVoucher(phoneNumber,token,voucherId,orderId)
                //事件消费在主线程
                .observeOn(AndroidSchedulers.mainThread())
                //事件消费在子线程
                .subscribeOn(Schedulers.io())
                //指定一个观察者
                .subscribe(new Observer<BaseData<ChooseVoucherModel>>() {
                    @Override
                    public void onCompleted() {
                        if(useVoucherModel!=null){
                            voucherView.doUseVoucher(useVoucherModel);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(Tag,"获取失败"+e.getMessage());
                    }

                    @Override
                    public void onNext(BaseData<ChooseVoucherModel> useVoucherData) {
                        Log.i(Tag,useVoucherData.getData().toString());
                        useVoucherModel=useVoucherData;
                    }
                })
        );
    }

}
