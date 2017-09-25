package com.youhu.shareman.shareman.presentercoml;

import android.content.Context;
import android.util.Log;

import com.youhu.shareman.shareman.base.BaseView;
import com.youhu.shareman.shareman.model.constant.DataManager;
import com.youhu.shareman.shareman.model.data.NormalModel;
import com.youhu.shareman.shareman.presenter.BasePresenter;
import com.youhu.shareman.shareman.view.FeedBackView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Touch on 2017/9/20.
 */

public class FeedBackPresenter implements BasePresenter {

    private static String Tag="FeedBackPresenter";
    private Context context;
    private DataManager manager;
    private NormalModel normalModel;
    private FeedBackView feedBackView;
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
        feedBackView = (FeedBackView) view;
    }

    public void doSendAdvice(String phoneNumber,String token,String advice,String contactWay) {
        compositeSubscription.add(manager.sendAdvice(phoneNumber, token, advice,contactWay)
                //事件消费在主线程
                .observeOn(AndroidSchedulers.mainThread())
                //事件消费在子线程
                .subscribeOn(Schedulers.io())
                //指定一个观察者
                .subscribe(new Observer<NormalModel>() {
                    @Override
                    public void onCompleted() {
                        if (normalModel != null) {
                            feedBackView.doSendAdvice(normalModel);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(Tag, "获取失败" + e.getMessage());
//                        ToastUtils.show(context,"网络连接失败");
                    }

                    @Override
                    public void onNext(NormalModel normaldata) {
                        Log.i(Tag, normaldata.getMessage());
                        normalModel = normaldata;
                    }
                })

        );
    }
}
