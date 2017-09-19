package com.youhu.shareman.shareman.presentercoml;

import android.content.Context;
import android.util.Log;

import com.youhu.shareman.shareman.base.BaseView;
import com.youhu.shareman.shareman.model.constant.DataManager;
import com.youhu.shareman.shareman.model.data.BaseData;
import com.youhu.shareman.shareman.model.data.LoginCodeModel;
import com.youhu.shareman.shareman.model.data.NormalModel;
import com.youhu.shareman.shareman.presenter.BasePresenter;
import com.youhu.shareman.shareman.ui.view.NewPhoneNumberView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Touch on 2017/9/19.
 */

public class NewPhoneNumberPresenter implements BasePresenter {

    private static String Tag="NewPhoneNumberPresenter";
    private Context context;
    private DataManager manager;
    private NormalModel phoneNumberModel;
    private BaseData<LoginCodeModel> newPhoneNumberData;
    private NewPhoneNumberView newPhoneNumberView;
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
        newPhoneNumberView= (NewPhoneNumberView) view;
    }

    //修改手机号码
    public void doChangePhoneNumber(String phoneNumber,String token,String newPhoneNumber){
        compositeSubscription.add(manager.changePhoneNumber(phoneNumber,token,newPhoneNumber)
                //事件消费在主线程
                .observeOn(AndroidSchedulers.mainThread())
                //事件消费在子线程
                .subscribeOn(Schedulers.io())
                //指定一个观察者
                .subscribe(new Observer<NormalModel>() {
                    @Override
                    public void onCompleted() {
                        if(phoneNumberModel!=null){
                            newPhoneNumberView.doChangePhoneNumber(phoneNumberModel);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(Tag,"获取失败"+e.getMessage());
                    }

                    @Override
                    public void onNext(NormalModel phoneNumberData) {
                        Log.i(Tag,phoneNumberData.getMessage());
                        phoneNumberModel=phoneNumberData;
                    }
                })

        );
    }


    //新手机号码验证
    public void doNewPhoneNumber(String phoneNumber,String token,String newPhoneNumber,String code){
        compositeSubscription.add(manager.newPhoneNumber(phoneNumber,token,newPhoneNumber,code)
                //事件消费在主线程
                .observeOn(AndroidSchedulers.mainThread())
                //事件消费在子线程
                .subscribeOn(Schedulers.io())
                //指定一个观察者
                .subscribe(new Observer<BaseData<LoginCodeModel>>() {
                    @Override
                    public void onCompleted() {
                        if(newPhoneNumberData!=null){
                            newPhoneNumberView.doNewPhoneNumber(newPhoneNumberData);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(Tag,"获取失败"+e.getMessage());
                    }

                    @Override
                    public void onNext(BaseData<LoginCodeModel> newPhoneData) {
                        Log.i(Tag,newPhoneData.getMessage());
                        newPhoneNumberData=newPhoneData;
                    }
                })

        );
    }
}
