package com.youhu.shareman.shareman.presentercoml;

import android.content.Context;
import android.util.Log;

import com.youhu.shareman.shareman.base.BaseView;
import com.youhu.shareman.shareman.model.constant.DataManager;
import com.youhu.shareman.shareman.model.data.BaseData;
import com.youhu.shareman.shareman.model.data.PayWayModel;
import com.youhu.shareman.shareman.presenter.BasePresenter;
import com.youhu.shareman.shareman.view.PaymentWayView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Touch on 2017/9/27.
 */

public class PaymentWayPresenter implements BasePresenter {

    private static String Tag="PaymentWayPresenter";
    private Context context;
    private DataManager manager;
    private BaseData<PayWayModel> payWayModel;
    private BaseData<String> orderInfoModel;
    private PaymentWayView paymentWayView;
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
        paymentWayView= (PaymentWayView) view;
    }



    public void getPayInfo(String phoneNumber, String token, int orderId){
        compositeSubscription.add(manager.getPayInfo(phoneNumber,token,orderId)
                //事件消费在主线程
                .observeOn(AndroidSchedulers.mainThread())
                //事件消费在子线程
                .subscribeOn(Schedulers.io())
                //指定一个观察者
                .subscribe(new Observer<BaseData<PayWayModel>>() {
                    @Override
                    public void onCompleted() {
                        if(payWayModel!=null){
                            paymentWayView.doGetPayPrice(payWayModel);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(Tag,"获取失败"+e.getMessage());
                    }

                    @Override
                    public void onNext(BaseData<PayWayModel> brandDataBaseData) {
                        Log.i(Tag,brandDataBaseData.getData().toString());
                        payWayModel=brandDataBaseData;
                    }
                })
        );
    }


    //获取订单信息
    public void getPayOrderInfo(String phoneNumber, String token,int voucherId, int orderId){
        compositeSubscription.add(manager.getPayOrderInfo(phoneNumber,token,voucherId,orderId)
                //事件消费在主线程
                .observeOn(AndroidSchedulers.mainThread())
                //事件消费在子线程
                .subscribeOn(Schedulers.io())
                //指定一个观察者
                .subscribe(new Observer<BaseData<String>>() {
                    @Override
                    public void onCompleted() {
                        if(orderInfoModel!=null){
                            paymentWayView.doGetOrderInfo(orderInfoModel);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(Tag,"获取失败"+e.getMessage());
                    }

                    @Override
                    public void onNext(BaseData<String> orderInfoData) {
                        Log.i(Tag,orderInfoData.getData());
                        orderInfoModel=orderInfoData;
                    }
                })
        );
    }
}
