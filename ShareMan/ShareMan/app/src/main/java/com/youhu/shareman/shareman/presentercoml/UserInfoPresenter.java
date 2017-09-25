package com.youhu.shareman.shareman.presentercoml;

import android.content.Context;
import android.util.Log;

import com.youhu.shareman.shareman.base.BaseView;
import com.youhu.shareman.shareman.model.constant.DataManager;
import com.youhu.shareman.shareman.model.data.BaseData;
import com.youhu.shareman.shareman.model.data.NormalModel;
import com.youhu.shareman.shareman.model.data.UserInfoModel;
import com.youhu.shareman.shareman.presenter.BasePresenter;
import com.youhu.shareman.shareman.view.UserInfoView;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.RequestBody;
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
    private BaseData<UserInfoModel> userInfoModel;
    private NormalModel nickNameModel;
    private NormalModel sexModel;
    private NormalModel userImageModel;
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

    //获取用户信息
    public void getUserInfo(String phoneNumber,String token){
        compositeSubscription.add(manager.getUserInfo(phoneNumber,token)
                //事件消费在主线程
                .observeOn(AndroidSchedulers.mainThread())
                //事件消费在子线程
                .subscribeOn(Schedulers.io())
                //指定一个观察者
                .subscribe(new Observer<BaseData<UserInfoModel>>() {
                    @Override
                    public void onCompleted() {
                        if(userInfoModel!=null){
                            userInfoView.doGetUserInfo(userInfoModel);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(Tag,"获取失败"+e.getMessage());
                    }

                    @Override
                    public void onNext(BaseData<UserInfoModel> userInfoData) {
                        Log.i(Tag,userInfoData.getMessage());
                        userInfoModel=userInfoData;
                    }
                })

        );
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


    //修改头像
    public void doChangeUserImage(String phoneNumber,String token, File signImage){
        //创建RequwstBody对象
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), signImage);
        //创建nickNameBody对象
        RequestBody phoneNumberBody = RequestBody.create(MediaType.parse("text/plain"), phoneNumber);
        RequestBody tokenBody = RequestBody.create(MediaType.parse("text/plain"), token);
        compositeSubscription.add(manager.changeUserImage(phoneNumberBody,tokenBody,requestBody)
                //事件消费在主线程
                .observeOn(AndroidSchedulers.mainThread())
                //事件消费在子线程
                .subscribeOn(Schedulers.io())
                //指定一个观察者
                .subscribe(new Observer<NormalModel>() {
                    @Override
                    public void onCompleted() {
                        if(userImageModel!=null){
                            userInfoView.doChangeUserImage(userImageModel);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(Tag,"获取失败"+e.getMessage());
                    }

                    @Override
                    public void onNext(NormalModel userImageData) {
                        Log.i(Tag,userImageData.getMessage());
                        userImageModel=userImageData;
                    }
                })

        );
    }

}
