package com.youhu.shareman.shareman.presentercoml;

import android.content.Context;
import android.util.Log;

import com.youhu.shareman.shareman.base.BaseView;
import com.youhu.shareman.shareman.model.constant.DataManager;
import com.youhu.shareman.shareman.model.data.NormalModel;
import com.youhu.shareman.shareman.presenter.BasePresenter;
import com.youhu.shareman.shareman.view.InformationView;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Touch on 2017/9/22.
 */

public class InformationPresenter implements BasePresenter{

    private static String Tag="InformationPresenter";
    private Context context;
    private DataManager manager;
    private NormalModel informationModel;
    private InformationView informationView;
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
        informationView= (InformationView) view;
    }


    //上传身份信息
    public void uploadInformation(String phoneNumber, String token,String name,String idCardNo,String servicePassword,String company,String address){
        compositeSubscription.add(manager.uploadInformation(phoneNumber,token,name,idCardNo,servicePassword,company,address)
                //事件消费在主线程
                .observeOn(AndroidSchedulers.mainThread())
                //事件消费在子线程
                .subscribeOn(Schedulers.io())
                //指定一个观察者
                .subscribe(new Observer<NormalModel>() {
                    @Override
                    public void onCompleted() {
                        if(informationModel!=null){
                            informationView.doUploadInformation(informationModel);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(Tag,"获取失败"+e.getMessage());
                    }

                    @Override
                    public void onNext(NormalModel brandDataBaseData) {
                        Log.i(Tag,brandDataBaseData.getMessage());
                        informationModel=brandDataBaseData;
                    }
                })

        );
    }


    //上传身份证A面
    public void uploadIdCardA(String phoneNumber, String token, File idCardA){
        //创建RequwstBody对象
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), idCardA);
        //创建nickNameBody对象
        RequestBody phoneNumberBody = RequestBody.create(MediaType.parse("text/plain"), phoneNumber);
        RequestBody tokenBody = RequestBody.create(MediaType.parse("text/plain"), token);
        compositeSubscription.add(manager.uploadIdCardA(phoneNumberBody,tokenBody,requestBody)
                //事件消费在主线程
                .observeOn(AndroidSchedulers.mainThread())
                //事件消费在子线程
                .subscribeOn(Schedulers.io())
                //指定一个观察者
                .subscribe(new Observer<NormalModel>() {
                    @Override
                    public void onCompleted() {
                        if(informationModel!=null){
                            informationView.doUploadIdCardA(informationModel);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(Tag,"获取失败"+e.getMessage());
                    }

                    @Override
                    public void onNext(NormalModel brandDataBaseData) {
                        Log.i(Tag,brandDataBaseData.getMessage());
                        informationModel=brandDataBaseData;
                    }
                })

        );
    }
}
