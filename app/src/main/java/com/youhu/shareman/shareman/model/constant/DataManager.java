package com.youhu.shareman.shareman.model.constant;

import android.content.Context;

import com.youhu.shareman.shareman.model.Api.RetrofitService;
import com.youhu.shareman.shareman.model.data.AddressMangerModel;
import com.youhu.shareman.shareman.model.data.BaseData;
import com.youhu.shareman.shareman.model.data.BrandModel;
import com.youhu.shareman.shareman.model.data.LoginCodeModel;
import com.youhu.shareman.shareman.model.data.NewNumberLoginCodeModel;
import com.youhu.shareman.shareman.model.data.NormalModel;
import com.youhu.shareman.shareman.model.data.ShareOrderModel;

import java.util.List;

import okhttp3.RequestBody;
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
    public  Observable<BaseData<String>> changePhoneNumber(String phoneNumber, String token, String newPhoneNumber){
        return mRetrofitService.changePhoneNumber(phoneNumber,token,newPhoneNumber);
    }

    //新手机号码验证
    public  Observable<BaseData<NewNumberLoginCodeModel>> newPhoneNumber(String phoneNumber, String token, String newPhoneNumber, String code){
        return mRetrofitService.newPhoneNumber(phoneNumber,token,newPhoneNumber,code);
    }

    //打小报告
    public  Observable<NormalModel> sendAdvice(String phoneNumber, String token, String advice,String contactWay){
        return mRetrofitService.sendAdvice(phoneNumber,token,advice,contactWay);
    }

    //获取收货地址
    public  Observable<BaseData<List<AddressMangerModel>>> getPostDetail(String phoneNumber, String token){
        return mRetrofitService.getPostDetail(phoneNumber,token);
    }

    //添加收货地址
    public  Observable<NormalModel> addPostDetail(String phoneNumber, String token,String consigneeName,String consigneeTel,String consigneeAddress,String detailAddress){
        return mRetrofitService.addPostDetail(phoneNumber,token,consigneeName,consigneeTel,consigneeAddress,detailAddress);
    }

    //修改收货地址
    public  Observable<NormalModel> updatePostDetail(String phoneNumber, String token,String consigneeName,String consigneeTel,String consigneeAddress,String detailAddress,int postDetailId){
        return mRetrofitService.updatePostDetail(phoneNumber,token,consigneeName,consigneeTel,consigneeAddress,detailAddress,postDetailId);
    }

    //删除收货地址
    public  Observable<NormalModel> deletePostDetail(String phoneNumber, String token,int postDetailId){
        return mRetrofitService.deletePostDetail(phoneNumber,token,postDetailId);
    }

    //获取共享订单
    public  Observable<BaseData<List<ShareOrderModel>>> getShareOrder(String phoneNumber, String token, int status){
        return mRetrofitService.getShareOrder(phoneNumber,token,status);
    }

    //获取订单协议
    public  Observable<NormalModel> getOrderAgreement(String phoneNumber, String token, int orderId){
        return mRetrofitService.getOrderAgreement(phoneNumber,token,orderId);
    }

    //上传签名图片
    public  Observable<NormalModel> uploadSign(RequestBody phoneNumber, RequestBody token, RequestBody orderId, RequestBody signImage){
        return mRetrofitService.uploadSign(phoneNumber,token,orderId,signImage);
    }

    //上传身份信息
    public  Observable<NormalModel> uploadInformation(String phoneNumber, String token,String name,String idCardNo,String servicePassword,String company,String address){
        return mRetrofitService.uploadInformation(phoneNumber,token,name,idCardNo,servicePassword,company,address);
    }

    //上传签名图片
    public  Observable<NormalModel> uploadIdCardA(RequestBody phoneNumber, RequestBody token, RequestBody signImage){
        return mRetrofitService.uploadIdCardA(phoneNumber,token,signImage);
    }
}
