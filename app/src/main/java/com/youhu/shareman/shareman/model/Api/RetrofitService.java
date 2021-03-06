package com.youhu.shareman.shareman.model.Api;

import com.youhu.shareman.shareman.model.constant.AppConfig;
import com.youhu.shareman.shareman.model.data.AddressMangerModel;
import com.youhu.shareman.shareman.model.data.BaseData;
import com.youhu.shareman.shareman.model.data.BrandModel;
import com.youhu.shareman.shareman.model.data.LoginCodeModel;
import com.youhu.shareman.shareman.model.data.NewNumberLoginCodeModel;
import com.youhu.shareman.shareman.model.data.NormalModel;
import com.youhu.shareman.shareman.model.data.ShareOrderModel;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
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

    //获取专区数据
    @FormUrlEncoded
    @POST(AppConfig.SHARE_BRAND)
    Observable<BaseData<List<BrandModel>>> getBrandDatas(@Field("brand") String brand);

    //修改昵称
    @FormUrlEncoded
    @POST(AppConfig.CHANGE_NICKNAME)
    Observable<NormalModel> changeNickname(@Field("phoneNumber") String phoneNumber, @Field("token") String token, @Field("nickname") String nickName);

    //修改性别
    @FormUrlEncoded
    @POST(AppConfig.CHANGE_SEX)
    Observable<NormalModel> changeSex(@Field("phoneNumber") String phoneNumber, @Field("token") String token, @Field("gender") int sex);

    //修改手机号码获取验证码
    @FormUrlEncoded
    @POST(AppConfig.CHANGE_PHONENUMBER)
    Observable<BaseData<String>> changePhoneNumber(@Field("phoneNumber") String phoneNumber, @Field("token") String token, @Field("newPhoneNumber") String newPhoneNumber);

    //修改手机号码验证码登录
    @FormUrlEncoded
    @POST(AppConfig.NEW_PHONENUMBER)
    Observable<BaseData<NewNumberLoginCodeModel>> newPhoneNumber(@Field("phoneNumber") String phoneNumber, @Field("token") String token, @Field("newPhoneNumber") String newPhoneNumber, @Field("identifyCode") String code);

    //打小报告
    @FormUrlEncoded
    @POST(AppConfig.SEND_ADVICE)
    Observable<NormalModel> sendAdvice(@Field("phoneNumber") String phoneNumber, @Field("token") String token, @Field("advice") String advice,@Field("contactWay") String contactWay);

    //获取手机收货地址
    @FormUrlEncoded
    @POST(AppConfig.GET_POST_DETAIL)
    Observable<BaseData<List<AddressMangerModel>>> getPostDetail(@Field("phoneNumber") String phoneNumber, @Field("token") String token);

    //添加手机收货地址
    @FormUrlEncoded
    @POST(AppConfig.ADD_POST_DETAIL)
    Observable<NormalModel> addPostDetail(
            @Field("phoneNumber") String phoneNumber,
            @Field("token") String token,
            @Field("consigneeName") String consigneeName,
            @Field("consigneeTel") String consigneeTel,
            @Field("consigneeAddress") String consigneeAddress,
            @Field("detailAddress") String detailAddress);

    //修改手机收货地址
    @FormUrlEncoded
    @POST(AppConfig.UPDATE_POST_DETAIL)
    Observable<NormalModel> updatePostDetail(
            @Field("phoneNumber") String phoneNumber,
            @Field("token") String token,
            @Field("consigneeName") String consigneeName,
            @Field("consigneeTel") String consigneeTel,
            @Field("consigneeAddress") String consigneeAddress,
            @Field("detailAddress") String detailAddress,
            @Field("postDetailId") int postDetailId);

    //删除手机收货地址
    @FormUrlEncoded
    @POST(AppConfig.DELETE_POST_DETAIL)
    Observable<NormalModel> deletePostDetail(@Field("phoneNumber") String phoneNumber, @Field("token") String token,@Field("postDetailId") int postDetailId);

    //共享订单列表
    @FormUrlEncoded
    @POST(AppConfig.SHARE_ORDER)
    Observable<BaseData<List<ShareOrderModel>>> getShareOrder(@Field("phoneNumber") String phoneNumber, @Field("token") String token, @Field("status") int status);

    //共享订单列表
    @FormUrlEncoded
    @POST(AppConfig.CHECK_AGREEMENT)
    Observable<NormalModel> getOrderAgreement(@Field("phoneNumber") String phoneNumber, @Field("token") String token, @Field("orderId") int orderId);

    //上传签名(图片)
    @Multipart
    @POST(AppConfig.UPLOAD_SIGN)
    Observable<NormalModel> uploadSign(@Part("phoneNumber") RequestBody phoneNumber, @Part("token") RequestBody token, @Part("orderId") RequestBody orderId,@Part("image\"; filename=\"image.png\"") RequestBody signImage);

    //修改手机收货地址
    @FormUrlEncoded
    @POST(AppConfig.UPLOAD_INFORMATION)
    Observable<NormalModel> uploadInformation(
            @Field("phoneNumber") String phoneNumber,
            @Field("token") String token,
            @Field("name") String name,
            @Field("idCardNo") String idCardNo,
            @Field("servicePassword") String servicePassword,
            @Field("company") String company,
            @Field("address") String address);

    //上传身份证A面
    @Multipart
    @POST(AppConfig.UPLOAD_ID_CARD_A)
    Observable<NormalModel> uploadIdCardA(@Part("phoneNumber") RequestBody phoneNumber, @Part("token") RequestBody token,@Part("image\"; filename=\"image.png\"") RequestBody idCardA);
}
