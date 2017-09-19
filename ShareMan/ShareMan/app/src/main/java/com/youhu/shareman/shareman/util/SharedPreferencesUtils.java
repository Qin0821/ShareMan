package com.youhu.shareman.shareman.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Touch on 2017/9/19.
 */

public class SharedPreferencesUtils {


    //获取本地已登录的账户和token
    public static String getPhoneNumber(Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences("User",0);
        String mPhoneNumber=sharedPreferences.getString("phoneNumber","");
        return mPhoneNumber;
    }


    public static String getToken(Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences("User",0);
        String mToken=sharedPreferences.getString("token","");
        return mToken;
    }
}
