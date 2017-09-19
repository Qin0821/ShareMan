package com.youhu.shareman.shareman.presentercoml;

import android.content.Context;
import android.util.Log;

import com.youhu.shareman.shareman.base.BaseView;
import com.youhu.shareman.shareman.model.constant.DataManager;
import com.youhu.shareman.shareman.model.data.NormalModel;
import com.youhu.shareman.shareman.presenter.BasePresenter;
import com.youhu.shareman.shareman.ui.view.UserInfoView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Touch on 2017/9/18.
 */

public class UserInfoPresenter implements BasePresenter {

    private static String Tag="UserInfoPresenter";
    private Context context;
    private DataManager manager;
    private NormalModel nickNameModel;
    private NormalModel sexModel;
    private NormalModel phoneNumberModel;
    private UserInfoView userInfoView;
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
        userInfoView= (UserInfoView) view;
    }

    //修改昵称
    public void doChangeNick(String phoneNumber,String token,String nickName){
        compositeSubscription.add(manager.changeNickname(phoneNumber,token,nickName)
                //事件消费在主线程
                .observeOn(AndroidSchedulers.mainThread())
                //事件消费在子线程
                .subscribeOn(Schedulers.io())
                //指定一个观察者
                .subscribe(new Observer<NormalModel>() {
                    @Override
                    public void onCompleted() {
                        if(sexModel!=null){
                            userInfoView.doChangeNickname(sexModel);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(Tag,"获取失败"+e.getMessage());
                    }

                    @Override
                    public void onNext(NormalModel sexModelData) {
                        Log.i(Tag,sexModelData.getMessage());
                        sexModel=sexModelData;
                    }
                })

        );
    }

    //修改性别
    public void doChangeSex(String phoneNumber,String token,int sex){
        compositeSubscription.add(manager.changeSex(phoneNumber,token,sex)
                //事件消费在主线程
                .observeOn(AndroidSchedulers.mainThread())
                //事件消费在子线程
                .subscribeOn(Schedulers.io())
                //指定一个观察者
                .subscribe(new Observer<NormalModel>() {
                    @Override
                    public void onCompleted() {
                        if(nickNameModel!=null){
                            userInfoView.doChangeNickname(nickNameModel);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(Tag,"获取失败"+e.getMessage());
                    }

                    @Override
                    public void onNext(NormalModel nickNameData) {
                        Log.i(Tag,nickNameData.getMessage());
                        nickNameModel=nickNameData;
                    }
                })

        );
    }

}
