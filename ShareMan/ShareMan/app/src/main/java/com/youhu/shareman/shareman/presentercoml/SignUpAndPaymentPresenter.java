package com.youhu.shareman.shareman.presentercoml;

import android.content.Context;
import android.util.Log;

import com.youhu.shareman.shareman.base.BaseView;
import com.youhu.shareman.shareman.model.constant.DataManager;
import com.youhu.shareman.shareman.model.data.NormalModel;
import com.youhu.shareman.shareman.presenter.BasePresenter;
import com.youhu.shareman.shareman.view.SignUpAndPaymentView;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Touch on 2017/9/21.
 */

public class SignUpAndPaymentPresenter implements BasePresenter {

    private static String Tag="SignUpAndPayment";
    private Context context;
    private DataManager manager;
    private NormalModel orderAgreementModel;
    private SignUpAndPaymentView signUpAndPaymentView;
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
        signUpAndPaymentView= (SignUpAndPaymentView) view;
    }

    public void getOrderAgreement(String phoneNumber, String token, int orderId){
        compositeSubscription.add(manager.getOrderAgreement(phoneNumber,token,orderId)
                //事件消费在主线程
                .observeOn(AndroidSchedulers.mainThread())
                //事件消费在子线程
                .subscribeOn(Schedulers.io())
                //指定一个观察者
                .subscribe(new Observer<NormalModel>() {
                    @Override
                    public void onCompleted() {
                        if(orderAgreementModel!=null){
                            signUpAndPaymentView.doOrderAgreement(orderAgreementModel);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(Tag,"获取失败"+e.getMessage());
                    }

                    @Override
                    public void onNext(NormalModel orderAgreementData) {
                        Log.i(Tag,orderAgreementData.getMessage());
                        orderAgreementModel=orderAgreementData;
                    }
                })

        );
    }


    //上传图片
    public void uploadSign(String phoneNumber, String token, String orderId, File signImage){
        //创建RequwstBody对象
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), signImage);
        //创建nickNameBody对象
        RequestBody phoneNumberBody = RequestBody.create(MediaType.parse("text/plain"), phoneNumber);
        RequestBody tokenBody = RequestBody.create(MediaType.parse("text/plain"), token);
        RequestBody orderIdBody = RequestBody.create(MediaType.parse("text/plain"), orderId);
        compositeSubscription.add(manager.uploadSign(phoneNumberBody,tokenBody,orderIdBody,requestBody)
                //事件消费在主线程
                .observeOn(AndroidSchedulers.mainThread())
                //事件消费在子线程
                .subscribeOn(Schedulers.io())
                //指定一个观察者
                .subscribe(new Observer<NormalModel>() {
                    @Override
                    public void onCompleted() {
                        if(orderAgreementModel!=null){
                            signUpAndPaymentView.doOrderAgreement(orderAgreementModel);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(Tag,"获取失败"+e.getMessage());
                    }

                    @Override
                    public void onNext(NormalModel orderAgreementData) {
                        Log.i(Tag,orderAgreementData.getMessage());
                        orderAgreementModel=orderAgreementData;
                    }
                })

        );
    }
}
