package com.youhu.shareman.shareman.model.Api;

import com.youhu.shareman.shareman.model.constant.AppConfig;
import com.youhu.shareman.shareman.model.data.BaseData;
import com.youhu.shareman.shareman.model.data.BrandData;
import com.youhu.shareman.shareman.model.data.LoginCodeModel;
import com.youhu.shareman.shareman.model.data.NormalData;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Touch on 2017/9/15.
 */

public interface RetrofitService {

    //获取验证码
    @FormUrlEncoded
    @POST(AppConfig.USER_LOGIN)
    Observable<BaseData<String>> getCode(@Field("phoneNumber") String phoneNumber);

    //验证验证码是否正确
    @FormUrlEncoded
    @POST(AppConfig.USER_LOGIN_CODE)
    Observable<BaseData<LoginCodeModel>> dologin(@Field("phoneNumber") String phoneNumber, @Field("identifyCode") String code);

    //获取验证码
    @FormUrlEncoded
    @POST(AppConfig.SHARE_BRAND)
    Observable<BaseData<List<BrandData>>> getBrandDatas(@Field("brand") String brand);

    //修改昵称
    @FormUrlEncoded
    @POST(AppConfig.CHANGE_NICKNAME)
    Observable<NormalData> changeNickname(@Field("phoneNumber") String phoneNumber,@Field("token") String token,@Field("nickname") String nickName);

    //修改性别
    @FormUrlEncoded
    @POST(AppConfig.CHANGE_SEX)
    Observable<NormalData> changeSex(@Field("phoneNumber") String phoneNumber,@Field("token") String token,@Field("gender") int sex);

}
