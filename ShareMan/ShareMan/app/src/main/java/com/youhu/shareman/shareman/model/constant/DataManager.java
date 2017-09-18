package com.youhu.shareman.shareman.model.constant;

import android.content.Context;

import com.youhu.shareman.shareman.model.Api.RetrofitService;
import com.youhu.shareman.shareman.model.data.BaseData;
import com.youhu.shareman.shareman.model.data.BrandData;
import com.youhu.shareman.shareman.model.data.LoginCodeModel;

import java.util.List;

import rx.Observable;

/**
 * Created by win764-1 on 2016/12/12.
 */

public class DataManager {
    private RetrofitService mRetrofitService;
    public DataManager(Context context){
        this.mRetrofitService = RetrofitHelper.getInstance(context).getServer();
    }
    public  Observable<BaseData<LoginCodeModel>> dologin(String phoneNumber,String code){
        return mRetrofitService.dologin(phoneNumber,code);
    }
    public  Observable<BaseData<String>> getCode(String phoneNumber){
        return mRetrofitService.getCode(phoneNumber);
    }

    public  Observable<BaseData<List<BrandData>>> getBrandDatas(String series){
        return mRetrofitService.getBrandDatas(series);
    }
}
