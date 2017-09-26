package com.youhu.shareman.shareman.presentercoml;

import android.content.Context;
import android.util.Log;

import com.youhu.shareman.shareman.base.BaseView;
import com.youhu.shareman.shareman.model.constant.DataManager;
import com.youhu.shareman.shareman.model.data.BannerModel;
import com.youhu.shareman.shareman.model.data.BaseData;
import com.youhu.shareman.shareman.model.data.HotRecomentModel;
import com.youhu.shareman.shareman.presenter.BasePresenter;
import com.youhu.shareman.shareman.view.MainActivityView;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Touch on 2017/9/25.
 */

public class MainActivityPreserter implements BasePresenter {

    private static String Tag="MainActivityPreserter";
    private Context context;
    private DataManager manager;
    private BaseData<List<BannerModel>> bannerModel;
    private BaseData<List<HotRecomentModel>> hotRecomentModel;
    private MainActivityView mainActivityView;
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
        mainActivityView= (MainActivityView) view;
    }


    public void getMainBanner( ){
        compositeSubscription.add(manager.getMainBanner()
                //事件消费在主线程
                .observeOn(AndroidSchedulers.mainThread())
                //事件消费在子线程
                .subscribeOn(Schedulers.io())
                //指定一个观察者
                .subscribe(new Observer<BaseData<List<BannerModel>>>() {
                    @Override
                    public void onCompleted() {
                        if(bannerModel!=null){
                            mainActivityView.doBanner(bannerModel);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(Tag,"获取失败"+e.getMessage());
                    }

                    @Override
                    public void onNext(BaseData<List<BannerModel>> bannerData) {
                        Log.i(Tag,bannerData.getData().toString());
                        bannerModel=bannerData;
                    }
                })

        );
    }


    public void getHotRecoment( ){
        compositeSubscription.add(manager.getHotRecoment()
                //事件消费在主线程
                .observeOn(AndroidSchedulers.mainThread())
                //事件消费在子线程
                .subscribeOn(Schedulers.io())
                //指定一个观察者
                .subscribe(new Observer<BaseData<List<HotRecomentModel>>>() {
                    @Override
                    public void onCompleted() {
                        if(hotRecomentModel!=null){
                            mainActivityView.doHotRecoment(hotRecomentModel);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(Tag,"获取失败"+e.getMessage());
                    }

                    @Override
                    public void onNext(BaseData<List<HotRecomentModel>> hotRecomentData) {
                        Log.i(Tag,hotRecomentData.getData().toString());
                        hotRecomentModel=hotRecomentData;
                    }
                })

        );
    }
}
