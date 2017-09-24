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
    private NormalModel informationAModel;
    private NormalModel informationBModel;
    private NormalModel informationBanshenModel;
    private NormalModel informationStudentModel;
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
                        if(informationAModel!=null){
                            informationView.doUploadIdCardA(informationAModel);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(Tag,"获取失败"+e.getMessage());
                    }

                    @Override
                    public void onNext(NormalModel brandDataBaseData) {
                        Log.i(Tag,brandDataBaseData.getMessage());
                        informationAModel=brandDataBaseData;
                    }
                })

        );
    }


    //上传身份证B面
    public void uploadIdCardB(String phoneNumber, String token, File idCardA){
        //创建RequwstBody对象
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), idCardA);
        //创建nickNameBody对象
        RequestBody phoneNumberBody = RequestBody.create(MediaType.parse("text/plain"), phoneNumber);
        RequestBody tokenBody = RequestBody.create(MediaType.parse("text/plain"), token);
        compositeSubscription.add(manager.uploadIdCardB(phoneNumberBody,tokenBody,requestBody)
                //事件消费在主线程
                .observeOn(AndroidSchedulers.mainThread())
                //事件消费在子线程
                .subscribeOn(Schedulers.io())
                //指定一个观察者
                .subscribe(new Observer<NormalModel>() {
                    @Override
                    public void onCompleted() {
                        if(informationBModel!=null){
                            informationView.doUploadIdCardB(informationBModel);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(Tag,"获取失败"+e.getMessage());
                    }

                    @Override
                    public void onNext(NormalModel brandDataBaseData) {
                        Log.i(Tag,brandDataBaseData.getMessage());
                        informationBModel=brandDataBaseData;
                    }
                })

        );
    }



    //上传手持身份证照
    public void uploadIdCardBanshen(String phoneNumber, String token, File idCardA){
        //创建RequwstBody对象
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), idCardA);
        //创建nickNameBody对象
        RequestBody phoneNumberBody = RequestBody.create(MediaType.parse("text/plain"), phoneNumber);
        RequestBody tokenBody = RequestBody.create(MediaType.parse("text/plain"), token);
        compositeSubscription.add(manager.uploadIdCardBanshen(phoneNumberBody,tokenBody,requestBody)
                //事件消费在主线程
                .observeOn(AndroidSchedulers.mainThread())
                //事件消费在子线程
                .subscribeOn(Schedulers.io())
                //指定一个观察者
                .subscribe(new Observer<NormalModel>() {
                    @Override
                    public void onCompleted() {
                        if(informationBanshenModel!=null){
                            informationView.doUploadBanshen(informationBanshenModel);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(Tag,"获取失败"+e.getMessage());
                    }

                    @Override
                    public void onNext(NormalModel brandDataBaseData) {
                        Log.i(Tag,brandDataBaseData.getMessage());
                        informationBanshenModel=brandDataBaseData;
                    }
                })

        );
    }



    //上传学生照
    public void uploadStudent(String phoneNumber, String token, File idCardA){
        //创建RequwstBody对象
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), idCardA);
        //创建nickNameBody对象
        RequestBody phoneNumberBody = RequestBody.create(MediaType.parse("text/plain"), phoneNumber);
        RequestBody tokenBody = RequestBody.create(MediaType.parse("text/plain"), token);
        compositeSubscription.add(manager.uploadIdCardStudent(phoneNumberBody,tokenBody,requestBody)
                //事件消费在主线程
                .observeOn(AndroidSchedulers.mainThread())
                //事件消费在子线程
                .subscribeOn(Schedulers.io())
                //指定一个观察者
                .subscribe(new Observer<NormalModel>() {
                    @Override
                    public void onCompleted() {
                        if(informationStudentModel!=null){
                            informationView.doUploadStudent(informationStudentModel);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(Tag,"获取失败"+e.getMessage());
                    }

                    @Override
                    public void onNext(NormalModel brandDataBaseData) {
                        Log.i(Tag,brandDataBaseData.getMessage());
                        informationStudentModel=brandDataBaseData;
                    }
                })

        );
    }
}
