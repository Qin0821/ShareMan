package com.youhu.shareman.shareman.model.constant;

import android.content.Context;

import com.youhu.shareman.shareman.model.Api.RetrofitService;
import com.youhu.shareman.shareman.model.data.BaseData;
import com.youhu.shareman.shareman.model.data.BrandModel;
import com.youhu.shareman.shareman.model.data.LoginCodeModel;
import com.youhu.shareman.shareman.model.data.NormalModel;

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

    //获取验证码
    public  Observable<BaseData<LoginCodeModel>> dologin(String phoneNumber,String code){
        return mRetrofitService.dologin(phoneNumber,code);
    }

    //验证验证码是否正确
    public  Observable<BaseData<String>> getCode(String phoneNumber){
        return mRetrofitService.getCode(phoneNumber);
    }

    //专区
    public  Observable<BaseData<List<BrandModel>>> getBrandDatas(String brand){
        return mRetrofitService.getBrandDatas(brand);
    }

    //修改昵称
    public  Observable<NormalModel> changeNickname(String phoneNumber, String token, String nickname){
        return mRetrofitService.changeNickname(phoneNumber,token,nickname);
    }

    //修改性别
    public  Observable<NormalModel> changeSex(String phoneNumber, String token, int sex){
        return mRetrofitService.changeSex(phoneNumber,token,sex);
    }

    //修改手机号码
    public  Observable<NormalModel> changePhoneNumber(String phoneNumber, String token, String newPhoneNumber){
        return mRetrofitService.changePhoneNumber(phoneNumber,token,newPhoneNumber);
    }

    //新手机号码验证
    public  Observable<BaseData<LoginCodeModel>> newPhoneNumber(String phoneNumber, String token, String newPhoneNumber,String code){
        return mRetrofitService.newPhoneNumber(phoneNumber,token,newPhoneNumber,code);
    }
}
