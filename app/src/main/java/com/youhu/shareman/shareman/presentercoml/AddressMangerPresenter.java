package com.youhu.shareman.shareman.presentercoml;

import android.content.Context;
import android.util.Log;

import com.youhu.shareman.shareman.base.BaseView;
import com.youhu.shareman.shareman.model.constant.DataManager;
import com.youhu.shareman.shareman.model.data.AddressMangerModel;
import com.youhu.shareman.shareman.model.data.BaseData;
import com.youhu.shareman.shareman.model.data.NormalModel;
import com.youhu.shareman.shareman.presenter.BasePresenter;
import com.youhu.shareman.shareman.view.AddressMangerView;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Touch on 2017/9/20.
 */

public class AddressMangerPresenter implements BasePresenter {

    private static String Tag="AddressMangerPresenter";
    private Context context;
    private DataManager manager;
    private BaseData<List<AddressMangerModel>> addressMangerModel;
    private NormalModel deleteAddressModel;
    private AddressMangerView addressMangerView;
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
        addressMangerView= (AddressMangerView) view;
    }


    public void doPostDetail(String phoneNumber,String token) {
        compositeSubscription.add(manager.getPostDetail(phoneNumber, token)
                        //事件消费在主线程
                        .observeOn(AndroidSchedulers.mainThread())
                        //事件消费在子线程
                        .subscribeOn(Schedulers.io())
                        //指定一个观察者
                        .subscribe(new Observer<BaseData<List<AddressMangerModel>>>() {
                            @Override
                            public void onCompleted() {
                                if (addressMangerModel != null) {
                                    addressMangerView.doPostDetail(addressMangerModel);
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e(Tag, "获取失败" + e.getMessage());
//                        ToastUtils.show(context,"网络连接失败");
                            }

                            @Override
                            public void onNext(BaseData<List<AddressMangerModel>> addressMangerData) {
                                Log.i(Tag, addressMangerData.getMessage());
                                addressMangerModel = addressMangerData;
                            }
                        })

        );
    }


    public void doDeletePostDetail(String phoneNumber,String token,int postDetailId) {
        compositeSubscription.add(manager.deletePostDetail(phoneNumber, token,postDetailId)
                        //事件消费在主线程
                        .observeOn(AndroidSchedulers.mainThread())
                        //事件消费在子线程
                        .subscribeOn(Schedulers.io())
                        //指定一个观察者
                        .subscribe(new Observer<NormalModel>() {
                            @Override
                            public void onCompleted() {
                                if (deleteAddressModel != null) {
                                    addressMangerView.doDeletePostDetail(deleteAddressModel);
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e(Tag, "获取失败" + e.getMessage());
//                        ToastUtils.show(context,"网络连接失败");
                            }

                            @Override
                            public void onNext(NormalModel deleteAddressData) {
                                Log.i(Tag, deleteAddressData.getMessage());
                                deleteAddressModel = deleteAddressData;
                            }
                        })

        );
    }
}
