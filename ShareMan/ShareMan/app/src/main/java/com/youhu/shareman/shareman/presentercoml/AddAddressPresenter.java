package com.youhu.shareman.shareman.presentercoml;

import android.content.Context;
import android.util.Log;

import com.youhu.shareman.shareman.base.BaseView;
import com.youhu.shareman.shareman.model.constant.DataManager;
import com.youhu.shareman.shareman.model.data.NormalModel;
import com.youhu.shareman.shareman.presenter.BasePresenter;
import com.youhu.shareman.shareman.view.AddAddressView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Touch on 2017/9/20.
 */

public class AddAddressPresenter implements BasePresenter {

    private static String Tag="AddressMangerPresenter";
    private Context context;
    private DataManager manager;
    private NormalModel addAddressModel;
    private AddAddressView addAddressView;
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
        addAddressView= (AddAddressView) view;
    }


    public void doPostDetail(String phoneNumber, String token,String consigneeName,String consigneeTel,String consigneeAddress,String detailAddress) {
        compositeSubscription.add(manager.addPostDetail(phoneNumber,token,consigneeName,consigneeTel,consigneeAddress,detailAddress)
                        //事件消费在主线程
                        .observeOn(AndroidSchedulers.mainThread())
                        //事件消费在子线程
                        .subscribeOn(Schedulers.io())
                        //指定一个观察者
                        .subscribe(new Observer<NormalModel>() {
                            @Override
                            public void onCompleted() {
                                if (addAddressModel != null) {
                                    addAddressView.doAddAddress(addAddressModel);
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.e(Tag, "获取失败" + e.getMessage());
//                        ToastUtils.show(context,"网络连接失败");
                            }

                            @Override
                            public void onNext(NormalModel addAddressData) {
                                Log.i(Tag, addAddressData.getMessage());
                                addAddressModel = addAddressData;
                            }
                        })

        );
    }
}
