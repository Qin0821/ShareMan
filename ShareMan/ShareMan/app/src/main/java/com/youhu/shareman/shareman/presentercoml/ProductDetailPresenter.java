package com.youhu.shareman.shareman.presentercoml;

import android.content.Context;
import android.util.Log;

import com.youhu.shareman.shareman.base.BaseView;
import com.youhu.shareman.shareman.model.constant.DataManager;
import com.youhu.shareman.shareman.model.data.BaseData;
import com.youhu.shareman.shareman.model.data.NormalModel;
import com.youhu.shareman.shareman.model.data.ProductDetailModel;
import com.youhu.shareman.shareman.model.data.ZhimaModel;
import com.youhu.shareman.shareman.presenter.BasePresenter;
import com.youhu.shareman.shareman.view.ProductDetailView;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Touch on 2017/9/25.
 */

public class ProductDetailPresenter implements BasePresenter {

    private static String Tag="ProductDetailPresenter";
    private Context context;
    private DataManager manager;
    private NormalModel startModel;
    private BaseData<ProductDetailModel> productDetailModel;
    private BaseData<ZhimaModel> zhimaModel;
    private ProductDetailView productDetailView;
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
        productDetailView= (ProductDetailView) view;
    }

    //获取详情信息
    public void getProductDetail(String version){
        compositeSubscription.add(manager.getProductDetail(version)
                //事件消费在主线程
                .observeOn(AndroidSchedulers.mainThread())
                //事件消费在子线程
                .subscribeOn(Schedulers.io())
                //指定一个观察者
                .subscribe(new Observer<BaseData<ProductDetailModel>>() {
                    @Override
                    public void onCompleted() {
                        if(productDetailModel!=null){
                            productDetailView.getProductDetail(productDetailModel);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(Tag,"获取失败"+e.getMessage());
                    }

                    @Override
                    public void onNext(BaseData<ProductDetailModel> productDetailData) {
                        Log.i(Tag,productDetailData.getData().toString());
                        productDetailModel=productDetailData;
                    }
                })

        );
    }


    //开始预约
    public void startBooking(String phoneNumber, String token, String version,String introduceTitle){
        compositeSubscription.add(manager.startBooking(phoneNumber,token,version,introduceTitle)
                //事件消费在主线程
                .observeOn(AndroidSchedulers.mainThread())
                //事件消费在子线程
                .subscribeOn(Schedulers.io())
                //指定一个观察者
                .subscribe(new Observer<NormalModel>() {
                    @Override
                    public void onCompleted() {
                        if(startModel!=null){
                            productDetailView.doStartBooking(startModel);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(Tag,"获取失败"+e.getMessage());
                    }

                    @Override
                    public void onNext(NormalModel startData) {
                        Log.i(Tag,startData.getMessage());
                        startModel=startData;
                    }
                })
        );
    }


    //请求芝麻信用
    public void getZhima(String phoneNumber, String token){
        compositeSubscription.add(manager.getZhima(phoneNumber,token)
                //事件消费在主线程
                .observeOn(AndroidSchedulers.mainThread())
                //事件消费在子线程
                .subscribeOn(Schedulers.io())
                //指定一个观察者
                .subscribe(new Observer<BaseData<ZhimaModel>>() {
                    @Override
                    public void onCompleted() {
                        if(zhimaModel!=null){
                            productDetailView.doGetZhima(zhimaModel);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(Tag,"获取失败"+e.getMessage());
                    }

                    @Override
                    public void onNext(BaseData<ZhimaModel> zhimaData) {
                        Log.i(Tag,zhimaData.getMessage());
                        zhimaModel=zhimaData;
                    }
                })
        );
    }
}
