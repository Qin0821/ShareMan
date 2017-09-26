package com.youhu.shareman.shareman.presentercoml;

import android.content.Context;
import android.util.Log;

import com.youhu.shareman.shareman.base.BaseView;
import com.youhu.shareman.shareman.model.constant.DataManager;
import com.youhu.shareman.shareman.model.data.BaseData;
import com.youhu.shareman.shareman.model.data.BrandModel;
import com.youhu.shareman.shareman.presenter.BasePresenter;
import com.youhu.shareman.shareman.view.BrandView;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Touch on 2017/9/15.
 */

public class BrandPresenter implements BasePresenter {

    private static String Tag="BrandPresenter";
    private Context context;
    private DataManager manager;
    private BaseData<List<BrandModel>> brandModel;
    private BaseData<List<BrandModel>> mainTypeModel;
    private BrandView brandView;
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
        brandView= (BrandView) view;
    }

    public void doBrandData(String brandId){
        compositeSubscription.add(manager.getBrandDatas(brandId)
                //事件消费在主线程
                .observeOn(AndroidSchedulers.mainThread())
                //事件消费在子线程
                .subscribeOn(Schedulers.io())
                //指定一个观察者
                .subscribe(new Observer<BaseData<List<BrandModel>>>() {
                    @Override
                    public void onCompleted() {
                        if(brandModel!=null){
                            brandView.getBrandData(brandModel);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(Tag,"获取失败"+e.getMessage());
                    }

                    @Override
                    public void onNext(BaseData<List<BrandModel>> brandDataBaseData) {
                        Log.i(Tag,brandDataBaseData.getData().toString());
                        brandModel=brandDataBaseData;
                    }
                })
        );
    }


    public void getMainType(int type){
        compositeSubscription.add(manager.getMainType(type)
                //事件消费在主线程
                .observeOn(AndroidSchedulers.mainThread())
                //事件消费在子线程
                .subscribeOn(Schedulers.io())
                //指定一个观察者
                .subscribe(new Observer<BaseData<List<BrandModel>>>() {
                    @Override
                    public void onCompleted() {
                        if(mainTypeModel!=null){
                            brandView.getMainBrandData(mainTypeModel);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(Tag,"获取失败"+e.getMessage());
                    }

                    @Override
                    public void onNext(BaseData<List<BrandModel>> mainBrandData) {
                        Log.i(Tag,mainBrandData.getData().toString());
                        mainTypeModel=mainBrandData;
                    }
                })
        );
    }

}
