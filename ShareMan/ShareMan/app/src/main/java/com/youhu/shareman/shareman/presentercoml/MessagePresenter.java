package com.youhu.shareman.shareman.presentercoml;

import android.content.Context;
import android.util.Log;

import com.youhu.shareman.shareman.base.BaseView;
import com.youhu.shareman.shareman.model.constant.DataManager;
import com.youhu.shareman.shareman.model.data.BaseData;
import com.youhu.shareman.shareman.model.data.MessageModel;
import com.youhu.shareman.shareman.presenter.BasePresenter;
import com.youhu.shareman.shareman.view.MessageView;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Touch on 2017/9/26.
 */

public class MessagePresenter implements BasePresenter {

    private static String Tag="MessagePresenter";
    private Context context;
    private DataManager manager;
    private BaseData<List<MessageModel>> messageModel;
    private MessageView messageView;
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
        messageView= (MessageView) view;
    }


    public void getMainMessage(){
        compositeSubscription.add(manager.getMainMessage()
                //事件消费在主线程
                .observeOn(AndroidSchedulers.mainThread())
                //事件消费在子线程
                .subscribeOn(Schedulers.io())
                //指定一个观察者
                .subscribe(new Observer<BaseData<List<MessageModel>>>() {
                    @Override
                    public void onCompleted() {
                        if(messageModel!=null){
                            messageView.doMessage(messageModel);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(Tag,"获取失败"+e.getMessage());
                    }

                    @Override
                    public void onNext(BaseData<List<MessageModel>> messageData) {
                        Log.i(Tag,messageData.getData().toString());
                        messageModel=messageData;
                    }
                })
        );
    }
}
